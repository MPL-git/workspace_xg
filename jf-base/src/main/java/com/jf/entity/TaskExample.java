package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public TaskExample() {
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

        public Criteria andSendChannelIsNull() {
            addCriterion("send_channel is null");
            return (Criteria) this;
        }

        public Criteria andSendChannelIsNotNull() {
            addCriterion("send_channel is not null");
            return (Criteria) this;
        }

        public Criteria andSendChannelEqualTo(String value) {
            addCriterion("send_channel =", value, "sendChannel");
            return (Criteria) this;
        }

        public Criteria andSendChannelNotEqualTo(String value) {
            addCriterion("send_channel <>", value, "sendChannel");
            return (Criteria) this;
        }

        public Criteria andSendChannelGreaterThan(String value) {
            addCriterion("send_channel >", value, "sendChannel");
            return (Criteria) this;
        }

        public Criteria andSendChannelGreaterThanOrEqualTo(String value) {
            addCriterion("send_channel >=", value, "sendChannel");
            return (Criteria) this;
        }

        public Criteria andSendChannelLessThan(String value) {
            addCriterion("send_channel <", value, "sendChannel");
            return (Criteria) this;
        }

        public Criteria andSendChannelLessThanOrEqualTo(String value) {
            addCriterion("send_channel <=", value, "sendChannel");
            return (Criteria) this;
        }

        public Criteria andSendChannelLike(String value) {
            addCriterion("send_channel like", value, "sendChannel");
            return (Criteria) this;
        }

        public Criteria andSendChannelNotLike(String value) {
            addCriterion("send_channel not like", value, "sendChannel");
            return (Criteria) this;
        }

        public Criteria andSendChannelIn(List<String> values) {
            addCriterion("send_channel in", values, "sendChannel");
            return (Criteria) this;
        }

        public Criteria andSendChannelNotIn(List<String> values) {
            addCriterion("send_channel not in", values, "sendChannel");
            return (Criteria) this;
        }

        public Criteria andSendChannelBetween(String value1, String value2) {
            addCriterion("send_channel between", value1, value2, "sendChannel");
            return (Criteria) this;
        }

        public Criteria andSendChannelNotBetween(String value1, String value2) {
            addCriterion("send_channel not between", value1, value2, "sendChannel");
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

        public Criteria andTaskExplainIsNull() {
            addCriterion("task_explain is null");
            return (Criteria) this;
        }

        public Criteria andTaskExplainIsNotNull() {
            addCriterion("task_explain is not null");
            return (Criteria) this;
        }

        public Criteria andTaskExplainEqualTo(String value) {
            addCriterion("task_explain =", value, "taskExplain");
            return (Criteria) this;
        }

        public Criteria andTaskExplainNotEqualTo(String value) {
            addCriterion("task_explain <>", value, "taskExplain");
            return (Criteria) this;
        }

        public Criteria andTaskExplainGreaterThan(String value) {
            addCriterion("task_explain >", value, "taskExplain");
            return (Criteria) this;
        }

        public Criteria andTaskExplainGreaterThanOrEqualTo(String value) {
            addCriterion("task_explain >=", value, "taskExplain");
            return (Criteria) this;
        }

        public Criteria andTaskExplainLessThan(String value) {
            addCriterion("task_explain <", value, "taskExplain");
            return (Criteria) this;
        }

        public Criteria andTaskExplainLessThanOrEqualTo(String value) {
            addCriterion("task_explain <=", value, "taskExplain");
            return (Criteria) this;
        }

        public Criteria andTaskExplainLike(String value) {
            addCriterion("task_explain like", value, "taskExplain");
            return (Criteria) this;
        }

        public Criteria andTaskExplainNotLike(String value) {
            addCriterion("task_explain not like", value, "taskExplain");
            return (Criteria) this;
        }

        public Criteria andTaskExplainIn(List<String> values) {
            addCriterion("task_explain in", values, "taskExplain");
            return (Criteria) this;
        }

        public Criteria andTaskExplainNotIn(List<String> values) {
            addCriterion("task_explain not in", values, "taskExplain");
            return (Criteria) this;
        }

        public Criteria andTaskExplainBetween(String value1, String value2) {
            addCriterion("task_explain between", value1, value2, "taskExplain");
            return (Criteria) this;
        }

        public Criteria andTaskExplainNotBetween(String value1, String value2) {
            addCriterion("task_explain not between", value1, value2, "taskExplain");
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

        public Criteria andSendModeIsNull() {
            addCriterion("send_mode is null");
            return (Criteria) this;
        }

        public Criteria andSendModeIsNotNull() {
            addCriterion("send_mode is not null");
            return (Criteria) this;
        }

        public Criteria andSendModeEqualTo(String value) {
            addCriterion("send_mode =", value, "sendMode");
            return (Criteria) this;
        }

        public Criteria andSendModeNotEqualTo(String value) {
            addCriterion("send_mode <>", value, "sendMode");
            return (Criteria) this;
        }

        public Criteria andSendModeGreaterThan(String value) {
            addCriterion("send_mode >", value, "sendMode");
            return (Criteria) this;
        }

        public Criteria andSendModeGreaterThanOrEqualTo(String value) {
            addCriterion("send_mode >=", value, "sendMode");
            return (Criteria) this;
        }

        public Criteria andSendModeLessThan(String value) {
            addCriterion("send_mode <", value, "sendMode");
            return (Criteria) this;
        }

        public Criteria andSendModeLessThanOrEqualTo(String value) {
            addCriterion("send_mode <=", value, "sendMode");
            return (Criteria) this;
        }

        public Criteria andSendModeLike(String value) {
            addCriterion("send_mode like", value, "sendMode");
            return (Criteria) this;
        }

        public Criteria andSendModeNotLike(String value) {
            addCriterion("send_mode not like", value, "sendMode");
            return (Criteria) this;
        }

        public Criteria andSendModeIn(List<String> values) {
            addCriterion("send_mode in", values, "sendMode");
            return (Criteria) this;
        }

        public Criteria andSendModeNotIn(List<String> values) {
            addCriterion("send_mode not in", values, "sendMode");
            return (Criteria) this;
        }

        public Criteria andSendModeBetween(String value1, String value2) {
            addCriterion("send_mode between", value1, value2, "sendMode");
            return (Criteria) this;
        }

        public Criteria andSendModeNotBetween(String value1, String value2) {
            addCriterion("send_mode not between", value1, value2, "sendMode");
            return (Criteria) this;
        }

        public Criteria andSendDateIsNull() {
            addCriterion("send_date is null");
            return (Criteria) this;
        }

        public Criteria andSendDateIsNotNull() {
            addCriterion("send_date is not null");
            return (Criteria) this;
        }

        public Criteria andSendDateEqualTo(Date value) {
            addCriterion("send_date =", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateNotEqualTo(Date value) {
            addCriterion("send_date <>", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateGreaterThan(Date value) {
            addCriterion("send_date >", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateGreaterThanOrEqualTo(Date value) {
            addCriterion("send_date >=", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateLessThan(Date value) {
            addCriterion("send_date <", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateLessThanOrEqualTo(Date value) {
            addCriterion("send_date <=", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateIn(List<Date> values) {
            addCriterion("send_date in", values, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateNotIn(List<Date> values) {
            addCriterion("send_date not in", values, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateBetween(Date value1, Date value2) {
            addCriterion("send_date between", value1, value2, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateNotBetween(Date value1, Date value2) {
            addCriterion("send_date not between", value1, value2, "sendDate");
            return (Criteria) this;
        }

        public Criteria andTimeIntervalIsNull() {
            addCriterion("time_interval is null");
            return (Criteria) this;
        }

        public Criteria andTimeIntervalIsNotNull() {
            addCriterion("time_interval is not null");
            return (Criteria) this;
        }

        public Criteria andTimeIntervalEqualTo(Integer value) {
            addCriterion("time_interval =", value, "timeInterval");
            return (Criteria) this;
        }

        public Criteria andTimeIntervalNotEqualTo(Integer value) {
            addCriterion("time_interval <>", value, "timeInterval");
            return (Criteria) this;
        }

        public Criteria andTimeIntervalGreaterThan(Integer value) {
            addCriterion("time_interval >", value, "timeInterval");
            return (Criteria) this;
        }

        public Criteria andTimeIntervalGreaterThanOrEqualTo(Integer value) {
            addCriterion("time_interval >=", value, "timeInterval");
            return (Criteria) this;
        }

        public Criteria andTimeIntervalLessThan(Integer value) {
            addCriterion("time_interval <", value, "timeInterval");
            return (Criteria) this;
        }

        public Criteria andTimeIntervalLessThanOrEqualTo(Integer value) {
            addCriterion("time_interval <=", value, "timeInterval");
            return (Criteria) this;
        }

        public Criteria andTimeIntervalIn(List<Integer> values) {
            addCriterion("time_interval in", values, "timeInterval");
            return (Criteria) this;
        }

        public Criteria andTimeIntervalNotIn(List<Integer> values) {
            addCriterion("time_interval not in", values, "timeInterval");
            return (Criteria) this;
        }

        public Criteria andTimeIntervalBetween(Integer value1, Integer value2) {
            addCriterion("time_interval between", value1, value2, "timeInterval");
            return (Criteria) this;
        }

        public Criteria andTimeIntervalNotBetween(Integer value1, Integer value2) {
            addCriterion("time_interval not between", value1, value2, "timeInterval");
            return (Criteria) this;
        }

        public Criteria andSendCountIsNull() {
            addCriterion("send_count is null");
            return (Criteria) this;
        }

        public Criteria andSendCountIsNotNull() {
            addCriterion("send_count is not null");
            return (Criteria) this;
        }

        public Criteria andSendCountEqualTo(Integer value) {
            addCriterion("send_count =", value, "sendCount");
            return (Criteria) this;
        }

        public Criteria andSendCountNotEqualTo(Integer value) {
            addCriterion("send_count <>", value, "sendCount");
            return (Criteria) this;
        }

        public Criteria andSendCountGreaterThan(Integer value) {
            addCriterion("send_count >", value, "sendCount");
            return (Criteria) this;
        }

        public Criteria andSendCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("send_count >=", value, "sendCount");
            return (Criteria) this;
        }

        public Criteria andSendCountLessThan(Integer value) {
            addCriterion("send_count <", value, "sendCount");
            return (Criteria) this;
        }

        public Criteria andSendCountLessThanOrEqualTo(Integer value) {
            addCriterion("send_count <=", value, "sendCount");
            return (Criteria) this;
        }

        public Criteria andSendCountIn(List<Integer> values) {
            addCriterion("send_count in", values, "sendCount");
            return (Criteria) this;
        }

        public Criteria andSendCountNotIn(List<Integer> values) {
            addCriterion("send_count not in", values, "sendCount");
            return (Criteria) this;
        }

        public Criteria andSendCountBetween(Integer value1, Integer value2) {
            addCriterion("send_count between", value1, value2, "sendCount");
            return (Criteria) this;
        }

        public Criteria andSendCountNotBetween(Integer value1, Integer value2) {
            addCriterion("send_count not between", value1, value2, "sendCount");
            return (Criteria) this;
        }

        public Criteria andSendTypeIsNull() {
            addCriterion("send_type is null");
            return (Criteria) this;
        }

        public Criteria andSendTypeIsNotNull() {
            addCriterion("send_type is not null");
            return (Criteria) this;
        }

        public Criteria andSendTypeEqualTo(String value) {
            addCriterion("send_type =", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeNotEqualTo(String value) {
            addCriterion("send_type <>", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeGreaterThan(String value) {
            addCriterion("send_type >", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeGreaterThanOrEqualTo(String value) {
            addCriterion("send_type >=", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeLessThan(String value) {
            addCriterion("send_type <", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeLessThanOrEqualTo(String value) {
            addCriterion("send_type <=", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeLike(String value) {
            addCriterion("send_type like", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeNotLike(String value) {
            addCriterion("send_type not like", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeIn(List<String> values) {
            addCriterion("send_type in", values, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeNotIn(List<String> values) {
            addCriterion("send_type not in", values, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeBetween(String value1, String value2) {
            addCriterion("send_type between", value1, value2, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeNotBetween(String value1, String value2) {
            addCriterion("send_type not between", value1, value2, "sendType");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNull() {
            addCriterion("file_path is null");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNotNull() {
            addCriterion("file_path is not null");
            return (Criteria) this;
        }

        public Criteria andFilePathEqualTo(String value) {
            addCriterion("file_path =", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotEqualTo(String value) {
            addCriterion("file_path <>", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThan(String value) {
            addCriterion("file_path >", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThanOrEqualTo(String value) {
            addCriterion("file_path >=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThan(String value) {
            addCriterion("file_path <", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThanOrEqualTo(String value) {
            addCriterion("file_path <=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLike(String value) {
            addCriterion("file_path like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotLike(String value) {
            addCriterion("file_path not like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathIn(List<String> values) {
            addCriterion("file_path in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotIn(List<String> values) {
            addCriterion("file_path not in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathBetween(String value1, String value2) {
            addCriterion("file_path between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotBetween(String value1, String value2) {
            addCriterion("file_path not between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andLabelGroupIdsIsNull() {
            addCriterion("label_group_ids is null");
            return (Criteria) this;
        }

        public Criteria andLabelGroupIdsIsNotNull() {
            addCriterion("label_group_ids is not null");
            return (Criteria) this;
        }

        public Criteria andLabelGroupIdsEqualTo(String value) {
            addCriterion("label_group_ids =", value, "labelGroupIds");
            return (Criteria) this;
        }

        public Criteria andLabelGroupIdsNotEqualTo(String value) {
            addCriterion("label_group_ids <>", value, "labelGroupIds");
            return (Criteria) this;
        }

        public Criteria andLabelGroupIdsGreaterThan(String value) {
            addCriterion("label_group_ids >", value, "labelGroupIds");
            return (Criteria) this;
        }

        public Criteria andLabelGroupIdsGreaterThanOrEqualTo(String value) {
            addCriterion("label_group_ids >=", value, "labelGroupIds");
            return (Criteria) this;
        }

        public Criteria andLabelGroupIdsLessThan(String value) {
            addCriterion("label_group_ids <", value, "labelGroupIds");
            return (Criteria) this;
        }

        public Criteria andLabelGroupIdsLessThanOrEqualTo(String value) {
            addCriterion("label_group_ids <=", value, "labelGroupIds");
            return (Criteria) this;
        }

        public Criteria andLabelGroupIdsLike(String value) {
            addCriterion("label_group_ids like", value, "labelGroupIds");
            return (Criteria) this;
        }

        public Criteria andLabelGroupIdsNotLike(String value) {
            addCriterion("label_group_ids not like", value, "labelGroupIds");
            return (Criteria) this;
        }

        public Criteria andLabelGroupIdsIn(List<String> values) {
            addCriterion("label_group_ids in", values, "labelGroupIds");
            return (Criteria) this;
        }

        public Criteria andLabelGroupIdsNotIn(List<String> values) {
            addCriterion("label_group_ids not in", values, "labelGroupIds");
            return (Criteria) this;
        }

        public Criteria andLabelGroupIdsBetween(String value1, String value2) {
            addCriterion("label_group_ids between", value1, value2, "labelGroupIds");
            return (Criteria) this;
        }

        public Criteria andLabelGroupIdsNotBetween(String value1, String value2) {
            addCriterion("label_group_ids not between", value1, value2, "labelGroupIds");
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

        public Criteria andLastSendDateIsNull() {
            addCriterion("last_send_date is null");
            return (Criteria) this;
        }

        public Criteria andLastSendDateIsNotNull() {
            addCriterion("last_send_date is not null");
            return (Criteria) this;
        }

        public Criteria andLastSendDateEqualTo(Date value) {
            addCriterion("last_send_date =", value, "lastSendDate");
            return (Criteria) this;
        }

        public Criteria andLastSendDateNotEqualTo(Date value) {
            addCriterion("last_send_date <>", value, "lastSendDate");
            return (Criteria) this;
        }

        public Criteria andLastSendDateGreaterThan(Date value) {
            addCriterion("last_send_date >", value, "lastSendDate");
            return (Criteria) this;
        }

        public Criteria andLastSendDateGreaterThanOrEqualTo(Date value) {
            addCriterion("last_send_date >=", value, "lastSendDate");
            return (Criteria) this;
        }

        public Criteria andLastSendDateLessThan(Date value) {
            addCriterion("last_send_date <", value, "lastSendDate");
            return (Criteria) this;
        }

        public Criteria andLastSendDateLessThanOrEqualTo(Date value) {
            addCriterion("last_send_date <=", value, "lastSendDate");
            return (Criteria) this;
        }

        public Criteria andLastSendDateIn(List<Date> values) {
            addCriterion("last_send_date in", values, "lastSendDate");
            return (Criteria) this;
        }

        public Criteria andLastSendDateNotIn(List<Date> values) {
            addCriterion("last_send_date not in", values, "lastSendDate");
            return (Criteria) this;
        }

        public Criteria andLastSendDateBetween(Date value1, Date value2) {
            addCriterion("last_send_date between", value1, value2, "lastSendDate");
            return (Criteria) this;
        }

        public Criteria andLastSendDateNotBetween(Date value1, Date value2) {
            addCriterion("last_send_date not between", value1, value2, "lastSendDate");
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