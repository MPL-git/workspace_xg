package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.CloudProductItemCustom;
@Repository
public interface CloudProductItemCustomMapper{

	List<CloudProductItemCustom> getCloudProducrItems(Integer cloudProductItemId);
   
}