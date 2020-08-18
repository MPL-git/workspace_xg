package com.jf.dao;

import com.jf.entity.ClientServiceComment;
import com.jf.entity.ClientServiceCommentExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientServiceCommentMapper extends BaseDao<ClientServiceComment, ClientServiceCommentExample> {
    int countByExample(ClientServiceCommentExample example);

    int deleteByExample(ClientServiceCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ClientServiceComment record);

    int insertSelective(ClientServiceComment record);

    List<ClientServiceComment> selectByExample(ClientServiceCommentExample example);

    ClientServiceComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ClientServiceComment record, @Param("example") ClientServiceCommentExample example);

    int updateByExample(@Param("record") ClientServiceComment record, @Param("example") ClientServiceCommentExample example);

    int updateByPrimaryKeySelective(ClientServiceComment record);

    int updateByPrimaryKey(ClientServiceComment record);
}