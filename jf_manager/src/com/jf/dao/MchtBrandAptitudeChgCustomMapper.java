package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtBrandAptitudeChgCustom;
import com.jf.entity.MchtBrandAptitudeChgExample;
@Repository
public interface MchtBrandAptitudeChgCustomMapper{
	public List<MchtBrandAptitudeChgCustom>selectByExample(MchtBrandAptitudeChgExample mchtBrandAptitudeChgExample);

	public List<Integer> getIdsByMchtBrandChgId(Integer mchtBrandChgId);
}