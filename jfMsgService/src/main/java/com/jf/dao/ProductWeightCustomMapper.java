package com.jf.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;
@Repository
public interface ProductWeightCustomMapper{
 int insertProductWeights();
 int updateProductSeasonWeights(int currentMonth);
 int updateProductMchtGradeWeights();
 int updateProductBrandGradeWeights();
 List<HashMap<String, Object>> selectProductSaleCount(HashMap<String, Object> paramMap);
 List<HashMap<String, Object>> selectProductSaleAmount(HashMap<String, Object> paramMap);
 List<HashMap<String, Object>> selectProductPvCount(HashMap<String, Object> paramMap);
 int updateProductCommentWeight();
 int updateProductWeightsTotal();
}