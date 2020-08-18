package com.jf.service;

import com.jf.dao.ConfigSpecialMchtCustomMapper;
import com.jf.dao.ConfigSpecialMchtMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import com.jf.entity.ConfigSpecialMcht;
import com.jf.entity.ConfigSpecialMchtExample;
@Service
@Transactional
public class ConfigSpecialMchtService extends BaseService<ConfigSpecialMcht, ConfigSpecialMchtExample> {

    @Autowired
    private ConfigSpecialMchtMapper configSpecialMchtMapper;

    @Autowired
    private ConfigSpecialMchtCustomMapper configSpecialMchtCustomMapper;

    @Autowired
    public void setConfigSpecialMchtMapper(ConfigSpecialMchtMapper configSpecialMchtMapper) {
        super.setDao(configSpecialMchtMapper);
        this.configSpecialMchtMapper = configSpecialMchtMapper;
    }

    /**
     * 根据商家序号查询信息
     * @param mchtCode
     * @return
     */
    public Map<String, Object> selectByMchtCode(String mchtCode){
        return configSpecialMchtCustomMapper.selectByMchtCode(mchtCode);
    }

    public List<Map<String, Object>> selectSpecialMchtList(Map<String, Object> parameMap){
        return configSpecialMchtCustomMapper.selectSpecialMchtList(parameMap);
    }

    public  List<Map<String, Object>> selectNotSpecialMchtList(Map<String, Object> parameMap){
        return configSpecialMchtCustomMapper.selectNotSpecialMchtList(parameMap);
    };

    public  int selectSpecialMchtListCount(Map<String, Object> parameMap){
        return configSpecialMchtCustomMapper.selectSpecialMchtListCount(parameMap);
    };

    public  int selectNotSpecialMchtListCount(Map<String, Object> parameMap){
        return configSpecialMchtCustomMapper.selectNotSpecialMchtListCount(parameMap);
    };

}
