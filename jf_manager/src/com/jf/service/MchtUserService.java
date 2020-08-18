package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtUserCustomMapper;
import com.jf.dao.MchtUserMapper;
import com.jf.entity.MchtUser;
import com.jf.entity.MchtUserCustom;
import com.jf.entity.MchtUserExample;

@Service
@Transactional
public class MchtUserService extends BaseService<MchtUser,MchtUserExample> {
	@Autowired
	private MchtUserMapper mchtUserMapper;
	
	@Autowired
	private MchtUserCustomMapper mchtUserCustomMapper;
	
	@Autowired
	public void setMchtUserMapper(MchtUserMapper mchtUserMapper) {
		super.setDao(mchtUserMapper);
		this.mchtUserMapper = mchtUserMapper;
	}
	
	public List<MchtUserCustom> selectMchtUserCustomByExample(MchtUserExample example){
    	return mchtUserCustomMapper.selectByExample(example);
    }
	
	public int countByExample(MchtUserExample example){
		return mchtUserCustomMapper.countByExample(example);
	}
	
	/**
     * 根据商家id查询商家主账号手机号码
     * 
     * @param mchtId
     * @return
     */
	public String selectMobileByMchtId(Integer mchtId) {
		return mchtUserCustomMapper.selectMobileByMchtId(mchtId);
	}

	/**
	 * 根据商家id查询商家对接人的手机号码
	 * 
	 * @param mchtId
	 * @return
	 */
	public String selectMchtContactMobileByMchtId(Integer mchtId,Integer type) {
		return mchtUserCustomMapper.selectMchtContactMobileByMchtId(mchtId,type);
	}
}
