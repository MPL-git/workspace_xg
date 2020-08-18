package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.Comment;
import com.jf.entity.CommentCustom;
import com.jf.entity.CommentCustomExample;

@Repository
public interface CommentCustomMapper {
	
	public int countByCustomExample(CommentCustomExample example);

	public List<CommentCustom> selectByCustomExample(CommentCustomExample example);

	public CommentCustom selectByCustomPrimaryKey(Integer id);

	public int updateByCustomExampleSelective(@Param("record") Comment record, @Param("example") CommentCustomExample example);

    List<Comment> selectByPropDescCustomExample(Integer id);

	Integer selectSaleQuantity(Integer id);

	Integer selectPayers(Integer id);
}