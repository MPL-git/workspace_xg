package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ToutiaoAdInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public ToutiaoAdInfoExample() {
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

        public Criteria andAdvertiserIdIsNull() {
            addCriterion("advertiser_id is null");
            return (Criteria) this;
        }

        public Criteria andAdvertiserIdIsNotNull() {
            addCriterion("advertiser_id is not null");
            return (Criteria) this;
        }

        public Criteria andAdvertiserIdEqualTo(String value) {
            addCriterion("advertiser_id =", value, "advertiserId");
            return (Criteria) this;
        }

        public Criteria andAdvertiserIdNotEqualTo(String value) {
            addCriterion("advertiser_id <>", value, "advertiserId");
            return (Criteria) this;
        }

        public Criteria andAdvertiserIdGreaterThan(String value) {
            addCriterion("advertiser_id >", value, "advertiserId");
            return (Criteria) this;
        }

        public Criteria andAdvertiserIdGreaterThanOrEqualTo(String value) {
            addCriterion("advertiser_id >=", value, "advertiserId");
            return (Criteria) this;
        }

        public Criteria andAdvertiserIdLessThan(String value) {
            addCriterion("advertiser_id <", value, "advertiserId");
            return (Criteria) this;
        }

        public Criteria andAdvertiserIdLessThanOrEqualTo(String value) {
            addCriterion("advertiser_id <=", value, "advertiserId");
            return (Criteria) this;
        }

        public Criteria andAdvertiserIdLike(String value) {
            addCriterion("advertiser_id like", value, "advertiserId");
            return (Criteria) this;
        }

        public Criteria andAdvertiserIdNotLike(String value) {
            addCriterion("advertiser_id not like", value, "advertiserId");
            return (Criteria) this;
        }

        public Criteria andAdvertiserIdIn(List<String> values) {
            addCriterion("advertiser_id in", values, "advertiserId");
            return (Criteria) this;
        }

        public Criteria andAdvertiserIdNotIn(List<String> values) {
            addCriterion("advertiser_id not in", values, "advertiserId");
            return (Criteria) this;
        }

        public Criteria andAdvertiserIdBetween(String value1, String value2) {
            addCriterion("advertiser_id between", value1, value2, "advertiserId");
            return (Criteria) this;
        }

        public Criteria andAdvertiserIdNotBetween(String value1, String value2) {
            addCriterion("advertiser_id not between", value1, value2, "advertiserId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdIsNull() {
            addCriterion("campaign_id is null");
            return (Criteria) this;
        }

        public Criteria andCampaignIdIsNotNull() {
            addCriterion("campaign_id is not null");
            return (Criteria) this;
        }

        public Criteria andCampaignIdEqualTo(String value) {
            addCriterion("campaign_id =", value, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdNotEqualTo(String value) {
            addCriterion("campaign_id <>", value, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdGreaterThan(String value) {
            addCriterion("campaign_id >", value, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdGreaterThanOrEqualTo(String value) {
            addCriterion("campaign_id >=", value, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdLessThan(String value) {
            addCriterion("campaign_id <", value, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdLessThanOrEqualTo(String value) {
            addCriterion("campaign_id <=", value, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdLike(String value) {
            addCriterion("campaign_id like", value, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdNotLike(String value) {
            addCriterion("campaign_id not like", value, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdIn(List<String> values) {
            addCriterion("campaign_id in", values, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdNotIn(List<String> values) {
            addCriterion("campaign_id not in", values, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdBetween(String value1, String value2) {
            addCriterion("campaign_id between", value1, value2, "campaignId");
            return (Criteria) this;
        }

        public Criteria andCampaignIdNotBetween(String value1, String value2) {
            addCriterion("campaign_id not between", value1, value2, "campaignId");
            return (Criteria) this;
        }

        public Criteria andAdIdIsNull() {
            addCriterion("ad_id is null");
            return (Criteria) this;
        }

        public Criteria andAdIdIsNotNull() {
            addCriterion("ad_id is not null");
            return (Criteria) this;
        }

        public Criteria andAdIdEqualTo(String value) {
            addCriterion("ad_id =", value, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdNotEqualTo(String value) {
            addCriterion("ad_id <>", value, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdGreaterThan(String value) {
            addCriterion("ad_id >", value, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdGreaterThanOrEqualTo(String value) {
            addCriterion("ad_id >=", value, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdLessThan(String value) {
            addCriterion("ad_id <", value, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdLessThanOrEqualTo(String value) {
            addCriterion("ad_id <=", value, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdLike(String value) {
            addCriterion("ad_id like", value, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdNotLike(String value) {
            addCriterion("ad_id not like", value, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdIn(List<String> values) {
            addCriterion("ad_id in", values, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdNotIn(List<String> values) {
            addCriterion("ad_id not in", values, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdBetween(String value1, String value2) {
            addCriterion("ad_id between", value1, value2, "adId");
            return (Criteria) this;
        }

        public Criteria andAdIdNotBetween(String value1, String value2) {
            addCriterion("ad_id not between", value1, value2, "adId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(String value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(String value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(String value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(String value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(String value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(String value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLike(String value) {
            addCriterion("modify_time like", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotLike(String value) {
            addCriterion("modify_time not like", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<String> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<String> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(String value1, String value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(String value1, String value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andAdModifyTimeIsNull() {
            addCriterion("ad_modify_time is null");
            return (Criteria) this;
        }

        public Criteria andAdModifyTimeIsNotNull() {
            addCriterion("ad_modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andAdModifyTimeEqualTo(String value) {
            addCriterion("ad_modify_time =", value, "adModifyTime");
            return (Criteria) this;
        }

        public Criteria andAdModifyTimeNotEqualTo(String value) {
            addCriterion("ad_modify_time <>", value, "adModifyTime");
            return (Criteria) this;
        }

        public Criteria andAdModifyTimeGreaterThan(String value) {
            addCriterion("ad_modify_time >", value, "adModifyTime");
            return (Criteria) this;
        }

        public Criteria andAdModifyTimeGreaterThanOrEqualTo(String value) {
            addCriterion("ad_modify_time >=", value, "adModifyTime");
            return (Criteria) this;
        }

        public Criteria andAdModifyTimeLessThan(String value) {
            addCriterion("ad_modify_time <", value, "adModifyTime");
            return (Criteria) this;
        }

        public Criteria andAdModifyTimeLessThanOrEqualTo(String value) {
            addCriterion("ad_modify_time <=", value, "adModifyTime");
            return (Criteria) this;
        }

        public Criteria andAdModifyTimeLike(String value) {
            addCriterion("ad_modify_time like", value, "adModifyTime");
            return (Criteria) this;
        }

        public Criteria andAdModifyTimeNotLike(String value) {
            addCriterion("ad_modify_time not like", value, "adModifyTime");
            return (Criteria) this;
        }

        public Criteria andAdModifyTimeIn(List<String> values) {
            addCriterion("ad_modify_time in", values, "adModifyTime");
            return (Criteria) this;
        }

        public Criteria andAdModifyTimeNotIn(List<String> values) {
            addCriterion("ad_modify_time not in", values, "adModifyTime");
            return (Criteria) this;
        }

        public Criteria andAdModifyTimeBetween(String value1, String value2) {
            addCriterion("ad_modify_time between", value1, value2, "adModifyTime");
            return (Criteria) this;
        }

        public Criteria andAdModifyTimeNotBetween(String value1, String value2) {
            addCriterion("ad_modify_time not between", value1, value2, "adModifyTime");
            return (Criteria) this;
        }

        public Criteria andAdCreateTimeIsNull() {
            addCriterion("ad_create_time is null");
            return (Criteria) this;
        }

        public Criteria andAdCreateTimeIsNotNull() {
            addCriterion("ad_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andAdCreateTimeEqualTo(String value) {
            addCriterion("ad_create_time =", value, "adCreateTime");
            return (Criteria) this;
        }

        public Criteria andAdCreateTimeNotEqualTo(String value) {
            addCriterion("ad_create_time <>", value, "adCreateTime");
            return (Criteria) this;
        }

        public Criteria andAdCreateTimeGreaterThan(String value) {
            addCriterion("ad_create_time >", value, "adCreateTime");
            return (Criteria) this;
        }

        public Criteria andAdCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("ad_create_time >=", value, "adCreateTime");
            return (Criteria) this;
        }

        public Criteria andAdCreateTimeLessThan(String value) {
            addCriterion("ad_create_time <", value, "adCreateTime");
            return (Criteria) this;
        }

        public Criteria andAdCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("ad_create_time <=", value, "adCreateTime");
            return (Criteria) this;
        }

        public Criteria andAdCreateTimeLike(String value) {
            addCriterion("ad_create_time like", value, "adCreateTime");
            return (Criteria) this;
        }

        public Criteria andAdCreateTimeNotLike(String value) {
            addCriterion("ad_create_time not like", value, "adCreateTime");
            return (Criteria) this;
        }

        public Criteria andAdCreateTimeIn(List<String> values) {
            addCriterion("ad_create_time in", values, "adCreateTime");
            return (Criteria) this;
        }

        public Criteria andAdCreateTimeNotIn(List<String> values) {
            addCriterion("ad_create_time not in", values, "adCreateTime");
            return (Criteria) this;
        }

        public Criteria andAdCreateTimeBetween(String value1, String value2) {
            addCriterion("ad_create_time between", value1, value2, "adCreateTime");
            return (Criteria) this;
        }

        public Criteria andAdCreateTimeNotBetween(String value1, String value2) {
            addCriterion("ad_create_time not between", value1, value2, "adCreateTime");
            return (Criteria) this;
        }

        public Criteria andBudgetModeIsNull() {
            addCriterion("budget_mode is null");
            return (Criteria) this;
        }

        public Criteria andBudgetModeIsNotNull() {
            addCriterion("budget_mode is not null");
            return (Criteria) this;
        }

        public Criteria andBudgetModeEqualTo(String value) {
            addCriterion("budget_mode =", value, "budgetMode");
            return (Criteria) this;
        }

        public Criteria andBudgetModeNotEqualTo(String value) {
            addCriterion("budget_mode <>", value, "budgetMode");
            return (Criteria) this;
        }

        public Criteria andBudgetModeGreaterThan(String value) {
            addCriterion("budget_mode >", value, "budgetMode");
            return (Criteria) this;
        }

        public Criteria andBudgetModeGreaterThanOrEqualTo(String value) {
            addCriterion("budget_mode >=", value, "budgetMode");
            return (Criteria) this;
        }

        public Criteria andBudgetModeLessThan(String value) {
            addCriterion("budget_mode <", value, "budgetMode");
            return (Criteria) this;
        }

        public Criteria andBudgetModeLessThanOrEqualTo(String value) {
            addCriterion("budget_mode <=", value, "budgetMode");
            return (Criteria) this;
        }

        public Criteria andBudgetModeLike(String value) {
            addCriterion("budget_mode like", value, "budgetMode");
            return (Criteria) this;
        }

        public Criteria andBudgetModeNotLike(String value) {
            addCriterion("budget_mode not like", value, "budgetMode");
            return (Criteria) this;
        }

        public Criteria andBudgetModeIn(List<String> values) {
            addCriterion("budget_mode in", values, "budgetMode");
            return (Criteria) this;
        }

        public Criteria andBudgetModeNotIn(List<String> values) {
            addCriterion("budget_mode not in", values, "budgetMode");
            return (Criteria) this;
        }

        public Criteria andBudgetModeBetween(String value1, String value2) {
            addCriterion("budget_mode between", value1, value2, "budgetMode");
            return (Criteria) this;
        }

        public Criteria andBudgetModeNotBetween(String value1, String value2) {
            addCriterion("budget_mode not between", value1, value2, "budgetMode");
            return (Criteria) this;
        }

        public Criteria andBudgetIsNull() {
            addCriterion("budget is null");
            return (Criteria) this;
        }

        public Criteria andBudgetIsNotNull() {
            addCriterion("budget is not null");
            return (Criteria) this;
        }

        public Criteria andBudgetEqualTo(BigDecimal value) {
            addCriterion("budget =", value, "budget");
            return (Criteria) this;
        }

        public Criteria andBudgetNotEqualTo(BigDecimal value) {
            addCriterion("budget <>", value, "budget");
            return (Criteria) this;
        }

        public Criteria andBudgetGreaterThan(BigDecimal value) {
            addCriterion("budget >", value, "budget");
            return (Criteria) this;
        }

        public Criteria andBudgetGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("budget >=", value, "budget");
            return (Criteria) this;
        }

        public Criteria andBudgetLessThan(BigDecimal value) {
            addCriterion("budget <", value, "budget");
            return (Criteria) this;
        }

        public Criteria andBudgetLessThanOrEqualTo(BigDecimal value) {
            addCriterion("budget <=", value, "budget");
            return (Criteria) this;
        }

        public Criteria andBudgetIn(List<BigDecimal> values) {
            addCriterion("budget in", values, "budget");
            return (Criteria) this;
        }

        public Criteria andBudgetNotIn(List<BigDecimal> values) {
            addCriterion("budget not in", values, "budget");
            return (Criteria) this;
        }

        public Criteria andBudgetBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("budget between", value1, value2, "budget");
            return (Criteria) this;
        }

        public Criteria andBudgetNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("budget not between", value1, value2, "budget");
            return (Criteria) this;
        }

        public Criteria andHideIfExistsIsNull() {
            addCriterion("hide_if_exists is null");
            return (Criteria) this;
        }

        public Criteria andHideIfExistsIsNotNull() {
            addCriterion("hide_if_exists is not null");
            return (Criteria) this;
        }

        public Criteria andHideIfExistsEqualTo(Integer value) {
            addCriterion("hide_if_exists =", value, "hideIfExists");
            return (Criteria) this;
        }

        public Criteria andHideIfExistsNotEqualTo(Integer value) {
            addCriterion("hide_if_exists <>", value, "hideIfExists");
            return (Criteria) this;
        }

        public Criteria andHideIfExistsGreaterThan(Integer value) {
            addCriterion("hide_if_exists >", value, "hideIfExists");
            return (Criteria) this;
        }

        public Criteria andHideIfExistsGreaterThanOrEqualTo(Integer value) {
            addCriterion("hide_if_exists >=", value, "hideIfExists");
            return (Criteria) this;
        }

        public Criteria andHideIfExistsLessThan(Integer value) {
            addCriterion("hide_if_exists <", value, "hideIfExists");
            return (Criteria) this;
        }

        public Criteria andHideIfExistsLessThanOrEqualTo(Integer value) {
            addCriterion("hide_if_exists <=", value, "hideIfExists");
            return (Criteria) this;
        }

        public Criteria andHideIfExistsIn(List<Integer> values) {
            addCriterion("hide_if_exists in", values, "hideIfExists");
            return (Criteria) this;
        }

        public Criteria andHideIfExistsNotIn(List<Integer> values) {
            addCriterion("hide_if_exists not in", values, "hideIfExists");
            return (Criteria) this;
        }

        public Criteria andHideIfExistsBetween(Integer value1, Integer value2) {
            addCriterion("hide_if_exists between", value1, value2, "hideIfExists");
            return (Criteria) this;
        }

        public Criteria andHideIfExistsNotBetween(Integer value1, Integer value2) {
            addCriterion("hide_if_exists not between", value1, value2, "hideIfExists");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andOptStatusIsNull() {
            addCriterion("opt_status is null");
            return (Criteria) this;
        }

        public Criteria andOptStatusIsNotNull() {
            addCriterion("opt_status is not null");
            return (Criteria) this;
        }

        public Criteria andOptStatusEqualTo(String value) {
            addCriterion("opt_status =", value, "optStatus");
            return (Criteria) this;
        }

        public Criteria andOptStatusNotEqualTo(String value) {
            addCriterion("opt_status <>", value, "optStatus");
            return (Criteria) this;
        }

        public Criteria andOptStatusGreaterThan(String value) {
            addCriterion("opt_status >", value, "optStatus");
            return (Criteria) this;
        }

        public Criteria andOptStatusGreaterThanOrEqualTo(String value) {
            addCriterion("opt_status >=", value, "optStatus");
            return (Criteria) this;
        }

        public Criteria andOptStatusLessThan(String value) {
            addCriterion("opt_status <", value, "optStatus");
            return (Criteria) this;
        }

        public Criteria andOptStatusLessThanOrEqualTo(String value) {
            addCriterion("opt_status <=", value, "optStatus");
            return (Criteria) this;
        }

        public Criteria andOptStatusLike(String value) {
            addCriterion("opt_status like", value, "optStatus");
            return (Criteria) this;
        }

        public Criteria andOptStatusNotLike(String value) {
            addCriterion("opt_status not like", value, "optStatus");
            return (Criteria) this;
        }

        public Criteria andOptStatusIn(List<String> values) {
            addCriterion("opt_status in", values, "optStatus");
            return (Criteria) this;
        }

        public Criteria andOptStatusNotIn(List<String> values) {
            addCriterion("opt_status not in", values, "optStatus");
            return (Criteria) this;
        }

        public Criteria andOptStatusBetween(String value1, String value2) {
            addCriterion("opt_status between", value1, value2, "optStatus");
            return (Criteria) this;
        }

        public Criteria andOptStatusNotBetween(String value1, String value2) {
            addCriterion("opt_status not between", value1, value2, "optStatus");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(String value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(String value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(String value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(String value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(String value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLike(String value) {
            addCriterion("start_time like", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotLike(String value) {
            addCriterion("start_time not like", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<String> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<String> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(String value1, String value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(String value1, String value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(String value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(String value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(String value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(String value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(String value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLike(String value) {
            addCriterion("end_time like", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotLike(String value) {
            addCriterion("end_time not like", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<String> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<String> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(String value1, String value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(String value1, String value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andBidIsNull() {
            addCriterion("bid is null");
            return (Criteria) this;
        }

        public Criteria andBidIsNotNull() {
            addCriterion("bid is not null");
            return (Criteria) this;
        }

        public Criteria andBidEqualTo(BigDecimal value) {
            addCriterion("bid =", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidNotEqualTo(BigDecimal value) {
            addCriterion("bid <>", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidGreaterThan(BigDecimal value) {
            addCriterion("bid >", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("bid >=", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidLessThan(BigDecimal value) {
            addCriterion("bid <", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidLessThanOrEqualTo(BigDecimal value) {
            addCriterion("bid <=", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidIn(List<BigDecimal> values) {
            addCriterion("bid in", values, "bid");
            return (Criteria) this;
        }

        public Criteria andBidNotIn(List<BigDecimal> values) {
            addCriterion("bid not in", values, "bid");
            return (Criteria) this;
        }

        public Criteria andBidBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bid between", value1, value2, "bid");
            return (Criteria) this;
        }

        public Criteria andBidNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bid not between", value1, value2, "bid");
            return (Criteria) this;
        }

        public Criteria andPricingIsNull() {
            addCriterion("pricing is null");
            return (Criteria) this;
        }

        public Criteria andPricingIsNotNull() {
            addCriterion("pricing is not null");
            return (Criteria) this;
        }

        public Criteria andPricingEqualTo(String value) {
            addCriterion("pricing =", value, "pricing");
            return (Criteria) this;
        }

        public Criteria andPricingNotEqualTo(String value) {
            addCriterion("pricing <>", value, "pricing");
            return (Criteria) this;
        }

        public Criteria andPricingGreaterThan(String value) {
            addCriterion("pricing >", value, "pricing");
            return (Criteria) this;
        }

        public Criteria andPricingGreaterThanOrEqualTo(String value) {
            addCriterion("pricing >=", value, "pricing");
            return (Criteria) this;
        }

        public Criteria andPricingLessThan(String value) {
            addCriterion("pricing <", value, "pricing");
            return (Criteria) this;
        }

        public Criteria andPricingLessThanOrEqualTo(String value) {
            addCriterion("pricing <=", value, "pricing");
            return (Criteria) this;
        }

        public Criteria andPricingLike(String value) {
            addCriterion("pricing like", value, "pricing");
            return (Criteria) this;
        }

        public Criteria andPricingNotLike(String value) {
            addCriterion("pricing not like", value, "pricing");
            return (Criteria) this;
        }

        public Criteria andPricingIn(List<String> values) {
            addCriterion("pricing in", values, "pricing");
            return (Criteria) this;
        }

        public Criteria andPricingNotIn(List<String> values) {
            addCriterion("pricing not in", values, "pricing");
            return (Criteria) this;
        }

        public Criteria andPricingBetween(String value1, String value2) {
            addCriterion("pricing between", value1, value2, "pricing");
            return (Criteria) this;
        }

        public Criteria andPricingNotBetween(String value1, String value2) {
            addCriterion("pricing not between", value1, value2, "pricing");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeIsNull() {
            addCriterion("schedule_type is null");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeIsNotNull() {
            addCriterion("schedule_type is not null");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeEqualTo(String value) {
            addCriterion("schedule_type =", value, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeNotEqualTo(String value) {
            addCriterion("schedule_type <>", value, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeGreaterThan(String value) {
            addCriterion("schedule_type >", value, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeGreaterThanOrEqualTo(String value) {
            addCriterion("schedule_type >=", value, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeLessThan(String value) {
            addCriterion("schedule_type <", value, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeLessThanOrEqualTo(String value) {
            addCriterion("schedule_type <=", value, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeLike(String value) {
            addCriterion("schedule_type like", value, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeNotLike(String value) {
            addCriterion("schedule_type not like", value, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeIn(List<String> values) {
            addCriterion("schedule_type in", values, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeNotIn(List<String> values) {
            addCriterion("schedule_type not in", values, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeBetween(String value1, String value2) {
            addCriterion("schedule_type between", value1, value2, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeNotBetween(String value1, String value2) {
            addCriterion("schedule_type not between", value1, value2, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andFlowControlModeIsNull() {
            addCriterion("flow_control_mode is null");
            return (Criteria) this;
        }

        public Criteria andFlowControlModeIsNotNull() {
            addCriterion("flow_control_mode is not null");
            return (Criteria) this;
        }

        public Criteria andFlowControlModeEqualTo(String value) {
            addCriterion("flow_control_mode =", value, "flowControlMode");
            return (Criteria) this;
        }

        public Criteria andFlowControlModeNotEqualTo(String value) {
            addCriterion("flow_control_mode <>", value, "flowControlMode");
            return (Criteria) this;
        }

        public Criteria andFlowControlModeGreaterThan(String value) {
            addCriterion("flow_control_mode >", value, "flowControlMode");
            return (Criteria) this;
        }

        public Criteria andFlowControlModeGreaterThanOrEqualTo(String value) {
            addCriterion("flow_control_mode >=", value, "flowControlMode");
            return (Criteria) this;
        }

        public Criteria andFlowControlModeLessThan(String value) {
            addCriterion("flow_control_mode <", value, "flowControlMode");
            return (Criteria) this;
        }

        public Criteria andFlowControlModeLessThanOrEqualTo(String value) {
            addCriterion("flow_control_mode <=", value, "flowControlMode");
            return (Criteria) this;
        }

        public Criteria andFlowControlModeLike(String value) {
            addCriterion("flow_control_mode like", value, "flowControlMode");
            return (Criteria) this;
        }

        public Criteria andFlowControlModeNotLike(String value) {
            addCriterion("flow_control_mode not like", value, "flowControlMode");
            return (Criteria) this;
        }

        public Criteria andFlowControlModeIn(List<String> values) {
            addCriterion("flow_control_mode in", values, "flowControlMode");
            return (Criteria) this;
        }

        public Criteria andFlowControlModeNotIn(List<String> values) {
            addCriterion("flow_control_mode not in", values, "flowControlMode");
            return (Criteria) this;
        }

        public Criteria andFlowControlModeBetween(String value1, String value2) {
            addCriterion("flow_control_mode between", value1, value2, "flowControlMode");
            return (Criteria) this;
        }

        public Criteria andFlowControlModeNotBetween(String value1, String value2) {
            addCriterion("flow_control_mode not between", value1, value2, "flowControlMode");
            return (Criteria) this;
        }

        public Criteria andOpenUrlIsNull() {
            addCriterion("open_url is null");
            return (Criteria) this;
        }

        public Criteria andOpenUrlIsNotNull() {
            addCriterion("open_url is not null");
            return (Criteria) this;
        }

        public Criteria andOpenUrlEqualTo(String value) {
            addCriterion("open_url =", value, "openUrl");
            return (Criteria) this;
        }

        public Criteria andOpenUrlNotEqualTo(String value) {
            addCriterion("open_url <>", value, "openUrl");
            return (Criteria) this;
        }

        public Criteria andOpenUrlGreaterThan(String value) {
            addCriterion("open_url >", value, "openUrl");
            return (Criteria) this;
        }

        public Criteria andOpenUrlGreaterThanOrEqualTo(String value) {
            addCriterion("open_url >=", value, "openUrl");
            return (Criteria) this;
        }

        public Criteria andOpenUrlLessThan(String value) {
            addCriterion("open_url <", value, "openUrl");
            return (Criteria) this;
        }

        public Criteria andOpenUrlLessThanOrEqualTo(String value) {
            addCriterion("open_url <=", value, "openUrl");
            return (Criteria) this;
        }

        public Criteria andOpenUrlLike(String value) {
            addCriterion("open_url like", value, "openUrl");
            return (Criteria) this;
        }

        public Criteria andOpenUrlNotLike(String value) {
            addCriterion("open_url not like", value, "openUrl");
            return (Criteria) this;
        }

        public Criteria andOpenUrlIn(List<String> values) {
            addCriterion("open_url in", values, "openUrl");
            return (Criteria) this;
        }

        public Criteria andOpenUrlNotIn(List<String> values) {
            addCriterion("open_url not in", values, "openUrl");
            return (Criteria) this;
        }

        public Criteria andOpenUrlBetween(String value1, String value2) {
            addCriterion("open_url between", value1, value2, "openUrl");
            return (Criteria) this;
        }

        public Criteria andOpenUrlNotBetween(String value1, String value2) {
            addCriterion("open_url not between", value1, value2, "openUrl");
            return (Criteria) this;
        }

        public Criteria andDownloadTypeIsNull() {
            addCriterion("download_type is null");
            return (Criteria) this;
        }

        public Criteria andDownloadTypeIsNotNull() {
            addCriterion("download_type is not null");
            return (Criteria) this;
        }

        public Criteria andDownloadTypeEqualTo(String value) {
            addCriterion("download_type =", value, "downloadType");
            return (Criteria) this;
        }

        public Criteria andDownloadTypeNotEqualTo(String value) {
            addCriterion("download_type <>", value, "downloadType");
            return (Criteria) this;
        }

        public Criteria andDownloadTypeGreaterThan(String value) {
            addCriterion("download_type >", value, "downloadType");
            return (Criteria) this;
        }

        public Criteria andDownloadTypeGreaterThanOrEqualTo(String value) {
            addCriterion("download_type >=", value, "downloadType");
            return (Criteria) this;
        }

        public Criteria andDownloadTypeLessThan(String value) {
            addCriterion("download_type <", value, "downloadType");
            return (Criteria) this;
        }

        public Criteria andDownloadTypeLessThanOrEqualTo(String value) {
            addCriterion("download_type <=", value, "downloadType");
            return (Criteria) this;
        }

        public Criteria andDownloadTypeLike(String value) {
            addCriterion("download_type like", value, "downloadType");
            return (Criteria) this;
        }

        public Criteria andDownloadTypeNotLike(String value) {
            addCriterion("download_type not like", value, "downloadType");
            return (Criteria) this;
        }

        public Criteria andDownloadTypeIn(List<String> values) {
            addCriterion("download_type in", values, "downloadType");
            return (Criteria) this;
        }

        public Criteria andDownloadTypeNotIn(List<String> values) {
            addCriterion("download_type not in", values, "downloadType");
            return (Criteria) this;
        }

        public Criteria andDownloadTypeBetween(String value1, String value2) {
            addCriterion("download_type between", value1, value2, "downloadType");
            return (Criteria) this;
        }

        public Criteria andDownloadTypeNotBetween(String value1, String value2) {
            addCriterion("download_type not between", value1, value2, "downloadType");
            return (Criteria) this;
        }

        public Criteria andExternalUrlIsNull() {
            addCriterion("external_url is null");
            return (Criteria) this;
        }

        public Criteria andExternalUrlIsNotNull() {
            addCriterion("external_url is not null");
            return (Criteria) this;
        }

        public Criteria andExternalUrlEqualTo(String value) {
            addCriterion("external_url =", value, "externalUrl");
            return (Criteria) this;
        }

        public Criteria andExternalUrlNotEqualTo(String value) {
            addCriterion("external_url <>", value, "externalUrl");
            return (Criteria) this;
        }

        public Criteria andExternalUrlGreaterThan(String value) {
            addCriterion("external_url >", value, "externalUrl");
            return (Criteria) this;
        }

        public Criteria andExternalUrlGreaterThanOrEqualTo(String value) {
            addCriterion("external_url >=", value, "externalUrl");
            return (Criteria) this;
        }

        public Criteria andExternalUrlLessThan(String value) {
            addCriterion("external_url <", value, "externalUrl");
            return (Criteria) this;
        }

        public Criteria andExternalUrlLessThanOrEqualTo(String value) {
            addCriterion("external_url <=", value, "externalUrl");
            return (Criteria) this;
        }

        public Criteria andExternalUrlLike(String value) {
            addCriterion("external_url like", value, "externalUrl");
            return (Criteria) this;
        }

        public Criteria andExternalUrlNotLike(String value) {
            addCriterion("external_url not like", value, "externalUrl");
            return (Criteria) this;
        }

        public Criteria andExternalUrlIn(List<String> values) {
            addCriterion("external_url in", values, "externalUrl");
            return (Criteria) this;
        }

        public Criteria andExternalUrlNotIn(List<String> values) {
            addCriterion("external_url not in", values, "externalUrl");
            return (Criteria) this;
        }

        public Criteria andExternalUrlBetween(String value1, String value2) {
            addCriterion("external_url between", value1, value2, "externalUrl");
            return (Criteria) this;
        }

        public Criteria andExternalUrlNotBetween(String value1, String value2) {
            addCriterion("external_url not between", value1, value2, "externalUrl");
            return (Criteria) this;
        }

        public Criteria andDownloadUrlIsNull() {
            addCriterion("download_url is null");
            return (Criteria) this;
        }

        public Criteria andDownloadUrlIsNotNull() {
            addCriterion("download_url is not null");
            return (Criteria) this;
        }

        public Criteria andDownloadUrlEqualTo(String value) {
            addCriterion("download_url =", value, "downloadUrl");
            return (Criteria) this;
        }

        public Criteria andDownloadUrlNotEqualTo(String value) {
            addCriterion("download_url <>", value, "downloadUrl");
            return (Criteria) this;
        }

        public Criteria andDownloadUrlGreaterThan(String value) {
            addCriterion("download_url >", value, "downloadUrl");
            return (Criteria) this;
        }

        public Criteria andDownloadUrlGreaterThanOrEqualTo(String value) {
            addCriterion("download_url >=", value, "downloadUrl");
            return (Criteria) this;
        }

        public Criteria andDownloadUrlLessThan(String value) {
            addCriterion("download_url <", value, "downloadUrl");
            return (Criteria) this;
        }

        public Criteria andDownloadUrlLessThanOrEqualTo(String value) {
            addCriterion("download_url <=", value, "downloadUrl");
            return (Criteria) this;
        }

        public Criteria andDownloadUrlLike(String value) {
            addCriterion("download_url like", value, "downloadUrl");
            return (Criteria) this;
        }

        public Criteria andDownloadUrlNotLike(String value) {
            addCriterion("download_url not like", value, "downloadUrl");
            return (Criteria) this;
        }

        public Criteria andDownloadUrlIn(List<String> values) {
            addCriterion("download_url in", values, "downloadUrl");
            return (Criteria) this;
        }

        public Criteria andDownloadUrlNotIn(List<String> values) {
            addCriterion("download_url not in", values, "downloadUrl");
            return (Criteria) this;
        }

        public Criteria andDownloadUrlBetween(String value1, String value2) {
            addCriterion("download_url between", value1, value2, "downloadUrl");
            return (Criteria) this;
        }

        public Criteria andDownloadUrlNotBetween(String value1, String value2) {
            addCriterion("download_url not between", value1, value2, "downloadUrl");
            return (Criteria) this;
        }

        public Criteria andAppTypeIsNull() {
            addCriterion("app_type is null");
            return (Criteria) this;
        }

        public Criteria andAppTypeIsNotNull() {
            addCriterion("app_type is not null");
            return (Criteria) this;
        }

        public Criteria andAppTypeEqualTo(String value) {
            addCriterion("app_type =", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeNotEqualTo(String value) {
            addCriterion("app_type <>", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeGreaterThan(String value) {
            addCriterion("app_type >", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeGreaterThanOrEqualTo(String value) {
            addCriterion("app_type >=", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeLessThan(String value) {
            addCriterion("app_type <", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeLessThanOrEqualTo(String value) {
            addCriterion("app_type <=", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeLike(String value) {
            addCriterion("app_type like", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeNotLike(String value) {
            addCriterion("app_type not like", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeIn(List<String> values) {
            addCriterion("app_type in", values, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeNotIn(List<String> values) {
            addCriterion("app_type not in", values, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeBetween(String value1, String value2) {
            addCriterion("app_type between", value1, value2, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeNotBetween(String value1, String value2) {
            addCriterion("app_type not between", value1, value2, "appType");
            return (Criteria) this;
        }

        public Criteria andAdPackageIsNull() {
            addCriterion("ad_package is null");
            return (Criteria) this;
        }

        public Criteria andAdPackageIsNotNull() {
            addCriterion("ad_package is not null");
            return (Criteria) this;
        }

        public Criteria andAdPackageEqualTo(String value) {
            addCriterion("ad_package =", value, "adPackage");
            return (Criteria) this;
        }

        public Criteria andAdPackageNotEqualTo(String value) {
            addCriterion("ad_package <>", value, "adPackage");
            return (Criteria) this;
        }

        public Criteria andAdPackageGreaterThan(String value) {
            addCriterion("ad_package >", value, "adPackage");
            return (Criteria) this;
        }

        public Criteria andAdPackageGreaterThanOrEqualTo(String value) {
            addCriterion("ad_package >=", value, "adPackage");
            return (Criteria) this;
        }

        public Criteria andAdPackageLessThan(String value) {
            addCriterion("ad_package <", value, "adPackage");
            return (Criteria) this;
        }

        public Criteria andAdPackageLessThanOrEqualTo(String value) {
            addCriterion("ad_package <=", value, "adPackage");
            return (Criteria) this;
        }

        public Criteria andAdPackageLike(String value) {
            addCriterion("ad_package like", value, "adPackage");
            return (Criteria) this;
        }

        public Criteria andAdPackageNotLike(String value) {
            addCriterion("ad_package not like", value, "adPackage");
            return (Criteria) this;
        }

        public Criteria andAdPackageIn(List<String> values) {
            addCriterion("ad_package in", values, "adPackage");
            return (Criteria) this;
        }

        public Criteria andAdPackageNotIn(List<String> values) {
            addCriterion("ad_package not in", values, "adPackage");
            return (Criteria) this;
        }

        public Criteria andAdPackageBetween(String value1, String value2) {
            addCriterion("ad_package between", value1, value2, "adPackage");
            return (Criteria) this;
        }

        public Criteria andAdPackageNotBetween(String value1, String value2) {
            addCriterion("ad_package not between", value1, value2, "adPackage");
            return (Criteria) this;
        }

        public Criteria andAuditRejectReasonIsNull() {
            addCriterion("audit_reject_reason is null");
            return (Criteria) this;
        }

        public Criteria andAuditRejectReasonIsNotNull() {
            addCriterion("audit_reject_reason is not null");
            return (Criteria) this;
        }

        public Criteria andAuditRejectReasonEqualTo(String value) {
            addCriterion("audit_reject_reason =", value, "auditRejectReason");
            return (Criteria) this;
        }

        public Criteria andAuditRejectReasonNotEqualTo(String value) {
            addCriterion("audit_reject_reason <>", value, "auditRejectReason");
            return (Criteria) this;
        }

        public Criteria andAuditRejectReasonGreaterThan(String value) {
            addCriterion("audit_reject_reason >", value, "auditRejectReason");
            return (Criteria) this;
        }

        public Criteria andAuditRejectReasonGreaterThanOrEqualTo(String value) {
            addCriterion("audit_reject_reason >=", value, "auditRejectReason");
            return (Criteria) this;
        }

        public Criteria andAuditRejectReasonLessThan(String value) {
            addCriterion("audit_reject_reason <", value, "auditRejectReason");
            return (Criteria) this;
        }

        public Criteria andAuditRejectReasonLessThanOrEqualTo(String value) {
            addCriterion("audit_reject_reason <=", value, "auditRejectReason");
            return (Criteria) this;
        }

        public Criteria andAuditRejectReasonLike(String value) {
            addCriterion("audit_reject_reason like", value, "auditRejectReason");
            return (Criteria) this;
        }

        public Criteria andAuditRejectReasonNotLike(String value) {
            addCriterion("audit_reject_reason not like", value, "auditRejectReason");
            return (Criteria) this;
        }

        public Criteria andAuditRejectReasonIn(List<String> values) {
            addCriterion("audit_reject_reason in", values, "auditRejectReason");
            return (Criteria) this;
        }

        public Criteria andAuditRejectReasonNotIn(List<String> values) {
            addCriterion("audit_reject_reason not in", values, "auditRejectReason");
            return (Criteria) this;
        }

        public Criteria andAuditRejectReasonBetween(String value1, String value2) {
            addCriterion("audit_reject_reason between", value1, value2, "auditRejectReason");
            return (Criteria) this;
        }

        public Criteria andAuditRejectReasonNotBetween(String value1, String value2) {
            addCriterion("audit_reject_reason not between", value1, value2, "auditRejectReason");
            return (Criteria) this;
        }

        public Criteria andCpaBidIsNull() {
            addCriterion("cpa_bid is null");
            return (Criteria) this;
        }

        public Criteria andCpaBidIsNotNull() {
            addCriterion("cpa_bid is not null");
            return (Criteria) this;
        }

        public Criteria andCpaBidEqualTo(BigDecimal value) {
            addCriterion("cpa_bid =", value, "cpaBid");
            return (Criteria) this;
        }

        public Criteria andCpaBidNotEqualTo(BigDecimal value) {
            addCriterion("cpa_bid <>", value, "cpaBid");
            return (Criteria) this;
        }

        public Criteria andCpaBidGreaterThan(BigDecimal value) {
            addCriterion("cpa_bid >", value, "cpaBid");
            return (Criteria) this;
        }

        public Criteria andCpaBidGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cpa_bid >=", value, "cpaBid");
            return (Criteria) this;
        }

        public Criteria andCpaBidLessThan(BigDecimal value) {
            addCriterion("cpa_bid <", value, "cpaBid");
            return (Criteria) this;
        }

        public Criteria andCpaBidLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cpa_bid <=", value, "cpaBid");
            return (Criteria) this;
        }

        public Criteria andCpaBidIn(List<BigDecimal> values) {
            addCriterion("cpa_bid in", values, "cpaBid");
            return (Criteria) this;
        }

        public Criteria andCpaBidNotIn(List<BigDecimal> values) {
            addCriterion("cpa_bid not in", values, "cpaBid");
            return (Criteria) this;
        }

        public Criteria andCpaBidBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cpa_bid between", value1, value2, "cpaBid");
            return (Criteria) this;
        }

        public Criteria andCpaBidNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cpa_bid not between", value1, value2, "cpaBid");
            return (Criteria) this;
        }

        public Criteria andCpaSkipFirstPhraseIsNull() {
            addCriterion("cpa_skip_first_phrase is null");
            return (Criteria) this;
        }

        public Criteria andCpaSkipFirstPhraseIsNotNull() {
            addCriterion("cpa_skip_first_phrase is not null");
            return (Criteria) this;
        }

        public Criteria andCpaSkipFirstPhraseEqualTo(Integer value) {
            addCriterion("cpa_skip_first_phrase =", value, "cpaSkipFirstPhrase");
            return (Criteria) this;
        }

        public Criteria andCpaSkipFirstPhraseNotEqualTo(Integer value) {
            addCriterion("cpa_skip_first_phrase <>", value, "cpaSkipFirstPhrase");
            return (Criteria) this;
        }

        public Criteria andCpaSkipFirstPhraseGreaterThan(Integer value) {
            addCriterion("cpa_skip_first_phrase >", value, "cpaSkipFirstPhrase");
            return (Criteria) this;
        }

        public Criteria andCpaSkipFirstPhraseGreaterThanOrEqualTo(Integer value) {
            addCriterion("cpa_skip_first_phrase >=", value, "cpaSkipFirstPhrase");
            return (Criteria) this;
        }

        public Criteria andCpaSkipFirstPhraseLessThan(Integer value) {
            addCriterion("cpa_skip_first_phrase <", value, "cpaSkipFirstPhrase");
            return (Criteria) this;
        }

        public Criteria andCpaSkipFirstPhraseLessThanOrEqualTo(Integer value) {
            addCriterion("cpa_skip_first_phrase <=", value, "cpaSkipFirstPhrase");
            return (Criteria) this;
        }

        public Criteria andCpaSkipFirstPhraseIn(List<Integer> values) {
            addCriterion("cpa_skip_first_phrase in", values, "cpaSkipFirstPhrase");
            return (Criteria) this;
        }

        public Criteria andCpaSkipFirstPhraseNotIn(List<Integer> values) {
            addCriterion("cpa_skip_first_phrase not in", values, "cpaSkipFirstPhrase");
            return (Criteria) this;
        }

        public Criteria andCpaSkipFirstPhraseBetween(Integer value1, Integer value2) {
            addCriterion("cpa_skip_first_phrase between", value1, value2, "cpaSkipFirstPhrase");
            return (Criteria) this;
        }

        public Criteria andCpaSkipFirstPhraseNotBetween(Integer value1, Integer value2) {
            addCriterion("cpa_skip_first_phrase not between", value1, value2, "cpaSkipFirstPhrase");
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

        public Criteria andHideIfConvertedIsNull() {
            addCriterion("hide_if_converted is null");
            return (Criteria) this;
        }

        public Criteria andHideIfConvertedIsNotNull() {
            addCriterion("hide_if_converted is not null");
            return (Criteria) this;
        }

        public Criteria andHideIfConvertedEqualTo(String value) {
            addCriterion("hide_if_converted =", value, "hideIfConverted");
            return (Criteria) this;
        }

        public Criteria andHideIfConvertedNotEqualTo(String value) {
            addCriterion("hide_if_converted <>", value, "hideIfConverted");
            return (Criteria) this;
        }

        public Criteria andHideIfConvertedGreaterThan(String value) {
            addCriterion("hide_if_converted >", value, "hideIfConverted");
            return (Criteria) this;
        }

        public Criteria andHideIfConvertedGreaterThanOrEqualTo(String value) {
            addCriterion("hide_if_converted >=", value, "hideIfConverted");
            return (Criteria) this;
        }

        public Criteria andHideIfConvertedLessThan(String value) {
            addCriterion("hide_if_converted <", value, "hideIfConverted");
            return (Criteria) this;
        }

        public Criteria andHideIfConvertedLessThanOrEqualTo(String value) {
            addCriterion("hide_if_converted <=", value, "hideIfConverted");
            return (Criteria) this;
        }

        public Criteria andHideIfConvertedLike(String value) {
            addCriterion("hide_if_converted like", value, "hideIfConverted");
            return (Criteria) this;
        }

        public Criteria andHideIfConvertedNotLike(String value) {
            addCriterion("hide_if_converted not like", value, "hideIfConverted");
            return (Criteria) this;
        }

        public Criteria andHideIfConvertedIn(List<String> values) {
            addCriterion("hide_if_converted in", values, "hideIfConverted");
            return (Criteria) this;
        }

        public Criteria andHideIfConvertedNotIn(List<String> values) {
            addCriterion("hide_if_converted not in", values, "hideIfConverted");
            return (Criteria) this;
        }

        public Criteria andHideIfConvertedBetween(String value1, String value2) {
            addCriterion("hide_if_converted between", value1, value2, "hideIfConverted");
            return (Criteria) this;
        }

        public Criteria andHideIfConvertedNotBetween(String value1, String value2) {
            addCriterion("hide_if_converted not between", value1, value2, "hideIfConverted");
            return (Criteria) this;
        }

        public Criteria andPageInfoIsNull() {
            addCriterion("page_info is null");
            return (Criteria) this;
        }

        public Criteria andPageInfoIsNotNull() {
            addCriterion("page_info is not null");
            return (Criteria) this;
        }

        public Criteria andPageInfoEqualTo(String value) {
            addCriterion("page_info =", value, "pageInfo");
            return (Criteria) this;
        }

        public Criteria andPageInfoNotEqualTo(String value) {
            addCriterion("page_info <>", value, "pageInfo");
            return (Criteria) this;
        }

        public Criteria andPageInfoGreaterThan(String value) {
            addCriterion("page_info >", value, "pageInfo");
            return (Criteria) this;
        }

        public Criteria andPageInfoGreaterThanOrEqualTo(String value) {
            addCriterion("page_info >=", value, "pageInfo");
            return (Criteria) this;
        }

        public Criteria andPageInfoLessThan(String value) {
            addCriterion("page_info <", value, "pageInfo");
            return (Criteria) this;
        }

        public Criteria andPageInfoLessThanOrEqualTo(String value) {
            addCriterion("page_info <=", value, "pageInfo");
            return (Criteria) this;
        }

        public Criteria andPageInfoLike(String value) {
            addCriterion("page_info like", value, "pageInfo");
            return (Criteria) this;
        }

        public Criteria andPageInfoNotLike(String value) {
            addCriterion("page_info not like", value, "pageInfo");
            return (Criteria) this;
        }

        public Criteria andPageInfoIn(List<String> values) {
            addCriterion("page_info in", values, "pageInfo");
            return (Criteria) this;
        }

        public Criteria andPageInfoNotIn(List<String> values) {
            addCriterion("page_info not in", values, "pageInfo");
            return (Criteria) this;
        }

        public Criteria andPageInfoBetween(String value1, String value2) {
            addCriterion("page_info between", value1, value2, "pageInfo");
            return (Criteria) this;
        }

        public Criteria andPageInfoNotBetween(String value1, String value2) {
            addCriterion("page_info not between", value1, value2, "pageInfo");
            return (Criteria) this;
        }

        public Criteria andBatchCodeIsNull() {
            addCriterion("batch_code is null");
            return (Criteria) this;
        }

        public Criteria andBatchCodeIsNotNull() {
            addCriterion("batch_code is not null");
            return (Criteria) this;
        }

        public Criteria andBatchCodeEqualTo(Integer value) {
            addCriterion("batch_code =", value, "batchCode");
            return (Criteria) this;
        }

        public Criteria andBatchCodeNotEqualTo(Integer value) {
            addCriterion("batch_code <>", value, "batchCode");
            return (Criteria) this;
        }

        public Criteria andBatchCodeGreaterThan(Integer value) {
            addCriterion("batch_code >", value, "batchCode");
            return (Criteria) this;
        }

        public Criteria andBatchCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("batch_code >=", value, "batchCode");
            return (Criteria) this;
        }

        public Criteria andBatchCodeLessThan(Integer value) {
            addCriterion("batch_code <", value, "batchCode");
            return (Criteria) this;
        }

        public Criteria andBatchCodeLessThanOrEqualTo(Integer value) {
            addCriterion("batch_code <=", value, "batchCode");
            return (Criteria) this;
        }

        public Criteria andBatchCodeIn(List<Integer> values) {
            addCriterion("batch_code in", values, "batchCode");
            return (Criteria) this;
        }

        public Criteria andBatchCodeNotIn(List<Integer> values) {
            addCriterion("batch_code not in", values, "batchCode");
            return (Criteria) this;
        }

        public Criteria andBatchCodeBetween(Integer value1, Integer value2) {
            addCriterion("batch_code between", value1, value2, "batchCode");
            return (Criteria) this;
        }

        public Criteria andBatchCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("batch_code not between", value1, value2, "batchCode");
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

        public Criteria andDeliveryRangeIsNull() {
            addCriterion("delivery_range is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryRangeIsNotNull() {
            addCriterion("delivery_range is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryRangeEqualTo(String value) {
            addCriterion("delivery_range =", value, "deliveryRange");
            return (Criteria) this;
        }

        public Criteria andDeliveryRangeNotEqualTo(String value) {
            addCriterion("delivery_range <>", value, "deliveryRange");
            return (Criteria) this;
        }

        public Criteria andDeliveryRangeGreaterThan(String value) {
            addCriterion("delivery_range >", value, "deliveryRange");
            return (Criteria) this;
        }

        public Criteria andDeliveryRangeGreaterThanOrEqualTo(String value) {
            addCriterion("delivery_range >=", value, "deliveryRange");
            return (Criteria) this;
        }

        public Criteria andDeliveryRangeLessThan(String value) {
            addCriterion("delivery_range <", value, "deliveryRange");
            return (Criteria) this;
        }

        public Criteria andDeliveryRangeLessThanOrEqualTo(String value) {
            addCriterion("delivery_range <=", value, "deliveryRange");
            return (Criteria) this;
        }

        public Criteria andDeliveryRangeLike(String value) {
            addCriterion("delivery_range like", value, "deliveryRange");
            return (Criteria) this;
        }

        public Criteria andDeliveryRangeNotLike(String value) {
            addCriterion("delivery_range not like", value, "deliveryRange");
            return (Criteria) this;
        }

        public Criteria andDeliveryRangeIn(List<String> values) {
            addCriterion("delivery_range in", values, "deliveryRange");
            return (Criteria) this;
        }

        public Criteria andDeliveryRangeNotIn(List<String> values) {
            addCriterion("delivery_range not in", values, "deliveryRange");
            return (Criteria) this;
        }

        public Criteria andDeliveryRangeBetween(String value1, String value2) {
            addCriterion("delivery_range between", value1, value2, "deliveryRange");
            return (Criteria) this;
        }

        public Criteria andDeliveryRangeNotBetween(String value1, String value2) {
            addCriterion("delivery_range not between", value1, value2, "deliveryRange");
            return (Criteria) this;
        }

        public Criteria andUnionVideoTypeIsNull() {
            addCriterion("union_video_type is null");
            return (Criteria) this;
        }

        public Criteria andUnionVideoTypeIsNotNull() {
            addCriterion("union_video_type is not null");
            return (Criteria) this;
        }

        public Criteria andUnionVideoTypeEqualTo(String value) {
            addCriterion("union_video_type =", value, "unionVideoType");
            return (Criteria) this;
        }

        public Criteria andUnionVideoTypeNotEqualTo(String value) {
            addCriterion("union_video_type <>", value, "unionVideoType");
            return (Criteria) this;
        }

        public Criteria andUnionVideoTypeGreaterThan(String value) {
            addCriterion("union_video_type >", value, "unionVideoType");
            return (Criteria) this;
        }

        public Criteria andUnionVideoTypeGreaterThanOrEqualTo(String value) {
            addCriterion("union_video_type >=", value, "unionVideoType");
            return (Criteria) this;
        }

        public Criteria andUnionVideoTypeLessThan(String value) {
            addCriterion("union_video_type <", value, "unionVideoType");
            return (Criteria) this;
        }

        public Criteria andUnionVideoTypeLessThanOrEqualTo(String value) {
            addCriterion("union_video_type <=", value, "unionVideoType");
            return (Criteria) this;
        }

        public Criteria andUnionVideoTypeLike(String value) {
            addCriterion("union_video_type like", value, "unionVideoType");
            return (Criteria) this;
        }

        public Criteria andUnionVideoTypeNotLike(String value) {
            addCriterion("union_video_type not like", value, "unionVideoType");
            return (Criteria) this;
        }

        public Criteria andUnionVideoTypeIn(List<String> values) {
            addCriterion("union_video_type in", values, "unionVideoType");
            return (Criteria) this;
        }

        public Criteria andUnionVideoTypeNotIn(List<String> values) {
            addCriterion("union_video_type not in", values, "unionVideoType");
            return (Criteria) this;
        }

        public Criteria andUnionVideoTypeBetween(String value1, String value2) {
            addCriterion("union_video_type between", value1, value2, "unionVideoType");
            return (Criteria) this;
        }

        public Criteria andUnionVideoTypeNotBetween(String value1, String value2) {
            addCriterion("union_video_type not between", value1, value2, "unionVideoType");
            return (Criteria) this;
        }

        public Criteria andDeepBidTypeIsNull() {
            addCriterion("deep_bid_type is null");
            return (Criteria) this;
        }

        public Criteria andDeepBidTypeIsNotNull() {
            addCriterion("deep_bid_type is not null");
            return (Criteria) this;
        }

        public Criteria andDeepBidTypeEqualTo(String value) {
            addCriterion("deep_bid_type =", value, "deepBidType");
            return (Criteria) this;
        }

        public Criteria andDeepBidTypeNotEqualTo(String value) {
            addCriterion("deep_bid_type <>", value, "deepBidType");
            return (Criteria) this;
        }

        public Criteria andDeepBidTypeGreaterThan(String value) {
            addCriterion("deep_bid_type >", value, "deepBidType");
            return (Criteria) this;
        }

        public Criteria andDeepBidTypeGreaterThanOrEqualTo(String value) {
            addCriterion("deep_bid_type >=", value, "deepBidType");
            return (Criteria) this;
        }

        public Criteria andDeepBidTypeLessThan(String value) {
            addCriterion("deep_bid_type <", value, "deepBidType");
            return (Criteria) this;
        }

        public Criteria andDeepBidTypeLessThanOrEqualTo(String value) {
            addCriterion("deep_bid_type <=", value, "deepBidType");
            return (Criteria) this;
        }

        public Criteria andDeepBidTypeLike(String value) {
            addCriterion("deep_bid_type like", value, "deepBidType");
            return (Criteria) this;
        }

        public Criteria andDeepBidTypeNotLike(String value) {
            addCriterion("deep_bid_type not like", value, "deepBidType");
            return (Criteria) this;
        }

        public Criteria andDeepBidTypeIn(List<String> values) {
            addCriterion("deep_bid_type in", values, "deepBidType");
            return (Criteria) this;
        }

        public Criteria andDeepBidTypeNotIn(List<String> values) {
            addCriterion("deep_bid_type not in", values, "deepBidType");
            return (Criteria) this;
        }

        public Criteria andDeepBidTypeBetween(String value1, String value2) {
            addCriterion("deep_bid_type between", value1, value2, "deepBidType");
            return (Criteria) this;
        }

        public Criteria andDeepBidTypeNotBetween(String value1, String value2) {
            addCriterion("deep_bid_type not between", value1, value2, "deepBidType");
            return (Criteria) this;
        }

        public Criteria andDeepCpabidIsNull() {
            addCriterion("deep_cpabid is null");
            return (Criteria) this;
        }

        public Criteria andDeepCpabidIsNotNull() {
            addCriterion("deep_cpabid is not null");
            return (Criteria) this;
        }

        public Criteria andDeepCpabidEqualTo(BigDecimal value) {
            addCriterion("deep_cpabid =", value, "deepCpabid");
            return (Criteria) this;
        }

        public Criteria andDeepCpabidNotEqualTo(BigDecimal value) {
            addCriterion("deep_cpabid <>", value, "deepCpabid");
            return (Criteria) this;
        }

        public Criteria andDeepCpabidGreaterThan(BigDecimal value) {
            addCriterion("deep_cpabid >", value, "deepCpabid");
            return (Criteria) this;
        }

        public Criteria andDeepCpabidGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deep_cpabid >=", value, "deepCpabid");
            return (Criteria) this;
        }

        public Criteria andDeepCpabidLessThan(BigDecimal value) {
            addCriterion("deep_cpabid <", value, "deepCpabid");
            return (Criteria) this;
        }

        public Criteria andDeepCpabidLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deep_cpabid <=", value, "deepCpabid");
            return (Criteria) this;
        }

        public Criteria andDeepCpabidIn(List<BigDecimal> values) {
            addCriterion("deep_cpabid in", values, "deepCpabid");
            return (Criteria) this;
        }

        public Criteria andDeepCpabidNotIn(List<BigDecimal> values) {
            addCriterion("deep_cpabid not in", values, "deepCpabid");
            return (Criteria) this;
        }

        public Criteria andDeepCpabidBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deep_cpabid between", value1, value2, "deepCpabid");
            return (Criteria) this;
        }

        public Criteria andDeepCpabidNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deep_cpabid not between", value1, value2, "deepCpabid");
            return (Criteria) this;
        }

        public Criteria andUniqueFkIsNull() {
            addCriterion("unique_fk is null");
            return (Criteria) this;
        }

        public Criteria andUniqueFkIsNotNull() {
            addCriterion("unique_fk is not null");
            return (Criteria) this;
        }

        public Criteria andUniqueFkEqualTo(String value) {
            addCriterion("unique_fk =", value, "uniqueFk");
            return (Criteria) this;
        }

        public Criteria andUniqueFkNotEqualTo(String value) {
            addCriterion("unique_fk <>", value, "uniqueFk");
            return (Criteria) this;
        }

        public Criteria andUniqueFkGreaterThan(String value) {
            addCriterion("unique_fk >", value, "uniqueFk");
            return (Criteria) this;
        }

        public Criteria andUniqueFkGreaterThanOrEqualTo(String value) {
            addCriterion("unique_fk >=", value, "uniqueFk");
            return (Criteria) this;
        }

        public Criteria andUniqueFkLessThan(String value) {
            addCriterion("unique_fk <", value, "uniqueFk");
            return (Criteria) this;
        }

        public Criteria andUniqueFkLessThanOrEqualTo(String value) {
            addCriterion("unique_fk <=", value, "uniqueFk");
            return (Criteria) this;
        }

        public Criteria andUniqueFkLike(String value) {
            addCriterion("unique_fk like", value, "uniqueFk");
            return (Criteria) this;
        }

        public Criteria andUniqueFkNotLike(String value) {
            addCriterion("unique_fk not like", value, "uniqueFk");
            return (Criteria) this;
        }

        public Criteria andUniqueFkIn(List<String> values) {
            addCriterion("unique_fk in", values, "uniqueFk");
            return (Criteria) this;
        }

        public Criteria andUniqueFkNotIn(List<String> values) {
            addCriterion("unique_fk not in", values, "uniqueFk");
            return (Criteria) this;
        }

        public Criteria andUniqueFkBetween(String value1, String value2) {
            addCriterion("unique_fk between", value1, value2, "uniqueFk");
            return (Criteria) this;
        }

        public Criteria andUniqueFkNotBetween(String value1, String value2) {
            addCriterion("unique_fk not between", value1, value2, "uniqueFk");
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