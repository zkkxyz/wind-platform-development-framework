package com.wind.common.utils.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import com.wind.common.exceptions.BizException;
import com.wind.common.exceptions.CheckedException;
import com.wind.common.utils.object.ObjectUtils;
import com.wind.common.utils.string.StringUtils;
import org.apache.commons.collections.map.ListOrderedMap;


import java.util.*;

public class JsonUtils extends JSONObject {

    private static SerializeConfig mapping = new SerializeConfig();
    private static String dateFormat;


    static {
        dateFormat = "yyyy-MM-dd HH:mm:ss";
        mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat));
    }

    private static final long serialVersionUID = 1L;

    public static void copy(JSONObject target, JSONObject source, String... keys) {

        if (keys == null || keys.length < 0)
            source.keySet().forEach(key -> copyTo(target, source, (String) key));
        else
            for (String key : keys)
                copyTo(target, source, key);
    }

    protected static void copyTo(JSONObject target, JSONObject source, String key) {
        if (source.containsKey(key))
            target.put(key, source.get(key));
    }

    public static void alterKey(JSONObject source, String targetKey, String sourceKey) {
        if (source.containsKey(sourceKey)) {
            source.put(targetKey, source.get(sourceKey));
            source.remove(sourceKey);
        }
    }

    public static Map<String, Object> parserToMap(String jsonStr) {
        Map<String, Object> map = new HashMap<>();
        JSONObject json = JSON.parseObject(jsonStr);
        for (Map.Entry<String, Object> entry : json.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue() + "";
            if (value.startsWith("{") && value.endsWith("}")) {
                map.put(key, parserToMap(value));
            } else {
                map.put(key, value);
            }
        }
        /*
         * Set set = json.entrySet(); Iterator keys = set.iterator(); while
         * (keys.hasNext()) { String key = (String) keys.next(); String value =
         * json.get(key).toString(); if (value.startsWith("{") &&
         * value.endsWith("}")) { map.put(key, parserToMap(value)); } else {
         * map.put(key, value); } }
         */
        return map;
    }

    public static boolean isEmpty(JSONObject target, String key) {

        if (target == null || target.isEmpty()) {
            return true;
        }

        if (!target.containsKey(key)) {
            return true;
        }

        if ( ObjectUtils.isEmpty(target.get(key))) {
            return true;
        }

        return false;
    }

    public static JSONObject getRequestJo(Object parameter) {
        try {
            JSONObject jo = JSON.parseObject(parameter.toString());
            return jo;
        } catch (Exception e) {
            throw new BizException(e, "获取解析请求异常错误！");
        }
    }

    public static void checkKey(JSONObject target, String... keys) {
        for (String key : keys)
            if (!target.containsKey(key))
                throw new BizException("JSONObject不包含key对象：" + key);
    }

    public static void checkValue(JSONObject target, String... keys) {
        for (String key : keys) {
            if (!target.containsKey(key))
                throw new BizException("JSONObject不包含key对象：" + key);
            if (target.get(key) == null)
                throw new BizException("JSONObject不包含key对象：" + key);
        }
    }

    public static Object getNotNullValue(JSONObject target, String key) {
        if (target == null)
            throw new BizException("JSONObject不能为空");
        if (target.get(key) == null) {
            throw new BizException("JSONObject不包含key对象：" + key);
        }

        return target.get(key);
    }

    /**
     * object 转 JSONObject
     * Date类型默认格式为 yyyy-MM-dd HH:mm:ss
     *
     * @param o 实体类型
     * @return T
     * @throws
     */
    public static JSONObject toJSONObject(Object o) {
        return toJSONObject(o, mapping);
    }

    public static JSONObject toJSONObject(Object o, SerializeConfig mapping) {
        JSONObject result = null;
        if (o == null)
            result = new JSONObject();
        else if (o instanceof JSONObject)
            result = (JSONObject) o;
        else if (o instanceof String)
            result = JSON.parseObject((String) o);
        else
            result = JSON.parseObject(toJSONString(o, mapping));
        return result == null ? new JSONObject() : result;
    }


    /**
     * object 转 JSONObject
     * Date类型按照指定格式转化
     *
     * @param o 实体类型
     * @return T
     * @throws
     */
    public static JSONObject toJSONObject(Object o, String dateFormat1) {
        if (dateFormat1 == null || dateFormat1.trim().length() <= 0)
            return toJSONObject(o, mapping);
        SerializeConfig mapping1 = new SerializeConfig();
        mapping1.put(Date.class, new SimpleDateFormatSerializer(dateFormat1));
        return toJSONObject(o, mapping1);
    }

    /**
     * object 转 JSONArray
     * Date类型默认格式为 yyyy-MM-dd HH:mm:ss
     *
     * @param o 实体类型
     * @return T
     * @throws
     */
    public static JSONArray toJSONArray(Object o) {
        return toJSONArray(o, mapping);
    }

    public static JSONArray toJSONArray(Object o, SerializeConfig mapping) {
        JSONArray result = null;
        if (o == null)
            result = new JSONArray();
        else if (o instanceof JSONObject)
            result = (JSONArray) o;
        else if (o instanceof String)
            result = JSON.parseArray((String) o);
        else
            result = JSON.parseArray(toJSONString(o, mapping));
        return result == null ? new JSONArray() : result;

    }

    /**
     * object 转 JSONArray
     * Date类型按照指定格式转化
     *
     * @param o 实体类型
     * @return T
     * @throws
     */
    public static JSONArray toJSONArray(Object o, String dateFormat1) {
        if (dateFormat1 == null || dateFormat1.trim().length() <= 0)
            return toJSONArray(o, mapping);
        SerializeConfig mapping1 = new SerializeConfig();
        mapping1.put(Date.class, new SimpleDateFormatSerializer(dateFormat1));
        return toJSONArray(o, mapping1);
    }

    public static String toJSONString(Object o) {
        return toJSONString(o, mapping);
    }

    public static String toJSONString(Object o, SerializeConfig mapping) {
        return JSON.toJSONString(o, mapping, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullBooleanAsFalse);
    }


    /**
     * 反序列化对象
     *
     * @param cls  实体类型
     * @param json json文本
     * @return T
     * @throws
     */
    public static <T> T Deserialize(Class<T> cls, String json) throws Exception {
        return Deserialize(cls, json, null);
    }

    /**
     * 反序列化对象
     *
     * @param cls  实体类型
     * @param json json文本
     * @return T
     * @throws
     */
    @SuppressWarnings({"unchecked", "static-access"})
    public static <T> T toBean(Class<T> cls, String json) throws Exception {
        if (cls == null)
            throw new Exception("Json的Deserialize方法传入实体类型为空，反序列化失败！");
        if ( StringUtils.isEmpty(json))
            throw new Exception("Json的Deserialize方法传入json内容为空，反序列化失败！");
        try {
            JSONObject jo = JSON.parseObject(json);
            return (T) JSON.toJavaObject(jo, cls);
        } catch (Exception e) {
            throw new Exception("Json的Deserialize[" + cls.getName() + "]类型的实体失败！");
        }
    }

    /**
     * 反序列化对象
     *
     * @param cls      实体类型
     * @param json     json文本
     * @param excludes 排除项
     * @return T
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T> T Deserialize(Class<T> cls, String json, String[] excludes) throws Exception {
        if (cls == null)
            throw new Exception("Json的Deserialize方法传入实体类型为空，反序列化失败！");
        if ( StringUtils.isEmpty(json))
            throw new Exception("Json的Deserialize方法传入json内容为空，反序列化失败！");
        try {
            JSONObject object = null;
            if (excludes == null) {
                //JsonConfig config = CommonConstant.JSONCONFIG;//modify by linys 日期的json格式转换配置
                object = JSON.parseObject(json);
            } else {
                //JsonConfig config = new JsonConfig();
                //config.setExcludes(excludes);
                //object = JSON.parseObject(json, config);
            }
            return (T) JSON.toJavaObject(object, cls);
        } catch (Exception e) {
            //e.printStackTrace();
            throw new CheckedException(e, "Json的Deserialize[" + cls.getName() + "]类型的实体失败！");
        }
    }

    /**
     * 反序列化对象
     *
     * @param cls  实体类型
     * @param json json文本
     * @return List<T>
     * @throws Exception
     */
    public static <T> List<T> DeserializeList(Class<T> cls, String json) throws Exception {
        return DeserializeList(cls, json, null);
    }

    /**
     * 反序列化对象
     *
     * @param cls      实体类型
     * @param json     json文本
     * @param excludes 排除项
     * @return List<T>
     * @throws Exception
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <T> List<T> DeserializeList(Class<T> cls, String json, String[] excludes) throws Exception {
        if (cls == null)
            throw new Exception("Json的DeserializeList方法传入实体类型为空，反序列化失败！");
        if ( StringUtils.isEmpty(json))
            throw new Exception("Json的DeserializeList方法传入json内容为空，反序列化失败！");
        try {
            JSONArray array = null;
            if (excludes == null) {
                array = JSON.parseArray(json);
            } else {
                //JsonConfig config = new JsonConfig();
                //config.setExcludes(excludes);
                //array = JSON.parseArray(json, config);
            }
            List<T> list = new ArrayList<T>();
            for (Iterator iter = array.iterator(); iter.hasNext(); ) {
                JSONObject object = (JSONObject) iter.next();
                T t = (T) JSON.toJavaObject(object, cls);
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            throw new Exception("Json的DeserializeList[" + cls.getName() + "]类型的实体失败！");
        }
    }

    /**
     * key 升序排序 lambda
     */
    public static JSONObject keySortAsc(JSONObject json) {
        ListOrderedMap.Entry[] entry = (ListOrderedMap.Entry[]) json.entrySet().stream().sorted((o1, o2) -> {
            ListOrderedMap.Entry t1 = ((ListOrderedMap.Entry) o1);
            ListOrderedMap.Entry t2 = ((ListOrderedMap.Entry) o2);
            return t1.getKey().toString().compareTo(t2.toString());
        }).toArray(ListOrderedMap.Entry[]::new);

        return fillSortJson(entry);
    }

    /**
     * key 降序排序 lambda
     */
    public static JSONObject keySortDesc(JSONObject json) {
        ListOrderedMap.Entry[] entry = (ListOrderedMap.Entry[]) json.entrySet().stream().sorted((o1, o2) -> {
            ListOrderedMap.Entry t1 = ((ListOrderedMap.Entry) o1);
            ListOrderedMap.Entry t2 = ((ListOrderedMap.Entry) o2);
            return t2.getKey().toString().compareTo(t1.toString());
        }).toArray(ListOrderedMap.Entry[]::new);

        return fillSortJson(entry);
    }

    /**
     * 排序结果
     */
    private static JSONObject fillSortJson(ListOrderedMap.Entry[] entry) {
        JSONObject result = new JSONObject();

        for (ListOrderedMap.Entry e : entry)
            result.put(e.getKey().toString(), e.getValue());

        return result;
    }

    /**
     * 迭代并添加数据 (从 from 对象中copy全部数据到 to 对象中, 让 to 拥有 from 的所有数据)
     *
     * @param from 要copy数据的对象
     * @param to   要添加到目标对象
     */
    public static void addAllJson(JSONObject from, JSONObject to) {
        Iterator keys = from.keySet().iterator();
        for (; keys.hasNext(); ) {
            String key = keys.next().toString();
            to.put(key, from.get(key));
        }
    }

}
