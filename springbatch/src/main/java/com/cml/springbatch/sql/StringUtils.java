package com.cml.springbatch.sql;

public class StringUtils {
	public static boolean isBlank(String value) {
		return null == value || value.trim().length() == 0;
	}

	public static boolean isNotBlank(String value) {
		return null != value && value.trim().length() > 0;
	}
}
