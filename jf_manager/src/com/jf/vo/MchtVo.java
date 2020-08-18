package com.jf.vo;

import com.jf.entity.MchtContact;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtPlatformContact;
import com.jf.entity.MchtUser;

public class MchtVo {
 private MchtInfo mchtInfo;
 private MchtUser mchtUser;
 private MchtContact mchtContact;
 private MchtPlatformContact mchtPlatformContact;
public MchtInfo getMchtInfo() {
	return mchtInfo;
}
public void setMchtInfo(MchtInfo mchtInfo) {
	this.mchtInfo = mchtInfo;
}
public MchtUser getMchtUser() {
	return mchtUser;
}
public void setMchtUser(MchtUser mchtUser) {
	this.mchtUser = mchtUser;
}
public MchtContact getMchtContact() {
	return mchtContact;
}
public void setMchtContact(MchtContact mchtContact) {
	this.mchtContact = mchtContact;
}
public MchtPlatformContact getMchtPlatformContact() {
	return mchtPlatformContact;
}
public void setMchtPlatformContact(MchtPlatformContact mchtPlatformContact) {
	this.mchtPlatformContact = mchtPlatformContact;
} 
}
