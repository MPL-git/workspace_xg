package com.jf.common.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Menu implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	  private int menuID;
	  private String menuCode;
	  private String menuName;
	  private String menuDesc;
	  private String menuPath;
	  private int parentID;
	  private int menuOrder;
	  private String status;
	  private String isCheck;
	  private List<Menu> childs;

	  public String getIsCheck()
	  {
	    return this.isCheck;
	  }

	  public void setIsCheck(String isCheck) {
	    this.isCheck = isCheck;
	  }

	  public int getMenuID() {
	    return this.menuID;
	  }

	  public void setMenuID(int menuID) {
	    this.menuID = menuID;
	  }

	  public String getMenuCode() {
	    return this.menuCode;
	  }

	  public void setMenuCode(String menuCode) {
	    this.menuCode = menuCode;
	  }

	  public String getMenuName() {
	    return this.menuName;
	  }

	  public void setMenuName(String menuName) {
	    this.menuName = menuName;
	  }

	  public String getMenuDesc() {
	    return this.menuDesc;
	  }

	  public void setMenuDesc(String menuDesc) {
	    this.menuDesc = menuDesc;
	  }

	  public String getMenuPath() {
	    return this.menuPath;
	  }

	  public void setMenuPath(String menuPath) {
	    this.menuPath = menuPath;
	  }

	  public int getParentID() {
	    return this.parentID;
	  }

	  public void setParentID(int parentID) {
	    this.parentID = parentID;
	  }

	  public int getMenuOrder() {
	    return this.menuOrder;
	  }

	  public void setMenuOrder(int menuOrder) {
	    this.menuOrder = menuOrder;
	  }


	  public String getStatus() {
	    return this.status;
	  }

	  public void setStatus(String status) {
	    this.status = status;
	  }


	  public List<Menu> getChilds() {
	    return this.childs;
	  }

	  public void setChilds(List<Menu> childs) {
	    this.childs = childs;
	  }

	  public static List<Menu> buildTree(List<Menu> list, int parentID) {
	    List result = new ArrayList();
	    for (Menu menu : list) {
	      if (menu.getParentID() == parentID) {
	        List childs = buildTree(list, menu.getMenuID());
	        if ((childs != null) && (childs.size() != 0)) menu.setChilds(childs);
	        result.add(menu);
	      }
	    }
	    //实现比较器，也可以单独用一个类来实现  
        Comparator c = new Comparator(){  
            @Override  
            public int compare(Object arg0, Object arg1) { //这里实现按照用户年龄大小来排序  
            	Menu temp1 = (Menu) arg0;  
            	Menu temp2 = (Menu) arg1;  
            	return temp1.getMenuOrder() - temp1.getMenuOrder();  
            }  
        };  
	    if (result.size() > 1) {
	      Collections.sort(result, c);
	    }
	    return result;
	  }

	 
	  

	

}
