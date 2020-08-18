package com.jf.dao;

import java.util.List;
import java.util.Map;

import com.jf.entity.CutPriceOrderDtlExample;
import org.springframework.stereotype.Repository;

import com.jf.entity.CutPriceOrderDtl;
import com.jf.entity.CutPriceOrderDtlCustom;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface CutPriceOrderDtlCustomMapper{

	List<CutPriceOrderDtlCustom> getMemberCutOrderDtlList(Map<String, Object> paramsMap);

	int getAlreadyInvitedCount(Map<String, Object> map);

	List<CutPriceOrderDtlCustom> getInviteDetail(Map<String, Object> paramsMap);

	List<CutPriceOrderDtl> getCutPriceOrderDtlModelByOrderId(Integer cutOrderId);

	List<CutPriceOrderDtl> findMemberAssistanceLog(@RequestParam("memberId") Integer memberId);
}