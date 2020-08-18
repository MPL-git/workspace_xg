package com.jf.controller;

import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.CommentPicMapper;
import com.jf.entity.*;
import com.jf.service.CommentService;
import com.jf.service.MchtProductBrandService;
import com.jf.service.OrderDtlService;
import com.jf.service.ShopScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CommentController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@Resource
	private CommentService commentService;
	
	@Resource
	private CommentPicMapper commentPicMapper;
	
	@Resource
	private ShopScoreService shopScoreService;
	
	@Resource
	private MchtProductBrandService mchtProductBrandService;
	
	@Resource
	private OrderDtlService orderDtlService;
	
	/**
	 * 评论管理首页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/comment/commentIndex")
	public String commentIndex(Model model, HttpServletRequest request) {
		List<HashMap<String, Object>> totalShopScoreList = shopScoreService.getTotalShopScoreByMchtId(this.getSessionMchtInfo(request).getId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String endDate = sdf.format(new Date());
		String beginDate = endDate.substring(0, 7)+"-01";
		List<HashMap<String, Object>> totalProductScore = commentService.getTotalProductScoreByMchtId(this.getSessionMchtInfo(request).getId());
		DecimalFormat df = new DecimalFormat("#0.000");
		model.addAttribute("totalMchtScore", df.format(totalShopScoreList.get(0).get("totalMchtScore")).substring(0, 4));
		model.addAttribute("totalWuliuScore", df.format(totalShopScoreList.get(0).get("totalWuliuScore")).substring(0, 4));
		model.addAttribute("endDate", endDate);
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("totalProductScore", df.format(totalProductScore.get(0).get("totalProductScore")).substring(0, 4));
		List<ProductBrand> productBrandList = mchtProductBrandService.getMchtProductBrandList(this.getSessionMchtInfo(request).getId());
		model.addAttribute("productBrandList", productBrandList);
		model.addAttribute("defaultComment", 1);
		return "comment/commentIndex";
	}
	
	/**
	 * 数据列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/comment/getCommentList")
	@ResponseBody
	public ResponseMsg getSubOrderList(HttpServletRequest request, Page page) throws ParseException {
		Map<String, Object> returnData = new HashMap<String, Object>();
		CommentCustomExample commentCustomExample = new CommentCustomExample();
		CommentCustomExample.CommentCustomCriteria commentCustomCriteria = commentCustomExample.createCriteria();
		commentCustomExample.setOrderByClause("t.id desc");
		commentCustomCriteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		commentCustomCriteria.andDelFlagEqualTo("0");
		commentCustomCriteria.andIsAppendCommentEqualTo("0");
		if (!StringUtil.isEmpty(request.getParameter("hasPic"))) {
			commentCustomCriteria.andHasPic();
		}
		if (!StringUtil.isEmpty(request.getParameter("productCode"))) {
			commentCustomCriteria.andProductCodeEqualTo(request.getParameter("productCode").trim());
		}
		
		if (!StringUtil.isEmpty(request.getParameter("brandId"))) {
			commentCustomCriteria.andBrandIdEqualTo(Integer.parseInt(request.getParameter("brandId")));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (!StringUtil.isEmpty(request.getParameter("createDateBegin"))) {
			commentCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("createDateBegin")+" 00:00:00"));
		}
		if (!StringUtil.isEmpty(request.getParameter("createDateEnd"))) {
			commentCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("createDateEnd")+" 23:59:59"));
		}
		if (!StringUtil.isEmpty(request.getParameter("orderCreateDateBegin"))) {
			commentCustomCriteria.andOrderCreateDateGreaterThanOrEqualTo(request.getParameter("orderCreateDateBegin")+" 00:00:00");
		}
		if (!StringUtil.isEmpty(request.getParameter("orderCreateDateEnd"))) {
			commentCustomCriteria.andOrderCreateDateLessThanOrEqualTo(request.getParameter("orderCreateDateEnd")+" 23:59:59");
		}
		if (!StringUtil.isEmpty(request.getParameter("isAllowModifyComment"))) {
			commentCustomCriteria.andIsAllowModifyCommentEqualTo(request.getParameter("isAllowModifyComment"));
		}
		if (!StringUtil.isEmpty(request.getParameter("subOrderCode"))) {
			commentCustomCriteria.andSubOrderCodeEqualTo(request.getParameter("subOrderCode").trim());
		}
		if (!StringUtil.isEmpty(request.getParameter("defaultComment"))) {
			commentCustomCriteria.andContentNotEqualTo("默认好评！");
		}
		
		int totalCount = commentService.countCommentCustomByExample(commentCustomExample);
		commentCustomExample.setLimitStart(page.getLimitStart());
		commentCustomExample.setLimitSize(page.getLimitSize());
		List<CommentCustom> commentCustoms = commentService.selectCommentCustomByExample(commentCustomExample);
		for(CommentCustom commentCustom:commentCustoms){
			Integer commentId = commentCustom.getId();
			CommentPicExample cpe = new CommentPicExample();
			CommentPicExample.Criteria c = cpe.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andCommentIdEqualTo(commentId);
			c.andPicTypeEqualTo("1");//1.评论
			List<CommentPic> commentPics = commentPicMapper.selectByExample(cpe);
			commentCustom.setCommentPics(commentPics);
			
			if(commentCustom.getAppendCommentId()!=null){
				CommentPicExample appendCpe = new CommentPicExample();
				CommentPicExample.Criteria appendC = appendCpe.createCriteria();
				appendC.andDelFlagEqualTo("0");
				appendC.andCommentIdEqualTo(commentCustom.getAppendCommentId());
				appendC.andPicTypeEqualTo("1");//1.评论
				List<CommentPic> appendCommentPics = commentPicMapper.selectByExample(appendCpe);
				commentCustom.setAppendCommentPics(appendCommentPics);
				//计算评论和追评相差天数
				long betweenDays = DateUtil.getDayBetween(commentCustom.getCreateDate(), commentCustom.getAppendCreateDate());
				commentCustom.setBetweenDays(betweenDays);
			}
			if(commentCustom.getMchtReplyDate()!=null){
				long mchtReplyBetweenDays = DateUtil.getDayBetween(commentCustom.getCreateDate(), commentCustom.getMchtReplyDate());
				commentCustom.setMchtReplyBetweenDays(mchtReplyBetweenDays);
			}
		}
		returnData.put("Rows", commentCustoms);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	/**
	 * 评论详情
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/comment/commentView")
	public String subOrderView(Model model,HttpServletRequest request) {
		String commentId = request.getParameter("id");
		Comment comment = commentService.selectByPrimaryKey(Integer.parseInt(commentId));
		model.addAttribute("comment", comment);
		model.addAttribute("starWidth", comment.getProductScore()*24);
		CommentPicExample cpe = new CommentPicExample();
		CommentPicExample.Criteria cpec = cpe.createCriteria();
		cpec.andDelFlagEqualTo("0");
		cpec.andCommentIdEqualTo(comment.getId());
		cpec.andPicTypeEqualTo("1");
		List<CommentPic> commentPics = commentPicMapper.selectByExample(cpe);
		model.addAttribute("commentPics", commentPics);
		OrderDtl orderDtl = orderDtlService.selectByPrimaryKey(comment.getOrderDtlId());
		model.addAttribute("productPropDesc", orderDtl.getProductPropDesc());
		CommentExample ce = new CommentExample();
		CommentExample.Criteria cec = ce.createCriteria();
		cec.andDelFlagEqualTo("0");
		cec.andSubOrderIdEqualTo(comment.getSubOrderId());
		cec.andOrderDtlIdEqualTo(comment.getOrderDtlId());
		cec.andIsAppendCommentEqualTo("1");
		List<Comment> appendComments = commentService.selectByExample(ce);
		if(appendComments!=null && appendComments.size()>0){
			model.addAttribute("appendComment", appendComments.get(0));
			//计算评论和追评相差天数
			long betweenDays = DateUtil.getDayBetween(comment.getCreateDate(), appendComments.get(0).getCreateDate());
			model.addAttribute("betweenDays", betweenDays);
			CommentPicExample e = new CommentPicExample();
			CommentPicExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andCommentIdEqualTo(appendComments.get(0).getId());
			c.andPicTypeEqualTo("1");
			List<CommentPic> appendCommentPics = commentPicMapper.selectByExample(e);
			model.addAttribute("appendCommentPics", appendCommentPics);
		}
		ShopScoreExample sse = new ShopScoreExample();
		ShopScoreExample.Criteria ssec = sse.createCriteria();
		ssec.andDelFlagEqualTo("0");
		ssec.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		ssec.andSubOrderIdEqualTo(comment.getSubOrderId());
		List<ShopScore> shopScores = shopScoreService.selectByExample(sse);
		if(shopScores!=null && shopScores.size()>0){
			model.addAttribute("mchtScoreStarWidth", shopScores.get(0).getMchtScore()*24);
			model.addAttribute("wuliuScoreStarWidth", shopScores.get(0).getWuliuScore()*24);
		}
		return "comment/commentView";
	}
	
	/**
	 * 商家回复评论页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/comment/toReplyComment")
	public String toReplyComment(Model model,HttpServletRequest request) {
		String commentId = request.getParameter("id");
		model.addAttribute("commentId", commentId);
		return "comment/toReplyComment";
	}
	
	@RequestMapping("/comment/replyComment")
	@ResponseBody
	public ResponseMsg replyComment(HttpServletRequest request) {
		String id = request.getParameter("commentId");
		String mchtReply = request.getParameter("mchtReply");
		Comment comment = commentService.selectByPrimaryKey(Integer.parseInt(id));
		if(comment == null){
			return new ResponseMsg(ResponseMsg.ERROR, "评论不存在，或已被删除");
		}
		comment.setMchtReply(mchtReply);
		comment.setMchtReplyDate(new Date());
		comment.setUpdateBy(this.getSessionUserInfo(request).getId());
		comment.setUpdateDate(new Date());
		commentService.updateByPrimaryKeySelective(comment);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	@RequestMapping("/comment/deleteReply")
	@ResponseBody
	public ResponseMsg deleteReply(HttpServletRequest request) {
		String id = request.getParameter("id");
		Comment comment = commentService.selectByPrimaryKey(Integer.parseInt(id));
		if(comment == null){
			return new ResponseMsg(ResponseMsg.ERROR, "已删除回复，无需再次删除");
		}
		commentService.deleteReply(Integer.parseInt(id));
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
}
