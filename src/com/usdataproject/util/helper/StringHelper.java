package com.usdataproject.util.helper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class StringHelper {

	public static boolean isEmpty(final String p_strData) {
		return !hasText(p_strData);
	}

	public static boolean hasText(final String p_strData) {
		if (null != p_strData) {
			final String trimmedString = p_strData.trim();
			if (!trimmedString.isEmpty()) {
				return true;
			}
		}

		return false;
	}

	public static Byte byteOrNull(final String data) {
		if (hasText(data)) {
			return Byte.valueOf(data);
		}

		return null;
	}

	public static Integer integerOrNull(final String p_strData) {
		if (hasText(p_strData)) {
			if (isNumeric(p_strData)) {
				return new Integer(p_strData);
			}
		}

		return null;
	}

	public static BigInteger bigIntegerOrNull(final String p_strData) {
		if (hasText(p_strData)) {
			if (isNumeric(p_strData)) {
				return new BigInteger(p_strData);
			}
		}

		return null;
	}

	public static BigDecimal bigDecimalOrNull(final String p_strData) {
		if (hasText(p_strData)) {
			if (isNumeric(p_strData, false)) {
				return new BigDecimal(p_strData);
			}
		}

		return null;
	}

	public static String stringOrNull(final String p_strData) {
		if (null != p_strData) {
			final String trimmedData = p_strData.trim();
			if (!trimmedData.isEmpty()) {
				return trimmedData;
			}
		}
		return null;
	}

	public static Integer parseInteger(final String p_strData) {
		if (isNumeric(p_strData)) {
			return new Integer(p_strData);
		}

		return null;
	}

	public static BigDecimal parseDecimal(final String p_strData) {
		if (isNumeric(p_strData, false)) {
			return new BigDecimal(p_strData);
		}

		return null;
	}

	public static boolean isNumeric(final String p_strData) {
		return isNumeric(p_strData, true);
	}

	public static boolean isNumeric(final String p_strData, final boolean p_bWholeNumber) {
		final String string = stringOrNull(p_strData);

		if (hasText(string)) {
			for (char c : string.toCharArray()) {
				if (!Character.isDigit(c)) {
					if (p_bWholeNumber) {
						return false;
					} else if (p_strData.indexOf('.') != p_strData.lastIndexOf('.')) {
						return false;
					}
				}
			}

			return true;
		}

		return false;
	}

	public static Date dateOrNull(final String string) {
		if (Pattern.compile("\\d{2}/\\d{2}/\\d{4}").matcher(string).find()) {
			return Date.valueOf(LocalDate.from(DateTimeFormatter.ofPattern("MM/dd/yyyy").parse(string)));
		} else if (Pattern.compile("\\d{1}/\\d{2}/\\d{4}").matcher(string).find()) {
			return Date.valueOf(LocalDate.from(DateTimeFormatter.ofPattern("M/dd/yyyy").parse(string)));
		} else if (Pattern.compile("\\d{1}/\\d{1}/\\d{4}").matcher(string).find()) {
			return Date.valueOf(LocalDate.from(DateTimeFormatter.ofPattern("M/d/yyyy").parse(string)));
		} else if (Pattern.compile("\\d{4}-\\d{2}-\\d{2}").matcher(string).find()) {
			return Date.valueOf(LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(string)));
		} else {
			System.out.println("-------------------Unable to parse: " + string);
		}
		return null;
	}
}
