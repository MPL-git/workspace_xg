package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.SysAppMessageCustom;
@Repository
public interface SysAppMessageCustomMapper{

	List<SysAppMessageCustom> getSystemMsg(Map<String, Object> params);

	List<SysAppMessageCustom> getTransactionMsg(Map<String, Object> params);

}