/**
 * Copyright (c) 2012 teamTopGame
 * All rights reserved.
 */
package com.dce.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Json序列化
 */
public abstract class JsonUtil {
	
	private final static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	/**
	 * 私有构造函数，防止实例化
	 */
	private JsonUtil() {
	}

	/**
	 * ObjectMapper实例
	 */
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	/**
	 * 静态块，可初始化ObjectMapper
	 */
	static {
		configure(Feature.ALLOW_SINGLE_QUOTES, true);
		configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, false);
	}

	/**
	 * 配置ObjectMapper
	 * @param f 特性
	 * @param state 特性值
	 */
	public static void configure(Feature f, boolean state) {
		OBJECT_MAPPER.configure(f, state);
	}

	/**
	 * 将一个对象序列化成JSON字符串
	 * @param objectSource
	 * @return
	 */
	public static String objToJson(Object objectSource) {
		try {
			if (objectSource == null){
				return "";
			}
			return OBJECT_MAPPER.writeValueAsString(objectSource);
		} catch (Exception e) {
			logger.error("obj to json error :" + objectSource, e);
		}
		return null;
	}

	
	/**
	 * 将JSON字符串封装成指定对象
	 * @param objectSource JSON字符串
	 * @param clazz  指定的类型
	 * @return 返回指定类型的实例
	 */
	public static <T> T jsonToObj(String objectSource, Class<T> clazz) {
		if (StringUtils.isBlank(objectSource) || "0".equals(objectSource)){
			return null;
		}
		try {
			return OBJECT_MAPPER.readValue(objectSource, clazz);
		} catch (Exception e) {
			logger.error("json to obj error {}", e);
		}
		return null;
	}
	

	/**
	 * 将JSON字符串封装成Map对象
	 * @param objectSource JSON字符串
	 * @param keyClass Map的Key类型
	 * @param valueClass Map的Value类型
	 * @return 返回HashMap实例
	 */
	public static <K, V> HashMap<K, V> jsonToMap(String objectSource, Class<K> keyClass, Class<V> valueClass){
		try{
			return OBJECT_MAPPER.readValue(objectSource, TypeFactory.defaultInstance().constructMapType(HashMap.class, keyClass, valueClass));
		}catch(IOException e){
			logger.error("json to map error json character is : "+objectSource,e);
		}
		return null;
	}
	
	
	/**
	 * 将JSON字符串封装成ConcurrentHashMap对象
	 * 
	 * @param objectSource JSON字符串
	 * @param keyClass Map的Key类型
	 * @param valueClass Map的Value类型
	 * @return 返回ConcurrentHashMap实例
	 */
	public static <K, V> ConcurrentHashMap<K, V> jsonToConcurrentMap(String objectSource, Class<K> keyClass, Class<V> valueClass){
		try{
			return OBJECT_MAPPER.readValue(objectSource, TypeFactory.defaultInstance().constructMapType(ConcurrentHashMap.class, keyClass, valueClass));
		}catch(IOException e){
			logger.error("json to concurrent map error json character is : "+objectSource,e);
		}
		return null;
	}
	
	/**
	 * 将JSON字符串封装成ConcurrentLinkedQueue对象
	 * @param objectSource JSON字符串
	 * @param clazz List类型
	 * @return 返回ConcurrentLinkedQueue实例
	 * @throws IOException IO操作异常
	 */
	public static <T> ConcurrentLinkedQueue<T> json2ConcurrentLinkedQueue(String objectSource, Class<T> clazz){
		try {
			return OBJECT_MAPPER.readValue(objectSource, TypeFactory.defaultInstance().constructCollectionType(ConcurrentLinkedQueue.class, clazz));
		} catch (Exception e) {
			logger.error("objectJsonCWListDecode error json is:"+objectSource,e);
		}
		return null;
	}
	
	/**
	 * 将JSON字符串封装成List对象
	 * 
	 * @param objectSource
	 *            JSON字符串
	 * @param clazz
	 *            List类型
	 * @return 返回List实例
	 * @throws IOException
	 *             IO操作异常
	 */
	public static <T> List<T> objectJsonListDecode(String objectSource, Class<T> clazz){
		if(StringUtil.isBlank(objectSource)){
			return null;
		}
		try {
			return OBJECT_MAPPER.readValue(objectSource, TypeFactory.defaultInstance().constructCollectionType(List.class, clazz));
		} catch (Exception e) {
			logger.error("objectJsonCWListDecode error json is:"+objectSource,e);
		}
		return null;
	}
	
	
	/**
	 * 将JSON字符串封装成List对象
	 * 
	 * @param objectSource
	 *            JSON字符串
	 * @param clazz
	 *            List类型
	 * @return 返回List实例
	 * @throws IOException
	 *             IO操作异常
	 */
	public static <T> LinkedList<T> objectJsonLinkedListDecode(String objectSource, Class<T> clazz){
		try {
			return OBJECT_MAPPER.readValue(objectSource, TypeFactory.defaultInstance().constructCollectionType(LinkedList.class, clazz));
		} catch (Exception e) {
			logger.error("objectJsonLinkedListDecode error json is:"+objectSource,e);
		}
		return null;
	}
	
	/**
	 * 将JSON字符串封装成List对象
	 * 
	 * @param objectSource
	 *            JSON字符串
	 * @param clazz
	 *            List类型
	 * @return 返回ConcurrentSkipListSet实例
	 * @throws IOException
	 *             IO操作异常
	 */
	public static <T> ConcurrentSkipListSet<T> objectJsonCLspSetDecode(String objectSource, Class<T> clazz){
		try {
			return OBJECT_MAPPER.readValue(objectSource, TypeFactory.defaultInstance().constructCollectionType(ConcurrentSkipListSet.class, clazz));
		} catch (Exception e) {
			logger.error("objectJsonCLspSetDecode error json is:"+objectSource,e);
		}
		return null;
	}
	
	
	/**
	 * 根据model类的成员变量，将其变成对应从1开始的key与该变量的值
	 * {"gangBattle":0,"yunBiaoAfternoon":0,"yunBiaoNight":0,"gangRobbertMorning":0,"gangRobbertAfternoon":0,"biwu":0,"wordBoss":0,"fish":0,"gangGh":0}
	 * 将会变成
	 * {"1":0,"2":0,"3":0,"4":0,"5":0,"6":0,"7":0,"8":0,"9":0}
	 * @param instance 事例
	 * @return
	 * @throws Exception
	 */
	public static String getNumOrderJsonByClass(Object instance) throws Exception{
		Class<? extends Object> clazz = instance.getClass();
		Field[] declaredFields = clazz.getDeclaredFields();
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		int length = declaredFields.length;
		for(int i=0;i<length;i++){
			Field field = declaredFields[i];
			field.setAccessible(true);
			sb.append("\"").append(i+1).append("\"").append(":").append(field.get(instance));
			if(i<length-1){
				sb.append(",");
			}
		}
		sb.append("}");
		return sb.toString();
	}
	
	/**
	 * 将JSON字符串封装成Queue对象
	 * 
	 * @param objectSource
	 *            JSON字符串
	 * @param clazz
	 *            List类型
	 * @return 返回List实例
	 * @throws IOException
	 *             IO操作异常
	 */
	public static <T> Queue<T> objectJsonQueueDecode(String objectSource, Class<T> clazz){
		try {
			return OBJECT_MAPPER.readValue(objectSource, TypeFactory.defaultInstance().constructCollectionType(Queue.class, clazz));
		} catch (Exception e) {
			logger.error("objectJsonQueueDecode error json is:"+objectSource,e);
		}
		return null;
	}
}
