package com.jf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SportGuessCustom extends SportGuess {

	private String guessTypeDesc;
	private String resultDesc;
	private String createStaffName;
	private String nickName; // 会员昵称
	private Integer memberAccountId; // 会员账户ID
	private Integer memberIntegral; // 积分
	private String sportSportName; // 比赛名称
	private Integer sportHomeTeam; // 主场队伍
	private Integer sportAwayTeam; // 客场队伍
	private BigDecimal sportHomeRate; // 主场比例
	private BigDecimal sportAwaysRate; // 客场比例
	private BigDecimal sportDrawRate; // 平局比例
	private Date sportBeginTime; // 比赛开始时间
	private Integer sportHomeScore; // 主场得分
	private Integer sportAwaysScore; // 客场得分
	private String sportResult; // 比赛结果
	private String sportHomeTeamName; // 主场队伍名称
	private String sportAwayTeamName; // 客场队伍名称
	private String sportTeamName; // 竞猜获胜队伍名称
	private String sportWinRate; //夺冠比例 

	public String getGuessTypeDesc() {
		return guessTypeDesc;
	}

	public void setGuessTypeDesc(String guessTypeDesc) {
		this.guessTypeDesc = guessTypeDesc;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public String getCreateStaffName() {
		return createStaffName;
	}

	public void setCreateStaffName(String createStaffName) {
		this.createStaffName = createStaffName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSportSportName() {
		return sportSportName;
	}

	public void setSportSportName(String sportSportName) {
		this.sportSportName = sportSportName;
	}

	public Integer getSportHomeTeam() {
		return sportHomeTeam;
	}

	public void setSportHomeTeam(Integer sportHomeTeam) {
		this.sportHomeTeam = sportHomeTeam;
	}

	public Integer getSportAwayTeam() {
		return sportAwayTeam;
	}

	public void setSportAwayTeam(Integer sportAwayTeam) {
		this.sportAwayTeam = sportAwayTeam;
	}

	public BigDecimal getSportHomeRate() {
		return sportHomeRate;
	}

	public void setSportHomeRate(BigDecimal sportHomeRate) {
		this.sportHomeRate = sportHomeRate;
	}

	public BigDecimal getSportAwaysRate() {
		return sportAwaysRate;
	}

	public void setSportAwaysRate(BigDecimal sportAwaysRate) {
		this.sportAwaysRate = sportAwaysRate;
	}

	public BigDecimal getSportDrawRate() {
		return sportDrawRate;
	}

	public void setSportDrawRate(BigDecimal sportDrawRate) {
		this.sportDrawRate = sportDrawRate;
	}

	public Date getSportBeginTime() {
		return sportBeginTime;
	}

	public void setSportBeginTime(Date sportBeginTime) {
		this.sportBeginTime = sportBeginTime;
	}

	public Integer getSportHomeScore() {
		return sportHomeScore;
	}

	public void setSportHomeScore(Integer sportHomeScore) {
		this.sportHomeScore = sportHomeScore;
	}

	public Integer getSportAwaysScore() {
		return sportAwaysScore;
	}

	public void setSportAwaysScore(Integer sportAwaysScore) {
		this.sportAwaysScore = sportAwaysScore;
	}

	public String getSportResult() {
		return sportResult;
	}

	public void setSportResult(String sportResult) {
		this.sportResult = sportResult;
	}

	public String getSportHomeTeamName() {
		return sportHomeTeamName;
	}

	public void setSportHomeTeamName(String sportHomeTeamName) {
		this.sportHomeTeamName = sportHomeTeamName;
	}

	public String getSportAwayTeamName() {
		return sportAwayTeamName;
	}

	public void setSportAwayTeamName(String sportAwayTeamName) {
		this.sportAwayTeamName = sportAwayTeamName;
	}

	public String getSportTeamName() {
		return sportTeamName;
	}

	public void setSportTeamName(String sportTeamName) {
		this.sportTeamName = sportTeamName;
	}

	public Integer getMemberIntegral() {
		return memberIntegral;
	}

	public void setMemberIntegral(Integer memberIntegral) {
		this.memberIntegral = memberIntegral;
	}

	public Integer getMemberAccountId() {
		return memberAccountId;
	}

	public void setMemberAccountId(Integer memberAccountId) {
		this.memberAccountId = memberAccountId;
	}

	public String getSportWinRate() {
		return sportWinRate;
	}

	public void setSportWinRate(String sportWinRate) {
		this.sportWinRate = sportWinRate;
	}
	

}
