package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SingleProductActivityCnf;
import com.jf.entity.SingleProductActivityCnfExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingleProductActivityCnfMapper extends BaseDao<SingleProductActivityCnf, SingleProductActivityCnfExample> {
    int countByExample(SingleProductActivityCnfExample example);

    int deleteByExample(SingleProductActivityCnfExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SingleProductActivityCnf record);

    int insertSelective(SingleProductActivityCnf record);

    List<SingleProductActivityCnf> selectByExample(SingleProductActivityCnfExample example);

    SingleProductActivityCnf selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SingleProductActivityCnf record, @Param("example") SingleProductActivityCnfExample example);

    int updateByExample(@Param("record") SingleProductActivityCnf record, @Param("example") SingleProductActivityCnfExample example);

    int updateByPrimaryKeySelective(SingleProductActivityCnf record);

    int updateByPrimaryKey(SingleProductActivityCnf record);
}
