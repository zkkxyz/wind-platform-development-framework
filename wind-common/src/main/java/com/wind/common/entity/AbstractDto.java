package com.wind.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wind.common.utils.mbplus.EqualsUtils;
import com.wind.common.utils.mbplus.HashCodeUtils;
import com.wind.common.utils.mbplus.StringUtils;


import java.lang.reflect.Field;
import java.util.Date;

/**
 * <Description>PO、DTO、VO 基础类 <br>
 */
public abstract class AbstractDto implements java.io.Serializable,
        Cloneable {

    public AbstractDto() {

    }

    /**
     * 主键
     */
    protected String id;
    /**
     * 创建人
     */
    protected String  createUser;
    /**
     * 创建时间
     */
    protected Date createTime;
    /**
     * 更新人
     */
    protected String  updateUser;
    /**
     * 更新时间
     */
    protected Date  updateTime;

    /**
     * 封装前台传入的查询参数对象（包含查询字段、查询操作、查询值）<br>
     * 形如：[{\"paramName\":\"userName\",\"operation\":\"Like\",\"paramValue\":[\"1\"]}...]
     */
    @JsonIgnore
    private String queryConditions;
    /**
     * 封装前台传入排序的字段
     */
    @JsonIgnore
    private String orderField;
    /**
     * 封装前台传入排序的类型 desc asc
     */
    private String orderDirection;


    public AbstractDto copy() {
        try {
            return (AbstractDto) this.clone();
        }
        catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * 提供默认的HashCode算法 当前对象上自己定义的Field才会参与Hash值计算，不包含父类的Field
     * 
     * @return int 返回哈希值<br>
     */
    @Override
    public int hashCode() {
        int result = 17;

        Field[] fieldList = this.getClass().getDeclaredFields();
        if (fieldList != null) {
            for (Field field : fieldList) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                try {
                    Object thisValue = field.get(this);
                    result = 37 * result + HashCodeUtils.hashCode(thisValue);
                }
                catch (IllegalArgumentException e) {
                    return super.hashCode();
                }
                catch (IllegalAccessException e) {
                    return super.hashCode();
                }
            }
        }

        return result;

    }

    /**
     * 判断两个对象是否相等 当前对象上自己定义的Field才会参与比较，不包含父类的Field
     * 
     * @param obj 要比较的对象
     * @return boolean 两个对象相等才会返回true，否则返回false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (null == obj) {
            return false;
        }
        Field[] fieldList = this.getClass().getDeclaredFields();
        if (fieldList != null) {
            for (Field field : fieldList) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                try {
                    Object thisValue = field.get(this);
                    Object thatValue = field.get(obj);
                    if (!EqualsUtils.equals(thisValue, thatValue)) {
                        return false;
                    }
                }
                catch (IllegalArgumentException e) {
                    super.equals(obj);
                }
                catch (IllegalAccessException e) {
                    super.equals(obj);
                }
            }
        }

        return true;
    }

    /**
     * 重载toString()函数 当前对象上自己定义的Field才会被输出，不包含父类的Field
     * 
     * @return String 返回字符串
     */
    @Override
    public String toString() {
        StringBuffer returnStr = new StringBuffer();
        Field[] fieldList = this.getClass().getDeclaredFields();
        if (fieldList != null) {
            returnStr.append("Ojbect Value List :").append(
                    System.getProperty("line.separator"));
            for (Field field : fieldList) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                returnStr.append(field.getName()).append(":[");
                String value;
                try {
                    value = StringUtils.toString(field.get(this));
                }
                catch (IllegalArgumentException e) {
                    value = "";
                }
                catch (IllegalAccessException e) {
                    value = "";
                }
                returnStr.append(value).append("]")
                        .append(System.getProperty("line.separator"));
            }
        }
        return returnStr.toString();
    }

    /**
     * @return the queryConditions
     */
    public String getQueryConditions() {
        return queryConditions;
    }

    /**
     * @param queryConditions the queryConditions to set
     */
    public void setQueryConditions(String queryConditions) {
        this.queryConditions = queryConditions;
    }
    
    public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}

	/**
     * 后台增加查询条件
     * @param property 字段名字
     * @param value 字段值
     */
    public void addNewConditions(String property,String value) {
    	String addStr = "";
    	if(value!=null&&!"".equals(value)){
    		addStr = "{\"paramName\":\""+property+"\",\"operation\":\"EqualTo\",\"paramValue\":[\""+value+"\"]}";
    	}
    	if(queryConditions==null||"".equals(queryConditions)){
    		this.queryConditions = "["+addStr+"]";
    	}else if("[]".equals(queryConditions)){
    		if(!"".equals(addStr)){
    			this.queryConditions = "["+addStr+"]";
    		}
    	}else{
    		if(!"".equals(addStr)){
    			this.queryConditions = queryConditions.substring(0, queryConditions.length()-1)+","+addStr+"]";
    		}
    	}
    }
    /**
     * 后台增加查询条件
     * @param property 字段名字
     * @param value 字段值
     * @param expression : {
            IsNull : "IsNull", // XXXX is null
            IsNotNull : "IsNotNull", // XXXX is not null
            EqualTo : "EqualTo", // 等于 =
            NotEqualTo : "NotEqualTo", // 不等于 <>
            GreaterThan : "GreaterThan", // 大于 >
            GreaterThanOrEqualTo : "GreaterThanOrEqualTo", // 大于等于 >=
            LessThan : "LessThan", // 小于 <
            LessThanOrEqualTo : "LessThanOrEqualTo", // 小于等于 <=
            Like : "Like", // 模糊查询 XXXX like %value%
            NotLike : "NotLike", // XXXX not like %value%
            LeftLike : "LeftLike", // XXXX like %value
            NotLeftLike : "NotLeftLike", // XXXX not like %value
            RightLike : "RightLike", // XXXX like value%
            NotRightLike : "NotRightLike", // XXXX not like value%
            In : "In", // in ()
            NotIn : "NotIn", // not in ()
            Between : "Between", // 范围 between
            NotBetween : "NotBetween"// not between
        }
     */
    public void addNewConditions(String property,String value,String expression) {
    	String addStr = "";
    	if(value!=null&&!"".equals(value)){
    		addStr = "{\"paramName\":\""+property+"\",\"operation\":\""+expression+"\",\"paramValue\":[\""+value+"\"]}";
    	}
    	if(queryConditions==null||"".equals(queryConditions)){
    		this.queryConditions = "["+addStr+"]";
    	}else if("[]".equals(queryConditions)){
    		if(!"".equals(addStr)){
    			this.queryConditions = "["+addStr+"]";
    		}
    	}else{
    		if(!"".equals(addStr)){
    			this.queryConditions = queryConditions.substring(0, queryConditions.length()-1)+","+addStr+"]";
    		}
    	}
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
