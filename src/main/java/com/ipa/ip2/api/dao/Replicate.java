package com.ipa.ip2.api.dao;

import java.io.Serializable;
import java.util.Date;

/**
 * Replicate
 * @author Amit
 * @version 1.0
 */
public class Replicate implements Comparable, Serializable {

    /**
     * Experiment date
     */
    private Date expDate;

    /**
     * Experiment ID
     */
    private int expid;

    /**
     * Search date
     */
    private Date searchDate;

    /**
     * Sample name
     */
    private String sampleName;

    /**
     * Sample description
     */
    private String sampleDescription;

    /**
     * Search name
     */
    private String searchName;

    /**
     * Search ID
     */
    private int searchId;

    /**
     * Path
     */
    private String path;

    /**
     * Label type
     */
    private String labelType;

    /**
     * Label folder
     */
    private String labelFolder;

    /**
     * Label reverse
     */
    private boolean labelReverse;

    public Date getExpDate() {
        return expDate;
    }

    public int getExpid() {
        return expid;
    }

    public void setExpid(int expid) {
        this.expid = expid;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public Date getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(Date searchDate) {
        this.searchDate = searchDate;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getSampleDescription() {
        return sampleDescription;
    }

    public void setSampleDescription(String sampleDescription) {
        this.sampleDescription = sampleDescription;
    }

    public int getSearchId() {
        return searchId;
    }

    public void setSearchId(int searchId) {
        this.searchId = searchId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setLabelType(String lt){
        labelType = lt;
    }
    public String getLabelType(){
        return labelType;
    }
    public void setLabelReverse(boolean lr){
        labelReverse = lr;
    }
    public boolean getLabelReverse(){
        if(this.labelType==null){
            return false;
        }
        else{
            if(labelType.contains("_HL") || labelType.contains("_ML") || labelType.contains("_HM") || labelType.contains("_REV")){
                return true;
            }
            else{
                return false;
            }
        }
    }
    public void setLabelFolder(String lf){
        this.labelFolder = lf;
    }
    public String getLabelFolder(){
        if(labelType==null || labelType.contains("FOR") || labelType.contains("REV")){
            return "";
        }
        else{
            String lt = labelType.substring(labelType.lastIndexOf("_")+1);
            if(getLabelReverse()){
                StringBuffer sbf = new StringBuffer();
                sbf.append(lt.charAt(1));
                sbf.append(lt.charAt(0));
                return sbf.toString();
            }
            else{
                return lt;
            }
        }
    }

    public int compareTo(Object o) throws ClassCastException {

        Replicate r = (Replicate)o;
        if(r.getLabelReverse() == true) {
            if(this.getLabelReverse()==true) {
                return this.getSampleName().compareTo(r.getSampleName());
            } else {
                return -1;
            }
        } else {
            return 1;
        }
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }



}