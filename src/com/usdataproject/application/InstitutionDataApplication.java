package com.usdataproject.application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.usdataproject.data.model.FailureAssistanceTransaction;
import com.usdataproject.data.model.FdicInstitutionDirectoryRecord;
import com.usdataproject.util.db.FdicInstitutionDirectory;

public class InstitutionDataApplication {

	public static void main(String[] args) {

		loadFailureAssistanceTransaction();
		// FdicInstitutionDirectory.readAllFdicDirectoryRecords();
	}

	public static void loadHelpItemFile() {

		try (final Scanner fileScanner = new Scanner(new FileInputStream("helpitem_title_definition.txt"))) {

			final ArrayList<FdicInstitutionDirectoryRecord> lstRecords = new ArrayList<>();

			while (fileScanner.hasNext()) {
				final String nextLine = fileScanner.nextLine();

				if (!nextLine.isEmpty()) {
					final String[] lineArray = nextLine.split(",", 3);

					lstRecords.add(new FdicInstitutionDirectoryRecord(lineArray[0], lineArray[1], lineArray[2]));
				}
			}

			FdicInstitutionDirectory.batchInsert(lstRecords);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void loadFailureAssistanceTransaction() {
		try (final Scanner fileScanner = new Scanner(new FileInputStream("data/hsobReportDataOnly.csv"))) {
			final ArrayList<FailureAssistanceTransaction> transactionList = new ArrayList<>();

			while (fileScanner.hasNext()) {
				final String nextLine = fileScanner.nextLine();

				if (!nextLine.isEmpty()) {

					final Iterator<String> iterator = newDataIterator(nextLine);

					final FailureAssistanceTransaction transaction = new FailureAssistanceTransaction();

					// private String institutionName;
					transaction.setInstitutionName(getNextString(iterator));
					// System.out.println(transaction.getInstitutionName());
					// private Integer cert;
					transaction.setCert(getNextInt(iterator));
					// System.out.println(transaction.getCert());
					// private Integer fin;
					transaction.setFin(getNextInt(iterator));
					// System.out.println(transaction.getFin());
					// private String location;
					transaction.setLocation(getNextString(iterator));
					// System.out.println(transaction.getLocation());
					// private Date effectiveDate;
					transaction.setEffectiveDate(getNextDate(iterator));
					// System.out.println(transaction.getEffectiveDate());
					// private String insFund;
					transaction.setInsFund(getNextString(iterator));
					// System.out.println(transaction.getInsFund());
					// private String transactionType;
					transaction.setTransactionType(getNextString(iterator));
					// System.out.println(transaction.getTransactionType());
					// private String charterClass;
					transaction.setCharterClass(getNextString(iterator));
					// System.out.println(transaction.getCharterClass());
					// private String failureOrAssistance;
					transaction.setFailureOrAssistance(getNextString(iterator));
					// System.out.println(transaction.getFailureOrAssistance());
					// private String totalDeposits;
					transaction.setTotalDeposits(getNextString(iterator));
					// System.out.println(transaction.getTotalDeposits());
					// private String totalAssets;
					transaction.setTotalAssets(getNextString(iterator));
					// System.out.println(transaction.getTotalAssets());
					// private String estimatedLossAsOf_12_3_2016;
					transaction.setEstimatedLoss(getNextString(iterator));
					// System.out.println(transaction.getEstimatedLossAsof_12_3_2016());

					transactionList.add(transaction);
				}
			}

			FdicInstitutionDirectory.batchFailureAssistanceTransactionsInsert(transactionList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static Iterator<String> newDataIterator(final String p_strData) {
		String string = new String(p_strData);
		final LinkedList<String> list = new LinkedList<>();

		while (!string.isEmpty()) {
			if (string.charAt(0) == '"') {
				list.add(string.substring(1, string.indexOf('"', 1)));
				string = new String(string.substring(string.indexOf('"', 1) + 2));
			} else {
				final int iCommaIndex = string.indexOf(',');
				if (iCommaIndex >= 0) {
					list.add(string.substring(0, iCommaIndex));
					string = new String(string.substring(string.indexOf(',', 1) + 1));
				} else {
					list.add(string.substring(0));
					string = "";
				}

			}
		}

		return list.iterator();
	}

	private static boolean hasNext(final Iterator<String> p_oIterator) {
		if (null != p_oIterator && p_oIterator.hasNext()) {
			return true;
		}
		return false;
	}

	private static boolean isApplicableValue(final String p_strData) {
		if (null == p_strData) {
			return false;
		}

		final String trimmedValue = p_strData.trim();
		if (trimmedValue.isEmpty()) {
			return false;
		}

		return !"N/A".equals(trimmedValue);
	}

	private static String getNextString(final Iterator<String> p_oIterator) {
		if (hasNext(p_oIterator)) {
			final String string = p_oIterator.next().trim();
			if (isApplicableValue(string)) {
				return string;
			}
		}

		return null;
	}

	private static Integer getNextInt(final Iterator<String> p_oIterator) {
		if (hasNext(p_oIterator)) {
			final String string = p_oIterator.next().trim();
			if (isApplicableValue(string)) {
				return new Integer(string);
			}
		}

		return null;
	}

	static enum DateFormatEnum {
		M_D_YYYY("\\d{1}/\\d{1}/\\d{4}", "M/d/yyyy"), M_DD_YYYY("\\d{1}/\\d{2}/\\d{4}",
				"M/dd/yyyy"), MM_DD_YYYY("\\d{2}/\\d{2}/\\d{4}", "MM/dd/yyyy");

		private final String strRegExp;
		private final String strFormat;

		private DateFormatEnum(final String p_strRegExp, final String p_strFormat) {
			this.strRegExp = p_strRegExp;
			this.strFormat = p_strFormat;
		}

		public String getRegExp() {
			return strRegExp;
		}

		public String getFormat() {
			return strFormat;
		}
	}

	private static Date getNextDate(final Iterator<String> p_oIterator) {
		if (hasNext(p_oIterator)) {
			final String string = p_oIterator.next().trim();
			if (isApplicableValue(string)) {
				if (Pattern.compile("\\d{2}/\\d{2}/\\d{4}").matcher(string).find()) {
					return Date.valueOf(LocalDate.from(DateTimeFormatter.ofPattern("MM/dd/yyyy").parse(string)));
				} else if (Pattern.compile("\\d{1}/\\d{2}/\\d{4}").matcher(string).find()) {
					return Date.valueOf(LocalDate.from(DateTimeFormatter.ofPattern("M/dd/yyyy").parse(string)));
				} else if (Pattern.compile("\\d{1}/\\d{1}/\\d{4}").matcher(string).find()) {
					return Date.valueOf(LocalDate.from(DateTimeFormatter.ofPattern("M/d/yyyy").parse(string)));
				} else {
					System.out.println("-------------------Unable to parse: " + string);
				}
			}
		}

		return null;
	}
}
