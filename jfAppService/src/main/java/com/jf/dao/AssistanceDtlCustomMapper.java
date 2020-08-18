package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.AssistanceDtlCustom;

@Repository
public interface AssistanceDtlCustomMapper {

	List<AssistanceDtlCustom> getMemberAssistanceInfo(Integer memberId);

}
