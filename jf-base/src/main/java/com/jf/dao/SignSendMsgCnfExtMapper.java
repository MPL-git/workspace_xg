package com.jf.dao;

import com.jf.entity.SignSendMsgCnfExt;
import com.jf.entity.SignSendMsgCnfExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignSendMsgCnfExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SignSendMsgCnfExt findById(int id);

    SignSendMsgCnfExt find(SignSendMsgCnfExtExample example);

    List<SignSendMsgCnfExt> list(SignSendMsgCnfExtExample example);

    List<Integer> listId(SignSendMsgCnfExtExample example);

    int count(SignSendMsgCnfExtExample example);

    long sum(@Param("field") String field, @Param("example") SignSendMsgCnfExtExample example);

    int max(@Param("field") String field, @Param("example") SignSendMsgCnfExtExample example);

    int min(@Param("field") String field, @Param("example") SignSendMsgCnfExtExample example);

}

