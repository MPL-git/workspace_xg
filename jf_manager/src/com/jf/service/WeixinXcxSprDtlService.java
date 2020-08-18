package com.jf.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzs.common.utils.StringUtil;
import com.jf.common.constant.Const;
import com.jf.common.constant.SysConfig;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.WeChatSendUtil;
import com.jf.common.utils.WeixinUtil;
import com.jf.dao.MchtInfoMapper;
import com.jf.dao.ProductMapper;
import com.jf.dao.WeixinXcxSprDtlCustomMapper;
import com.jf.dao.WeixinXcxSprDtlMapper;
import com.jf.dao.WeixinXcxSprPlanMapper;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtInfoExample;
import com.jf.entity.Product;
import com.jf.entity.ProductExample;
import com.jf.entity.StateCode;
import com.jf.entity.WeixinXcxSprDtl;
import com.jf.entity.WeixinXcxSprDtlCustom;
import com.jf.entity.WeixinXcxSprDtlCustomExample;
import com.jf.entity.WeixinXcxSprDtlExample;
import com.jf.entity.WeixinXcxSprPlan;
import com.jf.entity.WeixinXcxSprPlanExample;

@Service
@Transactional
public class WeixinXcxSprDtlService extends BaseService<WeixinXcxSprDtl, WeixinXcxSprDtlExample> {

	@Autowired
	private WeixinXcxSprDtlMapper weixinXcxSprDtlMapper;
	
	@Autowired
	private WeixinXcxSprDtlCustomMapper weixinXcxSprDtlCustomMapper;
	
	@Autowired
	private WeixinXcxSprPlanMapper weixinXcxSprPlanMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private MchtInfoMapper mchtInfoMapper;
	
	@Autowired
	public void setWeixinXcxSprDtlMapper(WeixinXcxSprDtlMapper weixinXcxSprDtlMapper) {
		super.setDao(weixinXcxSprDtlMapper);
		this.weixinXcxSprDtlMapper = weixinXcxSprDtlMapper;
	}
	
	public int countByCustomExample(WeixinXcxSprDtlCustomExample example) {
		return weixinXcxSprDtlCustomMapper.countByCustomExample(example);
	}

	public List<WeixinXcxSprDtlCustom> selectByCustomExample(WeixinXcxSprDtlCustomExample example) {
		return weixinXcxSprDtlCustomMapper.selectByCustomExample(example);
	}

	public WeixinXcxSprDtlCustom selectByCustomPrimaryKey(Integer id) {
		return weixinXcxSprDtlCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(@Param("record") WeixinXcxSprDtl record, @Param("example") WeixinXcxSprDtlCustomExample example) {
		return weixinXcxSprDtlCustomMapper.updateByCustomExampleSelective(record, example);
	}
	
	public List<Map<String, Object>> getWeixinXcxSprDtlList(Map<String, Object> paramMap) {
		return weixinXcxSprDtlCustomMapper.getWeixinXcxSprDtlList(paramMap);
	}
	
	public int countWeixinXcxSprDtlList(Map<String, Object> paramMap) {
		return weixinXcxSprDtlCustomMapper.countWeixinXcxSprDtlList(paramMap);
	}
	
	public List<Map<String, Object>> getCountSubOrderList(Map<String, Object> paramMap) {
		return weixinXcxSprDtlCustomMapper.getCountSubOrderList(paramMap);
	}
	
	/**
	 * 
	 * @Title addWeixinXcxSprDtl   
	 * @Description TODO(添加链接)   
	 * @author pengl
	 * @date 2018年11月7日 上午10:19:16
	 */
	public Map<String, String> addWeixinXcxSprDtl(Map<String, String> paramMap) {
		Map<String, String> map = new HashMap<String, String>();
		String code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
		String msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		Date date = new Date();
		Integer staffID = Integer.parseInt(paramMap.get("staffID"));
		Integer weixinXcxSprChnlId = Integer.parseInt(paramMap.get("weixinXcxSprChnlId"));
		String sprPlanName = paramMap.get("sprPlanName");
		String sprType = paramMap.get("sprType");
		String sprValue = paramMap.get("sprValue");
		String pic = paramMap.get("pic");
		
		String sprTypeId = sprValue;
		//商品详情页
		if("15".equals(sprType)) {
			ProductExample productExample = new ProductExample();
			productExample.createCriteria().andDelFlagEqualTo("0").andCodeEqualTo(sprValue);
			List<Product> productList = productMapper.selectByExample(productExample);
			if(productList != null && productList.size() > 0) {
				sprTypeId = productList.get(0).getId().toString();
			}else {
				map.put("code", StateCode.JSON_AJAX_ERROR.getStateCode());
				map.put("msg", "商品ID不存在");
				return map;
			}
		}
		//商家店铺
		if("18".equals(sprType)) {
			MchtInfoExample mchtInfoExample = new MchtInfoExample();
			mchtInfoExample.createCriteria().andDelFlagEqualTo("0").andMchtCodeEqualTo(sprValue);
			List<MchtInfo> mchtInfoList = mchtInfoMapper.selectByExample(mchtInfoExample);
			if(mchtInfoList != null && mchtInfoList.size() > 0) {
				sprTypeId = mchtInfoList.get(0).getId().toString();
			}else {
				map.put("code", StateCode.JSON_AJAX_ERROR.getStateCode());
				map.put("msg", "商家序号不存在");
				return map;
			}
		}
		
		WeixinXcxSprPlanExample weixinXcxSprPlanExample = new WeixinXcxSprPlanExample();
		weixinXcxSprPlanExample.createCriteria().andDelFlagEqualTo("0")
			.andSprChnlIdEqualTo(weixinXcxSprChnlId)
			.andSprPlanNameEqualTo(sprPlanName);
		List<WeixinXcxSprPlan> weixinXcxSprPlanList = weixinXcxSprPlanMapper.selectByExample(weixinXcxSprPlanExample);
		WeixinXcxSprPlan weixinXcxSprPlan = new WeixinXcxSprPlan();
		if(weixinXcxSprPlanList != null && weixinXcxSprPlanList.size() > 0) {
			weixinXcxSprPlan = weixinXcxSprPlanList.get(0);
		}else {
			weixinXcxSprPlan.setSprChnlId(weixinXcxSprChnlId);
			weixinXcxSprPlan.setSprPlanName(sprPlanName);
			weixinXcxSprPlan.setCreateBy(staffID);
			weixinXcxSprPlan.setCreateDate(date);
			weixinXcxSprPlan.setDelFlag("0");
			weixinXcxSprPlanMapper.insertSelective(weixinXcxSprPlan);
		}
		
		WeixinXcxSprDtl weixinXcxSprDtl = new WeixinXcxSprDtl();
		weixinXcxSprDtl.setSprPlanId(weixinXcxSprPlan.getId());
		weixinXcxSprDtl.setSprType(sprType);
		weixinXcxSprDtl.setSprValue(sprTypeId);
		weixinXcxSprDtl.setIsEffect("1");
		weixinXcxSprDtl.setPic(pic);
		weixinXcxSprDtl.setCreateBy(staffID);
		weixinXcxSprDtl.setCreateDate(date);
		weixinXcxSprDtl.setDelFlag("0");
		weixinXcxSprDtlMapper.insertSelective(weixinXcxSprDtl);
		
		String sprQrCode = getWXACodeUnlimit(weixinXcxSprDtl, sprTypeId);
		String sprUrl = Const.WXA_PAGE+"?sprParam=dtlid_"+weixinXcxSprDtl.getId()+"&sprType="+weixinXcxSprDtl.getSprType();
		if(!StringUtil.isEmpty(weixinXcxSprDtl.getSprValue())) {
			sprUrl += "&sprValue="+weixinXcxSprDtl.getSprValue();
		}
		weixinXcxSprDtl.setSprUrl(sprUrl);
		weixinXcxSprDtl.setSprQrCode(sprQrCode.replace("\\", "/"));
		weixinXcxSprDtlMapper.updateByPrimaryKeySelective(weixinXcxSprDtl);
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * 
	 * @Title getWXACodeUnlimit   
	 * @Description TODO(添加链接)   
	 * @author pengl
	 * @date 2018年11月7日 上午10:19:16
	 */
	public String getWXACodeUnlimit(WeixinXcxSprDtl weixinXcxSprDtl, String sprTypeId) {
		String accessToken = WeixinUtil.getXcxAccessToken();
		Map<String, String> lineColorMap = new HashMap<String, String>();
		lineColorMap.put("r", "0");
		lineColorMap.put("g", "0");
		lineColorMap.put("b", "0");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String scene = "dtlid_"+weixinXcxSprDtl.getId()+"-"+weixinXcxSprDtl.getSprType();
		if(!StringUtil.isEmpty(sprTypeId)) {
			scene += "-"+sprTypeId;
		}
		paramMap.put("scene", scene);
		paramMap.put("page", Const.WXA_PAGE);
		paramMap.put("width", 430);
		paramMap.put("auto_color", false);
		paramMap.put("line_color", lineColorMap);
		paramMap.put("is_hyaline", false);
		
		String fileName = String.valueOf(new Date().getTime());
		String targetPath = FileUtil.getRandomFileName(fileName , 10, 0)+".jpg";
		WeChatSendUtil.sendPost(Const.WXA_CODE_UNLIMIT+accessToken, paramMap, SysConfig.defaultPath.concat(targetPath));
		
		return targetPath;
	}
	
	
}
