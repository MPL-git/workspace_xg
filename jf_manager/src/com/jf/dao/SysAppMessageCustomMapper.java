package com.jf.dao;

import com.jf.entity.SysAppMessage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SysAppMessageCustomMapper{

	int batchInsertSysAppMessageByTaskActivitySelectionId(@Param("itemsList")List<Integer> itemsList,@Param("sysAppMessage")SysAppMessage sysAppMessage);
	
}