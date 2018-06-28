package com.usdataproject.data.model;

import java.sql.Date;

public class FailureAssistanceTransaction {

	private Integer transactionId;
	private String institutionName;
	private Integer cert;
	private Integer fin;
	private String location;
	private Date effectiveDate;
	private String insFund;
	private String transactionType;
	private String charterClass;
	private String failureOrAssistance;
	private String totalDeposits;
	private String totalAssets;
	private String estimatedLoss;

	public FailureAssistanceTransaction() {
	}

	public FailureAssistanceTransaction(final Integer p_transactionId, final String p_institutionName,
			final Integer p_cert, final Integer p_fin, final String p_location, final Date p_effectiveDate,
			final String p_insFund, final String p_transactionType, final String p_charterClass,
			final String p_failureOrAssistance, final String p_totalDeposits, final String p_totalAssets,
			final String p_estimatedLoss) {

		this.transactionId = p_transactionId;
		this.institutionName = p_institutionName;
		this.cert = p_cert;
		this.fin = p_fin;
		this.location = p_location;
		this.effectiveDate = p_effectiveDate;
		this.insFund = p_insFund;
		this.transactionType = p_transactionType;
		this.charterClass = p_charterClass;
		this.failureOrAssistance = p_failureOrAssistance;
		this.totalDeposits = p_totalDeposits;
		this.totalAssets = p_totalAssets;
		this.estimatedLoss = p_estimatedLoss;

	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public Integer getCert() {
		return cert;
	}

	public void setCert(Integer cert) {
		this.cert = cert;
	}

	public Integer getFin() {
		return fin;
	}

	public void setFin(Integer fin) {
		this.fin = fin;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getInsFund() {
		return insFund;
	}

	public void setInsFund(String insFund) {
		this.insFund = insFund;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getCharterClass() {
		return charterClass;
	}

	public void setCharterClass(String charterClass) {
		this.charterClass = charterClass;
	}

	public String getFailureOrAssistance() {
		return failureOrAssistance;
	}

	public void setFailureOrAssistance(String failureOrAssistance) {
		this.failureOrAssistance = failureOrAssistance;
	}

	public String getTotalDeposits() {
		return totalDeposits;
	}

	public void setTotalDeposits(String totalDeposits) {
		this.totalDeposits = totalDeposits;
	}

	public String getTotalAssets() {
		return totalAssets;
	}

	public void setTotalAssets(String totalAssets) {
		this.totalAssets = totalAssets;
	}

	public String getEstimatedLoss() {
		return estimatedLoss;
	}

	public void setEstimatedLoss(String estimatedLoss) {
		this.estimatedLoss = estimatedLoss;
	}
}
