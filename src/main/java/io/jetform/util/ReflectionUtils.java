package io.jetform.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReflectionUtils {

	public static Object parse(String value, Class type) {
		Object result = null;
		if (value == null) {
			return result;
		}
		try {
			if (isNumericType(type)) {
				if (type == long.class || type == Long.class) {
					result = Long.parseLong(value);
				} else if (type == int.class || type == Integer.class) {
					result = Integer.parseInt(value);
				} else if (type == float.class || type == Float.class) {
					result = Float.parseFloat(value);
				} else if (type == double.class || type == Double.class) {
					result = Double.parseDouble(value);
				}
			} else if (type == boolean.class || type == Boolean.class) {
				result = Boolean.parseBoolean(value);
			} else if (type == Date.class) {
				result = SOURCE_DATE_FORMAT.parse(value);
			} else if (type == String.class) {
				result = value;
			}
		} catch (Exception e) {
//			LOGGER.info(e.getMessage());
		}
		return result;
	}

	public static boolean isNumericType(Class type) {
		return (type == int.class || type == Integer.class || type == long.class || type == Long.class
				|| type == float.class || type == Float.class || type == double.class || type == Double.class);
	}

	public static boolean isNumericValue(Object value) {
		Class type = value.getClass();
		return (type == int.class || type == Integer.class || type == long.class || type == Long.class
				|| type == float.class || type == Float.class || type == double.class || type == Double.class);
	}

	public static boolean isBooleanType(Class type) {
		return (type == boolean.class || type == Boolean.class);
	}
	
	final static DateFormat SOURCE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
}
