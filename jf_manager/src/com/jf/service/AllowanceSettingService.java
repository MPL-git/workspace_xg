package com.jf.service;

import com.jf.dao.AllowanceSettingCustomMapper;
import com.jf.dao.AllowanceSettingIntegralExchangeMapper;
import com.jf.dao.AllowanceSettingMapper;
import com.jf.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AllowanceSettingService extends BaseService<AllowanceSetting, AllowanceSettingExample> {
    @Autowired
    private AllowanceSettingMapper allowanceSettingMapper;

    @Autowired
    private AllowanceSettingCustomMapper allowanceSettingCustomMapper;

    @Autowired
    private AllowanceSettingIntegralExchangeMapper allowanceSettingIntegralExchangeMapper;

    @Autowired
    public void setAllowanceSettingMapper(AllowanceSettingMapper allowanceSettingMapper) {
        super.setDao(allowanceSettingMapper);
        this.allowanceSettingMapper = allowanceSettingMapper;
    }


    public int countAllowanceSettingList(Map<String, Object> paramMap) {
        return allowanceSettingCustomMapper.countAllowanceSettingList(paramMap);
    }

    public List<Map<String, Object>> selectAllowanceSettingList(Map<String, Object> paramMap) {
        return  allowanceSettingCustomMapper.selectAllowanceSettingList(paramMap);
    }

    public  Map<String, Object>  addOrEditAllowanceSetting(HttpServletRequest request, AllowanceSetting setting, StaffBean staffBean) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "操作成功");
        String code = null;
        String msg =null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String exchangeBeginDate=request.getParameter("exchangeBeginDate");
        String exchangeEndDate=request.getParameter("exchangeEndDate");
        String name = request.getParameter("name");
        String integral1 = request.getParameter("integral1");
        String integral2 = request.getParameter("integral2");
        String exchangeAmountMin1 = request.getParameter("exchangeAmountMin1");
        String exchangeAmountMin2 = request.getParameter("exchangeAmountMin2");
        String exchangeAmountMax1 = request.getParameter("exchangeAmountMax1");
        String exchangeAmountMax2 = request.getParameter("exchangeAmountMax2");
        String popupCount = request.getParameter("popupCount");
        String day = request.getParameter("day");
        String pic = request.getParameter("pic");
        int staffId=Integer.valueOf(staffBean.getStaffID());

        try {
        if (setting.getId()==null){
            AllowanceSetting allowanceSetting = new AllowanceSetting();
            BeanUtils.copyProperties(setting,allowanceSetting);
            allowanceSetting.setExchangeBeginDate(sdf.parse(exchangeBeginDate+":00"));
            allowanceSetting.setExchangeEndDate(sdf.parse(exchangeEndDate+":59"));
            if ("3".equals(popupCount)){
                allowanceSetting.setDay(Integer.parseInt(day));
            }
            allowanceSetting.setCreateBy(staffId);
            allowanceSetting.setCreateDate(new Date());
            allowanceSetting.setStatus("0");
            allowanceSetting.setDelFlag("0");
            allowanceSettingMapper.insertSelective(allowanceSetting);

            AllowanceSettingIntegralExchange exchange1 = new AllowanceSettingIntegralExchange();
            exchange1.setIntegral(Integer.parseInt(integral1));
            exchange1.setAllowanceId(allowanceSetting.getId());
            exchange1.setExchangeAmountMin(new BigDecimal(exchangeAmountMin1));
            exchange1.setExchangeAmountMax(new BigDecimal(exchangeAmountMax1));
            exchange1.setCreateBy(staffId);
            exchange1.setCreateDate(new Date());
            exchange1.setDelFlag("0");
            allowanceSettingIntegralExchangeMapper.insertSelective(exchange1);

            AllowanceSettingIntegralExchange exchange2 = new AllowanceSettingIntegralExchange();
            exchange2.setAllowanceId(allowanceSetting.getId());
            exchange2.setIntegral(Integer.parseInt(integral2));
            exchange2.setExchangeAmountMin(new BigDecimal(exchangeAmountMin2));
            exchange2.setExchangeAmountMax(new BigDecimal(exchangeAmountMax2));
            exchange2.setDelFlag("0");
            exchange2.setCreateBy(staffId);
            exchange2.setCreateDate(new Date());
            allowanceSettingIntegralExchangeMapper.insertSelective(exchange2);
        }else {//更新
            AllowanceSetting allowanceSetting = allowanceSettingMapper.selectByPrimaryKey(setting.getId());
            allowanceSetting.setName(setting.getName());
            allowanceSetting.setPopupCount(setting.getPopupCount());
            allowanceSetting.setPic(pic);
            allowanceSetting.setExchangeBeginDate(sdf.parse(exchangeBeginDate+":00"));
            allowanceSetting.setExchangeEndDate(sdf.parse(exchangeEndDate+":59"));
            allowanceSetting.setCreateBy(staffId);
            allowanceSetting.setCreateDate(new Date());
            if ("3".equals(popupCount)){
                allowanceSetting.setDay(Integer.parseInt(day));
            }
            allowanceSetting.setStatus("0");
            allowanceSetting.setDelFlag("0");
            allowanceSettingMapper.updateByPrimaryKeySelective(allowanceSetting);
            AllowanceSettingIntegralExchangeExample example = new AllowanceSettingIntegralExchangeExample();
            example.createCriteria().andDelFlagEqualTo("0").andAllowanceIdEqualTo(setting.getId());
            List<AllowanceSettingIntegralExchange> integralExchanges = allowanceSettingIntegralExchangeMapper.selectByExample(example);
            integralExchanges.get(0).setIntegral(Integer.parseInt(integral1));
            integralExchanges.get(0).setExchangeAmountMin(new BigDecimal(exchangeAmountMin1));
            integralExchanges.get(0).setExchangeAmountMax(new BigDecimal(exchangeAmountMax1));
            integralExchanges.get(0).setCreateBy(staffId);
            integralExchanges.get(0).setCreateDate(new Date());
            integralExchanges.get(0).setDelFlag("0");
            allowanceSettingIntegralExchangeMapper.updateByPrimaryKeySelective(integralExchanges.get(0));
            integralExchanges.get(1).setIntegral(Integer.parseInt(integral2));
            integralExchanges.get(1).setExchangeAmountMin(new BigDecimal(exchangeAmountMin2));
            integralExchanges.get(1).setExchangeAmountMax(new BigDecimal(exchangeAmountMax2));
            integralExchanges.get(1).setCreateBy(staffId);
            integralExchanges.get(1).setCreateDate(new Date());
            integralExchanges.get(1).setDelFlag("0");
            allowanceSettingIntegralExchangeMapper.updateByPrimaryKeySelective(integralExchanges.get(1));
        }
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "保存失败，请稍后重试");
            return resMap;
        }
        resMap.put("statusCode", code);
        resMap.put("message", msg);
        return resMap;

    }
}
