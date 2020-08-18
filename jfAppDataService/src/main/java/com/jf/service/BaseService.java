package com.jf.service;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jf.dao.BaseDao;



/**
 * 提供基础的crud、分页查询等服务
 * 
 * @author ghq
 *
 * @param <T>
 * @param <E>
 */
public abstract class BaseService<T,E,D extends BaseDao<T,E>> {
	
	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;
	
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
    
    public int save(T record){
    	Class clazz = record.getClass();
        Method getId;
        int count=0;
		try {
			getId = clazz.getDeclaredMethod("getId");
			Integer id=(Integer)getId.invoke(record);
			if(id==null){
				count=dao.insertSelective(record);
			}else{
				count=dao.updateByPrimaryKeySelective(record);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
      return count;
    }

    public int deleteLogic(T record){

        int count=0;
		try {
	    	Class clazz = record.getClass();
			T record4Update=(T)clazz.newInstance();
			Method getId = clazz.getDeclaredMethod("getId");
			Integer id=(Integer)getId.invoke(record);
			Method setDelFlag = clazz.getDeclaredMethod("setDelFlag",String.class);
			Method setId = clazz.getDeclaredMethod("setId",Integer.class);
			setId.invoke(record4Update,id);
			setDelFlag.invoke(record4Update,"1");
			count= dao.updateByPrimaryKeySelective(record4Update);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return count;
    
    }
}
