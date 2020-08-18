package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SysStaffInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public SysStaffInfoExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andStaffIdIsNull() {
            addCriterion("STAFF_ID is null");
            return (Criteria) this;
        }

        public Criteria andStaffIdIsNotNull() {
            addCriterion("STAFF_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStaffIdEqualTo(Integer value) {
            addCriterion("STAFF_ID =", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotEqualTo(Integer value) {
            addCriterion("STAFF_ID <>", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdGreaterThan(Integer value) {
            addCriterion("STAFF_ID >", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("STAFF_ID >=", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdLessThan(Integer value) {
            addCriterion("STAFF_ID <", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdLessThanOrEqualTo(Integer value) {
            addCriterion("STAFF_ID <=", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdIn(List<Integer> values) {
            addCriterion("STAFF_ID in", values, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotIn(List<Integer> values) {
            addCriterion("STAFF_ID not in", values, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdBetween(Integer value1, Integer value2) {
            addCriterion("STAFF_ID between", value1, value2, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotBetween(Integer value1, Integer value2) {
            addCriterion("STAFF_ID not between", value1, value2, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffCodeIsNull() {
            addCriterion("STAFF_CODE is null");
            return (Criteria) this;
        }

        public Criteria andStaffCodeIsNotNull() {
            addCriterion("STAFF_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andStaffCodeEqualTo(String value) {
            addCriterion("STAFF_CODE =", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeNotEqualTo(String value) {
            addCriterion("STAFF_CODE <>", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeGreaterThan(String value) {
            addCriterion("STAFF_CODE >", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeGreaterThanOrEqualTo(String value) {
            addCriterion("STAFF_CODE >=", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeLessThan(String value) {
            addCriterion("STAFF_CODE <", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeLessThanOrEqualTo(String value) {
            addCriterion("STAFF_CODE <=", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeLike(String value) {
            addCriterion("STAFF_CODE like", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeNotLike(String value) {
            addCriterion("STAFF_CODE not like", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeIn(List<String> values) {
            addCriterion("STAFF_CODE in", values, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeNotIn(List<String> values) {
            addCriterion("STAFF_CODE not in", values, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeBetween(String value1, String value2) {
            addCriterion("STAFF_CODE between", value1, value2, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeNotBetween(String value1, String value2) {
            addCriterion("STAFF_CODE not between", value1, value2, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffPassIsNull() {
            addCriterion("STAFF_PASS is null");
            return (Criteria) this;
        }

        public Criteria andStaffPassIsNotNull() {
            addCriterion("STAFF_PASS is not null");
            return (Criteria) this;
        }

        public Criteria andStaffPassEqualTo(String value) {
            addCriterion("STAFF_PASS =", value, "staffPass");
            return (Criteria) this;
        }

        public Criteria andStaffPassNotEqualTo(String value) {
            addCriterion("STAFF_PASS <>", value, "staffPass");
            return (Criteria) this;
        }

        public Criteria andStaffPassGreaterThan(String value) {
            addCriterion("STAFF_PASS >", value, "staffPass");
            return (Criteria) this;
        }

        public Criteria andStaffPassGreaterThanOrEqualTo(String value) {
            addCriterion("STAFF_PASS >=", value, "staffPass");
            return (Criteria) this;
        }

        public Criteria andStaffPassLessThan(String value) {
            addCriterion("STAFF_PASS <", value, "staffPass");
            return (Criteria) this;
        }

        public Criteria andStaffPassLessThanOrEqualTo(String value) {
            addCriterion("STAFF_PASS <=", value, "staffPass");
            return (Criteria) this;
        }

        public Criteria andStaffPassLike(String value) {
            addCriterion("STAFF_PASS like", value, "staffPass");
            return (Criteria) this;
        }

        public Criteria andStaffPassNotLike(String value) {
            addCriterion("STAFF_PASS not like", value, "staffPass");
            return (Criteria) this;
        }

        public Criteria andStaffPassIn(List<String> values) {
            addCriterion("STAFF_PASS in", values, "staffPass");
            return (Criteria) this;
        }

        public Criteria andStaffPassNotIn(List<String> values) {
            addCriterion("STAFF_PASS not in", values, "staffPass");
            return (Criteria) this;
        }

        public Criteria andStaffPassBetween(String value1, String value2) {
            addCriterion("STAFF_PASS between", value1, value2, "staffPass");
            return (Criteria) this;
        }

        public Criteria andStaffPassNotBetween(String value1, String value2) {
            addCriterion("STAFF_PASS not between", value1, value2, "staffPass");
            return (Criteria) this;
        }

        public Criteria andStaffNameIsNull() {
            addCriterion("STAFF_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStaffNameIsNotNull() {
            addCriterion("STAFF_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStaffNameEqualTo(String value) {
            addCriterion("STAFF_NAME =", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameNotEqualTo(String value) {
            addCriterion("STAFF_NAME <>", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameGreaterThan(String value) {
            addCriterion("STAFF_NAME >", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameGreaterThanOrEqualTo(String value) {
            addCriterion("STAFF_NAME >=", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameLessThan(String value) {
            addCriterion("STAFF_NAME <", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameLessThanOrEqualTo(String value) {
            addCriterion("STAFF_NAME <=", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameLike(String value) {
            addCriterion("STAFF_NAME like", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameNotLike(String value) {
            addCriterion("STAFF_NAME not like", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameIn(List<String> values) {
            addCriterion("STAFF_NAME in", values, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameNotIn(List<String> values) {
            addCriterion("STAFF_NAME not in", values, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameBetween(String value1, String value2) {
            addCriterion("STAFF_NAME between", value1, value2, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameNotBetween(String value1, String value2) {
            addCriterion("STAFF_NAME not between", value1, value2, "staffName");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneIsNull() {
            addCriterion("MOBILE_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneIsNotNull() {
            addCriterion("MOBILE_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneEqualTo(String value) {
            addCriterion("MOBILE_PHONE =", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotEqualTo(String value) {
            addCriterion("MOBILE_PHONE <>", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneGreaterThan(String value) {
            addCriterion("MOBILE_PHONE >", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneGreaterThanOrEqualTo(String value) {
            addCriterion("MOBILE_PHONE >=", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneLessThan(String value) {
            addCriterion("MOBILE_PHONE <", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneLessThanOrEqualTo(String value) {
            addCriterion("MOBILE_PHONE <=", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneLike(String value) {
            addCriterion("MOBILE_PHONE like", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotLike(String value) {
            addCriterion("MOBILE_PHONE not like", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneIn(List<String> values) {
            addCriterion("MOBILE_PHONE in", values, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotIn(List<String> values) {
            addCriterion("MOBILE_PHONE not in", values, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneBetween(String value1, String value2) {
            addCriterion("MOBILE_PHONE between", value1, value2, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotBetween(String value1, String value2) {
            addCriterion("MOBILE_PHONE not between", value1, value2, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("EMAIL =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("EMAIL <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("EMAIL >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("EMAIL >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("EMAIL <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("EMAIL <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("EMAIL like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("EMAIL not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("EMAIL in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("EMAIL not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("EMAIL between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("EMAIL not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNull() {
            addCriterion("ORG_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNotNull() {
            addCriterion("ORG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgIdEqualTo(Integer value) {
            addCriterion("ORG_ID =", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotEqualTo(Integer value) {
            addCriterion("ORG_ID <>", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThan(Integer value) {
            addCriterion("ORG_ID >", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ORG_ID >=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThan(Integer value) {
            addCriterion("ORG_ID <", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThanOrEqualTo(Integer value) {
            addCriterion("ORG_ID <=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdIn(List<Integer> values) {
            addCriterion("ORG_ID in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotIn(List<Integer> values) {
            addCriterion("ORG_ID not in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdBetween(Integer value1, Integer value2) {
            addCriterion("ORG_ID between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ORG_ID not between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andIsManagementIsNull() {
            addCriterion("IS_MANAGEMENT is null");
            return (Criteria) this;
        }

        public Criteria andIsManagementIsNotNull() {
            addCriterion("IS_MANAGEMENT is not null");
            return (Criteria) this;
        }

        public Criteria andIsManagementEqualTo(String value) {
            addCriterion("IS_MANAGEMENT =", value, "isManagement");
            return (Criteria) this;
        }

        public Criteria andIsManagementNotEqualTo(String value) {
            addCriterion("IS_MANAGEMENT <>", value, "isManagement");
            return (Criteria) this;
        }

        public Criteria andIsManagementGreaterThan(String value) {
            addCriterion("IS_MANAGEMENT >", value, "isManagement");
            return (Criteria) this;
        }

        public Criteria andIsManagementGreaterThanOrEqualTo(String value) {
            addCriterion("IS_MANAGEMENT >=", value, "isManagement");
            return (Criteria) this;
        }

        public Criteria andIsManagementLessThan(String value) {
            addCriterion("IS_MANAGEMENT <", value, "isManagement");
            return (Criteria) this;
        }

        public Criteria andIsManagementLessThanOrEqualTo(String value) {
            addCriterion("IS_MANAGEMENT <=", value, "isManagement");
            return (Criteria) this;
        }

        public Criteria andIsManagementLike(String value) {
            addCriterion("IS_MANAGEMENT like", value, "isManagement");
            return (Criteria) this;
        }

        public Criteria andIsManagementNotLike(String value) {
            addCriterion("IS_MANAGEMENT not like", value, "isManagement");
            return (Criteria) this;
        }

        public Criteria andIsManagementIn(List<String> values) {
            addCriterion("IS_MANAGEMENT in", values, "isManagement");
            return (Criteria) this;
        }

        public Criteria andIsManagementNotIn(List<String> values) {
            addCriterion("IS_MANAGEMENT not in", values, "isManagement");
            return (Criteria) this;
        }

        public Criteria andIsManagementBetween(String value1, String value2) {
            addCriterion("IS_MANAGEMENT between", value1, value2, "isManagement");
            return (Criteria) this;
        }

        public Criteria andIsManagementNotBetween(String value1, String value2) {
            addCriterion("IS_MANAGEMENT not between", value1, value2, "isManagement");
            return (Criteria) this;
        }

        public Criteria andCanViewQualificationIsNull() {
            addCriterion("CAN_VIEW_QUALIFICATION is null");
            return (Criteria) this;
        }

        public Criteria andCanViewQualificationIsNotNull() {
            addCriterion("CAN_VIEW_QUALIFICATION is not null");
            return (Criteria) this;
        }

        public Criteria andCanViewQualificationEqualTo(String value) {
            addCriterion("CAN_VIEW_QUALIFICATION =", value, "canViewQualification");
            return (Criteria) this;
        }

        public Criteria andCanViewQualificationNotEqualTo(String value) {
            addCriterion("CAN_VIEW_QUALIFICATION <>", value, "canViewQualification");
            return (Criteria) this;
        }

        public Criteria andCanViewQualificationGreaterThan(String value) {
            addCriterion("CAN_VIEW_QUALIFICATION >", value, "canViewQualification");
            return (Criteria) this;
        }

        public Criteria andCanViewQualificationGreaterThanOrEqualTo(String value) {
            addCriterion("CAN_VIEW_QUALIFICATION >=", value, "canViewQualification");
            return (Criteria) this;
        }

        public Criteria andCanViewQualificationLessThan(String value) {
            addCriterion("CAN_VIEW_QUALIFICATION <", value, "canViewQualification");
            return (Criteria) this;
        }

        public Criteria andCanViewQualificationLessThanOrEqualTo(String value) {
            addCriterion("CAN_VIEW_QUALIFICATION <=", value, "canViewQualification");
            return (Criteria) this;
        }

        public Criteria andCanViewQualificationLike(String value) {
            addCriterion("CAN_VIEW_QUALIFICATION like", value, "canViewQualification");
            return (Criteria) this;
        }

        public Criteria andCanViewQualificationNotLike(String value) {
            addCriterion("CAN_VIEW_QUALIFICATION not like", value, "canViewQualification");
            return (Criteria) this;
        }

        public Criteria andCanViewQualificationIn(List<String> values) {
            addCriterion("CAN_VIEW_QUALIFICATION in", values, "canViewQualification");
            return (Criteria) this;
        }

        public Criteria andCanViewQualificationNotIn(List<String> values) {
            addCriterion("CAN_VIEW_QUALIFICATION not in", values, "canViewQualification");
            return (Criteria) this;
        }

        public Criteria andCanViewQualificationBetween(String value1, String value2) {
            addCriterion("CAN_VIEW_QUALIFICATION between", value1, value2, "canViewQualification");
            return (Criteria) this;
        }

        public Criteria andCanViewQualificationNotBetween(String value1, String value2) {
            addCriterion("CAN_VIEW_QUALIFICATION not between", value1, value2, "canViewQualification");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusDateIsNull() {
            addCriterion("STATUS_DATE is null");
            return (Criteria) this;
        }

        public Criteria andStatusDateIsNotNull() {
            addCriterion("STATUS_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andStatusDateEqualTo(Date value) {
            addCriterion("STATUS_DATE =", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateNotEqualTo(Date value) {
            addCriterion("STATUS_DATE <>", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateGreaterThan(Date value) {
            addCriterion("STATUS_DATE >", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateGreaterThanOrEqualTo(Date value) {
            addCriterion("STATUS_DATE >=", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateLessThan(Date value) {
            addCriterion("STATUS_DATE <", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateLessThanOrEqualTo(Date value) {
            addCriterion("STATUS_DATE <=", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateIn(List<Date> values) {
            addCriterion("STATUS_DATE in", values, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateNotIn(List<Date> values) {
            addCriterion("STATUS_DATE not in", values, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateBetween(Date value1, Date value2) {
            addCriterion("STATUS_DATE between", value1, value2, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateNotBetween(Date value1, Date value2) {
            addCriterion("STATUS_DATE not between", value1, value2, "statusDate");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdIsNull() {
            addCriterion("CREATE_STAFF_ID is null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdIsNotNull() {
            addCriterion("CREATE_STAFF_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdEqualTo(Integer value) {
            addCriterion("CREATE_STAFF_ID =", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdNotEqualTo(Integer value) {
            addCriterion("CREATE_STAFF_ID <>", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdGreaterThan(Integer value) {
            addCriterion("CREATE_STAFF_ID >", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("CREATE_STAFF_ID >=", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdLessThan(Integer value) {
            addCriterion("CREATE_STAFF_ID <", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdLessThanOrEqualTo(Integer value) {
            addCriterion("CREATE_STAFF_ID <=", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdIn(List<Integer> values) {
            addCriterion("CREATE_STAFF_ID in", values, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdNotIn(List<Integer> values) {
            addCriterion("CREATE_STAFF_ID not in", values, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdBetween(Integer value1, Integer value2) {
            addCriterion("CREATE_STAFF_ID between", value1, value2, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdNotBetween(Integer value1, Integer value2) {
            addCriterion("CREATE_STAFF_ID not between", value1, value2, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andModifyStaffIdIsNull() {
            addCriterion("MODIFY_STAFF_ID is null");
            return (Criteria) this;
        }

        public Criteria andModifyStaffIdIsNotNull() {
            addCriterion("MODIFY_STAFF_ID is not null");
            return (Criteria) this;
        }

        public Criteria andModifyStaffIdEqualTo(Integer value) {
            addCriterion("MODIFY_STAFF_ID =", value, "modifyStaffId");
            return (Criteria) this;
        }

        public Criteria andModifyStaffIdNotEqualTo(Integer value) {
            addCriterion("MODIFY_STAFF_ID <>", value, "modifyStaffId");
            return (Criteria) this;
        }

        public Criteria andModifyStaffIdGreaterThan(Integer value) {
            addCriterion("MODIFY_STAFF_ID >", value, "modifyStaffId");
            return (Criteria) this;
        }

        public Criteria andModifyStaffIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("MODIFY_STAFF_ID >=", value, "modifyStaffId");
            return (Criteria) this;
        }

        public Criteria andModifyStaffIdLessThan(Integer value) {
            addCriterion("MODIFY_STAFF_ID <", value, "modifyStaffId");
            return (Criteria) this;
        }

        public Criteria andModifyStaffIdLessThanOrEqualTo(Integer value) {
            addCriterion("MODIFY_STAFF_ID <=", value, "modifyStaffId");
            return (Criteria) this;
        }

        public Criteria andModifyStaffIdIn(List<Integer> values) {
            addCriterion("MODIFY_STAFF_ID in", values, "modifyStaffId");
            return (Criteria) this;
        }

        public Criteria andModifyStaffIdNotIn(List<Integer> values) {
            addCriterion("MODIFY_STAFF_ID not in", values, "modifyStaffId");
            return (Criteria) this;
        }

        public Criteria andModifyStaffIdBetween(Integer value1, Integer value2) {
            addCriterion("MODIFY_STAFF_ID between", value1, value2, "modifyStaffId");
            return (Criteria) this;
        }

        public Criteria andModifyStaffIdNotBetween(Integer value1, Integer value2) {
            addCriterion("MODIFY_STAFF_ID not between", value1, value2, "modifyStaffId");
            return (Criteria) this;
        }

        public Criteria andCertTypeIsNull() {
            addCriterion("CERT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCertTypeIsNotNull() {
            addCriterion("CERT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCertTypeEqualTo(String value) {
            addCriterion("CERT_TYPE =", value, "certType");
            return (Criteria) this;
        }

        public Criteria andCertTypeNotEqualTo(String value) {
            addCriterion("CERT_TYPE <>", value, "certType");
            return (Criteria) this;
        }

        public Criteria andCertTypeGreaterThan(String value) {
            addCriterion("CERT_TYPE >", value, "certType");
            return (Criteria) this;
        }

        public Criteria andCertTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CERT_TYPE >=", value, "certType");
            return (Criteria) this;
        }

        public Criteria andCertTypeLessThan(String value) {
            addCriterion("CERT_TYPE <", value, "certType");
            return (Criteria) this;
        }

        public Criteria andCertTypeLessThanOrEqualTo(String value) {
            addCriterion("CERT_TYPE <=", value, "certType");
            return (Criteria) this;
        }

        public Criteria andCertTypeLike(String value) {
            addCriterion("CERT_TYPE like", value, "certType");
            return (Criteria) this;
        }

        public Criteria andCertTypeNotLike(String value) {
            addCriterion("CERT_TYPE not like", value, "certType");
            return (Criteria) this;
        }

        public Criteria andCertTypeIn(List<String> values) {
            addCriterion("CERT_TYPE in", values, "certType");
            return (Criteria) this;
        }

        public Criteria andCertTypeNotIn(List<String> values) {
            addCriterion("CERT_TYPE not in", values, "certType");
            return (Criteria) this;
        }

        public Criteria andCertTypeBetween(String value1, String value2) {
            addCriterion("CERT_TYPE between", value1, value2, "certType");
            return (Criteria) this;
        }

        public Criteria andCertTypeNotBetween(String value1, String value2) {
            addCriterion("CERT_TYPE not between", value1, value2, "certType");
            return (Criteria) this;
        }

        public Criteria andCertNbrIsNull() {
            addCriterion("CERT_NBR is null");
            return (Criteria) this;
        }

        public Criteria andCertNbrIsNotNull() {
            addCriterion("CERT_NBR is not null");
            return (Criteria) this;
        }

        public Criteria andCertNbrEqualTo(String value) {
            addCriterion("CERT_NBR =", value, "certNbr");
            return (Criteria) this;
        }

        public Criteria andCertNbrNotEqualTo(String value) {
            addCriterion("CERT_NBR <>", value, "certNbr");
            return (Criteria) this;
        }

        public Criteria andCertNbrGreaterThan(String value) {
            addCriterion("CERT_NBR >", value, "certNbr");
            return (Criteria) this;
        }

        public Criteria andCertNbrGreaterThanOrEqualTo(String value) {
            addCriterion("CERT_NBR >=", value, "certNbr");
            return (Criteria) this;
        }

        public Criteria andCertNbrLessThan(String value) {
            addCriterion("CERT_NBR <", value, "certNbr");
            return (Criteria) this;
        }

        public Criteria andCertNbrLessThanOrEqualTo(String value) {
            addCriterion("CERT_NBR <=", value, "certNbr");
            return (Criteria) this;
        }

        public Criteria andCertNbrLike(String value) {
            addCriterion("CERT_NBR like", value, "certNbr");
            return (Criteria) this;
        }

        public Criteria andCertNbrNotLike(String value) {
            addCriterion("CERT_NBR not like", value, "certNbr");
            return (Criteria) this;
        }

        public Criteria andCertNbrIn(List<String> values) {
            addCriterion("CERT_NBR in", values, "certNbr");
            return (Criteria) this;
        }

        public Criteria andCertNbrNotIn(List<String> values) {
            addCriterion("CERT_NBR not in", values, "certNbr");
            return (Criteria) this;
        }

        public Criteria andCertNbrBetween(String value1, String value2) {
            addCriterion("CERT_NBR between", value1, value2, "certNbr");
            return (Criteria) this;
        }

        public Criteria andCertNbrNotBetween(String value1, String value2) {
            addCriterion("CERT_NBR not between", value1, value2, "certNbr");
            return (Criteria) this;
        }

        public Criteria andSexTypeIsNull() {
            addCriterion("SEX_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSexTypeIsNotNull() {
            addCriterion("SEX_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSexTypeEqualTo(String value) {
            addCriterion("SEX_TYPE =", value, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeNotEqualTo(String value) {
            addCriterion("SEX_TYPE <>", value, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeGreaterThan(String value) {
            addCriterion("SEX_TYPE >", value, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SEX_TYPE >=", value, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeLessThan(String value) {
            addCriterion("SEX_TYPE <", value, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeLessThanOrEqualTo(String value) {
            addCriterion("SEX_TYPE <=", value, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeLike(String value) {
            addCriterion("SEX_TYPE like", value, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeNotLike(String value) {
            addCriterion("SEX_TYPE not like", value, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeIn(List<String> values) {
            addCriterion("SEX_TYPE in", values, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeNotIn(List<String> values) {
            addCriterion("SEX_TYPE not in", values, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeBetween(String value1, String value2) {
            addCriterion("SEX_TYPE between", value1, value2, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeNotBetween(String value1, String value2) {
            addCriterion("SEX_TYPE not between", value1, value2, "sexType");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("BIRTHDAY is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("BIRTHDAY is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(Date value) {
            addCriterionForJDBCDate("BIRTHDAY =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(Date value) {
            addCriterionForJDBCDate("BIRTHDAY <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(Date value) {
            addCriterionForJDBCDate("BIRTHDAY >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("BIRTHDAY >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(Date value) {
            addCriterionForJDBCDate("BIRTHDAY <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("BIRTHDAY <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<Date> values) {
            addCriterionForJDBCDate("BIRTHDAY in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<Date> values) {
            addCriterionForJDBCDate("BIRTHDAY not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("BIRTHDAY between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("BIRTHDAY not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andLoginErrorCountIsNull() {
            addCriterion("LOGIN_ERROR_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andLoginErrorCountIsNotNull() {
            addCriterion("LOGIN_ERROR_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andLoginErrorCountEqualTo(Integer value) {
            addCriterion("LOGIN_ERROR_COUNT =", value, "loginErrorCount");
            return (Criteria) this;
        }

        public Criteria andLoginErrorCountNotEqualTo(Integer value) {
            addCriterion("LOGIN_ERROR_COUNT <>", value, "loginErrorCount");
            return (Criteria) this;
        }

        public Criteria andLoginErrorCountGreaterThan(Integer value) {
            addCriterion("LOGIN_ERROR_COUNT >", value, "loginErrorCount");
            return (Criteria) this;
        }

        public Criteria andLoginErrorCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("LOGIN_ERROR_COUNT >=", value, "loginErrorCount");
            return (Criteria) this;
        }

        public Criteria andLoginErrorCountLessThan(Integer value) {
            addCriterion("LOGIN_ERROR_COUNT <", value, "loginErrorCount");
            return (Criteria) this;
        }

        public Criteria andLoginErrorCountLessThanOrEqualTo(Integer value) {
            addCriterion("LOGIN_ERROR_COUNT <=", value, "loginErrorCount");
            return (Criteria) this;
        }

        public Criteria andLoginErrorCountIn(List<Integer> values) {
            addCriterion("LOGIN_ERROR_COUNT in", values, "loginErrorCount");
            return (Criteria) this;
        }

        public Criteria andLoginErrorCountNotIn(List<Integer> values) {
            addCriterion("LOGIN_ERROR_COUNT not in", values, "loginErrorCount");
            return (Criteria) this;
        }

        public Criteria andLoginErrorCountBetween(Integer value1, Integer value2) {
            addCriterion("LOGIN_ERROR_COUNT between", value1, value2, "loginErrorCount");
            return (Criteria) this;
        }

        public Criteria andLoginErrorCountNotBetween(Integer value1, Integer value2) {
            addCriterion("LOGIN_ERROR_COUNT not between", value1, value2, "loginErrorCount");
            return (Criteria) this;
        }

        public Criteria andSubordinateStaffIdsIsNull() {
            addCriterion("SUBORDINATE_STAFF_IDS is null");
            return (Criteria) this;
        }

        public Criteria andSubordinateStaffIdsIsNotNull() {
            addCriterion("SUBORDINATE_STAFF_IDS is not null");
            return (Criteria) this;
        }

        public Criteria andSubordinateStaffIdsEqualTo(String value) {
            addCriterion("SUBORDINATE_STAFF_IDS =", value, "subordinateStaffIds");
            return (Criteria) this;
        }

        public Criteria andSubordinateStaffIdsNotEqualTo(String value) {
            addCriterion("SUBORDINATE_STAFF_IDS <>", value, "subordinateStaffIds");
            return (Criteria) this;
        }

        public Criteria andSubordinateStaffIdsGreaterThan(String value) {
            addCriterion("SUBORDINATE_STAFF_IDS >", value, "subordinateStaffIds");
            return (Criteria) this;
        }

        public Criteria andSubordinateStaffIdsGreaterThanOrEqualTo(String value) {
            addCriterion("SUBORDINATE_STAFF_IDS >=", value, "subordinateStaffIds");
            return (Criteria) this;
        }

        public Criteria andSubordinateStaffIdsLessThan(String value) {
            addCriterion("SUBORDINATE_STAFF_IDS <", value, "subordinateStaffIds");
            return (Criteria) this;
        }

        public Criteria andSubordinateStaffIdsLessThanOrEqualTo(String value) {
            addCriterion("SUBORDINATE_STAFF_IDS <=", value, "subordinateStaffIds");
            return (Criteria) this;
        }

        public Criteria andSubordinateStaffIdsLike(String value) {
            addCriterion("SUBORDINATE_STAFF_IDS like", value, "subordinateStaffIds");
            return (Criteria) this;
        }

        public Criteria andSubordinateStaffIdsNotLike(String value) {
            addCriterion("SUBORDINATE_STAFF_IDS not like", value, "subordinateStaffIds");
            return (Criteria) this;
        }

        public Criteria andSubordinateStaffIdsIn(List<String> values) {
            addCriterion("SUBORDINATE_STAFF_IDS in", values, "subordinateStaffIds");
            return (Criteria) this;
        }

        public Criteria andSubordinateStaffIdsNotIn(List<String> values) {
            addCriterion("SUBORDINATE_STAFF_IDS not in", values, "subordinateStaffIds");
            return (Criteria) this;
        }

        public Criteria andSubordinateStaffIdsBetween(String value1, String value2) {
            addCriterion("SUBORDINATE_STAFF_IDS between", value1, value2, "subordinateStaffIds");
            return (Criteria) this;
        }

        public Criteria andSubordinateStaffIdsNotBetween(String value1, String value2) {
            addCriterion("SUBORDINATE_STAFF_IDS not between", value1, value2, "subordinateStaffIds");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
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