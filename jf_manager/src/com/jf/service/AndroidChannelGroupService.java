package com.jf.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.AndroidChannelGroupCustomMapper;
import com.jf.dao.AndroidChannelGroupMapper;
import com.jf.dao.SpreadDataMapper;
import com.jf.entity.AndroidChannelGroup;
import com.jf.entity.AndroidChannelGroupCustom;
import com.jf.entity.AndroidChannelGroupExample;
import com.jf.entity.SpreadData;
import com.jf.entity.SpreadDataExample;

@Service
@Transactional
public class AndroidChannelGroupService extends BaseService<AndroidChannelGroup,AndroidChannelGroupExample> {
	@Autowired
	private AndroidChannelGroupMapper androidChannelGroupMapper;
	@Autowired
	private AndroidChannelGroupCustomMapper androidChannelGroupCustomMapper;
	@Autowired
	private SpreadDataMapper spreadDataMapper;
	
	@Autowired
	public void setAndroidChannelGroupMapper(AndroidChannelGroupMapper androidChannelGroupMapper) {
		super.setDao(androidChannelGroupMapper);
		this.androidChannelGroupMapper = androidChannelGroupMapper;
	}
	
	public int countCouponCustomByExample(AndroidChannelGroupExample example){
		return androidChannelGroupCustomMapper.countByExample(example);
	}
    public List<AndroidChannelGroupCustom> selectAndroidChannelGroupCustomByExample(AndroidChannelGroupExample example){
    	return androidChannelGroupCustomMapper.selectByExample(example);
    }
    public AndroidChannelGroupCustom selectAndroidChannelGroupCustomByPrimaryKey(Integer id){
    	return androidChannelGroupCustomMapper.selectByPrimaryKey(id);
    }
    
	public Map<String, Object> updateOrInsertSpreadData(HttpServletRequest request, List<SpreadData> spreadDataList, Integer staffId) {
		if(spreadDataList != null && spreadDataList.size() > 0) {
			Integer updateNum = 0;
			Integer insertNum = 0;
				for(SpreadData spreadData : spreadDataList) {
					SpreadDataExample spreadDataExample = new SpreadDataExample();
					SpreadDataExample.Criteria spreadDataCriteria = spreadDataExample.createCriteria();
					spreadDataCriteria.andDelFlagEqualTo("0").andDateEqualTo(spreadData.getDate()).andAccountIdEqualTo(spreadData.getAccountId());
					List<SpreadData> spreadDatas = spreadDataMapper.selectByExample(spreadDataExample);
					if(spreadDatas != null && spreadDatas.size() > 0) {
						++updateNum;
						spreadData.setUpdateBy(staffId);
						spreadData.setUpdateDate(new Date());
						spreadDataMapper.updateByExampleSelective(spreadData, spreadDataExample);
					}else {
						++insertNum;
						spreadData.setCreateBy(staffId);
						spreadData.setCreateDate(new Date());
						spreadDataMapper.insertSelective(spreadData);
					}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("updateNum", updateNum);
			map.put("insertNum", insertNum);
			return map;
		}
		return null;
	}
}
