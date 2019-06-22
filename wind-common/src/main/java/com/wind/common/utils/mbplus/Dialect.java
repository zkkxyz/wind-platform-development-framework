package com.wind.common.utils.mbplus;

/**
 * <Description> 分页抽象类<br>
 * 
 * @CreateDate 2014年9月15日 <br>
 * @since V1.0<br>
 */
public abstract class Dialect {

    public static enum Type {
        MYSQL, SQLSERVER, ORACLE
    }

    public abstract String getLimitString(String sql, int skipResults,
            int maxResults);

}
