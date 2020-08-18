package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MemberInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public MemberInfoExample() {
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

        public Criteria andLoginCodeIsNull() {
            addCriterion("login_code is null");
            return (Criteria) this;
        }

        public Criteria andLoginCodeIsNotNull() {
            addCriterion("login_code is not null");
            return (Criteria) this;
        }

        public Criteria andLoginCodeEqualTo(String value) {
            addCriterion("login_code =", value, "loginCode");
            return (Criteria) this;
        }

        public Criteria andLoginCodeNotEqualTo(String value) {
            addCriterion("login_code <>", value, "loginCode");
            return (Criteria) this;
        }

        public Criteria andLoginCodeGreaterThan(String value) {
            addCriterion("login_code >", value, "loginCode");
            return (Criteria) this;
        }

        public Criteria andLoginCodeGreaterThanOrEqualTo(String value) {
            addCriterion("login_code >=", value, "loginCode");
            return (Criteria) this;
        }

        public Criteria andLoginCodeLessThan(String value) {
            addCriterion("login_code <", value, "loginCode");
            return (Criteria) this;
        }

        public Criteria andLoginCodeLessThanOrEqualTo(String value) {
            addCriterion("login_code <=", value, "loginCode");
            return (Criteria) this;
        }

        public Criteria andLoginCodeLike(String value) {
            addCriterion("login_code like", value, "loginCode");
            return (Criteria) this;
        }

        public Criteria andLoginCodeNotLike(String value) {
            addCriterion("login_code not like", value, "loginCode");
            return (Criteria) this;
        }

        public Criteria andLoginCodeIn(List<String> values) {
            addCriterion("login_code in", values, "loginCode");
            return (Criteria) this;
        }

        public Criteria andLoginCodeNotIn(List<String> values) {
            addCriterion("login_code not in", values, "loginCode");
            return (Criteria) this;
        }

        public Criteria andLoginCodeBetween(String value1, String value2) {
            addCriterion("login_code between", value1, value2, "loginCode");
            return (Criteria) this;
        }

        public Criteria andLoginCodeNotBetween(String value1, String value2) {
            addCriterion("login_code not between", value1, value2, "loginCode");
            return (Criteria) this;
        }

        public Criteria andLoginPassIsNull() {
            addCriterion("login_pass is null");
            return (Criteria) this;
        }

        public Criteria andLoginPassIsNotNull() {
            addCriterion("login_pass is not null");
            return (Criteria) this;
        }

        public Criteria andLoginPassEqualTo(String value) {
            addCriterion("login_pass =", value, "loginPass");
            return (Criteria) this;
        }

        public Criteria andLoginPassNotEqualTo(String value) {
            addCriterion("login_pass <>", value, "loginPass");
            return (Criteria) this;
        }

        public Criteria andLoginPassGreaterThan(String value) {
            addCriterion("login_pass >", value, "loginPass");
            return (Criteria) this;
        }

        public Criteria andLoginPassGreaterThanOrEqualTo(String value) {
            addCriterion("login_pass >=", value, "loginPass");
            return (Criteria) this;
        }

        public Criteria andLoginPassLessThan(String value) {
            addCriterion("login_pass <", value, "loginPass");
            return (Criteria) this;
        }

        public Criteria andLoginPassLessThanOrEqualTo(String value) {
            addCriterion("login_pass <=", value, "loginPass");
            return (Criteria) this;
        }

        public Criteria andLoginPassLike(String value) {
            addCriterion("login_pass like", value, "loginPass");
            return (Criteria) this;
        }

        public Criteria andLoginPassNotLike(String value) {
            addCriterion("login_pass not like", value, "loginPass");
            return (Criteria) this;
        }

        public Criteria andLoginPassIn(List<String> values) {
            addCriterion("login_pass in", values, "loginPass");
            return (Criteria) this;
        }

        public Criteria andLoginPassNotIn(List<String> values) {
            addCriterion("login_pass not in", values, "loginPass");
            return (Criteria) this;
        }

        public Criteria andLoginPassBetween(String value1, String value2) {
            addCriterion("login_pass between", value1, value2, "loginPass");
            return (Criteria) this;
        }

        public Criteria andLoginPassNotBetween(String value1, String value2) {
            addCriterion("login_pass not between", value1, value2, "loginPass");
            return (Criteria) this;
        }

        public Criteria andSupIdIsNull() {
            addCriterion("sup_id is null");
            return (Criteria) this;
        }

        public Criteria andSupIdIsNotNull() {
            addCriterion("sup_id is not null");
            return (Criteria) this;
        }

        public Criteria andSupIdEqualTo(Integer value) {
            addCriterion("sup_id =", value, "supId");
            return (Criteria) this;
        }

        public Criteria andSupIdNotEqualTo(Integer value) {
            addCriterion("sup_id <>", value, "supId");
            return (Criteria) this;
        }

        public Criteria andSupIdGreaterThan(Integer value) {
            addCriterion("sup_id >", value, "supId");
            return (Criteria) this;
        }

        public Criteria andSupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sup_id >=", value, "supId");
            return (Criteria) this;
        }

        public Criteria andSupIdLessThan(Integer value) {
            addCriterion("sup_id <", value, "supId");
            return (Criteria) this;
        }

        public Criteria andSupIdLessThanOrEqualTo(Integer value) {
            addCriterion("sup_id <=", value, "supId");
            return (Criteria) this;
        }

        public Criteria andSupIdIn(List<Integer> values) {
            addCriterion("sup_id in", values, "supId");
            return (Criteria) this;
        }

        public Criteria andSupIdNotIn(List<Integer> values) {
            addCriterion("sup_id not in", values, "supId");
            return (Criteria) this;
        }

        public Criteria andSupIdBetween(Integer value1, Integer value2) {
            addCriterion("sup_id between", value1, value2, "supId");
            return (Criteria) this;
        }

        public Criteria andSupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sup_id not between", value1, value2, "supId");
            return (Criteria) this;
        }

        public Criteria andNickIsNull() {
            addCriterion("nick is null");
            return (Criteria) this;
        }

        public Criteria andNickIsNotNull() {
            addCriterion("nick is not null");
            return (Criteria) this;
        }

        public Criteria andNickEqualTo(String value) {
            addCriterion("nick =", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickNotEqualTo(String value) {
            addCriterion("nick <>", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickGreaterThan(String value) {
            addCriterion("nick >", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickGreaterThanOrEqualTo(String value) {
            addCriterion("nick >=", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickLessThan(String value) {
            addCriterion("nick <", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickLessThanOrEqualTo(String value) {
            addCriterion("nick <=", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickLike(String value) {
            addCriterion("nick like", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickNotLike(String value) {
            addCriterion("nick not like", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickIn(List<String> values) {
            addCriterion("nick in", values, "nick");
            return (Criteria) this;
        }

        public Criteria andNickNotIn(List<String> values) {
            addCriterion("nick not in", values, "nick");
            return (Criteria) this;
        }

        public Criteria andNickBetween(String value1, String value2) {
            addCriterion("nick between", value1, value2, "nick");
            return (Criteria) this;
        }

        public Criteria andNickNotBetween(String value1, String value2) {
            addCriterion("nick not between", value1, value2, "nick");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNull() {
            addCriterion("group_id is null");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNotNull() {
            addCriterion("group_id is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIdEqualTo(Integer value) {
            addCriterion("group_id =", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotEqualTo(Integer value) {
            addCriterion("group_id <>", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThan(Integer value) {
            addCriterion("group_id >", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_id >=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThan(Integer value) {
            addCriterion("group_id <", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("group_id <=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIn(List<Integer> values) {
            addCriterion("group_id in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotIn(List<Integer> values) {
            addCriterion("group_id not in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("group_id between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("group_id not between", value1, value2, "groupId");
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

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMVerfiyFlagIsNull() {
            addCriterion("m_verfiy_flag is null");
            return (Criteria) this;
        }

        public Criteria andMVerfiyFlagIsNotNull() {
            addCriterion("m_verfiy_flag is not null");
            return (Criteria) this;
        }

        public Criteria andMVerfiyFlagEqualTo(String value) {
            addCriterion("m_verfiy_flag =", value, "mVerfiyFlag");
            return (Criteria) this;
        }

        public Criteria andMVerfiyFlagNotEqualTo(String value) {
            addCriterion("m_verfiy_flag <>", value, "mVerfiyFlag");
            return (Criteria) this;
        }

        public Criteria andMVerfiyFlagGreaterThan(String value) {
            addCriterion("m_verfiy_flag >", value, "mVerfiyFlag");
            return (Criteria) this;
        }

        public Criteria andMVerfiyFlagGreaterThanOrEqualTo(String value) {
            addCriterion("m_verfiy_flag >=", value, "mVerfiyFlag");
            return (Criteria) this;
        }

        public Criteria andMVerfiyFlagLessThan(String value) {
            addCriterion("m_verfiy_flag <", value, "mVerfiyFlag");
            return (Criteria) this;
        }

        public Criteria andMVerfiyFlagLessThanOrEqualTo(String value) {
            addCriterion("m_verfiy_flag <=", value, "mVerfiyFlag");
            return (Criteria) this;
        }

        public Criteria andMVerfiyFlagLike(String value) {
            addCriterion("m_verfiy_flag like", value, "mVerfiyFlag");
            return (Criteria) this;
        }

        public Criteria andMVerfiyFlagNotLike(String value) {
            addCriterion("m_verfiy_flag not like", value, "mVerfiyFlag");
            return (Criteria) this;
        }

        public Criteria andMVerfiyFlagIn(List<String> values) {
            addCriterion("m_verfiy_flag in", values, "mVerfiyFlag");
            return (Criteria) this;
        }

        public Criteria andMVerfiyFlagNotIn(List<String> values) {
            addCriterion("m_verfiy_flag not in", values, "mVerfiyFlag");
            return (Criteria) this;
        }

        public Criteria andMVerfiyFlagBetween(String value1, String value2) {
            addCriterion("m_verfiy_flag between", value1, value2, "mVerfiyFlag");
            return (Criteria) this;
        }

        public Criteria andMVerfiyFlagNotBetween(String value1, String value2) {
            addCriterion("m_verfiy_flag not between", value1, value2, "mVerfiyFlag");
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

        public Criteria andEVerfiyFlagIsNull() {
            addCriterion("e_verfiy_flag is null");
            return (Criteria) this;
        }

        public Criteria andEVerfiyFlagIsNotNull() {
            addCriterion("e_verfiy_flag is not null");
            return (Criteria) this;
        }

        public Criteria andEVerfiyFlagEqualTo(String value) {
            addCriterion("e_verfiy_flag =", value, "eVerfiyFlag");
            return (Criteria) this;
        }

        public Criteria andEVerfiyFlagNotEqualTo(String value) {
            addCriterion("e_verfiy_flag <>", value, "eVerfiyFlag");
            return (Criteria) this;
        }

        public Criteria andEVerfiyFlagGreaterThan(String value) {
            addCriterion("e_verfiy_flag >", value, "eVerfiyFlag");
            return (Criteria) this;
        }

        public Criteria andEVerfiyFlagGreaterThanOrEqualTo(String value) {
            addCriterion("e_verfiy_flag >=", value, "eVerfiyFlag");
            return (Criteria) this;
        }

        public Criteria andEVerfiyFlagLessThan(String value) {
            addCriterion("e_verfiy_flag <", value, "eVerfiyFlag");
            return (Criteria) this;
        }

        public Criteria andEVerfiyFlagLessThanOrEqualTo(String value) {
            addCriterion("e_verfiy_flag <=", value, "eVerfiyFlag");
            return (Criteria) this;
        }

        public Criteria andEVerfiyFlagLike(String value) {
            addCriterion("e_verfiy_flag like", value, "eVerfiyFlag");
            return (Criteria) this;
        }

        public Criteria andEVerfiyFlagNotLike(String value) {
            addCriterion("e_verfiy_flag not like", value, "eVerfiyFlag");
            return (Criteria) this;
        }

        public Criteria andEVerfiyFlagIn(List<String> values) {
            addCriterion("e_verfiy_flag in", values, "eVerfiyFlag");
            return (Criteria) this;
        }

        public Criteria andEVerfiyFlagNotIn(List<String> values) {
            addCriterion("e_verfiy_flag not in", values, "eVerfiyFlag");
            return (Criteria) this;
        }

        public Criteria andEVerfiyFlagBetween(String value1, String value2) {
            addCriterion("e_verfiy_flag between", value1, value2, "eVerfiyFlag");
            return (Criteria) this;
        }

        public Criteria andEVerfiyFlagNotBetween(String value1, String value2) {
            addCriterion("e_verfiy_flag not between", value1, value2, "eVerfiyFlag");
            return (Criteria) this;
        }

        public Criteria andWeixinIdIsNull() {
            addCriterion("weixin_id is null");
            return (Criteria) this;
        }

        public Criteria andWeixinIdIsNotNull() {
            addCriterion("weixin_id is not null");
            return (Criteria) this;
        }

        public Criteria andWeixinIdEqualTo(String value) {
            addCriterion("weixin_id =", value, "weixinId");
            return (Criteria) this;
        }

        public Criteria andWeixinIdNotEqualTo(String value) {
            addCriterion("weixin_id <>", value, "weixinId");
            return (Criteria) this;
        }

        public Criteria andWeixinIdGreaterThan(String value) {
            addCriterion("weixin_id >", value, "weixinId");
            return (Criteria) this;
        }

        public Criteria andWeixinIdGreaterThanOrEqualTo(String value) {
            addCriterion("weixin_id >=", value, "weixinId");
            return (Criteria) this;
        }

        public Criteria andWeixinIdLessThan(String value) {
            addCriterion("weixin_id <", value, "weixinId");
            return (Criteria) this;
        }

        public Criteria andWeixinIdLessThanOrEqualTo(String value) {
            addCriterion("weixin_id <=", value, "weixinId");
            return (Criteria) this;
        }

        public Criteria andWeixinIdLike(String value) {
            addCriterion("weixin_id like", value, "weixinId");
            return (Criteria) this;
        }

        public Criteria andWeixinIdNotLike(String value) {
            addCriterion("weixin_id not like", value, "weixinId");
            return (Criteria) this;
        }

        public Criteria andWeixinIdIn(List<String> values) {
            addCriterion("weixin_id in", values, "weixinId");
            return (Criteria) this;
        }

        public Criteria andWeixinIdNotIn(List<String> values) {
            addCriterion("weixin_id not in", values, "weixinId");
            return (Criteria) this;
        }

        public Criteria andWeixinIdBetween(String value1, String value2) {
            addCriterion("weixin_id between", value1, value2, "weixinId");
            return (Criteria) this;
        }

        public Criteria andWeixinIdNotBetween(String value1, String value2) {
            addCriterion("weixin_id not between", value1, value2, "weixinId");
            return (Criteria) this;
        }

        public Criteria andWeixinUnionidIsNull() {
            addCriterion("weixin_unionid is null");
            return (Criteria) this;
        }

        public Criteria andWeixinUnionidIsNotNull() {
            addCriterion("weixin_unionid is not null");
            return (Criteria) this;
        }

        public Criteria andWeixinUnionidEqualTo(String value) {
            addCriterion("weixin_unionid =", value, "weixinUnionid");
            return (Criteria) this;
        }

        public Criteria andWeixinUnionidNotEqualTo(String value) {
            addCriterion("weixin_unionid <>", value, "weixinUnionid");
            return (Criteria) this;
        }

        public Criteria andWeixinUnionidGreaterThan(String value) {
            addCriterion("weixin_unionid >", value, "weixinUnionid");
            return (Criteria) this;
        }

        public Criteria andWeixinUnionidGreaterThanOrEqualTo(String value) {
            addCriterion("weixin_unionid >=", value, "weixinUnionid");
            return (Criteria) this;
        }

        public Criteria andWeixinUnionidLessThan(String value) {
            addCriterion("weixin_unionid <", value, "weixinUnionid");
            return (Criteria) this;
        }

        public Criteria andWeixinUnionidLessThanOrEqualTo(String value) {
            addCriterion("weixin_unionid <=", value, "weixinUnionid");
            return (Criteria) this;
        }

        public Criteria andWeixinUnionidLike(String value) {
            addCriterion("weixin_unionid like", value, "weixinUnionid");
            return (Criteria) this;
        }

        public Criteria andWeixinUnionidNotLike(String value) {
            addCriterion("weixin_unionid not like", value, "weixinUnionid");
            return (Criteria) this;
        }

        public Criteria andWeixinUnionidIn(List<String> values) {
            addCriterion("weixin_unionid in", values, "weixinUnionid");
            return (Criteria) this;
        }

        public Criteria andWeixinUnionidNotIn(List<String> values) {
            addCriterion("weixin_unionid not in", values, "weixinUnionid");
            return (Criteria) this;
        }

        public Criteria andWeixinUnionidBetween(String value1, String value2) {
            addCriterion("weixin_unionid between", value1, value2, "weixinUnionid");
            return (Criteria) this;
        }

        public Criteria andWeixinUnionidNotBetween(String value1, String value2) {
            addCriterion("weixin_unionid not between", value1, value2, "weixinUnionid");
            return (Criteria) this;
        }

        public Criteria andQqIdIsNull() {
            addCriterion("qq_id is null");
            return (Criteria) this;
        }

        public Criteria andQqIdIsNotNull() {
            addCriterion("qq_id is not null");
            return (Criteria) this;
        }

        public Criteria andQqIdEqualTo(String value) {
            addCriterion("qq_id =", value, "qqId");
            return (Criteria) this;
        }

        public Criteria andQqIdNotEqualTo(String value) {
            addCriterion("qq_id <>", value, "qqId");
            return (Criteria) this;
        }

        public Criteria andQqIdGreaterThan(String value) {
            addCriterion("qq_id >", value, "qqId");
            return (Criteria) this;
        }

        public Criteria andQqIdGreaterThanOrEqualTo(String value) {
            addCriterion("qq_id >=", value, "qqId");
            return (Criteria) this;
        }

        public Criteria andQqIdLessThan(String value) {
            addCriterion("qq_id <", value, "qqId");
            return (Criteria) this;
        }

        public Criteria andQqIdLessThanOrEqualTo(String value) {
            addCriterion("qq_id <=", value, "qqId");
            return (Criteria) this;
        }

        public Criteria andQqIdLike(String value) {
            addCriterion("qq_id like", value, "qqId");
            return (Criteria) this;
        }

        public Criteria andQqIdNotLike(String value) {
            addCriterion("qq_id not like", value, "qqId");
            return (Criteria) this;
        }

        public Criteria andQqIdIn(List<String> values) {
            addCriterion("qq_id in", values, "qqId");
            return (Criteria) this;
        }

        public Criteria andQqIdNotIn(List<String> values) {
            addCriterion("qq_id not in", values, "qqId");
            return (Criteria) this;
        }

        public Criteria andQqIdBetween(String value1, String value2) {
            addCriterion("qq_id between", value1, value2, "qqId");
            return (Criteria) this;
        }

        public Criteria andQqIdNotBetween(String value1, String value2) {
            addCriterion("qq_id not between", value1, value2, "qqId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdIsNull() {
            addCriterion("weibo_id is null");
            return (Criteria) this;
        }

        public Criteria andWeiboIdIsNotNull() {
            addCriterion("weibo_id is not null");
            return (Criteria) this;
        }

        public Criteria andWeiboIdEqualTo(String value) {
            addCriterion("weibo_id =", value, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdNotEqualTo(String value) {
            addCriterion("weibo_id <>", value, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdGreaterThan(String value) {
            addCriterion("weibo_id >", value, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdGreaterThanOrEqualTo(String value) {
            addCriterion("weibo_id >=", value, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdLessThan(String value) {
            addCriterion("weibo_id <", value, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdLessThanOrEqualTo(String value) {
            addCriterion("weibo_id <=", value, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdLike(String value) {
            addCriterion("weibo_id like", value, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdNotLike(String value) {
            addCriterion("weibo_id not like", value, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdIn(List<String> values) {
            addCriterion("weibo_id in", values, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdNotIn(List<String> values) {
            addCriterion("weibo_id not in", values, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdBetween(String value1, String value2) {
            addCriterion("weibo_id between", value1, value2, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdNotBetween(String value1, String value2) {
            addCriterion("weibo_id not between", value1, value2, "weiboId");
            return (Criteria) this;
        }

        public Criteria andSexTypeIsNull() {
            addCriterion("sex_type is null");
            return (Criteria) this;
        }

        public Criteria andSexTypeIsNotNull() {
            addCriterion("sex_type is not null");
            return (Criteria) this;
        }

        public Criteria andSexTypeEqualTo(String value) {
            addCriterion("sex_type =", value, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeNotEqualTo(String value) {
            addCriterion("sex_type <>", value, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeGreaterThan(String value) {
            addCriterion("sex_type >", value, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeGreaterThanOrEqualTo(String value) {
            addCriterion("sex_type >=", value, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeLessThan(String value) {
            addCriterion("sex_type <", value, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeLessThanOrEqualTo(String value) {
            addCriterion("sex_type <=", value, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeLike(String value) {
            addCriterion("sex_type like", value, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeNotLike(String value) {
            addCriterion("sex_type not like", value, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeIn(List<String> values) {
            addCriterion("sex_type in", values, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeNotIn(List<String> values) {
            addCriterion("sex_type not in", values, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeBetween(String value1, String value2) {
            addCriterion("sex_type between", value1, value2, "sexType");
            return (Criteria) this;
        }

        public Criteria andSexTypeNotBetween(String value1, String value2) {
            addCriterion("sex_type not between", value1, value2, "sexType");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("birthday is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("birthday is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(Date value) {
            addCriterionForJDBCDate("birthday =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(Date value) {
            addCriterionForJDBCDate("birthday <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(Date value) {
            addCriterionForJDBCDate("birthday >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birthday >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(Date value) {
            addCriterionForJDBCDate("birthday <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birthday <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<Date> values) {
            addCriterionForJDBCDate("birthday in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<Date> values) {
            addCriterionForJDBCDate("birthday not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birthday between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birthday not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(Integer value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(Integer value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(Integer value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(Integer value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(Integer value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(Integer value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<Integer> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<Integer> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(Integer value1, Integer value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(Integer value1, Integer value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(Integer value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(Integer value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(Integer value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(Integer value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(Integer value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(Integer value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<Integer> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<Integer> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(Integer value1, Integer value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(Integer value1, Integer value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCountyIsNull() {
            addCriterion("county is null");
            return (Criteria) this;
        }

        public Criteria andCountyIsNotNull() {
            addCriterion("county is not null");
            return (Criteria) this;
        }

        public Criteria andCountyEqualTo(Integer value) {
            addCriterion("county =", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotEqualTo(Integer value) {
            addCriterion("county <>", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyGreaterThan(Integer value) {
            addCriterion("county >", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyGreaterThanOrEqualTo(Integer value) {
            addCriterion("county >=", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyLessThan(Integer value) {
            addCriterion("county <", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyLessThanOrEqualTo(Integer value) {
            addCriterion("county <=", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyIn(List<Integer> values) {
            addCriterion("county in", values, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotIn(List<Integer> values) {
            addCriterion("county not in", values, "county");
            return (Criteria) this;
        }

        public Criteria andCountyBetween(Integer value1, Integer value2) {
            addCriterion("county between", value1, value2, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotBetween(Integer value1, Integer value2) {
            addCriterion("county not between", value1, value2, "county");
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

        public Criteria andWeixinHeadIsNull() {
            addCriterion("weixin_head is null");
            return (Criteria) this;
        }

        public Criteria andWeixinHeadIsNotNull() {
            addCriterion("weixin_head is not null");
            return (Criteria) this;
        }

        public Criteria andWeixinHeadEqualTo(String value) {
            addCriterion("weixin_head =", value, "weixinHead");
            return (Criteria) this;
        }

        public Criteria andWeixinHeadNotEqualTo(String value) {
            addCriterion("weixin_head <>", value, "weixinHead");
            return (Criteria) this;
        }

        public Criteria andWeixinHeadGreaterThan(String value) {
            addCriterion("weixin_head >", value, "weixinHead");
            return (Criteria) this;
        }

        public Criteria andWeixinHeadGreaterThanOrEqualTo(String value) {
            addCriterion("weixin_head >=", value, "weixinHead");
            return (Criteria) this;
        }

        public Criteria andWeixinHeadLessThan(String value) {
            addCriterion("weixin_head <", value, "weixinHead");
            return (Criteria) this;
        }

        public Criteria andWeixinHeadLessThanOrEqualTo(String value) {
            addCriterion("weixin_head <=", value, "weixinHead");
            return (Criteria) this;
        }

        public Criteria andWeixinHeadLike(String value) {
            addCriterion("weixin_head like", value, "weixinHead");
            return (Criteria) this;
        }

        public Criteria andWeixinHeadNotLike(String value) {
            addCriterion("weixin_head not like", value, "weixinHead");
            return (Criteria) this;
        }

        public Criteria andWeixinHeadIn(List<String> values) {
            addCriterion("weixin_head in", values, "weixinHead");
            return (Criteria) this;
        }

        public Criteria andWeixinHeadNotIn(List<String> values) {
            addCriterion("weixin_head not in", values, "weixinHead");
            return (Criteria) this;
        }

        public Criteria andWeixinHeadBetween(String value1, String value2) {
            addCriterion("weixin_head between", value1, value2, "weixinHead");
            return (Criteria) this;
        }

        public Criteria andWeixinHeadNotBetween(String value1, String value2) {
            addCriterion("weixin_head not between", value1, value2, "weixinHead");
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

        public Criteria andRegClientIsNull() {
            addCriterion("reg_client is null");
            return (Criteria) this;
        }

        public Criteria andRegClientIsNotNull() {
            addCriterion("reg_client is not null");
            return (Criteria) this;
        }

        public Criteria andRegClientEqualTo(String value) {
            addCriterion("reg_client =", value, "regClient");
            return (Criteria) this;
        }

        public Criteria andRegClientNotEqualTo(String value) {
            addCriterion("reg_client <>", value, "regClient");
            return (Criteria) this;
        }

        public Criteria andRegClientGreaterThan(String value) {
            addCriterion("reg_client >", value, "regClient");
            return (Criteria) this;
        }

        public Criteria andRegClientGreaterThanOrEqualTo(String value) {
            addCriterion("reg_client >=", value, "regClient");
            return (Criteria) this;
        }

        public Criteria andRegClientLessThan(String value) {
            addCriterion("reg_client <", value, "regClient");
            return (Criteria) this;
        }

        public Criteria andRegClientLessThanOrEqualTo(String value) {
            addCriterion("reg_client <=", value, "regClient");
            return (Criteria) this;
        }

        public Criteria andRegClientLike(String value) {
            addCriterion("reg_client like", value, "regClient");
            return (Criteria) this;
        }

        public Criteria andRegClientNotLike(String value) {
            addCriterion("reg_client not like", value, "regClient");
            return (Criteria) this;
        }

        public Criteria andRegClientIn(List<String> values) {
            addCriterion("reg_client in", values, "regClient");
            return (Criteria) this;
        }

        public Criteria andRegClientNotIn(List<String> values) {
            addCriterion("reg_client not in", values, "regClient");
            return (Criteria) this;
        }

        public Criteria andRegClientBetween(String value1, String value2) {
            addCriterion("reg_client between", value1, value2, "regClient");
            return (Criteria) this;
        }

        public Criteria andRegClientNotBetween(String value1, String value2) {
            addCriterion("reg_client not between", value1, value2, "regClient");
            return (Criteria) this;
        }

        public Criteria andReqMobileBrandIsNull() {
            addCriterion("req_mobile_brand is null");
            return (Criteria) this;
        }

        public Criteria andReqMobileBrandIsNotNull() {
            addCriterion("req_mobile_brand is not null");
            return (Criteria) this;
        }

        public Criteria andReqMobileBrandEqualTo(String value) {
            addCriterion("req_mobile_brand =", value, "reqMobileBrand");
            return (Criteria) this;
        }

        public Criteria andReqMobileBrandNotEqualTo(String value) {
            addCriterion("req_mobile_brand <>", value, "reqMobileBrand");
            return (Criteria) this;
        }

        public Criteria andReqMobileBrandGreaterThan(String value) {
            addCriterion("req_mobile_brand >", value, "reqMobileBrand");
            return (Criteria) this;
        }

        public Criteria andReqMobileBrandGreaterThanOrEqualTo(String value) {
            addCriterion("req_mobile_brand >=", value, "reqMobileBrand");
            return (Criteria) this;
        }

        public Criteria andReqMobileBrandLessThan(String value) {
            addCriterion("req_mobile_brand <", value, "reqMobileBrand");
            return (Criteria) this;
        }

        public Criteria andReqMobileBrandLessThanOrEqualTo(String value) {
            addCriterion("req_mobile_brand <=", value, "reqMobileBrand");
            return (Criteria) this;
        }

        public Criteria andReqMobileBrandLike(String value) {
            addCriterion("req_mobile_brand like", value, "reqMobileBrand");
            return (Criteria) this;
        }

        public Criteria andReqMobileBrandNotLike(String value) {
            addCriterion("req_mobile_brand not like", value, "reqMobileBrand");
            return (Criteria) this;
        }

        public Criteria andReqMobileBrandIn(List<String> values) {
            addCriterion("req_mobile_brand in", values, "reqMobileBrand");
            return (Criteria) this;
        }

        public Criteria andReqMobileBrandNotIn(List<String> values) {
            addCriterion("req_mobile_brand not in", values, "reqMobileBrand");
            return (Criteria) this;
        }

        public Criteria andReqMobileBrandBetween(String value1, String value2) {
            addCriterion("req_mobile_brand between", value1, value2, "reqMobileBrand");
            return (Criteria) this;
        }

        public Criteria andReqMobileBrandNotBetween(String value1, String value2) {
            addCriterion("req_mobile_brand not between", value1, value2, "reqMobileBrand");
            return (Criteria) this;
        }

        public Criteria andReqMobileModelIsNull() {
            addCriterion("req_mobile_model is null");
            return (Criteria) this;
        }

        public Criteria andReqMobileModelIsNotNull() {
            addCriterion("req_mobile_model is not null");
            return (Criteria) this;
        }

        public Criteria andReqMobileModelEqualTo(String value) {
            addCriterion("req_mobile_model =", value, "reqMobileModel");
            return (Criteria) this;
        }

        public Criteria andReqMobileModelNotEqualTo(String value) {
            addCriterion("req_mobile_model <>", value, "reqMobileModel");
            return (Criteria) this;
        }

        public Criteria andReqMobileModelGreaterThan(String value) {
            addCriterion("req_mobile_model >", value, "reqMobileModel");
            return (Criteria) this;
        }

        public Criteria andReqMobileModelGreaterThanOrEqualTo(String value) {
            addCriterion("req_mobile_model >=", value, "reqMobileModel");
            return (Criteria) this;
        }

        public Criteria andReqMobileModelLessThan(String value) {
            addCriterion("req_mobile_model <", value, "reqMobileModel");
            return (Criteria) this;
        }

        public Criteria andReqMobileModelLessThanOrEqualTo(String value) {
            addCriterion("req_mobile_model <=", value, "reqMobileModel");
            return (Criteria) this;
        }

        public Criteria andReqMobileModelLike(String value) {
            addCriterion("req_mobile_model like", value, "reqMobileModel");
            return (Criteria) this;
        }

        public Criteria andReqMobileModelNotLike(String value) {
            addCriterion("req_mobile_model not like", value, "reqMobileModel");
            return (Criteria) this;
        }

        public Criteria andReqMobileModelIn(List<String> values) {
            addCriterion("req_mobile_model in", values, "reqMobileModel");
            return (Criteria) this;
        }

        public Criteria andReqMobileModelNotIn(List<String> values) {
            addCriterion("req_mobile_model not in", values, "reqMobileModel");
            return (Criteria) this;
        }

        public Criteria andReqMobileModelBetween(String value1, String value2) {
            addCriterion("req_mobile_model between", value1, value2, "reqMobileModel");
            return (Criteria) this;
        }

        public Criteria andReqMobileModelNotBetween(String value1, String value2) {
            addCriterion("req_mobile_model not between", value1, value2, "reqMobileModel");
            return (Criteria) this;
        }

        public Criteria andReqImeiIsNull() {
            addCriterion("req_imei is null");
            return (Criteria) this;
        }

        public Criteria andReqImeiIsNotNull() {
            addCriterion("req_imei is not null");
            return (Criteria) this;
        }

        public Criteria andReqImeiEqualTo(String value) {
            addCriterion("req_imei =", value, "reqImei");
            return (Criteria) this;
        }

        public Criteria andReqImeiNotEqualTo(String value) {
            addCriterion("req_imei <>", value, "reqImei");
            return (Criteria) this;
        }

        public Criteria andReqImeiGreaterThan(String value) {
            addCriterion("req_imei >", value, "reqImei");
            return (Criteria) this;
        }

        public Criteria andReqImeiGreaterThanOrEqualTo(String value) {
            addCriterion("req_imei >=", value, "reqImei");
            return (Criteria) this;
        }

        public Criteria andReqImeiLessThan(String value) {
            addCriterion("req_imei <", value, "reqImei");
            return (Criteria) this;
        }

        public Criteria andReqImeiLessThanOrEqualTo(String value) {
            addCriterion("req_imei <=", value, "reqImei");
            return (Criteria) this;
        }

        public Criteria andReqImeiLike(String value) {
            addCriterion("req_imei like", value, "reqImei");
            return (Criteria) this;
        }

        public Criteria andReqImeiNotLike(String value) {
            addCriterion("req_imei not like", value, "reqImei");
            return (Criteria) this;
        }

        public Criteria andReqImeiIn(List<String> values) {
            addCriterion("req_imei in", values, "reqImei");
            return (Criteria) this;
        }

        public Criteria andReqImeiNotIn(List<String> values) {
            addCriterion("req_imei not in", values, "reqImei");
            return (Criteria) this;
        }

        public Criteria andReqImeiBetween(String value1, String value2) {
            addCriterion("req_imei between", value1, value2, "reqImei");
            return (Criteria) this;
        }

        public Criteria andReqImeiNotBetween(String value1, String value2) {
            addCriterion("req_imei not between", value1, value2, "reqImei");
            return (Criteria) this;
        }

        public Criteria andRegIpIsNull() {
            addCriterion("reg_ip is null");
            return (Criteria) this;
        }

        public Criteria andRegIpIsNotNull() {
            addCriterion("reg_ip is not null");
            return (Criteria) this;
        }

        public Criteria andRegIpEqualTo(String value) {
            addCriterion("reg_ip =", value, "regIp");
            return (Criteria) this;
        }

        public Criteria andRegIpNotEqualTo(String value) {
            addCriterion("reg_ip <>", value, "regIp");
            return (Criteria) this;
        }

        public Criteria andRegIpGreaterThan(String value) {
            addCriterion("reg_ip >", value, "regIp");
            return (Criteria) this;
        }

        public Criteria andRegIpGreaterThanOrEqualTo(String value) {
            addCriterion("reg_ip >=", value, "regIp");
            return (Criteria) this;
        }

        public Criteria andRegIpLessThan(String value) {
            addCriterion("reg_ip <", value, "regIp");
            return (Criteria) this;
        }

        public Criteria andRegIpLessThanOrEqualTo(String value) {
            addCriterion("reg_ip <=", value, "regIp");
            return (Criteria) this;
        }

        public Criteria andRegIpLike(String value) {
            addCriterion("reg_ip like", value, "regIp");
            return (Criteria) this;
        }

        public Criteria andRegIpNotLike(String value) {
            addCriterion("reg_ip not like", value, "regIp");
            return (Criteria) this;
        }

        public Criteria andRegIpIn(List<String> values) {
            addCriterion("reg_ip in", values, "regIp");
            return (Criteria) this;
        }

        public Criteria andRegIpNotIn(List<String> values) {
            addCriterion("reg_ip not in", values, "regIp");
            return (Criteria) this;
        }

        public Criteria andRegIpBetween(String value1, String value2) {
            addCriterion("reg_ip between", value1, value2, "regIp");
            return (Criteria) this;
        }

        public Criteria andRegIpNotBetween(String value1, String value2) {
            addCriterion("reg_ip not between", value1, value2, "regIp");
            return (Criteria) this;
        }

        public Criteria andRegAreaIsNull() {
            addCriterion("reg_area is null");
            return (Criteria) this;
        }

        public Criteria andRegAreaIsNotNull() {
            addCriterion("reg_area is not null");
            return (Criteria) this;
        }

        public Criteria andRegAreaEqualTo(String value) {
            addCriterion("reg_area =", value, "regArea");
            return (Criteria) this;
        }

        public Criteria andRegAreaNotEqualTo(String value) {
            addCriterion("reg_area <>", value, "regArea");
            return (Criteria) this;
        }

        public Criteria andRegAreaGreaterThan(String value) {
            addCriterion("reg_area >", value, "regArea");
            return (Criteria) this;
        }

        public Criteria andRegAreaGreaterThanOrEqualTo(String value) {
            addCriterion("reg_area >=", value, "regArea");
            return (Criteria) this;
        }

        public Criteria andRegAreaLessThan(String value) {
            addCriterion("reg_area <", value, "regArea");
            return (Criteria) this;
        }

        public Criteria andRegAreaLessThanOrEqualTo(String value) {
            addCriterion("reg_area <=", value, "regArea");
            return (Criteria) this;
        }

        public Criteria andRegAreaLike(String value) {
            addCriterion("reg_area like", value, "regArea");
            return (Criteria) this;
        }

        public Criteria andRegAreaNotLike(String value) {
            addCriterion("reg_area not like", value, "regArea");
            return (Criteria) this;
        }

        public Criteria andRegAreaIn(List<String> values) {
            addCriterion("reg_area in", values, "regArea");
            return (Criteria) this;
        }

        public Criteria andRegAreaNotIn(List<String> values) {
            addCriterion("reg_area not in", values, "regArea");
            return (Criteria) this;
        }

        public Criteria andRegAreaBetween(String value1, String value2) {
            addCriterion("reg_area between", value1, value2, "regArea");
            return (Criteria) this;
        }

        public Criteria andRegAreaNotBetween(String value1, String value2) {
            addCriterion("reg_area not between", value1, value2, "regArea");
            return (Criteria) this;
        }

        public Criteria andIsInfPerfectIsNull() {
            addCriterion("is_inf_perfect is null");
            return (Criteria) this;
        }

        public Criteria andIsInfPerfectIsNotNull() {
            addCriterion("is_inf_perfect is not null");
            return (Criteria) this;
        }

        public Criteria andIsInfPerfectEqualTo(String value) {
            addCriterion("is_inf_perfect =", value, "isInfPerfect");
            return (Criteria) this;
        }

        public Criteria andIsInfPerfectNotEqualTo(String value) {
            addCriterion("is_inf_perfect <>", value, "isInfPerfect");
            return (Criteria) this;
        }

        public Criteria andIsInfPerfectGreaterThan(String value) {
            addCriterion("is_inf_perfect >", value, "isInfPerfect");
            return (Criteria) this;
        }

        public Criteria andIsInfPerfectGreaterThanOrEqualTo(String value) {
            addCriterion("is_inf_perfect >=", value, "isInfPerfect");
            return (Criteria) this;
        }

        public Criteria andIsInfPerfectLessThan(String value) {
            addCriterion("is_inf_perfect <", value, "isInfPerfect");
            return (Criteria) this;
        }

        public Criteria andIsInfPerfectLessThanOrEqualTo(String value) {
            addCriterion("is_inf_perfect <=", value, "isInfPerfect");
            return (Criteria) this;
        }

        public Criteria andIsInfPerfectLike(String value) {
            addCriterion("is_inf_perfect like", value, "isInfPerfect");
            return (Criteria) this;
        }

        public Criteria andIsInfPerfectNotLike(String value) {
            addCriterion("is_inf_perfect not like", value, "isInfPerfect");
            return (Criteria) this;
        }

        public Criteria andIsInfPerfectIn(List<String> values) {
            addCriterion("is_inf_perfect in", values, "isInfPerfect");
            return (Criteria) this;
        }

        public Criteria andIsInfPerfectNotIn(List<String> values) {
            addCriterion("is_inf_perfect not in", values, "isInfPerfect");
            return (Criteria) this;
        }

        public Criteria andIsInfPerfectBetween(String value1, String value2) {
            addCriterion("is_inf_perfect between", value1, value2, "isInfPerfect");
            return (Criteria) this;
        }

        public Criteria andIsInfPerfectNotBetween(String value1, String value2) {
            addCriterion("is_inf_perfect not between", value1, value2, "isInfPerfect");
            return (Criteria) this;
        }

        public Criteria andIsAcceptPushIsNull() {
            addCriterion("is_accept_push is null");
            return (Criteria) this;
        }

        public Criteria andIsAcceptPushIsNotNull() {
            addCriterion("is_accept_push is not null");
            return (Criteria) this;
        }

        public Criteria andIsAcceptPushEqualTo(String value) {
            addCriterion("is_accept_push =", value, "isAcceptPush");
            return (Criteria) this;
        }

        public Criteria andIsAcceptPushNotEqualTo(String value) {
            addCriterion("is_accept_push <>", value, "isAcceptPush");
            return (Criteria) this;
        }

        public Criteria andIsAcceptPushGreaterThan(String value) {
            addCriterion("is_accept_push >", value, "isAcceptPush");
            return (Criteria) this;
        }

        public Criteria andIsAcceptPushGreaterThanOrEqualTo(String value) {
            addCriterion("is_accept_push >=", value, "isAcceptPush");
            return (Criteria) this;
        }

        public Criteria andIsAcceptPushLessThan(String value) {
            addCriterion("is_accept_push <", value, "isAcceptPush");
            return (Criteria) this;
        }

        public Criteria andIsAcceptPushLessThanOrEqualTo(String value) {
            addCriterion("is_accept_push <=", value, "isAcceptPush");
            return (Criteria) this;
        }

        public Criteria andIsAcceptPushLike(String value) {
            addCriterion("is_accept_push like", value, "isAcceptPush");
            return (Criteria) this;
        }

        public Criteria andIsAcceptPushNotLike(String value) {
            addCriterion("is_accept_push not like", value, "isAcceptPush");
            return (Criteria) this;
        }

        public Criteria andIsAcceptPushIn(List<String> values) {
            addCriterion("is_accept_push in", values, "isAcceptPush");
            return (Criteria) this;
        }

        public Criteria andIsAcceptPushNotIn(List<String> values) {
            addCriterion("is_accept_push not in", values, "isAcceptPush");
            return (Criteria) this;
        }

        public Criteria andIsAcceptPushBetween(String value1, String value2) {
            addCriterion("is_accept_push between", value1, value2, "isAcceptPush");
            return (Criteria) this;
        }

        public Criteria andIsAcceptPushNotBetween(String value1, String value2) {
            addCriterion("is_accept_push not between", value1, value2, "isAcceptPush");
            return (Criteria) this;
        }

        public Criteria andWeixinXcxSprDtlIdIsNull() {
            addCriterion("weixin_xcx_spr_dtl_id is null");
            return (Criteria) this;
        }

        public Criteria andWeixinXcxSprDtlIdIsNotNull() {
            addCriterion("weixin_xcx_spr_dtl_id is not null");
            return (Criteria) this;
        }

        public Criteria andWeixinXcxSprDtlIdEqualTo(Integer value) {
            addCriterion("weixin_xcx_spr_dtl_id =", value, "weixinXcxSprDtlId");
            return (Criteria) this;
        }

        public Criteria andWeixinXcxSprDtlIdNotEqualTo(Integer value) {
            addCriterion("weixin_xcx_spr_dtl_id <>", value, "weixinXcxSprDtlId");
            return (Criteria) this;
        }

        public Criteria andWeixinXcxSprDtlIdGreaterThan(Integer value) {
            addCriterion("weixin_xcx_spr_dtl_id >", value, "weixinXcxSprDtlId");
            return (Criteria) this;
        }

        public Criteria andWeixinXcxSprDtlIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("weixin_xcx_spr_dtl_id >=", value, "weixinXcxSprDtlId");
            return (Criteria) this;
        }

        public Criteria andWeixinXcxSprDtlIdLessThan(Integer value) {
            addCriterion("weixin_xcx_spr_dtl_id <", value, "weixinXcxSprDtlId");
            return (Criteria) this;
        }

        public Criteria andWeixinXcxSprDtlIdLessThanOrEqualTo(Integer value) {
            addCriterion("weixin_xcx_spr_dtl_id <=", value, "weixinXcxSprDtlId");
            return (Criteria) this;
        }

        public Criteria andWeixinXcxSprDtlIdIn(List<Integer> values) {
            addCriterion("weixin_xcx_spr_dtl_id in", values, "weixinXcxSprDtlId");
            return (Criteria) this;
        }

        public Criteria andWeixinXcxSprDtlIdNotIn(List<Integer> values) {
            addCriterion("weixin_xcx_spr_dtl_id not in", values, "weixinXcxSprDtlId");
            return (Criteria) this;
        }

        public Criteria andWeixinXcxSprDtlIdBetween(Integer value1, Integer value2) {
            addCriterion("weixin_xcx_spr_dtl_id between", value1, value2, "weixinXcxSprDtlId");
            return (Criteria) this;
        }

        public Criteria andWeixinXcxSprDtlIdNotBetween(Integer value1, Integer value2) {
            addCriterion("weixin_xcx_spr_dtl_id not between", value1, value2, "weixinXcxSprDtlId");
            return (Criteria) this;
        }

        public Criteria andNewWeixinXcxSprDtlIdIsNull() {
            addCriterion("new_weixin_xcx_spr_dtl_id is null");
            return (Criteria) this;
        }

        public Criteria andNewWeixinXcxSprDtlIdIsNotNull() {
            addCriterion("new_weixin_xcx_spr_dtl_id is not null");
            return (Criteria) this;
        }

        public Criteria andNewWeixinXcxSprDtlIdEqualTo(Integer value) {
            addCriterion("new_weixin_xcx_spr_dtl_id =", value, "newWeixinXcxSprDtlId");
            return (Criteria) this;
        }

        public Criteria andNewWeixinXcxSprDtlIdNotEqualTo(Integer value) {
            addCriterion("new_weixin_xcx_spr_dtl_id <>", value, "newWeixinXcxSprDtlId");
            return (Criteria) this;
        }

        public Criteria andNewWeixinXcxSprDtlIdGreaterThan(Integer value) {
            addCriterion("new_weixin_xcx_spr_dtl_id >", value, "newWeixinXcxSprDtlId");
            return (Criteria) this;
        }

        public Criteria andNewWeixinXcxSprDtlIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("new_weixin_xcx_spr_dtl_id >=", value, "newWeixinXcxSprDtlId");
            return (Criteria) this;
        }

        public Criteria andNewWeixinXcxSprDtlIdLessThan(Integer value) {
            addCriterion("new_weixin_xcx_spr_dtl_id <", value, "newWeixinXcxSprDtlId");
            return (Criteria) this;
        }

        public Criteria andNewWeixinXcxSprDtlIdLessThanOrEqualTo(Integer value) {
            addCriterion("new_weixin_xcx_spr_dtl_id <=", value, "newWeixinXcxSprDtlId");
            return (Criteria) this;
        }

        public Criteria andNewWeixinXcxSprDtlIdIn(List<Integer> values) {
            addCriterion("new_weixin_xcx_spr_dtl_id in", values, "newWeixinXcxSprDtlId");
            return (Criteria) this;
        }

        public Criteria andNewWeixinXcxSprDtlIdNotIn(List<Integer> values) {
            addCriterion("new_weixin_xcx_spr_dtl_id not in", values, "newWeixinXcxSprDtlId");
            return (Criteria) this;
        }

        public Criteria andNewWeixinXcxSprDtlIdBetween(Integer value1, Integer value2) {
            addCriterion("new_weixin_xcx_spr_dtl_id between", value1, value2, "newWeixinXcxSprDtlId");
            return (Criteria) this;
        }

        public Criteria andNewWeixinXcxSprDtlIdNotBetween(Integer value1, Integer value2) {
            addCriterion("new_weixin_xcx_spr_dtl_id not between", value1, value2, "newWeixinXcxSprDtlId");
            return (Criteria) this;
        }

        public Criteria andWeixinGdtVidIsNull() {
            addCriterion("weixin_gdt_vid is null");
            return (Criteria) this;
        }

        public Criteria andWeixinGdtVidIsNotNull() {
            addCriterion("weixin_gdt_vid is not null");
            return (Criteria) this;
        }

        public Criteria andWeixinGdtVidEqualTo(String value) {
            addCriterion("weixin_gdt_vid =", value, "weixinGdtVid");
            return (Criteria) this;
        }

        public Criteria andWeixinGdtVidNotEqualTo(String value) {
            addCriterion("weixin_gdt_vid <>", value, "weixinGdtVid");
            return (Criteria) this;
        }

        public Criteria andWeixinGdtVidGreaterThan(String value) {
            addCriterion("weixin_gdt_vid >", value, "weixinGdtVid");
            return (Criteria) this;
        }

        public Criteria andWeixinGdtVidGreaterThanOrEqualTo(String value) {
            addCriterion("weixin_gdt_vid >=", value, "weixinGdtVid");
            return (Criteria) this;
        }

        public Criteria andWeixinGdtVidLessThan(String value) {
            addCriterion("weixin_gdt_vid <", value, "weixinGdtVid");
            return (Criteria) this;
        }

        public Criteria andWeixinGdtVidLessThanOrEqualTo(String value) {
            addCriterion("weixin_gdt_vid <=", value, "weixinGdtVid");
            return (Criteria) this;
        }

        public Criteria andWeixinGdtVidLike(String value) {
            addCriterion("weixin_gdt_vid like", value, "weixinGdtVid");
            return (Criteria) this;
        }

        public Criteria andWeixinGdtVidNotLike(String value) {
            addCriterion("weixin_gdt_vid not like", value, "weixinGdtVid");
            return (Criteria) this;
        }

        public Criteria andWeixinGdtVidIn(List<String> values) {
            addCriterion("weixin_gdt_vid in", values, "weixinGdtVid");
            return (Criteria) this;
        }

        public Criteria andWeixinGdtVidNotIn(List<String> values) {
            addCriterion("weixin_gdt_vid not in", values, "weixinGdtVid");
            return (Criteria) this;
        }

        public Criteria andWeixinGdtVidBetween(String value1, String value2) {
            addCriterion("weixin_gdt_vid between", value1, value2, "weixinGdtVid");
            return (Criteria) this;
        }

        public Criteria andWeixinGdtVidNotBetween(String value1, String value2) {
            addCriterion("weixin_gdt_vid not between", value1, value2, "weixinGdtVid");
            return (Criteria) this;
        }

        public Criteria andWeixinAdinfoIsNull() {
            addCriterion("weixin_adinfo is null");
            return (Criteria) this;
        }

        public Criteria andWeixinAdinfoIsNotNull() {
            addCriterion("weixin_adinfo is not null");
            return (Criteria) this;
        }

        public Criteria andWeixinAdinfoEqualTo(String value) {
            addCriterion("weixin_adinfo =", value, "weixinAdinfo");
            return (Criteria) this;
        }

        public Criteria andWeixinAdinfoNotEqualTo(String value) {
            addCriterion("weixin_adinfo <>", value, "weixinAdinfo");
            return (Criteria) this;
        }

        public Criteria andWeixinAdinfoGreaterThan(String value) {
            addCriterion("weixin_adinfo >", value, "weixinAdinfo");
            return (Criteria) this;
        }

        public Criteria andWeixinAdinfoGreaterThanOrEqualTo(String value) {
            addCriterion("weixin_adinfo >=", value, "weixinAdinfo");
            return (Criteria) this;
        }

        public Criteria andWeixinAdinfoLessThan(String value) {
            addCriterion("weixin_adinfo <", value, "weixinAdinfo");
            return (Criteria) this;
        }

        public Criteria andWeixinAdinfoLessThanOrEqualTo(String value) {
            addCriterion("weixin_adinfo <=", value, "weixinAdinfo");
            return (Criteria) this;
        }

        public Criteria andWeixinAdinfoLike(String value) {
            addCriterion("weixin_adinfo like", value, "weixinAdinfo");
            return (Criteria) this;
        }

        public Criteria andWeixinAdinfoNotLike(String value) {
            addCriterion("weixin_adinfo not like", value, "weixinAdinfo");
            return (Criteria) this;
        }

        public Criteria andWeixinAdinfoIn(List<String> values) {
            addCriterion("weixin_adinfo in", values, "weixinAdinfo");
            return (Criteria) this;
        }

        public Criteria andWeixinAdinfoNotIn(List<String> values) {
            addCriterion("weixin_adinfo not in", values, "weixinAdinfo");
            return (Criteria) this;
        }

        public Criteria andWeixinAdinfoBetween(String value1, String value2) {
            addCriterion("weixin_adinfo between", value1, value2, "weixinAdinfo");
            return (Criteria) this;
        }

        public Criteria andWeixinAdinfoNotBetween(String value1, String value2) {
            addCriterion("weixin_adinfo not between", value1, value2, "weixinAdinfo");
            return (Criteria) this;
        }

        public Criteria andCancelReasonIsNull() {
            addCriterion("cancel_reason is null");
            return (Criteria) this;
        }

        public Criteria andCancelReasonIsNotNull() {
            addCriterion("cancel_reason is not null");
            return (Criteria) this;
        }

        public Criteria andCancelReasonEqualTo(String value) {
            addCriterion("cancel_reason =", value, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonNotEqualTo(String value) {
            addCriterion("cancel_reason <>", value, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonGreaterThan(String value) {
            addCriterion("cancel_reason >", value, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonGreaterThanOrEqualTo(String value) {
            addCriterion("cancel_reason >=", value, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonLessThan(String value) {
            addCriterion("cancel_reason <", value, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonLessThanOrEqualTo(String value) {
            addCriterion("cancel_reason <=", value, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonLike(String value) {
            addCriterion("cancel_reason like", value, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonNotLike(String value) {
            addCriterion("cancel_reason not like", value, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonIn(List<String> values) {
            addCriterion("cancel_reason in", values, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonNotIn(List<String> values) {
            addCriterion("cancel_reason not in", values, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonBetween(String value1, String value2) {
            addCriterion("cancel_reason between", value1, value2, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonNotBetween(String value1, String value2) {
            addCriterion("cancel_reason not between", value1, value2, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andLimitFunctionIsNull() {
            addCriterion("limit_function is null");
            return (Criteria) this;
        }

        public Criteria andLimitFunctionIsNotNull() {
            addCriterion("limit_function is not null");
            return (Criteria) this;
        }

        public Criteria andLimitFunctionEqualTo(String value) {
            addCriterion("limit_function =", value, "limitFunction");
            return (Criteria) this;
        }

        public Criteria andLimitFunctionNotEqualTo(String value) {
            addCriterion("limit_function <>", value, "limitFunction");
            return (Criteria) this;
        }

        public Criteria andLimitFunctionGreaterThan(String value) {
            addCriterion("limit_function >", value, "limitFunction");
            return (Criteria) this;
        }

        public Criteria andLimitFunctionGreaterThanOrEqualTo(String value) {
            addCriterion("limit_function >=", value, "limitFunction");
            return (Criteria) this;
        }

        public Criteria andLimitFunctionLessThan(String value) {
            addCriterion("limit_function <", value, "limitFunction");
            return (Criteria) this;
        }

        public Criteria andLimitFunctionLessThanOrEqualTo(String value) {
            addCriterion("limit_function <=", value, "limitFunction");
            return (Criteria) this;
        }

        public Criteria andLimitFunctionLike(String value) {
            addCriterion("limit_function like", value, "limitFunction");
            return (Criteria) this;
        }

        public Criteria andLimitFunctionNotLike(String value) {
            addCriterion("limit_function not like", value, "limitFunction");
            return (Criteria) this;
        }

        public Criteria andLimitFunctionIn(List<String> values) {
            addCriterion("limit_function in", values, "limitFunction");
            return (Criteria) this;
        }

        public Criteria andLimitFunctionNotIn(List<String> values) {
            addCriterion("limit_function not in", values, "limitFunction");
            return (Criteria) this;
        }

        public Criteria andLimitFunctionBetween(String value1, String value2) {
            addCriterion("limit_function between", value1, value2, "limitFunction");
            return (Criteria) this;
        }

        public Criteria andLimitFunctionNotBetween(String value1, String value2) {
            addCriterion("limit_function not between", value1, value2, "limitFunction");
            return (Criteria) this;
        }

        public Criteria andBlackReasonIsNull() {
            addCriterion("black_reason is null");
            return (Criteria) this;
        }

        public Criteria andBlackReasonIsNotNull() {
            addCriterion("black_reason is not null");
            return (Criteria) this;
        }

        public Criteria andBlackReasonEqualTo(String value) {
            addCriterion("black_reason =", value, "blackReason");
            return (Criteria) this;
        }

        public Criteria andBlackReasonNotEqualTo(String value) {
            addCriterion("black_reason <>", value, "blackReason");
            return (Criteria) this;
        }

        public Criteria andBlackReasonGreaterThan(String value) {
            addCriterion("black_reason >", value, "blackReason");
            return (Criteria) this;
        }

        public Criteria andBlackReasonGreaterThanOrEqualTo(String value) {
            addCriterion("black_reason >=", value, "blackReason");
            return (Criteria) this;
        }

        public Criteria andBlackReasonLessThan(String value) {
            addCriterion("black_reason <", value, "blackReason");
            return (Criteria) this;
        }

        public Criteria andBlackReasonLessThanOrEqualTo(String value) {
            addCriterion("black_reason <=", value, "blackReason");
            return (Criteria) this;
        }

        public Criteria andBlackReasonLike(String value) {
            addCriterion("black_reason like", value, "blackReason");
            return (Criteria) this;
        }

        public Criteria andBlackReasonNotLike(String value) {
            addCriterion("black_reason not like", value, "blackReason");
            return (Criteria) this;
        }

        public Criteria andBlackReasonIn(List<String> values) {
            addCriterion("black_reason in", values, "blackReason");
            return (Criteria) this;
        }

        public Criteria andBlackReasonNotIn(List<String> values) {
            addCriterion("black_reason not in", values, "blackReason");
            return (Criteria) this;
        }

        public Criteria andBlackReasonBetween(String value1, String value2) {
            addCriterion("black_reason between", value1, value2, "blackReason");
            return (Criteria) this;
        }

        public Criteria andBlackReasonNotBetween(String value1, String value2) {
            addCriterion("black_reason not between", value1, value2, "blackReason");
            return (Criteria) this;
        }

        public Criteria andBlackInnerRemarksIsNull() {
            addCriterion("black_inner_remarks is null");
            return (Criteria) this;
        }

        public Criteria andBlackInnerRemarksIsNotNull() {
            addCriterion("black_inner_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andBlackInnerRemarksEqualTo(String value) {
            addCriterion("black_inner_remarks =", value, "blackInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andBlackInnerRemarksNotEqualTo(String value) {
            addCriterion("black_inner_remarks <>", value, "blackInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andBlackInnerRemarksGreaterThan(String value) {
            addCriterion("black_inner_remarks >", value, "blackInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andBlackInnerRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("black_inner_remarks >=", value, "blackInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andBlackInnerRemarksLessThan(String value) {
            addCriterion("black_inner_remarks <", value, "blackInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andBlackInnerRemarksLessThanOrEqualTo(String value) {
            addCriterion("black_inner_remarks <=", value, "blackInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andBlackInnerRemarksLike(String value) {
            addCriterion("black_inner_remarks like", value, "blackInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andBlackInnerRemarksNotLike(String value) {
            addCriterion("black_inner_remarks not like", value, "blackInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andBlackInnerRemarksIn(List<String> values) {
            addCriterion("black_inner_remarks in", values, "blackInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andBlackInnerRemarksNotIn(List<String> values) {
            addCriterion("black_inner_remarks not in", values, "blackInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andBlackInnerRemarksBetween(String value1, String value2) {
            addCriterion("black_inner_remarks between", value1, value2, "blackInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andBlackInnerRemarksNotBetween(String value1, String value2) {
            addCriterion("black_inner_remarks not between", value1, value2, "blackInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andIsSvipIsNull() {
            addCriterion("is_svip is null");
            return (Criteria) this;
        }

        public Criteria andIsSvipIsNotNull() {
            addCriterion("is_svip is not null");
            return (Criteria) this;
        }

        public Criteria andIsSvipEqualTo(String value) {
            addCriterion("is_svip =", value, "isSvip");
            return (Criteria) this;
        }

        public Criteria andIsSvipNotEqualTo(String value) {
            addCriterion("is_svip <>", value, "isSvip");
            return (Criteria) this;
        }

        public Criteria andIsSvipGreaterThan(String value) {
            addCriterion("is_svip >", value, "isSvip");
            return (Criteria) this;
        }

        public Criteria andIsSvipGreaterThanOrEqualTo(String value) {
            addCriterion("is_svip >=", value, "isSvip");
            return (Criteria) this;
        }

        public Criteria andIsSvipLessThan(String value) {
            addCriterion("is_svip <", value, "isSvip");
            return (Criteria) this;
        }

        public Criteria andIsSvipLessThanOrEqualTo(String value) {
            addCriterion("is_svip <=", value, "isSvip");
            return (Criteria) this;
        }

        public Criteria andIsSvipLike(String value) {
            addCriterion("is_svip like", value, "isSvip");
            return (Criteria) this;
        }

        public Criteria andIsSvipNotLike(String value) {
            addCriterion("is_svip not like", value, "isSvip");
            return (Criteria) this;
        }

        public Criteria andIsSvipIn(List<String> values) {
            addCriterion("is_svip in", values, "isSvip");
            return (Criteria) this;
        }

        public Criteria andIsSvipNotIn(List<String> values) {
            addCriterion("is_svip not in", values, "isSvip");
            return (Criteria) this;
        }

        public Criteria andIsSvipBetween(String value1, String value2) {
            addCriterion("is_svip between", value1, value2, "isSvip");
            return (Criteria) this;
        }

        public Criteria andIsSvipNotBetween(String value1, String value2) {
            addCriterion("is_svip not between", value1, value2, "isSvip");
            return (Criteria) this;
        }

        public Criteria andSvipExpireDateIsNull() {
            addCriterion("svip_expire_date is null");
            return (Criteria) this;
        }

        public Criteria andSvipExpireDateIsNotNull() {
            addCriterion("svip_expire_date is not null");
            return (Criteria) this;
        }

        public Criteria andSvipExpireDateEqualTo(Date value) {
            addCriterion("svip_expire_date =", value, "svipExpireDate");
            return (Criteria) this;
        }

        public Criteria andSvipExpireDateNotEqualTo(Date value) {
            addCriterion("svip_expire_date <>", value, "svipExpireDate");
            return (Criteria) this;
        }

        public Criteria andSvipExpireDateGreaterThan(Date value) {
            addCriterion("svip_expire_date >", value, "svipExpireDate");
            return (Criteria) this;
        }

        public Criteria andSvipExpireDateGreaterThanOrEqualTo(Date value) {
            addCriterion("svip_expire_date >=", value, "svipExpireDate");
            return (Criteria) this;
        }

        public Criteria andSvipExpireDateLessThan(Date value) {
            addCriterion("svip_expire_date <", value, "svipExpireDate");
            return (Criteria) this;
        }

        public Criteria andSvipExpireDateLessThanOrEqualTo(Date value) {
            addCriterion("svip_expire_date <=", value, "svipExpireDate");
            return (Criteria) this;
        }

        public Criteria andSvipExpireDateIn(List<Date> values) {
            addCriterion("svip_expire_date in", values, "svipExpireDate");
            return (Criteria) this;
        }

        public Criteria andSvipExpireDateNotIn(List<Date> values) {
            addCriterion("svip_expire_date not in", values, "svipExpireDate");
            return (Criteria) this;
        }

        public Criteria andSvipExpireDateBetween(Date value1, Date value2) {
            addCriterion("svip_expire_date between", value1, value2, "svipExpireDate");
            return (Criteria) this;
        }

        public Criteria andSvipExpireDateNotBetween(Date value1, Date value2) {
            addCriterion("svip_expire_date not between", value1, value2, "svipExpireDate");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeIsNull() {
            addCriterion("invitation_code is null");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeIsNotNull() {
            addCriterion("invitation_code is not null");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeEqualTo(String value) {
            addCriterion("invitation_code =", value, "invitationCode");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeNotEqualTo(String value) {
            addCriterion("invitation_code <>", value, "invitationCode");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeGreaterThan(String value) {
            addCriterion("invitation_code >", value, "invitationCode");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeGreaterThanOrEqualTo(String value) {
            addCriterion("invitation_code >=", value, "invitationCode");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeLessThan(String value) {
            addCriterion("invitation_code <", value, "invitationCode");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeLessThanOrEqualTo(String value) {
            addCriterion("invitation_code <=", value, "invitationCode");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeLike(String value) {
            addCriterion("invitation_code like", value, "invitationCode");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeNotLike(String value) {
            addCriterion("invitation_code not like", value, "invitationCode");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeIn(List<String> values) {
            addCriterion("invitation_code in", values, "invitationCode");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeNotIn(List<String> values) {
            addCriterion("invitation_code not in", values, "invitationCode");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeBetween(String value1, String value2) {
            addCriterion("invitation_code between", value1, value2, "invitationCode");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeNotBetween(String value1, String value2) {
            addCriterion("invitation_code not between", value1, value2, "invitationCode");
            return (Criteria) this;
        }

        public Criteria andInvitationMemberIdIsNull() {
            addCriterion("invitation_member_id is null");
            return (Criteria) this;
        }

        public Criteria andInvitationMemberIdIsNotNull() {
            addCriterion("invitation_member_id is not null");
            return (Criteria) this;
        }

        public Criteria andInvitationMemberIdEqualTo(Integer value) {
            addCriterion("invitation_member_id =", value, "invitationMemberId");
            return (Criteria) this;
        }

        public Criteria andInvitationMemberIdNotEqualTo(Integer value) {
            addCriterion("invitation_member_id <>", value, "invitationMemberId");
            return (Criteria) this;
        }

        public Criteria andInvitationMemberIdGreaterThan(Integer value) {
            addCriterion("invitation_member_id >", value, "invitationMemberId");
            return (Criteria) this;
        }

        public Criteria andInvitationMemberIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("invitation_member_id >=", value, "invitationMemberId");
            return (Criteria) this;
        }

        public Criteria andInvitationMemberIdLessThan(Integer value) {
            addCriterion("invitation_member_id <", value, "invitationMemberId");
            return (Criteria) this;
        }

        public Criteria andInvitationMemberIdLessThanOrEqualTo(Integer value) {
            addCriterion("invitation_member_id <=", value, "invitationMemberId");
            return (Criteria) this;
        }

        public Criteria andInvitationMemberIdIn(List<Integer> values) {
            addCriterion("invitation_member_id in", values, "invitationMemberId");
            return (Criteria) this;
        }

        public Criteria andInvitationMemberIdNotIn(List<Integer> values) {
            addCriterion("invitation_member_id not in", values, "invitationMemberId");
            return (Criteria) this;
        }

        public Criteria andInvitationMemberIdBetween(Integer value1, Integer value2) {
            addCriterion("invitation_member_id between", value1, value2, "invitationMemberId");
            return (Criteria) this;
        }

        public Criteria andInvitationMemberIdNotBetween(Integer value1, Integer value2) {
            addCriterion("invitation_member_id not between", value1, value2, "invitationMemberId");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeBindTimeIsNull() {
            addCriterion("invitation_code_bind_time is null");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeBindTimeIsNotNull() {
            addCriterion("invitation_code_bind_time is not null");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeBindTimeEqualTo(Date value) {
            addCriterion("invitation_code_bind_time =", value, "invitationCodeBindTime");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeBindTimeNotEqualTo(Date value) {
            addCriterion("invitation_code_bind_time <>", value, "invitationCodeBindTime");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeBindTimeGreaterThan(Date value) {
            addCriterion("invitation_code_bind_time >", value, "invitationCodeBindTime");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeBindTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("invitation_code_bind_time >=", value, "invitationCodeBindTime");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeBindTimeLessThan(Date value) {
            addCriterion("invitation_code_bind_time <", value, "invitationCodeBindTime");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeBindTimeLessThanOrEqualTo(Date value) {
            addCriterion("invitation_code_bind_time <=", value, "invitationCodeBindTime");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeBindTimeIn(List<Date> values) {
            addCriterion("invitation_code_bind_time in", values, "invitationCodeBindTime");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeBindTimeNotIn(List<Date> values) {
            addCriterion("invitation_code_bind_time not in", values, "invitationCodeBindTime");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeBindTimeBetween(Date value1, Date value2) {
            addCriterion("invitation_code_bind_time between", value1, value2, "invitationCodeBindTime");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeBindTimeNotBetween(Date value1, Date value2) {
            addCriterion("invitation_code_bind_time not between", value1, value2, "invitationCodeBindTime");
            return (Criteria) this;
        }

        public Criteria andNovaProjectBeginDateIsNull() {
            addCriterion("nova_project_begin_date is null");
            return (Criteria) this;
        }

        public Criteria andNovaProjectBeginDateIsNotNull() {
            addCriterion("nova_project_begin_date is not null");
            return (Criteria) this;
        }

        public Criteria andNovaProjectBeginDateEqualTo(Date value) {
            addCriterion("nova_project_begin_date =", value, "novaProjectBeginDate");
            return (Criteria) this;
        }

        public Criteria andNovaProjectBeginDateNotEqualTo(Date value) {
            addCriterion("nova_project_begin_date <>", value, "novaProjectBeginDate");
            return (Criteria) this;
        }

        public Criteria andNovaProjectBeginDateGreaterThan(Date value) {
            addCriterion("nova_project_begin_date >", value, "novaProjectBeginDate");
            return (Criteria) this;
        }

        public Criteria andNovaProjectBeginDateGreaterThanOrEqualTo(Date value) {
            addCriterion("nova_project_begin_date >=", value, "novaProjectBeginDate");
            return (Criteria) this;
        }

        public Criteria andNovaProjectBeginDateLessThan(Date value) {
            addCriterion("nova_project_begin_date <", value, "novaProjectBeginDate");
            return (Criteria) this;
        }

        public Criteria andNovaProjectBeginDateLessThanOrEqualTo(Date value) {
            addCriterion("nova_project_begin_date <=", value, "novaProjectBeginDate");
            return (Criteria) this;
        }

        public Criteria andNovaProjectBeginDateIn(List<Date> values) {
            addCriterion("nova_project_begin_date in", values, "novaProjectBeginDate");
            return (Criteria) this;
        }

        public Criteria andNovaProjectBeginDateNotIn(List<Date> values) {
            addCriterion("nova_project_begin_date not in", values, "novaProjectBeginDate");
            return (Criteria) this;
        }

        public Criteria andNovaProjectBeginDateBetween(Date value1, Date value2) {
            addCriterion("nova_project_begin_date between", value1, value2, "novaProjectBeginDate");
            return (Criteria) this;
        }

        public Criteria andNovaProjectBeginDateNotBetween(Date value1, Date value2) {
            addCriterion("nova_project_begin_date not between", value1, value2, "novaProjectBeginDate");
            return (Criteria) this;
        }

        public Criteria andNovaProjectEndDateIsNull() {
            addCriterion("nova_project_end_date is null");
            return (Criteria) this;
        }

        public Criteria andNovaProjectEndDateIsNotNull() {
            addCriterion("nova_project_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andNovaProjectEndDateEqualTo(Date value) {
            addCriterion("nova_project_end_date =", value, "novaProjectEndDate");
            return (Criteria) this;
        }

        public Criteria andNovaProjectEndDateNotEqualTo(Date value) {
            addCriterion("nova_project_end_date <>", value, "novaProjectEndDate");
            return (Criteria) this;
        }

        public Criteria andNovaProjectEndDateGreaterThan(Date value) {
            addCriterion("nova_project_end_date >", value, "novaProjectEndDate");
            return (Criteria) this;
        }

        public Criteria andNovaProjectEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("nova_project_end_date >=", value, "novaProjectEndDate");
            return (Criteria) this;
        }

        public Criteria andNovaProjectEndDateLessThan(Date value) {
            addCriterion("nova_project_end_date <", value, "novaProjectEndDate");
            return (Criteria) this;
        }

        public Criteria andNovaProjectEndDateLessThanOrEqualTo(Date value) {
            addCriterion("nova_project_end_date <=", value, "novaProjectEndDate");
            return (Criteria) this;
        }

        public Criteria andNovaProjectEndDateIn(List<Date> values) {
            addCriterion("nova_project_end_date in", values, "novaProjectEndDate");
            return (Criteria) this;
        }

        public Criteria andNovaProjectEndDateNotIn(List<Date> values) {
            addCriterion("nova_project_end_date not in", values, "novaProjectEndDate");
            return (Criteria) this;
        }

        public Criteria andNovaProjectEndDateBetween(Date value1, Date value2) {
            addCriterion("nova_project_end_date between", value1, value2, "novaProjectEndDate");
            return (Criteria) this;
        }

        public Criteria andNovaProjectEndDateNotBetween(Date value1, Date value2) {
            addCriterion("nova_project_end_date not between", value1, value2, "novaProjectEndDate");
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