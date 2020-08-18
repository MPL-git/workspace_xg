package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ViolatePlatformRemarksPicCustom;
import com.jf.entity.ViolatePlatformRemarksPicExample;
@Repository
public interface ViolatePlatformRemarksPicCustomMapper{
	public List<ViolatePlatformRemarksPicCustom>selectByExample(ViolatePlatformRemarksPicExample violatePlatformRemarksPicExample);
}