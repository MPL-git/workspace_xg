package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViolatePunishStandardExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public ViolatePunishStandardExample() {
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

        public Criteria andViolateTypeIsNull() {
            addCriterion("violate_type is null");
            return (Criteria) this;
        }

        public Criteria andViolateTypeIsNotNull() {
            addCriterion("violate_type is not null");
            return (Criteria) this;
        }

        public Criteria andViolateTypeEqualTo(String value) {
            addCriterion("violate_type =", value, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeNotEqualTo(String value) {
            addCriterion("violate_type <>", value, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeGreaterThan(String value) {
            addCriterion("violate_type >", value, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeGreaterThanOrEqualTo(String value) {
            addCriterion("violate_type >=", value, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeLessThan(String value) {
            addCriterion("violate_type <", value, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeLessThanOrEqualTo(String value) {
            addCriterion("violate_type <=", value, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeLike(String value) {
            addCriterion("violate_type like", value, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeNotLike(String value) {
            addCriterion("violate_type not like", value, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeIn(List<String> values) {
            addCriterion("violate_type in", values, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeNotIn(List<String> values) {
            addCriterion("violate_type not in", values, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeBetween(String value1, String value2) {
            addCriterion("violate_type between", value1, value2, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeNotBetween(String value1, String value2) {
            addCriterion("violate_type not between", value1, value2, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateActionIsNull() {
            addCriterion("violate_action is null");
            return (Criteria) this;
        }

        public Criteria andViolateActionIsNotNull() {
            addCriterion("violate_action is not null");
            return (Criteria) this;
        }

        public Criteria andViolateActionEqualTo(String value) {
            addCriterion("violate_action =", value, "violateAction");
            return (Criteria) this;
        }

        public Criteria andViolateActionNotEqualTo(String value) {
            addCriterion("violate_action <>", value, "violateAction");
            return (Criteria) this;
        }

        public Criteria andViolateActionGreaterThan(String value) {
            addCriterion("violate_action >", value, "violateAction");
            return (Criteria) this;
        }

        public Criteria andViolateActionGreaterThanOrEqualTo(String value) {
            addCriterion("violate_action >=", value, "violateAction");
            return (Criteria) this;
        }

        public Criteria andViolateActionLessThan(String value) {
            addCriterion("violate_action <", value, "violateAction");
            return (Criteria) this;
        }

        public Criteria andViolateActionLessThanOrEqualTo(String value) {
            addCriterion("violate_action <=", value, "violateAction");
            return (Criteria) this;
        }

        public Criteria andViolateActionLike(String value) {
            addCriterion("violate_action like", value, "violateAction");
            return (Criteria) this;
        }

        public Criteria andViolateActionNotLike(String value) {
            addCriterion("violate_action not like", value, "violateAction");
            return (Criteria) this;
        }

        public Criteria andViolateActionIn(List<String> values) {
            addCriterion("violate_action in", values, "violateAction");
            return (Criteria) this;
        }

        public Criteria andViolateActionNotIn(List<String> values) {
            addCriterion("violate_action not in", values, "violateAction");
            return (Criteria) this;
        }

        public Criteria andViolateActionBetween(String value1, String value2) {
            addCriterion("violate_action between", value1, value2, "violateAction");
            return (Criteria) this;
        }

        public Criteria andViolateActionNotBetween(String value1, String value2) {
            addCriterion("violate_action not between", value1, value2, "violateAction");
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

        public Criteria andPunishStandardIsNull() {
            addCriterion("punish_standard is null");
            return (Criteria) this;
        }

        public Criteria andPunishStandardIsNotNull() {
            addCriterion("punish_standard is not null");
            return (Criteria) this;
        }

        public Criteria andPunishStandardEqualTo(String value) {
            addCriterion("punish_standard =", value, "punishStandard");
            return (Criteria) this;
        }

        public Criteria andPunishStandardNotEqualTo(String value) {
            addCriterion("punish_standard <>", value, "punishStandard");
            return (Criteria) this;
        }

        public Criteria andPunishStandardGreaterThan(String value) {
            addCriterion("punish_standard >", value, "punishStandard");
            return (Criteria) this;
        }

        public Criteria andPunishStandardGreaterThanOrEqualTo(String value) {
            addCriterion("punish_standard >=", value, "punishStandard");
            return (Criteria) this;
        }

        public Criteria andPunishStandardLessThan(String value) {
            addCriterion("punish_standard <", value, "punishStandard");
            return (Criteria) this;
        }

        public Criteria andPunishStandardLessThanOrEqualTo(String value) {
            addCriterion("punish_standard <=", value, "punishStandard");
            return (Criteria) this;
        }

        public Criteria andPunishStandardLike(String value) {
            addCriterion("punish_standard like", value, "punishStandard");
            return (Criteria) this;
        }

        public Criteria andPunishStandardNotLike(String value) {
            addCriterion("punish_standard not like", value, "punishStandard");
            return (Criteria) this;
        }

        public Criteria andPunishStandardIn(List<String> values) {
            addCriterion("punish_standard in", values, "punishStandard");
            return (Criteria) this;
        }

        public Criteria andPunishStandardNotIn(List<String> values) {
            addCriterion("punish_standard not in", values, "punishStandard");
            return (Criteria) this;
        }

        public Criteria andPunishStandardBetween(String value1, String value2) {
            addCriterion("punish_standard between", value1, value2, "punishStandard");
            return (Criteria) this;
        }

        public Criteria andPunishStandardNotBetween(String value1, String value2) {
            addCriterion("punish_standard not between", value1, value2, "punishStandard");
            return (Criteria) this;
        }

        public Criteria andPunishOtherIsNull() {
            addCriterion("punish_other is null");
            return (Criteria) this;
        }

        public Criteria andPunishOtherIsNotNull() {
            addCriterion("punish_other is not null");
            return (Criteria) this;
        }

        public Criteria andPunishOtherEqualTo(String value) {
            addCriterion("punish_other =", value, "punishOther");
            return (Criteria) this;
        }

        public Criteria andPunishOtherNotEqualTo(String value) {
            addCriterion("punish_other <>", value, "punishOther");
            return (Criteria) this;
        }

        public Criteria andPunishOtherGreaterThan(String value) {
            addCriterion("punish_other >", value, "punishOther");
            return (Criteria) this;
        }

        public Criteria andPunishOtherGreaterThanOrEqualTo(String value) {
            addCriterion("punish_other >=", value, "punishOther");
            return (Criteria) this;
        }

        public Criteria andPunishOtherLessThan(String value) {
            addCriterion("punish_other <", value, "punishOther");
            return (Criteria) this;
        }

        public Criteria andPunishOtherLessThanOrEqualTo(String value) {
            addCriterion("punish_other <=", value, "punishOther");
            return (Criteria) this;
        }

        public Criteria andPunishOtherLike(String value) {
            addCriterion("punish_other like", value, "punishOther");
            return (Criteria) this;
        }

        public Criteria andPunishOtherNotLike(String value) {
            addCriterion("punish_other not like", value, "punishOther");
            return (Criteria) this;
        }

        public Criteria andPunishOtherIn(List<String> values) {
            addCriterion("punish_other in", values, "punishOther");
            return (Criteria) this;
        }

        public Criteria andPunishOtherNotIn(List<String> values) {
            addCriterion("punish_other not in", values, "punishOther");
            return (Criteria) this;
        }

        public Criteria andPunishOtherBetween(String value1, String value2) {
            addCriterion("punish_other between", value1, value2, "punishOther");
            return (Criteria) this;
        }

        public Criteria andPunishOtherNotBetween(String value1, String value2) {
            addCriterion("punish_other not between", value1, value2, "punishOther");
            return (Criteria) this;
        }

        public Criteria andComplainFlagIsNull() {
            addCriterion("complain_flag is null");
            return (Criteria) this;
        }

        public Criteria andComplainFlagIsNotNull() {
            addCriterion("complain_flag is not null");
            return (Criteria) this;
        }

        public Criteria andComplainFlagEqualTo(String value) {
            addCriterion("complain_flag =", value, "complainFlag");
            return (Criteria) this;
        }

        public Criteria andComplainFlagNotEqualTo(String value) {
            addCriterion("complain_flag <>", value, "complainFlag");
            return (Criteria) this;
        }

        public Criteria andComplainFlagGreaterThan(String value) {
            addCriterion("complain_flag >", value, "complainFlag");
            return (Criteria) this;
        }

        public Criteria andComplainFlagGreaterThanOrEqualTo(String value) {
            addCriterion("complain_flag >=", value, "complainFlag");
            return (Criteria) this;
        }

        public Criteria andComplainFlagLessThan(String value) {
            addCriterion("complain_flag <", value, "complainFlag");
            return (Criteria) this;
        }

        public Criteria andComplainFlagLessThanOrEqualTo(String value) {
            addCriterion("complain_flag <=", value, "complainFlag");
            return (Criteria) this;
        }

        public Criteria andComplainFlagLike(String value) {
            addCriterion("complain_flag like", value, "complainFlag");
            return (Criteria) this;
        }

        public Criteria andComplainFlagNotLike(String value) {
            addCriterion("complain_flag not like", value, "complainFlag");
            return (Criteria) this;
        }

        public Criteria andComplainFlagIn(List<String> values) {
            addCriterion("complain_flag in", values, "complainFlag");
            return (Criteria) this;
        }

        public Criteria andComplainFlagNotIn(List<String> values) {
            addCriterion("complain_flag not in", values, "complainFlag");
            return (Criteria) this;
        }

        public Criteria andComplainFlagBetween(String value1, String value2) {
            addCriterion("complain_flag between", value1, value2, "complainFlag");
            return (Criteria) this;
        }

        public Criteria andComplainFlagNotBetween(String value1, String value2) {
            addCriterion("complain_flag not between", value1, value2, "complainFlag");
            return (Criteria) this;
        }

        public Criteria andEnableHoursIsNull() {
            addCriterion("enable_hours is null");
            return (Criteria) this;
        }

        public Criteria andEnableHoursIsNotNull() {
            addCriterion("enable_hours is not null");
            return (Criteria) this;
        }

        public Criteria andEnableHoursEqualTo(String value) {
            addCriterion("enable_hours =", value, "enableHours");
            return (Criteria) this;
        }

        public Criteria andEnableHoursNotEqualTo(String value) {
            addCriterion("enable_hours <>", value, "enableHours");
            return (Criteria) this;
        }

        public Criteria andEnableHoursGreaterThan(String value) {
            addCriterion("enable_hours >", value, "enableHours");
            return (Criteria) this;
        }

        public Criteria andEnableHoursGreaterThanOrEqualTo(String value) {
            addCriterion("enable_hours >=", value, "enableHours");
            return (Criteria) this;
        }

        public Criteria andEnableHoursLessThan(String value) {
            addCriterion("enable_hours <", value, "enableHours");
            return (Criteria) this;
        }

        public Criteria andEnableHoursLessThanOrEqualTo(String value) {
            addCriterion("enable_hours <=", value, "enableHours");
            return (Criteria) this;
        }

        public Criteria andEnableHoursLike(String value) {
            addCriterion("enable_hours like", value, "enableHours");
            return (Criteria) this;
        }

        public Criteria andEnableHoursNotLike(String value) {
            addCriterion("enable_hours not like", value, "enableHours");
            return (Criteria) this;
        }

        public Criteria andEnableHoursIn(List<String> values) {
            addCriterion("enable_hours in", values, "enableHours");
            return (Criteria) this;
        }

        public Criteria andEnableHoursNotIn(List<String> values) {
            addCriterion("enable_hours not in", values, "enableHours");
            return (Criteria) this;
        }

        public Criteria andEnableHoursBetween(String value1, String value2) {
            addCriterion("enable_hours between", value1, value2, "enableHours");
            return (Criteria) this;
        }

        public Criteria andEnableHoursNotBetween(String value1, String value2) {
            addCriterion("enable_hours not between", value1, value2, "enableHours");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralDescIsNull() {
            addCriterion("jifen_integral_desc is null");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralDescIsNotNull() {
            addCriterion("jifen_integral_desc is not null");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralDescEqualTo(String value) {
            addCriterion("jifen_integral_desc =", value, "jifenIntegralDesc");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralDescNotEqualTo(String value) {
            addCriterion("jifen_integral_desc <>", value, "jifenIntegralDesc");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralDescGreaterThan(String value) {
            addCriterion("jifen_integral_desc >", value, "jifenIntegralDesc");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralDescGreaterThanOrEqualTo(String value) {
            addCriterion("jifen_integral_desc >=", value, "jifenIntegralDesc");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralDescLessThan(String value) {
            addCriterion("jifen_integral_desc <", value, "jifenIntegralDesc");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralDescLessThanOrEqualTo(String value) {
            addCriterion("jifen_integral_desc <=", value, "jifenIntegralDesc");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralDescLike(String value) {
            addCriterion("jifen_integral_desc like", value, "jifenIntegralDesc");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralDescNotLike(String value) {
            addCriterion("jifen_integral_desc not like", value, "jifenIntegralDesc");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralDescIn(List<String> values) {
            addCriterion("jifen_integral_desc in", values, "jifenIntegralDesc");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralDescNotIn(List<String> values) {
            addCriterion("jifen_integral_desc not in", values, "jifenIntegralDesc");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralDescBetween(String value1, String value2) {
            addCriterion("jifen_integral_desc between", value1, value2, "jifenIntegralDesc");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralDescNotBetween(String value1, String value2) {
            addCriterion("jifen_integral_desc not between", value1, value2, "jifenIntegralDesc");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralIsNull() {
            addCriterion("jifen_integral is null");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralIsNotNull() {
            addCriterion("jifen_integral is not null");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralEqualTo(Integer value) {
            addCriterion("jifen_integral =", value, "jifenIntegral");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralNotEqualTo(Integer value) {
            addCriterion("jifen_integral <>", value, "jifenIntegral");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralGreaterThan(Integer value) {
            addCriterion("jifen_integral >", value, "jifenIntegral");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralGreaterThanOrEqualTo(Integer value) {
            addCriterion("jifen_integral >=", value, "jifenIntegral");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralLessThan(Integer value) {
            addCriterion("jifen_integral <", value, "jifenIntegral");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralLessThanOrEqualTo(Integer value) {
            addCriterion("jifen_integral <=", value, "jifenIntegral");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralIn(List<Integer> values) {
            addCriterion("jifen_integral in", values, "jifenIntegral");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralNotIn(List<Integer> values) {
            addCriterion("jifen_integral not in", values, "jifenIntegral");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralBetween(Integer value1, Integer value2) {
            addCriterion("jifen_integral between", value1, value2, "jifenIntegral");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralNotBetween(Integer value1, Integer value2) {
            addCriterion("jifen_integral not between", value1, value2, "jifenIntegral");
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

        public Criteria andPaymentBreachModelIsNull() {
            addCriterion("payment_breach_model is null");
            return (Criteria) this;
        }

        public Criteria andPaymentBreachModelIsNotNull() {
            addCriterion("payment_breach_model is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentBreachModelEqualTo(String value) {
            addCriterion("payment_breach_model =", value, "paymentBreachModel");
            return (Criteria) this;
        }

        public Criteria andPaymentBreachModelNotEqualTo(String value) {
            addCriterion("payment_breach_model <>", value, "paymentBreachModel");
            return (Criteria) this;
        }

        public Criteria andPaymentBreachModelGreaterThan(String value) {
            addCriterion("payment_breach_model >", value, "paymentBreachModel");
            return (Criteria) this;
        }

        public Criteria andPaymentBreachModelGreaterThanOrEqualTo(String value) {
            addCriterion("payment_breach_model >=", value, "paymentBreachModel");
            return (Criteria) this;
        }

        public Criteria andPaymentBreachModelLessThan(String value) {
            addCriterion("payment_breach_model <", value, "paymentBreachModel");
            return (Criteria) this;
        }

        public Criteria andPaymentBreachModelLessThanOrEqualTo(String value) {
            addCriterion("payment_breach_model <=", value, "paymentBreachModel");
            return (Criteria) this;
        }

        public Criteria andPaymentBreachModelLike(String value) {
            addCriterion("payment_breach_model like", value, "paymentBreachModel");
            return (Criteria) this;
        }

        public Criteria andPaymentBreachModelNotLike(String value) {
            addCriterion("payment_breach_model not like", value, "paymentBreachModel");
            return (Criteria) this;
        }

        public Criteria andPaymentBreachModelIn(List<String> values) {
            addCriterion("payment_breach_model in", values, "paymentBreachModel");
            return (Criteria) this;
        }

        public Criteria andPaymentBreachModelNotIn(List<String> values) {
            addCriterion("payment_breach_model not in", values, "paymentBreachModel");
            return (Criteria) this;
        }

        public Criteria andPaymentBreachModelBetween(String value1, String value2) {
            addCriterion("payment_breach_model between", value1, value2, "paymentBreachModel");
            return (Criteria) this;
        }

        public Criteria andPaymentBreachModelNotBetween(String value1, String value2) {
            addCriterion("payment_breach_model not between", value1, value2, "paymentBreachModel");
            return (Criteria) this;
        }

        public Criteria andBreachDeductionQuotaIsNull() {
            addCriterion("breach_deduction_quota is null");
            return (Criteria) this;
        }

        public Criteria andBreachDeductionQuotaIsNotNull() {
            addCriterion("breach_deduction_quota is not null");
            return (Criteria) this;
        }

        public Criteria andBreachDeductionQuotaEqualTo(BigDecimal value) {
            addCriterion("breach_deduction_quota =", value, "breachDeductionQuota");
            return (Criteria) this;
        }

        public Criteria andBreachDeductionQuotaNotEqualTo(BigDecimal value) {
            addCriterion("breach_deduction_quota <>", value, "breachDeductionQuota");
            return (Criteria) this;
        }

        public Criteria andBreachDeductionQuotaGreaterThan(BigDecimal value) {
            addCriterion("breach_deduction_quota >", value, "breachDeductionQuota");
            return (Criteria) this;
        }

        public Criteria andBreachDeductionQuotaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("breach_deduction_quota >=", value, "breachDeductionQuota");
            return (Criteria) this;
        }

        public Criteria andBreachDeductionQuotaLessThan(BigDecimal value) {
            addCriterion("breach_deduction_quota <", value, "breachDeductionQuota");
            return (Criteria) this;
        }

        public Criteria andBreachDeductionQuotaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("breach_deduction_quota <=", value, "breachDeductionQuota");
            return (Criteria) this;
        }

        public Criteria andBreachDeductionQuotaIn(List<BigDecimal> values) {
            addCriterion("breach_deduction_quota in", values, "breachDeductionQuota");
            return (Criteria) this;
        }

        public Criteria andBreachDeductionQuotaNotIn(List<BigDecimal> values) {
            addCriterion("breach_deduction_quota not in", values, "breachDeductionQuota");
            return (Criteria) this;
        }

        public Criteria andBreachDeductionQuotaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("breach_deduction_quota between", value1, value2, "breachDeductionQuota");
            return (Criteria) this;
        }

        public Criteria andBreachDeductionQuotaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("breach_deduction_quota not between", value1, value2, "breachDeductionQuota");
            return (Criteria) this;
        }

        public Criteria andMinDeductionQuotaIsNull() {
            addCriterion("min_deduction_quota is null");
            return (Criteria) this;
        }

        public Criteria andMinDeductionQuotaIsNotNull() {
            addCriterion("min_deduction_quota is not null");
            return (Criteria) this;
        }

        public Criteria andMinDeductionQuotaEqualTo(BigDecimal value) {
            addCriterion("min_deduction_quota =", value, "minDeductionQuota");
            return (Criteria) this;
        }

        public Criteria andMinDeductionQuotaNotEqualTo(BigDecimal value) {
            addCriterion("min_deduction_quota <>", value, "minDeductionQuota");
            return (Criteria) this;
        }

        public Criteria andMinDeductionQuotaGreaterThan(BigDecimal value) {
            addCriterion("min_deduction_quota >", value, "minDeductionQuota");
            return (Criteria) this;
        }

        public Criteria andMinDeductionQuotaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("min_deduction_quota >=", value, "minDeductionQuota");
            return (Criteria) this;
        }

        public Criteria andMinDeductionQuotaLessThan(BigDecimal value) {
            addCriterion("min_deduction_quota <", value, "minDeductionQuota");
            return (Criteria) this;
        }

        public Criteria andMinDeductionQuotaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("min_deduction_quota <=", value, "minDeductionQuota");
            return (Criteria) this;
        }

        public Criteria andMinDeductionQuotaIn(List<BigDecimal> values) {
            addCriterion("min_deduction_quota in", values, "minDeductionQuota");
            return (Criteria) this;
        }

        public Criteria andMinDeductionQuotaNotIn(List<BigDecimal> values) {
            addCriterion("min_deduction_quota not in", values, "minDeductionQuota");
            return (Criteria) this;
        }

        public Criteria andMinDeductionQuotaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_deduction_quota between", value1, value2, "minDeductionQuota");
            return (Criteria) this;
        }

        public Criteria andMinDeductionQuotaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_deduction_quota not between", value1, value2, "minDeductionQuota");
            return (Criteria) this;
        }

        public Criteria andIntegralCompensateModelIsNull() {
            addCriterion("integral_compensate_model is null");
            return (Criteria) this;
        }

        public Criteria andIntegralCompensateModelIsNotNull() {
            addCriterion("integral_compensate_model is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralCompensateModelEqualTo(String value) {
            addCriterion("integral_compensate_model =", value, "integralCompensateModel");
            return (Criteria) this;
        }

        public Criteria andIntegralCompensateModelNotEqualTo(String value) {
            addCriterion("integral_compensate_model <>", value, "integralCompensateModel");
            return (Criteria) this;
        }

        public Criteria andIntegralCompensateModelGreaterThan(String value) {
            addCriterion("integral_compensate_model >", value, "integralCompensateModel");
            return (Criteria) this;
        }

        public Criteria andIntegralCompensateModelGreaterThanOrEqualTo(String value) {
            addCriterion("integral_compensate_model >=", value, "integralCompensateModel");
            return (Criteria) this;
        }

        public Criteria andIntegralCompensateModelLessThan(String value) {
            addCriterion("integral_compensate_model <", value, "integralCompensateModel");
            return (Criteria) this;
        }

        public Criteria andIntegralCompensateModelLessThanOrEqualTo(String value) {
            addCriterion("integral_compensate_model <=", value, "integralCompensateModel");
            return (Criteria) this;
        }

        public Criteria andIntegralCompensateModelLike(String value) {
            addCriterion("integral_compensate_model like", value, "integralCompensateModel");
            return (Criteria) this;
        }

        public Criteria andIntegralCompensateModelNotLike(String value) {
            addCriterion("integral_compensate_model not like", value, "integralCompensateModel");
            return (Criteria) this;
        }

        public Criteria andIntegralCompensateModelIn(List<String> values) {
            addCriterion("integral_compensate_model in", values, "integralCompensateModel");
            return (Criteria) this;
        }

        public Criteria andIntegralCompensateModelNotIn(List<String> values) {
            addCriterion("integral_compensate_model not in", values, "integralCompensateModel");
            return (Criteria) this;
        }

        public Criteria andIntegralCompensateModelBetween(String value1, String value2) {
            addCriterion("integral_compensate_model between", value1, value2, "integralCompensateModel");
            return (Criteria) this;
        }

        public Criteria andIntegralCompensateModelNotBetween(String value1, String value2) {
            addCriterion("integral_compensate_model not between", value1, value2, "integralCompensateModel");
            return (Criteria) this;
        }

        public Criteria andIntegralCompensateRateIsNull() {
            addCriterion("integral_compensate_rate is null");
            return (Criteria) this;
        }

        public Criteria andIntegralCompensateRateIsNotNull() {
            addCriterion("integral_compensate_rate is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralCompensateRateEqualTo(BigDecimal value) {
            addCriterion("integral_compensate_rate =", value, "integralCompensateRate");
            return (Criteria) this;
        }

        public Criteria andIntegralCompensateRateNotEqualTo(BigDecimal value) {
            addCriterion("integral_compensate_rate <>", value, "integralCompensateRate");
            return (Criteria) this;
        }

        public Criteria andIntegralCompensateRateGreaterThan(BigDecimal value) {
            addCriterion("integral_compensate_rate >", value, "integralCompensateRate");
            return (Criteria) this;
        }

        public Criteria andIntegralCompensateRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("integral_compensate_rate >=", value, "integralCompensateRate");
            return (Criteria) this;
        }

        public Criteria andIntegralCompensateRateLessThan(BigDecimal value) {
            addCriterion("integral_compensate_rate <", value, "integralCompensateRate");
            return (Criteria) this;
        }

        public Criteria andIntegralCompensateRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("integral_compensate_rate <=", value, "integralCompensateRate");
            return (Criteria) this;
        }

        public Criteria andIntegralCompensateRateIn(List<BigDecimal> values) {
            addCriterion("integral_compensate_rate in", values, "integralCompensateRate");
            return (Criteria) this;
        }

        public Criteria andIntegralCompensateRateNotIn(List<BigDecimal> values) {
            addCriterion("integral_compensate_rate not in", values, "integralCompensateRate");
            return (Criteria) this;
        }

        public Criteria andIntegralCompensateRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("integral_compensate_rate between", value1, value2, "integralCompensateRate");
            return (Criteria) this;
        }

        public Criteria andIntegralCompensateRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("integral_compensate_rate not between", value1, value2, "integralCompensateRate");
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