package com.jf.service;

import com.google.common.collect.Maps;
import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.constant.StateConst;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ProductCustomMapper;
import com.jf.dao.SourceNicheCustomMapper;
import com.jf.dao.SourceNicheMapper;
import com.jf.entity.CouponCustom;
import com.jf.entity.MemberSupportSourceLog;
import com.jf.entity.ProductCustom;
import com.jf.entity.ProductType;
import com.jf.entity.SourceNiche;
import com.jf.entity.SourceNicheExample;
import com.jf.entity.SourceProductType;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SourceNicheService extends BaseService<SourceNiche, SourceNicheExample> {
    private static Logger logger = LoggerFactory.getLogger(SourceNicheService.class);
    @Autowired
    private SourceNicheMapper sourceNicheMapper;
    @Autowired
    private SourceNicheCustomMapper sourceNicheCustomMapper;
    @Autowired
    private MemberSupportSourceLogService memberSupportSourceLogService;
    @Autowired
    private ProductCustomMapper productCustomMapper;

    @Autowired
    public void setSourceNicheMapper(SourceNicheMapper sourceNicheMapper) {
        this.setDao(sourceNicheMapper);
        this.sourceNicheMapper = sourceNicheMapper;
    }

    public void updateSourceNicheSupportQuantity(JSONObject reqPRM, JSONObject reqDataJson) {
        Integer sourceNicheId = reqDataJson.getInt("sourceNicheId");
        Integer memberId = reqDataJson.getInt("memberId");
        List<MemberSupportSourceLog> logs = memberSupportSourceLogService.findModesBySourceNicheId(memberId, sourceNicheId);
        if (CollectionUtils.isEmpty(logs)) {
            MemberSupportSourceLog log = new MemberSupportSourceLog();
            log.setMemberId(memberId);
            log.setSourceNicheId(sourceNicheId);
            log.setCreateDate(new Date());
            log.setDelFlag("0");
            memberSupportSourceLogService.insertSelective(log);
            sourceNicheCustomMapper.updateSourceNicheSupportQuantity(sourceNicheId);
        } else {
            throw new ArgException("您赞过啦，请勿重复操作哦~");
        }
    }

    public List<Map<String, Object>> getProductCouponList(Map<String, Object> paramMap) {
        List<Map<String, Object>> productCouponList = sourceNicheCustomMapper.getProductCouponList(paramMap);
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> productCoupon : productCouponList) {
            Map<String, Object> map = new HashMap<String, Object>();
            Integer grantQuantity = (Integer) productCoupon.get("grantQuantity") == null ? 0 : (Integer) productCoupon.get("grantQuantity");
            // 优惠券已领数量
            Integer recQuantity = (Integer) productCoupon.get("recQuantity") == null ? 0 : (Integer) productCoupon.get("recQuantity");
            // 优惠券 限领类型(1每人每天限领1张 2每人限领指定数量 3不限)
            String recLimitType = StringUtil.isEmpty((String) productCoupon.get("recLimitType")) ? "" : (String) productCoupon.get("recLimitType");
            // 优惠券 限领数量
            Integer recEach = (Integer) productCoupon.get("recEach") == null ? 0 : (Integer) productCoupon.get("recEach");
            //优惠券，判断是否已抢光
//			Coupon coupon = new Coupon();
//			coupon.setId((Integer)productCoupon.get("couponId"));
//			coupon.setGrantQuantity(grantQuantity);
//			coupon.setRecQuantity(recQuantity);
//			coupon.setRecLimitType(recLimitType);
//			coupon.setRecEach(recEach);
//			coupon.setRecBeginDate((Date)productCoupon.get("recBeginDate"));
//			Map<String, Object> msgMap = memberCouponService.getMemberIsReceiveCoupon(coupon, (String)paramMap.get("memberId"));
//			String msgType = "0";
//			msgType = msgMap.get("msgType").toString();
//			if(!"1".equals(msgType) && !"2".equals(msgType)){
//				if(coupon.getRecBeginDate().compareTo(new Date()) > 0){
//					msgType = "3";
//					map.put("recBeginDate", coupon.getRecBeginDate().getTime());
//				}else{
//					msgType = "0";
//				}
//			}
            String msgType = "0";
            if (grantQuantity > 0 && grantQuantity > recQuantity) {//商品券的限领类型为2
                String memberCouponIds = (String) productCoupon.get("memberCouponIds");
                if (!StringUtil.isEmpty(memberCouponIds)) {
                    // 限领类型(1每人每天限领1张 2每人限领指定数量 3不限)
                    if (recLimitType.equals("2")) {
                        if (memberCouponIds.split(",").length >= recEach) {
                            msgType = "1";
                        }
                    }
                }
            } else {
                msgType = "2";
            }
            map.put("recType", (String) productCoupon.get("recType"));
            map.put("msgType", msgType);
            map.put("noThreshold", "无门槛");
            String name = (String) productCoupon.get("name");
            if (name.length() > 5) {
                name = name.substring(0, 5) + "...";
            }
            map.put("name", name);
            map.put("couponId", productCoupon.get("couponId"));
            map.put("productId", productCoupon.get("productId"));
            map.put("pic", StringUtil.getPic((String) productCoupon.get("pic"), "M"));
            DecimalFormat df = new DecimalFormat();
            df.applyPattern("#.##");
            map.put("money", df.format((BigDecimal) productCoupon.get("money")));
            resultList.add(map);
        }
        return resultList;
    }

    public List<CouponCustom> getCouponListByRecBeginDate(Map<String, Object> paramMap) {
        return sourceNicheCustomMapper.getCouponListByRecBeginDate(paramMap);
    }

    public List<ProductType> getProductTypeList() {
        return sourceNicheCustomMapper.getProductTypeList();
    }

    public List<Map<String, Object>> getProductCouponListByProductTypeId(Map<String, Object> paramMap) {
        return sourceNicheCustomMapper.getProductCouponListByProductTypeId(paramMap);
    }

    public List<CouponCustom> getActivityAreaCouponList(Map<String, Object> map) {
        return sourceNicheCustomMapper.getActivityAreaCouponList(map);
    }

    public List<SourceProductType> getSourceProductTypeListByActivityAreaCoupon() {
        return sourceNicheCustomMapper.getSourceProductTypeListByActivityAreaCoupon();
    }

    public ProductCustom getLotteryProductInfo(int productIndex) {
        SourceNiche sourceNiche = getLotteryProduct(productIndex);
        return sourceNiche == null ? null : productCustomMapper.getProductInfo(sourceNiche.getLinkId());
    }

    /**
     * 获取积分转盘商品奖池的第 productIndex 件商品
     *
     * @param productIndex 从 1 开始
     */
    public SourceNiche getLotteryProduct(int productIndex) {
        SourceNicheExample example = new SourceNicheExample();
        example.createCriteria()
                .andTypeEqualTo("13") //积分转盘
                .andAuditStatusEqualTo("1")
                .andStockGreaterThan(0)
                .andStatusEqualTo("0")
                .andUpDateLessThanOrEqualTo(new Date())
                .andDelFlagEqualTo(StateConst.FALSE);
        example.setOrderByClause("ifnull(seq_no,999) asc");
        example.setLimitStart(productIndex - 1);
        example.setLimitSize(1);
        List<SourceNiche> list = this.selectByExample(example);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    public boolean decreaseLotteryProductStock(Integer id, Integer originStock) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("id", id);
        params.put("originStock", originStock);
        return sourceNicheCustomMapper.decreaseLotteryProductStock(params) > 0;
    }

    public List<SourceNiche> findLotteryProduct() {
        SourceNicheExample example = new SourceNicheExample();
        example.createCriteria()
                .andTypeEqualTo("13") //积分转盘
                .andAuditStatusEqualTo("1")
                .andStockGreaterThan(0)
                .andStatusEqualTo("0")
                .andDelFlagEqualTo(StateConst.FALSE);
        example.setOrderByClause("ifnull(seq_no,999) asc");
        return this.selectByExample(example);
    }

    @Transactional
    public void removeLotteryProduct(SourceNiche sourceNiche) {
        sourceNiche.setStatus("1");
        sourceNiche.setUpdateDate(new Date());
        this.updateByPrimaryKeySelective(sourceNiche);
    }
}
