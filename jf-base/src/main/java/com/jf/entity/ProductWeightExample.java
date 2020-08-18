package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductWeightExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public ProductWeightExample() {
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

        public Criteria andSeasonWeightIsNull() {
            addCriterion("season_weight is null");
            return (Criteria) this;
        }

        public Criteria andSeasonWeightIsNotNull() {
            addCriterion("season_weight is not null");
            return (Criteria) this;
        }

        public Criteria andSeasonWeightEqualTo(Integer value) {
            addCriterion("season_weight =", value, "seasonWeight");
            return (Criteria) this;
        }

        public Criteria andSeasonWeightNotEqualTo(Integer value) {
            addCriterion("season_weight <>", value, "seasonWeight");
            return (Criteria) this;
        }

        public Criteria andSeasonWeightGreaterThan(Integer value) {
            addCriterion("season_weight >", value, "seasonWeight");
            return (Criteria) this;
        }

        public Criteria andSeasonWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("season_weight >=", value, "seasonWeight");
            return (Criteria) this;
        }

        public Criteria andSeasonWeightLessThan(Integer value) {
            addCriterion("season_weight <", value, "seasonWeight");
            return (Criteria) this;
        }

        public Criteria andSeasonWeightLessThanOrEqualTo(Integer value) {
            addCriterion("season_weight <=", value, "seasonWeight");
            return (Criteria) this;
        }

        public Criteria andSeasonWeightIn(List<Integer> values) {
            addCriterion("season_weight in", values, "seasonWeight");
            return (Criteria) this;
        }

        public Criteria andSeasonWeightNotIn(List<Integer> values) {
            addCriterion("season_weight not in", values, "seasonWeight");
            return (Criteria) this;
        }

        public Criteria andSeasonWeightBetween(Integer value1, Integer value2) {
            addCriterion("season_weight between", value1, value2, "seasonWeight");
            return (Criteria) this;
        }

        public Criteria andSeasonWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("season_weight not between", value1, value2, "seasonWeight");
            return (Criteria) this;
        }

        public Criteria andSaleWeightIsNull() {
            addCriterion("sale_weight is null");
            return (Criteria) this;
        }

        public Criteria andSaleWeightIsNotNull() {
            addCriterion("sale_weight is not null");
            return (Criteria) this;
        }

        public Criteria andSaleWeightEqualTo(Integer value) {
            addCriterion("sale_weight =", value, "saleWeight");
            return (Criteria) this;
        }

        public Criteria andSaleWeightNotEqualTo(Integer value) {
            addCriterion("sale_weight <>", value, "saleWeight");
            return (Criteria) this;
        }

        public Criteria andSaleWeightGreaterThan(Integer value) {
            addCriterion("sale_weight >", value, "saleWeight");
            return (Criteria) this;
        }

        public Criteria andSaleWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("sale_weight >=", value, "saleWeight");
            return (Criteria) this;
        }

        public Criteria andSaleWeightLessThan(Integer value) {
            addCriterion("sale_weight <", value, "saleWeight");
            return (Criteria) this;
        }

        public Criteria andSaleWeightLessThanOrEqualTo(Integer value) {
            addCriterion("sale_weight <=", value, "saleWeight");
            return (Criteria) this;
        }

        public Criteria andSaleWeightIn(List<Integer> values) {
            addCriterion("sale_weight in", values, "saleWeight");
            return (Criteria) this;
        }

        public Criteria andSaleWeightNotIn(List<Integer> values) {
            addCriterion("sale_weight not in", values, "saleWeight");
            return (Criteria) this;
        }

        public Criteria andSaleWeightBetween(Integer value1, Integer value2) {
            addCriterion("sale_weight between", value1, value2, "saleWeight");
            return (Criteria) this;
        }

        public Criteria andSaleWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("sale_weight not between", value1, value2, "saleWeight");
            return (Criteria) this;
        }

        public Criteria andSaleAmountWeightIsNull() {
            addCriterion("sale_amount_weight is null");
            return (Criteria) this;
        }

        public Criteria andSaleAmountWeightIsNotNull() {
            addCriterion("sale_amount_weight is not null");
            return (Criteria) this;
        }

        public Criteria andSaleAmountWeightEqualTo(Integer value) {
            addCriterion("sale_amount_weight =", value, "saleAmountWeight");
            return (Criteria) this;
        }

        public Criteria andSaleAmountWeightNotEqualTo(Integer value) {
            addCriterion("sale_amount_weight <>", value, "saleAmountWeight");
            return (Criteria) this;
        }

        public Criteria andSaleAmountWeightGreaterThan(Integer value) {
            addCriterion("sale_amount_weight >", value, "saleAmountWeight");
            return (Criteria) this;
        }

        public Criteria andSaleAmountWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("sale_amount_weight >=", value, "saleAmountWeight");
            return (Criteria) this;
        }

        public Criteria andSaleAmountWeightLessThan(Integer value) {
            addCriterion("sale_amount_weight <", value, "saleAmountWeight");
            return (Criteria) this;
        }

        public Criteria andSaleAmountWeightLessThanOrEqualTo(Integer value) {
            addCriterion("sale_amount_weight <=", value, "saleAmountWeight");
            return (Criteria) this;
        }

        public Criteria andSaleAmountWeightIn(List<Integer> values) {
            addCriterion("sale_amount_weight in", values, "saleAmountWeight");
            return (Criteria) this;
        }

        public Criteria andSaleAmountWeightNotIn(List<Integer> values) {
            addCriterion("sale_amount_weight not in", values, "saleAmountWeight");
            return (Criteria) this;
        }

        public Criteria andSaleAmountWeightBetween(Integer value1, Integer value2) {
            addCriterion("sale_amount_weight between", value1, value2, "saleAmountWeight");
            return (Criteria) this;
        }

        public Criteria andSaleAmountWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("sale_amount_weight not between", value1, value2, "saleAmountWeight");
            return (Criteria) this;
        }

        public Criteria andPvWeightIsNull() {
            addCriterion("pv_weight is null");
            return (Criteria) this;
        }

        public Criteria andPvWeightIsNotNull() {
            addCriterion("pv_weight is not null");
            return (Criteria) this;
        }

        public Criteria andPvWeightEqualTo(Integer value) {
            addCriterion("pv_weight =", value, "pvWeight");
            return (Criteria) this;
        }

        public Criteria andPvWeightNotEqualTo(Integer value) {
            addCriterion("pv_weight <>", value, "pvWeight");
            return (Criteria) this;
        }

        public Criteria andPvWeightGreaterThan(Integer value) {
            addCriterion("pv_weight >", value, "pvWeight");
            return (Criteria) this;
        }

        public Criteria andPvWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("pv_weight >=", value, "pvWeight");
            return (Criteria) this;
        }

        public Criteria andPvWeightLessThan(Integer value) {
            addCriterion("pv_weight <", value, "pvWeight");
            return (Criteria) this;
        }

        public Criteria andPvWeightLessThanOrEqualTo(Integer value) {
            addCriterion("pv_weight <=", value, "pvWeight");
            return (Criteria) this;
        }

        public Criteria andPvWeightIn(List<Integer> values) {
            addCriterion("pv_weight in", values, "pvWeight");
            return (Criteria) this;
        }

        public Criteria andPvWeightNotIn(List<Integer> values) {
            addCriterion("pv_weight not in", values, "pvWeight");
            return (Criteria) this;
        }

        public Criteria andPvWeightBetween(Integer value1, Integer value2) {
            addCriterion("pv_weight between", value1, value2, "pvWeight");
            return (Criteria) this;
        }

        public Criteria andPvWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("pv_weight not between", value1, value2, "pvWeight");
            return (Criteria) this;
        }

        public Criteria andMchtGradeWeightIsNull() {
            addCriterion("mcht_grade_weight is null");
            return (Criteria) this;
        }

        public Criteria andMchtGradeWeightIsNotNull() {
            addCriterion("mcht_grade_weight is not null");
            return (Criteria) this;
        }

        public Criteria andMchtGradeWeightEqualTo(Integer value) {
            addCriterion("mcht_grade_weight =", value, "mchtGradeWeight");
            return (Criteria) this;
        }

        public Criteria andMchtGradeWeightNotEqualTo(Integer value) {
            addCriterion("mcht_grade_weight <>", value, "mchtGradeWeight");
            return (Criteria) this;
        }

        public Criteria andMchtGradeWeightGreaterThan(Integer value) {
            addCriterion("mcht_grade_weight >", value, "mchtGradeWeight");
            return (Criteria) this;
        }

        public Criteria andMchtGradeWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("mcht_grade_weight >=", value, "mchtGradeWeight");
            return (Criteria) this;
        }

        public Criteria andMchtGradeWeightLessThan(Integer value) {
            addCriterion("mcht_grade_weight <", value, "mchtGradeWeight");
            return (Criteria) this;
        }

        public Criteria andMchtGradeWeightLessThanOrEqualTo(Integer value) {
            addCriterion("mcht_grade_weight <=", value, "mchtGradeWeight");
            return (Criteria) this;
        }

        public Criteria andMchtGradeWeightIn(List<Integer> values) {
            addCriterion("mcht_grade_weight in", values, "mchtGradeWeight");
            return (Criteria) this;
        }

        public Criteria andMchtGradeWeightNotIn(List<Integer> values) {
            addCriterion("mcht_grade_weight not in", values, "mchtGradeWeight");
            return (Criteria) this;
        }

        public Criteria andMchtGradeWeightBetween(Integer value1, Integer value2) {
            addCriterion("mcht_grade_weight between", value1, value2, "mchtGradeWeight");
            return (Criteria) this;
        }

        public Criteria andMchtGradeWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("mcht_grade_weight not between", value1, value2, "mchtGradeWeight");
            return (Criteria) this;
        }

        public Criteria andBrandGradeWeightIsNull() {
            addCriterion("brand_grade_weight is null");
            return (Criteria) this;
        }

        public Criteria andBrandGradeWeightIsNotNull() {
            addCriterion("brand_grade_weight is not null");
            return (Criteria) this;
        }

        public Criteria andBrandGradeWeightEqualTo(Integer value) {
            addCriterion("brand_grade_weight =", value, "brandGradeWeight");
            return (Criteria) this;
        }

        public Criteria andBrandGradeWeightNotEqualTo(Integer value) {
            addCriterion("brand_grade_weight <>", value, "brandGradeWeight");
            return (Criteria) this;
        }

        public Criteria andBrandGradeWeightGreaterThan(Integer value) {
            addCriterion("brand_grade_weight >", value, "brandGradeWeight");
            return (Criteria) this;
        }

        public Criteria andBrandGradeWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("brand_grade_weight >=", value, "brandGradeWeight");
            return (Criteria) this;
        }

        public Criteria andBrandGradeWeightLessThan(Integer value) {
            addCriterion("brand_grade_weight <", value, "brandGradeWeight");
            return (Criteria) this;
        }

        public Criteria andBrandGradeWeightLessThanOrEqualTo(Integer value) {
            addCriterion("brand_grade_weight <=", value, "brandGradeWeight");
            return (Criteria) this;
        }

        public Criteria andBrandGradeWeightIn(List<Integer> values) {
            addCriterion("brand_grade_weight in", values, "brandGradeWeight");
            return (Criteria) this;
        }

        public Criteria andBrandGradeWeightNotIn(List<Integer> values) {
            addCriterion("brand_grade_weight not in", values, "brandGradeWeight");
            return (Criteria) this;
        }

        public Criteria andBrandGradeWeightBetween(Integer value1, Integer value2) {
            addCriterion("brand_grade_weight between", value1, value2, "brandGradeWeight");
            return (Criteria) this;
        }

        public Criteria andBrandGradeWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("brand_grade_weight not between", value1, value2, "brandGradeWeight");
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

        public Criteria andManualWeightIsNull() {
            addCriterion("manual_weight is null");
            return (Criteria) this;
        }

        public Criteria andManualWeightIsNotNull() {
            addCriterion("manual_weight is not null");
            return (Criteria) this;
        }

        public Criteria andManualWeightEqualTo(Integer value) {
            addCriterion("manual_weight =", value, "manualWeight");
            return (Criteria) this;
        }

        public Criteria andManualWeightNotEqualTo(Integer value) {
            addCriterion("manual_weight <>", value, "manualWeight");
            return (Criteria) this;
        }

        public Criteria andManualWeightGreaterThan(Integer value) {
            addCriterion("manual_weight >", value, "manualWeight");
            return (Criteria) this;
        }

        public Criteria andManualWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("manual_weight >=", value, "manualWeight");
            return (Criteria) this;
        }

        public Criteria andManualWeightLessThan(Integer value) {
            addCriterion("manual_weight <", value, "manualWeight");
            return (Criteria) this;
        }

        public Criteria andManualWeightLessThanOrEqualTo(Integer value) {
            addCriterion("manual_weight <=", value, "manualWeight");
            return (Criteria) this;
        }

        public Criteria andManualWeightIn(List<Integer> values) {
            addCriterion("manual_weight in", values, "manualWeight");
            return (Criteria) this;
        }

        public Criteria andManualWeightNotIn(List<Integer> values) {
            addCriterion("manual_weight not in", values, "manualWeight");
            return (Criteria) this;
        }

        public Criteria andManualWeightBetween(Integer value1, Integer value2) {
            addCriterion("manual_weight between", value1, value2, "manualWeight");
            return (Criteria) this;
        }

        public Criteria andManualWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("manual_weight not between", value1, value2, "manualWeight");
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