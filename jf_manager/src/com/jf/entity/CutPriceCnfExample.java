package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CutPriceCnfExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public CutPriceCnfExample() {
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

        public Criteria andSingleProductActivityIdIsNull() {
            addCriterion("single_product_activity_id is null");
            return (Criteria) this;
        }

        public Criteria andSingleProductActivityIdIsNotNull() {
            addCriterion("single_product_activity_id is not null");
            return (Criteria) this;
        }

        public Criteria andSingleProductActivityIdEqualTo(Integer value) {
            addCriterion("single_product_activity_id =", value, "singleProductActivityId");
            return (Criteria) this;
        }

        public Criteria andSingleProductActivityIdNotEqualTo(Integer value) {
            addCriterion("single_product_activity_id <>", value, "singleProductActivityId");
            return (Criteria) this;
        }

        public Criteria andSingleProductActivityIdGreaterThan(Integer value) {
            addCriterion("single_product_activity_id >", value, "singleProductActivityId");
            return (Criteria) this;
        }

        public Criteria andSingleProductActivityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("single_product_activity_id >=", value, "singleProductActivityId");
            return (Criteria) this;
        }

        public Criteria andSingleProductActivityIdLessThan(Integer value) {
            addCriterion("single_product_activity_id <", value, "singleProductActivityId");
            return (Criteria) this;
        }

        public Criteria andSingleProductActivityIdLessThanOrEqualTo(Integer value) {
            addCriterion("single_product_activity_id <=", value, "singleProductActivityId");
            return (Criteria) this;
        }

        public Criteria andSingleProductActivityIdIn(List<Integer> values) {
            addCriterion("single_product_activity_id in", values, "singleProductActivityId");
            return (Criteria) this;
        }

        public Criteria andSingleProductActivityIdNotIn(List<Integer> values) {
            addCriterion("single_product_activity_id not in", values, "singleProductActivityId");
            return (Criteria) this;
        }

        public Criteria andSingleProductActivityIdBetween(Integer value1, Integer value2) {
            addCriterion("single_product_activity_id between", value1, value2, "singleProductActivityId");
            return (Criteria) this;
        }

        public Criteria andSingleProductActivityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("single_product_activity_id not between", value1, value2, "singleProductActivityId");
            return (Criteria) this;
        }

        public Criteria andNeedCutToPriceIsNull() {
            addCriterion("need_cut_to_price is null");
            return (Criteria) this;
        }

        public Criteria andNeedCutToPriceIsNotNull() {
            addCriterion("need_cut_to_price is not null");
            return (Criteria) this;
        }

        public Criteria andNeedCutToPriceEqualTo(BigDecimal value) {
            addCriterion("need_cut_to_price =", value, "needCutToPrice");
            return (Criteria) this;
        }

        public Criteria andNeedCutToPriceNotEqualTo(BigDecimal value) {
            addCriterion("need_cut_to_price <>", value, "needCutToPrice");
            return (Criteria) this;
        }

        public Criteria andNeedCutToPriceGreaterThan(BigDecimal value) {
            addCriterion("need_cut_to_price >", value, "needCutToPrice");
            return (Criteria) this;
        }

        public Criteria andNeedCutToPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("need_cut_to_price >=", value, "needCutToPrice");
            return (Criteria) this;
        }

        public Criteria andNeedCutToPriceLessThan(BigDecimal value) {
            addCriterion("need_cut_to_price <", value, "needCutToPrice");
            return (Criteria) this;
        }

        public Criteria andNeedCutToPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("need_cut_to_price <=", value, "needCutToPrice");
            return (Criteria) this;
        }

        public Criteria andNeedCutToPriceIn(List<BigDecimal> values) {
            addCriterion("need_cut_to_price in", values, "needCutToPrice");
            return (Criteria) this;
        }

        public Criteria andNeedCutToPriceNotIn(List<BigDecimal> values) {
            addCriterion("need_cut_to_price not in", values, "needCutToPrice");
            return (Criteria) this;
        }

        public Criteria andNeedCutToPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("need_cut_to_price between", value1, value2, "needCutToPrice");
            return (Criteria) this;
        }

        public Criteria andNeedCutToPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("need_cut_to_price not between", value1, value2, "needCutToPrice");
            return (Criteria) this;
        }

        public Criteria andMinCutToPriceIsNull() {
            addCriterion("min_cut_to_price is null");
            return (Criteria) this;
        }

        public Criteria andMinCutToPriceIsNotNull() {
            addCriterion("min_cut_to_price is not null");
            return (Criteria) this;
        }

        public Criteria andMinCutToPriceEqualTo(BigDecimal value) {
            addCriterion("min_cut_to_price =", value, "minCutToPrice");
            return (Criteria) this;
        }

        public Criteria andMinCutToPriceNotEqualTo(BigDecimal value) {
            addCriterion("min_cut_to_price <>", value, "minCutToPrice");
            return (Criteria) this;
        }

        public Criteria andMinCutToPriceGreaterThan(BigDecimal value) {
            addCriterion("min_cut_to_price >", value, "minCutToPrice");
            return (Criteria) this;
        }

        public Criteria andMinCutToPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("min_cut_to_price >=", value, "minCutToPrice");
            return (Criteria) this;
        }

        public Criteria andMinCutToPriceLessThan(BigDecimal value) {
            addCriterion("min_cut_to_price <", value, "minCutToPrice");
            return (Criteria) this;
        }

        public Criteria andMinCutToPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("min_cut_to_price <=", value, "minCutToPrice");
            return (Criteria) this;
        }

        public Criteria andMinCutToPriceIn(List<BigDecimal> values) {
            addCriterion("min_cut_to_price in", values, "minCutToPrice");
            return (Criteria) this;
        }

        public Criteria andMinCutToPriceNotIn(List<BigDecimal> values) {
            addCriterion("min_cut_to_price not in", values, "minCutToPrice");
            return (Criteria) this;
        }

        public Criteria andMinCutToPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_cut_to_price between", value1, value2, "minCutToPrice");
            return (Criteria) this;
        }

        public Criteria andMinCutToPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_cut_to_price not between", value1, value2, "minCutToPrice");
            return (Criteria) this;
        }

        public Criteria andInviteTimesIsNull() {
            addCriterion("invite_times is null");
            return (Criteria) this;
        }

        public Criteria andInviteTimesIsNotNull() {
            addCriterion("invite_times is not null");
            return (Criteria) this;
        }

        public Criteria andInviteTimesEqualTo(Integer value) {
            addCriterion("invite_times =", value, "inviteTimes");
            return (Criteria) this;
        }

        public Criteria andInviteTimesNotEqualTo(Integer value) {
            addCriterion("invite_times <>", value, "inviteTimes");
            return (Criteria) this;
        }

        public Criteria andInviteTimesGreaterThan(Integer value) {
            addCriterion("invite_times >", value, "inviteTimes");
            return (Criteria) this;
        }

        public Criteria andInviteTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("invite_times >=", value, "inviteTimes");
            return (Criteria) this;
        }

        public Criteria andInviteTimesLessThan(Integer value) {
            addCriterion("invite_times <", value, "inviteTimes");
            return (Criteria) this;
        }

        public Criteria andInviteTimesLessThanOrEqualTo(Integer value) {
            addCriterion("invite_times <=", value, "inviteTimes");
            return (Criteria) this;
        }

        public Criteria andInviteTimesIn(List<Integer> values) {
            addCriterion("invite_times in", values, "inviteTimes");
            return (Criteria) this;
        }

        public Criteria andInviteTimesNotIn(List<Integer> values) {
            addCriterion("invite_times not in", values, "inviteTimes");
            return (Criteria) this;
        }

        public Criteria andInviteTimesBetween(Integer value1, Integer value2) {
            addCriterion("invite_times between", value1, value2, "inviteTimes");
            return (Criteria) this;
        }

        public Criteria andInviteTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("invite_times not between", value1, value2, "inviteTimes");
            return (Criteria) this;
        }

        public Criteria andMaxInviteTimesIsNull() {
            addCriterion("max_invite_times is null");
            return (Criteria) this;
        }

        public Criteria andMaxInviteTimesIsNotNull() {
            addCriterion("max_invite_times is not null");
            return (Criteria) this;
        }

        public Criteria andMaxInviteTimesEqualTo(Integer value) {
            addCriterion("max_invite_times =", value, "maxInviteTimes");
            return (Criteria) this;
        }

        public Criteria andMaxInviteTimesNotEqualTo(Integer value) {
            addCriterion("max_invite_times <>", value, "maxInviteTimes");
            return (Criteria) this;
        }

        public Criteria andMaxInviteTimesGreaterThan(Integer value) {
            addCriterion("max_invite_times >", value, "maxInviteTimes");
            return (Criteria) this;
        }

        public Criteria andMaxInviteTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_invite_times >=", value, "maxInviteTimes");
            return (Criteria) this;
        }

        public Criteria andMaxInviteTimesLessThan(Integer value) {
            addCriterion("max_invite_times <", value, "maxInviteTimes");
            return (Criteria) this;
        }

        public Criteria andMaxInviteTimesLessThanOrEqualTo(Integer value) {
            addCriterion("max_invite_times <=", value, "maxInviteTimes");
            return (Criteria) this;
        }

        public Criteria andMaxInviteTimesIn(List<Integer> values) {
            addCriterion("max_invite_times in", values, "maxInviteTimes");
            return (Criteria) this;
        }

        public Criteria andMaxInviteTimesNotIn(List<Integer> values) {
            addCriterion("max_invite_times not in", values, "maxInviteTimes");
            return (Criteria) this;
        }

        public Criteria andMaxInviteTimesBetween(Integer value1, Integer value2) {
            addCriterion("max_invite_times between", value1, value2, "maxInviteTimes");
            return (Criteria) this;
        }

        public Criteria andMaxInviteTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("max_invite_times not between", value1, value2, "maxInviteTimes");
            return (Criteria) this;
        }

        public Criteria andPredictMinTimesIsNull() {
            addCriterion("predict_min_times is null");
            return (Criteria) this;
        }

        public Criteria andPredictMinTimesIsNotNull() {
            addCriterion("predict_min_times is not null");
            return (Criteria) this;
        }

        public Criteria andPredictMinTimesEqualTo(Integer value) {
            addCriterion("predict_min_times =", value, "predictMinTimes");
            return (Criteria) this;
        }

        public Criteria andPredictMinTimesNotEqualTo(Integer value) {
            addCriterion("predict_min_times <>", value, "predictMinTimes");
            return (Criteria) this;
        }

        public Criteria andPredictMinTimesGreaterThan(Integer value) {
            addCriterion("predict_min_times >", value, "predictMinTimes");
            return (Criteria) this;
        }

        public Criteria andPredictMinTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("predict_min_times >=", value, "predictMinTimes");
            return (Criteria) this;
        }

        public Criteria andPredictMinTimesLessThan(Integer value) {
            addCriterion("predict_min_times <", value, "predictMinTimes");
            return (Criteria) this;
        }

        public Criteria andPredictMinTimesLessThanOrEqualTo(Integer value) {
            addCriterion("predict_min_times <=", value, "predictMinTimes");
            return (Criteria) this;
        }

        public Criteria andPredictMinTimesIn(List<Integer> values) {
            addCriterion("predict_min_times in", values, "predictMinTimes");
            return (Criteria) this;
        }

        public Criteria andPredictMinTimesNotIn(List<Integer> values) {
            addCriterion("predict_min_times not in", values, "predictMinTimes");
            return (Criteria) this;
        }

        public Criteria andPredictMinTimesBetween(Integer value1, Integer value2) {
            addCriterion("predict_min_times between", value1, value2, "predictMinTimes");
            return (Criteria) this;
        }

        public Criteria andPredictMinTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("predict_min_times not between", value1, value2, "predictMinTimes");
            return (Criteria) this;
        }

        public Criteria andPredictMaxTimesIsNull() {
            addCriterion("predict_max_times is null");
            return (Criteria) this;
        }

        public Criteria andPredictMaxTimesIsNotNull() {
            addCriterion("predict_max_times is not null");
            return (Criteria) this;
        }

        public Criteria andPredictMaxTimesEqualTo(Integer value) {
            addCriterion("predict_max_times =", value, "predictMaxTimes");
            return (Criteria) this;
        }

        public Criteria andPredictMaxTimesNotEqualTo(Integer value) {
            addCriterion("predict_max_times <>", value, "predictMaxTimes");
            return (Criteria) this;
        }

        public Criteria andPredictMaxTimesGreaterThan(Integer value) {
            addCriterion("predict_max_times >", value, "predictMaxTimes");
            return (Criteria) this;
        }

        public Criteria andPredictMaxTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("predict_max_times >=", value, "predictMaxTimes");
            return (Criteria) this;
        }

        public Criteria andPredictMaxTimesLessThan(Integer value) {
            addCriterion("predict_max_times <", value, "predictMaxTimes");
            return (Criteria) this;
        }

        public Criteria andPredictMaxTimesLessThanOrEqualTo(Integer value) {
            addCriterion("predict_max_times <=", value, "predictMaxTimes");
            return (Criteria) this;
        }

        public Criteria andPredictMaxTimesIn(List<Integer> values) {
            addCriterion("predict_max_times in", values, "predictMaxTimes");
            return (Criteria) this;
        }

        public Criteria andPredictMaxTimesNotIn(List<Integer> values) {
            addCriterion("predict_max_times not in", values, "predictMaxTimes");
            return (Criteria) this;
        }

        public Criteria andPredictMaxTimesBetween(Integer value1, Integer value2) {
            addCriterion("predict_max_times between", value1, value2, "predictMaxTimes");
            return (Criteria) this;
        }

        public Criteria andPredictMaxTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("predict_max_times not between", value1, value2, "predictMaxTimes");
            return (Criteria) this;
        }

        public Criteria andActiveTimeIsNull() {
            addCriterion("active_time is null");
            return (Criteria) this;
        }

        public Criteria andActiveTimeIsNotNull() {
            addCriterion("active_time is not null");
            return (Criteria) this;
        }

        public Criteria andActiveTimeEqualTo(Integer value) {
            addCriterion("active_time =", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeNotEqualTo(Integer value) {
            addCriterion("active_time <>", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeGreaterThan(Integer value) {
            addCriterion("active_time >", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("active_time >=", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeLessThan(Integer value) {
            addCriterion("active_time <", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeLessThanOrEqualTo(Integer value) {
            addCriterion("active_time <=", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeIn(List<Integer> values) {
            addCriterion("active_time in", values, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeNotIn(List<Integer> values) {
            addCriterion("active_time not in", values, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeBetween(Integer value1, Integer value2) {
            addCriterion("active_time between", value1, value2, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("active_time not between", value1, value2, "activeTime");
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