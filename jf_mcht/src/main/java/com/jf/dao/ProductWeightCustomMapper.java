package com.jf.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;
@Repository
public interface ProductWeightCustomMapper{
 int selectProductSaleCount(HashMap<String, Object> paramMap);
 int selectProductPvCount(HashMap<String, Object> paramMap);
}