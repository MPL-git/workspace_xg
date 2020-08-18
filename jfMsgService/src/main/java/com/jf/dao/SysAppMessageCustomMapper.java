package com.jf.dao;

import com.jf.entity.SysAppMessage;
import com.jf.entity.SysAppMessageMember;
import com.jf.entity.TaskSendMember;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SysAppMessageCustomMapper{

	void insertBatchForMemberMsg(List<SysAppMessageMember> memberMsgs);

	int batchInsertSysAppMessageByTaskActivitySelectionId(@Param("taskSendMemberList")List<TaskSendMember> taskSendMemberList, @Param("sysAppMessage")SysAppMessage sysAppMessage);
}