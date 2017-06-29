
/*
* Copyright (c) 2008 Integrated Proteomics Applications.  All rights reserved.
*/

package com.ipa.ip2.api.dao;

import java.util.*;

import com.ipa.ip2.api.util.dtaselect.CensusPeptide;
import com.ipa.ip2.api.util.dtaselect.Protein;

/**
 *
 * @author Haofei, Fang
 * Created on April 9, 2012
*/

public class ProteinQuant{

	private String locus;
	private double ratio;
	private double revRatio;
	private double std;
	private double revStd;
	private double weightRatio;
	//private String pepNum;
	private int pepNum;
	private int pepNumTotal = -1;
	private int specCount;
        private String ratioLH;
	private String ratioLM;
	private String revRatioLM;
	private String stdLM;
        private String stdLH;
	private String revStdLM;
	private String weightRatioLM;
	private String pepNumLM;
	private String specCountLM;
	private String ratioMH;
	private String revRatioMH;
	private String stdMH;
	private String revStdMH;
	private String weightRatioMH;
	private String pepNumMH;
	private String specCountMH;
	private String desc;
        private double compositeRatio;
        private double compositeRatioStdev;
	private String pLine;
        private String lspecCount;
        private String hspecCount;
        private String areaRatio;
        private String geneName;
        

    private List<CensusPeptide> pepList = new ArrayList<CensusPeptide>();

    public String getGeneName() {
        return geneName;
    }

    public void setGeneName(String description) {
        for (String currentWord : description.split(" ")) {
            if (currentWord.startsWith("GN=")) {
                String str = currentWord.split("GN=")[1].trim();

                this.geneName = Protein.removeRef(str);
            }
        }
    }

    
    
    public String getLspecCount() {
        return lspecCount;
    }

    public void setLspecCount(String lspecCount) {
        this.lspecCount = lspecCount;
    }

    public String getHspecCount() {
        return hspecCount;
    }

    public void setHspecCount(String hspecCount) {
        this.hspecCount = hspecCount;
    }

    public String getAreaRatio() {
        return areaRatio;
    }

    public void setAreaRatio(String areaRatio) {
        this.areaRatio = areaRatio;
    }

	public void setLocus(String l){
		this.locus = l;
	}
	public String getLocus(){
		return locus;
	}
	public void setRatio(String l){
            if("NA".equals(l) || "NI".equals(l))
		this.ratio = -1.0;
            else
                this.ratio = Double.parseDouble(l);
	}
	public void setRatio(double l){
		this.ratio = l;
	}
	public double getRatio(){
		return ratio;
	}
	public void setRevRatio(String l){
            if("NA".equals(l) || "NI".equals(l))
                this.revRatio= -1.0;
            else
		this.revRatio= Double.parseDouble(l);
	}
	public void setRevRatio(double l){
		this.revRatio= l;
	}
	public double getRevRatio(){
		return revRatio;
	}
	public void setStd(double l){            
		this.std= l;
	}
        
	public void setStd(String l){
            if("NA".equals(l) || "NI".equals(l))
                this.std = -1.0;
            else {
                try {
                    this.std= Double.parseDouble(l);
                }catch(Exception e) {
                    this.std = -1.0;
                }
            }
	}
	public double getStd(){
		return std;
	}
	public void setRevStd(double l){
		this.revStd= l;
	}
	public void setRevStd(String l){
            if("NA".equals(l) || "NI".equals(l) )
                this.revStd= -1.0;
            else {
                
                try {
                    this.revStd= Double.parseDouble(l);
                } catch (Exception e) {
                    this.revStd= -1.0;
                }
            }
	}
	public double getRevStd(){
		return revStd;
	}
	public void setWeightRatio(String l){
            if("NA".equals(l) || "NI".equals(l) )
                this.weightRatio= -1.0;
            else {
                try {
                    this.weightRatio= Double.parseDouble(l);
                } catch(Exception e) {
                    this.weightRatio= -1.0;
                }
            }
		
	}
	public void setWeightRatio(double l){
		this.weightRatio= l;
	}
	public double getWeightRatio(){
		return weightRatio;
	}

	public void setPepNum(String l){
            if("NA".equals(l) || "NI".equals(l) )               
		this.pepNum= -1;
            else
                this.pepNum= Integer.parseInt(l);
	}
	public void setPepNum(int l){
		this.pepNum= l;
	}
	public int getPepNum(){
		return pepNum;
	}

	public void setSpecCount(String l){
            if("NA".equals(l) || "NI".equals(l) )               
		this.specCount= -1;
            else
                this.specCount= Integer.parseInt(l);
            		
	}
	public void setSpecCount(int l){
		this.specCount = l;
	}
	public int getSpecCount(){
		return specCount;
	}
	public void setRatioLM(String l){
		this.ratioLM = l;
	}
	public String getRatioLM(){
		return ratioLM;
	}
	public void setRevRatioLM(String l){
            if("NA".equals(l) || "NI".equals(l))
		this.revRatioLM= "-0.1";
            else
                this.revRatioLM= l;
		
	}
	public String getRevRatioLM(){
		return revRatioLM;
	}
	public void setStdLM(String l){
		this.stdLM= l;
	}
	public String getStdLM(){
		return stdLM;
	}
	public void setRevStdLM(String l){
            if("NA".equals(l) || "NI".equals(l))
		this.revStdLM= "-0.1";
            else
                this.revStdLM= l;
	}
	public String getRevStdLM(){
		return revStdLM;
	}
	public void setWeightRatioLM(String l){
		this.weightRatioLM= l;
	}
	public String getWeightRatioLM(){
		return weightRatioLM;
	}
	public void setPepNumLM(String l){
		this.pepNumLM= l;
	}
	public String getPepNumLM(){
		return pepNumLM;
	}
	public void setSpecCountLM(String l){
		this.specCountLM = l;
	}
	public String getSpecCountLM(){
		return specCountLM;
	}
	public void setRatioMH(String l){
		this.ratioMH = l;
	}
	public String getRatioMH(){
		return ratioMH;
	}
	public void setRevRatioMH(String l){
            if("NA".equals(l) || "NI".equals(l))
		this.revRatioMH= "-0.1";
            else
                this.revRatioMH= l;                        
	}
	public String getRevRatioMH(){
		return revRatioMH;
	}
	public void setStdMH(String l){
		this.stdMH= l;
	}
	public String getStdMH(){
		return stdMH;
	}
	public void setRevStdMH(String l){
            if("NA".equals(l) || "NI".equals(l))
		this.revStdMH = "-0.1";
            else
                this.revStdMH = l;                                    
	}
	public String getRevStdMH(){
		return revStdMH;
	}
	public void setWeightRatioMH(String l){
		this.weightRatioMH= l;
	}
	public String getWeightRatioMH(){
		return weightRatioMH;
	}
	public void setPepNumMH(String l){
		this.pepNumMH= l;
	}
	public String getPepNumMH(){
		return pepNumMH;
	}
	public void setSpecCountMH(String l){
		this.specCountMH = l;
	}
	public String getSpecCountMH(){
		return specCountMH;
	}

	public void setDesc(String l){
		this.desc = l;
	}
	public String getDesc(){
		return desc;
	}

        public double getCompositeRatio() {
            return compositeRatio;
        }

        public void setCompositeRatio(double compositeRatio) {
            this.compositeRatio = compositeRatio;
        }

        public double getCompositeRatioStdev() {
            return compositeRatioStdev;
        }

        public void setCompositeRatioStdev(double compositeRatioStdev) {
            this.compositeRatioStdev = compositeRatioStdev;
        }

    public List<CensusPeptide> getPepList() {
        return pepList;
    }

    public void setPepList(List<CensusPeptide> pepList) {
        this.pepList = pepList;
    }

    public String getRatioLH() {
        return ratioLH;
    }

    public void setRatioLH(String ratioLH) {
        this.ratioLH = ratioLH;
    }

    public String getStdLH() {
        return stdLH;
    }

    public void setStdLH(String stdLH) {
        this.stdLH = stdLH;
    }
	public int getPepNumTotal() {
		return pepNumTotal;
	}
	public void setPepNumTotal(int pepNumTotal) {
		this.pepNumTotal = pepNumTotal;
	}
	
	public void setPepNumTotal(String l){
        if("NA".equals(l) || "NI".equals(l) )               
        	this.pepNumTotal= -1;
        else
            this.pepNumTotal= Integer.parseInt(l);
	}

	public String getpLine() {
		return pLine;
	}

	public void setpLine(String pLine) {
		this.pLine = pLine;
	}
}
