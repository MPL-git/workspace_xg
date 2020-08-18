package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MemberFootprintCustom;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月20日 下午5:00:06 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Repository
public interface MemberFootprintCustomMapper {

	List<MemberFootprintCustom> getMemberFootprintList(Map<String, Object> params);

	Integer getMemberFootprintListCount(@Param(value = "memberId") Integer memberId);

	Integer getMemberFootprintCount(Map<String, Object> params);

	Integer countMemberFootprint30Days(Map<String, Object> paramMap);

	List<MemberFootprintCustom> findMemberFootprint(Map<String, Object> params);

    List<Integer> findMemberRecentlyScanProductType3(int memberId);

}
