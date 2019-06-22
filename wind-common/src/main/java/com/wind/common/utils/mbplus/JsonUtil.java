package com.wind.common.utils.mbplus;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    private static final JsonObjectMapper mapper=new JsonObjectMapper();
    /**
     * 字符串转为bean
     * 
     * @param content
     * @param valueType
     * @return
     */
    public static <T> T toBean(String content, Class<T> valueType) {
        try {
        	if(Utils.notEmpty(content)){
        		return mapper.readValue(content, valueType);
        	}
        }
        catch (Exception e) {
            e.getMessage();
        }

        return null;
    }

    /**
     * 把JavaBean转换为json字符串 <br>
     * 普通对象转换： toJson(Student) <br>
     * List转换：toJson(List) <br>
     * Map转换:toJson(Map) <br>
     * 我们发现不管什么类型，都可以直接传入这个方法
     * 
     * @param object JavaBean对象
     * @return json字符串
     */
    public static String toJson(Object object) {
        try {
        	if(Utils.notEmpty(object)){
        		return mapper.writeValueAsString(object);
        	}
        }
        catch (Exception e) {
            e.getMessage();
        }

        return null;
    }

    /**
     * 字符串转为List
     * 
     * @param object
     * @param T
     * @return
     */
    public static <T> List<T> toList(String content, Class<T> T) {
        try {
        	if(Utils.notEmpty(content)){
	            JavaType javaType = mapper.getTypeFactory()
	                    .constructParametricType(List.class, T);
	            return mapper.readValue(content, javaType);
        	}
        }
        catch (Exception e) {
            e.getMessage();
        }

        return null;
    }

    /**
     * 字符串转为Array
     * 
     * @param object
     * @param T
     * @return
     */
    public static <T> T[] toArray(String content, Class<T> T) {
        try {
        	if(Utils.notEmpty(content)){
	            List<T> list = JsonUtil.toList(content, T);
	            @SuppressWarnings("unchecked")
	            T[] a = (T[]) java.lang.reflect.Array.newInstance(T, list.size());
	            return list.toArray(a);
            }
        }
        catch (Exception e) {
            e.getMessage();
        }

        return null;
    }

    /**
     * List串转为Array
     * 
     * @param list
     * @param T
     * @return
     */
    public static <T> T[] toArray(List<T> list, Class<T> T) {
        try {
        	if(Utils.notEmpty(list)){
	            @SuppressWarnings("unchecked")
	            T[] a = (T[]) java.lang.reflect.Array.newInstance(T, list.size());
	            return list.toArray(a);
        	}
        }
        catch (Exception e) {
            e.getMessage();
        }

        return null;
    }

    /**
     * 字符串转为Map<String, Object>
     * 
     * @param content
     * @return
     */
    public static Map<String, Object> toMap(String content) {
        try {
        	if(Utils.notEmpty(content)){
            return mapper.readValue(content,
                    new TypeReference<Map<String, Object>>() {
                    });}
        }
        catch (Exception e) {
            e.getMessage();
        }

        return null;
    }

    /**
     * 对象转为Map<String, Object>
     * 
     * @param content
     * @return
     */
    public static Map<String, Object> toMap(Object object) {
        try {
        	if(Utils.notEmpty(object)){
            return mapper.convertValue(object,new TypeReference<Map<String, Object>>() {
                    });}
        }catch (Exception e) {
            e.getMessage();
        }

        return null;
    }

    /**
     * bean对象转Map,首字母大写
     * @param thisObj
     * @return
     */
    public static Map toMapHeadLatterUp(Object thisObj){
    	Map map = new HashMap();
    	Class c;
    	try{
	        c = Class.forName(thisObj.getClass().getName());
	        Method[] m = c.getMethods();
	        for (int i = 0; i < m.length; i++){
	        	String method = m[i].getName();
	        	if(method.startsWith("get")&&!"getClass".equals(method)){
		            try{
		            	Object value = m[i].invoke(thisObj);
		            	if(value != null){
		            		String key=method.substring(3);
		            		key=key.substring(0,1).toUpperCase()+key.substring(1);
		            		map.put(key, value);
		            	}
		            }catch (Exception e) {
		            	System.out.println("error:"+method);
		            }
	        	}
	        }
    	}catch (Exception e){
	        e.printStackTrace();
	    }
    	return map;
	}
    
    public static <T, V> Map<T, V> toJavaMap(String jsonString,
            Class<T> keyClass, Class<V> valueClass) throws Exception {
    	if(Utils.notEmpty(jsonString)){
        return mapper.readValue(jsonString, mapper.getTypeFactory()
                .constructMapLikeType(Map.class, keyClass, valueClass));
    	}
    	return null;
    }

}
