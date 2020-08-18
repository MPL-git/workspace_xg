package com.jf.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.constant.SysConfig;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.HttpUtil;
import com.jf.entity.AppealOrderCustom;
import com.jf.entity.AppealOrderCustomExample;
import com.jf.entity.BusinessCirclesAppealOrder;
import com.jf.entity.BusinessCirclesAppealOrderCustom;
import com.jf.entity.BusinessCirclesAppealOrderCustomExample;
import com.jf.entity.BusinessCirclesAppealOrderPic;
import com.jf.entity.BusinessCirclesAppealOrderPicExample;
import com.jf.entity.CustomerServiceOrderCustom;
import com.jf.entity.CustomerServiceOrderCustomExample;
import com.jf.entity.CustomerServiceRecords;
import com.jf.entity.CustomerServiceRecordsExample;
import com.jf.entity.CustomerServiceRecordsFile;
import com.jf.entity.CustomerServiceRecordsFileExample;
import com.jf.entity.InterventionOrderCustom;
import com.jf.entity.InterventionOrderCustomExample;
import com.jf.entity.MchtInfoCustom;
import com.jf.entity.MchtInfoCustomExample;
import com.jf.entity.MchtProductBrand;
import com.jf.entity.MchtProductBrandExample;
import com.jf.entity.MemberInfoCustom;
import com.jf.entity.MemberInfoCustomExample;
import com.jf.entity.ProductBrandCustom;
import com.jf.entity.ProductBrandCustomExample;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.entity.SubOrderCustom;
import com.jf.entity.SubOrderCustomExample;
import com.jf.entity.SysStaffInfoCustom;
import com.jf.entity.SysStaffInfoCustomExample;
import com.jf.service.AppealOrderService;
import com.jf.service.BusinessCirclesAppealOrderPicService;
import com.jf.service.BusinessCirclesAppealOrderService;
import com.jf.service.CustomerServiceOrderService;
import com.jf.service.CustomerServiceRecordsFileService;
import com.jf.service.CustomerServiceRecordsService;
import com.jf.service.InterventionOrderService;
import com.jf.service.MchtInfoService;
import com.jf.service.MchtProductBrandService;
import com.jf.service.MemberInfoService;
import com.jf.service.PlatformContactService;
import com.jf.service.ProductBrandService;
import com.jf.service.SubOrderService;
import com.jf.service.SysStaffInfoService;
import com.jf.vo.Page;

@Controller
public class BusinessCirclesAppealOrderController extends BaseController {
	
	private static final Log logger = LogFactory.getLog(BusinessCirclesAppealOrderController.class);
	
	private static final long serialVersionUID = 1L;
		
	@Resource
	private BusinessCirclesAppealOrderService businessCirclesAppealOrderService;
	
	@Resource
	private BusinessCirclesAppealOrderPicService businessCirclesAppealOrderPicService;
	
	@Resource
	private PlatformContactService platformContactService;
	
	@Resource
	private SysStaffInfoService sysStaffInfoService;
	
	@Resource
	private MemberInfoService memberInfoService;
	
	@Resource
	private MchtInfoService mchtInfoService;
	
	@Resource
	private SubOrderService subOrderService ;
	
	@Resource
	private CustomerServiceOrderService customerServiceOrderService ;
	
	@Resource
	private InterventionOrderService interventionOrderService;
	
	@Resource
	private AppealOrderService appealOrderService;
	
	@Resource
	private MchtProductBrandService mchtProductBrandService;
	
	@Resource
	private ProductBrandService productBrandService;
	
	@Resource
	private CustomerServiceRecordsService customerServiceRecordsService;
	
	@Resource
	private CustomerServiceRecordsFileService customerServiceRecordsFileService;
	
	//我创建的工商投诉
	@RequestMapping("/businessCirclesAppealOrder/mybusinessCirclesAppealOrderList.shtml")
	public ModelAndView myWorkList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/businessCirclesAppealOrder/mybusinessCirclesAppealOrderList");
		m.addObject("staffidList",businessCirclesAppealOrderService.getstaffidList());
		return m;
	}
	
	//我创建的工商投诉数据列表
	@ResponseBody
	@RequestMapping("/businessCirclesAppealOrder/businessCirclesAppealOrderdata.shtml")
	public Map<String, Object> businessCirclesAppealOrderlist(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<BusinessCirclesAppealOrderCustom> dataList = null;
		Integer totalCount = 0;
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			Integer staffId = Integer.valueOf(staffBean.getStaffID());
			
			BusinessCirclesAppealOrderCustomExample businessCirclesAppealOrderCustomExample=new BusinessCirclesAppealOrderCustomExample();
			BusinessCirclesAppealOrderCustomExample.BusinessCirclesAppealOrderCustomCriteria criteria=businessCirclesAppealOrderCustomExample.createCriteria();
			criteria.andDelFlagEqualTo("0").andCreateByEqualTo(staffId);
			businessCirclesAppealOrderCustomExample.setOrderByClause("create_date desc");
			if (!StringUtil.isEmpty(request.getParameter("startconsumerAppealDate")) && !StringUtil.isEmpty(request.getParameter("endconsumerAppealDate"))) {
				criteria.andConsumerAppealDateBetween(sdf.parse(request.getParameter("startconsumerAppealDate")+" 00:00:00"), sdf.parse(request.getParameter("endconsumerAppealDate")+" 23:59:59"));				
			}
			if (!StringUtil.isEmpty(request.getParameter("appealOrderType"))) {
				if (request.getParameter("appealOrderType").equals("1")) {
					criteria.andAppealOrderTypeEqualTo("1");
				}
				if (request.getParameter("appealOrderType").equals("2")) {
					criteria.andAppealOrderTypeEqualTo("2");
				}

			}
			if (!StringUtil.isEmpty(request.getParameter("appealStatus"))) {
				String appealStatus=request.getParameter("appealStatus");
				if (appealStatus.equals("0")) {
					criteria.andAppealStatusEqualTo("0");	
				}else if (appealStatus.equals("1")) {
					criteria.andAppealStatusEqualTo("1");
				}else if (appealStatus.equals("2")) {
					criteria.andAppealStatusEqualTo("2");
				}else if (appealStatus.equals("3")) {
					criteria.andAppealStatusEqualTo("3");
				}else if (appealStatus.equals("4")) {
					criteria.andAppealStatusEqualTo("4");
				}else if (appealStatus.equals("5")) {
					criteria.andAppealStatusEqualTo("5");
				}
			}
			if (!StringUtil.isEmpty(request.getParameter("memberIds"))) {
				criteria.andmemberIdEqualTo(request.getParameter("memberIds"));
			}
			if (!StringUtil.isEmpty(request.getParameter("subOrderCode"))) {
				criteria.andSubOrderCodeEqualTo(request.getParameter("subOrderCode"));
			}
			if (!StringUtil.isEmpty(request.getParameter("staffId"))) {
				criteria.andCustomerServiceHandlerEqualTo(Integer.valueOf(request.getParameter("staffId")));
			}
			if (!StringUtil.isEmpty(request.getParameter("companyName"))) {
				criteria.andcompanyNameEqualTo("%"+request.getParameter("companyName")+"%");
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				criteria.andmchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			if (!StringUtil.isEmpty(request.getParameter("registrationNumber"))) {
				criteria.andRegistrationNumberEqualTo(request.getParameter("registrationNumber"));
			}
			if (!StringUtil.isEmpty(request.getParameter("createby"))) {
				criteria.andCreateByEqualTo(Integer.valueOf(request.getParameter("createby")));
			}
			businessCirclesAppealOrderCustomExample.setLimitStart(page.getLimitStart());
			businessCirclesAppealOrderCustomExample.setLimitSize(page.getLimitSize());
			dataList=businessCirclesAppealOrderService.selectByCustomExample(businessCirclesAppealOrderCustomExample);
			totalCount=businessCirclesAppealOrderService.countByCustomExample(businessCirclesAppealOrderCustomExample);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	
	        //创建工商投诉
			@RequestMapping("/businessCirclesAppealOrder/addbusinessCirclesAppealOrder.shtml")
			public ModelAndView addbusinessCirclesAppealOrder(HttpServletRequest request) {
				ModelAndView m = new ModelAndView("/businessCirclesAppealOrder/addbusinessCirclesAppealOrder");		
				return m;
			}
			
	
			
			    //添加工商投诉数据
				@RequestMapping(value = "/businessCirclesAppealOrder/editbusinessCirclesAppealOrder.shtml")
				@ResponseBody
			 	public ModelAndView editbusinessCirclesAppealOrder(HttpServletRequest request,BusinessCirclesAppealOrder businessCirclesAppealOrder,String pics){
					String rtPage = "/success/success";
					Map<String, Object> resMap = new HashMap<String, Object>();
					String code = null;
					String msg =null;
					try {
						StaffBean staffBean = this.getSessionStaffBean(request);
						int staffId=Integer.valueOf(staffBean.getStaffID());
						
						businessCirclesAppealOrder.setAppealStatus("0");
						businessCirclesAppealOrder.setDelFlag("0");
						businessCirclesAppealOrder.setCreateBy(staffId);
						businessCirclesAppealOrder.setCreateDate(new Date());
						businessCirclesAppealOrderService.insertbusinessCirclesAppealOrder(businessCirclesAppealOrder,pics);				
	
						SysStaffInfoCustomExample sysStaffInfoCustomExample=new SysStaffInfoCustomExample();
						sysStaffInfoCustomExample.createCriteria().andStatusEqualTo("A").andIsManagementEqualTo("1").andOrgIdEqualTo(63);
						List<SysStaffInfoCustom> sysStaffInfoCustoms=sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
                        if (sysStaffInfoCustoms!=null && sysStaffInfoCustoms.size()>0) {
                        	for (SysStaffInfoCustom sysStaffInfoCustom : sysStaffInfoCustoms) {
                        		JSONObject param=new JSONObject();
        						JSONObject reqData=new JSONObject();
        						reqData.put("mobile", sysStaffInfoCustom.getMobilePhone());
        						reqData.put("content", "【醒购】您有新的工商投诉单待处理，工商登记编号："+businessCirclesAppealOrder.getRegistrationNumber()+"请及时登录平台进行处理;");
        						reqData.put("smsType", "4");
        						param.put("reqData", reqData);
        						JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
        						if(!"0000".equals(result.getString("returnCode"))){
        							logger.info("短信发送用户失败！！！！！");
        						}
							}
							
						}
						
						code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
						msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
					} catch (Exception e) {
						  e.printStackTrace();
						 code = StateCode.JSON_AJAX_ERROR.getStateCode();
						 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
					}
					resMap.put(this.JSON_RESULT_CODE, code);
					resMap.put(this.JSON_RESULT_MESSAGE, msg);
					return new ModelAndView(rtPage, resMap);
				}
	
				
				//待领取的工商投诉
				@RequestMapping("/service/businessCirclesAppeal/toReceiveAppeal.shtml")
				public ModelAndView toReceiveAppeal(HttpServletRequest request) {
					ModelAndView m = new ModelAndView("/businessCirclesAppealOrder/unclaimedbusinessCirclesAppealOrderList");
					return m;
				}
				
				//待领取的工商投诉数据列表
				@ResponseBody
				@RequestMapping("/businessCirclesAppealOrder/unclaimedbusinessCirclesAppealOrderdata.shtml")
				public Map<String, Object> unclaimedbusinessCirclesAppealOrderdata(HttpServletRequest request, Page page) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					List<BusinessCirclesAppealOrderCustom> dataList = null;
					Integer totalCount = 0;
					try {
						BusinessCirclesAppealOrderCustomExample businessCirclesAppealOrderCustomExample=new BusinessCirclesAppealOrderCustomExample();
						BusinessCirclesAppealOrderCustomExample.BusinessCirclesAppealOrderCustomCriteria criteria=businessCirclesAppealOrderCustomExample.createCriteria();
						criteria.andDelFlagEqualTo("0").andAppealStatusEqualTo("0");
						businessCirclesAppealOrderCustomExample.setOrderByClause("create_date desc");
						
						businessCirclesAppealOrderCustomExample.setLimitStart(page.getLimitStart());
						businessCirclesAppealOrderCustomExample.setLimitSize(page.getLimitSize());
						dataList=businessCirclesAppealOrderService.selectByCustomExample(businessCirclesAppealOrderCustomExample);
						totalCount=businessCirclesAppealOrderService.countByCustomExample(businessCirclesAppealOrderCustomExample);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					resMap.put("Rows", dataList);
					resMap.put("Total", totalCount);
					return resMap;
				}
				
				
				//客服处理人领取
				@RequestMapping(value = "/businessCirclesAppealOrder/kfbusinessCirclesAppealOrder.shtml")
				@ResponseBody
				public Map<String, Object> kfbusinessCirclesAppealOrder(HttpServletRequest request) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					resMap.put("returnCode", "0000");
					resMap.put("returnMsg", "成功");
					try {
						StaffBean staffBean = this.getSessionStaffBean(request);
						int staffId=Integer.valueOf(staffBean.getStaffID());
						
						String id=request.getParameter("id");
						BusinessCirclesAppealOrder businessCirclesAppealOrder=businessCirclesAppealOrderService.selectByPrimaryKey(Integer.valueOf(id));
						businessCirclesAppealOrder.setAppealStatus("1");
						businessCirclesAppealOrder.setCustomerServiceHandler(staffId);
						businessCirclesAppealOrder.setUpdateBy(staffId);
						businessCirclesAppealOrder.setUpdateDate(new Date());
						businessCirclesAppealOrderService.updateByPrimaryKeySelective(businessCirclesAppealOrder);
						
						
					} catch (Exception e) {
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", e.getMessage());
						e.printStackTrace();
					}
					return resMap;
				}
				
				
				//客服跟进工商投诉
				@RequestMapping("/service/businessCirclesAppeal/serviceFollow.shtml")
				public ModelAndView serviceFollow(HttpServletRequest request) {
					ModelAndView m = new ModelAndView("/businessCirclesAppealOrder/kfbusinessCirclesAppealOrderList");
					m.addObject("staffidList",businessCirclesAppealOrderService.getstaffidList());
					return m;
				}
				
				
				//客服跟进工商投诉数据列表
				@ResponseBody
				@RequestMapping("/businessCirclesAppealOrder/kfbusinessCirclesAppealOrderdata.shtml")
				public Map<String, Object> kfbusinessCirclesAppealOrderdata(HttpServletRequest request, Page page) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					List<BusinessCirclesAppealOrderCustom> dataList = null;
					Integer totalCount = 0;
					try {
						List<String> appealStatusList=new ArrayList<String>();
						appealStatusList.add("1");
						appealStatusList.add("5");
						BusinessCirclesAppealOrderCustomExample businessCirclesAppealOrderCustomExample=new BusinessCirclesAppealOrderCustomExample();
						BusinessCirclesAppealOrderCustomExample.BusinessCirclesAppealOrderCustomCriteria criteria=businessCirclesAppealOrderCustomExample.createCriteria();
						criteria.andDelFlagEqualTo("0").andAppealStatusIn(appealStatusList);
						businessCirclesAppealOrderCustomExample.setOrderByClause("create_date desc");
						if (!StringUtil.isEmpty(request.getParameter("startconsumerAppealDate")) && !StringUtil.isEmpty(request.getParameter("endconsumerAppealDate"))) {
							criteria.andConsumerAppealDateBetween(sdf.parse(request.getParameter("startconsumerAppealDate")+" 00:00:00"), sdf.parse(request.getParameter("endconsumerAppealDate")+" 23:59:59"));				
						}
						if (!StringUtil.isEmpty(request.getParameter("appealOrderType"))) {
							if (request.getParameter("appealOrderType").equals("1")) {
								criteria.andAppealOrderTypeEqualTo("1");
							}
							if (request.getParameter("appealOrderType").equals("2")) {
								criteria.andAppealOrderTypeEqualTo("2");
							}

						}
						if (!StringUtil.isEmpty(request.getParameter("appealStatus"))) {
							String appealStatus=request.getParameter("appealStatus");
							if (appealStatus.equals("1")) {
								criteria.andAppealStatusEqualTo("1");	
							}else if (appealStatus.equals("2")) {
								criteria.andAppealStatusEqualTo("2");
							}
							
						}
						if (!StringUtil.isEmpty(request.getParameter("memberIds"))) {
							criteria.andmemberIdEqualTo(request.getParameter("memberIds"));
						}
						if (!StringUtil.isEmpty(request.getParameter("companyName"))) {
							criteria.andcompanyNameEqualTo("%"+request.getParameter("companyName")+"%");
						}
						if (!StringUtil.isEmpty(request.getParameter("staffId"))) {
							criteria.andCustomerServiceHandlerEqualTo(Integer.valueOf(request.getParameter("staffId")));
						}
						businessCirclesAppealOrderCustomExample.setLimitStart(page.getLimitStart());
						businessCirclesAppealOrderCustomExample.setLimitSize(page.getLimitSize());
						dataList=businessCirclesAppealOrderService.selectByCustomExample(businessCirclesAppealOrderCustomExample);
						totalCount=businessCirclesAppealOrderService.countByCustomExample(businessCirclesAppealOrderCustomExample);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					resMap.put("Rows", dataList);
					resMap.put("Total", totalCount);
					return resMap;
				}
				
				
				//查看工商投诉原件
				@RequestMapping("/businessCirclesAppealOrder/businessCirclesAppealOrderPic.shtml")
				public String businessCirclesAppealOrderPic(Model model,HttpServletRequest request) {
					   BusinessCirclesAppealOrderPicExample businessCirclesAppealOrderPicExample=new BusinessCirclesAppealOrderPicExample();
					   businessCirclesAppealOrderPicExample.createCriteria().andDelFlagEqualTo("0").andBusinessCirclesAppealOrderIdEqualTo(Integer.valueOf(request.getParameter("id")));
					   List<BusinessCirclesAppealOrderPic> businessCirclesAppealOrderPics=businessCirclesAppealOrderPicService.selectByExample(businessCirclesAppealOrderPicExample);
					   List<Map<String, Object>> businessCirclesAppealOrderPiclist=new ArrayList<Map<String, Object>>();
					   for (BusinessCirclesAppealOrderPic businessCirclesAppealOrderPic : businessCirclesAppealOrderPics) {
						   Map<String, Object> pic=new HashMap<String, Object>();
						   pic.put("PICTURE_PATH", businessCirclesAppealOrderPic.getPic());
						   businessCirclesAppealOrderPiclist.add(pic);
					  } 
					   model.addAttribute("businessCirclesAppealOrderPiclist", businessCirclesAppealOrderPiclist);
					
					return "/businessCirclesAppealOrder/viewbusinessCirclesAppealOrderPic";
				}
				
				
				//客服补齐信息界面
				@RequestMapping("/businessCirclesAppealOrder/MakupformationbusinessCirclesAppealOrder.shtml")
				public ModelAndView MakupformationbusinessCirclesAppealOrder(HttpServletRequest request) {
					ModelAndView m = new ModelAndView("/businessCirclesAppealOrder/makeupbusinessCirclesAppealOrder");
					String id=request.getParameter("id");
					BusinessCirclesAppealOrder businessCirclesAppealOrder=businessCirclesAppealOrderService.selectByPrimaryKey(Integer.valueOf(id));
					if (businessCirclesAppealOrder.getMchtIds()!=null) {
						List<Integer> mchtIds=new ArrayList<Integer>();	
						String[] strings=businessCirclesAppealOrder.getMchtIds().split(",");
						for (String string : strings) {
							mchtIds.add(Integer.valueOf(string));
						}
						MchtInfoCustomExample mchtInfoCustomExample=new MchtInfoCustomExample();
						mchtInfoCustomExample.createCriteria().andDelFlagEqualTo("0").andIdIn(mchtIds);
						List<MchtInfoCustom> mchtInfoCustoms=mchtInfoService.selectByExample(mchtInfoCustomExample);
						String MchtCodeGroup = "";
						for (MchtInfoCustom mchtInfoCustom : mchtInfoCustoms) {
							if("".equals(MchtCodeGroup)) {
								MchtCodeGroup = mchtInfoCustom.getMchtCode().toString();
							}else {
								MchtCodeGroup += "," + mchtInfoCustom.getMchtCode().toString();
							}	
							
						}
						
						m.addObject("MchtCodeGroup", MchtCodeGroup);	
					}
						
					m.addObject("businessCirclesAppealOrder", businessCirclesAppealOrder);
					
					return m;
				}
				
				//检测会员信息
				@RequestMapping(value ="/businessCirclesAppealOrder/memberIdlist.shtml")
				public ModelAndView memberIdlist(HttpServletRequest request) {
					    ModelAndView m = new ModelAndView();
						MemberInfoCustomExample memberInfoCustomExample=new MemberInfoCustomExample();
						MemberInfoCustomExample.MemberInfoCustomCriteria memberInfoCustomCriteria=memberInfoCustomExample.createCriteria();
						memberInfoCustomCriteria.andDelFlagEqualTo("0");
						List<Integer> memberidList = new ArrayList<Integer>();
						String[] memberIds=request.getParameter("memberIds").split(",");
						for (String memberId : memberIds) {
							 Pattern pattern = Pattern.compile("[0-9]*");
					         Matcher isNum = pattern.matcher(memberId);
							if (isNum.matches() && memberId.length()<11) {
							   memberidList.add(Integer.valueOf(memberId));			
							}else {
							   memberidList.add(0);
							}
						}
						memberInfoCustomCriteria.andIdIn(memberidList);
						List<MemberInfoCustom> memberInfoCustoms=memberInfoService.selectMemberInfoCustomByExample(memberInfoCustomExample);
						if (memberInfoCustoms.size()<=0) {
						    m.setViewName("/businessCirclesAppealOrder/nomemberInfoList");
							
						}else {
							String memberIdGroup = "";
							for (MemberInfoCustom memberInfoCustom : memberInfoCustoms) {
								if("".equals(memberIdGroup)) {
									memberIdGroup = memberInfoCustom.getId().toString();
								}else {
									memberIdGroup += "," + memberInfoCustom.getId().toString();
								}	
							}
							m.addObject("memberIdGroup", memberIdGroup);
							m.setViewName("/businessCirclesAppealOrder/memberInfoList");	
							
						}	
					return m;
				}
				
				//获取会员id列表表数据
				@ResponseBody
				@RequestMapping(value = "/businessCirclesAppealOrder/memberinfodata.shtml")
				public Map<String, Object> memberinfodata(HttpServletRequest request, Page page){
					Map<String, Object> resMap = new HashMap<String, Object>();
					List<MemberInfoCustom> dataList = new ArrayList<MemberInfoCustom>();
					Integer totalCount = 0;
					try {
						MemberInfoCustomExample memberInfoCustomExample=new MemberInfoCustomExample();
						MemberInfoCustomExample.MemberInfoCustomCriteria memberInfoCustomCriteria=memberInfoCustomExample.createCriteria();
						memberInfoCustomCriteria.andDelFlagEqualTo("0");
						if (!StringUtil.isEmpty(request.getParameter("memberIdGroup"))) {
							List<Integer> memberIdlist=new ArrayList<Integer>();
							String[] memberidStrings=request.getParameter("memberIdGroup").split(",");
							for (String memberid : memberidStrings) {
								memberIdlist.add(Integer.valueOf(memberid));
							}
							memberInfoCustomCriteria.andIdIn(memberIdlist);
						}
						memberInfoCustomExample.setLimitStart(page.getLimitStart());
						memberInfoCustomExample.setLimitSize(page.getLimitSize());
						dataList=memberInfoService.selectMemberInfoCustomByExample(memberInfoCustomExample);
						totalCount=memberInfoService.countMemberInfoCustomByExample(memberInfoCustomExample);
					} catch (Exception e) {
						e.printStackTrace();
					}
					resMap.put("Rows", dataList);
					resMap.put("Total", totalCount);
					return resMap;
				}
				
				
				//检测商家信息
				@RequestMapping(value ="/businessCirclesAppealOrder/mchtIdsList.shtml")
				public ModelAndView mchtIdsList(HttpServletRequest request) {
					    ModelAndView m = new ModelAndView();
						MchtInfoCustomExample mchtInfoCustomExample=new MchtInfoCustomExample();
						MchtInfoCustomExample.MchtInfoCustomCriteria mchtInfoCustomCriteria=mchtInfoCustomExample.createCriteria();
						mchtInfoCustomCriteria.andDelFlagEqualTo("0");
						List<String> mchtCodeList = new ArrayList<String>();
						String[] mchtCodes=request.getParameter("mchtCodes").split(",");
						for (String mchtCode : mchtCodes) {
							mchtCodeList.add(mchtCode);
						}
						mchtInfoCustomCriteria.andMchtCodeIn(mchtCodeList);
						List<MchtInfoCustom> mchtInfoCustoms=mchtInfoService.selectByExample(mchtInfoCustomExample);
						List<Integer> mchtIdList=new ArrayList<Integer>();
						if (mchtInfoCustoms.size()<=0) {
						    m.setViewName("/businessCirclesAppealOrder/nomchtInfoList");
							
						}else {
							String mchtIdGroup = "";
							for (MchtInfoCustom mchtInfoCustom : mchtInfoCustoms) {
								if("".equals(mchtIdGroup)) {
									mchtIdGroup = mchtInfoCustom.getId().toString();
								}else {
									mchtIdGroup += "," + mchtInfoCustom.getId().toString();
								}
								mchtIdList.add(Integer.valueOf(mchtInfoCustom.getId().toString()));
							}
												
							m.addObject("mchtIdGroup", mchtIdGroup);
							m.setViewName("/businessCirclesAppealOrder/mchtInfoList");	
							
						}	
					return m;
				}
				
				
				//获取商家列表数据
				@ResponseBody
				@RequestMapping(value = "/businessCirclesAppealOrder/mchtInfodata.shtml")
				public Map<String, Object> mchtInfodata(HttpServletRequest request, Page page){
					Map<String, Object> resMap = new HashMap<String, Object>();
					List<MchtInfoCustom> dataList = new ArrayList<MchtInfoCustom>();
					Integer totalCount = 0;
					try {
						MchtInfoCustomExample mchtInfoCustomExample=new MchtInfoCustomExample();
						MchtInfoCustomExample.MchtInfoCustomCriteria mchtInfoCustomCriteria=mchtInfoCustomExample.createCriteria();
						mchtInfoCustomCriteria.andDelFlagEqualTo("0");
						if (!StringUtil.isEmpty(request.getParameter("mchtIdGroup"))) {
							List<Integer> mchtIdGroupList=new ArrayList<Integer>();
							String[] mchtIdGroup=request.getParameter("mchtIdGroup").split(",");
							for (String mchtIdGroups : mchtIdGroup) {
								mchtIdGroupList.add(Integer.valueOf(mchtIdGroups));
							}
							mchtInfoCustomCriteria.andIdIn(mchtIdGroupList);
						}
						mchtInfoCustomExample.setLimitStart(page.getLimitStart());
						mchtInfoCustomExample.setLimitSize(page.getLimitSize());
						dataList=mchtInfoService.selectByExample(mchtInfoCustomExample);
						totalCount=mchtInfoService.countByExample(mchtInfoCustomExample);
					} catch (Exception e) {
						e.printStackTrace();
					}
					resMap.put("Rows", dataList);
					resMap.put("Total", totalCount);
					return resMap;
				}
				
				//补齐信息
				@ResponseBody
				@RequestMapping(value = "/businessCirclesAppealOrder/orderList.shtml")
				public Map<String, Object> orderList(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {
					Map<String, Object> map = new HashMap<String, Object>();
					String code = "200";
					String msg = "操作成功！";
					try {
						 /*List<Integer> memberIdList=new ArrayList<Integer>();		 
						 String[] memberIds=request.getParameter("memberIds").split(",");
						 for (String memberId : memberIds) {
							 memberIdList.add(Integer.valueOf(memberId));
						}*/
						/*int subOrderCustomscuont=0;
						 Integer totalCount=0;
						 List<String> statusList=new ArrayList<String>();//子订单状态
						 statusList.add("1");
						 statusList.add("2");					 
						 MemberInfoCustomExample memberInfoCustomExample=new MemberInfoCustomExample();
						 MemberInfoCustomExample.MemberInfoCustomCriteria memberInfoCustomCriteria=memberInfoCustomExample.createCriteria();
						 memberInfoCustomCriteria.andDelFlagEqualTo("0").andIdIn(memberIdList);
						 List<MemberInfoCustom> memberInfoCustoms=memberInfoService.selectMemberInfoCustomByExample(memberInfoCustomExample);
						 if (memberInfoCustoms.size()>0) {
							 for (MemberInfoCustom memberInfoCustom : memberInfoCustoms) {
								 SubOrderCustomExample subOrderCustomExample = new SubOrderCustomExample();
								 SubOrderCustomExample.SubOrderCustomCriteria subOrderCustomCriteria = subOrderCustomExample.createCriteria();
								 subOrderCustomCriteria.andDelFlagEqualTo("0").andStatusIn(statusList);
								 subOrderCustomCriteria.andMemberInfoOr(memberInfoCustom);								
								 totalCount=subOrderService.countSubOrderCustomByExample(subOrderCustomExample);
								 subOrderCustomscuont+=totalCount;
								 
							 }
							 
							 
						}*/
						 int subOrderCustomscuont=businessCirclesAppealOrderService.subOrderCustomscuont(request.getParameter("memberIds"));
						 int customerServiceOrdercount=businessCirclesAppealOrderService.customerServiceOrdercount(request.getParameter("memberIds"));
						 int interventionOrdercount=businessCirclesAppealOrderService.interventionOrdercount(request.getParameter("memberIds"));
						 int appealOrdercount=businessCirclesAppealOrderService.appealOrdercount(request.getParameter("memberIds"));
						 
						 map.put("subOrderCustomscuont", subOrderCustomscuont);
						 map.put("customerServiceOrdercount", customerServiceOrdercount);
						 map.put("interventionOrdercount", interventionOrdercount);
						 map.put("appealOrdercount", appealOrdercount);
						 
						 
						 
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						code = "9999";
						msg = "系统异常请稍后再试！";
					}
					map.put("code", code);
					map.put("msg", msg);
					return map;
				}
				
				
				//获取子订单
				@ResponseBody
				@RequestMapping(value = "/businessCirclesAppealOrder/subOrderList.shtml")
				public ModelAndView subOrderList(HttpServletRequest request){
					    ModelAndView m = new ModelAndView();
						List<Integer> memberIds=new ArrayList<Integer>();	
						String[] strings=request.getParameter("memberIds").split(",");
						 for (String string : strings) {
							 Pattern pattern = Pattern.compile("[0-9]*");
					         Matcher isNum = pattern.matcher(string);
							if (isNum.matches() && string.length()<11) {
								memberIds.add(Integer.valueOf(string));			
							}else {
								memberIds.add(0);
							}
						}
						 List<String> statusList=new ArrayList<String>();//子订单状态
						 statusList.add("1");
						 statusList.add("2");					 
						/* MemberInfoCustomExample memberInfoCustomExample=new MemberInfoCustomExample();
						 MemberInfoCustomExample.MemberInfoCustomCriteria memberInfoCustomCriteria=memberInfoCustomExample.createCriteria();
						 memberInfoCustomCriteria.andDelFlagEqualTo("0").andIdIn(memberIds);
						 List<MemberInfoCustom> memberInfoCustoms=memberInfoService.selectMemberInfoCustomByExample(memberInfoCustomExample);*/
						 String suborderList="";
						/* if (memberInfoCustoms.size()>0) {*/
							 /*for (MemberInfoCustom memberInfoCustom2 : memberInfoCustoms) {*/
								 SubOrderCustomExample subOrderCustomExample = new SubOrderCustomExample();
								 SubOrderCustomExample.SubOrderCustomCriteria subOrderCustomCriteria = subOrderCustomExample.createCriteria();
								 subOrderCustomCriteria.andDelFlagEqualTo("0").andStatusIn(statusList);
								 subOrderCustomCriteria.andCreateByIn(memberIds);
								 /*subOrderCustomCriteria.andMemberInfoOr(memberInfoCustom2);	*/
								 List<SubOrderCustom> subOrderCustoms=subOrderService.selectSubOrderCustomByExample(subOrderCustomExample);
								 
								 for (SubOrderCustom subOrderCustom : subOrderCustoms) {
									 if("".equals(suborderList)) {
										 suborderList = subOrderCustom.getId().toString();
									 }else {
										 suborderList += "," + subOrderCustom.getId().toString();
									 }	
								}
								 
						/*	}
							
						}*/
						 
				    m.addObject("suborderList", suborderList);	 
					m.setViewName("/businessCirclesAppealOrder/suborderList");	
			
					return m;
					
				}		
				
				
				//子订单列表数据
				@ResponseBody
				@RequestMapping(value = "/businessCirclesAppealOrder/suborderdata.shtml")
				public Map<String, Object> suborderdata(HttpServletRequest request, Page page){
					Map<String, Object> resMap = new HashMap<String, Object>();
					List<SubOrderCustom> dataList = new ArrayList<SubOrderCustom>();
					Integer totalCount = 0;
					try {
						 SubOrderCustomExample subOrderCustomExample=new SubOrderCustomExample();
						 SubOrderCustomExample.SubOrderCustomCriteria subOrderCustomCriteria=subOrderCustomExample.createCriteria();
						 subOrderCustomCriteria.andDelFlagEqualTo("0");		
						if (!StringUtil.isEmpty(request.getParameter("suborderList"))) {
							List<Integer> suborderidGroupList=new ArrayList<Integer>();
							String[] suborderidGroup=request.getParameter("suborderList").split(",");
							for (String suborderidGroups : suborderidGroup) {
								suborderidGroupList.add(Integer.valueOf(suborderidGroups));
							}
							subOrderCustomCriteria.andIdIn(suborderidGroupList);
						}else {
							subOrderCustomCriteria.andIdEqualTo(0);
						}
						if (!StringUtil.isEmpty(request.getParameter("subOrderCode"))) {
							subOrderCustomCriteria.andSubOrderCodeEqualTo(request.getParameter("subOrderCode"));
						}
						if (!StringUtil.isEmpty(request.getParameter("Status"))) {
							subOrderCustomCriteria.andStatusEqualTo(request.getParameter("Status"));
						}
						if (!StringUtil.isEmpty(request.getParameter("productName"))) {
							subOrderCustomCriteria.andProductNameLikeTo("%"+request.getParameter("productName")+"%");
						}
						subOrderCustomExample.setLimitStart(page.getLimitStart());
						subOrderCustomExample.setLimitSize(page.getLimitSize());
						dataList=subOrderService.selectSubOrderCustomByExample(subOrderCustomExample);
						totalCount=subOrderService.countSubOrderCustomByExample(subOrderCustomExample);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					resMap.put("Rows", dataList);
					resMap.put("Total", totalCount);
					return resMap;
				}
				
				
				//获取售后单
				@ResponseBody
				@RequestMapping(value = "/businessCirclesAppealOrder/customerServiceOrderList.shtml")
				public ModelAndView customerServiceOrderList(HttpServletRequest request){
					    ModelAndView m = new ModelAndView();
						List<Integer> memberIds=new ArrayList<Integer>();	
						String[] strings=request.getParameter("memberIds").split(",");
						 for (String string : strings) {
							 Pattern pattern = Pattern.compile("[0-9]*");
					         Matcher isNum = pattern.matcher(string);
							if (isNum.matches() && string.length()<11) {
								memberIds.add(Integer.valueOf(string));			
							}else {
								memberIds.add(0);
							}
						}			 
						 CustomerServiceOrderCustomExample customerServiceOrderCustomExample=new CustomerServiceOrderCustomExample();
						 CustomerServiceOrderCustomExample.CustomerServiceOrderCustomCriteria customCriteria=customerServiceOrderCustomExample.createCriteria();
						 customCriteria.andDelFlagEqualTo("0").andCreateByIn(memberIds).andStatusEqualTo("0");
						 List<CustomerServiceOrderCustom> customerServiceOrderCustoms=customerServiceOrderService.selectCustomerServiceOrderCustomByExample(customerServiceOrderCustomExample);
						 String customerServiceOrderlist="";
						 if (customerServiceOrderCustoms.size()>0) {
							 for (CustomerServiceOrderCustom customerServiceOrderCustom : customerServiceOrderCustoms) {
								  if ("".equals(customerServiceOrderlist)) {
									  customerServiceOrderlist=customerServiceOrderCustom.getId().toString();
								}else {
									  customerServiceOrderlist += "," + customerServiceOrderCustom.getId().toString();
								 }	

														  
							 }
						}
						 
						 List<Map<String, Object>> getproStatuslist =businessCirclesAppealOrderService.getproStatus(request.getParameter("memberIds"));
					 				 	 
				    m.addObject("customerServiceOrderlist",customerServiceOrderlist);
				    m.addObject("proStatuslist",getproStatuslist);
					m.setViewName("/businessCirclesAppealOrder/customerServiceOrderList");		
					return m;
					
				}
				
				//售后单列表数据
				@ResponseBody
				@RequestMapping(value = "/businessCirclesAppealOrder/customerServiceOrderdata.shtml")
				public Map<String, Object> customerServiceOrderdata(HttpServletRequest request, Page page){
					Map<String, Object> resMap = new HashMap<String, Object>();
					List<CustomerServiceOrderCustom> dataList = new ArrayList<CustomerServiceOrderCustom>();
					Integer totalCount = 0;
					try {
						 
						 CustomerServiceOrderCustomExample customerServiceOrderCustomExample=new CustomerServiceOrderCustomExample();
						 CustomerServiceOrderCustomExample.CustomerServiceOrderCustomCriteria customCriteria=customerServiceOrderCustomExample.createCriteria();
						 customCriteria.andDelFlagEqualTo("0")/*.andStatusEqualTo("0")*/;
						 if (!StringUtil.isEmpty(request.getParameter("customerServiceOrderlist"))) {
								List<Integer> customerServiceOrderGrouplist=new ArrayList<Integer>();
								String[] customerServiceOrderGroup=request.getParameter("customerServiceOrderlist").split(",");
								for (String customerServiceOrderGroups : customerServiceOrderGroup) {
									customerServiceOrderGrouplist.add(Integer.valueOf(customerServiceOrderGroups));
								}
								
								customCriteria.andIdIn(customerServiceOrderGrouplist);
							}else {
								customCriteria.andIdEqualTo(0);
							}
						    if (!StringUtil.isEmpty(request.getParameter("proStatus"))) {
						    	customCriteria.andProStatusEqualTo(request.getParameter("proStatus"));
							}
						    if (!StringUtil.isEmpty(request.getParameter("orderCode"))) {
						    	customCriteria.andOrderCodeEqualTo(request.getParameter("orderCode"));
							}
						    if (!StringUtil.isEmpty(request.getParameter("productName"))) {
						    	customCriteria.andProductNameLikeTo("%"+request.getParameter("productName")+"%");
							}
						    customerServiceOrderCustomExample.setLimitStart(page.getLimitStart());
						    customerServiceOrderCustomExample.setLimitSize(page.getLimitSize());
						    dataList=customerServiceOrderService.selectCustomerServiceOrderCustomByExample(customerServiceOrderCustomExample);
						    totalCount=customerServiceOrderService.countCustomerServiceOrderCustomByExample(customerServiceOrderCustomExample); 
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					resMap.put("Rows", dataList);
					resMap.put("Total", totalCount);
					return resMap;
				}
				
				
				//获取介入单
				@ResponseBody
				@RequestMapping(value = "/businessCirclesAppealOrder/interventionOrderList.shtml")
				public ModelAndView interventionOrderList(HttpServletRequest request){
					    ModelAndView m = new ModelAndView();
						List<Integer> memberIds=new ArrayList<Integer>();	
						String[] strings=request.getParameter("memberIds").split(",");
						 for (String string : strings) {
							 Pattern pattern = Pattern.compile("[0-9]*");
					         Matcher isNum = pattern.matcher(string);
							if (isNum.matches() && string.length()<11) {
								memberIds.add(Integer.valueOf(string));			
							}else {
								memberIds.add(0);
							}
						}			 
						 InterventionOrderCustomExample interventionOrderCustomExample=new InterventionOrderCustomExample();
						 InterventionOrderCustomExample.InterventionOrderCustomCriteria interventionOrderCustomCriteria=interventionOrderCustomExample.createCriteria();
						 interventionOrderCustomCriteria.andDelFlagEqualTo("0")/*.andStatusNotEqualTo("8")*/.andCreateByIn(memberIds);
						 List<InterventionOrderCustom> interventionOrderCustoms=interventionOrderService.selectByCustomExample(interventionOrderCustomExample);
						 String interventionOrderList="";
						 if (interventionOrderCustoms.size()>0) {
							 for (InterventionOrderCustom interventionOrderCustom : interventionOrderCustoms) {
								  if ("".equals(interventionOrderList)) {
									  interventionOrderList=interventionOrderCustom.getId().toString();
								}else {
									interventionOrderList += "," + interventionOrderCustom.getId().toString();
								 }	

														  
							 }
						}
						 
						 List<Map<String, Object>> statuslist =businessCirclesAppealOrderService.getStatus(request.getParameter("memberIds"));
					 				 	 
				    m.addObject("interventionOrderList",interventionOrderList);
				    m.addObject("statuslist",statuslist);
					m.setViewName("/businessCirclesAppealOrder/interventionOrderList");		
					return m;
					
				}
				
				
				//介入单列表数据
				@ResponseBody
				@RequestMapping(value = "/businessCirclesAppealOrder/interventionOrderdata.shtml")
				public Map<String, Object> interventionOrderdata(HttpServletRequest request, Page page){
					Map<String, Object> resMap = new HashMap<String, Object>();
					List<InterventionOrderCustom> dataList = new ArrayList<InterventionOrderCustom>();
					Integer totalCount = 0;
					try {
						 
						 InterventionOrderCustomExample interventionOrderCustomExample=new InterventionOrderCustomExample();
						 InterventionOrderCustomExample.InterventionOrderCustomCriteria interventionOrderCustomCriteria=interventionOrderCustomExample.createCriteria();
						 interventionOrderCustomCriteria.andDelFlagEqualTo("0")/*.andStatusNotEqualTo("8")*/;
						 if (!StringUtil.isEmpty(request.getParameter("interventionOrderList"))) {
								List<Integer> interventionOrderGrouplist=new ArrayList<Integer>();
								String[] interventionOrderGroup=request.getParameter("interventionOrderList").split(",");
								for (String interventionOrderGroups : interventionOrderGroup) {
									interventionOrderGrouplist.add(Integer.valueOf(interventionOrderGroups));
								}
								
								interventionOrderCustomCriteria.andIdIn(interventionOrderGrouplist);
							}else {
								interventionOrderCustomCriteria.andIdEqualTo(0);
							}
						    if (!StringUtil.isEmpty(request.getParameter("interventionCode"))) {
						    	interventionOrderCustomCriteria.andInterventionCodeEqualTo(request.getParameter("interventionCode"));
							}
						    if (!StringUtil.isEmpty(request.getParameter("status"))) {
						    	interventionOrderCustomCriteria.andStatusEqualTo(request.getParameter("status"));
							}
						    interventionOrderCustomExample.setLimitStart(page.getLimitStart());
						    interventionOrderCustomExample.setLimitSize(page.getLimitSize());
						    dataList=interventionOrderService.selectByCustomExample(interventionOrderCustomExample);
						    totalCount=interventionOrderService.countByCustomExample(interventionOrderCustomExample);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					resMap.put("Rows", dataList);
					resMap.put("Total", totalCount);
					return resMap;
				}
				
				
				//获取投诉单
				@ResponseBody
				@RequestMapping(value = "/businessCirclesAppealOrder/appealOrderList.shtml")
				public ModelAndView appealOrderList(HttpServletRequest request){
					    ModelAndView m = new ModelAndView();
						List<Integer> memberIds=new ArrayList<Integer>();	
						String[] strings=request.getParameter("memberIds").split(",");
						 for (String string : strings) {
							 Pattern pattern = Pattern.compile("[0-9]*");
					         Matcher isNum = pattern.matcher(string);
							if (isNum.matches() && string.length()<11) {
								memberIds.add(Integer.valueOf(string));			
							}else {
								memberIds.add(0);
							}
						}
						 List<String> statusList=new ArrayList<String>();
						 statusList.add("1");
						 statusList.add("2");
						 statusList.add("5");
						 AppealOrderCustomExample appealOrderCustomExample=new AppealOrderCustomExample();
						 AppealOrderCustomExample.AppealOrderCustomCriteria appealOrderCustomCriteria=appealOrderCustomExample.createCriteria();
						 appealOrderCustomCriteria.andDelFlagEqualTo("0").andStatusIn(statusList).andCreateByIn(memberIds);
						 List<AppealOrderCustom> appealOrderCustoms=appealOrderService.selectAppealOrderCustomByExample(appealOrderCustomExample);		 
						 String appealOrderList="";
						 if (appealOrderCustoms.size()>0) {
							 for (AppealOrderCustom appealOrderCustom : appealOrderCustoms) {
								  if ("".equals(appealOrderList)) {
									  appealOrderList=appealOrderCustom.getId().toString();
								}else {
									appealOrderList += "," + appealOrderCustom.getId().toString();
								 }	

														  
							 }
						}
						 
						 List<Map<String, Object>> statuslist =businessCirclesAppealOrderService.getappealOrderstatus(request.getParameter("memberIds"));
					 				 	 
				    m.addObject("appealOrderList",appealOrderList);
				    m.addObject("statuslist",statuslist);
					m.setViewName("/businessCirclesAppealOrder/appealOrderList");		
					return m;
					
				}
				
				//获取投诉单列表数据
				@ResponseBody
				@RequestMapping(value = "/businessCirclesAppealOrder/appealOrderdata.shtml")
				public Map<String, Object> appealOrderdata(HttpServletRequest request, Page page){
					Map<String, Object> resMap = new HashMap<String, Object>();
					List<AppealOrderCustom> dataList = new ArrayList<AppealOrderCustom>();
					Integer totalCount = 0;
					try {			 
						 List<String> statusList=new ArrayList<String>();
						 statusList.add("1");
						 statusList.add("2");
						 statusList.add("5");
						 AppealOrderCustomExample appealOrderCustomExample=new AppealOrderCustomExample();
						 AppealOrderCustomExample.AppealOrderCustomCriteria appealOrderCustomCriteria=appealOrderCustomExample.createCriteria();
						 appealOrderCustomCriteria.andDelFlagEqualTo("0")/*.andStatusIn(statusList)*/;
						 if (!StringUtil.isEmpty(request.getParameter("appealOrderList"))) {
								List<Integer> appealOrderGrouplist=new ArrayList<Integer>();
								String[] appealOrderGroup=request.getParameter("appealOrderList").split(",");
								for (String appealOrderGroups : appealOrderGroup) {
									appealOrderGrouplist.add(Integer.valueOf(appealOrderGroups));
								}
								
								appealOrderCustomCriteria.andIdIn(appealOrderGrouplist);
							}else {
								appealOrderCustomCriteria.andIdEqualTo(0);
							}
						    if (!StringUtil.isEmpty(request.getParameter("orderCode"))) {
						    	appealOrderCustomCriteria.andOrderCodeEqualTo(request.getParameter("orderCode"));
							}
						    if (!StringUtil.isEmpty(request.getParameter("serviceStatus"))) {
						    	appealOrderCustomCriteria.andServiceStatusEqualTo(request.getParameter("serviceStatus"));
							}
						    appealOrderCustomExample.setLimitStart(page.getLimitStart());
						    appealOrderCustomExample.setLimitSize(page.getLimitSize());
						    dataList=appealOrderService.selectAppealOrderCustomByExample(appealOrderCustomExample);
						    totalCount=appealOrderService.countAppealOrderCustomByExample(appealOrderCustomExample);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					resMap.put("Rows", dataList);
					resMap.put("Total", totalCount);
					return resMap;
				}
				
				
				//获取被投诉的品牌
				@ResponseBody
				@RequestMapping(value ="/businessCirclesAppealOrder/productBrandList.shtml")
				public Map<String, Object> productBrandList(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap){
					Map<String, Object> map = new HashMap<String, Object>();
					String code = "200";
					String msg = "操作成功！";
					 try {
						    MchtInfoCustomExample mchtInfoCustomExample=new MchtInfoCustomExample();
							MchtInfoCustomExample.MchtInfoCustomCriteria mchtInfoCustomCriteria=mchtInfoCustomExample.createCriteria();
							mchtInfoCustomCriteria.andDelFlagEqualTo("0");
							List<String> mchtCodeList = new ArrayList<String>();
							String[] mchtCodes=request.getParameter("mchtCodes").split(",");
							for (String mchtCode : mchtCodes) {
								mchtCodeList.add(mchtCode);
							}
							mchtInfoCustomCriteria.andMchtCodeIn(mchtCodeList);
							List<MchtInfoCustom> mchtInfoCustoms=mchtInfoService.selectByExample(mchtInfoCustomExample);
							List<Integer> mchtIdList=new ArrayList<Integer>();
			
								for (MchtInfoCustom mchtInfoCustom : mchtInfoCustoms) {
						
									mchtIdList.add(Integer.valueOf(mchtInfoCustom.getId().toString()));
								}
								
								
								MchtProductBrandExample mchtProductBrandExample=new MchtProductBrandExample();
								MchtProductBrandExample.Criteria mcCriteria=mchtProductBrandExample.createCriteria();
								mcCriteria.andDelFlagEqualTo("0").andMchtIdIn(mchtIdList).andStatusEqualTo("1");
								
								List<MchtProductBrand> mchtProductBrands=mchtProductBrandService.selectByExample(mchtProductBrandExample);
								List<Integer> integers=new ArrayList<Integer>();
								for (MchtProductBrand mchtProductBrand : mchtProductBrands) {
									integers.add(mchtProductBrand.getProductBrandId());
								}
								
								ProductBrandCustomExample productBrandCustomExample=new ProductBrandCustomExample();
								ProductBrandCustomExample.ProductBrandExampleCriteria productBrandExampleCriteria=productBrandCustomExample.createCriteria();
								productBrandExampleCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1").andIdIn(integers);
								List<ProductBrandCustom> productBrandCustoms=productBrandService.selectByCustomExample(productBrandCustomExample);
								map.put("productBrandCustoms", productBrandCustoms);			
						
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						code = "9999";
						msg = "系统异常请稍后再试！";
					}			
					 map.put("code", code);
					 map.put("msg", msg);
					 return map;
					
				}
				
				//添加补齐信息数据
				@ResponseBody
				@RequestMapping(value ="/businessCirclesAppealOrder/addMakupformationbusinessCirclesAppealOrder.shtml")
				public ModelAndView addMakupformationbusinessCirclesAppealOrder(HttpServletRequest request,BusinessCirclesAppealOrder businessCirclesAppealOrder1) {
					String rtPage = "/success/success";
					Map<String, Object> resMap = new HashMap<String, Object>();
					String code = null;
					String msg = "";
					try {
						StaffBean staffBean = this.getSessionStaffBean(request);
						int staffId=Integer.valueOf(staffBean.getStaffID());
						String id=request.getParameter("id"); 
						String memberIds=request.getParameter("memberIds");
						String mchtProductBrandIds=request.getParameter("productBrandIds");
						String subOrderCode=request.getParameter("subOrderCode");
						String memberSituation=request.getParameter("memberSituation");
						String mchtAppealedCount=request.getParameter("mchtAppealedCount");
												
						MchtInfoCustomExample mchtInfoCustomExample=new MchtInfoCustomExample();
						MchtInfoCustomExample.MchtInfoCustomCriteria mchtInfoCustomCriteria=mchtInfoCustomExample.createCriteria();
						mchtInfoCustomCriteria.andDelFlagEqualTo("0");
						List<String> mchtCodeList = new ArrayList<String>();
						String[] mchtCodes=request.getParameter("mchtCodes").split(",");
						for (String mchtCode : mchtCodes) {
							mchtCodeList.add(mchtCode);
						}
						mchtInfoCustomCriteria.andMchtCodeIn(mchtCodeList);
						List<MchtInfoCustom> mchtInfoCustoms=mchtInfoService.selectByExample(mchtInfoCustomExample);
								 String mchtids="";
								 if (mchtInfoCustoms.size()>0) {
									 for (MchtInfoCustom mchtInfoCustom1 : mchtInfoCustoms) {
										  if ("".equals(mchtids)) {
											  mchtids=mchtInfoCustom1.getId().toString();
										}else {
											  mchtids += "," + mchtInfoCustom1.getId().toString();
										 }	

																  
									 }
								}
								 					
						 BusinessCirclesAppealOrder businessCirclesAppealOrder=businessCirclesAppealOrderService.selectByPrimaryKey(Integer.valueOf(id));
						 businessCirclesAppealOrder.setMemberIds(memberIds);
						 businessCirclesAppealOrder.setMchtProductBrandIds(mchtProductBrandIds.replace(";",","));
						 businessCirclesAppealOrder.setSubOrderCode(subOrderCode);
						 businessCirclesAppealOrder.setMemberSituation(memberSituation);
						 businessCirclesAppealOrder.setMchtAppealedCount(Integer.valueOf(mchtAppealedCount));
						 businessCirclesAppealOrder.setMchtIds(mchtids);
						 businessCirclesAppealOrder.setSignForDate(businessCirclesAppealOrder1.getSignForDate());
						 businessCirclesAppealOrder.setUpdateBy(staffId);
						 businessCirclesAppealOrder.setUpdateDate(new Date());
						 businessCirclesAppealOrderService.updateByPrimaryKey(businessCirclesAppealOrder);
						
						code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
						msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						code = StateCode.JSON_AJAX_ERROR.getStateCode();
						msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
					}
					resMap.put(this.JSON_RESULT_CODE, code);
					resMap.put(this.JSON_RESULT_MESSAGE, msg);
					return new ModelAndView(rtPage, resMap); 
				}
				
				
				//客服跟进工商界面获取子订单数据
				@ResponseBody
				@RequestMapping(value = "/businessCirclesAppealOrder/subOrderListdata.shtml")
				public ModelAndView subOrderListdata(HttpServletRequest request){
					    ModelAndView m = new ModelAndView();
					    String id=request.getParameter("id");
					    BusinessCirclesAppealOrderCustom businessCirclesAppealOrderCustom=businessCirclesAppealOrderService.selectByPrimaryKeyCustom(Integer.valueOf(id));
						List<Integer> memberIds=new ArrayList<Integer>();	
						String[] strings=businessCirclesAppealOrderCustom.getMemberIds().split(",");
						 for (String string : strings) {
							 memberIds.add(Integer.valueOf(string));
						}
						 List<String> statusList=new ArrayList<String>();//子订单状态
						 statusList.add("1");
						 statusList.add("2");					 
						 String suborderList="";
								 SubOrderCustomExample subOrderCustomExample = new SubOrderCustomExample();
								 SubOrderCustomExample.SubOrderCustomCriteria subOrderCustomCriteria = subOrderCustomExample.createCriteria();
								 subOrderCustomCriteria.andDelFlagEqualTo("0").andStatusIn(statusList);
								 subOrderCustomCriteria.andCreateByIn(memberIds);
								 List<SubOrderCustom> subOrderCustoms=subOrderService.selectSubOrderCustomByExample(subOrderCustomExample);
								 
								 for (SubOrderCustom subOrderCustom : subOrderCustoms) {
									 if("".equals(suborderList)) {
										 suborderList = subOrderCustom.getId().toString();
									 }else {
										 suborderList += "," + subOrderCustom.getId().toString();
									 }	
								}								 
						 
				    m.addObject("suborderList", suborderList);	 
					m.setViewName("/businessCirclesAppealOrder/suborderList");	
			
					return m;
					
				}
				
				//客服跟进工商界面获取售后单数据
				@ResponseBody
				@RequestMapping(value = "/businessCirclesAppealOrder/customerServiceOrderCountdata.shtml")
				public ModelAndView customerServiceOrderList1(HttpServletRequest request){
					    ModelAndView m = new ModelAndView();
					    String id=request.getParameter("id");
					    BusinessCirclesAppealOrderCustom businessCirclesAppealOrderCustom=businessCirclesAppealOrderService.selectByPrimaryKeyCustom(Integer.valueOf(id));
					    List<Integer> memberIds=new ArrayList<Integer>();	
						String[] strings=businessCirclesAppealOrderCustom.getMemberIds().split(",");
						 for (String string : strings) {
							 memberIds.add(Integer.valueOf(string));
						}	
						 CustomerServiceOrderCustomExample customerServiceOrderCustomExample=new CustomerServiceOrderCustomExample();
						 CustomerServiceOrderCustomExample.CustomerServiceOrderCustomCriteria customCriteria=customerServiceOrderCustomExample.createCriteria();
						 customCriteria.andDelFlagEqualTo("0").andCreateByIn(memberIds).andStatusEqualTo("0");
						 List<CustomerServiceOrderCustom> customerServiceOrderCustoms=customerServiceOrderService.selectCustomerServiceOrderCustomByExample(customerServiceOrderCustomExample);
						 String customerServiceOrderlist="";
						 if (customerServiceOrderCustoms.size()>0) {
							 for (CustomerServiceOrderCustom customerServiceOrderCustom : customerServiceOrderCustoms) {
								  if ("".equals(customerServiceOrderlist)) {
									  customerServiceOrderlist=customerServiceOrderCustom.getId().toString();
								}else {
									  customerServiceOrderlist += "," + customerServiceOrderCustom.getId().toString();
								 }	

														  
							 }
						}
						 
						List<Map<String, Object>> getproStatuslist =businessCirclesAppealOrderService.getproStatus(businessCirclesAppealOrderCustom.getMemberIds());
					 				 	 
				    m.addObject("customerServiceOrderlist",customerServiceOrderlist);
				    m.addObject("proStatuslist",getproStatuslist);
					m.setViewName("/businessCirclesAppealOrder/customerServiceOrderList");		
					return m;
					
				}
				
				//客服跟进工商界面获取介入单数据
				@ResponseBody
				@RequestMapping(value = "/businessCirclesAppealOrder/interventionOrderCountdata.shtml")
				public ModelAndView interventionOrderCountdata(HttpServletRequest request){
					    ModelAndView m = new ModelAndView();
					    String id=request.getParameter("id");
					    BusinessCirclesAppealOrderCustom businessCirclesAppealOrderCustom=businessCirclesAppealOrderService.selectByPrimaryKeyCustom(Integer.valueOf(id));
					    List<Integer> memberIds=new ArrayList<Integer>();	
						String[] strings=businessCirclesAppealOrderCustom.getMemberIds().split(",");
						 for (String string : strings) {
							 memberIds.add(Integer.valueOf(string));
						}	
						 InterventionOrderCustomExample interventionOrderCustomExample=new InterventionOrderCustomExample();
						 InterventionOrderCustomExample.InterventionOrderCustomCriteria interventionOrderCustomCriteria=interventionOrderCustomExample.createCriteria();
						 interventionOrderCustomCriteria.andDelFlagEqualTo("0").andStatusNotEqualTo("8").andCreateByIn(memberIds);
						 List<InterventionOrderCustom> interventionOrderCustoms=interventionOrderService.selectByCustomExample(interventionOrderCustomExample);
						 String interventionOrderList="";
						 if (interventionOrderCustoms.size()>0) {
							 for (InterventionOrderCustom interventionOrderCustom : interventionOrderCustoms) {
								  if ("".equals(interventionOrderList)) {
									  interventionOrderList=interventionOrderCustom.getId().toString();
								}else {
									interventionOrderList += "," + interventionOrderCustom.getId().toString();
								 }	

														  
							 }
						}
						 
						 List<Map<String, Object>> statuslist =businessCirclesAppealOrderService.getStatus(businessCirclesAppealOrderCustom.getMemberIds());
					 				 	 
				    m.addObject("interventionOrderList",interventionOrderList);
				    m.addObject("statuslist",statuslist);
					m.setViewName("/businessCirclesAppealOrder/interventionOrderList");		
					return m;
					
				}
				
				//客服跟进工商界面获取投诉单数据
				@ResponseBody
				@RequestMapping(value = "/businessCirclesAppealOrder/appealOrderCountdata.shtml")
				public ModelAndView appealOrderCountdata(HttpServletRequest request){
					    ModelAndView m = new ModelAndView();
					    String id=request.getParameter("id");
					    BusinessCirclesAppealOrderCustom businessCirclesAppealOrderCustom=businessCirclesAppealOrderService.selectByPrimaryKeyCustom(Integer.valueOf(id));
						List<Integer> memberIds=new ArrayList<Integer>();	
						String[] strings=businessCirclesAppealOrderCustom.getMemberIds().split(",");
						 for (String string : strings) {
							 memberIds.add(Integer.valueOf(string));
						}
						 List<String> statusList=new ArrayList<String>();
						 statusList.add("1");
						 statusList.add("2");
						 statusList.add("5");
						 AppealOrderCustomExample appealOrderCustomExample=new AppealOrderCustomExample();
						 AppealOrderCustomExample.AppealOrderCustomCriteria appealOrderCustomCriteria=appealOrderCustomExample.createCriteria();
						 appealOrderCustomCriteria.andDelFlagEqualTo("0").andStatusIn(statusList).andCreateByIn(memberIds);
						 List<AppealOrderCustom> appealOrderCustoms=appealOrderService.selectAppealOrderCustomByExample(appealOrderCustomExample);		 
						 String appealOrderList="";
						 if (appealOrderCustoms.size()>0) {
							 for (AppealOrderCustom appealOrderCustom : appealOrderCustoms) {
								  if ("".equals(appealOrderList)) {
									  appealOrderList=appealOrderCustom.getId().toString();
								}else {
									appealOrderList += "," + appealOrderCustom.getId().toString();
								 }	

														  
							 }
						}
						 
						 List<Map<String, Object>> statuslist =businessCirclesAppealOrderService.getappealOrderstatus(businessCirclesAppealOrderCustom.getMemberIds());
					 				 	 
				    m.addObject("appealOrderList",appealOrderList);
				    m.addObject("statuslist",statuslist);
					m.setViewName("/businessCirclesAppealOrder/appealOrderList");		
					return m;
					
				}
				
				
				//客服处理建议
				@RequestMapping("/businessCirclesAppealOrder/SubmitProcessingResults.shtml")
				public ModelAndView SubmitProcessingResults(HttpServletRequest request) {
					ModelAndView m = new ModelAndView("/businessCirclesAppealOrder/submitProcessingResults");
					String id=request.getParameter("id");
					String staString=request.getParameter("status");
					m.addObject("staString", staString);
					BusinessCirclesAppealOrder businessCirclesAppealOrder=businessCirclesAppealOrderService.selectByPrimaryKey(Integer.valueOf(id));
					m.addObject("businessCirclesAppealOrder", businessCirclesAppealOrder);
					return m;
				}
				
				//添加客服处理意见
				@ResponseBody
				@RequestMapping(value ="/businessCirclesAppealOrder/addsubmitProcessingResults.shtml")
				public ModelAndView addsubmitProcessingResults(HttpServletRequest request,BusinessCirclesAppealOrder businessCirclesAppealOrder1) {
					String rtPage = "/success/success";
					Map<String, Object> resMap = new HashMap<String, Object>();
					String code = null;
					String msg = "";
					try {
						StaffBean staffBean = this.getSessionStaffBean(request);
						int staffId=Integer.valueOf(staffBean.getStaffID());	
						
						 BusinessCirclesAppealOrder businessCirclesAppealOrder=businessCirclesAppealOrderService.selectByPrimaryKey(businessCirclesAppealOrder1.getId());
						 businessCirclesAppealOrder.setCustomerServiceResult(businessCirclesAppealOrder1.getCustomerServiceResult());
						 businessCirclesAppealOrder.setCustomerServiceResultDate(new Date());
						 businessCirclesAppealOrder.setAppealStatus("2");
						 businessCirclesAppealOrder.setUpdateBy(staffId);
						 businessCirclesAppealOrder.setUpdateDate(new Date());
						 businessCirclesAppealOrderService.updateByPrimaryKeySelective(businessCirclesAppealOrder);
						 
						 SysStaffInfoCustomExample sysStaffInfoCustomExample=new SysStaffInfoCustomExample();
						 sysStaffInfoCustomExample.createCriteria().andStatusEqualTo("A").andStaffIdEqualTo(businessCirclesAppealOrder.getCreateBy());
						 List<SysStaffInfoCustom> sysStaffInfoCustoms=sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);

					     JSONObject param=new JSONObject();
					     JSONObject reqData=new JSONObject();
					     reqData.put("mobile", sysStaffInfoCustoms.get(0).getMobilePhone());
					     reqData.put("content", "【醒购】工商投诉编号："+businessCirclesAppealOrder.getRegistrationNumber()+"客服部已处理完成,请及时登录平台进行处理;");
					     reqData.put("smsType", "4");
					     param.put("reqData", reqData);
					     JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
					   if(!"0000".equals(result.getString("returnCode"))){
						  logger.info("短信发送用户失败！！！！！");
					    }
					
						
						code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
						msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						code = StateCode.JSON_AJAX_ERROR.getStateCode();
						msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
					}
					resMap.put(this.JSON_RESULT_CODE, code);
					resMap.put(this.JSON_RESULT_MESSAGE, msg);
					return new ModelAndView(rtPage, resMap); 
				}
				
				
				//客服记录
				@RequestMapping("/businessCirclesAppealOrder/kfRecordsdata.shtml")
				public ModelAndView kfRecordsdata(HttpServletRequest request) {
					ModelAndView m = new ModelAndView("/businessCirclesAppealOrder/customerServiceRecords");
					String id=request.getParameter("id");
					BusinessCirclesAppealOrder businessCirclesAppealOrder=businessCirclesAppealOrderService.selectByPrimaryKey(Integer.valueOf(id));
					m.addObject("businessCirclesAppealOrder", businessCirclesAppealOrder);
					String staString=request.getParameter("status");
					m.addObject("staString", staString);
					CustomerServiceRecordsExample customerServiceRecordsExample=new CustomerServiceRecordsExample();
					customerServiceRecordsExample.createCriteria().andDelFlagEqualTo("0").andBusinessCirclesAppealOrderIdEqualTo(Integer.valueOf(id));
					List<CustomerServiceRecords> customerServiceRecords=customerServiceRecordsService.selectByExample(customerServiceRecordsExample);
					if (customerServiceRecords.size()>0) {
						CustomerServiceRecords customerServiceRecords2=customerServiceRecordsService.selectByPrimaryKey(customerServiceRecords.get(0).getId());
						m.addObject("customerServiceRecords2", customerServiceRecords2);
					 }
					if (customerServiceRecords.size()>0 && customerServiceRecords.get(0).getId() !=null) {						
						CustomerServiceRecordsFileExample customerServiceRecordsFileExample=new CustomerServiceRecordsFileExample();
						customerServiceRecordsFileExample.createCriteria().andDelFlagEqualTo("0").andCustomerServiceRecordsIdEqualTo(customerServiceRecords.get(0).getId());
						List<CustomerServiceRecordsFile> customerServiceRecordsFiles=customerServiceRecordsFileService.selectByExample(customerServiceRecordsFileExample);
						m.addObject("customerServiceRecordsFiles", customerServiceRecordsFiles);
					}
					return m;
				}
				
				
				 //添加客服记录
				@RequestMapping(value = "/businessCirclesAppealOrder/addcustomerServiceRecords.shtml")
				@ResponseBody
			 	public ModelAndView addcustomerServiceRecords(HttpServletRequest request,CustomerServiceRecords customerServiceRecords,String filePath){
					String rtPage = "/success/success";
					Map<String, Object> resMap = new HashMap<String, Object>();
					String code = null;
					String msg =null;
					try {
						StaffBean staffBean = this.getSessionStaffBean(request);
						int staffId=Integer.valueOf(staffBean.getStaffID());
						
						if (customerServiceRecords.getId()==null) {
							String businessCirclesAppealOrderid=request.getParameter("businessCirclesAppealOrderid");
							customerServiceRecords.setBusinessCirclesAppealOrderId(Integer.valueOf(businessCirclesAppealOrderid));
							customerServiceRecords.setCreateBy(staffId);
							customerServiceRecords.setCreateDate(new Date());
							customerServiceRecords.setDelFlag("0");
							customerServiceRecordsService.insertSelective(customerServiceRecords);
							
							CustomerServiceRecordsFile customerServiceRecordsFile2=new CustomerServiceRecordsFile();
							customerServiceRecordsFile2.setCustomerServiceRecordsId(customerServiceRecords.getId());
							customerServiceRecordsFile2.setFilePath(filePath);
							customerServiceRecordsFile2.setCreateBy(customerServiceRecords.getCreateBy());
							customerServiceRecordsFile2.setCreateDate(customerServiceRecords.getCreateDate());
							customerServiceRecordsFile2.setDelFlag("0");
							customerServiceRecordsFileService.insert(customerServiceRecordsFile2);
							
						}else {
							CustomerServiceRecords customerServiceRecords2=customerServiceRecordsService.selectByPrimaryKey(customerServiceRecords.getId());
							customerServiceRecords2.setMchtDealDate(customerServiceRecords.getMchtDealDate());
							customerServiceRecords2.setMchtComplain(customerServiceRecords.getMchtComplain());
							customerServiceRecords2.setMchtProcessingProgress(customerServiceRecords.getMchtProcessingProgress());
							customerServiceRecords2.setPlatformDealDate(customerServiceRecords.getPlatformDealDate());
							customerServiceRecords2.setPlatformProcessingProgress(customerServiceRecords.getPlatformProcessingProgress());
							customerServiceRecords2.setUpdateBy(staffId);
							customerServiceRecords2.setUpdateDate(new Date());
							customerServiceRecordsService.updateByPrimaryKey(customerServiceRecords2);
							
							CustomerServiceRecordsFileExample customerServiceRecordsFileExample=new CustomerServiceRecordsFileExample();
							customerServiceRecordsFileExample.createCriteria().andDelFlagEqualTo("0").andCustomerServiceRecordsIdEqualTo(customerServiceRecords.getId());
							List<CustomerServiceRecordsFile> customerServiceRecordsFiles=customerServiceRecordsFileService.selectByExample(customerServiceRecordsFileExample);
							CustomerServiceRecordsFile customerServiceRecordsFile=customerServiceRecordsFileService.selectByPrimaryKey(customerServiceRecordsFiles.get(0).getId());
							customerServiceRecordsFile.setFilePath(filePath);
							customerServiceRecordsFile.setUpdateBy(staffId);
							customerServiceRecordsFile.setUpdateDate(new Date());
							customerServiceRecordsFileService.updateByPrimaryKey(customerServiceRecordsFile);
							
						}
					
						
						code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
						msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
					} catch (Exception e) {
						  e.printStackTrace();
						 code = StateCode.JSON_AJAX_ERROR.getStateCode();
						 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
					}
					resMap.put(this.JSON_RESULT_CODE, code);
					resMap.put(this.JSON_RESULT_MESSAGE, msg);
					return new ModelAndView(rtPage, resMap);
				}
				
				
				//待法务处理工商投诉
				@RequestMapping("/service/businessCirclesAppeal/fwbusinessCirclesAppealOrder.shtml")
				public ModelAndView fwbusinessCirclesAppealOrder(HttpServletRequest request) {
					ModelAndView m = new ModelAndView("/businessCirclesAppealOrder/fwbusinessCirclesAppealOrderList");
					m.addObject("staffidList",businessCirclesAppealOrderService.getstaffidList());
					return m;
				}
				
				//待法务处理工商投诉数据列表
				@ResponseBody
				@RequestMapping("/businessCirclesAppealOrder/fwbusinessCirclesAppealOrderdata.shtml")
				public Map<String, Object> fwbusinessCirclesAppealOrderdata(HttpServletRequest request, Page page) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					List<BusinessCirclesAppealOrderCustom> dataList = null;
					Integer totalCount = 0;
					try {
						BusinessCirclesAppealOrderCustomExample businessCirclesAppealOrderCustomExample=new BusinessCirclesAppealOrderCustomExample();
						BusinessCirclesAppealOrderCustomExample.BusinessCirclesAppealOrderCustomCriteria criteria=businessCirclesAppealOrderCustomExample.createCriteria();
						criteria.andDelFlagEqualTo("0");
						businessCirclesAppealOrderCustomExample.setOrderByClause("create_date desc");
						if (!StringUtil.isEmpty(request.getParameter("startconsumerAppealDate")) && !StringUtil.isEmpty(request.getParameter("endconsumerAppealDate"))) {
							criteria.andConsumerAppealDateBetween(sdf.parse(request.getParameter("startconsumerAppealDate")+" 00:00:00"), sdf.parse(request.getParameter("endconsumerAppealDate")+" 23:59:59"));				
						}
						if (!StringUtil.isEmpty(request.getParameter("appealOrderType"))) {
							if (request.getParameter("appealOrderType").equals("1")) {
								criteria.andAppealOrderTypeEqualTo("1");
							}
							if (request.getParameter("appealOrderType").equals("2")) {
								criteria.andAppealOrderTypeEqualTo("2");
							}

						}
						if (!StringUtil.isEmpty(request.getParameter("appealStatus"))) {
							String appealStatus=request.getParameter("appealStatus");
							if (appealStatus.equals("2")) {
								criteria.andAppealStatusEqualTo("2");	
							}else if (appealStatus.equals("3")) {
								criteria.andAppealStatusEqualTo("3");
							}else if (appealStatus.equals("4")) {
								criteria.andAppealStatusEqualTo("4");
							}
							
						}
						if (!StringUtil.isEmpty(request.getParameter("memberIds"))) {
							criteria.andmemberIdEqualTo(request.getParameter("memberIds"));
						}
						if (!StringUtil.isEmpty(request.getParameter("companyName"))) {
							criteria.andcompanyNameEqualTo("%"+request.getParameter("companyName")+"%");
						}
						if (!StringUtil.isEmpty(request.getParameter("staffId"))) {
							criteria.andCustomerServiceHandlerEqualTo(Integer.valueOf(request.getParameter("staffId")));
						}
						businessCirclesAppealOrderCustomExample.setLimitStart(page.getLimitStart());
						businessCirclesAppealOrderCustomExample.setLimitSize(page.getLimitSize());
						dataList=businessCirclesAppealOrderService.selectByCustomExample(businessCirclesAppealOrderCustomExample);
						totalCount=businessCirclesAppealOrderService.countByCustomExample(businessCirclesAppealOrderCustomExample);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					resMap.put("Rows", dataList);
					resMap.put("Total", totalCount);
					return resMap;
				}
				
				
				//法务处理建议
				@RequestMapping("/businessCirclesAppealOrder/fwProcessingResults.shtml")
				public ModelAndView fwProcessingResults(HttpServletRequest request) {
					ModelAndView m = new ModelAndView("/businessCirclesAppealOrder/fwProcessingResults");
					String id=request.getParameter("id");
					BusinessCirclesAppealOrder businessCirclesAppealOrder=businessCirclesAppealOrderService.selectByPrimaryKey(Integer.valueOf(id));
					m.addObject("businessCirclesAppealOrder", businessCirclesAppealOrder);
					return m;
				}
				
				//添加法务处理意见
				@ResponseBody
				@RequestMapping(value ="/businessCirclesAppealOrder/addfwProcessingResults.shtml")
				public ModelAndView addfwProcessingResults(HttpServletRequest request,BusinessCirclesAppealOrder businessCirclesAppealOrder) {
					String rtPage = "/success/success";
					Map<String, Object> resMap = new HashMap<String, Object>();
					String code = null;
					String msg = "";
					try {
						StaffBean staffBean = this.getSessionStaffBean(request);
						int staffId=Integer.valueOf(staffBean.getStaffID());
						
						 String id=request.getParameter("id");
						 BusinessCirclesAppealOrder businessCirclesAppealOrder2=businessCirclesAppealOrderService.selectByPrimaryKey(Integer.valueOf(id));
						 businessCirclesAppealOrder2.setAppealStatus(businessCirclesAppealOrder.getAppealStatus());
						 businessCirclesAppealOrder2.setFwResult(businessCirclesAppealOrder.getFwResult());
						 businessCirclesAppealOrder2.setFwResultDate(new Date());
						 businessCirclesAppealOrder2.setUpdateBy(staffId);
						 businessCirclesAppealOrder2.setUpdateDate(new Date());
						 businessCirclesAppealOrderService.updateByPrimaryKey(businessCirclesAppealOrder2);
						
						code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
						msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						code = StateCode.JSON_AJAX_ERROR.getStateCode();
						msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
					}
					resMap.put(this.JSON_RESULT_CODE, code);
					resMap.put(this.JSON_RESULT_MESSAGE, msg);
					return new ModelAndView(rtPage, resMap); 
				}
				
				
				//工商处理结果
				@RequestMapping("/businessCirclesAppealOrder/businessCirclesResult.shtml")
				public ModelAndView businessCirclesResult(HttpServletRequest request) {
					ModelAndView m = new ModelAndView("/businessCirclesAppealOrder/businessCirclesResult");
					String id=request.getParameter("id");
					BusinessCirclesAppealOrder businessCirclesAppealOrder=businessCirclesAppealOrderService.selectByPrimaryKey(Integer.valueOf(id));
					m.addObject("businessCirclesAppealOrder", businessCirclesAppealOrder);
					return m;
				}
				
					
				//添加工商处理结果
				@ResponseBody
				@RequestMapping(value ="/businessCirclesAppealOrder/addbusinessCirclesResult.shtml")
				public ModelAndView addbusinessCirclesResult(HttpServletRequest request,BusinessCirclesAppealOrder businessCirclesAppealOrder) {
					String rtPage = "/success/success";
					Map<String, Object> resMap = new HashMap<String, Object>();
					String code = null;
					String msg = "";
					try {
						StaffBean staffBean = this.getSessionStaffBean(request);
						int staffId=Integer.valueOf(staffBean.getStaffID());
						
						 String id=request.getParameter("id");
						 BusinessCirclesAppealOrder businessCirclesAppealOrder2=businessCirclesAppealOrderService.selectByPrimaryKey(Integer.valueOf(id));
						 businessCirclesAppealOrder2.setBusinessCirclesResult(businessCirclesAppealOrder.getBusinessCirclesResult());
						 businessCirclesAppealOrder2.setBusinessCirclesDate(new Date());
						 businessCirclesAppealOrder2.setUpdateBy(staffId);
						 businessCirclesAppealOrder2.setUpdateDate(new Date());
						 businessCirclesAppealOrderService.updateByPrimaryKey(businessCirclesAppealOrder2);
						
						code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
						msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						code = StateCode.JSON_AJAX_ERROR.getStateCode();
						msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
					}
					resMap.put(this.JSON_RESULT_CODE, code);
					resMap.put(this.JSON_RESULT_MESSAGE, msg);
					return new ModelAndView(rtPage, resMap); 
				}
				
				
				//全部工商投诉
				@RequestMapping("/service/businessCirclesAppeal/wholebusinessCirclesAppealOrderList.shtml")
				public ModelAndView wholebusinessCirclesAppealOrderList(HttpServletRequest request) {
					ModelAndView m = new ModelAndView("/businessCirclesAppealOrder/wholebusinessCirclesAppealOrderList");
					m.addObject("staffidList",businessCirclesAppealOrderService.getstaffidList());
					m.addObject("createbyList",businessCirclesAppealOrderService.getcreatebyList());
					return m;
				}
				
				//全部工商投诉数据列表
				@ResponseBody
				@RequestMapping("/businessCirclesAppealOrder/wholebusinessCirclesAppealOrderdata.shtml")
				public Map<String, Object> wholebusinessCirclesAppealOrderdata(HttpServletRequest request, Page page) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					List<BusinessCirclesAppealOrderCustom> dataList = null;
					Integer totalCount = 0;
					try {						
						BusinessCirclesAppealOrderCustomExample businessCirclesAppealOrderCustomExample=new BusinessCirclesAppealOrderCustomExample();
						BusinessCirclesAppealOrderCustomExample.BusinessCirclesAppealOrderCustomCriteria criteria=businessCirclesAppealOrderCustomExample.createCriteria();
						criteria.andDelFlagEqualTo("0");
						businessCirclesAppealOrderCustomExample.setOrderByClause("create_date desc");
						if (!StringUtil.isEmpty(request.getParameter("startconsumerAppealDate")) && !StringUtil.isEmpty(request.getParameter("endconsumerAppealDate"))) {
							criteria.andConsumerAppealDateBetween(sdf.parse(request.getParameter("startconsumerAppealDate")+" 00:00:00"), sdf.parse(request.getParameter("endconsumerAppealDate")+" 23:59:59"));				
						}
						if (!StringUtil.isEmpty(request.getParameter("appealOrderType"))) {
							if (request.getParameter("appealOrderType").equals("1")) {
								criteria.andAppealOrderTypeEqualTo("1");
							}
							if (request.getParameter("appealOrderType").equals("2")) {
								criteria.andAppealOrderTypeEqualTo("2");
							}

						}
						if (!StringUtil.isEmpty(request.getParameter("appealStatus"))) {
							String appealStatus=request.getParameter("appealStatus");
							if (appealStatus.equals("0")) {
								criteria.andAppealStatusEqualTo("0");	
							}else if (appealStatus.equals("1")) {
								criteria.andAppealStatusEqualTo("1");
							}else if (appealStatus.equals("2")) {
								criteria.andAppealStatusEqualTo("2");
							}else if (appealStatus.equals("3")) {
								criteria.andAppealStatusEqualTo("3");
							}else if (appealStatus.equals("4")) {
								criteria.andAppealStatusEqualTo("4");
							}else if (appealStatus.equals("5")) {
								criteria.andAppealStatusEqualTo("5");
							}
						}
						if (!StringUtil.isEmpty(request.getParameter("memberIds"))) {
							criteria.andmemberIdEqualTo(request.getParameter("memberIds"));
						}
						if (!StringUtil.isEmpty(request.getParameter("subOrderCode"))) {
							criteria.andSubOrderCodeEqualTo(request.getParameter("subOrderCode"));
						}
						if (!StringUtil.isEmpty(request.getParameter("staffId"))) {
							criteria.andCustomerServiceHandlerEqualTo(Integer.valueOf(request.getParameter("staffId")));
						}
						if (!StringUtil.isEmpty(request.getParameter("companyName"))) {
							criteria.andcompanyNameEqualTo("%"+request.getParameter("companyName")+"%");
						}
						if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
							criteria.andmchtCodeEqualTo(request.getParameter("mchtCode"));
						}
						if (!StringUtil.isEmpty(request.getParameter("registrationNumber"))) {
							criteria.andRegistrationNumberEqualTo(request.getParameter("registrationNumber"));
						}
						if (!StringUtil.isEmpty(request.getParameter("createby"))) {
							criteria.andCreateByEqualTo(Integer.valueOf(request.getParameter("createby")));
						}
						businessCirclesAppealOrderCustomExample.setLimitStart(page.getLimitStart());
						businessCirclesAppealOrderCustomExample.setLimitSize(page.getLimitSize());
						dataList=businessCirclesAppealOrderService.selectByCustomExample(businessCirclesAppealOrderCustomExample);
						totalCount=businessCirclesAppealOrderService.countByCustomExample(businessCirclesAppealOrderCustomExample);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					resMap.put("Rows", dataList);
					resMap.put("Total", totalCount);
					return resMap;
				}
				
				//变更处理人界面
				@RequestMapping("/businessCirclesAppealOrder/changeStaffDialog.shtml")
				public ModelAndView changeStaff(String id) {
					ModelAndView m = new ModelAndView("/businessCirclesAppealOrder/changeStaff");
					m.addObject("staffList", businessCirclesAppealOrderService.getstaffidList());
					m.addObject("id", id);
					return m;
				}
				//变更处理人数据
				@ResponseBody
				@RequestMapping(value ="/businessCirclesAppealOrder/changeStaffId.shtml")
				public Map<String, Object> changeStaffId(HttpServletRequest request,int id, int customerServiceHandler) {
					Map<String, Object> map = new HashMap<String, Object>();
					try {
						StaffBean staffBean = this.getSessionStaffBean(request);
						int staffId=Integer.valueOf(staffBean.getStaffID());
						
						BusinessCirclesAppealOrder businessCirclesAppealOrder2=businessCirclesAppealOrderService.selectByPrimaryKey(id);
						businessCirclesAppealOrder2.setCustomerServiceHandler(customerServiceHandler);
						businessCirclesAppealOrder2.setUpdateBy(staffId);
						businessCirclesAppealOrder2.setUpdateDate(new Date());
					    businessCirclesAppealOrderService.updateByPrimaryKey(businessCirclesAppealOrder2);
						map.put("code", "200");
					} catch (Exception e) {
						e.printStackTrace();
						map.put("code", "999");
					}
					return map;
				}
				
				
				
				     //导出工商投诉数据
					@RequestMapping(value = "/businessCirclesAppealOrder/businessCirclesAppealOrderExport.shtml")
					public void subExport(HttpServletRequest request,HttpServletResponse response) throws Exception {
						try {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							StaffBean staffBean = this.getSessionStaffBean(request);
							int staffId=Integer.valueOf(staffBean.getStaffID());
							BusinessCirclesAppealOrderCustomExample businessCirclesAppealOrderCustomExample=new BusinessCirclesAppealOrderCustomExample();
							BusinessCirclesAppealOrderCustomExample.BusinessCirclesAppealOrderCustomCriteria criteria=businessCirclesAppealOrderCustomExample.createCriteria();
							criteria.andDelFlagEqualTo("0").andCreateByEqualTo(staffId);
							businessCirclesAppealOrderCustomExample.setOrderByClause("create_date desc");
							if (!StringUtil.isEmpty(request.getParameter("startconsumerAppealDate")) && !StringUtil.isEmpty(request.getParameter("endconsumerAppealDate"))) {
								criteria.andConsumerAppealDateBetween(sdf.parse(request.getParameter("startconsumerAppealDate")+" 00:00:00"), sdf.parse(request.getParameter("endconsumerAppealDate")+" 23:59:59"));				
							}
							if (!StringUtil.isEmpty(request.getParameter("appealOrderType"))) {
								if (request.getParameter("appealOrderType").equals("1")) {
									criteria.andAppealOrderTypeEqualTo("1");
								}
								if (request.getParameter("appealOrderType").equals("2")) {
									criteria.andAppealOrderTypeEqualTo("2");
								}

							}
							if (!StringUtil.isEmpty(request.getParameter("appealStatus"))) {
								String appealStatus=request.getParameter("appealStatus");
								if (appealStatus.equals("0")) {
									criteria.andAppealStatusEqualTo("0");	
								}else if (appealStatus.equals("1")) {
									criteria.andAppealStatusEqualTo("1");
								}else if (appealStatus.equals("2")) {
									criteria.andAppealStatusEqualTo("2");
								}else if (appealStatus.equals("3")) {
									criteria.andAppealStatusEqualTo("3");
								}else if (appealStatus.equals("4")) {
									criteria.andAppealStatusEqualTo("4");
								}else if (appealStatus.equals("5")) {
									criteria.andAppealStatusEqualTo("5");
								}
							}
							if (!StringUtil.isEmpty(request.getParameter("memberIds"))) {
								criteria.andmemberIdEqualTo(request.getParameter("memberIds"));
							}
							if (!StringUtil.isEmpty(request.getParameter("subOrderCode"))) {
								criteria.andSubOrderCodeEqualTo(request.getParameter("subOrderCode"));
							}
							if (!StringUtil.isEmpty(request.getParameter("staffId"))) {
								criteria.andCustomerServiceHandlerEqualTo(Integer.valueOf(request.getParameter("staffId")));
							}
							if (!StringUtil.isEmpty(request.getParameter("companyName"))) {
								criteria.andcompanyNameEqualTo(request.getParameter("companyName"));
							}
							if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
								criteria.andmchtCodeEqualTo(request.getParameter("mchtCode"));
							}
							if (!StringUtil.isEmpty(request.getParameter("registrationNumber"))) {
								criteria.andRegistrationNumberEqualTo(request.getParameter("registrationNumber"));
							}
							if (!StringUtil.isEmpty(request.getParameter("createby"))) {
								criteria.andCreateByEqualTo(Integer.valueOf(request.getParameter("createby")));
							}
							List<BusinessCirclesAppealOrderCustom> dataList=businessCirclesAppealOrderService.selectByCustomExample(businessCirclesAppealOrderCustomExample);
							String[] titles = { "序号", "消费者投诉时间", "客服领取时间", "投诉单登入编号", "投诉单类型", "投诉人姓名", "投诉人地址", "消费者投诉内容", "被投诉类型", "投诉单跟进状态","商家信息","商家序号", 
									"被投诉的品牌", "商家被投诉的次数", "子订单编号", "签收时间", "商家首次处理时间","商家的解决方案", "商家处理进度", "平台首次处理时间","平台处理进度","法务处理建议","工商处理情况"};
							ExcelBean excelBean = new ExcelBean("导出工商投诉列表.xls", "导出工商投诉列表", titles);
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
							List<String[]> datas = new ArrayList<String[]>();
							for (BusinessCirclesAppealOrderCustom businessCirclesAppealOrderCustom : dataList) {
								   String appealOrderType="";
								if (businessCirclesAppealOrderCustom.getAppealOrderType().equals("1")) {
									appealOrderType="投诉单";
								}else {
									appealOrderType="举报单";
								}
								String appealStatus="";
								if (businessCirclesAppealOrderCustom.getAppealStatus().equals("0")) {
									appealStatus="待跟进";
								}
								if (businessCirclesAppealOrderCustom.getAppealStatus().equals("1")) {
									appealStatus="客服处理中";
								}
								if (businessCirclesAppealOrderCustom.getAppealStatus().equals("2")) {
									appealStatus="待法务处理";
								}
								if (businessCirclesAppealOrderCustom.getAppealStatus().equals("3")) {
									appealStatus="内部结案移交工商";
								}
								if (businessCirclesAppealOrderCustom.getAppealStatus().equals("4")) {
									appealStatus="未结案移交工商";
								}
								if (businessCirclesAppealOrderCustom.getAppealStatus().equals("5")) {
									appealStatus="驳回跟进";
								}
							    CustomerServiceRecords customerServiceRecords=customerServiceRecordsService.selectByPrimaryKey(businessCirclesAppealOrderCustom.getCustomerServiceRecordsid());
							    Date mchtDealDate=null;
							    String mchtComplain="";
							    String mchtProcessingProgress="";
							    Date platformDealDate=null;
							    String PlatformProcessingProgress="";
							    if (customerServiceRecords!=null) {
							    	mchtDealDate=customerServiceRecords.getMchtDealDate();
							    	mchtComplain=customerServiceRecords.getMchtComplain();
							    	mchtProcessingProgress=customerServiceRecords.getMchtProcessingProgress();
							    	platformDealDate=customerServiceRecords.getPlatformDealDate();
							    	PlatformProcessingProgress=customerServiceRecords.getPlatformProcessingProgress();
								  }
							  String[] data = {
									  businessCirclesAppealOrderCustom.getId().toString(),
									  businessCirclesAppealOrderCustom.getConsumerAppealDate()==null?"":df.format(businessCirclesAppealOrderCustom.getConsumerAppealDate()),
								      businessCirclesAppealOrderCustom.getCustomerServiceResultDate()==null?"":df.format(businessCirclesAppealOrderCustom.getCustomerServiceResultDate()),
								      businessCirclesAppealOrderCustom.getRegistrationNumber()==null?"":businessCirclesAppealOrderCustom.getRegistrationNumber(),
								      appealOrderType,
								      businessCirclesAppealOrderCustom.getAppealName()==null?"":businessCirclesAppealOrderCustom.getAppealName(),
								      businessCirclesAppealOrderCustom.getAppealAddress()==null?"":businessCirclesAppealOrderCustom.getAppealAddress(),	
								      businessCirclesAppealOrderCustom.getConsumerAppealContent()==null?"":businessCirclesAppealOrderCustom.getConsumerAppealContent(),
								      businessCirclesAppealOrderCustom.getTypesOfComplaints()==null?"":businessCirclesAppealOrderCustom.getTypesOfComplaints(),	
								      appealStatus,
								      businessCirclesAppealOrderCustom.getMchtCompanyname()==null?"":businessCirclesAppealOrderCustom.getMchtCompanyname().replaceAll(",", "\n"),
								      businessCirclesAppealOrderCustom.getMchtCode()==null?"":businessCirclesAppealOrderCustom.getMchtCode().replaceAll(",", "\n"),
								      businessCirclesAppealOrderCustom.getProductbrandName()==null?"":businessCirclesAppealOrderCustom.getProductbrandName().replaceAll(",", "\n"),
								      businessCirclesAppealOrderCustom.getMchtAppealedCount()==null?"":businessCirclesAppealOrderCustom.getMchtAppealedCount().toString(),
								      businessCirclesAppealOrderCustom.getSubOrderCode()==null?"":businessCirclesAppealOrderCustom.getSubOrderCode().toString(),
								      businessCirclesAppealOrderCustom.getSignForDate()==null?"":df.format(businessCirclesAppealOrderCustom.getSignForDate()),
								      mchtDealDate==null?"":df.format(mchtDealDate),
								      mchtComplain==null?"":mchtComplain,
								      mchtProcessingProgress==null?"":mchtProcessingProgress,
								      platformDealDate==null?"":df.format(platformDealDate),
								      PlatformProcessingProgress==null?"":PlatformProcessingProgress,
								      businessCirclesAppealOrderCustom.getFwResult()==null?"":businessCirclesAppealOrderCustom.getFwResult(),
								      businessCirclesAppealOrderCustom.getBusinessCirclesResult()==null?"":businessCirclesAppealOrderCustom.getBusinessCirclesResult()
									
							  };
							
							datas.add(data);	
							
							}
							excelBean.setDataList(datas);
							ExcelUtils.export(excelBean, response);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				
				    //获取附件
					@RequestMapping(value ="/businessCirclesAppealOrder/checkFileExists.shtml")
					@ResponseBody
					public Map<String, Object> checkFileExists(HttpServletRequest request) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("code", "200");
						map.put("msg", "操作成功！");
						String customerServiceRecordsFilesId = request.getParameter("customerServiceRecordsFilesId");
						CustomerServiceRecordsFile customerServiceRecordsFile=customerServiceRecordsFileService.selectByPrimaryKey(Integer.valueOf(customerServiceRecordsFilesId));
						if(customerServiceRecordsFile != null){
							InputStream stream = OrderController.class.getResourceAsStream("/base_config.properties");
							String defaultPath=null;
							try {
								Properties properties = new Properties();
								properties.load(stream);
								defaultPath = properties.getProperty("annex.filePath");
								stream.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
							if(defaultPath==null){
								map.put("code", "4004");
								map.put("msg", "文件目录不存在");
								return map;
							}
							File file = new File(defaultPath+customerServiceRecordsFile.getFilePath());
							//如果文件不存在
							if(!file.exists()){
								map.put("code", "4004");
								map.put("msg", "附件不存在或已被删除");
								return map;
							}
						}else{
							map.put("code", "4004");
							map.put("msg", "附件不存在或已被删除");
							return map;
						}
						return map;
					}
					
					//下载附件
					@RequestMapping(value ="/customerServiceRecordsFilesId/downLoadcustomerServiceRecordsAttachment.shtml")
					public void downLoadAttachment(HttpServletRequest request,HttpServletResponse response) throws Exception {
						String filePath = request.getParameter("filePath");
						/*String fileName = request.getParameter("fileName");*/
						InputStream stream = OrderController.class.getResourceAsStream("/base_config.properties");
						String defaultPath=null;
						try {
							Properties properties = new Properties();
							properties.load(stream);
							defaultPath = properties.getProperty("annex.filePath");
							stream.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
						if(defaultPath==null){
							return;
						}
						File file = new File(defaultPath+filePath);
						//如果文件不存在
						if(!file.exists()){
						    return;
						}
						//设置响应头，控制浏览器下载该文件
						String downloadFileName = "";
						String agent = request.getHeader("USER-AGENT");  
				        if(agent != null && agent.toLowerCase().indexOf("firefox") > 0)
				        {
				            downloadFileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(filePath.getBytes("UTF-8")))) + "?=";    
				        }
				        else
				        {
				            downloadFileName =  java.net.URLEncoder.encode(filePath, "UTF-8");
				        }
						response.setHeader("content-disposition", "attachment;filename=" + downloadFileName);
						//读取要下载的文件，保存到文件输入流
						FileInputStream in = new FileInputStream(defaultPath+filePath);
						//创建输出流
						OutputStream out = response.getOutputStream();
						//缓存区
						byte buffer[] = new byte[1024];
						int len = 0;
						//循环将输入流中的内容读取到缓冲区中
						while((len = in.read(buffer)) > 0){
						    out.write(buffer, 0, len);
						}
						//关闭
						in.close();
						out.flush();
						out.close(); 
					}
}
