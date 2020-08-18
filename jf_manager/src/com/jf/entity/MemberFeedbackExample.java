package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberFeedbackExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public MemberFeedbackExample() {
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

        public Criteria andMemberIdIsNull() {
            addCriterion("member_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("member_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(Integer value) {
            addCriterion("member_id =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(Integer value) {
            addCriterion("member_id <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(Integer value) {
            addCriterion("member_id >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("member_id >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(Integer value) {
            addCriterion("member_id <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(Integer value) {
            addCriterion("member_id <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<Integer> values) {
            addCriterion("member_id in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<Integer> values) {
            addCriterion("member_id not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(Integer value1, Integer value2) {
            addCriterion("member_id between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(Integer value1, Integer value2) {
            addCriterion("member_id not between", value1, value2, "memberId");
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

        public Criteria andPhoneModelIsNull() {
            addCriterion("phone_model is null");
            return (Criteria) this;
        }

        public Criteria andPhoneModelIsNotNull() {
            addCriterion("phone_model is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneModelEqualTo(String value) {
            addCriterion("phone_model =", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelNotEqualTo(String value) {
            addCriterion("phone_model <>", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelGreaterThan(String value) {
            addCriterion("phone_model >", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelGreaterThanOrEqualTo(String value) {
            addCriterion("phone_model >=", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelLessThan(String value) {
            addCriterion("phone_model <", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelLessThanOrEqualTo(String value) {
            addCriterion("phone_model <=", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelLike(String value) {
            addCriterion("phone_model like", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelNotLike(String value) {
            addCriterion("phone_model not like", value, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelIn(List<String> values) {
            addCriterion("phone_model in", values, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelNotIn(List<String> values) {
            addCriterion("phone_model not in", values, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelBetween(String value1, String value2) {
            addCriterion("phone_model between", value1, value2, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneModelNotBetween(String value1, String value2) {
            addCriterion("phone_model not between", value1, value2, "phoneModel");
            return (Criteria) this;
        }

        public Criteria andPhoneSystemIsNull() {
            addCriterion("phone_system is null");
            return (Criteria) this;
        }

        public Criteria andPhoneSystemIsNotNull() {
            addCriterion("phone_system is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneSystemEqualTo(String value) {
            addCriterion("phone_system =", value, "phoneSystem");
            return (Criteria) this;
        }

        public Criteria andPhoneSystemNotEqualTo(String value) {
            addCriterion("phone_system <>", value, "phoneSystem");
            return (Criteria) this;
        }

        public Criteria andPhoneSystemGreaterThan(String value) {
            addCriterion("phone_system >", value, "phoneSystem");
            return (Criteria) this;
        }

        public Criteria andPhoneSystemGreaterThanOrEqualTo(String value) {
            addCriterion("phone_system >=", value, "phoneSystem");
            return (Criteria) this;
        }

        public Criteria andPhoneSystemLessThan(String value) {
            addCriterion("phone_system <", value, "phoneSystem");
            return (Criteria) this;
        }

        public Criteria andPhoneSystemLessThanOrEqualTo(String value) {
            addCriterion("phone_system <=", value, "phoneSystem");
            return (Criteria) this;
        }

        public Criteria andPhoneSystemLike(String value) {
            addCriterion("phone_system like", value, "phoneSystem");
            return (Criteria) this;
        }

        public Criteria andPhoneSystemNotLike(String value) {
            addCriterion("phone_system not like", value, "phoneSystem");
            return (Criteria) this;
        }

        public Criteria andPhoneSystemIn(List<String> values) {
            addCriterion("phone_system in", values, "phoneSystem");
            return (Criteria) this;
        }

        public Criteria andPhoneSystemNotIn(List<String> values) {
            addCriterion("phone_system not in", values, "phoneSystem");
            return (Criteria) this;
        }

        public Criteria andPhoneSystemBetween(String value1, String value2) {
            addCriterion("phone_system between", value1, value2, "phoneSystem");
            return (Criteria) this;
        }

        public Criteria andPhoneSystemNotBetween(String value1, String value2) {
            addCriterion("phone_system not between", value1, value2, "phoneSystem");
            return (Criteria) this;
        }

        public Criteria andAppVersionIsNull() {
            addCriterion("app_version is null");
            return (Criteria) this;
        }

        public Criteria andAppVersionIsNotNull() {
            addCriterion("app_version is not null");
            return (Criteria) this;
        }

        public Criteria andAppVersionEqualTo(String value) {
            addCriterion("app_version =", value, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionNotEqualTo(String value) {
            addCriterion("app_version <>", value, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionGreaterThan(String value) {
            addCriterion("app_version >", value, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionGreaterThanOrEqualTo(String value) {
            addCriterion("app_version >=", value, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionLessThan(String value) {
            addCriterion("app_version <", value, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionLessThanOrEqualTo(String value) {
            addCriterion("app_version <=", value, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionLike(String value) {
            addCriterion("app_version like", value, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionNotLike(String value) {
            addCriterion("app_version not like", value, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionIn(List<String> values) {
            addCriterion("app_version in", values, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionNotIn(List<String> values) {
            addCriterion("app_version not in", values, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionBetween(String value1, String value2) {
            addCriterion("app_version between", value1, value2, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionNotBetween(String value1, String value2) {
            addCriterion("app_version not between", value1, value2, "appVersion");
            return (Criteria) this;
        }
        public Criteria andProcesByIsNull() {
            addCriterion("proces_by is null");
            return (Criteria) this;
        }

        public Criteria andProcesByIsNotNull() {
            addCriterion("proces_by is not null");
            return (Criteria) this;
        }

        public Criteria andProcesByEqualTo(Integer value) {
            addCriterion("proces_by =", value, "procesby");
            return (Criteria) this;
        }

        public Criteria andProcesByNotEqualTo(Integer value) {
            addCriterion("proces_by <>", value, "procesby");
            return (Criteria) this;
        }

        public Criteria andProcesByGreaterThan(Integer value) {
            addCriterion("proces_by >", value, "procesby");
            return (Criteria) this;
        }

        public Criteria andProcesByGreaterThanOrEqualTo(Integer value) {
            addCriterion("proces_by >=", value, "procesby");
            return (Criteria) this;
        }

        public Criteria andProcesByLessThan(Integer value) {
            addCriterion("proces_by <", value, "procesby");
            return (Criteria) this;
        }

        public Criteria andProcesByLessThanOrEqualTo(Integer value) {
            addCriterion("proces_by <=", value, "procesby");
            return (Criteria) this;
        }

        public Criteria andProcesByLike(Integer value) {
            addCriterion("proces_by like", value, "procesby");
            return (Criteria) this;
        }

        public Criteria andProcesByNotLike(Integer value) {
            addCriterion("proces_by not like", value, "procesby");
            return (Criteria) this;
        }

        public Criteria andProcesByIn(List<Integer> values) {
            addCriterion("proces_by in", values, "procesby");
            return (Criteria) this;
        }

        public Criteria andProcesByNotIn(List<Integer> values) {
            addCriterion("proces_by not in", values, "procesby");
            return (Criteria) this;
        }

        public Criteria andProcesByBetween(Integer value1, Integer value2) {
            addCriterion("proces_by between", value1, value2, "procesby");
            return (Criteria) this;
        }

        public Criteria andProcesByNotBetween(Integer value1, Integer value2) {
            addCriterion("proces_by not between", value1, value2, "procesby");
            return (Criteria) this;
        }
        public Criteria andProcessDateIsNull() {
            addCriterion("process_date is null");
            return (Criteria) this;
        }

        public Criteria andProcessDateIsNotNull() {
            addCriterion("process_date is not null");
            return (Criteria) this;
        }

        public Criteria andProcessDateEqualTo(Date value) {
            addCriterion("process_date =", value, "processdate");
            return (Criteria) this;
        }

        public Criteria andProcessDateNotEqualTo(Date value) {
            addCriterion("process_date <>", value, "processdate");
            return (Criteria) this;
        }

        public Criteria andProcessDateGreaterThan(Date value) {
            addCriterion("process_date >", value, "processdate");
            return (Criteria) this;
        }

        public Criteria andProcessDateGreaterThanOrEqualTo(Date value) {
            addCriterion("process_date >=", value, "processdate");
            return (Criteria) this;
        }

        public Criteria andProcessDateLessThan(Date value) {
            addCriterion("process_date <", value, "processdate");
            return (Criteria) this;
        }

        public Criteria andProcessDateLessThanOrEqualTo(Date value) {
            addCriterion("process_date <=", value, "processdate");
            return (Criteria) this;
        }

        public Criteria andProcessDateLike(Date value) {
            addCriterion("process_date like", value, "processdate");
            return (Criteria) this;
        }

        public Criteria andProcessDateNotLike(Date value) {
            addCriterion("process_date not like", value, "processdate");
            return (Criteria) this;
        }

        public Criteria andProcessDateIn(List<Date> values) {
            addCriterion("process_date in", values, "processdate");
            return (Criteria) this;
        }

        public Criteria andProcessDateNotIn(List<Date> values) {
            addCriterion("process_date not in", values, "processdate");
            return (Criteria) this;
        }

        public Criteria andProcessDateBetween(Date value1, Date value2) {
            addCriterion("process_date between", value1, value2, "processdate");
            return (Criteria) this;
        }

        public Criteria andProcessDateNotBetween(Date value1, Date value2) {
            addCriterion("process_date not between", value1, value2, "processdate");
            return (Criteria) this;
        }
        public Criteria andDealStatusIsNull() {
            addCriterion("deal_status is null");
            return (Criteria) this;
        }

        public Criteria andDealStatusIsNotNull() {
            addCriterion("deal_status is not null");
            return (Criteria) this;
        }

        public Criteria andDealStatusEqualTo(String value) {
            addCriterion("deal_status =", value, "dealstatus");
            return (Criteria) this;
        }

        public Criteria andDealStatusNotEqualTo(String value) {
            addCriterion("deal_status <>", value, "dealstatus");
            return (Criteria) this;
        }

        public Criteria andDealStatusGreaterThan(String value) {
            addCriterion("deal_status >", value, "dealstatus");
            return (Criteria) this;
        }

        public Criteria andDealStatusGreaterThanOrEqualTo(String value) {
            addCriterion("deal_status >=", value, "dealstatus");
            return (Criteria) this;
        }

        public Criteria andDealStatusLessThan(String value) {
            addCriterion("deal_status <", value, "dealstatus");
            return (Criteria) this;
        }

        public Criteria andDealStatusLessThanOrEqualTo(String value) {
            addCriterion("deal_status <=", value, "dealstatus");
            return (Criteria) this;
        }

        public Criteria andDealStatusLike(String value) {
            addCriterion("deal_status like", value, "dealstatus");
            return (Criteria) this;
        }

        public Criteria andDealStatusNotLike(String value) {
            addCriterion("deal_status not like", value, "dealstatus");
            return (Criteria) this;
        }

        public Criteria andDealStatusIn(List<String> values) {
            addCriterion("deal_status in", values, "dealstatus");
            return (Criteria) this;
        }

        public Criteria andDealStatusNotIn(List<String> values) {
            addCriterion("deal_status not in", values, "dealstatus");
            return (Criteria) this;
        }

        public Criteria andDealStatusBetween(String value1, String value2) {
            addCriterion("deal_status between", value1, value2, "dealstatus");
            return (Criteria) this;
        }

        public Criteria andDealStatusNotBetween(String value1, String value2) {
            addCriterion("deal_status not between", value1, value2, "dealstatus");
            return (Criteria) this;
        }
        public Criteria andDealOpinionIsNull() {
            addCriterion("deal_opinion is null");
            return (Criteria) this;
        }

        public Criteria andDealOpinionNotNull() {
            addCriterion("deal_opinion is not null");
            return (Criteria) this;
        }

        public Criteria andDealOpinionEqualTo(String value) {
            addCriterion("deal_opinion =", value, "dealopinion");
            return (Criteria) this;
        }

        public Criteria andDealOpinionNotEqualTo(String value) {
            addCriterion("deal_opinion <>", value, "dealopinion");
            return (Criteria) this;
        }

        public Criteria andDealOpinionGreaterThan(String value) {
            addCriterion("deal_opinion >", value, "dealopinion");
            return (Criteria) this;
        }

        public Criteria andDealOpinionGreaterThanOrEqualTo(String value) {
            addCriterion("deal_opinion >=", value, "dealopinion");
            return (Criteria) this;
        }

        public Criteria andDealOpinionLessThan(String value) {
            addCriterion("deal_opinion <", value, "dealopinion");
            return (Criteria) this;
        }

        public Criteria andDealOpinionLessThanOrEqualTo(String value) {
            addCriterion("deal_opinion <=", value, "dealopinion");
            return (Criteria) this;
        }

        public Criteria andDealOpinionLike(String value) {
            addCriterion("deal_opinion like", value, "dealopinion");
            return (Criteria) this;
        }

        public Criteria andDealOpinionNotLike(String value) {
            addCriterion("deal_opinion not like", value, "dealopinion");
            return (Criteria) this;
        }

        public Criteria andDealOpinionIn(List<String> values) {
            addCriterion("deal_opinion in", values, "dealopinion");
            return (Criteria) this;
        }

        public Criteria andDealOpinionNotIn(List<String> values) {
            addCriterion("deal_opinion not in", values, "dealopinion");
            return (Criteria) this;
        }

        public Criteria andDealOpinionBetween(String value1, String value2) {
            addCriterion("deal_opinion between", value1, value2, "dealopinion");
            return (Criteria) this;
        }

        public Criteria andDealOpinionNotBetween(String value1, String value2) {
            addCriterion("deal_opinion not between", value1, value2, "dealopinion");
            return (Criteria) this;
        }
        public Criteria andRelatedOrderIsNull() {
            addCriterion("related_order is null");
            return (Criteria) this;
        }

        public Criteria andRelatedOrderNotNull() {
            addCriterion("related_order is not null");
            return (Criteria) this;
        }

        public Criteria andRelatedOrderEqualTo(String value) {
            addCriterion("related_order =", value, "relatedorder");
            return (Criteria) this;
        }

        public Criteria andRelatedOrderNotEqualTo(String value) {
            addCriterion("related_order <>", value, "relatedorder");
            return (Criteria) this;
        }

        public Criteria andRelatedOrderGreaterThan(String value) {
            addCriterion("related_order >", value, "relatedorder");
            return (Criteria) this;
        }

        public Criteria andRelatedOrderGreaterThanOrEqualTo(String value) {
            addCriterion("related_order >=", value, "relatedorder");
            return (Criteria) this;
        }

        public Criteria andRelatedOrderLessThan(String value) {
            addCriterion("related_order <", value, "relatedorder");
            return (Criteria) this;
        }

        public Criteria andRelatedOrderLessThanOrEqualTo(String value) {
            addCriterion("related_order <=", value, "relatedorder");
            return (Criteria) this;
        }

        public Criteria andRelatedOrderLike(String value) {
            addCriterion("related_order like", value, "relatedorder");
            return (Criteria) this;
        }

        public Criteria andRelatedOrderNotLike(String value) {
            addCriterion("related_order not like", value, "relatedorder");
            return (Criteria) this;
        }

        public Criteria andRelatedOrderIn(List<String> values) {
            addCriterion("related_order in", values, "relatedorder");
            return (Criteria) this;
        }

        public Criteria andRelatedOrderNotIn(List<String> values) {
            addCriterion("related_order not in", values, "relatedorder");
            return (Criteria) this;
        }

        public Criteria andRelatedOrderBetween(String value1, String value2) {
            addCriterion("related_order between", value1, value2, "relatedorder");
            return (Criteria) this;
        }

        public Criteria andRelatedOrderNotBetween(String value1, String value2) {
            addCriterion("related_order not between", value1, value2, "relatedorder");
            return (Criteria) this;
        }
        public Criteria andDealDateIsNull() {
            addCriterion("deal_date is null");
            return (Criteria) this;
        }

        public Criteria andDealDateNotNull() {
            addCriterion("deal_date is not null");
            return (Criteria) this;
        }

        public Criteria andDealDateEqualTo(Date value) {
            addCriterion("deal_date =", value, "dealdate");
            return (Criteria) this;
        }

        public Criteria andDealDateNotEqualTo(Date value) {
            addCriterion("deal_date <>", value, "dealdate");
            return (Criteria) this;
        }

        public Criteria andDealDateGreaterThan(Date value) {
            addCriterion("deal_date >", value, "dealdate");
            return (Criteria) this;
        }

        public Criteria andDealDateGreaterThanOrEqualTo(Date value) {
            addCriterion("deal_date >=", value, "dealdate");
            return (Criteria) this;
        }

        public Criteria andDealDateLessThan(Date value) {
            addCriterion("deal_date <", value, "dealdate");
            return (Criteria) this;
        }

        public Criteria andDealDateLessThanOrEqualTo(Date value) {
            addCriterion("deal_date <=", value, "dealdate");
            return (Criteria) this;
        }

        public Criteria andDealDateLike(Date value) {
            addCriterion("deal_date like", value, "dealdate");
            return (Criteria) this;
        }

        public Criteria andDealDateNotLike(Date value) {
            addCriterion("deal_date not like", value, "dealdate");
            return (Criteria) this;
        }

        public Criteria andDealDateIn(List<Date> values) {
            addCriterion("deal_date in", values, "dealdate");
            return (Criteria) this;
        }

        public Criteria andDealDateNotIn(List<Date> values) {
            addCriterion("deal_date not in", values, "dealdate");
            return (Criteria) this;
        }

        public Criteria andDealDateBetween(Date value1, Date value2) {
            addCriterion("deal_date between", value1, value2, "dealdate");
            return (Criteria) this;
        }

        public Criteria andDealDateNotBetween(Date value1, Date value2) {
            addCriterion("deal_date not between", value1, value2, "dealdate");
            return (Criteria) this;
        }
    
        public Criteria andmchtCodeIsNull() {
            addCriterion("mcht_code is null");
            return (Criteria) this;
        }

        public Criteria andmchtCodeNotNull() {
            addCriterion("mcht_code is not null");
            return (Criteria) this;
        }

        public Criteria andmchtCodeEqualTo(String value) {
            addCriterion("mcht_code =", value, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andmchtCodeNotEqualTo(String value) {
            addCriterion("mcht_code <>", value, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andmchtCodeGreaterThan(String value) {
            addCriterion("mcht_code >", value, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andmchtCodeGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_code >=", value, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andmchtCodeLessThan(String value) {
            addCriterion("mcht_code <", value, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andmchtCodeLessThanOrEqualTo(String value) {
            addCriterion("mcht_code <=", value, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andmchtCodeLike(String value) {
            addCriterion("mcht_code like", value, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andmchtCodeNotLike(String value) {
            addCriterion("mcht_code not like", value, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andmchtCodeIn(List<String> values) {
            addCriterion("mcht_code in", values, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andmchtCodeNotIn(List<String> values) {
            addCriterion("mcht_code not in", values, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andmchtCodeBetween(String value1, String value2) {
            addCriterion("mcht_code between", value1, value2, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andmchtCodeNotBetween(String value1, String value2) {
            addCriterion("mcht_code not between", value1, value2, "mchtCode");
            return (Criteria) this;
        }
        public Criteria andmchtcompanyNameIsNull() {
            addCriterion("mcht_company_name is null");
            return (Criteria) this;
        }

        public Criteria andmchtcompanyNameNotNull() {
            addCriterion("mcht_company_name is not null");
            return (Criteria) this;
        }

        public Criteria andmchtcompanyNameEqualTo(String value) {
            addCriterion("mcht_company_name =", value, "mchtcompanyName");
            return (Criteria) this;
        }

        public Criteria andmchtcompanyNameNotEqualTo(String value) {
            addCriterion("mcht_company_name <>", value, "mchtcompanyName");
            return (Criteria) this;
        }

        public Criteria andmchtcompanyNameGreaterThan(String value) {
            addCriterion("mcht_company_name >", value, "mchtcompanyName");
            return (Criteria) this;
        }

        public Criteria andmchtcompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_company_name >=", value, "mchtcompanyName");
            return (Criteria) this;
        }

        public Criteria andmchtcompanyNameLessThan(String value) {
            addCriterion("mcht_company_name <", value, "mchtcompanyName");
            return (Criteria) this;
        }

        public Criteria andmchtcompanyNameLessThanOrEqualTo(String value) {
            addCriterion("mcht_company_name <=", value, "mchtcompanyName");
            return (Criteria) this;
        }

        public Criteria andmchtcompanyNameLike(String value) {
            addCriterion("mcht_company_name like", value, "mchtcompanyName");
            return (Criteria) this;
        }

        public Criteria andmchtcompanyNameNotLike(String value) {
            addCriterion("mcht_company_name not like", value, "mchtcompanyName");
            return (Criteria) this;
        }

        public Criteria andmchtcompanyNameIn(List<String> values) {
            addCriterion("mcht_company_name in", values, "mchtcompanyName");
            return (Criteria) this;
        }

        public Criteria andmchtcompanyNameNotIn(List<String> values) {
            addCriterion("mcht_company_name not in", values, "mchtcompanyName");
            return (Criteria) this;
        }

        public Criteria andmchtcompanyNameBetween(String value1, String value2) {
            addCriterion("mcht_company_name between", value1, value2, "mchtcompanyName");
            return (Criteria) this;
        }

        public Criteria andmchtcompanyNameNotBetween(String value1, String value2) {
            addCriterion("mcht_company_name not between", value1, value2, "mchtcompanyName");
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