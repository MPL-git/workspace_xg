package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LandingPageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public LandingPageExample() {
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

        public Criteria andAndroidChannelIsNull() {
            addCriterion("android_channel is null");
            return (Criteria) this;
        }

        public Criteria andAndroidChannelIsNotNull() {
            addCriterion("android_channel is not null");
            return (Criteria) this;
        }

        public Criteria andAndroidChannelEqualTo(String value) {
            addCriterion("android_channel =", value, "androidChannel");
            return (Criteria) this;
        }

        public Criteria andAndroidChannelNotEqualTo(String value) {
            addCriterion("android_channel <>", value, "androidChannel");
            return (Criteria) this;
        }

        public Criteria andAndroidChannelGreaterThan(String value) {
            addCriterion("android_channel >", value, "androidChannel");
            return (Criteria) this;
        }

        public Criteria andAndroidChannelGreaterThanOrEqualTo(String value) {
            addCriterion("android_channel >=", value, "androidChannel");
            return (Criteria) this;
        }

        public Criteria andAndroidChannelLessThan(String value) {
            addCriterion("android_channel <", value, "androidChannel");
            return (Criteria) this;
        }

        public Criteria andAndroidChannelLessThanOrEqualTo(String value) {
            addCriterion("android_channel <=", value, "androidChannel");
            return (Criteria) this;
        }

        public Criteria andAndroidChannelLike(String value) {
            addCriterion("android_channel like", value, "androidChannel");
            return (Criteria) this;
        }

        public Criteria andAndroidChannelNotLike(String value) {
            addCriterion("android_channel not like", value, "androidChannel");
            return (Criteria) this;
        }

        public Criteria andAndroidChannelIn(List<String> values) {
            addCriterion("android_channel in", values, "androidChannel");
            return (Criteria) this;
        }

        public Criteria andAndroidChannelNotIn(List<String> values) {
            addCriterion("android_channel not in", values, "androidChannel");
            return (Criteria) this;
        }

        public Criteria andAndroidChannelBetween(String value1, String value2) {
            addCriterion("android_channel between", value1, value2, "androidChannel");
            return (Criteria) this;
        }

        public Criteria andAndroidChannelNotBetween(String value1, String value2) {
            addCriterion("android_channel not between", value1, value2, "androidChannel");
            return (Criteria) this;
        }

        public Criteria andIosActivityGroupIdIsNull() {
            addCriterion("ios_activity_group_id is null");
            return (Criteria) this;
        }

        public Criteria andIosActivityGroupIdIsNotNull() {
            addCriterion("ios_activity_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andIosActivityGroupIdEqualTo(Integer value) {
            addCriterion("ios_activity_group_id =", value, "iosActivityGroupId");
            return (Criteria) this;
        }

        public Criteria andIosActivityGroupIdNotEqualTo(Integer value) {
            addCriterion("ios_activity_group_id <>", value, "iosActivityGroupId");
            return (Criteria) this;
        }

        public Criteria andIosActivityGroupIdGreaterThan(Integer value) {
            addCriterion("ios_activity_group_id >", value, "iosActivityGroupId");
            return (Criteria) this;
        }

        public Criteria andIosActivityGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ios_activity_group_id >=", value, "iosActivityGroupId");
            return (Criteria) this;
        }

        public Criteria andIosActivityGroupIdLessThan(Integer value) {
            addCriterion("ios_activity_group_id <", value, "iosActivityGroupId");
            return (Criteria) this;
        }

        public Criteria andIosActivityGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("ios_activity_group_id <=", value, "iosActivityGroupId");
            return (Criteria) this;
        }

        public Criteria andIosActivityGroupIdIn(List<Integer> values) {
            addCriterion("ios_activity_group_id in", values, "iosActivityGroupId");
            return (Criteria) this;
        }

        public Criteria andIosActivityGroupIdNotIn(List<Integer> values) {
            addCriterion("ios_activity_group_id not in", values, "iosActivityGroupId");
            return (Criteria) this;
        }

        public Criteria andIosActivityGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("ios_activity_group_id between", value1, value2, "iosActivityGroupId");
            return (Criteria) this;
        }

        public Criteria andIosActivityGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ios_activity_group_id not between", value1, value2, "iosActivityGroupId");
            return (Criteria) this;
        }

        public Criteria andIosActivityNameIsNull() {
            addCriterion("ios_activity_name is null");
            return (Criteria) this;
        }

        public Criteria andIosActivityNameIsNotNull() {
            addCriterion("ios_activity_name is not null");
            return (Criteria) this;
        }

        public Criteria andIosActivityNameEqualTo(String value) {
            addCriterion("ios_activity_name =", value, "iosActivityName");
            return (Criteria) this;
        }

        public Criteria andIosActivityNameNotEqualTo(String value) {
            addCriterion("ios_activity_name <>", value, "iosActivityName");
            return (Criteria) this;
        }

        public Criteria andIosActivityNameGreaterThan(String value) {
            addCriterion("ios_activity_name >", value, "iosActivityName");
            return (Criteria) this;
        }

        public Criteria andIosActivityNameGreaterThanOrEqualTo(String value) {
            addCriterion("ios_activity_name >=", value, "iosActivityName");
            return (Criteria) this;
        }

        public Criteria andIosActivityNameLessThan(String value) {
            addCriterion("ios_activity_name <", value, "iosActivityName");
            return (Criteria) this;
        }

        public Criteria andIosActivityNameLessThanOrEqualTo(String value) {
            addCriterion("ios_activity_name <=", value, "iosActivityName");
            return (Criteria) this;
        }

        public Criteria andIosActivityNameLike(String value) {
            addCriterion("ios_activity_name like", value, "iosActivityName");
            return (Criteria) this;
        }

        public Criteria andIosActivityNameNotLike(String value) {
            addCriterion("ios_activity_name not like", value, "iosActivityName");
            return (Criteria) this;
        }

        public Criteria andIosActivityNameIn(List<String> values) {
            addCriterion("ios_activity_name in", values, "iosActivityName");
            return (Criteria) this;
        }

        public Criteria andIosActivityNameNotIn(List<String> values) {
            addCriterion("ios_activity_name not in", values, "iosActivityName");
            return (Criteria) this;
        }

        public Criteria andIosActivityNameBetween(String value1, String value2) {
            addCriterion("ios_activity_name between", value1, value2, "iosActivityName");
            return (Criteria) this;
        }

        public Criteria andIosActivityNameNotBetween(String value1, String value2) {
            addCriterion("ios_activity_name not between", value1, value2, "iosActivityName");
            return (Criteria) this;
        }

        public Criteria andEditorRecommendIsNull() {
            addCriterion("editor_recommend is null");
            return (Criteria) this;
        }

        public Criteria andEditorRecommendIsNotNull() {
            addCriterion("editor_recommend is not null");
            return (Criteria) this;
        }

        public Criteria andEditorRecommendEqualTo(String value) {
            addCriterion("editor_recommend =", value, "editorRecommend");
            return (Criteria) this;
        }

        public Criteria andEditorRecommendNotEqualTo(String value) {
            addCriterion("editor_recommend <>", value, "editorRecommend");
            return (Criteria) this;
        }

        public Criteria andEditorRecommendGreaterThan(String value) {
            addCriterion("editor_recommend >", value, "editorRecommend");
            return (Criteria) this;
        }

        public Criteria andEditorRecommendGreaterThanOrEqualTo(String value) {
            addCriterion("editor_recommend >=", value, "editorRecommend");
            return (Criteria) this;
        }

        public Criteria andEditorRecommendLessThan(String value) {
            addCriterion("editor_recommend <", value, "editorRecommend");
            return (Criteria) this;
        }

        public Criteria andEditorRecommendLessThanOrEqualTo(String value) {
            addCriterion("editor_recommend <=", value, "editorRecommend");
            return (Criteria) this;
        }

        public Criteria andEditorRecommendLike(String value) {
            addCriterion("editor_recommend like", value, "editorRecommend");
            return (Criteria) this;
        }

        public Criteria andEditorRecommendNotLike(String value) {
            addCriterion("editor_recommend not like", value, "editorRecommend");
            return (Criteria) this;
        }

        public Criteria andEditorRecommendIn(List<String> values) {
            addCriterion("editor_recommend in", values, "editorRecommend");
            return (Criteria) this;
        }

        public Criteria andEditorRecommendNotIn(List<String> values) {
            addCriterion("editor_recommend not in", values, "editorRecommend");
            return (Criteria) this;
        }

        public Criteria andEditorRecommendBetween(String value1, String value2) {
            addCriterion("editor_recommend between", value1, value2, "editorRecommend");
            return (Criteria) this;
        }

        public Criteria andEditorRecommendNotBetween(String value1, String value2) {
            addCriterion("editor_recommend not between", value1, value2, "editorRecommend");
            return (Criteria) this;
        }

        public Criteria andApplicationInformationIsNull() {
            addCriterion("application_information is null");
            return (Criteria) this;
        }

        public Criteria andApplicationInformationIsNotNull() {
            addCriterion("application_information is not null");
            return (Criteria) this;
        }

        public Criteria andApplicationInformationEqualTo(String value) {
            addCriterion("application_information =", value, "applicationInformation");
            return (Criteria) this;
        }

        public Criteria andApplicationInformationNotEqualTo(String value) {
            addCriterion("application_information <>", value, "applicationInformation");
            return (Criteria) this;
        }

        public Criteria andApplicationInformationGreaterThan(String value) {
            addCriterion("application_information >", value, "applicationInformation");
            return (Criteria) this;
        }

        public Criteria andApplicationInformationGreaterThanOrEqualTo(String value) {
            addCriterion("application_information >=", value, "applicationInformation");
            return (Criteria) this;
        }

        public Criteria andApplicationInformationLessThan(String value) {
            addCriterion("application_information <", value, "applicationInformation");
            return (Criteria) this;
        }

        public Criteria andApplicationInformationLessThanOrEqualTo(String value) {
            addCriterion("application_information <=", value, "applicationInformation");
            return (Criteria) this;
        }

        public Criteria andApplicationInformationLike(String value) {
            addCriterion("application_information like", value, "applicationInformation");
            return (Criteria) this;
        }

        public Criteria andApplicationInformationNotLike(String value) {
            addCriterion("application_information not like", value, "applicationInformation");
            return (Criteria) this;
        }

        public Criteria andApplicationInformationIn(List<String> values) {
            addCriterion("application_information in", values, "applicationInformation");
            return (Criteria) this;
        }

        public Criteria andApplicationInformationNotIn(List<String> values) {
            addCriterion("application_information not in", values, "applicationInformation");
            return (Criteria) this;
        }

        public Criteria andApplicationInformationBetween(String value1, String value2) {
            addCriterion("application_information between", value1, value2, "applicationInformation");
            return (Criteria) this;
        }

        public Criteria andApplicationInformationNotBetween(String value1, String value2) {
            addCriterion("application_information not between", value1, value2, "applicationInformation");
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