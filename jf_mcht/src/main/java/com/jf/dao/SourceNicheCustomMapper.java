package com.jf.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.jf.entity.SourceNicheCustom;
import com.jf.entity.SourceNicheCustomExample;

@Repository
public interface SourceNicheCustomMapper {

	List<SourceNicheCustom> selectByCustomExample(
			SourceNicheCustomExample sourceNicheCustomExample);

	int selectCountByCustomExample(
			SourceNicheCustomExample sourceNicheCustomExample);
}
