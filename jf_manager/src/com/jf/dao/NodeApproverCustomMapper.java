package com.jf.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.NodeApproverCustom;
import com.jf.entity.NodeApproverCustomExample;

@Repository
public interface NodeApproverCustomMapper {

	NodeApproverCustom selectCustomByPrimaryKey(Integer id);
    int countCustomByExample(NodeApproverCustomExample example);
    List<NodeApproverCustom> selectCustomByExample(NodeApproverCustomExample example);

}
