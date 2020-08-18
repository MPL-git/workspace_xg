package com.jf.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.MatrixToImageWriter;
import com.jf.common.utils.StringUtil;
import com.jf.controller.base.BaseController;
import com.jf.entity.Information;
import com.jf.entity.InformationExample;
import com.jf.entity.MemberInfo;
import com.jf.entity.ReportGdtLog;
import com.jf.entity.SysParamCfg;
import com.jf.service.BankBranchService;
import com.jf.service.CommonService;
import com.jf.service.InformationService;
import com.jf.service.MemberInfoService;
import com.jf.service.NovaPlanService;
import com.jf.service.ReportGdtLogService;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年9月30日 下午3:15:57 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class CommomController extends BaseController{
	
	@Resource
	private CommonService commonService;
	@Resource
	private ReportGdtLogService reportGdtLogService;
	@Resource
	private MemberInfoService memberInfoService;
	@Resource
	private BankBranchService bankBranchService;
	@Resource
	private NovaPlanService novaPlanService;
	@Resource
	private InformationService informationService;
	
	@RequestMapping(value = "/n/getSequence")
	@ResponseBody
	public ResponseMsg getSequence(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"type"};
			this.requiredStr(obj,request);
			String type = reqDataJson.getString("type");
			Integer sequence = null;
			if(type.equals("1")){//热云使用
				sequence = commonService.getSequence("hotCloudSeq");
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,sequence);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	@RequestMapping(value = "/api/y/getInvitationFriendPage")
	@ResponseBody
	public ResponseMsg getQrCode(HttpServletRequest request ,HttpServletResponse rep){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"memberId"};
			this.requiredStr(obj,request);
			Integer memberId = reqDataJson.getInt("memberId");
			MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(memberId);
			String invitationCode = memberInfo.getInvitationCode();
			String reqPath = request.getSession().getServletContext().getRealPath("");
			String logoUrl = reqPath+"/image/xg_nova_logo.png";
			String bgUrl = reqPath+"/image/invitation_code_bg.png";
			String url = commonService.getCloumnLinkUrl(memberId.toString(), "7");
			BufferedImage image= MatrixToImageWriter.genBarcode(url, 500, 500, logoUrl);
			BufferedImage bg= ImageIO.read(new File(bgUrl));//获取背景图片
			Graphics2D g=bg.createGraphics();
			int width=image.getWidth(null) > bg.getWidth() * 10/10? (bg.getWidth() * 10/10) : image.getWidth(null);
			int height=image.getHeight(null) > bg.getHeight() *10 /10? (bg.getHeight() * 10/10) : image.getWidth(null);
			g.drawImage(image,(bg.getWidth()- width)/2,(bg.getHeight()-height)/2,width,height,null);
			g.dispose();
			bg.flush();
			image.flush();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			boolean flag = ImageIO.write(bg, "png", out);
			byte[] imageArrayByte = out.toByteArray();
			ByteArrayOutputStream logoOut = new ByteArrayOutputStream();
			boolean flag2 = ImageIO.write(image, "png", logoOut);
			byte[] logoArrayByte = logoOut.toByteArray();
			String invitationRuelH5 = commonService.buildH5Page("2");
			Map<String, Object> map = new HashMap<>();
			map.put("imageArrayByte", imageArrayByte);
			map.put("logoArrayByte", logoArrayByte);
			map.put("invitationCode", invitationCode);
			map.put("invitationRuelH5", invitationRuelH5);
			map.put("qrCodeUrl", url);
			map.put("shareTitle", "送你500元大礼包，快来领取吧");
			map.put("shareContent", "注册即送500元大礼包，更有爆款商品新人秒杀价，点击领取");
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * 方法描述 ：广点通日志添加
	 * @author  chenwf: 
	 * @date 创建时间：2017年11月13日 下午3:22:31 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/addGdtLog")
	@ResponseBody
	public ResponseMsg addGdtLog(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"sourceClient","appId","advertiserId","imei"};
			this.requiredStr(obj,request);
			String sourceClient = reqDataJson.getString("sourceClient");
			Integer appId = reqDataJson.getInt("appId");
			Integer advertiserId = reqDataJson.getInt("advertiserId");
			String imei = reqDataJson.getString("imei");
			Integer memberId = null;
			if(!StringUtil.isBlank(reqDataJson.getString("memberId"))){
				memberId = reqDataJson.getInt("memberId");
			}
			ReportGdtLog reportGdtLog = new ReportGdtLog();
			reportGdtLog.setAdvertiserId(advertiserId);
			reportGdtLog.setSourceClient(sourceClient);
			reportGdtLog.setAppId(appId);
			reportGdtLog.setImei(imei);
			reportGdtLog.setMemberId(memberId);
			reportGdtLogService.saveModel(reportGdtLog);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * 方法描述 ：根据总行id+市获取所对应的支行
	 * @author  chenwf: 
	 * @date 创建时间：2017年11月13日 下午3:22:31 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getBankBranch")
	@ResponseBody
	public ResponseMsg getBankBranch(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"bankId","areaId"};
			this.requiredStr(obj,request);
			Integer bankId = reqDataJson.getInt("bankId");
			Integer areaId = reqDataJson.getInt("areaId");
			List<Map<String, Object>> dataList = bankBranchService.getBrandBranchInfos(bankId,areaId);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,dataList);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取信息管理内容
	 * @author  chenwf: 
	 * @date 创建时间：2018年10月25日 下午3:20:29 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getInformation")
	@ResponseBody
	public ResponseMsg getInformation(HttpServletRequest request){
		try {
			List<SysParamCfg> cfgs = DataDicUtil.getSysParamCfg("APP_SVIP_PRIVILEGE_PIC");
			List<Integer> idList = new ArrayList<Integer>();
			if(CollectionUtils.isNotEmpty(cfgs)){
				for (SysParamCfg sysParamCfg : cfgs) {
					String id = sysParamCfg.getParamName();
					if(!StringUtil.isEmpty(id)){
						idList.add(Integer.parseInt(id));
					}
				}
			}
			List<Map<String, Object>> dataList = new ArrayList<>();
			if(idList.size()>0){
				InformationExample informationExample = new InformationExample();
				informationExample.createCriteria().andIdIn(idList).andStatusEqualTo("1").andDelFlagEqualTo("0");
				List<Information> informations = informationService.selectByExampleWithBLOBs(informationExample);
				if(CollectionUtils.isNotEmpty(informations)){
					for(Information information:informations){
						Map<String, Object> dataMap = new HashMap<>();
						dataMap.put("id", information.getId());
						dataMap.put("title", information.getTitle());
						dataMap.put("content", information.getContent());
						for(SysParamCfg sysParamCfg : cfgs){
							if(!StringUtil.isEmpty(sysParamCfg.getParamName()) && information.getId().equals(Integer.parseInt(sysParamCfg.getParamName()))){
								String picPath = sysParamCfg.getParamValue();
								String pic = picPath.substring(0, picPath.lastIndexOf("."))+"-dtl"+sysParamCfg.getParamValue().substring(sysParamCfg.getParamValue().lastIndexOf("."));
								dataMap.put("pic", StringUtil.getPic(pic, ""));
								dataMap.put("picDesc", sysParamCfg.getParamDesc().substring(sysParamCfg.getParamDesc().indexOf("（")+1, sysParamCfg.getParamDesc().indexOf("）")));
							}
						}
						dataList.add(dataMap);
					}
				}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dataList", dataList);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, map);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
}
