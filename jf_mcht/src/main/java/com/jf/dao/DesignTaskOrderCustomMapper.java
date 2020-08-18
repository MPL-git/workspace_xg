package com.jf.dao;

import com.jf.entity.DesignTaskOrder;
import com.jf.entity.DesignTaskOrderCustom;
import com.jf.entity.DesignTaskOrderCustomExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Pengl
 * @create 2020-03-16 下午 2:45
 */
@Repository
public interface DesignTaskOrderCustomMapper {

	public int countByCustomExample(DesignTaskOrderCustomExample example);

	public List<DesignTaskOrderCustom> selectByCustomExample(DesignTaskOrderCustomExample example);

	public DesignTaskOrderCustom selectByCustomPrimaryKey(Integer id);

	public int updateByCustomExampleSelective(@Param("record") DesignTaskOrder record, @Param("example") DesignTaskOrderCustomExample example);

}
