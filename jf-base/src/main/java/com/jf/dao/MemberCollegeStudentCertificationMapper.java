package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberCollegeStudentCertification;
import com.jf.entity.MemberCollegeStudentCertificationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberCollegeStudentCertificationMapper extends BaseDao<MemberCollegeStudentCertification, MemberCollegeStudentCertificationExample> {
    int countByExample(MemberCollegeStudentCertificationExample example);

    int deleteByExample(MemberCollegeStudentCertificationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberCollegeStudentCertification record);

    int insertSelective(MemberCollegeStudentCertification record);

    List<MemberCollegeStudentCertification> selectByExample(MemberCollegeStudentCertificationExample example);

    MemberCollegeStudentCertification selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberCollegeStudentCertification record, @Param("example") MemberCollegeStudentCertificationExample example);

    int updateByExample(@Param("record") MemberCollegeStudentCertification record, @Param("example") MemberCollegeStudentCertificationExample example);

    int updateByPrimaryKeySelective(MemberCollegeStudentCertification record);

    int updateByPrimaryKey(MemberCollegeStudentCertification record);
}