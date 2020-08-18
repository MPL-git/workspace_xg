package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SeckillModuleColorSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public SeckillModuleColorSettingExample() {
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

        public Criteria andDecorateModuleIdIsNull() {
            addCriterion("decorate_module_id is null");
            return (Criteria) this;
        }

        public Criteria andDecorateModuleIdIsNotNull() {
            addCriterion("decorate_module_id is not null");
            return (Criteria) this;
        }

        public Criteria andDecorateModuleIdEqualTo(Integer value) {
            addCriterion("decorate_module_id =", value, "decorateModuleId");
            return (Criteria) this;
        }

        public Criteria andDecorateModuleIdNotEqualTo(Integer value) {
            addCriterion("decorate_module_id <>", value, "decorateModuleId");
            return (Criteria) this;
        }

        public Criteria andDecorateModuleIdGreaterThan(Integer value) {
            addCriterion("decorate_module_id >", value, "decorateModuleId");
            return (Criteria) this;
        }

        public Criteria andDecorateModuleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("decorate_module_id >=", value, "decorateModuleId");
            return (Criteria) this;
        }

        public Criteria andDecorateModuleIdLessThan(Integer value) {
            addCriterion("decorate_module_id <", value, "decorateModuleId");
            return (Criteria) this;
        }

        public Criteria andDecorateModuleIdLessThanOrEqualTo(Integer value) {
            addCriterion("decorate_module_id <=", value, "decorateModuleId");
            return (Criteria) this;
        }

        public Criteria andDecorateModuleIdIn(List<Integer> values) {
            addCriterion("decorate_module_id in", values, "decorateModuleId");
            return (Criteria) this;
        }

        public Criteria andDecorateModuleIdNotIn(List<Integer> values) {
            addCriterion("decorate_module_id not in", values, "decorateModuleId");
            return (Criteria) this;
        }

        public Criteria andDecorateModuleIdBetween(Integer value1, Integer value2) {
            addCriterion("decorate_module_id between", value1, value2, "decorateModuleId");
            return (Criteria) this;
        }

        public Criteria andDecorateModuleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("decorate_module_id not between", value1, value2, "decorateModuleId");
            return (Criteria) this;
        }

        public Criteria andDataSourceIsNull() {
            addCriterion("data_source is null");
            return (Criteria) this;
        }

        public Criteria andDataSourceIsNotNull() {
            addCriterion("data_source is not null");
            return (Criteria) this;
        }

        public Criteria andDataSourceEqualTo(String value) {
            addCriterion("data_source =", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceNotEqualTo(String value) {
            addCriterion("data_source <>", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceGreaterThan(String value) {
            addCriterion("data_source >", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceGreaterThanOrEqualTo(String value) {
            addCriterion("data_source >=", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceLessThan(String value) {
            addCriterion("data_source <", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceLessThanOrEqualTo(String value) {
            addCriterion("data_source <=", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceLike(String value) {
            addCriterion("data_source like", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceNotLike(String value) {
            addCriterion("data_source not like", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceIn(List<String> values) {
            addCriterion("data_source in", values, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceNotIn(List<String> values) {
            addCriterion("data_source not in", values, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceBetween(String value1, String value2) {
            addCriterion("data_source between", value1, value2, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceNotBetween(String value1, String value2) {
            addCriterion("data_source not between", value1, value2, "dataSource");
            return (Criteria) this;
        }

        public Criteria andTotalBgColorIsNull() {
            addCriterion("total_bg_color is null");
            return (Criteria) this;
        }

        public Criteria andTotalBgColorIsNotNull() {
            addCriterion("total_bg_color is not null");
            return (Criteria) this;
        }

        public Criteria andTotalBgColorEqualTo(String value) {
            addCriterion("total_bg_color =", value, "totalBgColor");
            return (Criteria) this;
        }

        public Criteria andTotalBgColorNotEqualTo(String value) {
            addCriterion("total_bg_color <>", value, "totalBgColor");
            return (Criteria) this;
        }

        public Criteria andTotalBgColorGreaterThan(String value) {
            addCriterion("total_bg_color >", value, "totalBgColor");
            return (Criteria) this;
        }

        public Criteria andTotalBgColorGreaterThanOrEqualTo(String value) {
            addCriterion("total_bg_color >=", value, "totalBgColor");
            return (Criteria) this;
        }

        public Criteria andTotalBgColorLessThan(String value) {
            addCriterion("total_bg_color <", value, "totalBgColor");
            return (Criteria) this;
        }

        public Criteria andTotalBgColorLessThanOrEqualTo(String value) {
            addCriterion("total_bg_color <=", value, "totalBgColor");
            return (Criteria) this;
        }

        public Criteria andTotalBgColorLike(String value) {
            addCriterion("total_bg_color like", value, "totalBgColor");
            return (Criteria) this;
        }

        public Criteria andTotalBgColorNotLike(String value) {
            addCriterion("total_bg_color not like", value, "totalBgColor");
            return (Criteria) this;
        }

        public Criteria andTotalBgColorIn(List<String> values) {
            addCriterion("total_bg_color in", values, "totalBgColor");
            return (Criteria) this;
        }

        public Criteria andTotalBgColorNotIn(List<String> values) {
            addCriterion("total_bg_color not in", values, "totalBgColor");
            return (Criteria) this;
        }

        public Criteria andTotalBgColorBetween(String value1, String value2) {
            addCriterion("total_bg_color between", value1, value2, "totalBgColor");
            return (Criteria) this;
        }

        public Criteria andTotalBgColorNotBetween(String value1, String value2) {
            addCriterion("total_bg_color not between", value1, value2, "totalBgColor");
            return (Criteria) this;
        }

        public Criteria andTimeColBgColorIsNull() {
            addCriterion("time_col_bg_color is null");
            return (Criteria) this;
        }

        public Criteria andTimeColBgColorIsNotNull() {
            addCriterion("time_col_bg_color is not null");
            return (Criteria) this;
        }

        public Criteria andTimeColBgColorEqualTo(String value) {
            addCriterion("time_col_bg_color =", value, "timeColBgColor");
            return (Criteria) this;
        }

        public Criteria andTimeColBgColorNotEqualTo(String value) {
            addCriterion("time_col_bg_color <>", value, "timeColBgColor");
            return (Criteria) this;
        }

        public Criteria andTimeColBgColorGreaterThan(String value) {
            addCriterion("time_col_bg_color >", value, "timeColBgColor");
            return (Criteria) this;
        }

        public Criteria andTimeColBgColorGreaterThanOrEqualTo(String value) {
            addCriterion("time_col_bg_color >=", value, "timeColBgColor");
            return (Criteria) this;
        }

        public Criteria andTimeColBgColorLessThan(String value) {
            addCriterion("time_col_bg_color <", value, "timeColBgColor");
            return (Criteria) this;
        }

        public Criteria andTimeColBgColorLessThanOrEqualTo(String value) {
            addCriterion("time_col_bg_color <=", value, "timeColBgColor");
            return (Criteria) this;
        }

        public Criteria andTimeColBgColorLike(String value) {
            addCriterion("time_col_bg_color like", value, "timeColBgColor");
            return (Criteria) this;
        }

        public Criteria andTimeColBgColorNotLike(String value) {
            addCriterion("time_col_bg_color not like", value, "timeColBgColor");
            return (Criteria) this;
        }

        public Criteria andTimeColBgColorIn(List<String> values) {
            addCriterion("time_col_bg_color in", values, "timeColBgColor");
            return (Criteria) this;
        }

        public Criteria andTimeColBgColorNotIn(List<String> values) {
            addCriterion("time_col_bg_color not in", values, "timeColBgColor");
            return (Criteria) this;
        }

        public Criteria andTimeColBgColorBetween(String value1, String value2) {
            addCriterion("time_col_bg_color between", value1, value2, "timeColBgColor");
            return (Criteria) this;
        }

        public Criteria andTimeColBgColorNotBetween(String value1, String value2) {
            addCriterion("time_col_bg_color not between", value1, value2, "timeColBgColor");
            return (Criteria) this;
        }

        public Criteria andSelectedBgColorIsNull() {
            addCriterion("selected_bg_color is null");
            return (Criteria) this;
        }

        public Criteria andSelectedBgColorIsNotNull() {
            addCriterion("selected_bg_color is not null");
            return (Criteria) this;
        }

        public Criteria andSelectedBgColorEqualTo(String value) {
            addCriterion("selected_bg_color =", value, "selectedBgColor");
            return (Criteria) this;
        }

        public Criteria andSelectedBgColorNotEqualTo(String value) {
            addCriterion("selected_bg_color <>", value, "selectedBgColor");
            return (Criteria) this;
        }

        public Criteria andSelectedBgColorGreaterThan(String value) {
            addCriterion("selected_bg_color >", value, "selectedBgColor");
            return (Criteria) this;
        }

        public Criteria andSelectedBgColorGreaterThanOrEqualTo(String value) {
            addCriterion("selected_bg_color >=", value, "selectedBgColor");
            return (Criteria) this;
        }

        public Criteria andSelectedBgColorLessThan(String value) {
            addCriterion("selected_bg_color <", value, "selectedBgColor");
            return (Criteria) this;
        }

        public Criteria andSelectedBgColorLessThanOrEqualTo(String value) {
            addCriterion("selected_bg_color <=", value, "selectedBgColor");
            return (Criteria) this;
        }

        public Criteria andSelectedBgColorLike(String value) {
            addCriterion("selected_bg_color like", value, "selectedBgColor");
            return (Criteria) this;
        }

        public Criteria andSelectedBgColorNotLike(String value) {
            addCriterion("selected_bg_color not like", value, "selectedBgColor");
            return (Criteria) this;
        }

        public Criteria andSelectedBgColorIn(List<String> values) {
            addCriterion("selected_bg_color in", values, "selectedBgColor");
            return (Criteria) this;
        }

        public Criteria andSelectedBgColorNotIn(List<String> values) {
            addCriterion("selected_bg_color not in", values, "selectedBgColor");
            return (Criteria) this;
        }

        public Criteria andSelectedBgColorBetween(String value1, String value2) {
            addCriterion("selected_bg_color between", value1, value2, "selectedBgColor");
            return (Criteria) this;
        }

        public Criteria andSelectedBgColorNotBetween(String value1, String value2) {
            addCriterion("selected_bg_color not between", value1, value2, "selectedBgColor");
            return (Criteria) this;
        }

        public Criteria andBtnDefaultBgColorIsNull() {
            addCriterion("btn_default_bg_color is null");
            return (Criteria) this;
        }

        public Criteria andBtnDefaultBgColorIsNotNull() {
            addCriterion("btn_default_bg_color is not null");
            return (Criteria) this;
        }

        public Criteria andBtnDefaultBgColorEqualTo(String value) {
            addCriterion("btn_default_bg_color =", value, "btnDefaultBgColor");
            return (Criteria) this;
        }

        public Criteria andBtnDefaultBgColorNotEqualTo(String value) {
            addCriterion("btn_default_bg_color <>", value, "btnDefaultBgColor");
            return (Criteria) this;
        }

        public Criteria andBtnDefaultBgColorGreaterThan(String value) {
            addCriterion("btn_default_bg_color >", value, "btnDefaultBgColor");
            return (Criteria) this;
        }

        public Criteria andBtnDefaultBgColorGreaterThanOrEqualTo(String value) {
            addCriterion("btn_default_bg_color >=", value, "btnDefaultBgColor");
            return (Criteria) this;
        }

        public Criteria andBtnDefaultBgColorLessThan(String value) {
            addCriterion("btn_default_bg_color <", value, "btnDefaultBgColor");
            return (Criteria) this;
        }

        public Criteria andBtnDefaultBgColorLessThanOrEqualTo(String value) {
            addCriterion("btn_default_bg_color <=", value, "btnDefaultBgColor");
            return (Criteria) this;
        }

        public Criteria andBtnDefaultBgColorLike(String value) {
            addCriterion("btn_default_bg_color like", value, "btnDefaultBgColor");
            return (Criteria) this;
        }

        public Criteria andBtnDefaultBgColorNotLike(String value) {
            addCriterion("btn_default_bg_color not like", value, "btnDefaultBgColor");
            return (Criteria) this;
        }

        public Criteria andBtnDefaultBgColorIn(List<String> values) {
            addCriterion("btn_default_bg_color in", values, "btnDefaultBgColor");
            return (Criteria) this;
        }

        public Criteria andBtnDefaultBgColorNotIn(List<String> values) {
            addCriterion("btn_default_bg_color not in", values, "btnDefaultBgColor");
            return (Criteria) this;
        }

        public Criteria andBtnDefaultBgColorBetween(String value1, String value2) {
            addCriterion("btn_default_bg_color between", value1, value2, "btnDefaultBgColor");
            return (Criteria) this;
        }

        public Criteria andBtnDefaultBgColorNotBetween(String value1, String value2) {
            addCriterion("btn_default_bg_color not between", value1, value2, "btnDefaultBgColor");
            return (Criteria) this;
        }

        public Criteria andBtnSelectedBgColorIsNull() {
            addCriterion("btn_selected_bg_color is null");
            return (Criteria) this;
        }

        public Criteria andBtnSelectedBgColorIsNotNull() {
            addCriterion("btn_selected_bg_color is not null");
            return (Criteria) this;
        }

        public Criteria andBtnSelectedBgColorEqualTo(String value) {
            addCriterion("btn_selected_bg_color =", value, "btnSelectedBgColor");
            return (Criteria) this;
        }

        public Criteria andBtnSelectedBgColorNotEqualTo(String value) {
            addCriterion("btn_selected_bg_color <>", value, "btnSelectedBgColor");
            return (Criteria) this;
        }

        public Criteria andBtnSelectedBgColorGreaterThan(String value) {
            addCriterion("btn_selected_bg_color >", value, "btnSelectedBgColor");
            return (Criteria) this;
        }

        public Criteria andBtnSelectedBgColorGreaterThanOrEqualTo(String value) {
            addCriterion("btn_selected_bg_color >=", value, "btnSelectedBgColor");
            return (Criteria) this;
        }

        public Criteria andBtnSelectedBgColorLessThan(String value) {
            addCriterion("btn_selected_bg_color <", value, "btnSelectedBgColor");
            return (Criteria) this;
        }

        public Criteria andBtnSelectedBgColorLessThanOrEqualTo(String value) {
            addCriterion("btn_selected_bg_color <=", value, "btnSelectedBgColor");
            return (Criteria) this;
        }

        public Criteria andBtnSelectedBgColorLike(String value) {
            addCriterion("btn_selected_bg_color like", value, "btnSelectedBgColor");
            return (Criteria) this;
        }

        public Criteria andBtnSelectedBgColorNotLike(String value) {
            addCriterion("btn_selected_bg_color not like", value, "btnSelectedBgColor");
            return (Criteria) this;
        }

        public Criteria andBtnSelectedBgColorIn(List<String> values) {
            addCriterion("btn_selected_bg_color in", values, "btnSelectedBgColor");
            return (Criteria) this;
        }

        public Criteria andBtnSelectedBgColorNotIn(List<String> values) {
            addCriterion("btn_selected_bg_color not in", values, "btnSelectedBgColor");
            return (Criteria) this;
        }

        public Criteria andBtnSelectedBgColorBetween(String value1, String value2) {
            addCriterion("btn_selected_bg_color between", value1, value2, "btnSelectedBgColor");
            return (Criteria) this;
        }

        public Criteria andBtnSelectedBgColorNotBetween(String value1, String value2) {
            addCriterion("btn_selected_bg_color not between", value1, value2, "btnSelectedBgColor");
            return (Criteria) this;
        }

        public Criteria andCouponBgColorIsNull() {
            addCriterion("coupon_bg_color is null");
            return (Criteria) this;
        }

        public Criteria andCouponBgColorIsNotNull() {
            addCriterion("coupon_bg_color is not null");
            return (Criteria) this;
        }

        public Criteria andCouponBgColorEqualTo(String value) {
            addCriterion("coupon_bg_color =", value, "couponBgColor");
            return (Criteria) this;
        }

        public Criteria andCouponBgColorNotEqualTo(String value) {
            addCriterion("coupon_bg_color <>", value, "couponBgColor");
            return (Criteria) this;
        }

        public Criteria andCouponBgColorGreaterThan(String value) {
            addCriterion("coupon_bg_color >", value, "couponBgColor");
            return (Criteria) this;
        }

        public Criteria andCouponBgColorGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_bg_color >=", value, "couponBgColor");
            return (Criteria) this;
        }

        public Criteria andCouponBgColorLessThan(String value) {
            addCriterion("coupon_bg_color <", value, "couponBgColor");
            return (Criteria) this;
        }

        public Criteria andCouponBgColorLessThanOrEqualTo(String value) {
            addCriterion("coupon_bg_color <=", value, "couponBgColor");
            return (Criteria) this;
        }

        public Criteria andCouponBgColorLike(String value) {
            addCriterion("coupon_bg_color like", value, "couponBgColor");
            return (Criteria) this;
        }

        public Criteria andCouponBgColorNotLike(String value) {
            addCriterion("coupon_bg_color not like", value, "couponBgColor");
            return (Criteria) this;
        }

        public Criteria andCouponBgColorIn(List<String> values) {
            addCriterion("coupon_bg_color in", values, "couponBgColor");
            return (Criteria) this;
        }

        public Criteria andCouponBgColorNotIn(List<String> values) {
            addCriterion("coupon_bg_color not in", values, "couponBgColor");
            return (Criteria) this;
        }

        public Criteria andCouponBgColorBetween(String value1, String value2) {
            addCriterion("coupon_bg_color between", value1, value2, "couponBgColor");
            return (Criteria) this;
        }

        public Criteria andCouponBgColorNotBetween(String value1, String value2) {
            addCriterion("coupon_bg_color not between", value1, value2, "couponBgColor");
            return (Criteria) this;
        }

        public Criteria andDefaultFontColorIsNull() {
            addCriterion("default_font_color is null");
            return (Criteria) this;
        }

        public Criteria andDefaultFontColorIsNotNull() {
            addCriterion("default_font_color is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultFontColorEqualTo(String value) {
            addCriterion("default_font_color =", value, "defaultFontColor");
            return (Criteria) this;
        }

        public Criteria andDefaultFontColorNotEqualTo(String value) {
            addCriterion("default_font_color <>", value, "defaultFontColor");
            return (Criteria) this;
        }

        public Criteria andDefaultFontColorGreaterThan(String value) {
            addCriterion("default_font_color >", value, "defaultFontColor");
            return (Criteria) this;
        }

        public Criteria andDefaultFontColorGreaterThanOrEqualTo(String value) {
            addCriterion("default_font_color >=", value, "defaultFontColor");
            return (Criteria) this;
        }

        public Criteria andDefaultFontColorLessThan(String value) {
            addCriterion("default_font_color <", value, "defaultFontColor");
            return (Criteria) this;
        }

        public Criteria andDefaultFontColorLessThanOrEqualTo(String value) {
            addCriterion("default_font_color <=", value, "defaultFontColor");
            return (Criteria) this;
        }

        public Criteria andDefaultFontColorLike(String value) {
            addCriterion("default_font_color like", value, "defaultFontColor");
            return (Criteria) this;
        }

        public Criteria andDefaultFontColorNotLike(String value) {
            addCriterion("default_font_color not like", value, "defaultFontColor");
            return (Criteria) this;
        }

        public Criteria andDefaultFontColorIn(List<String> values) {
            addCriterion("default_font_color in", values, "defaultFontColor");
            return (Criteria) this;
        }

        public Criteria andDefaultFontColorNotIn(List<String> values) {
            addCriterion("default_font_color not in", values, "defaultFontColor");
            return (Criteria) this;
        }

        public Criteria andDefaultFontColorBetween(String value1, String value2) {
            addCriterion("default_font_color between", value1, value2, "defaultFontColor");
            return (Criteria) this;
        }

        public Criteria andDefaultFontColorNotBetween(String value1, String value2) {
            addCriterion("default_font_color not between", value1, value2, "defaultFontColor");
            return (Criteria) this;
        }

        public Criteria andSelectedFontColorIsNull() {
            addCriterion("selected_font_color is null");
            return (Criteria) this;
        }

        public Criteria andSelectedFontColorIsNotNull() {
            addCriterion("selected_font_color is not null");
            return (Criteria) this;
        }

        public Criteria andSelectedFontColorEqualTo(String value) {
            addCriterion("selected_font_color =", value, "selectedFontColor");
            return (Criteria) this;
        }

        public Criteria andSelectedFontColorNotEqualTo(String value) {
            addCriterion("selected_font_color <>", value, "selectedFontColor");
            return (Criteria) this;
        }

        public Criteria andSelectedFontColorGreaterThan(String value) {
            addCriterion("selected_font_color >", value, "selectedFontColor");
            return (Criteria) this;
        }

        public Criteria andSelectedFontColorGreaterThanOrEqualTo(String value) {
            addCriterion("selected_font_color >=", value, "selectedFontColor");
            return (Criteria) this;
        }

        public Criteria andSelectedFontColorLessThan(String value) {
            addCriterion("selected_font_color <", value, "selectedFontColor");
            return (Criteria) this;
        }

        public Criteria andSelectedFontColorLessThanOrEqualTo(String value) {
            addCriterion("selected_font_color <=", value, "selectedFontColor");
            return (Criteria) this;
        }

        public Criteria andSelectedFontColorLike(String value) {
            addCriterion("selected_font_color like", value, "selectedFontColor");
            return (Criteria) this;
        }

        public Criteria andSelectedFontColorNotLike(String value) {
            addCriterion("selected_font_color not like", value, "selectedFontColor");
            return (Criteria) this;
        }

        public Criteria andSelectedFontColorIn(List<String> values) {
            addCriterion("selected_font_color in", values, "selectedFontColor");
            return (Criteria) this;
        }

        public Criteria andSelectedFontColorNotIn(List<String> values) {
            addCriterion("selected_font_color not in", values, "selectedFontColor");
            return (Criteria) this;
        }

        public Criteria andSelectedFontColorBetween(String value1, String value2) {
            addCriterion("selected_font_color between", value1, value2, "selectedFontColor");
            return (Criteria) this;
        }

        public Criteria andSelectedFontColorNotBetween(String value1, String value2) {
            addCriterion("selected_font_color not between", value1, value2, "selectedFontColor");
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