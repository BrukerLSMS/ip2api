package com.ipa.ip2.api.dao;

import com.ipa.ip2.api.util.ProteinUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amit on 03-01-2017.
 */
public class ProteinIsobaric {
    private String locus;
    private String specCount;
    private String pepNum;
    private String molwt;
    private String length;
    private String seqcoverage;
    private String pI;
    private List<String> tagNameList;
    private String geneName;
    private List<Double> averageIntensityList;
    private List<Double> totalIntensityList;
    private List<Double> normalizedRatioList;
    private List<Double> normalizedMeidanRatioList;

    private String description;


    public ProteinIsobaric() {
        this.tagNameList = new ArrayList<String>();
        this.averageIntensityList = new ArrayList<Double>();
        this.totalIntensityList = new ArrayList<Double>();
        this.normalizedRatioList = new ArrayList<Double>();
        this.normalizedMeidanRatioList = new ArrayList<Double>();
    }


    public String getGeneName() {
        return geneName;
    }
    public void setGeneName(String description) {
        for (String currentWord : description.split(" ")) {
            if (currentWord.startsWith("GN=")) {
                String str = currentWord.split("GN=")[1].trim();

                this.geneName = ProteinUtils.removeRef(str);
            }
        }
    }

    public String getMolwt() {
        return molwt;
    }

    public void setMolwt(String molwt) {
        this.molwt = molwt;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getSeqcoverage() {
        return seqcoverage;
    }

    public void setSeqcoverage(String seqcoverage) {
        this.seqcoverage = seqcoverage;
    }

    public String getpI() {
        return pI;
    }

    public void setpI(String pI) {
        this.pI = pI;
    }
    public List<Double> getTotalIntensityList() {
        return totalIntensityList;
    }

    public void setTotalIntensityList(List<Double> totalIntensityList) {
        this.totalIntensityList = totalIntensityList;
    }
    public void addTotalIntensity(double d) {
        this.totalIntensityList.add(d);
    }


    public String getLocus() {
        return locus;
    }

    public void setLocus(String locus) {
        this.locus = locus;
    }

    public String getPepNum() {
        return pepNum;
    }

    public void setPepNum(String pepNum) {
        this.pepNum = pepNum;
    }

    public String getSpecCount() {
        return specCount;
    }

    public void setSpecCount(String specCount) {
        this.specCount = specCount;
    }

    public List<String> getTagNameList() {
        return tagNameList;
    }

    public void setTagNameList(List<String> tagNameList) {
        this.tagNameList = tagNameList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Double> getAverageIntensityList() {
        return averageIntensityList;
    }

    public void setAverageIntensityList(List<Double> averageIntensityList) {
        this.averageIntensityList = averageIntensityList;
    }

    public List<Double> getNormalizedRatioList() {
        return normalizedRatioList;
    }

    public void setNormalizedRatioList(List<Double> normalizedRatioList) {
        this.normalizedRatioList = normalizedRatioList;
    }

    public void addAverageIntensity(double d) {
        this.averageIntensityList.add(d);
    }

    public void addNormalizedRatio(double d) {
        this.normalizedRatioList.add(d);
    }

    public void addNormalizedMedianRatio(double d) {
        this.normalizedMeidanRatioList.add(d);
    }

    public List<Double> getNormalizedMeidanRatioList() {
        return normalizedMeidanRatioList;
    }

    public void setNormalizedMeidanRatioList(List<Double> normalizedMeidanRatioList) {
        this.normalizedMeidanRatioList = normalizedMeidanRatioList;
    }
}
