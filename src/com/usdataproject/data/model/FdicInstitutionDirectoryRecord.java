package com.usdataproject.data.model;

public class FdicInstitutionDirectoryRecord {
	private Integer id;
	private String directoryItem;
	private String title;
	private String definition;

	public FdicInstitutionDirectoryRecord() {}

	public FdicInstitutionDirectoryRecord(final String p_strDirectoryItem, final String p_strTitle,
			final String p_strDefinition) {
		this.directoryItem = p_strDirectoryItem;
		this.title = p_strTitle;
		this.definition = p_strDefinition;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer p_Id) {
		this.id = p_Id;
	}

	public String getDirectoryItem() {
		return directoryItem;
	}

	public void setDirectoryItem(final String p_strDirectoryItem) {
		this.directoryItem = p_strDirectoryItem;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String p_strTitle) {
		this.title = p_strTitle;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(final String p_strDefinition) {
		this.definition = p_strDefinition;
	}

}
