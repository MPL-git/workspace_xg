package com.jf.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.jf.common.utils.QRCodeUtil;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberInfoCustom;
import com.jf.entity.MemberInfoCustomExample;
import com.jf.entity.MemberInfoCustomExample.MemberInfoCustomCriteria;
import com.jf.entity.Salesman;
import com.jf.entity.SalesmanCustom;
import com.jf.entity.SalesmanCustomExample;
import com.jf.entity.StateCode;
import com.jf.entity.SalesmanCustomExample.SalesmanCustomCriteria;
import com.jf.entity.SalesmanExample;
import com.jf.entity.SalesmanExample.Criteria;
import com.jf.entity.ShopownerCustom;
import com.jf.entity.ShopownerCustomExample;
import com.jf.entity.ShopownerCustomExample.ShopownerCustomCriteria;
import com.jf.service.MemberInfoService;
import com.jf.service.SalesmanService;
import com.jf.service.ShopownerService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class SalesmanManageController extends BaseController {

	@Resource
	private SalesmanService salesmanService;
	@Resource
	private ShopownerService shopownerService;
	@Resource
	private MemberInfoService memberInfoService;
	/**
	 * 
	 * @MethodName index
	 * @Description TODO(业务员管理页面)
	 * @author chengh
	 * @date 2019年7月9日 下午6:27:51
	 */
	@RequestMapping(value = "/salesmanManage/index.shtml")
	public ModelAndView index(HttpServletRequest request,Model model) {	
		return new ModelAndView("/novaStrategy/salesmanManageList");
	}

	/**
	 * 
	 * @MethodName getNewStartList(业务员管理页面列表数据)
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月12日 下午1:48:04
	 */
	@ResponseBody
	@RequestMapping(value = "/novaStrategy/salesmanManageList.shtml")
	public Map<String, Object> getNewStartList(HttpServletRequest request,
			Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		SalesmanCustomExample salesmanCustomExample = new SalesmanCustomExample();
		SalesmanCustomCriteria criteria = salesmanCustomExample.createCriteria();
		salesmanCustomExample.setLimitSize(page.getLimitSize());
		salesmanCustomExample.setLimitStart(page.getLimitStart());
		salesmanCustomExample.setOrderByClause("create_date desc");
		//业务员会员ID
		if (StringUtils.isNotBlank(request.getParameter("memberId"))) {
			criteria.andMemberIdEqualTo(Integer.valueOf(request.getParameter("memberId")));
		}
		//业务员会员昵称
		if (StringUtils.isNotBlank(request.getParameter("nick"))) {
			criteria.andNickLikeEqual("%"+request.getParameter("nick") + "%");
		}
		//状态
		if (StringUtils.isNotBlank(request.getParameter("status"))) {
			criteria.andStatusEqualTo(request.getParameter("status"));
		}
		//创建日期
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (StringUtils.isNotBlank(request.getParameter("createDateBegin"))) {		
				criteria.andCreateDateGreaterThanOrEqualTo(df.parse(request
						.getParameter("createDateBegin") + " 00:00:00"));			
			}
			if (StringUtils.isNotBlank(request.getParameter("createDateEnd"))) {
				criteria.andCreateDateLessThanOrEqualTo(df.parse(request
						.getParameter("createDateEnd") + " 23:59:59"));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		Integer totalCount = salesmanService.countByCustomExample(salesmanCustomExample);
		List<SalesmanCustom> list = salesmanService.selectByCustomExample(salesmanCustomExample);
		map.put("Rows", list);
		map.put("Total", totalCount);
		return map;
	}
	
	/**
	 * 
	 * @MethodName laNewRecordShopowner
	 * @Description TODO(业务员拉新记录页面)
	 * @author chengh
	 * @date 2019年7月12日 下午8:23:05
	 */
	@RequestMapping(value = "/salesmanManage/laNewRecordShopowner.shtml")
	public ModelAndView laNewRecordShopowner(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", request.getParameter("id"));
		return new ModelAndView("/novaStrategy/laNewRecordShopowner",map);
	}
	
	/**
	 * 
	 * @MethodName laNewRecordShopowner
	 * @Description TODO(拉新记录（业务员ID/业务员昵称）)
	 * @author chengh
	 * @date 2019年7月12日 下午5:06:19
	 */
	@ResponseBody
	@RequestMapping(value = "/salesmanManage/laNewRecordShopownerList.shtml")
	public Map<String, Object> laNewRecordShopownerList(HttpServletRequest request,Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		String memberId = request.getParameter("id");
		ShopownerCustomExample shopownerCustomExample = new ShopownerCustomExample();
		ShopownerCustomCriteria criteria = shopownerCustomExample.createCriteria();
		shopownerCustomExample.setLimitSize(page.getLimitSize());
		shopownerCustomExample.setLimitStart(page.getLimitStart());
		shopownerCustomExample.setOrderByClause("pay_date desc,create_date desc,member_id desc");
		criteria.andSalesmanIdEqualTo(Integer.valueOf(memberId)).andDelFlagEqualTo("0");
		//店长会员ID
		if (StringUtils.isNotBlank(request.getParameter("memberId"))) {
			criteria.andMemberIdEqualTo(Integer.valueOf(request.getParameter("memberId")));
		}
		//店长会员昵称
		if (StringUtils.isNotBlank(request.getParameter("nick"))) {
			criteria.andNickLikeEqual("%"+request.getParameter("nick") + "%");
		}
		//升级日期
		if (StringUtils.isNotBlank(request.getParameter("dealCompleteDateBegin"))) {		
			criteria.andDealCompleteDateGreaterThanOrEqualTo(request
					.getParameter("dealCompleteDateBegin") + " 00:00:00");			
		}
		if (StringUtils.isNotBlank(request.getParameter("dealCompleteDateEnd"))) {
			criteria.andDealCompleteDateLessThanOrEqualTo(request
					.getParameter("dealCompleteDateEnd") + " 23:59:59");
		}
		Integer totalCount = shopownerService.countByCustomExample(shopownerCustomExample);
		List<ShopownerCustom> list = shopownerService.selectByCustomExample(shopownerCustomExample);
		map.put("Rows", list);
		map.put("Total", totalCount);
		return map;
	}
	
	/**
	 * 
	 * @MethodName edit
	 * @Description TODO(新增、编辑业务员页面)
	 * @author chengh
	 * @date 2019年7月15日 上午8:40:11
	 */
	@RequestMapping(value = "/salesmanManage/edit.shtml")
	public ModelAndView edit(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			SalesmanCustomExample salesmanCustomExample = new SalesmanCustomExample();
			salesmanCustomExample.createCriteria().andIdEqualTo(Integer.valueOf(id)).andDelFlagEqualTo("0");
			List<SalesmanCustom> salesmanCustoms = salesmanService.selectByCustomExample(salesmanCustomExample);
			if(!salesmanCustoms.isEmpty()){
				map.put("salesMan", salesmanCustoms.get(0));
			}
		}
		return new ModelAndView("/novaStrategy/addBusinessManager",map);
	}
	
	/**
	 * 
	 * @MethodName submit(业务员添加、编辑操作)
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月15日 下午3:49:48
	 */
	@RequestMapping(value = "/salesmanManage/submit.shtml")
	public ModelAndView submit(HttpServletRequest request) {
		String rtPage = "/success/success";
 		String code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
		String msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		try {			
			Integer staffId = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			String id = request.getParameter("id");
			String memberId = request.getParameter("memberIdTag");
			String name = request.getParameter("name");
			String otherRemarks = request.getParameter("otherRemarks");
			Salesman salesman = new Salesman();
			salesman.setUpdateBy(staffId);
			salesman.setUpdateDate(new Date());
			if(StringUtils.isNotBlank(id)){
				salesman.setId(Integer.valueOf(id));
				salesman.setName(name);
				salesman.setOtherRemarks(otherRemarks);
				salesmanService.updateByPrimaryKeySelective(salesman);
			}else {
				salesman.setMemberId(Integer.valueOf(memberId));
				salesman.setName(name);
				salesman.setOtherRemarks(otherRemarks);
				salesman.setStatus("1");
				//专属二维码
				InputStream stream = SalesmanManageController.class.getResourceAsStream("/base_config.properties");
				Properties properties = new Properties();
				properties.load(stream);
				String mUrl = properties.getProperty("mUrl");
				stream.close();
				salesman.setInviteCodePic(mUrl+"/xgbuy/views/index.html?redirect_url=activity/shopowner/pages/salesman/index.html?salesmanId="+memberId);
				//链接邀请
				salesman.setInviteUrl(mUrl+"/xgbuy/views/index.html?redirect_url=activity/shopowner/pages/salesman/index.html?salesmanId="+memberId);
				salesman.setCreateBy(staffId);
				salesman.setCreateDate(new Date());
				salesman.setDelFlag("0");
				salesmanService.insertSelective(salesman);
			}
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_DB_INVOKE.getStateMessage();
		}
		map.put(this.JSON_RESULT_CODE,code);
		map.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage,map);
	}
	
	@RequestMapping(value = "/salesmanManage/getQrCode.shtml",method = RequestMethod.GET)
	@ResponseBody
	public void generateQRCode4Product(HttpServletRequest request,HttpServletResponse response) {
		try {
			//会员id
			String id = request.getParameter("id");
			//url地址
			Salesman salesman = salesmanService.selectByPrimaryKey(Integer.valueOf(id));
			String url = salesman.getInviteCodePic();
			// 生成二维码
	        BitMatrix qRcodeImg = QRCodeUtil.generateQRCodeStream(url, response);
	        // 将二维码输出到页面中
			MatrixToImageWriter.writeToStream(qRcodeImg, "png", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @MethodName add(禁用/启用)
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月15日 下午4:02:49
	 */
	@ResponseBody
	@RequestMapping(value = "/salesmanManage/prohibit.shtml")
	public Map<String, Object> prohibit(HttpServletRequest request) {
		String code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
		String msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer id = Integer.valueOf(request.getParameter("id"));
			String status = request.getParameter("status");
			Salesman salesman = new Salesman();
			salesman.setId(id);
			if(StringUtils.equals(status, "0")){
				salesman.setStatus("1");
			}else{
				salesman.setStatus("0");
			}	
			salesmanService.updateByPrimaryKeySelective(salesman);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_DB_INVOKE.getStateMessage();
		}
		map.put(this.JSON_RESULT_CODE,code);
		map.put(this.JSON_RESULT_MESSAGE, msg);
		return map;
	}

	/**
	 * 
	 * @MethodName binder
	 * @Description TODO(绑定会员页面)
	 * @author chengh
	 * @date 2019年7月15日 上午11:37:56
	 */
	@RequestMapping(value = "/salesmanManage/binder.shtml")
	public ModelAndView binder(HttpServletRequest request) {
		return new ModelAndView("/novaStrategy/binder");
	}
	
	/**
	 * 
	 * @MethodName binderList
	 * @Description TODO(绑定会员列表)
	 * @author chengh
	 * @date 2019年7月15日 下午4:22:19
	 */
	@ResponseBody
	@RequestMapping(value = "/salesmanManage/binderList.shtml")
	public Map<String, Object> binderList(HttpServletRequest request,
			Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isBlank(request.getParameter("memberId")) && StringUtils.isBlank(request.getParameter("nick")) && StringUtils.isBlank(request.getParameter("mobile"))){
			return map;
		}		
		MemberInfoCustomExample memberInfoCustomExample = new MemberInfoCustomExample();
		MemberInfoCustomCriteria criteria = memberInfoCustomExample.createCriteria();
		memberInfoCustomExample.setLimitSize(page.getLimitSize());
		memberInfoCustomExample.setLimitStart(page.getLimitStart());
		memberInfoCustomExample.setOrderByClause(" last_date desc");
		//会员ID
		if (StringUtils.isNotBlank(request.getParameter("memberId"))) {
			criteria.andIdEqualTo(Integer.valueOf(request.getParameter("memberId")));
		}
		//会员昵称
		if (StringUtils.isNotBlank(request.getParameter("nick"))) {
			criteria.andNickLikeEqual("%"+request.getParameter("nick") + "%");
		}
		//手机号
		if (StringUtils.isNotBlank(request.getParameter("mobile"))) {
			criteria.andMobileEqualTo(request.getParameter("mobile"));
		}
		Integer totalCount = memberInfoService.countByCustomExample(memberInfoCustomExample);
		List<MemberInfoCustom> list = memberInfoService.selectByCustomExample(memberInfoCustomExample);
		map.put("Rows", list);
		map.put("Total", totalCount);
		return map;
	}
	
	/**
	 * 
	 * @MethodName add
	 * @Description TODO(业务员筛选)
	 * @author chengh
	 * @date 2019年7月15日 下午3:49:11
	 */
	@ResponseBody
	@RequestMapping(value = "/salesmanManage/add.shtml")
	public Map<String, Object> add(HttpServletRequest request) {
		String code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
		String msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer memberId = Integer.valueOf(request.getParameter("memberId"));
			MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(memberId);
			map.put("memberInfo", memberInfo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_DB_INVOKE.getStateMessage();
		}
		map.put(this.JSON_RESULT_CODE,code);
		map.put(this.JSON_RESULT_MESSAGE, msg);
		return map;
	}
}
