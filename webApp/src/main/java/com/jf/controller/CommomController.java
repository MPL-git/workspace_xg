package com.jf.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.internal.util.codec.Base64;
import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.BaseDefine;
import com.jf.common.constant.Const;
import com.jf.common.utils.CaptchaUtils;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.MatrixToImageWriter;
import com.jf.common.utils.StringUtil;
import com.jf.entity.Information;
import com.jf.entity.InformationExample;
import com.jf.entity.MemberInfo;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.entity.SysParamCfg;
import com.jf.service.BankBranchService;
import com.jf.service.CommonService;
import com.jf.service.InformationService;
import com.jf.service.MemberInfoService;
import com.jf.service.NovaPlanService;
import com.jf.service.ProductTypeService;

import net.sf.json.JSONObject;

/**
  * @author  chenwf: 公共
  * @date 创建时间：2017年9月30日 下午3:15:57 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class CommomController extends BaseController{
	
	@Resource
	private InformationService informationService;
	@Resource
	private ProductTypeService productTypeService;
	@Resource
	private MemberInfoService memberInfoService;
	@Resource
	private NovaPlanService novaPlanService;
	@Resource
	private CommonService commonService;
	@Resource
	private BankBranchService bankBranchService;
	
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
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			String type = "";
			String id = null;
			String title = "";
			String content = "";
			String fileSite = "";
			String catalogId = "";
			Date beginDate = null;
			Date endDate = null;
			String openType = "";
			String mVerfiyFlag = "0";
			String payAmount = "";
			Integer memberId = getMemberId(request);
			if(memberId == null){
				if(reqDataJson.containsKey("memberId") && !StringUtil.isBlank(reqDataJson.getString("memberId"))){
					memberId = reqDataJson.getInt("memberId");
				}
			}
			if(reqDataJson.containsKey("id") && !StringUtil.isBlank(reqDataJson.getString("id"))) {
				id = reqDataJson.getString("id");
			}else if(reqDataJson.containsKey("type") && !StringUtil.isBlank(reqDataJson.getString("type"))){
				type = reqDataJson.getString("type");
			}
			
			if(memberId != null) {
				MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(memberId);
				beginDate = memberInfo.getNovaProjectBeginDate();
				endDate = memberInfo.getNovaProjectEndDate();
				mVerfiyFlag = memberInfo.getmVerfiyFlag();
				openType = novaPlanService.getNovaPlanOpenType(memberInfo, memberInfo.getId());
			}
			// id为空时，以type约定获取规则
			if(StringUtil.isEmpty(id)) {
				if("1".equals(type)){
					id = "159";
					title = "醒购隐私政策";
				}else if("2".equals(type)){
					id = "193";
					title = "定金不退预售协议";
				}else if("3".equals(type)){
					id = "163";
					title = "醒购用户协议";
				}else if("4".equals(type)){
					id = "164";
					title = "醒购服务条款";
				}else if("5".equals(type)){
					id = "165";
					title = "关于醒购";
				}else if("6".equals(type)){
					id = "187";
					title = "醒购平台评价规则";
				}else if("7".equals(type)){
					id = "211";
					title = "SVIP会员协议";
				}
			}
			if(!StringUtil.isEmpty(id)){
				InformationExample informationExample = new InformationExample();
				informationExample.createCriteria().andIdEqualTo(Integer.parseInt(id)).andStatusEqualTo("1").andDelFlagEqualTo("0");
				List<Information> informations = informationService.selectByExampleWithBLOBs(informationExample);
				if(CollectionUtils.isNotEmpty(informations)){
					content = informations.get(0).getContent();
					title = informations.get(0).getTitle();
					fileSite = StringUtil.getPic(informations.get(0).getFileSite(), "");
					catalogId = informations.get(0).getCatalogId().toString();
					if(catalogId.equals(Const.NOVA_PLAN_SHOPWNER_AGREEMENT_CATALOG_ID+"")){
						SysParamCfg cfg = DataDicUtil.getSysParamCfgModel("APP_SHOWOWNER_EQUITY_OPEN_AMOUNT", null);
						if(cfg != null){
							payAmount = cfg.getParamValue();
						}else{
							payAmount = "500";
						}
					}
				}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("content",content);
			map.put("title",title);
			map.put("fileSite",fileSite);
			map.put("catalogId",catalogId);
			map.put("beginDate",beginDate);
			map.put("endDate",endDate);
			map.put("openType",openType);
			map.put("currentDate",new Date());
			map.put("mVerfiyFlag",mVerfiyFlag);
			map.put("payAmount",payAmount);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	
	/**
	 * 生成登录图片验证码
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getCaptcha", method = RequestMethod.GET, produces = MediaType.IMAGE_GIF_VALUE)
	@ResponseBody
	public byte[] getCaptcha(HttpServletRequest request) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		String captcha = CaptchaUtils.getGifCaptcha(100, 38, 4, outputStream, 1000).toLowerCase();
		request.getSession().setAttribute(BaseDefine.CAPTCHA, captcha);

		return outputStream.toByteArray();
	}
	
	/**
	 * 生成登录图片验证码,小程序用
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getCaptchaXcx")
	@ResponseBody
	public String getCaptchaXcx(HttpServletRequest request) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		String captcha = CaptchaUtils.getGifCaptcha(100, 38, 4, outputStream, 1000).toLowerCase();
		request.getSession().setAttribute(BaseDefine.CAPTCHA, captcha);
		return  new String(Base64.encodeBase64(outputStream.toByteArray())) ;
	}
	
	/**
	 * 
	 * 方法描述 ：商品三级类目联动
	 * @author  chenwf: 
	 * @date 创建时间：2018年10月25日 下午3:20:29 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getProductTypeByParentId")
	@ResponseBody
	public ResponseMsg getProductTypeByParentId(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Integer productTypeId = 1;
			if(reqDataJson.containsKey("productTypeId") && !StringUtil.isBlank(reqDataJson.getString("productTypeId"))){
				productTypeId = reqDataJson.getInt("productTypeId");
			}
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			ProductTypeExample productTypeExample = new ProductTypeExample();
			productTypeExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0").andParentIdEqualTo(productTypeId);
			productTypeExample.setOrderByClause("ifnull(seq_no,99999),id desc");
			List<ProductType> pts = productTypeService.selectByExample(productTypeExample);
			if(CollectionUtils.isNotEmpty(pts)){
				for (ProductType pt : pts) {
					Map<String, Object> dataMap = new HashMap<String, Object>();
					dataMap.put("id",pt.getId());
					dataMap.put("name",pt.getName());
					dataList.add(dataMap);
				}
			}
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, dataList);
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
			Integer memberId = getMemberId(request);
			MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(memberId);
			String invitationCode = memberInfo.getInvitationCode();
			String reqPath = request.getSession().getServletContext().getRealPath("");
			String logoUrl = reqPath+"/applet/image/activity/novaPlan/invite/xg_nova_logo.png";
			String bgUrl = reqPath+"/applet/image/activity/novaPlan/invite/invitation_code_bg.png";
			String url = commonService.getCloumnLinkUrl(memberId.toString(), "7");
			BufferedImage image= MatrixToImageWriter.genBarcode(url, 500, 500, logoUrl);
			ByteArrayOutputStream imageOut = new ByteArrayOutputStream();
			ImageIO.write(image, "png", imageOut);
			byte[] qrCodeImageArratByte = imageOut.toByteArray();
			//获取背景图片
			BufferedImage bg= ImageIO.read(new File(bgUrl));
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
			String invitationRuelH5 = commonService.buildH5Page("2");
			Map<String, Object> map = new HashMap<>();
			map.put("imageArrayByte", imageArrayByte);
			map.put("qrCodeImageArratByte", qrCodeImageArratByte);
			map.put("invitationCode", invitationCode);
			map.put("invitationRuelH5", invitationRuelH5);
			map.put("invitationId", memberId);
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
}
