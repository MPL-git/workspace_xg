package com.jf.common.utils;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
public class ObjectTransform {

	 public static final String SELECT_TEXT = "TEXT";
	  public static final String SELECT_VALUE = "VALUE";
	  public static final String TREE_ID = "ID";
	  public static final String TREE_TEXT = "TEXT";
	  public static final String TREE_CHILDREN = "CHILDREN";

	  public static Map<String, Object> transformMap(Object object)
	  {
	    return transformMap(object, new String[] { "" });
	  }

	  public static Map<String, Object> transformMap(Object object, String[] keys)
	  {
	    Map filter = new HashMap();
	    for (String key : keys) filter.put(key, key);
	    filter.put("serialVersionUID", "serialVersionUID");

	    Map result = new HashMap();
	    Field[] fields = object.getClass().getDeclaredFields();
	    for (Field field : fields) {
	      try {
	        String fieldName = field.getName();
	        if (!filter.containsKey(fieldName)) {
	          field.setAccessible(true);
	          Object value = field.get(object);
	          if ((value instanceof Date))
	            result.put(StringUtil.getUnderlineName(fieldName), DateUtil.getStandardDateTime((Date)value));
	          else
	            result.put(StringUtil.getUnderlineName(fieldName), value);
	        }
	      }
	      catch (IllegalArgumentException e) {
	        e.printStackTrace();
	      } catch (IllegalAccessException e) {
	        e.printStackTrace();
	      }
	    }
	    return result;
	  }

	  public static List<Map<String, Object>> transformSelect(List<?> list, String textField, String valueField) {
	    List ret = new ArrayList();
	    for (Iterator i$ = list.iterator(); i$.hasNext(); ) { Object o = i$.next();
	      Map map = new HashMap();
	      map.put("TEXT", getFieldValue(o, textField));
	      map.put("VALUE", getFieldValue(o, valueField));
	      ret.add(map);
	    }
	    return ret;
	  }

	  public static Object getFieldValue(Object object, String fieldName)
	  {
	    if ((object instanceof Map)) {
	      Map map = (Map)object;
	      return map.get(fieldName);
	    }
	    Method method = getGetMethod(object.getClass(), fieldName);
	    if (method != null) {
	      try {
	        return method.invoke(object, new Object[0]);
	      } catch (IllegalArgumentException e) {
	        e.printStackTrace();
	      } catch (IllegalAccessException e) {
	        e.printStackTrace();
	      } catch (InvocationTargetException e) {
	        e.printStackTrace();
	      }
	    }

	    return null;
	  }
	  private static Method getGetMethod(Class<?> cls, String fieldName) {
	    StringBuffer sb = new StringBuffer();
	    sb.append("get");
	    sb.append(fieldName.substring(0, 1).toUpperCase());
	    sb.append(fieldName.substring(1));
	    try {
	      return cls.getMethod(sb.toString(), new Class[0]);
	    } catch (SecurityException e) {
	      e.printStackTrace();
	    } catch (NoSuchMethodException e) {
	      e.printStackTrace();
	    }
	    return null;
	  }

	  public static void main(String[] args) {
	    Menu menu = new Menu();
	    menu.setMenuID(10);
	    menu.setMenuName("SS");
	    System.out.println(transformMap(menu, new String[] { "menuID" }));
	  }
}
