package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.PlatformMsgCustom;
import com.jf.entity.PlatformMsgExample;
/**
 * 站内信息扩展
 * @author luoyl
 * 创建时间：2017-3-27：18:06
 */
@Repository
public interface PlatformMsgCustomMapper{
	/**
	 * 总条数
	 * @param example
	 * @return
	 */
    int countByExample(PlatformMsgExample example);
    /**
     * 列表
     * @param example
     * @return
     */
    List<PlatformMsgCustom> selectByExample(PlatformMsgExample example);
    /**
     * 详情信息
     * @param id
     * @return
     */
    PlatformMsgCustom selectByPrimaryKey(Integer id);
}