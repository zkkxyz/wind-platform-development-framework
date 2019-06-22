package com.wind.common.core.dao;


import com.wind.common.core.mybatis.BaseArg;
import com.wind.common.core.mybatis.BaseMapper;
import com.wind.common.entity.AbstractDto;
import com.wind.common.page.Page;
import com.wind.common.utils.string.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.wind.common.utils.mbplus.StringUtils.toUpperCaseFirstOne;

/**
* @author XUZHERONG
* @date 2018/2/7
*/

public abstract class BaseDaoImpl<T1 extends AbstractDto,T2> extends SqlSessionDaoSupport implements BaseDao<T1,T2>  {

	@Resource(name = "sessionTemplate")
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public T1 save(T1 t1){
		if(t1==null){
			return null;
		}else {
			if(StringUtils.isEmpty(t1.getId())){
				this.insertSelective(t1);
			}else {
				this.updateByPrimaryKeySelective(t1);
			}
			return t1;
		}
	}

	@Override
	public int countByArg(T2 arg) {
		return getMapper().countByArg(arg);
	}

	@Override
	public int deleteByArg(T2 arg) {
		return getMapper().deleteByArg(arg);
	}

	@Override
	public List<T1> selectByArg(T2 arg) {
		return getMapper().selectByArg(arg);
	}

	@Override
	public int updateByArgSelective(T1 record, T2 arg) {
		return getMapper().updateByArgSelective(record, arg);
	}

	@Override
	public int updateByArg(T1 record, T2 arg) {
		return getMapper().updateByArg(record, arg);
	}

	@Override
	public Page<T1> selectByArgAndPage(T2 arg,
									   Page<T1> resultPage) {
		int limit = resultPage.getLimit();
		List<T1> resultList = getMapper().selectByArgAndPage(arg,
				resultPage);

		resultPage.setLimit(limit);
		resultPage.setResultList(resultList);
		return resultPage;
	}

	@Override
	public int insert(T1 record) {
		return getMapper().insert(record);
	}

	@Override
	public int insertSelective(T1 record) {
		return getMapper().insertSelective(record);
	}

	@Override
	public int insertBatch(List<T1> records) {
		return getMapper().insertBatch(records);
	}

	@Override
	public int deleteByPrimaryKey(String key) {
		return getMapper().deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKeys(T1 record) {
		return getMapper().updateByPrimaryKeys(record);
	}

	@Override
	public T1 selectByPrimaryKey(String key) {
		return getMapper().selectByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKeySelective(T1 record) {
		return getMapper().updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(T1 record) {
		return getMapper().updateByPrimaryKey(record);
	}

	/**
	 * 根据传入的Map条件进行查询，当前仅支持所有Map中Key字段的EqualTo查询
	 * @param params Map,Key=字段名，value=查询值
	 * @return
	 */
	@Override
	public List<T1> selectByMap(Map<String, Object> params) {
		return (selectByArg(buildQueryObject(params)));
	}

	protected abstract T2 buildQueryObject(Map<String, Object> params);

	protected BaseArg buildQueryObject(Map<String, Object> params, BaseArg object, Object object2) {

		Class criteriaClass = object2.getClass();
		Set keys = params.keySet();

		if (keys != null) {

			Iterator iterator = keys.iterator();

			while (iterator.hasNext()) {

				Object key = iterator.next();
				Object value = params.get(key);
				for (Method method : criteriaClass.getMethods()) {
					if (method.getName().equals(
							"and"+ toUpperCaseFirstOne(key.toString()) + "EqualTo")) {
						try {
							method.invoke(object2, value);
						}
						catch (Exception e) {
							throw new RuntimeException(e);
						}
						break;
					}
				}
			}
		}
		return object;
	}

	public BaseMapper<T1,T2> getMapper() {
		return getSqlSession().getMapper(BaseMapper.class);
	}

}
