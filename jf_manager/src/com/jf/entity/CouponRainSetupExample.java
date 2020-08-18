package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jf.entity.CouponRainExample.Criteria;

public class CouponRainSetupExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public CouponRainSetupExample() {
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

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralIsNull() {
            addCriterion("include_integral is null");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralIsNotNull() {
            addCriterion("include_integral is not null");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralEqualTo(String value) {
            addCriterion("include_integral =", value, "includeIntegral");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralNotEqualTo(String value) {
            addCriterion("include_integral <>", value, "includeIntegral");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralGreaterThan(String value) {
            addCriterion("include_integral >", value, "includeIntegral");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralGreaterThanOrEqualTo(String value) {
            addCriterion("include_integral >=", value, "includeIntegral");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralLessThan(String value) {
            addCriterion("include_integral <", value, "includeIntegral");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralLessThanOrEqualTo(String value) {
            addCriterion("include_integral <=", value, "includeIntegral");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralLike(String value) {
            addCriterion("include_integral like", value, "includeIntegral");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralNotLike(String value) {
            addCriterion("include_integral not like", value, "includeIntegral");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralIn(List<String> values) {
            addCriterion("include_integral in", values, "includeIntegral");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralNotIn(List<String> values) {
            addCriterion("include_integral not in", values, "includeIntegral");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralBetween(String value1, String value2) {
            addCriterion("include_integral between", value1, value2, "includeIntegral");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralNotBetween(String value1, String value2) {
            addCriterion("include_integral not between", value1, value2, "includeIntegral");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralPercentIsNull() {
            addCriterion("include_integral_percent is null");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralPercentIsNotNull() {
            addCriterion("include_integral_percent is not null");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralPercentEqualTo(BigDecimal value) {
            addCriterion("include_integral_percent =", value, "includeIntegralPercent");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralPercentNotEqualTo(BigDecimal value) {
            addCriterion("include_integral_percent <>", value, "includeIntegralPercent");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralPercentGreaterThan(BigDecimal value) {
            addCriterion("include_integral_percent >", value, "includeIntegralPercent");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralPercentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("include_integral_percent >=", value, "includeIntegralPercent");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralPercentLessThan(BigDecimal value) {
            addCriterion("include_integral_percent <", value, "includeIntegralPercent");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralPercentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("include_integral_percent <=", value, "includeIntegralPercent");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralPercentIn(List<BigDecimal> values) {
            addCriterion("include_integral_percent in", values, "includeIntegralPercent");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralPercentNotIn(List<BigDecimal> values) {
            addCriterion("include_integral_percent not in", values, "includeIntegralPercent");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralPercentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("include_integral_percent between", value1, value2, "includeIntegralPercent");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralPercentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("include_integral_percent not between", value1, value2, "includeIntegralPercent");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralMinIsNull() {
            addCriterion("include_integral_min is null");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralMinIsNotNull() {
            addCriterion("include_integral_min is not null");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralMinEqualTo(Integer value) {
            addCriterion("include_integral_min =", value, "includeIntegralMin");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralMinNotEqualTo(Integer value) {
            addCriterion("include_integral_min <>", value, "includeIntegralMin");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralMinGreaterThan(Integer value) {
            addCriterion("include_integral_min >", value, "includeIntegralMin");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralMinGreaterThanOrEqualTo(Integer value) {
            addCriterion("include_integral_min >=", value, "includeIntegralMin");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralMinLessThan(Integer value) {
            addCriterion("include_integral_min <", value, "includeIntegralMin");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralMinLessThanOrEqualTo(Integer value) {
            addCriterion("include_integral_min <=", value, "includeIntegralMin");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralMinIn(List<Integer> values) {
            addCriterion("include_integral_min in", values, "includeIntegralMin");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralMinNotIn(List<Integer> values) {
            addCriterion("include_integral_min not in", values, "includeIntegralMin");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralMinBetween(Integer value1, Integer value2) {
            addCriterion("include_integral_min between", value1, value2, "includeIntegralMin");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralMinNotBetween(Integer value1, Integer value2) {
            addCriterion("include_integral_min not between", value1, value2, "includeIntegralMin");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralMaxIsNull() {
            addCriterion("include_integral_max is null");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralMaxIsNotNull() {
            addCriterion("include_integral_max is not null");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralMaxEqualTo(Integer value) {
            addCriterion("include_integral_max =", value, "includeIntegralMax");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralMaxNotEqualTo(Integer value) {
            addCriterion("include_integral_max <>", value, "includeIntegralMax");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralMaxGreaterThan(Integer value) {
            addCriterion("include_integral_max >", value, "includeIntegralMax");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralMaxGreaterThanOrEqualTo(Integer value) {
            addCriterion("include_integral_max >=", value, "includeIntegralMax");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralMaxLessThan(Integer value) {
            addCriterion("include_integral_max <", value, "includeIntegralMax");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralMaxLessThanOrEqualTo(Integer value) {
            addCriterion("include_integral_max <=", value, "includeIntegralMax");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralMaxIn(List<Integer> values) {
            addCriterion("include_integral_max in", values, "includeIntegralMax");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralMaxNotIn(List<Integer> values) {
            addCriterion("include_integral_max not in", values, "includeIntegralMax");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralMaxBetween(Integer value1, Integer value2) {
            addCriterion("include_integral_max between", value1, value2, "includeIntegralMax");
            return (Criteria) this;
        }

        public Criteria andIncludeIntegralMaxNotBetween(Integer value1, Integer value2) {
            addCriterion("include_integral_max not between", value1, value2, "includeIntegralMax");
            return (Criteria) this;
        }

        public Criteria andIncludeProductCouponIsNull() {
            addCriterion("include_product_coupon is null");
            return (Criteria) this;
        }

        public Criteria andIncludeProductCouponIsNotNull() {
            addCriterion("include_product_coupon is not null");
            return (Criteria) this;
        }

        public Criteria andIncludeProductCouponEqualTo(String value) {
            addCriterion("include_product_coupon =", value, "includeProductCoupon");
            return (Criteria) this;
        }

        public Criteria andIncludeProductCouponNotEqualTo(String value) {
            addCriterion("include_product_coupon <>", value, "includeProductCoupon");
            return (Criteria) this;
        }

        public Criteria andIncludeProductCouponGreaterThan(String value) {
            addCriterion("include_product_coupon >", value, "includeProductCoupon");
            return (Criteria) this;
        }

        public Criteria andIncludeProductCouponGreaterThanOrEqualTo(String value) {
            addCriterion("include_product_coupon >=", value, "includeProductCoupon");
            return (Criteria) this;
        }

        public Criteria andIncludeProductCouponLessThan(String value) {
            addCriterion("include_product_coupon <", value, "includeProductCoupon");
            return (Criteria) this;
        }

        public Criteria andIncludeProductCouponLessThanOrEqualTo(String value) {
            addCriterion("include_product_coupon <=", value, "includeProductCoupon");
            return (Criteria) this;
        }

        public Criteria andIncludeProductCouponLike(String value) {
            addCriterion("include_product_coupon like", value, "includeProductCoupon");
            return (Criteria) this;
        }

        public Criteria andIncludeProductCouponNotLike(String value) {
            addCriterion("include_product_coupon not like", value, "includeProductCoupon");
            return (Criteria) this;
        }

        public Criteria andIncludeProductCouponIn(List<String> values) {
            addCriterion("include_product_coupon in", values, "includeProductCoupon");
            return (Criteria) this;
        }

        public Criteria andIncludeProductCouponNotIn(List<String> values) {
            addCriterion("include_product_coupon not in", values, "includeProductCoupon");
            return (Criteria) this;
        }

        public Criteria andIncludeProductCouponBetween(String value1, String value2) {
            addCriterion("include_product_coupon between", value1, value2, "includeProductCoupon");
            return (Criteria) this;
        }

        public Criteria andIncludeProductCouponNotBetween(String value1, String value2) {
            addCriterion("include_product_coupon not between", value1, value2, "includeProductCoupon");
            return (Criteria) this;
        }

        public Criteria andIncludeProductCouponPercentIsNull() {
            addCriterion("include_product_coupon_percent is null");
            return (Criteria) this;
        }

        public Criteria andIncludeProductCouponPercentIsNotNull() {
            addCriterion("include_product_coupon_percent is not null");
            return (Criteria) this;
        }

        public Criteria andIncludeProductCouponPercentEqualTo(BigDecimal value) {
            addCriterion("include_product_coupon_percent =", value, "includeProductCouponPercent");
            return (Criteria) this;
        }

        public Criteria andIncludeProductCouponPercentNotEqualTo(BigDecimal value) {
            addCriterion("include_product_coupon_percent <>", value, "includeProductCouponPercent");
            return (Criteria) this;
        }

        public Criteria andIncludeProductCouponPercentGreaterThan(BigDecimal value) {
            addCriterion("include_product_coupon_percent >", value, "includeProductCouponPercent");
            return (Criteria) this;
        }

        public Criteria andIncludeProductCouponPercentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("include_product_coupon_percent >=", value, "includeProductCouponPercent");
            return (Criteria) this;
        }

        public Criteria andIncludeProductCouponPercentLessThan(BigDecimal value) {
            addCriterion("include_product_coupon_percent <", value, "includeProductCouponPercent");
            return (Criteria) this;
        }

        public Criteria andIncludeProductCouponPercentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("include_product_coupon_percent <=", value, "includeProductCouponPercent");
            return (Criteria) this;
        }

        public Criteria andIncludeProductCouponPercentIn(List<BigDecimal> values) {
            addCriterion("include_product_coupon_percent in", values, "includeProductCouponPercent");
            return (Criteria) this;
        }

        public Criteria andIncludeProductCouponPercentNotIn(List<BigDecimal> values) {
            addCriterion("include_product_coupon_percent not in", values, "includeProductCouponPercent");
            return (Criteria) this;
        }

        public Criteria andIncludeProductCouponPercentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("include_product_coupon_percent between", value1, value2, "includeProductCouponPercent");
            return (Criteria) this;
        }

        public Criteria andIncludeProductCouponPercentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("include_product_coupon_percent not between", value1, value2, "includeProductCouponPercent");
            return (Criteria) this;
        }

        public Criteria andRecBeginDateIsNull() {
            addCriterion("rec_begin_date is null");
            return (Criteria) this;
        }

        public Criteria andRecBeginDateIsNotNull() {
            addCriterion("rec_begin_date is not null");
            return (Criteria) this;
        }

        public Criteria andRecBeginDateEqualTo(Date value) {
            addCriterion("rec_begin_date =", value, "recBeginDate");
            return (Criteria) this;
        }

        public Criteria andRecBeginDateNotEqualTo(Date value) {
            addCriterion("rec_begin_date <>", value, "recBeginDate");
            return (Criteria) this;
        }

        public Criteria andRecBeginDateGreaterThan(Date value) {
            addCriterion("rec_begin_date >", value, "recBeginDate");
            return (Criteria) this;
        }

        public Criteria andRecBeginDateGreaterThanOrEqualTo(Date value) {
            addCriterion("rec_begin_date >=", value, "recBeginDate");
            return (Criteria) this;
        }

        public Criteria andRecBeginDateLessThan(Date value) {
            addCriterion("rec_begin_date <", value, "recBeginDate");
            return (Criteria) this;
        }

        public Criteria andRecBeginDateLessThanOrEqualTo(Date value) {
            addCriterion("rec_begin_date <=", value, "recBeginDate");
            return (Criteria) this;
        }

        public Criteria andRecBeginDateIn(List<Date> values) {
            addCriterion("rec_begin_date in", values, "recBeginDate");
            return (Criteria) this;
        }

        public Criteria andRecBeginDateNotIn(List<Date> values) {
            addCriterion("rec_begin_date not in", values, "recBeginDate");
            return (Criteria) this;
        }

        public Criteria andRecBeginDateBetween(Date value1, Date value2) {
            addCriterion("rec_begin_date between", value1, value2, "recBeginDate");
            return (Criteria) this;
        }

        public Criteria andRecBeginDateNotBetween(Date value1, Date value2) {
            addCriterion("rec_begin_date not between", value1, value2, "recBeginDate");
            return (Criteria) this;
        }

        public Criteria andRecEndDateIsNull() {
            addCriterion("rec_end_date is null");
            return (Criteria) this;
        }

        public Criteria andRecEndDateIsNotNull() {
            addCriterion("rec_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andRecEndDateEqualTo(Date value) {
            addCriterion("rec_end_date =", value, "recEndDate");
            return (Criteria) this;
        }

        public Criteria andRecEndDateNotEqualTo(Date value) {
            addCriterion("rec_end_date <>", value, "recEndDate");
            return (Criteria) this;
        }

        public Criteria andRecEndDateGreaterThan(Date value) {
            addCriterion("rec_end_date >", value, "recEndDate");
            return (Criteria) this;
        }

        public Criteria andRecEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("rec_end_date >=", value, "recEndDate");
            return (Criteria) this;
        }

        public Criteria andRecEndDateLessThan(Date value) {
            addCriterion("rec_end_date <", value, "recEndDate");
            return (Criteria) this;
        }

        public Criteria andRecEndDateLessThanOrEqualTo(Date value) {
            addCriterion("rec_end_date <=", value, "recEndDate");
            return (Criteria) this;
        }

        public Criteria andRecEndDateIn(List<Date> values) {
            addCriterion("rec_end_date in", values, "recEndDate");
            return (Criteria) this;
        }

        public Criteria andRecEndDateNotIn(List<Date> values) {
            addCriterion("rec_end_date not in", values, "recEndDate");
            return (Criteria) this;
        }

        public Criteria andRecEndDateBetween(Date value1, Date value2) {
            addCriterion("rec_end_date between", value1, value2, "recEndDate");
            return (Criteria) this;
        }

        public Criteria andRecEndDateNotBetween(Date value1, Date value2) {
            addCriterion("rec_end_date not between", value1, value2, "recEndDate");
            return (Criteria) this;
        }

        public Criteria andLimitRecCountIsNull() {
            addCriterion("limit_rec_count is null");
            return (Criteria) this;
        }

        public Criteria andLimitRecCountIsNotNull() {
            addCriterion("limit_rec_count is not null");
            return (Criteria) this;
        }

        public Criteria andLimitRecCountEqualTo(Integer value) {
            addCriterion("limit_rec_count =", value, "limitRecCount");
            return (Criteria) this;
        }

        public Criteria andLimitRecCountNotEqualTo(Integer value) {
            addCriterion("limit_rec_count <>", value, "limitRecCount");
            return (Criteria) this;
        }

        public Criteria andLimitRecCountGreaterThan(Integer value) {
            addCriterion("limit_rec_count >", value, "limitRecCount");
            return (Criteria) this;
        }

        public Criteria andLimitRecCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("limit_rec_count >=", value, "limitRecCount");
            return (Criteria) this;
        }

        public Criteria andLimitRecCountLessThan(Integer value) {
            addCriterion("limit_rec_count <", value, "limitRecCount");
            return (Criteria) this;
        }

        public Criteria andLimitRecCountLessThanOrEqualTo(Integer value) {
            addCriterion("limit_rec_count <=", value, "limitRecCount");
            return (Criteria) this;
        }

        public Criteria andLimitRecCountIn(List<Integer> values) {
            addCriterion("limit_rec_count in", values, "limitRecCount");
            return (Criteria) this;
        }

        public Criteria andLimitRecCountNotIn(List<Integer> values) {
            addCriterion("limit_rec_count not in", values, "limitRecCount");
            return (Criteria) this;
        }

        public Criteria andLimitRecCountBetween(Integer value1, Integer value2) {
            addCriterion("limit_rec_count between", value1, value2, "limitRecCount");
            return (Criteria) this;
        }

        public Criteria andLimitRecCountNotBetween(Integer value1, Integer value2) {
            addCriterion("limit_rec_count not between", value1, value2, "limitRecCount");
            return (Criteria) this;
        }
        public Criteria andPicIsNull() {
            addCriterion("pic is null");
            return (Criteria) this;
        }

        public Criteria andPicIsNotNull() {
            addCriterion("pic is not null");
            return (Criteria) this;
        }

        public Criteria andPicEqualTo(String value) {
            addCriterion("pic =", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotEqualTo(String value) {
            addCriterion("pic <>", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThan(String value) {
            addCriterion("pic >", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThanOrEqualTo(String value) {
            addCriterion("pic >=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThan(String value) {
            addCriterion("pic <", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThanOrEqualTo(String value) {
            addCriterion("pic <=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLike(String value) {
            addCriterion("pic like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotLike(String value) {
            addCriterion("pic not like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicIn(List<String> values) {
            addCriterion("pic in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotIn(List<String> values) {
            addCriterion("pic not in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicBetween(String value1, String value2) {
            addCriterion("pic between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotBetween(String value1, String value2) {
            addCriterion("pic not between", value1, value2, "pic");
            return (Criteria) this;
        }
        public Criteria andIncludeSaleIsNull() {
            addCriterion("include_sale is null");
            return (Criteria) this;
        }

        public Criteria andIncludeSaleIsNotNull() {
            addCriterion("include_sale is not null");
            return (Criteria) this;
        }

        public Criteria andIncludeSaleEqualTo(String value) {
            addCriterion("include_sale =", value, "includeSale");
            return (Criteria) this;
        }

        public Criteria andIncludeSaleNotEqualTo(String value) {
            addCriterion("include_sale <>", value, "includeSale");
            return (Criteria) this;
        }

        public Criteria andIncludeSaleGreaterThan(String value) {
            addCriterion("include_sale >", value, "includeSale");
            return (Criteria) this;
        }

        public Criteria andIncludeSaleGreaterThanOrEqualTo(String value) {
            addCriterion("include_sale >=", value, "includeSale");
            return (Criteria) this;
        }

        public Criteria andIncludeSaleLessThan(String value) {
            addCriterion("include_sale <", value, "includeSale");
            return (Criteria) this;
        }

        public Criteria andIncludeSaleLessThanOrEqualTo(String value) {
            addCriterion("include_sale <=", value, "includeSale");
            return (Criteria) this;
        }

        public Criteria andIncludeSaleLike(String value) {
            addCriterion("include_sale like", value, "includeSale");
            return (Criteria) this;
        }

        public Criteria andIncludeSaleNotLike(String value) {
            addCriterion("include_sale not like", value, "includeSale");
            return (Criteria) this;
        }

        public Criteria andIncludeSaleIn(List<String> values) {
            addCriterion("include_sale in", values, "includeSale");
            return (Criteria) this;
        }

        public Criteria andIncludeSaleNotIn(List<String> values) {
            addCriterion("include_sale not in", values, "includeSale");
            return (Criteria) this;
        }

        public Criteria andIncludeSaleBetween(String value1, String value2) {
            addCriterion("include_sale between", value1, value2, "includeSale");
            return (Criteria) this;
        }

        public Criteria andIncludeSaleNotBetween(String value1, String value2) {
            addCriterion("include_sale not between", value1, value2, "includeSale");
            return (Criteria) this;
        }
        public Criteria andIncludeSalePercentIsNull() {
            addCriterion("include_sale_percent is null");
            return (Criteria) this;
        }

        public Criteria andIncludeSalePercentIsNotNull() {
            addCriterion("include_sale_percent is not null");
            return (Criteria) this;
        }

        public Criteria andIncludeSalePercentEqualTo(BigDecimal value) {
            addCriterion("include_sale_percent =", value, "includeSalePercent");
            return (Criteria) this;
        }

        public Criteria andIncludeSalePercentNotEqualTo(BigDecimal value) {
            addCriterion("include_sale_percent <>", value, "includeSalePercent");
            return (Criteria) this;
        }

        public Criteria andIncludeSalePercentGreaterThan(BigDecimal value) {
            addCriterion("include_sale_percent >", value, "includeSalePercent");
            return (Criteria) this;
        }

        public Criteria andIncludeSalePercentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("include_sale_percent >=", value, "includeSalePercent");
            return (Criteria) this;
        }

        public Criteria andIncludeSalePercentLessThan(BigDecimal value) {
            addCriterion("include_sale_percent <", value, "includeSalePercent");
            return (Criteria) this;
        }

        public Criteria andIncludeSalePercentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("include_sale_percent <=", value, "includeSalePercent");
            return (Criteria) this;
        }

        public Criteria andIncludeSalePercentIn(List<BigDecimal> values) {
            addCriterion("include_sale_percent in", values, "includeSalePercent");
            return (Criteria) this;
        }

        public Criteria andIncludeSalePercentNotIn(List<BigDecimal> values) {
            addCriterion("include_sale_percent not in", values, "includeSalePercent");
            return (Criteria) this;
        }

        public Criteria andIncludeSalePercentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("include_sale_percent between", value1, value2, "includeSalePercent");
            return (Criteria) this;
        }

        public Criteria andIncludeSalePercentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("include_sale_percent not between", value1, value2, "includeSalePercent");
            return (Criteria) this;
        }
        
        public Criteria andIncludePlatformCouponIsNull() {
            addCriterion("include_platform_coupon is null");
            return (Criteria) this;
        }

        public Criteria andIncludePlatformCouponIsNotNull() {
            addCriterion("include_platform_coupon is not null");
            return (Criteria) this;
        }

        public Criteria andIncludePlatformCouponEqualTo(String value) {
            addCriterion("include_platform_coupon =", value, "includePlatformCoupon");
            return (Criteria) this;
        }

        public Criteria andIncludePlatformCouponNotEqualTo(String value) {
            addCriterion("include_platform_coupon <>", value, "includePlatformCoupon");
            return (Criteria) this;
        }

        public Criteria andIncludePlatformCouponGreaterThan(String value) {
            addCriterion("include_platform_coupon >", value, "includePlatformCoupon");
            return (Criteria) this;
        }

        public Criteria andIncludePlatformCouponGreaterThanOrEqualTo(String value) {
            addCriterion("include_platform_coupon >=", value, "includePlatformCoupon");
            return (Criteria) this;
        }

        public Criteria andIncludePlatformCouponLessThan(String value) {
            addCriterion("include_platform_coupon <", value, "includePlatformCoupon");
            return (Criteria) this;
        }

        public Criteria andIncludePlatformCouponLessThanOrEqualTo(String value) {
            addCriterion("include_platform_coupon <=", value, "includePlatformCoupon");
            return (Criteria) this;
        }

        public Criteria andIncludePlatformCouponLike(String value) {
            addCriterion("include_platform_coupon like", value, "includePlatformCoupon");
            return (Criteria) this;
        }

        public Criteria andIncludePlatformCouponNotLike(String value) {
            addCriterion("include_platform_coupon not like", value, "includePlatformCoupon");
            return (Criteria) this;
        }

        public Criteria andIncludePlatformCouponIn(List<String> values) {
            addCriterion("include_platform_coupon in", values, "includePlatformCoupon");
            return (Criteria) this;
        }

        public Criteria andIncludePlatformCouponNotIn(List<String> values) {
            addCriterion("include_platform_coupon not in", values, "includePlatformCoupon");
            return (Criteria) this;
        }

        public Criteria andIncludePlatformCouponBetween(String value1, String value2) {
            addCriterion("include_platform_coupon between", value1, value2, "includePlatformCoupon");
            return (Criteria) this;
        }

        public Criteria andIncludePlatformCouponNotBetween(String value1, String value2) {
            addCriterion("include_platform_coupon not between", value1, value2, "includePlatformCoupon");
            return (Criteria) this;
        }
        public Criteria andIncludePlatformCouponPercentIsNull() {
            addCriterion("include_platform_coupon_percent is null");
            return (Criteria) this;
        }

        public Criteria andIncludePlatformCouponPercentIsNotNull() {
            addCriterion("include_platform_coupon_percent is not null");
            return (Criteria) this;
        }

        public Criteria andIncludePlatformCouponPercentEqualTo(BigDecimal value) {
            addCriterion("include_platform_coupon_percent =", value, "includePlatformCouponPercent");
            return (Criteria) this;
        }

        public Criteria andIncludePlatformCouponPercentNotEqualTo(BigDecimal value) {
            addCriterion("include_platform_coupon_percent <>", value, "includePlatformCouponPercent");
            return (Criteria) this;
        }

        public Criteria andIncludePlatformCouponPercentGreaterThan(BigDecimal value) {
            addCriterion("include_platform_coupon_percent >", value, "includePlatformCouponPercent");
            return (Criteria) this;
        }

        public Criteria andIncludePlatformCouponPercentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("include_platform_coupon_percent >=", value, "includePlatformCouponPercent");
            return (Criteria) this;
        }

        public Criteria andIncludePlatformCouponPercentLessThan(BigDecimal value) {
            addCriterion("include_platform_coupon_percent <", value, "includePlatformCouponPercent");
            return (Criteria) this;
        }

        public Criteria andIncludePlatformCouponPercentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("include_platform_coupon_percent <=", value, "includePlatformCouponPercent");
            return (Criteria) this;
        }

        public Criteria andIncludePlatformCouponPercentIn(List<BigDecimal> values) {
            addCriterion("include_platform_coupon_percent in", values, "includePlatformCouponPercent");
            return (Criteria) this;
        }

        public Criteria andIncludePlatformCouponPercentNotIn(List<BigDecimal> values) {
            addCriterion("include_platform_coupon_percent not in", values, "includePlatformCouponPercent");
            return (Criteria) this;
        }

        public Criteria andIncludePlatformCouponPercentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("include_platform_coupon_percent between", value1, value2, "includePlatformCouponPercent");
            return (Criteria) this;
        }

        public Criteria andIncludePlatformCouponPercentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("include_platform_coupon_percent not between", value1, value2, "includePlatformCouponPercent");
            return (Criteria) this;
        }

        public Criteria andSeqNoIsNull() {
            addCriterion("seq_no is null");
            return (Criteria) this;
        }

        public Criteria andSeqNoIsNotNull() {
            addCriterion("seq_no is not null");
            return (Criteria) this;
        }

        public Criteria andSeqNoEqualTo(Integer value) {
            addCriterion("seq_no =", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoNotEqualTo(Integer value) {
            addCriterion("seq_no <>", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoGreaterThan(Integer value) {
            addCriterion("seq_no >", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("seq_no >=", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoLessThan(Integer value) {
            addCriterion("seq_no <", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoLessThanOrEqualTo(Integer value) {
            addCriterion("seq_no <=", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoIn(List<Integer> values) {
            addCriterion("seq_no in", values, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoNotIn(List<Integer> values) {
            addCriterion("seq_no not in", values, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoBetween(Integer value1, Integer value2) {
            addCriterion("seq_no between", value1, value2, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoNotBetween(Integer value1, Integer value2) {
            addCriterion("seq_no not between", value1, value2, "seqNo");
            return (Criteria) this;
        }
        public Criteria andRedEnvelopeTypeIsNull() {
            addCriterion("red_envelope_type is null");
            return (Criteria) this;
        }

        public Criteria andRedEnvelopeTypeIsNotNull() {
            addCriterion("red_envelope_type is not null");
            return (Criteria) this;
        }

        public Criteria andRedEnvelopeTypeEqualTo(String value) {
            addCriterion("red_envelope_type =", value, "redEnvelopeType");
            return (Criteria) this;
        }

        public Criteria andRedEnvelopeTypeNotEqualTo(String value) {
            addCriterion("red_envelope_type <>", value, "redEnvelopeType");
            return (Criteria) this;
        }

        public Criteria andRedEnvelopeTypeGreaterThan(String value) {
            addCriterion("red_envelope_type >", value, "redEnvelopeType");
            return (Criteria) this;
        }

        public Criteria andRedEnvelopeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("red_envelope_type >=", value, "redEnvelopeType");
            return (Criteria) this;
        }

        public Criteria andRedEnvelopeTypeLessThan(String value) {
            addCriterion("red_envelope_type <", value, "redEnvelopeType");
            return (Criteria) this;
        }

        public Criteria andRedEnvelopeTypeLessThanOrEqualTo(String value) {
            addCriterion("red_envelope_type <=", value, "redEnvelopeType");
            return (Criteria) this;
        }

        public Criteria andRedEnvelopeTypeLike(String value) {
            addCriterion("red_envelope_type like", value, "redEnvelopeType");
            return (Criteria) this;
        }

        public Criteria andRedEnvelopeTypeNotLike(String value) {
            addCriterion("red_envelope_type not like", value, "redEnvelopeType");
            return (Criteria) this;
        }

        public Criteria andRedEnvelopeTypeIn(List<String> values) {
            addCriterion("red_envelope_type in", values, "redEnvelopeType");
            return (Criteria) this;
        }

        public Criteria andRedEnvelopeTypeNotIn(List<String> values) {
            addCriterion("red_envelope_type not in", values, "redEnvelopeType");
            return (Criteria) this;
        }

        public Criteria andRedEnvelopeTypeBetween(String value1, String value2) {
            addCriterion("red_envelope_type between", value1, value2, "redEnvelopeType");
            return (Criteria) this;
        }

        public Criteria andRedEnvelopeTypeNotBetween(String value1, String value2) {
            addCriterion("red_envelope_type not between", value1, value2, "redEnvelopeType");
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