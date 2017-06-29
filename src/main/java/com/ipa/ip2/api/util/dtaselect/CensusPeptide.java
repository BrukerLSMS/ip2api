package com.ipa.ip2.api.util.dtaselect;

/**
 * @author  Robin Park
 * @version $Id: CensusPeptide.java,v 1.3 2012/08/21 15:43:23 cvsuser Exp $
 */

import java.util.*;

public class CensusPeptide extends Peptide
{
    private boolean isSline;// 'SLINE': true, '&SLINE': false
    private String sline;
 
    private boolean unique;
    private String sequence;
    private String ratio;
    private String ratioRev;
    private String regFactor;//REGRESSION_FACTOR
    private String detFactor;//DETERMINANT_FACTOR
    private String pValue;
    private String xCorr;
    private String deltCN;
    private String samInt;
    private String refInt;
    private String areaRatio;
    private String profScore;//SINGLETON_SCORE or PROFILE_SCORE
    private String fileName;
    private String scan;
    private String cs;
    private String enrichment;
    private String proteins;
    private String proteinDescription;
    private String singleton;

    private static int slineIndex = -1;
    private static int uniqueIndex = -1;
    private static int sequenceIndex = -1;
    private static int ratioIndex = -1;
    private static int ratioRevIndex = -1;
    private static int regFactorIndex = -1;
    private static int detFactorIndex = -1;
    private static int pValueIndex = -1;
    private static int xCorrIndex = -1;
    private static int deltCNIndex = -1;
    private static int samIntIndex = -1;
    private static int refIntIndex = -1;
    private static int areaRatioIndex = -1;
    private static int profScoreIndex = -1;
    private static int fileNameIndex = -1;
    private static int scanIndex = -1;
    private static int csIndex = -1;
    private static int enrichmentIndex = -1;
    private static int proteinsIndex=-1;
    private static int proteinDescriptionIndex=-1;
    private static int probabilityScoreIndex=-1;
    
    private static int slineIndexSingleton = -1;
    private static int uniqueIndexSingleton = -1;
    private static int sequenceIndexSingleton = -1;
    private static int ratioIndexSingleton = -1;
    private static int ratioRevIndexSingleton = -1;
    private static int regFactorIndexSingleton = -1;
    private static int detFactorIndexSingleton = -1;
    private static int pValueIndexSingleton = -1;
    private static int xCorrIndexSingleton = -1;
    private static int deltCNIndexSingleton = -1;
    private static int samIntIndexSingleton = -1;
    private static int refIntIndexSingleton = -1;
    private static int areaRatioIndexSingleton = -1;
    private static int profScoreIndexSingleton = -1;
    private static int fileNameIndexSingleton = -1;
    private static int scanIndexSingleton = -1;
    private static int csIndexSingleton = -1;
    private static int enrichmentIndexSingleton = -1;
    private static int proteinsIndexSingleton = -1;
    private static int proteinDescriptionIndexSingleton = -1;
    private static int probabilityScoreIndexSingleton = -1;
    /*
        private static int slineIndex = 0;
    private static int uniqueIndex = 1;
    private static int sequenceIndex = 2;
    private static int ratioIndex = 3;
    private static int ratioRevIndex = 3;
    private static int regFactorIndex = 4;
    private static int detFactorIndex = 5;
    private static int pValueIndex = 6;
    private static int xCorrIndex = 7;
    private static int deltCNIndex = 8;
    private static int samIntIndex = 9;
    private static int refIntIndex = 10;
    private static int areaRatioIndex = 11;
    private static int profScoreIndex = 12;
    private static int fileNameIndex = 13;
    private static int scanIndex = 14;
    private static int csIndex = 15;
    private static int enrichmentIndex = 16;
    private static int proteinsIndex=17;
    private static int proteinDescriptionIndex=18;
    */

    private String[] peptideLine;
    private int hashcode = -1;
    private static int slineCount = -1;

    private List<Protein> proteinList = new ArrayList<Protein>();




    public int hashCode() {
        if(hashcode == -1) {
            // fileName contains the scan number
            hashcode = (getSequence() + cs+fileName).hashCode();
          }
        return hashcode;
    }
    public boolean equals(Object o) {
        CensusPeptide p = (CensusPeptide)o;
        return getSequence().equals(p.getSequence()) && cs.equals(p.getCs()) &&fileName.equals(p.fileName);
    }

    public static void setFeatureIndices(String features) {
        String [] contents = features.split("\t");
        for(int i = 0; i < contents.length; i++) {
            String s = contents[i].trim();

            if(s.startsWith("SLINE")) {
                slineIndex = i-1;
            } else if(s.startsWith("UNI")) {
                uniqueIndex = i-1;           
            } else if(s.startsWith("SEQUENCE")) {
                sequenceIndex = i-1;
            } else if(s.equals("RATIO")) {
                ratioIndex = i-1; 
            } else if(s.equals("RATIO_REV") || s.equals("REV_SLOPE_RATIO") || s.equals("REV_RATIO")) {
                ratioRevIndex = i-1;
            } else if(s.startsWith("REGRESSION")) {
                regFactorIndex = i-1;
            } else if(s.startsWith("DETERMINANT_")) {            
                detFactorIndex = i-1;
            } else if(s.startsWith("PVALUE") || s.equals("PROBABILITY_SCORE")) {
                pValueIndex = i-1;            
            } else if(s.startsWith("XC")) {
                xCorrIndex = i-1;
            } else if(s.startsWith("deltaCN")) {
                deltCNIndex = i-1;            
            } else if(s.startsWith("SAM_INT")) {
                samIntIndex = i-1;
            } else if(s.startsWith("REF_INT")) {
                refIntIndex = i-1;                
            } else if(s.startsWith("AREA")) {
                areaRatioIndex = i-1;
            } else if((s.startsWith("SINGLETON_SCORE")||s.startsWith("PROFILE_SCORE"))) {
                profScoreIndex = i-1;
            } else if(s.startsWith("FILE_NAME")) {
                fileNameIndex = i-1;
            } else if(s.startsWith("SCAN")) {
                scanIndex = i-1;
            } else if(s.startsWith("CS")) {
                csIndex = i-1;
            } else if(s.startsWith("ENRICHMENT")) {
                enrichmentIndex = i-1;
            } else if(s.startsWith("Proteins")) {
                proteinsIndex = i-1;
            } else if(s.startsWith("Protein Descriptions")) {                
                proteinDescriptionIndex = i-1;
            } else if(s.equals("PROBABILITY_SCORE")) {                
                probabilityScoreIndex = i-1;
            } 
            
                
                /*
            slineIndex = (s.startsWith("SLINE")||s.startsWith("&SLINE"))? i-1 : slineIndex;
            uniqueIndex = s.startsWith("UNI")? i-1 : uniqueIndex;
            sequenceIndex = s.startsWith("SEQUENCE")? i-1 : sequenceIndex;
            ratioIndex = s.equals("RATIO")? i-1 : ratioIndex;
            //if(s.equals("RATIO_REV")||s.equals("REV_SLOPE_RATIO")) 
            //if(true) 
            //    ratioRevIndex = i-1;
            //else 
            //   ratioRevIndex = ratioRevIndex;
            
            ratioRevIndex = (s.equals("RATIO_REV")||s.equals("REV_SLOPE_RATIO"))? i-1 : ratioRevIndex;
            regFactorIndex = s.startsWith("REGRESSION")? i-1 : regFactorIndex;
            detFactorIndex = s.startsWith("DETERMINANT_")? i-1 : detFactorIndex;
            pValueIndex = (s.startsWith("PVALUE")||s.equals("PROBABILITY_SCORE"))? i-1 : pValueIndex;
            xCorrIndex = s.startsWith("XC")? i-1 : xCorrIndex;
            deltCNIndex = s.startsWith("deltaCN")? i-1 : deltCNIndex;
            samIntIndex = s.startsWith("SAM")? i-1 : samIntIndex;
            refIntIndex = s.startsWith("REF")? i-1 : refIntIndex;
            areaRatioIndex = s.startsWith("AREA")? i-1 : areaRatioIndex;
            profScoreIndex = (s.startsWith("SINGLETON_SCORE")||s.startsWith("PROFILE_SCORE"))? i-1 : profScoreIndex;
            fileNameIndex = s.startsWith("FILE_NAME")? i-1 : fileNameIndex;
            scanIndex = s.startsWith("SCAN")? i-1 : scanIndex;
            csIndex = s.startsWith("CS")? i-1 : csIndex;
            enrichmentIndex = s.startsWith("ENRICHMENT")? i-1 : enrichmentIndex;
            proteinsIndex = s.startsWith("Proteins")? i-1 : proteinsIndex;
            proteinDescriptionIndex = s.startsWith("Protein Descriptions")? i-1 : proteinDescriptionIndex;
            probabilityScoreIndex = s.equals("PROBABILITY_SCORE")? i-1 : probabilityScoreIndex;
            */
         
        }
     //   System.out.println("read index............ "  );
    }
   
    public static void setSingletonFeatureIndices(String features) {
        String [] contents = features.split("\t");
        slineCount = contents.length;
        for(int i = 0; i < contents.length; i++) {
            String s = contents[i].trim();
            slineIndexSingleton = (s.startsWith("&SLINE"))? i-1 : slineIndexSingleton;
            uniqueIndexSingleton = s.startsWith("UNI")? i-1 : uniqueIndexSingleton;
            sequenceIndexSingleton = s.startsWith("SEQUENCE")? i-1 : sequenceIndexSingleton;
            ratioIndexSingleton = s.equals("RATIO")? i-1 : ratioIndexSingleton;
            ratioRevIndexSingleton = s.equals("RATIO_REV")? i-1 : ratioRevIndexSingleton;
            regFactorIndexSingleton = s.startsWith("REGRESSION")? i-1 : regFactorIndexSingleton;
            detFactorIndexSingleton = s.startsWith("DETERMINANT_")? i-1 : detFactorIndexSingleton;
            pValueIndexSingleton = s.startsWith("PVALUE")? i-1 : pValueIndexSingleton;
            xCorrIndexSingleton = s.startsWith("XC")? i-1 : xCorrIndexSingleton;
            deltCNIndexSingleton = s.startsWith("deltaCN")? i-1 : deltCNIndexSingleton;
            samIntIndexSingleton = s.startsWith("SAM")? i-1 : samIntIndexSingleton;
            refIntIndexSingleton = s.startsWith("REF")? i-1 : refIntIndexSingleton;
            areaRatioIndexSingleton = s.startsWith("AREA")? i-1 : areaRatioIndexSingleton;
            profScoreIndexSingleton = (s.startsWith("SINGLETON_SCORE")||s.startsWith("PROFILE_SCORE"))? i-1 : profScoreIndexSingleton;
            fileNameIndexSingleton = s.startsWith("FILE_NAME")? i-1 : fileNameIndexSingleton;
            scanIndexSingleton = s.startsWith("SCAN")? i-1 : scanIndexSingleton;
            csIndexSingleton = s.startsWith("CS")? i-1 : csIndexSingleton;
            enrichmentIndexSingleton = s.startsWith("ENRICHMENT")? i-1 : enrichmentIndexSingleton;
            proteinsIndexSingleton = s.startsWith("Proteins")? i-1 : proteinsIndexSingleton;
            proteinDescriptionIndexSingleton = s.startsWith("Protein Descriptions")? i-1 : proteinDescriptionIndexSingleton;
            probabilityScoreIndexSingleton = s.equals("PROBABILITY_SCORE")? i-1 : probabilityScoreIndexSingleton;
            
         
        }
  //      System.out.println("read index............ "  );
    }
    public CensusPeptide() {
    	
    }
    public CensusPeptide(String[] peptideLine)
    {
        this.peptideLine = peptideLine;     
        parseLine();     
    }
    
    public CensusPeptide(String[] peptideLine,String singleTon)
    {
        this.peptideLine = peptideLine;     
        parseSingltonLine();     
    }
    
    
//    H	&SLINE	UNIQUE	SEQUENCE	RATIO	REGRESSION_FACTOR	DETERMINANT_FACTOR	XCorr	deltaCN	SAM_INT	REF_INT	PEAK_INT	AREA_RATIO	SINGLETON_SCORE	FILE_NAME	SCAN	CS
    
    private void parseSingltonLine() throws ArrayIndexOutOfBoundsException
    {

        if(peptideLine.length != (slineCount-1) ) return;  // old file format

    	this.setSline(peptideLine[slineIndexSingleton]);
        this.setUnique(peptideLine[uniqueIndexSingleton]);
        this.setSequence(peptideLine[sequenceIndexSingleton]);
        
        
        this.setRatio(peptideLine[ratioIndexSingleton]);
        
        
        
//        if(ratioRevIndexSingleton<0)
//            this.setRatioRev("-1");
//        else
         //  this.setRatioRev(peptideLine[ratioRevIndexSingleton]);
        
        this.setRegFactor(peptideLine[regFactorIndexSingleton]);
        this.setDetFactor(peptideLine[detFactorIndexSingleton]);
        //this.setpValue(peptideLine[pValueIndexSingleton]);
        this.setxCorr(peptideLine[xCorrIndexSingleton]);
        this.setDeltCN(peptideLine[deltCNIndexSingleton]);
        this.setSamInt(peptideLine[samIntIndexSingleton]);
        this.setRefInt(peptideLine[refIntIndexSingleton]);
        this.setAreaRatio(peptideLine[areaRatioIndexSingleton]);
        this.setProfScore(peptideLine[profScoreIndexSingleton]);
        this.setFileName(peptideLine[fileNameIndexSingleton]);
        this.setScan(peptideLine[scanIndexSingleton]);
        this.setCs(peptideLine[csIndexSingleton]);
        //if (this.isSline)
            
//        if(peptideLine.length-1>enrichmentIndexSingleton)
//            this.setEnrichment(peptideLine[enrichmentIndexSingleton]);
        
        this.setProteins(peptideLine[peptideLine.length-2]);
        this.setProteinDescription(peptideLine[peptideLine.length-1]);
        
        this.setSingleton("singleton");
    	
    }
    
    
    
    
        
    
    private void parseLine() throws ArrayIndexOutOfBoundsException
    {
       this.setSline(peptideLine[slineIndex]);
        this.setUnique(peptideLine[uniqueIndex]);
        this.setSequence(peptideLine[sequenceIndex]);
        
        
        if(ratioIndex>=0) this.setRatio(peptideLine[ratioIndex]);
        

        if(ratioRevIndex<0)
            this.setRatioRev("-1");
        else
            this.setRatioRev(peptideLine[ratioRevIndex]);

        if(regFactorIndex>=0) this.setRegFactor(peptideLine[regFactorIndex]);
        if(detFactorIndex>=0) this.setDetFactor(peptideLine[detFactorIndex]);
        if(pValueIndex>=0)this.setpValue(peptideLine[pValueIndex]);
        if(xCorrIndex>=0)this.setxCorr(peptideLine[xCorrIndex]);
        if(deltCNIndex>=0)this.setDeltCN(peptideLine[deltCNIndex]);
        if(samIntIndex>=0)this.setSamInt(peptideLine[samIntIndex]);
        if(refIntIndex>=0)this.setRefInt(peptideLine[refIntIndex]);
        if(areaRatioIndex>=0) this.setAreaRatio(peptideLine[areaRatioIndex]);
        if(profScoreIndex>=0) this.setProfScore(peptideLine[profScoreIndex]);
        if(fileNameIndex>=0) this.setFileName(peptideLine[fileNameIndex]);
        if(scanIndex>=0)this.setScan(peptideLine[scanIndex]);
        if(csIndex>=0) this.setCs(peptideLine[csIndex]);
        //if (this.isSline)
            
        if(peptideLine.length-1>enrichmentIndex && enrichmentIndex>=0)
            this.setEnrichment(peptideLine[enrichmentIndex]);
       
        this.setProteins(peptideLine[peptideLine.length-2]);
        this.setProteinDescription(peptideLine[peptideLine.length-1]);
        this.setSingleton("");
    }

    public String getAreaRatio() {
        return areaRatio;
    }

    public void setAreaRatio(String areaRatio) {
        this.areaRatio = areaRatio;
    }

    public String getCs() {
        return cs;
    }

    public void setCs(String cs) {
        this.cs = cs;
    }

    public String getDeltCN() {
        return deltCN;
    }

    public void setDeltCN(String deltCN) {
        this.deltCN = deltCN;
    }

    public String getDetFactor() {
        return detFactor;
    }

    public void setDetFactor(String detFactor) {
        this.detFactor = detFactor;
    }

    public String getEnrichment() {
        return enrichment;
    }

    public void setEnrichment(String enrichment) {
        this.enrichment = enrichment;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getHashcode() {
        return hashcode;
    }

    public void setHashcode(int hashcode) {
        this.hashcode = hashcode;
    }

    public boolean isIsSline() {
        return isSline;
    }

    public void setIsSline(boolean isSline) {
        this.isSline = isSline;
    }

   
    public String getpValue() {
        return pValue;
    }

    public void setpValue(String pValue) {
        this.pValue = pValue;
    }

    public String getProfScore() {
        return profScore;
    }

    public void setProfScore(String profScore) {
        this.profScore = profScore;
    }

    public String getProteinDescription() {
        return proteinDescription;
    }

    public void setProteinDescription(String proteinDescription) {
        this.proteinDescription = proteinDescription;
    }

    public String getProteins() {
        return proteins;
    }

    public void setProteins(String proteins) {
        this.proteins = proteins;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getRatioRev() {
        return ratioRev;
    }

    public void setRatioRev(String ratioRev) {
        this.ratioRev = ratioRev;
    }

    public String getRefInt() {
        return refInt;
    }

    public void setRefInt(String refInt) {
        this.refInt = refInt;
    }

    public String getRegFactor() {
        return regFactor;
    }

    public void setRegFactor(String regFactor) {
        this.regFactor = regFactor;
    }

    public String getSamInt() {
        return samInt;
    }

    public void setSamInt(String samInt) {
        this.samInt = samInt;
    }

    public String getScan() {
        return scan;
    }

    public void setScan(String scan) {
        this.scan = scan;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getSline() {
        return sline;
    }

    public void setSline(String sline) {
        if(sline.equals("SLINE")) isSline=true;
        else isSline=false;
        this.sline = sline;
    }

    public boolean isUnique() {

    	
        return unique;
    }

    public void setUnique(String unique) {
        if(unique.equals("U")) this.unique=true;
         else this.unique = false;
    }

    public String getxCorr() {
        return xCorr;
    }

    public void setxCorr(String xCorr) {
        this.xCorr = xCorr;
    }
   

  public void addProtein(Protein protein) {
    	this.proteinList.add(protein);

    }
    public List<Protein> getProteinList() {
        return proteinList;
    }

    public void setProteinList(List<Protein> proteinList) {
        this.proteinList = proteinList;
    }

    public static int getSlineIndex() {
        return slineIndex;
    }

    public static void setSlineIndex(int slineIndex) {
        CensusPeptide.slineIndex = slineIndex;
    }

    public static int getUniqueIndex() {
        return uniqueIndex;
    }

    public static void setUniqueIndex(int uniqueIndex) {
        CensusPeptide.uniqueIndex = uniqueIndex;
    }

    public static int getSequenceIndex() {
        return sequenceIndex;
    }

    public static void setSequenceIndex(int sequenceIndex) {
        CensusPeptide.sequenceIndex = sequenceIndex;
    }

    public static int getRatioIndex() {
        return ratioIndex;
    }

    public static void setRatioIndex(int ratioIndex) {
        CensusPeptide.ratioIndex = ratioIndex;
    }

    public static int getRatioRevIndex() {
        return ratioRevIndex;
    }

    public static void setRatioRevIndex(int ratioRevIndex) {
        CensusPeptide.ratioRevIndex = ratioRevIndex;
    }

    public static int getRegFactorIndex() {
        return regFactorIndex;
    }

    public static void setRegFactorIndex(int regFactorIndex) {
        CensusPeptide.regFactorIndex = regFactorIndex;
    }

    public static int getDetFactorIndex() {
        return detFactorIndex;
    }

    public static void setDetFactorIndex(int detFactorIndex) {
        CensusPeptide.detFactorIndex = detFactorIndex;
    }

    public static int getpValueIndex() {
        return pValueIndex;
    }

    public static void setpValueIndex(int pValueIndex) {
        CensusPeptide.pValueIndex = pValueIndex;
    }

    public static int getxCorrIndex() {
        return xCorrIndex;
    }

    public static void setxCorrIndex(int xCorrIndex) {
        CensusPeptide.xCorrIndex = xCorrIndex;
    }

    public static int getDeltCNIndex() {
        return deltCNIndex;
    }

    public static void setDeltCNIndex(int deltCNIndex) {
        CensusPeptide.deltCNIndex = deltCNIndex;
    }

    public static int getSamIntIndex() {
        return samIntIndex;
    }

    public static void setSamIntIndex(int samIntIndex) {
        CensusPeptide.samIntIndex = samIntIndex;
    }

    public static int getRefIntIndex() {
        return refIntIndex;
    }

    public static void setRefIntIndex(int refIntIndex) {
        CensusPeptide.refIntIndex = refIntIndex;
    }

    public static int getAreaRatioIndex() {
        return areaRatioIndex;
    }

    public static void setAreaRatioIndex(int areaRatioIndex) {
        CensusPeptide.areaRatioIndex = areaRatioIndex;
    }

    public static int getProfScoreIndex() {
        return profScoreIndex;
    }

    public static void setProfScoreIndex(int profScoreIndex) {
        CensusPeptide.profScoreIndex = profScoreIndex;
    }

    public static int getFileNameIndex() {
        return fileNameIndex;
    }

    public static void setFileNameIndex(int fileNameIndex) {
        CensusPeptide.fileNameIndex = fileNameIndex;
    }

    public static int getScanIndex() {
        return scanIndex;
    }

    public static void setScanIndex(int scanIndex) {
        CensusPeptide.scanIndex = scanIndex;
    }

    public static int getCsIndex() {
        return csIndex;
    }

    public static void setCsIndex(int csIndex) {
        CensusPeptide.csIndex = csIndex;
    }

    public static int getEnrichmentIndex() {
        return enrichmentIndex;
    }

    public static void setEnrichmentIndex(int enrichmentIndex) {
        CensusPeptide.enrichmentIndex = enrichmentIndex;
    }

    public static int getProteinsIndex() {
        return proteinsIndex;
    }

    public static void setProteinsIndex(int proteinsIndex) {
        CensusPeptide.proteinsIndex = proteinsIndex;
    }

    public static int getProteinDescriptionIndex() {
        return proteinDescriptionIndex;
    }

    public static void setProteinDescriptionIndex(int proteinDescriptionIndex) {
        CensusPeptide.proteinDescriptionIndex = proteinDescriptionIndex;
    }

    public static int getProbabilityScoreIndex() {
        return probabilityScoreIndex;
    }

    public static void setProbabilityScoreIndex(int probabilityScoreIndex) {
        CensusPeptide.probabilityScoreIndex = probabilityScoreIndex;
    }

    public static int getSlineIndexSingleton() {
        return slineIndexSingleton;
    }

    public static void setSlineIndexSingleton(int slineIndexSingleton) {
        CensusPeptide.slineIndexSingleton = slineIndexSingleton;
    }

    public static int getUniqueIndexSingleton() {
        return uniqueIndexSingleton;
    }

    public static void setUniqueIndexSingleton(int uniqueIndexSingleton) {
        CensusPeptide.uniqueIndexSingleton = uniqueIndexSingleton;
    }

    public static int getSequenceIndexSingleton() {
        return sequenceIndexSingleton;
    }

    public static void setSequenceIndexSingleton(int sequenceIndexSingleton) {
        CensusPeptide.sequenceIndexSingleton = sequenceIndexSingleton;
    }

    public static int getRatioIndexSingleton() {
        return ratioIndexSingleton;
    }

    public static void setRatioIndexSingleton(int ratioIndexSingleton) {
        CensusPeptide.ratioIndexSingleton = ratioIndexSingleton;
    }

    public static int getRatioRevIndexSingleton() {
        return ratioRevIndexSingleton;
    }

    public static void setRatioRevIndexSingleton(int ratioRevIndexSingleton) {
        CensusPeptide.ratioRevIndexSingleton = ratioRevIndexSingleton;
    }

    public static int getRegFactorIndexSingleton() {
        return regFactorIndexSingleton;
    }

    public static void setRegFactorIndexSingleton(int regFactorIndexSingleton) {
        CensusPeptide.regFactorIndexSingleton = regFactorIndexSingleton;
    }

    public static int getDetFactorIndexSingleton() {
        return detFactorIndexSingleton;
    }

    public static void setDetFactorIndexSingleton(int detFactorIndexSingleton) {
        CensusPeptide.detFactorIndexSingleton = detFactorIndexSingleton;
    }

    public static int getpValueIndexSingleton() {
        return pValueIndexSingleton;
    }

    public static void setpValueIndexSingleton(int pValueIndexSingleton) {
        CensusPeptide.pValueIndexSingleton = pValueIndexSingleton;
    }

    public static int getxCorrIndexSingleton() {
        return xCorrIndexSingleton;
    }

    public static void setxCorrIndexSingleton(int xCorrIndexSingleton) {
        CensusPeptide.xCorrIndexSingleton = xCorrIndexSingleton;
    }

    public static int getDeltCNIndexSingleton() {
        return deltCNIndexSingleton;
    }

    public static void setDeltCNIndexSingleton(int deltCNIndexSingleton) {
        CensusPeptide.deltCNIndexSingleton = deltCNIndexSingleton;
    }

    public static int getSamIntIndexSingleton() {
        return samIntIndexSingleton;
    }

    public static void setSamIntIndexSingleton(int samIntIndexSingleton) {
        CensusPeptide.samIntIndexSingleton = samIntIndexSingleton;
    }

    public static int getRefIntIndexSingleton() {
        return refIntIndexSingleton;
    }

    public static void setRefIntIndexSingleton(int refIntIndexSingleton) {
        CensusPeptide.refIntIndexSingleton = refIntIndexSingleton;
    }

    public static int getAreaRatioIndexSingleton() {
        return areaRatioIndexSingleton;
    }

    public static void setAreaRatioIndexSingleton(int areaRatioIndexSingleton) {
        CensusPeptide.areaRatioIndexSingleton = areaRatioIndexSingleton;
    }

    public static int getProfScoreIndexSingleton() {
        return profScoreIndexSingleton;
    }

    public static void setProfScoreIndexSingleton(int profScoreIndexSingleton) {
        CensusPeptide.profScoreIndexSingleton = profScoreIndexSingleton;
    }

    public static int getFileNameIndexSingleton() {
        return fileNameIndexSingleton;
    }

    public static void setFileNameIndexSingleton(int fileNameIndexSingleton) {
        CensusPeptide.fileNameIndexSingleton = fileNameIndexSingleton;
    }

    public static int getScanIndexSingleton() {
        return scanIndexSingleton;
    }

    public static void setScanIndexSingleton(int scanIndexSingleton) {
        CensusPeptide.scanIndexSingleton = scanIndexSingleton;
    }

    public static int getCsIndexSingleton() {
        return csIndexSingleton;
    }

    public static void setCsIndexSingleton(int csIndexSingleton) {
        CensusPeptide.csIndexSingleton = csIndexSingleton;
    }

    public static int getEnrichmentIndexSingleton() {
        return enrichmentIndexSingleton;
    }

    public static void setEnrichmentIndexSingleton(int enrichmentIndexSingleton) {
        CensusPeptide.enrichmentIndexSingleton = enrichmentIndexSingleton;
    }

    public static int getProteinsIndexSingleton() {
        return proteinsIndexSingleton;
    }

    public static void setProteinsIndexSingleton(int proteinsIndexSingleton) {
        CensusPeptide.proteinsIndexSingleton = proteinsIndexSingleton;
    }

    public static int getProteinDescriptionIndexSingleton() {
        return proteinDescriptionIndexSingleton;
    }

    public static void setProteinDescriptionIndexSingleton(int proteinDescriptionIndexSingleton) {
        CensusPeptide.proteinDescriptionIndexSingleton = proteinDescriptionIndexSingleton;
    }

    public static int getProbabilityScoreIndexSingleton() {
        return probabilityScoreIndexSingleton;
    }

    public static void setProbabilityScoreIndexSingleton(int probabilityScoreIndexSingleton) {
        CensusPeptide.probabilityScoreIndexSingleton = probabilityScoreIndexSingleton;
    }
	public String getSingleton() {
		return singleton;
	}
	public void setSingleton(String singleton) {
		this.singleton = singleton;
	}

    

}
