package com.wind.common.config;



import com.wind.common.utils.properties.ResourceUtils;

import java.util.Map;


/**
 * 提供系统级的配置，几乎不修改的配置,配置文件放在zoe-common-config工程下
 * @author linxiaoqing
 *
 */
public class PublicConfig {

	/**
	 * 用户系统文件配置 加载。
	 */
	public static Map<String, String> PUBLIC_USER = ResourceUtils.getResource("public_user").getMap();

	/**
	 * 系统文件配置 加载。
	 */
	public static Map<String, String> PUBLIC_SYSTEM = ResourceUtils.getResource("public_system").getMap();


	/**
	 * 密码错误限制次数
	 */
	public final static Integer PWD_ERROR_LIMIT_TIMES = Integer.parseInt(PUBLIC_USER.get("PWD_ERROR_LIMIT_TIMES"));

	/**
	 * 密码错误限制时间（分钟）
	 */
	public final static Integer PWD_ERROR_LIMIT_TIME = Integer.parseInt(PUBLIC_USER.get("PWD_ERROR_LIMIT_TIME"));

	/**
	 * 门户是否使用验证码 配合密码错误次数值使用
	 */
	public final static boolean IS_USE_KAPTCHA = Boolean.parseBoolean(PUBLIC_USER.get("IS_USE_KAPTCHA"));

	/**
	 * 密码错误次数值 将 出现验证码，如果值为0 则永远不会出现验证码
	 */
	public final static Integer PWD_TIMES_USE_KAPTCHA = Integer.parseInt(PUBLIC_USER.get("PWD_TIMES_USE_KAPTCHA"));

	/**
	 * 是否使用CFCA密码控件
	 */
	public final static boolean USE_KEYBOARD = Boolean.parseBoolean(PUBLIC_USER.get("USE_KEYBOARD"));

	/**
	 * 是否启用CFCA数字证书
	 */
	public final static boolean USE_SECURITYCENTER = Boolean.parseBoolean(PUBLIC_USER.get("USE_SECURITYCENTER"));

	/** CA service ip */
	public static String CA_SERVER_IP;
	/** CA服务端口 */
	public static String CA_SERVERPORT;
	/** CA证书路径 */
	public static String CA_KEYSTORE_PATH;
	/** CA证书口令 */
	public static String CA_KEYSTORE_PWD;

	static {
		if (USE_SECURITYCENTER) {
			CA_SERVER_IP = PUBLIC_USER.get("CA_SERVER_IP");
			CA_SERVERPORT = PUBLIC_USER.get("CA_SERVERPORT");
			CA_KEYSTORE_PATH = PUBLIC_USER.get("CA_KEYSTORE_PATH");
			CA_KEYSTORE_PWD = PUBLIC_USER.get("CA_KEYSTORE_PWD");
		}
	}
	
	/**
	 * 是否开发状态
	 */
	public final static boolean IS_DEV_STATUS = Boolean.parseBoolean(PUBLIC_USER.get("IS_DEV_STATUS"));

	/**
	 * 是否 是 域名 + 应用名
	 */
	public final static boolean IS_USE_DOMAIN_NAME = Boolean.parseBoolean(PUBLIC_USER.get("IS_USE_DOMAIN_NAME"));

	/**
	 * 是否采用SSL协议
	 */
	public final static boolean IS_SSL = Boolean.parseBoolean(PUBLIC_USER.get("IS_SSL"));

	/**
	 * 是否需要注册功能
	 */
	public final static boolean PORTAL_IS_REGISTER = Boolean.parseBoolean(PUBLIC_USER.get("PORTAL_IS_REGISTER"));


	/**
	 * 公司名称
	 */
	public final static String COMPANY_NAME = PUBLIC_USER.get("COMPANY_NAME");

	/**
	 * 公司简称
	 */
	public final static String COMPANY_FOR = PUBLIC_USER.get("COMPANY_FOR");

	/**
	 * 公司LOGO
	 */
	public final static String COMPANY_LOGO = PUBLIC_USER.get("COMPANY_LOGO");

	/**
	 * 公司 ICP
	 */
	public final static String COMPANY_NET_ICP = PUBLIC_USER.get("COMPANY_NET_ICP");

	/**
	 * 公司 Address
	 */
	public final static String COMPANY_ADDRESS = PUBLIC_USER.get("COMPANY_ADDRESS");

	/**
	 * 公司 TEL
	 */
	public final static String COMPANY_TEL = PUBLIC_USER.get("COMPANY_TEL");

	/**
	 * 公司 email
	 */
	public final static String COMPANY_EMAIL = PUBLIC_USER.get("COMPANY_EMAIL");

	/**
	 * 公司 HR email
	 */
	public final static String COMPANY_HR_EMAIL = PUBLIC_USER.get("COMPANY_HR_EMAIL");

	/**
	 * 公司 About us
	 */
	public final static String COMPANY_ABOUT_US = PUBLIC_USER.get("COMPANY_ABOUT_US");


}
