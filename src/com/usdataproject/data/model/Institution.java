package com.usdataproject.data.model;

import java.math.BigInteger;
import java.sql.Date;

public class Institution {
	private String stname;
	private Integer cert;
	private Integer docket;
	private Boolean active;
	private String address;
	private BigInteger asset;
	private String bkclass;
	private Integer changec1;
	private Integer changec2;
	private Integer changec3;
	private Integer changec4;
	private Integer changec5;
	private Integer charter;
	private String chrtagnt;
	private Boolean conserve;
	private String city;
	private Integer clcode;
	private Integer cmsa_no;
	private String cmsa;
	private String county;
	private Date dateupdt;
	private String denovo;
	private Integer dep;
	private Date effdate;
	private Date endefymd;
	private Integer eq;
	private Date estymd;
	private Byte fdicdbs;
	private String fdicregn;
	private String fdicsupv;
	private Byte fed;
	private Integer fed_rssd;
	private Boolean fedchrtr;
	private String fldoff;
	private Boolean iba;
	private Boolean inactive;
	private String insagnt1;
	private String insagnt2;
	private Date insdate;
	private String instcrcd;
	private Boolean insbif;
	private Boolean inscoml;
	private Boolean insdif;
	private Boolean insfdic;
	private Boolean inssaif;
	private Boolean inssave;
	private Integer msa_no;
	private String msa;
	private String name;
	private Integer newcert;
	private Boolean oakar;
	private Byte otsdist;
	private String otsregnm;
	private Date procdate;
	private Byte qbprcoml;
	private String regagnt;
	private Date repdte;
	private Date risdate;
	private Boolean stchrtr;
	private Float roa;
	private Float roaq;
	private Float roe;

	public Byte getOtsdist() {
		return otsdist;
	}

	public void setOtsdist(Byte otsdist) {
		this.otsdist = otsdist;
	}

	public String getOtsregnm() {
		return otsregnm;
	}

	public void setOtsregnm(String otsregnm) {
		this.otsregnm = otsregnm;
	}

	public Date getProcdate() {
		return procdate;
	}

	public void setProcdate(Date procdate) {
		this.procdate = procdate;
	}

	public Byte getQbprcoml() {
		return qbprcoml;
	}

	public void setQbprcoml(Byte qbprcoml) {
		this.qbprcoml = qbprcoml;
	}

	public String getRegagnt() {
		return regagnt;
	}

	public void setRegagnt(String regagnt) {
		this.regagnt = regagnt;
	}

	public Date getRepdte() {
		return repdte;
	}

	public void setRepdte(Date repdte) {
		this.repdte = repdte;
	}

	public Date getRisdate() {
		return risdate;
	}

	public void setRisdate(Date risdate) {
		this.risdate = risdate;
	}

	public Boolean getStchrtr() {
		return stchrtr;
	}

	public void setStchrtr(Boolean stchrtr) {
		this.stchrtr = stchrtr;
	}

	public Float getRoa() {
		return roa;
	}

	public void setRoa(Float roa) {
		this.roa = roa;
	}

	public Float getRoaq() {
		return roaq;
	}

	public void setRoaq(Float roaq) {
		this.roaq = roaq;
	}

	public Float getRoe() {
		return roe;
	}

	public void setRoe(Float roe) {
		this.roe = roe;
	}

	public Boolean getOakar() {
		return oakar;
	}

	public void setOakar(Boolean oakar) {
		this.oakar = oakar;
	}

	public Boolean getInscoml() {
		return inscoml;
	}

	public void setInscoml(Boolean inscoml) {
		this.inscoml = inscoml;
	}

	public Boolean getInsdif() {
		return insdif;
	}

	public void setInsdif(Boolean insdif) {
		this.insdif = insdif;
	}

	public Boolean getInsfdic() {
		return insfdic;
	}

	public void setInsfdic(Boolean insfdic) {
		this.insfdic = insfdic;
	}

	public Boolean getInssaif() {
		return inssaif;
	}

	public void setInssaif(Boolean inssaif) {
		this.inssaif = inssaif;
	}

	public Boolean getInssave() {
		return inssave;
	}

	public void setInssave(Boolean inssave) {
		this.inssave = inssave;
	}

	public Integer getMsa_no() {
		return msa_no;
	}

	public void setMsa_no(Integer msa_no) {
		this.msa_no = msa_no;
	}

	public String getMsa() {
		return msa;
	}

	public void setMsa(String msa) {
		this.msa = msa;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getActive() {
		return active;
	}

	public Boolean getConserve() {
		return conserve;
	}

	public Boolean getFedchrtr() {
		return fedchrtr;
	}

	public Boolean getIba() {
		return iba;
	}

	public Boolean getInactive() {
		return inactive;
	}

	public Boolean getInsbif() {
		return insbif;
	}

	public Integer getNewcert() {
		return newcert;
	}

	public void setNewcert(Integer newcert) {
		this.newcert = newcert;
	}

	public Integer getFed_rssd() {
		return fed_rssd;
	}

	public void setFed_rssd(Integer fed_rssd) {
		this.fed_rssd = fed_rssd;
	}

	public Boolean isFedchrtr() {
		return fedchrtr;
	}

	public void setFedchrtr(Boolean fedchrtr) {
		this.fedchrtr = fedchrtr;
	}

	public String getFldoff() {
		return fldoff;
	}

	public void setFldoff(String fldoff) {
		this.fldoff = fldoff;
	}

	public Boolean isIba() {
		return iba;
	}

	public void setIba(Boolean iba) {
		this.iba = iba;
	}

	public Boolean isInactive() {
		return inactive;
	}

	public void setInactive(Boolean inactive) {
		this.inactive = inactive;
	}

	public String getInsagnt1() {
		return insagnt1;
	}

	public void setInsagnt1(String insagnt1) {
		this.insagnt1 = insagnt1;
	}

	public String getInsagnt2() {
		return insagnt2;
	}

	public void setInsagnt2(String insagnt2) {
		this.insagnt2 = insagnt2;
	}

	public Date getInsdate() {
		return insdate;
	}

	public void setInsdate(Date insdate) {
		this.insdate = insdate;
	}

	public String getInstcrcd() {
		return instcrcd;
	}

	public void setInstcrcd(String instcrcd) {
		this.instcrcd = instcrcd;
	}

	public Boolean isInsbif() {
		return insbif;
	}

	public void setInsbif(Boolean insbif) {
		this.insbif = insbif;
	}

	public Integer getDep() {
		return dep;
	}

	public void setDep(Integer dep) {
		this.dep = dep;
	}

	public Date getEffdate() {
		return effdate;
	}

	public void setEffdate(Date effdate) {
		this.effdate = effdate;
	}

	public Date getEndefymd() {
		return endefymd;
	}

	public void setEndefymd(Date endefymd) {
		this.endefymd = endefymd;
	}

	public Integer getEq() {
		return eq;
	}

	public void setEq(Integer eq) {
		this.eq = eq;
	}

	public Date getEstymd() {
		return estymd;
	}

	public void setEstymd(Date estymd) {
		this.estymd = estymd;
	}

	public Byte getFdicdbs() {
		return fdicdbs;
	}

	public void setFdicdbs(Byte fdicdbs) {
		this.fdicdbs = fdicdbs;
	}

	public String getFdicregn() {
		return fdicregn;
	}

	public void setFdicregn(String fdicregn) {
		this.fdicregn = fdicregn;
	}

	public String getFdicsupv() {
		return fdicsupv;
	}

	public void setFdicsupv(String fdicsupv) {
		this.fdicsupv = fdicsupv;
	}

	public Byte getFed() {
		return fed;
	}

	public void setFed(Byte fed) {
		this.fed = fed;
	}

	public String getChrtagnt() {
		return chrtagnt;
	}

	public void setChrtagnt(String chrtagnt) {
		this.chrtagnt = chrtagnt;
	}

	public Boolean isConserve() {
		return conserve;
	}

	public void setConserve(Boolean conserve) {
		this.conserve = conserve;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getClcode() {
		return clcode;
	}

	public void setClcode(Integer clcode) {
		this.clcode = clcode;
	}

	public Integer getCmsa_no() {
		return cmsa_no;
	}

	public void setCmsa_no(Integer cmsa_no) {
		this.cmsa_no = cmsa_no;
	}

	public String getCmsa() {
		return cmsa;
	}

	public void setCmsa(String cmsa) {
		this.cmsa = cmsa;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public Date getDateupdt() {
		return dateupdt;
	}

	public void setDateupdt(Date dateupdt) {
		this.dateupdt = dateupdt;
	}

	public String getDenovo() {
		return denovo;
	}

	public void setDenovo(String denovo) {
		this.denovo = denovo;
	}

	public BigInteger getAsset() {
		return asset;
	}

	public void setAsset(BigInteger asset) {
		this.asset = asset;
	}

	public String getBkclass() {
		return bkclass;
	}

	public void setBkclass(String bkclass) {
		this.bkclass = bkclass;
	}

	public Integer getChangec1() {
		return changec1;
	}

	public void setChangec1(Integer changec1) {
		this.changec1 = changec1;
	}

	public Integer getChangec2() {
		return changec2;
	}

	public void setChangec2(Integer changec2) {
		this.changec2 = changec2;
	}

	public Integer getChangec3() {
		return changec3;
	}

	public void setChangec3(Integer changec3) {
		this.changec3 = changec3;
	}

	public Integer getChangec4() {
		return changec4;
	}

	public void setChangec4(Integer changec4) {
		this.changec4 = changec4;
	}

	public Integer getChangec5() {
		return changec5;
	}

	public void setChangec5(Integer changec5) {
		this.changec5 = changec5;
	}

	public Integer getCharter() {
		return charter;
	}

	public void setCharter(Integer charter) {
		this.charter = charter;
	}

	public String getStname() {
		return stname;
	}

	public void setStname(String stname) {
		this.stname = stname;
	}

	public Integer getCert() {
		return cert;
	}

	public void setCert(Integer cert) {
		this.cert = cert;
	}

	public Integer getDocket() {
		return docket;
	}

	public void setDocket(Integer docket) {
		this.docket = docket;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
