package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DouyinSprPlanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public DouyinSprPlanExample() {
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

        public Criteria andSprChnlIdIsNull() {
            addCriterion("spr_chnl_id is null");
            return (Criteria) this;
        }

        public Criteria andSprChnlIdIsNotNull() {
            addCriterion("spr_chnl_id is not null");
            return (Criteria) this;
        }

        public Criteria andSprChnlIdEqualTo(Integer value) {
            addCriterion("spr_chnl_id =", value, "sprChnlId");
            return (Criteria) this;
        }

        public Criteria andSprChnlIdNotEqualTo(Integer value) {
            addCriterion("spr_chnl_id <>", value, "sprChnlId");
            return (Criteria) this;
        }

        public Criteria andSprChnlIdGreaterThan(Integer value) {
            addCriterion("spr_chnl_id >", value, "sprChnlId");
            return (Criteria) this;
        }

        public Criteria andSprChnlIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("spr_chnl_id >=", value, "sprChnlId");
            return (Criteria) this;
        }

        public Criteria andSprChnlIdLessThan(Integer value) {
            addCriterion("spr_chnl_id <", value, "sprChnlId");
            return (Criteria) this;
        }

        public Criteria andSprChnlIdLessThanOrEqualTo(Integer value) {
            addCriterion("spr_chnl_id <=", value, "sprChnlId");
            return (Criteria) this;
        }

        public Criteria andSprChnlIdIn(List<Integer> values) {
            addCriterion("spr_chnl_id in", values, "sprChnlId");
            return (Criteria) this;
        }

        public Criteria andSprChnlIdNotIn(List<Integer> values) {
            addCriterion("spr_chnl_id not in", values, "sprChnlId");
            return (Criteria) this;
        }

        public Criteria andSprChnlIdBetween(Integer value1, Integer value2) {
            addCriterion("spr_chnl_id between", value1, value2, "sprChnlId");
            return (Criteria) this;
        }

        public Criteria andSprChnlIdNotBetween(Integer value1, Integer value2) {
            addCriterion("spr_chnl_id not between", value1, value2, "sprChnlId");
            return (Criteria) this;
        }

        public Criteria andSprPlanSiteIsNull() {
            addCriterion("spr_plan_site is null");
            return (Criteria) this;
        }

        public Criteria andSprPlanSiteIsNotNull() {
            addCriterion("spr_plan_site is not null");
            return (Criteria) this;
        }

        public Criteria andSprPlanSiteEqualTo(String value) {
            addCriterion("spr_plan_site =", value, "sprPlanSite");
            return (Criteria) this;
        }

        public Criteria andSprPlanSiteNotEqualTo(String value) {
            addCriterion("spr_plan_site <>", value, "sprPlanSite");
            return (Criteria) this;
        }

        public Criteria andSprPlanSiteGreaterThan(String value) {
            addCriterion("spr_plan_site >", value, "sprPlanSite");
            return (Criteria) this;
        }

        public Criteria andSprPlanSiteGreaterThanOrEqualTo(String value) {
            addCriterion("spr_plan_site >=", value, "sprPlanSite");
            return (Criteria) this;
        }

        public Criteria andSprPlanSiteLessThan(String value) {
            addCriterion("spr_plan_site <", value, "sprPlanSite");
            return (Criteria) this;
        }

        public Criteria andSprPlanSiteLessThanOrEqualTo(String value) {
            addCriterion("spr_plan_site <=", value, "sprPlanSite");
            return (Criteria) this;
        }

        public Criteria andSprPlanSiteLike(String value) {
            addCriterion("spr_plan_site like", value, "sprPlanSite");
            return (Criteria) this;
        }

        public Criteria andSprPlanSiteNotLike(String value) {
            addCriterion("spr_plan_site not like", value, "sprPlanSite");
            return (Criteria) this;
        }

        public Criteria andSprPlanSiteIn(List<String> values) {
            addCriterion("spr_plan_site in", values, "sprPlanSite");
            return (Criteria) this;
        }

        public Criteria andSprPlanSiteNotIn(List<String> values) {
            addCriterion("spr_plan_site not in", values, "sprPlanSite");
            return (Criteria) this;
        }

        public Criteria andSprPlanSiteBetween(String value1, String value2) {
            addCriterion("spr_plan_site between", value1, value2, "sprPlanSite");
            return (Criteria) this;
        }

        public Criteria andSprPlanSiteNotBetween(String value1, String value2) {
            addCriterion("spr_plan_site not between", value1, value2, "sprPlanSite");
            return (Criteria) this;
        }

        public Criteria andSprPlanTypeIsNull() {
            addCriterion("spr_plan_type is null");
            return (Criteria) this;
        }

        public Criteria andSprPlanTypeIsNotNull() {
            addCriterion("spr_plan_type is not null");
            return (Criteria) this;
        }

        public Criteria andSprPlanTypeEqualTo(String value) {
            addCriterion("spr_plan_type =", value, "sprPlanType");
            return (Criteria) this;
        }

        public Criteria andSprPlanTypeNotEqualTo(String value) {
            addCriterion("spr_plan_type <>", value, "sprPlanType");
            return (Criteria) this;
        }

        public Criteria andSprPlanTypeGreaterThan(String value) {
            addCriterion("spr_plan_type >", value, "sprPlanType");
            return (Criteria) this;
        }

        public Criteria andSprPlanTypeGreaterThanOrEqualTo(String value) {
            addCriterion("spr_plan_type >=", value, "sprPlanType");
            return (Criteria) this;
        }

        public Criteria andSprPlanTypeLessThan(String value) {
            addCriterion("spr_plan_type <", value, "sprPlanType");
            return (Criteria) this;
        }

        public Criteria andSprPlanTypeLessThanOrEqualTo(String value) {
            addCriterion("spr_plan_type <=", value, "sprPlanType");
            return (Criteria) this;
        }

        public Criteria andSprPlanTypeLike(String value) {
            addCriterion("spr_plan_type like", value, "sprPlanType");
            return (Criteria) this;
        }

        public Criteria andSprPlanTypeNotLike(String value) {
            addCriterion("spr_plan_type not like", value, "sprPlanType");
            return (Criteria) this;
        }

        public Criteria andSprPlanTypeIn(List<String> values) {
            addCriterion("spr_plan_type in", values, "sprPlanType");
            return (Criteria) this;
        }

        public Criteria andSprPlanTypeNotIn(List<String> values) {
            addCriterion("spr_plan_type not in", values, "sprPlanType");
            return (Criteria) this;
        }

        public Criteria andSprPlanTypeBetween(String value1, String value2) {
            addCriterion("spr_plan_type between", value1, value2, "sprPlanType");
            return (Criteria) this;
        }

        public Criteria andSprPlanTypeNotBetween(String value1, String value2) {
            addCriterion("spr_plan_type not between", value1, value2, "sprPlanType");
            return (Criteria) this;
        }

        public Criteria andLinkValueIsNull() {
            addCriterion("link_value is null");
            return (Criteria) this;
        }

        public Criteria andLinkValueIsNotNull() {
            addCriterion("link_value is not null");
            return (Criteria) this;
        }

        public Criteria andLinkValueEqualTo(String value) {
            addCriterion("link_value =", value, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueNotEqualTo(String value) {
            addCriterion("link_value <>", value, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueGreaterThan(String value) {
            addCriterion("link_value >", value, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueGreaterThanOrEqualTo(String value) {
            addCriterion("link_value >=", value, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueLessThan(String value) {
            addCriterion("link_value <", value, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueLessThanOrEqualTo(String value) {
            addCriterion("link_value <=", value, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueLike(String value) {
            addCriterion("link_value like", value, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueNotLike(String value) {
            addCriterion("link_value not like", value, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueIn(List<String> values) {
            addCriterion("link_value in", values, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueNotIn(List<String> values) {
            addCriterion("link_value not in", values, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueBetween(String value1, String value2) {
            addCriterion("link_value between", value1, value2, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueNotBetween(String value1, String value2) {
            addCriterion("link_value not between", value1, value2, "linkValue");
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

        public Criteria andConvertIdIsNull() {
            addCriterion("convert_id is null");
            return (Criteria) this;
        }

        public Criteria andConvertIdIsNotNull() {
            addCriterion("convert_id is not null");
            return (Criteria) this;
        }

        public Criteria andConvertIdEqualTo(String value) {
            addCriterion("convert_id =", value, "convertId");
            return (Criteria) this;
        }

        public Criteria andConvertIdNotEqualTo(String value) {
            addCriterion("convert_id <>", value, "convertId");
            return (Criteria) this;
        }

        public Criteria andConvertIdGreaterThan(String value) {
            addCriterion("convert_id >", value, "convertId");
            return (Criteria) this;
        }

        public Criteria andConvertIdGreaterThanOrEqualTo(String value) {
            addCriterion("convert_id >=", value, "convertId");
            return (Criteria) this;
        }

        public Criteria andConvertIdLessThan(String value) {
            addCriterion("convert_id <", value, "convertId");
            return (Criteria) this;
        }

        public Criteria andConvertIdLessThanOrEqualTo(String value) {
            addCriterion("convert_id <=", value, "convertId");
            return (Criteria) this;
        }

        public Criteria andConvertIdLike(String value) {
            addCriterion("convert_id like", value, "convertId");
            return (Criteria) this;
        }

        public Criteria andConvertIdNotLike(String value) {
            addCriterion("convert_id not like", value, "convertId");
            return (Criteria) this;
        }

        public Criteria andConvertIdIn(List<String> values) {
            addCriterion("convert_id in", values, "convertId");
            return (Criteria) this;
        }

        public Criteria andConvertIdNotIn(List<String> values) {
            addCriterion("convert_id not in", values, "convertId");
            return (Criteria) this;
        }

        public Criteria andConvertIdBetween(String value1, String value2) {
            addCriterion("convert_id between", value1, value2, "convertId");
            return (Criteria) this;
        }

        public Criteria andConvertIdNotBetween(String value1, String value2) {
            addCriterion("convert_id not between", value1, value2, "convertId");
            return (Criteria) this;
        }

        public Criteria andConvertId2IsNull() {
            addCriterion("convert_id2 is null");
            return (Criteria) this;
        }

        public Criteria andConvertId2IsNotNull() {
            addCriterion("convert_id2 is not null");
            return (Criteria) this;
        }

        public Criteria andConvertId2EqualTo(String value) {
            addCriterion("convert_id2 =", value, "convertId2");
            return (Criteria) this;
        }

        public Criteria andConvertId2NotEqualTo(String value) {
            addCriterion("convert_id2 <>", value, "convertId2");
            return (Criteria) this;
        }

        public Criteria andConvertId2GreaterThan(String value) {
            addCriterion("convert_id2 >", value, "convertId2");
            return (Criteria) this;
        }

        public Criteria andConvertId2GreaterThanOrEqualTo(String value) {
            addCriterion("convert_id2 >=", value, "convertId2");
            return (Criteria) this;
        }

        public Criteria andConvertId2LessThan(String value) {
            addCriterion("convert_id2 <", value, "convertId2");
            return (Criteria) this;
        }

        public Criteria andConvertId2LessThanOrEqualTo(String value) {
            addCriterion("convert_id2 <=", value, "convertId2");
            return (Criteria) this;
        }

        public Criteria andConvertId2Like(String value) {
            addCriterion("convert_id2 like", value, "convertId2");
            return (Criteria) this;
        }

        public Criteria andConvertId2NotLike(String value) {
            addCriterion("convert_id2 not like", value, "convertId2");
            return (Criteria) this;
        }

        public Criteria andConvertId2In(List<String> values) {
            addCriterion("convert_id2 in", values, "convertId2");
            return (Criteria) this;
        }

        public Criteria andConvertId2NotIn(List<String> values) {
            addCriterion("convert_id2 not in", values, "convertId2");
            return (Criteria) this;
        }

        public Criteria andConvertId2Between(String value1, String value2) {
            addCriterion("convert_id2 between", value1, value2, "convertId2");
            return (Criteria) this;
        }

        public Criteria andConvertId2NotBetween(String value1, String value2) {
            addCriterion("convert_id2 not between", value1, value2, "convertId2");
            return (Criteria) this;
        }

        public Criteria andTrackingIdIsNull() {
            addCriterion("tracking_id is null");
            return (Criteria) this;
        }

        public Criteria andTrackingIdIsNotNull() {
            addCriterion("tracking_id is not null");
            return (Criteria) this;
        }

        public Criteria andTrackingIdEqualTo(String value) {
            addCriterion("tracking_id =", value, "trackingId");
            return (Criteria) this;
        }

        public Criteria andTrackingIdNotEqualTo(String value) {
            addCriterion("tracking_id <>", value, "trackingId");
            return (Criteria) this;
        }

        public Criteria andTrackingIdGreaterThan(String value) {
            addCriterion("tracking_id >", value, "trackingId");
            return (Criteria) this;
        }

        public Criteria andTrackingIdGreaterThanOrEqualTo(String value) {
            addCriterion("tracking_id >=", value, "trackingId");
            return (Criteria) this;
        }

        public Criteria andTrackingIdLessThan(String value) {
            addCriterion("tracking_id <", value, "trackingId");
            return (Criteria) this;
        }

        public Criteria andTrackingIdLessThanOrEqualTo(String value) {
            addCriterion("tracking_id <=", value, "trackingId");
            return (Criteria) this;
        }

        public Criteria andTrackingIdLike(String value) {
            addCriterion("tracking_id like", value, "trackingId");
            return (Criteria) this;
        }

        public Criteria andTrackingIdNotLike(String value) {
            addCriterion("tracking_id not like", value, "trackingId");
            return (Criteria) this;
        }

        public Criteria andTrackingIdIn(List<String> values) {
            addCriterion("tracking_id in", values, "trackingId");
            return (Criteria) this;
        }

        public Criteria andTrackingIdNotIn(List<String> values) {
            addCriterion("tracking_id not in", values, "trackingId");
            return (Criteria) this;
        }

        public Criteria andTrackingIdBetween(String value1, String value2) {
            addCriterion("tracking_id between", value1, value2, "trackingId");
            return (Criteria) this;
        }

        public Criteria andTrackingIdNotBetween(String value1, String value2) {
            addCriterion("tracking_id not between", value1, value2, "trackingId");
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