package com.jf.common.base;

import java.util.List;


/**
 * 提供基础的crud、分页查询等服务
 * 
 * @author ghq
 *
 * @param <T>
 */
public abstract class BaseService<T,E> {
	
	protected BaseDao<T,E> dao;
	public void setDao(BaseDao<T,E> dao) {
		this.dao = dao;
	}

	public int countByExample(E example){
		return dao.countByExample(example);
	}

    public int deleteByExample(E example){
    	return dao.deleteByExample(example);
    }

    public int deleteByPrimaryKey(Integer id){
    	return dao.deleteByPrimaryKey(id);
    }

    public int insert(T record){
    	return dao.insert(record);
    }

    public int insertSelective(T record){
    	return dao.insertSelective(record);
    }

    public List<T> selectByExample(E example){
    	return dao.selectByExample(example);
    }

    public T selectByPrimaryKey(Integer id){
    	return dao.selectByPrimaryKey(id);
    }

    public int updateByExampleSelective(T record,E example){
    	return dao.updateByExampleSelective(record, example);
    }

    public int updateByExample(T record,E example){
    	return dao.updateByExample(record, example);
    }

    public int updateByPrimaryKeySelective(T record){
    	return dao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(T record){
    	return dao.updateByPrimaryKey(record);
    }
    
    
}
