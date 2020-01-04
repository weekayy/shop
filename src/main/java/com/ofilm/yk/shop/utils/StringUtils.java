package com.ofilm.yk.shop.utils;

import java.util.Collection;
import java.util.Map;

/**
 * 
 * @author NF1675
 *	创建字符串工具类
 */
public class StringUtils extends org.apache.commons.lang.StringUtils{
	/**
	 * 	空字符串
	 */
	private static final String NULLSTR ="";
	
	/**
	 * 	下划线
	 */
	private static final char SEPARATOR ='_';
	
	/**
	 * 	获取参数不为空值
	 * @param <T>
	 * @param value 返回值
	 * @param defaultValue 要判断的值
	 * @return
	 */
	public static<T> T Nvl(T value,T defaultValue) {
		
		return value != null?value:defaultValue;
		
	}
	
	/**
	 * 	判断一个集合是否为空 list set Queue
	 * @param collection
	 * @return
	 */
	public static boolean isEmpty(Collection<?> collection) {
		
		return isNull(collection) || collection.isEmpty();
		
	}
	
	/**
	 * 	判断一个集合是否为非空
	 * @param collection
	 * @return
	 */
	public static boolean isNotEmpty(Collection<?> collection) {
		
		return !isEmpty(collection);
		
	}
	
	/**
	 * 	判断一个数组是否为空
	 * @param objects
	 * @return
	 */
	public static boolean isEmpty(Object[] objects) {
		
		return isNull(objects) || (objects.length == 0);
	}
	
	/**
	 * 	判断一个数组是否非空
	 * @param objects
	 * @return
	 */
	public static boolean isNotEmpty(Object[] objects) {
		
		return !isEmpty(objects);
	}
	
	/**
	 * 	判断一个map是否为空
	 * @param map
	 * @return
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		
		return isNull(map) || map.isEmpty();
	}
	/**
	 * 	判断一个map是否为非空
	 * @param map
	 * @return
	 */
	public static boolean isNotEmpty(Map<?, ?> map) {
		
		return !isEmpty(map);
	}
	/**
	 * 	判断一个字符串是否为空串
	 * @param string
	 * @return
	 */
	
	public static boolean isEmpty(String string) {
		
		return isNull(string) || NULLSTR.equals(string.trim());
	}
	
	/**
	 * 	判断一个字符串是否为非空串
	 * @param string
	 * @return
	 */
	public static boolean isNotEmpty(String string) {
		
		return !isEmpty(string);
	}
	/** 
	 * 	判断一个对象是否为空
	 * @param object
	 * @return
	 */
	public static boolean isNull(Object object) {
		
		return object == null;
	}
	
	/**
	 *	 判断一个对象是否非空
	 * @param object
	 * @return
	 */
	public static boolean isNotNull(Object object) {
		
		return !isNull(object);
	}
	
	/**
	 * 	判断一个对象是否是数组（Java类型）
	 * @param object
	 * @return
	 */
	public static boolean isArray(Object object) {
		
		return isNotNull(object) && object.getClass().isArray();
	}
	
	/**
	 * 	去空格
	 * @param string
	 * @return
	 */
	public static String trim(String string) {
		
		return (string == null ?"":string.trim());
	}
}
