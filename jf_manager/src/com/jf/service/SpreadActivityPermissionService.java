package com.jf.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jf.dao.*;
import com.jf.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SpreadActivityPermissionService extends BaseService<SpreadActivityPermission, SpreadActivityPermissionExample> {

	@Autowired
	private SpreadActivityPermissionMapper spreadActivityPermissionMapper;
	
	@Autowired
	private SpreadActivityPermissionCustomMapper spreadActivityPermissionCustomMapper;
	
	@Autowired
	private SpreadActivityGroupMapper spreadActivityGroupMapper;
	
	@Autowired
	private SpreadActivityGroupSetDtlMapper spreadActivityGroupSetDtlMapper;
	
	@Autowired
	private SpreadActivityGroupSetDtlCustomMapper spreadActivityGroupSetDtlCustomMapper;

	@Autowired
	private AndroidChannelGroupSetDtlMapper androidChannelGroupSetDtlMapper;

	@Autowired
	private AndroidChannelGroupSetDtlCustomMapper androidChannelGroupSetDtlCustomMapper;

	@Autowired
	public void setSpreadActivityPermissionMapper(SpreadActivityPermissionMapper spreadActivityPermissionMapper) {
		super.setDao(spreadActivityPermissionMapper);
		this.spreadActivityPermissionMapper = spreadActivityPermissionMapper;
	}
	
	public Integer getSpreadActivityGroupCount(Map<String, Object> paramMap) {
		return spreadActivityPermissionCustomMapper.getSpreadActivityGroupCount(paramMap);
	}
	
	public List<Map<String, Object>> getSpreadActivityGroupList(Map<String, Object> paramMap) {
		return spreadActivityPermissionCustomMapper.getSpreadActivityGroupList(paramMap);
	}
	
	public Integer getAndroidChannelGroupCount(Map<String, Object> paramMap) {
		return spreadActivityPermissionCustomMapper.getAndroidChannelGroupCount(paramMap);
	}
	
	public List<Map<String, Object>> getAndroidChannelGroupList(Map<String, Object> paramMap) {
		return spreadActivityPermissionCustomMapper.getAndroidChannelGroupList(paramMap);
	}
	
	public void updateIOSPermission(HttpServletRequest request) {
		Date date = new Date();
		String spreadId = request.getParameter("spreadId");
		String staffId = request.getParameter("staffId");
		String type = request.getParameter("type");
		SpreadActivityGroupSetDtlExample spreadActivityGroupSetDtlExample = new SpreadActivityGroupSetDtlExample();
		spreadActivityGroupSetDtlExample.createCriteria().andDelFlagEqualTo("0").andActivityGroupIdEqualTo(Integer.parseInt(spreadId));
		spreadActivityGroupSetDtlExample.setOrderByClause(" id desc");
		List<SpreadActivityGroupSetDtl> spreadActivityGroupSetDtlList = spreadActivityGroupSetDtlMapper.selectByExample(spreadActivityGroupSetDtlExample);
		if("1".equals(type) ) { //添加
			SpreadActivityPermissionExample spreadActivityPermissionExample = new SpreadActivityPermissionExample();
			spreadActivityPermissionExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("1")
				.andStaffInfoIdEqualTo(Integer.parseInt(staffId))
				.andSpreadIdEqualTo(Integer.parseInt(spreadId));
			List<SpreadActivityPermission> spreadActivityPermissionList = spreadActivityPermissionMapper.selectByExample(spreadActivityPermissionExample);
			if(spreadActivityPermissionList != null && spreadActivityPermissionList.size() > 0 ) {
				SpreadActivityPermission spreadActivityPermission = new SpreadActivityPermission();
				spreadActivityPermission.setStatus("1");
				spreadActivityPermission.setUpdateDate(date);
				spreadActivityPermissionMapper.updateByExampleSelective(spreadActivityPermission, spreadActivityPermissionExample);
			}else {
				SpreadActivityPermission spreadActivityPermission = new SpreadActivityPermission();
				spreadActivityPermission.setType("1");
				spreadActivityPermission.setStaffInfoId(Integer.parseInt(staffId));
				spreadActivityPermission.setSpreadId(Integer.parseInt(spreadId));
				spreadActivityPermission.setStatus("1");
				spreadActivityPermission.setCreateDate(date);
				spreadActivityPermissionMapper.insertSelective(spreadActivityPermission);
			}
			for(SpreadActivityGroupSetDtl activityGroupSetDtl : spreadActivityGroupSetDtlList) {
				SpreadActivityGroupSetDtlCustomExample spreadActivityGroupSetDtlCustomExample = new SpreadActivityGroupSetDtlCustomExample();
				SpreadActivityGroupSetDtlCustomExample.SpreadActivityGroupSetDtlCustomCriteria spreadActivityGroupSetDtlCustomCriteria = spreadActivityGroupSetDtlCustomExample.createCriteria();
				spreadActivityGroupSetDtlCustomCriteria.andDelFlagEqualTo("0").andSpreadActivityGroupSetIdEqualTo(activityGroupSetDtl.getSpreadActivityGroupSetId());
				spreadActivityGroupSetDtlCustomCriteria.andSpreadActivityGroupSetStatusEqualTo();
				List<SpreadActivityGroupSetDtlCustom> spreadActivityGroupSetDtlCustomList = spreadActivityGroupSetDtlCustomMapper.selectByCustomExample(spreadActivityGroupSetDtlCustomExample);
				if(spreadActivityGroupSetDtlCustomList != null && spreadActivityGroupSetDtlCustomList.size() > 0 ) {
					List<Integer> spreadActivityGroupIdList = new ArrayList<Integer>();
					for(SpreadActivityGroupSetDtlCustom spreadActivityGroupSetDtlCustom : spreadActivityGroupSetDtlCustomList ) {
						if(!spreadActivityGroupIdList.contains(spreadActivityGroupSetDtlCustom.getActivityGroupId()) ) {
							spreadActivityGroupIdList.add(spreadActivityGroupSetDtlCustom.getActivityGroupId());
						}
					}
					SpreadActivityPermissionExample spreadPermissionExample = new SpreadActivityPermissionExample();
					spreadPermissionExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("1").andStatusEqualTo("1")
							.andStaffInfoIdEqualTo(Integer.parseInt(staffId))
							.andSpreadIdIn(spreadActivityGroupIdList);
					List<SpreadActivityPermission> spreadPermissionList = spreadActivityPermissionMapper.selectByExample(spreadPermissionExample);
					if(spreadPermissionList != null && spreadPermissionList.size() > 0 &&  spreadPermissionList.size() == spreadActivityGroupIdList.size() ) {
						SpreadActivityPermissionExample groupSetPermissionExample = new SpreadActivityPermissionExample();
						groupSetPermissionExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("2")
								.andStaffInfoIdEqualTo(Integer.parseInt(staffId))
								.andSpreadIdEqualTo(activityGroupSetDtl.getSpreadActivityGroupSetId());
						List<SpreadActivityPermission> permissionList = spreadActivityPermissionMapper.selectByExample(groupSetPermissionExample);
						if(permissionList != null && permissionList.size() > 0 ) {
							SpreadActivityPermission spreadActivityPermission = new SpreadActivityPermission();
							spreadActivityPermission.setStatus("1");
							spreadActivityPermission.setUpdateDate(date);
							spreadActivityPermissionMapper.updateByExampleSelective(spreadActivityPermission, groupSetPermissionExample);
						}else {
							SpreadActivityPermission spreadActivityPermission = new SpreadActivityPermission();
							spreadActivityPermission.setType("2");
							spreadActivityPermission.setStaffInfoId(Integer.parseInt(staffId));
							spreadActivityPermission.setSpreadId(activityGroupSetDtl.getSpreadActivityGroupSetId());
							spreadActivityPermission.setStatus("1");
							spreadActivityPermission.setCreateDate(date);
							spreadActivityPermissionMapper.insertSelective(spreadActivityPermission);
						}
					}
				}
			}
		}else { //移除
			SpreadActivityPermissionExample spreadActivityPermissionExample = new SpreadActivityPermissionExample();
			spreadActivityPermissionExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("1").andStatusEqualTo("1")
					.andStaffInfoIdEqualTo(Integer.parseInt(staffId))
					.andSpreadIdEqualTo(Integer.parseInt(spreadId));
			SpreadActivityPermission spreadActivityPermission = new SpreadActivityPermission();
			spreadActivityPermission.setStatus("0");
			spreadActivityPermission.setUpdateDate(date);
			spreadActivityPermissionMapper.updateByExampleSelective(spreadActivityPermission, spreadActivityPermissionExample);
			for(SpreadActivityGroupSetDtl activityGroupSetDtl : spreadActivityGroupSetDtlList) {
				if(spreadActivityGroupSetDtlList != null && spreadActivityGroupSetDtlList.size() > 0 ) {
					SpreadActivityPermissionExample groupSetPermissionExample = new SpreadActivityPermissionExample();
					groupSetPermissionExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("2").andStatusEqualTo("1")
							.andStaffInfoIdEqualTo(Integer.parseInt(staffId))
							.andSpreadIdEqualTo(activityGroupSetDtl.getSpreadActivityGroupSetId());
					List<SpreadActivityPermission> permissionList = spreadActivityPermissionMapper.selectByExample(groupSetPermissionExample);
					if(permissionList != null && permissionList.size() > 0 ) {
						SpreadActivityPermission groupSetPermission = new SpreadActivityPermission();
						groupSetPermission.setStatus("0");
						groupSetPermission.setUpdateDate(date);
						spreadActivityPermissionMapper.updateByExampleSelective(groupSetPermission, groupSetPermissionExample);
					}
				}
			}
		}
	}


	public void updateAndroidPermission(HttpServletRequest request) {
		Date date = new Date();
		String spreadId = request.getParameter("spreadId");
		String staffId = request.getParameter("staffId");
		String type = request.getParameter("type");
		AndroidChannelGroupSetDtlExample androidChannelGroupSetDtlExample = new AndroidChannelGroupSetDtlExample();
		androidChannelGroupSetDtlExample.createCriteria().andDelFlagEqualTo("0").andAndroidChannelGroupIdEqualTo(Integer.parseInt(spreadId));
		androidChannelGroupSetDtlExample.setOrderByClause(" id desc");
		List<AndroidChannelGroupSetDtl> androidChannelGroupSetDtlList = androidChannelGroupSetDtlMapper.selectByExample(androidChannelGroupSetDtlExample);
		if("1".equals(type) ) { //添加
			SpreadActivityPermissionExample spreadActivityPermissionExample = new SpreadActivityPermissionExample();
			spreadActivityPermissionExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("3")
					.andStaffInfoIdEqualTo(Integer.parseInt(staffId))
					.andSpreadIdEqualTo(Integer.parseInt(spreadId));
			List<SpreadActivityPermission> spreadActivityPermissionList = spreadActivityPermissionMapper.selectByExample(spreadActivityPermissionExample);
			if(spreadActivityPermissionList != null && spreadActivityPermissionList.size() > 0 ) {
				SpreadActivityPermission spreadActivityPermission = new SpreadActivityPermission();
				spreadActivityPermission.setStatus("1");
				spreadActivityPermission.setUpdateDate(date);
				spreadActivityPermissionMapper.updateByExampleSelective(spreadActivityPermission, spreadActivityPermissionExample);
			}else {
				SpreadActivityPermission spreadActivityPermission = new SpreadActivityPermission();
				spreadActivityPermission.setType("3");
				spreadActivityPermission.setStaffInfoId(Integer.parseInt(staffId));
				spreadActivityPermission.setSpreadId(Integer.parseInt(spreadId));
				spreadActivityPermission.setStatus("1");
				spreadActivityPermission.setCreateDate(date);
				spreadActivityPermissionMapper.insertSelective(spreadActivityPermission);
			}
			for(AndroidChannelGroupSetDtl androidChannelGroupSetDtl : androidChannelGroupSetDtlList ) {
				AndroidChannelGroupSetDtlCustomExample androidChannelGroupSetDtlCustomExample = new AndroidChannelGroupSetDtlCustomExample();
				AndroidChannelGroupSetDtlCustomExample.AndroidChannelGroupSetDtlCustomCriteria androidChannelGroupSetDtlCustomCriteria = androidChannelGroupSetDtlCustomExample.createCriteria();
				androidChannelGroupSetDtlCustomCriteria.andDelFlagEqualTo("0").andAndroidChannelGroupSetIdEqualTo(androidChannelGroupSetDtl.getAndroidChannelGroupSetId());
				androidChannelGroupSetDtlCustomCriteria.andAndroidChannelGroupSetStatusEqualTo();
				List<AndroidChannelGroupSetDtlCustom> androidChannelGroupSetDtlCustomList = androidChannelGroupSetDtlCustomMapper.selectByCustomExample(androidChannelGroupSetDtlCustomExample);
				if(androidChannelGroupSetDtlCustomList != null && androidChannelGroupSetDtlCustomList.size() > 0 ) {
					List<Integer> androidChannelGroupIdList = new ArrayList<Integer>();
					for(AndroidChannelGroupSetDtlCustom androidChannelGroupSetDtlCustom : androidChannelGroupSetDtlCustomList ) {
						if(!androidChannelGroupIdList.contains(androidChannelGroupSetDtlCustom.getAndroidChannelGroupId()) ) {
							androidChannelGroupIdList.add(androidChannelGroupSetDtlCustom.getAndroidChannelGroupId());
						}
					}
					SpreadActivityPermissionExample spreadPermissionExample = new SpreadActivityPermissionExample();
					spreadPermissionExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("3").andStatusEqualTo("1")
							.andStaffInfoIdEqualTo(Integer.parseInt(staffId))
							.andSpreadIdIn(androidChannelGroupIdList);
					List<SpreadActivityPermission> spreadPermissionList = spreadActivityPermissionMapper.selectByExample(spreadPermissionExample);
					if(spreadPermissionList != null && spreadPermissionList.size() > 0 &&  spreadPermissionList.size() == androidChannelGroupIdList.size() ) {
						SpreadActivityPermissionExample groupSetPermissionExample = new SpreadActivityPermissionExample();
						groupSetPermissionExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("4")
								.andStaffInfoIdEqualTo(Integer.parseInt(staffId))
								.andSpreadIdEqualTo(androidChannelGroupSetDtl.getAndroidChannelGroupSetId());
						List<SpreadActivityPermission> permissionList = spreadActivityPermissionMapper.selectByExample(groupSetPermissionExample);
						if(permissionList != null && permissionList.size() > 0 ) {
							SpreadActivityPermission spreadActivityPermission = new SpreadActivityPermission();
							spreadActivityPermission.setStatus("1");
							spreadActivityPermission.setUpdateDate(date);
							spreadActivityPermissionMapper.updateByExampleSelective(spreadActivityPermission, groupSetPermissionExample);
						}else {
							SpreadActivityPermission spreadActivityPermission = new SpreadActivityPermission();
							spreadActivityPermission.setType("4");
							spreadActivityPermission.setStaffInfoId(Integer.parseInt(staffId));
							spreadActivityPermission.setSpreadId(androidChannelGroupSetDtl.getAndroidChannelGroupSetId());
							spreadActivityPermission.setStatus("1");
							spreadActivityPermission.setCreateDate(date);
							spreadActivityPermissionMapper.insertSelective(spreadActivityPermission);
						}
					}
				}
			}
		}else { //移除
			SpreadActivityPermissionExample spreadActivityPermissionExample = new SpreadActivityPermissionExample();
			spreadActivityPermissionExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("3").andStatusEqualTo("1")
					.andStaffInfoIdEqualTo(Integer.parseInt(staffId))
					.andSpreadIdEqualTo(Integer.parseInt(spreadId));
			SpreadActivityPermission spreadActivityPermission = new SpreadActivityPermission();
			spreadActivityPermission.setStatus("0");
			spreadActivityPermission.setUpdateDate(date);
			spreadActivityPermissionMapper.updateByExampleSelective(spreadActivityPermission, spreadActivityPermissionExample);
			for(AndroidChannelGroupSetDtl androidChannelGroupSetDtl : androidChannelGroupSetDtlList ) {
				SpreadActivityPermissionExample groupSetPermissionExample = new SpreadActivityPermissionExample();
				groupSetPermissionExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("4").andStatusEqualTo("1")
						.andStaffInfoIdEqualTo(Integer.parseInt(staffId))
						.andSpreadIdEqualTo(androidChannelGroupSetDtl.getAndroidChannelGroupSetId());
				List<SpreadActivityPermission> permissionList = spreadActivityPermissionMapper.selectByExample(groupSetPermissionExample);
				if(permissionList != null && permissionList.size() > 0 ) {
					SpreadActivityPermission groupSetPermission = new SpreadActivityPermission();
					groupSetPermission.setStatus("0");
					groupSetPermission.setUpdateDate(date);
					spreadActivityPermissionMapper.updateByExampleSelective(groupSetPermission, groupSetPermissionExample);
				}
			}
		}
	}


	
}
