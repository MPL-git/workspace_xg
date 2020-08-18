package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdPvStatisticalExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public AdPvStatisticalExample() {
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

        public Criteria andStatisticalDateIsNull() {
            addCriterion("statistical_date is null");
            return (Criteria) this;
        }

        public Criteria andStatisticalDateIsNotNull() {
            addCriterion("statistical_date is not null");
            return (Criteria) this;
        }

        public Criteria andStatisticalDateEqualTo(String value) {
            addCriterion("statistical_date =", value, "statisticalDate");
            return (Criteria) this;
        }

        public Criteria andStatisticalDateNotEqualTo(String value) {
            addCriterion("statistical_date <>", value, "statisticalDate");
            return (Criteria) this;
        }

        public Criteria andStatisticalDateGreaterThan(String value) {
            addCriterion("statistical_date >", value, "statisticalDate");
            return (Criteria) this;
        }

        public Criteria andStatisticalDateGreaterThanOrEqualTo(String value) {
            addCriterion("statistical_date >=", value, "statisticalDate");
            return (Criteria) this;
        }

        public Criteria andStatisticalDateLessThan(String value) {
            addCriterion("statistical_date <", value, "statisticalDate");
            return (Criteria) this;
        }

        public Criteria andStatisticalDateLessThanOrEqualTo(String value) {
            addCriterion("statistical_date <=", value, "statisticalDate");
            return (Criteria) this;
        }

        public Criteria andStatisticalDateLike(String value) {
            addCriterion("statistical_date like", value, "statisticalDate");
            return (Criteria) this;
        }

        public Criteria andStatisticalDateNotLike(String value) {
            addCriterion("statistical_date not like", value, "statisticalDate");
            return (Criteria) this;
        }

        public Criteria andStatisticalDateIn(List<String> values) {
            addCriterion("statistical_date in", values, "statisticalDate");
            return (Criteria) this;
        }

        public Criteria andStatisticalDateNotIn(List<String> values) {
            addCriterion("statistical_date not in", values, "statisticalDate");
            return (Criteria) this;
        }

        public Criteria andStatisticalDateBetween(String value1, String value2) {
            addCriterion("statistical_date between", value1, value2, "statisticalDate");
            return (Criteria) this;
        }

        public Criteria andStatisticalDateNotBetween(String value1, String value2) {
            addCriterion("statistical_date not between", value1, value2, "statisticalDate");
            return (Criteria) this;
        }

        public Criteria andAdTypeIsNull() {
            addCriterion("ad_type is null");
            return (Criteria) this;
        }

        public Criteria andAdTypeIsNotNull() {
            addCriterion("ad_type is not null");
            return (Criteria) this;
        }

        public Criteria andAdTypeEqualTo(String value) {
            addCriterion("ad_type =", value, "adType");
            return (Criteria) this;
        }

        public Criteria andAdTypeNotEqualTo(String value) {
            addCriterion("ad_type <>", value, "adType");
            return (Criteria) this;
        }

        public Criteria andAdTypeGreaterThan(String value) {
            addCriterion("ad_type >", value, "adType");
            return (Criteria) this;
        }

        public Criteria andAdTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ad_type >=", value, "adType");
            return (Criteria) this;
        }

        public Criteria andAdTypeLessThan(String value) {
            addCriterion("ad_type <", value, "adType");
            return (Criteria) this;
        }

        public Criteria andAdTypeLessThanOrEqualTo(String value) {
            addCriterion("ad_type <=", value, "adType");
            return (Criteria) this;
        }

        public Criteria andAdTypeLike(String value) {
            addCriterion("ad_type like", value, "adType");
            return (Criteria) this;
        }

        public Criteria andAdTypeNotLike(String value) {
            addCriterion("ad_type not like", value, "adType");
            return (Criteria) this;
        }

        public Criteria andAdTypeIn(List<String> values) {
            addCriterion("ad_type in", values, "adType");
            return (Criteria) this;
        }

        public Criteria andAdTypeNotIn(List<String> values) {
            addCriterion("ad_type not in", values, "adType");
            return (Criteria) this;
        }

        public Criteria andAdTypeBetween(String value1, String value2) {
            addCriterion("ad_type between", value1, value2, "adType");
            return (Criteria) this;
        }

        public Criteria andAdTypeNotBetween(String value1, String value2) {
            addCriterion("ad_type not between", value1, value2, "adType");
            return (Criteria) this;
        }

        public Criteria andAdSourceTypeIsNull() {
            addCriterion("ad_source_type is null");
            return (Criteria) this;
        }

        public Criteria andAdSourceTypeIsNotNull() {
            addCriterion("ad_source_type is not null");
            return (Criteria) this;
        }

        public Criteria andAdSourceTypeEqualTo(String value) {
            addCriterion("ad_source_type =", value, "adSourceType");
            return (Criteria) this;
        }

        public Criteria andAdSourceTypeNotEqualTo(String value) {
            addCriterion("ad_source_type <>", value, "adSourceType");
            return (Criteria) this;
        }

        public Criteria andAdSourceTypeGreaterThan(String value) {
            addCriterion("ad_source_type >", value, "adSourceType");
            return (Criteria) this;
        }

        public Criteria andAdSourceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ad_source_type >=", value, "adSourceType");
            return (Criteria) this;
        }

        public Criteria andAdSourceTypeLessThan(String value) {
            addCriterion("ad_source_type <", value, "adSourceType");
            return (Criteria) this;
        }

        public Criteria andAdSourceTypeLessThanOrEqualTo(String value) {
            addCriterion("ad_source_type <=", value, "adSourceType");
            return (Criteria) this;
        }

        public Criteria andAdSourceTypeLike(String value) {
            addCriterion("ad_source_type like", value, "adSourceType");
            return (Criteria) this;
        }

        public Criteria andAdSourceTypeNotLike(String value) {
            addCriterion("ad_source_type not like", value, "adSourceType");
            return (Criteria) this;
        }

        public Criteria andAdSourceTypeIn(List<String> values) {
            addCriterion("ad_source_type in", values, "adSourceType");
            return (Criteria) this;
        }

        public Criteria andAdSourceTypeNotIn(List<String> values) {
            addCriterion("ad_source_type not in", values, "adSourceType");
            return (Criteria) this;
        }

        public Criteria andAdSourceTypeBetween(String value1, String value2) {
            addCriterion("ad_source_type between", value1, value2, "adSourceType");
            return (Criteria) this;
        }

        public Criteria andAdSourceTypeNotBetween(String value1, String value2) {
            addCriterion("ad_source_type not between", value1, value2, "adSourceType");
            return (Criteria) this;
        }

        public Criteria andAdInfoIdIsNull() {
            addCriterion("ad_info_id is null");
            return (Criteria) this;
        }

        public Criteria andAdInfoIdIsNotNull() {
            addCriterion("ad_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andAdInfoIdEqualTo(Integer value) {
            addCriterion("ad_info_id =", value, "adInfoId");
            return (Criteria) this;
        }

        public Criteria andAdInfoIdNotEqualTo(Integer value) {
            addCriterion("ad_info_id <>", value, "adInfoId");
            return (Criteria) this;
        }

        public Criteria andAdInfoIdGreaterThan(Integer value) {
            addCriterion("ad_info_id >", value, "adInfoId");
            return (Criteria) this;
        }

        public Criteria andAdInfoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ad_info_id >=", value, "adInfoId");
            return (Criteria) this;
        }

        public Criteria andAdInfoIdLessThan(Integer value) {
            addCriterion("ad_info_id <", value, "adInfoId");
            return (Criteria) this;
        }

        public Criteria andAdInfoIdLessThanOrEqualTo(Integer value) {
            addCriterion("ad_info_id <=", value, "adInfoId");
            return (Criteria) this;
        }

        public Criteria andAdInfoIdIn(List<Integer> values) {
            addCriterion("ad_info_id in", values, "adInfoId");
            return (Criteria) this;
        }

        public Criteria andAdInfoIdNotIn(List<Integer> values) {
            addCriterion("ad_info_id not in", values, "adInfoId");
            return (Criteria) this;
        }

        public Criteria andAdInfoIdBetween(Integer value1, Integer value2) {
            addCriterion("ad_info_id between", value1, value2, "adInfoId");
            return (Criteria) this;
        }

        public Criteria andAdInfoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ad_info_id not between", value1, value2, "adInfoId");
            return (Criteria) this;
        }

        public Criteria andAdPicIsNull() {
            addCriterion("ad_pic is null");
            return (Criteria) this;
        }

        public Criteria andAdPicIsNotNull() {
            addCriterion("ad_pic is not null");
            return (Criteria) this;
        }

        public Criteria andAdPicEqualTo(String value) {
            addCriterion("ad_pic =", value, "adPic");
            return (Criteria) this;
        }

        public Criteria andAdPicNotEqualTo(String value) {
            addCriterion("ad_pic <>", value, "adPic");
            return (Criteria) this;
        }

        public Criteria andAdPicGreaterThan(String value) {
            addCriterion("ad_pic >", value, "adPic");
            return (Criteria) this;
        }

        public Criteria andAdPicGreaterThanOrEqualTo(String value) {
            addCriterion("ad_pic >=", value, "adPic");
            return (Criteria) this;
        }

        public Criteria andAdPicLessThan(String value) {
            addCriterion("ad_pic <", value, "adPic");
            return (Criteria) this;
        }

        public Criteria andAdPicLessThanOrEqualTo(String value) {
            addCriterion("ad_pic <=", value, "adPic");
            return (Criteria) this;
        }

        public Criteria andAdPicLike(String value) {
            addCriterion("ad_pic like", value, "adPic");
            return (Criteria) this;
        }

        public Criteria andAdPicNotLike(String value) {
            addCriterion("ad_pic not like", value, "adPic");
            return (Criteria) this;
        }

        public Criteria andAdPicIn(List<String> values) {
            addCriterion("ad_pic in", values, "adPic");
            return (Criteria) this;
        }

        public Criteria andAdPicNotIn(List<String> values) {
            addCriterion("ad_pic not in", values, "adPic");
            return (Criteria) this;
        }

        public Criteria andAdPicBetween(String value1, String value2) {
            addCriterion("ad_pic between", value1, value2, "adPic");
            return (Criteria) this;
        }

        public Criteria andAdPicNotBetween(String value1, String value2) {
            addCriterion("ad_pic not between", value1, value2, "adPic");
            return (Criteria) this;
        }

        public Criteria andExposureCountIsNull() {
            addCriterion("exposure_count is null");
            return (Criteria) this;
        }

        public Criteria andExposureCountIsNotNull() {
            addCriterion("exposure_count is not null");
            return (Criteria) this;
        }

        public Criteria andExposureCountEqualTo(Integer value) {
            addCriterion("exposure_count =", value, "exposureCount");
            return (Criteria) this;
        }

        public Criteria andExposureCountNotEqualTo(Integer value) {
            addCriterion("exposure_count <>", value, "exposureCount");
            return (Criteria) this;
        }

        public Criteria andExposureCountGreaterThan(Integer value) {
            addCriterion("exposure_count >", value, "exposureCount");
            return (Criteria) this;
        }

        public Criteria andExposureCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("exposure_count >=", value, "exposureCount");
            return (Criteria) this;
        }

        public Criteria andExposureCountLessThan(Integer value) {
            addCriterion("exposure_count <", value, "exposureCount");
            return (Criteria) this;
        }

        public Criteria andExposureCountLessThanOrEqualTo(Integer value) {
            addCriterion("exposure_count <=", value, "exposureCount");
            return (Criteria) this;
        }

        public Criteria andExposureCountIn(List<Integer> values) {
            addCriterion("exposure_count in", values, "exposureCount");
            return (Criteria) this;
        }

        public Criteria andExposureCountNotIn(List<Integer> values) {
            addCriterion("exposure_count not in", values, "exposureCount");
            return (Criteria) this;
        }

        public Criteria andExposureCountBetween(Integer value1, Integer value2) {
            addCriterion("exposure_count between", value1, value2, "exposureCount");
            return (Criteria) this;
        }

        public Criteria andExposureCountNotBetween(Integer value1, Integer value2) {
            addCriterion("exposure_count not between", value1, value2, "exposureCount");
            return (Criteria) this;
        }

        public Criteria andClickMemberCountIsNull() {
            addCriterion("click_member_count is null");
            return (Criteria) this;
        }

        public Criteria andClickMemberCountIsNotNull() {
            addCriterion("click_member_count is not null");
            return (Criteria) this;
        }

        public Criteria andClickMemberCountEqualTo(Integer value) {
            addCriterion("click_member_count =", value, "clickMemberCount");
            return (Criteria) this;
        }

        public Criteria andClickMemberCountNotEqualTo(Integer value) {
            addCriterion("click_member_count <>", value, "clickMemberCount");
            return (Criteria) this;
        }

        public Criteria andClickMemberCountGreaterThan(Integer value) {
            addCriterion("click_member_count >", value, "clickMemberCount");
            return (Criteria) this;
        }

        public Criteria andClickMemberCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("click_member_count >=", value, "clickMemberCount");
            return (Criteria) this;
        }

        public Criteria andClickMemberCountLessThan(Integer value) {
            addCriterion("click_member_count <", value, "clickMemberCount");
            return (Criteria) this;
        }

        public Criteria andClickMemberCountLessThanOrEqualTo(Integer value) {
            addCriterion("click_member_count <=", value, "clickMemberCount");
            return (Criteria) this;
        }

        public Criteria andClickMemberCountIn(List<Integer> values) {
            addCriterion("click_member_count in", values, "clickMemberCount");
            return (Criteria) this;
        }

        public Criteria andClickMemberCountNotIn(List<Integer> values) {
            addCriterion("click_member_count not in", values, "clickMemberCount");
            return (Criteria) this;
        }

        public Criteria andClickMemberCountBetween(Integer value1, Integer value2) {
            addCriterion("click_member_count between", value1, value2, "clickMemberCount");
            return (Criteria) this;
        }

        public Criteria andClickMemberCountNotBetween(Integer value1, Integer value2) {
            addCriterion("click_member_count not between", value1, value2, "clickMemberCount");
            return (Criteria) this;
        }

        public Criteria andClickMemberCountTouristIsNull() {
            addCriterion("click_member_count_tourist is null");
            return (Criteria) this;
        }

        public Criteria andClickMemberCountTouristIsNotNull() {
            addCriterion("click_member_count_tourist is not null");
            return (Criteria) this;
        }

        public Criteria andClickMemberCountTouristEqualTo(Integer value) {
            addCriterion("click_member_count_tourist =", value, "clickMemberCountTourist");
            return (Criteria) this;
        }

        public Criteria andClickMemberCountTouristNotEqualTo(Integer value) {
            addCriterion("click_member_count_tourist <>", value, "clickMemberCountTourist");
            return (Criteria) this;
        }

        public Criteria andClickMemberCountTouristGreaterThan(Integer value) {
            addCriterion("click_member_count_tourist >", value, "clickMemberCountTourist");
            return (Criteria) this;
        }

        public Criteria andClickMemberCountTouristGreaterThanOrEqualTo(Integer value) {
            addCriterion("click_member_count_tourist >=", value, "clickMemberCountTourist");
            return (Criteria) this;
        }

        public Criteria andClickMemberCountTouristLessThan(Integer value) {
            addCriterion("click_member_count_tourist <", value, "clickMemberCountTourist");
            return (Criteria) this;
        }

        public Criteria andClickMemberCountTouristLessThanOrEqualTo(Integer value) {
            addCriterion("click_member_count_tourist <=", value, "clickMemberCountTourist");
            return (Criteria) this;
        }

        public Criteria andClickMemberCountTouristIn(List<Integer> values) {
            addCriterion("click_member_count_tourist in", values, "clickMemberCountTourist");
            return (Criteria) this;
        }

        public Criteria andClickMemberCountTouristNotIn(List<Integer> values) {
            addCriterion("click_member_count_tourist not in", values, "clickMemberCountTourist");
            return (Criteria) this;
        }

        public Criteria andClickMemberCountTouristBetween(Integer value1, Integer value2) {
            addCriterion("click_member_count_tourist between", value1, value2, "clickMemberCountTourist");
            return (Criteria) this;
        }

        public Criteria andClickMemberCountTouristNotBetween(Integer value1, Integer value2) {
            addCriterion("click_member_count_tourist not between", value1, value2, "clickMemberCountTourist");
            return (Criteria) this;
        }

        public Criteria andClickCountIsNull() {
            addCriterion("click_count is null");
            return (Criteria) this;
        }

        public Criteria andClickCountIsNotNull() {
            addCriterion("click_count is not null");
            return (Criteria) this;
        }

        public Criteria andClickCountEqualTo(Integer value) {
            addCriterion("click_count =", value, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountNotEqualTo(Integer value) {
            addCriterion("click_count <>", value, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountGreaterThan(Integer value) {
            addCriterion("click_count >", value, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("click_count >=", value, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountLessThan(Integer value) {
            addCriterion("click_count <", value, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountLessThanOrEqualTo(Integer value) {
            addCriterion("click_count <=", value, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountIn(List<Integer> values) {
            addCriterion("click_count in", values, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountNotIn(List<Integer> values) {
            addCriterion("click_count not in", values, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountBetween(Integer value1, Integer value2) {
            addCriterion("click_count between", value1, value2, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountNotBetween(Integer value1, Integer value2) {
            addCriterion("click_count not between", value1, value2, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountTouristIsNull() {
            addCriterion("click_count_tourist is null");
            return (Criteria) this;
        }

        public Criteria andClickCountTouristIsNotNull() {
            addCriterion("click_count_tourist is not null");
            return (Criteria) this;
        }

        public Criteria andClickCountTouristEqualTo(Integer value) {
            addCriterion("click_count_tourist =", value, "clickCountTourist");
            return (Criteria) this;
        }

        public Criteria andClickCountTouristNotEqualTo(Integer value) {
            addCriterion("click_count_tourist <>", value, "clickCountTourist");
            return (Criteria) this;
        }

        public Criteria andClickCountTouristGreaterThan(Integer value) {
            addCriterion("click_count_tourist >", value, "clickCountTourist");
            return (Criteria) this;
        }

        public Criteria andClickCountTouristGreaterThanOrEqualTo(Integer value) {
            addCriterion("click_count_tourist >=", value, "clickCountTourist");
            return (Criteria) this;
        }

        public Criteria andClickCountTouristLessThan(Integer value) {
            addCriterion("click_count_tourist <", value, "clickCountTourist");
            return (Criteria) this;
        }

        public Criteria andClickCountTouristLessThanOrEqualTo(Integer value) {
            addCriterion("click_count_tourist <=", value, "clickCountTourist");
            return (Criteria) this;
        }

        public Criteria andClickCountTouristIn(List<Integer> values) {
            addCriterion("click_count_tourist in", values, "clickCountTourist");
            return (Criteria) this;
        }

        public Criteria andClickCountTouristNotIn(List<Integer> values) {
            addCriterion("click_count_tourist not in", values, "clickCountTourist");
            return (Criteria) this;
        }

        public Criteria andClickCountTouristBetween(Integer value1, Integer value2) {
            addCriterion("click_count_tourist between", value1, value2, "clickCountTourist");
            return (Criteria) this;
        }

        public Criteria andClickCountTouristNotBetween(Integer value1, Integer value2) {
            addCriterion("click_count_tourist not between", value1, value2, "clickCountTourist");
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