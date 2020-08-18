package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.PropertyRightComplainCustomMapper;
import com.jf.dao.PropertyRightComplainMapper;
import com.jf.entity.IntellectualPropertyRightAppeal;
import com.jf.entity.PropertyRightComplain;
import com.jf.entity.PropertyRightComplainExample;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Description: 知识产权声明业务层
 * @Author: zhen.li
 * @Date: 2018/12/26 15:21
 */
@Service
@Transactional
public class PropertyRightComplainService extends BaseService<PropertyRightComplain, PropertyRightComplainExample> {

    private static Logger logger = LoggerFactory.getLogger(PropertyRightComplainService.class);

    @Autowired
    private PropertyRightComplainMapper mapper;

    @Autowired
    private PropertyRightComplainCustomMapper customMapper;

    @Autowired
    private PropertyRightAppealService propertyRightAppealService;

    @Autowired
    public void setMapper(PropertyRightComplainMapper mapper) {
        this.setDao(mapper);
        this.mapper = mapper;
    }

    /**
     * 更新超期未起诉投诉单的主状态为完成
     *
     */
    public void updateOverProsecutionDueStatus() {
        Date date = new Date();
        PropertyRightComplainExample complainExample = new PropertyRightComplainExample();
        PropertyRightComplainExample.Criteria criteria = complainExample.createCriteria();
        criteria.andStatusEqualTo("4");
        criteria.andProsecutionEndDateLessThan(date);
        criteria.andDelFlagEqualTo("0");

        List<PropertyRightComplain> complainList = customMapper.selectOverDueByExample(complainExample);
        if (CollectionUtils.isNotEmpty(complainList)) {
            for (PropertyRightComplain complain : complainList) {
                IntellectualPropertyRightAppeal appeal = new IntellectualPropertyRightAppeal();
                appeal.setId(complain.getIntellectualPropertyRightAppealId());
                appeal.setStatus("3");
                appeal.setStatusDate(date);
                appeal.setUpdateDate(date);
                propertyRightAppealService.updateByPrimaryKeySelective(appeal);
            }
        }

        logger.info("此次任务更改了" + complainList.size() + "条记录");
    }
}
