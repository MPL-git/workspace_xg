package com.jf.dao;

import com.jf.entity.Product;
import com.jf.entity.SvipBindProduct;
import com.jf.entity.SvipBindProductCustom;
import com.jf.entity.SvipBindProductExample;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SvipBindProductCustomMapper extends BaseDao<SvipBindProduct,SvipBindProductExample>{
    int countSvipBindProductCustomByExample(Map<String, Object> paramMap);

    List<SvipBindProductCustom> selectSvipBindProductCustomByExample(Map<String, Object> paramMap);

    List<Integer> selectProductList(List<String> productCodeList);
}