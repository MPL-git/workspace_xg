package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.SourceNicheProductCustom;
import com.jf.entity.SourceNicheProductExample;

@Repository
public interface SourceNicheProductCustomMapper {

		List<SourceNicheProductCustom> selectCustomByExample(SourceNicheProductExample  example);
}
