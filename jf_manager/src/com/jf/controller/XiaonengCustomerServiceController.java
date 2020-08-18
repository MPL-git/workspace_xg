package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.utils.ExcelUtils;
import com.jf.entity.*;
import com.jf.service.MchtInfoService;
import com.jf.service.XiaonengCustomerserviceService;
import com.jf.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@SuppressWarnings("serial")
public class XiaonengCustomerServiceController extends BaseController {

	@Autowired
	private MchtInfoService mchtInfoService;
	
	@Autowired
	private XiaonengCustomerserviceService xiaonengCustomerserviceService;

	/**
	 * 
	 * @Title xiaonengCustomerServiceManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年4月21日 下午3:23:33
	 */
	@RequestMapping("/xiaonengCustomerService/xiaonengCustomerServiceManager.shtml")
	public ModelAndView xiaonengCustomerServiceManager(HttpServletRequest request, String statusPage) {
		ModelAndView m = new ModelAndView();
		if(!StringUtil.isEmpty(statusPage) && "1".equals(statusPage)) { //小能客服待开通
			m.setViewName("/xiaonengCustomerService/xiaonengCustomerServiceOpenList");
		}else if(!StringUtil.isEmpty(statusPage) && "2".equals(statusPage)) { //选择客服序号
			m.setViewName("/xiaonengCustomerService/xiaonengCustomerServiceSelectList");
		}else if(!StringUtil.isEmpty(statusPage) && "3".equals(statusPage)) { //小能客服待关闭
			m.setViewName("/xiaonengCustomerService/xiaonengCustomerServiceCloseList");
		}
		return m;
	}
	
	/**
	 * 
	 * @Title xiaonengCustomerServiceList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年4月21日 下午4:01:30
	 */
	@ResponseBody
	@RequestMapping("/xiaonengCustomerService/xiaonengCustomerServiceList.shtml")
	public Map<String, Object> xiaonengCustomerServiceList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<MchtInfoCustom> dataList = null;
		Integer totalCount = 0;
		try {
			MchtInfoCustomExample mchtInfoCustomExample = new MchtInfoCustomExample();
			MchtInfoCustomExample.MchtInfoCustomCriteria mchtInfoCustomCriteria = mchtInfoCustomExample.createCriteria();
			mchtInfoCustomCriteria.andDelFlagEqualTo("0");
			String flagStatus = request.getParameter("flagStatus");
			if(!StringUtil.isEmpty(flagStatus) && "1".equals(flagStatus)) { //商家合作状态=（正常 或 暂停） 
				List<String> statusList = new ArrayList<String>();
				statusList.add("1"); //1 正常
				statusList.add("2"); //2 业务暂停
				mchtInfoCustomCriteria.andStatusIn(statusList);
			}
			if(!StringUtil.isEmpty(flagStatus) && "2".equals(flagStatus)) { //已开通的小能的商家
				mchtInfoCustomCriteria.andXiaonengIdIsNotNull();
			}
			if(!StringUtil.isEmpty(flagStatus) && "3".equals(flagStatus)) { //商家合作状态=关闭   且已开通的小能的商家 
				mchtInfoCustomCriteria.andStatusEqualTo("3");
				mchtInfoCustomCriteria.andXiaonengIdIsNotNull();
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))) { //商家编码
				mchtInfoCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtName"))) { //名称
				mchtInfoCustomCriteria.andMchtNameLike(request.getParameter("mchtName"));
			}
			if(!StringUtil.isEmpty(request.getParameter("xiaonengId"))) { //客服序号
				mchtInfoCustomCriteria.andXiaonengIdEqualTo(Integer.parseInt(request.getParameter("xiaonengId")));
			}
			if(!StringUtil.isEmpty(request.getParameter("xiaonengFlag"))) { //开通状态 0未开通 1已开通
				if("0".equals(request.getParameter("xiaonengFlag"))) {
					mchtInfoCustomCriteria.andXiaonengIdIsNull();
				}
				if("1".equals(request.getParameter("xiaonengFlag"))) {
					mchtInfoCustomCriteria.andXiaonengIdIsNotNull();
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("xiaonengStatus"))) { //客服启用状态
				mchtInfoCustomCriteria.andXiaonengStatusEqualTo(request.getParameter("xiaonengStatus"));
			}
			if(!StringUtil.isEmpty(request.getParameter("xiaonengCode"))) { //客服代码
				mchtInfoCustomCriteria.andXiaonengCodeEqualTo(request.getParameter("xiaonengCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("xiaonengBusId"))) { //客服企业ID
				mchtInfoCustomCriteria.andXiaonengBusIdEqualTo(Integer.parseInt(request.getParameter("xiaonengBusId")));
			}
			mchtInfoCustomExample.setOrderByClause(" a.id desc");
			mchtInfoCustomExample.setLimitStart(page.getLimitStart());
			mchtInfoCustomExample.setLimitSize(page.getLimitSize());
			dataList = mchtInfoService.selectByExample(mchtInfoCustomExample);
			totalCount = mchtInfoService.countByExample(mchtInfoCustomExample);
			if (totalCount==0){
				resMap.put("returnCode", "0000");
				resMap.put("returnMsg", "无搜索结果");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title openXiaoneng   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年4月21日 下午5:25:43
	 */
	@RequestMapping("/xiaonengCustomerService/openXiaoneng.shtml")
	public ModelAndView openXiaoneng(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/xiaonengCustomerService/openXiaoneng");
		String mchtId = request.getParameter("mchtId");
		if(!StringUtil.isEmpty(mchtId)) {
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(Integer.parseInt(mchtId));
			m.addObject("mchtInfo", mchtInfo);
		}
		m.addObject("flagStatus", request.getParameter("flagStatus"));
		return m;
	}
	
	/**
	 * 
	 * @Title addOrUpdateXiaonengCustomerService   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年4月23日 下午12:05:03
	 */
	@RequestMapping("/xiaonengCustomerService/addOrUpdateXiaonengCustomerService.shtml")
	public ModelAndView addOrUpdateXiaonengCustomerService(HttpServletRequest request, Xiaonengcustomerservice xiaonengCustomerService) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			Integer staffId = Integer.valueOf(staffBean.getStaffID());
			xiaonengCustomerserviceService.addOrUpdateXiaonengCustomerService(request, xiaonengCustomerService, staffId);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap); 
	}
	
	/**
	 * 
	 * @Title updateXiaoneng   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年4月23日 下午12:06:28
	 */
	@RequestMapping("/xiaonengCustomerService/updateXiaoneng.shtml")
	public ModelAndView updateXiaoneng(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/xiaonengCustomerService/updateXiaoneng");
		String xiaonengId = request.getParameter("xiaonengId");
		String mchtId = request.getParameter("mchtId");
		Xiaonengcustomerservice xiaonengCustomerservice = xiaonengCustomerserviceService.selectByPrimaryKey(Integer.parseInt(xiaonengId));
		m.addObject("xiaonengCustomerservice", xiaonengCustomerservice);
		m.addObject("mchtId", mchtId);
		return m;
	}
	
	/**
	 * 
	 * @Title updateXiaonengCustomerService   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年4月23日 下午12:19:39
	 */
	@RequestMapping("/xiaonengCustomerService/updateXiaonengCustomerService.shtml")
	public ModelAndView updateXiaonengCustomerService(HttpServletRequest request, Xiaonengcustomerservice xiaonengCustomerService) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			Integer staffId = Integer.valueOf(staffBean.getStaffID());
			xiaonengCustomerserviceService.updateXiaonengCustomerService(request, xiaonengCustomerService, staffId);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap); 
	}

	/**
	 *
	 * @Title compoundXiaoneng
	 * @Description 合成小能信息
	 * @author dlj
	 * @date 2020年3月14日
	 */
	@ResponseBody
	@RequestMapping("/xiaonengCustomerService/compoundXiaoneng.shtml")
	public Map<String, Object> compoundXiaoneng(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		String mchtId = request.getParameter("mchtId");
		Integer staffId = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		try {
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(Integer.parseInt(mchtId));
			xiaonengCustomerserviceService.compoundXiaoneng(mchtInfo,staffId);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
		}
		return resMap;
	}
	/**
	 *
	 * @Title ExportXiaonengMcht
	 * @Description 导出小能客服的商家
	 * @author dlj
	 * @date 2020年3月14日
	 */
	@RequestMapping("/xiaonengCustomerService/ExportXiaonengMcht.shtml")
	public void ExportXiaoneng(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		String ids = request.getParameter("ids");
		try {
			String[] titles = { "企业id", "商户名称", "商户账号", "密码"};
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			ExcelBean excelBean = new ExcelBean("商家小能信息"+df.format(new Date())+".xls",
					"商家小能信息", titles);
			List<String[]> datas = new ArrayList<String[]>();//整个表
			String[] split = ids.split(",");
			List<Integer> asList = new ArrayList<>();
			for (String s : split) {
				asList.add(Integer.parseInt(s));
			}
			MchtInfoCustomExample where = new MchtInfoCustomExample();
			where.createCriteria().andIdIn(asList);
			List<MchtInfoCustom> mchtInfoCustoms = mchtInfoService.selectByExample(where);
			for(MchtInfoCustom mchtInfoCustom:mchtInfoCustoms){
				if (mchtInfoCustom.getXiaonengId()!=null){
				Integer xiaonengId = mchtInfoCustom.getXiaonengId();
					Xiaonengcustomerservice xiaonengCustomerService = xiaonengCustomerserviceService.selectByPrimaryKey(xiaonengId);
				String[] data = {
						xiaonengCustomerService.getBusId()==null?"":xiaonengCustomerService.getBusId().toString(),
						xiaonengCustomerService.getMchtName()==null?"":xiaonengCustomerService.getMchtName(),
						xiaonengCustomerService.getAccount()==null?"":xiaonengCustomerService.getAccount(),
						xiaonengCustomerService.getPassword()==null?"":xiaonengCustomerService.getPassword()
					};
				datas.add(data);
				}else {
					String[] data = {
							mchtInfoCustom.getMchtCode()==null?"":mchtInfoCustom.getMchtCode().substring(1),
							mchtInfoCustom.getCompanyName()==null?"":mchtInfoCustom.getCompanyName(),
							"未开通无商家账号,请点击合成",
							"未开通无初始密码,请点击合成"
					};
					datas.add(data);
				}
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Title delXiaoneng   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年4月23日 下午1:55:57
	 */
	@ResponseBody
	@RequestMapping("/xiaonengCustomerService/delXiaoneng.shtml")
	public Map<String, Object> delXiaoneng(HttpServletRequest request, Integer xiaonengId, Integer mchtId) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			Integer staffId = Integer.valueOf(staffBean.getStaffID());
			xiaonengCustomerserviceService.delXiaoneng(staffId, xiaonengId, mchtId);
		} catch (Exception e) {
			e.printStackTrace();
			code = "9999";
			msg = "操作失败！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map; 
	}
	
	/**
	 * 
	 * @Title xiaonengCustomerServiceData   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月2日 上午11:22:45
	 */
	@ResponseBody
	@RequestMapping("/xiaonengCustomerService/xiaonengCustomerServiceData.shtml")
	public Map<String, Object> xiaonengCustomerServiceData(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<Xiaonengcustomerservice> dataList = null;
		Integer totalCount = 0;
		try {
			XiaonengcustomerserviceExample xiaonengcustomerserviceExample = new XiaonengcustomerserviceExample();
			XiaonengcustomerserviceExample.Criteria xiaonengcustomerserviceCriteria = xiaonengcustomerserviceExample.createCriteria();
			xiaonengcustomerserviceCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1");
			if(!StringUtil.isEmpty(request.getParameter("mchtName"))) { //商户名称
				xiaonengcustomerserviceCriteria.andMchtNameLike("%"+request.getParameter("mchtName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("xiaonengId"))) { 
				xiaonengcustomerserviceCriteria.andIdEqualTo(Integer.parseInt(request.getParameter("xiaonengId")));
			}
			if(!StringUtil.isEmpty(request.getParameter("xiaonengBusId"))) { 
				xiaonengcustomerserviceCriteria.andBusIdEqualTo(Integer.parseInt(request.getParameter("xiaonengBusId")));
			}
			xiaonengcustomerserviceExample.setOrderByClause(" id desc");
			xiaonengcustomerserviceExample.setLimitStart(page.getLimitStart());
			xiaonengcustomerserviceExample.setLimitSize(page.getLimitSize());
			dataList = xiaonengCustomerserviceService.selectByExample(xiaonengcustomerserviceExample);
			totalCount = xiaonengCustomerserviceService.countByExample(xiaonengcustomerserviceExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
}
