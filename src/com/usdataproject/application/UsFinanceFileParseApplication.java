package com.usdataproject.application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.usdataproject.data.model.Institution;
import com.usdataproject.util.helper.InstitutionDataStaticValues.InstitutionDataColumnEnum;
import com.usdataproject.util.helper.StringHelper;

public class UsFinanceFileParseApplication {

	public static void main(String[] args) {
		try (final Scanner fileScanner = new Scanner(new FileInputStream("data/institutions2_dataOnly.csv"))) {

			while (fileScanner.hasNext()) {

				final String nextLine = fileScanner.nextLine();

				if (!nextLine.isEmpty()) {
					final List<String> list = newDataIterator(nextLine);

					final Institution institution = new Institution();

					// STNAME (state_name)
					institution
							.setStname(StringHelper.stringOrNull(list.get(InstitutionDataColumnEnum.STNAME.ordinal())));
					// CERT (fdic_cert)
					institution.setCert(integerOrNull(list.get(InstitutionDataColumnEnum.CERT.ordinal())));
					// DOCKET (ots_docket_number)
					institution.setDocket(integerOrNull(list.get(InstitutionDataColumnEnum.DOCKET.ordinal())));
					// ACTIVE (active)
					institution.setActive(
							StringHelper.booleanOrNull(list.get(InstitutionDataColumnEnum.ACTIVE.ordinal())));
					// ADDRESS
					institution.setAddress(
							StringHelper.stringOrNull(list.get(InstitutionDataColumnEnum.ADDRESS.ordinal())));
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
					institution.setClcode(integerOrNull(list.get(InstitutionDataColumnEnum.CLCODE.ordinal())));
					// CMSA_NO
					institution.setCmsa_no(integerOrNull(list.get(InstitutionDataColumnEnum.CMSA_NO.ordinal())));
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
					institution.setDep(
							integerOrNull(list.get(InstitutionDataColumnEnum.DEP.ordinal()).replaceAll(",", "")));
					// EFFDATE
					institution
							.setEffdate(StringHelper.dateOrNull(list.get(InstitutionDataColumnEnum.EFFDATE.ordinal())));
					// ENDEFYMD
					institution.setEndefymd(
							StringHelper.dateOrNull(list.get(InstitutionDataColumnEnum.ENDEFYMD.ordinal())));
					// EQ
					institution
							.setEq(integerOrNull(list.get(InstitutionDataColumnEnum.EQ.ordinal()).replaceAll(",", "")));
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
					institution.setFed_rssd(integerOrNull(list.get(InstitutionDataColumnEnum.FED_RSSD.ordinal())));
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
					institution.setMsa_no(integerOrNull(list.get(InstitutionDataColumnEnum.MSA_NO.ordinal())));
					/**
					 * TODO: create dictionary table
					 */
					// MSA
					institution.setMsa(StringHelper.stringOrNull(list.get(InstitutionDataColumnEnum.MSA.ordinal())));
					// NAME
					institution.setName(StringHelper.stringOrNull(list.get(InstitutionDataColumnEnum.NAME.ordinal())));
					// NEWCERT
					institution.setNewcert(integerOrNull(list.get(InstitutionDataColumnEnum.NEWCERT.ordinal())));
					// OAKAR
					institution
							.setOakar(StringHelper.booleanOrNull(list.get(InstitutionDataColumnEnum.OAKAR.ordinal())));
					// OTSDIST
					institution
							.setOtsdist(StringHelper.byteOrNull(list.get(InstitutionDataColumnEnum.OTSDIST.ordinal())));
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
					institution.setRegagnt(
							StringHelper.stringOrNull(list.get(InstitutionDataColumnEnum.REGAGNT.ordinal())));
					// REPDTE
					institution
							.setRepdte(StringHelper.dateOrNull(list.get(InstitutionDataColumnEnum.REPDTE.ordinal())));
					// RISDATE
					institution
							.setRisdate(StringHelper.dateOrNull(list.get(InstitutionDataColumnEnum.RISDATE.ordinal())));
					// STCHRTR
					institution.setStchrtr(
							StringHelper.booleanOrNull(list.get(InstitutionDataColumnEnum.STCHRTR.ordinal())));
					// ROA
					institution.setRoa(StringHelper.floatOrNull(list.get(InstitutionDataColumnEnum.ROA.ordinal())));
					// ROAQ
					institution.setRoaq(nextData(list, InstitutionDataColumnEnum.ROAQ, Float.class));
					// ROE
					institution.setRoe(nextData(list, InstitutionDataColumnEnum.ROE, Float.class));
					// ROEQ
					nextData(list, InstitutionDataColumnEnum.ROEQ, Float.class);
					// RUNDATE
					nextData(list, InstitutionDataColumnEnum.RUNDATE, Date.class);
					// SASSER {0 or 1}
					StringHelper.booleanOrNull(list.get(InstitutionDataColumnEnum.SASSER.ordinal()));
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
					nextData(list, InstitutionDataColumnEnum.CFPBEFFDTE, Date.class);
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
					nextData(list, InstitutionDataColumnEnum.TE10N528, String.class);
					// TE01N529
					nextData(list, InstitutionDataColumnEnum.TE01N529, String.class);
					// TE02N529
					nextData(list, InstitutionDataColumnEnum.TE02N529, String.class);
					// TE03N529
					nextData(list, InstitutionDataColumnEnum.TE03N529, String.class);
					// TE04N529
					nextData(list, InstitutionDataColumnEnum.TE04N529, String.class);
					// TE05N529
					nextData(list, InstitutionDataColumnEnum.TE05N529, String.class);
					// TE06N529
					nextData(list, InstitutionDataColumnEnum.TE06N529, String.class);
					// WEBADDR
					nextData(list, InstitutionDataColumnEnum.WEBADDR, String.class);
					// OFFICES
					nextData(list, InstitutionDataColumnEnum.OFFICES, Integer.class);
					// CERTCONS
					String string = nextData(list, InstitutionDataColumnEnum.CERTCONS, String.class);
					if (string != null) {
						Integer i = Integer.valueOf(string);
						if (i != 0 && i != 1) {
							System.out.println(i);
						}
					}

					// System.out.println(nextData(list,
					// InstitutionDataColumnEnum.CERTCONS, String.class));
					// PARCERT
					nextData(list, InstitutionDataColumnEnum.PARCERT, String.class);
					// CITYHCR
					// System.out.println(nextData(list,
					// InstitutionDataColumnEnum.CITYHCR, String.class));
					// DEPDOM
					// System.out.println(nextData(list,
					// InstitutionDataColumnEnum.DEPDOM, Integer.class));
					// FORM31
					nextData(list, InstitutionDataColumnEnum.FORM31, String.class);
					// HCTMULT

					// System.out.println(nextData(list,
					// InstitutionDataColumnEnum.HCTMULT, Integer.class));
					// INSTAG
					// System.out.println(nextData(list,
					// InstitutionDataColumnEnum.INSTAG));
					// MUTUAL
					// NAMEHCR
					// NETINC
					// NETINCQ
					// OFFDOM
					// OFFFOR
					// OFFOA,RSSDHCR,STALPHCR,STMULT,SUBCHAPS,ROAPTX,ROAPTXQ,TRUST,SPECGRP,
					// SPECGRPN,TRACT,CSA,CSA_NO,CSA_FLG,CBSA,CBSA_NO,CBSA_METRO_NAME,
					// CBSA_METRO,CBSA_METRO_FLG,CBSA_MICRO_FLG,CBSA_DIV,CBSA_DIV_NO,CBSA_DIV_FLG,CB
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static final String nextData(List<String> dataList, InstitutionDataColumnEnum eColumn) {
		return nextData(dataList, eColumn, String.class);
	}

	private static final <T> T nextData(List<String> dataList, InstitutionDataColumnEnum eColumn, Class<T> dataType) {
		if (dataType.equals(String.class)) {
			return (dataType.cast(StringHelper.stringOrNull(dataList.get(eColumn.ordinal()))));
		} else if (dataType.equals(Float.class)) {
			return (dataType.cast(StringHelper.floatOrNull(dataList.get(eColumn.ordinal()).replaceAll(",", ""))));
		} else if (dataType.equals(Date.class)) {
			return (dataType.cast(StringHelper.dateOrNull(dataList.get(eColumn.ordinal()))));
		} else if (dataType.equals(Integer.class)) {
			return (dataType.cast(integerOrNull(dataList.get(eColumn.ordinal()).replaceAll(",", ""))));
		} else if (dataType.equals(Byte.class)) {
			return (dataType.cast(StringHelper.byteOrNull(dataList.get(eColumn.ordinal()))));
		} else if (dataType.equals(Boolean.class)) {
			return (dataType.cast(StringHelper.booleanOrNull(dataList.get(eColumn.ordinal()))));
		}

		return null;
	}

	private static LinkedList<String> newDataIterator(final String data) {
		final LinkedList<String> list = new LinkedList<>();

		String string = data.trim();

		while (StringHelper.hasText(string)) {
			int idxStart = 0;
			int idxEnd = 0;

			if (string.charAt(0) == ',') {
				list.add(StringHelper.EMPTY_STRING);
				string = string.substring(1);
			} else if (string.charAt(0) == '"') {
				idxStart = 1;
				idxEnd = string.indexOf('"', 1);

				list.add(string.substring(idxStart, idxEnd));
				string = string.substring(string.indexOf(',', idxEnd) + 1);
			} else {
				idxEnd = string.indexOf(',');
				if (idxEnd > 0) {
					list.add(string.substring(idxStart, idxEnd));
					string = string.substring(idxEnd + 1);
				} else {
					list.add(string.substring(idxStart, string.length()));
					string = null;
				}
			}
		}

		return list;
	}

	private static Integer integerOrNull(String data) {
		if (StringHelper.hasText(data)) {
			return Integer.valueOf(data.trim());
		}

		return null;
	}
}
