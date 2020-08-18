package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.SignSendMsgCnf;
import com.jf.entity.SignSendMsgCnfCustom;
import com.jf.entity.SignSendMsgCnfCustomExample;

@Repository
public interface SignSendMsgCnfCustomMapper {
    
	public int countByCustomExample(SignSendMsgCnfCustomExample example);

	public List<SignSendMsgCnfCustom> selectByCustomExample(SignSendMsgCnfCustomExample example);

	public SignSendMsgCnfCustom selectByCustomPrimaryKey(Integer id);

	public int updateByCustomExampleSelective(@Param("record") SignSendMsgCnf record, @Param("example") SignSendMsgCnfCustomExample example);

}