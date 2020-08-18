package com.jf.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jf.common.base.BaseService;
import com.jf.entity.Area;
import com.jf.entity.AreaExample;
import com.jf.entity.MchtInfo;
import com.jf.entity.MemberExtend;
import com.jf.entity.MemberExtendExample;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.InformationMapper;
import com.jf.dao.MemberInfoCustomMapper;
import com.jf.dao.MemberInfoMapper;
import com.jf.dao.MemberNovaRecordCustomMapper;
import com.jf.dao.MemberNovaRecordMapper;
import com.jf.entity.Information;
import com.jf.entity.InformationExample;
import com.jf.entity.InformationExample.Criteria;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberInfoExample;
import com.jf.entity.MemberNovaRecord;
import com.jf.entity.MemberNovaRecordCustomExample;
import com.jf.entity.MemberNovaRecordCustomExample.MemberNovaRecordCustomCriteria;
import sun.net.util.IPAddressUtil;

@Service
@Transactional
public class MemberInfoService extends BaseService<MemberInfo,MemberInfoExample> {
	@Autowired
	private MemberInfoMapper memberInfoMapper;
	
	@Resource
	private MemberAccountService memberAccountService;
	
	@Resource
	private MemberGroupService memberGroupService;
	
	@Resource
	private MemberInfoCustomMapper memberInfoCustomMapper;
	
	@Resource
	private MemberNovaRecordMapper memberNovaRecordMapper;
	
	@Resource
	private MemberNovaRecordCustomMapper memberNovaRecordCustomMapper;
	
	@Resource
	private InformationMapper informationMapper;

	@Resource
	private SysParamCfgService sysParamCfgService;

	@Resource
	private MemberInfoService memberInfoService;

	@Resource
	private AreaService areaService;

	@Resource
	private MemberExtendService memberExtendService;
	@Autowired
	public void setMemberInfoMapper(MemberInfoMapper memberInfoMapper) {
		super.setDao(memberInfoMapper);
		this.memberInfoMapper = memberInfoMapper;
	}
	

	public MemberInfo findMemberById(Integer memberId) {
		
		return memberInfoMapper.selectByPrimaryKey(memberId);
	}

	public void update(MemberInfo memberInfo) {
		memberInfo.setUpdateDate(new Date());
		memberInfoMapper.updateByPrimaryKeySelective(memberInfo);
	}


	/**
	 * @MethodName operate
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月18日 上午9:26:12
	 */
	public void operate() {
		// TODO Auto-generated method stub
		//找出开通新星计划并且结束时间>now() and 结束时间-10天<=now() and 开始时间+4年>now()的会员店长
		List<Integer> list = memberInfoCustomMapper.selectByCustomExample();
		//批量更新会员开通新星计划日期
		if(!list.isEmpty()){
			memberInfoCustomMapper.updateNovaProjectDate(list);
			//合约日志更新日期
			MemberNovaRecordCustomExample memberNovaRecordCustomExample = new MemberNovaRecordCustomExample();
			MemberNovaRecordCustomCriteria criteria = memberNovaRecordCustomExample.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andMemberIdIn(list);
			List<MemberNovaRecord> memberNovaRecords = memberNovaRecordCustomMapper.selectGroupByExample(memberNovaRecordCustomExample);
			//获取新星计划最新栏目信息
			InformationExample informationExample = new InformationExample();
			informationExample.setOrderByClause(" id desc");
			Criteria criteria2 = informationExample.createCriteria();
			criteria2.andDelFlagEqualTo("0").andStatusEqualTo("1").andCatalogIdEqualTo(68);
			Information information = informationMapper.selectByExample(informationExample).get(0);
			//新增新星计划开通记录表
			Calendar cal = Calendar.getInstance();
			for (MemberNovaRecord memRecord : memberNovaRecords) {
				MemberNovaRecord memberNovaRecord = new MemberNovaRecord();
				memberNovaRecord.setMemberId(memRecord.getMemberId());
				memberNovaRecord.setInformationtId(information.getId());
				memberNovaRecord.setContractTime(new Date());
				cal.setTime(memRecord.getAgreementBeginDate());
				cal.add(Calendar.YEAR, 1);
				memberNovaRecord.setAgreementBeginDate(cal.getTime());
				cal.setTime(memRecord.getAgreementEndDate());
				cal.add(Calendar.YEAR, 1);
				memberNovaRecord.setAgreementEndDate(cal.getTime());
				memberNovaRecord.setCreateBy(memberNovaRecord.getMemberId());
				memberNovaRecord.setCreateDate(new Date());
				memberNovaRecord.setDelFlag("0");
				memberNovaRecordMapper.insertSelective(memberNovaRecord);
			}
		}
	}

	/**
	 * 根据IP获取地址
	 */
    public void getAddressByIp() {
    	try {
			//查询配置，判断是否有新会员要根据IP查询地址
			SysParamCfg sysParamCfg = new SysParamCfg();
			SysParamCfgExample e = new SysParamCfgExample();
			e.createCriteria().andParamCodeEqualTo("MAX_MEMBER_ID");
			List<SysParamCfg> sysParamCfgList = sysParamCfgService.selectByExample(e);
			if(sysParamCfgList!=null && sysParamCfgList.size()>0){
				sysParamCfg = sysParamCfgList.get(0);
			}
			MemberInfoExample memberInfoExample = new MemberInfoExample();
			memberInfoExample.setOrderByClause(" id desc limit 1");
			List<MemberInfo> memberInfos = memberInfoService.selectByExample(memberInfoExample);
			int memberId = 0;
			if(StringUtils.isNotEmpty(sysParamCfg.getParamValue())){
				memberId = Integer.parseInt(sysParamCfg.getParamValue());
			}
			//如果查询的最大值大于设置的值，则执行程序
			if(memberInfos.get(0).getId() > memberId){
				memberInfoExample = null;
				memberInfoExample = new MemberInfoExample();
				//设置每次只设置一万条范围内的数据
				memberInfoExample.setOrderByClause(" id ASC limit 100000");
				memberInfoExample.createCriteria().andIdGreaterThan(memberId).andRegIpIsNotNull().andRegAreaIsNull().andDelFlagEqualTo("0");
				List<MemberInfo> memberInfoList = memberInfoService.selectByExample(memberInfoExample);
				//当memberinfo的reg_area为空,且reg_ip不等于空，开始根据IP获取地址
				for (int i = 0;i<memberInfoList.size();i++) {
 					if(isIp(memberInfoList.get(i).getRegIp())){
						Map<String, Object> map = getAddressByIp(memberInfoList.get(i).getRegIp());
						if(!map.get("regArea").equals("未知城市")){
							AreaExample areaExample = new AreaExample();
							areaExample.createCriteria().andStatusEqualTo("A").andAreaNameLike("%"+map.get("province")+"%");
							List<Area> areas = areaService.selectByExample(areaExample);
							AreaExample areaExample1 = new AreaExample();
							areaExample1.createCriteria().andStatusEqualTo("A").andAreaNameLike("%"+map.get("city")+"%");
							List<Area> areas1 = areaService.selectByExample(areaExample1);
							if(!areas.isEmpty() && !areas1.isEmpty()){
								memberInfoList.get(i).setRegArea(areas.get(0).getAreaName()+" "+areas1.get(0).getAreaName());
								memberInfoList.get(i).setProvince(areas.get(0).getAreaId());
								memberInfoList.get(i).setCity(areas1.get(0).getAreaId());
							}else{
								memberInfoList.get(i).setRegArea("未知城市");
							}
						}else {
							memberInfoList.get(i).setRegArea((String) map.get("regArea"));
						}
						//省市字符串写入memberInfo的regArea字段
						memberInfoService.updateByPrimaryKeySelective(memberInfoList.get(i));
					}
 					if(i == memberInfoList.size()-1){
						//如果执行完毕，则将最大值memberID存入配置
						sysParamCfg.setParamValue(String.valueOf(memberInfoList.get(i).getId()));
						sysParamCfgService.updateByPrimaryKeySelective(sysParamCfg);
					}
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 地址解析API
	 */
	public static Map<String,Object> getAddressByIp(String ip) {
		String resout = "";
		Map<String,Object> map = new HashMap<>();
		try {
			if (isInner(ip) || "127.0.0.1".equals(ip)) {
				resout = "内网IP:" + ip;
			} else {
				String str = doGet("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
				/*String str = getJsonContent("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);*/
				if(StringUtils.isNotEmpty(str)){
					JsonObject jsonObject = (JsonObject) new JsonParser().parse(str);
						JsonElement data = jsonObject.get("data");
						int code = jsonObject.get("code").getAsInt();
						if (code == 0) {
							if(data.getAsJsonObject().get("region").getAsString().contains("XX")||data.getAsJsonObject().get("city").getAsString().contains("XX")){
								resout = "未知城市";
							}else{
								map.put("province",data.getAsJsonObject().get("region").getAsString());
								map.put("city",data.getAsJsonObject().get("city").getAsString());
							}
						} else {
							resout = "未知城市";
						}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			resout = "未知城市";
			map.put("regArea",resout);
			return map;
		}
		map.put("regArea",resout);
		return map;
	}

	public boolean isIp(String IP){//判断是否是一个IP
		boolean b = false;
		if(StringUtils.isNotEmpty(IP)){
			IP = trims(IP);
			if(IP.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")){
				String s[] = IP.split("\\.");
				if(Integer.parseInt(s[0])<255)
					if(Integer.parseInt(s[1])<255)
						if(Integer.parseInt(s[2])<255)
							if(Integer.parseInt(s[3])<255)
								b = true;
			}
		}else{
			return b;
		}
		return b;
	}

	public String trims(String IP){//去掉IP字符串前后所有的空格
		while(IP.startsWith(" ")){
			IP= IP.substring(1,IP.length()).trim();
		}
		while(IP.endsWith(" ")){
			IP= IP.substring(0,IP.length()-1).trim();
		}
		return IP;
	}

	/**
	 * 判断IP地址是不是内网IP
	 * @param isInner
	 * @return
	 */
	public static boolean isInner(String ip) {
		byte[] addr = IPAddressUtil.textToNumericFormatV4(ip);
		return internalIp(addr);
	}

	public static boolean internalIp(byte[] addr) {
		final byte b0 = addr[0];
		final byte b1 = addr[1];
		//10.x.x.x/8
		final byte SECTION_1 = 0x0A;
		//172.16.x.x/12
		final byte SECTION_2 = (byte) 0xAC;
		final byte SECTION_3 = (byte) 0x10;
		final byte SECTION_4 = (byte) 0x1F;
		//192.168.x.x/16
		final byte SECTION_5 = (byte) 0xC0;
		final byte SECTION_6 = (byte) 0xA8;
		switch (b0) {
			case SECTION_1:
				return true;
			case SECTION_2:
				if (b1 >= SECTION_3 && b1 <= SECTION_4) {
					return true;
				}
			case SECTION_5:
				switch (b1) {
					case SECTION_6:
						return true;
				}
			default:
				return false;

		}
	}

	public static String doGet(String httpurl) {
		HttpURLConnection connection = null;
		InputStream is = null;
		BufferedReader br = null;
		String result = null;// 返回结果字符串
		try {
			// 创建远程url连接对象
			URL url = new URL(httpurl);
			// 通过远程url连接对象打开一个连接，强转成httpURLConnection类
			connection = (HttpURLConnection) url.openConnection();
			// 设置连接方式：get
			connection.setRequestMethod("GET");
			// 设置连接主机服务器的超时时间：15000毫秒
			connection.setConnectTimeout(15000);
			// 设置读取远程返回的数据时间：60000毫秒
			connection.setReadTimeout(60000);
			// 发送请求
			connection.connect();
			// 通过connection连接，获取输入流
			if (connection.getResponseCode() == 200) {
				is = connection.getInputStream();
				// 封装输入流is，并指定字符集
				br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				// 存放数据
				StringBuffer sbf = new StringBuffer();
				String temp = null;
				while ((temp = br.readLine()) != null) {
					sbf.append(temp);
					sbf.append("\r\n");
				}
				result = sbf.toString();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			connection.disconnect();// 关闭远程连接
		}

		return result;
	}
}
