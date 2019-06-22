package com.wind.common.utils.check;



import com.wind.common.exceptions.CheckedException;
import com.wind.common.utils.object.ObjectUtils;
import com.wind.common.utils.string.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;


/**
 * 通用检查，主要校验空值
 * 
 * @author linxiaoqing
 * 
 */
public class CheckUtils {

	  public static void isTrue(boolean expression)
	  {
	    if (!expression)
	    	throw new CheckedException("message_checker_isTrue");
	  }


	  public static void isNull(Object object)
	  {
	    if (object != null)
	    	throw new CheckedException("message_checker_isNull");
	  }


	  public static void notNull(Object object)
	  {
	    if (object == null)
	    	throw new CheckedException("message_checker_notNull");
	  }

	  public static void hasLength(String text)
	  {
	    if (!StringUtils.hasLength(text))
	    	throw new CheckedException("message_checker_hasLength");
	  }

	  public static void hasText(String text)
	  {
	    if (!StringUtils.hasText(text))
	    	throw new CheckedException("message_checker_hasText");
	  }

	  public static void doesNotContain(String textToSearch, String substring)
	  {
	    if ((StringUtils.hasLength(textToSearch)) && (StringUtils.hasLength(substring)) && (textToSearch.indexOf(substring) != -1))
	    	throw new CheckedException("message_checker_doesNotContain");
	  }


	  public static void notEmpty(Object[] array)
	  {
	    if (ObjectUtils.isEmpty(array))
	    	throw new CheckedException("message_checker_notEmpty");
	  }

	  public static void noNullElements(Object[] array)
	  {
	    if (array != null) {
	      Object[] arrayOfObject = array; int j = array.length; for (int i = 0; i < j; i++) { Object obj = arrayOfObject[i];
	        if (obj == null)
	        	throw new CheckedException("message_checker_noNullElements");
	      }
	    }
	  }

	  public static void notEmpty(Collection<?> collection)
	  {
	    if (CollectionUtils.isEmpty(collection))
	    	throw new CheckedException("message_checker_notEmptyCollection");
	  }

	  public static void notEmpty(Map<?, ?> map)
	  {
	    if (CollectionUtils.isEmpty(map))
	    	throw new CheckedException("message_checker_notEmptyMap");
	  }

	  public static void isInstanceOf(Class<?> type, Object obj)
	  {
	    notNull(type);
	    if (!type.isInstance(obj))
	    	throw new CheckedException("message_checker_isInstanceOf");
	  }
	
}
