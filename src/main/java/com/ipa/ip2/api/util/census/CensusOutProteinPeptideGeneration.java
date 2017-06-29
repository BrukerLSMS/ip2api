/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.ipa.ip2.util.io.census;
package com.ipa.ip2.api.util.census;

import com.ipa.ip2.api.dao.*;
import com.ipa.ip2.api.reader.LabelFreeStatReader;
import com.ipa.ip2.api.util.dtaselect.CensusPeptide;
import com.ipa.ip2.api.util.dtaselect.Protein;
import com.ipa.ip2.api.util.labelfree.LabelfreePeptide;
import com.ipa.ip2.api.util.labelfree.LabelfreePeptideParser;
import com.ipa.ip2.api.util.tmt.TMTCensusOutReader;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.util.List;

/**
 *
 * @author rampuria
 */
public class CensusOutProteinPeptideGeneration {
    
    public static void main (String [] args){
        
        
        //String fiiePath="/home/rampuria/census_labelfree_out_13269_filled.txt";
        String fiiePath="/data/2/rpark/ip2_data/xudong/AC_TM10_M3/TMT10_MS3_test_2016_04_21_12_81789/quant/2016_06_24_16_12824";
        CensusOutProteinPeptideGeneration cs = new CensusOutProteinPeptideGeneration();
        cs.generateCensusProteinPeptideTMT(fiiePath);

    }
    
    
    public void generateCensusProteinPeptideSILAC(String filePath){
        
        CensusOutReader2 reader = new CensusOutReader2();
        List<QuantProteinModel> qProList = reader.read(filePath);
        File outFile1 = new File(filePath+File.separator+"census-out_protein.txt");
        File outFile2 = new File(filePath+File.separator+"census-out_peptide.txt");
        HashMap<String,String> proteinMap = new HashMap<>();
        HashMap<String,List<String>> peptideProMap = new HashMap<>();
        try{
            BufferedWriter bw1 = new BufferedWriter(new FileWriter(outFile1));
            BufferedWriter bw2 = new BufferedWriter(new FileWriter(outFile2));
            // Header for Protein File   
           // bw1.write("H\tCREATION DATE\t"+new java.text.SimpleDateFormat().format(new Date())+"\n");
           // bw1.write("H\tPATH\t"+filePath+File.separator+"census-out.txt\n");
            bw1.write("ACCESSION\tACCESSION_TYPE\tGENE_SYMBOL\tDESCRIPTION\tINTENSITY_L\tINTENSITY_H\tAVERAGE_RATIO\tSTANDARD_DEVIATION\t"
                    + "COMPOSITE_RATIO\tCOMPOSITE_RATIO_STANDARD_DEVIATION\tPEPTIDE_NUM\tTOTAL_PEPTIDE_NUM"
                    + "\tSPEC_COUNT\tAREA_RATIO\n");
            
            
            
            // Header for Peptide File
           // bw2.write("H\tCREATION DATE\t"+new java.text.SimpleDateFormat().format(new Date())+"\n");
           // bw2.write("H\tPATH\t"+filePath+File.separator+"census-out.txt\n");
            bw2.write("SEQUENCE\tACCESSION\tGENE_SYMBOL\tDESCRIPTION\tINTENSITY_L\tINTENSITY_H\tUNIQUE\tRATIO\tDETERMINANT_FACTOR\tXCorr\t"
                    + "deltaCN\tAREA_RATIO\tFILE_NAME\tSCAN\tCS\n");
            
            for (int i = 0; i < qProList.size(); i++) {
                QuantProteinModel p = qProList.get(i);
                
                
                
                List<ProteinQuant> proteinList = p.getProList();
                List<CensusPeptide> peptideList = p.getPeptideList();
                
                //Building HashMap
                
                for(int j=0;j<peptideList.size();j++){
                    
                    for(int k=0;k<proteinList.size();k++){
                        
                        
                        if(peptideProMap.get(peptideList.get(j).getScan()+peptideList.get(j).getCs()+peptideList.get(j).getFileName()) == null){
                            List<String> proList = new ArrayList<>();
                            proList.add(proteinList.get(k).getLocus());
                            peptideProMap.put(peptideList.get(j).getScan()+peptideList.get(j).getCs()+peptideList.get(j).getFileName(),proList);
                        
                        }
                        else{
                            List<String> proList = peptideProMap.get(peptideList.get(j).getScan()+peptideList.get(j).getCs()+peptideList.get(j).getFileName());
                            proList.add(proteinList.get(k).getLocus());
                        }
                        
                        
                    }
                }          
                DescriptiveStatistics stat1 = new DescriptiveStatistics();
                DescriptiveStatistics stat2 = new DescriptiveStatistics();
                for(int k =0;k<peptideList.size();k++){
                    CensusPeptide peptide = peptideList.get(k);
                    stat1.addValue(Double.parseDouble(peptide.getSamInt()));
                    stat2.addValue(Double.parseDouble(peptide.getRefInt()));
                }
                
                
                // Printing proteins
                for (int j = 0; j < proteinList.size(); j++) {
                    ProteinQuant protein = proteinList.get(j);
                    
                    proteinMap.put(protein.getLocus(), protein.getDesc());
                    
                    //writing of file
                    
                    if(protein.getLocus().startsWith("sp|") || protein.getLocus().startsWith("tr|")){
                        String locus = protein.getLocus();
                        String [] word = locus.split("\\|");
                        bw1.write(word[1]+"\t");
                    }
                    else{
                        bw1.write(protein.getLocus()+"\t");
                    }
                    
                    if(protein.getLocus().startsWith("sp|") || protein.getLocus().startsWith("tr|")){
                        bw1.write("Uniprot\t"); 
                    }else{
                        bw1.write("Unknown\t"); 
                    }
                     
                    bw1.write(protein.getGeneName()+"\t");
                    bw1.write(protein.getDesc()+"\t"); 
                    bw1.write(""+stat1.getPercentile(50)+"\t");
                    bw1.write(""+stat2.getPercentile(50)+"\t");
                    bw1.write(protein.getRatio()+"\t");
                    //bw1.write(protein.getRevRatio()+"\t");
                    bw1.write(protein.getStd()+"\t");
                   // bw1.write(protein.getRevStd()+"\t");
                    bw1.write(protein.getCompositeRatio()+"\t");
                    bw1.write(protein.getCompositeRatioStdev()+"\t");
                    bw1.write(protein.getPepNum()+"\t");
                    bw1.write(protein.getPepNumTotal()+"\t");
                    bw1.write(protein.getSpecCount()+"\t");
                   // bw1.write(protein.getLspecCount()+"\t");
                    //bw1.write(protein.getHspecCount()+"\t");
                    bw1.write(protein.getAreaRatio()+"\n");
                     
                }
                
            }
            bw1.close();  
            
            List<String> analysedList = new ArrayList<>();
            for (int i = 0; i < qProList.size(); i++) {
                QuantProteinModel p = qProList.get(i);
                List<CensusPeptide> peptideList = p.getPeptideList();
                
                            //Printing peptide
                for(int k =0;k<peptideList.size();k++){
                    CensusPeptide peptide = peptideList.get(k);
                    if(analysedList.contains(peptide.getScan()+peptide.getCs()+peptide.getFileName())){
                        continue;
                    }else{
                        analysedList.add(peptide.getScan()+peptide.getCs()+peptide.getFileName());
                        List<String> proteinList = peptideProMap.get(peptide.getScan()+peptide.getCs()+peptide.getFileName());                   
                       
                       
                        //sequence
                        bw2.write(peptide.getSequence() + "\t");
                        //proteins
                        List<String> proList = new ArrayList<>();
                        List<String> descriptionList = new ArrayList<>();
                        List<String> geneList = new ArrayList<>();
                        for (String pro : proteinList) {
                            String desc = proteinMap.get(pro);
                            
                            if (pro.startsWith("sp|") || pro.startsWith("tr|")) {
                                String[] word = pro.split("\\|");
                                proList.add(word[1]);
                            } else {
                                proList.add(pro);
                            }

                            descriptionList.add(desc);
                            geneList.add(proteinGene(desc));
                        }
                        
                        
                       
                  
                  
                  for(String s:proList){
                      bw2.write(s+";");
                  }
                  bw2.write("\t");
                  for(String s:geneList){
                      bw2.write(s+";");
                  }
                  bw2.write("\t");
                  for(String s:descriptionList){
                      bw2.write(s+";");
                  }
                  bw2.write("\t");
                        //intensity_L
                        bw2.write(peptide.getSamInt()+"\t");
                        //intensity_H
                        bw2.write(peptide.getRefInt()+"\t");
                         //Unique
                        if(peptide.isUnique() == true){
                            bw2.write("U\t");
                       }else{
                            bw2.write("NU\t");
                        }
                        //ratio
                        bw2.write(peptide.getRatio()+"\t");
                        //determinant factor
                        bw2.write(peptide.getDetFactor()+"\t");
                        //Xcorr
                        bw2.write(peptide.getxCorr()+"\t");
                        //deltacn
                        bw2.write(peptide.getDeltCN()+"\t");
                        //area_ratio
                        bw2.write(peptide.getAreaRatio()+"\t");
                        //filename
                        bw2.write(peptide.getFileName()+"\t");
                        //scan
                        bw2.write(peptide.getScan()+"\t");
                        //charge state
                        bw2.write(peptide.getCs()+"\n");
                    }
                   
                    
                   // bw2.write(peptide);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
    }
    
    public void generateCensusProteinPeptideTMT(String filePath){
        TMTCensusOutReader tmt = new TMTCensusOutReader();
        List<ProteinIsobaric> proList = tmt.readerProtein(filePath);
        List<PeptideIsobaric> pepList = tmt.readerPeptide(filePath);
       // new CensusPeptideConverter(filePath+File.separator+"census-out.txt",filePath+File.separator+"census-out_peptide.txt");
        List<String> isobaricNameList = proList.get(0).getTagNameList();
        List<String> isobaricNameList2 = pepList.get(0).getTandemList();
        File outFile1 = new File(filePath+File.separator+"census-out_protein.txt");
        File outFile2 = new File(filePath+File.separator+"census-out_peptide.txt");
        HashMap<String,String> proteinMap = new HashMap<>();
        HashMap<String,List<String>> peptideProMap = new HashMap<>();
        try{
            BufferedWriter bw1 = new BufferedWriter(new FileWriter(outFile1));
            BufferedWriter bw2 = new BufferedWriter(new FileWriter(outFile2));
            
          //Header
          bw1.write("ACCESSION\tACCESSION_TYPE\tGENE_SYMBOL\tDESCRIPTION\t");
          //ADDING INTENSITY COLUMN
          for(int i=0;i<isobaricNameList.size();i++){
              bw1.write("INTENSITY_average_"+isobaricNameList.get(i)+"_int\t");
              bw1.write("INTENSITY_norm_average_"+isobaricNameList.get(i)+"_int\t");
              bw1.write("INTENSITY_norm_median_"+isobaricNameList.get(i)+"_int\t");
          }
          bw1.write("SPEC_COUNT\tSEQ_COVERAGE\tLENGTH\tMOLWT\tpI\n");
          
          
          
          //hashMap
                          for(int j=0;j<pepList.size();j++){
                    PeptideIsobaric pep = pepList.get(j);
                    List<String> proteinList = pep.getProteinList();
                    for(int k=0;k<proteinList.size();k++){
                        if(peptideProMap.get(pep.getScanNum()+""+pep.getChargeState()+pep.getFileName()) == null){
                            List<String> tempList = new ArrayList<>();
                            tempList.add(proteinList.get(k));
                            peptideProMap.put(pep.getScanNum()+""+pep.getChargeState()+pep.getFileName(),tempList);
                        
                        }
                        else{
                            List<String> tempList = peptideProMap.get(pep.getScanNum()+""+pep.getChargeState()+pep.getFileName());
                            tempList.add(proteinList.get(k));
                        }
                        
                        
                    }
                } 
          
          
          
          // printing protein
            for (int i = 0; i < proList.size(); i++) {
                ProteinIsobaric protein = proList.get(i);
                
                
                if(protein.getLocus().startsWith("sp|") || protein.getLocus().startsWith("tr|")){
                        String locus = protein.getLocus();
                        String [] word = locus.split("\\|");
                        bw1.write(word[1]+"\t");
                    }
                    else{
                        bw1.write(protein.getLocus()+"\t");
                    }
                
               
                if (protein.getLocus().startsWith("sp|") || protein.getLocus().startsWith("tr|")) {
                    bw1.write("Uniprot\t");
                } else {
                    bw1.write("Unknown\t");
                }

                bw1.write(protein.getGeneName() + "\t");
                bw1.write(protein.getDescription()+"\t");
                
                List<Double> avgIntensity = protein.getAverageIntensityList();
                for(int j=0;j<avgIntensity.size();j++){
                   bw1.write(protein.getAverageIntensityList().get(j)+"\t"+
                           protein.getNormalizedRatioList().get(j)+"\t"+
                           protein.getNormalizedMeidanRatioList().get(j)+"\t");
                }
                bw1.write(protein.getSpecCount() + "\t");
                bw1.write(protein.getSeqcoverage() + "\t");
                bw1.write(protein.getLength() + "\t");
                bw1.write(protein.getMolwt() + "\t");
                bw1.write(protein.getpI() + "\n");

            }
          bw1.close();
          
          
          //Header for peptide
          bw2.write("SEQUENCE\t");
          bw2.write("ACCESSION\tGENE_SYMBOL\tDESCRIPTION\t");
          
//          
          for(int i =0;i<isobaricNameList2.size();i++){
              bw2.write("INTENSITY_"+isobaricNameList2.get(i)+"\t");
              bw2.write("INTENSITY_norm_"+isobaricNameList2.get(i)+"\t");
          }
          bw2.write("UNIQUE\t");			
          bw2.write("SPEC_COUNT\tSCAN_NUM\tCSTATE\tFILENAME\n");
          
          //printing peptide
          List<String> analysedList = new ArrayList<>();
          for(int i=0;i<pepList.size();i++){
              PeptideIsobaric temp = pepList.get(i);
              if(analysedList.contains(temp.getScanNum()+""+temp.getChargeState()+temp.getFileName())){
                  continue;
              }
              else{
                  analysedList.add(temp.getScanNum()+""+temp.getChargeState()+temp.getFileName());
                  List<String> proteindescList = peptideProMap.get(temp.getScanNum()+""+temp.getChargeState()+temp.getFileName());
                  
                  bw2.write(temp.getSequence()+"\t");
                  
                  List<String> proteinList = new ArrayList<>();
                  List<String> descriptionList = new ArrayList<>();
                  List<String> geneList = new ArrayList<>();
                  for (int j = 0; j < proteindescList.size(); j++) {
                      String text = proteindescList.get(j);
                      String [] words = text.split(" ", 2);
                      if (words[0].startsWith("sp|") || words[0].startsWith("tr|")) {
                                String[] word = words[0].split("\\|");
                                proteinList.add(word[1]);
                            } else {
                                proteinList.add(words[0]);
                            }
                      
                      
                      
                      descriptionList.add(words[1]);
                      geneList.add(proteinGene(words[1]));
                  }
                  for(String s:proteinList){
                      bw2.write(s+";");
                  }
                  bw2.write("\t");
                  for(String s:geneList){
                      bw2.write(s+";");
                  }
                  bw2.write("\t");
                  for(String s:descriptionList){
                      bw2.write(s+";");
                  }
                  bw2.write("\t");
                  
                  
                  for(int j=0;j<temp.getIntensityList().size();j++){
                      bw2.write(temp.getIntensityList().get(j)+"\t"+temp.getNormIntensityList().get(j)+"\t");
                  }
                  bw2.write(temp.getUnique() + "\t");
                  bw2.write(temp.getSpC()+"\t");
                  bw2.write(temp.getScanNum()+"\t");
                  bw2.write(temp.getChargeState()+"\t");
                  bw2.write(temp.getFileName()+"\n");
              }
          }
            
          bw2.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    public void generateCensusPeptideLabelfree(String filePath,int quantaionId){
        File file = new File(filePath);
        LabelfreePeptideParser lfree = new LabelfreePeptideParser();
        lfree.read(filePath);
        List<LabelfreePeptide> pepList = lfree.getPepList();
        int expNum=lfree.getRepList().size() * lfree.getRepList().get(0).getReplicateList().size();
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(file.getParent()+File.separator+"labelfree_peptide_"+quantaionId+".txt"));
            System.out.print(file.getParent()+File.separator+"labelfree_protein_"+quantaionId+".txt");

               //bw.write("SEQUENCE\tFILENAME\tSCAN\tCSTATE\tINTENSITY\t");
                
             bw.write("SEQUENCE\tACCESSION\tGENE_SYMBOL\tDESCRIPTION\t");
            
            for (int i = 1; i <= expNum; i++) {
                bw.write("INTENSITY_"+i+"\t");
            }
            for (int i = 1; i <= expNum; i++) {
                bw.write("FILENAME_" + i + "\t");
            }
            for (int i = 1; i <= expNum; i++) {
                bw.write("CSTATE_" + i + "\t");
            }
            for (int i = 1; i <= expNum; i++) {
                bw.write("SCAN_" + i + "\t");
            }
            bw.write("\n");
            for(int i=0;i<pepList.size();i++){
                LabelfreePeptide pep = pepList.get(i);
                bw.write(pep.getSequence()+"\t");
                
                
                if(pep.getProtein().startsWith("sp|") || pep.getProtein().startsWith("tr|")){
                        String locus = pep.getProtein();
                        String [] word = locus.split("\\|");
                        bw.write(word[1]+"\t");
                    }
                    else{
                        bw.write(pep.getProtein()+"\t");
                    }
                
                
                
                
                bw.write(proteinGene(pep.getDescription())+"\t");
                bw.write(pep.getDescription()+"\t");
                
                for(int j=0;j<expNum;j++){
                    bw.write(pep.getIntensity().get(j)+"\t");
                }
                for(int j=0;j<expNum;j++){                   
                    bw.write(pep.getFilename().get(j)+"\t"); 
                }
                for(int j=0;j<expNum;j++){
                    bw.write(pep.getChargestate()+"\t");
                }
                for(int j=0;j<expNum;j++){
                    bw.write(pep.getScanList().get(j)+"\t");
                }
                bw.write("\n");
               
            }
            bw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
       
        
    }
    public void generateCensusProteinLabelfree(String fileName,int quantaionId){
        File file = new File(fileName);
        LabelFreeStatReader labelFreeStatReader = new LabelFreeStatReader(fileName);
	    List<LabelFreeStatPline> pList =labelFreeStatReader.getPlines();
            try{
                
                BufferedWriter bw = new BufferedWriter(new FileWriter(file.getParent()+File.separator+"labelfree_protein_"+quantaionId+".txt"));
                //header
                System.out.print(file.getParent()+File.separator+"labelfree_protein_"+quantaionId+".txt");
                bw.write("ACCESSION\tACCESSION_TYPE\tGENE_SYMBOL\tDESCRIPTION\t");
                
                for(int i=0;i<labelFreeStatReader.getScountHeader().size();i++){
                    bw.write("INTENSITY_"+(i+1)+"\t");
                }
                for(String scount:labelFreeStatReader.getScountHeader()){
                    bw.write(scount+"\t");
                }
                bw.write("INTENSITY_PVALUE\tPVALUE_RATIO_BASED\n");
                
                
                
                // PRINTING VALUES
                for (int i = 0; i < pList.size(); i++) {
                    LabelFreeStatPline protein = pList.get(i);
                    if(protein.getAccession().startsWith("sp|") || protein.getAccession().startsWith("tr|")){
                        String locus = protein.getAccession();
                        String [] word = locus.split("\\|");
                        bw.write(word[1]+"\t");
                    }
                    else{
                        bw.write(protein.getAccession() +"\t");
                    }
                    
                    
                    
                    
                    if (protein.getAccession().startsWith("sp|") || protein.getAccession().startsWith("tr|")) {
                        bw.write("Uniprot\t");
                    } else {
                        bw.write("Unknown\t");
                    }
                    bw.write(protein.getGene()+"\t");
                    bw.write(protein.getDesc()+"\t");
                    if(protein.getIndividualIntensity().size()>0){
                        for(int j=0;j<protein.getIndividualIntensity().size();j++){
                            bw.write(protein.getIndividualIntensity().get(j)+"\t");
                        }
                    }else{
                        for(int j=0;j<protein.getAverageIntensity().size();j++){
                            bw.write(protein.getAverageIntensity().get(j)+"\t");
                        }
                    }
                    for(int j=0;j<protein.getScounts().size();j++){
                        Scount s= protein.getScounts().get(j);
                        bw.write(s.getScountValue()+"\t");
                }
                    
                    bw.write(protein.getpValue()+"\t");
                    bw.write(protein.getpValueRatioBased()+"\n");
                    
                }
                bw.close();
                
            }catch(Exception e){
                e.printStackTrace();
            }
    }
    
    public String proteinGene(String description) {
        for (String currentWord : description.split(" ")) {
            if (currentWord.startsWith("GN=")) {
                String str = currentWord.split("GN=")[1].trim();
                return Protein.removeRef(str);
                
            }
        }
        return null;
    }
}
