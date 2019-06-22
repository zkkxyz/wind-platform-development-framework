package com.wind.common.utils.string;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * StringTools提供纯字符串操作
 * @author linxiaoqing
 *
 */
public class StringUtils extends org.springframework.util.StringUtils {
	
	public StringUtils() {
	}

	public static List<String> delimitedStringToList(String source, String delim) {
		List<String> result = new ArrayList<String>();
		if (!hasLength(source))
			return result;
		String[] tempStr = source.split(delim);
		for (int i = 0; i < tempStr.length; i++) {
			result.add(tempStr[i]);
		}
		return result;
	}

	public static boolean inList(String source, List<?> list) {
		if ((list == null) || (list.size() == 0) || (!hasLength(source)))
			return false;
		for (int i = 0; i < list.size(); i++) {
			if (source.equals((String) list.get(i))) {
				return true;
			}
		}
		return false;
	}

	public static boolean inArray(String source, String[] arr) {
		if ((arr == null) || (arr.length == 0) || (!hasLength(source)))
			return false;
		for (int i = 0; i < arr.length; i++) {
			if (source.equals(arr[i])) {
				return true;
			}
		}
		return false;
	}

	public static boolean inDelimitedString(String source, String str, String delim) {
		return inArray(source, str.split(delim));
	}

	public static String escapeSBC(String sbcStr) throws UnsupportedEncodingException {
		StringBuffer outStr = new StringBuffer();
		String tempStr = "";
		byte[] b = (byte[]) null;
		try {
			for (int i = 0; i < sbcStr.length(); i++) {
				tempStr = sbcStr.substring(i, i + 1);

				b = tempStr.getBytes("utf-16be");
				if ((b[0] == 48) && (b[1] == 0)) {
					b[0] = 0;
					b[1] = 32;
					outStr.append(new String(b, "utf-16be"));
				} else if (b[0] == -1) {
					b[1] = (byte) (b[1] + 32);
					b[0] = 0;
					outStr.append(new String(b, "utf-16be"));
				} else {
					outStr.append(tempStr);
				}
			}
		} catch (UnsupportedEncodingException e) {
			throw e;
		}
		return escapeSQL(outStr.toString());
	}

	public static String escapeSBCChar(String sbcStr) {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < sbcStr.length(); i++) {
			char c = sbcStr.charAt(i);
			if (c == '　') {
				c = ' ';
				sb.append(c);
			} else if ((c >= 65281) && (c < 65375)) {
				sb.append((char) (c - 65248));
			} else {
				sb.append(sbcStr.charAt(i));
			}
		}
		return escapeSQL(sb.toString());
	}

	public static String escapeSQL(String sql) {
		if ((sql == null) || (sql.trim().length() == 0)) {
			return "";
		}
		return sql.replaceAll("([';]+|(--)+)", "");
	}

	public static boolean escapeSQLByHint(String sql) {
		if ((sql == null) || (sql.trim().length() == 0)) {
			return false;
		}
		String temp = sql;
		return !temp.equals(sql.replaceAll("([';]+|(--)+)", ""));
	}

	public static String escapeSQLLike(String likeStr) {
		String str = StringUtils.replace(likeStr, "_", "/_");
		str = StringUtils.replace(str, "%", "/%");
		return str;
	}

	public static String parseString(Object obj) {
		return obj == null ? null : obj.toString();
	}

	public static String stringValue(String s) {
		return s == null ? "" : s;
	}

	public static String firstCharLowerCase(String str) {
		str = firstChar(str).toLowerCase() + str.substring(1, str.length());
		return str;
	}

	public static String firstCharUpperCase(String str) {
		str = firstChar(str).toUpperCase() + str.substring(1, str.length());
		return str;
	}

	public static String firstChar(String str) {
		return str.substring(0, 1);
	}

	public static String getUUID32() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 获取带横线的长度为36的UUID串.
	 * @author linxiaoqing.
	 * @return uuid.
	 */
	public static String get36UUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 验证一个字符串是否完全由纯数字组成的字符串，当字符串为空时也返回false.
	 * @author linxiaoqing .
	 * @param str 要判断的字符串 .
	 * @return true or false .
	 */
	public static boolean isNumeric(String str) {
		if (StringUtils.isEmpty(str)){
			return false;
		}else{
			return str.matches("\\d*");
		}
	}
	
	/**
	 * 获取字符串长度，当字符串为空时返回0.
	 * @param str .
	 * @return length .
	 */
	public static int strLength(String str){
		if (StringUtils.isEmpty(str)){
			return 0;
		}else{
			return str.length();
		}
	}
	
	/**
	 * 获取字符串的长度，如果有中文，则每个中文字符计为3位 ，当字符串为空时返回0.
	 * 
	 * @param str 字符串 .
	 * @return 字符串的长度 .
	 */
	public static int strLengthCn(String str)
	{
		if (StringUtils.isEmpty(str)){
			return 0;
		}
		int valueLength = 0;
		final String chinese = "[\u0391-\uFFE5]";
		/* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
		for (int num = 0; num < str.length(); num++){
			/* 获取一个字符 */
			final String temp = str.substring(num, num + 1);
			/* 判断是否为中文字符 */
			if (temp.matches(chinese)){
				/* 中文字符长度为3 */
				valueLength += 3;
			} else{
				/* 其他字符长度为1 */
				valueLength += 1;
			}
		}
		return valueLength;
	}
	

	
}