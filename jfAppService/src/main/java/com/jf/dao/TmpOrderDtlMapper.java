package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.TmpOrderDtl;
import com.jf.entity.TmpOrderDtlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface TmpOrderDtlMapper extends BaseDao<TmpOrderDtl, TmpOrderDtlExample>{
    int countByExample(TmpOrderDtlExample example);

    int deleteByExample(TmpOrderDtlExample example);

    int insert(TmpOrderDtl record);

    int insertSelective(TmpOrderDtl record);

    List<TmpOrderDtl> selectByExample(TmpOrderDtlExample example);

    int updateByExampleSelective(@Param("record") TmpOrderDtl record, @Param("example") TmpOrderDtlExample example);

    int updateByExample(@Param("record") TmpOrderDtl record, @Param("example") TmpOrderDtlExample example);
}