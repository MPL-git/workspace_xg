package com.jf.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.skd.KdApi;
import com.jf.common.ext.skd.LogisticInfo;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ExpressMapper;
import com.jf.dao.KdnWuliuInfoCustomMapper;
import com.jf.dao.KdnWuliuInfoMapper;
import com.jf.entity.CombineOrderCustom;
import com.jf.entity.Express;
import com.jf.entity.ExpressExample;
import com.jf.entity.KdnWuliuInfo;
import com.jf.entity.KdnWuliuInfoCustom;
import com.jf.entity.KdnWuliuInfoExample;
import com.jf.entity.dto.ZzySubscribeDTO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.jf.common.constant.Const.FLAG_FALSE;
import static com.jf.common.constant.Const.FLAG_TRUE;

@Service
@Transactional
public class KdnWuliuInfoService {

	private static Logger logger = LoggerFactory.getLogger(KdnWuliuInfoService.class);

	@Autowired
	private KdnWuliuInfoMapper dao;
	
	@Autowired
	private KdnWuliuInfoCustomMapper kdnWuliuInfoCustomMapper;

	@Resource
	private ExpressService expressService;
	@Resource
	private CombineOrderService combineOrderService;
	@Autowired
	private ExpressMapper expressDao;

	private static final List<Integer> SPECIAL_KDN_CODE = new ArrayList<Integer>(Arrays.asList(1,9));

	public void subscribe(String orderNumber, int expressId, String logisticCode){
		KdnWuliuInfo model = findByLogisticCode(expressId, logisticCode.trim());
		if(model == null){
			model = new KdnWuliuInfo();
			model.setExpressId(expressId);
			model.setLogisticCode(logisticCode.trim());
			model.setSubscribeStatus(Const.FLAG_FALSE);
			model.setSubscribeTime(new Date());
			save(model);
		}

		if(model.getSubscribeStatus().equals(Const.FLAG_FALSE)){
			Express express = expressService.findById(expressId);
			CombineOrderCustom combineOrderCustom = new CombineOrderCustom();
			ArrayList<String> kdnCodes = new ArrayList<>();
			kdnCodes.add("YD");
			kdnCodes.add("SF");
			if(kdnCodes.contains(express.getKdnCode()) && model.getSubOrderId() != null){
				//韵达,顺丰
				combineOrderCustom = combineOrderService.getOrderAddressInfo(model.getSubOrderId());
			}else if(express.getKdnCode().equals("JD")){
				//京东
				combineOrderCustom.setMerchantCode(model.getMerchantCode());
			}
			try {
				boolean b = KdApi.subscribe(orderNumber, express.getKdnCode(), logisticCode,combineOrderCustom);
				if(b){
					model.setSubscribeStatus(Const.FLAG_TRUE);
					model.setSubscribeTime(new Date());
					update(model);
				}else{
					model.setTryTimes(model.getTryTimes() + 1);
					update(model);
				}
			} catch (Exception e) {
				throw new BizException("订阅快递鸟物流信息出错");
			}
		}

	}

	public void zzySubscribe(HashMap<String, Object> paramMap) {
		List<ZzySubscribeDTO> selectByZZYList = this.findSelectByZZYList(paramMap);

		logger.info("扫描到的需订阅猪猪云物流信息的运单数：{}", selectByZZYList.size());
		try {
			for (ZzySubscribeDTO zzySubscribeDTO : selectByZZYList) {
				HashMap<String, Object> map = new HashMap<>();
				String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				if(!StringUtil.isEmpty(zzySubscribeDTO.getExpressCompanyCode())) {
					String resultStr = KdApi.zzySubscribe(zzySubscribeDTO);
					JSONObject jsonObject = JSON.parseObject(resultStr);
					String code = jsonObject.getString("code");
					map.put("kdnId", zzySubscribeDTO.getId());
					map.put("tryTimesAuto", "autoIncrement");
					map.put("updateDate", now);
					map.put("updateBy", 1);
					if (Objects.equals("1", code)) {
						map.put("subscribeStatus", FLAG_TRUE);
						map.put("subscribeTime", now);
					} else {
						map.put("subscribeStatus", FLAG_FALSE);
						map.put("subscribeFailedReason", jsonObject.get("msg"));
					}
				}else {
					map.put("kdnId", zzySubscribeDTO.getId());
					map.put("tryTimes", 8);
					map.put("updateDate", now);
					map.put("updateBy", 1);
					map.put("subscribeStatus", FLAG_FALSE);
					map.put("subscribeFailedReason","快递类型不支持");
				}
				kdnWuliuInfoCustomMapper.updateKdnByExample(map);
			}

		} catch (Exception e) {
			throw new BizException("订阅猪猪云物流信息出错");
		}
	}


	public KdnWuliuInfo update(LogisticInfo logisticInfo){
		Express express = expressService.findByKdnCode(logisticInfo.getShipperCode());
		if(express == null){
			logger.warn("不支持的快递公司，快递公司编码：" + logisticInfo.getShipperCode());
			return null;
		}

		KdnWuliuInfo kdnWuliuInfo = findByLogisticCode(express.getId(), logisticInfo.getLogisticCode());
		if(kdnWuliuInfo == null){
			logger.warn("未找到系统对应的物流信息，快递单号：" + logisticInfo.getLogisticCode());
			return null;
		}

		kdnWuliuInfo.setTractInfo(logisticInfo.getTraces());
		kdnWuliuInfo.setStatus(logisticInfo.getState());
		kdnWuliuInfo.setTractInfoSource("1");
		update(kdnWuliuInfo);

		return kdnWuliuInfo;
	}

	public KdnWuliuInfo findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public KdnWuliuInfo findByLogisticCode(int expressId, String logisticCode) {
		QueryObject queryObject = new QueryObject(1,1);
		queryObject.addQuery("expressId", expressId);
		queryObject.addQuery("logisticCode", logisticCode);
		List<KdnWuliuInfo> list = findList(queryObject);
		return list.size() > 0 ? list.get(0) : null;
	}

	public KdnWuliuInfo save(KdnWuliuInfo model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public KdnWuliuInfo update(KdnWuliuInfo model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKeySelective(model);
		return model;
	}

	public List<KdnWuliuInfo> findList(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	private KdnWuliuInfoExample builderQuery(QueryObject queryObject) {
		KdnWuliuInfoExample example = new KdnWuliuInfoExample();
		KdnWuliuInfoExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getQuery("expressId") != null){
			Integer expressId = queryObject.getQueryToInt("expressId");
			if(expressId.equals(1)){
				criteria.andExpressIdIn(SPECIAL_KDN_CODE);
			}else {
				criteria.andExpressIdEqualTo(expressId);
			}
		}
		if(queryObject.getQuery("tryTimesLessThan") != null){
			criteria.andTryTimesLessThan(queryObject.getQueryToInt("tryTimesLessThan"));
		}
		if(queryObject.getQuery("subscribeStatus") != null){
			criteria.andSubscribeStatusEqualTo(queryObject.getQueryToStr("subscribeStatus"));
		}
		if(queryObject.getQuery("logisticCode") != null){
			criteria.andLogisticCodeEqualTo(queryObject.getQueryToStr("logisticCode"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}

	public KdnWuliuInfo updateKdn(LogisticInfo logisticInfo, KdnWuliuInfo kdnWuliuInfo) {
		kdnWuliuInfo.setTractInfo(logisticInfo.getTraces());
		kdnWuliuInfo.setStatus(logisticInfo.getState());
		update(kdnWuliuInfo);
		return kdnWuliuInfo;
	}

	public List<KdnWuliuInfoCustom> getKdnLogisticsInfo() {
		
		return kdnWuliuInfoCustomMapper.getKdnLogisticsInfo();
	}
	
	public List<KdnWuliuInfoCustom> getKdnLogisticsInfoByDate(Map<String, Object> paramMap) {
		
		return kdnWuliuInfoCustomMapper.getKdnLogisticsInfoByDate(paramMap);
	}

	public List<ZzySubscribeDTO> findSelectByZZYList(Map<String, Object> paramMap) {
		return kdnWuliuInfoCustomMapper.findSelectByZZYList(paramMap);
	}

    public void updateKdnByExample(HashMap<String, Object> map) {
        kdnWuliuInfoCustomMapper.updateKdnByExample(map);
    }

	public void zzyBatchSubscribe(HashMap<String, Object> paramMap) {

		try {
			ExpressExample expressExample = new ExpressExample();
			expressExample.createCriteria().andDelFlagEqualTo("0").andZzyCodeIsNotNull();
			List<Express> expressList = expressDao.selectByExample(expressExample);
			for (Express express : expressList) {
				paramMap.put("expressId", express.getId());
				List<String> logisticCodeList = kdnWuliuInfoCustomMapper.findZZYBatchSubscribeList(paramMap);
				if(logisticCodeList != null && logisticCodeList.size() > 0){
					if (logisticCodeList.size() > 5000) {
						//每5000条订阅一次
						int size = logisticCodeList.size();
						int num = (size) % 5000 == 0 ? (size / 5000) : (size / 5000 + 1);
						int start = 0;
						int end = 0;
						List<String> itemsList = new ArrayList<String>();
						for (int i = 1; i <= num; i++) {
							end = (i * 5000) > size ? size : (i * 5000);
							start = (i - 1) * 5000;
							itemsList = Lists.newArrayList(logisticCodeList.subList(start, end));
							this.zzyResultReduction(express, itemsList);
						}
					} else {
						this.zzyResultReduction(express, logisticCodeList);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void zzyBatchAgainQuert(HashMap<String, Object> paramMap) {
		try {
			List<Map<String,Object>> zzyTagList = kdnWuliuInfoCustomMapper.findZZyBatchQuertList(paramMap);
			for (Map zzyMap : zzyTagList) {
				String zzyTag = zzyMap.get("zzyTag").toString();
				String resultStr = KdApi.zzyBatchAgainQuert(zzyTag,"1");

				logger.info("猪猪云任务名:{},页码:1,详情:{}", zzyTag, resultStr);

				if(!isJsonObject(resultStr)){
					continue;
				}
				JSONObject jsonObject = JSON.parseObject(resultStr);
				if(Objects.equals("1",jsonObject.getString("code"))){
					JSONObject jsonMsg = JSON.parseObject(jsonObject.getString("msg"));
					Integer totalPage = Integer.parseInt(jsonMsg.getString("totalpage"));
					JSONArray resList = jsonMsg.getJSONArray("list");
					if(resList.size()>0){
						this.parseZzyResList(resList,zzyMap);
					}
					if(1 < totalPage){
						for (int i = 2; i <= totalPage; i++) {
							String resultStrByPage = KdApi.zzyBatchAgainQuert(zzyTag,String.valueOf(i));

							logger.info("猪猪云任务名:{},页码:{},详情:{}", zzyTag, i, resultStr);

							if(!isJsonObject(resultStrByPage)){
								continue;
							}
							JSONObject jsonObjectByPage = JSON.parseObject(resultStrByPage);
							if(Objects.equals("1",jsonObjectByPage.getString("code"))){
								JSONObject jsonMsgByPage = JSON.parseObject(jsonObjectByPage.getString("msg"));
								JSONArray resListByPage = jsonMsgByPage.getJSONArray("list");
								if(resListByPage.size()>0){
									this.parseZzyResList(resListByPage,zzyMap);
								}
							}
						}
					}
				}
			}
		}catch (Exception e){
		    e.printStackTrace();
			throw new BizException("查询猪猪云物流信息出错");
		}

	}

	private void parseZzyResList(JSONArray resList, Map zzyMap) {
		for(int i=0;i<resList.size();i++){
			HashMap<String, Object> map = new HashMap<>();
			JSONObject resJson = resList.getJSONObject(i);
			String wuLiuZhuangTai = resJson.getString("wuliuzhuangtai");
			String xiangXiWuLiu = resJson.getString("xiangxiwuliu");
			if(Objects.equals("运输中",wuLiuZhuangTai)){
				map.put("status","2");
				String objJson = this.parseZzyResult(xiangXiWuLiu);
				map.put("tractInfo",objJson);
				map.put("tractInfoSource", "2");
			}else if (Objects.equals("已签收",wuLiuZhuangTai) || Objects.equals("代收",wuLiuZhuangTai)){
				map.put("status","3");
				String objJson = this.parseZzyResult(xiangXiWuLiu);
				map.put("tractInfo",objJson);
				map.put("tractInfoSource", "2");
			}else if (Objects.equals("无物流",wuLiuZhuangTai) || Objects.equals("疑似无物流",wuLiuZhuangTai)){
				map.put("status","1");
			}else if (Objects.equals("异常件",wuLiuZhuangTai)){
				map.put("status","4");
			}
			map.put("zzyTagEqualsTo",zzyMap.get("zzyTag"));
			map.put("expressId",zzyMap.get("expressId"));
			map.put("expressNo",resJson.getString("kddh"));
			map.put("updateDate",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			map.put("updateBy",1);
			logger.info("物流详情:{}",map.toString());
			this.updateKdnByExample(map);
		}
	}

	private void zzyResultReduction(Express express, List<String> itemsList) throws Exception{
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        StringBuffer stringBuffer = new StringBuffer();
        List<String> abnormalOrderList = new ArrayList<String>();
        Iterator<String> iterator = itemsList.iterator();
        while (iterator.hasNext()) {
            String logisticCode = iterator.next();
            String regex = logisticCode.substring(0, 1) + "{" + logisticCode.length() + "}";
            if(logisticCode.matches("^[a-z0-9A-Z]+$") && !logisticCode.matches("[a-zA-Z]+")
                    && !logisticCode.matches(regex) && logisticCode.length() > 5
                    && logisticCode.length() <= 30){
                //申通快递单号的长度，不可能小于8位,且为纯数字
                if(express.getId() == 4 && (logisticCode.length() < 8 || !logisticCode.matches("^[0-9]+$"))){
                    abnormalOrderList.add(logisticCode);
                    continue;
                }
				//55开头的申通单号长度必须等于13位数
                if(express.getId() == 4 && "55".equals(logisticCode.substring(0,2))  && logisticCode.length() != 13){
                    abnormalOrderList.add(logisticCode);
                    continue;
                }
                //京东快递单号的长度，不可能小于9位
                if(express.getId() == 20 && logisticCode.length() < 9){
                    abnormalOrderList.add(logisticCode);
                    continue;
                }
                //天天快递单号的长度，不可能小于12位
                if(express.getId() == 10 && logisticCode.length() < 12){
                    abnormalOrderList.add(logisticCode);
                    continue;
                }
                stringBuffer.append(logisticCode+",");
            }else{
                abnormalOrderList.add(logisticCode);
            }
        }
        itemsList.removeAll(abnormalOrderList);
        String logisticCodes = stringBuffer.toString();
        if(!StringUtil.isEmpty(logisticCodes)){
            String resultStr = KdApi.zzyBatchSubscribe(express.getZzyCode(), logisticCodes.substring(0, logisticCodes.length()-1));
			if(!isJsonObject(resultStr)){
				logger.info("猪猪云物流{}批次返回异常:{}", express.getZzyCode(), resultStr);
				return;
			}
            JSONObject jsonObject = JSON.parseObject(resultStr);
            String code = jsonObject.getString("code");
            HashMap<String, Object> map = new HashMap<>();
            map.put("expressId", express.getId());
            map.put("logisticCodes", itemsList);
            map.put("tryTimesAuto", "autoIncrement");
            map.put("updateDate", now);
            map.put("updateBy", 1);
            if (Objects.equals("1", code)) {
                map.put("subscribeStatus", FLAG_TRUE);
                map.put("subscribeTime", now);
                map.put("zzyTag", jsonObject.get("msg"));
            } else {
                String subscribeFailedReason = jsonObject.get("msg").toString();
                map.put("subscribeStatus", FLAG_FALSE);
                map.put("subscribeFailedReason", jsonObject.get("msg"));
                if("请删除无效单号，无效单号是：".equals(subscribeFailedReason.substring(0,13))){
                    abnormalOrderList.add(subscribeFailedReason.substring(13));
                }
            }
            kdnWuliuInfoCustomMapper.updateKdnByExample(map);
        }
        if(abnormalOrderList != null && abnormalOrderList.size() > 0){
			HashMap<String, Object> abnormalMap = new HashMap<>();
			abnormalMap.put("expressId", express.getId());
			abnormalMap.put("logisticCodes", abnormalOrderList);
			abnormalMap.put("status", "4");
			abnormalMap.put("updateDate", now);
			abnormalMap.put("updateBy", 1);
			kdnWuliuInfoCustomMapper.updateKdnByExample(abnormalMap);
		}
	}

	public String parseZzyResult(String xiangXiWuLiu) {
		String htmlStr = xiangXiWuLiu.replaceAll("&", "").replaceAll("<br>", "&");
		String regEx_html = "<[^>]+>";
		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签
		String[] wuLius = htmlStr.split("&");
		JSONArray jsonArray = new JSONArray();
		for (int i = wuLius.length - 1; i >= 0; i--) {
			if(!StringUtil.isEmpty(wuLius[i])){
				String[] wuLiuDetails = wuLius[i].split("\\|");
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("AcceptStation",wuLiuDetails[1].trim());
				jsonObject.put("Action",null);
				jsonObject.put("Location",null);
				jsonObject.put("AcceptTime",wuLiuDetails[0].trim());
				jsonArray.add(jsonObject);
			}
		}
		return JSON.toJSONString(jsonArray, SerializerFeature.WriteMapNullValue);
	}

	/**
	 * 判断字符串是否可以转化为json对象
	 * @param content
	 * @return
	 */
	public static boolean isJsonObject(String content) {
		if(StringUtils.isBlank(content)) return false;
		try {
			JSONObject jsonStr = JSONObject.parseObject(content);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
