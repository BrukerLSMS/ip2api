/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipa.ip2.api.util.tmt;

import com.ipa.ip2.api.dao.PeptideIsobaric;
import com.ipa.ip2.api.dao.ProteinIsobaric;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author rampuria
 */
public class TMTCensusOutReader {
    public static void main (String [] args){
        TMTCensusOutReader tmt = new TMTCensusOutReader();
        List<PeptideIsobaric> peptideList = tmt.readerPeptide("/home/rampuria/data/tmt/");
       // List<ProteinIsobaric> proteinList = tmt.readerProtein("/home/rampuria/data/tmt/");
        tmt.displayPeptideList(peptideList);
    }
    public List<ProteinIsobaric> readerProtein (String path){
        //String path = quantitation.getPath();
        path += File.separator;
        path += "census-out.txt";

        BufferedReader br = null;
        List<ProteinIsobaric> proList = new ArrayList<ProteinIsobaric>();
	try {
		br = new BufferedReader(new FileReader(path));
		String eachLine;
		while(!(eachLine=br.readLine()).startsWith("H\tPLINE\tLOCUS") );

		String[] harr = eachLine.split("\t");

		List<Integer> averageIndexList = new ArrayList<Integer>();
		List<Integer> normIndexList = new ArrayList<Integer>();
        List<Integer> normMedianIndexList = new ArrayList<Integer>();

		List<String> isobaricNameList = new ArrayList<String>();
                
		int locusIndex=-1;
		int descIndex=-1;
		int specCountIndex=-1;
		int pepNumIndex=-1;
                int seqCoverIndex =-1;
                int lengthIndex = -1;
                int molwtIndex = -1;
                int pIIndex = -1;

		int tmpIndex=-1; //because of H line, it starts with -1;
		for(String str:harr) {
			if(str.startsWith("average")) {
				averageIndexList.add(tmpIndex);
				isobaricNameList.add(str.substring(str.indexOf("m/z")));
			}
			else if(str.startsWith("norm_average"))
				normIndexList.add(tmpIndex);
            else if(str.startsWith("norm_median"))
                normMedianIndexList.add(tmpIndex);
			else if(str.startsWith("LOCUS"))
				locusIndex=tmpIndex;
			else if(str.startsWith("DESCRIPTION"))
				descIndex=tmpIndex;
			else if(str.startsWith("SPEC_COUNT"))
				specCountIndex=tmpIndex;
			else if(str.startsWith("PEP_NUM") || str.startsWith("PEPTIDE_NUM"))
				pepNumIndex=tmpIndex;
                        else if(str.startsWith("SEQ_COVERAGE"))
				seqCoverIndex=tmpIndex;
                        else if(str.startsWith("LENGTH"))
				lengthIndex=tmpIndex;
                        else if(str.startsWith("MOLWT"))
				molwtIndex=tmpIndex;
                        else if(str.startsWith("pI"))
				pIIndex=tmpIndex;
			tmpIndex++;
		}

		

		while((eachLine=br.readLine()) != null) {

			if(!eachLine.startsWith("P"))
				continue;

			try {
			String[] arr = eachLine.split("\t");
			ProteinIsobaric pro = new ProteinIsobaric();
			pro.setLocus(arr[locusIndex]);
			pro.setDescription(arr[descIndex]);
			for(int i:averageIndexList) {
				pro.addAverageIntensity(Double.parseDouble(arr[i]));
			}

			for(int i:normIndexList) {
				if("NA".equals(arr[i]))
					pro.addNormalizedRatio(-0.00000001);
				else
					pro.addNormalizedRatio(Double.parseDouble(arr[i]));
			}

            for(int i:normMedianIndexList) {
                if("NA".equals(arr[i]))
                    pro.addNormalizedMedianRatio(-0.00000001);
                else
                    pro.addNormalizedMedianRatio(Double.parseDouble(arr[i]));
            }

			pro.setSpecCount(arr[specCountIndex]);
			pro.setPepNum(arr[pepNumIndex]);
                        pro.setMolwt(arr[molwtIndex]);
                        pro.setSeqcoverage(arr[seqCoverIndex]);
                        pro.setLength(arr[lengthIndex]);
                        pro.setpI(arr[pIIndex]);
                        pro.setTagNameList(isobaricNameList);
                        pro.setGeneName(arr[descIndex]);
			proList.add(pro);
			} catch (Exception e) {
				System.out.println("error: " + eachLine);
				e.printStackTrace();
			}
			//System.out.println(eachLine);
		}
                br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proList;
    }
    
    public List<PeptideIsobaric> readerPeptide(String path){
      List<PeptideIsobaric> pepList = new ArrayList<>();
      path += File.separator;
      path += "census-out.txt";

      BufferedReader br = null;
      try{
          br = new BufferedReader(new FileReader(path));
          String eachLine = null;
          while(!(eachLine=br.readLine()).startsWith("H\tPLINE") );
          
          String[] harr1 = eachLine.split("\t");
          int proteinAccIndex =-1;
          int proteinDesc = -1;
          for(int i=0;i<harr1.length;i++){
              if(harr1[i].startsWith("LOCUS"))
                  proteinAccIndex =i-1;
              else if(harr1[i].startsWith("DESCRIPTION"))
                  proteinDesc=i-1;
          }
          while(!(eachLine=br.readLine()).startsWith("H\tSLINE") );
          String[] harr2 = eachLine.split("\t");

		List<Integer> intensityIndexList = new ArrayList<Integer>();
		List<Integer> normIntensityIndexList = new ArrayList<Integer>();
		List<String> isobaricNameList = new ArrayList<String>();
                
		//int locusIndex=-1;
		//int descIndex=-1;
                List<String> proteinList = new ArrayList<>();
		int spCIndex=-1;
                int uniqueIndex =-1;
                int sequenceIndex = -1;
                int csIndex =- -1;
                int filenameIndex = -1;
                int scanNumIndex =-1;
                
                //H	SLINE	UNIQUE	SEQUENCE	m/z_126.127726_int	norm_m/z_126.127726_int	m/z_127.124761_int
       // norm_m/z_127.124761_int	m/z_127.131081_int	norm_m/z_127.131081_int	m/z_128.128116_int
       //         norm_m/z_128.128116_int	m/z_128.134436_int	norm_m/z_128.134436_int	m/z_129.131471_int
                  //      norm_m/z_129.131471_int	m/z_129.13779_int	norm_m/z_129.13779_int	
                 //               m/z_130.134825_int	norm_m/z_130.134825_int	m/z_130.141145_int
                           //             norm_m/z_130.141145_int	m/z_131.13818_int	norm_m/z_131.13818_int	
                           //                     SpC	ScanNum	CState	Filename


                for(int i=0;i<harr2.length;i++){
                    if(harr2[i].startsWith("UNIQUE"))
                        uniqueIndex=i-1;
                    else if(harr2[i].startsWith("norm_")) {
				normIntensityIndexList.add(i-1);
				//isobaricNameList.add(harr2[i].substring(harr2[i].indexOf("m/z")));
			}
                    else if(harr2[i].startsWith("m/z_")){
                        intensityIndexList.add(i-1);
                        isobaricNameList.add(harr2[i]);
                    }
				
                    else if(harr2[i].startsWith("SEQUENCE"))
                        sequenceIndex=i-1;
                    else if(harr2[i].startsWith("SpC"))
                        spCIndex=i-1;
                    else if(harr2[i].startsWith("ScanNum"))
                        scanNumIndex=i-1;
                    else if(harr2[i].startsWith("CState"))
                        csIndex=i-1;
                    else if(harr2[i].startsWith("Filename"))
                        filenameIndex=i-1;
                }
                PeptideIsobaric peptide = null;
                eachLine=br.readLine();
                while(eachLine != null) {
                    if(eachLine.startsWith("P\t")){
                        String [] words = eachLine.split("\t");
                        proteinList.add(words[proteinAccIndex]+" "+words[proteinDesc]);
                        eachLine = br.readLine();
                    }
                    else if(eachLine.startsWith("S\t")){
                        peptide = new PeptideIsobaric();
                        String [] words = eachLine.split("\t");
                        peptide.setUnique(words[uniqueIndex]);
                        peptide.setChargeState(Integer.parseInt(words[csIndex]));
                        peptide.setFileName(words[filenameIndex]);
                        peptide.setSequence(words[sequenceIndex]);
                        peptide.setSpC(Integer.parseInt(words[spCIndex]));
                        peptide.setScanNum(Integer.parseInt(words[scanNumIndex]));
                        for(int k=0;k<intensityIndexList.size();k++){
                            if("NA".equals(words[intensityIndexList.get(k)]))
                                peptide.addIntensity(-0.00000001);
                            else
                                peptide.addIntensity(Double.parseDouble(words[intensityIndexList.get(k)]));
                        }
                        for(int k=0;k<normIntensityIndexList.size();k++){
                        if("NA".equals(words[normIntensityIndexList.get(k)]))
                                peptide.addNormIntensity(-0.00000001);
                            else
                                peptide.addNormIntensity(Double.parseDouble(words[normIntensityIndexList.get(k)]));
                        }
                        peptide.setProteinList(proteinList);
                        peptide.setTandemList(isobaricNameList);
                        pepList.add(peptide);
                        eachLine=br.readLine();
                        if (eachLine == null ||(eachLine.startsWith("P\t"))){                           
                            proteinList = new ArrayList<>();
                        }
                    }
                }
                
          
          
      }catch(Exception e){
          e.printStackTrace();
      }
      
      
      
      return pepList;
    }
    
    
    
    
    public List<PeptideIsobaric> displayPeptideList(List<PeptideIsobaric> pepList) {
        List<PeptideIsobaric> finalList = new ArrayList<>();
        HashMap<String,PeptideIsobaric> analysedList = new HashMap<>();
        for (int i = 0; i < pepList.size(); i++) {
            PeptideIsobaric peptide = pepList.get(i);

            if (analysedList.get(peptide.getSequence() + "" + peptide.getChargeState() + peptide.getFileName()) != null) {
                PeptideIsobaric temp =analysedList.get(peptide.getSequence() + "" + peptide.getChargeState() + peptide.getFileName());
                List<Double> intensity = temp.getIntensityList();
                List<Double> normintensity = temp.getNormIntensityList();
                
                List<Double> intensitynew = peptide.getIntensityList();
                List<Double> normintensitynew = peptide.getNormIntensityList();
                
                List<Double> intensityfinal = new ArrayList<>();
                List<Double> normintensityfinal = new ArrayList<>();
                
                for(int j=0;j< intensity.size();j++){
                    double sum = intensity.get(j) + intensitynew.get(j);
                    double sum2 = normintensity.get(j) + normintensitynew.get(j);
                    intensityfinal.add(sum);
                    normintensityfinal.add(sum2);
                }
                temp.setIntensityList(intensityfinal);
                temp.setNormIntensityList(normintensityfinal);
                
            } else {
                analysedList.put(peptide.getSequence() + "" + peptide.getChargeState() + peptide.getFileName(),peptide);
            }  

        }
        for(String key : analysedList.keySet()){
            finalList.add(analysedList.get(key));
        }

        return finalList;
    }
}
