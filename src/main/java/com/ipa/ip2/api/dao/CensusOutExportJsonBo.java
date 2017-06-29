package com.ipa.ip2.api.dao;


public class CensusOutExportJsonBo {

//	private List<EachQuant> quantList = new ArrayList<>();

	private String projectName = "";

	private String quantationId = "";

	private String quantType = "";

	private String experimentId = "";
	
	private String pid = "";
	
	private String projectCreatedDate = "";
	
	private String sampleName = "";
	
	public String getSampleName() {
		return sampleName;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}

	public String getExperimentCreatedDate() {
		return experimentCreatedDate;
	}

	public void setExperimentCreatedDate(String experimentCreatedDate) {
		this.experimentCreatedDate = experimentCreatedDate;
	}

	private String experimentCreatedDate = "";

//	public List<EachQuant> getQuantList() {
//		return quantList;
//	}
//
//	public void setQuantList(List<EachQuant> quantList) {
//		this.quantList = quantList;
//	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getQuantationId() {
		return quantationId;
	}

	public void setQuantationId(String quantationId) {
		this.quantationId = quantationId;
	}

	public String getQuantType() {
		return quantType;
	}

	public void setQuantType(String quantType) {
		this.quantType = quantType;
	}

	public String getExperimentId() {
		return experimentId;
	}

	public void setExperimentId(String experimentId) {
		this.experimentId = experimentId;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getProjectCreatedDate() {
		return projectCreatedDate;
	}

	public void setProjectCreatedDate(String projectCreatedDate) {
		this.projectCreatedDate = projectCreatedDate;
	}

}
