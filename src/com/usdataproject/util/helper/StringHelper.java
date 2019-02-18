package com.usdataproject.util.helper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public final class StringHelper {

	public static final String EMPTY_STRING = "";

	public static String getTrimmed(final String data) {
		if (data == null) {
			return "";
		}

		return data.trim();
	}

	public static String getTrimmedStringOrNull(final String data) {
		if (data == null || data.trim().isEmpty()) {
			return null;
		}

		return data.trim();
	}

	public static boolean isEmpty(final String data) {
		return !hasText(data);
	}

	public static boolean hasText(final String data) {
		return data != null && !data.trim().isEmpty();
	}

	public static Boolean booleanOrNull(final char data) {
		if (Character.isDigit(data)) {
			int n = Character.getNumericValue(data);
			if (n == 1) {
				return true;
			}
			if (n == 0) {
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
		if (validFloat(data)) {
			return Float.valueOf(data.trim());
		}

		return null;
	}

	public static Byte byteOrNull(final String data) {
		if (isEmpty(data)) {
			return null;
		}

		return Byte.valueOf(data.trim());
	}

	public static Integer integerOrNull(final String data) {
		if (isEmpty(data)) {
			return null;
		}

		if (validInteger(data)) {
			return new Integer(data);
		}

		return null;
	}

	public static BigInteger bigIntegerOrNull(final String data) {
		if (isEmpty(data)) {
			return null;
		}

		if (validBigInteger(data)) {
			return new BigInteger(data);
		}

		return null;
	}

	public static BigDecimal bigDecimalOrNull(final String data) {
		if (isEmpty(data)) {
			return null;
		}

		if (validBigDecimal(data)) {
			return new BigDecimal(data);
		}

		return null;
	}

	public static String stringOrNull(final String data) {
		if (isEmpty(data)) {
			return null;
		}

		return data.trim();
	}

	public static boolean validFloat(final String data) {
		if (isEmpty(data)) {
			return false;
		}

		try {
			Float.valueOf(data.trim());
			return true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean validInteger(final String data) {
		if (isEmpty(data)) {
			return false;
		}

		try {
			Integer.parseInt(data.trim());
			return true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean validBigDecimal(final String data) {
		if (isEmpty(data)) {
			return false;
		}

		try {
			new BigDecimal(data.trim());
			return true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean validBigInteger(final String data) {
		if (isEmpty(data)) {
			return false;
		}

		try {
			new BigInteger(data.trim());
			return true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean isNumeric(final String data) {
		return isNumeric(data, true);
	}

	public static boolean isNumeric(final String data, final boolean p_bWholeNumber) {
		if (isEmpty(data)) {
			return false;
		}

		try {
			if (p_bWholeNumber) {
				new BigDecimal(data);
			} else {
				Integer.parseInt(data);
			}
			return true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}

		// for (char c : data.trim().toCharArray()) {
		// if (!Character.isDigit(c)) {
		// if (p_bWholeNumber) {
		// return false;
		// } else if (c != '.') {
		// return false;
		// } else if (data.indexOf('.') != data.lastIndexOf('.')) {
		// return false;
		// }
		// }
		// }
		// return true;
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

			for (DatePattern eDate : values()) {
				if (Pattern.compile(eDate.pattern).matcher(data).find()) {
					if (eDate.equals(DDMMMYYY)) {
						// due to the date possibly being in the wrong format
						// change the capitalization of the last to characters
						// in the month
						String month = data.substring(2, 5);
						month = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();
						String date = data.substring(0, 2) + month + data.substring(5);
						return Date.valueOf(LocalDate.from(DateTimeFormatter.ofPattern(eDate.format).parse(date)));
					} else {
						return Date.valueOf(LocalDate.from(DateTimeFormatter.ofPattern(eDate.format).parse(data)));
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

	private StringHelper() {
	}
}
