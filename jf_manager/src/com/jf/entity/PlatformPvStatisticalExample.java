package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlatformPvStatisticalExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public PlatformPvStatisticalExample() {
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

        public Criteria andClientSourceIsNull() {
            addCriterion("client_source is null");
            return (Criteria) this;
        }

        public Criteria andClientSourceIsNotNull() {
            addCriterion("client_source is not null");
            return (Criteria) this;
        }

        public Criteria andClientSourceEqualTo(String value) {
            addCriterion("client_source =", value, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceNotEqualTo(String value) {
            addCriterion("client_source <>", value, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceGreaterThan(String value) {
            addCriterion("client_source >", value, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceGreaterThanOrEqualTo(String value) {
            addCriterion("client_source >=", value, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceLessThan(String value) {
            addCriterion("client_source <", value, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceLessThanOrEqualTo(String value) {
            addCriterion("client_source <=", value, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceLike(String value) {
            addCriterion("client_source like", value, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceNotLike(String value) {
            addCriterion("client_source not like", value, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceIn(List<String> values) {
            addCriterion("client_source in", values, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceNotIn(List<String> values) {
            addCriterion("client_source not in", values, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceBetween(String value1, String value2) {
            addCriterion("client_source between", value1, value2, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceNotBetween(String value1, String value2) {
            addCriterion("client_source not between", value1, value2, "clientSource");
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

        public Criteria andTotalVisitorCountIsNull() {
            addCriterion("total_visitor_count is null");
            return (Criteria) this;
        }

        public Criteria andTotalVisitorCountIsNotNull() {
            addCriterion("total_visitor_count is not null");
            return (Criteria) this;
        }

        public Criteria andTotalVisitorCountEqualTo(Integer value) {
            addCriterion("total_visitor_count =", value, "totalVisitorCount");
            return (Criteria) this;
        }

        public Criteria andTotalVisitorCountNotEqualTo(Integer value) {
            addCriterion("total_visitor_count <>", value, "totalVisitorCount");
            return (Criteria) this;
        }

        public Criteria andTotalVisitorCountGreaterThan(Integer value) {
            addCriterion("total_visitor_count >", value, "totalVisitorCount");
            return (Criteria) this;
        }

        public Criteria andTotalVisitorCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_visitor_count >=", value, "totalVisitorCount");
            return (Criteria) this;
        }

        public Criteria andTotalVisitorCountLessThan(Integer value) {
            addCriterion("total_visitor_count <", value, "totalVisitorCount");
            return (Criteria) this;
        }

        public Criteria andTotalVisitorCountLessThanOrEqualTo(Integer value) {
            addCriterion("total_visitor_count <=", value, "totalVisitorCount");
            return (Criteria) this;
        }

        public Criteria andTotalVisitorCountIn(List<Integer> values) {
            addCriterion("total_visitor_count in", values, "totalVisitorCount");
            return (Criteria) this;
        }

        public Criteria andTotalVisitorCountNotIn(List<Integer> values) {
            addCriterion("total_visitor_count not in", values, "totalVisitorCount");
            return (Criteria) this;
        }

        public Criteria andTotalVisitorCountBetween(Integer value1, Integer value2) {
            addCriterion("total_visitor_count between", value1, value2, "totalVisitorCount");
            return (Criteria) this;
        }

        public Criteria andTotalVisitorCountNotBetween(Integer value1, Integer value2) {
            addCriterion("total_visitor_count not between", value1, value2, "totalVisitorCount");
            return (Criteria) this;
        }

        public Criteria andTotalPvCountIsNull() {
            addCriterion("total_pv_count is null");
            return (Criteria) this;
        }

        public Criteria andTotalPvCountIsNotNull() {
            addCriterion("total_pv_count is not null");
            return (Criteria) this;
        }

        public Criteria andTotalPvCountEqualTo(Integer value) {
            addCriterion("total_pv_count =", value, "totalPvCount");
            return (Criteria) this;
        }

        public Criteria andTotalPvCountNotEqualTo(Integer value) {
            addCriterion("total_pv_count <>", value, "totalPvCount");
            return (Criteria) this;
        }

        public Criteria andTotalPvCountGreaterThan(Integer value) {
            addCriterion("total_pv_count >", value, "totalPvCount");
            return (Criteria) this;
        }

        public Criteria andTotalPvCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_pv_count >=", value, "totalPvCount");
            return (Criteria) this;
        }

        public Criteria andTotalPvCountLessThan(Integer value) {
            addCriterion("total_pv_count <", value, "totalPvCount");
            return (Criteria) this;
        }

        public Criteria andTotalPvCountLessThanOrEqualTo(Integer value) {
            addCriterion("total_pv_count <=", value, "totalPvCount");
            return (Criteria) this;
        }

        public Criteria andTotalPvCountIn(List<Integer> values) {
            addCriterion("total_pv_count in", values, "totalPvCount");
            return (Criteria) this;
        }

        public Criteria andTotalPvCountNotIn(List<Integer> values) {
            addCriterion("total_pv_count not in", values, "totalPvCount");
            return (Criteria) this;
        }

        public Criteria andTotalPvCountBetween(Integer value1, Integer value2) {
            addCriterion("total_pv_count between", value1, value2, "totalPvCount");
            return (Criteria) this;
        }

        public Criteria andTotalPvCountNotBetween(Integer value1, Integer value2) {
            addCriterion("total_pv_count not between", value1, value2, "totalPvCount");
            return (Criteria) this;
        }

        public Criteria andPayAmountIsNull() {
            addCriterion("pay_amount is null");
            return (Criteria) this;
        }

        public Criteria andPayAmountIsNotNull() {
            addCriterion("pay_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPayAmountEqualTo(BigDecimal value) {
            addCriterion("pay_amount =", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotEqualTo(BigDecimal value) {
            addCriterion("pay_amount <>", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountGreaterThan(BigDecimal value) {
            addCriterion("pay_amount >", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_amount >=", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountLessThan(BigDecimal value) {
            addCriterion("pay_amount <", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_amount <=", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountIn(List<BigDecimal> values) {
            addCriterion("pay_amount in", values, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotIn(List<BigDecimal> values) {
            addCriterion("pay_amount not in", values, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_amount between", value1, value2, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_amount not between", value1, value2, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPaySubOrderCountIsNull() {
            addCriterion("pay_sub_order_count is null");
            return (Criteria) this;
        }

        public Criteria andPaySubOrderCountIsNotNull() {
            addCriterion("pay_sub_order_count is not null");
            return (Criteria) this;
        }

        public Criteria andPaySubOrderCountEqualTo(Integer value) {
            addCriterion("pay_sub_order_count =", value, "paySubOrderCount");
            return (Criteria) this;
        }

        public Criteria andPaySubOrderCountNotEqualTo(Integer value) {
            addCriterion("pay_sub_order_count <>", value, "paySubOrderCount");
            return (Criteria) this;
        }

        public Criteria andPaySubOrderCountGreaterThan(Integer value) {
            addCriterion("pay_sub_order_count >", value, "paySubOrderCount");
            return (Criteria) this;
        }

        public Criteria andPaySubOrderCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_sub_order_count >=", value, "paySubOrderCount");
            return (Criteria) this;
        }

        public Criteria andPaySubOrderCountLessThan(Integer value) {
            addCriterion("pay_sub_order_count <", value, "paySubOrderCount");
            return (Criteria) this;
        }

        public Criteria andPaySubOrderCountLessThanOrEqualTo(Integer value) {
            addCriterion("pay_sub_order_count <=", value, "paySubOrderCount");
            return (Criteria) this;
        }

        public Criteria andPaySubOrderCountIn(List<Integer> values) {
            addCriterion("pay_sub_order_count in", values, "paySubOrderCount");
            return (Criteria) this;
        }

        public Criteria andPaySubOrderCountNotIn(List<Integer> values) {
            addCriterion("pay_sub_order_count not in", values, "paySubOrderCount");
            return (Criteria) this;
        }

        public Criteria andPaySubOrderCountBetween(Integer value1, Integer value2) {
            addCriterion("pay_sub_order_count between", value1, value2, "paySubOrderCount");
            return (Criteria) this;
        }

        public Criteria andPaySubOrderCountNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_sub_order_count not between", value1, value2, "paySubOrderCount");
            return (Criteria) this;
        }

        public Criteria andPayProductCountIsNull() {
            addCriterion("pay_product_count is null");
            return (Criteria) this;
        }

        public Criteria andPayProductCountIsNotNull() {
            addCriterion("pay_product_count is not null");
            return (Criteria) this;
        }

        public Criteria andPayProductCountEqualTo(Integer value) {
            addCriterion("pay_product_count =", value, "payProductCount");
            return (Criteria) this;
        }

        public Criteria andPayProductCountNotEqualTo(Integer value) {
            addCriterion("pay_product_count <>", value, "payProductCount");
            return (Criteria) this;
        }

        public Criteria andPayProductCountGreaterThan(Integer value) {
            addCriterion("pay_product_count >", value, "payProductCount");
            return (Criteria) this;
        }

        public Criteria andPayProductCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_product_count >=", value, "payProductCount");
            return (Criteria) this;
        }

        public Criteria andPayProductCountLessThan(Integer value) {
            addCriterion("pay_product_count <", value, "payProductCount");
            return (Criteria) this;
        }

        public Criteria andPayProductCountLessThanOrEqualTo(Integer value) {
            addCriterion("pay_product_count <=", value, "payProductCount");
            return (Criteria) this;
        }

        public Criteria andPayProductCountIn(List<Integer> values) {
            addCriterion("pay_product_count in", values, "payProductCount");
            return (Criteria) this;
        }

        public Criteria andPayProductCountNotIn(List<Integer> values) {
            addCriterion("pay_product_count not in", values, "payProductCount");
            return (Criteria) this;
        }

        public Criteria andPayProductCountBetween(Integer value1, Integer value2) {
            addCriterion("pay_product_count between", value1, value2, "payProductCount");
            return (Criteria) this;
        }

        public Criteria andPayProductCountNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_product_count not between", value1, value2, "payProductCount");
            return (Criteria) this;
        }

        public Criteria andPayMemberCountIsNull() {
            addCriterion("pay_member_count is null");
            return (Criteria) this;
        }

        public Criteria andPayMemberCountIsNotNull() {
            addCriterion("pay_member_count is not null");
            return (Criteria) this;
        }

        public Criteria andPayMemberCountEqualTo(Integer value) {
            addCriterion("pay_member_count =", value, "payMemberCount");
            return (Criteria) this;
        }

        public Criteria andPayMemberCountNotEqualTo(Integer value) {
            addCriterion("pay_member_count <>", value, "payMemberCount");
            return (Criteria) this;
        }

        public Criteria andPayMemberCountGreaterThan(Integer value) {
            addCriterion("pay_member_count >", value, "payMemberCount");
            return (Criteria) this;
        }

        public Criteria andPayMemberCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_member_count >=", value, "payMemberCount");
            return (Criteria) this;
        }

        public Criteria andPayMemberCountLessThan(Integer value) {
            addCriterion("pay_member_count <", value, "payMemberCount");
            return (Criteria) this;
        }

        public Criteria andPayMemberCountLessThanOrEqualTo(Integer value) {
            addCriterion("pay_member_count <=", value, "payMemberCount");
            return (Criteria) this;
        }

        public Criteria andPayMemberCountIn(List<Integer> values) {
            addCriterion("pay_member_count in", values, "payMemberCount");
            return (Criteria) this;
        }

        public Criteria andPayMemberCountNotIn(List<Integer> values) {
            addCriterion("pay_member_count not in", values, "payMemberCount");
            return (Criteria) this;
        }

        public Criteria andPayMemberCountBetween(Integer value1, Integer value2) {
            addCriterion("pay_member_count between", value1, value2, "payMemberCount");
            return (Criteria) this;
        }

        public Criteria andPayMemberCountNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_member_count not between", value1, value2, "payMemberCount");
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