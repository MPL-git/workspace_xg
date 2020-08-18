package com.jf.dao;

import com.jf.entity.ImpeachMemberProof;
import com.jf.entity.ImpeachMemberProofExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ImpeachMemberProofMapper extends BaseDao<ImpeachMemberProof, ImpeachMemberProofExample>{
    int countByExample(ImpeachMemberProofExample example);

    int deleteByExample(ImpeachMemberProofExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ImpeachMemberProof record);

    int insertSelective(ImpeachMemberProof record);

    List<ImpeachMemberProof> selectByExample(ImpeachMemberProofExample example);

    ImpeachMemberProof selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ImpeachMemberProof record, @Param("example") ImpeachMemberProofExample example);

    int updateByExample(@Param("record") ImpeachMemberProof record, @Param("example") ImpeachMemberProofExample example);

    int updateByPrimaryKeySelective(ImpeachMemberProof record);

    int updateByPrimaryKey(ImpeachMemberProof record);
}