package com.jf.entity;


public class IntegralDtlCustom extends IntegralDtl{
	private Integer memberId;
	private String nick;
	private String tallyModeName;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getTallyModeName() {
		return tallyModeName;
	}
	public void setTallyModeName(String tallyModeName) {
		this.tallyModeName = tallyModeName;
	}
}
