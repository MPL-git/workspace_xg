package com.jf.service;

import com.jf.common.utils.DateUtil;
import com.jf.dao.ColumnPvStatisticalCustomMapper;
import com.jf.dao.ColumnPvStatisticalMapper;
import com.jf.entity.ColumnPvStatistical;
import com.jf.entity.ColumnPvStatisticalCustom;
import com.jf.entity.ColumnPvStatisticalCustomExample;
import com.jf.entity.ColumnPvStatisticalExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Pengl
 * @create 2019-10-08 下午 4:05
 */
@Service
@Transactional
public class ColumnPvStatisticalService extends BaseService<ColumnPvStatistical, ColumnPvStatisticalExample> {

    @Autowired
    private ColumnPvStatisticalMapper columnPvStatisticalMapper;

    @Autowired
    private ColumnPvStatisticalCustomMapper columnPvStatisticalCustomMapper;

    @Autowired
    public void setColumnPvStatisticalMapper(ColumnPvStatisticalMapper columnPvStatisticalMapper) {
        super.setDao(columnPvStatisticalMapper);
        this.columnPvStatisticalMapper = columnPvStatisticalMapper;
    }

    public List<Map<String, Object>> selectColumnPvStatisticalDayList(Map<String, Object> paramMap) {
        List<Map<String, Object>> columnPvStatisticalDayList = columnPvStatisticalCustomMapper.selectColumnPvStatisticalDayList(paramMap);
        return columnPvStatisticalDayList;
    }

    public Map<String, Object> selectColumnPvStatisticalDaySum(Map<String, Object> paramMap) {
        return columnPvStatisticalCustomMapper.selectColumnPvStatisticalDaySum(paramMap);
    }

    public List<Map<String, Object>> selectColumnPvStatisticalWeekList(Map<String, Object> paramMap) {
        String statisticalDateStart = paramMap.get("statisticalDateStart").toString();
        String statisticalDateEnd = paramMap.get("statisticalDateEnd").toString();
		List<Map<String, Object>> dataList = new ArrayList<>();
        int count = 1;
        for(;;) {
            paramMap.put("statisticalDateStart", DateUtil.formatDateByFormat(DateUtil.getDateAfter(DateUtil.StringToDate(statisticalDateStart), (count-1)*7), "yyyy-MM-dd"));
            Date dateEnd = DateUtil.getDateAfter(DateUtil.StringToDate(statisticalDateStart), count*7-1);
            if(DateUtil.StringToDate(statisticalDateEnd).compareTo(dateEnd) < 1 ) {
                paramMap.put("statisticalDateEnd", statisticalDateEnd);
                dataList.add(columnPvStatisticalCustomMapper.selectColumnPvStatisticalWeekList(paramMap));
				break;
            }else {
                paramMap.put("statisticalDateEnd", DateUtil.formatDateByFormat(dateEnd, "yyyy-MM-dd"));
				dataList.add(columnPvStatisticalCustomMapper.selectColumnPvStatisticalWeekList(paramMap));
            }
            count++;
        }
        return dataList;
    }

    public List<Map<String, Object>> selectColumnPvStatisticalMonthList(Map<String, Object> paramMap) {
        return columnPvStatisticalCustomMapper.selectColumnPvStatisticalMonthList(paramMap);
    }

    public List<Map<String, Object>> selectColumnPvStatisticalColumnTypeList(Map<String, Object> paramMap) {
        List<Map<String, Object>> columnPvStatisticalColumnTypeList = columnPvStatisticalCustomMapper.selectColumnPvStatisticalColumnTypeList(paramMap);
        return columnPvStatisticalColumnTypeList;
    }

    public Map<String, Object> selectColumnPvStatisticalColumnTypeSum(Map<String, Object> paramMap) {
        return columnPvStatisticalCustomMapper.selectColumnPvStatisticalColumnTypeSum(paramMap);
    }

    public  List<ColumnPvStatisticalCustom> selectByCustomExample(ColumnPvStatisticalCustomExample example){
      return  columnPvStatisticalCustomMapper.selectByCustomExample(example);
    };
}
