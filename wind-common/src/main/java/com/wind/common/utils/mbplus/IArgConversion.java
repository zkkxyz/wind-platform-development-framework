package com.wind.common.utils.mbplus;


import com.wind.common.entity.AbstractDto;

/**
 * <Description>查询条件转换成Arg类的服务接口 <br>
 */

public interface IArgConversion {

    /**
     * 根据传入的Arg类型，及参数VO，构造出查询参数封装Arg
     * 
     * @param argClass Arg类
     * @param vo 前台到后台查询参数封装的VO，其中queryConditions字段必须为JSON字符串（代表要查询的条件）形如
     *            [{\"paramName\":\"userName\",\"operation\":\"Like\",\"paramValue\":[\"1\"]}...]
     *            
     *            "[{\"paramName\":\"userId\",\"operation\":\"EqualTo\",\"paramValue\":[\"1\"]},
     *            {\"paramName\":\"age\",\"operation\":\"GreaterThan\",\"paramValue\":[\"20\"]},
     *            {\"paramName\":\"age\",\"operation\":\"In\",\"paramValue\":[\"21\",\"22\"]}]";
     * @return
     */
    <T> T invokeArg(Class<T> argClass, AbstractDto vo);
}
