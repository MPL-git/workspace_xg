package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ComplainOrderPic;
import com.jf.entity.ComplainOrderPicExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplainOrderPicMapper extends BaseDao<ComplainOrderPic, ComplainOrderPicExample> {
    int countByExample(ComplainOrderPicExample example);

    int deleteByExample(ComplainOrderPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ComplainOrderPic record);

    int insertSelective(ComplainOrderPic record);

    List<ComplainOrderPic> selectByExample(ComplainOrderPicExample example);

    ComplainOrderPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ComplainOrderPic record, @Param("example") ComplainOrderPicExample example);

    int updateByExample(@Param("record") ComplainOrderPic record, @Param("example") ComplainOrderPicExample example);

    int updateByPrimaryKeySelective(ComplainOrderPic record);

    int updateByPrimaryKey(ComplainOrderPic record);
}
