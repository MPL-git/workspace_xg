package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeixinXcxSprDtlExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public WeixinXcxSprDtlExample() {
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

        public Criteria andSprPlanIdIsNull() {
            addCriterion("spr_plan_id is null");
            return (Criteria) this;
        }

        public Criteria andSprPlanIdIsNotNull() {
            addCriterion("spr_plan_id is not null");
            return (Criteria) this;
        }

        public Criteria andSprPlanIdEqualTo(Integer value) {
            addCriterion("spr_plan_id =", value, "sprPlanId");
            return (Criteria) this;
        }

        public Criteria andSprPlanIdNotEqualTo(Integer value) {
            addCriterion("spr_plan_id <>", value, "sprPlanId");
            return (Criteria) this;
        }

        public Criteria andSprPlanIdGreaterThan(Integer value) {
            addCriterion("spr_plan_id >", value, "sprPlanId");
            return (Criteria) this;
        }

        public Criteria andSprPlanIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("spr_plan_id >=", value, "sprPlanId");
            return (Criteria) this;
        }

        public Criteria andSprPlanIdLessThan(Integer value) {
            addCriterion("spr_plan_id <", value, "sprPlanId");
            return (Criteria) this;
        }

        public Criteria andSprPlanIdLessThanOrEqualTo(Integer value) {
            addCriterion("spr_plan_id <=", value, "sprPlanId");
            return (Criteria) this;
        }

        public Criteria andSprPlanIdIn(List<Integer> values) {
            addCriterion("spr_plan_id in", values, "sprPlanId");
            return (Criteria) this;
        }

        public Criteria andSprPlanIdNotIn(List<Integer> values) {
            addCriterion("spr_plan_id not in", values, "sprPlanId");
            return (Criteria) this;
        }

        public Criteria andSprPlanIdBetween(Integer value1, Integer value2) {
            addCriterion("spr_plan_id between", value1, value2, "sprPlanId");
            return (Criteria) this;
        }

        public Criteria andSprPlanIdNotBetween(Integer value1, Integer value2) {
            addCriterion("spr_plan_id not between", value1, value2, "sprPlanId");
            return (Criteria) this;
        }

        public Criteria andSprTypeIsNull() {
            addCriterion("spr_type is null");
            return (Criteria) this;
        }

        public Criteria andSprTypeIsNotNull() {
            addCriterion("spr_type is not null");
            return (Criteria) this;
        }

        public Criteria andSprTypeEqualTo(String value) {
            addCriterion("spr_type =", value, "sprType");
            return (Criteria) this;
        }

        public Criteria andSprTypeNotEqualTo(String value) {
            addCriterion("spr_type <>", value, "sprType");
            return (Criteria) this;
        }

        public Criteria andSprTypeGreaterThan(String value) {
            addCriterion("spr_type >", value, "sprType");
            return (Criteria) this;
        }

        public Criteria andSprTypeGreaterThanOrEqualTo(String value) {
            addCriterion("spr_type >=", value, "sprType");
            return (Criteria) this;
        }

        public Criteria andSprTypeLessThan(String value) {
            addCriterion("spr_type <", value, "sprType");
            return (Criteria) this;
        }

        public Criteria andSprTypeLessThanOrEqualTo(String value) {
            addCriterion("spr_type <=", value, "sprType");
            return (Criteria) this;
        }

        public Criteria andSprTypeLike(String value) {
            addCriterion("spr_type like", value, "sprType");
            return (Criteria) this;
        }

        public Criteria andSprTypeNotLike(String value) {
            addCriterion("spr_type not like", value, "sprType");
            return (Criteria) this;
        }

        public Criteria andSprTypeIn(List<String> values) {
            addCriterion("spr_type in", values, "sprType");
            return (Criteria) this;
        }

        public Criteria andSprTypeNotIn(List<String> values) {
            addCriterion("spr_type not in", values, "sprType");
            return (Criteria) this;
        }

        public Criteria andSprTypeBetween(String value1, String value2) {
            addCriterion("spr_type between", value1, value2, "sprType");
            return (Criteria) this;
        }

        public Criteria andSprTypeNotBetween(String value1, String value2) {
            addCriterion("spr_type not between", value1, value2, "sprType");
            return (Criteria) this;
        }

        public Criteria andSprValueIsNull() {
            addCriterion("spr_value is null");
            return (Criteria) this;
        }

        public Criteria andSprValueIsNotNull() {
            addCriterion("spr_value is not null");
            return (Criteria) this;
        }

        public Criteria andSprValueEqualTo(String value) {
            addCriterion("spr_value =", value, "sprValue");
            return (Criteria) this;
        }

        public Criteria andSprValueNotEqualTo(String value) {
            addCriterion("spr_value <>", value, "sprValue");
            return (Criteria) this;
        }

        public Criteria andSprValueGreaterThan(String value) {
            addCriterion("spr_value >", value, "sprValue");
            return (Criteria) this;
        }

        public Criteria andSprValueGreaterThanOrEqualTo(String value) {
            addCriterion("spr_value >=", value, "sprValue");
            return (Criteria) this;
        }

        public Criteria andSprValueLessThan(String value) {
            addCriterion("spr_value <", value, "sprValue");
            return (Criteria) this;
        }

        public Criteria andSprValueLessThanOrEqualTo(String value) {
            addCriterion("spr_value <=", value, "sprValue");
            return (Criteria) this;
        }

        public Criteria andSprValueLike(String value) {
            addCriterion("spr_value like", value, "sprValue");
            return (Criteria) this;
        }

        public Criteria andSprValueNotLike(String value) {
            addCriterion("spr_value not like", value, "sprValue");
            return (Criteria) this;
        }

        public Criteria andSprValueIn(List<String> values) {
            addCriterion("spr_value in", values, "sprValue");
            return (Criteria) this;
        }

        public Criteria andSprValueNotIn(List<String> values) {
            addCriterion("spr_value not in", values, "sprValue");
            return (Criteria) this;
        }

        public Criteria andSprValueBetween(String value1, String value2) {
            addCriterion("spr_value between", value1, value2, "sprValue");
            return (Criteria) this;
        }

        public Criteria andSprValueNotBetween(String value1, String value2) {
            addCriterion("spr_value not between", value1, value2, "sprValue");
            return (Criteria) this;
        }

        public Criteria andSprUrlIsNull() {
            addCriterion("spr_url is null");
            return (Criteria) this;
        }

        public Criteria andSprUrlIsNotNull() {
            addCriterion("spr_url is not null");
            return (Criteria) this;
        }

        public Criteria andSprUrlEqualTo(String value) {
            addCriterion("spr_url =", value, "sprUrl");
            return (Criteria) this;
        }

        public Criteria andSprUrlNotEqualTo(String value) {
            addCriterion("spr_url <>", value, "sprUrl");
            return (Criteria) this;
        }

        public Criteria andSprUrlGreaterThan(String value) {
            addCriterion("spr_url >", value, "sprUrl");
            return (Criteria) this;
        }

        public Criteria andSprUrlGreaterThanOrEqualTo(String value) {
            addCriterion("spr_url >=", value, "sprUrl");
            return (Criteria) this;
        }

        public Criteria andSprUrlLessThan(String value) {
            addCriterion("spr_url <", value, "sprUrl");
            return (Criteria) this;
        }

        public Criteria andSprUrlLessThanOrEqualTo(String value) {
            addCriterion("spr_url <=", value, "sprUrl");
            return (Criteria) this;
        }

        public Criteria andSprUrlLike(String value) {
            addCriterion("spr_url like", value, "sprUrl");
            return (Criteria) this;
        }

        public Criteria andSprUrlNotLike(String value) {
            addCriterion("spr_url not like", value, "sprUrl");
            return (Criteria) this;
        }

        public Criteria andSprUrlIn(List<String> values) {
            addCriterion("spr_url in", values, "sprUrl");
            return (Criteria) this;
        }

        public Criteria andSprUrlNotIn(List<String> values) {
            addCriterion("spr_url not in", values, "sprUrl");
            return (Criteria) this;
        }

        public Criteria andSprUrlBetween(String value1, String value2) {
            addCriterion("spr_url between", value1, value2, "sprUrl");
            return (Criteria) this;
        }

        public Criteria andSprUrlNotBetween(String value1, String value2) {
            addCriterion("spr_url not between", value1, value2, "sprUrl");
            return (Criteria) this;
        }

        public Criteria andSprQrCodeIsNull() {
            addCriterion("spr_qr_code is null");
            return (Criteria) this;
        }

        public Criteria andSprQrCodeIsNotNull() {
            addCriterion("spr_qr_code is not null");
            return (Criteria) this;
        }

        public Criteria andSprQrCodeEqualTo(String value) {
            addCriterion("spr_qr_code =", value, "sprQrCode");
            return (Criteria) this;
        }

        public Criteria andSprQrCodeNotEqualTo(String value) {
            addCriterion("spr_qr_code <>", value, "sprQrCode");
            return (Criteria) this;
        }

        public Criteria andSprQrCodeGreaterThan(String value) {
            addCriterion("spr_qr_code >", value, "sprQrCode");
            return (Criteria) this;
        }

        public Criteria andSprQrCodeGreaterThanOrEqualTo(String value) {
            addCriterion("spr_qr_code >=", value, "sprQrCode");
            return (Criteria) this;
        }

        public Criteria andSprQrCodeLessThan(String value) {
            addCriterion("spr_qr_code <", value, "sprQrCode");
            return (Criteria) this;
        }

        public Criteria andSprQrCodeLessThanOrEqualTo(String value) {
            addCriterion("spr_qr_code <=", value, "sprQrCode");
            return (Criteria) this;
        }

        public Criteria andSprQrCodeLike(String value) {
            addCriterion("spr_qr_code like", value, "sprQrCode");
            return (Criteria) this;
        }

        public Criteria andSprQrCodeNotLike(String value) {
            addCriterion("spr_qr_code not like", value, "sprQrCode");
            return (Criteria) this;
        }

        public Criteria andSprQrCodeIn(List<String> values) {
            addCriterion("spr_qr_code in", values, "sprQrCode");
            return (Criteria) this;
        }

        public Criteria andSprQrCodeNotIn(List<String> values) {
            addCriterion("spr_qr_code not in", values, "sprQrCode");
            return (Criteria) this;
        }

        public Criteria andSprQrCodeBetween(String value1, String value2) {
            addCriterion("spr_qr_code between", value1, value2, "sprQrCode");
            return (Criteria) this;
        }

        public Criteria andSprQrCodeNotBetween(String value1, String value2) {
            addCriterion("spr_qr_code not between", value1, value2, "sprQrCode");
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

        public Criteria andIsEffectIsNull() {
            addCriterion("is_effect is null");
            return (Criteria) this;
        }

        public Criteria andIsEffectIsNotNull() {
            addCriterion("is_effect is not null");
            return (Criteria) this;
        }

        public Criteria andIsEffectEqualTo(String value) {
            addCriterion("is_effect =", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectNotEqualTo(String value) {
            addCriterion("is_effect <>", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectGreaterThan(String value) {
            addCriterion("is_effect >", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectGreaterThanOrEqualTo(String value) {
            addCriterion("is_effect >=", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectLessThan(String value) {
            addCriterion("is_effect <", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectLessThanOrEqualTo(String value) {
            addCriterion("is_effect <=", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectLike(String value) {
            addCriterion("is_effect like", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectNotLike(String value) {
            addCriterion("is_effect not like", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectIn(List<String> values) {
            addCriterion("is_effect in", values, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectNotIn(List<String> values) {
            addCriterion("is_effect not in", values, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectBetween(String value1, String value2) {
            addCriterion("is_effect between", value1, value2, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectNotBetween(String value1, String value2) {
            addCriterion("is_effect not between", value1, value2, "isEffect");
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