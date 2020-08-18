package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.CommentDraw;
import com.jf.entity.CommentDrawExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDrawMapper extends BaseDao<CommentDraw, CommentDrawExample> {
    int countByExample(CommentDrawExample example);

    int deleteByExample(CommentDrawExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CommentDraw record);

    int insertSelective(CommentDraw record);

    List<CommentDraw> selectByExample(CommentDrawExample example);

    CommentDraw selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CommentDraw record, @Param("example") CommentDrawExample example);

    int updateByExample(@Param("record") CommentDraw record, @Param("example") CommentDrawExample example);

    int updateByPrimaryKeySelective(CommentDraw record);

    int updateByPrimaryKey(CommentDraw record);
}
