package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrackDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public TrackDataExample() {
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andSpreadurlIsNull() {
            addCriterion("spreadurl is null");
            return (Criteria) this;
        }

        public Criteria andSpreadurlIsNotNull() {
            addCriterion("spreadurl is not null");
            return (Criteria) this;
        }

        public Criteria andSpreadurlEqualTo(String value) {
            addCriterion("spreadurl =", value, "spreadurl");
            return (Criteria) this;
        }

        public Criteria andSpreadurlNotEqualTo(String value) {
            addCriterion("spreadurl <>", value, "spreadurl");
            return (Criteria) this;
        }

        public Criteria andSpreadurlGreaterThan(String value) {
            addCriterion("spreadurl >", value, "spreadurl");
            return (Criteria) this;
        }

        public Criteria andSpreadurlGreaterThanOrEqualTo(String value) {
            addCriterion("spreadurl >=", value, "spreadurl");
            return (Criteria) this;
        }

        public Criteria andSpreadurlLessThan(String value) {
            addCriterion("spreadurl <", value, "spreadurl");
            return (Criteria) this;
        }

        public Criteria andSpreadurlLessThanOrEqualTo(String value) {
            addCriterion("spreadurl <=", value, "spreadurl");
            return (Criteria) this;
        }

        public Criteria andSpreadurlLike(String value) {
            addCriterion("spreadurl like", value, "spreadurl");
            return (Criteria) this;
        }

        public Criteria andSpreadurlNotLike(String value) {
            addCriterion("spreadurl not like", value, "spreadurl");
            return (Criteria) this;
        }

        public Criteria andSpreadurlIn(List<String> values) {
            addCriterion("spreadurl in", values, "spreadurl");
            return (Criteria) this;
        }

        public Criteria andSpreadurlNotIn(List<String> values) {
            addCriterion("spreadurl not in", values, "spreadurl");
            return (Criteria) this;
        }

        public Criteria andSpreadurlBetween(String value1, String value2) {
            addCriterion("spreadurl between", value1, value2, "spreadurl");
            return (Criteria) this;
        }

        public Criteria andSpreadurlNotBetween(String value1, String value2) {
            addCriterion("spreadurl not between", value1, value2, "spreadurl");
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

        public Criteria andFirstspreadnameIsNull() {
            addCriterion("firstSpreadname is null");
            return (Criteria) this;
        }

        public Criteria andFirstspreadnameIsNotNull() {
            addCriterion("firstSpreadname is not null");
            return (Criteria) this;
        }

        public Criteria andFirstspreadnameEqualTo(String value) {
            addCriterion("firstSpreadname =", value, "firstspreadname");
            return (Criteria) this;
        }

        public Criteria andFirstspreadnameNotEqualTo(String value) {
            addCriterion("firstSpreadname <>", value, "firstspreadname");
            return (Criteria) this;
        }

        public Criteria andFirstspreadnameGreaterThan(String value) {
            addCriterion("firstSpreadname >", value, "firstspreadname");
            return (Criteria) this;
        }

        public Criteria andFirstspreadnameGreaterThanOrEqualTo(String value) {
            addCriterion("firstSpreadname >=", value, "firstspreadname");
            return (Criteria) this;
        }

        public Criteria andFirstspreadnameLessThan(String value) {
            addCriterion("firstSpreadname <", value, "firstspreadname");
            return (Criteria) this;
        }

        public Criteria andFirstspreadnameLessThanOrEqualTo(String value) {
            addCriterion("firstSpreadname <=", value, "firstspreadname");
            return (Criteria) this;
        }

        public Criteria andFirstspreadnameLike(String value) {
            addCriterion("firstSpreadname like", value, "firstspreadname");
            return (Criteria) this;
        }

        public Criteria andFirstspreadnameNotLike(String value) {
            addCriterion("firstSpreadname not like", value, "firstspreadname");
            return (Criteria) this;
        }

        public Criteria andFirstspreadnameIn(List<String> values) {
            addCriterion("firstSpreadname in", values, "firstspreadname");
            return (Criteria) this;
        }

        public Criteria andFirstspreadnameNotIn(List<String> values) {
            addCriterion("firstSpreadname not in", values, "firstspreadname");
            return (Criteria) this;
        }

        public Criteria andFirstspreadnameBetween(String value1, String value2) {
            addCriterion("firstSpreadname between", value1, value2, "firstspreadname");
            return (Criteria) this;
        }

        public Criteria andFirstspreadnameNotBetween(String value1, String value2) {
            addCriterion("firstSpreadname not between", value1, value2, "firstspreadname");
            return (Criteria) this;
        }

        public Criteria andFirstchannelIsNull() {
            addCriterion("firstChannel is null");
            return (Criteria) this;
        }

        public Criteria andFirstchannelIsNotNull() {
            addCriterion("firstChannel is not null");
            return (Criteria) this;
        }

        public Criteria andFirstchannelEqualTo(String value) {
            addCriterion("firstChannel =", value, "firstchannel");
            return (Criteria) this;
        }

        public Criteria andFirstchannelNotEqualTo(String value) {
            addCriterion("firstChannel <>", value, "firstchannel");
            return (Criteria) this;
        }

        public Criteria andFirstchannelGreaterThan(String value) {
            addCriterion("firstChannel >", value, "firstchannel");
            return (Criteria) this;
        }

        public Criteria andFirstchannelGreaterThanOrEqualTo(String value) {
            addCriterion("firstChannel >=", value, "firstchannel");
            return (Criteria) this;
        }

        public Criteria andFirstchannelLessThan(String value) {
            addCriterion("firstChannel <", value, "firstchannel");
            return (Criteria) this;
        }

        public Criteria andFirstchannelLessThanOrEqualTo(String value) {
            addCriterion("firstChannel <=", value, "firstchannel");
            return (Criteria) this;
        }

        public Criteria andFirstchannelLike(String value) {
            addCriterion("firstChannel like", value, "firstchannel");
            return (Criteria) this;
        }

        public Criteria andFirstchannelNotLike(String value) {
            addCriterion("firstChannel not like", value, "firstchannel");
            return (Criteria) this;
        }

        public Criteria andFirstchannelIn(List<String> values) {
            addCriterion("firstChannel in", values, "firstchannel");
            return (Criteria) this;
        }

        public Criteria andFirstchannelNotIn(List<String> values) {
            addCriterion("firstChannel not in", values, "firstchannel");
            return (Criteria) this;
        }

        public Criteria andFirstchannelBetween(String value1, String value2) {
            addCriterion("firstChannel between", value1, value2, "firstchannel");
            return (Criteria) this;
        }

        public Criteria andFirstchannelNotBetween(String value1, String value2) {
            addCriterion("firstChannel not between", value1, value2, "firstchannel");
            return (Criteria) this;
        }

        public Criteria andClicktimeIsNull() {
            addCriterion("clicktime is null");
            return (Criteria) this;
        }

        public Criteria andClicktimeIsNotNull() {
            addCriterion("clicktime is not null");
            return (Criteria) this;
        }

        public Criteria andClicktimeEqualTo(Date value) {
            addCriterion("clicktime =", value, "clicktime");
            return (Criteria) this;
        }

        public Criteria andClicktimeNotEqualTo(Date value) {
            addCriterion("clicktime <>", value, "clicktime");
            return (Criteria) this;
        }

        public Criteria andClicktimeGreaterThan(Date value) {
            addCriterion("clicktime >", value, "clicktime");
            return (Criteria) this;
        }

        public Criteria andClicktimeGreaterThanOrEqualTo(Date value) {
            addCriterion("clicktime >=", value, "clicktime");
            return (Criteria) this;
        }

        public Criteria andClicktimeLessThan(Date value) {
            addCriterion("clicktime <", value, "clicktime");
            return (Criteria) this;
        }

        public Criteria andClicktimeLessThanOrEqualTo(Date value) {
            addCriterion("clicktime <=", value, "clicktime");
            return (Criteria) this;
        }

        public Criteria andClicktimeIn(List<Date> values) {
            addCriterion("clicktime in", values, "clicktime");
            return (Criteria) this;
        }

        public Criteria andClicktimeNotIn(List<Date> values) {
            addCriterion("clicktime not in", values, "clicktime");
            return (Criteria) this;
        }

        public Criteria andClicktimeBetween(Date value1, Date value2) {
            addCriterion("clicktime between", value1, value2, "clicktime");
            return (Criteria) this;
        }

        public Criteria andClicktimeNotBetween(Date value1, Date value2) {
            addCriterion("clicktime not between", value1, value2, "clicktime");
            return (Criteria) this;
        }

        public Criteria andUaIsNull() {
            addCriterion("ua is null");
            return (Criteria) this;
        }

        public Criteria andUaIsNotNull() {
            addCriterion("ua is not null");
            return (Criteria) this;
        }

        public Criteria andUaEqualTo(String value) {
            addCriterion("ua =", value, "ua");
            return (Criteria) this;
        }

        public Criteria andUaNotEqualTo(String value) {
            addCriterion("ua <>", value, "ua");
            return (Criteria) this;
        }

        public Criteria andUaGreaterThan(String value) {
            addCriterion("ua >", value, "ua");
            return (Criteria) this;
        }

        public Criteria andUaGreaterThanOrEqualTo(String value) {
            addCriterion("ua >=", value, "ua");
            return (Criteria) this;
        }

        public Criteria andUaLessThan(String value) {
            addCriterion("ua <", value, "ua");
            return (Criteria) this;
        }

        public Criteria andUaLessThanOrEqualTo(String value) {
            addCriterion("ua <=", value, "ua");
            return (Criteria) this;
        }

        public Criteria andUaLike(String value) {
            addCriterion("ua like", value, "ua");
            return (Criteria) this;
        }

        public Criteria andUaNotLike(String value) {
            addCriterion("ua not like", value, "ua");
            return (Criteria) this;
        }

        public Criteria andUaIn(List<String> values) {
            addCriterion("ua in", values, "ua");
            return (Criteria) this;
        }

        public Criteria andUaNotIn(List<String> values) {
            addCriterion("ua not in", values, "ua");
            return (Criteria) this;
        }

        public Criteria andUaBetween(String value1, String value2) {
            addCriterion("ua between", value1, value2, "ua");
            return (Criteria) this;
        }

        public Criteria andUaNotBetween(String value1, String value2) {
            addCriterion("ua not between", value1, value2, "ua");
            return (Criteria) this;
        }

        public Criteria andUipIsNull() {
            addCriterion("uip is null");
            return (Criteria) this;
        }

        public Criteria andUipIsNotNull() {
            addCriterion("uip is not null");
            return (Criteria) this;
        }

        public Criteria andUipEqualTo(String value) {
            addCriterion("uip =", value, "uip");
            return (Criteria) this;
        }

        public Criteria andUipNotEqualTo(String value) {
            addCriterion("uip <>", value, "uip");
            return (Criteria) this;
        }

        public Criteria andUipGreaterThan(String value) {
            addCriterion("uip >", value, "uip");
            return (Criteria) this;
        }

        public Criteria andUipGreaterThanOrEqualTo(String value) {
            addCriterion("uip >=", value, "uip");
            return (Criteria) this;
        }

        public Criteria andUipLessThan(String value) {
            addCriterion("uip <", value, "uip");
            return (Criteria) this;
        }

        public Criteria andUipLessThanOrEqualTo(String value) {
            addCriterion("uip <=", value, "uip");
            return (Criteria) this;
        }

        public Criteria andUipLike(String value) {
            addCriterion("uip like", value, "uip");
            return (Criteria) this;
        }

        public Criteria andUipNotLike(String value) {
            addCriterion("uip not like", value, "uip");
            return (Criteria) this;
        }

        public Criteria andUipIn(List<String> values) {
            addCriterion("uip in", values, "uip");
            return (Criteria) this;
        }

        public Criteria andUipNotIn(List<String> values) {
            addCriterion("uip not in", values, "uip");
            return (Criteria) this;
        }

        public Criteria andUipBetween(String value1, String value2) {
            addCriterion("uip between", value1, value2, "uip");
            return (Criteria) this;
        }

        public Criteria andUipNotBetween(String value1, String value2) {
            addCriterion("uip not between", value1, value2, "uip");
            return (Criteria) this;
        }

        public Criteria andAppkeyIsNull() {
            addCriterion("appkey is null");
            return (Criteria) this;
        }

        public Criteria andAppkeyIsNotNull() {
            addCriterion("appkey is not null");
            return (Criteria) this;
        }

        public Criteria andAppkeyEqualTo(String value) {
            addCriterion("appkey =", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyNotEqualTo(String value) {
            addCriterion("appkey <>", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyGreaterThan(String value) {
            addCriterion("appkey >", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyGreaterThanOrEqualTo(String value) {
            addCriterion("appkey >=", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyLessThan(String value) {
            addCriterion("appkey <", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyLessThanOrEqualTo(String value) {
            addCriterion("appkey <=", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyLike(String value) {
            addCriterion("appkey like", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyNotLike(String value) {
            addCriterion("appkey not like", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyIn(List<String> values) {
            addCriterion("appkey in", values, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyNotIn(List<String> values) {
            addCriterion("appkey not in", values, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyBetween(String value1, String value2) {
            addCriterion("appkey between", value1, value2, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyNotBetween(String value1, String value2) {
            addCriterion("appkey not between", value1, value2, "appkey");
            return (Criteria) this;
        }

        public Criteria andActivetimeIsNull() {
            addCriterion("activetime is null");
            return (Criteria) this;
        }

        public Criteria andActivetimeIsNotNull() {
            addCriterion("activetime is not null");
            return (Criteria) this;
        }

        public Criteria andActivetimeEqualTo(Date value) {
            addCriterion("activetime =", value, "activetime");
            return (Criteria) this;
        }

        public Criteria andActivetimeNotEqualTo(Date value) {
            addCriterion("activetime <>", value, "activetime");
            return (Criteria) this;
        }

        public Criteria andActivetimeGreaterThan(Date value) {
            addCriterion("activetime >", value, "activetime");
            return (Criteria) this;
        }

        public Criteria andActivetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("activetime >=", value, "activetime");
            return (Criteria) this;
        }

        public Criteria andActivetimeLessThan(Date value) {
            addCriterion("activetime <", value, "activetime");
            return (Criteria) this;
        }

        public Criteria andActivetimeLessThanOrEqualTo(Date value) {
            addCriterion("activetime <=", value, "activetime");
            return (Criteria) this;
        }

        public Criteria andActivetimeIn(List<Date> values) {
            addCriterion("activetime in", values, "activetime");
            return (Criteria) this;
        }

        public Criteria andActivetimeNotIn(List<Date> values) {
            addCriterion("activetime not in", values, "activetime");
            return (Criteria) this;
        }

        public Criteria andActivetimeBetween(Date value1, Date value2) {
            addCriterion("activetime between", value1, value2, "activetime");
            return (Criteria) this;
        }

        public Criteria andActivetimeNotBetween(Date value1, Date value2) {
            addCriterion("activetime not between", value1, value2, "activetime");
            return (Criteria) this;
        }

        public Criteria andOsversionIsNull() {
            addCriterion("osversion is null");
            return (Criteria) this;
        }

        public Criteria andOsversionIsNotNull() {
            addCriterion("osversion is not null");
            return (Criteria) this;
        }

        public Criteria andOsversionEqualTo(String value) {
            addCriterion("osversion =", value, "osversion");
            return (Criteria) this;
        }

        public Criteria andOsversionNotEqualTo(String value) {
            addCriterion("osversion <>", value, "osversion");
            return (Criteria) this;
        }

        public Criteria andOsversionGreaterThan(String value) {
            addCriterion("osversion >", value, "osversion");
            return (Criteria) this;
        }

        public Criteria andOsversionGreaterThanOrEqualTo(String value) {
            addCriterion("osversion >=", value, "osversion");
            return (Criteria) this;
        }

        public Criteria andOsversionLessThan(String value) {
            addCriterion("osversion <", value, "osversion");
            return (Criteria) this;
        }

        public Criteria andOsversionLessThanOrEqualTo(String value) {
            addCriterion("osversion <=", value, "osversion");
            return (Criteria) this;
        }

        public Criteria andOsversionLike(String value) {
            addCriterion("osversion like", value, "osversion");
            return (Criteria) this;
        }

        public Criteria andOsversionNotLike(String value) {
            addCriterion("osversion not like", value, "osversion");
            return (Criteria) this;
        }

        public Criteria andOsversionIn(List<String> values) {
            addCriterion("osversion in", values, "osversion");
            return (Criteria) this;
        }

        public Criteria andOsversionNotIn(List<String> values) {
            addCriterion("osversion not in", values, "osversion");
            return (Criteria) this;
        }

        public Criteria andOsversionBetween(String value1, String value2) {
            addCriterion("osversion between", value1, value2, "osversion");
            return (Criteria) this;
        }

        public Criteria andOsversionNotBetween(String value1, String value2) {
            addCriterion("osversion not between", value1, value2, "osversion");
            return (Criteria) this;
        }

        public Criteria andDevicetypeIsNull() {
            addCriterion("devicetype is null");
            return (Criteria) this;
        }

        public Criteria andDevicetypeIsNotNull() {
            addCriterion("devicetype is not null");
            return (Criteria) this;
        }

        public Criteria andDevicetypeEqualTo(String value) {
            addCriterion("devicetype =", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeNotEqualTo(String value) {
            addCriterion("devicetype <>", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeGreaterThan(String value) {
            addCriterion("devicetype >", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeGreaterThanOrEqualTo(String value) {
            addCriterion("devicetype >=", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeLessThan(String value) {
            addCriterion("devicetype <", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeLessThanOrEqualTo(String value) {
            addCriterion("devicetype <=", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeLike(String value) {
            addCriterion("devicetype like", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeNotLike(String value) {
            addCriterion("devicetype not like", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeIn(List<String> values) {
            addCriterion("devicetype in", values, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeNotIn(List<String> values) {
            addCriterion("devicetype not in", values, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeBetween(String value1, String value2) {
            addCriterion("devicetype between", value1, value2, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeNotBetween(String value1, String value2) {
            addCriterion("devicetype not between", value1, value2, "devicetype");
            return (Criteria) this;
        }

        public Criteria andIdfaIsNull() {
            addCriterion("idfa is null");
            return (Criteria) this;
        }

        public Criteria andIdfaIsNotNull() {
            addCriterion("idfa is not null");
            return (Criteria) this;
        }

        public Criteria andIdfaEqualTo(String value) {
            addCriterion("idfa =", value, "idfa");
            return (Criteria) this;
        }

        public Criteria andIdfaNotEqualTo(String value) {
            addCriterion("idfa <>", value, "idfa");
            return (Criteria) this;
        }

        public Criteria andIdfaGreaterThan(String value) {
            addCriterion("idfa >", value, "idfa");
            return (Criteria) this;
        }

        public Criteria andIdfaGreaterThanOrEqualTo(String value) {
            addCriterion("idfa >=", value, "idfa");
            return (Criteria) this;
        }

        public Criteria andIdfaLessThan(String value) {
            addCriterion("idfa <", value, "idfa");
            return (Criteria) this;
        }

        public Criteria andIdfaLessThanOrEqualTo(String value) {
            addCriterion("idfa <=", value, "idfa");
            return (Criteria) this;
        }

        public Criteria andIdfaLike(String value) {
            addCriterion("idfa like", value, "idfa");
            return (Criteria) this;
        }

        public Criteria andIdfaNotLike(String value) {
            addCriterion("idfa not like", value, "idfa");
            return (Criteria) this;
        }

        public Criteria andIdfaIn(List<String> values) {
            addCriterion("idfa in", values, "idfa");
            return (Criteria) this;
        }

        public Criteria andIdfaNotIn(List<String> values) {
            addCriterion("idfa not in", values, "idfa");
            return (Criteria) this;
        }

        public Criteria andIdfaBetween(String value1, String value2) {
            addCriterion("idfa between", value1, value2, "idfa");
            return (Criteria) this;
        }

        public Criteria andIdfaNotBetween(String value1, String value2) {
            addCriterion("idfa not between", value1, value2, "idfa");
            return (Criteria) this;
        }

        public Criteria andMacIsNull() {
            addCriterion("mac is null");
            return (Criteria) this;
        }

        public Criteria andMacIsNotNull() {
            addCriterion("mac is not null");
            return (Criteria) this;
        }

        public Criteria andMacEqualTo(String value) {
            addCriterion("mac =", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacNotEqualTo(String value) {
            addCriterion("mac <>", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacGreaterThan(String value) {
            addCriterion("mac >", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacGreaterThanOrEqualTo(String value) {
            addCriterion("mac >=", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacLessThan(String value) {
            addCriterion("mac <", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacLessThanOrEqualTo(String value) {
            addCriterion("mac <=", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacLike(String value) {
            addCriterion("mac like", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacNotLike(String value) {
            addCriterion("mac not like", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacIn(List<String> values) {
            addCriterion("mac in", values, "mac");
            return (Criteria) this;
        }

        public Criteria andMacNotIn(List<String> values) {
            addCriterion("mac not in", values, "mac");
            return (Criteria) this;
        }

        public Criteria andMacBetween(String value1, String value2) {
            addCriterion("mac between", value1, value2, "mac");
            return (Criteria) this;
        }

        public Criteria andMacNotBetween(String value1, String value2) {
            addCriterion("mac not between", value1, value2, "mac");
            return (Criteria) this;
        }

        public Criteria andImeiIsNull() {
            addCriterion("imei is null");
            return (Criteria) this;
        }

        public Criteria andImeiIsNotNull() {
            addCriterion("imei is not null");
            return (Criteria) this;
        }

        public Criteria andImeiEqualTo(String value) {
            addCriterion("imei =", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiNotEqualTo(String value) {
            addCriterion("imei <>", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiGreaterThan(String value) {
            addCriterion("imei >", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiGreaterThanOrEqualTo(String value) {
            addCriterion("imei >=", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiLessThan(String value) {
            addCriterion("imei <", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiLessThanOrEqualTo(String value) {
            addCriterion("imei <=", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiLike(String value) {
            addCriterion("imei like", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiNotLike(String value) {
            addCriterion("imei not like", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiIn(List<String> values) {
            addCriterion("imei in", values, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiNotIn(List<String> values) {
            addCriterion("imei not in", values, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiBetween(String value1, String value2) {
            addCriterion("imei between", value1, value2, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiNotBetween(String value1, String value2) {
            addCriterion("imei not between", value1, value2, "imei");
            return (Criteria) this;
        }

        public Criteria andSkeyIsNull() {
            addCriterion("skey is null");
            return (Criteria) this;
        }

        public Criteria andSkeyIsNotNull() {
            addCriterion("skey is not null");
            return (Criteria) this;
        }

        public Criteria andSkeyEqualTo(String value) {
            addCriterion("skey =", value, "skey");
            return (Criteria) this;
        }

        public Criteria andSkeyNotEqualTo(String value) {
            addCriterion("skey <>", value, "skey");
            return (Criteria) this;
        }

        public Criteria andSkeyGreaterThan(String value) {
            addCriterion("skey >", value, "skey");
            return (Criteria) this;
        }

        public Criteria andSkeyGreaterThanOrEqualTo(String value) {
            addCriterion("skey >=", value, "skey");
            return (Criteria) this;
        }

        public Criteria andSkeyLessThan(String value) {
            addCriterion("skey <", value, "skey");
            return (Criteria) this;
        }

        public Criteria andSkeyLessThanOrEqualTo(String value) {
            addCriterion("skey <=", value, "skey");
            return (Criteria) this;
        }

        public Criteria andSkeyLike(String value) {
            addCriterion("skey like", value, "skey");
            return (Criteria) this;
        }

        public Criteria andSkeyNotLike(String value) {
            addCriterion("skey not like", value, "skey");
            return (Criteria) this;
        }

        public Criteria andSkeyIn(List<String> values) {
            addCriterion("skey in", values, "skey");
            return (Criteria) this;
        }

        public Criteria andSkeyNotIn(List<String> values) {
            addCriterion("skey not in", values, "skey");
            return (Criteria) this;
        }

        public Criteria andSkeyBetween(String value1, String value2) {
            addCriterion("skey between", value1, value2, "skey");
            return (Criteria) this;
        }

        public Criteria andSkeyNotBetween(String value1, String value2) {
            addCriterion("skey not between", value1, value2, "skey");
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