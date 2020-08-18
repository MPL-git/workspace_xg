package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.utils.StringUtil;
import com.jf.dao.IntellectualPropertyRightAppealCustomMapper;
import com.jf.dao.IntellectualPropertyRightAppealMapper;
import com.jf.entity.IntellectualPropertyRightAppeal;
import com.jf.entity.IntellectualPropertyRightAppealCustom;
import com.jf.entity.IntellectualPropertyRightAppealCustomExample;
import com.jf.entity.IntellectualPropertyRightAppealExample;
import com.jf.entity.PropertyRightAppealPic;
import com.jf.entity.PropertyRightAppealPicExample;
import com.jf.entity.PropertyRightComplain;
import com.jf.entity.PropertyRightComplainPic;
import com.jf.entity.PropertyRightComplainPicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 知识侵权管理业务层
 * @Author: zhen.li
 * @Date: 2018/12/13 16:19
 */
@Service
@Transactional
public class PropertyRightAppealService extends BaseService<IntellectualPropertyRightAppeal, IntellectualPropertyRightAppealExample> {

    @Autowired
    private IntellectualPropertyRightAppealMapper mapper;

    @Autowired
    private IntellectualPropertyRightAppealCustomMapper customMapper;

    @Autowired
    private PropertyRightAppealPicService propertyRightAppealPicService;

    @Autowired
    private PropertyRightComplainPicService propertyRightComplainPicService;

    @Autowired
    private PropertyRightComplainService propertyRightComplainService;

    @Autowired
    public void setIntellectualPropertyRightAppealMapper(IntellectualPropertyRightAppealMapper propertyRightAppealMapper) {
        super.setDao(propertyRightAppealMapper);
        this.mapper = propertyRightAppealMapper;
    }

    public List<IntellectualPropertyRightAppealCustom> selectCustomByExample(IntellectualPropertyRightAppealCustomExample appealCustomExample) {
        return customMapper.selectByExample(appealCustomExample);
    }

    /**
     * 获取详情页数据
     *
     * @param id
     * @return
     */
    public Map<String, Object> getDetail(Integer id) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 投诉信息
        IntellectualPropertyRightAppealCustom rightAppealCustom = customMapper.selectByPrimaryKey(id);

        if (rightAppealCustom == null) {
            return null;
        }

        resultMap.put("rightAppealCustom", rightAppealCustom);

        // 举证材料
        PropertyRightAppealPicExample appealPicExample = new PropertyRightAppealPicExample();
        PropertyRightAppealPicExample.Criteria appealPicCriteria = appealPicExample.createCriteria();
        appealPicCriteria.andIntellectualPropertyRightAppealIdEqualTo(rightAppealCustom.getId());
        appealPicCriteria.andDelFlagEqualTo("0");
        List<PropertyRightAppealPic> appealPicList = propertyRightAppealPicService.selectByExample(appealPicExample);
        resultMap.put("appealPicList", appealPicList);

        // 申诉材料
        if (rightAppealCustom.getComplainId() != null) {
            PropertyRightComplainPicExample complainPicExample = new PropertyRightComplainPicExample();
            PropertyRightComplainPicExample.Criteria complainPicCriteria = complainPicExample.createCriteria();
            complainPicCriteria.andPropertyRightComplainIdEqualTo(rightAppealCustom.getComplainId());
            complainPicCriteria.andDelFlagEqualTo("0");
            List<PropertyRightComplainPic> complainPicList = propertyRightComplainPicService.selectByExample(complainPicExample);
            resultMap.put("complainPicList", complainPicList);
        }

        int showFlag = 0;
        if (rightAppealCustom.getComplainId() == null) {
            showFlag = rightAppealCustom.getComplainEndDate().after(new Date()) ? 1 : 0;
        } else {
            showFlag = "2".equals(rightAppealCustom.getComplainStatus()) && rightAppealCustom.getComplainEndDate().after(new Date()) ? 1 : 2;
        }
        resultMap.put("showFlag", showFlag);

        return resultMap;
    }

    /**
     * 提交材料
     *
     * @param appealCustom
     */
    public void saveComplain(IntellectualPropertyRightAppealCustom appealCustom) {
        if (appealCustom.getId() == null) {
            throw new ArgException("数据错误,无法进行该操作");
        }

        // 通过complainId判断是否新增或修改
        PropertyRightComplain propertyRightComplain = new PropertyRightComplain();
        if(appealCustom.getComplainId() == null) {
            propertyRightComplain.setIntellectualPropertyRightAppealId(appealCustom.getId());
            propertyRightComplain.setStatus("1");
            propertyRightComplain.setComplainReason(appealCustom.getComplainReason());
            propertyRightComplain.setCreateDate(new Date());
            propertyRightComplain.setCreateBy(appealCustom.getUpdateBy());
            propertyRightComplain.setDelFlag("0");
            propertyRightComplainService.insert(propertyRightComplain);
        } else {
            // 更新申诉理由
            propertyRightComplain.setId(appealCustom.getComplainId());
            propertyRightComplain.setStatus("1");
            propertyRightComplain.setComplainReason(appealCustom.getComplainReason());
            propertyRightComplain.setUpdateDate(new Date());
            propertyRightComplain.setUpdateBy(appealCustom.getUpdateBy());
            propertyRightComplainService.updateByPrimaryKeySelective(propertyRightComplain);
        }

        if(appealCustom.getComplainId() != null) {
            // 申诉材料先删再增
            propertyRightComplainPicService.delete(appealCustom.getComplainId(), appealCustom.getUpdateBy());
        }

        if(!StringUtil.isEmpty(appealCustom.getPicUrls())) {
            String picUrls = appealCustom.getPicUrls();
            String[] arr = picUrls.split(",");
            propertyRightComplainPicService.savePicList(arr, propertyRightComplain.getId(), appealCustom.getUpdateBy());
        }
    }
}
