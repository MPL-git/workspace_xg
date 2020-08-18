package com.jf.dao;

import com.jf.entity.AllowanceInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllowanceInfoCustomMapper {

	AllowanceInfo getMaxPreAllowanceInfo();

    List<AllowanceInfo> findActiveAllowance();

}