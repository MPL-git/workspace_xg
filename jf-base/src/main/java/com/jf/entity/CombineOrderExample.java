package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CombineOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public CombineOrderExample() {
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

        public Criteria andCombineOrderCodeIsNull() {
            addCriterion("combine_order_code is null");
            return (Criteria) this;
        }

        public Criteria andCombineOrderCodeIsNotNull() {
            addCriterion("combine_order_code is not null");
            return (Criteria) this;
        }

        public Criteria andCombineOrderCodeEqualTo(String value) {
            addCriterion("combine_order_code =", value, "combineOrderCode");
            return (Criteria) this;
        }

        public Criteria andCombineOrderCodeNotEqualTo(String value) {
            addCriterion("combine_order_code <>", value, "combineOrderCode");
            return (Criteria) this;
        }

        public Criteria andCombineOrderCodeGreaterThan(String value) {
            addCriterion("combine_order_code >", value, "combineOrderCode");
            return (Criteria) this;
        }

        public Criteria andCombineOrderCodeGreaterThanOrEqualTo(String value) {
            addCriterion("combine_order_code >=", value, "combineOrderCode");
            return (Criteria) this;
        }

        public Criteria andCombineOrderCodeLessThan(String value) {
            addCriterion("combine_order_code <", value, "combineOrderCode");
            return (Criteria) this;
        }

        public Criteria andCombineOrderCodeLessThanOrEqualTo(String value) {
            addCriterion("combine_order_code <=", value, "combineOrderCode");
            return (Criteria) this;
        }

        public Criteria andCombineOrderCodeLike(String value) {
            addCriterion("combine_order_code like", value, "combineOrderCode");
            return (Criteria) this;
        }

        public Criteria andCombineOrderCodeNotLike(String value) {
            addCriterion("combine_order_code not like", value, "combineOrderCode");
            return (Criteria) this;
        }

        public Criteria andCombineOrderCodeIn(List<String> values) {
            addCriterion("combine_order_code in", values, "combineOrderCode");
            return (Criteria) this;
        }

        public Criteria andCombineOrderCodeNotIn(List<String> values) {
            addCriterion("combine_order_code not in", values, "combineOrderCode");
            return (Criteria) this;
        }

        public Criteria andCombineOrderCodeBetween(String value1, String value2) {
            addCriterion("combine_order_code between", value1, value2, "combineOrderCode");
            return (Criteria) this;
        }

        public Criteria andCombineOrderCodeNotBetween(String value1, String value2) {
            addCriterion("combine_order_code not between", value1, value2, "combineOrderCode");
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

        public Criteria andMemberNickIsNull() {
            addCriterion("member_nick is null");
            return (Criteria) this;
        }

        public Criteria andMemberNickIsNotNull() {
            addCriterion("member_nick is not null");
            return (Criteria) this;
        }

        public Criteria andMemberNickEqualTo(String value) {
            addCriterion("member_nick =", value, "memberNick");
            return (Criteria) this;
        }

        public Criteria andMemberNickNotEqualTo(String value) {
            addCriterion("member_nick <>", value, "memberNick");
            return (Criteria) this;
        }

        public Criteria andMemberNickGreaterThan(String value) {
            addCriterion("member_nick >", value, "memberNick");
            return (Criteria) this;
        }

        public Criteria andMemberNickGreaterThanOrEqualTo(String value) {
            addCriterion("member_nick >=", value, "memberNick");
            return (Criteria) this;
        }

        public Criteria andMemberNickLessThan(String value) {
            addCriterion("member_nick <", value, "memberNick");
            return (Criteria) this;
        }

        public Criteria andMemberNickLessThanOrEqualTo(String value) {
            addCriterion("member_nick <=", value, "memberNick");
            return (Criteria) this;
        }

        public Criteria andMemberNickLike(String value) {
            addCriterion("member_nick like", value, "memberNick");
            return (Criteria) this;
        }

        public Criteria andMemberNickNotLike(String value) {
            addCriterion("member_nick not like", value, "memberNick");
            return (Criteria) this;
        }

        public Criteria andMemberNickIn(List<String> values) {
            addCriterion("member_nick in", values, "memberNick");
            return (Criteria) this;
        }

        public Criteria andMemberNickNotIn(List<String> values) {
            addCriterion("member_nick not in", values, "memberNick");
            return (Criteria) this;
        }

        public Criteria andMemberNickBetween(String value1, String value2) {
            addCriterion("member_nick between", value1, value2, "memberNick");
            return (Criteria) this;
        }

        public Criteria andMemberNickNotBetween(String value1, String value2) {
            addCriterion("member_nick not between", value1, value2, "memberNick");
            return (Criteria) this;
        }

        public Criteria andAddressIdIsNull() {
            addCriterion("address_id is null");
            return (Criteria) this;
        }

        public Criteria andAddressIdIsNotNull() {
            addCriterion("address_id is not null");
            return (Criteria) this;
        }

        public Criteria andAddressIdEqualTo(Integer value) {
            addCriterion("address_id =", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotEqualTo(Integer value) {
            addCriterion("address_id <>", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdGreaterThan(Integer value) {
            addCriterion("address_id >", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("address_id >=", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdLessThan(Integer value) {
            addCriterion("address_id <", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdLessThanOrEqualTo(Integer value) {
            addCriterion("address_id <=", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdIn(List<Integer> values) {
            addCriterion("address_id in", values, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotIn(List<Integer> values) {
            addCriterion("address_id not in", values, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdBetween(Integer value1, Integer value2) {
            addCriterion("address_id between", value1, value2, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotBetween(Integer value1, Integer value2) {
            addCriterion("address_id not between", value1, value2, "addressId");
            return (Criteria) this;
        }

        public Criteria andReceiverNameIsNull() {
            addCriterion("receiver_name is null");
            return (Criteria) this;
        }

        public Criteria andReceiverNameIsNotNull() {
            addCriterion("receiver_name is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverNameEqualTo(String value) {
            addCriterion("receiver_name =", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotEqualTo(String value) {
            addCriterion("receiver_name <>", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameGreaterThan(String value) {
            addCriterion("receiver_name >", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_name >=", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameLessThan(String value) {
            addCriterion("receiver_name <", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameLessThanOrEqualTo(String value) {
            addCriterion("receiver_name <=", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameLike(String value) {
            addCriterion("receiver_name like", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotLike(String value) {
            addCriterion("receiver_name not like", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameIn(List<String> values) {
            addCriterion("receiver_name in", values, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotIn(List<String> values) {
            addCriterion("receiver_name not in", values, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameBetween(String value1, String value2) {
            addCriterion("receiver_name between", value1, value2, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotBetween(String value1, String value2) {
            addCriterion("receiver_name not between", value1, value2, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneIsNull() {
            addCriterion("receiver_phone is null");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneIsNotNull() {
            addCriterion("receiver_phone is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneEqualTo(String value) {
            addCriterion("receiver_phone =", value, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneNotEqualTo(String value) {
            addCriterion("receiver_phone <>", value, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneGreaterThan(String value) {
            addCriterion("receiver_phone >", value, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_phone >=", value, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneLessThan(String value) {
            addCriterion("receiver_phone <", value, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneLessThanOrEqualTo(String value) {
            addCriterion("receiver_phone <=", value, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneLike(String value) {
            addCriterion("receiver_phone like", value, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneNotLike(String value) {
            addCriterion("receiver_phone not like", value, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneIn(List<String> values) {
            addCriterion("receiver_phone in", values, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneNotIn(List<String> values) {
            addCriterion("receiver_phone not in", values, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneBetween(String value1, String value2) {
            addCriterion("receiver_phone between", value1, value2, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneNotBetween(String value1, String value2) {
            addCriterion("receiver_phone not between", value1, value2, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressIsNull() {
            addCriterion("receiver_address is null");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressIsNotNull() {
            addCriterion("receiver_address is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressEqualTo(String value) {
            addCriterion("receiver_address =", value, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressNotEqualTo(String value) {
            addCriterion("receiver_address <>", value, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressGreaterThan(String value) {
            addCriterion("receiver_address >", value, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_address >=", value, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressLessThan(String value) {
            addCriterion("receiver_address <", value, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressLessThanOrEqualTo(String value) {
            addCriterion("receiver_address <=", value, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressLike(String value) {
            addCriterion("receiver_address like", value, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressNotLike(String value) {
            addCriterion("receiver_address not like", value, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressIn(List<String> values) {
            addCriterion("receiver_address in", values, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressNotIn(List<String> values) {
            addCriterion("receiver_address not in", values, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressBetween(String value1, String value2) {
            addCriterion("receiver_address between", value1, value2, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressNotBetween(String value1, String value2) {
            addCriterion("receiver_address not between", value1, value2, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIsNull() {
            addCriterion("total_amount is null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIsNotNull() {
            addCriterion("total_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountEqualTo(BigDecimal value) {
            addCriterion("total_amount =", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotEqualTo(BigDecimal value) {
            addCriterion("total_amount <>", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountGreaterThan(BigDecimal value) {
            addCriterion("total_amount >", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_amount >=", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountLessThan(BigDecimal value) {
            addCriterion("total_amount <", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_amount <=", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIn(List<BigDecimal> values) {
            addCriterion("total_amount in", values, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotIn(List<BigDecimal> values) {
            addCriterion("total_amount not in", values, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_amount between", value1, value2, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_amount not between", value1, value2, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalPayAmountIsNull() {
            addCriterion("total_pay_amount is null");
            return (Criteria) this;
        }

        public Criteria andTotalPayAmountIsNotNull() {
            addCriterion("total_pay_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTotalPayAmountEqualTo(BigDecimal value) {
            addCriterion("total_pay_amount =", value, "totalPayAmount");
            return (Criteria) this;
        }

        public Criteria andTotalPayAmountNotEqualTo(BigDecimal value) {
            addCriterion("total_pay_amount <>", value, "totalPayAmount");
            return (Criteria) this;
        }

        public Criteria andTotalPayAmountGreaterThan(BigDecimal value) {
            addCriterion("total_pay_amount >", value, "totalPayAmount");
            return (Criteria) this;
        }

        public Criteria andTotalPayAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_pay_amount >=", value, "totalPayAmount");
            return (Criteria) this;
        }

        public Criteria andTotalPayAmountLessThan(BigDecimal value) {
            addCriterion("total_pay_amount <", value, "totalPayAmount");
            return (Criteria) this;
        }

        public Criteria andTotalPayAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_pay_amount <=", value, "totalPayAmount");
            return (Criteria) this;
        }

        public Criteria andTotalPayAmountIn(List<BigDecimal> values) {
            addCriterion("total_pay_amount in", values, "totalPayAmount");
            return (Criteria) this;
        }

        public Criteria andTotalPayAmountNotIn(List<BigDecimal> values) {
            addCriterion("total_pay_amount not in", values, "totalPayAmount");
            return (Criteria) this;
        }

        public Criteria andTotalPayAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_pay_amount between", value1, value2, "totalPayAmount");
            return (Criteria) this;
        }

        public Criteria andTotalPayAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_pay_amount not between", value1, value2, "totalPayAmount");
            return (Criteria) this;
        }

        public Criteria andTotalPlatformPreferentialIsNull() {
            addCriterion("total_platform_preferential is null");
            return (Criteria) this;
        }

        public Criteria andTotalPlatformPreferentialIsNotNull() {
            addCriterion("total_platform_preferential is not null");
            return (Criteria) this;
        }

        public Criteria andTotalPlatformPreferentialEqualTo(BigDecimal value) {
            addCriterion("total_platform_preferential =", value, "totalPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andTotalPlatformPreferentialNotEqualTo(BigDecimal value) {
            addCriterion("total_platform_preferential <>", value, "totalPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andTotalPlatformPreferentialGreaterThan(BigDecimal value) {
            addCriterion("total_platform_preferential >", value, "totalPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andTotalPlatformPreferentialGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_platform_preferential >=", value, "totalPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andTotalPlatformPreferentialLessThan(BigDecimal value) {
            addCriterion("total_platform_preferential <", value, "totalPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andTotalPlatformPreferentialLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_platform_preferential <=", value, "totalPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andTotalPlatformPreferentialIn(List<BigDecimal> values) {
            addCriterion("total_platform_preferential in", values, "totalPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andTotalPlatformPreferentialNotIn(List<BigDecimal> values) {
            addCriterion("total_platform_preferential not in", values, "totalPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andTotalPlatformPreferentialBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_platform_preferential between", value1, value2, "totalPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andTotalPlatformPreferentialNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_platform_preferential not between", value1, value2, "totalPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andTotalMchtPreferentialIsNull() {
            addCriterion("total_mcht_preferential is null");
            return (Criteria) this;
        }

        public Criteria andTotalMchtPreferentialIsNotNull() {
            addCriterion("total_mcht_preferential is not null");
            return (Criteria) this;
        }

        public Criteria andTotalMchtPreferentialEqualTo(BigDecimal value) {
            addCriterion("total_mcht_preferential =", value, "totalMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andTotalMchtPreferentialNotEqualTo(BigDecimal value) {
            addCriterion("total_mcht_preferential <>", value, "totalMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andTotalMchtPreferentialGreaterThan(BigDecimal value) {
            addCriterion("total_mcht_preferential >", value, "totalMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andTotalMchtPreferentialGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_mcht_preferential >=", value, "totalMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andTotalMchtPreferentialLessThan(BigDecimal value) {
            addCriterion("total_mcht_preferential <", value, "totalMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andTotalMchtPreferentialLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_mcht_preferential <=", value, "totalMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andTotalMchtPreferentialIn(List<BigDecimal> values) {
            addCriterion("total_mcht_preferential in", values, "totalMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andTotalMchtPreferentialNotIn(List<BigDecimal> values) {
            addCriterion("total_mcht_preferential not in", values, "totalMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andTotalMchtPreferentialBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_mcht_preferential between", value1, value2, "totalMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andTotalMchtPreferentialNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_mcht_preferential not between", value1, value2, "totalMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andTotalIntegralPreferentialIsNull() {
            addCriterion("total_integral_preferential is null");
            return (Criteria) this;
        }

        public Criteria andTotalIntegralPreferentialIsNotNull() {
            addCriterion("total_integral_preferential is not null");
            return (Criteria) this;
        }

        public Criteria andTotalIntegralPreferentialEqualTo(BigDecimal value) {
            addCriterion("total_integral_preferential =", value, "totalIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andTotalIntegralPreferentialNotEqualTo(BigDecimal value) {
            addCriterion("total_integral_preferential <>", value, "totalIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andTotalIntegralPreferentialGreaterThan(BigDecimal value) {
            addCriterion("total_integral_preferential >", value, "totalIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andTotalIntegralPreferentialGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_integral_preferential >=", value, "totalIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andTotalIntegralPreferentialLessThan(BigDecimal value) {
            addCriterion("total_integral_preferential <", value, "totalIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andTotalIntegralPreferentialLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_integral_preferential <=", value, "totalIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andTotalIntegralPreferentialIn(List<BigDecimal> values) {
            addCriterion("total_integral_preferential in", values, "totalIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andTotalIntegralPreferentialNotIn(List<BigDecimal> values) {
            addCriterion("total_integral_preferential not in", values, "totalIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andTotalIntegralPreferentialBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_integral_preferential between", value1, value2, "totalIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andTotalIntegralPreferentialNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_integral_preferential not between", value1, value2, "totalIntegralPreferential");
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

        public Criteria andCancelTypeIsNull() {
            addCriterion("cancel_type is null");
            return (Criteria) this;
        }

        public Criteria andCancelTypeIsNotNull() {
            addCriterion("cancel_type is not null");
            return (Criteria) this;
        }

        public Criteria andCancelTypeEqualTo(String value) {
            addCriterion("cancel_type =", value, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelTypeNotEqualTo(String value) {
            addCriterion("cancel_type <>", value, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelTypeGreaterThan(String value) {
            addCriterion("cancel_type >", value, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelTypeGreaterThanOrEqualTo(String value) {
            addCriterion("cancel_type >=", value, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelTypeLessThan(String value) {
            addCriterion("cancel_type <", value, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelTypeLessThanOrEqualTo(String value) {
            addCriterion("cancel_type <=", value, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelTypeLike(String value) {
            addCriterion("cancel_type like", value, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelTypeNotLike(String value) {
            addCriterion("cancel_type not like", value, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelTypeIn(List<String> values) {
            addCriterion("cancel_type in", values, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelTypeNotIn(List<String> values) {
            addCriterion("cancel_type not in", values, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelTypeBetween(String value1, String value2) {
            addCriterion("cancel_type between", value1, value2, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelTypeNotBetween(String value1, String value2) {
            addCriterion("cancel_type not between", value1, value2, "cancelType");
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

        public Criteria andCancelDateIsNull() {
            addCriterion("cancel_date is null");
            return (Criteria) this;
        }

        public Criteria andCancelDateIsNotNull() {
            addCriterion("cancel_date is not null");
            return (Criteria) this;
        }

        public Criteria andCancelDateEqualTo(Date value) {
            addCriterion("cancel_date =", value, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateNotEqualTo(Date value) {
            addCriterion("cancel_date <>", value, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateGreaterThan(Date value) {
            addCriterion("cancel_date >", value, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateGreaterThanOrEqualTo(Date value) {
            addCriterion("cancel_date >=", value, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateLessThan(Date value) {
            addCriterion("cancel_date <", value, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateLessThanOrEqualTo(Date value) {
            addCriterion("cancel_date <=", value, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateIn(List<Date> values) {
            addCriterion("cancel_date in", values, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateNotIn(List<Date> values) {
            addCriterion("cancel_date not in", values, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateBetween(Date value1, Date value2) {
            addCriterion("cancel_date between", value1, value2, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateNotBetween(Date value1, Date value2) {
            addCriterion("cancel_date not between", value1, value2, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateIsNull() {
            addCriterion("status_date is null");
            return (Criteria) this;
        }

        public Criteria andStatusDateIsNotNull() {
            addCriterion("status_date is not null");
            return (Criteria) this;
        }

        public Criteria andStatusDateEqualTo(Date value) {
            addCriterion("status_date =", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateNotEqualTo(Date value) {
            addCriterion("status_date <>", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateGreaterThan(Date value) {
            addCriterion("status_date >", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateGreaterThanOrEqualTo(Date value) {
            addCriterion("status_date >=", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateLessThan(Date value) {
            addCriterion("status_date <", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateLessThanOrEqualTo(Date value) {
            addCriterion("status_date <=", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateIn(List<Date> values) {
            addCriterion("status_date in", values, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateNotIn(List<Date> values) {
            addCriterion("status_date not in", values, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateBetween(Date value1, Date value2) {
            addCriterion("status_date between", value1, value2, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateNotBetween(Date value1, Date value2) {
            addCriterion("status_date not between", value1, value2, "statusDate");
            return (Criteria) this;
        }

        public Criteria andIsUserDelIsNull() {
            addCriterion("is_user_del is null");
            return (Criteria) this;
        }

        public Criteria andIsUserDelIsNotNull() {
            addCriterion("is_user_del is not null");
            return (Criteria) this;
        }

        public Criteria andIsUserDelEqualTo(String value) {
            addCriterion("is_user_del =", value, "isUserDel");
            return (Criteria) this;
        }

        public Criteria andIsUserDelNotEqualTo(String value) {
            addCriterion("is_user_del <>", value, "isUserDel");
            return (Criteria) this;
        }

        public Criteria andIsUserDelGreaterThan(String value) {
            addCriterion("is_user_del >", value, "isUserDel");
            return (Criteria) this;
        }

        public Criteria andIsUserDelGreaterThanOrEqualTo(String value) {
            addCriterion("is_user_del >=", value, "isUserDel");
            return (Criteria) this;
        }

        public Criteria andIsUserDelLessThan(String value) {
            addCriterion("is_user_del <", value, "isUserDel");
            return (Criteria) this;
        }

        public Criteria andIsUserDelLessThanOrEqualTo(String value) {
            addCriterion("is_user_del <=", value, "isUserDel");
            return (Criteria) this;
        }

        public Criteria andIsUserDelLike(String value) {
            addCriterion("is_user_del like", value, "isUserDel");
            return (Criteria) this;
        }

        public Criteria andIsUserDelNotLike(String value) {
            addCriterion("is_user_del not like", value, "isUserDel");
            return (Criteria) this;
        }

        public Criteria andIsUserDelIn(List<String> values) {
            addCriterion("is_user_del in", values, "isUserDel");
            return (Criteria) this;
        }

        public Criteria andIsUserDelNotIn(List<String> values) {
            addCriterion("is_user_del not in", values, "isUserDel");
            return (Criteria) this;
        }

        public Criteria andIsUserDelBetween(String value1, String value2) {
            addCriterion("is_user_del between", value1, value2, "isUserDel");
            return (Criteria) this;
        }

        public Criteria andIsUserDelNotBetween(String value1, String value2) {
            addCriterion("is_user_del not between", value1, value2, "isUserDel");
            return (Criteria) this;
        }

        public Criteria andPaymentIdIsNull() {
            addCriterion("payment_id is null");
            return (Criteria) this;
        }

        public Criteria andPaymentIdIsNotNull() {
            addCriterion("payment_id is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentIdEqualTo(Integer value) {
            addCriterion("payment_id =", value, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdNotEqualTo(Integer value) {
            addCriterion("payment_id <>", value, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdGreaterThan(Integer value) {
            addCriterion("payment_id >", value, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("payment_id >=", value, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdLessThan(Integer value) {
            addCriterion("payment_id <", value, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdLessThanOrEqualTo(Integer value) {
            addCriterion("payment_id <=", value, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdIn(List<Integer> values) {
            addCriterion("payment_id in", values, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdNotIn(List<Integer> values) {
            addCriterion("payment_id not in", values, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdBetween(Integer value1, Integer value2) {
            addCriterion("payment_id between", value1, value2, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("payment_id not between", value1, value2, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentNoIsNull() {
            addCriterion("payment_no is null");
            return (Criteria) this;
        }

        public Criteria andPaymentNoIsNotNull() {
            addCriterion("payment_no is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentNoEqualTo(String value) {
            addCriterion("payment_no =", value, "paymentNo");
            return (Criteria) this;
        }

        public Criteria andPaymentNoNotEqualTo(String value) {
            addCriterion("payment_no <>", value, "paymentNo");
            return (Criteria) this;
        }

        public Criteria andPaymentNoGreaterThan(String value) {
            addCriterion("payment_no >", value, "paymentNo");
            return (Criteria) this;
        }

        public Criteria andPaymentNoGreaterThanOrEqualTo(String value) {
            addCriterion("payment_no >=", value, "paymentNo");
            return (Criteria) this;
        }

        public Criteria andPaymentNoLessThan(String value) {
            addCriterion("payment_no <", value, "paymentNo");
            return (Criteria) this;
        }

        public Criteria andPaymentNoLessThanOrEqualTo(String value) {
            addCriterion("payment_no <=", value, "paymentNo");
            return (Criteria) this;
        }

        public Criteria andPaymentNoLike(String value) {
            addCriterion("payment_no like", value, "paymentNo");
            return (Criteria) this;
        }

        public Criteria andPaymentNoNotLike(String value) {
            addCriterion("payment_no not like", value, "paymentNo");
            return (Criteria) this;
        }

        public Criteria andPaymentNoIn(List<String> values) {
            addCriterion("payment_no in", values, "paymentNo");
            return (Criteria) this;
        }

        public Criteria andPaymentNoNotIn(List<String> values) {
            addCriterion("payment_no not in", values, "paymentNo");
            return (Criteria) this;
        }

        public Criteria andPaymentNoBetween(String value1, String value2) {
            addCriterion("payment_no between", value1, value2, "paymentNo");
            return (Criteria) this;
        }

        public Criteria andPaymentNoNotBetween(String value1, String value2) {
            addCriterion("payment_no not between", value1, value2, "paymentNo");
            return (Criteria) this;
        }

        public Criteria andPayDateIsNull() {
            addCriterion("pay_date is null");
            return (Criteria) this;
        }

        public Criteria andPayDateIsNotNull() {
            addCriterion("pay_date is not null");
            return (Criteria) this;
        }

        public Criteria andPayDateEqualTo(Date value) {
            addCriterion("pay_date =", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateNotEqualTo(Date value) {
            addCriterion("pay_date <>", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateGreaterThan(Date value) {
            addCriterion("pay_date >", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_date >=", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateLessThan(Date value) {
            addCriterion("pay_date <", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateLessThanOrEqualTo(Date value) {
            addCriterion("pay_date <=", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateIn(List<Date> values) {
            addCriterion("pay_date in", values, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateNotIn(List<Date> values) {
            addCriterion("pay_date not in", values, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateBetween(Date value1, Date value2) {
            addCriterion("pay_date between", value1, value2, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateNotBetween(Date value1, Date value2) {
            addCriterion("pay_date not between", value1, value2, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayStatusIsNull() {
            addCriterion("pay_status is null");
            return (Criteria) this;
        }

        public Criteria andPayStatusIsNotNull() {
            addCriterion("pay_status is not null");
            return (Criteria) this;
        }

        public Criteria andPayStatusEqualTo(String value) {
            addCriterion("pay_status =", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotEqualTo(String value) {
            addCriterion("pay_status <>", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusGreaterThan(String value) {
            addCriterion("pay_status >", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusGreaterThanOrEqualTo(String value) {
            addCriterion("pay_status >=", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLessThan(String value) {
            addCriterion("pay_status <", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLessThanOrEqualTo(String value) {
            addCriterion("pay_status <=", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLike(String value) {
            addCriterion("pay_status like", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotLike(String value) {
            addCriterion("pay_status not like", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusIn(List<String> values) {
            addCriterion("pay_status in", values, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotIn(List<String> values) {
            addCriterion("pay_status not in", values, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusBetween(String value1, String value2) {
            addCriterion("pay_status between", value1, value2, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotBetween(String value1, String value2) {
            addCriterion("pay_status not between", value1, value2, "payStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusIsNull() {
            addCriterion("financial_status is null");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusIsNotNull() {
            addCriterion("financial_status is not null");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusEqualTo(String value) {
            addCriterion("financial_status =", value, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusNotEqualTo(String value) {
            addCriterion("financial_status <>", value, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusGreaterThan(String value) {
            addCriterion("financial_status >", value, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusGreaterThanOrEqualTo(String value) {
            addCriterion("financial_status >=", value, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusLessThan(String value) {
            addCriterion("financial_status <", value, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusLessThanOrEqualTo(String value) {
            addCriterion("financial_status <=", value, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusLike(String value) {
            addCriterion("financial_status like", value, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusNotLike(String value) {
            addCriterion("financial_status not like", value, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusIn(List<String> values) {
            addCriterion("financial_status in", values, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusNotIn(List<String> values) {
            addCriterion("financial_status not in", values, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusBetween(String value1, String value2) {
            addCriterion("financial_status between", value1, value2, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusNotBetween(String value1, String value2) {
            addCriterion("financial_status not between", value1, value2, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialNoIsNull() {
            addCriterion("financial_no is null");
            return (Criteria) this;
        }

        public Criteria andFinancialNoIsNotNull() {
            addCriterion("financial_no is not null");
            return (Criteria) this;
        }

        public Criteria andFinancialNoEqualTo(String value) {
            addCriterion("financial_no =", value, "financialNo");
            return (Criteria) this;
        }

        public Criteria andFinancialNoNotEqualTo(String value) {
            addCriterion("financial_no <>", value, "financialNo");
            return (Criteria) this;
        }

        public Criteria andFinancialNoGreaterThan(String value) {
            addCriterion("financial_no >", value, "financialNo");
            return (Criteria) this;
        }

        public Criteria andFinancialNoGreaterThanOrEqualTo(String value) {
            addCriterion("financial_no >=", value, "financialNo");
            return (Criteria) this;
        }

        public Criteria andFinancialNoLessThan(String value) {
            addCriterion("financial_no <", value, "financialNo");
            return (Criteria) this;
        }

        public Criteria andFinancialNoLessThanOrEqualTo(String value) {
            addCriterion("financial_no <=", value, "financialNo");
            return (Criteria) this;
        }

        public Criteria andFinancialNoLike(String value) {
            addCriterion("financial_no like", value, "financialNo");
            return (Criteria) this;
        }

        public Criteria andFinancialNoNotLike(String value) {
            addCriterion("financial_no not like", value, "financialNo");
            return (Criteria) this;
        }

        public Criteria andFinancialNoIn(List<String> values) {
            addCriterion("financial_no in", values, "financialNo");
            return (Criteria) this;
        }

        public Criteria andFinancialNoNotIn(List<String> values) {
            addCriterion("financial_no not in", values, "financialNo");
            return (Criteria) this;
        }

        public Criteria andFinancialNoBetween(String value1, String value2) {
            addCriterion("financial_no between", value1, value2, "financialNo");
            return (Criteria) this;
        }

        public Criteria andFinancialNoNotBetween(String value1, String value2) {
            addCriterion("financial_no not between", value1, value2, "financialNo");
            return (Criteria) this;
        }

        public Criteria andCollectionRegisterDateIsNull() {
            addCriterion("collection_register_date is null");
            return (Criteria) this;
        }

        public Criteria andCollectionRegisterDateIsNotNull() {
            addCriterion("collection_register_date is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionRegisterDateEqualTo(Date value) {
            addCriterion("collection_register_date =", value, "collectionRegisterDate");
            return (Criteria) this;
        }

        public Criteria andCollectionRegisterDateNotEqualTo(Date value) {
            addCriterion("collection_register_date <>", value, "collectionRegisterDate");
            return (Criteria) this;
        }

        public Criteria andCollectionRegisterDateGreaterThan(Date value) {
            addCriterion("collection_register_date >", value, "collectionRegisterDate");
            return (Criteria) this;
        }

        public Criteria andCollectionRegisterDateGreaterThanOrEqualTo(Date value) {
            addCriterion("collection_register_date >=", value, "collectionRegisterDate");
            return (Criteria) this;
        }

        public Criteria andCollectionRegisterDateLessThan(Date value) {
            addCriterion("collection_register_date <", value, "collectionRegisterDate");
            return (Criteria) this;
        }

        public Criteria andCollectionRegisterDateLessThanOrEqualTo(Date value) {
            addCriterion("collection_register_date <=", value, "collectionRegisterDate");
            return (Criteria) this;
        }

        public Criteria andCollectionRegisterDateIn(List<Date> values) {
            addCriterion("collection_register_date in", values, "collectionRegisterDate");
            return (Criteria) this;
        }

        public Criteria andCollectionRegisterDateNotIn(List<Date> values) {
            addCriterion("collection_register_date not in", values, "collectionRegisterDate");
            return (Criteria) this;
        }

        public Criteria andCollectionRegisterDateBetween(Date value1, Date value2) {
            addCriterion("collection_register_date between", value1, value2, "collectionRegisterDate");
            return (Criteria) this;
        }

        public Criteria andCollectionRegisterDateNotBetween(Date value1, Date value2) {
            addCriterion("collection_register_date not between", value1, value2, "collectionRegisterDate");
            return (Criteria) this;
        }

        public Criteria andFinancialStaffIsNull() {
            addCriterion("financial_staff is null");
            return (Criteria) this;
        }

        public Criteria andFinancialStaffIsNotNull() {
            addCriterion("financial_staff is not null");
            return (Criteria) this;
        }

        public Criteria andFinancialStaffEqualTo(Integer value) {
            addCriterion("financial_staff =", value, "financialStaff");
            return (Criteria) this;
        }

        public Criteria andFinancialStaffNotEqualTo(Integer value) {
            addCriterion("financial_staff <>", value, "financialStaff");
            return (Criteria) this;
        }

        public Criteria andFinancialStaffGreaterThan(Integer value) {
            addCriterion("financial_staff >", value, "financialStaff");
            return (Criteria) this;
        }

        public Criteria andFinancialStaffGreaterThanOrEqualTo(Integer value) {
            addCriterion("financial_staff >=", value, "financialStaff");
            return (Criteria) this;
        }

        public Criteria andFinancialStaffLessThan(Integer value) {
            addCriterion("financial_staff <", value, "financialStaff");
            return (Criteria) this;
        }

        public Criteria andFinancialStaffLessThanOrEqualTo(Integer value) {
            addCriterion("financial_staff <=", value, "financialStaff");
            return (Criteria) this;
        }

        public Criteria andFinancialStaffIn(List<Integer> values) {
            addCriterion("financial_staff in", values, "financialStaff");
            return (Criteria) this;
        }

        public Criteria andFinancialStaffNotIn(List<Integer> values) {
            addCriterion("financial_staff not in", values, "financialStaff");
            return (Criteria) this;
        }

        public Criteria andFinancialStaffBetween(Integer value1, Integer value2) {
            addCriterion("financial_staff between", value1, value2, "financialStaff");
            return (Criteria) this;
        }

        public Criteria andFinancialStaffNotBetween(Integer value1, Integer value2) {
            addCriterion("financial_staff not between", value1, value2, "financialStaff");
            return (Criteria) this;
        }

        public Criteria andFinancialUpdateDateIsNull() {
            addCriterion("financial_update_date is null");
            return (Criteria) this;
        }

        public Criteria andFinancialUpdateDateIsNotNull() {
            addCriterion("financial_update_date is not null");
            return (Criteria) this;
        }

        public Criteria andFinancialUpdateDateEqualTo(Date value) {
            addCriterion("financial_update_date =", value, "financialUpdateDate");
            return (Criteria) this;
        }

        public Criteria andFinancialUpdateDateNotEqualTo(Date value) {
            addCriterion("financial_update_date <>", value, "financialUpdateDate");
            return (Criteria) this;
        }

        public Criteria andFinancialUpdateDateGreaterThan(Date value) {
            addCriterion("financial_update_date >", value, "financialUpdateDate");
            return (Criteria) this;
        }

        public Criteria andFinancialUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("financial_update_date >=", value, "financialUpdateDate");
            return (Criteria) this;
        }

        public Criteria andFinancialUpdateDateLessThan(Date value) {
            addCriterion("financial_update_date <", value, "financialUpdateDate");
            return (Criteria) this;
        }

        public Criteria andFinancialUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("financial_update_date <=", value, "financialUpdateDate");
            return (Criteria) this;
        }

        public Criteria andFinancialUpdateDateIn(List<Date> values) {
            addCriterion("financial_update_date in", values, "financialUpdateDate");
            return (Criteria) this;
        }

        public Criteria andFinancialUpdateDateNotIn(List<Date> values) {
            addCriterion("financial_update_date not in", values, "financialUpdateDate");
            return (Criteria) this;
        }

        public Criteria andFinancialUpdateDateBetween(Date value1, Date value2) {
            addCriterion("financial_update_date between", value1, value2, "financialUpdateDate");
            return (Criteria) this;
        }

        public Criteria andFinancialUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("financial_update_date not between", value1, value2, "financialUpdateDate");
            return (Criteria) this;
        }

        public Criteria andSourceClientIsNull() {
            addCriterion("source_client is null");
            return (Criteria) this;
        }

        public Criteria andSourceClientIsNotNull() {
            addCriterion("source_client is not null");
            return (Criteria) this;
        }

        public Criteria andSourceClientEqualTo(String value) {
            addCriterion("source_client =", value, "sourceClient");
            return (Criteria) this;
        }

        public Criteria andSourceClientNotEqualTo(String value) {
            addCriterion("source_client <>", value, "sourceClient");
            return (Criteria) this;
        }

        public Criteria andSourceClientGreaterThan(String value) {
            addCriterion("source_client >", value, "sourceClient");
            return (Criteria) this;
        }

        public Criteria andSourceClientGreaterThanOrEqualTo(String value) {
            addCriterion("source_client >=", value, "sourceClient");
            return (Criteria) this;
        }

        public Criteria andSourceClientLessThan(String value) {
            addCriterion("source_client <", value, "sourceClient");
            return (Criteria) this;
        }

        public Criteria andSourceClientLessThanOrEqualTo(String value) {
            addCriterion("source_client <=", value, "sourceClient");
            return (Criteria) this;
        }

        public Criteria andSourceClientLike(String value) {
            addCriterion("source_client like", value, "sourceClient");
            return (Criteria) this;
        }

        public Criteria andSourceClientNotLike(String value) {
            addCriterion("source_client not like", value, "sourceClient");
            return (Criteria) this;
        }

        public Criteria andSourceClientIn(List<String> values) {
            addCriterion("source_client in", values, "sourceClient");
            return (Criteria) this;
        }

        public Criteria andSourceClientNotIn(List<String> values) {
            addCriterion("source_client not in", values, "sourceClient");
            return (Criteria) this;
        }

        public Criteria andSourceClientBetween(String value1, String value2) {
            addCriterion("source_client between", value1, value2, "sourceClient");
            return (Criteria) this;
        }

        public Criteria andSourceClientNotBetween(String value1, String value2) {
            addCriterion("source_client not between", value1, value2, "sourceClient");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIsNull() {
            addCriterion("order_type is null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIsNotNull() {
            addCriterion("order_type is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeEqualTo(String value) {
            addCriterion("order_type =", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotEqualTo(String value) {
            addCriterion("order_type <>", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThan(String value) {
            addCriterion("order_type >", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThanOrEqualTo(String value) {
            addCriterion("order_type >=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThan(String value) {
            addCriterion("order_type <", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThanOrEqualTo(String value) {
            addCriterion("order_type <=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLike(String value) {
            addCriterion("order_type like", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotLike(String value) {
            addCriterion("order_type not like", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIn(List<String> values) {
            addCriterion("order_type in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotIn(List<String> values) {
            addCriterion("order_type not in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeBetween(String value1, String value2) {
            addCriterion("order_type between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotBetween(String value1, String value2) {
            addCriterion("order_type not between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andPayExtraInfoIsNull() {
            addCriterion("pay_extra_info is null");
            return (Criteria) this;
        }

        public Criteria andPayExtraInfoIsNotNull() {
            addCriterion("pay_extra_info is not null");
            return (Criteria) this;
        }

        public Criteria andPayExtraInfoEqualTo(String value) {
            addCriterion("pay_extra_info =", value, "payExtraInfo");
            return (Criteria) this;
        }

        public Criteria andPayExtraInfoNotEqualTo(String value) {
            addCriterion("pay_extra_info <>", value, "payExtraInfo");
            return (Criteria) this;
        }

        public Criteria andPayExtraInfoGreaterThan(String value) {
            addCriterion("pay_extra_info >", value, "payExtraInfo");
            return (Criteria) this;
        }

        public Criteria andPayExtraInfoGreaterThanOrEqualTo(String value) {
            addCriterion("pay_extra_info >=", value, "payExtraInfo");
            return (Criteria) this;
        }

        public Criteria andPayExtraInfoLessThan(String value) {
            addCriterion("pay_extra_info <", value, "payExtraInfo");
            return (Criteria) this;
        }

        public Criteria andPayExtraInfoLessThanOrEqualTo(String value) {
            addCriterion("pay_extra_info <=", value, "payExtraInfo");
            return (Criteria) this;
        }

        public Criteria andPayExtraInfoLike(String value) {
            addCriterion("pay_extra_info like", value, "payExtraInfo");
            return (Criteria) this;
        }

        public Criteria andPayExtraInfoNotLike(String value) {
            addCriterion("pay_extra_info not like", value, "payExtraInfo");
            return (Criteria) this;
        }

        public Criteria andPayExtraInfoIn(List<String> values) {
            addCriterion("pay_extra_info in", values, "payExtraInfo");
            return (Criteria) this;
        }

        public Criteria andPayExtraInfoNotIn(List<String> values) {
            addCriterion("pay_extra_info not in", values, "payExtraInfo");
            return (Criteria) this;
        }

        public Criteria andPayExtraInfoBetween(String value1, String value2) {
            addCriterion("pay_extra_info between", value1, value2, "payExtraInfo");
            return (Criteria) this;
        }

        public Criteria andPayExtraInfoNotBetween(String value1, String value2) {
            addCriterion("pay_extra_info not between", value1, value2, "payExtraInfo");
            return (Criteria) this;
        }

        public Criteria andOrderExtraInfoIsNull() {
            addCriterion("order_extra_info is null");
            return (Criteria) this;
        }

        public Criteria andOrderExtraInfoIsNotNull() {
            addCriterion("order_extra_info is not null");
            return (Criteria) this;
        }

        public Criteria andOrderExtraInfoEqualTo(String value) {
            addCriterion("order_extra_info =", value, "orderExtraInfo");
            return (Criteria) this;
        }

        public Criteria andOrderExtraInfoNotEqualTo(String value) {
            addCriterion("order_extra_info <>", value, "orderExtraInfo");
            return (Criteria) this;
        }

        public Criteria andOrderExtraInfoGreaterThan(String value) {
            addCriterion("order_extra_info >", value, "orderExtraInfo");
            return (Criteria) this;
        }

        public Criteria andOrderExtraInfoGreaterThanOrEqualTo(String value) {
            addCriterion("order_extra_info >=", value, "orderExtraInfo");
            return (Criteria) this;
        }

        public Criteria andOrderExtraInfoLessThan(String value) {
            addCriterion("order_extra_info <", value, "orderExtraInfo");
            return (Criteria) this;
        }

        public Criteria andOrderExtraInfoLessThanOrEqualTo(String value) {
            addCriterion("order_extra_info <=", value, "orderExtraInfo");
            return (Criteria) this;
        }

        public Criteria andOrderExtraInfoLike(String value) {
            addCriterion("order_extra_info like", value, "orderExtraInfo");
            return (Criteria) this;
        }

        public Criteria andOrderExtraInfoNotLike(String value) {
            addCriterion("order_extra_info not like", value, "orderExtraInfo");
            return (Criteria) this;
        }

        public Criteria andOrderExtraInfoIn(List<String> values) {
            addCriterion("order_extra_info in", values, "orderExtraInfo");
            return (Criteria) this;
        }

        public Criteria andOrderExtraInfoNotIn(List<String> values) {
            addCriterion("order_extra_info not in", values, "orderExtraInfo");
            return (Criteria) this;
        }

        public Criteria andOrderExtraInfoBetween(String value1, String value2) {
            addCriterion("order_extra_info between", value1, value2, "orderExtraInfo");
            return (Criteria) this;
        }

        public Criteria andOrderExtraInfoNotBetween(String value1, String value2) {
            addCriterion("order_extra_info not between", value1, value2, "orderExtraInfo");
            return (Criteria) this;
        }

        public Criteria andFreightIsNull() {
            addCriterion("freight is null");
            return (Criteria) this;
        }

        public Criteria andFreightIsNotNull() {
            addCriterion("freight is not null");
            return (Criteria) this;
        }

        public Criteria andFreightEqualTo(BigDecimal value) {
            addCriterion("freight =", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotEqualTo(BigDecimal value) {
            addCriterion("freight <>", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThan(BigDecimal value) {
            addCriterion("freight >", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freight >=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThan(BigDecimal value) {
            addCriterion("freight <", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freight <=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightIn(List<BigDecimal> values) {
            addCriterion("freight in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotIn(List<BigDecimal> values) {
            addCriterion("freight not in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freight between", value1, value2, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freight not between", value1, value2, "freight");
            return (Criteria) this;
        }

        public Criteria andIsFirstOrderIsNull() {
            addCriterion("is_first_order is null");
            return (Criteria) this;
        }

        public Criteria andIsFirstOrderIsNotNull() {
            addCriterion("is_first_order is not null");
            return (Criteria) this;
        }

        public Criteria andIsFirstOrderEqualTo(String value) {
            addCriterion("is_first_order =", value, "isFirstOrder");
            return (Criteria) this;
        }

        public Criteria andIsFirstOrderNotEqualTo(String value) {
            addCriterion("is_first_order <>", value, "isFirstOrder");
            return (Criteria) this;
        }

        public Criteria andIsFirstOrderGreaterThan(String value) {
            addCriterion("is_first_order >", value, "isFirstOrder");
            return (Criteria) this;
        }

        public Criteria andIsFirstOrderGreaterThanOrEqualTo(String value) {
            addCriterion("is_first_order >=", value, "isFirstOrder");
            return (Criteria) this;
        }

        public Criteria andIsFirstOrderLessThan(String value) {
            addCriterion("is_first_order <", value, "isFirstOrder");
            return (Criteria) this;
        }

        public Criteria andIsFirstOrderLessThanOrEqualTo(String value) {
            addCriterion("is_first_order <=", value, "isFirstOrder");
            return (Criteria) this;
        }

        public Criteria andIsFirstOrderLike(String value) {
            addCriterion("is_first_order like", value, "isFirstOrder");
            return (Criteria) this;
        }

        public Criteria andIsFirstOrderNotLike(String value) {
            addCriterion("is_first_order not like", value, "isFirstOrder");
            return (Criteria) this;
        }

        public Criteria andIsFirstOrderIn(List<String> values) {
            addCriterion("is_first_order in", values, "isFirstOrder");
            return (Criteria) this;
        }

        public Criteria andIsFirstOrderNotIn(List<String> values) {
            addCriterion("is_first_order not in", values, "isFirstOrder");
            return (Criteria) this;
        }

        public Criteria andIsFirstOrderBetween(String value1, String value2) {
            addCriterion("is_first_order between", value1, value2, "isFirstOrder");
            return (Criteria) this;
        }

        public Criteria andIsFirstOrderNotBetween(String value1, String value2) {
            addCriterion("is_first_order not between", value1, value2, "isFirstOrder");
            return (Criteria) this;
        }

        public Criteria andPromotionTypeIsNull() {
            addCriterion("promotion_type is null");
            return (Criteria) this;
        }

        public Criteria andPromotionTypeIsNotNull() {
            addCriterion("promotion_type is not null");
            return (Criteria) this;
        }

        public Criteria andPromotionTypeEqualTo(String value) {
            addCriterion("promotion_type =", value, "promotionType");
            return (Criteria) this;
        }

        public Criteria andPromotionTypeNotEqualTo(String value) {
            addCriterion("promotion_type <>", value, "promotionType");
            return (Criteria) this;
        }

        public Criteria andPromotionTypeGreaterThan(String value) {
            addCriterion("promotion_type >", value, "promotionType");
            return (Criteria) this;
        }

        public Criteria andPromotionTypeGreaterThanOrEqualTo(String value) {
            addCriterion("promotion_type >=", value, "promotionType");
            return (Criteria) this;
        }

        public Criteria andPromotionTypeLessThan(String value) {
            addCriterion("promotion_type <", value, "promotionType");
            return (Criteria) this;
        }

        public Criteria andPromotionTypeLessThanOrEqualTo(String value) {
            addCriterion("promotion_type <=", value, "promotionType");
            return (Criteria) this;
        }

        public Criteria andPromotionTypeLike(String value) {
            addCriterion("promotion_type like", value, "promotionType");
            return (Criteria) this;
        }

        public Criteria andPromotionTypeNotLike(String value) {
            addCriterion("promotion_type not like", value, "promotionType");
            return (Criteria) this;
        }

        public Criteria andPromotionTypeIn(List<String> values) {
            addCriterion("promotion_type in", values, "promotionType");
            return (Criteria) this;
        }

        public Criteria andPromotionTypeNotIn(List<String> values) {
            addCriterion("promotion_type not in", values, "promotionType");
            return (Criteria) this;
        }

        public Criteria andPromotionTypeBetween(String value1, String value2) {
            addCriterion("promotion_type between", value1, value2, "promotionType");
            return (Criteria) this;
        }

        public Criteria andPromotionTypeNotBetween(String value1, String value2) {
            addCriterion("promotion_type not between", value1, value2, "promotionType");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountIsNull() {
            addCriterion("distribution_amount is null");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountIsNotNull() {
            addCriterion("distribution_amount is not null");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountEqualTo(BigDecimal value) {
            addCriterion("distribution_amount =", value, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountNotEqualTo(BigDecimal value) {
            addCriterion("distribution_amount <>", value, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountGreaterThan(BigDecimal value) {
            addCriterion("distribution_amount >", value, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("distribution_amount >=", value, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountLessThan(BigDecimal value) {
            addCriterion("distribution_amount <", value, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("distribution_amount <=", value, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountIn(List<BigDecimal> values) {
            addCriterion("distribution_amount in", values, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountNotIn(List<BigDecimal> values) {
            addCriterion("distribution_amount not in", values, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("distribution_amount between", value1, value2, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("distribution_amount not between", value1, value2, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andSvipOrderIdIsNull() {
            addCriterion("svip_order_id is null");
            return (Criteria) this;
        }

        public Criteria andSvipOrderIdIsNotNull() {
            addCriterion("svip_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andSvipOrderIdEqualTo(Integer value) {
            addCriterion("svip_order_id =", value, "svipOrderId");
            return (Criteria) this;
        }

        public Criteria andSvipOrderIdNotEqualTo(Integer value) {
            addCriterion("svip_order_id <>", value, "svipOrderId");
            return (Criteria) this;
        }

        public Criteria andSvipOrderIdGreaterThan(Integer value) {
            addCriterion("svip_order_id >", value, "svipOrderId");
            return (Criteria) this;
        }

        public Criteria andSvipOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("svip_order_id >=", value, "svipOrderId");
            return (Criteria) this;
        }

        public Criteria andSvipOrderIdLessThan(Integer value) {
            addCriterion("svip_order_id <", value, "svipOrderId");
            return (Criteria) this;
        }

        public Criteria andSvipOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("svip_order_id <=", value, "svipOrderId");
            return (Criteria) this;
        }

        public Criteria andSvipOrderIdIn(List<Integer> values) {
            addCriterion("svip_order_id in", values, "svipOrderId");
            return (Criteria) this;
        }

        public Criteria andSvipOrderIdNotIn(List<Integer> values) {
            addCriterion("svip_order_id not in", values, "svipOrderId");
            return (Criteria) this;
        }

        public Criteria andSvipOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("svip_order_id between", value1, value2, "svipOrderId");
            return (Criteria) this;
        }

        public Criteria andSvipOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("svip_order_id not between", value1, value2, "svipOrderId");
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