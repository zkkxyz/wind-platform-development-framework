package com.wind.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类描述：公共模板枚举
 * @author linxiaoqing
 *
 */
public enum PublicTemplateEnum {
	
	/************邮件模板**********/
	MAIL_RES_MA("邮箱注册", 1001),
	MAIL_FIND_CODE_LOGIN_MA("找回登陆密码", 1002),
	MAIL_FIND_CODE_PAY_MA("找回支付密码", 1003),
	MAIL_BANG_MA("绑定邮箱" , 1004),
	MAIL_BANG_MA_MOD("修改绑定邮箱" , 1005),
	MAIL_ADD_NEW_MER("添加新商户" , 1006),
	MAIL_RESET_CODE("重置密码", 1007),
	MAIL_UNBANG_MA("解绑邮箱" , 1008),
	MAIL_MEMBER_RES_MA("会员邮箱注册", 1009),
	MAIL_MERCHANT_RES_MA("商户邮箱注册", 1010),;
	
	/** 枚举值 */
	private int value; 
	
	/** 描述 */
	private String desc;
	
	private PublicTemplateEnum(String desc, int value) {
		this.value = value;
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static PublicTemplateEnum getEnum(int value){
		PublicTemplateEnum resultEnum = null;
		PublicTemplateEnum[] enumAry = PublicTemplateEnum.values();
		for(int i = 0; i<enumAry.length; i++){
			if(value == enumAry[i].getValue()){
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static Map<String, Map<String, Object>> toMap() {
		PublicTemplateEnum[] ary = PublicTemplateEnum.values();
		Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
		for (int num = 0; num < ary.length; num++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String key = String.valueOf(getEnum(ary[num].getValue()));
			map.put("value", String.valueOf(ary[num].getValue()));
			map.put("desc", ary[num].getDesc());
			enumMap.put(key, map);
		}
		return enumMap;
	}
	
	public static String getName(int value){
		String result = null;
		PublicTemplateEnum[] enumAry = PublicTemplateEnum.values();
		for(int i = 0;i<enumAry.length;i++){
			if(value == enumAry[i].getValue()){
				result = enumAry[i].name();
				break;
			}
		}
		return result;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList(){
		PublicTemplateEnum[] ary = PublicTemplateEnum.values();
		List list = new ArrayList();
		for(int i=0;i<ary.length;i++){
			Map<String,String> map = new HashMap<String,String>();
			map.put("value", ary[i].toString());
			map.put("desc", ary[i].getDesc());
			list.add(map);
		}
		return list;
	}
}
 