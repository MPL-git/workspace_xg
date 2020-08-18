package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jf.dao.SysStaffRoleCustomMapper;
import com.jf.dao.SysStaffRoleMapper;
import com.jf.entity.SysStaffRole;
import com.jf.entity.SysStaffRoleCustom;
import com.jf.entity.SysStaffRoleCustomExample;
import com.jf.entity.SysStaffRoleExample;

@Service
public class SysStaffRoleService extends BaseService<SysStaffRole, SysStaffRoleExample> {

	@Autowired
	private SysStaffRoleMapper sysStaffRoleMapper;
	
	@Autowired
	private SysStaffRoleCustomMapper sysStaffRoleCustomMapper;
	
	@Autowired
	public void setsysStaffRoleMapper(SysStaffRoleMapper sysStaffRoleMapper) {
		super.setDao(sysStaffRoleMapper);
		this.sysStaffRoleMapper = sysStaffRoleMapper;
	}
	
	public Integer countByCustomExample(SysStaffRoleCustomExample example) {
		return sysStaffRoleCustomMapper.countByCustomExample(example);
	}

	public List<SysStaffRoleCustom> selectByCustomExample(SysStaffRoleCustomExample example) {
		return sysStaffRoleCustomMapper.selectByCustomExample(example);
	}
	
	public SysStaffRoleCustom selectByPrimaryKeyCustom(Integer staffRoleId) {
		return sysStaffRoleCustomMapper.selectByPrimaryKeyCustom(staffRoleId);
	}
	
	/**
	 * 通过roleId获取系统用户list
	 * 
	 * @param roleId
	 * @return
	 */
	public List<SysStaffRoleCustom> selectStaffRoleList(Integer roleId) {
		return sysStaffRoleCustomMapper.selectStaffRoleList(roleId);
	}
	
	/**
     * 通过角色id以及用户id查询记录
     * 
     * @param staffId
     * @param roleId
     * @return
     */
    public Integer getCanOperate(Integer staffId, Integer roleId) {
    	SysStaffRoleExample roleExample = new SysStaffRoleExample();
    	SysStaffRoleExample.Criteria roleCriteria = roleExample.createCriteria();
    	roleCriteria.andStaffIdEqualTo(staffId);
    	roleCriteria.andRoleIdEqualTo(roleId);
    	roleCriteria.andStatusEqualTo("A");
    	List<SysStaffRole> list = sysStaffRoleMapper.selectByExample(roleExample);
    	if (list.isEmpty()) {
    		return 0;
    	}
    	return 1;
    }
	
}
