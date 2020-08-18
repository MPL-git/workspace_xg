package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public CommentExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitSize(Integer limitSize) {
        this.limitSize=limitSize;
    }

    public Integer getLimitSize() {
        return limitSize;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMchtIdIsNull() {
            addCriterion("mcht_id is null");
            return (Criteria) this;
        }

        public Criteria andMchtIdIsNotNull() {
            addCriterion("mcht_id is not null");
            return (Criteria) this;
        }

        public Criteria andMchtIdEqualTo(Integer value) {
            addCriterion("mcht_id =", value, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdNotEqualTo(Integer value) {
            addCriterion("mcht_id <>", value, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdGreaterThan(Integer value) {
            addCriterion("mcht_id >", value, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mcht_id >=", value, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdLessThan(Integer value) {
            addCriterion("mcht_id <", value, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdLessThanOrEqualTo(Integer value) {
            addCriterion("mcht_id <=", value, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdIn(List<Integer> values) {
            addCriterion("mcht_id in", values, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdNotIn(List<Integer> values) {
            addCriterion("mcht_id not in", values, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdBetween(Integer value1, Integer value2) {
            addCriterion("mcht_id between", value1, value2, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mcht_id not between", value1, value2, "mchtId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdIsNull() {
            addCriterion("sub_order_id is null");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdIsNotNull() {
            addCriterion("sub_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdEqualTo(Integer value) {
            addCriterion("sub_order_id =", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdNotEqualTo(Integer value) {
            addCriterion("sub_order_id <>", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdGreaterThan(Integer value) {
            addCriterion("sub_order_id >", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sub_order_id >=", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdLessThan(Integer value) {
            addCriterion("sub_order_id <", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("sub_order_id <=", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdIn(List<Integer> values) {
            addCriterion("sub_order_id in", values, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdNotIn(List<Integer> values) {
            addCriterion("sub_order_id not in", values, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("sub_order_id between", value1, value2, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sub_order_id not between", value1, value2, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdIsNull() {
            addCriterion("order_dtl_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdIsNotNull() {
            addCriterion("order_dtl_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdEqualTo(Integer value) {
            addCriterion("order_dtl_id =", value, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdNotEqualTo(Integer value) {
            addCriterion("order_dtl_id <>", value, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdGreaterThan(Integer value) {
            addCriterion("order_dtl_id >", value, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_dtl_id >=", value, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdLessThan(Integer value) {
            addCriterion("order_dtl_id <", value, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_dtl_id <=", value, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdIn(List<Integer> values) {
            addCriterion("order_dtl_id in", values, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdNotIn(List<Integer> values) {
            addCriterion("order_dtl_id not in", values, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdBetween(Integer value1, Integer value2) {
            addCriterion("order_dtl_id between", value1, value2, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_dtl_id not between", value1, value2, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Integer value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Integer value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Integer value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Integer value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Integer value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Integer> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Integer> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Integer value1, Integer value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNull() {
            addCriterion("member_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("member_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(Integer value) {
            addCriterion("member_id =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(Integer value) {
            addCriterion("member_id <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(Integer value) {
            addCriterion("member_id >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("member_id >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(Integer value) {
            addCriterion("member_id <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(Integer value) {
            addCriterion("member_id <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<Integer> values) {
            addCriterion("member_id in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<Integer> values) {
            addCriterion("member_id not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(Integer value1, Integer value2) {
            addCriterion("member_id between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(Integer value1, Integer value2) {
            addCriterion("member_id not between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andProductPropDescIsNull() {
            addCriterion("product_prop_desc is null");
            return (Criteria) this;
        }

        public Criteria andProductPropDescIsNotNull() {
            addCriterion("product_prop_desc is not null");
            return (Criteria) this;
        }

        public Criteria andProductPropDescEqualTo(String value) {
            addCriterion("product_prop_desc =", value, "productPropDesc");
            return (Criteria) this;
        }

        public Criteria andProductPropDescNotEqualTo(String value) {
            addCriterion("product_prop_desc <>", value, "productPropDesc");
            return (Criteria) this;
        }

        public Criteria andProductPropDescGreaterThan(String value) {
            addCriterion("product_prop_desc >", value, "productPropDesc");
            return (Criteria) this;
        }

        public Criteria andProductPropDescGreaterThanOrEqualTo(String value) {
            addCriterion("product_prop_desc >=", value, "productPropDesc");
            return (Criteria) this;
        }

        public Criteria andProductPropDescLessThan(String value) {
            addCriterion("product_prop_desc <", value, "productPropDesc");
            return (Criteria) this;
        }

        public Criteria andProductPropDescLessThanOrEqualTo(String value) {
            addCriterion("product_prop_desc <=", value, "productPropDesc");
            return (Criteria) this;
        }

        public Criteria andProductPropDescLike(String value) {
            addCriterion("product_prop_desc like", value, "productPropDesc");
            return (Criteria) this;
        }

        public Criteria andProductPropDescNotLike(String value) {
            addCriterion("product_prop_desc not like", value, "productPropDesc");
            return (Criteria) this;
        }

        public Criteria andProductPropDescIn(List<String> values) {
            addCriterion("product_prop_desc in", values, "productPropDesc");
            return (Criteria) this;
        }

        public Criteria andProductPropDescNotIn(List<String> values) {
            addCriterion("product_prop_desc not in", values, "productPropDesc");
            return (Criteria) this;
        }

        public Criteria andProductPropDescBetween(String value1, String value2) {
            addCriterion("product_prop_desc between", value1, value2, "productPropDesc");
            return (Criteria) this;
        }

        public Criteria andProductPropDescNotBetween(String value1, String value2) {
            addCriterion("product_prop_desc not between", value1, value2, "productPropDesc");
            return (Criteria) this;
        }

        public Criteria andIsAppendCommentIsNull() {
            addCriterion("is_append_comment is null");
            return (Criteria) this;
        }

        public Criteria andIsAppendCommentIsNotNull() {
            addCriterion("is_append_comment is not null");
            return (Criteria) this;
        }

        public Criteria andIsAppendCommentEqualTo(String value) {
            addCriterion("is_append_comment =", value, "isAppendComment");
            return (Criteria) this;
        }

        public Criteria andIsAppendCommentNotEqualTo(String value) {
            addCriterion("is_append_comment <>", value, "isAppendComment");
            return (Criteria) this;
        }

        public Criteria andIsAppendCommentGreaterThan(String value) {
            addCriterion("is_append_comment >", value, "isAppendComment");
            return (Criteria) this;
        }

        public Criteria andIsAppendCommentGreaterThanOrEqualTo(String value) {
            addCriterion("is_append_comment >=", value, "isAppendComment");
            return (Criteria) this;
        }

        public Criteria andIsAppendCommentLessThan(String value) {
            addCriterion("is_append_comment <", value, "isAppendComment");
            return (Criteria) this;
        }

        public Criteria andIsAppendCommentLessThanOrEqualTo(String value) {
            addCriterion("is_append_comment <=", value, "isAppendComment");
            return (Criteria) this;
        }

        public Criteria andIsAppendCommentLike(String value) {
            addCriterion("is_append_comment like", value, "isAppendComment");
            return (Criteria) this;
        }

        public Criteria andIsAppendCommentNotLike(String value) {
            addCriterion("is_append_comment not like", value, "isAppendComment");
            return (Criteria) this;
        }

        public Criteria andIsAppendCommentIn(List<String> values) {
            addCriterion("is_append_comment in", values, "isAppendComment");
            return (Criteria) this;
        }

        public Criteria andIsAppendCommentNotIn(List<String> values) {
            addCriterion("is_append_comment not in", values, "isAppendComment");
            return (Criteria) this;
        }

        public Criteria andIsAppendCommentBetween(String value1, String value2) {
            addCriterion("is_append_comment between", value1, value2, "isAppendComment");
            return (Criteria) this;
        }

        public Criteria andIsAppendCommentNotBetween(String value1, String value2) {
            addCriterion("is_append_comment not between", value1, value2, "isAppendComment");
            return (Criteria) this;
        }

        public Criteria andProductScoreIsNull() {
            addCriterion("product_score is null");
            return (Criteria) this;
        }

        public Criteria andProductScoreIsNotNull() {
            addCriterion("product_score is not null");
            return (Criteria) this;
        }

        public Criteria andProductScoreEqualTo(Integer value) {
            addCriterion("product_score =", value, "productScore");
            return (Criteria) this;
        }

        public Criteria andProductScoreNotEqualTo(Integer value) {
            addCriterion("product_score <>", value, "productScore");
            return (Criteria) this;
        }

        public Criteria andProductScoreGreaterThan(Integer value) {
            addCriterion("product_score >", value, "productScore");
            return (Criteria) this;
        }

        public Criteria andProductScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_score >=", value, "productScore");
            return (Criteria) this;
        }

        public Criteria andProductScoreLessThan(Integer value) {
            addCriterion("product_score <", value, "productScore");
            return (Criteria) this;
        }

        public Criteria andProductScoreLessThanOrEqualTo(Integer value) {
            addCriterion("product_score <=", value, "productScore");
            return (Criteria) this;
        }

        public Criteria andProductScoreIn(List<Integer> values) {
            addCriterion("product_score in", values, "productScore");
            return (Criteria) this;
        }

        public Criteria andProductScoreNotIn(List<Integer> values) {
            addCriterion("product_score not in", values, "productScore");
            return (Criteria) this;
        }

        public Criteria andProductScoreBetween(Integer value1, Integer value2) {
            addCriterion("product_score between", value1, value2, "productScore");
            return (Criteria) this;
        }

        public Criteria andProductScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("product_score not between", value1, value2, "productScore");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andHideContentIsNull() {
            addCriterion("hide_content is null");
            return (Criteria) this;
        }

        public Criteria andHideContentIsNotNull() {
            addCriterion("hide_content is not null");
            return (Criteria) this;
        }

        public Criteria andHideContentEqualTo(String value) {
            addCriterion("hide_content =", value, "hideContent");
            return (Criteria) this;
        }

        public Criteria andHideContentNotEqualTo(String value) {
            addCriterion("hide_content <>", value, "hideContent");
            return (Criteria) this;
        }

        public Criteria andHideContentGreaterThan(String value) {
            addCriterion("hide_content >", value, "hideContent");
            return (Criteria) this;
        }

        public Criteria andHideContentGreaterThanOrEqualTo(String value) {
            addCriterion("hide_content >=", value, "hideContent");
            return (Criteria) this;
        }

        public Criteria andHideContentLessThan(String value) {
            addCriterion("hide_content <", value, "hideContent");
            return (Criteria) this;
        }

        public Criteria andHideContentLessThanOrEqualTo(String value) {
            addCriterion("hide_content <=", value, "hideContent");
            return (Criteria) this;
        }

        public Criteria andHideContentLike(String value) {
            addCriterion("hide_content like", value, "hideContent");
            return (Criteria) this;
        }

        public Criteria andHideContentNotLike(String value) {
            addCriterion("hide_content not like", value, "hideContent");
            return (Criteria) this;
        }

        public Criteria andHideContentIn(List<String> values) {
            addCriterion("hide_content in", values, "hideContent");
            return (Criteria) this;
        }

        public Criteria andHideContentNotIn(List<String> values) {
            addCriterion("hide_content not in", values, "hideContent");
            return (Criteria) this;
        }

        public Criteria andHideContentBetween(String value1, String value2) {
            addCriterion("hide_content between", value1, value2, "hideContent");
            return (Criteria) this;
        }

        public Criteria andHideContentNotBetween(String value1, String value2) {
            addCriterion("hide_content not between", value1, value2, "hideContent");
            return (Criteria) this;
        }

        public Criteria andMchtReplyIsNull() {
            addCriterion("mcht_reply is null");
            return (Criteria) this;
        }

        public Criteria andMchtReplyIsNotNull() {
            addCriterion("mcht_reply is not null");
            return (Criteria) this;
        }

        public Criteria andMchtReplyEqualTo(String value) {
            addCriterion("mcht_reply =", value, "mchtReply");
            return (Criteria) this;
        }

        public Criteria andMchtReplyNotEqualTo(String value) {
            addCriterion("mcht_reply <>", value, "mchtReply");
            return (Criteria) this;
        }

        public Criteria andMchtReplyGreaterThan(String value) {
            addCriterion("mcht_reply >", value, "mchtReply");
            return (Criteria) this;
        }

        public Criteria andMchtReplyGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_reply >=", value, "mchtReply");
            return (Criteria) this;
        }

        public Criteria andMchtReplyLessThan(String value) {
            addCriterion("mcht_reply <", value, "mchtReply");
            return (Criteria) this;
        }

        public Criteria andMchtReplyLessThanOrEqualTo(String value) {
            addCriterion("mcht_reply <=", value, "mchtReply");
            return (Criteria) this;
        }

        public Criteria andMchtReplyLike(String value) {
            addCriterion("mcht_reply like", value, "mchtReply");
            return (Criteria) this;
        }

        public Criteria andMchtReplyNotLike(String value) {
            addCriterion("mcht_reply not like", value, "mchtReply");
            return (Criteria) this;
        }

        public Criteria andMchtReplyIn(List<String> values) {
            addCriterion("mcht_reply in", values, "mchtReply");
            return (Criteria) this;
        }

        public Criteria andMchtReplyNotIn(List<String> values) {
            addCriterion("mcht_reply not in", values, "mchtReply");
            return (Criteria) this;
        }

        public Criteria andMchtReplyBetween(String value1, String value2) {
            addCriterion("mcht_reply between", value1, value2, "mchtReply");
            return (Criteria) this;
        }

        public Criteria andMchtReplyNotBetween(String value1, String value2) {
            addCriterion("mcht_reply not between", value1, value2, "mchtReply");
            return (Criteria) this;
        }

        public Criteria andMchtReplyDateIsNull() {
            addCriterion("mcht_reply_date is null");
            return (Criteria) this;
        }

        public Criteria andMchtReplyDateIsNotNull() {
            addCriterion("mcht_reply_date is not null");
            return (Criteria) this;
        }

        public Criteria andMchtReplyDateEqualTo(Date value) {
            addCriterion("mcht_reply_date =", value, "mchtReplyDate");
            return (Criteria) this;
        }

        public Criteria andMchtReplyDateNotEqualTo(Date value) {
            addCriterion("mcht_reply_date <>", value, "mchtReplyDate");
            return (Criteria) this;
        }

        public Criteria andMchtReplyDateGreaterThan(Date value) {
            addCriterion("mcht_reply_date >", value, "mchtReplyDate");
            return (Criteria) this;
        }

        public Criteria andMchtReplyDateGreaterThanOrEqualTo(Date value) {
            addCriterion("mcht_reply_date >=", value, "mchtReplyDate");
            return (Criteria) this;
        }

        public Criteria andMchtReplyDateLessThan(Date value) {
            addCriterion("mcht_reply_date <", value, "mchtReplyDate");
            return (Criteria) this;
        }

        public Criteria andMchtReplyDateLessThanOrEqualTo(Date value) {
            addCriterion("mcht_reply_date <=", value, "mchtReplyDate");
            return (Criteria) this;
        }

        public Criteria andMchtReplyDateIn(List<Date> values) {
            addCriterion("mcht_reply_date in", values, "mchtReplyDate");
            return (Criteria) this;
        }

        public Criteria andMchtReplyDateNotIn(List<Date> values) {
            addCriterion("mcht_reply_date not in", values, "mchtReplyDate");
            return (Criteria) this;
        }

        public Criteria andMchtReplyDateBetween(Date value1, Date value2) {
            addCriterion("mcht_reply_date between", value1, value2, "mchtReplyDate");
            return (Criteria) this;
        }

        public Criteria andMchtReplyDateNotBetween(Date value1, Date value2) {
            addCriterion("mcht_reply_date not between", value1, value2, "mchtReplyDate");
            return (Criteria) this;
        }

        public Criteria andIsDrawIsNull() {
            addCriterion("is_draw is null");
            return (Criteria) this;
        }

        public Criteria andIsDrawIsNotNull() {
            addCriterion("is_draw is not null");
            return (Criteria) this;
        }

        public Criteria andIsDrawEqualTo(String value) {
            addCriterion("is_draw =", value, "isDraw");
            return (Criteria) this;
        }

        public Criteria andIsDrawNotEqualTo(String value) {
            addCriterion("is_draw <>", value, "isDraw");
            return (Criteria) this;
        }

        public Criteria andIsDrawGreaterThan(String value) {
            addCriterion("is_draw >", value, "isDraw");
            return (Criteria) this;
        }

        public Criteria andIsDrawGreaterThanOrEqualTo(String value) {
            addCriterion("is_draw >=", value, "isDraw");
            return (Criteria) this;
        }

        public Criteria andIsDrawLessThan(String value) {
            addCriterion("is_draw <", value, "isDraw");
            return (Criteria) this;
        }

        public Criteria andIsDrawLessThanOrEqualTo(String value) {
            addCriterion("is_draw <=", value, "isDraw");
            return (Criteria) this;
        }

        public Criteria andIsDrawLike(String value) {
            addCriterion("is_draw like", value, "isDraw");
            return (Criteria) this;
        }

        public Criteria andIsDrawNotLike(String value) {
            addCriterion("is_draw not like", value, "isDraw");
            return (Criteria) this;
        }

        public Criteria andIsDrawIn(List<String> values) {
            addCriterion("is_draw in", values, "isDraw");
            return (Criteria) this;
        }

        public Criteria andIsDrawNotIn(List<String> values) {
            addCriterion("is_draw not in", values, "isDraw");
            return (Criteria) this;
        }

        public Criteria andIsDrawBetween(String value1, String value2) {
            addCriterion("is_draw between", value1, value2, "isDraw");
            return (Criteria) this;
        }

        public Criteria andIsDrawNotBetween(String value1, String value2) {
            addCriterion("is_draw not between", value1, value2, "isDraw");
            return (Criteria) this;
        }

        public Criteria andCommentWeightIsNull() {
            addCriterion("comment_weight is null");
            return (Criteria) this;
        }

        public Criteria andCommentWeightIsNotNull() {
            addCriterion("comment_weight is not null");
            return (Criteria) this;
        }

        public Criteria andCommentWeightEqualTo(Integer value) {
            addCriterion("comment_weight =", value, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andCommentWeightNotEqualTo(Integer value) {
            addCriterion("comment_weight <>", value, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andCommentWeightGreaterThan(Integer value) {
            addCriterion("comment_weight >", value, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andCommentWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("comment_weight >=", value, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andCommentWeightLessThan(Integer value) {
            addCriterion("comment_weight <", value, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andCommentWeightLessThanOrEqualTo(Integer value) {
            addCriterion("comment_weight <=", value, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andCommentWeightIn(List<Integer> values) {
            addCriterion("comment_weight in", values, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andCommentWeightNotIn(List<Integer> values) {
            addCriterion("comment_weight not in", values, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andCommentWeightBetween(Integer value1, Integer value2) {
            addCriterion("comment_weight between", value1, value2, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andCommentWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("comment_weight not between", value1, value2, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andCommentSourceIsNull() {
            addCriterion("comment_source is null");
            return (Criteria) this;
        }

        public Criteria andCommentSourceIsNotNull() {
            addCriterion("comment_source is not null");
            return (Criteria) this;
        }

        public Criteria andCommentSourceEqualTo(String value) {
            addCriterion("comment_source =", value, "commentSource");
            return (Criteria) this;
        }

        public Criteria andCommentSourceNotEqualTo(String value) {
            addCriterion("comment_source <>", value, "commentSource");
            return (Criteria) this;
        }

        public Criteria andCommentSourceGreaterThan(String value) {
            addCriterion("comment_source >", value, "commentSource");
            return (Criteria) this;
        }

        public Criteria andCommentSourceGreaterThanOrEqualTo(String value) {
            addCriterion("comment_source >=", value, "commentSource");
            return (Criteria) this;
        }

        public Criteria andCommentSourceLessThan(String value) {
            addCriterion("comment_source <", value, "commentSource");
            return (Criteria) this;
        }

        public Criteria andCommentSourceLessThanOrEqualTo(String value) {
            addCriterion("comment_source <=", value, "commentSource");
            return (Criteria) this;
        }

        public Criteria andCommentSourceLike(String value) {
            addCriterion("comment_source like", value, "commentSource");
            return (Criteria) this;
        }

        public Criteria andCommentSourceNotLike(String value) {
            addCriterion("comment_source not like", value, "commentSource");
            return (Criteria) this;
        }

        public Criteria andCommentSourceIn(List<String> values) {
            addCriterion("comment_source in", values, "commentSource");
            return (Criteria) this;
        }

        public Criteria andCommentSourceNotIn(List<String> values) {
            addCriterion("comment_source not in", values, "commentSource");
            return (Criteria) this;
        }

        public Criteria andCommentSourceBetween(String value1, String value2) {
            addCriterion("comment_source between", value1, value2, "commentSource");
            return (Criteria) this;
        }

        public Criteria andCommentSourceNotBetween(String value1, String value2) {
            addCriterion("comment_source not between", value1, value2, "commentSource");
            return (Criteria) this;
        }

        public Criteria andIsShowIsNull() {
            addCriterion("is_show is null");
            return (Criteria) this;
        }

        public Criteria andIsShowIsNotNull() {
            addCriterion("is_show is not null");
            return (Criteria) this;
        }

        public Criteria andIsShowEqualTo(String value) {
            addCriterion("is_show =", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotEqualTo(String value) {
            addCriterion("is_show <>", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowGreaterThan(String value) {
            addCriterion("is_show >", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowGreaterThanOrEqualTo(String value) {
            addCriterion("is_show >=", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowLessThan(String value) {
            addCriterion("is_show <", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowLessThanOrEqualTo(String value) {
            addCriterion("is_show <=", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowLike(String value) {
            addCriterion("is_show like", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotLike(String value) {
            addCriterion("is_show not like", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowIn(List<String> values) {
            addCriterion("is_show in", values, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotIn(List<String> values) {
            addCriterion("is_show not in", values, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowBetween(String value1, String value2) {
            addCriterion("is_show between", value1, value2, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotBetween(String value1, String value2) {
            addCriterion("is_show not between", value1, value2, "isShow");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(Integer value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(Integer value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(Integer value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(Integer value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(Integer value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<Integer> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<Integer> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(Integer value1, Integer value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(Integer value1, Integer value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(Integer value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(Integer value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(Integer value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(Integer value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(Integer value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(Integer value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<Integer> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<Integer> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(Integer value1, Integer value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(Integer value1, Integer value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNull() {
            addCriterion("del_flag is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("del_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(String value) {
            addCriterion("del_flag =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(String value) {
            addCriterion("del_flag <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(String value) {
            addCriterion("del_flag >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(String value) {
            addCriterion("del_flag >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(String value) {
            addCriterion("del_flag <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(String value) {
            addCriterion("del_flag <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLike(String value) {
            addCriterion("del_flag like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotLike(String value) {
            addCriterion("del_flag not like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<String> values) {
            addCriterion("del_flag in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<String> values) {
            addCriterion("del_flag not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(String value1, String value2) {
            addCriterion("del_flag between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(String value1, String value2) {
            addCriterion("del_flag not between", value1, value2, "delFlag");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}