package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SportExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public SportExample() {
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

        public Criteria andSportTypeIsNull() {
            addCriterion("sport_type is null");
            return (Criteria) this;
        }

        public Criteria andSportTypeIsNotNull() {
            addCriterion("sport_type is not null");
            return (Criteria) this;
        }

        public Criteria andSportTypeEqualTo(Integer value) {
            addCriterion("sport_type =", value, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeNotEqualTo(Integer value) {
            addCriterion("sport_type <>", value, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeGreaterThan(Integer value) {
            addCriterion("sport_type >", value, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("sport_type >=", value, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeLessThan(Integer value) {
            addCriterion("sport_type <", value, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeLessThanOrEqualTo(Integer value) {
            addCriterion("sport_type <=", value, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeIn(List<Integer> values) {
            addCriterion("sport_type in", values, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeNotIn(List<Integer> values) {
            addCriterion("sport_type not in", values, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeBetween(Integer value1, Integer value2) {
            addCriterion("sport_type between", value1, value2, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("sport_type not between", value1, value2, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportNameIsNull() {
            addCriterion("sport_name is null");
            return (Criteria) this;
        }

        public Criteria andSportNameIsNotNull() {
            addCriterion("sport_name is not null");
            return (Criteria) this;
        }

        public Criteria andSportNameEqualTo(String value) {
            addCriterion("sport_name =", value, "sportName");
            return (Criteria) this;
        }

        public Criteria andSportNameNotEqualTo(String value) {
            addCriterion("sport_name <>", value, "sportName");
            return (Criteria) this;
        }

        public Criteria andSportNameGreaterThan(String value) {
            addCriterion("sport_name >", value, "sportName");
            return (Criteria) this;
        }

        public Criteria andSportNameGreaterThanOrEqualTo(String value) {
            addCriterion("sport_name >=", value, "sportName");
            return (Criteria) this;
        }

        public Criteria andSportNameLessThan(String value) {
            addCriterion("sport_name <", value, "sportName");
            return (Criteria) this;
        }

        public Criteria andSportNameLessThanOrEqualTo(String value) {
            addCriterion("sport_name <=", value, "sportName");
            return (Criteria) this;
        }

        public Criteria andSportNameLike(String value) {
            addCriterion("sport_name like", value, "sportName");
            return (Criteria) this;
        }

        public Criteria andSportNameNotLike(String value) {
            addCriterion("sport_name not like", value, "sportName");
            return (Criteria) this;
        }

        public Criteria andSportNameIn(List<String> values) {
            addCriterion("sport_name in", values, "sportName");
            return (Criteria) this;
        }

        public Criteria andSportNameNotIn(List<String> values) {
            addCriterion("sport_name not in", values, "sportName");
            return (Criteria) this;
        }

        public Criteria andSportNameBetween(String value1, String value2) {
            addCriterion("sport_name between", value1, value2, "sportName");
            return (Criteria) this;
        }

        public Criteria andSportNameNotBetween(String value1, String value2) {
            addCriterion("sport_name not between", value1, value2, "sportName");
            return (Criteria) this;
        }

        public Criteria andHomeTeamIsNull() {
            addCriterion("home_team is null");
            return (Criteria) this;
        }

        public Criteria andHomeTeamIsNotNull() {
            addCriterion("home_team is not null");
            return (Criteria) this;
        }

        public Criteria andHomeTeamEqualTo(Integer value) {
            addCriterion("home_team =", value, "homeTeam");
            return (Criteria) this;
        }

        public Criteria andHomeTeamNotEqualTo(Integer value) {
            addCriterion("home_team <>", value, "homeTeam");
            return (Criteria) this;
        }

        public Criteria andHomeTeamGreaterThan(Integer value) {
            addCriterion("home_team >", value, "homeTeam");
            return (Criteria) this;
        }

        public Criteria andHomeTeamGreaterThanOrEqualTo(Integer value) {
            addCriterion("home_team >=", value, "homeTeam");
            return (Criteria) this;
        }

        public Criteria andHomeTeamLessThan(Integer value) {
            addCriterion("home_team <", value, "homeTeam");
            return (Criteria) this;
        }

        public Criteria andHomeTeamLessThanOrEqualTo(Integer value) {
            addCriterion("home_team <=", value, "homeTeam");
            return (Criteria) this;
        }

        public Criteria andHomeTeamIn(List<Integer> values) {
            addCriterion("home_team in", values, "homeTeam");
            return (Criteria) this;
        }

        public Criteria andHomeTeamNotIn(List<Integer> values) {
            addCriterion("home_team not in", values, "homeTeam");
            return (Criteria) this;
        }

        public Criteria andHomeTeamBetween(Integer value1, Integer value2) {
            addCriterion("home_team between", value1, value2, "homeTeam");
            return (Criteria) this;
        }

        public Criteria andHomeTeamNotBetween(Integer value1, Integer value2) {
            addCriterion("home_team not between", value1, value2, "homeTeam");
            return (Criteria) this;
        }

        public Criteria andAwayTeamIsNull() {
            addCriterion("away_team is null");
            return (Criteria) this;
        }

        public Criteria andAwayTeamIsNotNull() {
            addCriterion("away_team is not null");
            return (Criteria) this;
        }

        public Criteria andAwayTeamEqualTo(Integer value) {
            addCriterion("away_team =", value, "awayTeam");
            return (Criteria) this;
        }

        public Criteria andAwayTeamNotEqualTo(Integer value) {
            addCriterion("away_team <>", value, "awayTeam");
            return (Criteria) this;
        }

        public Criteria andAwayTeamGreaterThan(Integer value) {
            addCriterion("away_team >", value, "awayTeam");
            return (Criteria) this;
        }

        public Criteria andAwayTeamGreaterThanOrEqualTo(Integer value) {
            addCriterion("away_team >=", value, "awayTeam");
            return (Criteria) this;
        }

        public Criteria andAwayTeamLessThan(Integer value) {
            addCriterion("away_team <", value, "awayTeam");
            return (Criteria) this;
        }

        public Criteria andAwayTeamLessThanOrEqualTo(Integer value) {
            addCriterion("away_team <=", value, "awayTeam");
            return (Criteria) this;
        }

        public Criteria andAwayTeamIn(List<Integer> values) {
            addCriterion("away_team in", values, "awayTeam");
            return (Criteria) this;
        }

        public Criteria andAwayTeamNotIn(List<Integer> values) {
            addCriterion("away_team not in", values, "awayTeam");
            return (Criteria) this;
        }

        public Criteria andAwayTeamBetween(Integer value1, Integer value2) {
            addCriterion("away_team between", value1, value2, "awayTeam");
            return (Criteria) this;
        }

        public Criteria andAwayTeamNotBetween(Integer value1, Integer value2) {
            addCriterion("away_team not between", value1, value2, "awayTeam");
            return (Criteria) this;
        }

        public Criteria andHomeRateIsNull() {
            addCriterion("home_rate is null");
            return (Criteria) this;
        }

        public Criteria andHomeRateIsNotNull() {
            addCriterion("home_rate is not null");
            return (Criteria) this;
        }

        public Criteria andHomeRateEqualTo(BigDecimal value) {
            addCriterion("home_rate =", value, "homeRate");
            return (Criteria) this;
        }

        public Criteria andHomeRateNotEqualTo(BigDecimal value) {
            addCriterion("home_rate <>", value, "homeRate");
            return (Criteria) this;
        }

        public Criteria andHomeRateGreaterThan(BigDecimal value) {
            addCriterion("home_rate >", value, "homeRate");
            return (Criteria) this;
        }

        public Criteria andHomeRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("home_rate >=", value, "homeRate");
            return (Criteria) this;
        }

        public Criteria andHomeRateLessThan(BigDecimal value) {
            addCriterion("home_rate <", value, "homeRate");
            return (Criteria) this;
        }

        public Criteria andHomeRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("home_rate <=", value, "homeRate");
            return (Criteria) this;
        }

        public Criteria andHomeRateIn(List<BigDecimal> values) {
            addCriterion("home_rate in", values, "homeRate");
            return (Criteria) this;
        }

        public Criteria andHomeRateNotIn(List<BigDecimal> values) {
            addCriterion("home_rate not in", values, "homeRate");
            return (Criteria) this;
        }

        public Criteria andHomeRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("home_rate between", value1, value2, "homeRate");
            return (Criteria) this;
        }

        public Criteria andHomeRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("home_rate not between", value1, value2, "homeRate");
            return (Criteria) this;
        }

        public Criteria andAwaysRateIsNull() {
            addCriterion("aways_rate is null");
            return (Criteria) this;
        }

        public Criteria andAwaysRateIsNotNull() {
            addCriterion("aways_rate is not null");
            return (Criteria) this;
        }

        public Criteria andAwaysRateEqualTo(BigDecimal value) {
            addCriterion("aways_rate =", value, "awaysRate");
            return (Criteria) this;
        }

        public Criteria andAwaysRateNotEqualTo(BigDecimal value) {
            addCriterion("aways_rate <>", value, "awaysRate");
            return (Criteria) this;
        }

        public Criteria andAwaysRateGreaterThan(BigDecimal value) {
            addCriterion("aways_rate >", value, "awaysRate");
            return (Criteria) this;
        }

        public Criteria andAwaysRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("aways_rate >=", value, "awaysRate");
            return (Criteria) this;
        }

        public Criteria andAwaysRateLessThan(BigDecimal value) {
            addCriterion("aways_rate <", value, "awaysRate");
            return (Criteria) this;
        }

        public Criteria andAwaysRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("aways_rate <=", value, "awaysRate");
            return (Criteria) this;
        }

        public Criteria andAwaysRateIn(List<BigDecimal> values) {
            addCriterion("aways_rate in", values, "awaysRate");
            return (Criteria) this;
        }

        public Criteria andAwaysRateNotIn(List<BigDecimal> values) {
            addCriterion("aways_rate not in", values, "awaysRate");
            return (Criteria) this;
        }

        public Criteria andAwaysRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("aways_rate between", value1, value2, "awaysRate");
            return (Criteria) this;
        }

        public Criteria andAwaysRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("aways_rate not between", value1, value2, "awaysRate");
            return (Criteria) this;
        }

        public Criteria andDrawRateIsNull() {
            addCriterion("draw_rate is null");
            return (Criteria) this;
        }

        public Criteria andDrawRateIsNotNull() {
            addCriterion("draw_rate is not null");
            return (Criteria) this;
        }

        public Criteria andDrawRateEqualTo(BigDecimal value) {
            addCriterion("draw_rate =", value, "drawRate");
            return (Criteria) this;
        }

        public Criteria andDrawRateNotEqualTo(BigDecimal value) {
            addCriterion("draw_rate <>", value, "drawRate");
            return (Criteria) this;
        }

        public Criteria andDrawRateGreaterThan(BigDecimal value) {
            addCriterion("draw_rate >", value, "drawRate");
            return (Criteria) this;
        }

        public Criteria andDrawRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("draw_rate >=", value, "drawRate");
            return (Criteria) this;
        }

        public Criteria andDrawRateLessThan(BigDecimal value) {
            addCriterion("draw_rate <", value, "drawRate");
            return (Criteria) this;
        }

        public Criteria andDrawRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("draw_rate <=", value, "drawRate");
            return (Criteria) this;
        }

        public Criteria andDrawRateIn(List<BigDecimal> values) {
            addCriterion("draw_rate in", values, "drawRate");
            return (Criteria) this;
        }

        public Criteria andDrawRateNotIn(List<BigDecimal> values) {
            addCriterion("draw_rate not in", values, "drawRate");
            return (Criteria) this;
        }

        public Criteria andDrawRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("draw_rate between", value1, value2, "drawRate");
            return (Criteria) this;
        }

        public Criteria andDrawRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("draw_rate not between", value1, value2, "drawRate");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNull() {
            addCriterion("begin_time is null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNotNull() {
            addCriterion("begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeEqualTo(Date value) {
            addCriterion("begin_time =", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotEqualTo(Date value) {
            addCriterion("begin_time <>", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThan(Date value) {
            addCriterion("begin_time >", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("begin_time >=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThan(Date value) {
            addCriterion("begin_time <", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("begin_time <=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIn(List<Date> values) {
            addCriterion("begin_time in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotIn(List<Date> values) {
            addCriterion("begin_time not in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeBetween(Date value1, Date value2) {
            addCriterion("begin_time between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("begin_time not between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andHomeScoreIsNull() {
            addCriterion("home_score is null");
            return (Criteria) this;
        }

        public Criteria andHomeScoreIsNotNull() {
            addCriterion("home_score is not null");
            return (Criteria) this;
        }

        public Criteria andHomeScoreEqualTo(Integer value) {
            addCriterion("home_score =", value, "homeScore");
            return (Criteria) this;
        }

        public Criteria andHomeScoreNotEqualTo(Integer value) {
            addCriterion("home_score <>", value, "homeScore");
            return (Criteria) this;
        }

        public Criteria andHomeScoreGreaterThan(Integer value) {
            addCriterion("home_score >", value, "homeScore");
            return (Criteria) this;
        }

        public Criteria andHomeScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("home_score >=", value, "homeScore");
            return (Criteria) this;
        }

        public Criteria andHomeScoreLessThan(Integer value) {
            addCriterion("home_score <", value, "homeScore");
            return (Criteria) this;
        }

        public Criteria andHomeScoreLessThanOrEqualTo(Integer value) {
            addCriterion("home_score <=", value, "homeScore");
            return (Criteria) this;
        }

        public Criteria andHomeScoreIn(List<Integer> values) {
            addCriterion("home_score in", values, "homeScore");
            return (Criteria) this;
        }

        public Criteria andHomeScoreNotIn(List<Integer> values) {
            addCriterion("home_score not in", values, "homeScore");
            return (Criteria) this;
        }

        public Criteria andHomeScoreBetween(Integer value1, Integer value2) {
            addCriterion("home_score between", value1, value2, "homeScore");
            return (Criteria) this;
        }

        public Criteria andHomeScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("home_score not between", value1, value2, "homeScore");
            return (Criteria) this;
        }

        public Criteria andAwaysScoreIsNull() {
            addCriterion("aways_score is null");
            return (Criteria) this;
        }

        public Criteria andAwaysScoreIsNotNull() {
            addCriterion("aways_score is not null");
            return (Criteria) this;
        }

        public Criteria andAwaysScoreEqualTo(Integer value) {
            addCriterion("aways_score =", value, "awaysScore");
            return (Criteria) this;
        }

        public Criteria andAwaysScoreNotEqualTo(Integer value) {
            addCriterion("aways_score <>", value, "awaysScore");
            return (Criteria) this;
        }

        public Criteria andAwaysScoreGreaterThan(Integer value) {
            addCriterion("aways_score >", value, "awaysScore");
            return (Criteria) this;
        }

        public Criteria andAwaysScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("aways_score >=", value, "awaysScore");
            return (Criteria) this;
        }

        public Criteria andAwaysScoreLessThan(Integer value) {
            addCriterion("aways_score <", value, "awaysScore");
            return (Criteria) this;
        }

        public Criteria andAwaysScoreLessThanOrEqualTo(Integer value) {
            addCriterion("aways_score <=", value, "awaysScore");
            return (Criteria) this;
        }

        public Criteria andAwaysScoreIn(List<Integer> values) {
            addCriterion("aways_score in", values, "awaysScore");
            return (Criteria) this;
        }

        public Criteria andAwaysScoreNotIn(List<Integer> values) {
            addCriterion("aways_score not in", values, "awaysScore");
            return (Criteria) this;
        }

        public Criteria andAwaysScoreBetween(Integer value1, Integer value2) {
            addCriterion("aways_score between", value1, value2, "awaysScore");
            return (Criteria) this;
        }

        public Criteria andAwaysScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("aways_score not between", value1, value2, "awaysScore");
            return (Criteria) this;
        }

        public Criteria andResultIsNull() {
            addCriterion("result is null");
            return (Criteria) this;
        }

        public Criteria andResultIsNotNull() {
            addCriterion("result is not null");
            return (Criteria) this;
        }

        public Criteria andResultEqualTo(String value) {
            addCriterion("result =", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotEqualTo(String value) {
            addCriterion("result <>", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThan(String value) {
            addCriterion("result >", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThanOrEqualTo(String value) {
            addCriterion("result >=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThan(String value) {
            addCriterion("result <", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThanOrEqualTo(String value) {
            addCriterion("result <=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLike(String value) {
            addCriterion("result like", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotLike(String value) {
            addCriterion("result not like", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultIn(List<String> values) {
            addCriterion("result in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotIn(List<String> values) {
            addCriterion("result not in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultBetween(String value1, String value2) {
            addCriterion("result between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotBetween(String value1, String value2) {
            addCriterion("result not between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNull() {
            addCriterion("audit_status is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNotNull() {
            addCriterion("audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusEqualTo(String value) {
            addCriterion("audit_status =", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotEqualTo(String value) {
            addCriterion("audit_status <>", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThan(String value) {
            addCriterion("audit_status >", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("audit_status >=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThan(String value) {
            addCriterion("audit_status <", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThanOrEqualTo(String value) {
            addCriterion("audit_status <=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLike(String value) {
            addCriterion("audit_status like", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotLike(String value) {
            addCriterion("audit_status not like", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIn(List<String> values) {
            addCriterion("audit_status in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotIn(List<String> values) {
            addCriterion("audit_status not in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusBetween(String value1, String value2) {
            addCriterion("audit_status between", value1, value2, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotBetween(String value1, String value2) {
            addCriterion("audit_status not between", value1, value2, "auditStatus");
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