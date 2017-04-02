package com.ipa.ip2.api.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amit on 03-01-2017.
 */
public class LabelFreeStatPline {
    //	PLINE	ACCESSION	p-value	p-value_ratio_based	MEDIAN_LOG_RATIO_1	MEDIAN_LOG_RATIO_2	MEDIAN_LOG_RATIO_3	MEDIAN_LOG_RATIO_4	MEDIAN_LOG_RATIO_5	MEDIAN_LOG_RATIO_6	DESCRIPTION
    private String accession = null;

    private String pValue = "";
    private String pValueIIT = "";
    private String qValue = null;
    private String qValueIIT = null;

    private String pValueRatioBased = null;

    private List<MedianLogRatio> medianLogRatios = new ArrayList<MedianLogRatio>();

    private List<Scount> scounts = new ArrayList<Scount>();
    private List<LabelfreeMedianRatio> medianRatios = new ArrayList<LabelfreeMedianRatio>();
    private List<String> averageIntensity = new ArrayList<String>();
    private List<String> averageIITIntensity = new ArrayList<String>();
    private List<String> averageAVGIntensity = new ArrayList<String>();
    private List<String> averageAVGIITIntensity = new ArrayList<String>();
    private List<String> ratioIntensity = new ArrayList<String>();
    private List<String> ratioIITIntensity = new ArrayList<String>();
    private List<String> logratioIntensity = new ArrayList<String>();
    private List<String> logratioIITIntensity = new ArrayList<String>();
    private List<String>  individualIntensity = new ArrayList<String>();

    private double peptideIntensityDividedByPLength = 0.0;
    private int proteinLength;



    private String desc = null;
    private String gene;

    public String getqValue() {
        return qValue;
    }

    public void setqValue(String qValue) {
        this.qValue = qValue;
    }

    public String getqValueIIT() {
        return qValueIIT;
    }

    public void setqValueIIT(String qValueIIT) {
        this.qValueIIT = qValueIIT;
    }

    public String getGene() {
        return gene;
    }

    public void setGene(String gene) {
        this.gene = gene;
    }

    public List<Double> getScoutList(List<Scount> s){
        List<Double> tempList = new ArrayList<Double>();
        for(Scount val :s){
            tempList.add(Double.parseDouble(val.getScountValue()));
        }
        return tempList;
    }
    public List<Double> getMedianLogList(List<MedianLogRatio> s){
        List<Double> tempList = new ArrayList<Double>();
        for(MedianLogRatio val :s){
            tempList.add(Double.parseDouble(val.getMedianLogValue()));
        }
        return tempList;
    }

    public List<String> getAverageAVGIntensity() {
        return averageAVGIntensity;
    }

    public void setAverageAVGIntensity(List<String> averageAVGIntensity) {
        this.averageAVGIntensity = averageAVGIntensity;
    }

    public List<String> getAverageAVGIITIntensity() {
        return averageAVGIITIntensity;
    }

    public void setAverageAVGIITIntensity(List<String> averageAVGIITIntensity) {
        this.averageAVGIITIntensity = averageAVGIITIntensity;
    }

    public List<String> getRatioIntensity() {
        return ratioIntensity;
    }

    public void setRatioIntensity(List<String> ratioIntensity) {
        this.ratioIntensity = ratioIntensity;
    }

    public List<String> getRatioIITIntensity() {
        return ratioIITIntensity;
    }

    public void setRatioIITIntensity(List<String> ratioIITIntensity) {
        this.ratioIITIntensity = ratioIITIntensity;
    }

    public List<String> getLogratioIntensity() {
        return logratioIntensity;
    }

    public void setLogratioIntensity(List<String> logratioIntensity) {
        this.logratioIntensity = logratioIntensity;
    }

    public List<String> getLogratioIITIntensity() {
        return logratioIITIntensity;
    }

    public void setLogratioIITIntensity(List<String> logratioIITIntensity) {
        this.logratioIITIntensity = logratioIITIntensity;
    }



    public String getpValueIIT() {
        return pValueIIT;
    }

    public void setpValueIIT(String pValueIIT) {
        this.pValueIIT = pValueIIT;
    }


    public List<String> getAverageIITIntensity() {
        return averageIITIntensity;
    }

    public void setAverageIITIntensity(List<String> averageIITIntensity) {
        this.averageIITIntensity = averageIITIntensity;
    }


    public List<String> getAverageIntensity() {
        return averageIntensity;
    }

    public void setAverageIntensity(List<String> averageIntensity) {
        this.averageIntensity = averageIntensity;
    }



    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public String getpValue() {
        return pValue;
    }

    public void setpValue(String pValue) {
        this.pValue = pValue;
    }

    public String getpValueRatioBased() {
        return pValueRatioBased;
    }

    public void setpValueRatioBased(String pValueRatioBased) {
        this.pValueRatioBased = pValueRatioBased;
    }

    public List<MedianLogRatio> getMedianLogRatios() {
        return medianLogRatios;
    }

    public void setMedianLogRatios(List<MedianLogRatio> medianLogRatios) {
        this.medianLogRatios = medianLogRatios;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Scount> getScounts() {
        return scounts;
    }

    public void setScounts(List<Scount> scounts) {
        this.scounts = scounts;
    }

    public List<LabelfreeMedianRatio> getMedianRatios() {
        return medianRatios;
    }

    public void setMedianRatios(List<LabelfreeMedianRatio> medianRatios) {
        this.medianRatios = medianRatios;
    }

    public List<String> getIndividualIntensity() {
        return individualIntensity;
    }

    public void setIndividualIntensity(List<String> individualIntensity) {
        this.individualIntensity = individualIntensity;
    }

    public double getPeptideIntensityDividedByPLength() {
        return peptideIntensityDividedByPLength;
    }

    public void setPeptideIntensityDividedByPLength(double peptideIntensityDividedByPLength) {
        this.peptideIntensityDividedByPLength = peptideIntensityDividedByPLength;
    }

    public int getProteinLength() {
        return proteinLength;
    }

    public void setProteinLength(int proteinLength) {
        this.proteinLength = proteinLength;
    }
}
