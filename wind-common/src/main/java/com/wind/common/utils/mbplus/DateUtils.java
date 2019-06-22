package com.wind.common.utils.mbplus;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	/** Ĭ�ϵ����ڸ�ʽ.yyyy-MM-dd HH:mm:ss */
	public static String STR_DEFAULT_DATE_FORMAT_WITH_SPLIT = "yyyy-MM-dd HH:mm:ss";

	/** ���ڸ�ʽ.yyyyMMddHHmmss */
	public static String STR_DEFAULT_DATE_FORMAT_WITHOUT_SPLIT = "yyyyMMddHHmmss";

	/** �����ļ����������ڸ�ʽ.yyyyMMdd_HHmmss */
	public static String STR_NAME_FILE_DATE_FORMAT = "yyyyMMdd_HHmmss";

	/** DAY���ڸ�ʽ. yyyy-MM-dd */
	public static String STR_DATE_FORMAT_DAY_WITH_SPLIT = "yyyy-MM-dd";

	/** DAY���ڸ�ʽ. yyyyMMdd */
	public static String STR_DATE_FORMAT_DAY_WITHOUT_SPLIT = "yyyyMMdd";

	/** DAY���ڸ�ʽ. yyyy-MM */
	public static String STR_DATE_FORMAT_MONTH_WITH_SPLIT = "yyyy-MM";

	/** DAY���ڸ�ʽ. yyyyMM */
	public static String STR_DATE_FORMAT_MONTH_WITHOUT_SPLIT = "yyyyMM";

	/** DAY���ڸ�ʽ. yyyy */
	public static String STR_DATE_FORMAT_YEAR = "yyyy";

	/** �������ڸ�ʽ. yyyy-MM-dd HH:mm */
	public static String STR_DATE_FORMAT_MINUTE = "yyyy-MM-dd HH:mm";

	public static Date getDate(String datestr, String format) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			dateFormat.setLenient(true);
			return dateFormat.parse(datestr);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static Date string2Date(String date, String dateFormat) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			long time = sdf.parse(date).getTime();
			return new Date(time);
		}
		catch (Exception e) {
			return null;
		}
	}
}
