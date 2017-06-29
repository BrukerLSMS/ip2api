/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipa.ip2.api.util.census;

import com.ipa.ip2.api.dao.ProteinQuant;
import com.ipa.ip2.api.util.dtaselect.CensusPeptide;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Rohan
 */
public class CensusOutReader2 {

    public static void main(String[] args) {
        //String filepath = "/Users/OfirSoft/Ofir/sandbox/ip2_dev/vcour/zIp2Test/data/quant_view";
        //String filepath = "/data/2/rpark/ip2_data/rpark/Demo_Project_c_elegance/D2_pPro_A__2009_12_09_15_461/quant/2015_09_11_13_11356";
        //String filepath = "/data/2/rpark/ip2_data/rpark/Demo_Project_c_elegance/D2_pPro_A__2009_12_09_15_461/quant/2014_01_02_15_9029";
        //String filepath = "/data/2/rpark/ip2_data//jsavas/Tg_AD_PDAPP_and_N5/ADpre2CTX_sample1_2010_05_26_17_1261/quant/2010_06_17_15_1314/";
        String filepath = "/home/rampuria/data/Quant_Isotopes/";

//        filepath = args[0];
        CensusOutReader2 reader = new CensusOutReader2();
        // use this as filepath /data/2/rpark/ip2_data/rpark/Demo_Project_c_elegance/D2_pPro_A__2009_12_09_15_461/quant/2014_01_02_15_9029/census-out.txt
        List<QuantProteinModel> qProList = reader.read(filepath);
        for(QuantProteinModel q:qProList) {
            List<ProteinQuant> pqList = q.getProList();
           // for(ProteinQuant pq:pqList)
           // System.out.println("--" + pq.getLocus());
        }
    //    QuantProteinModel qPro = reader.find("C26D10.1", qProList);
    }


    public List<QuantProteinModel> read(String filepath) {
        List<QuantProteinModel> qproList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filepath + File.separator + "census-out.txt"));
            String eachLine;
            String[] tmpArr;
            System.out.println("filename==\t" + filepath + File.separator + "census-out.txt");
            // H	PLINE	LOCUS	AVERAGE_RATIO	AVERAGE_RATIO_REV	STANDARD_DEVIATION	STANDARD_DEVIATION_REV	COMPOSITE_RATIO	COMPOSITE_RATIO_STANDARD_DEVIATION	WEIGHTED_AVERAGE	LOG_INV_AVERAGE	LOG_INV_AVERAGE_REV	PEPTIDE_NUM	SPEC_COUNT	LSPEC_COUNT	HSPEC_COUNT	AREA_RATIO	DESCRIPTION
            int locusIndex = -1;
            int ratioIndex = -1;
            int revRatioIndex = -1;
            int stdIndex = -1;
            int revStdIndex = -1;
            int weightRatioIndex = -1;
            int pepNumIndex = -1;
            int pepNumTotalIndex = -1;
            int specCountIndex = -1;
            int descIndex = -1;
            int compositeRatioIndex = -1;
            int compositeRatioStdevIndex = -1;
            int lspecCountIndex = -1;
            int hspecCountIndex = -1;
            int areaRatioIndex = -1;
            List<ProteinQuant> proList = new ArrayList<>();
            eachLine = br.readLine();
            while (eachLine != null) {
                if (eachLine.startsWith("H\tPLINE\tLOCUS")) {
                    tmpArr = eachLine.split("\t");
                    for (int i = 0; i < tmpArr.length; i++) {
                        if (tmpArr[i].equals("AVERAGE_RATIO")) {
                            ratioIndex = i - 1;
                        } else if (tmpArr[i].equals("LOCUS")) {
                            locusIndex = i - 1;
                        } else if (tmpArr[i].equals("AVERAGE_RATIO_REV")) {
                            revRatioIndex = i - 1;
                        } else if (tmpArr[i].equals("STANDARD_DEVIATION")) {
                            stdIndex = i - 1;
                        } else if (tmpArr[i].equals("STANDARD_DEVIATION_REV")) {
                            revStdIndex = i - 1;
                        } else if (tmpArr[i].equals("WEIGHTED_AVERAGE")) {
                            weightRatioIndex = i - 1;
                        } else if (tmpArr[i].equals("PEPTIDE_NUM")) {
                            pepNumIndex = i - 1;
                        } else if (tmpArr[i].equals("TOTAL_PEPTIDE_NUM")) {
                            pepNumTotalIndex = i - 1;
                        } else if (tmpArr[i].equals("SPEC_COUNT")) {
                            specCountIndex = i - 1;
                        } else if (tmpArr[i].equals("DESCRIPTION")) {
                            descIndex = i - 1;
                        } else if (tmpArr[i].equals("COMPOSITE_RATIO")) {
                            compositeRatioIndex = i - 1;
                        } else if (tmpArr[i].equals("COMPOSITE_RATIO_STANDARD_DEVIATION")) {
                            compositeRatioStdevIndex = i - 1;
                        }else if (tmpArr[i].equals("LSPEC_COUNT")) {
                            lspecCountIndex = i - 1;
                        } else if (tmpArr[i].equals("HSPEC_COUNT")) {
                            hspecCountIndex = i - 1;
                        } else if (tmpArr[i].equals("AREA_RATIO")) {
                            areaRatioIndex = i - 1;
                        }
                    }
                }
                //     H	SLINE	UNIQUE	SEQUENCE	RATIO	REV_SLOPE_RATIO	REGRESSION_FACTOR	DETERMINANT_FACTOR	PROBABILITY_SCORE	XCorr	deltaCN	SAM_INT	REF_INT	PEAK_INT	AREA_RATIO	PROFILE_SCORE	FILE_NAME	SCAN	CS	ENRICHMENT	Mod Sequence	Localization Score	Debunker Score

                if (eachLine.startsWith("H\tSLINE\tUNIQUE")) {
                    CensusPeptide.setFeatureIndices(eachLine);
                }
                if (eachLine.startsWith("H\t&SLINE\tUNIQUE")) {
                    CensusPeptide.setSingletonFeatureIndices(eachLine);
                }
                if (eachLine.startsWith("P\t")) {
                    QuantProteinModel qProtein = new QuantProteinModel();
                    while (eachLine.startsWith("P\t")){
                            //System.out.println("aaaaaaaabbbb " + eachLine);
                        String[] words;
                        words = eachLine.split("\t");
                        if(words.length<12 || "".equals(words[ratioIndex])) {
                           eachLine = br.readLine(); continue; //old format with singlenton peptides only
                        }

                        ProteinQuant pro = new ProteinQuant();
                        pro.setLocus(words[locusIndex]);

                        pro.setRatio(words[ratioIndex]);

                        pro.setRevRatio(revRatioIndex > -1 ? words[revRatioIndex] : "NA");

                        pro.setStd(words[stdIndex]);

                        pro.setRevStd(revStdIndex > -1 ? words[revStdIndex] : "NA");

                        pro.setWeightRatio(words[weightRatioIndex]);

                        pro.setPepNum(words[pepNumIndex]);

                        if(pepNumTotalIndex > -1){
                            pro.setPepNumTotal(words[pepNumTotalIndex]);
                        }
                        pro.setSpecCount(words[specCountIndex]);
                        pro.setDesc(words[descIndex]);
                        pro.setGeneName(words[descIndex]);

                        if (compositeRatioIndex >= 0) {
                            pro.setCompositeRatio(Double.parseDouble(words[compositeRatioIndex]));
                            pro.setCompositeRatioStdev(Double.parseDouble(words[compositeRatioStdevIndex]));
                        }
                        
                        //recently added
                        pro.setLspecCount(words[lspecCountIndex]);
                        pro.setHspecCount(words[hspecCountIndex]);
                        pro.setAreaRatio(words[areaRatioIndex]);
                        
                        
                        
                        qProtein.addQuantProtein(pro);
                        eachLine = br.readLine();
                    }
                    if (eachLine.startsWith("S\t") || eachLine.startsWith("&S")){
                        // read peptide line
                        CensusPeptide pep = null;
                        while(eachLine != null){

                            if (eachLine.startsWith("S\t")) {
                                String[] arr = eachLine.split("\t");
                                pep = new CensusPeptide(arr);
                                qProtein.addQuantPeptide(pep);
                                eachLine = br.readLine();
                            }
                            else if(eachLine.startsWith("&S")) {
                                String[] arr = eachLine.split("\t");
                                pep = new CensusPeptide(arr,"singleton");
                                qProtein.addQuantPeptide(pep);
                                eachLine = br.readLine();
                            }
                            else{
                                break;
                            }

                        }

                        if (eachLine == null ||(eachLine.startsWith("P\t"))){
                            qproList.add(qProtein);
                            proList.clear();
                        }
                    }
                }
                else {
                    eachLine = br.readLine();
                }
            }

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return qproList;
    }


    public QuantProteinModel find (String locus, List<QuantProteinModel> qProList){
        Iterator i = qProList.iterator();
        while (i.hasNext()) {
            QuantProteinModel qPro = (QuantProteinModel) i.next();
            for (int j = 0; j< qPro.getProList().size(); j++){
                if (locus.equals(qPro.getProList().get(j).getLocus())){
                    System.out.println("reached here");
                    return qPro;
                }
            }
            
            
        }
        
        
        
        return null; 
    }
    
}
