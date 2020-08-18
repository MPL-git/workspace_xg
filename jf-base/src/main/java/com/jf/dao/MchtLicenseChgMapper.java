package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtLicenseChg;
import com.jf.entity.MchtLicenseChgExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtLicenseChgMapper extends BaseDao<MchtLicenseChg, MchtLicenseChgExample> {
    int countByExample(MchtLicenseChgExample example);

    int deleteByExample(MchtLicenseChgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtLicenseChg record);

    int insertSelective(MchtLicenseChg record);

    List<MchtLicenseChg> selectByExample(MchtLicenseChgExample example);

    MchtLicenseChg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtLicenseChg record, @Param("example") MchtLicenseChgExample example);

    int updateByExample(@Param("record") MchtLicenseChg record, @Param("example") MchtLicenseChgExample example);

    int updateByPrimaryKeySelective(MchtLicenseChg record);

    int updateByPrimaryKey(MchtLicenseChg record);
}
