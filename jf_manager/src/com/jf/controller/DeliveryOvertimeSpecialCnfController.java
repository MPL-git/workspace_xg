package com.jf.controller;

import com.gzs.common.utils.DateUtil;
import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.*;
import com.jf.service.AreaService;
import com.jf.service.DeliveryOvertimeSpecialCnfAreaService;
import com.jf.service.DeliveryOvertimeSpecialCnfService;
import com.jf.vo.Page;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Pengl
 * @create 2020-02-26 上午 11:19
 */
@Controller
public class DeliveryOvertimeSpecialCnfController extends BaseController {

	@Autowired
	private DeliveryOvertimeSpecialCnfService deliveryOvertimeSpecialCnfService;

	@Autowired
	private DeliveryOvertimeSpecialCnfAreaService deliveryOvertimeSpecialCnfAreaService;

	@Autowired
	private AreaService areaService;

	@RequestMapping("/deliveryOvertimeSpecialCnf/deliveryOvertimeSpecialCnfManager.shtml")
	public ModelAndView deliveryOvertimeSpecialCnfManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/deliveryOvertimeSpecialCnf/deliveryOvertimeSpecialCnfList");
		return m;
	}

	@ResponseBody
	@RequestMapping("/deliveryOvertimeSpecialCnf/deliveryOvertimeSpecialCnfList.shtml")
	public Map<String, Object> deliveryOvertimeSpecialCnfList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<DeliveryOvertimeSpecialCnf> dataList = null;
		Integer totalCount = 0;
		try {
			DeliveryOvertimeSpecialCnfExample deliveryOvertimeSpecialCnfExample = new DeliveryOvertimeSpecialCnfExample();
			DeliveryOvertimeSpecialCnfExample.Criteria specialAreaOvertimeCnfCriteria = deliveryOvertimeSpecialCnfExample.createCriteria();
			specialAreaOvertimeCnfCriteria.andDelFlagEqualTo("0");
			if(!StringUtils.isEmpty(request.getParameter("payDate"))) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date payDate = sdf.parse(request.getParameter("payDate")+" 00:00:00");
				specialAreaOvertimeCnfCriteria.andBeginPayDateLessThanOrEqualTo(payDate);
				specialAreaOvertimeCnfCriteria.andEndPayDateGreaterThanOrEqualTo(payDate);
			}
			deliveryOvertimeSpecialCnfExample.setOrderByClause(" id desc");
			deliveryOvertimeSpecialCnfExample.setLimitStart(page.getLimitStart());
			deliveryOvertimeSpecialCnfExample.setLimitSize(page.getLimitSize());
			totalCount = deliveryOvertimeSpecialCnfService.countByExample(deliveryOvertimeSpecialCnfExample);
			dataList = deliveryOvertimeSpecialCnfService.selectByExample(deliveryOvertimeSpecialCnfExample);
			for(DeliveryOvertimeSpecialCnf deliveryOvertimeSpecialCnf : dataList) {
				DeliveryOvertimeSpecialCnfAreaExample deliveryOvertimeSpecialCnfAreaExample = new DeliveryOvertimeSpecialCnfAreaExample();
				deliveryOvertimeSpecialCnfAreaExample.createCriteria()
						.andDelFlagEqualTo("0")
						.andSpecialCnfIdEqualTo(deliveryOvertimeSpecialCnf.getId());
				List<DeliveryOvertimeSpecialCnfArea> deliveryOvertimeSpecialCnfAreaList = deliveryOvertimeSpecialCnfAreaService.selectByExample(deliveryOvertimeSpecialCnfAreaExample);
				String address = "";
				for (int i = 1; i <= deliveryOvertimeSpecialCnfAreaList.size(); i++) {
					if(!"".equals(address)) {
						address += "<br/>";
					}
					address += i + "、" + deliveryOvertimeSpecialCnfAreaList.get(i-1).getAddress();
				}
				deliveryOvertimeSpecialCnf.setRemarks(address);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}

	@ResponseBody
	@RequestMapping("/deliveryOvertimeSpecialCnf/delDeliveryOvertimeSpecialCnf.shtml")
	public Map<String, Object> delDeliveryOvertimeSpecialCnf(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			String deliveryOvertimeSpecialCnfId = request.getParameter("deliveryOvertimeSpecialCnfId");
			if(!StringUtil.isEmpty(deliveryOvertimeSpecialCnfId)) {
				String staffId = this.getSessionStaffBean(request).getStaffID();
				DeliveryOvertimeSpecialCnf deliveryOvertimeSpecialCnf = new DeliveryOvertimeSpecialCnf();
				deliveryOvertimeSpecialCnf.setId(Integer.parseInt(deliveryOvertimeSpecialCnfId));
				deliveryOvertimeSpecialCnf.setUpdateBy(Integer.parseInt(staffId));
				deliveryOvertimeSpecialCnf.setUpdateDate(new Date());
				deliveryOvertimeSpecialCnf.setDelFlag("1");
				deliveryOvertimeSpecialCnfService.delDeliveryOvertimeSpecialCnf(deliveryOvertimeSpecialCnf);
			}
		} catch (Exception e) {
			e.printStackTrace();
			code = "9999";
			msg = "系统异常请稍后再试！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}

	@RequestMapping("/deliveryOvertimeSpecialCnf/editDeliveryOvertimeSpecialCnfManager.shtml")
	public ModelAndView editDeliveryOvertimeSpecialCnfManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/deliveryOvertimeSpecialCnf/editDeliveryOvertimeSpecialCnf");
		String deliveryOvertimeSpecialCnfId = request.getParameter("deliveryOvertimeSpecialCnfId");
		if(!StringUtil.isEmpty(deliveryOvertimeSpecialCnfId)) {
			DeliveryOvertimeSpecialCnf deliveryOvertimeSpecialCnf = deliveryOvertimeSpecialCnfService.selectByPrimaryKey(Integer.parseInt(deliveryOvertimeSpecialCnfId));
			m.addObject("deliveryOvertimeSpecialCnf", deliveryOvertimeSpecialCnf);
			DeliveryOvertimeSpecialCnfAreaExample deliveryOvertimeSpecialCnfAreaExample = new DeliveryOvertimeSpecialCnfAreaExample();
			deliveryOvertimeSpecialCnfAreaExample.createCriteria()
					.andDelFlagEqualTo("0")
					.andSpecialCnfIdEqualTo(deliveryOvertimeSpecialCnf.getId());
			List<DeliveryOvertimeSpecialCnfArea> deliveryOvertimeSpecialCnfAreaList = deliveryOvertimeSpecialCnfAreaService.selectByExample(deliveryOvertimeSpecialCnfAreaExample);
			if(deliveryOvertimeSpecialCnfAreaList != null && deliveryOvertimeSpecialCnfAreaList.size() > 0) {
				m.addObject("deliveryOvertimeSpecialCnfAreaList", JSONArray.fromObject(deliveryOvertimeSpecialCnfAreaList).toString());
			}
		}
		AreaExample areaExample = new AreaExample();
		areaExample.createCriteria().andAreaTypeEqualTo("1").andStatusEqualTo("A");
		List<Area> areaList = areaService.selectByExample(areaExample);
		if(areaList != null && areaList.size() > 0) {
			m.addObject("areaList", JSONArray.fromObject(areaList).toString());
		}
		return m;
	}

	@RequestMapping("/deliveryOvertimeSpecialCnf/editDeliveryOvertimeSpecialCnf.shtml")
	public ModelAndView editDeliveryOvertimeSpecialCnf(HttpServletRequest request, DeliveryOvertimeSpecialCnf deliveryOvertimeSpecialCnf) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			String staffId = this.getSessionStaffBean(request).getStaffID();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtils.isEmpty(request.getParameter("beginPayDate"))) {
				deliveryOvertimeSpecialCnf.setBeginPayDate(sdf.parse(request.getParameter("beginPayDate")));
			}
			if(!StringUtils.isEmpty(request.getParameter("endPayDate"))) {
				deliveryOvertimeSpecialCnf.setEndPayDate(sdf.parse(request.getParameter("endPayDate")));
			}
			if(!StringUtils.isEmpty(request.getParameter("deliveryDate"))) {
				deliveryOvertimeSpecialCnf.setDeliveryDate(sdf.parse(request.getParameter("deliveryDate")));
			}
			if("0".equals(deliveryOvertimeSpecialCnf.getTimeType())) {
				deliveryOvertimeSpecialCnf.setDeliveryDate(null);
			}else {
				deliveryOvertimeSpecialCnf.setOvertime(null);
			}
			if(deliveryOvertimeSpecialCnf.getId() != null) {
				deliveryOvertimeSpecialCnf.setUpdateBy(Integer.parseInt(staffId));
				deliveryOvertimeSpecialCnf.setUpdateDate(date);
				deliveryOvertimeSpecialCnfService.updateDeliveryOvertimeSpecialCnf(deliveryOvertimeSpecialCnf);
			}else {
				deliveryOvertimeSpecialCnf.setCreateBy(Integer.parseInt(staffId));
				deliveryOvertimeSpecialCnf.setCreateDate(date);
				deliveryOvertimeSpecialCnfService.insertDeliveryOvertimeSpecialCnf(deliveryOvertimeSpecialCnf);
			}
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

	@ResponseBody
	@RequestMapping("/deliveryOvertimeSpecialCnf/selectAreaList.shtml")
	public Map<String, Object> selectAreaList(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		List<Area> areaList = null;
		try {
			String parentId = request.getParameter("parentId");
			String areaType = request.getParameter("areaType");
			if(!StringUtil.isEmpty(parentId)) {
				AreaExample areaExample = new AreaExample();
				areaExample.createCriteria()
						.andParentIdEqualTo(Integer.parseInt(parentId))
						.andStatusEqualTo("A").andAreaTypeEqualTo(areaType);
				areaList = areaService.selectByExample(areaExample);
			}
		} catch (Exception e) {
			e.printStackTrace();
			code = "9999";
			msg = "系统异常请稍后再试！";
		}
		map.put("code", code);
		map.put("msg", msg);
		map.put("areaList", areaList);
		return map;
	}

	@ResponseBody
	@RequestMapping("/deliveryOvertimeSpecialCnf/validatePayDate.shtml")
	public Map<String, Object> validatePayDate(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String beginPayDate = request.getParameter("beginPayDate");
			String endPayDate = request.getParameter("endPayDate");
			String id = request.getParameter("id");
			DeliveryOvertimeSpecialCnfExample deliveryOvertimeSpecialCnfExampleA = new DeliveryOvertimeSpecialCnfExample();
			DeliveryOvertimeSpecialCnfExample.Criteria deliveryOvertimeSpecialCnfCriteriaA = deliveryOvertimeSpecialCnfExampleA.createCriteria();
			if(!StringUtils.isEmpty(id)) {
				deliveryOvertimeSpecialCnfCriteriaA.andIdNotEqualTo(Integer.parseInt(id));
			}
			deliveryOvertimeSpecialCnfCriteriaA.andDelFlagEqualTo("0")
					.andBeginPayDateLessThanOrEqualTo(sdf.parse(beginPayDate))
					.andEndPayDateGreaterThanOrEqualTo(sdf.parse(beginPayDate));
			int countA = deliveryOvertimeSpecialCnfService.countByExample(deliveryOvertimeSpecialCnfExampleA);
			DeliveryOvertimeSpecialCnfExample deliveryOvertimeSpecialCnfExampleB = new DeliveryOvertimeSpecialCnfExample();
			DeliveryOvertimeSpecialCnfExample.Criteria deliveryOvertimeSpecialCnfCriteriaB = deliveryOvertimeSpecialCnfExampleB.createCriteria();
			if(!StringUtils.isEmpty(id)) {
				deliveryOvertimeSpecialCnfCriteriaB.andIdNotEqualTo(Integer.parseInt(id));
			}
			deliveryOvertimeSpecialCnfCriteriaB.andDelFlagEqualTo("0")
					.andBeginPayDateLessThanOrEqualTo(sdf.parse(endPayDate))
					.andEndPayDateGreaterThanOrEqualTo(sdf.parse(endPayDate));
			int countB = deliveryOvertimeSpecialCnfService.countByExample(deliveryOvertimeSpecialCnfExampleB);
			if(countA != 0 || countB != 0) {
				code = "9999";
				msg = "对不起，支付时间与其它数据有交集，不能添加！";
			}
		} catch (Exception e) {
			e.printStackTrace();
			code = "9999";
			msg = "系统异常请稍后再试！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}



}
