package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysMenuExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public SysMenuExample() {
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

        public Criteria andMenuIdIsNull() {
            addCriterion("MENU_ID is null");
            return (Criteria) this;
        }

        public Criteria andMenuIdIsNotNull() {
            addCriterion("MENU_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMenuIdEqualTo(Integer value) {
            addCriterion("MENU_ID =", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotEqualTo(Integer value) {
            addCriterion("MENU_ID <>", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdGreaterThan(Integer value) {
            addCriterion("MENU_ID >", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("MENU_ID >=", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdLessThan(Integer value) {
            addCriterion("MENU_ID <", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdLessThanOrEqualTo(Integer value) {
            addCriterion("MENU_ID <=", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdIn(List<Integer> values) {
            addCriterion("MENU_ID in", values, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotIn(List<Integer> values) {
            addCriterion("MENU_ID not in", values, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdBetween(Integer value1, Integer value2) {
            addCriterion("MENU_ID between", value1, value2, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotBetween(Integer value1, Integer value2) {
            addCriterion("MENU_ID not between", value1, value2, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuCodeIsNull() {
            addCriterion("MENU_CODE is null");
            return (Criteria) this;
        }

        public Criteria andMenuCodeIsNotNull() {
            addCriterion("MENU_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andMenuCodeEqualTo(String value) {
            addCriterion("MENU_CODE =", value, "menuCode");
            return (Criteria) this;
        }

        public Criteria andMenuCodeNotEqualTo(String value) {
            addCriterion("MENU_CODE <>", value, "menuCode");
            return (Criteria) this;
        }

        public Criteria andMenuCodeGreaterThan(String value) {
            addCriterion("MENU_CODE >", value, "menuCode");
            return (Criteria) this;
        }

        public Criteria andMenuCodeGreaterThanOrEqualTo(String value) {
            addCriterion("MENU_CODE >=", value, "menuCode");
            return (Criteria) this;
        }

        public Criteria andMenuCodeLessThan(String value) {
            addCriterion("MENU_CODE <", value, "menuCode");
            return (Criteria) this;
        }

        public Criteria andMenuCodeLessThanOrEqualTo(String value) {
            addCriterion("MENU_CODE <=", value, "menuCode");
            return (Criteria) this;
        }

        public Criteria andMenuCodeLike(String value) {
            addCriterion("MENU_CODE like", value, "menuCode");
            return (Criteria) this;
        }

        public Criteria andMenuCodeNotLike(String value) {
            addCriterion("MENU_CODE not like", value, "menuCode");
            return (Criteria) this;
        }

        public Criteria andMenuCodeIn(List<String> values) {
            addCriterion("MENU_CODE in", values, "menuCode");
            return (Criteria) this;
        }

        public Criteria andMenuCodeNotIn(List<String> values) {
            addCriterion("MENU_CODE not in", values, "menuCode");
            return (Criteria) this;
        }

        public Criteria andMenuCodeBetween(String value1, String value2) {
            addCriterion("MENU_CODE between", value1, value2, "menuCode");
            return (Criteria) this;
        }

        public Criteria andMenuCodeNotBetween(String value1, String value2) {
            addCriterion("MENU_CODE not between", value1, value2, "menuCode");
            return (Criteria) this;
        }

        public Criteria andMenuNameIsNull() {
            addCriterion("MENU_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMenuNameIsNotNull() {
            addCriterion("MENU_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMenuNameEqualTo(String value) {
            addCriterion("MENU_NAME =", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotEqualTo(String value) {
            addCriterion("MENU_NAME <>", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameGreaterThan(String value) {
            addCriterion("MENU_NAME >", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameGreaterThanOrEqualTo(String value) {
            addCriterion("MENU_NAME >=", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLessThan(String value) {
            addCriterion("MENU_NAME <", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLessThanOrEqualTo(String value) {
            addCriterion("MENU_NAME <=", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLike(String value) {
            addCriterion("MENU_NAME like", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotLike(String value) {
            addCriterion("MENU_NAME not like", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameIn(List<String> values) {
            addCriterion("MENU_NAME in", values, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotIn(List<String> values) {
            addCriterion("MENU_NAME not in", values, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameBetween(String value1, String value2) {
            addCriterion("MENU_NAME between", value1, value2, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotBetween(String value1, String value2) {
            addCriterion("MENU_NAME not between", value1, value2, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuDescIsNull() {
            addCriterion("MENU_DESC is null");
            return (Criteria) this;
        }

        public Criteria andMenuDescIsNotNull() {
            addCriterion("MENU_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andMenuDescEqualTo(String value) {
            addCriterion("MENU_DESC =", value, "menuDesc");
            return (Criteria) this;
        }

        public Criteria andMenuDescNotEqualTo(String value) {
            addCriterion("MENU_DESC <>", value, "menuDesc");
            return (Criteria) this;
        }

        public Criteria andMenuDescGreaterThan(String value) {
            addCriterion("MENU_DESC >", value, "menuDesc");
            return (Criteria) this;
        }

        public Criteria andMenuDescGreaterThanOrEqualTo(String value) {
            addCriterion("MENU_DESC >=", value, "menuDesc");
            return (Criteria) this;
        }

        public Criteria andMenuDescLessThan(String value) {
            addCriterion("MENU_DESC <", value, "menuDesc");
            return (Criteria) this;
        }

        public Criteria andMenuDescLessThanOrEqualTo(String value) {
            addCriterion("MENU_DESC <=", value, "menuDesc");
            return (Criteria) this;
        }

        public Criteria andMenuDescLike(String value) {
            addCriterion("MENU_DESC like", value, "menuDesc");
            return (Criteria) this;
        }

        public Criteria andMenuDescNotLike(String value) {
            addCriterion("MENU_DESC not like", value, "menuDesc");
            return (Criteria) this;
        }

        public Criteria andMenuDescIn(List<String> values) {
            addCriterion("MENU_DESC in", values, "menuDesc");
            return (Criteria) this;
        }

        public Criteria andMenuDescNotIn(List<String> values) {
            addCriterion("MENU_DESC not in", values, "menuDesc");
            return (Criteria) this;
        }

        public Criteria andMenuDescBetween(String value1, String value2) {
            addCriterion("MENU_DESC between", value1, value2, "menuDesc");
            return (Criteria) this;
        }

        public Criteria andMenuDescNotBetween(String value1, String value2) {
            addCriterion("MENU_DESC not between", value1, value2, "menuDesc");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("PARENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("PARENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Integer value) {
            addCriterion("PARENT_ID =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Integer value) {
            addCriterion("PARENT_ID <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Integer value) {
            addCriterion("PARENT_ID >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("PARENT_ID >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Integer value) {
            addCriterion("PARENT_ID <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Integer value) {
            addCriterion("PARENT_ID <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Integer> values) {
            addCriterion("PARENT_ID in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Integer> values) {
            addCriterion("PARENT_ID not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Integer value1, Integer value2) {
            addCriterion("PARENT_ID between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("PARENT_ID not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andMenuPathIsNull() {
            addCriterion("MENU_PATH is null");
            return (Criteria) this;
        }

        public Criteria andMenuPathIsNotNull() {
            addCriterion("MENU_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andMenuPathEqualTo(String value) {
            addCriterion("MENU_PATH =", value, "menuPath");
            return (Criteria) this;
        }

        public Criteria andMenuPathNotEqualTo(String value) {
            addCriterion("MENU_PATH <>", value, "menuPath");
            return (Criteria) this;
        }

        public Criteria andMenuPathGreaterThan(String value) {
            addCriterion("MENU_PATH >", value, "menuPath");
            return (Criteria) this;
        }

        public Criteria andMenuPathGreaterThanOrEqualTo(String value) {
            addCriterion("MENU_PATH >=", value, "menuPath");
            return (Criteria) this;
        }

        public Criteria andMenuPathLessThan(String value) {
            addCriterion("MENU_PATH <", value, "menuPath");
            return (Criteria) this;
        }

        public Criteria andMenuPathLessThanOrEqualTo(String value) {
            addCriterion("MENU_PATH <=", value, "menuPath");
            return (Criteria) this;
        }

        public Criteria andMenuPathLike(String value) {
            addCriterion("MENU_PATH like", value, "menuPath");
            return (Criteria) this;
        }

        public Criteria andMenuPathNotLike(String value) {
            addCriterion("MENU_PATH not like", value, "menuPath");
            return (Criteria) this;
        }

        public Criteria andMenuPathIn(List<String> values) {
            addCriterion("MENU_PATH in", values, "menuPath");
            return (Criteria) this;
        }

        public Criteria andMenuPathNotIn(List<String> values) {
            addCriterion("MENU_PATH not in", values, "menuPath");
            return (Criteria) this;
        }

        public Criteria andMenuPathBetween(String value1, String value2) {
            addCriterion("MENU_PATH between", value1, value2, "menuPath");
            return (Criteria) this;
        }

        public Criteria andMenuPathNotBetween(String value1, String value2) {
            addCriterion("MENU_PATH not between", value1, value2, "menuPath");
            return (Criteria) this;
        }

        public Criteria andMenuOrderIsNull() {
            addCriterion("MENU_ORDER is null");
            return (Criteria) this;
        }

        public Criteria andMenuOrderIsNotNull() {
            addCriterion("MENU_ORDER is not null");
            return (Criteria) this;
        }

        public Criteria andMenuOrderEqualTo(Integer value) {
            addCriterion("MENU_ORDER =", value, "menuOrder");
            return (Criteria) this;
        }

        public Criteria andMenuOrderNotEqualTo(Integer value) {
            addCriterion("MENU_ORDER <>", value, "menuOrder");
            return (Criteria) this;
        }

        public Criteria andMenuOrderGreaterThan(Integer value) {
            addCriterion("MENU_ORDER >", value, "menuOrder");
            return (Criteria) this;
        }

        public Criteria andMenuOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("MENU_ORDER >=", value, "menuOrder");
            return (Criteria) this;
        }

        public Criteria andMenuOrderLessThan(Integer value) {
            addCriterion("MENU_ORDER <", value, "menuOrder");
            return (Criteria) this;
        }

        public Criteria andMenuOrderLessThanOrEqualTo(Integer value) {
            addCriterion("MENU_ORDER <=", value, "menuOrder");
            return (Criteria) this;
        }

        public Criteria andMenuOrderIn(List<Integer> values) {
            addCriterion("MENU_ORDER in", values, "menuOrder");
            return (Criteria) this;
        }

        public Criteria andMenuOrderNotIn(List<Integer> values) {
            addCriterion("MENU_ORDER not in", values, "menuOrder");
            return (Criteria) this;
        }

        public Criteria andMenuOrderBetween(Integer value1, Integer value2) {
            addCriterion("MENU_ORDER between", value1, value2, "menuOrder");
            return (Criteria) this;
        }

        public Criteria andMenuOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("MENU_ORDER not between", value1, value2, "menuOrder");
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