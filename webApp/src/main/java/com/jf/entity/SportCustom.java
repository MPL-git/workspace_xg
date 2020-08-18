package com.jf.entity;

import java.math.BigDecimal;

public class SportCustom extends Sport{
	private Integer homeTeamId;
	
    private String homeTeamName;
    
    private String homeTeamPic;
    
    private Integer awayTeamId;
    
    private String awayTeamName;
    
    private String awayTeamPic;	
    
    private Integer guessId;
    
    private String guessResult;	
    
    private String guessCode;
    
    private String guessType;
    
    private Integer guessWinTeam;
    
    private Integer integral;

    private BigDecimal winRate;
    
    private String teamResult;
    /**
     * 竞猜队伍比例
     */
    private BigDecimal rate;

	/**  
	 * homeTeamId  
	 * @return homeTeamId homeTeamId  
	 */
	public Integer getHomeTeamId() {
		return homeTeamId;
	}
	

	/**  
	 * homeTeamId  
	 * @param homeTeamId homeTeamId  
	 */
	public void setHomeTeamId(Integer homeTeamId) {
		this.homeTeamId = homeTeamId;
	}
	

	/**  
	 * homeTeamName  
	 * @return homeTeamName homeTeamName  
	 */
	public String getHomeTeamName() {
		return homeTeamName;
	}
	

	/**  
	 * homeTeamName  
	 * @param homeTeamName homeTeamName  
	 */
	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}
	

	/**  
	 * homeTeamPic  
	 * @return homeTeamPic homeTeamPic  
	 */
	public String getHomeTeamPic() {
		return homeTeamPic;
	}
	

	/**  
	 * homeTeamPic  
	 * @param homeTeamPic homeTeamPic  
	 */
	public void setHomeTeamPic(String homeTeamPic) {
		this.homeTeamPic = homeTeamPic;
	}
	

	/**  
	 * awayTeamId  
	 * @return awayTeamId awayTeamId  
	 */
	public Integer getAwayTeamId() {
		return awayTeamId;
	}
	

	/**  
	 * awayTeamId  
	 * @param awayTeamId awayTeamId  
	 */
	public void setAwayTeamId(Integer awayTeamId) {
		this.awayTeamId = awayTeamId;
	}
	

	/**  
	 * awayTeamName  
	 * @return awayTeamName awayTeamName  
	 */
	public String getAwayTeamName() {
		return awayTeamName;
	}
	

	/**  
	 * awayTeamName  
	 * @param awayTeamName awayTeamName  
	 */
	public void setAwayTeamName(String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}
	

	/**  
	 * awayTeamPic  
	 * @return awayTeamPic awayTeamPic  
	 */
	public String getAwayTeamPic() {
		return awayTeamPic;
	}
	

	/**  
	 * awayTeamPic  
	 * @param awayTeamPic awayTeamPic  
	 */
	public void setAwayTeamPic(String awayTeamPic) {
		this.awayTeamPic = awayTeamPic;
	}
	

	/**  
	 * guessId  
	 * @return guessId guessId  
	 */
	public Integer getGuessId() {
		return guessId;
	}
	

	/**  
	 * guessId  
	 * @param guessId guessId  
	 */
	public void setGuessId(Integer guessId) {
		this.guessId = guessId;
	}
	

	/**  
	 * guessResult  
	 * @return guessResult guessResult  
	 */
	public String getGuessResult() {
		return guessResult;
	}
	

	/**  
	 * guessResult  
	 * @param guessResult guessResult  
	 */
	public void setGuessResult(String guessResult) {
		this.guessResult = guessResult;
	}
	

	/**  
	 * guessCode  
	 * @return guessCode guessCode  
	 */
	public String getGuessCode() {
		return guessCode;
	}
	

	/**  
	 * guessCode  
	 * @param guessCode guessCode  
	 */
	public void setGuessCode(String guessCode) {
		this.guessCode = guessCode;
	}
	

	/**  
	 * guessWinTeam  
	 * @return guessWinTeam guessWinTeam  
	 */
	public Integer getGuessWinTeam() {
		return guessWinTeam;
	}
	

	/**  
	 * guessWinTeam  
	 * @param guessWinTeam guessWinTeam  
	 */
	public void setGuessWinTeam(Integer guessWinTeam) {
		this.guessWinTeam = guessWinTeam;
	}
	

	/**  
	 * integral  
	 * @return integral integral  
	 */
	public Integer getIntegral() {
		return integral;
	}
	

	/**  
	 * integral  
	 * @param integral integral  
	 */
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	

	/**  
	 * winRate  
	 * @return winRate winRate  
	 */
	public BigDecimal getWinRate() {
		return winRate;
	}
	

	/**  
	 * winRate  
	 * @param winRate winRate  
	 */
	public void setWinRate(BigDecimal winRate) {
		this.winRate = winRate;
	}


	/**  
	 * guessType  
	 * @return guessType guessType  
	 */
	public String getGuessType() {
		return guessType;
	}
	


	/**  
	 * guessType  
	 * @param guessType guessType  
	 */
	public void setGuessType(String guessType) {
		this.guessType = guessType;
	}


	/**  
	 * teamResult  
	 * @return teamResult teamResult  
	 */
	public String getTeamResult() {
		return teamResult;
	}
	


	/**  
	 * teamResult  
	 * @param teamResult teamResult  
	 */
	public void setTeamResult(String teamResult) {
		this.teamResult = teamResult;
	}


	public BigDecimal getRate() {
		return rate;
	}
	

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
}