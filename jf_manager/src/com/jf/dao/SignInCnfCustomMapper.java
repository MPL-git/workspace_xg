package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.SignInCnf;
import com.jf.entity.SignInCnfCustom;
import com.jf.entity.SignInCnfCustomExample;

@Repository
public interface SignInCnfCustomMapper {
    public int countByCustomExample(SignInCnfCustomExample example);

    public List<SignInCnfCustom> selectByCustomExample(SignInCnfCustomExample example);

    public SignInCnfCustom selectByPrimaryKeyCustom(Integer id);

    public int updateByCustomExampleSelective(@Param("record") SignInCnf record, @Param("example") SignInCnfCustomExample example);

    /**
     * 
     * @Title selectSignInCnfStatisticsList   
     * @Description TODO(现金签到数据统计)   
     * @author pengl
     * @date 2018年6月13日 上午11:08:31
     */
    public Map<String, Object> selectSignInCnfStatisticsList(Map<String, String> mapParam);
    
    /**
     * 
     * @Title selectMemberSum   
     * @Description TODO(总提现人数)   
     * @author pengl
     * @date 2018年7月9日 下午4:12:52
     */
    public Integer selectMemberCount();
    
    /**
     * 
     * @Title selectAmountSum   
     * @Description TODO(已提现金额)   
     * @author pengl
     * @date 2018年7月9日 下午4:22:01
     */
    public String selectAmountSum(List<String> bizTypeList);
    
    
}