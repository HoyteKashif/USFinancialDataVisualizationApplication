package com.usdataproject.application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.usdataproject.data.model.Institution;
import com.usdataproject.util.helper.InstitutionDataColumnEnum;
import com.usdataproject.util.helper.StringHelper;

public class UsFinanceFileParseApplication {

	public static void main(String[] args) {
		try (final Scanner fileScanner = new Scanner(new FileInputStream("data/institutions2_dataOnly.csv"))) {

			while (fileScanner.hasNext()) {

				final String line = fileScanner.nextLine();

				if (!line.isEmpty()) {
					final List<String> list = newDataIterator(line);

					final Institution institution = new Institution();

					// STNAME (state_name)
					institution.setStname(nextString(list, InstitutionDataColumnEnum.STNAME));
					// CERT (fdic_cert)
					institution.setCert(nextInt(list, InstitutionDataColumnEnum.CERT));
					// DOCKET (ots_docket_number)
					institution.setDocket(nextInt(list, InstitutionDataColumnEnum.DOCKET));
					// ACTIVE (active)
					institution.setActive(nextBoolean(list, InstitutionDataColumnEnum.ACTIVE));
					// ADDRESS
					institution.setAddress(nextString(list, InstitutionDataColumnEnum.ADDRESS));
					// ASSET
					institution.setAsset(StringHelper
							.bigIntegerOrNull(list.get(InstitutionDataColumnEnum.ASSET.ordinal()).replaceAll(",", "")));
					// BKCLASS
					institution.setBkclass(
							StringHelper.stringOrNull(list.get(InstitutionDataColumnEnum.BKCLASS.ordinal())));
					/**
					 * TODO:LOAD INTO DATABASE AS A CHANGE CODE DICTIONARY ID
					 */
					// CHANGEC1
					institution.setChangec1(
							StringHelper.integerOrNull(list.get(InstitutionDataColumnEnum.CHANGEC1.ordinal())));
					// CHANGEC2
					institution.setChangec2(
							StringHelper.integerOrNull(list.get(InstitutionDataColumnEnum.CHANGEC2.ordinal())));
					// CHANGEC3
					institution.setChangec3(
							StringHelper.integerOrNull(list.get(InstitutionDataColumnEnum.CHANGEC3.ordinal())));
					// CHANGEC4
					institution.setChangec4(
							StringHelper.integerOrNull(list.get(InstitutionDataColumnEnum.CHANGEC4.ordinal())));
					// CHANGEC5
					institution.setChangec5(
							StringHelper.integerOrNull(list.get(InstitutionDataColumnEnum.CHANGEC5.ordinal())));
					// CHARTER
					institution.setCharter(
							StringHelper.integerOrNull(list.get(InstitutionDataColumnEnum.CHARTER.ordinal())));
					// CHRTAGNT
					institution.setChrtagnt(
							StringHelper.stringOrNull(list.get(InstitutionDataColumnEnum.CHRTAGNT.ordinal())));
					// CONSERVE (Y|N)
					institution.setConserve(
							StringHelper.booleanOrNull(list.get(InstitutionDataColumnEnum.ACTIVE.ordinal())));
					// CITY
					institution.setCity(list.get(InstitutionDataColumnEnum.CITY.ordinal()));
					// CLCODE
					institution.setClcode(
							StringHelper.integerOrNull(list.get(InstitutionDataColumnEnum.CLCODE.ordinal())));
					// CMSA_NO
					institution.setCmsa_no(
							StringHelper.integerOrNull(list.get(InstitutionDataColumnEnum.CMSA_NO.ordinal())));
					// CMSA
					institution.setCmsa(StringHelper.stringOrNull(list.get(InstitutionDataColumnEnum.CMSA.ordinal())));
					/**
					 * TODO: Create Dictionary Values
					 */
					// COUNTY
					institution
							.setCounty(StringHelper.stringOrNull(list.get(InstitutionDataColumnEnum.COUNTY.ordinal())));
					// DATEUPDT
					institution.setDateupdt(
							StringHelper.dateOrNull(list.get(InstitutionDataColumnEnum.DATEUPDT.ordinal())));
					// DENOVO
					institution
							.setDenovo(StringHelper.stringOrNull(list.get(InstitutionDataColumnEnum.DENOVO.ordinal())));
					// DEP
					institution.setDep(StringHelper
							.integerOrNull(list.get(InstitutionDataColumnEnum.DEP.ordinal()).replaceAll(",", "")));
					// EFFDATE
					institution
							.setEffdate(StringHelper.dateOrNull(list.get(InstitutionDataColumnEnum.EFFDATE.ordinal())));
					// ENDEFYMD
					institution.setEndefymd(
							StringHelper.dateOrNull(list.get(InstitutionDataColumnEnum.ENDEFYMD.ordinal())));
					// EQ
					institution.setEq(StringHelper
							.integerOrNull(list.get(InstitutionDataColumnEnum.EQ.ordinal()).replaceAll(",", "")));
					// ESTYMD
					institution
							.setEstymd(StringHelper.dateOrNull(list.get(InstitutionDataColumnEnum.ESTYMD.ordinal())));
					// FDICDBS max of 2 digits
					institution.setFdicdbs(Byte.valueOf((list.get(InstitutionDataColumnEnum.FDICDBS.ordinal()))));
					/**
					 * TODO: create dictionary table
					 */
					// FDICREGN
					institution.setFdicregn(
							StringHelper.stringOrNull(list.get(InstitutionDataColumnEnum.FDICREGN.ordinal())));
					/**
					 * TODO: create dictionary table
					 */
					// FDICSUPV
					institution.setFdicsupv(
							StringHelper.stringOrNull(list.get(InstitutionDataColumnEnum.FDICSUPV.ordinal())));
					// FED
					institution.setFed(Byte.valueOf(list.get(InstitutionDataColumnEnum.FED.ordinal())));
					// FED_RSSD {minimum=0 and max=5143788}
					institution.setFed_rssd(
							StringHelper.integerOrNull(list.get(InstitutionDataColumnEnum.FED_RSSD.ordinal())));
					// FEDCHRTR {false=0,true=1}
					institution.setFedchrtr(
							StringHelper.booleanOrNull(list.get(InstitutionDataColumnEnum.FEDCHRTR.ordinal())));
					/**
					 * TODO: create dictionary table
					 */
					// FLDOFF
					institution
							.setFldoff(StringHelper.stringOrNull(list.get(InstitutionDataColumnEnum.FLDOFF.ordinal())));
					// IBA
					institution.setIba(StringHelper.booleanOrNull(list.get(InstitutionDataColumnEnum.IBA.ordinal())));
					// INACTIVE
					institution.setInactive(
							StringHelper.booleanOrNull(list.get(InstitutionDataColumnEnum.INACTIVE.ordinal())));
					// INSAGNT1
					institution.setInsagnt1(
							StringHelper.stringOrNull(list.get(InstitutionDataColumnEnum.INSAGNT1.ordinal())));
					// INSAGNT2
					institution.setInsagnt2(
							StringHelper.stringOrNull(list.get(InstitutionDataColumnEnum.INSAGNT2.ordinal())));
					// INSDATE
					institution
							.setInsdate(StringHelper.dateOrNull(list.get(InstitutionDataColumnEnum.INSDATE.ordinal())));
					// INSTCRCD
					institution.setInstcrcd(
							StringHelper.stringOrNull(list.get(InstitutionDataColumnEnum.INSTCRCD.ordinal())));
					// INSBIF
					institution.setInsbif(
							StringHelper.booleanOrNull(list.get(InstitutionDataColumnEnum.INSBIF.ordinal())));
					// INSCOML
					institution.setInscoml(
							StringHelper.booleanOrNull(list.get(InstitutionDataColumnEnum.INSCOML.ordinal())));
					// INSDIF
					institution.setInsdif(
							StringHelper.booleanOrNull(list.get(InstitutionDataColumnEnum.INSDIF.ordinal())));
					// INSFDIC
					institution.setInsfdic(
							StringHelper.booleanOrNull(list.get(InstitutionDataColumnEnum.INSFDIC.ordinal())));
					// INSSAIF
					institution.setInssaif(
							StringHelper.booleanOrNull(list.get(InstitutionDataColumnEnum.INSSAIF.ordinal())));
					// INSSAVE
					institution.setInssave(
							StringHelper.booleanOrNull(list.get(InstitutionDataColumnEnum.INSSAVE.ordinal())));
					// MSA_NO
					institution.setMsa_no(
							StringHelper.integerOrNull(list.get(InstitutionDataColumnEnum.MSA_NO.ordinal())));
					/**
					 * TODO: create dictionary table
					 */
					// MSA
					institution.setMsa(nextString(list, InstitutionDataColumnEnum.MSA));
					// NAME
					institution.setName(nextString(list, InstitutionDataColumnEnum.NAME));
					// NEWCERT
					institution.setNewcert(nextInt(list, InstitutionDataColumnEnum.NEWCERT));
					// OAKAR
					institution.setOakar(nextBoolean(list, InstitutionDataColumnEnum.OAKAR));
					// OTSDIST
					institution.setOtsdist(nextByte(list, InstitutionDataColumnEnum.OTSDIST));
					// OTSREGNM
					institution.setOtsregnm(
							StringHelper.stringOrNull(list.get(InstitutionDataColumnEnum.OTSREGNM.ordinal())));
					// PROCDATE
					institution.setProcdate(
							StringHelper.dateOrNull(list.get(InstitutionDataColumnEnum.PROCDATE.ordinal())));
					// QBPRCOML
					institution.setQbprcoml(
							StringHelper.byteOrNull(list.get(InstitutionDataColumnEnum.QBPRCOML.ordinal())));
					// REGAGNT
					institution.setRegagnt(nextData(list, InstitutionDataColumnEnum.REGAGNT, String.class));
					// REPDTE
					institution.setRepdte(nextData(list, InstitutionDataColumnEnum.REPDTE, Date.class));
					// RISDATE
					institution.setRisdate(nextData(list, InstitutionDataColumnEnum.RISDATE, Date.class));
					// STCHRTR
					institution.setStchrtr(nextData(list, InstitutionDataColumnEnum.STCHRTR, Boolean.class));
					// ROA
					institution.setRoa(nextFloat(list, InstitutionDataColumnEnum.ROA));
					// ROAQ
					institution.setRoaq(nextFloat(list, InstitutionDataColumnEnum.ROAQ));
					// ROE
					institution.setRoe(nextData(list, InstitutionDataColumnEnum.ROE, Float.class));
					// ROEQ
					nextData(list, InstitutionDataColumnEnum.ROEQ, Float.class);
					// RUNDATE
					nextData(list, InstitutionDataColumnEnum.RUNDATE, Date.class);
					// SASSER {0 or 1}
					nextData(list, InstitutionDataColumnEnum.SASSER, Boolean.class);
					// LAW_SASSER_FLG {N or Y}
					nextData(list, InstitutionDataColumnEnum.LAW_SASSER_FLG, Boolean.class);
					// STALP
					nextData(list, InstitutionDataColumnEnum.STALP, String.class);
					// STCNTY
					nextData(list, InstitutionDataColumnEnum.STCNTY, Integer.class);
					// STNUM
					nextData(list, InstitutionDataColumnEnum.STNUM, Byte.class);
					// ZIP
					nextData(list, InstitutionDataColumnEnum.ZIP, Integer.class);
					// SUPRV_FD
					nextData(list, InstitutionDataColumnEnum.SUPRV_FD, Byte.class);
					// OCCDIST
					nextData(list, InstitutionDataColumnEnum.OCCDIST, Byte.class);
					// UNINUM
					nextData(list, InstitutionDataColumnEnum.UNINUM, Integer.class);
					// ULTCERT
					nextData(list, InstitutionDataColumnEnum.ULTCERT, Integer.class);
					// CFPBEFFDTE
					nextDate(list, InstitutionDataColumnEnum.CFPBEFFDTE);
					// CFPBENDDTE
					nextData(list, InstitutionDataColumnEnum.CFPBENDDTE, Date.class);
					// CFPBFLAG
					nextData(list, InstitutionDataColumnEnum.CFPBFLAG, Boolean.class);
					// REGAGENT2 -- all entries are null
					nextData(list, InstitutionDataColumnEnum.REGAGENT2, String.class);
					// TE01N528
					nextData(list, InstitutionDataColumnEnum.TE01N528, String.class);
					// TE02N528
					nextData(list, InstitutionDataColumnEnum.TE02N528, String.class);
					// TE03N528
					nextData(list, InstitutionDataColumnEnum.TE03N528, String.class);
					// TE04N528
					nextData(list, InstitutionDataColumnEnum.TE04N528, String.class);
					// TE05N528
					nextData(list, InstitutionDataColumnEnum.TE05N528, String.class);
					// TE06N528
					nextData(list, InstitutionDataColumnEnum.TE06N528, String.class);
					// TE07N528
					nextData(list, InstitutionDataColumnEnum.TE07N528, String.class);
					// TE08N528
					nextData(list, InstitutionDataColumnEnum.TE08N528, String.class);
					// TE09N528
					nextData(list, InstitutionDataColumnEnum.TE09N528, String.class);
					// TE10N528
					nextString(list, InstitutionDataColumnEnum.TE10N528);
					// TE01N529
					nextString(list, InstitutionDataColumnEnum.TE01N529);
					// TE02N529
					nextString(list, InstitutionDataColumnEnum.TE02N529);
					// TE03N529
					nextString(list, InstitutionDataColumnEnum.TE03N529);
					// TE04N529
					nextString(list, InstitutionDataColumnEnum.TE04N529);
					// TE05N529
					nextString(list, InstitutionDataColumnEnum.TE05N529);
					// TE06N529
					nextString(list, InstitutionDataColumnEnum.TE06N529);
					// WEBADDR
					nextString(list, InstitutionDataColumnEnum.WEBADDR);
					// OFFICES
					nextInt(list, InstitutionDataColumnEnum.OFFICES);
					// CERTCONS
					nextInt(list, InstitutionDataColumnEnum.CERTCONS);
					// PARCERT
					nextInt(list, InstitutionDataColumnEnum.PARCERT);
					// CITYHCR
					nextString(list, InstitutionDataColumnEnum.CITYHCR);
					// DEPDOM
					nextInt(list, InstitutionDataColumnEnum.DEPDOM);
					// FORM31
					nextString(list, InstitutionDataColumnEnum.FORM31);
					// HCTMULT ** Possible Boolean value **
					nextInt(list, InstitutionDataColumnEnum.HCTMULT);
					// INSTAG ** Possible Boolean value **
					nextInt(list, InstitutionDataColumnEnum.INSTAG);
					// MUTUAL ** Possible Boolean value **
					nextInt(list, InstitutionDataColumnEnum.MUTUAL);
					// NAMEHCR
					nextString(list, InstitutionDataColumnEnum.NAMEHCR);
					// NETINC
					nextInt(list, InstitutionDataColumnEnum.NETINC);
					// NETINCQ
					nextInt(list, InstitutionDataColumnEnum.NETINCQ);
					// OFFDOM **numeric**
					nextString(list, InstitutionDataColumnEnum.OFFDOM);
					// OFFFOR **numeric**
					nextString(list, InstitutionDataColumnEnum.OFFFOR);
					// OFFOA **numeric**
					nextString(list, InstitutionDataColumnEnum.OFFOA);
					// RSSDHCR **numeric**
					nextString(list, InstitutionDataColumnEnum.RSSDHCR);
					// STALPHCR TODO: create dictionary table
					nextString(list, InstitutionDataColumnEnum.STALPHCR);
					// STMULT **numeric**
					nextString(list, InstitutionDataColumnEnum.STMULT);
					// SUBCHAPS
					nextBoolean(list, InstitutionDataColumnEnum.SUBCHAPS);
					// ROAPTX
					nextBigDecimal(list, InstitutionDataColumnEnum.ROAPTX);
					// ROAPTXQ
					nextBigDecimal(list, InstitutionDataColumnEnum.ROAPTXQ);
					// TRUST
					nextInt(list, InstitutionDataColumnEnum.TRUST);
					// SPECGRP TODO: create dictionary table
					nextInt(list, InstitutionDataColumnEnum.SPECGRP);
					// SPECGRPN TODO: create dictionary table
					nextString(list, InstitutionDataColumnEnum.SPECGRPN);
					// TRACT
					nextBoolean(list, InstitutionDataColumnEnum.TRACT);
					// CSA TODO: create dictionary table
					nextString(list, InstitutionDataColumnEnum.CSA);
					// CSA_NO
					nextInt(list, InstitutionDataColumnEnum.CSA_NO);
					// CSA_FLG
					nextBoolean(list, InstitutionDataColumnEnum.CSA_FLG);
					// CBSA TODO: create dictionary with the CBSA_NO
					nextString(list, InstitutionDataColumnEnum.CBSA);
					// CBSA_NO TODO: create dictionary table withthe CBSA
					nextInt(list, InstitutionDataColumnEnum.CBSA_NO);
					// CBSA_METRO_NAME TODO: create dictionary table
					nextString(list, InstitutionDataColumnEnum.CBSA_METRO_NAME);
					// CBSA_METRO TODO: create dictionary table with
					nextInt(list, InstitutionDataColumnEnum.CBSA_METRO);
					// CBSA_METRO_FLG
					nextBoolean(list, InstitutionDataColumnEnum.CBSA_METRO_FLG);
					// CBSA_MICRO_FLG
					nextBoolean(list, InstitutionDataColumnEnum.CBSA_MICRO_FLG);
					// CBSA_DIV TODO: create dictionary table with CBSA_DIV_NO
					nextString(list, InstitutionDataColumnEnum.CBSA_DIV);
					// CBSA_DIV_NO
					nextInt(list, InstitutionDataColumnEnum.CBSA_DIV_NO);
					// CBSA_DIV_FLG
					nextBoolean(list, InstitutionDataColumnEnum.CBSA_DIV_FLG);
					// CB done this way due to poor dataset
					if (list.size() < InstitutionDataColumnEnum.values().length) {
						// false;
					} else {
						pnextBoolean(list, InstitutionDataColumnEnum.CB);
					}
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static final String pnextString(List<String> dataList, InstitutionDataColumnEnum eColumn) {
		String s = nextData(dataList, eColumn, String.class);
		if (s != null) {
			System.out.println(s);
		}
		return s;
	}

	private static final String nextString(List<String> dataList, InstitutionDataColumnEnum eColumn) {
		return nextData(dataList, eColumn, String.class);
	}

	private static final Float nextFloat(List<String> dataList, InstitutionDataColumnEnum eColumn) {
		return nextData(dataList, eColumn, Float.class);
	}

	private static final Date nextDate(List<String> dataList, InstitutionDataColumnEnum eColumn) {
		return nextData(dataList, eColumn, Date.class);
	}

	private static final Integer pnextInt(List<String> dataList, InstitutionDataColumnEnum eColumn) {
		Integer i = nextData(dataList, eColumn, Integer.class);
		if (i != null) {
			System.out.println(i);
		}
		return i;
	}

	private static final Integer nextInt(List<String> dataList, InstitutionDataColumnEnum eColumn) {
		return nextData(dataList, eColumn, Integer.class);
	}

	private static final Byte nextByte(List<String> dataList, InstitutionDataColumnEnum eColumn) {
		return nextData(dataList, eColumn, Byte.class);
	}

	private static final Boolean pnextBoolean(List<String> dataList, InstitutionDataColumnEnum eColumn) {
		Boolean b = nextData(dataList, eColumn, Boolean.class);
		if (b != null) {
			System.out.println(b);
		}
		return b;
	}

	private static final Boolean nextBoolean(List<String> dataList, InstitutionDataColumnEnum eColumn) {
		return nextData(dataList, eColumn, Boolean.class);
	}

	private static final BigInteger pnextBigInteger(List<String> dataList, InstitutionDataColumnEnum eColumn) {
		BigInteger i = nextData(dataList, eColumn, BigInteger.class);
		if (i != null) {
			System.out.println(i);
		}
		return i;
	}

	private static final BigInteger nextBigInteger(List<String> dataList, InstitutionDataColumnEnum eColumn) {
		return nextData(dataList, eColumn, BigInteger.class);
	}

	private static final BigDecimal pnextBigDecimal(List<String> dataList, InstitutionDataColumnEnum eColumn) {
		BigDecimal bd = nextData(dataList, eColumn, BigDecimal.class);
		if (bd != null) {
			System.out.println(bd);
		}

		return bd;
	}

	private static final BigDecimal nextBigDecimal(List<String> dataList, InstitutionDataColumnEnum eColumn) {
		return nextData(dataList, eColumn, BigDecimal.class);
	}

	private static final <T> T nextData(List<String> dataList, InstitutionDataColumnEnum eColumn, Class<T> type) {
		if (type.equals(String.class)) {
			return (type.cast(StringHelper.stringOrNull(dataList.get(eColumn.ordinal()))));
		}

		if (type.equals(Float.class)) {
			return (type.cast(StringHelper.floatOrNull(dataList.get(eColumn.ordinal()).replaceAll(",", ""))));
		}

		if (type.equals(Date.class)) {
			return (type.cast(StringHelper.dateOrNull(dataList.get(eColumn.ordinal()))));
		}

		if (type.equals(Integer.class)) {
			return (type.cast(StringHelper.integerOrNull(dataList.get(eColumn.ordinal()).replaceAll(",", ""))));
		}

		if (type.equals(Byte.class)) {
			return (type.cast(StringHelper.byteOrNull(dataList.get(eColumn.ordinal()))));
		}

		if (type.equals(Boolean.class)) {
			return (type.cast(StringHelper.booleanOrNull(dataList.get(eColumn.ordinal()))));
		}

		if (type.equals(BigInteger.class)) {
			return (type.cast(StringHelper.bigIntegerOrNull(dataList.get(eColumn.ordinal()).replaceAll(",", ""))));
		}

		if (type.equals(BigDecimal.class)) {
			return (type.cast(StringHelper.bigDecimalOrNull(dataList.get(eColumn.ordinal()).replaceAll(",", ""))));
		}

		return null;
	}

	private static List<String> newDataIterator(final String data) {
		final List<String> list = new LinkedList<>();

		String s = data.trim();

		while (StringHelper.hasText(s)) {
			int start = 0;
			int end = 0;

			if (s.charAt(0) == ',') {
				list.add(StringHelper.EMPTY_STRING);
				s = s.substring(1);
			} else if (s.charAt(0) == '"') {
				start = 1;
				end = s.indexOf("\",", 1);

				list.add(s.substring(start, end));
				s = s.substring(s.indexOf(',', end) + 1);
			} else {
				end = s.indexOf(',');
				if (end > 0) {
					list.add(s.substring(start, end));
					s = s.substring(end + 1);
				} else {
					list.add(s.substring(start, s.length()));
					s = null;
				}
			}
		}

		return list;
	}
}
