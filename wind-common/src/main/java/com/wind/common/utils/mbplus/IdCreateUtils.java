package com.wind.common.utils.mbplus;

import java.util.UUID;

/** 
* @ClassName: IdCreateUtils 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author li 276827604 
* @date 2015年11月11日 下午6:03:47 
*  
*/
public class IdCreateUtils {
	/**
	 * 
	* @Title: createId 
	* @Description: TODO(生成32位UUUID) 
	* @return    设定文件 
	* @throws
	 */
	public static String createId(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
}
