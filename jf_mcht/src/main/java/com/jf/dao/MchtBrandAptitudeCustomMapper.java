package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtBrandAptitudeCustom;
import com.jf.entity.MchtBrandAptitudeExample;
@Repository
public interface MchtBrandAptitudeCustomMapper{
	public List<MchtBrandAptitudeCustom>selectByExample(MchtBrandAptitudeExample mchtBrandAptitudeExample);

	public List<Integer> getIdsByMchtProductBrandId(Integer mchtProductBrandId);
}