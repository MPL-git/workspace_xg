package com.jf.dao;

import com.jf.entity.CutPriceOrderDtl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CutPriceOrderDtlCustomMapper{

	List<CutPriceOrderDtl> getMemberInviteOrder(Map<String, Object> paramsMap);

	int updateInviteOrderHelpSuccess(CutPriceOrderDtl orderDtl);

}