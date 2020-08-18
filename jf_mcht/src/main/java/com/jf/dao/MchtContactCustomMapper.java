package com.jf.dao;

import java.util.List;

import com.jf.entity.MchtContact;
import org.springframework.stereotype.Repository;

import com.jf.entity.MchtContactCustom;
import com.jf.entity.MchtContactExample;
@Repository
public interface MchtContactCustomMapper{
    int countByExample(MchtContactExample example);

    List<MchtContactCustom> selectByExample(MchtContactExample example);

    MchtContactCustom selectByPrimaryKey(Integer id);

    List<MchtContactCustom> selectCustomByExample(MchtContactExample mchtContactExample);
}