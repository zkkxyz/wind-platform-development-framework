package com.wind.common.utils.mbplus;


import com.wind.common.entity.AbstractDto;
import com.wind.common.param.QueryCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <Description>默认的接口实现，根据PO，转换出Arg类 <br>
 */

public class DefaultArgConversion implements IArgConversion {

    /**
     * 使用配置好的类型转换器工厂
     */
	@Autowired
    private FormattingConversionServiceFactoryBean conversionService;

    /**
     * 根据传入的Arg类型，及参数VO，构造出查询参数封装Arg
     * 
     * @param argClass Arg类
     * @param vo 前台到后台查询参数封装的VO
     * @return
     */
    public <T> T invokeArg(Class<T> argClass, AbstractDto vo){
        AssertUtils.isNotNull(argClass, "this argClass not allow null!");
        // 查询条件
        String queryConditions = vo.getQueryConditions();
        //排序方式
        String orderByClause = "";
        if(StringUtils.isNotEmpty(vo.getOrderField())&& StringUtils.isNotEmpty(vo.getOrderDirection())){
        	orderByClause=vo.getOrderField()+" "+vo.getOrderDirection();
        }
        T arg = null;
        try {
            arg = argClass.newInstance();
        }
        catch (Exception ex) {
            ex.getMessage();
        }

        Method argMethod;
        Object criteria = null;
        Method[] criteriaMethods = null;
        try {
            argMethod = argClass.getMethod("createCriteria");
            criteria = argMethod.invoke(arg);
            criteriaMethods = criteria.getClass().getDeclaredMethods();
            //排序字段添加
            if(orderByClause!=null&&orderByClause.trim().length() != 0){
            	Method orderMethod = arg.getClass().getMethod("setOrderByClause",String.class);
    			orderMethod.invoke(arg, orderByClause);
            }
        }
        catch (Exception ex) {
            ex.getMessage();
        }
        
        
        
        if (null == queryConditions || queryConditions.trim().length() == 0) {
        	// 没有查询条件直接返回
        	return arg;
        }
        
        List<QueryCondition> queryConditionsList = JsonUtil.toList(
                queryConditions, QueryCondition.class);
        if (null == queryConditionsList) {
        	return arg;
        }

        Iterator<QueryCondition> iter = queryConditionsList.iterator();

        while (iter.hasNext()) {
            QueryCondition thizondition = (QueryCondition) iter.next();
            try {
                String paramName = thizondition.getParamName();
                String operation = thizondition.getOperation().toString();
                String[] paramValue = thizondition.getParamValue();
                String methodName = "and"
                        + paramName.replaceFirst(paramName.substring(0, 1),
                                paramName.substring(0, 1).toUpperCase())
                        + operation;

                for (Method criteriamethod : criteriaMethods) {
                    // 方法名相等
                    if (criteriamethod.getName().equals(methodName)) {
                        System.out.println(criteriamethod.getName());
                        Class<?>[] paramsType = criteriamethod
                                .getParameterTypes();
                        if (QueryCondition.ConditionOperation.Between.toString().equals(
                                operation)
                                || QueryCondition.ConditionOperation.NotBetween.toString()
                                        .equals(operation)) {
                            // 如果是Between或者NotBetween操作的，取两个值
                            ReflectionUtils.invokeMethod(
                                    criteriamethod,
                                    criteria,
                                    conversionService.getObject().convert(
                                            paramValue[0], paramsType[0]),
                                    conversionService.getObject().convert(
                                            paramValue[1], paramsType[1]));
                        }
                        else if (QueryCondition.ConditionOperation.In.toString().equals(
                                operation)
                                ||QueryCondition.ConditionOperation.NotIn.toString().equals(
                                        operation)) {
                            // 如果是In或者NotIn操作的，取所有值
                            List invokeArgs = new ArrayList();
                            // Object[] invokeArgs = new Object[paramValue.length];
                            for (int i = 0; i < paramValue.length; i++) {
                                invokeArgs.add(conversionService.getObject()
                                        .convert(paramValue[i], Object.class));
                                // invokeArgs[i] = defaultConversionService
                                // .convert(paramValue[i], paramsType[i]);
                            }
                            ReflectionUtils.invokeMethod(criteriamethod,
                                    criteria, invokeArgs);
                        }
                        else {
                            // 其他的操作，只有一种值的情况
                            ReflectionUtils.invokeMethod(
                                    criteriamethod,
                                    criteria,
                                    conversionService.getObject().convert(
                                            thizondition.getParamValue()[0],
                                            paramsType[0]));
                        }

                    }
                }

            }
            catch (Exception ex) {
                ex.getMessage();
            }
            
        }
        
        return arg;
    }

    public static void main(String[] args) {
        // DefaultArgConversionService defaultArgConversionService = new DefaultArgConversionService();
        // String jsonStr =
        // "[{\"paramName\":\"userId\",\"operation\":\"EqualTo\",\"paramValue\":[\"1\"]},{\"paramName\":\"userId\",\"operation\":\"GreaterThan\",\"paramValue\":[\"1\"]},{\"paramName\":\"userName\",\"operation\":\"In\",\"paramValue\":[\"1\",\"2\"]}]";
        // AmUserPO po = new AmUserPO();
        // po.setQueryConditions(null);
        // po.setQueryConditions("");
        // po.setQueryConditions("[]");
        // po.setQueryConditions(jsonStr);
        // defalultArgConversionService.invokeArg(AmUserArg.class, po);
    	FormattingConversionService conversionService1 = new FormattingConversionService();
    	Double d = conversionService1.convert(0.68, Double.class);
    	
    	System.out.println(d);

    }
}
