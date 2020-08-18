package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.StringUtils;
import com.jf.entity.*;
import com.jf.entity.MchtInfoCustomExample.MchtInfoCustomCriteria;
import com.jf.service.*;
import com.jf.vo.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/mchtData")
public class MchtDataController extends BaseController {
	@Resource
	private MchtInfoService mchtInfoService;
	@Resource
	private ActivityProductService activityProductService;
	@Resource
	private ProductTypeService productTypeService;
	@Resource
	private PlatformContactService platformContactService;
	@Resource
	private MchtInfoChangeLogService mchtInfoChangeLogService;
	@Resource
	private MchtContractService mchtContractService;
	@Resource
	private MchtProductTypeService mchtProductTypeService;
	@Resource
	private SysStaffRoleService sysStaffRoleService;
	@Resource
	private ProductService productService;
	
	private static final long serialVersionUID = 1L;

	/**
	 * 每日商家数
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/businessesDailynumber.shtml")
	public ModelAndView list(HttpServletRequest request) {
		String rtPage = "/dataCenter/mchtData/businessesDailynumber";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateEnd = df.format(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		String dateBegin = df.format(calendar.getTime());
		resMap.put("date_end", dateEnd);
		resMap.put("date_begin", dateBegin);
		return new ModelAndView(rtPage, resMap);
	}

	/**
	 * 每日商家统计列表数据
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/businessesDailynumberdata.shtml")
	@ResponseBody
	public Map<String, Object> datanuber(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String DateBegin = request.getParameter("date_begin") + " 00:00:00";
			String DateEnd = request.getParameter("date_end") + " 23:59:59";
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			if (!StringUtil.isEmpty(request.getParameter("date_begin"))) {
				paramMap.put("DateBegin", DateBegin);
			} else {
				DateEnd = df.format(new Date());
				DateBegin = DateEnd.substring(0, 7) + "-01 00:00:00";
				paramMap.put("DateBegin", DateBegin);
			}
			if (!StringUtil.isEmpty(request.getParameter("date_end"))) {
				paramMap.put("DateEnd", DateEnd);
			} else {
				DateEnd = df.format(new Date());
				paramMap.put("DateEnd", DateEnd);
			}

			int totalmchtcount = 0;
			int mctotalmchtcount = 0;
			int committotalcount = 0;
			int quatotalcount = 0;
			int coopertotalcount = 0;

			MchtInfoCustom odc = new MchtInfoCustom();
			List<MchtInfoCustom> mchtInlCustomList = mchtInfoService
					.mchtInfoCustom(paramMap);
			for (MchtInfoCustom mchtInfoCustomlis : mchtInlCustomList) {
				if (mchtInlCustomList != null && mchtInlCustomList.size() > 0) {
					odc = mchtInlCustomList.get(0);
					odc.setEachDay("合计");
					totalmchtcount = totalmchtcount
							+ mchtInfoCustomlis.getSettledcount();
					mctotalmchtcount = mctotalmchtcount
							+ mchtInfoCustomlis.getMchtcount();
					committotalcount = committotalcount
							+ mchtInfoCustomlis.getCommitauditdatecount();
					quatotalcount = quatotalcount
							+ mchtInfoCustomlis.getQualificationthroughcount();
					coopertotalcount = coopertotalcount
							+ mchtInfoCustomlis.getCooperatebegindatecount();
					odc.setSettledcount(totalmchtcount);
					odc.setMchtcount(mctotalmchtcount);
					odc.setCommitauditdatecount(committotalcount);
					odc.setQualificationthroughcount(quatotalcount);
					odc.setCooperatebegindatecount(coopertotalcount);

				}
			}

			List<MchtInfoCustom> mchtInfoCustom = new ArrayList<MchtInfoCustom>();
			mchtInfoCustom = mchtInfoService.mchtInfoCustom(paramMap);
			HashMap<String, MchtInfoCustom> map = new HashMap<String, MchtInfoCustom>();
			List<String> containDays = new ArrayList<String>();
			for (MchtInfoCustom mchtInfoCustoms : mchtInfoCustom) {
				containDays.add(mchtInfoCustoms.getEachDay());
				map.put(mchtInfoCustoms.getEachDay(), mchtInfoCustoms);
			}

			List<String> amountDays = this.getBetweenDays(DateBegin, DateEnd);
			for (int i = 0; i < amountDays.size(); i++) {
				if (!containDays.contains(amountDays.get(i))) {
					MchtInfoCustom mchtInfoCustomm = new MchtInfoCustom();
					mchtInfoCustomm.setEachDay(amountDays.get(i));
					mchtInfoCustom.add(mchtInfoCustomm);
					map.put(amountDays.get(i), mchtInfoCustomm);
				}
			}
			Collections.sort(mchtInfoCustom, new Comparator<MchtInfoCustom>() {
				@Override
				public int compare(MchtInfoCustom c1, MchtInfoCustom c2) {

					return c1.getEachDay().compareTo(c2.getEachDay()); // 按时间升序
				}
			});
			mchtInfoCustom.add(0, odc);
			resMap.put("Rows", mchtInfoCustom);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;

	}

	/**
	 * 每日商家漏斗统计
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/BusinessStatistics.shtml")
	public ModelAndView lists(HttpServletRequest request) {
		String rtPage = "/dataCenter/mchtData/BusinessStatistics";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateEnd = df.format(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		String dateBegin = df.format(calendar.getTime());
		resMap.put("date_end", dateEnd);
		resMap.put("date_begin", dateBegin);
		return new ModelAndView(rtPage, resMap);
	}

	/**
	 * 每日商家漏斗统计列表数据
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/BusinessStatisticsdata.shtml")
	@ResponseBody
	public Map<String, Object> datanubers(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String DateBegin = request.getParameter("date_begin") + " 00:00:00";
			String DateEnd = request.getParameter("date_end") + " 23:59:59";
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			if (!StringUtil.isEmpty(request.getParameter("date_begin"))) {
				paramMap.put("DateBegin", DateBegin);
			} else {
				DateEnd = df.format(new Date());
				DateBegin = DateEnd.substring(0, 7) + "-01 00:00:00";
				paramMap.put("DateBegin", DateBegin);
			}
			if (!StringUtil.isEmpty(request.getParameter("date_end"))) {
				paramMap.put("DateEnd", DateEnd);
			} else {
				DateEnd = df.format(new Date());
				paramMap.put("DateEnd", DateEnd);
			}

			int mctotalmchtcount = 0;
			int settledunsubmittedtcount = 0;
			int settledauditedtcount = 0;
			int settledadopttotcount = 0;
			int shopopentcount = 0;
			int contractfiletcount = 0;

			MchtInfoCustom odc = new MchtInfoCustom();
			List<MchtInfoCustom> totalmchtInlCustomList = mchtInfoService
					.totalmchtInfoCustom(paramMap);
			for (MchtInfoCustom totalmchtInlCustomListt : totalmchtInlCustomList) {
				if (totalmchtInlCustomList != null
						&& totalmchtInlCustomList.size() > 0) {
					odc = totalmchtInlCustomList.get(0);
					odc.setEachDay("合计");
					mctotalmchtcount = mctotalmchtcount
							+ totalmchtInlCustomListt.getMchtcount();
					settledunsubmittedtcount = settledunsubmittedtcount
							+ totalmchtInlCustomListt.getSettledunsubmitted();
					settledauditedtcount = settledauditedtcount
							+ totalmchtInlCustomListt.getSettledaudited();
					settledadopttotcount = settledadopttotcount
							+ totalmchtInlCustomListt.getSettledadopt();
					shopopentcount = shopopentcount
							+ totalmchtInlCustomListt.getShopopen();
					contractfiletcount = contractfiletcount
							+ totalmchtInlCustomListt.getContractfile();
					odc.setMchtcount(mctotalmchtcount);
					odc.setSettledunsubmitted(settledunsubmittedtcount);
					odc.setSettledaudited(settledauditedtcount);
					odc.setSettledadopt(settledadopttotcount);
					odc.setShopopen(shopopentcount);
					odc.setContractfile(contractfiletcount);

				}
			}

			List<MchtInfoCustom> totalmchtInfoCustom = new ArrayList<MchtInfoCustom>();
			totalmchtInfoCustom = mchtInfoService.totalmchtInfoCustom(paramMap);
			HashMap<String, MchtInfoCustom> map = new HashMap<String, MchtInfoCustom>();
			List<String> containDays = new ArrayList<String>();
			for (MchtInfoCustom totalmchtInfoCustoms : totalmchtInfoCustom) {
				containDays.add(totalmchtInfoCustoms.getEachDay());
				map.put(totalmchtInfoCustoms.getEachDay(), totalmchtInfoCustoms);
			}

			List<String> amountDays = this.getBetweenDays(DateBegin, DateEnd);
			for (int i = 0; i < amountDays.size(); i++) {
				if (!containDays.contains(amountDays.get(i))) {
					MchtInfoCustom totalmchtInfoCustomm = new MchtInfoCustom();
					totalmchtInfoCustomm.setEachDay(amountDays.get(i));
					totalmchtInfoCustom.add(totalmchtInfoCustomm);
					map.put(amountDays.get(i), totalmchtInfoCustomm);
				}
			}
			Collections.sort(totalmchtInfoCustom,
					new Comparator<MchtInfoCustom>() {
						@Override
						public int compare(MchtInfoCustom c1, MchtInfoCustom c2) {

							return c1.getEachDay().compareTo(c2.getEachDay()); // 按时间升序
						}
					});
			totalmchtInfoCustom.add(0, odc);
			resMap.put("Rows", totalmchtInfoCustom);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;

	}

	/**
	 * 每日商品数
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/commodityData.shtml")
	public ModelAndView shoplist(HttpServletRequest request) {
		String rtPage = "/dataCenter/mchtData/commodityData";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateEnd = df.format(new Date());
		String dateBegin = dateEnd.substring(0, 7) + "-01";
		resMap.put("date_end", dateEnd);
		resMap.put("date_begin", dateBegin);
		return new ModelAndView(rtPage, resMap);

	}

	/**
	 * 每日商品数据列表
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/commodityDatadata.shtml")
	@ResponseBody
	public Map<String, Object> datashop(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();

		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String DateBegin = request.getParameter("date_begin") + " 00:00:00";
			String DateEnd = request.getParameter("date_end") + " 23:59:59";
			String MchtCode = request.getParameter("mchtCode");
			String ShopName = request.getParameter("shopName");
			HashMap<String, Object> paramMap = new HashMap<String, Object>();

			if (!StringUtil.isEmpty(request.getParameter("date_begin"))) {
				paramMap.put("DateBegin", DateBegin);
			} else {
				DateEnd = df.format(new Date());
				DateBegin = DateEnd.substring(0, 7) + "-01 00:00:00";
				paramMap.put("DateBegin", DateBegin);
			}
			if (!StringUtil.isEmpty(request.getParameter("date_end"))) {
				paramMap.put("DateEnd", DateEnd);
			} else {
				DateEnd = df.format(new Date());
				paramMap.put("DateEnd", DateEnd);
			}

			if (!StringUtil.isEmpty(MchtCode)) {
				paramMap.put("mchtCode", MchtCode);
			}
			if (!StringUtil.isEmpty(ShopName)) {
				paramMap.put("shopName", ShopName);
			}

			// ActivityProductCustomExample activityProductCustomExample = new
			// ActivityProductCustomExample();
			// totalCount =
			// activityProductService.countByCustomExample(activityProductCustomExample);
			// activityProductCustomExample.setLimitStart(page.getLimitStart());
			// activityProductCustomExample.setLimitSize(page.getLimitSize());
			// paramMap.put("activityProductCustomExamplest",activityProductCustomExample.getLimitStart())
			// ;
			// paramMap.put("activityProductCustomExamplesi",
			// activityProductCustomExample.getLimitSize());

			// paramMap.put("limitStart",page.getLimitStart());
			// paramMap.put("limitSize", page.getLimitSize());

			List<ActivityProductCustom> shopInfoCustom = activityProductService
					.totalshopCustom(paramMap);
			HashMap<String, ActivityProductCustom> map = new HashMap<String, ActivityProductCustom>();
			List<String> containDays = new ArrayList<String>();
			for (ActivityProductCustom shopInfoCustoms : shopInfoCustom) {
				containDays.add(shopInfoCustoms.getEachDay());
				map.put(shopInfoCustoms.getEachDay(), shopInfoCustoms);
			}

			List<String> amountDays = this.getBetweenDays(DateBegin, DateEnd);
			for (int i = 0; i < amountDays.size(); i++) {
				if (!containDays.contains(amountDays.get(i))) {
					ActivityProductCustom shopmchtInfoCustom = new ActivityProductCustom();
					shopmchtInfoCustom.setEachDay(amountDays.get(i));
					shopInfoCustom.add(shopmchtInfoCustom);
					map.put(amountDays.get(i), shopmchtInfoCustom);

				}
			}
			Collections.sort(shopInfoCustom,
					new Comparator<ActivityProductCustom>() {
						@Override
						public int compare(ActivityProductCustom c1,
								ActivityProductCustom c2) {

							return c1.getEachDay().compareTo(c2.getEachDay()); // 按时间升序
						}
					});

			resMap.put("Rows", shopInfoCustom);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;

	}

	/**
	 * 上架商品统计页面
	 *
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/commodityStatistics.shtml")
	public String commodityStatistics(HttpServletRequest request, Model model) {
		ProductTypeExample productTypeExample = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		productTypeCriteria.andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
		productTypeExample.setOrderByClause(" seq_no");
		List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		model.addAttribute("productTypeList", productTypeList); //1级类目
		return "/dataCenter/mchtData/commodityStatistics";
	}


	/**
	 * 上架商品统计数据
	 *
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/commodityStatisticsList.shtml")
	@ResponseBody
	public Map<String, Object> commodityStatisticsList(Model model, HttpServletRequest request, Page page){
		Map<String,Object> resMap = new HashMap<String,Object>();
		int totalCount = 0;
		List<Map<String, Object>> resList = new ArrayList<Map<String,Object>>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		try {
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))){
				paramMap.put("mchtCode", request.getParameter("mchtCode"));
			}

			if(!StringUtil.isEmpty(request.getParameter("shopName"))){
				paramMap.put("shopName", request.getParameter("shopName"));
			}

			if(!StringUtil.isEmpty(request.getParameter("beginProductCount"))){
				paramMap.put("beginProductCount", request.getParameter("beginProductCount"));
			}

			if(!StringUtil.isEmpty(request.getParameter("endProductCount"))){
				paramMap.put("endProductCount", request.getParameter("endProductCount"));
			}

			if(!StringUtil.isEmpty(request.getParameter("type1Id"))){
				paramMap.put("type1Id", request.getParameter("type1Id"));
			}

			if(!StringUtil.isEmpty(request.getParameter("type2Id"))){
				paramMap.put("type2Id", request.getParameter("type2Id"));
			}

			totalCount = productService.countCommodityStatisticsList(paramMap);
			paramMap.put("limitStart", page.getLimitStart());
			paramMap.put("limitSize", page.getLimitSize());
			resList = productService.selectCommodityStatisticsList(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", resList);
		resMap.put("Total", totalCount);
		return resMap;
	}


	/**
	 * 商品销量统计页面
	 *
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/commoditySaleStatistics.shtml")
	public String commoditySaleStatistics(HttpServletRequest request, Model model) {
		ProductTypeExample productTypeExample = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		productTypeCriteria.andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
		productTypeExample.setOrderByClause(" seq_no");
		List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		model.addAttribute("productTypeList", productTypeList); //1级类目
		return "/dataCenter/mchtData/commoditySaleStatistics";
	}


	/**
	 * 商家销量统计数据
	 *
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/commoditySaleStatisticsList.shtml")
	@ResponseBody
	public Map<String, Object> commoditySaleStatisticsList(Model model, HttpServletRequest request, Page page){
		Map<String,Object> resMap = new HashMap<String,Object>();
		int totalCount = 0;
		List<Map<String, Object>> resList = new ArrayList<Map<String,Object>>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		List<Object> mchtIds = new ArrayList<>();
		try {

			if(!StringUtil.isEmpty(request.getParameter("shopName"))){
				paramMap.put("shopName", request.getParameter("shopName"));
			}

			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))){
				paramMap.put("mchtCode", request.getParameter("mchtCode"));
			}

			if(!StringUtil.isEmpty(request.getParameter("type1Id"))){
				paramMap.put("type1Id", request.getParameter("type1Id"));
			}

			if(!StringUtil.isEmpty(request.getParameter("type2Id"))){
				paramMap.put("type2Id", request.getParameter("type2Id"));
			}

			if(!StringUtil.isEmpty(request.getParameter("beginProductCount"))){
				paramMap.put("beginProductCount", request.getParameter("beginProductCount"));
			}

			if(!StringUtil.isEmpty(request.getParameter("endProductCount"))){
				paramMap.put("endProductCount", request.getParameter("endProductCount"));
			}

			List<Map<String, Object>> ids = productService.selectCommoditySaleStatisticsIds(paramMap);
			if (ids.size() >0 && ids !=null) {
				for (Map<String, Object> id : ids) {
					mchtIds.add(id.get("id"));
				}
				if (mchtIds.size() <= (page.getLimitStart() + page.getLimitSize())) {
					mchtIds = mchtIds.subList(page.getLimitStart(), mchtIds.size());
				} else {
					mchtIds = mchtIds.subList(page.getLimitStart(), page.getLimitStart() + page.getLimitSize());
				}

				paramMap.put("mchtIds", mchtIds);
				//不为空的话直接作为总条数返回;
				totalCount=ids.size();
				resList = productService.selectCommoditySaleStatisticsList(paramMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Total", totalCount);
		resMap.put("Rows", resList);
		return resMap;
	}


	
	public List<String> getBetweenDays(String stime, String etime) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date sdate = null;
		Date eDate = null;
		try {
			sdate = df.parse(stime);
			eDate = df.parse(etime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		List<String> list = new ArrayList<String>();
		while (sdate.getTime() <= eDate.getTime()) {
			list.add(df.format(sdate));
			c.setTime(sdate);
			c.add(Calendar.DATE, 1); // 日期加1天
			sdate = c.getTime();
		}

		return list;
	}
	
	
	//最新商家数量
	@RequestMapping(value = "/newestmchtInfo.shtml")
	public ModelAndView newestmchtInfolist(HttpServletRequest request) {
		String rtPage = "/dataCenter/mchtData/newestmchtInfo";
		Map<String, Object> resMap = new HashMap<String, Object>();
		return new ModelAndView(rtPage, resMap);
	}
	
	//最新商家数量数据列表	
	@RequestMapping(value = "/newestmchtInfodata.shtml")
	@ResponseBody
	public Map<String, Object> newestmchtInfodata(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			
			MchtInfoCustomExample mchtInfoCustomExample = new MchtInfoCustomExample();
			MchtInfoCustomCriteria createCriteria = mchtInfoCustomExample.createCriteria();
			createCriteria.andDelFlagEqualTo("0");  
             		
			int generalqualification = 0;
			int opentoopen = 0;
			int alreadyopened = 0;
			int contractfiling = 0;
			int sendback = 0;
			int shop = 0;
			int unsubmitted = 0;
			int submission = 0;
			int cancellation = 0;

			MchtInfoCustom odc = new MchtInfoCustom();
			List<MchtInfoCustom> totalmchtInlCustomList = mchtInfoService
					.totalmchtinfoCoum(mchtInfoCustomExample);
			for (MchtInfoCustom totalmchtInlCustomListt : totalmchtInlCustomList) {
				if (totalmchtInlCustomList != null
						&& totalmchtInlCustomList.size() > 0) {
					odc = totalmchtInlCustomList.get(0);
					odc.setProductname("全部");
					generalqualification = generalqualification
							+ totalmchtInlCustomListt.getGeneralqualification();
					opentoopen = opentoopen
							+ totalmchtInlCustomListt.getOpentoopen();
					alreadyopened = alreadyopened
							+ totalmchtInlCustomListt.getAlreadyopened();
					contractfiling = contractfiling
							+ totalmchtInlCustomListt.getContractfiling();
					sendback = sendback
							+ totalmchtInlCustomListt.getSendback();
					shop = shop
							+ totalmchtInlCustomListt.getShop();
					unsubmitted = unsubmitted
							+ totalmchtInlCustomListt.getUnsubmitted();
					submission = submission
							+ totalmchtInlCustomListt.getSubmission();
					cancellation = cancellation
							+ totalmchtInlCustomListt.getCancellation();
					odc.setGeneralqualification(generalqualification);
					odc.setOpentoopen(opentoopen);
					odc.setAlreadyopened(alreadyopened);
					odc.setContractfiling(contractfiling);
					odc.setSendback(sendback);
					odc.setShop(shop);
					odc.setUnsubmitted(unsubmitted);
					odc.setSubmission(submission);
					odc.setCancellation(cancellation);

				}
			}

			List<MchtInfoCustom> totalmchtInfoCustom = new ArrayList<MchtInfoCustom>();
			totalmchtInfoCustom = mchtInfoService.totalmchtinfoCoum(mchtInfoCustomExample);
			HashMap<String, MchtInfoCustom> map = new HashMap<String, MchtInfoCustom>();
			List<String> productnames = new ArrayList<String>();
			for (MchtInfoCustom totalmchtInfoCustoms : totalmchtInfoCustom) {
				productnames.add(totalmchtInfoCustoms.getProductname());
				map.put(totalmchtInfoCustoms.getProductname(), totalmchtInfoCustoms);
			}

			Collections.sort(totalmchtInfoCustom,
					new Comparator<MchtInfoCustom>() {
						@Override
						public int compare(MchtInfoCustom c1, MchtInfoCustom c2) {

							return c1.getProductname().compareTo(c2.getProductname());
						}
					});
			totalmchtInfoCustom.add(0, odc);
			resMap.put("Rows", totalmchtInfoCustom);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;

	}
	
	/**
	 * 
	 * @Title mchtInfoAudit   
	 * @Description TODO(招商入驻进度)   
	 * @author pengl
	 * @date 2018年5月9日 下午6:25:48
	 */
	@RequestMapping("/mchtInfoAudit.shtml")
	public ModelAndView mchtInfoAudit(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/mchtData/mchtInfoAuditList");
		m.addObject("mchtStatusList", DataDicUtil.getTableStatus("BU_MCHT_INFO", "STATUS")); //商家合作状态
		ProductTypeExample productTypeExample = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		productTypeCriteria.andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
		//钟表运营部状态，只获取主营类目为钟表 
		String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
		if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
			String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
			if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
				productTypeCriteria.andIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
			}
		}
		m.addObject("isCwOrgStatus", isCwOrgStatus);
		productTypeExample.setOrderByClause(" seq_no");
		List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		m.addObject("productTypeList", productTypeList); //1级类目（主营类目）
		m.addObject("mchtTotalAuditStatusList", DataDicUtil.getTableStatus("BU_MCHT_INFO", "TOTAL_AUDIT_STATUS")); //商家总资质审核状态
		m.addObject("mchtContractAuditStatusList", DataDicUtil.getTableStatus("BU_MCHT_CONTRACT", "AUDIT_STATUS")); //线上合同状态
		m.addObject("mchtContractArchiveStatusList", DataDicUtil.getTableStatus("BU_MCHT_CONTRACT", "ARCHIVE_STATUS")); //合同归档状态
		m.addObject("mchtDepositTypeList", DataDicUtil.getTableStatus("BU_MCHT_INFO", "DEPOSIT_TYPE")); //保证金类型
		//对接人
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		Integer isContact = 0; //默认不是对接人
		PlatformContactExample platformContactExample = new PlatformContactExample(); //当用户是对接人时，获取所对接的商家列表
		platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffID);
		List<PlatformContact> platformContactList = platformContactService.selectByExample(platformContactExample);
		if (platformContactList != null && platformContactList.size() > 0) {
			isContact = 1;
			Integer myContactId = platformContactList.get(0).getId(); //目前系统有限制，一个工号只能关联一个对接人
			PlatformContactExample platformContactExampleSecond = new PlatformContactExample(); //当用户是对接人时，获取所协助的商家列表
			platformContactExampleSecond.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(myContactId);
			List<PlatformContact> platformAssistantContactList = platformContactService.selectByExample(platformContactExampleSecond);
			m.addObject("myContactId", myContactId);
			m.addObject("platformAssistantContactList", platformAssistantContactList);
		}
		PlatformContactExample platformContactExamplThirdly = new PlatformContactExample(); //当用户不是不对接人时，获取商家招商对接人列表
		platformContactExamplThirdly.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("1");
		List<PlatformContact> platformMchtContactList = platformContactService.selectByExample(platformContactExamplThirdly);				
		m.addObject("platformMchtContactList", platformMchtContactList);
		m.addObject("isContact", isContact);
		SysStaffRoleExample e = new SysStaffRoleExample();
		e.createCriteria().andStatusEqualTo("A").andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID())).andRoleIdEqualTo(87);
		List<SysStaffRole> sysStaffRoles = sysStaffRoleService.selectByExample(e);
		if(sysStaffRoles!=null && sysStaffRoles.size()>0){
			m.addObject("role87", sysStaffRoles.get(0).getRoleId());
		}
		return m;
	}
	
	/**
	 * 
	 * @Title mchtInfoAuditList   
	 * @Description TODO(招商入驻进度)   
	 * @author pengl
	 * @date 2018年5月10日 下午6:32:27
	 */
	@ResponseBody
	@RequestMapping("/mchtInfoAuditList.shtml")
	public Map<String, Object> mchtInfoAuditList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = null;
		Integer totalCount = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			MchtInfoCustomExample mchtInfoCustomExample = new MchtInfoCustomExample();
			MchtInfoCustomExample.MchtInfoCustomCriteria mchtInfoCustomCriteria = mchtInfoCustomExample.createCriteria();
			mchtInfoCustomCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				mchtInfoCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("companyName"))) {
				mchtInfoCustomCriteria.andCompanyNameLike("%"+request.getParameter("companyName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("shopName"))) {
				mchtInfoCustomCriteria.andShopNameLike("%"+request.getParameter("shopName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("productBrandName"))) {
				mchtInfoCustomCriteria.andProductBrandNameLike("%"+request.getParameter("productBrandName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtSettledContactName"))) {
				mchtInfoCustomCriteria.andMchtSettledContactNameLike("%"+request.getParameter("mchtSettledContactName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtStatus"))) {
				mchtInfoCustomCriteria.andStatusEqualTo(request.getParameter("mchtStatus"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
				mchtInfoCustomCriteria.andMchtProductTypeIdEqualTo(request.getParameter("productTypeId"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtTotalAuditStatus"))) {
				if("999".equals(request.getParameter("mchtTotalAuditStatus"))) {
					mchtInfoCustomCriteria.andTotalAuditStatusNotEqualTo("4"); //不等于未提交
				}else {
					mchtInfoCustomCriteria.andTotalAuditStatusEqualTo(request.getParameter("mchtTotalAuditStatus"));
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtContractAuditStatus"))) {
				mchtInfoCustomCriteria.andMchtContractAuditStatusEqualTo(request.getParameter("mchtContractAuditStatus"));
			}
			if(!StringUtil.isEmpty(request.getParameter("financeConfirmStatus"))) {
				mchtInfoCustomCriteria.andFinanceConfirmStatusEqualTo(request.getParameter("financeConfirmStatus"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtContractIsMchtSend"))) {
				mchtInfoCustomCriteria.andMchtContractIsMchtSendEqualTo(request.getParameter("mchtContractIsMchtSend"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtContractIsPlatformSend"))) {
				mchtInfoCustomCriteria.andMchtContractIsPlatformSendEqualTo(request.getParameter("mchtContractIsPlatformSend"));
			}
			if(!StringUtil.isEmpty(request.getParameter("beginCreateDate"))) {
				mchtInfoCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("beginCreateDate")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("endCreateDate"))) {
				mchtInfoCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("endCreateDate")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("beginCooperateBeginDate"))) {
				mchtInfoCustomCriteria.andCooperateBeginDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("beginCooperateBeginDate")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("endCooperateBeginDate"))) {
				mchtInfoCustomCriteria.andCooperateBeginDateLessThanOrEqualTo(sdf.parse(request.getParameter("endCooperateBeginDate")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("beginAgreementEndDate"))) {
				mchtInfoCustomCriteria.andAgreementEndDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("beginAgreementEndDate")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("endAgreementEndDate"))) {
				mchtInfoCustomCriteria.andAgreementEndDateLessThanOrEqualTo(sdf.parse(request.getParameter("endAgreementEndDate")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtContractArchiveStatus"))) {
				mchtInfoCustomCriteria.andMchtContractArchiveStatusEqualTo(request.getParameter("mchtContractArchiveStatus"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtDepositType"))) {
				mchtInfoCustomCriteria.andDepositTypeEqualTo(request.getParameter("mchtDepositType"));
			}
			if(!StringUtil.isEmpty(request.getParameter("platformContactId"))) {
				mchtInfoCustomCriteria.andPlatformContactIdEqualTo(request.getParameter("platformContactId"));
			}
			mchtInfoCustomExample.setOrderByClause(" t.id desc");
			mchtInfoCustomExample.setLimitStart(page.getLimitStart());
			mchtInfoCustomExample.setLimitSize(page.getLimitSize());
			totalCount = mchtInfoService.countMchtInfoAudit(mchtInfoCustomExample);
			dataList = mchtInfoService.selectMchtInfoAudit(mchtInfoCustomExample);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title mchtInfoAuditExport   
	 * @Description TODO(招商入驻进度-->导出)   
	 * @author pengl
	 * @date 2018年5月11日 上午10:12:38
	 */
	@RequestMapping("/mchtInfoAuditExport.shtml")
	public void mchtInfoAuditExport(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			MchtInfoCustomExample mchtInfoCustomExample = new MchtInfoCustomExample();
			MchtInfoCustomExample.MchtInfoCustomCriteria mchtInfoCustomCriteria = mchtInfoCustomExample.createCriteria();
			mchtInfoCustomCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				mchtInfoCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("companyName"))) {
				mchtInfoCustomCriteria.andCompanyNameLike("%"+request.getParameter("companyName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("shopName"))) {
				mchtInfoCustomCriteria.andShopNameLike("%"+request.getParameter("shopName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("productBrandName"))) {
				mchtInfoCustomCriteria.andProductBrandNameLike("%"+request.getParameter("productBrandName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtSettledContactName"))) {
				mchtInfoCustomCriteria.andMchtSettledContactNameLike("%"+request.getParameter("mchtSettledContactName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtStatus"))) {
				mchtInfoCustomCriteria.andStatusEqualTo(request.getParameter("mchtStatus"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
				mchtInfoCustomCriteria.andMchtProductTypeIdEqualTo(request.getParameter("productTypeId"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtTotalAuditStatus"))) {
				if("999".equals(request.getParameter("mchtTotalAuditStatus"))) {
					mchtInfoCustomCriteria.andTotalAuditStatusNotEqualTo("4"); //不等于未提交
				}else {
					mchtInfoCustomCriteria.andTotalAuditStatusEqualTo(request.getParameter("mchtTotalAuditStatus"));
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtContractAuditStatus"))) {
				mchtInfoCustomCriteria.andMchtContractAuditStatusEqualTo(request.getParameter("mchtContractAuditStatus"));
			}
			if(!StringUtil.isEmpty(request.getParameter("financeConfirmStatus"))) {
				mchtInfoCustomCriteria.andFinanceConfirmStatusEqualTo(request.getParameter("financeConfirmStatus"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtContractIsMchtSend"))) {
				mchtInfoCustomCriteria.andMchtContractIsMchtSendEqualTo(request.getParameter("mchtContractIsMchtSend"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtContractIsPlatformSend"))) {
				mchtInfoCustomCriteria.andMchtContractIsPlatformSendEqualTo(request.getParameter("mchtContractIsPlatformSend"));
			}
			if(!StringUtil.isEmpty(request.getParameter("beginCreateDate"))) {
				mchtInfoCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("beginCreateDate")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("endCreateDate"))) {
				mchtInfoCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("endCreateDate")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("beginCooperateBeginDate"))) {
				mchtInfoCustomCriteria.andCooperateBeginDateGreaterThan(sdf.parse(request.getParameter("beginCooperateBeginDate")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("endCooperateBeginDate"))) {
				mchtInfoCustomCriteria.andCooperateBeginDateLessThanOrEqualTo(sdf.parse(request.getParameter("endCooperateBeginDate")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtContractArchiveStatus"))) {
				mchtInfoCustomCriteria.andMchtContractArchiveStatusEqualTo(request.getParameter("mchtContractArchiveStatus"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtDepositType"))) {
				mchtInfoCustomCriteria.andDepositTypeEqualTo(request.getParameter("mchtDepositType"));
			}
			if(!StringUtil.isEmpty(request.getParameter("platformContactId"))) {
				mchtInfoCustomCriteria.andPlatformContactIdEqualTo(request.getParameter("platformContactId"));
			}
			mchtInfoCustomExample.setOrderByClause(" t.id desc");
			List<Map<String, Object>> dataList = mchtInfoService.selectMchtInfoAudit(mchtInfoCustomExample);
			
			String[] titles = { "创建日期", "招商对接人", "商家序号", "公司名称", "合作状态", "店铺名称", "主营类目", "品牌", "联系人", "联系电话", 
					"商家是否提交", "招商是否确认", "法务总审状态", "驳回原因", "线上合同", "财务确认", "应缴保证金", "已缴保证金", "保证金类型", "开通日期",
					"最新合同到期日期","商家合同寄出", "合同归档", "平台合同寄出", "合同备注驳回原因", "商品数", "近月销售额", "近月特卖数"};
			ExcelBean excelBean = new ExcelBean("导出招商入驻进度列表.xls", "导出招商入驻进度列表", titles);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			List<String[]> datas = new ArrayList<String[]>();
			
			for(Map<String, Object> map : dataList){
				String zs_audit_status = "";
				if(map.get("zs_audit_status") != null && "1".equals(map.get("zs_audit_status").toString())) {
					zs_audit_status = "是";
				}else if(map.get("zs_audit_status") != null && "0".equals(map.get("zs_audit_status").toString())) {
					zs_audit_status = "否";
				}
				String finance_confirm_status = "";
				if(map.get("finance_confirm_status") != null && "1".equals(map.get("finance_confirm_status").toString())) {
					finance_confirm_status = "已确认";
				}else if(map.get("finance_confirm_status") != null && "0".equals(map.get("finance_confirm_status").toString())) {
					finance_confirm_status = "未确认";
				}
				String mcht_contract_is_mcht_send = "";
				if(map.get("mcht_contract_is_mcht_send") != null && "1".equals(map.get("mcht_contract_is_mcht_send").toString())) {
					mcht_contract_is_mcht_send = "是";
				}else if(map.get("mcht_contract_is_mcht_send") != null && "0".equals(map.get("mcht_contract_is_mcht_send").toString())) {
					mcht_contract_is_mcht_send = "否";
				}
				String mcht_contract_is_platform_send = "";
				if(map.get("mcht_contract_is_platform_send") != null && "1".equals(map.get("mcht_contract_is_platform_send").toString())) {
					mcht_contract_is_platform_send = "是";
				}else if(map.get("mcht_contract_is_platform_send") != null && "0".equals(map.get("mcht_contract_is_platform_send").toString())) {
					mcht_contract_is_platform_send = "否";
				}
				String[] data = {
						map.get("create_date")==null?"":simpleDateFormat.format(map.get("create_date")),
						map.get("zs_contact_name")==null?"":map.get("zs_contact_name").toString(),
						map.get("mcht_code")==null?"":map.get("mcht_code").toString(),		
						map.get("company_name")==null?"":map.get("company_name").toString(),		
						map.get("mcht_status_desc")==null?"":map.get("mcht_status_desc").toString(),			
						map.get("shop_name")==null?"":map.get("shop_name").toString(),		
						map.get("mcht_product_type_name")==null?"":map.get("mcht_product_type_name").toString(),		
						map.get("product_brand_name_audit_status_rate")==null?"":map.get("product_brand_name_audit_status_rate").toString(),
						map.get("mcht_settled_contact_name")==null?"":map.get("mcht_settled_contact_name").toString(),
						map.get("mcht_settled_phone")==null?"":map.get("mcht_settled_phone").toString(),
						map.get("total_audit_status")==null?"":("4".equals(map.get("total_audit_status").toString())?"否":"是"),
						zs_audit_status,
						map.get("mcht_total_audit_status_desc")==null?"":map.get("mcht_total_audit_status_desc").toString(),
						map.get("total_audit_remarks")==null?"":map.get("total_audit_remarks").toString(),
						map.get("mcht_contract_audit_status_desc")==null?"":map.get("mcht_contract_audit_status_desc").toString(),
						finance_confirm_status,
						map.get("mcht_deposit_total_amt")==null?"":map.get("mcht_deposit_total_amt").toString(),
						map.get("mcht_deposit_pay_amt")==null?"":map.get("mcht_deposit_pay_amt").toString(),
						map.get("mcht_deposit_type_desc")==null?"":map.get("mcht_deposit_type_desc").toString(),
						map.get("cooperate_begin_date")==null?"":simpleDateFormat.format(map.get("cooperate_begin_date")),
						map.get("agreement_end_date")==null?"":simpleDateFormat.format(map.get("agreement_end_date")),
						mcht_contract_is_mcht_send,	
						map.get("mcht_contract_archive_status_desc")==null?"":map.get("mcht_contract_archive_status_desc").toString(),
						mcht_contract_is_platform_send,
						map.get("mcht_contract_remarks")==null?"":map.get("mcht_contract_remarks").toString(),
						map.get("mcht_product_sum").toString(),
						map.get("order_dtl_pay_amount_sum").toString(),
						map.get("activity_area_count").toString()
				};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 法务审核情况
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/lawAuditSituation.shtml")
	public ModelAndView lawAuditSituation(HttpServletRequest request) {
		String rtPage = "/dataCenter/mchtData/lawAuditSituation";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateEnd = df.format(new Date());
		String dateBegin = dateEnd.substring(0, 7) + "-01";
		resMap.put("dateEnd", dateEnd);
		resMap.put("dateBegin", dateBegin);
		Integer staffId = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		String isManagement = this.getSessionStaffBean(request).getIsManagement();
		resMap.put("isManagement", isManagement);
		if(isManagement.equals("1")){//是管理层
			PlatformContactExample pce = new PlatformContactExample();
			PlatformContactExample.Criteria c = pce.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andContactTypeEqualTo("7");//法务对接人
			c.andStatusEqualTo("0");
			List<PlatformContact> platformContacts = platformContactService.selectByExample(pce);
			resMap.put("platformContacts", platformContacts);
		}else{//不是管理层
			PlatformContactExample pce = new PlatformContactExample();
			PlatformContactExample.Criteria pcec = pce.createCriteria();
			pcec.andDelFlagEqualTo("0");
			pcec.andContactTypeEqualTo("7");//法务对接人
			pcec.andStatusEqualTo("0");
			pcec.andStaffIdEqualTo(staffId);
			List<PlatformContact> platformContacts = platformContactService.selectByExample(pce);
			if(platformContacts!=null && platformContacts.size()>0){//是法务对接人
				resMap.put("myContactId", platformContacts.get(0).getId());
				PlatformContactExample e = new PlatformContactExample();
				PlatformContactExample.Criteria c = e.createCriteria();
				c.andDelFlagEqualTo("0");
				c.andStatusEqualTo("0");
				c.andAssistantIdEqualTo(platformContacts.get(0).getId());
				List<PlatformContact> platformContactList = platformContactService.selectByExample(e);
				resMap.put("platformContacts", platformContactList);
			}else{//不是法务对接人
				resMap.put("notAuth", 1);
			}
		}
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 法务审核情况数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/lawAuditSituation/data.shtml")
	@ResponseBody
	public Map<String, Object> getLawAuditSituationDate(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String dateBegin = request.getParameter("dateBegin");
			String dateEnd = request.getParameter("dateEnd");
			String platformContactId = request.getParameter("platformContactId");
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("dateBegin", dateBegin+" 00:00:00");
			paramMap.put("dateEnd", dateEnd+" 23:59:59");
			if(!StringUtils.isEmpty(platformContactId)){
				paramMap.put("platformContactId", Integer.parseInt(platformContactId));
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			MchtInfoCustomExample mice = new MchtInfoCustomExample();
			MchtInfoCustomExample.MchtInfoCustomCriteria micc = mice.createCriteria();
			micc.andDelFlagEqualTo("0");
			micc.andCreateDateBetween(sdf.parse(dateBegin+" 00:00:00"), sdf.parse(dateEnd+" 23:59:59"));
			if(!StringUtils.isEmpty(platformContactId)){
				micc.andPlatformContactIdEqualTo(Integer.parseInt(platformContactId));
			}
			int totalMchtInfoCount = mchtInfoService.countByExample(mice);
			resMap.put("totalMchtInfoCount", totalMchtInfoCount);
			
			MchtInfoChangeLogCustomExample mchtInfoChangeLogCustomExample = new MchtInfoChangeLogCustomExample();
			MchtInfoChangeLogCustomExample.MchtInfoChangeLogCustomCriteria mchtInfoChangeLogCustomCriteria = mchtInfoChangeLogCustomExample.createCriteria();
			mchtInfoChangeLogCustomCriteria.andDelFlagEqualTo("0");
			List<String> logTypeList = new ArrayList<String>();
			logTypeList.add("资质总审核");
			logTypeList.add("店铺名审核");
			logTypeList.add("线上合同状态");
			logTypeList.add("品牌审核");
			logTypeList.add("公司信息");
			mchtInfoChangeLogCustomCriteria.andLogTypeIn(logTypeList);
			if(!StringUtil.isEmpty(dateBegin)) {//操作时间
				mchtInfoChangeLogCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(dateBegin+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(dateEnd)) {
				mchtInfoChangeLogCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(dateEnd+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(platformContactId)) { //法务对接人
				mchtInfoChangeLogCustomCriteria.andCreateByEqualTo(Integer.parseInt(platformContactId));
			}
			int totalAuditCount = mchtInfoChangeLogService.countByExample(mchtInfoChangeLogCustomExample);
			resMap.put("totalAuditCount", totalAuditCount);
			
			MchtInfoCustomExample mchtInfoCustomExample = new MchtInfoCustomExample();
			MchtInfoCustomExample.MchtInfoCustomCriteria mchtInfoCustomCriteria = mchtInfoCustomExample.createCriteria();
			mchtInfoCustomCriteria.andDelFlagEqualTo("0");
			mchtInfoCustomCriteria.andStatusEqualTo("1");
			mchtInfoCustomCriteria.andCooperateBeginDateBetween(sdf.parse(dateBegin+" 00:00:00"), sdf.parse(dateEnd+" 23:59:59"));
			if(!StringUtils.isEmpty(platformContactId)){
				mchtInfoCustomCriteria.andPlatformContactIdEqualTo(Integer.parseInt(platformContactId));
			}
			int hasOpenCount = mchtInfoService.countByExample(mchtInfoCustomExample);
			resMap.put("hasOpenCount", hasOpenCount);
			
			List<HashMap<String,Object>> productTypeList = mchtProductTypeService.countByProductType(paramMap);
			resMap.put("productTypeList", productTypeList);
			
			int rejectAuditCount = mchtInfoChangeLogService.rejectAuditCount(paramMap);
			resMap.put("rejectAuditCount", rejectAuditCount);
			
			List<HashMap<String,Object>> mchtContractList = mchtContractService.countMchtContract(paramMap);
			resMap.put("mchtContractList", mchtContractList);
			
			List<HashMap<String,Object>> mchtContracts = mchtContractService.countArchiveStatusMchtContract(paramMap);
			resMap.put("mchtContracts", mchtContracts);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	
	/**
	 * 每月商家漏斗统计
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping("/monthlybusinessStatistics.shtml")
	public ModelAndView monthlybusinessStatistics(HttpServletRequest request) {
		String rtPage = "/dataCenter/mchtData/monthlyBusinessStatistics";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		String dateEnd = df.format(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -5);
		String dateBegin = df.format(calendar.getTime());
		resMap.put("date_end", dateEnd);
		resMap.put("date_begin", dateBegin);
		return new ModelAndView(rtPage, resMap);
	}
	
	
	/**
	 * 每月商家漏斗统计列表数据
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/monthlyBusinessStatisticsdata.shtml")
	@ResponseBody
	public Map<String, Object> monthlyBusinessStatisticsdata(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
			String DateBegin = request.getParameter("date_begin");
			String DateEnd = request.getParameter("date_end");
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			if (!StringUtil.isEmpty(request.getParameter("date_begin"))) {
				paramMap.put("DateBegin", DateBegin);
			} else {
				DateEnd = df.format(new Date());
				DateBegin = DateEnd.substring(0, 7);
				paramMap.put("DateBegin", DateBegin);
			}
			if (!StringUtil.isEmpty(request.getParameter("date_end"))) {
				paramMap.put("DateEnd", DateEnd);
			} else {
				DateEnd = df.format(new Date());
				DateEnd = DateEnd.substring(0, 7);
				paramMap.put("DateEnd", DateEnd);
			}

			int mchtotalmchtcount = 0;
			int settledunsubmitedtcount = 0;
			int settledauditedtcount = 0;
			int settledadoptcount = 0;
			int shopopencount = 0;
			int contractfilecount = 0;
			int mchtcloseapplicationcount=0;
			int mchtclosecount=0;

			List<MchtInfoCustom> totalmchtInfoMonthlyCustomList = mchtInfoService.totalmchtInfoMonthlyCustom(paramMap);
			for (MchtInfoCustom totalmchtInfoMonthlycustomList : totalmchtInfoMonthlyCustomList) {
				if (totalmchtInfoMonthlyCustomList!=null && totalmchtInfoMonthlyCustomList.size()>0) {
					mchtotalmchtcount=mchtotalmchtcount+totalmchtInfoMonthlycustomList.getMchtCount();
					settledunsubmitedtcount=settledunsubmitedtcount+totalmchtInfoMonthlycustomList.getSettledunSubmitted();
					settledauditedtcount=settledauditedtcount+totalmchtInfoMonthlycustomList.getSettleDaudited();
					settledadoptcount=settledadoptcount+totalmchtInfoMonthlycustomList.getSettleDadopt();
					shopopencount=shopopencount+totalmchtInfoMonthlycustomList.getShopOpen();
					contractfilecount=contractfilecount+totalmchtInfoMonthlycustomList.getContractFile();
					mchtcloseapplicationcount=mchtcloseapplicationcount+totalmchtInfoMonthlycustomList.getMchtcloseApplication();
					mchtclosecount=mchtclosecount+totalmchtInfoMonthlycustomList.getMchtcloseCount();
				}
			}

			List<MchtInfoCustom> totalmchtInfoMonthlyCustom = new ArrayList<MchtInfoCustom>();
			totalmchtInfoMonthlyCustom = mchtInfoService.totalmchtInfoMonthlyCustom(paramMap);
			HashMap<String, MchtInfoCustom> map = new HashMap<String, MchtInfoCustom>();
			List<String> containMonthly = new ArrayList<String>();
			for (MchtInfoCustom totalmchtInfoMonthlyCustoms : totalmchtInfoMonthlyCustom) {
				containMonthly.add(totalmchtInfoMonthlyCustoms.getEachMonth());
				map.put(totalmchtInfoMonthlyCustoms.getEachMonth(), totalmchtInfoMonthlyCustoms);
			}

			List<String> amountDays = this.getBetweenMonthlys(DateBegin, DateEnd);
			for (int i = 0; i < amountDays.size(); i++) {
				if (!containMonthly.contains(amountDays.get(i))) {
					MchtInfoCustom totalmchtInfoMonthlyCustomm = new MchtInfoCustom();
					totalmchtInfoMonthlyCustomm.setEachMonth(amountDays.get(i));
					totalmchtInfoMonthlyCustom.add(totalmchtInfoMonthlyCustomm);
					map.put(amountDays.get(i), totalmchtInfoMonthlyCustomm);
				}
			}
			Collections.sort(totalmchtInfoMonthlyCustom,
					new Comparator<MchtInfoCustom>() {
						@Override
						public int compare(MchtInfoCustom c1, MchtInfoCustom c2) {

							return c1.getEachMonth().compareTo(c2.getEachMonth()); // 按时间升序
						}
					});

			resMap.put("Rows", totalmchtInfoMonthlyCustom);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;

	}
	
	
	/**
	 * 每月商家数
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping("/monthlyMchtinfoStatistics.shtml")
	public ModelAndView monthlyMchtinfoStatistics(HttpServletRequest request) {
		String rtPage = "/dataCenter/mchtData/monthlyMchtinfoStatistics";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		String dateEnd = df.format(new Date());
		Calendar calendar = Calendar.getInstance();
		String dateBegin = df.format(calendar.getTime());
		resMap.put("date_end", dateEnd);
		resMap.put("date_begin", dateBegin);
		return new ModelAndView(rtPage, resMap);
	}
	
	
	/**
	 * 每月商家数列表数据
	 * 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/monthlyMchtinfoStatisticsdata.shtml")
	@ResponseBody
	public Map<String, Object> monthlyMchtinfoStatisticsdata(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
			String DateBegin = request.getParameter("date_begin");
			String DateEnd = request.getParameter("date_end");
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			if (!StringUtil.isEmpty(request.getParameter("date_begin"))) {
				paramMap.put("DateBegin", DateBegin);
			} else {
				DateEnd = df.format(new Date());
				DateBegin = DateEnd.substring(0, 7);
				paramMap.put("DateBegin", DateBegin);
			}
			if (!StringUtil.isEmpty(request.getParameter("date_end"))) {
				paramMap.put("DateEnd", DateEnd);
			} else {
				DateEnd = df.format(new Date());
				DateEnd = DateEnd.substring(0, 7);
				paramMap.put("DateEnd", DateEnd);
			}

			int settledCount = 0;
			int mchtCount = 0;
			int popcommitauditCount = 0;
			int popqualificationthroughCount = 0;
			int popcooperatebeginCount = 0;
			int popmchtcloseAppliCation = 0;
			int popmchtcloseCount=0;

			List<MchtInfoCustom> mchtInfoMonthlyCustomList = mchtInfoService.mchtinfoMonthlyCoum(paramMap);
			for (MchtInfoCustom mchtInfoMonthlycustomList : mchtInfoMonthlyCustomList) {
				if (mchtInfoMonthlyCustomList!=null && mchtInfoMonthlyCustomList.size()>0) {
					settledCount=settledCount+mchtInfoMonthlycustomList.getSettledCount();
					mchtCount=mchtCount+mchtInfoMonthlycustomList.getMchtCount();
					popcommitauditCount=popcommitauditCount+mchtInfoMonthlycustomList.getPopcommitauditCount();
					popqualificationthroughCount=popqualificationthroughCount+mchtInfoMonthlycustomList.getPopqualificationthroughCount();
					popcooperatebeginCount=popcooperatebeginCount+mchtInfoMonthlycustomList.getPopcooperatebeginCount();
					popmchtcloseAppliCation=popmchtcloseAppliCation+mchtInfoMonthlycustomList.getPopmchtcloseAppliCation();
					popmchtcloseCount=popmchtcloseCount+mchtInfoMonthlycustomList.getPopmchtcloseCount();
				}
			}

			List<MchtInfoCustom> mchtInfoMonthlyCustom = new ArrayList<MchtInfoCustom>();
			mchtInfoMonthlyCustom = mchtInfoService.mchtinfoMonthlyCoum(paramMap);
			HashMap<String, MchtInfoCustom> map = new HashMap<String, MchtInfoCustom>();
			List<String> containMonthly = new ArrayList<String>();
			for (MchtInfoCustom mchtInfoMonthlyCustoms : mchtInfoMonthlyCustom) {
				containMonthly.add(mchtInfoMonthlyCustoms.getEachMonth());
				map.put(mchtInfoMonthlyCustoms.getEachMonth(), mchtInfoMonthlyCustoms);
			}

			List<String> amountDays = this.getBetweenMonthlys(DateBegin, DateEnd);
			for (int i = 0; i < amountDays.size(); i++) {
				if (!containMonthly.contains(amountDays.get(i))) {
					MchtInfoCustom mchtInfoMonthlyCustomm = new MchtInfoCustom();
					mchtInfoMonthlyCustomm.setEachMonth(amountDays.get(i));
					mchtInfoMonthlyCustom.add(mchtInfoMonthlyCustomm);
					map.put(amountDays.get(i), mchtInfoMonthlyCustomm);
				}
			}
			Collections.sort(mchtInfoMonthlyCustom,
					new Comparator<MchtInfoCustom>() {
						@Override
						public int compare(MchtInfoCustom c1, MchtInfoCustom c2) {

							return c1.getEachMonth().compareTo(c2.getEachMonth()); // 按时间升序
						}
					});

			resMap.put("Rows", mchtInfoMonthlyCustom);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;

	}
	
	
	
	public List<String> getBetweenMonthlys(String stime, String etime) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		Date sdate = null;
		Date eDate = null;
		try {
			sdate = df.parse(stime);
			eDate = df.parse(etime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		List<String> list = new ArrayList<String>();
		while (sdate.getTime() <= eDate.getTime()) {
			list.add(df.format(sdate));
			c.setTime(sdate);
			c.add(Calendar.MONTH, 1); //月份加1月
			sdate = c.getTime();
		}

		return list;
	}

	
}
