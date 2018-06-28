package com.usdataproject.application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.JSpinner.DateEditor;

import com.usdataproject.util.helper.InstitutionDataStaticValues.InstitutionDataColumnEnum;
import com.usdataproject.util.helper.StringHelper;

public class UsFinanceFileParseApplication {
	// STNAME,CERT,DOCKET,ACTIVE,ADDRESS,ASSET,
	// BKCLASS,CHANGEC1,CHANGEC2,CHANGEC3,CHANGEC4,CHANGEC5,
	// CHARTER,CHRTAGNT,CONSERVE,CITY,CLCODE,CMSA_NO,CMSA,
	// COUNTY,DATEUPDT,DENOVO,DEP,EFFDATE,ENDEFYMD,EQ,ESTYMD,
	// FDICDBS,FDICREGN,FDICSUPV,FED,FED_RSSD,FEDCHRTR,FLDOFF,IBA,INACTIVE,
	// TODO-still-adding
	// INSAGNT1,INSAGNT2,INSDATE,INSTCRCD,INSBIF,INSCOML,INSDIF,INSFDIC,
	// INSSAIF,INSSAVE,MSA_NO,MSA,NAME,NEWCERT,OAKAR,OTSDIST,OTSREGNM,
	// PROCDATE,QBPRCOML,REGAGNT,REPDTE,RISDATE,STCHRTR,ROA,ROAQ,ROE,
	// ROEQ,RUNDATE,SASSER,LAW_SASSER_FLG,STALP,STCNTY,STNUM,ZIP,SUPRV_FD,
	// OCCDIST,UNINUM,ULTCERT,CFPBEFFDTE,CFPBENDDTE,CFPBFLAG,REGAGENT2,
	// TE01N528,TE02N528,TE03N528,TE04N528,TE05N528,TE06N528,TE07N528,
	// TE08N528,TE09N528,TE10N528,TE01N529,TE02N529,TE03N529,TE04N529,
	// TE05N529,TE06N529,WEBADDR,OFFICES,CERTCONS,PARCERT,CITYHCR,DEPDOM,
	// FORM31,HCTMULT,INSTAG,MUTUAL,NAMEHCR,NETINC,NETINCQ,OFFDOM,OFFFOR,
	// OFFOA,RSSDHCR,STALPHCR,STMULT,SUBCHAPS,ROAPTX,ROAPTXQ,TRUST,SPECGRP,
	// SPECGRPN,TRACT,CSA,CSA_NO,CSA_FLG,CBSA,CBSA_NO,CBSA_METRO_NAME,
	// CBSA_METRO,CBSA_METRO_FLG,CBSA_MICRO_FLG,CBSA_DIV,CBSA_DIV_NO,CBSA_DIV_FLG,CB

	public static void main(String[] args) {
		try (final Scanner fileScanner = new Scanner(new FileInputStream("data/institutions2_dataOnly.csv"))) {

			while (fileScanner.hasNext()) {

				final String nextLine = fileScanner.nextLine();

				if (!nextLine.isEmpty()) {
					final List<String> list = newDataIterator(nextLine);

					// STNAME (state_name)
					// System.out.print(stringOrNull(list.get(InstitutionDataColumnEnum.STNAME.ordinal())));
					// CERT (fdic_cert)
					// System.out.println(integerOrNull(list.get(InstitutionDataColumnEnum.CERT.ordinal())));
					// DOCKET (ots_docket_number)
					// System.out.println(integerOrNull(list.get(InstitutionDataColumnEnum.DOCKET.ordinal())));
					// ACTIVE (active)
					// System.out.println(booleanOrNull(charOrNull(list.get(InstitutionDataColumnEnum.ACTIVE.ordinal()))));
					// ADDRESS
					// System.out.println(stringOrNull(list.get(InstitutionDataColumnEnum.ADDRESS.ordinal())));
					// ASSET
					// System.out.println(StringHelper
					// .bigIntegerOrNull(list.get(InstitutionDataColumnEnum.ASSET.ordinal()).replaceAll(",",
					// "")));
					// BKCLASS
					// System.out.println(stringOrNull(list.get(InstitutionDataColumnEnum.BKCLASS.ordinal())));
					// CHANGEC1 TODO-FIXME: LOAD INTO DATABASE AS A CHANGE CODE
					// DICTIONARY ID
					// System.out.println(
					// StringHelper.integerOrNull(list.get(InstitutionDataColumnEnum.CHANGEC1.ordinal())));
					// CHANGEC2 TODO-FIXME: LOAD INTO DATABASE AS A CHANGE CODE
					// DICTIONARY ID
					// System.out.println(
					// StringHelper.integerOrNull(list.get(InstitutionDataColumnEnum.CHANGEC2.ordinal())));
					// CHANGEC3 TODO-FIXME: LOAD INTO DATABASE AS A CHANGE CODE
					// DICTIONARY ID
					// System.out.println(
					// StringHelper.integerOrNull(list.get(InstitutionDataColumnEnum.CHANGEC3.ordinal())));
					// CHANGEC4 TODO-FIXME: LOAD INTO DATABASE AS A CHANGE CODE
					// DICTIONARY ID
					// System.out.println(
					// StringHelper.integerOrNull(list.get(InstitutionDataColumnEnum.CHANGEC4.ordinal())));
					// CHANGEC5 TODO-FIXME: LOAD INTO DATABASE AS A CHANGE CODE
					// DICTIONARY ID
					// System.out.println(
					// StringHelper.integerOrNull(list.get(InstitutionDataColumnEnum.CHANGEC5.ordinal())));
					// CHARTER
					// System.out
					// .println(StringHelper.integerOrNull(list.get(InstitutionDataColumnEnum.CHARTER.ordinal())));
					// CHRTAGNT
					// System.out.println(stringOrNull(list.get(InstitutionDataColumnEnum.CHRTAGNT.ordinal())));
					// CONSERVE (Y|N)
					// System.out.println(booleanOrNull(charOrNull(list.get(InstitutionDataColumnEnum.ACTIVE.ordinal()))));
					// CITY
					// System.out.println(list.get(InstitutionDataColumnEnum.CITY.ordinal()));
					// CLCODE
					// System.out.println(integerOrNull(list.get(InstitutionDataColumnEnum.CLCODE.ordinal())));
					// CMSA_NO
					// System.out.println(integerOrNull(list.get(InstitutionDataColumnEnum.CMSA_NO.ordinal())));
					// CMSA
					// System.out.println(stringOrNull(list.get(InstitutionDataColumnEnum.CMSA.ordinal())));
					// COUNTY TODO-FIXME: Create Dicitionary Values
					// System.out.println(stringOrNull(list.get(InstitutionDataColumnEnum.COUNTY.ordinal())));
					// DATEUPDT
					// System.out.println(dateOrNull(list.get(InstitutionDataColumnEnum.DATEUPDT.ordinal())));
					// DENOVO
					// System.out.println(stringOrNull(list.get(InstitutionDataColumnEnum.DENOVO.ordinal())));
					// DEP
					// System.out.println(integerOrNull(list.get(InstitutionDataColumnEnum.DEP.ordinal()).replaceAll(",",
					// "")));
					// EFFDATE
					// System.out.println(dateOrNull(list.get(InstitutionDataColumnEnum.EFFDATE.ordinal())));
					// ENDEFYMD
					// System.out.println(dateOrNull(list.get(InstitutionDataColumnEnum.ENDEFYMD.ordinal())));
					// EQ
					// System.out.println(
					// integerOrNull(list.get(InstitutionDataColumnEnum.EQ.ordinal()).replaceAll(",",
					// "")));
					// ESTYMD
					// System.out.println(dateOrNull(list.get(InstitutionDataColumnEnum.ESTYMD.ordinal())));
					// FDICDBS max of 2 digits
					// System.out.println(Byte.valueOf((list.get(InstitutionDataColumnEnum.FDICDBS.ordinal()))));
					// FDICREGN TODO-FIXME: Create Dictionary Values
					// {FDICCREGN,FDICSUPV,FLDOFF}
					// System.out.println(stringOrNull(list.get(InstitutionDataColumnEnum.FDICREGN.ordinal())));
					// FDICSUPV TODO-FIXME: Create Dictionary Values
					// {FDICCREGN,FDICSUPV,FLDOFF}
					// System.out.println(stringOrNull(list.get(InstitutionDataColumnEnum.FDICSUPV.ordinal())));
					// FED
					// System.out.println(Byte.valueOf(list.get(InstitutionDataColumnEnum.FED.ordinal())));
					// FED_RSSD {minimum=0 and max=5143788}
					// System.out.println(integerOrNull(list.get(InstitutionDataColumnEnum.FED_RSSD.ordinal())));
					// FEDCHRTR {false=0,true=1}
					// System.out.println(booleanOrNull(list.get(InstitutionDataColumnEnum.FEDCHRTR.ordinal())));
					// FLDOFF TODO-FIXME: Create Dictionary Values
					// {FDICCREGN,FDICSUPV,FLDOFF}
					// System.out.println(stringOrNull(list.get(InstitutionDataColumnEnum.FLDOFF.ordinal())));
					// IBA
					// System.out.println(booleanOrNull(list.get(InstitutionDataColumnEnum.IBA.ordinal())));
					// INACTIVE
					// System.out.println(booleanOrNull(list.get(InstitutionDataColumnEnum.INACTIVE.ordinal())));
					// INSAGNT1
					// System.out.println(stringOrNull(list.get(InstitutionDataColumnEnum.INSAGNT1.ordinal())));
					// INSAGNT2
					// System.out.println(stringOrNull(list.get(InstitutionDataColumnEnum.INSAGNT2.ordinal())));
					// INSDATE
					// System.out.println(StringHelper.dateOrNull(list.get(InstitutionDataColumnEnum.INSDATE.ordinal())));
					// INSTCRCD
					// System.out.println(StringHelper.stringOrNull(list.get(InstitutionDataColumnEnum.INSTCRCD.ordinal())));
					// INSBIF
					// System.out.println(booleanOrNull(list.get(InstitutionDataColumnEnum.INSBIF.ordinal())));
					// INSCOML
					// System.out.println(booleanOrNull(list.get(InstitutionDataColumnEnum.INSCOML.ordinal())));
					// INSDIF
					// System.out.println(booleanOrNull(list.get(InstitutionDataColumnEnum.INSDIF.ordinal())));
					// INSFDIC
					// System.out.println(booleanOrNull(list.get(InstitutionDataColumnEnum.INSFDIC.ordinal())));
					// INSSAIF
					// System.out.println(booleanOrNull(list.get(InstitutionDataColumnEnum.INSSAIF.ordinal())));
					// INSSAVE
					// System.out.println(booleanOrNull(list.get(InstitutionDataColumnEnum.INSSAVE.ordinal())));
					// MSA_NO
					// System.out.println(integerOrNull(list.get(InstitutionDataColumnEnum.MSA_NO.ordinal())));
					// MSA FIXME-TODO: create dictionary
					// System.out.println(StringHelper.stringOrNull(list.get(InstitutionDataColumnEnum.MSA.ordinal())));
					// NAME
					// System.out.println(StringHelper.stringOrNull(list.get(InstitutionDataColumnEnum.NAME.ordinal())));
					// NEWCERT
					String string = list.get(InstitutionDataColumnEnum.NEWCERT.ordinal());
					if (string.equals("Savings Bank )"))
					{
						System.out.println(string);
					}
					// OAKAR
					// System.out.println(list.get(InstitutionDataColumnEnum.OAKAR.ordinal()));
					// OTSDIST
					// System.out.println(list.get(InstitutionDataColumnEnum.OTSDIST.ordinal()));
					// OTSREGNM
					// System.out.println(list.get(InstitutionDataColumnEnum.OTSREGNM.ordinal()));
					// PROCDATE
					// System.out.println(list.get(InstitutionDataColumnEnum.PROCDATE.ordinal()));
					// QBPRCOML
					// System.out.println(list.get(InstitutionDataColumnEnum.QBPRCOML.ordinal()));
					// REGAGNT
					// System.out.println(list.get(InstitutionDataColumnEnum.REGAGNT.ordinal()));
					// REPDTE
					// System.out.println(list.get(InstitutionDataColumnEnum.REPDTE.ordinal()));
					// RISDATE
					// System.out.println(list.get(InstitutionDataColumnEnum.RISDATE.ordinal()));
					// STCHRTR
					// System.out.println(list.get(InstitutionDataColumnEnum.STCHRTR.ordinal()));
					// ROA
					// System.out.println(list.get(InstitutionDataColumnEnum.ROA.ordinal()));
					// ROAQ
					// System.out.println(list.get(InstitutionDataColumnEnum.ROAQ.ordinal()));
					// ROE
					// System.out.println(list.get(InstitutionDataColumnEnum.ROE.ordinal()));

				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static LinkedList<String> newDataIterator(final String p_strData) {
		String string = new String(p_strData);
		final LinkedList<String> list = new LinkedList<>();

		while (!string.isEmpty()) {

			// if the opening character is a quotation
			// then locate the closing quotation
			if (string.charAt(0) == '"') {
				list.add(string.substring(1, string.indexOf('"', 1)));
				string = new String(string.substring(string.indexOf('"', 1) + 2));
			} else {
				final int iCommaIndex = string.indexOf(',');
				if (iCommaIndex == 0) {
					list.add("");
					string = new String(string.substring(1));
				} else if (iCommaIndex > 0) {
					list.add(string.substring(0, iCommaIndex));
					string = new String(string.substring(string.indexOf(',', 1) + 1));
				} else {
					list.add(string.substring(0));
					string = "";
				}
			}
		}

		return list;
	}

	private static Boolean booleanOrNull(final char p_cData) {
		if (Character.isDigit(p_cData)) {
			int number = Character.getNumericValue(p_cData);
			if (number == 1) {
				return true;
			}
			if (number == 0) {
				return false;
			}

			throw new IllegalArgumentException("Value of character is not 0 or 1");
		} else if (p_cData == 'y' && p_cData == 'Y') {
			return true;
		} else if (p_cData == 'n' && p_cData == 'N') {
			return false;
		}

		return null;
	}

	private static Boolean booleanOrNull(final String p_strData) {
		if (null != p_strData) {
			final String trimmedString = p_strData.trim();
			if (!trimmedString.isEmpty()) {
				if (trimmedString.length() == 1) {
					return booleanOrNull(trimmedString.charAt(0));
				} else {
					throw new IllegalArgumentException("Value of character is not 0 or 1");
				}
			}
		}

		return null;
	}

	private static Character charOrNull(final String p_strData) {
		if (null != p_strData) {
			final String trimmedString = p_strData.trim();
			if (!trimmedString.isEmpty()) {
				if (trimmedString.length() > 1) {
					throw new IllegalArgumentException();
				}
				return trimmedString.charAt(0);
			}
		}

		return null;
	}

	private static Integer integerOrNull(final String p_strData) {
		if (null != p_strData) {
			final String trimmedString = p_strData.trim();
			if (!trimmedString.isEmpty()) {
				return new Integer(trimmedString);
			}
		}

		return null;
	}
}
