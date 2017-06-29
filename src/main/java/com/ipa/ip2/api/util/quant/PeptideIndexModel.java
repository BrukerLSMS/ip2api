
/*
* Copyright (c) 2008 Integrated Proteomics Applications.  All rights reserved.  
*/

package com.ipa.ip2.api.util.quant;

/**
 *
 * @author Sung Kyu, Robin, Park
 * @email robinparky@yahoo.com
 * Created on Jul 31, 2009 
 * $Revision: 1.5 $
 * $Date: 2012/05/18 03:58:03 $
 */

public class PeptideIndexModel {
    private int uniqueIndex = -1;
    private int seqIndex = -1;
    private int ratioIndex = -1;
    private int ratioRevIndex = -1;
    private int regIndex = -1;
    private int detIndex = -1;
    private int pvalueIndex = -1;
    private int xcorrIndex = -1;
    private int dcnIndex = -1;
    private int samAreaIndex = -1;
    private int refAreaIndex = -1;
    private int areaRatioIndex = -1;
    private int profileScoreIndex = -1;
    private int singletonScoreIndex = -1; 
    private int fileNameIndex = -1;
    private int scanIndex = -1;
    private int csIndex = -1;
    private int enrichmentIndex = -1;
    private int localizationIndex = -1;
    private int modSequenceIndex = -1;
    private int debunkerScoreIndex = -1;
    
    public  PeptideIndexModel() {
        uniqueIndex = -1;
        seqIndex = -1;
        ratioIndex = -1;
       regIndex = -1;
         detIndex = -1;
        pvalueIndex = -1;
         xcorrIndex = -1;
     dcnIndex = -1;
         samAreaIndex = -1;
         refAreaIndex = -1;
         areaRatioIndex = -1;
         profileScoreIndex = -1;
         singletonScoreIndex = -1; 
         fileNameIndex = -1;
         scanIndex = -1;
       csIndex = -1;
        enrichmentIndex = -1;
        localizationIndex = -1;
        modSequenceIndex = -1;
       debunkerScoreIndex = -1;  
    } 
    public int getUniqueIndex() {
        return uniqueIndex;
    }

    public void setUniqueIndex(int uniqueIndex) {
        this.uniqueIndex = uniqueIndex;
    }

    public int getSeqIndex() {
        return seqIndex;
    }

    public void setSeqIndex(int seqIndex) {
        this.seqIndex = seqIndex;
    }

    public int getRatioIndex() {
        return ratioIndex;
    }

    public void setRatioIndex(int ratioIndex) {
        this.ratioIndex = ratioIndex;
    }

    public int getRatioRevIndex() {
        return ratioRevIndex;
    }

    public void setRatioRevIndex(int ratioRevIndex) {
        this.ratioRevIndex = ratioRevIndex;
    }

    public int getRegIndex() {
        return regIndex;
    }

    public void setRegIndex(int regIndex) {
        this.regIndex = regIndex;
    }

    public int getDetIndex() {
        return detIndex;
    }

    public void setDetIndex(int detIndex) {
        this.detIndex = detIndex;
    }

    public int getPvalueIndex() {
        return pvalueIndex;
    }

    public void setPvalueIndex(int pvalueIndex) {
        this.pvalueIndex = pvalueIndex;
    }

    public int getXcorrIndex() {
        return xcorrIndex;
    }

    public void setXcorrIndex(int xcorrIndex) {
        this.xcorrIndex = xcorrIndex;
    }

    public int getDcnIndex() {
        return dcnIndex;
    }

    public void setDcnIndex(int dcnIndex) {
        this.dcnIndex = dcnIndex;
    }

    public int getSamAreaIndex() {
        return samAreaIndex;
    }

    public void setSamAreaIndex(int samAreaIndex) {
        this.samAreaIndex = samAreaIndex;
    }

    public int getRefAreaIndex() {
        return refAreaIndex;
    }

    public void setRefAreaIndex(int refAreaIndex) {
        this.refAreaIndex = refAreaIndex;
    }

    public int getAreaRatioIndex() {
        return areaRatioIndex;
    }

    public void setAreaRatioIndex(int areaRatioIndex) {
        this.areaRatioIndex = areaRatioIndex;
    }

    public int getProfileScoreIndex() {
        return profileScoreIndex;
    }

    public void setProfileScoreIndex(int profileScoreIndex) {
        this.profileScoreIndex = profileScoreIndex;
    }
    public int getSingletonScoreIndex() {
        return singletonScoreIndex;
    }
    public void setSingletonScoreIndex(int singletonScoreIndex) {
        this.singletonScoreIndex = singletonScoreIndex;
    }
    public int getFileNameIndex() {
        return fileNameIndex;
    }

    public void setFileNameIndex(int fileNameIndex) {
        this.fileNameIndex = fileNameIndex;
    }

    public int getScanIndex() {
        return scanIndex;
    }

    public void setScanIndex(int scanIndex) {
        this.scanIndex = scanIndex;
    }

    public int getCsIndex() {
        return csIndex;
    }

    public void setCsIndex(int csIndex) {
        this.csIndex = csIndex;
    }

    public int getEnrichmentIndex() {
        return enrichmentIndex;
    }

    public void setEnrichmentIndex(int enrichmentIndex) {
        this.enrichmentIndex = enrichmentIndex;
    }

    public int getDebunkerScoreIndex() {
        return debunkerScoreIndex;
    }

    public void setDebunkerScoreIndex(int debunkerScoreIndex) {
        this.debunkerScoreIndex = debunkerScoreIndex;
    }

    public int getLocalizationIndex() {
        return localizationIndex;
    }

    public void setLocalizationIndex(int localizationIndex) {
        this.localizationIndex = localizationIndex;
    }

    public int getModSequenceIndex() {
        return modSequenceIndex;
    }

    public void setModSequenceIndex(int modSequenceIndex) {
        this.modSequenceIndex = modSequenceIndex;
    }
        
}


