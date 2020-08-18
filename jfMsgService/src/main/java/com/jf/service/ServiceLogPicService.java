package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ServiceLogPicMapper;
import com.jf.entity.ServiceLogPic;
import com.jf.entity.ServiceLogPicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class ServiceLogPicService extends BaseService<ServiceLogPic, ServiceLogPicExample> {

    @Autowired
    private ServiceLogPicMapper dao;


    @Autowired
    public void setServiceLogPicMapper(ServiceLogPicMapper serviceLogPicCustomMapper) {
        super.setDao(serviceLogPicCustomMapper);
        this.dao = serviceLogPicCustomMapper;
    }

    /**
     * 批量新增售后单图片
     *
     * @param pics
     * @param date
     * @param createBy
     * @param serviceLogId
     */
    public void insertSelective(String pics, Date date, Integer createBy, Integer serviceLogId) {
        if (!StringUtil.isEmpty(pics)) {
            String[] imgArray = pics.split("\\|");
            for (int i = 1; i < imgArray.length; i++) {
                ServiceLogPic serviceLogPic = new ServiceLogPic();
                serviceLogPic.setCreateDate(date);
                serviceLogPic.setUpdateDate(date);
                serviceLogPic.setCreateBy(createBy);
                serviceLogPic.setDelFlag("0");
                serviceLogPic.setPic(imgArray[i]);
                serviceLogPic.setServiceLogId(serviceLogId);
                dao.insertSelective(serviceLogPic);
            }
        }
    }

}
