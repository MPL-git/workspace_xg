package com.jf.common.ext.query;


import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;


public class QueryObject implements Serializable {

	private static final long serialVersionUID = 1;

	public static final int SORT_ASC = 1;
	public static final int SORT_DESC = 2;
	public static final String INCLUDE_DELETE = "include_deleted";	//查询包含删除的记录

	private int pageNumber = 1;	//页码

	private int pageSize = 20;	//每页几条

	private int limitSize = 0;

	private Map<String, Object> queryParam;

	private LinkedHashMap<String,Integer> sort;

	public QueryObject(int pageNumber, int pageSize) {
		this.queryParam = new HashMap<String, Object>();
		this.sort = new LinkedHashMap<String,Integer>();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	public QueryObject() {
		this.queryParam = new HashMap<String, Object>();
		this.sort = new LinkedHashMap<String,Integer>();
	}

	public QueryObject addQuery(String name, Object value) {
		queryParam.put(name, value);
		return this;
	}

	public <T> T getQuery(String name) {
		Object result = queryParam.get(name);
		return (result != null ? (T)result : null);
	}

	public String getQueryToStr(String name) {
		Object result = queryParam.get(name);
		return (result != null ? result.toString() : null);
	}

	public Integer getQueryToInt(String name) {
		Object result = queryParam.get(name);
		return (result != null ? (Integer) result : null);
	}

	public Date getQueryToDate(String name) {
		Object result = queryParam.get(name);
		return (result != null ? (Date)result : null);
	}

	public QueryObject removeQuery(String column) {
		queryParam.remove(column);
		return this;
	}

	public QueryObject addSort(String name, int sortType) {
		sort.put(name, sortType);
		return this;
	}

	public QueryObject removeSort(String name) {
		sort.remove(name);
		return this;
	}

	public LinkedHashMap<String, Integer> getSort() {
		return sort;
	}

	/**
	 * 获取排序子句，形如：
	 * `id` ASC, `age` DESC
	 *
	 * @return String
	 */
	public String getSortString() {
		Set<String> columns = sort.keySet();
		if (columns.size() == 0) {
			return "";
		}
		StringBuilder sortString = new StringBuilder();
		Iterator<String> names = columns.iterator();
		while (names.hasNext()) {
			String name = names.next();
			sortString.append(name);
			if (sort.get(name) == QueryObject.SORT_ASC) {
				sortString.append(" ASC,");
			} else {
				sortString.append(" DESC,");
			}
		}
		return sortString.substring(0, sortString.length() - 1);
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getLimitSize() {
		return limitSize;
	}

	public void setLimitSize(int limitSize) {
		this.limitSize = limitSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getLimitStart() {
		return (pageNumber-1)*pageSize;
	}

	public int getTotalPage(int totalCount) {
		return totalCount % pageSize == 0 ? (totalCount / pageSize) : (totalCount / pageSize) + 1;
	}

	public boolean hasQuery(){
		return queryParam.size() > 0;
	}
	
	public QueryObject addExample(Object value) {
		queryParam.put("example", value);
		return this;
	}

	public <T> T getExample() {
		Object result = queryParam.get("example");
		return (result != null ? (T)result : null);
	}

	@Override
	public String toString() {
		return "QueryObject [queryParam=" + JSONObject.toJSONString(queryParam) + "]";
	}
	
	
}
