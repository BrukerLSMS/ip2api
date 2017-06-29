package com.ipa.ip2.api.util.labelfree;

import java.util.ArrayList;
import java.util.List;

public class LabelFreePeptideDisplay {

	private int groupSample = 0;
	
	private List<LabelfreePeptide> pepList = new ArrayList<>();

	public int getGroupSample() {
		return groupSample;
	}

	public void setGroupSample(int groupSample) {
		this.groupSample = groupSample;
	}

	public List<LabelfreePeptide> getPepList() {
		return pepList;
	}

	public void setPepList(List<LabelfreePeptide> pepList) {
		this.pepList = pepList;
	}
}
