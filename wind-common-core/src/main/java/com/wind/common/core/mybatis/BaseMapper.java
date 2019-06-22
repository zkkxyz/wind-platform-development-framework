package com.wind.common.core.mybatis;


import com.wind.common.entity.AbstractDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
* @author complone
*/

public interface BaseMapper<T1 extends AbstractDto,T2> {

    /**
     * 根据查询参数统计数据
     * @param arg
     * @return
     */
    int countByArg(T2 arg);

    /**
     *根据查询条件获取LIST集合
     * @param arg
     * @return
     */
    List<T1> selectByArg(T2 arg);

    /**
     *根据主键获取对象
     * @param id
     * @return
     */
    T1 selectByPrimaryKey(String id);

    /**
     *分页查询数据
     * @param arg
     * @param rowBound
     * @return
     */
    List<T1> selectByArgAndPage(T2 arg, RowBounds rowBound);

    /**
     *插入单个对象，空值字段也插入数据
     * @param record
     * @return
     */
    int insert(T1 record);

    /**
     *插入单个对象，空值字段不插入
     * @param record
     * @return
     */
    int insertSelective(T1 record);

    /**
     *批量插入数据
     * @param records
     * @return
     */
    int insertBatch(@Param("list") List<T1> records);

    /**
     *根据条件更新对象  空值字段不更新
     * @param record
     * @param arg
     * @return
     */
    int updateByArgSelective(@Param("record") T1 record,
                             @Param("arg") T2 arg);

    /**
     *根据条件更新对象  空值字段也更新
     * @param record
     * @param arg
     * @return
     */
    int updateByArg(@Param("record") T1 record,
                    @Param("arg") T2 arg);

    /**
     *根据对象主键更新对象 非空字段更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(T1 record);

    /**
     *根据多主键更新成统一值
     * @param record
     * @return
     */
    int updateByPrimaryKeys(T1 record);

    /**
     *根据对象主键更新对象 非空字段更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(T1 record);

    /**
     *根据查询条件删除对象
     * @param arg
     * @return
     */
    int deleteByArg(T2 arg);

    /**
     *根据主键删除对象
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);
}
