/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipa.ip2.api.util.labelfree;


import com.ipa.ip2.api.util.Formatter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rohan
 */
public class LabelfreePeptide {

    private String sequence = "";
    private int chargestate;
    private double pvalue;
    private double qvalue;
    private List<String> intensity = new ArrayList<>();
    private List<String> filename = new ArrayList<>();
    private List<String> xCorr = new ArrayList<>();
    private List<String> dcn = new ArrayList<>();
    private List<String> dmass = new ArrayList<>();
    private List<String> sprank = new ArrayList<>();
    private List<String> spscore = new ArrayList<>();
    private List<String> redundancy = new ArrayList<>();
    private List<String> startrange = new ArrayList<>();
    private List<String> endrange = new ArrayList<>();
    private List<String> retentiontime = new ArrayList<>();
    private List<String> ioninjection = new ArrayList<>();
    private List<String> corrioninjectionintensity = new ArrayList<>();
    private String protein;
    private String description;
	private List<Double> ratioList = new ArrayList<>();
        private List<String> scanList = new ArrayList<>();
	private String sline;

    public double getPvalue() {
        return pvalue;
    }

    public void setPvalue(double pvalue) {
        this.pvalue = pvalue;
    }

    public double getQvalue() {
        return qvalue;
    }

    public void setQvalue(double qvalue) {
        this.qvalue = qvalue;
    }

        
        
        
    public List<String> getScanList() {
        return scanList;
    }

    public void setScanList(List<String> scanList) {
        this.scanList = scanList;
    }
        
    public void addScan(String scan){
        this.scanList.add(scan);
    }
        
        

    public List<String> getCorrioninjectionintensity() {
        return corrioninjectionintensity;
    }

    public void setCorrioninjectionintensity(List<String> corrioninjectionintensity) {
        this.corrioninjectionintensity = corrioninjectionintensity;
    }

        
    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public int getChargestate() {
        return chargestate;
    }

    public void setChargestate(int chargestate) {
        this.chargestate = chargestate;
    }

    public void addIntensity(String intensity){
    	this.intensity.add(intensity);
    }
    
    public List<String> getIntensity() {
		return intensity;
	}

	public void setIntensity(List<String> intensity) {
		this.intensity = intensity;
	}

	public List<String> getFilename() {
		return filename;
	}

	public void setFilename(List<String> filename) {
		this.filename = filename;
	}

	public List<String> getxCorr() {
		return xCorr;
	}

	public void setxCorr(List<String> xCorr) {
		this.xCorr = xCorr;
	}

	public List<String> getDcn() {
		return dcn;
	}

	public void setDcn(List<String> dcn) {
		this.dcn = dcn;
	}

	public List<String> getDmass() {
		return dmass;
	}

	public void setDmass(List<String> dmass) {
		this.dmass = dmass;
	}

	public List<String> getSprank() {
		return sprank;
	}

	public void setSprank(List<String> sprank) {
		this.sprank = sprank;
	}

	public List<String> getSpscore() {
		return spscore;
	}

	public void setSpscore(List<String> spscore) {
		this.spscore = spscore;
	}

	public List<String> getRedundancy() {
		return redundancy;
	}

	public void setRedundancy(List<String> redundancy) {
		this.redundancy = redundancy;
	}

	public List<String> getStartrange() {
		return startrange;
	}

	public void setStartrange(List<String> startrange) {
		this.startrange = startrange;
	}

	public List<String> getEndrange() {
		return endrange;
	}

	public void setEndrange(List<String> endrange) {
		this.endrange = endrange;
	}

	public List<String> getRetentiontime() {
		return retentiontime;
	}

	public void setRetentiontime(List<String> retentiontime) {
		this.retentiontime = retentiontime;
	}

	public List<String> getIoninjection() {
		return ioninjection;
	}

	public void setIoninjection(List<String> ioninjection) {
		this.ioninjection = ioninjection;
	}

	public String getProtein() {
		return protein;
	}

	public void setProtein(String protein) {
		this.protein = protein;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void addFilename(String filename){
        this.filename.add(filename);
    }
   
    public void addXCorr(String xcorr){
        xCorr.add(xcorr);
    }
    
    public void addrt(String ret){
        retentiontime.add(ret);
    }
    
    public void addion(String ion){
        ioninjection.add(ion);
    }
    
    public void adddcn(String dcn){
        this.dcn.add(dcn);
    }

    public void adddmass(String dmass) {
        this.dmass.add(dmass);
    }

    public void addsprank(String sprank) {
        this.sprank.add(sprank);
    }

    public void addstart(String start){
        this.startrange.add(start);
    }
    public void addend(String end){
        this.endrange.add(end);
    }
    
    public void addred(String red){
        this.redundancy.add(red);
    }
    public void addsp(String sp){
        this.spscore.add(sp);
    }
    public void addcorrintensity(String sp){
        this.corrioninjectionintensity.add(sp);
    }

	public List<Double> getRatioList() {
		if(ratioList.size()>0) return ratioList;

		double control = Double.parseDouble(this.intensity.get(0));
		for(int i=1;i<this.intensity.size();i++) {
			double each = Double.parseDouble(this.intensity.get(i));

			this.ratioList.add(Formatter.round(each/control, 2));
		}

		return ratioList;
	}

	public void setRatioList(List<Double> ratioList) {
		this.ratioList = ratioList;
	}

	public String getSline() {
		return sline;
	}

	public void setSline(String sline) {
		this.sline = sline;
	}
}
