package it.mm.advancedSearch.core.util;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

public class AdvancedSearchUtil {

	private static final String[] formats;

	static {
		formats = new String[] { 
			DateFormatUtils.ISO_DATE_FORMAT.getPattern(),
			DateFormatUtils.ISO_DATE_TIME_ZONE_FORMAT.getPattern(),
			DateFormatUtils.ISO_DATETIME_FORMAT.getPattern(),
			DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.getPattern(),
			DateFormatUtils.ISO_TIME_FORMAT.getPattern(), 
			DateFormatUtils.ISO_TIME_NO_T_FORMAT.getPattern(),
			DateFormatUtils.ISO_TIME_NO_T_TIME_ZONE_FORMAT.getPattern(),
			DateFormatUtils.ISO_TIME_TIME_ZONE_FORMAT.getPattern(),
			DateFormatUtils.SMTP_DATETIME_FORMAT.getPattern(), 
			"yyyy-MM-dd'T'HH:mm:ss.SSSZ", 
			"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
			"dd/MM/yyyy" 
		};
	}

	public static Date parseDate(String value) {
		try {
			return DateUtils.parseDateStrictly(value, formats);
		} catch (Exception e) {
			throw new RuntimeException("Unable to parse " + value + " to date");
		}
	}

}
