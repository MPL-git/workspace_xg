package com.jf.dao;

import com.jf.entity.IndexPopupAd;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndexPopupAdCustomMapper {

	List<IndexPopupAd> findMemberIndexPopupAd(@Param("memberId") int memberId);
}