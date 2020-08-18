package com.jf.dao;

import com.jf.entity.MchtContractPic;
import com.jf.entity.MchtContractPicExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtContractPicMapper extends BaseDao<MchtContractPic, MchtContractPicExample> {
    int countByExample(MchtContractPicExample example);

    int deleteByExample(MchtContractPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtContractPic record);

    int insertSelective(MchtContractPic record);

    List<MchtContractPic> selectByExample(MchtContractPicExample example);

    MchtContractPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtContractPic record, @Param("example") MchtContractPicExample example);

    int updateByExample(@Param("record") MchtContractPic record, @Param("example") MchtContractPicExample example);

    int updateByPrimaryKeySelective(MchtContractPic record);

    int updateByPrimaryKey(MchtContractPic record);
}
