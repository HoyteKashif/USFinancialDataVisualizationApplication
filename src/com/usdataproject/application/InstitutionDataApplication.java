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

public final class InstitutionDataApplication {

	public static void main(String[] args) {

		loadFailureAssistanceTransaction();
		// FdicInstitutionDirectory.readAllFdicDirectoryRecords();
	}

	public static void loadHelpItemFile() {

		try (final Scanner fileScanner = new Scanner(new FileInputStream("helpitem_title_definition.txt"))) {

			final ArrayList<FdicInstitutionDirectoryRecord> lstRecords = new ArrayList<>();

			while (fileScanner.hasNext()) {
				final String line = fileScanner.nextLine();

				if (!line.isEmpty()) {
					final String[] lineArray = line.split(",", 3);

					lstRecords.add(new FdicInstitutionDirectoryRecord(lineArray[0], lineArray[1], lineArray[2]));
				}
			}

			FdicInstitutionDirectory.batchInsert(lstRecords);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void print(final FailureAssistanceTransaction transaction) {
		System.out.println(transaction.getInstitutionName());
		System.out.println(transaction.getCert());
		System.out.println(transaction.getFin());
		System.out.println(transaction.getLocation());
		System.out.println(transaction.getEffectiveDate());
		System.out.println(transaction.getInsFund());
		System.out.println(transaction.getTransactionType());
		System.out.println(transaction.getCharterClass());
		System.out.println(transaction.getFailureOrAssistance());
		System.out.println(transaction.getTotalDeposits());
		System.out.println(transaction.getTotalAssets());
		System.out.println(transaction.getEstimatedLoss());
	}

	public static void loadFailureAssistanceTransaction() {
		try (final Scanner fileScanner = new Scanner(new FileInputStream("data/hsobReportDataOnly.csv"))) {
			final ArrayList<FailureAssistanceTransaction> transactions = new ArrayList<>();

			while (fileScanner.hasNext()) {
				final String line = fileScanner.nextLine();

				if (!line.isEmpty()) {

					final Iterator<String> itr = newIterator(line);

					final FailureAssistanceTransaction transaction = new FailureAssistanceTransaction();
					transaction.setInstitutionName(nextString(itr));
					transaction.setCert(nextInt(itr));
					transaction.setFin(nextInt(itr));
					transaction.setLocation(nextString(itr));
					transaction.setEffectiveDate(getNextDate(itr));
					transaction.setInsFund(nextString(itr));
					transaction.setTransactionType(nextString(itr));
					transaction.setCharterClass(nextString(itr));
					transaction.setFailureOrAssistance(nextString(itr));
					transaction.setTotalDeposits(nextString(itr));
					transaction.setTotalAssets(nextString(itr));
					transaction.setEstimatedLoss(nextString(itr));

					transactions.add(transaction);
				}
			}

			FdicInstitutionDirectory.batchFailureAssistanceTransactionsInsert(transactions);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static Iterator<String> newIterator(final String data) {
		final LinkedList<String> list = new LinkedList<>();

		String s = new String(data);
		while (!s.isEmpty()) {
			if (s.charAt(0) == '"') {
				list.add(s.substring(1, s.indexOf('"', 1)));
				s = s.substring(s.indexOf('"', 1) + 2);
			} else {
				final int commaIdx = s.indexOf(',');
				if (commaIdx > -1) {
					list.add(s.substring(0, commaIdx));
					s = s.substring(s.indexOf(',', 1) + 1);
				} else {
					list.add(s.substring(0));
					s = "";
				}
			}
		}

		return list.iterator();
	}

	private static boolean hasNext(final Iterator<String> itr) {
		if (itr != null && itr.hasNext()) {
			return true;
		}
		return false;
	}

	private static boolean isApplicableValue(final String data) {
		if (data == null) {
			return false;
		}

		final String trimmedValue = data.trim();
		if (trimmedValue.isEmpty()) {
			return false;
		}

		return !"N/A".equals(trimmedValue);
	}

	private static String nextString(final Iterator<String> itr) {
		if (hasNext(itr)) {
			final String string = itr.next().trim();
			if (isApplicableValue(string)) {
				return string;
			}
		}

		return null;
	}

	private static Integer nextInt(final Iterator<String> itr) {
		if (hasNext(itr)) {
			final String string = itr.next().trim();
			if (isApplicableValue(string)) {
				return new Integer(string);
			}
		}

		return null;
	}

	static enum DateFormatEnum {
		M_D_YYYY("\\d{1}/\\d{1}/\\d{4}", "M/d/yyyy"), M_DD_YYYY("\\d{1}/\\d{2}/\\d{4}",
				"M/dd/yyyy"), MM_DD_YYYY("\\d{2}/\\d{2}/\\d{4}", "MM/dd/yyyy");

		private final String regexp;
		private final String format;

		private DateFormatEnum(final String p_regexp, final String p_format) {
			this.regexp = p_regexp;
			this.format = p_format;
		}

		public String getRegExp() {
			return regexp;
		}

		public String getFormat() {
			return format;
		}
	}

	private static Date getNextDate(final Iterator<String> itr) {
		if (hasNext(itr)) {
			final String string = itr.next().trim();
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
