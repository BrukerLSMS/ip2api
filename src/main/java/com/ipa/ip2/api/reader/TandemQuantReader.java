package com.ipa.ip2.api.reader;

import com.ipa.ip2.api.dao.ProteinIsobaric;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class will useful to parse the tandem quant census file. (e.g census-out.txt)
 *
 * @author Amit Patel
 * @version 1.0
 */
public class TandemQuantReader {
    private String file = null;
    private List<ProteinIsobaric> proteinList = new ArrayList();
    private List<String> isobaricNameList = new ArrayList<String>();

    /**
     * Provide the tandem quant census file to the constructor to parse the file.
     * @param file
     */
    public TandemQuantReader(String file){
        this.file = file;
        parse();
    }

    /**
     * Parse the census file.
     */
    private void parse() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(this.file));
            String eachLine;
            while(!(eachLine=br.readLine()).startsWith("H\tPLINE\tLOCUS") );

            String[] harr = eachLine.split("\t");

            List<Integer> averageIndexList = new ArrayList<Integer>();
            List<Integer> totalIndexList = new ArrayList<Integer>();
            List<Integer> normIndexList = new ArrayList<Integer>();
            List<Integer> normMedianIndexList = new ArrayList<Integer>();

            List<String> isobaricNameList = new ArrayList<String>();

            int locusIndex=-1;
            int descIndex=-1;
            int specCountIndex=-1;
            int pepNumIndex=-1;

            int tmpIndex=-1; //because of H line, it starts with -1;
            for(String str:harr) {
                if(str.startsWith("average")) {
                    averageIndexList.add(tmpIndex);
                    isobaricNameList.add(str.substring(str.indexOf("m/z")));
                }else if(str.startsWith("total")){
                    totalIndexList.add(tmpIndex);
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

                tmpIndex++;
            }

            List<ProteinIsobaric> proList = new ArrayList<ProteinIsobaric>();

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
                    for(int i:totalIndexList) {
                        pro.addTotalIntensity(Double.parseDouble(arr[i]));
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
                    proList.add(pro);
                } catch (Exception e) {
                    System.out.println("error: " + eachLine);
                    e.printStackTrace();
                }
            }
            this.setIsobaricNameList(isobaricNameList);
            this.setProteinList(proList);

        } catch (Exception e) {
            try {   if(null != br) br.close(); }
            catch(IOException ie) { }
        }
    }

    /**
     * Get the proteins list
     * @return proteinList
     */
    public List<ProteinIsobaric> getProteinList() {
        return proteinList;
    }

    public void setProteinList(List<ProteinIsobaric> proteinList) {
        this.proteinList = proteinList;
    }

    /**
     * Get the Isobaric name list
     * @return isobaricNameList
     */
    public List<String> getIsobaricNameList() {
        return isobaricNameList;
    }

    public void setIsobaricNameList(List<String> isobaricNameList) {
        this.isobaricNameList = isobaricNameList;
    }
}
