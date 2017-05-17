package com.ipa.ip2.api.util.dtaselect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amit on 15/02/17.
 */
public class Peptide
{

    private boolean unique;
    private String fileName;
    private String xCorr;
    private String deltCN;
    private String mhPlus;
    private String calcMHplus;
    private String totalIntensity;
    private String spRank;
    private String spScore;
    private String zScore;
    private String ionProportion;
    private double ppm;
    private int redundancy;
    private String sequence;
    private String[] peptideLine;
    private String tmpStr;
    private String scanNum;
    private String conf;
    private String proteins;
    private String proteinDescription;
    private String chargeState;
    private static int uniqueIndex = -1;
    private static int scanNumIndex =-1;
    private static int xcorrIndex = -1;
    private static int dcnIndex = -1;
    private static int confIndex = -1;
    private static int mPlusHIndex = -1;
    private static int calcMassIndex = -1;
    private static int totalIntensityIndex = -1;
    private static int spRankIndex = -1;
    private static int spScoreIndex = -1;
    private static int ionProportionIndex = -1;
    private static int redundancyIndex = -1;
    private static int sequenceIndex = -1;
    private static int pIIndex = -1;
    private static int ppmIndex = -1;
    private static int zScoreIndex=-1;
    private boolean isDecoy=false;

    private int hashcode = -1;

    private List<Protein> proteinList = new ArrayList<Protein>();

    private boolean destroyedInCensusOut;

    public String peptideWholeLine;

    public int hashCode() {
        if(hashcode == -1) {
            // fileName contains the scan number
            hashcode = (getSequence() + fileName).hashCode();
            //hashcode = (fileName + scanNum).hashCode();
        }
        return hashcode;
    }
    public boolean equals(Object o) {
        Peptide p = (Peptide)o;
        return getSequence().equals(p.getSequence()) && fileName.equals(p.fileName);
    }

    // this function need to be changed if the format of getInfo() function changes
    public static String getInfoHeader() {
        return "Peptide\tXCorr\tDeltaCN\tMPlusH\tCalcMPlusH\tDeltaMass\tSpScore\tConfidence\tFileName";
    }
    public String getInfo() {
        StringBuffer sb = new StringBuffer(1000);
        sb.append(sequence + "\t" + xCorr + "\t" + deltCN);
        sb.append("\t" + mhPlus + "\t" + calcMHplus + "\t" + getDeltaMass());
        sb.append("\t" + spScore + "\t" + conf + "\t" + fileName);
        return sb.toString();
    }
    /* DTASelect 2.0 file */
    private void parseLine2() throws ArrayIndexOutOfBoundsException
    {
        scanNum = peptideLine[scanNumIndex];
        this.chargeState = scanNum.substring(scanNum.lastIndexOf(".")+1);
        scanNum = scanNum.substring(0, scanNum.lastIndexOf("."));
        scanNum = scanNum.substring(scanNum.lastIndexOf(".")+1);


        this.setUnique((peptideLine[uniqueIndex]).startsWith("*"));
        this.setFileName(peptideLine[scanNumIndex]);
        this.setXCorr(peptideLine[xcorrIndex]);
        this.setDeltCN(peptideLine[dcnIndex]);

        if(confIndex>0)
            this.setConf(peptideLine[confIndex]);
        this.setMhPlus(peptideLine[mPlusHIndex]);
        this.setCalcMHplus(peptideLine[calcMassIndex]);

        if(ppmIndex>=0)
            this.setPpm(peptideLine[ppmIndex]);

        this.setTotalIntensity(peptideLine[totalIntensityIndex]);
        this.setSpRank(peptideLine[spRankIndex]);
        if(spScoreIndex>0)
            this.setSpScore(peptideLine[spScoreIndex]);
        this.setIonProportion(peptideLine[ionProportionIndex]);
        this.setRedundancy(peptideLine[redundancyIndex]);
        this.setSequence(peptideLine[sequenceIndex]);

    }
    public static void clearIndex(){
        uniqueIndex = -1;
        scanNumIndex = -1;
        xcorrIndex = -1;
        dcnIndex = -1;
        confIndex = -1;
        mPlusHIndex = -1;
        calcMassIndex = -1;
        totalIntensityIndex = -1;
        spRankIndex = -1;
        spScoreIndex = -1;
        ionProportionIndex = -1;
        redundancyIndex = -1;
        sequenceIndex = -1;
        pIIndex = -1;
        ppmIndex = -1;

        zScoreIndex=-1;

    }
    public static void setFeatureIndices(String features) {
        clearIndex();
        String [] contents = features.split("\t");
        for(int i = 0; i < contents.length; i++) {
            String s = contents[i].trim();
            uniqueIndex = s.startsWith("Uni")? i : uniqueIndex;
            scanNumIndex = s.startsWith("File")? i : scanNumIndex;
            xcorrIndex = s.startsWith("XC")? i : xcorrIndex;
            dcnIndex = (s.startsWith("DeltCN") || s.startsWith("DeltaCN"))? i : dcnIndex;
            confIndex = s.startsWith("Conf%")? i : confIndex;
            mPlusHIndex = s.startsWith("M")? i : mPlusHIndex;
            calcMassIndex = s.startsWith("CalcM")? i : calcMassIndex;
            totalIntensityIndex = s.startsWith("Total")? i : totalIntensityIndex;
            spRankIndex = s.startsWith("SpR")? i : spRankIndex;
            spScoreIndex = (s.startsWith("Prob")||s.startsWith("SpScore"))? i : spScoreIndex;
            ionProportionIndex = s.startsWith("IonP")? i : ionProportionIndex;
            redundancyIndex = s.startsWith("Red")? i : redundancyIndex;
            sequenceIndex = s.startsWith("Sequence")? i : sequenceIndex;
            pIIndex = s.startsWith("pI")? i : pIIndex;
            ppmIndex = s.startsWith("PPM")? i : ppmIndex;


        }
    }
    //For DTASelect version 2
    public Peptide() {

    }


    public Peptide(String peptideLine, boolean isV2, boolean isV1) {

        this(peptideLine.split("\t"), isV2, isV1);
        this.peptideWholeLine = peptideLine;
    }

    public Peptide(String[] peptideLine, boolean isV2, boolean isV1)
    {
        this.peptideLine = peptideLine;

	    /*
		  ppmIndex = 7;
		  totalIntensityIndex = 8;
		  spRankIndex = 9;
		  spScoreIndex = 10;
		  ionProportionIndex = 12;
		  redundancyIndex = 13;
		  sequenceIndex = 14;
		  pIIndex = 11;
	     */
        if(isV1)
            parseLine();
        else
            parseLine2();
        //else
    }


    /* DTASelect file */
    private void parseLine() throws ArrayIndexOutOfBoundsException
    {
        scanNum = peptideLine[1];
        scanNum = scanNum.substring(0, scanNum.lastIndexOf("."));
        scanNum = scanNum.substring(scanNum.lastIndexOf(".")+1);

        //this.setUnique(!"".equals(peptideLine[0]));
        this.setUnique((peptideLine[0]).startsWith("*"));
        this.setFileName(peptideLine[1]);
        this.setXCorr(peptideLine[2]);
        this.setDeltCN(peptideLine[3]);
        this.setMhPlus(peptideLine[4]);
        this.setCalcMHplus(peptideLine[5]);
        this.setTotalIntensity(peptideLine[6]);
        this.setSpRank(peptideLine[7]);
        this.setSpScore(peptideLine[8]);
        this.setIonProportion(peptideLine[9]);
        this.setRedundancy(peptideLine[10]);
        this.setSequence(peptideLine[11]);
    }
    public float getPi() {
        return Float.parseFloat(peptideLine[pIIndex]);
    }
    public float getDeltaMass() {
        return ppmIndex != -1 ? Float.parseFloat(peptideLine[ppmIndex]) : 1000;
    }
    public String getChargeState()
    {
        return this.fileName.substring( this.fileName.lastIndexOf(".") + 1 );
    }
    public int getChargeStateValue()
    {
        return Integer.parseInt(getChargeState());
    }
    public void setChargeState(String cs){
        this.chargeState=cs;
    }
    public String getLoScan()
    {
        tmpStr = this.fileName.substring( this.fileName.indexOf(".") +1 );
        return tmpStr.substring(0, tmpStr.indexOf(".") );
    }

    public double getPpm()
    {
        return this.ppm;
    }
    public String getFileName()
    {
        return fileName.substring(0, fileName.indexOf("."));
    }

    public String getXCorr()
    {
        return xCorr;
    }

    public double  getXCorrValue()
    {
        return Double.parseDouble(xCorr);
    }
    public String getDeltCN()
    {
        return deltCN;
    }
    public double  getDeltCnValue()
    {
        return Double.parseDouble(deltCN);
    }

    public String getMhPlus()
    {
        return mhPlus;
    }

    public String getSpRank()
    {
        return spRank;
    }

    public String getSpScore()
    {
        return spScore;
    }
    public String getZScore()
    {
        return zScore;
    }

    public double getSpScoreValue()
    {
        return Double.parseDouble(spScore);
    }

    public String getIonProportion()
    {
        return ionProportion;
    }

    public int getRedundancy()
    {
        return redundancy;
    }

    public int getSpectralCount()
    {
        return redundancy;
    }
    public String getSequence()
    {
        return sequence;
    }
    // return the peptide sequence without leading and tailing residues
    public String getMidSeq()
    {
        int lastindex = sequence.length() - 2;
        return sequence.substring(2, lastindex);
    }

    public boolean isUnique()
    {
        return unique;
    }

    public double getCalcMHplusValue()
    {
        return Double.parseDouble(calcMHplus);
    }
    public String getCalcMHplus()
    {
        return calcMHplus;
    }

    public String getTotalIntensity()
    {
        return totalIntensity;
    }

    public String getScanNum()
    {
        return scanNum;
    }
    public int getScanNumber()
    {
        return Integer.parseInt(scanNum);
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }
    public void setPpm(String ppm)
    {
        setPpm( Double.parseDouble(ppm) );
    }
    public void setPpm(double ppm)
    {
        this.ppm=ppm;
    }
    public void setXCorr(String xCorr)
    {
        this.xCorr = xCorr;
    }

    public void setDeltCN(String deltCN)
    {
        this.deltCN = deltCN;
    }

    public void setMhPlus(String mhPlus)
    {
        this.mhPlus = mhPlus;
    }

    public void setSpRank(String spRank)
    {
        this.spRank = spRank;
    }

    public void setSpScore(String spScore)
    {
        this.spScore = spScore;
    }
    public void setZScore(String zScore)
    {
        this.zScore = zScore;
    }

    public void setIonProportion(String ionProportion)
    {
        this.ionProportion = ionProportion;
    }

    public void setRedundancy(String redundancy) {
        setRedundancy(Integer.parseInt(redundancy));
    }

    public void setRedundancy(int redundancy)
    {
        this.redundancy = redundancy;
    }

    public void setSequence(String sequence)
    {
        this.sequence = sequence;
    }

    public void setUnique(boolean unique)
    {
        this.unique = unique;
    }

    public void setCalcMHplus(String calcMHplus)
    {
        this.calcMHplus = calcMHplus;
    }

    public void setTotalIntensity(String totalIntensity)
    {
        this.totalIntensity = totalIntensity;
    }

    public void setScanNum(String scanNum)
    {
        this.scanNum = scanNum;
    }

    public String getConf() {
        return (null==conf)?"":conf;
    }
    public double  getConfValue() {
        if(conf == null) {
            return 0;
        }
        return Double.parseDouble(conf);
    }

    public void setConf(String conf) {
        this.conf = conf;
    }

    public String[] getPeptideLine()
    {
        return peptideLine;
    }

    public void setPeptideLine(String[] peptideLine) {
        this.peptideLine = peptideLine;
    }


    public void addProtein(Protein protein) {
        this.proteinList.add(protein);

    }

    public void setProtein(String protein)
    {
        this.proteins = protein;
    }

    public String getProteins()
    {
        return this.proteins;
    }


    public void setProteinDescription(String proteinDescription)
    {
        this.proteinDescription = proteinDescription;
    }

    public String getProteinDescription()
    {
        return this.proteinDescription;
    }



    public List<Protein> getProteinList() {
        return proteinList;
    }

    public void setProteinList(List<Protein> proteinList) {
        this.proteinList = proteinList;
    }

    public boolean isIsDecoy() {
        return isDecoy;
    }

    public void setIsDecoy(boolean isDecoy) {
        this.isDecoy = isDecoy;
    }

    public String getxCorr() {
        return xCorr;
    }

    public void setxCorr(String xCorr) {
        this.xCorr = xCorr;
    }


    public boolean isDestroyedInCensusOut() {
        return destroyedInCensusOut;
    }

    public void setDestroyedInCensusOut(boolean destroyedInCensusOut) {
        this.destroyedInCensusOut = destroyedInCensusOut;
    }

    public String getPeptideWholeLine() {
        return peptideWholeLine;
    }

    public void setPeptideWholeLine(String peptideWholeLine) {
        this.peptideWholeLine = peptideWholeLine;
    }
}
