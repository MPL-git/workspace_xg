package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ToutiaoAdvertiserInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public ToutiaoAdvertiserInfoExample() {
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

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andContacterIsNull() {
            addCriterion("contacter is null");
            return (Criteria) this;
        }

        public Criteria andContacterIsNotNull() {
            addCriterion("contacter is not null");
            return (Criteria) this;
        }

        public Criteria andContacterEqualTo(String value) {
            addCriterion("contacter =", value, "contacter");
            return (Criteria) this;
        }

        public Criteria andContacterNotEqualTo(String value) {
            addCriterion("contacter <>", value, "contacter");
            return (Criteria) this;
        }

        public Criteria andContacterGreaterThan(String value) {
            addCriterion("contacter >", value, "contacter");
            return (Criteria) this;
        }

        public Criteria andContacterGreaterThanOrEqualTo(String value) {
            addCriterion("contacter >=", value, "contacter");
            return (Criteria) this;
        }

        public Criteria andContacterLessThan(String value) {
            addCriterion("contacter <", value, "contacter");
            return (Criteria) this;
        }

        public Criteria andContacterLessThanOrEqualTo(String value) {
            addCriterion("contacter <=", value, "contacter");
            return (Criteria) this;
        }

        public Criteria andContacterLike(String value) {
            addCriterion("contacter like", value, "contacter");
            return (Criteria) this;
        }

        public Criteria andContacterNotLike(String value) {
            addCriterion("contacter not like", value, "contacter");
            return (Criteria) this;
        }

        public Criteria andContacterIn(List<String> values) {
            addCriterion("contacter in", values, "contacter");
            return (Criteria) this;
        }

        public Criteria andContacterNotIn(List<String> values) {
            addCriterion("contacter not in", values, "contacter");
            return (Criteria) this;
        }

        public Criteria andContacterBetween(String value1, String value2) {
            addCriterion("contacter between", value1, value2, "contacter");
            return (Criteria) this;
        }

        public Criteria andContacterNotBetween(String value1, String value2) {
            addCriterion("contacter not between", value1, value2, "contacter");
            return (Criteria) this;
        }

        public Criteria andPhonenumberIsNull() {
            addCriterion("phonenumber is null");
            return (Criteria) this;
        }

        public Criteria andPhonenumberIsNotNull() {
            addCriterion("phonenumber is not null");
            return (Criteria) this;
        }

        public Criteria andPhonenumberEqualTo(String value) {
            addCriterion("phonenumber =", value, "phonenumber");
            return (Criteria) this;
        }

        public Criteria andPhonenumberNotEqualTo(String value) {
            addCriterion("phonenumber <>", value, "phonenumber");
            return (Criteria) this;
        }

        public Criteria andPhonenumberGreaterThan(String value) {
            addCriterion("phonenumber >", value, "phonenumber");
            return (Criteria) this;
        }

        public Criteria andPhonenumberGreaterThanOrEqualTo(String value) {
            addCriterion("phonenumber >=", value, "phonenumber");
            return (Criteria) this;
        }

        public Criteria andPhonenumberLessThan(String value) {
            addCriterion("phonenumber <", value, "phonenumber");
            return (Criteria) this;
        }

        public Criteria andPhonenumberLessThanOrEqualTo(String value) {
            addCriterion("phonenumber <=", value, "phonenumber");
            return (Criteria) this;
        }

        public Criteria andPhonenumberLike(String value) {
            addCriterion("phonenumber like", value, "phonenumber");
            return (Criteria) this;
        }

        public Criteria andPhonenumberNotLike(String value) {
            addCriterion("phonenumber not like", value, "phonenumber");
            return (Criteria) this;
        }

        public Criteria andPhonenumberIn(List<String> values) {
            addCriterion("phonenumber in", values, "phonenumber");
            return (Criteria) this;
        }

        public Criteria andPhonenumberNotIn(List<String> values) {
            addCriterion("phonenumber not in", values, "phonenumber");
            return (Criteria) this;
        }

        public Criteria andPhonenumberBetween(String value1, String value2) {
            addCriterion("phonenumber between", value1, value2, "phonenumber");
            return (Criteria) this;
        }

        public Criteria andPhonenumberNotBetween(String value1, String value2) {
            addCriterion("phonenumber not between", value1, value2, "phonenumber");
            return (Criteria) this;
        }

        public Criteria andRoleIsNull() {
            addCriterion("role is null");
            return (Criteria) this;
        }

        public Criteria andRoleIsNotNull() {
            addCriterion("role is not null");
            return (Criteria) this;
        }

        public Criteria andRoleEqualTo(String value) {
            addCriterion("role =", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotEqualTo(String value) {
            addCriterion("role <>", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleGreaterThan(String value) {
            addCriterion("role >", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleGreaterThanOrEqualTo(String value) {
            addCriterion("role >=", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleLessThan(String value) {
            addCriterion("role <", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleLessThanOrEqualTo(String value) {
            addCriterion("role <=", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleLike(String value) {
            addCriterion("role like", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotLike(String value) {
            addCriterion("role not like", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleIn(List<String> values) {
            addCriterion("role in", values, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotIn(List<String> values) {
            addCriterion("role not in", values, "role");
            return (Criteria) this;
        }

        public Criteria andRoleBetween(String value1, String value2) {
            addCriterion("role between", value1, value2, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotBetween(String value1, String value2) {
            addCriterion("role not between", value1, value2, "role");
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

        public Criteria andTelephoneIsNull() {
            addCriterion("telephone is null");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNotNull() {
            addCriterion("telephone is not null");
            return (Criteria) this;
        }

        public Criteria andTelephoneEqualTo(String value) {
            addCriterion("telephone =", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotEqualTo(String value) {
            addCriterion("telephone <>", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThan(String value) {
            addCriterion("telephone >", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("telephone >=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThan(String value) {
            addCriterion("telephone <", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThanOrEqualTo(String value) {
            addCriterion("telephone <=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLike(String value) {
            addCriterion("telephone like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotLike(String value) {
            addCriterion("telephone not like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneIn(List<String> values) {
            addCriterion("telephone in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotIn(List<String> values) {
            addCriterion("telephone not in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneBetween(String value1, String value2) {
            addCriterion("telephone between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotBetween(String value1, String value2) {
            addCriterion("telephone not between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlIsNull() {
            addCriterion("license_url is null");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlIsNotNull() {
            addCriterion("license_url is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlEqualTo(String value) {
            addCriterion("license_url =", value, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlNotEqualTo(String value) {
            addCriterion("license_url <>", value, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlGreaterThan(String value) {
            addCriterion("license_url >", value, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlGreaterThanOrEqualTo(String value) {
            addCriterion("license_url >=", value, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlLessThan(String value) {
            addCriterion("license_url <", value, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlLessThanOrEqualTo(String value) {
            addCriterion("license_url <=", value, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlLike(String value) {
            addCriterion("license_url like", value, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlNotLike(String value) {
            addCriterion("license_url not like", value, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlIn(List<String> values) {
            addCriterion("license_url in", values, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlNotIn(List<String> values) {
            addCriterion("license_url not in", values, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlBetween(String value1, String value2) {
            addCriterion("license_url between", value1, value2, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlNotBetween(String value1, String value2) {
            addCriterion("license_url not between", value1, value2, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseNoIsNull() {
            addCriterion("license_no is null");
            return (Criteria) this;
        }

        public Criteria andLicenseNoIsNotNull() {
            addCriterion("license_no is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseNoEqualTo(String value) {
            addCriterion("license_no =", value, "licenseNo");
            return (Criteria) this;
        }

        public Criteria andLicenseNoNotEqualTo(String value) {
            addCriterion("license_no <>", value, "licenseNo");
            return (Criteria) this;
        }

        public Criteria andLicenseNoGreaterThan(String value) {
            addCriterion("license_no >", value, "licenseNo");
            return (Criteria) this;
        }

        public Criteria andLicenseNoGreaterThanOrEqualTo(String value) {
            addCriterion("license_no >=", value, "licenseNo");
            return (Criteria) this;
        }

        public Criteria andLicenseNoLessThan(String value) {
            addCriterion("license_no <", value, "licenseNo");
            return (Criteria) this;
        }

        public Criteria andLicenseNoLessThanOrEqualTo(String value) {
            addCriterion("license_no <=", value, "licenseNo");
            return (Criteria) this;
        }

        public Criteria andLicenseNoLike(String value) {
            addCriterion("license_no like", value, "licenseNo");
            return (Criteria) this;
        }

        public Criteria andLicenseNoNotLike(String value) {
            addCriterion("license_no not like", value, "licenseNo");
            return (Criteria) this;
        }

        public Criteria andLicenseNoIn(List<String> values) {
            addCriterion("license_no in", values, "licenseNo");
            return (Criteria) this;
        }

        public Criteria andLicenseNoNotIn(List<String> values) {
            addCriterion("license_no not in", values, "licenseNo");
            return (Criteria) this;
        }

        public Criteria andLicenseNoBetween(String value1, String value2) {
            addCriterion("license_no between", value1, value2, "licenseNo");
            return (Criteria) this;
        }

        public Criteria andLicenseNoNotBetween(String value1, String value2) {
            addCriterion("license_no not between", value1, value2, "licenseNo");
            return (Criteria) this;
        }

        public Criteria andLicenseProvinceIsNull() {
            addCriterion("license_province is null");
            return (Criteria) this;
        }

        public Criteria andLicenseProvinceIsNotNull() {
            addCriterion("license_province is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseProvinceEqualTo(String value) {
            addCriterion("license_province =", value, "licenseProvince");
            return (Criteria) this;
        }

        public Criteria andLicenseProvinceNotEqualTo(String value) {
            addCriterion("license_province <>", value, "licenseProvince");
            return (Criteria) this;
        }

        public Criteria andLicenseProvinceGreaterThan(String value) {
            addCriterion("license_province >", value, "licenseProvince");
            return (Criteria) this;
        }

        public Criteria andLicenseProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("license_province >=", value, "licenseProvince");
            return (Criteria) this;
        }

        public Criteria andLicenseProvinceLessThan(String value) {
            addCriterion("license_province <", value, "licenseProvince");
            return (Criteria) this;
        }

        public Criteria andLicenseProvinceLessThanOrEqualTo(String value) {
            addCriterion("license_province <=", value, "licenseProvince");
            return (Criteria) this;
        }

        public Criteria andLicenseProvinceLike(String value) {
            addCriterion("license_province like", value, "licenseProvince");
            return (Criteria) this;
        }

        public Criteria andLicenseProvinceNotLike(String value) {
            addCriterion("license_province not like", value, "licenseProvince");
            return (Criteria) this;
        }

        public Criteria andLicenseProvinceIn(List<String> values) {
            addCriterion("license_province in", values, "licenseProvince");
            return (Criteria) this;
        }

        public Criteria andLicenseProvinceNotIn(List<String> values) {
            addCriterion("license_province not in", values, "licenseProvince");
            return (Criteria) this;
        }

        public Criteria andLicenseProvinceBetween(String value1, String value2) {
            addCriterion("license_province between", value1, value2, "licenseProvince");
            return (Criteria) this;
        }

        public Criteria andLicenseProvinceNotBetween(String value1, String value2) {
            addCriterion("license_province not between", value1, value2, "licenseProvince");
            return (Criteria) this;
        }

        public Criteria andLicenseCityIsNull() {
            addCriterion("license_city is null");
            return (Criteria) this;
        }

        public Criteria andLicenseCityIsNotNull() {
            addCriterion("license_city is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseCityEqualTo(String value) {
            addCriterion("license_city =", value, "licenseCity");
            return (Criteria) this;
        }

        public Criteria andLicenseCityNotEqualTo(String value) {
            addCriterion("license_city <>", value, "licenseCity");
            return (Criteria) this;
        }

        public Criteria andLicenseCityGreaterThan(String value) {
            addCriterion("license_city >", value, "licenseCity");
            return (Criteria) this;
        }

        public Criteria andLicenseCityGreaterThanOrEqualTo(String value) {
            addCriterion("license_city >=", value, "licenseCity");
            return (Criteria) this;
        }

        public Criteria andLicenseCityLessThan(String value) {
            addCriterion("license_city <", value, "licenseCity");
            return (Criteria) this;
        }

        public Criteria andLicenseCityLessThanOrEqualTo(String value) {
            addCriterion("license_city <=", value, "licenseCity");
            return (Criteria) this;
        }

        public Criteria andLicenseCityLike(String value) {
            addCriterion("license_city like", value, "licenseCity");
            return (Criteria) this;
        }

        public Criteria andLicenseCityNotLike(String value) {
            addCriterion("license_city not like", value, "licenseCity");
            return (Criteria) this;
        }

        public Criteria andLicenseCityIn(List<String> values) {
            addCriterion("license_city in", values, "licenseCity");
            return (Criteria) this;
        }

        public Criteria andLicenseCityNotIn(List<String> values) {
            addCriterion("license_city not in", values, "licenseCity");
            return (Criteria) this;
        }

        public Criteria andLicenseCityBetween(String value1, String value2) {
            addCriterion("license_city between", value1, value2, "licenseCity");
            return (Criteria) this;
        }

        public Criteria andLicenseCityNotBetween(String value1, String value2) {
            addCriterion("license_city not between", value1, value2, "licenseCity");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNull() {
            addCriterion("company is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNotNull() {
            addCriterion("company is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyEqualTo(String value) {
            addCriterion("company =", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotEqualTo(String value) {
            addCriterion("company <>", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThan(String value) {
            addCriterion("company >", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("company >=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThan(String value) {
            addCriterion("company <", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThanOrEqualTo(String value) {
            addCriterion("company <=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLike(String value) {
            addCriterion("company like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotLike(String value) {
            addCriterion("company not like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyIn(List<String> values) {
            addCriterion("company in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotIn(List<String> values) {
            addCriterion("company not in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyBetween(String value1, String value2) {
            addCriterion("company between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotBetween(String value1, String value2) {
            addCriterion("company not between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andBrandIsNull() {
            addCriterion("brand is null");
            return (Criteria) this;
        }

        public Criteria andBrandIsNotNull() {
            addCriterion("brand is not null");
            return (Criteria) this;
        }

        public Criteria andBrandEqualTo(String value) {
            addCriterion("brand =", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotEqualTo(String value) {
            addCriterion("brand <>", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThan(String value) {
            addCriterion("brand >", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThanOrEqualTo(String value) {
            addCriterion("brand >=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThan(String value) {
            addCriterion("brand <", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThanOrEqualTo(String value) {
            addCriterion("brand <=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLike(String value) {
            addCriterion("brand like", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotLike(String value) {
            addCriterion("brand not like", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandIn(List<String> values) {
            addCriterion("brand in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotIn(List<String> values) {
            addCriterion("brand not in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandBetween(String value1, String value2) {
            addCriterion("brand between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotBetween(String value1, String value2) {
            addCriterion("brand not between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andPromotionAreaIsNull() {
            addCriterion("promotion_area is null");
            return (Criteria) this;
        }

        public Criteria andPromotionAreaIsNotNull() {
            addCriterion("promotion_area is not null");
            return (Criteria) this;
        }

        public Criteria andPromotionAreaEqualTo(String value) {
            addCriterion("promotion_area =", value, "promotionArea");
            return (Criteria) this;
        }

        public Criteria andPromotionAreaNotEqualTo(String value) {
            addCriterion("promotion_area <>", value, "promotionArea");
            return (Criteria) this;
        }

        public Criteria andPromotionAreaGreaterThan(String value) {
            addCriterion("promotion_area >", value, "promotionArea");
            return (Criteria) this;
        }

        public Criteria andPromotionAreaGreaterThanOrEqualTo(String value) {
            addCriterion("promotion_area >=", value, "promotionArea");
            return (Criteria) this;
        }

        public Criteria andPromotionAreaLessThan(String value) {
            addCriterion("promotion_area <", value, "promotionArea");
            return (Criteria) this;
        }

        public Criteria andPromotionAreaLessThanOrEqualTo(String value) {
            addCriterion("promotion_area <=", value, "promotionArea");
            return (Criteria) this;
        }

        public Criteria andPromotionAreaLike(String value) {
            addCriterion("promotion_area like", value, "promotionArea");
            return (Criteria) this;
        }

        public Criteria andPromotionAreaNotLike(String value) {
            addCriterion("promotion_area not like", value, "promotionArea");
            return (Criteria) this;
        }

        public Criteria andPromotionAreaIn(List<String> values) {
            addCriterion("promotion_area in", values, "promotionArea");
            return (Criteria) this;
        }

        public Criteria andPromotionAreaNotIn(List<String> values) {
            addCriterion("promotion_area not in", values, "promotionArea");
            return (Criteria) this;
        }

        public Criteria andPromotionAreaBetween(String value1, String value2) {
            addCriterion("promotion_area between", value1, value2, "promotionArea");
            return (Criteria) this;
        }

        public Criteria andPromotionAreaNotBetween(String value1, String value2) {
            addCriterion("promotion_area not between", value1, value2, "promotionArea");
            return (Criteria) this;
        }

        public Criteria andPromotionCenterProvinceIsNull() {
            addCriterion("promotion_center_province is null");
            return (Criteria) this;
        }

        public Criteria andPromotionCenterProvinceIsNotNull() {
            addCriterion("promotion_center_province is not null");
            return (Criteria) this;
        }

        public Criteria andPromotionCenterProvinceEqualTo(String value) {
            addCriterion("promotion_center_province =", value, "promotionCenterProvince");
            return (Criteria) this;
        }

        public Criteria andPromotionCenterProvinceNotEqualTo(String value) {
            addCriterion("promotion_center_province <>", value, "promotionCenterProvince");
            return (Criteria) this;
        }

        public Criteria andPromotionCenterProvinceGreaterThan(String value) {
            addCriterion("promotion_center_province >", value, "promotionCenterProvince");
            return (Criteria) this;
        }

        public Criteria andPromotionCenterProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("promotion_center_province >=", value, "promotionCenterProvince");
            return (Criteria) this;
        }

        public Criteria andPromotionCenterProvinceLessThan(String value) {
            addCriterion("promotion_center_province <", value, "promotionCenterProvince");
            return (Criteria) this;
        }

        public Criteria andPromotionCenterProvinceLessThanOrEqualTo(String value) {
            addCriterion("promotion_center_province <=", value, "promotionCenterProvince");
            return (Criteria) this;
        }

        public Criteria andPromotionCenterProvinceLike(String value) {
            addCriterion("promotion_center_province like", value, "promotionCenterProvince");
            return (Criteria) this;
        }

        public Criteria andPromotionCenterProvinceNotLike(String value) {
            addCriterion("promotion_center_province not like", value, "promotionCenterProvince");
            return (Criteria) this;
        }

        public Criteria andPromotionCenterProvinceIn(List<String> values) {
            addCriterion("promotion_center_province in", values, "promotionCenterProvince");
            return (Criteria) this;
        }

        public Criteria andPromotionCenterProvinceNotIn(List<String> values) {
            addCriterion("promotion_center_province not in", values, "promotionCenterProvince");
            return (Criteria) this;
        }

        public Criteria andPromotionCenterProvinceBetween(String value1, String value2) {
            addCriterion("promotion_center_province between", value1, value2, "promotionCenterProvince");
            return (Criteria) this;
        }

        public Criteria andPromotionCenterProvinceNotBetween(String value1, String value2) {
            addCriterion("promotion_center_province not between", value1, value2, "promotionCenterProvince");
            return (Criteria) this;
        }

        public Criteria andPromotionCenterCityIsNull() {
            addCriterion("promotion_center_city is null");
            return (Criteria) this;
        }

        public Criteria andPromotionCenterCityIsNotNull() {
            addCriterion("promotion_center_city is not null");
            return (Criteria) this;
        }

        public Criteria andPromotionCenterCityEqualTo(String value) {
            addCriterion("promotion_center_city =", value, "promotionCenterCity");
            return (Criteria) this;
        }

        public Criteria andPromotionCenterCityNotEqualTo(String value) {
            addCriterion("promotion_center_city <>", value, "promotionCenterCity");
            return (Criteria) this;
        }

        public Criteria andPromotionCenterCityGreaterThan(String value) {
            addCriterion("promotion_center_city >", value, "promotionCenterCity");
            return (Criteria) this;
        }

        public Criteria andPromotionCenterCityGreaterThanOrEqualTo(String value) {
            addCriterion("promotion_center_city >=", value, "promotionCenterCity");
            return (Criteria) this;
        }

        public Criteria andPromotionCenterCityLessThan(String value) {
            addCriterion("promotion_center_city <", value, "promotionCenterCity");
            return (Criteria) this;
        }

        public Criteria andPromotionCenterCityLessThanOrEqualTo(String value) {
            addCriterion("promotion_center_city <=", value, "promotionCenterCity");
            return (Criteria) this;
        }

        public Criteria andPromotionCenterCityLike(String value) {
            addCriterion("promotion_center_city like", value, "promotionCenterCity");
            return (Criteria) this;
        }

        public Criteria andPromotionCenterCityNotLike(String value) {
            addCriterion("promotion_center_city not like", value, "promotionCenterCity");
            return (Criteria) this;
        }

        public Criteria andPromotionCenterCityIn(List<String> values) {
            addCriterion("promotion_center_city in", values, "promotionCenterCity");
            return (Criteria) this;
        }

        public Criteria andPromotionCenterCityNotIn(List<String> values) {
            addCriterion("promotion_center_city not in", values, "promotionCenterCity");
            return (Criteria) this;
        }

        public Criteria andPromotionCenterCityBetween(String value1, String value2) {
            addCriterion("promotion_center_city between", value1, value2, "promotionCenterCity");
            return (Criteria) this;
        }

        public Criteria andPromotionCenterCityNotBetween(String value1, String value2) {
            addCriterion("promotion_center_city not between", value1, value2, "promotionCenterCity");
            return (Criteria) this;
        }

        public Criteria andIndustryIsNull() {
            addCriterion("industry is null");
            return (Criteria) this;
        }

        public Criteria andIndustryIsNotNull() {
            addCriterion("industry is not null");
            return (Criteria) this;
        }

        public Criteria andIndustryEqualTo(String value) {
            addCriterion("industry =", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotEqualTo(String value) {
            addCriterion("industry <>", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryGreaterThan(String value) {
            addCriterion("industry >", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryGreaterThanOrEqualTo(String value) {
            addCriterion("industry >=", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryLessThan(String value) {
            addCriterion("industry <", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryLessThanOrEqualTo(String value) {
            addCriterion("industry <=", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryLike(String value) {
            addCriterion("industry like", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotLike(String value) {
            addCriterion("industry not like", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryIn(List<String> values) {
            addCriterion("industry in", values, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotIn(List<String> values) {
            addCriterion("industry not in", values, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryBetween(String value1, String value2) {
            addCriterion("industry between", value1, value2, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotBetween(String value1, String value2) {
            addCriterion("industry not between", value1, value2, "industry");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("reason not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("reason not between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNull() {
            addCriterion("balance is null");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNotNull() {
            addCriterion("balance is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceEqualTo(BigDecimal value) {
            addCriterion("balance =", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotEqualTo(BigDecimal value) {
            addCriterion("balance <>", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThan(BigDecimal value) {
            addCriterion("balance >", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("balance >=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThan(BigDecimal value) {
            addCriterion("balance <", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("balance <=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceIn(List<BigDecimal> values) {
            addCriterion("balance in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotIn(List<BigDecimal> values) {
            addCriterion("balance not in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance not between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andCampaignPageIsNull() {
            addCriterion("campaign_page is null");
            return (Criteria) this;
        }

        public Criteria andCampaignPageIsNotNull() {
            addCriterion("campaign_page is not null");
            return (Criteria) this;
        }

        public Criteria andCampaignPageEqualTo(Integer value) {
            addCriterion("campaign_page =", value, "campaignPage");
            return (Criteria) this;
        }

        public Criteria andCampaignPageNotEqualTo(Integer value) {
            addCriterion("campaign_page <>", value, "campaignPage");
            return (Criteria) this;
        }

        public Criteria andCampaignPageGreaterThan(Integer value) {
            addCriterion("campaign_page >", value, "campaignPage");
            return (Criteria) this;
        }

        public Criteria andCampaignPageGreaterThanOrEqualTo(Integer value) {
            addCriterion("campaign_page >=", value, "campaignPage");
            return (Criteria) this;
        }

        public Criteria andCampaignPageLessThan(Integer value) {
            addCriterion("campaign_page <", value, "campaignPage");
            return (Criteria) this;
        }

        public Criteria andCampaignPageLessThanOrEqualTo(Integer value) {
            addCriterion("campaign_page <=", value, "campaignPage");
            return (Criteria) this;
        }

        public Criteria andCampaignPageIn(List<Integer> values) {
            addCriterion("campaign_page in", values, "campaignPage");
            return (Criteria) this;
        }

        public Criteria andCampaignPageNotIn(List<Integer> values) {
            addCriterion("campaign_page not in", values, "campaignPage");
            return (Criteria) this;
        }

        public Criteria andCampaignPageBetween(Integer value1, Integer value2) {
            addCriterion("campaign_page between", value1, value2, "campaignPage");
            return (Criteria) this;
        }

        public Criteria andCampaignPageNotBetween(Integer value1, Integer value2) {
            addCriterion("campaign_page not between", value1, value2, "campaignPage");
            return (Criteria) this;
        }

        public Criteria andCampaignPageSizeIsNull() {
            addCriterion("campaign_page_size is null");
            return (Criteria) this;
        }

        public Criteria andCampaignPageSizeIsNotNull() {
            addCriterion("campaign_page_size is not null");
            return (Criteria) this;
        }

        public Criteria andCampaignPageSizeEqualTo(Integer value) {
            addCriterion("campaign_page_size =", value, "campaignPageSize");
            return (Criteria) this;
        }

        public Criteria andCampaignPageSizeNotEqualTo(Integer value) {
            addCriterion("campaign_page_size <>", value, "campaignPageSize");
            return (Criteria) this;
        }

        public Criteria andCampaignPageSizeGreaterThan(Integer value) {
            addCriterion("campaign_page_size >", value, "campaignPageSize");
            return (Criteria) this;
        }

        public Criteria andCampaignPageSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("campaign_page_size >=", value, "campaignPageSize");
            return (Criteria) this;
        }

        public Criteria andCampaignPageSizeLessThan(Integer value) {
            addCriterion("campaign_page_size <", value, "campaignPageSize");
            return (Criteria) this;
        }

        public Criteria andCampaignPageSizeLessThanOrEqualTo(Integer value) {
            addCriterion("campaign_page_size <=", value, "campaignPageSize");
            return (Criteria) this;
        }

        public Criteria andCampaignPageSizeIn(List<Integer> values) {
            addCriterion("campaign_page_size in", values, "campaignPageSize");
            return (Criteria) this;
        }

        public Criteria andCampaignPageSizeNotIn(List<Integer> values) {
            addCriterion("campaign_page_size not in", values, "campaignPageSize");
            return (Criteria) this;
        }

        public Criteria andCampaignPageSizeBetween(Integer value1, Integer value2) {
            addCriterion("campaign_page_size between", value1, value2, "campaignPageSize");
            return (Criteria) this;
        }

        public Criteria andCampaignPageSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("campaign_page_size not between", value1, value2, "campaignPageSize");
            return (Criteria) this;
        }

        public Criteria andCampaignTotalNumberIsNull() {
            addCriterion("campaign_total_number is null");
            return (Criteria) this;
        }

        public Criteria andCampaignTotalNumberIsNotNull() {
            addCriterion("campaign_total_number is not null");
            return (Criteria) this;
        }

        public Criteria andCampaignTotalNumberEqualTo(Integer value) {
            addCriterion("campaign_total_number =", value, "campaignTotalNumber");
            return (Criteria) this;
        }

        public Criteria andCampaignTotalNumberNotEqualTo(Integer value) {
            addCriterion("campaign_total_number <>", value, "campaignTotalNumber");
            return (Criteria) this;
        }

        public Criteria andCampaignTotalNumberGreaterThan(Integer value) {
            addCriterion("campaign_total_number >", value, "campaignTotalNumber");
            return (Criteria) this;
        }

        public Criteria andCampaignTotalNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("campaign_total_number >=", value, "campaignTotalNumber");
            return (Criteria) this;
        }

        public Criteria andCampaignTotalNumberLessThan(Integer value) {
            addCriterion("campaign_total_number <", value, "campaignTotalNumber");
            return (Criteria) this;
        }

        public Criteria andCampaignTotalNumberLessThanOrEqualTo(Integer value) {
            addCriterion("campaign_total_number <=", value, "campaignTotalNumber");
            return (Criteria) this;
        }

        public Criteria andCampaignTotalNumberIn(List<Integer> values) {
            addCriterion("campaign_total_number in", values, "campaignTotalNumber");
            return (Criteria) this;
        }

        public Criteria andCampaignTotalNumberNotIn(List<Integer> values) {
            addCriterion("campaign_total_number not in", values, "campaignTotalNumber");
            return (Criteria) this;
        }

        public Criteria andCampaignTotalNumberBetween(Integer value1, Integer value2) {
            addCriterion("campaign_total_number between", value1, value2, "campaignTotalNumber");
            return (Criteria) this;
        }

        public Criteria andCampaignTotalNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("campaign_total_number not between", value1, value2, "campaignTotalNumber");
            return (Criteria) this;
        }

        public Criteria andCampaignTotalPageIsNull() {
            addCriterion("campaign_total_page is null");
            return (Criteria) this;
        }

        public Criteria andCampaignTotalPageIsNotNull() {
            addCriterion("campaign_total_page is not null");
            return (Criteria) this;
        }

        public Criteria andCampaignTotalPageEqualTo(Integer value) {
            addCriterion("campaign_total_page =", value, "campaignTotalPage");
            return (Criteria) this;
        }

        public Criteria andCampaignTotalPageNotEqualTo(Integer value) {
            addCriterion("campaign_total_page <>", value, "campaignTotalPage");
            return (Criteria) this;
        }

        public Criteria andCampaignTotalPageGreaterThan(Integer value) {
            addCriterion("campaign_total_page >", value, "campaignTotalPage");
            return (Criteria) this;
        }

        public Criteria andCampaignTotalPageGreaterThanOrEqualTo(Integer value) {
            addCriterion("campaign_total_page >=", value, "campaignTotalPage");
            return (Criteria) this;
        }

        public Criteria andCampaignTotalPageLessThan(Integer value) {
            addCriterion("campaign_total_page <", value, "campaignTotalPage");
            return (Criteria) this;
        }

        public Criteria andCampaignTotalPageLessThanOrEqualTo(Integer value) {
            addCriterion("campaign_total_page <=", value, "campaignTotalPage");
            return (Criteria) this;
        }

        public Criteria andCampaignTotalPageIn(List<Integer> values) {
            addCriterion("campaign_total_page in", values, "campaignTotalPage");
            return (Criteria) this;
        }

        public Criteria andCampaignTotalPageNotIn(List<Integer> values) {
            addCriterion("campaign_total_page not in", values, "campaignTotalPage");
            return (Criteria) this;
        }

        public Criteria andCampaignTotalPageBetween(Integer value1, Integer value2) {
            addCriterion("campaign_total_page between", value1, value2, "campaignTotalPage");
            return (Criteria) this;
        }

        public Criteria andCampaignTotalPageNotBetween(Integer value1, Integer value2) {
            addCriterion("campaign_total_page not between", value1, value2, "campaignTotalPage");
            return (Criteria) this;
        }

        public Criteria andAdPageIsNull() {
            addCriterion("ad_page is null");
            return (Criteria) this;
        }

        public Criteria andAdPageIsNotNull() {
            addCriterion("ad_page is not null");
            return (Criteria) this;
        }

        public Criteria andAdPageEqualTo(Integer value) {
            addCriterion("ad_page =", value, "adPage");
            return (Criteria) this;
        }

        public Criteria andAdPageNotEqualTo(Integer value) {
            addCriterion("ad_page <>", value, "adPage");
            return (Criteria) this;
        }

        public Criteria andAdPageGreaterThan(Integer value) {
            addCriterion("ad_page >", value, "adPage");
            return (Criteria) this;
        }

        public Criteria andAdPageGreaterThanOrEqualTo(Integer value) {
            addCriterion("ad_page >=", value, "adPage");
            return (Criteria) this;
        }

        public Criteria andAdPageLessThan(Integer value) {
            addCriterion("ad_page <", value, "adPage");
            return (Criteria) this;
        }

        public Criteria andAdPageLessThanOrEqualTo(Integer value) {
            addCriterion("ad_page <=", value, "adPage");
            return (Criteria) this;
        }

        public Criteria andAdPageIn(List<Integer> values) {
            addCriterion("ad_page in", values, "adPage");
            return (Criteria) this;
        }

        public Criteria andAdPageNotIn(List<Integer> values) {
            addCriterion("ad_page not in", values, "adPage");
            return (Criteria) this;
        }

        public Criteria andAdPageBetween(Integer value1, Integer value2) {
            addCriterion("ad_page between", value1, value2, "adPage");
            return (Criteria) this;
        }

        public Criteria andAdPageNotBetween(Integer value1, Integer value2) {
            addCriterion("ad_page not between", value1, value2, "adPage");
            return (Criteria) this;
        }

        public Criteria andAdPageSizeIsNull() {
            addCriterion("ad_page_size is null");
            return (Criteria) this;
        }

        public Criteria andAdPageSizeIsNotNull() {
            addCriterion("ad_page_size is not null");
            return (Criteria) this;
        }

        public Criteria andAdPageSizeEqualTo(Integer value) {
            addCriterion("ad_page_size =", value, "adPageSize");
            return (Criteria) this;
        }

        public Criteria andAdPageSizeNotEqualTo(Integer value) {
            addCriterion("ad_page_size <>", value, "adPageSize");
            return (Criteria) this;
        }

        public Criteria andAdPageSizeGreaterThan(Integer value) {
            addCriterion("ad_page_size >", value, "adPageSize");
            return (Criteria) this;
        }

        public Criteria andAdPageSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ad_page_size >=", value, "adPageSize");
            return (Criteria) this;
        }

        public Criteria andAdPageSizeLessThan(Integer value) {
            addCriterion("ad_page_size <", value, "adPageSize");
            return (Criteria) this;
        }

        public Criteria andAdPageSizeLessThanOrEqualTo(Integer value) {
            addCriterion("ad_page_size <=", value, "adPageSize");
            return (Criteria) this;
        }

        public Criteria andAdPageSizeIn(List<Integer> values) {
            addCriterion("ad_page_size in", values, "adPageSize");
            return (Criteria) this;
        }

        public Criteria andAdPageSizeNotIn(List<Integer> values) {
            addCriterion("ad_page_size not in", values, "adPageSize");
            return (Criteria) this;
        }

        public Criteria andAdPageSizeBetween(Integer value1, Integer value2) {
            addCriterion("ad_page_size between", value1, value2, "adPageSize");
            return (Criteria) this;
        }

        public Criteria andAdPageSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("ad_page_size not between", value1, value2, "adPageSize");
            return (Criteria) this;
        }

        public Criteria andAdTotalNumberIsNull() {
            addCriterion("ad_total_number is null");
            return (Criteria) this;
        }

        public Criteria andAdTotalNumberIsNotNull() {
            addCriterion("ad_total_number is not null");
            return (Criteria) this;
        }

        public Criteria andAdTotalNumberEqualTo(Integer value) {
            addCriterion("ad_total_number =", value, "adTotalNumber");
            return (Criteria) this;
        }

        public Criteria andAdTotalNumberNotEqualTo(Integer value) {
            addCriterion("ad_total_number <>", value, "adTotalNumber");
            return (Criteria) this;
        }

        public Criteria andAdTotalNumberGreaterThan(Integer value) {
            addCriterion("ad_total_number >", value, "adTotalNumber");
            return (Criteria) this;
        }

        public Criteria andAdTotalNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("ad_total_number >=", value, "adTotalNumber");
            return (Criteria) this;
        }

        public Criteria andAdTotalNumberLessThan(Integer value) {
            addCriterion("ad_total_number <", value, "adTotalNumber");
            return (Criteria) this;
        }

        public Criteria andAdTotalNumberLessThanOrEqualTo(Integer value) {
            addCriterion("ad_total_number <=", value, "adTotalNumber");
            return (Criteria) this;
        }

        public Criteria andAdTotalNumberIn(List<Integer> values) {
            addCriterion("ad_total_number in", values, "adTotalNumber");
            return (Criteria) this;
        }

        public Criteria andAdTotalNumberNotIn(List<Integer> values) {
            addCriterion("ad_total_number not in", values, "adTotalNumber");
            return (Criteria) this;
        }

        public Criteria andAdTotalNumberBetween(Integer value1, Integer value2) {
            addCriterion("ad_total_number between", value1, value2, "adTotalNumber");
            return (Criteria) this;
        }

        public Criteria andAdTotalNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("ad_total_number not between", value1, value2, "adTotalNumber");
            return (Criteria) this;
        }

        public Criteria andAdTotalPageIsNull() {
            addCriterion("ad_total_page is null");
            return (Criteria) this;
        }

        public Criteria andAdTotalPageIsNotNull() {
            addCriterion("ad_total_page is not null");
            return (Criteria) this;
        }

        public Criteria andAdTotalPageEqualTo(Integer value) {
            addCriterion("ad_total_page =", value, "adTotalPage");
            return (Criteria) this;
        }

        public Criteria andAdTotalPageNotEqualTo(Integer value) {
            addCriterion("ad_total_page <>", value, "adTotalPage");
            return (Criteria) this;
        }

        public Criteria andAdTotalPageGreaterThan(Integer value) {
            addCriterion("ad_total_page >", value, "adTotalPage");
            return (Criteria) this;
        }

        public Criteria andAdTotalPageGreaterThanOrEqualTo(Integer value) {
            addCriterion("ad_total_page >=", value, "adTotalPage");
            return (Criteria) this;
        }

        public Criteria andAdTotalPageLessThan(Integer value) {
            addCriterion("ad_total_page <", value, "adTotalPage");
            return (Criteria) this;
        }

        public Criteria andAdTotalPageLessThanOrEqualTo(Integer value) {
            addCriterion("ad_total_page <=", value, "adTotalPage");
            return (Criteria) this;
        }

        public Criteria andAdTotalPageIn(List<Integer> values) {
            addCriterion("ad_total_page in", values, "adTotalPage");
            return (Criteria) this;
        }

        public Criteria andAdTotalPageNotIn(List<Integer> values) {
            addCriterion("ad_total_page not in", values, "adTotalPage");
            return (Criteria) this;
        }

        public Criteria andAdTotalPageBetween(Integer value1, Integer value2) {
            addCriterion("ad_total_page between", value1, value2, "adTotalPage");
            return (Criteria) this;
        }

        public Criteria andAdTotalPageNotBetween(Integer value1, Integer value2) {
            addCriterion("ad_total_page not between", value1, value2, "adTotalPage");
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