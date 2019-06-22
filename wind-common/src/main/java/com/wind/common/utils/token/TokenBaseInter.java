package com.wind.common.utils.token;


/**
 * token生成接口
 * @author linxiaoqing
 *
 */
public interface TokenBaseInter {
	
	 public final  String key="gzzyzz";
	
	/**
	 * 解密
	 * 
	 * @param str
	 * @return
	 */
	public  String decrypt(String str);
	
	/**
	 * 加密
	 * 
	 * @param str
	 * @return
	 */
	public  String encrypt(String str);
	
	/**
	 * 放入各种定制的参数，生产Token
	 * @param pramaters
	 * @return
	 */
	public  String productToken(String[] pramaters);

	/**
	 * 放入各种定制的参数，生产Token
	 * @param userNo
	 * @return
	 */
	public  String productToken(String pix, String userNo);
}
