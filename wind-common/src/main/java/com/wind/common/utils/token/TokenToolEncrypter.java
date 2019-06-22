package com.wind.common.utils.token;

import com.wind.common.exceptions.BizException;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;


/**
 * 一个Token加密与解密工具 ，不限于Token范围  只能解本JDK加密串
 * 使用时，结合 TokenProductFactory 生成平台Token
 * 通过解密，识别Token来源。
 * @author linxiaoqing
 *注：DES的加密方式
 */
public class TokenToolEncrypter  implements TokenBaseInter{

	/** BASE64 加密处理工具 */
	private static Cipher ecipher;
	/** BASE64 解密处理工具 */
	private static Cipher dcipher;
    
	/**初始化值**/
	public TokenToolEncrypter(){
		init();
	}
	public static void init() {
		try {
			SecretKey key = KeyGenerator.getInstance("DES").generateKey();
			ecipher = Cipher.getInstance("DES");
			dcipher = Cipher.getInstance("DES");
			ecipher.init(Cipher.ENCRYPT_MODE, key);
			dcipher.init(Cipher.DECRYPT_MODE, key);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加密
	 * 
	 * @param str
	 * @return
	 */
	@SuppressWarnings("restriction")
	public  String encrypt(String str) {
		try {
			if(ecipher==null){
				init();
			}if(StringUtils.isEmpty(str)){
				throw new BizException("Token 验证非法");
			}			
			byte[] utf8 = str.getBytes("UTF8");// Encode the string into bytes using utf-8			
			byte[] enc = ecipher.doFinal(utf8);	// Encrypt
			return new sun.misc.BASE64Encoder().encode(enc).replace("+", "_");// Encode bytes to base64 to get a string
		} catch (javax.crypto.BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param str
	 * @return
	 */
	@SuppressWarnings("restriction")
	public   String decrypt(String str) {
		try {
			if(ecipher==null){
				init();
			}if(StringUtils.isEmpty(str)){
				throw new BizException("Token 验证非法");
			}
			str = str.replace("_", "+");			
			byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);// Decode base64 to get bytes			
			byte[] utf8 = dcipher.doFinal(dec);// Decrypt			
			return new String(utf8, "UTF8");// Decode using utf-8
		} catch (javax.crypto.BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 生成token
	 * @param pramaters  自定义参数
	 */
	@Override
	public String productToken(String[] pramaters) {
		if(pramaters==null || pramaters.length==0){
			return null;
		}else{
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < pramaters.length; i++) {
				sb.append(pramaters[i]+"-");
			}
			sb.append(key);//最后加上Key值
			return this.encrypt(sb.toString());
		}
	}
	
	/**
	 * 通过自定义前缀和用户编号，生成token
	 * @param pix 前缀
	 *  @param userNo 用户编码
	 */
	@Override
	public String productToken(String pix, String userNo) {
		return this.encrypt(pix+"-"+userNo+"-"+System.currentTimeMillis()+"-"+key);
	}

}
