package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CombineOrderExtendExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public CombineOrderExtendExample() {
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

        public Criteria andCombineOrderIdIsNull() {
            addCriterion("combine_order_id is null");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdIsNotNull() {
            addCriterion("combine_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdEqualTo(Integer value) {
            addCriterion("combine_order_id =", value, "combineOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdNotEqualTo(Integer value) {
            addCriterion("combine_order_id <>", value, "combineOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdGreaterThan(Integer value) {
            addCriterion("combine_order_id >", value, "combineOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("combine_order_id >=", value, "combineOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdLessThan(Integer value) {
            addCriterion("combine_order_id <", value, "combineOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("combine_order_id <=", value, "combineOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdIn(List<Integer> values) {
            addCriterion("combine_order_id in", values, "combineOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdNotIn(List<Integer> values) {
            addCriterion("combine_order_id not in", values, "combineOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("combine_order_id between", value1, value2, "combineOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("combine_order_id not between", value1, value2, "combineOrderId");
            return (Criteria) this;
        }

        public Criteria andSpreadnameIsNull() {
            addCriterion("spreadname is null");
            return (Criteria) this;
        }

        public Criteria andSpreadnameIsNotNull() {
            addCriterion("spreadname is not null");
            return (Criteria) this;
        }

        public Criteria andSpreadnameEqualTo(String value) {
            addCriterion("spreadname =", value, "spreadname");
            return (Criteria) this;
        }

        public Criteria andSpreadnameNotEqualTo(String value) {
            addCriterion("spreadname <>", value, "spreadname");
            return (Criteria) this;
        }

        public Criteria andSpreadnameGreaterThan(String value) {
            addCriterion("spreadname >", value, "spreadname");
            return (Criteria) this;
        }

        public Criteria andSpreadnameGreaterThanOrEqualTo(String value) {
            addCriterion("spreadname >=", value, "spreadname");
            return (Criteria) this;
        }

        public Criteria andSpreadnameLessThan(String value) {
            addCriterion("spreadname <", value, "spreadname");
            return (Criteria) this;
        }

        public Criteria andSpreadnameLessThanOrEqualTo(String value) {
            addCriterion("spreadname <=", value, "spreadname");
            return (Criteria) this;
        }

        public Criteria andSpreadnameLike(String value) {
            addCriterion("spreadname like", value, "spreadname");
            return (Criteria) this;
        }

        public Criteria andSpreadnameNotLike(String value) {
            addCriterion("spreadname not like", value, "spreadname");
            return (Criteria) this;
        }

        public Criteria andSpreadnameIn(List<String> values) {
            addCriterion("spreadname in", values, "spreadname");
            return (Criteria) this;
        }

        public Criteria andSpreadnameNotIn(List<String> values) {
            addCriterion("spreadname not in", values, "spreadname");
            return (Criteria) this;
        }

        public Criteria andSpreadnameBetween(String value1, String value2) {
            addCriterion("spreadname between", value1, value2, "spreadname");
            return (Criteria) this;
        }

        public Criteria andSpreadnameNotBetween(String value1, String value2) {
            addCriterion("spreadname not between", value1, value2, "spreadname");
            return (Criteria) this;
        }

        public Criteria andActivitynameIsNull() {
            addCriterion("activityname is null");
            return (Criteria) this;
        }

        public Criteria andActivitynameIsNotNull() {
            addCriterion("activityname is not null");
            return (Criteria) this;
        }

        public Criteria andActivitynameEqualTo(String value) {
            addCriterion("activityname =", value, "activityname");
            return (Criteria) this;
        }

        public Criteria andActivitynameNotEqualTo(String value) {
            addCriterion("activityname <>", value, "activityname");
            return (Criteria) this;
        }

        public Criteria andActivitynameGreaterThan(String value) {
            addCriterion("activityname >", value, "activityname");
            return (Criteria) this;
        }

        public Criteria andActivitynameGreaterThanOrEqualTo(String value) {
            addCriterion("activityname >=", value, "activityname");
            return (Criteria) this;
        }

        public Criteria andActivitynameLessThan(String value) {
            addCriterion("activityname <", value, "activityname");
            return (Criteria) this;
        }

        public Criteria andActivitynameLessThanOrEqualTo(String value) {
            addCriterion("activityname <=", value, "activityname");
            return (Criteria) this;
        }

        public Criteria andActivitynameLike(String value) {
            addCriterion("activityname like", value, "activityname");
            return (Criteria) this;
        }

        public Criteria andActivitynameNotLike(String value) {
            addCriterion("activityname not like", value, "activityname");
            return (Criteria) this;
        }

        public Criteria andActivitynameIn(List<String> values) {
            addCriterion("activityname in", values, "activityname");
            return (Criteria) this;
        }

        public Criteria andActivitynameNotIn(List<String> values) {
            addCriterion("activityname not in", values, "activityname");
            return (Criteria) this;
        }

        public Criteria andActivitynameBetween(String value1, String value2) {
            addCriterion("activityname between", value1, value2, "activityname");
            return (Criteria) this;
        }

        public Criteria andActivitynameNotBetween(String value1, String value2) {
            addCriterion("activityname not between", value1, value2, "activityname");
            return (Criteria) this;
        }

        public Criteria andChannelIsNull() {
            addCriterion("channel is null");
            return (Criteria) this;
        }

        public Criteria andChannelIsNotNull() {
            addCriterion("channel is not null");
            return (Criteria) this;
        }

        public Criteria andChannelEqualTo(String value) {
            addCriterion("channel =", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotEqualTo(String value) {
            addCriterion("channel <>", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThan(String value) {
            addCriterion("channel >", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThanOrEqualTo(String value) {
            addCriterion("channel >=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThan(String value) {
            addCriterion("channel <", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThanOrEqualTo(String value) {
            addCriterion("channel <=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLike(String value) {
            addCriterion("channel like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotLike(String value) {
            addCriterion("channel not like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelIn(List<String> values) {
            addCriterion("channel in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotIn(List<String> values) {
            addCriterion("channel not in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelBetween(String value1, String value2) {
            addCriterion("channel between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotBetween(String value1, String value2) {
            addCriterion("channel not between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andTrackingioCommitStatusIsNull() {
            addCriterion("trackingio_commit_status is null");
            return (Criteria) this;
        }

        public Criteria andTrackingioCommitStatusIsNotNull() {
            addCriterion("trackingio_commit_status is not null");
            return (Criteria) this;
        }

        public Criteria andTrackingioCommitStatusEqualTo(String value) {
            addCriterion("trackingio_commit_status =", value, "trackingioCommitStatus");
            return (Criteria) this;
        }

        public Criteria andTrackingioCommitStatusNotEqualTo(String value) {
            addCriterion("trackingio_commit_status <>", value, "trackingioCommitStatus");
            return (Criteria) this;
        }

        public Criteria andTrackingioCommitStatusGreaterThan(String value) {
            addCriterion("trackingio_commit_status >", value, "trackingioCommitStatus");
            return (Criteria) this;
        }

        public Criteria andTrackingioCommitStatusGreaterThanOrEqualTo(String value) {
            addCriterion("trackingio_commit_status >=", value, "trackingioCommitStatus");
            return (Criteria) this;
        }

        public Criteria andTrackingioCommitStatusLessThan(String value) {
            addCriterion("trackingio_commit_status <", value, "trackingioCommitStatus");
            return (Criteria) this;
        }

        public Criteria andTrackingioCommitStatusLessThanOrEqualTo(String value) {
            addCriterion("trackingio_commit_status <=", value, "trackingioCommitStatus");
            return (Criteria) this;
        }

        public Criteria andTrackingioCommitStatusLike(String value) {
            addCriterion("trackingio_commit_status like", value, "trackingioCommitStatus");
            return (Criteria) this;
        }

        public Criteria andTrackingioCommitStatusNotLike(String value) {
            addCriterion("trackingio_commit_status not like", value, "trackingioCommitStatus");
            return (Criteria) this;
        }

        public Criteria andTrackingioCommitStatusIn(List<String> values) {
            addCriterion("trackingio_commit_status in", values, "trackingioCommitStatus");
            return (Criteria) this;
        }

        public Criteria andTrackingioCommitStatusNotIn(List<String> values) {
            addCriterion("trackingio_commit_status not in", values, "trackingioCommitStatus");
            return (Criteria) this;
        }

        public Criteria andTrackingioCommitStatusBetween(String value1, String value2) {
            addCriterion("trackingio_commit_status between", value1, value2, "trackingioCommitStatus");
            return (Criteria) this;
        }

        public Criteria andTrackingioCommitStatusNotBetween(String value1, String value2) {
            addCriterion("trackingio_commit_status not between", value1, value2, "trackingioCommitStatus");
            return (Criteria) this;
        }

        public Criteria andTrackingioCommitTimeIsNull() {
            addCriterion("trackingio_commit_time is null");
            return (Criteria) this;
        }

        public Criteria andTrackingioCommitTimeIsNotNull() {
            addCriterion("trackingio_commit_time is not null");
            return (Criteria) this;
        }

        public Criteria andTrackingioCommitTimeEqualTo(Date value) {
            addCriterion("trackingio_commit_time =", value, "trackingioCommitTime");
            return (Criteria) this;
        }

        public Criteria andTrackingioCommitTimeNotEqualTo(Date value) {
            addCriterion("trackingio_commit_time <>", value, "trackingioCommitTime");
            return (Criteria) this;
        }

        public Criteria andTrackingioCommitTimeGreaterThan(Date value) {
            addCriterion("trackingio_commit_time >", value, "trackingioCommitTime");
            return (Criteria) this;
        }

        public Criteria andTrackingioCommitTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("trackingio_commit_time >=", value, "trackingioCommitTime");
            return (Criteria) this;
        }

        public Criteria andTrackingioCommitTimeLessThan(Date value) {
            addCriterion("trackingio_commit_time <", value, "trackingioCommitTime");
            return (Criteria) this;
        }

        public Criteria andTrackingioCommitTimeLessThanOrEqualTo(Date value) {
            addCriterion("trackingio_commit_time <=", value, "trackingioCommitTime");
            return (Criteria) this;
        }

        public Criteria andTrackingioCommitTimeIn(List<Date> values) {
            addCriterion("trackingio_commit_time in", values, "trackingioCommitTime");
            return (Criteria) this;
        }

        public Criteria andTrackingioCommitTimeNotIn(List<Date> values) {
            addCriterion("trackingio_commit_time not in", values, "trackingioCommitTime");
            return (Criteria) this;
        }

        public Criteria andTrackingioCommitTimeBetween(Date value1, Date value2) {
            addCriterion("trackingio_commit_time between", value1, value2, "trackingioCommitTime");
            return (Criteria) this;
        }

        public Criteria andTrackingioCommitTimeNotBetween(Date value1, Date value2) {
            addCriterion("trackingio_commit_time not between", value1, value2, "trackingioCommitTime");
            return (Criteria) this;
        }

        public Criteria andIsSvipMemberIsNull() {
            addCriterion("is_svip_member is null");
            return (Criteria) this;
        }

        public Criteria andIsSvipMemberIsNotNull() {
            addCriterion("is_svip_member is not null");
            return (Criteria) this;
        }

        public Criteria andIsSvipMemberEqualTo(String value) {
            addCriterion("is_svip_member =", value, "isSvipMember");
            return (Criteria) this;
        }

        public Criteria andIsSvipMemberNotEqualTo(String value) {
            addCriterion("is_svip_member <>", value, "isSvipMember");
            return (Criteria) this;
        }

        public Criteria andIsSvipMemberGreaterThan(String value) {
            addCriterion("is_svip_member >", value, "isSvipMember");
            return (Criteria) this;
        }

        public Criteria andIsSvipMemberGreaterThanOrEqualTo(String value) {
            addCriterion("is_svip_member >=", value, "isSvipMember");
            return (Criteria) this;
        }

        public Criteria andIsSvipMemberLessThan(String value) {
            addCriterion("is_svip_member <", value, "isSvipMember");
            return (Criteria) this;
        }

        public Criteria andIsSvipMemberLessThanOrEqualTo(String value) {
            addCriterion("is_svip_member <=", value, "isSvipMember");
            return (Criteria) this;
        }

        public Criteria andIsSvipMemberLike(String value) {
            addCriterion("is_svip_member like", value, "isSvipMember");
            return (Criteria) this;
        }

        public Criteria andIsSvipMemberNotLike(String value) {
            addCriterion("is_svip_member not like", value, "isSvipMember");
            return (Criteria) this;
        }

        public Criteria andIsSvipMemberIn(List<String> values) {
            addCriterion("is_svip_member in", values, "isSvipMember");
            return (Criteria) this;
        }

        public Criteria andIsSvipMemberNotIn(List<String> values) {
            addCriterion("is_svip_member not in", values, "isSvipMember");
            return (Criteria) this;
        }

        public Criteria andIsSvipMemberBetween(String value1, String value2) {
            addCriterion("is_svip_member between", value1, value2, "isSvipMember");
            return (Criteria) this;
        }

        public Criteria andIsSvipMemberNotBetween(String value1, String value2) {
            addCriterion("is_svip_member not between", value1, value2, "isSvipMember");
            return (Criteria) this;
        }

        public Criteria andSprChnlIsNull() {
            addCriterion("spr_chnl is null");
            return (Criteria) this;
        }

        public Criteria andSprChnlIsNotNull() {
            addCriterion("spr_chnl is not null");
            return (Criteria) this;
        }

        public Criteria andSprChnlEqualTo(String value) {
            addCriterion("spr_chnl =", value, "sprChnl");
            return (Criteria) this;
        }

        public Criteria andSprChnlNotEqualTo(String value) {
            addCriterion("spr_chnl <>", value, "sprChnl");
            return (Criteria) this;
        }

        public Criteria andSprChnlGreaterThan(String value) {
            addCriterion("spr_chnl >", value, "sprChnl");
            return (Criteria) this;
        }

        public Criteria andSprChnlGreaterThanOrEqualTo(String value) {
            addCriterion("spr_chnl >=", value, "sprChnl");
            return (Criteria) this;
        }

        public Criteria andSprChnlLessThan(String value) {
            addCriterion("spr_chnl <", value, "sprChnl");
            return (Criteria) this;
        }

        public Criteria andSprChnlLessThanOrEqualTo(String value) {
            addCriterion("spr_chnl <=", value, "sprChnl");
            return (Criteria) this;
        }

        public Criteria andSprChnlLike(String value) {
            addCriterion("spr_chnl like", value, "sprChnl");
            return (Criteria) this;
        }

        public Criteria andSprChnlNotLike(String value) {
            addCriterion("spr_chnl not like", value, "sprChnl");
            return (Criteria) this;
        }

        public Criteria andSprChnlIn(List<String> values) {
            addCriterion("spr_chnl in", values, "sprChnl");
            return (Criteria) this;
        }

        public Criteria andSprChnlNotIn(List<String> values) {
            addCriterion("spr_chnl not in", values, "sprChnl");
            return (Criteria) this;
        }

        public Criteria andSprChnlBetween(String value1, String value2) {
            addCriterion("spr_chnl between", value1, value2, "sprChnl");
            return (Criteria) this;
        }

        public Criteria andSprChnlNotBetween(String value1, String value2) {
            addCriterion("spr_chnl not between", value1, value2, "sprChnl");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightIsNull() {
            addCriterion("manage_self_freight is null");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightIsNotNull() {
            addCriterion("manage_self_freight is not null");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightEqualTo(BigDecimal value) {
            addCriterion("manage_self_freight =", value, "manageSelfFreight");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightNotEqualTo(BigDecimal value) {
            addCriterion("manage_self_freight <>", value, "manageSelfFreight");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightGreaterThan(BigDecimal value) {
            addCriterion("manage_self_freight >", value, "manageSelfFreight");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("manage_self_freight >=", value, "manageSelfFreight");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightLessThan(BigDecimal value) {
            addCriterion("manage_self_freight <", value, "manageSelfFreight");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("manage_self_freight <=", value, "manageSelfFreight");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightIn(List<BigDecimal> values) {
            addCriterion("manage_self_freight in", values, "manageSelfFreight");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightNotIn(List<BigDecimal> values) {
            addCriterion("manage_self_freight not in", values, "manageSelfFreight");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("manage_self_freight between", value1, value2, "manageSelfFreight");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("manage_self_freight not between", value1, value2, "manageSelfFreight");
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