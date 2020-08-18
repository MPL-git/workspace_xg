package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.SvipOrderCustom;
@Repository
public interface SvipOrderCustomMapper{

	List<SvipOrderCustom> getSvipOrderInfo(Integer memberId);

}