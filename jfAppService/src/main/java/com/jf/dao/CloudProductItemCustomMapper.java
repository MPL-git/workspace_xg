package com.jf.dao;

import com.jf.entity.CloudProductItemCustom;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CloudProductItemCustomMapper{

	List<CloudProductItemCustom> getCloudProducrItems(Integer cloudProductItemId);
   
}