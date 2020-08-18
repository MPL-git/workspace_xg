package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.IntellectualPropertyRightAppealCustomMapper;
import com.jf.dao.IntellectualPropertyRightAppealMapper;
import com.jf.entity.IntellectualPropertyRightAppeal;
import com.jf.entity.IntellectualPropertyRightAppealExample;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Description: 知识产权投诉业务层
 * @Author: zhen.li
 * @Date: 2018/12/26 14:32
 */
@Service
@Transactional
public class PropertyRightAppealService extends BaseService<IntellectualPropertyRightAppeal, IntellectualPropertyRightAppealExample> {

    private static Logger logger = LoggerFactory.getLogger(PropertyRightAppealService.class);

    @Autowired
    private IntellectualPropertyRightAppealMapper mapper;

    @Autowired
    private IntellectualPropertyRightAppealCustomMapper customMapper;

    @Autowired
    public void setMapper(IntellectualPropertyRightAppealMapper mapper) {
        this.setDao(mapper);
        this.mapper = mapper;
    }

    /**
     * 更新超期未声明投诉单的主状态为完成
     *
     */
    public void updateOverComplainDueStatus() {
        Date date = new Date();
        IntellectualPropertyRightAppealExample appealExample = new IntellectualPropertyRightAppealExample();
        IntellectualPropertyRightAppealExample.Criteria criteria = appealExample.createCriteria();
        criteria.andAcceptStatusEqualTo("1");
        criteria.andStatusEqualTo("1");
        criteria.andComplainEndDateLessThan(date);
        criteria.andDelFlagEqualTo("0");

        List<IntellectualPropertyRightAppeal> appealList = customMapper.selectOverDueByExample(appealExample);
        if (CollectionUtils.isNotEmpty(appealList)) {
            for (IntellectualPropertyRightAppeal appeal : appealList) {
                appeal.setStatus("2");
                appeal.setStatusDate(date);
                appeal.setUpdateDate(date);
                this.mapper.updateByPrimaryKeySelective(appeal);
            }
        }
        logger.info("此次任务更改了" + appealList.size() + "条记录");
    }
}
