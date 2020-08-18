package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.CommentPic;
import com.jf.entity.CommentPicExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentPicMapper extends BaseDao<CommentPic, CommentPicExample> {
    int countByExample(CommentPicExample example);

    int deleteByExample(CommentPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CommentPic record);

    int insertSelective(CommentPic record);

    List<CommentPic> selectByExample(CommentPicExample example);

    CommentPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CommentPic record, @Param("example") CommentPicExample example);

    int updateByExample(@Param("record") CommentPic record, @Param("example") CommentPicExample example);

    int updateByPrimaryKeySelective(CommentPic record);

    int updateByPrimaryKey(CommentPic record);
}
