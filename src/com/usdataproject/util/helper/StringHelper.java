package com.usdataproject.util.helper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import sun.applet.Main;

public class StringHelper {

	public static final String EMPTY_STRING = "";

	public static boolean isEmpty(final String p_strData) {
		return !hasText(p_strData);
	}

	public static boolean hasText(final String data) {
		if (null == data) {
			return false;
		} else if (data.trim().isEmpty()) {
			return false;
		}

		return true;
	}

	public static Boolean booleanOrNull(final char data) {
		if (Character.isDigit(data)) {
			int number = Character.getNumericValue(data);
			if (number == 1) {
				return true;
			}
			if (number == 0) {
				return false;
			}

			throw new IllegalArgumentException("Value of character is not 0 or 1");
		} else if (data == 'y' || data == 'Y') {
			return true;
		} else if (data == 'n' || data == 'N') {
			return false;
		}

		return null;
	}

	public static void main(String[] args) {
		System.out.println(Integer.valueOf("0001"));
	}

	public static Boolean booleanOrNull(final String data) {
		if (isEmpty(data)) {
			return null;
		}

		final String trimmedString = data.trim();
		if (trimmedString.length() == 1) {
			return booleanOrNull(trimmedString.charAt(0));
		} else if (isNumeric(trimmedString)) {
			// remove additional leading/repeating zeros
			final String iString = Integer.valueOf(trimmedString).toString();
			if (iString.length() == 1) {
				return booleanOrNull(iString.charAt(0));
			}
		}

		throw new IllegalArgumentException("Unable to construct a Boolean given " + trimmedString + ".");
	}

	public static Character charOrNull(final String data) {
		if (isEmpty(data)) {
			return null;
		}

		if (data.trim().length() > 1) {
			throw new IllegalArgumentException();
		}
		return data.trim().charAt(0);
	}

	public static Float floatOrNull(final String data) {
		if (isEmpty(data)) {
			return null;
		}

		if (data.charAt(0) == '-' && isNumeric(data.substring(1), false)) {
			return Float.valueOf(data.trim());
		} else if (isNumeric(data, false)) {
			return Float.valueOf(data.trim());
		}

		throw new IllegalArgumentException("Unable to parse " + data + " to a Float.");
	}

	public static Byte byteOrNull(final String data) {
		if (hasText(data)) {
			return Byte.valueOf(data.trim());
		}

		return null;
	}

	public static Integer integerOrNull(final String data) {
		if (hasText(data)) {
			if (isNumeric(data)) {
				return new Integer(data);
			}
		}

		return null;
	}

	public static BigInteger bigIntegerOrNull(final String data) {
		if (hasText(data)) {
			if (isNumeric(data)) {
				return new BigInteger(data);
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

	public static String stringOrNull(final String data) {
		if (isEmpty(data)) {
			return null;
		}

		return data.trim();
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
					} else if (c != '.') {
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

	public static enum DatePattern {
		MM_DD_YYYY("\\d{2}/\\d{2}/\\d{4}", "MM/dd/yyyy"),
		//
		M_DD_YYYY("\\d{1}/\\d{2}/\\d{4}", "M/dd/yyyy"),
		//
		M_D_YYYY("\\d{1}/\\d{1}/\\d{4}", "M/d/yyyy"),
		//
		YYYY_MM_DD("\\d{4}-\\d{2}-\\d{2}", "yyyy - MM - dd"),
		//
		DDMMMYYY("\\d{2}[a-zA-Z]{3}\\d{4}", "ddMMMyyyy");

		private final String pattern;
		private final String format;

		DatePattern(final String pattern, final String format) {
			this.pattern = pattern;
			this.format = format;
		}

		public String getPattern() {
			return pattern;
		}

		public String getFormat() {
			return format;
		}

		public static Date parse(final String data) {
			if (isEmpty(data)) {
				return null;
			}

			for (DatePattern dtPtrn : values()) {
				if (Pattern.compile(dtPtrn.pattern).matcher(data).find()) {
					if (dtPtrn.equals(DDMMMYYY)) {
						// due to the date possibly being in the wrong format
						// change the capitalization of the last to characters
						// in the month
						String month = data.substring(2, 5);
						month = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();
						String date = data.substring(0, 2) + month + data.substring(5);
						return Date.valueOf(LocalDate.from(DateTimeFormatter.ofPattern(dtPtrn.format).parse(date)));
					} else {
						return Date.valueOf(LocalDate.from(DateTimeFormatter.ofPattern(dtPtrn.format).parse(data)));
					}
				}
			}

			throw new IllegalArgumentException("Unable to parse: " + data);
		}
	}

	public static Date dateOrNull(final String data) {
		if (isEmpty(data)) {
			return null;
		} else if (Pattern.compile("\\d{2}/\\d{2}/\\d{4}").matcher(data).find()) {
			return Date.valueOf(LocalDate.from(DateTimeFormatter.ofPattern("MM/dd/yyyy").parse(data)));
		} else if (Pattern.compile("\\d{1}/\\d{2}/\\d{4}").matcher(data).find()) {
			return Date.valueOf(LocalDate.from(DateTimeFormatter.ofPattern("M/dd/yyyy").parse(data)));
		} else if (Pattern.compile("\\d{1}/\\d{1}/\\d{4}").matcher(data).find()) {
			return Date.valueOf(LocalDate.from(DateTimeFormatter.ofPattern("M/d/yyyy").parse(data)));
		} else if (Pattern.compile("\\d{4}-\\d{2}-\\d{2}").matcher(data).find()) {
			return Date.valueOf(LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(data)));
		} else if (Pattern.compile("\\d{2}[a-zA-Z]{3}\\d{4}").matcher(data).find()) {
			// due to the date possibly being in the wrong format
			// change the capitalization of the last to characters in the month
			String month = data.substring(2, 5);
			month = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();
			String date = data.substring(0, 2) + month + data.substring(5);
			return Date.valueOf(LocalDate.from(DateTimeFormatter.ofPattern("ddMMMyyyy").parse(date)));
		} else {
			throw new IllegalArgumentException("Unable to parse: " + data);
		}
	}
}
