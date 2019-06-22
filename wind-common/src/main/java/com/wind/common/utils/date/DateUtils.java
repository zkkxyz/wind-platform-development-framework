package com.wind.common.utils.date;


import com.wind.common.utils.check.CheckUtils;
import com.wind.common.utils.string.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {
	private static ThreadLocal<Map<String, SimpleDateFormat>> CACHE = new ThreadLocal();
	public static final String DEF_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String HHMMSS = "HHmmss";
	public static final String HH_MM_SS = "HH:mm:ss";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
	public static final String YYYYMMDDHHMMSSSSSSSS = "yyyyMMddHHmmssSSSSSS";
	public static final String HHMMSSSSSSSS = "HHmmssSSSSSS";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String MM_DD_YYYY = "MM-dd-yyyy";
	public static final String MM_DD_YYYY_HH_MM = "MM-dd-yyyy HH:mm";
	public static final String MM_DD_YYYY_HH_MM_SS = "MM-dd-yyyy HH:mm:ss";
	public static final String yyyyMMdd_HHmmss = "yyyyMMdd_HHmmss";

	public static Calendar getSystemCurrentTime() {
		return Calendar.getInstance();
	}

	public static Date getCurrentTime() {
		return Calendar.getInstance().getTime();
	}

	public static Timestamp convCalendarToTimestamp(Calendar date) {
		return convDateToTimestamp(date.getTime());
	}

	public static Calendar convTimestampToCalendar(Timestamp date) {
		if (date != null) {
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTimeInMillis(date.getTime());
			return gc;
		}
		return null;
	}

	public static Timestamp convDateToTimestamp(Date date) {
		 CheckUtils.notNull(date);
		return new Timestamp(date.getTime());
	}

	public static Date convTimestampToDate(Timestamp date) {
		 CheckUtils.notNull(date);
		return new Date(date.getTime());
	}

	public static Date parseDate(String date, String format) {
		 CheckUtils.hasLength(date);
		format =  StringUtils.hasLength(format) ? format
				: "yyyy-MM-dd HH:mm:ss";
		Map map = (Map) CACHE.get();
		if (map == null) {
			map = new HashMap();
			CACHE.set(map);
		}
		SimpleDateFormat f = null;
		if (map.containsKey(format)) {
			f = (SimpleDateFormat) map.get(format);
		} else {
			f = new SimpleDateFormat(format);
			map.put(format, f);
		}
		try {
			return f.parse(date);
		} catch (ParseException e) {
			
			  throw new RuntimeException("Date Parse Error", e);
		}
	}

	public static Date parseDate(String date) {
		return parseDate(date, null);
	}

	public static Date addForYEAR(Date date, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(1, amount);
		return calendar.getTime();
	}

	public static Date addForMONTH(Date date, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(2, amount);
		return calendar.getTime();
	}

	public static Date addForDAY(Date date, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(6, amount);
		return calendar.getTime();
	}

	public static Date add(Date date, int field, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	public static Calendar parseCalendar(String date, String format) {
		if ( StringUtils.hasLength(date)) {
			Date result = parseDate(date, format);
			Calendar cal = new GregorianCalendar(0, 0, 0, 0, 0, 0);
			cal.setTime(result);
			return cal;
		}
		return null;
	}

	public static Calendar parseCalendar(String date) {
		return parseCalendar(date, null);
	}

	public static String dateToString(Date date, String format) {
		 CheckUtils.notNull(date);
		format =  StringUtils.hasLength(format) ? format
				: "yyyy-MM-dd HH:mm:ss";
		Map map = (Map) CACHE.get();
		if (map == null) {
			map = new HashMap();
			CACHE.set(map);
		}
		SimpleDateFormat f = null;
		if (map.containsKey(format)) {
			f = (SimpleDateFormat) map.get(format);
		} else {
			f = new SimpleDateFormat(format);
			map.put(format, f);
		}
		return f.format(date);
	}

	public static String dateToString(Date date) {
		return dateToString(date, null);
	}

	public static boolean isTheEndOfMonth(Date date) {
		Calendar b = Calendar.getInstance();
		b.setTime(date);
		int lastDay = b.getActualMaximum(5);
		int now = b.get(5);

		return now == lastDay;
	}

	public static int getDayOfWeek(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal.get(7);
	}

	public static List<Date> sortDays(List<Date> list) {
		Collections.sort(list, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				if (((Date) arg0).getTime() > ((Date) arg1).getTime())
					return 1;
				if (((Date) arg0).getTime() < ((Date) arg1).getTime()) {
					return -1;
				}
				return 0;
			}
		});
		return list;
	}

	public static boolean isHoliday(Date date, List<?> holidays) {
		if ((holidays != null) && (holidays.size() > 0)) {
			for (int j = 0; j < holidays.size(); j++) {
				Date holiday = (Date) holidays.get(j);
				if (getDateDiff(holiday, date) == 0) {
					return true;
				}
			}
		}
		return false;
	}

	public static int getDateDiff(Date before, Date after) {
		if ((before == null) || (after == null))
			return 0;
		Calendar calendar1 = new GregorianCalendar();
		Calendar calendar2 = new GregorianCalendar();
		calendar1.setTime(before);
		calendar1.set(11, 0);
		calendar1.set(12, 0);
		calendar1.set(13, 0);
		calendar1.set(14, 0);
		calendar2.setTime(after);
		calendar2.set(11, 0);
		calendar2.set(12, 0);
		calendar2.set(13, 0);
		calendar2.set(14, 0);
		int diff = (int) ((calendar2.getTime().getTime() - calendar1.getTime()
				.getTime()) / 86400000L);
		return diff;
	}

	public static boolean isWorkDay(Date date, List<?> holidays,
			List<?> weekEndWorkDays) {
		int day_of_week = getDayOfWeek(date);
		if ((day_of_week == 1) && (!isHoliday(date, weekEndWorkDays)))
			return false;
		if ((day_of_week == 7) && (!isHoliday(date, weekEndWorkDays))) {
			return false;
		}

		return (holidays == null) || (holidays.size() <= 0)
				|| (!isHoliday(date, holidays));
	}

	private static Date getWorkDay(Date curDate, int num, List<?> holidays,
			List<?> weekEndWorkDays, boolean added) {
		Date endDate = curDate;
		Calendar cal = Calendar.getInstance();
		for (int i = 0; i < num; i++) {
			cal.setTime(endDate);
			if (added)
				cal.add(5, 1);
			else {
				cal.add(5, -1);
			}
			endDate = cal.getTime();
			if (!isWorkDay(endDate, holidays, weekEndWorkDays)) {
				i--;
			}
		}
		return endDate;
	}

	public static Date getForwardWorkDay(Date curDate, int num,
			List<?> holidays, List<?> weekEndWorkDays) {
		return getWorkDay(curDate, num, holidays, weekEndWorkDays, false);
	}

	public static Date getNextWorkDay(Date curDate, int num, List<?> holidays,
			List<?> weekEndWorkDays) {
		return getWorkDay(curDate, num, holidays, weekEndWorkDays, true);
	}

	public static boolean getDateIsAfter(Date before, Date after) {
		if ((before == null) || (after == null))
			return false;
		int diff = getDateDiff(before, after);

		return diff < 0;
	}

	public static boolean getDateIsBefore(Date before, Date after) {
		if ((before == null) || (after == null))
			return false;
		int diff = getDateDiff(before, after);

		return diff > 0;
	}

	public static int getAbsDateDiff(Date before, Date after) {
		int diff = getDateDiff(before, after);
		return Math.abs(diff);
	}
}