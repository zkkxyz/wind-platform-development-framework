package com.wind.common.page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;
import java.util.List;

/**
 * 分页类：分页参数及查询结果的封装
 * @param <T>
 */
public class Page<T> extends RowBounds implements Serializable {

    // 分页参数中，当前页面,默认为1
    @JsonIgnore
    private int page = 0;

    // 分页参数中，起始记录下标，从0开始
    @JsonIgnore
    private int start;

    // 分页参数中，前台分页数对应于PageSize,每页记录数
    @JsonIgnore
    private int limit = 20;
    
    @JsonIgnore
    private int offset;

    // 总页面
    private int totalPages;

    // 总记录数
    private int totalRecords;

    // 记录封装
    private List<T> resultList;

    public Page() {

    }

    public Page(int pageNo, int pageSize) {
        setPage(pageNo);
        setLimit(pageSize);
    }

    public void initOtherData() {
        setTotalPages((int) ((totalRecords - 1) / limit) + 1);

    }

    /**
     * @return the totalPages
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * @param totalPages the totalPages to set
     */
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * @return the totalRecords
     */
    public int getTotalRecords() {
        return totalRecords;
    }

    /**
     * @param totalRecords the totalRecords to set
     */
    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
        initOtherData();
    }

    /**
     * @return the resultList
     */
    public List<T> getResultList() {
        return resultList;
    }

    /**
     * @param resultList the resultList to set
     */
    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    /**
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @return the start
     */
    public int getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * @return the limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * @param limit the limit to set
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

}