package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.PropertyRightComplainPicMapper;
import com.jf.entity.PropertyRightComplainPic;
import com.jf.entity.PropertyRightComplainPicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
@Transactional
public class PropertyRightComplainPicService extends BaseService<PropertyRightComplainPic, PropertyRightComplainPicExample> {
	
	@Autowired
	private PropertyRightComplainPicMapper mapper;
	
	@Autowired
	public void setPropertyRightComplainPicMapper(PropertyRightComplainPicMapper propertyRightComplainPicMapper) {
		super.setDao(propertyRightComplainPicMapper);
		this.mapper = propertyRightComplainPicMapper;
	}

	/**
	 * 根据产权申诉id删除
	 *
	 * @param complainId
	 * @param userId
	 */
	public void delete(Integer complainId, Integer userId){
		PropertyRightComplainPicExample complainPicExample = new PropertyRightComplainPicExample();
		PropertyRightComplainPicExample.Criteria complainCriteria = complainPicExample.createCriteria();
		complainCriteria.andPropertyRightComplainIdEqualTo(complainId);
		complainCriteria.andDelFlagEqualTo("0");
		PropertyRightComplainPic complainPic = new PropertyRightComplainPic();
		complainPic.setUpdateBy(userId);
		complainPic.setUpdateDate(new Date());
		complainPic.setDelFlag("1");
		dao.updateByExampleSelective(complainPic, complainPicExample);
	}

	/**
	 * 批量保存
	 *
	 * @param picArr
	 * @param complainId
	 * @param createBy
	 */
	public void savePicList(String[] picArr, Integer complainId, Integer createBy) {
		for (int i = 0; i < picArr.length; i++) {
			PropertyRightComplainPic propertyRightComplainPic = new PropertyRightComplainPic();
			propertyRightComplainPic.setPropertyRightComplainId(complainId);
			propertyRightComplainPic.setCreateDate(new Date());
			propertyRightComplainPic.setCreateBy(createBy);
			propertyRightComplainPic.setPic(picArr[i]);
			propertyRightComplainPic.setDelFlag("0");
			dao.insert(propertyRightComplainPic);
		}
	}
	
}
