package com.usdataproject.util.helper;

public class ValidationHelper {

	
	
	public static String getTrimmedStringOrNull(final String p_strData) {
		if (null != p_strData) {
			final String trimmedData = p_strData.trim();
			if (!trimmedData.isEmpty()) {
				return trimmedData;
			}
		}
		return null;
	}
	
	
}
