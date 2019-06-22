package com.wind.common.core.mybatis;

/**
* @author complone
*/

public class BaseArg {
    /**
     * 主键名称
     */
    protected String pk_name = "id";

    /**
     * 排序条件
     */
    protected String orderByClause;

    /**
     * 分组条件
     */
    protected String groupByClause;

    /**
     *表字段
     */
    protected String columns;

    /**
     *
     */
    protected String countsql1;

    /**
     *
     */
    protected String countsql2;

    /**
     *是否去重标识
     */
    protected boolean distinct;

    /**
     *默认开始行
     */
    protected int rowStart = 0;

    /**
     *默认结束行
     */
    protected int rowEnd = 10;
}
