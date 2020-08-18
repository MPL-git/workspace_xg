package com.jf.service;

import com.gzs.common.utils.StringUtil;
import com.jf.dao.AndroidChannelGroupSetDtlMapper;
import com.jf.dao.AndroidChannelGroupSetMapper;
import com.jf.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pengl
 * @create 2019-10-18 下午 1:42
 */

@Service
@Transactional
public class AndroidChannelGroupSetService extends BaseService<AndroidChannelGroupSet, AndroidChannelGroupSetExample> {

    @Autowired
    public AndroidChannelGroupSetMapper androidChannelGroupSetMapper;

    @Autowired
    public AndroidChannelGroupSetDtlMapper androidChannelGroupSetDtlMapper;

    @Autowired
    public void setAndroidChannelGroupSetMapper(AndroidChannelGroupSetMapper androidChannelGroupSetMapper) {
        super.setDao(androidChannelGroupSetMapper);
        this.androidChannelGroupSetMapper = androidChannelGroupSetMapper;
    }

    public void editAndroidChannelGroupSet(AndroidChannelGroupSet androidChannelGroupSet, String androidChannelGroupIds) {
        String[] groupIds = null;
        if(!StringUtil.isEmpty(androidChannelGroupIds) ) {
            groupIds = androidChannelGroupIds.split(",");
        }
        List<Integer> groupIdList = new ArrayList<Integer>();
        for(String groupId : groupIds ) {
            groupIdList.add(Integer.parseInt(groupId));
        }
        if(androidChannelGroupSet.getId() == null ) {
            androidChannelGroupSetMapper.insertSelective(androidChannelGroupSet);
            for(String groupId : groupIds ) {
                AndroidChannelGroupSetDtl androidChannelGroupSetDtl = new AndroidChannelGroupSetDtl();
                androidChannelGroupSetDtl.setAndroidChannelGroupSetId(androidChannelGroupSet.getId());
                androidChannelGroupSetDtl.setAndroidChannelGroupId(Integer.parseInt(groupId));
                androidChannelGroupSetDtl.setCreateBy(androidChannelGroupSet.getCreateBy());
                androidChannelGroupSetDtl.setCreateDate(androidChannelGroupSet.getCreateDate());
                androidChannelGroupSetDtlMapper.insertSelective(androidChannelGroupSetDtl);
            }
        }else {
            androidChannelGroupSetMapper.updateByPrimaryKeySelective(androidChannelGroupSet);
            AndroidChannelGroupSetDtlExample androidChannelGroupSetDtlExample = new AndroidChannelGroupSetDtlExample();
            AndroidChannelGroupSetDtlExample.Criteria androidChannelGroupSetDtlCriteria = androidChannelGroupSetDtlExample.createCriteria();
            androidChannelGroupSetDtlCriteria.andDelFlagEqualTo("0")
                .andAndroidChannelGroupSetIdEqualTo(androidChannelGroupSet.getId())
                .andAndroidChannelGroupSetIdNotIn(groupIdList);
            AndroidChannelGroupSetDtl androidChannelGroupSetDtl = new AndroidChannelGroupSetDtl();
            androidChannelGroupSetDtl.setUpdateBy(androidChannelGroupSet.getUpdateBy());
            androidChannelGroupSetDtl.setUpdateDate(androidChannelGroupSet.getUpdateDate());
            androidChannelGroupSetDtl.setDelFlag("1");
            androidChannelGroupSetDtlMapper.updateByExampleSelective(androidChannelGroupSetDtl, androidChannelGroupSetDtlExample);

            AndroidChannelGroupSetDtlExample acgsdExample = new AndroidChannelGroupSetDtlExample();
            AndroidChannelGroupSetDtlExample.Criteria acgsdCriteria = acgsdExample.createCriteria();
            acgsdCriteria.andDelFlagEqualTo("0")
                    .andAndroidChannelGroupSetIdEqualTo(androidChannelGroupSet.getId())
                    .andAndroidChannelGroupIdIn(groupIdList);
            List<AndroidChannelGroupSetDtl> androidChannelGroupSetDtlList = androidChannelGroupSetDtlMapper.selectByExample(acgsdExample);
            for(AndroidChannelGroupSetDtl acgsd : androidChannelGroupSetDtlList ) {
                groupIdList.remove(acgsd.getAndroidChannelGroupId());
            }
            for(Integer groupId : groupIdList ) {
                AndroidChannelGroupSetDtl aChannelGroupSetDtl = new AndroidChannelGroupSetDtl();
                aChannelGroupSetDtl.setAndroidChannelGroupSetId(androidChannelGroupSet.getId());
                aChannelGroupSetDtl.setAndroidChannelGroupId(groupId);
                aChannelGroupSetDtl.setCreateBy(androidChannelGroupSet.getUpdateBy());
                aChannelGroupSetDtl.setCreateDate(androidChannelGroupSet.getUpdateDate());
                androidChannelGroupSetDtlMapper.insertSelective(aChannelGroupSetDtl);
            }
        }
    }


}
