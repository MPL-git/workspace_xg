package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberFavorites;
import com.jf.entity.MemberFavoritesExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberFavoritesMapper extends BaseDao<MemberFavorites, MemberFavoritesExample> {
    int countByExample(MemberFavoritesExample example);

    int deleteByExample(MemberFavoritesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberFavorites record);

    int insertSelective(MemberFavorites record);

    List<MemberFavorites> selectByExample(MemberFavoritesExample example);

    MemberFavorites selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberFavorites record, @Param("example") MemberFavoritesExample example);

    int updateByExample(@Param("record") MemberFavorites record, @Param("example") MemberFavoritesExample example);

    int updateByPrimaryKeySelective(MemberFavorites record);

    int updateByPrimaryKey(MemberFavorites record);
}
