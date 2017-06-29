package com.ipa.ip2.api.dao;

import com.ipa.ip2.api.util.Formatter;
import com.ipa.ip2.api.util.quant.PeptideIndexModel;

import java.util.*;

public class PeptideCompareModel {

    private List<String> uniqueList = new ArrayList<String>();
    private List<String> fileNameList = new ArrayList<String>();
    private List<Double> xcorrList = new ArrayList<Double>();
    private List<Double> dcnList = new ArrayList<Double>();
    private List<Double> confList = new ArrayList<Double>();
    private List<Double> mPhPList= new ArrayList<Double>();//M+P+
    private List<Double> calMPhPList= new ArrayList<Double>();//calM+P+
    private List<Double> totalIntensityList= new ArrayList<Double>();
    private List<Double> spRList= new ArrayList<Double>();
    private List<Double> probList= new ArrayList<Double>();
    private List<Double> ionProportionList= new ArrayList<Double>();
    private List<Double> redundancyList = new ArrayList<Double>();
    private List<String> seqList= new ArrayList<String>();
    private List<String> localizationList= new ArrayList<String>();
    private List<String> dbScoreList = new ArrayList<String>();

	private String modSequence;
    
    private String sequence="";
    private String protain="";
    private String description="";
    private String sorting="";
    private String numberStr="";
    private String xcorrStr="";
    private String uniqueStr="";
    private String fileNameStr="";
    private String dcnStr="";
    private String confStr="";
    private String mPhPStr="";
    private String calMPhPStr="";
    private String totalIntensityStr="";
    private String spRStr="";
    private String probStr="";
    private String ionProportionStr="";
    private String redundancyStr="";
    private String seqStr="";
    private String dbScoreStr="";
    private String localizationStr="";
    private String chargeState;
    private boolean singleton=false;
    //quant info
    private List<Double> ratioList = new ArrayList<>();
    private List<Double> regList = new ArrayList<>();
    private List<Double> detList = new ArrayList<>();
    private List<String> pvalueList = new ArrayList<>();
    private List<String> lightAreaList = new ArrayList<>();
    private List<String> heavyAreaList = new ArrayList<>();
    private List<Double> areaRatioList = new ArrayList<>();
    private List<Double> profileScoreList = new ArrayList<>();
    private List<String> scanList = new ArrayList<>();
    private List<Double> enrichmentList = new ArrayList<>();

    private HashSet<String> uniqueSet = new HashSet<>();

	public  PeptideCompareModel() {
	}

    public PeptideCompareModel(PeptideIndexModel index, String line) {
        String[] arr = line.split("\t");
//        System.out.println(line);

        this.addPeptide(index, line);
    }

    public void addPeptide(PeptideIndexModel index, String line) {
        String[] arr = line.split("\t");

        String uniqueKey = arr[index.getSeqIndex()] + arr[index.getCsIndex()] + arr[index.getFileNameIndex()];

        if(this.uniqueSet.contains(uniqueKey)) return;

        this.uniqueSet.add(uniqueKey);
/*
        if(arr.length<=index.getAreaRatioIndex()) {
            System.out.println(line);
            System.out.println(index.getAreaRatioIndex());
            System.exit(0);
        }
*/


        this.sequence = arr[index.getSeqIndex()];
        this.chargeState = arr[index.getCsIndex()];

        if(line.startsWith("S")) {
            if("NA".equals(arr[index.getRatioIndex()])) {
                //System.out.println("==============" + line);
                this.ratioList.add(0.0);
            }
            else
                this.ratioList.add(Double.parseDouble(arr[index.getRatioIndex()]));
        }
        else {
            String tmpStr =arr[index.getRatioIndex()];
            if("INF".equals(tmpStr))
                this.ratioList.add(1000.0);
            else {
                this.ratioList.add(Double.parseDouble(tmpStr));
            }

            this.singleton = true;
        }

        this.xcorrList.add(Double.parseDouble(arr[index.getXcorrIndex()]));
        this.dcnList.add(Double.parseDouble(arr[index.getDcnIndex()]));


        this.uniqueList.add(arr[index.getUniqueIndex()]);
        this.regList.add(Double.parseDouble(arr[index.getRegIndex()]));
        this.detList.add(Double.parseDouble(arr[index.getDetIndex()]));


        if(index.getPvalueIndex()>=0)
            this.pvalueList.add(arr[index.getPvalueIndex()]);
        else
            this.pvalueList.add("NA");

        this.lightAreaList.add(arr[index.getSamAreaIndex()]);
        this.heavyAreaList.add(arr[index.getRefAreaIndex()]);

        String tmpStr =arr[index.getAreaRatioIndex()];
        if("INF".equals(tmpStr))
            this.areaRatioList.add(1000.0);
        else
            this.areaRatioList.add(Double.parseDouble(tmpStr));


        //this.areaRatioList.add(Double.parseDouble(arr[index.getAreaRatioIndex()]));

        this.profileScoreList.add(Double.parseDouble(arr[index.getProfileScoreIndex()]));
        this.fileNameList.add(arr[index.getFileNameIndex()]);

        /*if(this.sequence.contains("W.DDMEKIWHHTFY.N")) {
            System.out.println(line);
            System.out.println(scanList + "=========================\t" + this.concancateStrValues(this.scanList) + "====\t" + index.getScanIndex() + "===\t" + arr[index.getScanIndex()]);
        }*/


        this.scanList.add(arr[index.getScanIndex()]);
        if(index.getEnrichmentIndex()<0 || "N/A".equals(arr[index.getEnrichmentIndex()]))
            this.enrichmentList.add(0.0);
        else
            this.enrichmentList.add(Double.parseDouble(arr[index.getEnrichmentIndex()]));

    }

    public List<Double> getCalMPhPList() {
        return calMPhPList;
    }


    public void setCalMPhPList(List<Double> calMPhPList) {
        this.calMPhPList = calMPhPList;
    }
    public void addCalMPhPList(String aCalMPhP) {
       Double tem=0.0;
        try{
           tem=Double.parseDouble(aCalMPhP);           
        }catch(Exception e){
            System.out.println(e.toString());
        }
        this.calMPhPList.add(tem);
    }
    public List<Double> getConfList() {
        return confList;
    }

    public void setConfList(List<Double> confList) {
        this.confList = confList;
    }
     public void addConfList(String aConf) {
       Double tem=0.0;
        try{
           tem=Double.parseDouble( aConf);           
        }catch(Exception e){
            System.out.println(e.toString());
        }
        this.confList.add(tem);
    }       
    public List<Double> getDcnList() {
        return dcnList;
    }

    public void setDcnList(List<Double> dcnList) {
        this.dcnList = dcnList;
    }
    public void addDcnList(String aDcn) {
       Double tem=0.0;
        try{
           tem=Double.parseDouble( aDcn);           
        }catch(Exception e){
            System.out.println(e.toString());
        }
        this.dcnList.add(tem);
    }       
    public List<String> getFileNameList() {
        return fileNameList;
    }

    public void setFileNameList(List<String> fileNameList) {
        this.fileNameList = fileNameList;
    }
    public void addFileNameList(String aFileName) {
     
        this.fileNameList.add(aFileName);
    }       
    public List<Double> getIonProportionList() {
        return ionProportionList;
    }

    public void setIonProportionList(List<Double> ionProportionList) {
        this.ionProportionList = ionProportionList;
    }
   public void addIonProportionList(String aIonProportion) {
       Double tem=0.0;
        try{
           tem=Double.parseDouble( aIonProportion);           
        }catch(Exception e){
            System.out.println(e.toString());
        }
        this.ionProportionList.add(tem);
    } 
    public List<Double> getmPhPList() {
        return mPhPList;
    }

    public void setmPhPList(List<Double> mPhPList) {
        this.mPhPList = mPhPList;
    }
    public void addMPhPList(String aMPhP) {
       Double tem=0.0;
        try{
           tem=Double.parseDouble( aMPhP);           
        }catch(Exception e){
            System.out.println(e.toString());
        }
       this.mPhPList.add(tem);
    } 
    public List<Double> getProbList() {
        return probList;
    }

    public void setProbList(List<Double> probList) {
        this.probList = probList;
    }
    public void addProbList(String aProb) {
        Double tem=0.0;
         try{
            tem=Double.parseDouble( aProb);           
         }catch(Exception e){
             System.out.println(e.toString());
         }
        this.probList.add(tem);
     } 
    
    public List<Double> getRedundancyList() {
        return redundancyList;
    }
     
    public void setRedundancyList(List<Double> redundancyList) {
        this.redundancyList = redundancyList;
    }
    public void addRedundancyList(String aRedundancy) {
    	
       Double tem=0.0;
        try{
           tem=Double.parseDouble( aRedundancy); 
          
        }catch(Exception e){
            System.out.println(e.toString());
        }
    
       this.redundancyList.add(tem);
    } 
    public List<Double> getSpRList() {
        return spRList;
    }

    public void setSpRList(List<Double> spRList) {
        this.spRList = spRList;
    }
    public void addSpRList(String aSpR) {
       Double tem=0.0;
        try{
           tem=Double.parseDouble( aSpR);           
        }catch(Exception e){
            System.out.println(e.toString());
        }
       this.spRList.add(tem);
    } 
    public List<Double> getTotalIntensityList() {
        return totalIntensityList;
    }

    public void setTotalIntensityList(List<Double> totalIntensityList) {
        this.totalIntensityList = totalIntensityList;
    }
    public void addTotalIntensityList(String aTotalIntensity) {
       Double tem=0.0;
        try{
           tem=Double.parseDouble( aTotalIntensity);           
        }catch(Exception e){
            System.out.println(e.toString());
        }
       this.totalIntensityList.add(tem);
    } 
    public List<String> getUniqueList() {
        return uniqueList;
    }

    public void setUniqueList(List<String> uniqueList) {
        this.uniqueList = uniqueList;
    }
    public void addUniqueList(String aUnique) {       
       this.uniqueList.add(aUnique);
    } 
    public List<Double> getXcorrList() {
        return xcorrList;
    }

    public void setXcorrList(List<Double> xcorrList) {
        this.xcorrList = xcorrList;
    }
   public void addXcorrList(String aXcorr) {
       Double tem=0.0;
        try{
           tem=Double.parseDouble( aXcorr);           
        }catch(Exception e){
            System.out.println(e.toString());
        }
       this.xcorrList.add(tem);
    } 
    public String getCalMPhPStr() {
        
        for(Iterator<Double> itr=calMPhPList.iterator(); itr.hasNext(); ) {
            double d = itr.next();
            if(d==-9999.999)
             calMPhPStr += "X";
             else   calMPhPStr += String.valueOf(d);
            calMPhPStr += ",";
        }
        return calMPhPStr;
    }

    public void setCalMPhPStr(String calMPhPStr) {
        this.calMPhPStr = calMPhPStr;
    }

    public String getConfStr() {
        
        for(Iterator<Double> itr=confList.iterator(); itr.hasNext(); ) {
            double d = itr.next();
             if(d==-9999.999)
             confStr += "X";
             else   confStr += String.valueOf(d);
            confStr += ",";
        }
        return confStr;
    }

    public void setConfStr(String confStr) {
        this.confStr = confStr;
    }

    public String getDcnStr() {
        
        for(Iterator<Double> itr=dcnList.iterator(); itr.hasNext(); ) {
            double d = itr.next();
             if(d==-9999.999)
             dcnStr += "X";
             else   dcnStr += String.valueOf(d);
            dcnStr += ",";
        }
        return dcnStr;
    }


    public void setDcnStr(String dcnStr) {
        this.dcnStr = dcnStr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

/*

    public String getFileNameStr() {
        
        for(Iterator<String> itr=fileNameList.iterator(); itr.hasNext(); ) {
            String s = itr.next();
            fileNameStr+= s;
            fileNameStr+= ",";
        }
        return fileNameStr;
    }*/

    public void setFileNameStr(String fileNameStr) {
        this.fileNameStr = fileNameStr;
    }

    public String getIonProportionStr() {
        
        for(Iterator<Double> itr=ionProportionList.iterator(); itr.hasNext(); ) {
            double d = itr.next();
             if(d==-9999.999)
             ionProportionStr += "X";
             else    ionProportionStr += String.valueOf(d);
            ionProportionStr+= ",";
        }
        return ionProportionStr;
    }

    public void setIonProportionStr(String ionProportionStr) {
        this.ionProportionStr = ionProportionStr;
    }

    public String getmPhPStr() {
       
        for(Iterator<Double> itr=mPhPList.iterator(); itr.hasNext(); ) {
            double d = itr.next();
             if(d==-9999.999)
             mPhPStr += "X";
             else     mPhPStr+= String.valueOf(d);
            mPhPStr += ",";
        }
        return mPhPStr;
    }

    public void setmPhPStr(String mPhPStr) {
        this.mPhPStr = mPhPStr;
    }

    public String getProbStr() {
        
        for(Iterator<Double> itr=probList.iterator(); itr.hasNext(); ) {
            double d = itr.next();
              if(d==-9999.999)
             probStr += "X";
             else   probStr+= String.valueOf(d);
            probStr += ",";
        }
        return probStr;
    }

    public void setProbStr(String probStr) {
        this.probStr = probStr;
    }

    public String getProtain() {

        return protain;
    }

    public void setProtain(String protain) {
        this.protain = protain;
    }

    public String getRedundancyStr() {
         String redundancyStr = "";       
         for(Iterator<Double> itr=redundancyList.iterator(); itr.hasNext(); ) {
            double d = itr.next();
              if(d==-9999.999)
             redundancyStr += "X";
             else 
            redundancyStr += String.valueOf(d);
            redundancyStr += ",";
        }
        return redundancyStr;
    }

    public void setRedundancyStr(String redundancyStr) {
        this.redundancyStr = redundancyStr;
    }

    public String getSequence() {

        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
  public String getSorting() {

        return sorting;
    }

    public void setSorting(String sorting) {
        this.sorting = sorting;
    }

  public String getNumberStr() {

        return numberStr;
    }

    public void setNumberStr(String numberStr) {
        this.numberStr = numberStr;
    }

    public String getSpRStr() {
        
        for(Iterator<Double> itr=spRList.iterator(); itr.hasNext(); ) {
            double d = itr.next();
              if(d==-9999.999)
             spRStr += "X";
             else     spRStr += String.valueOf(d);
            spRStr += ",";
        }
        return spRStr;
    }

    public void setSpRStr(String spRStr) {
        this.spRStr = spRStr;
    }

    public String getTotalIntensityStr() {
          
        for(Iterator<Double> itr=totalIntensityList.iterator(); itr.hasNext(); ) {
            double d = itr.next();
             if(d==-9999.999)
             totalIntensityStr += "X";
             else   totalIntensityStr+= String.valueOf(d);
            totalIntensityStr+= ",";
        }
        return totalIntensityStr;
    }

    public void setTotalIntensityStr(String totalIntensityStr) {
        this.totalIntensityStr = totalIntensityStr;
    }

    public String getUniqueStr() {
      

        for(Iterator<String> itr=uniqueList.iterator(); itr.hasNext(); ) {
            String s = itr.next();
            uniqueStr+= s;
            uniqueStr += ",";
        }
        
        return uniqueStr;
    }

    public void setUniqueStr(String uniqueStr) {
        this.uniqueStr = uniqueStr;
    }
    
    public String getXcorrStr() {

        for(Iterator<Double> itr=xcorrList.iterator(); itr.hasNext(); ) {
            double d = itr.next();
              if(d==-9999.999)
             xcorrStr += "X";
             else   xcorrStr+= String.valueOf(d);
            xcorrStr += ",";
        }
        return xcorrStr;
    }

    public void setXcorrStr(String xcorrStr) {
        this.xcorrStr = xcorrStr;
    }

   public List<String> getSeqList() {
		return seqList;
	}
 public void addSeqList(String s) {
        this.seqList.add(s);
    }

	public void setSeqList(ArrayList<String> seqList) {
		this.seqList = seqList;
	}
 public void addLocalizationList(String s) {
        this.localizationList.add(s);
    }

	public List<String> getLocalizationList() {
		return localizationList;
	}

	public void setLocalizationList(ArrayList<String> localizationList) {
		this.localizationList = localizationList;
	}
 public void addDbScoreList(String s) {
        this.dbScoreList.add(s);
    }

	public List<String> getDbScoreList() {
		return dbScoreList;
	}

	public void setDbScoreList(ArrayList<String> dbScoreList) {
		this.dbScoreList = dbScoreList;
	}
    public String getModSequence() {
		return modSequence;
	}

	public void setModSequence(String modSequence){ 
		this.modSequence = modSequence;
	}


  
	public String getSeqStr() {
//seqStr=seqList.toString();i
       for(Iterator<String> itr=seqList.iterator(); itr.hasNext(); ) {
            String s = itr.next();
             seqStr += s +",";
        }


		return seqStr;
	}

	public void setSeqStr(String seqStr) {
		this.seqStr = seqStr;
	}

	public String getDbScoreStr() {
//dbScoreStr=dbScoreList.toString();	
      for(Iterator<String> itr=dbScoreList.iterator(); itr.hasNext(); ) {
            String s = itr.next();
	  	try {
			 dbScoreStr +=	Formatter.sciRound(Double.parseDouble(s))+",";
			
		}catch(Exception e){
			 dbScoreStr += s +",";

		}		
        }

	return dbScoreStr;
	}

	public void setDbScoreStr(String dbScoreStr) {
		this.dbScoreStr = dbScoreStr;
	}

	public String getLocalizationStr() {
 for(Iterator<String> itr=localizationList.iterator(); itr.hasNext(); ) {
            String s = itr.next();
             localizationStr += s +",";
        }

//localizationStr=localizationList.toString();
		return localizationStr;
	}

	public void setLocalizationStr(String localizationStr) {
		this.localizationStr = localizationStr;
	}

    public String getChargeState() {
        return chargeState;
    }

    public void setChargeState(String chargeState) {
        this.chargeState = chargeState;
    }

    public List<Double> getRatioList() {
        return ratioList;
    }


    public String getRatioStr() {
        return concancateValues(this.ratioList);
    }

    public void setRatioList(List<Double> ratioList) {
        this.ratioList = ratioList;
    }

    public boolean isSingleton() {
        return singleton;
    }

    public void setSingleton(boolean singleton) {
        this.singleton = singleton;
    }

    public List<Double> getRegList() {
        return regList;
    }


    public void setRegList(List<Double> regList) {
        this.regList = regList;
    }

    public String concancateStrValues(List<String> list) {
        StringBuffer sb = new StringBuffer();

        for(Iterator<String> itr=list.iterator(); itr.hasNext(); ) {
            sb.append(itr.next()).append(",");
        }

        return sb.toString();
    }

    public String concancateValues(List<Double> list) {
        StringBuffer sb = new StringBuffer();

        for(Iterator<Double> itr=list.iterator(); itr.hasNext(); ) {
            sb.append(itr.next()).append(",");
        }

        return sb.toString();
    }

    public String getRegStr() {
        return concancateValues(this.getRegList());
    }

    public String getDetStr() {
        return concancateValues(this.detList);

    }

    public List<Double> getDetList() {
        return detList;
    }

    public void setDetList(List<Double> detList) {
        this.detList = detList;
    }

    public List<String> getPvalueList() {
        return pvalueList;
    }

    public String getPvalueStr() {
        return concancateStrValues(this.pvalueList);
    }
    public void setPvalueList(List<String> pvalueList) {
        this.pvalueList = pvalueList;
    }


    public List<String> getLightAreaList() {
        return lightAreaList;
    }

    public void setLightAreaList(List<String> lightAreaList) {
        this.lightAreaList = lightAreaList;
    }

    public List<String> getHeavyAreaList() {
        return heavyAreaList;
    }

    public void setHeavyAreaList(List<String> heavyAreaList) {
        this.heavyAreaList = heavyAreaList;
    }

    public String getLightAreaStr() {
        return this.concancateStrValues(this.lightAreaList);
    }

    public String getHeavyAreaStr() {
        return this.concancateStrValues(this.heavyAreaList);
    }

    public List<Double> getAreaRatioList() {
        return areaRatioList;
    }

    public void setAreaRatioList(List<Double> areaRatioList) {
        this.areaRatioList = areaRatioList;
    }

    public String getAreaRatioStr() {

        return this.concancateValues(this.areaRatioList);
    }

    public List<String> getScanList() {
        return scanList;
    }

    public void setScanList(List<String> scanList) {
        this.scanList = scanList;
    }

    public List<Double> getProfileScoreList() {
        return profileScoreList;
    }

    public void setProfileScoreList(List<Double> profileScoreList) {
        this.profileScoreList = profileScoreList;
    }

    public String getProfileScoreStr() {
        return this.concancateValues(this.profileScoreList);
    }

    public String getFilenameStr() {
        return this.concancateStrValues(this.fileNameList);
    }

    public String getScanStr() {

        return this.concancateStrValues(this.scanList);
    }


    public List<Double> getEnrichmentList() {
        return enrichmentList;
    }

    public void setEnrichmentList(List<Double> enrichmentList) {
        this.enrichmentList = enrichmentList;
    }

    public String getEnrichmentStr() {
        return this.concancateValues(this.enrichmentList);
    }
}



