package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.CommentCustom;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年6月29日 下午4:27:58 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Repository
public interface CommentCustomMapper {

	List<CommentCustom> getOrderCommentList(Map<String, Object> paramsMap);

	List<CommentCustom> getUserProductAllComment(Map<String, Object> paramsMap);

	List<CommentCustom> getShopAvgScore(Map<String, Object> paramsMap);

	List<CommentCustom> getProductAvgScore(Map<String, Object> paramsMap);

	List<Map<String, Object>> getTotalCountByMchtIdList(Map<String, Object> paramMap);

}
