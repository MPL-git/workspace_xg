package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.entity.*;
import com.jf.service.*;
import com.jf.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("serial")
@Controller
public class CommentController extends BaseController {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private CommentPicService commentPicService;
	
	@Autowired
	private ShopScoreService shopScoreService;
	
	@Autowired
	private ProductTypeService productTypeService;
	
	@Autowired
	private MchtInfoService mchtInfoService;
	
	@Autowired
	private MchtProductBrandService mchtProductBrandService;
	
	@Autowired
	private ProductService productService;
	
	/**
	 * 
	 * @Title commentManager   
	 * @Description TODO(评价管理)   
	 * @author pengl
	 * @date 2018年6月27日 下午6:19:32
	 */
	@RequestMapping("/comment/commentManager.shtml")
	public ModelAndView commentManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/comment/comment/getCommentList");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		m.addObject("beginCreateDate", sdf.format(calendar.getTime()));
		m.addObject("endCreateDate", sdf.format(date));
		m.addObject("isShowList", DataDicUtil.getTableStatus("BU_COMMENT", "IS_SHOW"));
		return m;
	}
	
	/**
	 * 
	 * @Title getCommentList   
	 * @Description TODO(评价管理)   
	 * @author pengl
	 * @date 2018年6月27日 下午6:19:35
	 */
	@ResponseBody
	@RequestMapping("/comment/getCommentList.shtml")
	public Map<String, Object> getCommentList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<CommentCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			CommentCustomExample commentCustomExample = new CommentCustomExample();
			CommentCustomExample.CommentCustomCriteria commentCustomCriteria = commentCustomExample.createCriteria();
			commentCustomCriteria.andDelFlagEqualTo("0");
			commentCustomCriteria.andIsAppendCommentEqualTo("0"); //不是追加
			if(!StringUtil.isEmpty(request.getParameter("commentStatus"))) {
				commentCustomCriteria.andCommentStatus(request.getParameter("commentStatus"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				commentCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtName"))) {
				commentCustomCriteria.andMchtNameLike("%"+request.getParameter("mchtName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("productCode"))) {
				commentCustomCriteria.andProductCodeEqualTo(request.getParameter("productCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("brandName"))) {
				commentCustomCriteria.andBrandNameLike("%"+request.getParameter("brandName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("beginCreateDate"))) {
				commentCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("beginCreateDate")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("endCreateDate"))) {
				commentCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("endCreateDate")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("content"))){
				commentCustomCriteria.andContentLike("%"+request.getParameter("content")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("contentFlag"))) {
				commentCustomCriteria.andContentNotEqualTo("默认好评！");
			}
			if(!StringUtil.isEmpty(request.getParameter("subOrderCode"))) {
				commentCustomCriteria.andSubOrderCodeEqualTo(request.getParameter("subOrderCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("isShow"))) {
				commentCustomCriteria.andIsShowEqualTo(request.getParameter("isShow"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productType"))){
				commentCustomCriteria.andProductTypeAll(productService.getProductTypeAllChild(Integer.valueOf(request.getParameter("productType"))));
			}
			if(!StringUtil.isEmpty(request.getParameter("commentGrade"))) {
				commentCustomCriteria.andCommentGrade(request.getParameter("commentGrade"));
			}
			commentCustomExample.setOrderByClause(" t.create_date desc");
			commentCustomExample.setLimitStart(page.getLimitStart());
			commentCustomExample.setLimitSize(page.getLimitSize());
			totalCount = commentService.countByCustomExample(commentCustomExample);
			dataList = commentService.selectByCustomExample(commentCustomExample);
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
	 * @Title getCommentPicList   
	 * @Description TODO(获取图片)   
	 * @author pengl
	 * @date 2018年6月28日 上午10:38:32
	 */
	@ResponseBody
	@RequestMapping("/comment/getCommentPicList.shtml")
	public List<CommentPic> getCommentPicList(HttpServletRequest request){
		List<CommentPic> commentPicList = new ArrayList<CommentPic>();
		if(!StringUtil.isEmpty(request.getParameter("commentId"))){
			CommentPicExample commentPicExample = new CommentPicExample();
			commentPicExample.createCriteria().andDelFlagEqualTo("0").andPicTypeEqualTo("1")
				.andCommentIdEqualTo(Integer.parseInt(request.getParameter("commentId")));
			commentPicExample.setLimitStart(0);
			commentPicExample.setLimitSize(6);
			commentPicList = commentPicService.selectByExample(commentPicExample);
		}
		return commentPicList;
	}
	
	/**
	 * 
	 * @Title getCommentMchtPicList   
	 * @Description TODO(获取商家回复图片)   
	 * @author pengl
	 * @date 2018年9月26日 上午10:51:43
	 */
	@ResponseBody
	@RequestMapping("/comment/getCommentMchtPicList.shtml")
	public List<CommentPic> getCommentMchtPicList(HttpServletRequest request){
		List<CommentPic> commentPicList = new ArrayList<CommentPic>();
		if(!StringUtil.isEmpty(request.getParameter("commentId"))){
			CommentPicExample commentPicExample = new CommentPicExample();
			commentPicExample.createCriteria().andDelFlagEqualTo("0").andPicTypeEqualTo("2")
			.andCommentIdEqualTo(Integer.parseInt(request.getParameter("commentId")));
			commentPicExample.setLimitStart(0);
			commentPicExample.setLimitSize(6);
			commentPicList = commentPicService.selectByExample(commentPicExample);
		}
		return commentPicList;
	}
	
	/**
	 * 
	 * @Title viewComment   
	 * @Description TODO(评价详情)   
	 * @author pengl
	 * @date 2018年6月28日 下午3:10:05
	 */
	@RequestMapping("/comment/viewComment.shtml")
	public ModelAndView viewComment(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/comment/comment/viewComment");
		if(!StringUtil.isEmpty(request.getParameter("commentId"))) {
			//不是追评
			CommentCustom commentCustom = commentService.selectByCustomPrimaryKey(Integer.parseInt(request.getParameter("commentId")));
			CommentPicExample commentPicExample = new CommentPicExample();
			commentPicExample.createCriteria().andDelFlagEqualTo("0").andPicTypeEqualTo("1")
				.andCommentIdEqualTo(commentCustom.getId());
			commentPicExample.setLimitStart(0);
			commentPicExample.setLimitSize(6);
			List<CommentPic> commentPicList = commentPicService.selectByExample(commentPicExample);
			m.addObject("commentCustom", commentCustom);
			m.addObject("commentPicList", commentPicList);
			//是追评
			CommentExample commentExample = new CommentExample();
			CommentExample.Criteria commentCriteria = commentExample.createCriteria();
			commentCriteria.andDelFlagEqualTo("0");
			commentCriteria.andIsAppendCommentEqualTo("1");
			commentCriteria.andSubOrderIdEqualTo(commentCustom.getSubOrderId());
			commentCriteria.andOrderDtlIdEqualTo(commentCustom.getOrderDtlId());
			List<Comment> commentList = commentService.selectByExample(commentExample);
			if(commentList != null && commentList.size() > 0) {
				Comment appendComment = commentList.get(0);
				CommentPicExample appendCommentPicExample = new CommentPicExample();
				appendCommentPicExample.createCriteria().andDelFlagEqualTo("0").andPicTypeEqualTo("1")
					.andCommentIdEqualTo(appendComment.getId());
				appendCommentPicExample.setLimitStart(0);
				appendCommentPicExample.setLimitSize(6);
				List<CommentPic> appendCommentPicList = commentPicService.selectByExample(appendCommentPicExample);
				m.addObject("appendComment", appendComment);
				m.addObject("appendCommentPicList", appendCommentPicList);
				//计算评论和追评相差天数
				long betweenDays = DateUtil.getDayBetween(commentCustom.getCreateDate(), appendComment.getCreateDate());
				m.addObject("betweenDays", betweenDays);
			}
			//店铺评分
			ShopScoreExample shopScoreExample = new ShopScoreExample();
			ShopScoreExample.Criteria shopScoreCriteria = shopScoreExample.createCriteria();
			shopScoreCriteria.andDelFlagEqualTo("0");
			shopScoreCriteria.andSubOrderIdEqualTo(commentCustom.getSubOrderId());
			List<ShopScore> shopScoreList = shopScoreService.selectByExample(shopScoreExample);
			if(shopScoreList != null && shopScoreList.size() > 0) {
				m.addObject("shopScore", shopScoreList.get(0));
			}
		}
		return m;
	}
	
	/**
	 * 
	 * @Title viewCommentList   
	 * @Description TODO(异步加载评论信息)   
	 * @author pengl
	 * @date 2018年6月29日 下午2:50:08
	 */
	@ResponseBody
	@RequestMapping("/comment/viewCommentList.shtml")
	public Map<String, Object> viewCommentList(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			if(!StringUtil.isEmpty(request.getParameter("subOrderId"))) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				CommentCustomExample commentCustomExample = new CommentCustomExample();
				CommentCustomExample.CommentCustomCriteria commentCustomCriteria = commentCustomExample.createCriteria();
				commentCustomCriteria.andDelFlagEqualTo("0");
				commentCustomCriteria.andIsAppendCommentEqualTo("0"); //不是追加
				commentCustomCriteria.andSubOrderIdEqualTo(Integer.parseInt(request.getParameter("subOrderId")));
				List<CommentCustom> commentCustomList = commentService.selectByCustomExample(commentCustomExample);
				if(commentCustomList != null && commentCustomList.size() > 0) {
					List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
					for(CommentCustom commentCustom : commentCustomList) {
						Map<String, Object> commentMap = new HashMap<String, Object>();
						commentMap.put("commentCustom", commentCustom);
						//评论图片
						CommentPicExample commentPicExample = new CommentPicExample();
						commentPicExample.createCriteria().andDelFlagEqualTo("0").andPicTypeEqualTo("1")
							.andCommentIdEqualTo(commentCustom.getId());
						commentPicExample.setLimitStart(0);
						commentPicExample.setLimitSize(6);
						List<CommentPic> commentPicList = commentPicService.selectByExample(commentPicExample);
						commentMap.put("commentPicList", commentPicList);
						//追评
						CommentCustomExample appendCommentCustomExample = new CommentCustomExample();
						CommentCustomExample.CommentCustomCriteria appendCommentCustomCriteria = appendCommentCustomExample.createCriteria();
						appendCommentCustomCriteria.andDelFlagEqualTo("0");
						appendCommentCustomCriteria.andIsAppendCommentEqualTo("1"); //是追加
						appendCommentCustomCriteria.andSubOrderIdEqualTo(commentCustom.getSubOrderId());
						appendCommentCustomCriteria.andProductIdEqualTo(commentCustom.getProductId());
						List<CommentCustom> appendCommentCustomList = commentService.selectByCustomExample(appendCommentCustomExample);
						if(appendCommentCustomList != null && appendCommentCustomList.size() > 0) {
							Comment appendCommentCustom = appendCommentCustomList.get(0);
							commentMap.put("appendCommentCustom", appendCommentCustom);
							//追评图片
							CommentPicExample appendCommentPicExample = new CommentPicExample();
							appendCommentPicExample.createCriteria().andDelFlagEqualTo("0").andPicTypeEqualTo("1")
								.andCommentIdEqualTo(appendCommentCustom.getId());
							appendCommentPicExample.setLimitStart(0);
							appendCommentPicExample.setLimitSize(6);
							List<CommentPic> appendCommentPicList = commentPicService.selectByExample(appendCommentPicExample);
							commentMap.put("appendCommentPicList", appendCommentPicList);
							//计算评论和追评相差天数
							long betweenDays = DateUtil.getDayBetween(commentCustom.getCreateDate(), appendCommentCustom.getCreateDate());
							commentMap.put("betweenDays", betweenDays);
						}
						listMap.add(commentMap);
					}
					map.put("listMap", listMap);
					//店铺评分
					ShopScoreExample shopScoreExample = new ShopScoreExample();
					ShopScoreExample.Criteria shopScoreCriteria = shopScoreExample.createCriteria();
					shopScoreCriteria.andDelFlagEqualTo("0");
					shopScoreCriteria.andSubOrderIdEqualTo(commentCustomList.get(0).getSubOrderId());
					List<ShopScore> shopScoreList = shopScoreService.selectByExample(shopScoreExample);
					if(shopScoreList != null && shopScoreList.size() > 0) {
						map.put("shopScore", shopScoreList.get(0));
						map.put("createDate", sdf.format(shopScoreList.get(0).getCreateDate()));
					}
				}
			}else {
				code = "9999";
				msg = "订单ID为空！";
			}
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
	
	/**
	 * 
	 * @Title mchtCommentManager   
	 * @Description TODO(商家评论信息)   
	 * @author pengl
	 * @date 2018年6月29日 下午5:35:22
	 */
	@RequestMapping("/comment/mchtCommentManager.shtml")
	public ModelAndView mchtCommentManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/comment/mchtComment/getMchtCommentList");
		ProductTypeExample example = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = example.createCriteria();
		productTypeCriteria.andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService.selectByExample(example);
		m.addObject("productTypes", productTypes);
		return m;
	}
	
	/**
	 * 
	 * @Title getMchtCommentList   
	 * @Description TODO(商家评论信息)   
	 * @author pengl
	 * @date 2018年6月29日 下午6:20:10
	 */
	@ResponseBody
	@RequestMapping("/comment/getMchtCommentList.shtml")
	public Map<String, Object> getMchtCommentList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		Integer totalCount = 0;
		try {
			MchtInfoCustomExample mchtInfoCustomExample = new MchtInfoCustomExample();
			MchtInfoCustomExample.MchtInfoCustomCriteria mchtInfoCustomCriteria = mchtInfoCustomExample.createCriteria();
			mchtInfoCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1");
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				mchtInfoCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtName"))) {
				mchtInfoCustomCriteria.andCompanyOrShopNameLike(request.getParameter("mchtName"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
				mchtInfoCustomCriteria.andProductTypeIdIsMainEqualTo(request.getParameter("productTypeId"));
			}
			if (!StringUtil.isEmpty(request.getParameter("avgProductScoreBegin"))) {
				mchtInfoCustomCriteria.andavgProductScoreBeginEqualTo(new BigDecimal(request.getParameter("avgProductScoreBegin")));
			}
			if (!StringUtil.isEmpty(request.getParameter("avgProductScoreEnd"))) {
				mchtInfoCustomCriteria.andavgProductScoreEndEqualTo(new BigDecimal(request.getParameter("avgProductScoreEnd")));
			}
			if (!StringUtil.isEmpty(request.getParameter("avgMchtScoreBegin"))) {
				mchtInfoCustomCriteria.andavgMchtScoreBeginEqualTo(new BigDecimal(request.getParameter("avgMchtScoreBegin")));
			}
			if (!StringUtil.isEmpty(request.getParameter("avgMchtScoreEnd"))) {
				mchtInfoCustomCriteria.andavgMchtScoreEndTo(new BigDecimal(request.getParameter("avgMchtScoreEnd")));
			}
			if (!StringUtil.isEmpty(request.getParameter("avgWuliuScoreBegin"))) {
				mchtInfoCustomCriteria.andavgWuliuScoreBeginEqualTo(new BigDecimal(request.getParameter("avgWuliuScoreBegin")));
			}
			if (!StringUtil.isEmpty(request.getParameter("avgWuliuScoreEnd"))) {
				mchtInfoCustomCriteria.andavgWuliuScoreEndEndTo(new BigDecimal(request.getParameter("avgWuliuScoreEnd")));
			}
			if (!StringUtil.isEmpty(request.getParameter("bandName"))) {
				mchtInfoCustomCriteria.andMchtProductBrandNameZhEnLike("%"+request.getParameter("bandName")+"%");
			}
			mchtInfoCustomExample.setLimitStart(page.getLimitStart());
			mchtInfoCustomExample.setLimitSize(page.getLimitSize());
			totalCount = mchtInfoService.countByExample(mchtInfoCustomExample);
			dataList = mchtInfoService.selectMchtInfoCustomExample(mchtInfoCustomExample);
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
	 * @Title mchtCommentInfoManager   
	 * @Description TODO(评价信息)   
	 * @author pengl
	 * @date 2018年6月30日 上午11:49:19
	 */
	@RequestMapping("/comment/mchtCommentInfoManager.shtml")
	public ModelAndView mchtCommentInfoManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/comment/mchtComment/getMchtCommentInfoList");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		if(!StringUtil.isEmpty(request.getParameter("mchtId"))) {
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(Integer.parseInt(request.getParameter("mchtId")));
			m.addObject("mchtInfo", mchtInfo);
		}
		m.addObject("beginCreateDate", sdf.format(calendar.getTime()));
		m.addObject("endCreateDate", sdf.format(date));
		m.addObject("mchtId", request.getParameter("mchtId"));
		m.addObject("avgProductScore", request.getParameter("avgProductScore"));
		m.addObject("avgMchtScore", request.getParameter("avgMchtScore"));
		m.addObject("avgWuliuScore", request.getParameter("avgWuliuScore"));
		m.addObject("isShowList", DataDicUtil.getTableStatus("BU_COMMENT", "IS_SHOW"));
		return m;
	}
	
	/**
	 * 
	 * @Title getMchtCommentInfoList   
	 * @Description TODO(评价信息)   
	 * @author pengl
	 * @date 2018年6月30日 上午11:50:06
	 */
	@ResponseBody
	@RequestMapping("/comment/getMchtCommentInfoList.shtml")
	public Map<String, Object> getMchtCommentInfoList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<CommentCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			CommentCustomExample commentCustomExample = new CommentCustomExample();
			CommentCustomExample.CommentCustomCriteria commentCustomCriteria = commentCustomExample.createCriteria();
			commentCustomCriteria.andDelFlagEqualTo("0");
			commentCustomCriteria.andIsAppendCommentEqualTo("0"); //不是追加
			if(!StringUtil.isEmpty(request.getParameter("mchtId"))) {
				commentCustomCriteria.andMchtIdEqualTo(Integer.parseInt(request.getParameter("mchtId")));
			}
			if(!StringUtil.isEmpty(request.getParameter("commentStatus"))) {
				commentCustomCriteria.andCommentStatus(request.getParameter("commentStatus"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productCode"))) {
				commentCustomCriteria.andProductCodeEqualTo(request.getParameter("productCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("brandName"))) {
				commentCustomCriteria.andBrandNameLike("%"+request.getParameter("brandName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("beginCreateDate"))) {
				commentCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("beginCreateDate")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("endCreateDate"))) {
				commentCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("endCreateDate")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("content"))) {
				commentCustomCriteria.andContentLike("%"+request.getParameter("content")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("contentFlag"))) {
				commentCustomCriteria.andContentNotEqualTo("默认好评！");
			}
			if(!StringUtil.isEmpty(request.getParameter("subOrderCode"))) {
				commentCustomCriteria.andSubOrderCodeEqualTo(request.getParameter("subOrderCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("isShow"))) {
				commentCustomCriteria.andIsShowEqualTo(request.getParameter("isShow"));
			}
			if(!StringUtil.isEmpty(request.getParameter("commentGrade"))) {
				commentCustomCriteria.andCommentGrade(request.getParameter("commentGrade"));
			}
			commentCustomExample.setOrderByClause(" t.create_date desc");
			commentCustomExample.setLimitStart(page.getLimitStart());
			commentCustomExample.setLimitSize(page.getLimitSize());
			totalCount = commentService.countByCustomExample(commentCustomExample);
			dataList = commentService.selectByCustomExample(commentCustomExample);
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
	 * @Title updateIsShow   
	 * @Description TODO(是否显示)   
	 * @author pengl
	 * @date 2018年9月26日 下午1:48:11
	 */
	@ResponseBody
	@RequestMapping("/comment/updateIsShow.shtml")
	public Map<String, Object> updateIsShow(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			if(!StringUtil.isEmpty(request.getParameter("commentId"))) {
				Date date = new Date();
				String staffId = this.getSessionStaffBean(request).getStaffID();
				CommentExample commentExample = new CommentExample();
				commentExample.createCriteria().andIdEqualTo(Integer.parseInt(request.getParameter("commentId")));
				Comment comment = new Comment();
				comment.setIsShow(request.getParameter("isShow"));
				comment.setUpdateDate(date);
				comment.setUpdateBy(Integer.parseInt(staffId));
				commentService.updateByExampleSelective(comment, commentExample);
				
				Comment comment2=commentService.selectByPrimaryKey(Integer.valueOf(request.getParameter("commentId")));
				map.put("subOrderId", comment2.getSubOrderId());
				
			}else {
				code = "9999";
				msg = "评价ID为空！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "系统异常请稍后再试！";
		}
		map.put("isShow", request.getParameter("isShow"));
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * 
	 * @Title updateIsShow   
	 * @Description TODO(是否显示)   
	 * @author pengl
	 * @date 2018年9月26日 下午1:48:11
	 */
	@ResponseBody
	@RequestMapping("/comment/saveCommentWeight.shtml")
	public Map<String, Object> saveCommentWeight(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			if(!StringUtil.isEmpty(request.getParameter("id"))) {
				Date date = new Date();
				String staffId = this.getSessionStaffBean(request).getStaffID();
				CommentExample commentExample = new CommentExample();
				commentExample.createCriteria().andIdEqualTo(Integer.parseInt(request.getParameter("id")));
				Comment comment = new Comment();
				comment.setCommentWeight(Integer.parseInt(request.getParameter("commentWeight")));
				comment.setUpdateDate(date);
				comment.setUpdateBy(Integer.parseInt(staffId));
				commentService.updateByExampleSelective(comment, commentExample);
			}else {
				code = "9999";
				msg = "评价ID为空！";
			}
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
}
