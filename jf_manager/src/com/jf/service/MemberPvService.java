package com.jf.service;

import com.jf.dao.MemberPvCustomMapper;
import com.jf.entity.MemberPvExample;
import com.jf.entity.ProductDataStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MemberPvService extends BaseService<MemberPvService, MemberPvExample> {

    @Autowired
    private MemberPvCustomMapper memberPvCustomMapper;

    public List<ProductDataStatistics> getCategoryProductDataStatisticsList(HashMap<String, Object> paramMap) {
        return  memberPvCustomMapper.getCategoryProductDataStatisticsList(paramMap);
    }

    public List<ProductDataStatistics> getHourProductDataStatisticsList(HashMap<String, Object> paramMap) {
        return  memberPvCustomMapper.getHourProductDataStatisticsList(paramMap);
    }

    public List<ProductDataStatistics> getCommodityFlowReportDataStatisticsList(HashMap<String, Object> paramMap) {
        return  memberPvCustomMapper.getHourProductDataStatisticsList(paramMap);
    }

    public List<ProductDataStatistics> getNowHourProductDataStatisticsList(HashMap<String, Object> paramMap) {
        return  memberPvCustomMapper.getNowHourProductDataStatisticsList(paramMap);
    }

    public List<ProductDataStatistics> getNowHourMchtDataStatisticsList(HashMap<String, Object> paramMap) {
        return  memberPvCustomMapper.getNowHourMchtDataStatisticsList(paramMap);
    }

}
