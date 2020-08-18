package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusinessCirclesAppealOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public BusinessCirclesAppealOrderExample() {
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

        public Criteria andConsumerAppealDateIsNull() {
            addCriterion("consumer_appeal_date is null");
            return (Criteria) this;
        }

        public Criteria andConsumerAppealDateIsNotNull() {
            addCriterion("consumer_appeal_date is not null");
            return (Criteria) this;
        }

        public Criteria andConsumerAppealDateEqualTo(Date value) {
            addCriterion("consumer_appeal_date =", value, "consumerAppealDate");
            return (Criteria) this;
        }

        public Criteria andConsumerAppealDateNotEqualTo(Date value) {
            addCriterion("consumer_appeal_date <>", value, "consumerAppealDate");
            return (Criteria) this;
        }

        public Criteria andConsumerAppealDateGreaterThan(Date value) {
            addCriterion("consumer_appeal_date >", value, "consumerAppealDate");
            return (Criteria) this;
        }

        public Criteria andConsumerAppealDateGreaterThanOrEqualTo(Date value) {
            addCriterion("consumer_appeal_date >=", value, "consumerAppealDate");
            return (Criteria) this;
        }

        public Criteria andConsumerAppealDateLessThan(Date value) {
            addCriterion("consumer_appeal_date <", value, "consumerAppealDate");
            return (Criteria) this;
        }

        public Criteria andConsumerAppealDateLessThanOrEqualTo(Date value) {
            addCriterion("consumer_appeal_date <=", value, "consumerAppealDate");
            return (Criteria) this;
        }

        public Criteria andConsumerAppealDateIn(List<Date> values) {
            addCriterion("consumer_appeal_date in", values, "consumerAppealDate");
            return (Criteria) this;
        }

        public Criteria andConsumerAppealDateNotIn(List<Date> values) {
            addCriterion("consumer_appeal_date not in", values, "consumerAppealDate");
            return (Criteria) this;
        }

        public Criteria andConsumerAppealDateBetween(Date value1, Date value2) {
            addCriterion("consumer_appeal_date between", value1, value2, "consumerAppealDate");
            return (Criteria) this;
        }

        public Criteria andConsumerAppealDateNotBetween(Date value1, Date value2) {
            addCriterion("consumer_appeal_date not between", value1, value2, "consumerAppealDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationNumberIsNull() {
            addCriterion("registration_number is null");
            return (Criteria) this;
        }

        public Criteria andRegistrationNumberIsNotNull() {
            addCriterion("registration_number is not null");
            return (Criteria) this;
        }

        public Criteria andRegistrationNumberEqualTo(String value) {
            addCriterion("registration_number =", value, "registrationNumber");
            return (Criteria) this;
        }

        public Criteria andRegistrationNumberNotEqualTo(String value) {
            addCriterion("registration_number <>", value, "registrationNumber");
            return (Criteria) this;
        }

        public Criteria andRegistrationNumberGreaterThan(String value) {
            addCriterion("registration_number >", value, "registrationNumber");
            return (Criteria) this;
        }

        public Criteria andRegistrationNumberGreaterThanOrEqualTo(String value) {
            addCriterion("registration_number >=", value, "registrationNumber");
            return (Criteria) this;
        }

        public Criteria andRegistrationNumberLessThan(String value) {
            addCriterion("registration_number <", value, "registrationNumber");
            return (Criteria) this;
        }

        public Criteria andRegistrationNumberLessThanOrEqualTo(String value) {
            addCriterion("registration_number <=", value, "registrationNumber");
            return (Criteria) this;
        }

        public Criteria andRegistrationNumberLike(String value) {
            addCriterion("registration_number like", value, "registrationNumber");
            return (Criteria) this;
        }

        public Criteria andRegistrationNumberNotLike(String value) {
            addCriterion("registration_number not like", value, "registrationNumber");
            return (Criteria) this;
        }

        public Criteria andRegistrationNumberIn(List<String> values) {
            addCriterion("registration_number in", values, "registrationNumber");
            return (Criteria) this;
        }

        public Criteria andRegistrationNumberNotIn(List<String> values) {
            addCriterion("registration_number not in", values, "registrationNumber");
            return (Criteria) this;
        }

        public Criteria andRegistrationNumberBetween(String value1, String value2) {
            addCriterion("registration_number between", value1, value2, "registrationNumber");
            return (Criteria) this;
        }

        public Criteria andRegistrationNumberNotBetween(String value1, String value2) {
            addCriterion("registration_number not between", value1, value2, "registrationNumber");
            return (Criteria) this;
        }

        public Criteria andAppealNameIsNull() {
            addCriterion("appeal_name is null");
            return (Criteria) this;
        }

        public Criteria andAppealNameIsNotNull() {
            addCriterion("appeal_name is not null");
            return (Criteria) this;
        }

        public Criteria andAppealNameEqualTo(String value) {
            addCriterion("appeal_name =", value, "appealName");
            return (Criteria) this;
        }

        public Criteria andAppealNameNotEqualTo(String value) {
            addCriterion("appeal_name <>", value, "appealName");
            return (Criteria) this;
        }

        public Criteria andAppealNameGreaterThan(String value) {
            addCriterion("appeal_name >", value, "appealName");
            return (Criteria) this;
        }

        public Criteria andAppealNameGreaterThanOrEqualTo(String value) {
            addCriterion("appeal_name >=", value, "appealName");
            return (Criteria) this;
        }

        public Criteria andAppealNameLessThan(String value) {
            addCriterion("appeal_name <", value, "appealName");
            return (Criteria) this;
        }

        public Criteria andAppealNameLessThanOrEqualTo(String value) {
            addCriterion("appeal_name <=", value, "appealName");
            return (Criteria) this;
        }

        public Criteria andAppealNameLike(String value) {
            addCriterion("appeal_name like", value, "appealName");
            return (Criteria) this;
        }

        public Criteria andAppealNameNotLike(String value) {
            addCriterion("appeal_name not like", value, "appealName");
            return (Criteria) this;
        }

        public Criteria andAppealNameIn(List<String> values) {
            addCriterion("appeal_name in", values, "appealName");
            return (Criteria) this;
        }

        public Criteria andAppealNameNotIn(List<String> values) {
            addCriterion("appeal_name not in", values, "appealName");
            return (Criteria) this;
        }

        public Criteria andAppealNameBetween(String value1, String value2) {
            addCriterion("appeal_name between", value1, value2, "appealName");
            return (Criteria) this;
        }

        public Criteria andAppealNameNotBetween(String value1, String value2) {
            addCriterion("appeal_name not between", value1, value2, "appealName");
            return (Criteria) this;
        }

        public Criteria andAppealMobileIsNull() {
            addCriterion("appeal_mobile is null");
            return (Criteria) this;
        }

        public Criteria andAppealMobileIsNotNull() {
            addCriterion("appeal_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andAppealMobileEqualTo(String value) {
            addCriterion("appeal_mobile =", value, "appealMobile");
            return (Criteria) this;
        }

        public Criteria andAppealMobileNotEqualTo(String value) {
            addCriterion("appeal_mobile <>", value, "appealMobile");
            return (Criteria) this;
        }

        public Criteria andAppealMobileGreaterThan(String value) {
            addCriterion("appeal_mobile >", value, "appealMobile");
            return (Criteria) this;
        }

        public Criteria andAppealMobileGreaterThanOrEqualTo(String value) {
            addCriterion("appeal_mobile >=", value, "appealMobile");
            return (Criteria) this;
        }

        public Criteria andAppealMobileLessThan(String value) {
            addCriterion("appeal_mobile <", value, "appealMobile");
            return (Criteria) this;
        }

        public Criteria andAppealMobileLessThanOrEqualTo(String value) {
            addCriterion("appeal_mobile <=", value, "appealMobile");
            return (Criteria) this;
        }

        public Criteria andAppealMobileLike(String value) {
            addCriterion("appeal_mobile like", value, "appealMobile");
            return (Criteria) this;
        }

        public Criteria andAppealMobileNotLike(String value) {
            addCriterion("appeal_mobile not like", value, "appealMobile");
            return (Criteria) this;
        }

        public Criteria andAppealMobileIn(List<String> values) {
            addCriterion("appeal_mobile in", values, "appealMobile");
            return (Criteria) this;
        }

        public Criteria andAppealMobileNotIn(List<String> values) {
            addCriterion("appeal_mobile not in", values, "appealMobile");
            return (Criteria) this;
        }

        public Criteria andAppealMobileBetween(String value1, String value2) {
            addCriterion("appeal_mobile between", value1, value2, "appealMobile");
            return (Criteria) this;
        }

        public Criteria andAppealMobileNotBetween(String value1, String value2) {
            addCriterion("appeal_mobile not between", value1, value2, "appealMobile");
            return (Criteria) this;
        }

        public Criteria andAppealAddressIsNull() {
            addCriterion("appeal_address is null");
            return (Criteria) this;
        }

        public Criteria andAppealAddressIsNotNull() {
            addCriterion("appeal_address is not null");
            return (Criteria) this;
        }

        public Criteria andAppealAddressEqualTo(String value) {
            addCriterion("appeal_address =", value, "appealAddress");
            return (Criteria) this;
        }

        public Criteria andAppealAddressNotEqualTo(String value) {
            addCriterion("appeal_address <>", value, "appealAddress");
            return (Criteria) this;
        }

        public Criteria andAppealAddressGreaterThan(String value) {
            addCriterion("appeal_address >", value, "appealAddress");
            return (Criteria) this;
        }

        public Criteria andAppealAddressGreaterThanOrEqualTo(String value) {
            addCriterion("appeal_address >=", value, "appealAddress");
            return (Criteria) this;
        }

        public Criteria andAppealAddressLessThan(String value) {
            addCriterion("appeal_address <", value, "appealAddress");
            return (Criteria) this;
        }

        public Criteria andAppealAddressLessThanOrEqualTo(String value) {
            addCriterion("appeal_address <=", value, "appealAddress");
            return (Criteria) this;
        }

        public Criteria andAppealAddressLike(String value) {
            addCriterion("appeal_address like", value, "appealAddress");
            return (Criteria) this;
        }

        public Criteria andAppealAddressNotLike(String value) {
            addCriterion("appeal_address not like", value, "appealAddress");
            return (Criteria) this;
        }

        public Criteria andAppealAddressIn(List<String> values) {
            addCriterion("appeal_address in", values, "appealAddress");
            return (Criteria) this;
        }

        public Criteria andAppealAddressNotIn(List<String> values) {
            addCriterion("appeal_address not in", values, "appealAddress");
            return (Criteria) this;
        }

        public Criteria andAppealAddressBetween(String value1, String value2) {
            addCriterion("appeal_address between", value1, value2, "appealAddress");
            return (Criteria) this;
        }

        public Criteria andAppealAddressNotBetween(String value1, String value2) {
            addCriterion("appeal_address not between", value1, value2, "appealAddress");
            return (Criteria) this;
        }

        public Criteria andAppealOrderTypeIsNull() {
            addCriterion("appeal_order_type is null");
            return (Criteria) this;
        }

        public Criteria andAppealOrderTypeIsNotNull() {
            addCriterion("appeal_order_type is not null");
            return (Criteria) this;
        }

        public Criteria andAppealOrderTypeEqualTo(String value) {
            addCriterion("appeal_order_type =", value, "appealOrderType");
            return (Criteria) this;
        }

        public Criteria andAppealOrderTypeNotEqualTo(String value) {
            addCriterion("appeal_order_type <>", value, "appealOrderType");
            return (Criteria) this;
        }

        public Criteria andAppealOrderTypeGreaterThan(String value) {
            addCriterion("appeal_order_type >", value, "appealOrderType");
            return (Criteria) this;
        }

        public Criteria andAppealOrderTypeGreaterThanOrEqualTo(String value) {
            addCriterion("appeal_order_type >=", value, "appealOrderType");
            return (Criteria) this;
        }

        public Criteria andAppealOrderTypeLessThan(String value) {
            addCriterion("appeal_order_type <", value, "appealOrderType");
            return (Criteria) this;
        }

        public Criteria andAppealOrderTypeLessThanOrEqualTo(String value) {
            addCriterion("appeal_order_type <=", value, "appealOrderType");
            return (Criteria) this;
        }

        public Criteria andAppealOrderTypeLike(String value) {
            addCriterion("appeal_order_type like", value, "appealOrderType");
            return (Criteria) this;
        }

        public Criteria andAppealOrderTypeNotLike(String value) {
            addCriterion("appeal_order_type not like", value, "appealOrderType");
            return (Criteria) this;
        }

        public Criteria andAppealOrderTypeIn(List<String> values) {
            addCriterion("appeal_order_type in", values, "appealOrderType");
            return (Criteria) this;
        }

        public Criteria andAppealOrderTypeNotIn(List<String> values) {
            addCriterion("appeal_order_type not in", values, "appealOrderType");
            return (Criteria) this;
        }

        public Criteria andAppealOrderTypeBetween(String value1, String value2) {
            addCriterion("appeal_order_type between", value1, value2, "appealOrderType");
            return (Criteria) this;
        }

        public Criteria andAppealOrderTypeNotBetween(String value1, String value2) {
            addCriterion("appeal_order_type not between", value1, value2, "appealOrderType");
            return (Criteria) this;
        }

        public Criteria andTypesOfComplaintsIsNull() {
            addCriterion("types_of_complaints is null");
            return (Criteria) this;
        }

        public Criteria andTypesOfComplaintsIsNotNull() {
            addCriterion("types_of_complaints is not null");
            return (Criteria) this;
        }

        public Criteria andTypesOfComplaintsEqualTo(String value) {
            addCriterion("types_of_complaints =", value, "typesOfComplaints");
            return (Criteria) this;
        }

        public Criteria andTypesOfComplaintsNotEqualTo(String value) {
            addCriterion("types_of_complaints <>", value, "typesOfComplaints");
            return (Criteria) this;
        }

        public Criteria andTypesOfComplaintsGreaterThan(String value) {
            addCriterion("types_of_complaints >", value, "typesOfComplaints");
            return (Criteria) this;
        }

        public Criteria andTypesOfComplaintsGreaterThanOrEqualTo(String value) {
            addCriterion("types_of_complaints >=", value, "typesOfComplaints");
            return (Criteria) this;
        }

        public Criteria andTypesOfComplaintsLessThan(String value) {
            addCriterion("types_of_complaints <", value, "typesOfComplaints");
            return (Criteria) this;
        }

        public Criteria andTypesOfComplaintsLessThanOrEqualTo(String value) {
            addCriterion("types_of_complaints <=", value, "typesOfComplaints");
            return (Criteria) this;
        }

        public Criteria andTypesOfComplaintsLike(String value) {
            addCriterion("types_of_complaints like", value, "typesOfComplaints");
            return (Criteria) this;
        }

        public Criteria andTypesOfComplaintsNotLike(String value) {
            addCriterion("types_of_complaints not like", value, "typesOfComplaints");
            return (Criteria) this;
        }

        public Criteria andTypesOfComplaintsIn(List<String> values) {
            addCriterion("types_of_complaints in", values, "typesOfComplaints");
            return (Criteria) this;
        }

        public Criteria andTypesOfComplaintsNotIn(List<String> values) {
            addCriterion("types_of_complaints not in", values, "typesOfComplaints");
            return (Criteria) this;
        }

        public Criteria andTypesOfComplaintsBetween(String value1, String value2) {
            addCriterion("types_of_complaints between", value1, value2, "typesOfComplaints");
            return (Criteria) this;
        }

        public Criteria andTypesOfComplaintsNotBetween(String value1, String value2) {
            addCriterion("types_of_complaints not between", value1, value2, "typesOfComplaints");
            return (Criteria) this;
        }

        public Criteria andConsumerAppealContentIsNull() {
            addCriterion("consumer_appeal_content is null");
            return (Criteria) this;
        }

        public Criteria andConsumerAppealContentIsNotNull() {
            addCriterion("consumer_appeal_content is not null");
            return (Criteria) this;
        }

        public Criteria andConsumerAppealContentEqualTo(String value) {
            addCriterion("consumer_appeal_content =", value, "consumerAppealContent");
            return (Criteria) this;
        }

        public Criteria andConsumerAppealContentNotEqualTo(String value) {
            addCriterion("consumer_appeal_content <>", value, "consumerAppealContent");
            return (Criteria) this;
        }

        public Criteria andConsumerAppealContentGreaterThan(String value) {
            addCriterion("consumer_appeal_content >", value, "consumerAppealContent");
            return (Criteria) this;
        }

        public Criteria andConsumerAppealContentGreaterThanOrEqualTo(String value) {
            addCriterion("consumer_appeal_content >=", value, "consumerAppealContent");
            return (Criteria) this;
        }

        public Criteria andConsumerAppealContentLessThan(String value) {
            addCriterion("consumer_appeal_content <", value, "consumerAppealContent");
            return (Criteria) this;
        }

        public Criteria andConsumerAppealContentLessThanOrEqualTo(String value) {
            addCriterion("consumer_appeal_content <=", value, "consumerAppealContent");
            return (Criteria) this;
        }

        public Criteria andConsumerAppealContentLike(String value) {
            addCriterion("consumer_appeal_content like", value, "consumerAppealContent");
            return (Criteria) this;
        }

        public Criteria andConsumerAppealContentNotLike(String value) {
            addCriterion("consumer_appeal_content not like", value, "consumerAppealContent");
            return (Criteria) this;
        }

        public Criteria andConsumerAppealContentIn(List<String> values) {
            addCriterion("consumer_appeal_content in", values, "consumerAppealContent");
            return (Criteria) this;
        }

        public Criteria andConsumerAppealContentNotIn(List<String> values) {
            addCriterion("consumer_appeal_content not in", values, "consumerAppealContent");
            return (Criteria) this;
        }

        public Criteria andConsumerAppealContentBetween(String value1, String value2) {
            addCriterion("consumer_appeal_content between", value1, value2, "consumerAppealContent");
            return (Criteria) this;
        }

        public Criteria andConsumerAppealContentNotBetween(String value1, String value2) {
            addCriterion("consumer_appeal_content not between", value1, value2, "consumerAppealContent");
            return (Criteria) this;
        }

        public Criteria andAppealStatusIsNull() {
            addCriterion("appeal_status is null");
            return (Criteria) this;
        }

        public Criteria andAppealStatusIsNotNull() {
            addCriterion("appeal_status is not null");
            return (Criteria) this;
        }

        public Criteria andAppealStatusEqualTo(String value) {
            addCriterion("appeal_status =", value, "appealStatus");
            return (Criteria) this;
        }

        public Criteria andAppealStatusNotEqualTo(String value) {
            addCriterion("appeal_status <>", value, "appealStatus");
            return (Criteria) this;
        }

        public Criteria andAppealStatusGreaterThan(String value) {
            addCriterion("appeal_status >", value, "appealStatus");
            return (Criteria) this;
        }

        public Criteria andAppealStatusGreaterThanOrEqualTo(String value) {
            addCriterion("appeal_status >=", value, "appealStatus");
            return (Criteria) this;
        }

        public Criteria andAppealStatusLessThan(String value) {
            addCriterion("appeal_status <", value, "appealStatus");
            return (Criteria) this;
        }

        public Criteria andAppealStatusLessThanOrEqualTo(String value) {
            addCriterion("appeal_status <=", value, "appealStatus");
            return (Criteria) this;
        }

        public Criteria andAppealStatusLike(String value) {
            addCriterion("appeal_status like", value, "appealStatus");
            return (Criteria) this;
        }

        public Criteria andAppealStatusNotLike(String value) {
            addCriterion("appeal_status not like", value, "appealStatus");
            return (Criteria) this;
        }

        public Criteria andAppealStatusIn(List<String> values) {
            addCriterion("appeal_status in", values, "appealStatus");
            return (Criteria) this;
        }

        public Criteria andAppealStatusNotIn(List<String> values) {
            addCriterion("appeal_status not in", values, "appealStatus");
            return (Criteria) this;
        }

        public Criteria andAppealStatusBetween(String value1, String value2) {
            addCriterion("appeal_status between", value1, value2, "appealStatus");
            return (Criteria) this;
        }

        public Criteria andAppealStatusNotBetween(String value1, String value2) {
            addCriterion("appeal_status not between", value1, value2, "appealStatus");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceHandlerIsNull() {
            addCriterion("customer_service_handler is null");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceHandlerIsNotNull() {
            addCriterion("customer_service_handler is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceHandlerEqualTo(Integer value) {
            addCriterion("customer_service_handler =", value, "customerServiceHandler");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceHandlerNotEqualTo(Integer value) {
            addCriterion("customer_service_handler <>", value, "customerServiceHandler");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceHandlerGreaterThan(Integer value) {
            addCriterion("customer_service_handler >", value, "customerServiceHandler");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceHandlerGreaterThanOrEqualTo(Integer value) {
            addCriterion("customer_service_handler >=", value, "customerServiceHandler");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceHandlerLessThan(Integer value) {
            addCriterion("customer_service_handler <", value, "customerServiceHandler");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceHandlerLessThanOrEqualTo(Integer value) {
            addCriterion("customer_service_handler <=", value, "customerServiceHandler");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceHandlerIn(List<Integer> values) {
            addCriterion("customer_service_handler in", values, "customerServiceHandler");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceHandlerNotIn(List<Integer> values) {
            addCriterion("customer_service_handler not in", values, "customerServiceHandler");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceHandlerBetween(Integer value1, Integer value2) {
            addCriterion("customer_service_handler between", value1, value2, "customerServiceHandler");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceHandlerNotBetween(Integer value1, Integer value2) {
            addCriterion("customer_service_handler not between", value1, value2, "customerServiceHandler");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceHandleDateIsNull() {
            addCriterion("customer_service_handle_date is null");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceHandleDateIsNotNull() {
            addCriterion("customer_service_handle_date is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceHandleDateEqualTo(Date value) {
            addCriterion("customer_service_handle_date =", value, "customerServiceHandleDate");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceHandleDateNotEqualTo(Date value) {
            addCriterion("customer_service_handle_date <>", value, "customerServiceHandleDate");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceHandleDateGreaterThan(Date value) {
            addCriterion("customer_service_handle_date >", value, "customerServiceHandleDate");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceHandleDateGreaterThanOrEqualTo(Date value) {
            addCriterion("customer_service_handle_date >=", value, "customerServiceHandleDate");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceHandleDateLessThan(Date value) {
            addCriterion("customer_service_handle_date <", value, "customerServiceHandleDate");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceHandleDateLessThanOrEqualTo(Date value) {
            addCriterion("customer_service_handle_date <=", value, "customerServiceHandleDate");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceHandleDateIn(List<Date> values) {
            addCriterion("customer_service_handle_date in", values, "customerServiceHandleDate");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceHandleDateNotIn(List<Date> values) {
            addCriterion("customer_service_handle_date not in", values, "customerServiceHandleDate");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceHandleDateBetween(Date value1, Date value2) {
            addCriterion("customer_service_handle_date between", value1, value2, "customerServiceHandleDate");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceHandleDateNotBetween(Date value1, Date value2) {
            addCriterion("customer_service_handle_date not between", value1, value2, "customerServiceHandleDate");
            return (Criteria) this;
        }

        public Criteria andMemberIdsIsNull() {
            addCriterion("member_ids is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdsIsNotNull() {
            addCriterion("member_ids is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdsEqualTo(String value) {
            addCriterion("member_ids =", value, "memberIds");
            return (Criteria) this;
        }

        public Criteria andMemberIdsNotEqualTo(String value) {
            addCriterion("member_ids <>", value, "memberIds");
            return (Criteria) this;
        }

        public Criteria andMemberIdsGreaterThan(String value) {
            addCriterion("member_ids >", value, "memberIds");
            return (Criteria) this;
        }

        public Criteria andMemberIdsGreaterThanOrEqualTo(String value) {
            addCriterion("member_ids >=", value, "memberIds");
            return (Criteria) this;
        }

        public Criteria andMemberIdsLessThan(String value) {
            addCriterion("member_ids <", value, "memberIds");
            return (Criteria) this;
        }

        public Criteria andMemberIdsLessThanOrEqualTo(String value) {
            addCriterion("member_ids <=", value, "memberIds");
            return (Criteria) this;
        }

        public Criteria andMemberIdsLike(String value) {
            addCriterion("member_ids like", value, "memberIds");
            return (Criteria) this;
        }

        public Criteria andMemberIdsNotLike(String value) {
            addCriterion("member_ids not like", value, "memberIds");
            return (Criteria) this;
        }

        public Criteria andMemberIdsIn(List<String> values) {
            addCriterion("member_ids in", values, "memberIds");
            return (Criteria) this;
        }

        public Criteria andMemberIdsNotIn(List<String> values) {
            addCriterion("member_ids not in", values, "memberIds");
            return (Criteria) this;
        }

        public Criteria andMemberIdsBetween(String value1, String value2) {
            addCriterion("member_ids between", value1, value2, "memberIds");
            return (Criteria) this;
        }

        public Criteria andMemberIdsNotBetween(String value1, String value2) {
            addCriterion("member_ids not between", value1, value2, "memberIds");
            return (Criteria) this;
        }

        public Criteria andMchtIdsIsNull() {
            addCriterion("mcht_ids is null");
            return (Criteria) this;
        }

        public Criteria andMchtIdsIsNotNull() {
            addCriterion("mcht_ids is not null");
            return (Criteria) this;
        }

        public Criteria andMchtIdsEqualTo(String value) {
            addCriterion("mcht_ids =", value, "mchtIds");
            return (Criteria) this;
        }

        public Criteria andMchtIdsNotEqualTo(String value) {
            addCriterion("mcht_ids <>", value, "mchtIds");
            return (Criteria) this;
        }

        public Criteria andMchtIdsGreaterThan(String value) {
            addCriterion("mcht_ids >", value, "mchtIds");
            return (Criteria) this;
        }

        public Criteria andMchtIdsGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_ids >=", value, "mchtIds");
            return (Criteria) this;
        }

        public Criteria andMchtIdsLessThan(String value) {
            addCriterion("mcht_ids <", value, "mchtIds");
            return (Criteria) this;
        }

        public Criteria andMchtIdsLessThanOrEqualTo(String value) {
            addCriterion("mcht_ids <=", value, "mchtIds");
            return (Criteria) this;
        }

        public Criteria andMchtIdsLike(String value) {
            addCriterion("mcht_ids like", value, "mchtIds");
            return (Criteria) this;
        }

        public Criteria andMchtIdsNotLike(String value) {
            addCriterion("mcht_ids not like", value, "mchtIds");
            return (Criteria) this;
        }

        public Criteria andMchtIdsIn(List<String> values) {
            addCriterion("mcht_ids in", values, "mchtIds");
            return (Criteria) this;
        }

        public Criteria andMchtIdsNotIn(List<String> values) {
            addCriterion("mcht_ids not in", values, "mchtIds");
            return (Criteria) this;
        }

        public Criteria andMchtIdsBetween(String value1, String value2) {
            addCriterion("mcht_ids between", value1, value2, "mchtIds");
            return (Criteria) this;
        }

        public Criteria andMchtIdsNotBetween(String value1, String value2) {
            addCriterion("mcht_ids not between", value1, value2, "mchtIds");
            return (Criteria) this;
        }

        public Criteria andMchtProductBrandIdsIsNull() {
            addCriterion("mcht_product_brand_ids is null");
            return (Criteria) this;
        }

        public Criteria andMchtProductBrandIdsIsNotNull() {
            addCriterion("mcht_product_brand_ids is not null");
            return (Criteria) this;
        }

        public Criteria andMchtProductBrandIdsEqualTo(String value) {
            addCriterion("mcht_product_brand_ids =", value, "mchtProductBrandIds");
            return (Criteria) this;
        }

        public Criteria andMchtProductBrandIdsNotEqualTo(String value) {
            addCriterion("mcht_product_brand_ids <>", value, "mchtProductBrandIds");
            return (Criteria) this;
        }

        public Criteria andMchtProductBrandIdsGreaterThan(String value) {
            addCriterion("mcht_product_brand_ids >", value, "mchtProductBrandIds");
            return (Criteria) this;
        }

        public Criteria andMchtProductBrandIdsGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_product_brand_ids >=", value, "mchtProductBrandIds");
            return (Criteria) this;
        }

        public Criteria andMchtProductBrandIdsLessThan(String value) {
            addCriterion("mcht_product_brand_ids <", value, "mchtProductBrandIds");
            return (Criteria) this;
        }

        public Criteria andMchtProductBrandIdsLessThanOrEqualTo(String value) {
            addCriterion("mcht_product_brand_ids <=", value, "mchtProductBrandIds");
            return (Criteria) this;
        }

        public Criteria andMchtProductBrandIdsLike(String value) {
            addCriterion("mcht_product_brand_ids like", value, "mchtProductBrandIds");
            return (Criteria) this;
        }

        public Criteria andMchtProductBrandIdsNotLike(String value) {
            addCriterion("mcht_product_brand_ids not like", value, "mchtProductBrandIds");
            return (Criteria) this;
        }

        public Criteria andMchtProductBrandIdsIn(List<String> values) {
            addCriterion("mcht_product_brand_ids in", values, "mchtProductBrandIds");
            return (Criteria) this;
        }

        public Criteria andMchtProductBrandIdsNotIn(List<String> values) {
            addCriterion("mcht_product_brand_ids not in", values, "mchtProductBrandIds");
            return (Criteria) this;
        }

        public Criteria andMchtProductBrandIdsBetween(String value1, String value2) {
            addCriterion("mcht_product_brand_ids between", value1, value2, "mchtProductBrandIds");
            return (Criteria) this;
        }

        public Criteria andMchtProductBrandIdsNotBetween(String value1, String value2) {
            addCriterion("mcht_product_brand_ids not between", value1, value2, "mchtProductBrandIds");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeIsNull() {
            addCriterion("sub_order_code is null");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeIsNotNull() {
            addCriterion("sub_order_code is not null");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeEqualTo(String value) {
            addCriterion("sub_order_code =", value, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeNotEqualTo(String value) {
            addCriterion("sub_order_code <>", value, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeGreaterThan(String value) {
            addCriterion("sub_order_code >", value, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeGreaterThanOrEqualTo(String value) {
            addCriterion("sub_order_code >=", value, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeLessThan(String value) {
            addCriterion("sub_order_code <", value, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeLessThanOrEqualTo(String value) {
            addCriterion("sub_order_code <=", value, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeLike(String value) {
            addCriterion("sub_order_code like", value, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeNotLike(String value) {
            addCriterion("sub_order_code not like", value, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeIn(List<String> values) {
            addCriterion("sub_order_code in", values, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeNotIn(List<String> values) {
            addCriterion("sub_order_code not in", values, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeBetween(String value1, String value2) {
            addCriterion("sub_order_code between", value1, value2, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeNotBetween(String value1, String value2) {
            addCriterion("sub_order_code not between", value1, value2, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSignForDateIsNull() {
            addCriterion("sign_for_date is null");
            return (Criteria) this;
        }

        public Criteria andSignForDateIsNotNull() {
            addCriterion("sign_for_date is not null");
            return (Criteria) this;
        }

        public Criteria andSignForDateEqualTo(Date value) {
            addCriterion("sign_for_date =", value, "signForDate");
            return (Criteria) this;
        }

        public Criteria andSignForDateNotEqualTo(Date value) {
            addCriterion("sign_for_date <>", value, "signForDate");
            return (Criteria) this;
        }

        public Criteria andSignForDateGreaterThan(Date value) {
            addCriterion("sign_for_date >", value, "signForDate");
            return (Criteria) this;
        }

        public Criteria andSignForDateGreaterThanOrEqualTo(Date value) {
            addCriterion("sign_for_date >=", value, "signForDate");
            return (Criteria) this;
        }

        public Criteria andSignForDateLessThan(Date value) {
            addCriterion("sign_for_date <", value, "signForDate");
            return (Criteria) this;
        }

        public Criteria andSignForDateLessThanOrEqualTo(Date value) {
            addCriterion("sign_for_date <=", value, "signForDate");
            return (Criteria) this;
        }

        public Criteria andSignForDateIn(List<Date> values) {
            addCriterion("sign_for_date in", values, "signForDate");
            return (Criteria) this;
        }

        public Criteria andSignForDateNotIn(List<Date> values) {
            addCriterion("sign_for_date not in", values, "signForDate");
            return (Criteria) this;
        }

        public Criteria andSignForDateBetween(Date value1, Date value2) {
            addCriterion("sign_for_date between", value1, value2, "signForDate");
            return (Criteria) this;
        }

        public Criteria andSignForDateNotBetween(Date value1, Date value2) {
            addCriterion("sign_for_date not between", value1, value2, "signForDate");
            return (Criteria) this;
        }

        public Criteria andMemberSituationIsNull() {
            addCriterion("member_situation is null");
            return (Criteria) this;
        }

        public Criteria andMemberSituationIsNotNull() {
            addCriterion("member_situation is not null");
            return (Criteria) this;
        }

        public Criteria andMemberSituationEqualTo(String value) {
            addCriterion("member_situation =", value, "memberSituation");
            return (Criteria) this;
        }

        public Criteria andMemberSituationNotEqualTo(String value) {
            addCriterion("member_situation <>", value, "memberSituation");
            return (Criteria) this;
        }

        public Criteria andMemberSituationGreaterThan(String value) {
            addCriterion("member_situation >", value, "memberSituation");
            return (Criteria) this;
        }

        public Criteria andMemberSituationGreaterThanOrEqualTo(String value) {
            addCriterion("member_situation >=", value, "memberSituation");
            return (Criteria) this;
        }

        public Criteria andMemberSituationLessThan(String value) {
            addCriterion("member_situation <", value, "memberSituation");
            return (Criteria) this;
        }

        public Criteria andMemberSituationLessThanOrEqualTo(String value) {
            addCriterion("member_situation <=", value, "memberSituation");
            return (Criteria) this;
        }

        public Criteria andMemberSituationLike(String value) {
            addCriterion("member_situation like", value, "memberSituation");
            return (Criteria) this;
        }

        public Criteria andMemberSituationNotLike(String value) {
            addCriterion("member_situation not like", value, "memberSituation");
            return (Criteria) this;
        }

        public Criteria andMemberSituationIn(List<String> values) {
            addCriterion("member_situation in", values, "memberSituation");
            return (Criteria) this;
        }

        public Criteria andMemberSituationNotIn(List<String> values) {
            addCriterion("member_situation not in", values, "memberSituation");
            return (Criteria) this;
        }

        public Criteria andMemberSituationBetween(String value1, String value2) {
            addCriterion("member_situation between", value1, value2, "memberSituation");
            return (Criteria) this;
        }

        public Criteria andMemberSituationNotBetween(String value1, String value2) {
            addCriterion("member_situation not between", value1, value2, "memberSituation");
            return (Criteria) this;
        }

        public Criteria andMchtAppealedCountIsNull() {
            addCriterion("mcht_appealed_count is null");
            return (Criteria) this;
        }

        public Criteria andMchtAppealedCountIsNotNull() {
            addCriterion("mcht_appealed_count is not null");
            return (Criteria) this;
        }

        public Criteria andMchtAppealedCountEqualTo(Integer value) {
            addCriterion("mcht_appealed_count =", value, "mchtAppealedCount");
            return (Criteria) this;
        }

        public Criteria andMchtAppealedCountNotEqualTo(Integer value) {
            addCriterion("mcht_appealed_count <>", value, "mchtAppealedCount");
            return (Criteria) this;
        }

        public Criteria andMchtAppealedCountGreaterThan(Integer value) {
            addCriterion("mcht_appealed_count >", value, "mchtAppealedCount");
            return (Criteria) this;
        }

        public Criteria andMchtAppealedCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("mcht_appealed_count >=", value, "mchtAppealedCount");
            return (Criteria) this;
        }

        public Criteria andMchtAppealedCountLessThan(Integer value) {
            addCriterion("mcht_appealed_count <", value, "mchtAppealedCount");
            return (Criteria) this;
        }

        public Criteria andMchtAppealedCountLessThanOrEqualTo(Integer value) {
            addCriterion("mcht_appealed_count <=", value, "mchtAppealedCount");
            return (Criteria) this;
        }

        public Criteria andMchtAppealedCountIn(List<Integer> values) {
            addCriterion("mcht_appealed_count in", values, "mchtAppealedCount");
            return (Criteria) this;
        }

        public Criteria andMchtAppealedCountNotIn(List<Integer> values) {
            addCriterion("mcht_appealed_count not in", values, "mchtAppealedCount");
            return (Criteria) this;
        }

        public Criteria andMchtAppealedCountBetween(Integer value1, Integer value2) {
            addCriterion("mcht_appealed_count between", value1, value2, "mchtAppealedCount");
            return (Criteria) this;
        }

        public Criteria andMchtAppealedCountNotBetween(Integer value1, Integer value2) {
            addCriterion("mcht_appealed_count not between", value1, value2, "mchtAppealedCount");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceResultIsNull() {
            addCriterion("customer_service_result is null");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceResultIsNotNull() {
            addCriterion("customer_service_result is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceResultEqualTo(String value) {
            addCriterion("customer_service_result =", value, "customerServiceResult");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceResultNotEqualTo(String value) {
            addCriterion("customer_service_result <>", value, "customerServiceResult");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceResultGreaterThan(String value) {
            addCriterion("customer_service_result >", value, "customerServiceResult");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceResultGreaterThanOrEqualTo(String value) {
            addCriterion("customer_service_result >=", value, "customerServiceResult");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceResultLessThan(String value) {
            addCriterion("customer_service_result <", value, "customerServiceResult");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceResultLessThanOrEqualTo(String value) {
            addCriterion("customer_service_result <=", value, "customerServiceResult");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceResultLike(String value) {
            addCriterion("customer_service_result like", value, "customerServiceResult");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceResultNotLike(String value) {
            addCriterion("customer_service_result not like", value, "customerServiceResult");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceResultIn(List<String> values) {
            addCriterion("customer_service_result in", values, "customerServiceResult");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceResultNotIn(List<String> values) {
            addCriterion("customer_service_result not in", values, "customerServiceResult");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceResultBetween(String value1, String value2) {
            addCriterion("customer_service_result between", value1, value2, "customerServiceResult");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceResultNotBetween(String value1, String value2) {
            addCriterion("customer_service_result not between", value1, value2, "customerServiceResult");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceResultDateIsNull() {
            addCriterion("customer_service_result_date is null");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceResultDateIsNotNull() {
            addCriterion("customer_service_result_date is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceResultDateEqualTo(Date value) {
            addCriterion("customer_service_result_date =", value, "customerServiceResultDate");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceResultDateNotEqualTo(Date value) {
            addCriterion("customer_service_result_date <>", value, "customerServiceResultDate");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceResultDateGreaterThan(Date value) {
            addCriterion("customer_service_result_date >", value, "customerServiceResultDate");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceResultDateGreaterThanOrEqualTo(Date value) {
            addCriterion("customer_service_result_date >=", value, "customerServiceResultDate");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceResultDateLessThan(Date value) {
            addCriterion("customer_service_result_date <", value, "customerServiceResultDate");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceResultDateLessThanOrEqualTo(Date value) {
            addCriterion("customer_service_result_date <=", value, "customerServiceResultDate");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceResultDateIn(List<Date> values) {
            addCriterion("customer_service_result_date in", values, "customerServiceResultDate");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceResultDateNotIn(List<Date> values) {
            addCriterion("customer_service_result_date not in", values, "customerServiceResultDate");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceResultDateBetween(Date value1, Date value2) {
            addCriterion("customer_service_result_date between", value1, value2, "customerServiceResultDate");
            return (Criteria) this;
        }

        public Criteria andCustomerServiceResultDateNotBetween(Date value1, Date value2) {
            addCriterion("customer_service_result_date not between", value1, value2, "customerServiceResultDate");
            return (Criteria) this;
        }

        public Criteria andFwResultIsNull() {
            addCriterion("fw_result is null");
            return (Criteria) this;
        }

        public Criteria andFwResultIsNotNull() {
            addCriterion("fw_result is not null");
            return (Criteria) this;
        }

        public Criteria andFwResultEqualTo(String value) {
            addCriterion("fw_result =", value, "fwResult");
            return (Criteria) this;
        }

        public Criteria andFwResultNotEqualTo(String value) {
            addCriterion("fw_result <>", value, "fwResult");
            return (Criteria) this;
        }

        public Criteria andFwResultGreaterThan(String value) {
            addCriterion("fw_result >", value, "fwResult");
            return (Criteria) this;
        }

        public Criteria andFwResultGreaterThanOrEqualTo(String value) {
            addCriterion("fw_result >=", value, "fwResult");
            return (Criteria) this;
        }

        public Criteria andFwResultLessThan(String value) {
            addCriterion("fw_result <", value, "fwResult");
            return (Criteria) this;
        }

        public Criteria andFwResultLessThanOrEqualTo(String value) {
            addCriterion("fw_result <=", value, "fwResult");
            return (Criteria) this;
        }

        public Criteria andFwResultLike(String value) {
            addCriterion("fw_result like", value, "fwResult");
            return (Criteria) this;
        }

        public Criteria andFwResultNotLike(String value) {
            addCriterion("fw_result not like", value, "fwResult");
            return (Criteria) this;
        }

        public Criteria andFwResultIn(List<String> values) {
            addCriterion("fw_result in", values, "fwResult");
            return (Criteria) this;
        }

        public Criteria andFwResultNotIn(List<String> values) {
            addCriterion("fw_result not in", values, "fwResult");
            return (Criteria) this;
        }

        public Criteria andFwResultBetween(String value1, String value2) {
            addCriterion("fw_result between", value1, value2, "fwResult");
            return (Criteria) this;
        }

        public Criteria andFwResultNotBetween(String value1, String value2) {
            addCriterion("fw_result not between", value1, value2, "fwResult");
            return (Criteria) this;
        }

        public Criteria andFwResultDateIsNull() {
            addCriterion("fw_result_date is null");
            return (Criteria) this;
        }

        public Criteria andFwResultDateIsNotNull() {
            addCriterion("fw_result_date is not null");
            return (Criteria) this;
        }

        public Criteria andFwResultDateEqualTo(Date value) {
            addCriterion("fw_result_date =", value, "fwResultDate");
            return (Criteria) this;
        }

        public Criteria andFwResultDateNotEqualTo(Date value) {
            addCriterion("fw_result_date <>", value, "fwResultDate");
            return (Criteria) this;
        }

        public Criteria andFwResultDateGreaterThan(Date value) {
            addCriterion("fw_result_date >", value, "fwResultDate");
            return (Criteria) this;
        }

        public Criteria andFwResultDateGreaterThanOrEqualTo(Date value) {
            addCriterion("fw_result_date >=", value, "fwResultDate");
            return (Criteria) this;
        }

        public Criteria andFwResultDateLessThan(Date value) {
            addCriterion("fw_result_date <", value, "fwResultDate");
            return (Criteria) this;
        }

        public Criteria andFwResultDateLessThanOrEqualTo(Date value) {
            addCriterion("fw_result_date <=", value, "fwResultDate");
            return (Criteria) this;
        }

        public Criteria andFwResultDateIn(List<Date> values) {
            addCriterion("fw_result_date in", values, "fwResultDate");
            return (Criteria) this;
        }

        public Criteria andFwResultDateNotIn(List<Date> values) {
            addCriterion("fw_result_date not in", values, "fwResultDate");
            return (Criteria) this;
        }

        public Criteria andFwResultDateBetween(Date value1, Date value2) {
            addCriterion("fw_result_date between", value1, value2, "fwResultDate");
            return (Criteria) this;
        }

        public Criteria andFwResultDateNotBetween(Date value1, Date value2) {
            addCriterion("fw_result_date not between", value1, value2, "fwResultDate");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesResultIsNull() {
            addCriterion("business_circles_result is null");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesResultIsNotNull() {
            addCriterion("business_circles_result is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesResultEqualTo(String value) {
            addCriterion("business_circles_result =", value, "businessCirclesResult");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesResultNotEqualTo(String value) {
            addCriterion("business_circles_result <>", value, "businessCirclesResult");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesResultGreaterThan(String value) {
            addCriterion("business_circles_result >", value, "businessCirclesResult");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesResultGreaterThanOrEqualTo(String value) {
            addCriterion("business_circles_result >=", value, "businessCirclesResult");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesResultLessThan(String value) {
            addCriterion("business_circles_result <", value, "businessCirclesResult");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesResultLessThanOrEqualTo(String value) {
            addCriterion("business_circles_result <=", value, "businessCirclesResult");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesResultLike(String value) {
            addCriterion("business_circles_result like", value, "businessCirclesResult");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesResultNotLike(String value) {
            addCriterion("business_circles_result not like", value, "businessCirclesResult");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesResultIn(List<String> values) {
            addCriterion("business_circles_result in", values, "businessCirclesResult");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesResultNotIn(List<String> values) {
            addCriterion("business_circles_result not in", values, "businessCirclesResult");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesResultBetween(String value1, String value2) {
            addCriterion("business_circles_result between", value1, value2, "businessCirclesResult");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesResultNotBetween(String value1, String value2) {
            addCriterion("business_circles_result not between", value1, value2, "businessCirclesResult");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesDateIsNull() {
            addCriterion("business_circles_date is null");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesDateIsNotNull() {
            addCriterion("business_circles_date is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesDateEqualTo(Date value) {
            addCriterion("business_circles_date =", value, "businessCirclesDate");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesDateNotEqualTo(Date value) {
            addCriterion("business_circles_date <>", value, "businessCirclesDate");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesDateGreaterThan(Date value) {
            addCriterion("business_circles_date >", value, "businessCirclesDate");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesDateGreaterThanOrEqualTo(Date value) {
            addCriterion("business_circles_date >=", value, "businessCirclesDate");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesDateLessThan(Date value) {
            addCriterion("business_circles_date <", value, "businessCirclesDate");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesDateLessThanOrEqualTo(Date value) {
            addCriterion("business_circles_date <=", value, "businessCirclesDate");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesDateIn(List<Date> values) {
            addCriterion("business_circles_date in", values, "businessCirclesDate");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesDateNotIn(List<Date> values) {
            addCriterion("business_circles_date not in", values, "businessCirclesDate");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesDateBetween(Date value1, Date value2) {
            addCriterion("business_circles_date between", value1, value2, "businessCirclesDate");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesDateNotBetween(Date value1, Date value2) {
            addCriterion("business_circles_date not between", value1, value2, "businessCirclesDate");
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