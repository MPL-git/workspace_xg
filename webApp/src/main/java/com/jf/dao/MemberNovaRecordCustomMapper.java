package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberNovaRecordCustom;
@Repository
public interface MemberNovaRecordCustomMapper {

	List<MemberNovaRecordCustom> getMemberRenewalProgressLog(Map<String, Object> paramsMap);

}
