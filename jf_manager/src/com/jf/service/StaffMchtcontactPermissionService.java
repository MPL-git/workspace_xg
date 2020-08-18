package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.StaffMchtcontactPermissionMapper;
import com.jf.entity.StaffMchtcontactPermission;
import com.jf.entity.StaffMchtcontactPermissionExample;

@Service
@Transactional
public class StaffMchtcontactPermissionService extends BaseService<StaffMchtcontactPermission, StaffMchtcontactPermissionExample> {

	@Autowired
	private StaffMchtcontactPermissionMapper staffMchtcontactPermissionMapper;
	
	
	@Autowired
	public void setStaffMchtcontactPermissionMapper(StaffMchtcontactPermissionMapper staffMchtcontactPermissionMapper) {
		super.setDao(staffMchtcontactPermissionMapper);
		this.staffMchtcontactPermissionMapper = staffMchtcontactPermissionMapper;
	}
	
}
