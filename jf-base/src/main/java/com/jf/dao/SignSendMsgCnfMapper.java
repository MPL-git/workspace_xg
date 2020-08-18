package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SignSendMsgCnf;
import com.jf.entity.SignSendMsgCnfExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignSendMsgCnfMapper extends BaseDao<SignSendMsgCnf, SignSendMsgCnfExample> {
    int countByExample(SignSendMsgCnfExample example);

    int deleteByExample(SignSendMsgCnfExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SignSendMsgCnf record);

    int insertSelective(SignSendMsgCnf record);

    List<SignSendMsgCnf> selectByExample(SignSendMsgCnfExample example);

    SignSendMsgCnf selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SignSendMsgCnf record, @Param("example") SignSendMsgCnfExample example);

    int updateByExample(@Param("record") SignSendMsgCnf record, @Param("example") SignSendMsgCnfExample example);

    int updateByPrimaryKeySelective(SignSendMsgCnf record);

    int updateByPrimaryKey(SignSendMsgCnf record);
}
