package com.jf.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.dao.CustomAdPageCustomMapper;
import com.jf.dao.CustomAdPageMapper;
import com.jf.dao.DecorateAreaCustomMapper;
import com.jf.dao.DecorateAreaMapper;
import com.jf.dao.DecorateInfoCustomMapper;
import com.jf.dao.DecorateInfoMapper;
import com.jf.dao.DecorateModuleCustomMapper;
import com.jf.dao.DecorateModuleMapper;
import com.jf.dao.ModuleMapMapper;
import com.jf.entity.BrandteamTypeadInfoCustom;
import com.jf.entity.BrandteamTypeadInfoCustomExample;
import com.jf.entity.CustomAdPage;
import com.jf.entity.CustomAdPageCustom;
import com.jf.entity.CustomAdPageCustomExample;
import com.jf.entity.CustomAdPageExample;
import com.jf.entity.DecorateArea;
import com.jf.entity.DecorateAreaCustom;
import com.jf.entity.DecorateAreaExample;
import com.jf.entity.DecorateInfo;
import com.jf.entity.DecorateInfoExample;
import com.jf.entity.DecorateModule;
import com.jf.entity.DecorateModuleCustom;
import com.jf.entity.DecorateModuleExample;
import com.jf.entity.ModuleMap;
import com.jf.entity.ModuleMapExample;

@Service
@Transactional
public class CustomAdPageService extends
		BaseService<CustomAdPage, CustomAdPageExample> {

	@Autowired
	private CustomAdPageMapper customAdPageMapper;

	@Autowired
	private CustomAdPageCustomMapper customAdPageCustomMapper;

	@Autowired
	private DecorateInfoMapper decorateInfoMapper;

	@Autowired
	private DecorateInfoCustomMapper decorateInfoCustomMapper;

	@Autowired
	private DecorateAreaMapper decorateAreaMapper;

	@Autowired
	private DecorateModuleCustomMapper decorateModuleCustomMapper;

	@Autowired
	private DecorateModuleMapper decorateModuleMapper;
	
	@Autowired
	private DecorateAreaCustomMapper decorateAreaCustomMapper;
	
	@Autowired
	private ModuleMapMapper moduleMapMapper;
	
	@Autowired
	public void setCustomAdPageMapper(CustomAdPageMapper customAdPageMapper) {
		super.setDao(customAdPageMapper);
		this.customAdPageMapper = customAdPageMapper;
	}

	public List<CustomAdPageCustom> selectByCustomExample(
			CustomAdPageCustomExample example) {
		return customAdPageCustomMapper.selectByCustomExample(example);
	}

	public Integer countByCustomExample(CustomAdPageCustomExample example) {
		return customAdPageCustomMapper.countByCustomExample(example);
	}

	public CustomAdPageCustom selectByCustomPrimaryKey(Integer id) {
		return customAdPageCustomMapper.selectByCustomPrimaryKey(id);
	}

	public void save(CustomAdPage customAdPage) {
		if (customAdPage.getId() != null) {
			this.updateByPrimaryKeySelective(customAdPage);
			DecorateInfo decorateInfo = decorateInfoMapper
					.selectByPrimaryKey(customAdPage.getDecorateInfoId());
			decorateInfo.setUpdateDate(new Date());
			decorateInfo.setDecorateName(customAdPage.getPageName());
			decorateInfoMapper.updateByPrimaryKey(decorateInfo);
		} else {
			DecorateInfo decorateInfo = new DecorateInfo();
			decorateInfo.setDelFlag("0");
			decorateInfo.setCreateDate(new Date());
			decorateInfo.setDecorateName(customAdPage.getPageName());
			decorateInfoMapper.insertSelective(decorateInfo);
			customAdPage.setDecorateInfoId(decorateInfo.getId());
			DecorateArea decorateArea = new DecorateArea();
			decorateArea.setDelFlag("0");
			decorateArea.setCreateDate(new Date());
			decorateArea.setDecorateInfoId(decorateInfo.getId());
			decorateArea.setRemarks(customAdPage.getPageName());
			decorateAreaMapper.insertSelective(decorateArea);
			this.insertSelective(customAdPage);
		}
	}

	// 首页栏目装修复制功能
	public Map<String, Object> copy(String decorateInfoId) {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		msgMap.put("returnCode", "200");
		try {
			// TODO Auto-generated method stub
			// 装修信息数据
			DecorateInfoExample dif = new DecorateInfoExample();
			DecorateInfoExample.Criteria difc = dif.createCriteria();
			difc.andDelFlagEqualTo("0").andIdEqualTo(
					Integer.parseInt(decorateInfoId));
			List<DecorateInfo> decorateInfos = decorateInfoMapper
					.selectByExample(dif);
			DecorateInfo decorateInfo = decorateInfos.get(0);
			// 复制装修信息数据
			DecorateInfo dInfo = new DecorateInfo();
			dInfo.setDecorateName(decorateInfo.getDecorateName() + "副本");
			dInfo.setCreateBy(decorateInfo.getCreateBy());
			dInfo.setCreateDate(decorateInfo.getCreateDate());
			dInfo.setDelFlag(decorateInfo.getDelFlag());
			dInfo.setRemarks(decorateInfo.getRemarks());
			dInfo.setUpdateBy(decorateInfo.getUpdateBy());
			dInfo.setUpdateDate(decorateInfo.getUpdateDate());
			decorateInfoCustomMapper.insertSelective1(dInfo);
			int id = dInfo.getId();
			// 自定义广告数据
			CustomAdPageCustomExample cape = new CustomAdPageCustomExample();
			CustomAdPageCustomExample.Criteria capec = cape.createCriteria();
			capec.andDelFlagEqualTo("0").andDecorateInfoIdEqualTo(
					Integer.parseInt(decorateInfoId));
			List<CustomAdPage> customs = customAdPageMapper
					.selectByExample(cape);
			// 复制自定义广告数据
			CustomAdPage cAdPage = customs.get(0);
			CustomAdPage customAdPage = new CustomAdPage();
			customAdPage.setPageType(cAdPage.getPageType());
			customAdPage.setPageName(cAdPage.getPageName() + "副本");
			customAdPage.setDecorateInfoId(id);
			customAdPage.setIsEffect("0");
			customAdPage.setAutoUpDate(cAdPage.getAutoUpDate());
			customAdPage.setAutoDownDate(cAdPage.getAutoDownDate());
			customAdPage.setCreateBy(cAdPage.getCreateBy());
			customAdPage.setCreateDate(cAdPage.getCreateDate());
			customAdPage.setUpdateBy(cAdPage.getUpdateBy());
			customAdPage.setUpdateDate(cAdPage.getUpdateDate());
			customAdPage.setDelFlag("0");
			customAdPage.setSourceProductTypeId(cAdPage
					.getSourceProductTypeId());
			customAdPageMapper.insertSelective(customAdPage);
			// 装修分区数据
			DecorateAreaExample dae = new DecorateAreaExample();
			DecorateAreaExample.Criteria daec = dae.createCriteria();
			daec.andDelFlagEqualTo("0");
			daec.andDecorateInfoIdEqualTo(Integer.parseInt(decorateInfoId));
			List<DecorateArea> decorateAreas = decorateAreaMapper
					.selectByExample(dae);
			DecorateArea decorateArea = decorateAreas.get(0);
			// 复制装修分区表
			DecorateArea dArea = new DecorateArea();
			dArea.setAreaName(decorateArea.getAreaName());
			dArea.setCreateBy(decorateArea.getCreateBy());
			dArea.setCreateDate(decorateArea.getCreateDate());
			dArea.setDecorateInfoId(id);
			dArea.setDelFlag(decorateArea.getDelFlag());
			dArea.setRemarks(decorateArea.getRemarks());
			dArea.setSeqNo(decorateArea.getSeqNo());
			dArea.setUpdateBy(decorateArea.getUpdateBy());
			dArea.setUpdateDate(decorateArea.getUpdateDate());
			decorateAreaCustomMapper.insertSelective1(dArea);
			int areaId = dArea.getId();
			// 装修模块数据
			DecorateModuleExample dme = new DecorateModuleExample();
			DecorateModuleExample.Criteria dmec = dme.createCriteria();
			dmec.andDelFlagEqualTo("0");
			dmec.andDecorateAreaIdEqualTo(decorateArea.getId());
			List<DecorateModuleCustom> decorateModuleCustoms = decorateModuleCustomMapper
					.selectByExample(dme);

			// 存储模块类型是图片的模块ID
			List<Integer> decorateModuleIdList = new ArrayList<Integer>();
			//用旧的装修模块ID作为KEY，新的装修模块ID作为VALUE
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			// 复制装修模块表
			for (DecorateModuleCustom decorateModuleCustom : decorateModuleCustoms) {
				DecorateModule dModule = new DecorateModule();
				dModule.setCreateBy(decorateModuleCustom.getCreateBy());
				dModule.setCreateDate(decorateModuleCustom.getCreateDate());
				dModule.setDecorateAreaId(areaId);
				dModule.setDelFlag(decorateModuleCustom.getDelFlag());
				dModule.setFieldBgColor(decorateModuleCustom.getFieldBgColor());
				dModule.setFieldFontColor(decorateModuleCustom
						.getFieldFontColor());
				dModule.setFieldSelectFontColor(decorateModuleCustom
						.getFieldSelectFontColor());
				dModule.setModuleType(decorateModuleCustom.getModuleType());
				dModule.setOpenBtnArrowColor(decorateModuleCustom
						.getOpenBtnArrowColor());
				dModule.setOpenBtnBgColor(decorateModuleCustom
						.getOpenBtnBgColor());
				dModule.setOpenFieldBgColor(decorateModuleCustom
						.getOpenFieldBgColor());
				dModule.setOpenFontColor(decorateModuleCustom
						.getOpenFontColor());
				dModule.setPic(decorateModuleCustom.getPic());
				dModule.setProductBrandIds(decorateModuleCustom
						.getProductBrandIds());
				dModule.setProductType1Ids(decorateModuleCustom
						.getProductType1Ids());
				dModule.setProductType2Ids(decorateModuleCustom
						.getProductType2Ids());
				dModule.setRemarks(decorateModuleCustom.getRemarks());
				dModule.setSeqNo(decorateModuleCustom.getSeqNo());
				dModule.setShowNum(decorateModuleCustom.getShowNum());
				dModule.setUpdateBy(decorateModuleCustom.getUpdateBy());
				dModule.setUpdateDate(decorateModuleCustom.getUpdateDate());
				decorateModuleCustomMapper.insertSelective(dModule);
				int decorateModuleId = dModule.getId();	
					decorateModuleIdList.add(decorateModuleCustom.getId());
					map.put(decorateModuleCustom.getId(), decorateModuleId);
			}
			// 图片热区数据
			if (!decorateModuleIdList.isEmpty()) {
				ModuleMapExample mme = new ModuleMapExample();
				ModuleMapExample.Criteria mmec = mme.createCriteria();
				mmec.andDelFlagEqualTo("0");
				mmec.andDecorateModuleIdIn(decorateModuleIdList);
				List<ModuleMap> moduleMaps = moduleMapMapper.selectByExample(mme);
				//复制图片热区数据
				for (ModuleMap moduleMap : moduleMaps) {
					ModuleMap moMap = new ModuleMap();
					moMap.setCoords(moduleMap.getCoords());
					moMap.setCreateBy(moduleMap.getCreateBy());
					moMap.setCreateDate(moduleMap.getCreateDate());
					moMap.setDecorateModuleId(map.get(moduleMap.getDecorateModuleId()));
					moMap.setDelFlag(moduleMap.getDelFlag());
					moMap.setLinkType(moduleMap.getLinkType());
					moMap.setLinkUrl(moduleMap.getLinkUrl());
					moMap.setLinkValue(moduleMap.getLinkValue());
					moMap.setRemarks(moduleMap.getRemarks());
					moMap.setUpdateBy(moduleMap.getUpdateBy());
					moMap.setUpdateDate(moduleMap.getUpdateDate());
					moduleMapMapper.insertSelective(moMap);
				}
			}
			return msgMap;
		} catch (Exception e) {
			// TODO: handle exception
			msgMap.put("returnCode", "4004");
			msgMap.put("returnMsg", e.getMessage());
			return msgMap;
		}
	}
}
