package com.wind.common.core.dao;



import com.wind.common.entity.AbstractDto;
import com.wind.common.page.Page;

import java.util.List;
import java.util.Map;

/**
* @author XUZHERONG
* @date 2018/2/7
*/

public  interface BaseDao<T1 extends AbstractDto,T2> {

	/**
	 * 如果要保存t1实例的ID为null则执行新增操作，否则执行更新操作。新增时将自动创建一个随机ID。
	 * @param t1
	 * @return
	 */
	T1 save(T1 t1);

	/**
	 * 根据查询参数统计数据
	 * @param arg
	 * @return
	 */
	int countByArg(T2 arg);

	/**
	 *根据查询条件删除对象
	 * @param arg
	 * @return
	 */
	int deleteByArg(T2 arg);

	/**
	 *根据查询条件获取LIST集合
	 * @param arg
	 * @return
	 */
	List<T1> selectByArg(T2 arg);

	/**
	 *根据条件更新对象  空值字段不更新
	 * @param record
	 * @param arg
	 * @return
	 */
	int updateByArgSelective(T1 record, T2 arg);

	/**
	 *根据条件更新对象  空值字段也更新
	 * @param record
	 * @param arg
	 * @return
	 */
	int updateByArg(T1 record, T2 arg);

	/**
	 *分页查询数据
	 * @param arg
	 * @param resultPage
	 * @return
	 */
	Page<T1> selectByArgAndPage(T2 arg,
								Page<T1> resultPage);

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
	int insertBatch(List<T1> records);

	/**
	 *根据主键删除对象
	 * @param key
	 * @return
	 */
	int deleteByPrimaryKey(String key);

	/**
	 *根据多主键更新成统一值
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeys(T1 record);

	/**
	 *根据主键获取对象
	 * @param key
	 * @return
	 */
	T1 selectByPrimaryKey(String key);

	/**
	 *根据对象主键更新对象 非空字段更新
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(T1 record);

	/**
	 *根据对象主键更新对象 全部字段都更新，不管有值没值
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(T1 record);

	/**
	 * 根据map获取LIST结果集
	 * @param params
	 * @return
	 */
	List<T1> selectByMap(Map<String, Object> params);
}

