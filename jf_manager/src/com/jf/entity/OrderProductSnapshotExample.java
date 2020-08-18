package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderProductSnapshotExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public OrderProductSnapshotExample() {
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

        public Criteria andMainPicGroupIsNull() {
            addCriterion("main_pic_group is null");
            return (Criteria) this;
        }

        public Criteria andMainPicGroupIsNotNull() {
            addCriterion("main_pic_group is not null");
            return (Criteria) this;
        }

        public Criteria andMainPicGroupEqualTo(String value) {
            addCriterion("main_pic_group =", value, "mainPicGroup");
            return (Criteria) this;
        }

        public Criteria andMainPicGroupNotEqualTo(String value) {
            addCriterion("main_pic_group <>", value, "mainPicGroup");
            return (Criteria) this;
        }

        public Criteria andMainPicGroupGreaterThan(String value) {
            addCriterion("main_pic_group >", value, "mainPicGroup");
            return (Criteria) this;
        }

        public Criteria andMainPicGroupGreaterThanOrEqualTo(String value) {
            addCriterion("main_pic_group >=", value, "mainPicGroup");
            return (Criteria) this;
        }

        public Criteria andMainPicGroupLessThan(String value) {
            addCriterion("main_pic_group <", value, "mainPicGroup");
            return (Criteria) this;
        }

        public Criteria andMainPicGroupLessThanOrEqualTo(String value) {
            addCriterion("main_pic_group <=", value, "mainPicGroup");
            return (Criteria) this;
        }

        public Criteria andMainPicGroupLike(String value) {
            addCriterion("main_pic_group like", value, "mainPicGroup");
            return (Criteria) this;
        }

        public Criteria andMainPicGroupNotLike(String value) {
            addCriterion("main_pic_group not like", value, "mainPicGroup");
            return (Criteria) this;
        }

        public Criteria andMainPicGroupIn(List<String> values) {
            addCriterion("main_pic_group in", values, "mainPicGroup");
            return (Criteria) this;
        }

        public Criteria andMainPicGroupNotIn(List<String> values) {
            addCriterion("main_pic_group not in", values, "mainPicGroup");
            return (Criteria) this;
        }

        public Criteria andMainPicGroupBetween(String value1, String value2) {
            addCriterion("main_pic_group between", value1, value2, "mainPicGroup");
            return (Criteria) this;
        }

        public Criteria andMainPicGroupNotBetween(String value1, String value2) {
            addCriterion("main_pic_group not between", value1, value2, "mainPicGroup");
            return (Criteria) this;
        }

        public Criteria andDescPicGroupIsNull() {
            addCriterion("desc_pic_group is null");
            return (Criteria) this;
        }

        public Criteria andDescPicGroupIsNotNull() {
            addCriterion("desc_pic_group is not null");
            return (Criteria) this;
        }

        public Criteria andDescPicGroupEqualTo(String value) {
            addCriterion("desc_pic_group =", value, "descPicGroup");
            return (Criteria) this;
        }

        public Criteria andDescPicGroupNotEqualTo(String value) {
            addCriterion("desc_pic_group <>", value, "descPicGroup");
            return (Criteria) this;
        }

        public Criteria andDescPicGroupGreaterThan(String value) {
            addCriterion("desc_pic_group >", value, "descPicGroup");
            return (Criteria) this;
        }

        public Criteria andDescPicGroupGreaterThanOrEqualTo(String value) {
            addCriterion("desc_pic_group >=", value, "descPicGroup");
            return (Criteria) this;
        }

        public Criteria andDescPicGroupLessThan(String value) {
            addCriterion("desc_pic_group <", value, "descPicGroup");
            return (Criteria) this;
        }

        public Criteria andDescPicGroupLessThanOrEqualTo(String value) {
            addCriterion("desc_pic_group <=", value, "descPicGroup");
            return (Criteria) this;
        }

        public Criteria andDescPicGroupLike(String value) {
            addCriterion("desc_pic_group like", value, "descPicGroup");
            return (Criteria) this;
        }

        public Criteria andDescPicGroupNotLike(String value) {
            addCriterion("desc_pic_group not like", value, "descPicGroup");
            return (Criteria) this;
        }

        public Criteria andDescPicGroupIn(List<String> values) {
            addCriterion("desc_pic_group in", values, "descPicGroup");
            return (Criteria) this;
        }

        public Criteria andDescPicGroupNotIn(List<String> values) {
            addCriterion("desc_pic_group not in", values, "descPicGroup");
            return (Criteria) this;
        }

        public Criteria andDescPicGroupBetween(String value1, String value2) {
            addCriterion("desc_pic_group between", value1, value2, "descPicGroup");
            return (Criteria) this;
        }

        public Criteria andDescPicGroupNotBetween(String value1, String value2) {
            addCriterion("desc_pic_group not between", value1, value2, "descPicGroup");
            return (Criteria) this;
        }

        public Criteria andActivityDiscountIsNull() {
            addCriterion("activity_discount is null");
            return (Criteria) this;
        }

        public Criteria andActivityDiscountIsNotNull() {
            addCriterion("activity_discount is not null");
            return (Criteria) this;
        }

        public Criteria andActivityDiscountEqualTo(String value) {
            addCriterion("activity_discount =", value, "activityDiscount");
            return (Criteria) this;
        }

        public Criteria andActivityDiscountNotEqualTo(String value) {
            addCriterion("activity_discount <>", value, "activityDiscount");
            return (Criteria) this;
        }

        public Criteria andActivityDiscountGreaterThan(String value) {
            addCriterion("activity_discount >", value, "activityDiscount");
            return (Criteria) this;
        }

        public Criteria andActivityDiscountGreaterThanOrEqualTo(String value) {
            addCriterion("activity_discount >=", value, "activityDiscount");
            return (Criteria) this;
        }

        public Criteria andActivityDiscountLessThan(String value) {
            addCriterion("activity_discount <", value, "activityDiscount");
            return (Criteria) this;
        }

        public Criteria andActivityDiscountLessThanOrEqualTo(String value) {
            addCriterion("activity_discount <=", value, "activityDiscount");
            return (Criteria) this;
        }

        public Criteria andActivityDiscountLike(String value) {
            addCriterion("activity_discount like", value, "activityDiscount");
            return (Criteria) this;
        }

        public Criteria andActivityDiscountNotLike(String value) {
            addCriterion("activity_discount not like", value, "activityDiscount");
            return (Criteria) this;
        }

        public Criteria andActivityDiscountIn(List<String> values) {
            addCriterion("activity_discount in", values, "activityDiscount");
            return (Criteria) this;
        }

        public Criteria andActivityDiscountNotIn(List<String> values) {
            addCriterion("activity_discount not in", values, "activityDiscount");
            return (Criteria) this;
        }

        public Criteria andActivityDiscountBetween(String value1, String value2) {
            addCriterion("activity_discount between", value1, value2, "activityDiscount");
            return (Criteria) this;
        }

        public Criteria andActivityDiscountNotBetween(String value1, String value2) {
            addCriterion("activity_discount not between", value1, value2, "activityDiscount");
            return (Criteria) this;
        }

        public Criteria andSuitGroupIsNull() {
            addCriterion("suit_group is null");
            return (Criteria) this;
        }

        public Criteria andSuitGroupIsNotNull() {
            addCriterion("suit_group is not null");
            return (Criteria) this;
        }

        public Criteria andSuitGroupEqualTo(String value) {
            addCriterion("suit_group =", value, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupNotEqualTo(String value) {
            addCriterion("suit_group <>", value, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupGreaterThan(String value) {
            addCriterion("suit_group >", value, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupGreaterThanOrEqualTo(String value) {
            addCriterion("suit_group >=", value, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupLessThan(String value) {
            addCriterion("suit_group <", value, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupLessThanOrEqualTo(String value) {
            addCriterion("suit_group <=", value, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupLike(String value) {
            addCriterion("suit_group like", value, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupNotLike(String value) {
            addCriterion("suit_group not like", value, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupIn(List<String> values) {
            addCriterion("suit_group in", values, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupNotIn(List<String> values) {
            addCriterion("suit_group not in", values, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupBetween(String value1, String value2) {
            addCriterion("suit_group between", value1, value2, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupNotBetween(String value1, String value2) {
            addCriterion("suit_group not between", value1, value2, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitSexIsNull() {
            addCriterion("suit_sex is null");
            return (Criteria) this;
        }

        public Criteria andSuitSexIsNotNull() {
            addCriterion("suit_sex is not null");
            return (Criteria) this;
        }

        public Criteria andSuitSexEqualTo(String value) {
            addCriterion("suit_sex =", value, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexNotEqualTo(String value) {
            addCriterion("suit_sex <>", value, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexGreaterThan(String value) {
            addCriterion("suit_sex >", value, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexGreaterThanOrEqualTo(String value) {
            addCriterion("suit_sex >=", value, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexLessThan(String value) {
            addCriterion("suit_sex <", value, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexLessThanOrEqualTo(String value) {
            addCriterion("suit_sex <=", value, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexLike(String value) {
            addCriterion("suit_sex like", value, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexNotLike(String value) {
            addCriterion("suit_sex not like", value, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexIn(List<String> values) {
            addCriterion("suit_sex in", values, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexNotIn(List<String> values) {
            addCriterion("suit_sex not in", values, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexBetween(String value1, String value2) {
            addCriterion("suit_sex between", value1, value2, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexNotBetween(String value1, String value2) {
            addCriterion("suit_sex not between", value1, value2, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSeasonIsNull() {
            addCriterion("season is null");
            return (Criteria) this;
        }

        public Criteria andSeasonIsNotNull() {
            addCriterion("season is not null");
            return (Criteria) this;
        }

        public Criteria andSeasonEqualTo(String value) {
            addCriterion("season =", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonNotEqualTo(String value) {
            addCriterion("season <>", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonGreaterThan(String value) {
            addCriterion("season >", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonGreaterThanOrEqualTo(String value) {
            addCriterion("season >=", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonLessThan(String value) {
            addCriterion("season <", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonLessThanOrEqualTo(String value) {
            addCriterion("season <=", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonLike(String value) {
            addCriterion("season like", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonNotLike(String value) {
            addCriterion("season not like", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonIn(List<String> values) {
            addCriterion("season in", values, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonNotIn(List<String> values) {
            addCriterion("season not in", values, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonBetween(String value1, String value2) {
            addCriterion("season between", value1, value2, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonNotBetween(String value1, String value2) {
            addCriterion("season not between", value1, value2, "season");
            return (Criteria) this;
        }

        public Criteria andServiceDescIsNull() {
            addCriterion("service_desc is null");
            return (Criteria) this;
        }

        public Criteria andServiceDescIsNotNull() {
            addCriterion("service_desc is not null");
            return (Criteria) this;
        }

        public Criteria andServiceDescEqualTo(String value) {
            addCriterion("service_desc =", value, "serviceDesc");
            return (Criteria) this;
        }

        public Criteria andServiceDescNotEqualTo(String value) {
            addCriterion("service_desc <>", value, "serviceDesc");
            return (Criteria) this;
        }

        public Criteria andServiceDescGreaterThan(String value) {
            addCriterion("service_desc >", value, "serviceDesc");
            return (Criteria) this;
        }

        public Criteria andServiceDescGreaterThanOrEqualTo(String value) {
            addCriterion("service_desc >=", value, "serviceDesc");
            return (Criteria) this;
        }

        public Criteria andServiceDescLessThan(String value) {
            addCriterion("service_desc <", value, "serviceDesc");
            return (Criteria) this;
        }

        public Criteria andServiceDescLessThanOrEqualTo(String value) {
            addCriterion("service_desc <=", value, "serviceDesc");
            return (Criteria) this;
        }

        public Criteria andServiceDescLike(String value) {
            addCriterion("service_desc like", value, "serviceDesc");
            return (Criteria) this;
        }

        public Criteria andServiceDescNotLike(String value) {
            addCriterion("service_desc not like", value, "serviceDesc");
            return (Criteria) this;
        }

        public Criteria andServiceDescIn(List<String> values) {
            addCriterion("service_desc in", values, "serviceDesc");
            return (Criteria) this;
        }

        public Criteria andServiceDescNotIn(List<String> values) {
            addCriterion("service_desc not in", values, "serviceDesc");
            return (Criteria) this;
        }

        public Criteria andServiceDescBetween(String value1, String value2) {
            addCriterion("service_desc between", value1, value2, "serviceDesc");
            return (Criteria) this;
        }

        public Criteria andServiceDescNotBetween(String value1, String value2) {
            addCriterion("service_desc not between", value1, value2, "serviceDesc");
            return (Criteria) this;
        }

        public Criteria andFreightDescIsNull() {
            addCriterion("freight_desc is null");
            return (Criteria) this;
        }

        public Criteria andFreightDescIsNotNull() {
            addCriterion("freight_desc is not null");
            return (Criteria) this;
        }

        public Criteria andFreightDescEqualTo(String value) {
            addCriterion("freight_desc =", value, "freightDesc");
            return (Criteria) this;
        }

        public Criteria andFreightDescNotEqualTo(String value) {
            addCriterion("freight_desc <>", value, "freightDesc");
            return (Criteria) this;
        }

        public Criteria andFreightDescGreaterThan(String value) {
            addCriterion("freight_desc >", value, "freightDesc");
            return (Criteria) this;
        }

        public Criteria andFreightDescGreaterThanOrEqualTo(String value) {
            addCriterion("freight_desc >=", value, "freightDesc");
            return (Criteria) this;
        }

        public Criteria andFreightDescLessThan(String value) {
            addCriterion("freight_desc <", value, "freightDesc");
            return (Criteria) this;
        }

        public Criteria andFreightDescLessThanOrEqualTo(String value) {
            addCriterion("freight_desc <=", value, "freightDesc");
            return (Criteria) this;
        }

        public Criteria andFreightDescLike(String value) {
            addCriterion("freight_desc like", value, "freightDesc");
            return (Criteria) this;
        }

        public Criteria andFreightDescNotLike(String value) {
            addCriterion("freight_desc not like", value, "freightDesc");
            return (Criteria) this;
        }

        public Criteria andFreightDescIn(List<String> values) {
            addCriterion("freight_desc in", values, "freightDesc");
            return (Criteria) this;
        }

        public Criteria andFreightDescNotIn(List<String> values) {
            addCriterion("freight_desc not in", values, "freightDesc");
            return (Criteria) this;
        }

        public Criteria andFreightDescBetween(String value1, String value2) {
            addCriterion("freight_desc between", value1, value2, "freightDesc");
            return (Criteria) this;
        }

        public Criteria andFreightDescNotBetween(String value1, String value2) {
            addCriterion("freight_desc not between", value1, value2, "freightDesc");
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