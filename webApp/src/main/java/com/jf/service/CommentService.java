package com.jf.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import com.jf.common.utils.comment.ProductCommentEnum;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.base.ArgException;
import com.jf.common.constant.Const;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.CommentCustomMapper;
import com.jf.dao.CommentMapper;
import com.jf.entity.Comment;
import com.jf.entity.CommentCustom;
import com.jf.entity.CommentExample;
import com.jf.entity.CommentPic;
import com.jf.entity.CommentPicExample;
import com.jf.entity.CustomerServiceOrder;
import com.jf.entity.CustomerServiceOrderExample;
import com.jf.entity.OrderDtl;
import com.jf.entity.OrderDtlExample;
import com.jf.entity.ShopScore;
import com.jf.entity.ShopScoreExample;
import com.jf.entity.SubOrder;
import com.jf.entity.SubOrderExample;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
@Transactional
public class CommentService extends BaseService<Comment,CommentExample> {
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private CommentCustomMapper commentCustomMapper;
	@Autowired
	private OrderDtlService orderDtlService;
	@Autowired
	private SubOrderService subOrderService;
	@Autowired
	private CommentPicService commentPicService;
	@Autowired
	private ShopScoreService shopScoreService;
	@Autowired
	private CommentDrawSettingService commentDrawSettingService;
	@Autowired
	private CustomerServiceOrderService customerServiceOrderService;
	@Autowired
	private ProductItemService productItemService;
	@Autowired
	private ViolateWordService violateWordService;
	
	@Autowired
	public void setCommentMapper(CommentMapper commentMapper) {
		super.setDao(commentMapper);
		this.commentMapper = commentMapper;
	}

	public Map<String,Object> addMemberComment(JSONObject reqDataJson,Integer memberId) {
		Map<String,Object> map = new HashMap<String,Object>();
		JSONArray dataList = reqDataJson.getJSONArray("dataList");
		Integer subOrderId = reqDataJson.getInt("subOrderId");
		//1评价2追加评价
		String type = reqDataJson.getString("type");
		SubOrder subOrder = subOrderService.selectByPrimaryKey(subOrderId);
		String status = subOrder.getStatus();
		String isComment = subOrder.getIsComment();
		Date receiptDate = subOrder.getReceiptDate();
		String isAppendComment = "1";
		Date date = new Date();
		//订单明细是否存在售后单据
		Map<Integer, String> isExistCustomerServiceOrderMap = new HashMap<Integer, String>();
		if(type.equals("1")){
			//判断是否评价过，或者是否订单已完成
			if(!Const.ORDER_STATUS_RECEIVED_GOODS.equals(status) && !Const.ORDER_STATUS_SUCCESS.equals(status)){
				//只有在已收货和完成收货才能评价
				throw new ArgException("订单未确认收货，不能评价");
			}else if("1".equals(isComment)){
				throw new ArgException("您已评价过,请勿重复评价");
			}else if(!reqDataJson.containsKey("mchtScore") || StringUtil.isBlank(reqDataJson.getString("mchtScore"))){
				throw new ArgException("请为卖家服务评分");
			}else if(!reqDataJson.containsKey("wuliuScore") || StringUtil.isBlank(reqDataJson.getString("wuliuScore"))){
				throw new ArgException("请为物流服务评分");
			}
			Integer mchtScore = reqDataJson.getInt("mchtScore");
			Integer wuliuScore = reqDataJson.getInt("wuliuScore");
			//店铺评分
			ShopScore shopScore = new ShopScore();
			shopScore.setSubOrderId(subOrderId);
			shopScore.setMchtId(subOrder.getMchtId());
			shopScore.setMchtScore(mchtScore);
			shopScore.setWuliuScore(wuliuScore);
			shopScore.setCommentSource("1");//1用户2系统
			shopScore.setCreateBy(memberId);
			shopScore.setCreateDate(date);
			shopScore.setDelFlag("0");
			shopScoreService.insertSelective(shopScore);
			//更新子订单为已评价
			subOrder.setIsComment("1");
			subOrderService.updateByPrimaryKeySelective(subOrder);
			isAppendComment = "0";
			//查看子订单所有的售后单据
			CustomerServiceOrderExample customerServiceOrderExample = new CustomerServiceOrderExample();
			customerServiceOrderExample.createCriteria().andSubOrderIdEqualTo(subOrderId).andDelFlagEqualTo("0");
			List<CustomerServiceOrder> customerServiceOrders = customerServiceOrderService.selectByExample(customerServiceOrderExample);
			if(CollectionUtils.isNotEmpty(customerServiceOrders)){
				for (CustomerServiceOrder customerServiceOrder : customerServiceOrders) {
					isExistCustomerServiceOrderMap.put(customerServiceOrder.getOrderDtlId(), "0");
				}
			}
		}
		for (Object object : dataList) {
			JSONObject jo = JSONObject.fromObject(object);
			int commentWeight = 0;//评价权重
			String content = "";
			String hideContent = "";
			String pics = "";
			Integer productScore = null;
			if(!jo.containsKey("orderDtlId") || StringUtil.isBlank(jo.getString("orderDtlId"))){
				throw new ArgException("请选择需要评价的订单");
			}
			if(jo.containsKey("productScore") && !StringUtil.isBlank(jo.getString("productScore"))){
				productScore = jo.getInt("productScore");
				if("1".equals(type)){
					int score = getProductScore(productScore);
					commentWeight += score;
				}
			}
			if(jo.containsKey("pics") && !StringUtil.isBlank(jo.getString("pics"))){
				pics = jo.getString("pics");
				if("1".equals(type)){
					int picLength = pics.split(",").length;
					int score = getPicsScore(picLength);
					commentWeight += score;
				}
			}
			if(jo.containsKey("content") && !StringUtil.isBlank(jo.getString("content"))){
				content = StringUtil.filterEmoji(jo.getString("content"));
				hideContent = content;
				content = violateWordService.getProhibitedWordsFilter(content);
				if("1".equals(type)){
					int score = getContentScore(content.length());
					commentWeight += score;
				}else if("2".equals(type)){
					int score = getAppendContentScore(content.length());
					commentWeight += score;
				}
			}else{
				content = ProductCommentEnum.getCommentDesc(productScore);
			}
			Integer orderDtlId = jo.getInt("orderDtlId");
			String isShow = isExistCustomerServiceOrderMap.get(orderDtlId);//0隐藏1展示
			if(StringUtil.isBlank(isShow)){
				isShow = "1";
			}
			if(type.equals("2")){
				//判断是否评价过，或者是否订单已完成
				if(!"1".equals(isComment)){
					throw new ArgException("还未评价不能追评");
				}else if(receiptDate != null && DateUtil.addDay(receiptDate, 30).compareTo(date) < 0){
					throw new ArgException("确认收货30后不能再次追评   ");
				}else{
					CommentExample commentExample = new CommentExample();
					commentExample.createCriteria().andOrderDtlIdEqualTo(orderDtlId).andIsAppendCommentEqualTo("1").andDelFlagEqualTo("0");
					int commentCount = countByExample(commentExample);
					if(commentCount > 0){
						throw new ArgException("每只商品只能追评一次");
					}
				}
				//追评增加主评价单分数
				if(commentWeight > 0){
					CommentExample commentExample1 = new CommentExample();
					commentExample1.createCriteria().andOrderDtlIdEqualTo(orderDtlId).andIsAppendCommentEqualTo("0").andDelFlagEqualTo("0");
					List<Comment> comments = selectByExample(commentExample1);
					Comment c = comments.get(0);
					int cw = c.getCommentWeight() == null ? 0 : c.getCommentWeight();
					c.setCommentWeight(cw+commentWeight);
					updateByPrimaryKeySelective(c);
				}
			}
			OrderDtl orderDtl = orderDtlService.selectByPrimaryKey(orderDtlId);
			Comment comment = new Comment();
			if("1".equals(type)){
				comment.setCommentWeight(commentWeight);
			}
			comment.setIsShow(isShow);
			comment.setSubOrderId(subOrderId);
			comment.setOrderDtlId(orderDtlId);
			comment.setProductId(orderDtl.getProductId());
			comment.setMemberId(memberId);
			comment.setMchtId(subOrder.getMchtId());
			comment.setIsAppendComment(isAppendComment);
			comment.setProductScore(productScore);
			comment.setContent(content);
			comment.setHideContent(hideContent);
			comment.setIsDraw("1");//评价好默认已抽奖，效果就是没有去抽奖就没有机会再次去抽取
			comment.setCommentSource("1");//1用户2系统
			comment.setCreateBy(memberId);
			comment.setCreateDate(date);
			comment.setDelFlag("0");
			insertSelective(comment);
			//图片
			if(!StringUtil.isBlank(pics)){
				String[] picList = pics.split(",");
				for (String pic : picList) {
					pic = StringUtil.replace(pic,"xgbuy.cc");
					CommentPic commentPic = new CommentPic();
					commentPic.setCommentId(comment.getId());
					commentPic.setPic(pic);
					commentPic.setPicType("1");
					commentPic.setCreateBy(memberId);
					commentPic.setCreateDate(date);
					commentPic.setDelFlag("0");
					commentPicService.insertSelective(commentPic);
				}
			}
		}
		if(type.equals("1")){
			//完成评价的时候顺便计算出此次抽奖所中的积分
			map = commentDrawSettingService.getDrawIntegral(subOrder,memberId);
		}else{
			map.put("isHasActivity", false);
			map.put("integral", 0);
		}
		map.put("subOrderId", subOrderId);
		return map;
	}


	

	public Map<String, Object> getOrderCommentList(JSONObject reqDataJson) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		Integer subOrderId = reqDataJson.getInt("subOrderId");
		Integer mchtScore = 0;
		Integer wuliuScore = 0;
		//是否允许修改评论(0 可设置 1 允许 2 已修改)
		String isAllowModifyComment = "0";
		boolean modifyCommentButton = false;
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("subOrderId", subOrderId);
		List<CommentCustom> commentCustoms = commentCustomMapper.getOrderCommentList(paramsMap);
		if(CollectionUtils.isNotEmpty(commentCustoms)){
			//查看评分
			ShopScoreExample shopScoreExample = new ShopScoreExample();
			shopScoreExample.createCriteria().andDelFlagEqualTo("0").andSubOrderIdEqualTo(subOrderId);
			List<ShopScore> shopScores = shopScoreService.selectByExample(shopScoreExample);
			mchtScore = shopScores.get(0).getMchtScore();
			wuliuScore = shopScores.get(0).getWuliuScore();
			isAllowModifyComment = commentCustoms.get(0).getIsAllowModifyComment();
			for (CommentCustom commentCustom : commentCustoms) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				List<Map<String, Object>> appendDataList = new ArrayList<Map<String, Object>>();
				Integer commentId = commentCustom.getId();
				Integer orderDtlId = commentCustom.getOrderDtlId();
				String content = commentCustom.getContent();
				String skuPic = commentCustom.getSkuPic();
				String productPropDesc = commentCustom.getProductPropDesc();
				Integer productScore = commentCustom.getProductScore();
				String mchtReply = commentCustom.getMchtReply() == null ? "" : commentCustom.getMchtReply();
				//评价创建时间
				Date createDate = commentCustom.getCreateDate();
				productPropDesc = DateUtil.getFormatDate(createDate, "yyyy-MM-dd")+" "+productPropDesc;
				//获取评价图片
				List<String> commentPics = commentPicService.getCommentPics(commentId);
				//查询追评
				CommentExample commentExample = new CommentExample();
				commentExample.createCriteria().andOrderDtlIdEqualTo(orderDtlId).andIsAppendCommentEqualTo("1").andDelFlagEqualTo("0");
				List<Comment> comments = selectByExample(commentExample);
				if(CollectionUtils.isNotEmpty(comments)){
					for (Comment comment : comments) {
						Map<String, Object> appendCommentMap = new HashMap<String, Object>();
						List<String> appendCommentPics = commentPicService.getCommentPics(comment.getId());
						int appendCommentDay = (int) ((comment.getCreateDate().getTime()-createDate.getTime())/(24*60*60*1000)); 
						String appendCommentDayStr = "";
						if(appendCommentDay <= 0){
							appendCommentDayStr = "当天追评";
						}else{
							appendCommentDayStr = appendCommentDay+"天后追评";
						}
						appendCommentMap.put("appendCommentId", comment.getId());
						appendCommentMap.put("appendContent", comment.getContent());
						appendCommentMap.put("appendCommentDay", appendCommentDayStr);
						appendCommentMap.put("appendCommentPics", appendCommentPics);
						appendDataList.add(appendCommentMap);
					}
				}
				dataMap.put("commentId", commentId);
				dataMap.put("orderDtlId", orderDtlId);
				dataMap.put("content", content);
				dataMap.put("skuPic", StringUtil.getPic(skuPic, "S"));
				dataMap.put("productPropDesc", productPropDesc);
				dataMap.put("productScore", productScore);
				dataMap.put("commentPics", commentPics);
				dataMap.put("appendDataList", appendDataList);
				dataMap.put("mchtReply", mchtReply);
				dataList.add(dataMap);
			}
		}
		if("1".equals(isAllowModifyComment)){
			modifyCommentButton = true;
		}
		map.put("modifyCommentButton", modifyCommentButton);
		map.put("dataList", dataList);
		map.put("mchtScore", mchtScore);
		map.put("wuliuScore", wuliuScore);
		return map;
	}

	public Map<String, Object> getCanCommentOrderDtl(JSONObject reqDataJson) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		Integer subOrderId = reqDataJson.getInt("subOrderId");
		OrderDtlExample orderDtlExample = new OrderDtlExample();
		orderDtlExample.createCriteria().andSubOrderIdEqualTo(subOrderId).andDelFlagEqualTo("0");
		List<OrderDtl> orderDtls = orderDtlService.selectByExample(orderDtlExample);
		if(CollectionUtils.isNotEmpty(orderDtls)){
			for (OrderDtl orderDtl : orderDtls) {
				Integer orderDtlId = orderDtl.getId();
				String skuPic = orderDtl.getSkuPic();
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("orderDtlId", orderDtlId);
				dataMap.put("skuPic", StringUtil.getPic(skuPic, "S"));
				dataList.add(dataMap);
			}
		}
		map.put("dataList", dataList);
		map.put("subOrderId", subOrderId);
		return map;
	}

	public Map<String, Object> getAppendCommentProduct(JSONObject reqDataJson) {
		Map<String,Object> map = new HashMap<String,Object>();
		Integer orderDtlId = reqDataJson.getInt("orderDtlId");
		OrderDtl orderDtl = orderDtlService.selectByPrimaryKey(orderDtlId);
		String skuPic = orderDtl.getSkuPic();
		String productName = orderDtl.getProductName();
		String content = "此用户没有填写评价！";
		Date createDate = new Date();
		//获取sku描述
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("productItemId", orderDtl.getProductItemId());
		paramsMap.put("mark", "-");
		String productPropDesc = productItemService.getProductItemPropDesc(paramsMap);
		//获取评价图片
		List<String> commentPics = new ArrayList<String>();
		//获取评价信息
		CommentExample commentExample = new CommentExample();
		commentExample.createCriteria().andOrderDtlIdEqualTo(orderDtlId).andIsAppendCommentEqualTo("0").andDelFlagEqualTo("0");
		List<Comment> comments = selectByExample(commentExample);
		if(CollectionUtils.isNotEmpty(comments)){
			content = comments.get(0).getContent();
			createDate = comments.get(0).getCreateDate();
			commentPics = commentPicService.getCommentPics(comments.get(0).getId());
		}
		
		map.put("commentPics", commentPics);
		map.put("subOrderId", orderDtl.getSubOrderId());
		map.put("orderDtlId", orderDtlId);
		map.put("skuPic", StringUtil.getPic(skuPic, "S"));
		map.put("productName", productName);
		map.put("content", content);
		map.put("createDate", DateUtil.getFormatDate(createDate, "yyyy-MM-dd"));
		map.put("productPropDesc", productPropDesc);
		return map;
	}
	
	/**
	 * 获取商品的评价数据信息
	 */
	public Map<String, Object> getUserProductAllComment(Integer productId, String type,Integer currentPage,Integer pageSize,List<Integer> commentIdList) {
		//type 1：商品详情（只展示一条评价）  2：展示该商品的分页商品
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("productId", productId);
		paramsMap.put("pageSize", pageSize);
		if(CollectionUtils.isNotEmpty(commentIdList) && currentPage != 0){
			paramsMap.put("commentIdList", commentIdList);
			//这边-1，是因为第一页的时候是用商品得分排序的,第二页要根据创建时间来排序，所以页数要从0开始计算
			paramsMap.put("currentPage", (currentPage-1) * pageSize);
		}else{
			paramsMap.put("currentPage", currentPage * pageSize);
			commentIdList.clear();
		}
		List<CommentCustom> commentCustoms = commentCustomMapper.getUserProductAllComment(paramsMap);
		if(CollectionUtils.isNotEmpty(commentCustoms)){
			for (CommentCustom commentCustom : commentCustoms) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				List<Map<String, Object>> appendDataList = new ArrayList<Map<String, Object>>();
				List<String> commentPics = new ArrayList<String>();
				String mchtReply = "";
				Integer commentId = commentCustom.getId();
				Integer orderDtlId = commentCustom.getOrderDtlId();
				String content = commentCustom.getContent();
				String memberPic = commentCustom.getMemberPic();
				String memberNick = StringUtil.getNickStar(commentCustom.getMemberNick(), null);
				if(StringUtil.isBlank(memberNick)){
					memberNick = "匿名用户";
				}
				String productPropDesc = commentCustom.getProductPropDesc();
				//商品得分
				Integer productScore = commentCustom.getProductScore();
				//评价创建时间
				Date createDate = commentCustom.getCreateDate();
				if(!"1".equals(type)){//商品详情进来的不需要要查询一下数据
					mchtReply = commentCustom.getMchtReply() == null ? "" : commentCustom.getMchtReply();
					productPropDesc = DateUtil.getFormatDate(createDate, "yyyy-MM-dd")+" "+productPropDesc;
					if(currentPage == 0){
						commentIdList.add(commentId);
					}
					//获取评价图片
					commentPics = commentPicService.getCommentPics(commentId);
					//查询追评
					CommentExample commentExample = new CommentExample();
					commentExample.createCriteria().andOrderDtlIdEqualTo(orderDtlId).andProductIdEqualTo(productId).andIsAppendCommentEqualTo("1").andDelFlagEqualTo("0");
					List<Comment> comments = selectByExample(commentExample);
					if(CollectionUtils.isNotEmpty(comments)){
						for (Comment comment : comments) {
							Map<String, Object> appendCommentMap = new HashMap<String, Object>();
							List<String> appendCommentPics = commentPicService.getCommentPics(comment.getId());
							int appendCommentDay = (int) ((comment.getCreateDate().getTime()-createDate.getTime())/(24*60*60*1000)); 
							String appendCommentDayStr = "";
							if(appendCommentDay <= 0){
								appendCommentDayStr = "当天追评";
							}else{
								appendCommentDayStr = appendCommentDay+"天后追评";
							}
							appendCommentMap.put("appendCommentId", comment.getId());
							appendCommentMap.put("appendContent", comment.getContent());
							appendCommentMap.put("appendCommentDay", appendCommentDayStr);
							appendCommentMap.put("appendCommentPics", appendCommentPics);
							appendDataList.add(appendCommentMap);
						}
					}
				}
				dataMap.put("commentId", commentId);
				dataMap.put("content", content);
				dataMap.put("memberPic", StringUtil.getPic(memberPic, ""));
				dataMap.put("memberNick", memberNick);
				dataMap.put("productPropDesc", productPropDesc);
				dataMap.put("commentPics", commentPics);
				dataMap.put("appendDataList", appendDataList);
				dataMap.put("productScore", productScore);
				dataMap.put("mchtReply", mchtReply);
				dataList.add(dataMap);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataList", dataList);
		//当第一页的时候把第一页的评价id传给前端，前端翻页的时候过滤第一页的数据
		//第1页  呈现前10条好评（4星、5星）的评价
		//第2页 开始按时间顺序，最新排最上面
		//前面展示的10条数据，不要重复展示（去重）
		map.put("commentIdList", commentIdList);
		return map;
	}
	
	/**
	 * 获取商品宝贝的总评价数
	 */
	public int getProductTotalCommentCount(Map<String, Object> commentParamsMap) {
		CommentExample commentExample = new CommentExample();
		CommentExample.Criteria criteria = commentExample.createCriteria();
		criteria.andDelFlagEqualTo("0").andIsAppendCommentEqualTo("0").andIsShowEqualTo("1");
		if(commentParamsMap.containsKey("productId") && !StringUtil.isBlank(commentParamsMap.get("productId").toString())){
			criteria.andProductIdEqualTo(Integer.parseInt(commentParamsMap.get("productId").toString()));
		}else if(commentParamsMap.containsKey("mchtId") && !StringUtil.isBlank(commentParamsMap.get("mchtId").toString())){
			criteria.andMchtIdEqualTo(Integer.parseInt(commentParamsMap.get("mchtId").toString()));
		}
		if(commentParamsMap.containsKey("productScore") && !StringUtil.isBlank(commentParamsMap.get("productScore").toString())){
			criteria.andProductScoreGreaterThanOrEqualTo(Integer.parseInt(commentParamsMap.get("productScore").toString()));
		}
		int count = countByExample(commentExample);
		return count;
	}
	
	/**
	 * 获取商品得分，卖家得分，物流得分
	 */
	public Map<String, BigDecimal> getShopAvgScore(Integer productId, Integer mchtId) {
		BigDecimal avgScore = new BigDecimal("4.00");
		BigDecimal five = new BigDecimal("5.00");
		BigDecimal zero = new BigDecimal("0");
		BigDecimal productAvgScore = five;
		BigDecimal mchtAvgScore = five;
		BigDecimal wuliuAvgScore = five;
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("productId", productId);
		paramsMap.put("mchtId", mchtId);
		List<CommentCustom> customs = commentCustomMapper.getShopAvgScore(paramsMap);
		if(CollectionUtils.isNotEmpty(customs)){
			CommentCustom custom = customs.get(0);
			mchtAvgScore = custom.getMchtAvgScore().compareTo(zero) == 0 ? five : custom.getMchtAvgScore().setScale(2, BigDecimal.ROUND_DOWN);
			wuliuAvgScore = custom.getWuliuAvgScore().compareTo(zero) == 0 ? five : custom.getWuliuAvgScore().setScale(2, BigDecimal.ROUND_DOWN);
			productAvgScore = custom.getProductAvgScore().compareTo(zero) == 0 ? five : custom.getProductAvgScore().setScale(2, BigDecimal.ROUND_DOWN);
			if(productAvgScore.compareTo(avgScore) < 0){
				productAvgScore = avgScore;
			}
			if(mchtAvgScore.compareTo(avgScore) < 0){
				mchtAvgScore = avgScore;
			}
			if(wuliuAvgScore.compareTo(avgScore) < 0){
				wuliuAvgScore = avgScore;
			}
		}
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		map.put("productAvgScore", productAvgScore);
		map.put("mchtAvgScore", mchtAvgScore);
		map.put("wuliuAvgScore", wuliuAvgScore);
		return map;
	}

	public void updateOrderComment(JSONObject reqDataJson, Integer memberId) {
		JSONArray dataList = reqDataJson.getJSONArray("dataList");
		Integer subOrderId = reqDataJson.getInt("subOrderId");
		Integer mchtScore = reqDataJson.getInt("mchtScore");
		Integer wuliuScore = reqDataJson.getInt("wuliuScore");
		Date currentDate = new Date();
		SubOrderExample orderExample = new SubOrderExample();
		orderExample.createCriteria().andIdEqualTo(subOrderId).andIsAllowModifyCommentEqualTo("1").andIsCommentEqualTo("1").andDelFlagEqualTo("0");
		SubOrder subOrder = new SubOrder();
		subOrder.setIsAllowModifyComment("2");
		subOrder.setUpdateDate(currentDate);
		int updateSubOrderCount = subOrderService.updateByExampleSelective(subOrder, orderExample);
		if(updateSubOrderCount <= 0){
			throw new ArgException("没有可修改的评价单据");
		}
		//修改店铺分数
		ShopScoreExample shopScoreExample = new ShopScoreExample();
		shopScoreExample.createCriteria().andSubOrderIdEqualTo(subOrderId).andDelFlagEqualTo("0");
		ShopScore shopScore = new ShopScore();
		shopScore.setMchtScore(mchtScore);
		shopScore.setWuliuScore(wuliuScore);
		shopScore.setUpdateBy(memberId);
		shopScore.setUpdateDate(currentDate);
		int shopScoreUpdateCount = shopScoreService.updateByExampleSelective(shopScore, shopScoreExample);
		if(shopScoreUpdateCount <= 0){
			throw new ArgException("没有可修改的评价单据");
		}
		for (Object object : dataList) {
			JSONObject jo = JSONObject.fromObject(object);
			Integer orderDtlId = jo.getInt("orderDtlId");
			int commentWeight = 0;//评价权重
			Integer productScore = 0;
			String content = "";
			String pics = "";
			if(jo.containsKey("productScore") && !StringUtil.isBlank(jo.getString("productScore"))){
				productScore = jo.getInt("productScore");
				int score = getProductScore(productScore);
				commentWeight += score;
			}
			if(jo.containsKey("content") && !StringUtil.isBlank(jo.getString("content"))){
				content = StringUtil.filterEmoji(jo.getString("content"));
				content = violateWordService.getProhibitedWordsFilter(content);
				int score = getContentScore(content.length());
				commentWeight += score;
			}
			if(jo.containsKey("pics") && !StringUtil.isBlank(jo.getString("pics"))){
				pics = jo.getString("pics");
				int picLength = pics.split(",").length;
				int score = getPicsScore(picLength);
				commentWeight += score;
			}
			CommentExample commentExample = new CommentExample();
			commentExample.createCriteria().andSubOrderIdEqualTo(subOrderId).andOrderDtlIdEqualTo(orderDtlId).andIsAppendCommentEqualTo("0").andDelFlagEqualTo("0");
			List<Comment> comments = selectByExample(commentExample);
			if(CollectionUtils.isNotEmpty(comments)){
				//查询追评文本分数
				CommentExample commentExample1 = new CommentExample();
				commentExample1.createCriteria().andSubOrderIdEqualTo(subOrderId).andOrderDtlIdEqualTo(orderDtlId).andIsAppendCommentEqualTo("1").andDelFlagEqualTo("0");
				List<Comment> cs = selectByExample(commentExample1);
				if(CollectionUtils.isNotEmpty(cs)){
					int score = getAppendContentScore(cs.get(0).getContent().length());
					commentWeight += score;
				}
				Comment comment = comments.get(0);
				comment.setContent(content);
				comment.setProductScore(productScore);
				comment.setCommentWeight(commentWeight);
				comment.setUpdateBy(memberId);
				comment.setUpdateDate(currentDate);
				updateByPrimaryKeySelective(comment);
				
				//图片设置为删除标志1
				CommentPicExample commentPicExampleUpdateAll = new CommentPicExample();
				commentPicExampleUpdateAll.createCriteria().andCommentIdEqualTo(comment.getId()).andCreateByEqualTo(memberId);
				CommentPic commentPicUpdateAll = new CommentPic();
				commentPicUpdateAll.setDelFlag("1");
				commentPicService.updateByExampleSelective(commentPicUpdateAll, commentPicExampleUpdateAll);
				if(!StringUtil.isBlank(pics)){
					String[] picList = pics.split(",");
					for (String customPic : picList) {
						customPic = StringUtil.replace(customPic,"xgbuy.cc");
						
						//根据图片url查找是否存在,存在就把删除标志设置为0，不存在则 循环走下一次
						CommentPicExample commentPicExampleUpdate = new CommentPicExample();
						commentPicExampleUpdate.createCriteria().andPicEqualTo(customPic).andCommentIdEqualTo(comment.getId()).andCreateByEqualTo(memberId);
						CommentPic commentPicUpdate = new CommentPic();
						commentPicUpdate.setDelFlag("0");
						int updateCommentPicCount = commentPicService.updateByExampleSelective(commentPicUpdate, commentPicExampleUpdate);
						if(updateCommentPicCount > 0){
							continue;
						}
						//不存在则做插入操作
						CommentPic commentPic = new CommentPic();
						commentPic.setCommentId(comment.getId());
						commentPic.setPic(customPic);
						commentPic.setPicType("1");
						commentPic.setCreateBy(memberId);
						commentPic.setCreateDate(currentDate);
						commentPic.setDelFlag("0");
						commentPicService.insertSelective(commentPic);
					}
				}
			}else{
				throw new ArgException("没有可修改的评价单据");
			}
		}
	}
	
	
	/**
	 * 获取评价商品评分
	 * @param productScore
	 * @return
	 */
	private int getProductScore(Integer productScore) {
		int score = 0;
		if(productScore == 1){
			score = 0;
		}else if(productScore == 2){
			score = 10;
		}else if(productScore == 3){
			score = 25;
		}else if(productScore == 4){
			score = 40;
		}else if(productScore == 5){
			score = 45;
		}
		return score;
	}
	/**
	 * 获取追评文本评分
	 * @param contentLength
	 * @return
	 */
	private int getAppendContentScore(int contentLength) {
		int score = 0;
		if(contentLength >= 1 && contentLength <= 5){
			score = 1;
		}else if(contentLength >= 6 && contentLength <= 30){
			score = 2;
		}else if(contentLength >= 31 && contentLength <= 200){
			score = 3;
		}else if(contentLength >= 201){
			score = 1;
		}
		return score;
	}
	/**
	 * 获取评价文本评分
	 * @param contentLength
	 * @return
	 */
	private int getContentScore(int contentLength) {
		int score = 0;
		if(contentLength >= 1 && contentLength <= 5){
			score = 3;
		}else if(contentLength >= 6 && contentLength <= 30){
			score = 4;
		}else if(contentLength >= 31 && contentLength <= 200){
			score = 5;
		}else if(contentLength >= 201){
			score = 3;
		}
		return score;
	}
	/**
	 * 获取评价图片评分
	 * @param picLength
	 * @return
	 */
	private int getPicsScore(int picLength) {
		int score = 0;
		if(picLength >=1 && picLength <=2){
			score = 4;
		}else if(picLength >=3 && picLength <=6){
			score = 5;
		}else if(picLength >=6){
			score = 6;
		}
		return score;
	}

}
