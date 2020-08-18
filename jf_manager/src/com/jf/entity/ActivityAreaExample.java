package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityAreaExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public ActivityAreaExample() {
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

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(String value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(String value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(String value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(String value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(String value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(String value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLike(String value) {
            addCriterion("source like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotLike(String value) {
            addCriterion("source not like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<String> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<String> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(String value1, String value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(String value1, String value2) {
            addCriterion("source not between", value1, value2, "source");
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

        public Criteria andMchtIdIsNull() {
            addCriterion("mcht_id is null");
            return (Criteria) this;
        }

        public Criteria andMchtIdIsNotNull() {
            addCriterion("mcht_id is not null");
            return (Criteria) this;
        }

        public Criteria andMchtIdEqualTo(Integer value) {
            addCriterion("mcht_id =", value, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdNotEqualTo(Integer value) {
            addCriterion("mcht_id <>", value, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdGreaterThan(Integer value) {
            addCriterion("mcht_id >", value, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mcht_id >=", value, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdLessThan(Integer value) {
            addCriterion("mcht_id <", value, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdLessThanOrEqualTo(Integer value) {
            addCriterion("mcht_id <=", value, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdIn(List<Integer> values) {
            addCriterion("mcht_id in", values, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdNotIn(List<Integer> values) {
            addCriterion("mcht_id not in", values, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdBetween(Integer value1, Integer value2) {
            addCriterion("mcht_id between", value1, value2, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mcht_id not between", value1, value2, "mchtId");
            return (Criteria) this;
        }

        public Criteria andEntryPicIsNull() {
            addCriterion("entry_pic is null");
            return (Criteria) this;
        }

        public Criteria andEntryPicIsNotNull() {
            addCriterion("entry_pic is not null");
            return (Criteria) this;
        }

        public Criteria andEntryPicEqualTo(String value) {
            addCriterion("entry_pic =", value, "entryPic");
            return (Criteria) this;
        }

        public Criteria andEntryPicNotEqualTo(String value) {
            addCriterion("entry_pic <>", value, "entryPic");
            return (Criteria) this;
        }

        public Criteria andEntryPicGreaterThan(String value) {
            addCriterion("entry_pic >", value, "entryPic");
            return (Criteria) this;
        }

        public Criteria andEntryPicGreaterThanOrEqualTo(String value) {
            addCriterion("entry_pic >=", value, "entryPic");
            return (Criteria) this;
        }

        public Criteria andEntryPicLessThan(String value) {
            addCriterion("entry_pic <", value, "entryPic");
            return (Criteria) this;
        }

        public Criteria andEntryPicLessThanOrEqualTo(String value) {
            addCriterion("entry_pic <=", value, "entryPic");
            return (Criteria) this;
        }

        public Criteria andEntryPicLike(String value) {
            addCriterion("entry_pic like", value, "entryPic");
            return (Criteria) this;
        }

        public Criteria andEntryPicNotLike(String value) {
            addCriterion("entry_pic not like", value, "entryPic");
            return (Criteria) this;
        }

        public Criteria andEntryPicIn(List<String> values) {
            addCriterion("entry_pic in", values, "entryPic");
            return (Criteria) this;
        }

        public Criteria andEntryPicNotIn(List<String> values) {
            addCriterion("entry_pic not in", values, "entryPic");
            return (Criteria) this;
        }

        public Criteria andEntryPicBetween(String value1, String value2) {
            addCriterion("entry_pic between", value1, value2, "entryPic");
            return (Criteria) this;
        }

        public Criteria andEntryPicNotBetween(String value1, String value2) {
            addCriterion("entry_pic not between", value1, value2, "entryPic");
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

        public Criteria andPreheatTimeIsNull() {
            addCriterion("preheat_time is null");
            return (Criteria) this;
        }

        public Criteria andPreheatTimeIsNotNull() {
            addCriterion("preheat_time is not null");
            return (Criteria) this;
        }

        public Criteria andPreheatTimeEqualTo(Date value) {
            addCriterion("preheat_time =", value, "preheatTime");
            return (Criteria) this;
        }

        public Criteria andPreheatTimeNotEqualTo(Date value) {
            addCriterion("preheat_time <>", value, "preheatTime");
            return (Criteria) this;
        }

        public Criteria andPreheatTimeGreaterThan(Date value) {
            addCriterion("preheat_time >", value, "preheatTime");
            return (Criteria) this;
        }

        public Criteria andPreheatTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("preheat_time >=", value, "preheatTime");
            return (Criteria) this;
        }

        public Criteria andPreheatTimeLessThan(Date value) {
            addCriterion("preheat_time <", value, "preheatTime");
            return (Criteria) this;
        }

        public Criteria andPreheatTimeLessThanOrEqualTo(Date value) {
            addCriterion("preheat_time <=", value, "preheatTime");
            return (Criteria) this;
        }

        public Criteria andPreheatTimeIn(List<Date> values) {
            addCriterion("preheat_time in", values, "preheatTime");
            return (Criteria) this;
        }

        public Criteria andPreheatTimeNotIn(List<Date> values) {
            addCriterion("preheat_time not in", values, "preheatTime");
            return (Criteria) this;
        }

        public Criteria andPreheatTimeBetween(Date value1, Date value2) {
            addCriterion("preheat_time between", value1, value2, "preheatTime");
            return (Criteria) this;
        }

        public Criteria andPreheatTimeNotBetween(Date value1, Date value2) {
            addCriterion("preheat_time not between", value1, value2, "preheatTime");
            return (Criteria) this;
        }

        public Criteria andPreheatFlagIsNull() {
            addCriterion("preheat_flag is null");
            return (Criteria) this;
        }

        public Criteria andPreheatFlagIsNotNull() {
            addCriterion("preheat_flag is not null");
            return (Criteria) this;
        }

        public Criteria andPreheatFlagEqualTo(String value) {
            addCriterion("preheat_flag =", value, "preheatFlag");
            return (Criteria) this;
        }

        public Criteria andPreheatFlagNotEqualTo(String value) {
            addCriterion("preheat_flag <>", value, "preheatFlag");
            return (Criteria) this;
        }

        public Criteria andPreheatFlagGreaterThan(String value) {
            addCriterion("preheat_flag >", value, "preheatFlag");
            return (Criteria) this;
        }

        public Criteria andPreheatFlagGreaterThanOrEqualTo(String value) {
            addCriterion("preheat_flag >=", value, "preheatFlag");
            return (Criteria) this;
        }

        public Criteria andPreheatFlagLessThan(String value) {
            addCriterion("preheat_flag <", value, "preheatFlag");
            return (Criteria) this;
        }

        public Criteria andPreheatFlagLessThanOrEqualTo(String value) {
            addCriterion("preheat_flag <=", value, "preheatFlag");
            return (Criteria) this;
        }

        public Criteria andPreheatFlagLike(String value) {
            addCriterion("preheat_flag like", value, "preheatFlag");
            return (Criteria) this;
        }

        public Criteria andPreheatFlagNotLike(String value) {
            addCriterion("preheat_flag not like", value, "preheatFlag");
            return (Criteria) this;
        }

        public Criteria andPreheatFlagIn(List<String> values) {
            addCriterion("preheat_flag in", values, "preheatFlag");
            return (Criteria) this;
        }

        public Criteria andPreheatFlagNotIn(List<String> values) {
            addCriterion("preheat_flag not in", values, "preheatFlag");
            return (Criteria) this;
        }

        public Criteria andPreheatFlagBetween(String value1, String value2) {
            addCriterion("preheat_flag between", value1, value2, "preheatFlag");
            return (Criteria) this;
        }

        public Criteria andPreheatFlagNotBetween(String value1, String value2) {
            addCriterion("preheat_flag not between", value1, value2, "preheatFlag");
            return (Criteria) this;
        }

        public Criteria andPreheatSeqNoIsNull() {
            addCriterion("preheat_seq_no is null");
            return (Criteria) this;
        }

        public Criteria andPreheatSeqNoIsNotNull() {
            addCriterion("preheat_seq_no is not null");
            return (Criteria) this;
        }

        public Criteria andPreheatSeqNoEqualTo(Integer value) {
            addCriterion("preheat_seq_no =", value, "preheatSeqNo");
            return (Criteria) this;
        }

        public Criteria andPreheatSeqNoNotEqualTo(Integer value) {
            addCriterion("preheat_seq_no <>", value, "preheatSeqNo");
            return (Criteria) this;
        }

        public Criteria andPreheatSeqNoGreaterThan(Integer value) {
            addCriterion("preheat_seq_no >", value, "preheatSeqNo");
            return (Criteria) this;
        }

        public Criteria andPreheatSeqNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("preheat_seq_no >=", value, "preheatSeqNo");
            return (Criteria) this;
        }

        public Criteria andPreheatSeqNoLessThan(Integer value) {
            addCriterion("preheat_seq_no <", value, "preheatSeqNo");
            return (Criteria) this;
        }

        public Criteria andPreheatSeqNoLessThanOrEqualTo(Integer value) {
            addCriterion("preheat_seq_no <=", value, "preheatSeqNo");
            return (Criteria) this;
        }

        public Criteria andPreheatSeqNoIn(List<Integer> values) {
            addCriterion("preheat_seq_no in", values, "preheatSeqNo");
            return (Criteria) this;
        }

        public Criteria andPreheatSeqNoNotIn(List<Integer> values) {
            addCriterion("preheat_seq_no not in", values, "preheatSeqNo");
            return (Criteria) this;
        }

        public Criteria andPreheatSeqNoBetween(Integer value1, Integer value2) {
            addCriterion("preheat_seq_no between", value1, value2, "preheatSeqNo");
            return (Criteria) this;
        }

        public Criteria andPreheatSeqNoNotBetween(Integer value1, Integer value2) {
            addCriterion("preheat_seq_no not between", value1, value2, "preheatSeqNo");
            return (Criteria) this;
        }

        public Criteria andEnrollBeginTimeIsNull() {
            addCriterion("enroll_begin_time is null");
            return (Criteria) this;
        }

        public Criteria andEnrollBeginTimeIsNotNull() {
            addCriterion("enroll_begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andEnrollBeginTimeEqualTo(Date value) {
            addCriterion("enroll_begin_time =", value, "enrollBeginTime");
            return (Criteria) this;
        }

        public Criteria andEnrollBeginTimeNotEqualTo(Date value) {
            addCriterion("enroll_begin_time <>", value, "enrollBeginTime");
            return (Criteria) this;
        }

        public Criteria andEnrollBeginTimeGreaterThan(Date value) {
            addCriterion("enroll_begin_time >", value, "enrollBeginTime");
            return (Criteria) this;
        }

        public Criteria andEnrollBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("enroll_begin_time >=", value, "enrollBeginTime");
            return (Criteria) this;
        }

        public Criteria andEnrollBeginTimeLessThan(Date value) {
            addCriterion("enroll_begin_time <", value, "enrollBeginTime");
            return (Criteria) this;
        }

        public Criteria andEnrollBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("enroll_begin_time <=", value, "enrollBeginTime");
            return (Criteria) this;
        }

        public Criteria andEnrollBeginTimeIn(List<Date> values) {
            addCriterion("enroll_begin_time in", values, "enrollBeginTime");
            return (Criteria) this;
        }

        public Criteria andEnrollBeginTimeNotIn(List<Date> values) {
            addCriterion("enroll_begin_time not in", values, "enrollBeginTime");
            return (Criteria) this;
        }

        public Criteria andEnrollBeginTimeBetween(Date value1, Date value2) {
            addCriterion("enroll_begin_time between", value1, value2, "enrollBeginTime");
            return (Criteria) this;
        }

        public Criteria andEnrollBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("enroll_begin_time not between", value1, value2, "enrollBeginTime");
            return (Criteria) this;
        }

        public Criteria andEnrollEndTimeIsNull() {
            addCriterion("enroll_end_time is null");
            return (Criteria) this;
        }

        public Criteria andEnrollEndTimeIsNotNull() {
            addCriterion("enroll_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEnrollEndTimeEqualTo(Date value) {
            addCriterion("enroll_end_time =", value, "enrollEndTime");
            return (Criteria) this;
        }

        public Criteria andEnrollEndTimeNotEqualTo(Date value) {
            addCriterion("enroll_end_time <>", value, "enrollEndTime");
            return (Criteria) this;
        }

        public Criteria andEnrollEndTimeGreaterThan(Date value) {
            addCriterion("enroll_end_time >", value, "enrollEndTime");
            return (Criteria) this;
        }

        public Criteria andEnrollEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("enroll_end_time >=", value, "enrollEndTime");
            return (Criteria) this;
        }

        public Criteria andEnrollEndTimeLessThan(Date value) {
            addCriterion("enroll_end_time <", value, "enrollEndTime");
            return (Criteria) this;
        }

        public Criteria andEnrollEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("enroll_end_time <=", value, "enrollEndTime");
            return (Criteria) this;
        }

        public Criteria andEnrollEndTimeIn(List<Date> values) {
            addCriterion("enroll_end_time in", values, "enrollEndTime");
            return (Criteria) this;
        }

        public Criteria andEnrollEndTimeNotIn(List<Date> values) {
            addCriterion("enroll_end_time not in", values, "enrollEndTime");
            return (Criteria) this;
        }

        public Criteria andEnrollEndTimeBetween(Date value1, Date value2) {
            addCriterion("enroll_end_time between", value1, value2, "enrollEndTime");
            return (Criteria) this;
        }

        public Criteria andEnrollEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("enroll_end_time not between", value1, value2, "enrollEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityBeginTimeIsNull() {
            addCriterion("activity_begin_time is null");
            return (Criteria) this;
        }

        public Criteria andActivityBeginTimeIsNotNull() {
            addCriterion("activity_begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andActivityBeginTimeEqualTo(Date value) {
            addCriterion("activity_begin_time =", value, "activityBeginTime");
            return (Criteria) this;
        }

        public Criteria andActivityBeginTimeNotEqualTo(Date value) {
            addCriterion("activity_begin_time <>", value, "activityBeginTime");
            return (Criteria) this;
        }

        public Criteria andActivityBeginTimeGreaterThan(Date value) {
            addCriterion("activity_begin_time >", value, "activityBeginTime");
            return (Criteria) this;
        }

        public Criteria andActivityBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("activity_begin_time >=", value, "activityBeginTime");
            return (Criteria) this;
        }

        public Criteria andActivityBeginTimeLessThan(Date value) {
            addCriterion("activity_begin_time <", value, "activityBeginTime");
            return (Criteria) this;
        }

        public Criteria andActivityBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("activity_begin_time <=", value, "activityBeginTime");
            return (Criteria) this;
        }

        public Criteria andActivityBeginTimeIn(List<Date> values) {
            addCriterion("activity_begin_time in", values, "activityBeginTime");
            return (Criteria) this;
        }

        public Criteria andActivityBeginTimeNotIn(List<Date> values) {
            addCriterion("activity_begin_time not in", values, "activityBeginTime");
            return (Criteria) this;
        }

        public Criteria andActivityBeginTimeBetween(Date value1, Date value2) {
            addCriterion("activity_begin_time between", value1, value2, "activityBeginTime");
            return (Criteria) this;
        }

        public Criteria andActivityBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("activity_begin_time not between", value1, value2, "activityBeginTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeIsNull() {
            addCriterion("activity_end_time is null");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeIsNotNull() {
            addCriterion("activity_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeEqualTo(Date value) {
            addCriterion("activity_end_time =", value, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeNotEqualTo(Date value) {
            addCriterion("activity_end_time <>", value, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeGreaterThan(Date value) {
            addCriterion("activity_end_time >", value, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("activity_end_time >=", value, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeLessThan(Date value) {
            addCriterion("activity_end_time <", value, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("activity_end_time <=", value, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeIn(List<Date> values) {
            addCriterion("activity_end_time in", values, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeNotIn(List<Date> values) {
            addCriterion("activity_end_time not in", values, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeBetween(Date value1, Date value2) {
            addCriterion("activity_end_time between", value1, value2, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("activity_end_time not between", value1, value2, "activityEndTime");
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

        public Criteria andLimitMchtNumIsNull() {
            addCriterion("limit_mcht_num is null");
            return (Criteria) this;
        }

        public Criteria andLimitMchtNumIsNotNull() {
            addCriterion("limit_mcht_num is not null");
            return (Criteria) this;
        }

        public Criteria andLimitMchtNumEqualTo(Integer value) {
            addCriterion("limit_mcht_num =", value, "limitMchtNum");
            return (Criteria) this;
        }

        public Criteria andLimitMchtNumNotEqualTo(Integer value) {
            addCriterion("limit_mcht_num <>", value, "limitMchtNum");
            return (Criteria) this;
        }

        public Criteria andLimitMchtNumGreaterThan(Integer value) {
            addCriterion("limit_mcht_num >", value, "limitMchtNum");
            return (Criteria) this;
        }

        public Criteria andLimitMchtNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("limit_mcht_num >=", value, "limitMchtNum");
            return (Criteria) this;
        }

        public Criteria andLimitMchtNumLessThan(Integer value) {
            addCriterion("limit_mcht_num <", value, "limitMchtNum");
            return (Criteria) this;
        }

        public Criteria andLimitMchtNumLessThanOrEqualTo(Integer value) {
            addCriterion("limit_mcht_num <=", value, "limitMchtNum");
            return (Criteria) this;
        }

        public Criteria andLimitMchtNumIn(List<Integer> values) {
            addCriterion("limit_mcht_num in", values, "limitMchtNum");
            return (Criteria) this;
        }

        public Criteria andLimitMchtNumNotIn(List<Integer> values) {
            addCriterion("limit_mcht_num not in", values, "limitMchtNum");
            return (Criteria) this;
        }

        public Criteria andLimitMchtNumBetween(Integer value1, Integer value2) {
            addCriterion("limit_mcht_num between", value1, value2, "limitMchtNum");
            return (Criteria) this;
        }

        public Criteria andLimitMchtNumNotBetween(Integer value1, Integer value2) {
            addCriterion("limit_mcht_num not between", value1, value2, "limitMchtNum");
            return (Criteria) this;
        }

        public Criteria andBenefitPointIsNull() {
            addCriterion("benefit_point is null");
            return (Criteria) this;
        }

        public Criteria andBenefitPointIsNotNull() {
            addCriterion("benefit_point is not null");
            return (Criteria) this;
        }

        public Criteria andBenefitPointEqualTo(String value) {
            addCriterion("benefit_point =", value, "benefitPoint");
            return (Criteria) this;
        }

        public Criteria andBenefitPointNotEqualTo(String value) {
            addCriterion("benefit_point <>", value, "benefitPoint");
            return (Criteria) this;
        }

        public Criteria andBenefitPointGreaterThan(String value) {
            addCriterion("benefit_point >", value, "benefitPoint");
            return (Criteria) this;
        }

        public Criteria andBenefitPointGreaterThanOrEqualTo(String value) {
            addCriterion("benefit_point >=", value, "benefitPoint");
            return (Criteria) this;
        }

        public Criteria andBenefitPointLessThan(String value) {
            addCriterion("benefit_point <", value, "benefitPoint");
            return (Criteria) this;
        }

        public Criteria andBenefitPointLessThanOrEqualTo(String value) {
            addCriterion("benefit_point <=", value, "benefitPoint");
            return (Criteria) this;
        }

        public Criteria andBenefitPointLike(String value) {
            addCriterion("benefit_point like", value, "benefitPoint");
            return (Criteria) this;
        }

        public Criteria andBenefitPointNotLike(String value) {
            addCriterion("benefit_point not like", value, "benefitPoint");
            return (Criteria) this;
        }

        public Criteria andBenefitPointIn(List<String> values) {
            addCriterion("benefit_point in", values, "benefitPoint");
            return (Criteria) this;
        }

        public Criteria andBenefitPointNotIn(List<String> values) {
            addCriterion("benefit_point not in", values, "benefitPoint");
            return (Criteria) this;
        }

        public Criteria andBenefitPointBetween(String value1, String value2) {
            addCriterion("benefit_point between", value1, value2, "benefitPoint");
            return (Criteria) this;
        }

        public Criteria andBenefitPointNotBetween(String value1, String value2) {
            addCriterion("benefit_point not between", value1, value2, "benefitPoint");
            return (Criteria) this;
        }

        public Criteria andMinMemberGroupIsNull() {
            addCriterion("min_member_group is null");
            return (Criteria) this;
        }

        public Criteria andMinMemberGroupIsNotNull() {
            addCriterion("min_member_group is not null");
            return (Criteria) this;
        }

        public Criteria andMinMemberGroupEqualTo(Integer value) {
            addCriterion("min_member_group =", value, "minMemberGroup");
            return (Criteria) this;
        }

        public Criteria andMinMemberGroupNotEqualTo(Integer value) {
            addCriterion("min_member_group <>", value, "minMemberGroup");
            return (Criteria) this;
        }

        public Criteria andMinMemberGroupGreaterThan(Integer value) {
            addCriterion("min_member_group >", value, "minMemberGroup");
            return (Criteria) this;
        }

        public Criteria andMinMemberGroupGreaterThanOrEqualTo(Integer value) {
            addCriterion("min_member_group >=", value, "minMemberGroup");
            return (Criteria) this;
        }

        public Criteria andMinMemberGroupLessThan(Integer value) {
            addCriterion("min_member_group <", value, "minMemberGroup");
            return (Criteria) this;
        }

        public Criteria andMinMemberGroupLessThanOrEqualTo(Integer value) {
            addCriterion("min_member_group <=", value, "minMemberGroup");
            return (Criteria) this;
        }

        public Criteria andMinMemberGroupIn(List<Integer> values) {
            addCriterion("min_member_group in", values, "minMemberGroup");
            return (Criteria) this;
        }

        public Criteria andMinMemberGroupNotIn(List<Integer> values) {
            addCriterion("min_member_group not in", values, "minMemberGroup");
            return (Criteria) this;
        }

        public Criteria andMinMemberGroupBetween(Integer value1, Integer value2) {
            addCriterion("min_member_group between", value1, value2, "minMemberGroup");
            return (Criteria) this;
        }

        public Criteria andMinMemberGroupNotBetween(Integer value1, Integer value2) {
            addCriterion("min_member_group not between", value1, value2, "minMemberGroup");
            return (Criteria) this;
        }

        public Criteria andPushMchtTypeIsNull() {
            addCriterion("push_mcht_type is null");
            return (Criteria) this;
        }

        public Criteria andPushMchtTypeIsNotNull() {
            addCriterion("push_mcht_type is not null");
            return (Criteria) this;
        }

        public Criteria andPushMchtTypeEqualTo(String value) {
            addCriterion("push_mcht_type =", value, "pushMchtType");
            return (Criteria) this;
        }

        public Criteria andPushMchtTypeNotEqualTo(String value) {
            addCriterion("push_mcht_type <>", value, "pushMchtType");
            return (Criteria) this;
        }

        public Criteria andPushMchtTypeGreaterThan(String value) {
            addCriterion("push_mcht_type >", value, "pushMchtType");
            return (Criteria) this;
        }

        public Criteria andPushMchtTypeGreaterThanOrEqualTo(String value) {
            addCriterion("push_mcht_type >=", value, "pushMchtType");
            return (Criteria) this;
        }

        public Criteria andPushMchtTypeLessThan(String value) {
            addCriterion("push_mcht_type <", value, "pushMchtType");
            return (Criteria) this;
        }

        public Criteria andPushMchtTypeLessThanOrEqualTo(String value) {
            addCriterion("push_mcht_type <=", value, "pushMchtType");
            return (Criteria) this;
        }

        public Criteria andPushMchtTypeLike(String value) {
            addCriterion("push_mcht_type like", value, "pushMchtType");
            return (Criteria) this;
        }

        public Criteria andPushMchtTypeNotLike(String value) {
            addCriterion("push_mcht_type not like", value, "pushMchtType");
            return (Criteria) this;
        }

        public Criteria andPushMchtTypeIn(List<String> values) {
            addCriterion("push_mcht_type in", values, "pushMchtType");
            return (Criteria) this;
        }

        public Criteria andPushMchtTypeNotIn(List<String> values) {
            addCriterion("push_mcht_type not in", values, "pushMchtType");
            return (Criteria) this;
        }

        public Criteria andPushMchtTypeBetween(String value1, String value2) {
            addCriterion("push_mcht_type between", value1, value2, "pushMchtType");
            return (Criteria) this;
        }

        public Criteria andPushMchtTypeNotBetween(String value1, String value2) {
            addCriterion("push_mcht_type not between", value1, value2, "pushMchtType");
            return (Criteria) this;
        }

        public Criteria andMchtIdGroupIsNull() {
            addCriterion("mcht_id_group is null");
            return (Criteria) this;
        }

        public Criteria andMchtIdGroupIsNotNull() {
            addCriterion("mcht_id_group is not null");
            return (Criteria) this;
        }

        public Criteria andMchtIdGroupEqualTo(String value) {
            addCriterion("mcht_id_group =", value, "mchtIdGroup");
            return (Criteria) this;
        }

        public Criteria andMchtIdGroupNotEqualTo(String value) {
            addCriterion("mcht_id_group <>", value, "mchtIdGroup");
            return (Criteria) this;
        }

        public Criteria andMchtIdGroupGreaterThan(String value) {
            addCriterion("mcht_id_group >", value, "mchtIdGroup");
            return (Criteria) this;
        }

        public Criteria andMchtIdGroupGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_id_group >=", value, "mchtIdGroup");
            return (Criteria) this;
        }

        public Criteria andMchtIdGroupLessThan(String value) {
            addCriterion("mcht_id_group <", value, "mchtIdGroup");
            return (Criteria) this;
        }

        public Criteria andMchtIdGroupLessThanOrEqualTo(String value) {
            addCriterion("mcht_id_group <=", value, "mchtIdGroup");
            return (Criteria) this;
        }

        public Criteria andMchtIdGroupLike(String value) {
            addCriterion("mcht_id_group like", value, "mchtIdGroup");
            return (Criteria) this;
        }

        public Criteria andMchtIdGroupNotLike(String value) {
            addCriterion("mcht_id_group not like", value, "mchtIdGroup");
            return (Criteria) this;
        }

        public Criteria andMchtIdGroupIn(List<String> values) {
            addCriterion("mcht_id_group in", values, "mchtIdGroup");
            return (Criteria) this;
        }

        public Criteria andMchtIdGroupNotIn(List<String> values) {
            addCriterion("mcht_id_group not in", values, "mchtIdGroup");
            return (Criteria) this;
        }

        public Criteria andMchtIdGroupBetween(String value1, String value2) {
            addCriterion("mcht_id_group between", value1, value2, "mchtIdGroup");
            return (Criteria) this;
        }

        public Criteria andMchtIdGroupNotBetween(String value1, String value2) {
            addCriterion("mcht_id_group not between", value1, value2, "mchtIdGroup");
            return (Criteria) this;
        }

        public Criteria andProductTypeGroupIsNull() {
            addCriterion("product_type_group is null");
            return (Criteria) this;
        }

        public Criteria andProductTypeGroupIsNotNull() {
            addCriterion("product_type_group is not null");
            return (Criteria) this;
        }

        public Criteria andProductTypeGroupEqualTo(String value) {
            addCriterion("product_type_group =", value, "productTypeGroup");
            return (Criteria) this;
        }

        public Criteria andProductTypeGroupNotEqualTo(String value) {
            addCriterion("product_type_group <>", value, "productTypeGroup");
            return (Criteria) this;
        }

        public Criteria andProductTypeGroupGreaterThan(String value) {
            addCriterion("product_type_group >", value, "productTypeGroup");
            return (Criteria) this;
        }

        public Criteria andProductTypeGroupGreaterThanOrEqualTo(String value) {
            addCriterion("product_type_group >=", value, "productTypeGroup");
            return (Criteria) this;
        }

        public Criteria andProductTypeGroupLessThan(String value) {
            addCriterion("product_type_group <", value, "productTypeGroup");
            return (Criteria) this;
        }

        public Criteria andProductTypeGroupLessThanOrEqualTo(String value) {
            addCriterion("product_type_group <=", value, "productTypeGroup");
            return (Criteria) this;
        }

        public Criteria andProductTypeGroupLike(String value) {
            addCriterion("product_type_group like", value, "productTypeGroup");
            return (Criteria) this;
        }

        public Criteria andProductTypeGroupNotLike(String value) {
            addCriterion("product_type_group not like", value, "productTypeGroup");
            return (Criteria) this;
        }

        public Criteria andProductTypeGroupIn(List<String> values) {
            addCriterion("product_type_group in", values, "productTypeGroup");
            return (Criteria) this;
        }

        public Criteria andProductTypeGroupNotIn(List<String> values) {
            addCriterion("product_type_group not in", values, "productTypeGroup");
            return (Criteria) this;
        }

        public Criteria andProductTypeGroupBetween(String value1, String value2) {
            addCriterion("product_type_group between", value1, value2, "productTypeGroup");
            return (Criteria) this;
        }

        public Criteria andProductTypeGroupNotBetween(String value1, String value2) {
            addCriterion("product_type_group not between", value1, value2, "productTypeGroup");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeIsNull() {
            addCriterion("preferential_type is null");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeIsNotNull() {
            addCriterion("preferential_type is not null");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeEqualTo(String value) {
            addCriterion("preferential_type =", value, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeNotEqualTo(String value) {
            addCriterion("preferential_type <>", value, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeGreaterThan(String value) {
            addCriterion("preferential_type >", value, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeGreaterThanOrEqualTo(String value) {
            addCriterion("preferential_type >=", value, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeLessThan(String value) {
            addCriterion("preferential_type <", value, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeLessThanOrEqualTo(String value) {
            addCriterion("preferential_type <=", value, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeLike(String value) {
            addCriterion("preferential_type like", value, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeNotLike(String value) {
            addCriterion("preferential_type not like", value, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeIn(List<String> values) {
            addCriterion("preferential_type in", values, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeNotIn(List<String> values) {
            addCriterion("preferential_type not in", values, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeBetween(String value1, String value2) {
            addCriterion("preferential_type between", value1, value2, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeNotBetween(String value1, String value2) {
            addCriterion("preferential_type not between", value1, value2, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialIdGroupIsNull() {
            addCriterion("preferential_id_group is null");
            return (Criteria) this;
        }

        public Criteria andPreferentialIdGroupIsNotNull() {
            addCriterion("preferential_id_group is not null");
            return (Criteria) this;
        }

        public Criteria andPreferentialIdGroupEqualTo(String value) {
            addCriterion("preferential_id_group =", value, "preferentialIdGroup");
            return (Criteria) this;
        }

        public Criteria andPreferentialIdGroupNotEqualTo(String value) {
            addCriterion("preferential_id_group <>", value, "preferentialIdGroup");
            return (Criteria) this;
        }

        public Criteria andPreferentialIdGroupGreaterThan(String value) {
            addCriterion("preferential_id_group >", value, "preferentialIdGroup");
            return (Criteria) this;
        }

        public Criteria andPreferentialIdGroupGreaterThanOrEqualTo(String value) {
            addCriterion("preferential_id_group >=", value, "preferentialIdGroup");
            return (Criteria) this;
        }

        public Criteria andPreferentialIdGroupLessThan(String value) {
            addCriterion("preferential_id_group <", value, "preferentialIdGroup");
            return (Criteria) this;
        }

        public Criteria andPreferentialIdGroupLessThanOrEqualTo(String value) {
            addCriterion("preferential_id_group <=", value, "preferentialIdGroup");
            return (Criteria) this;
        }

        public Criteria andPreferentialIdGroupLike(String value) {
            addCriterion("preferential_id_group like", value, "preferentialIdGroup");
            return (Criteria) this;
        }

        public Criteria andPreferentialIdGroupNotLike(String value) {
            addCriterion("preferential_id_group not like", value, "preferentialIdGroup");
            return (Criteria) this;
        }

        public Criteria andPreferentialIdGroupIn(List<String> values) {
            addCriterion("preferential_id_group in", values, "preferentialIdGroup");
            return (Criteria) this;
        }

        public Criteria andPreferentialIdGroupNotIn(List<String> values) {
            addCriterion("preferential_id_group not in", values, "preferentialIdGroup");
            return (Criteria) this;
        }

        public Criteria andPreferentialIdGroupBetween(String value1, String value2) {
            addCriterion("preferential_id_group between", value1, value2, "preferentialIdGroup");
            return (Criteria) this;
        }

        public Criteria andPreferentialIdGroupNotBetween(String value1, String value2) {
            addCriterion("preferential_id_group not between", value1, value2, "preferentialIdGroup");
            return (Criteria) this;
        }

        public Criteria andUrlSuffixIsNull() {
            addCriterion("url_suffix is null");
            return (Criteria) this;
        }

        public Criteria andUrlSuffixIsNotNull() {
            addCriterion("url_suffix is not null");
            return (Criteria) this;
        }

        public Criteria andUrlSuffixEqualTo(String value) {
            addCriterion("url_suffix =", value, "urlSuffix");
            return (Criteria) this;
        }

        public Criteria andUrlSuffixNotEqualTo(String value) {
            addCriterion("url_suffix <>", value, "urlSuffix");
            return (Criteria) this;
        }

        public Criteria andUrlSuffixGreaterThan(String value) {
            addCriterion("url_suffix >", value, "urlSuffix");
            return (Criteria) this;
        }

        public Criteria andUrlSuffixGreaterThanOrEqualTo(String value) {
            addCriterion("url_suffix >=", value, "urlSuffix");
            return (Criteria) this;
        }

        public Criteria andUrlSuffixLessThan(String value) {
            addCriterion("url_suffix <", value, "urlSuffix");
            return (Criteria) this;
        }

        public Criteria andUrlSuffixLessThanOrEqualTo(String value) {
            addCriterion("url_suffix <=", value, "urlSuffix");
            return (Criteria) this;
        }

        public Criteria andUrlSuffixLike(String value) {
            addCriterion("url_suffix like", value, "urlSuffix");
            return (Criteria) this;
        }

        public Criteria andUrlSuffixNotLike(String value) {
            addCriterion("url_suffix not like", value, "urlSuffix");
            return (Criteria) this;
        }

        public Criteria andUrlSuffixIn(List<String> values) {
            addCriterion("url_suffix in", values, "urlSuffix");
            return (Criteria) this;
        }

        public Criteria andUrlSuffixNotIn(List<String> values) {
            addCriterion("url_suffix not in", values, "urlSuffix");
            return (Criteria) this;
        }

        public Criteria andUrlSuffixBetween(String value1, String value2) {
            addCriterion("url_suffix between", value1, value2, "urlSuffix");
            return (Criteria) this;
        }

        public Criteria andUrlSuffixNotBetween(String value1, String value2) {
            addCriterion("url_suffix not between", value1, value2, "urlSuffix");
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

        public Criteria andTopPicIsNull() {
            addCriterion("top_pic is null");
            return (Criteria) this;
        }

        public Criteria andTopPicIsNotNull() {
            addCriterion("top_pic is not null");
            return (Criteria) this;
        }

        public Criteria andTopPicEqualTo(String value) {
            addCriterion("top_pic =", value, "topPic");
            return (Criteria) this;
        }

        public Criteria andTopPicNotEqualTo(String value) {
            addCriterion("top_pic <>", value, "topPic");
            return (Criteria) this;
        }

        public Criteria andTopPicGreaterThan(String value) {
            addCriterion("top_pic >", value, "topPic");
            return (Criteria) this;
        }

        public Criteria andTopPicGreaterThanOrEqualTo(String value) {
            addCriterion("top_pic >=", value, "topPic");
            return (Criteria) this;
        }

        public Criteria andTopPicLessThan(String value) {
            addCriterion("top_pic <", value, "topPic");
            return (Criteria) this;
        }

        public Criteria andTopPicLessThanOrEqualTo(String value) {
            addCriterion("top_pic <=", value, "topPic");
            return (Criteria) this;
        }

        public Criteria andTopPicLike(String value) {
            addCriterion("top_pic like", value, "topPic");
            return (Criteria) this;
        }

        public Criteria andTopPicNotLike(String value) {
            addCriterion("top_pic not like", value, "topPic");
            return (Criteria) this;
        }

        public Criteria andTopPicIn(List<String> values) {
            addCriterion("top_pic in", values, "topPic");
            return (Criteria) this;
        }

        public Criteria andTopPicNotIn(List<String> values) {
            addCriterion("top_pic not in", values, "topPic");
            return (Criteria) this;
        }

        public Criteria andTopPicBetween(String value1, String value2) {
            addCriterion("top_pic between", value1, value2, "topPic");
            return (Criteria) this;
        }

        public Criteria andTopPicNotBetween(String value1, String value2) {
            addCriterion("top_pic not between", value1, value2, "topPic");
            return (Criteria) this;
        }

        public Criteria andTempletTypeIsNull() {
            addCriterion("templet_type is null");
            return (Criteria) this;
        }

        public Criteria andTempletTypeIsNotNull() {
            addCriterion("templet_type is not null");
            return (Criteria) this;
        }

        public Criteria andTempletTypeEqualTo(String value) {
            addCriterion("templet_type =", value, "templetType");
            return (Criteria) this;
        }

        public Criteria andTempletTypeNotEqualTo(String value) {
            addCriterion("templet_type <>", value, "templetType");
            return (Criteria) this;
        }

        public Criteria andTempletTypeGreaterThan(String value) {
            addCriterion("templet_type >", value, "templetType");
            return (Criteria) this;
        }

        public Criteria andTempletTypeGreaterThanOrEqualTo(String value) {
            addCriterion("templet_type >=", value, "templetType");
            return (Criteria) this;
        }

        public Criteria andTempletTypeLessThan(String value) {
            addCriterion("templet_type <", value, "templetType");
            return (Criteria) this;
        }

        public Criteria andTempletTypeLessThanOrEqualTo(String value) {
            addCriterion("templet_type <=", value, "templetType");
            return (Criteria) this;
        }

        public Criteria andTempletTypeLike(String value) {
            addCriterion("templet_type like", value, "templetType");
            return (Criteria) this;
        }

        public Criteria andTempletTypeNotLike(String value) {
            addCriterion("templet_type not like", value, "templetType");
            return (Criteria) this;
        }

        public Criteria andTempletTypeIn(List<String> values) {
            addCriterion("templet_type in", values, "templetType");
            return (Criteria) this;
        }

        public Criteria andTempletTypeNotIn(List<String> values) {
            addCriterion("templet_type not in", values, "templetType");
            return (Criteria) this;
        }

        public Criteria andTempletTypeBetween(String value1, String value2) {
            addCriterion("templet_type between", value1, value2, "templetType");
            return (Criteria) this;
        }

        public Criteria andTempletTypeNotBetween(String value1, String value2) {
            addCriterion("templet_type not between", value1, value2, "templetType");
            return (Criteria) this;
        }

        public Criteria andTempletSuffixIsNull() {
            addCriterion("templet_suffix is null");
            return (Criteria) this;
        }

        public Criteria andTempletSuffixIsNotNull() {
            addCriterion("templet_suffix is not null");
            return (Criteria) this;
        }

        public Criteria andTempletSuffixEqualTo(String value) {
            addCriterion("templet_suffix =", value, "templetSuffix");
            return (Criteria) this;
        }

        public Criteria andTempletSuffixNotEqualTo(String value) {
            addCriterion("templet_suffix <>", value, "templetSuffix");
            return (Criteria) this;
        }

        public Criteria andTempletSuffixGreaterThan(String value) {
            addCriterion("templet_suffix >", value, "templetSuffix");
            return (Criteria) this;
        }

        public Criteria andTempletSuffixGreaterThanOrEqualTo(String value) {
            addCriterion("templet_suffix >=", value, "templetSuffix");
            return (Criteria) this;
        }

        public Criteria andTempletSuffixLessThan(String value) {
            addCriterion("templet_suffix <", value, "templetSuffix");
            return (Criteria) this;
        }

        public Criteria andTempletSuffixLessThanOrEqualTo(String value) {
            addCriterion("templet_suffix <=", value, "templetSuffix");
            return (Criteria) this;
        }

        public Criteria andTempletSuffixLike(String value) {
            addCriterion("templet_suffix like", value, "templetSuffix");
            return (Criteria) this;
        }

        public Criteria andTempletSuffixNotLike(String value) {
            addCriterion("templet_suffix not like", value, "templetSuffix");
            return (Criteria) this;
        }

        public Criteria andTempletSuffixIn(List<String> values) {
            addCriterion("templet_suffix in", values, "templetSuffix");
            return (Criteria) this;
        }

        public Criteria andTempletSuffixNotIn(List<String> values) {
            addCriterion("templet_suffix not in", values, "templetSuffix");
            return (Criteria) this;
        }

        public Criteria andTempletSuffixBetween(String value1, String value2) {
            addCriterion("templet_suffix between", value1, value2, "templetSuffix");
            return (Criteria) this;
        }

        public Criteria andTempletSuffixNotBetween(String value1, String value2) {
            addCriterion("templet_suffix not between", value1, value2, "templetSuffix");
            return (Criteria) this;
        }

        public Criteria andActivityGroupIdIsNull() {
            addCriterion("activity_group_id is null");
            return (Criteria) this;
        }

        public Criteria andActivityGroupIdIsNotNull() {
            addCriterion("activity_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andActivityGroupIdEqualTo(Integer value) {
            addCriterion("activity_group_id =", value, "activityGroupId");
            return (Criteria) this;
        }

        public Criteria andActivityGroupIdNotEqualTo(Integer value) {
            addCriterion("activity_group_id <>", value, "activityGroupId");
            return (Criteria) this;
        }

        public Criteria andActivityGroupIdGreaterThan(Integer value) {
            addCriterion("activity_group_id >", value, "activityGroupId");
            return (Criteria) this;
        }

        public Criteria andActivityGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("activity_group_id >=", value, "activityGroupId");
            return (Criteria) this;
        }

        public Criteria andActivityGroupIdLessThan(Integer value) {
            addCriterion("activity_group_id <", value, "activityGroupId");
            return (Criteria) this;
        }

        public Criteria andActivityGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("activity_group_id <=", value, "activityGroupId");
            return (Criteria) this;
        }

        public Criteria andActivityGroupIdIn(List<Integer> values) {
            addCriterion("activity_group_id in", values, "activityGroupId");
            return (Criteria) this;
        }

        public Criteria andActivityGroupIdNotIn(List<Integer> values) {
            addCriterion("activity_group_id not in", values, "activityGroupId");
            return (Criteria) this;
        }

        public Criteria andActivityGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("activity_group_id between", value1, value2, "activityGroupId");
            return (Criteria) this;
        }

        public Criteria andActivityGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("activity_group_id not between", value1, value2, "activityGroupId");
            return (Criteria) this;
        }

        public Criteria andIsSignIsNull() {
            addCriterion("is_sign is null");
            return (Criteria) this;
        }

        public Criteria andIsSignIsNotNull() {
            addCriterion("is_sign is not null");
            return (Criteria) this;
        }

        public Criteria andIsSignEqualTo(String value) {
            addCriterion("is_sign =", value, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignNotEqualTo(String value) {
            addCriterion("is_sign <>", value, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignGreaterThan(String value) {
            addCriterion("is_sign >", value, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignGreaterThanOrEqualTo(String value) {
            addCriterion("is_sign >=", value, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignLessThan(String value) {
            addCriterion("is_sign <", value, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignLessThanOrEqualTo(String value) {
            addCriterion("is_sign <=", value, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignLike(String value) {
            addCriterion("is_sign like", value, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignNotLike(String value) {
            addCriterion("is_sign not like", value, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignIn(List<String> values) {
            addCriterion("is_sign in", values, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignNotIn(List<String> values) {
            addCriterion("is_sign not in", values, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignBetween(String value1, String value2) {
            addCriterion("is_sign between", value1, value2, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignNotBetween(String value1, String value2) {
            addCriterion("is_sign not between", value1, value2, "isSign");
            return (Criteria) this;
        }

        public Criteria andAreaLabelIsNull() {
            addCriterion("area_label is null");
            return (Criteria) this;
        }

        public Criteria andAreaLabelIsNotNull() {
            addCriterion("area_label is not null");
            return (Criteria) this;
        }

        public Criteria andAreaLabelEqualTo(String value) {
            addCriterion("area_label =", value, "areaLabel");
            return (Criteria) this;
        }

        public Criteria andAreaLabelNotEqualTo(String value) {
            addCriterion("area_label <>", value, "areaLabel");
            return (Criteria) this;
        }

        public Criteria andAreaLabelGreaterThan(String value) {
            addCriterion("area_label >", value, "areaLabel");
            return (Criteria) this;
        }

        public Criteria andAreaLabelGreaterThanOrEqualTo(String value) {
            addCriterion("area_label >=", value, "areaLabel");
            return (Criteria) this;
        }

        public Criteria andAreaLabelLessThan(String value) {
            addCriterion("area_label <", value, "areaLabel");
            return (Criteria) this;
        }

        public Criteria andAreaLabelLessThanOrEqualTo(String value) {
            addCriterion("area_label <=", value, "areaLabel");
            return (Criteria) this;
        }

        public Criteria andAreaLabelLike(String value) {
            addCriterion("area_label like", value, "areaLabel");
            return (Criteria) this;
        }

        public Criteria andAreaLabelNotLike(String value) {
            addCriterion("area_label not like", value, "areaLabel");
            return (Criteria) this;
        }

        public Criteria andAreaLabelIn(List<String> values) {
            addCriterion("area_label in", values, "areaLabel");
            return (Criteria) this;
        }

        public Criteria andAreaLabelNotIn(List<String> values) {
            addCriterion("area_label not in", values, "areaLabel");
            return (Criteria) this;
        }

        public Criteria andAreaLabelBetween(String value1, String value2) {
            addCriterion("area_label between", value1, value2, "areaLabel");
            return (Criteria) this;
        }

        public Criteria andAreaLabelNotBetween(String value1, String value2) {
            addCriterion("area_label not between", value1, value2, "areaLabel");
            return (Criteria) this;
        }

        public Criteria andAreaLabelPicIsNull() {
            addCriterion("area_label_pic is null");
            return (Criteria) this;
        }

        public Criteria andAreaLabelPicIsNotNull() {
            addCriterion("area_label_pic is not null");
            return (Criteria) this;
        }

        public Criteria andAreaLabelPicEqualTo(String value) {
            addCriterion("area_label_pic =", value, "areaLabelPic");
            return (Criteria) this;
        }

        public Criteria andAreaLabelPicNotEqualTo(String value) {
            addCriterion("area_label_pic <>", value, "areaLabelPic");
            return (Criteria) this;
        }

        public Criteria andAreaLabelPicGreaterThan(String value) {
            addCriterion("area_label_pic >", value, "areaLabelPic");
            return (Criteria) this;
        }

        public Criteria andAreaLabelPicGreaterThanOrEqualTo(String value) {
            addCriterion("area_label_pic >=", value, "areaLabelPic");
            return (Criteria) this;
        }

        public Criteria andAreaLabelPicLessThan(String value) {
            addCriterion("area_label_pic <", value, "areaLabelPic");
            return (Criteria) this;
        }

        public Criteria andAreaLabelPicLessThanOrEqualTo(String value) {
            addCriterion("area_label_pic <=", value, "areaLabelPic");
            return (Criteria) this;
        }

        public Criteria andAreaLabelPicLike(String value) {
            addCriterion("area_label_pic like", value, "areaLabelPic");
            return (Criteria) this;
        }

        public Criteria andAreaLabelPicNotLike(String value) {
            addCriterion("area_label_pic not like", value, "areaLabelPic");
            return (Criteria) this;
        }

        public Criteria andAreaLabelPicIn(List<String> values) {
            addCriterion("area_label_pic in", values, "areaLabelPic");
            return (Criteria) this;
        }

        public Criteria andAreaLabelPicNotIn(List<String> values) {
            addCriterion("area_label_pic not in", values, "areaLabelPic");
            return (Criteria) this;
        }

        public Criteria andAreaLabelPicBetween(String value1, String value2) {
            addCriterion("area_label_pic between", value1, value2, "areaLabelPic");
            return (Criteria) this;
        }

        public Criteria andAreaLabelPicNotBetween(String value1, String value2) {
            addCriterion("area_label_pic not between", value1, value2, "areaLabelPic");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitsIsNull() {
            addCriterion("purchase_limits is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitsIsNotNull() {
            addCriterion("purchase_limits is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitsEqualTo(String value) {
            addCriterion("purchase_limits =", value, "purchaseLimits");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitsNotEqualTo(String value) {
            addCriterion("purchase_limits <>", value, "purchaseLimits");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitsGreaterThan(String value) {
            addCriterion("purchase_limits >", value, "purchaseLimits");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitsGreaterThanOrEqualTo(String value) {
            addCriterion("purchase_limits >=", value, "purchaseLimits");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitsLessThan(String value) {
            addCriterion("purchase_limits <", value, "purchaseLimits");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitsLessThanOrEqualTo(String value) {
            addCriterion("purchase_limits <=", value, "purchaseLimits");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitsLike(String value) {
            addCriterion("purchase_limits like", value, "purchaseLimits");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitsNotLike(String value) {
            addCriterion("purchase_limits not like", value, "purchaseLimits");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitsIn(List<String> values) {
            addCriterion("purchase_limits in", values, "purchaseLimits");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitsNotIn(List<String> values) {
            addCriterion("purchase_limits not in", values, "purchaseLimits");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitsBetween(String value1, String value2) {
            addCriterion("purchase_limits between", value1, value2, "purchaseLimits");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitsNotBetween(String value1, String value2) {
            addCriterion("purchase_limits not between", value1, value2, "purchaseLimits");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitsQuantityIsNull() {
            addCriterion("purchase_limits_quantity is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitsQuantityIsNotNull() {
            addCriterion("purchase_limits_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitsQuantityEqualTo(Integer value) {
            addCriterion("purchase_limits_quantity =", value, "purchaseLimitsQuantity");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitsQuantityNotEqualTo(Integer value) {
            addCriterion("purchase_limits_quantity <>", value, "purchaseLimitsQuantity");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitsQuantityGreaterThan(Integer value) {
            addCriterion("purchase_limits_quantity >", value, "purchaseLimitsQuantity");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitsQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("purchase_limits_quantity >=", value, "purchaseLimitsQuantity");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitsQuantityLessThan(Integer value) {
            addCriterion("purchase_limits_quantity <", value, "purchaseLimitsQuantity");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitsQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("purchase_limits_quantity <=", value, "purchaseLimitsQuantity");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitsQuantityIn(List<Integer> values) {
            addCriterion("purchase_limits_quantity in", values, "purchaseLimitsQuantity");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitsQuantityNotIn(List<Integer> values) {
            addCriterion("purchase_limits_quantity not in", values, "purchaseLimitsQuantity");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitsQuantityBetween(Integer value1, Integer value2) {
            addCriterion("purchase_limits_quantity between", value1, value2, "purchaseLimitsQuantity");
            return (Criteria) this;
        }

        public Criteria andPurchaseLimitsQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("purchase_limits_quantity not between", value1, value2, "purchaseLimitsQuantity");
            return (Criteria) this;
        }

        public Criteria andIsPreSellIsNull() {
            addCriterion("is_pre_sell is null");
            return (Criteria) this;
        }

        public Criteria andIsPreSellIsNotNull() {
            addCriterion("is_pre_sell is not null");
            return (Criteria) this;
        }

        public Criteria andIsPreSellEqualTo(String value) {
            addCriterion("is_pre_sell =", value, "isPreSell");
            return (Criteria) this;
        }

        public Criteria andIsPreSellNotEqualTo(String value) {
            addCriterion("is_pre_sell <>", value, "isPreSell");
            return (Criteria) this;
        }

        public Criteria andIsPreSellGreaterThan(String value) {
            addCriterion("is_pre_sell >", value, "isPreSell");
            return (Criteria) this;
        }

        public Criteria andIsPreSellGreaterThanOrEqualTo(String value) {
            addCriterion("is_pre_sell >=", value, "isPreSell");
            return (Criteria) this;
        }

        public Criteria andIsPreSellLessThan(String value) {
            addCriterion("is_pre_sell <", value, "isPreSell");
            return (Criteria) this;
        }

        public Criteria andIsPreSellLessThanOrEqualTo(String value) {
            addCriterion("is_pre_sell <=", value, "isPreSell");
            return (Criteria) this;
        }

        public Criteria andIsPreSellLike(String value) {
            addCriterion("is_pre_sell like", value, "isPreSell");
            return (Criteria) this;
        }

        public Criteria andIsPreSellNotLike(String value) {
            addCriterion("is_pre_sell not like", value, "isPreSell");
            return (Criteria) this;
        }

        public Criteria andIsPreSellIn(List<String> values) {
            addCriterion("is_pre_sell in", values, "isPreSell");
            return (Criteria) this;
        }

        public Criteria andIsPreSellNotIn(List<String> values) {
            addCriterion("is_pre_sell not in", values, "isPreSell");
            return (Criteria) this;
        }

        public Criteria andIsPreSellBetween(String value1, String value2) {
            addCriterion("is_pre_sell between", value1, value2, "isPreSell");
            return (Criteria) this;
        }

        public Criteria andIsPreSellNotBetween(String value1, String value2) {
            addCriterion("is_pre_sell not between", value1, value2, "isPreSell");
            return (Criteria) this;
        }

        public Criteria andShareIntegralTypeIsNull() {
            addCriterion("share_integral_type is null");
            return (Criteria) this;
        }

        public Criteria andShareIntegralTypeIsNotNull() {
            addCriterion("share_integral_type is not null");
            return (Criteria) this;
        }

        public Criteria andShareIntegralTypeEqualTo(String value) {
            addCriterion("share_integral_type =", value, "shareIntegralType");
            return (Criteria) this;
        }

        public Criteria andShareIntegralTypeNotEqualTo(String value) {
            addCriterion("share_integral_type <>", value, "shareIntegralType");
            return (Criteria) this;
        }

        public Criteria andShareIntegralTypeGreaterThan(String value) {
            addCriterion("share_integral_type >", value, "shareIntegralType");
            return (Criteria) this;
        }

        public Criteria andShareIntegralTypeGreaterThanOrEqualTo(String value) {
            addCriterion("share_integral_type >=", value, "shareIntegralType");
            return (Criteria) this;
        }

        public Criteria andShareIntegralTypeLessThan(String value) {
            addCriterion("share_integral_type <", value, "shareIntegralType");
            return (Criteria) this;
        }

        public Criteria andShareIntegralTypeLessThanOrEqualTo(String value) {
            addCriterion("share_integral_type <=", value, "shareIntegralType");
            return (Criteria) this;
        }

        public Criteria andShareIntegralTypeLike(String value) {
            addCriterion("share_integral_type like", value, "shareIntegralType");
            return (Criteria) this;
        }

        public Criteria andShareIntegralTypeNotLike(String value) {
            addCriterion("share_integral_type not like", value, "shareIntegralType");
            return (Criteria) this;
        }

        public Criteria andShareIntegralTypeIn(List<String> values) {
            addCriterion("share_integral_type in", values, "shareIntegralType");
            return (Criteria) this;
        }

        public Criteria andShareIntegralTypeNotIn(List<String> values) {
            addCriterion("share_integral_type not in", values, "shareIntegralType");
            return (Criteria) this;
        }

        public Criteria andShareIntegralTypeBetween(String value1, String value2) {
            addCriterion("share_integral_type between", value1, value2, "shareIntegralType");
            return (Criteria) this;
        }

        public Criteria andShareIntegralTypeNotBetween(String value1, String value2) {
            addCriterion("share_integral_type not between", value1, value2, "shareIntegralType");
            return (Criteria) this;
        }

        public Criteria andMinShareIntegralIsNull() {
            addCriterion("min_share_integral is null");
            return (Criteria) this;
        }

        public Criteria andMinShareIntegralIsNotNull() {
            addCriterion("min_share_integral is not null");
            return (Criteria) this;
        }

        public Criteria andMinShareIntegralEqualTo(Integer value) {
            addCriterion("min_share_integral =", value, "minShareIntegral");
            return (Criteria) this;
        }

        public Criteria andMinShareIntegralNotEqualTo(Integer value) {
            addCriterion("min_share_integral <>", value, "minShareIntegral");
            return (Criteria) this;
        }

        public Criteria andMinShareIntegralGreaterThan(Integer value) {
            addCriterion("min_share_integral >", value, "minShareIntegral");
            return (Criteria) this;
        }

        public Criteria andMinShareIntegralGreaterThanOrEqualTo(Integer value) {
            addCriterion("min_share_integral >=", value, "minShareIntegral");
            return (Criteria) this;
        }

        public Criteria andMinShareIntegralLessThan(Integer value) {
            addCriterion("min_share_integral <", value, "minShareIntegral");
            return (Criteria) this;
        }

        public Criteria andMinShareIntegralLessThanOrEqualTo(Integer value) {
            addCriterion("min_share_integral <=", value, "minShareIntegral");
            return (Criteria) this;
        }

        public Criteria andMinShareIntegralIn(List<Integer> values) {
            addCriterion("min_share_integral in", values, "minShareIntegral");
            return (Criteria) this;
        }

        public Criteria andMinShareIntegralNotIn(List<Integer> values) {
            addCriterion("min_share_integral not in", values, "minShareIntegral");
            return (Criteria) this;
        }

        public Criteria andMinShareIntegralBetween(Integer value1, Integer value2) {
            addCriterion("min_share_integral between", value1, value2, "minShareIntegral");
            return (Criteria) this;
        }

        public Criteria andMinShareIntegralNotBetween(Integer value1, Integer value2) {
            addCriterion("min_share_integral not between", value1, value2, "minShareIntegral");
            return (Criteria) this;
        }

        public Criteria andMaxShareIntegralIsNull() {
            addCriterion("max_share_integral is null");
            return (Criteria) this;
        }

        public Criteria andMaxShareIntegralIsNotNull() {
            addCriterion("max_share_integral is not null");
            return (Criteria) this;
        }

        public Criteria andMaxShareIntegralEqualTo(Integer value) {
            addCriterion("max_share_integral =", value, "maxShareIntegral");
            return (Criteria) this;
        }

        public Criteria andMaxShareIntegralNotEqualTo(Integer value) {
            addCriterion("max_share_integral <>", value, "maxShareIntegral");
            return (Criteria) this;
        }

        public Criteria andMaxShareIntegralGreaterThan(Integer value) {
            addCriterion("max_share_integral >", value, "maxShareIntegral");
            return (Criteria) this;
        }

        public Criteria andMaxShareIntegralGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_share_integral >=", value, "maxShareIntegral");
            return (Criteria) this;
        }

        public Criteria andMaxShareIntegralLessThan(Integer value) {
            addCriterion("max_share_integral <", value, "maxShareIntegral");
            return (Criteria) this;
        }

        public Criteria andMaxShareIntegralLessThanOrEqualTo(Integer value) {
            addCriterion("max_share_integral <=", value, "maxShareIntegral");
            return (Criteria) this;
        }

        public Criteria andMaxShareIntegralIn(List<Integer> values) {
            addCriterion("max_share_integral in", values, "maxShareIntegral");
            return (Criteria) this;
        }

        public Criteria andMaxShareIntegralNotIn(List<Integer> values) {
            addCriterion("max_share_integral not in", values, "maxShareIntegral");
            return (Criteria) this;
        }

        public Criteria andMaxShareIntegralBetween(Integer value1, Integer value2) {
            addCriterion("max_share_integral between", value1, value2, "maxShareIntegral");
            return (Criteria) this;
        }

        public Criteria andMaxShareIntegralNotBetween(Integer value1, Integer value2) {
            addCriterion("max_share_integral not between", value1, value2, "maxShareIntegral");
            return (Criteria) this;
        }

        public Criteria andActivitySharePicIsNull() {
            addCriterion("activity_share_pic is null");
            return (Criteria) this;
        }

        public Criteria andActivitySharePicIsNotNull() {
            addCriterion("activity_share_pic is not null");
            return (Criteria) this;
        }

        public Criteria andActivitySharePicEqualTo(String value) {
            addCriterion("activity_share_pic =", value, "activitySharePic");
            return (Criteria) this;
        }

        public Criteria andActivitySharePicNotEqualTo(String value) {
            addCriterion("activity_share_pic <>", value, "activitySharePic");
            return (Criteria) this;
        }

        public Criteria andActivitySharePicGreaterThan(String value) {
            addCriterion("activity_share_pic >", value, "activitySharePic");
            return (Criteria) this;
        }

        public Criteria andActivitySharePicGreaterThanOrEqualTo(String value) {
            addCriterion("activity_share_pic >=", value, "activitySharePic");
            return (Criteria) this;
        }

        public Criteria andActivitySharePicLessThan(String value) {
            addCriterion("activity_share_pic <", value, "activitySharePic");
            return (Criteria) this;
        }

        public Criteria andActivitySharePicLessThanOrEqualTo(String value) {
            addCriterion("activity_share_pic <=", value, "activitySharePic");
            return (Criteria) this;
        }

        public Criteria andActivitySharePicLike(String value) {
            addCriterion("activity_share_pic like", value, "activitySharePic");
            return (Criteria) this;
        }

        public Criteria andActivitySharePicNotLike(String value) {
            addCriterion("activity_share_pic not like", value, "activitySharePic");
            return (Criteria) this;
        }

        public Criteria andActivitySharePicIn(List<String> values) {
            addCriterion("activity_share_pic in", values, "activitySharePic");
            return (Criteria) this;
        }

        public Criteria andActivitySharePicNotIn(List<String> values) {
            addCriterion("activity_share_pic not in", values, "activitySharePic");
            return (Criteria) this;
        }

        public Criteria andActivitySharePicBetween(String value1, String value2) {
            addCriterion("activity_share_pic between", value1, value2, "activitySharePic");
            return (Criteria) this;
        }

        public Criteria andActivitySharePicNotBetween(String value1, String value2) {
            addCriterion("activity_share_pic not between", value1, value2, "activitySharePic");
            return (Criteria) this;
        }

        public Criteria andActivityShareDescIsNull() {
            addCriterion("activity_share_desc is null");
            return (Criteria) this;
        }

        public Criteria andActivityShareDescIsNotNull() {
            addCriterion("activity_share_desc is not null");
            return (Criteria) this;
        }

        public Criteria andActivityShareDescEqualTo(String value) {
            addCriterion("activity_share_desc =", value, "activityShareDesc");
            return (Criteria) this;
        }

        public Criteria andActivityShareDescNotEqualTo(String value) {
            addCriterion("activity_share_desc <>", value, "activityShareDesc");
            return (Criteria) this;
        }

        public Criteria andActivityShareDescGreaterThan(String value) {
            addCriterion("activity_share_desc >", value, "activityShareDesc");
            return (Criteria) this;
        }

        public Criteria andActivityShareDescGreaterThanOrEqualTo(String value) {
            addCriterion("activity_share_desc >=", value, "activityShareDesc");
            return (Criteria) this;
        }

        public Criteria andActivityShareDescLessThan(String value) {
            addCriterion("activity_share_desc <", value, "activityShareDesc");
            return (Criteria) this;
        }

        public Criteria andActivityShareDescLessThanOrEqualTo(String value) {
            addCriterion("activity_share_desc <=", value, "activityShareDesc");
            return (Criteria) this;
        }

        public Criteria andActivityShareDescLike(String value) {
            addCriterion("activity_share_desc like", value, "activityShareDesc");
            return (Criteria) this;
        }

        public Criteria andActivityShareDescNotLike(String value) {
            addCriterion("activity_share_desc not like", value, "activityShareDesc");
            return (Criteria) this;
        }

        public Criteria andActivityShareDescIn(List<String> values) {
            addCriterion("activity_share_desc in", values, "activityShareDesc");
            return (Criteria) this;
        }

        public Criteria andActivityShareDescNotIn(List<String> values) {
            addCriterion("activity_share_desc not in", values, "activityShareDesc");
            return (Criteria) this;
        }

        public Criteria andActivityShareDescBetween(String value1, String value2) {
            addCriterion("activity_share_desc between", value1, value2, "activityShareDesc");
            return (Criteria) this;
        }

        public Criteria andActivityShareDescNotBetween(String value1, String value2) {
            addCriterion("activity_share_desc not between", value1, value2, "activityShareDesc");
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