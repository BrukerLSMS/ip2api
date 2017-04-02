package com.ipa.ip2.api.reader;

import com.ipa.ip2.api.dao.*;
import com.ipa.ip2.api.util.LabelFreeUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class will useful to parse the label free stat file. (e.g census_labelfree_out_13532_stat.txt)
 *
 * @author Amit Patel
 * @version 1.0
 */
public class LabelFreeStatReader {

    private static final String DEFAULT_DELIMITER = "\t";
    /**
     * Current census out file.
     */
    private String file = null;

    /**
     * plineHeader
     */
    private Map<String, Integer> plineHeader = new HashMap<String, Integer>();

    /**
     * medianHeader
     */
    private List<String> medianHeader = new ArrayList<String>();

    /**
     * scountHeader
     */
    private List<String> scountHeader = new ArrayList<String>();

    /**
     * medianRatioHeader
     */
    private List<String> medianRatioHeader = new ArrayList<String>();

    /**
     * averageIntensityHeader
     */
    private List<String> averageIntensityHeader = new ArrayList<String>();

    /**
     * averageIITIntensityHeader
     */
    private List<String> averageIITIntensityHeader = new ArrayList<String>();

    /**
     * averageAVGIntensityHeader
     */
    private List<String> averageAVGIntensityHeader = new ArrayList<String>();

    /**
     * averageAVGIITIntensityHeader
     */
    private List<String> averageAVGIITIntensityHeader = new ArrayList<String>();

    /**
     * ratioIntensityHeader
     */
    private List<String> ratioIntensityHeader = new ArrayList<String>();

    /**
     * ratioIITIntensityHeader
     */
    private List<String> ratioIITIntensityHeader = new ArrayList<String>();

    /**
     * logratioIntensityHeader
     */
    private List<String> logratioIntensityHeader = new ArrayList<String>();

    /**
     * logratioIITIntensityHeader
     */
    private List<String> logratioIITIntensityHeader = new ArrayList<String>();

    /**
     * individualIntensityHeader
     */
    private List<String> individualIntensityHeader = new ArrayList<String>();

    /**
     * peptideIntensityDividedByPLengthHeader
     */
    private int peptideIntensityDividedByPLengthHeader = -1;

    /**
     * proteinLengthHeader
     */
    private int proteinLengthHeader = -1;

    /**
     * repList
     */
    private ArrayList<Replicates> repList = new ArrayList<Replicates>();

    /**
     * plines
     */
    private List<LabelFreeStatPline> plines = new ArrayList<LabelFreeStatPline>();

    /**
     * Provide the label free stat file path to the constructor to parse the file.
     * @param file
     */
    public LabelFreeStatReader(String file) {
        this.file = file;
        parse();
    }

    /**
     * This will parse the census out file.
     */
    @SuppressWarnings("resource")
    private void parse() {

        try {
            BufferedReader br = new BufferedReader(new FileReader(this.file));
            String eachLine = "";
            LabelFreeStatPline pline = null;
            List<MedianLogRatio> medianLogRatios = null;
            MedianLogRatio logRatio = null;

            List<Scount> scounts = null;
            Scount scount = null;

            List<LabelfreeMedianRatio> medianRatioList = null;
            LabelfreeMedianRatio medianRatio = null;

            while ((eachLine = br.readLine()) != null && !eachLine.startsWith("PLINE")) {
                if (eachLine.startsWith("H\tGROUP_SAMPLE")) {
                    String[] arr = eachLine.split("\t");

                    Replicates replicate = new Replicates();
                    replicate.setName(arr[2]);

                    for (int i = 3; i < arr.length; i++) {
                        Replicate r = new Replicate();
                        r.setSampleName(arr[i]);
                        replicate.addReplicate(r);
                    }

                    repList.add(replicate);
                }
            }


            String[] row = eachLine.split(DEFAULT_DELIMITER);
            if (row[0].equals("PLINE") && row[1].equals("ACCESSION")) {
                for (int i = 1; i < row.length; i++) {
                    if (row[i].startsWith("MEDIAN_LOG_RATIO")) {
                        medianHeader.add(row[i]);
                    } else if (row[i].startsWith("SCOUNT")) {
                        scountHeader.add(row[i]);
                    } else if (row[i].startsWith("RATIO_")) {
                        medianRatioHeader.add(row[i]);
                    } else if (row[i].startsWith("AVG_INT")) {
                        averageIntensityHeader.add(row[i]);
                    } else if (row[i].startsWith("AVG_ION_")) {
                        averageIITIntensityHeader.add(row[i]);
                    } else if (row[i].startsWith("AVG_GROUP_INTENSITY_VALUE_")) {
                        averageAVGIntensityHeader.add(row[i]);
                    } else if (row[i].startsWith("AVG_GROUP_ION_INJECT")) {
                        averageAVGIITIntensityHeader.add(row[i]);
                    } else if (row[i].startsWith("AVG_GROUP_INTENSITY_RAT")) {
                        ratioIntensityHeader.add(row[i]);
                    } else if (row[i].startsWith("AVG_GROUP_IIT_INTENSI")) {
                        ratioIITIntensityHeader.add(row[i]);
                    } else if (row[i].startsWith("LOG_AVG_GROUP_INTEN")) {
                        logratioIntensityHeader.add(row[i]);
                    } else if (row[i].startsWith("LOG_AVG_GROUP_IIT_INT")) {
                        logratioIITIntensityHeader.add(row[i]);
                    } else if (row[i].startsWith("INTENSITY_") && !row[i].startsWith("INTENSITY_P") && !row[i].startsWith("INTENSITY_Q")) {
                        individualIntensityHeader.add(row[i]);
                    } else if (row[i].startsWith("TOTOL_PEPTIDE_INTENSITY_DIVIDEDBY_PROTEIN_LENGTH_LOG10")) {
                        peptideIntensityDividedByPLengthHeader = i;
                    } else if (row[i].equals("PROTEIN_LENGTH")) {
                        proteinLengthHeader = i;
                    }

                    plineHeader.put(row[i], i);
                }
            }

            while ((eachLine = br.readLine()) != null) {
                row = eachLine.split(DEFAULT_DELIMITER);
                if (row[0].trim().equals("P")) {

                    pline = new LabelFreeStatPline();
                    List<String> avgList = new ArrayList<String>();
                    List<String> avgIITList = new ArrayList<String>();
                    List<String> avgAvgIntensityList = new ArrayList<String>();
                    List<String> avgAverageIITList = new ArrayList<String>();
                    List<String> ratioList = new ArrayList<String>();
                    List<String> ratioIITList = new ArrayList<String>();
                    List<String> logratioList = new ArrayList<String>();
                    List<String> logratioIITList = new ArrayList<String>();
                    List<String> individualIntensityList = new ArrayList<String>();

                    for (String s : averageIntensityHeader) {
                        avgList.add(row[plineHeader.get(s)]);

                    }
                    for (String s : averageIITIntensityHeader) {
                        avgIITList.add(row[plineHeader.get(s)]);
                    }
                    for (String s : averageAVGIntensityHeader) {
                        avgAvgIntensityList.add(row[plineHeader.get(s)]);
                    }
                    for (String s : averageAVGIITIntensityHeader) {
                        avgAverageIITList.add(row[plineHeader.get(s)]);
                    }
                    for (String s : ratioIntensityHeader) {
                        ratioList.add(row[plineHeader.get(s)]);
                    }
                    for (String s : ratioIITIntensityHeader) {
                        ratioIITList.add(row[plineHeader.get(s)]);
                    }
                    for (String s : logratioIntensityHeader) {
                        logratioList.add(row[plineHeader.get(s)]);
                    }
                    for (String s : logratioIITIntensityHeader) {
                        logratioIITList.add(row[plineHeader.get(s)]);
                    }
                    for (String s : individualIntensityHeader) {
                        individualIntensityList.add(row[plineHeader.get(s)]);
                    }

                    pline.setAverageAVGIntensity(avgAvgIntensityList);
                    pline.setAverageAVGIITIntensity(avgAverageIITList);
                    pline.setRatioIntensity(ratioList);
                    pline.setRatioIITIntensity(ratioIITList);
                    pline.setLogratioIntensity(logratioList);
                    pline.setLogratioIITIntensity(logratioIITList);
                    pline.setAverageIntensity(avgList);
                    pline.setAverageIITIntensity(avgIITList);
                    pline.setAccession(row[plineHeader.get("ACCESSION").intValue()]);
                    String description = row[plineHeader.get("DESCRIPTION").intValue()];

                    pline.setDesc(description);
                    String gene = LabelFreeUtils.extractGeneName(description);

                    pline.setGene(gene);


                    pline.setIndividualIntensity(individualIntensityList);


                    if (row[plineHeader.get("INTENSITY_P-VALUE")] == null || row[plineHeader.get("INTENSITY_P-VALUE")].equals("null")) {

                        pline.setpValue("1.0");

                    } else {
                        pline.setpValue(row[plineHeader.get("INTENSITY_P-VALUE").intValue()]);
                    }

                    if (row[plineHeader.get("IIT_INTENSITY_P-VALUE").intValue()] == null || row[plineHeader.get("IIT_INTENSITY_P-VALUE").intValue()].equals("null")) {
                        pline.setpValueIIT("1.0");
                    } else {
                        pline.setpValueIIT(row[plineHeader.get("IIT_INTENSITY_P-VALUE").intValue()]);
                    }


                    if (plineHeader.get("P-VALUE_RATIO_BASED") == null) {
                        // pline.setpValue(row[plineHeader.get("p-value_ratio_based").intValue()]);
                    } else {
                        pline.setpValueRatioBased(row[plineHeader.get("P-VALUE_RATIO_BASED").intValue()]);
                    }

                    if (null != plineHeader.get("INTENSITY_Q-VALUE"))
                        pline.setqValue(row[plineHeader.get("INTENSITY_Q-VALUE").intValue()]);

                    if (null != plineHeader.get("IIT_INTENSITY_Q-VALUE"))
                        pline.setqValueIIT(row[plineHeader.get("IIT_INTENSITY_Q-VALUE").intValue()]);


                    medianLogRatios = new ArrayList<MedianLogRatio>();
                    scounts = new ArrayList<Scount>();
                    medianRatioList = new ArrayList<LabelfreeMedianRatio>();

                    for (String key : medianHeader) {

                        if (key.startsWith("MEDIAN_LOG_RATIO")) {
                            logRatio = new MedianLogRatio();
                            logRatio.setMedianLogHeader(key);
                            logRatio.setMedianLogValue(row[plineHeader.get(key).intValue()]);
                            medianLogRatios.add(logRatio);
                        }

                    }

                    pline.setMedianLogRatios(medianLogRatios);

                    for (String key : scountHeader) {

                        if (key.startsWith("SCOUNT")) {
                            scount = new Scount();
                            scount.setScountHeader(key);
                            scount.setScountValue(row[plineHeader.get(key).intValue()]);
                            scounts.add(scount);
                        }

                    }


                    for (String key : medianRatioHeader) {

                        if (key.startsWith("RATIO_")) {
                            medianRatio = new LabelfreeMedianRatio();
                            medianRatio.setHeader(key);

                            medianRatio.setValue(Double.parseDouble(row[plineHeader.get(key).intValue()]));
                            medianRatioList.add(medianRatio);
                        }

                    }

                    if (peptideIntensityDividedByPLengthHeader >= 0)
                        pline.setPeptideIntensityDividedByPLength(Double.parseDouble(row[peptideIntensityDividedByPLengthHeader]));

                    if (proteinLengthHeader >= 0)
                        pline.setProteinLength(Integer.parseInt(row[proteinLengthHeader]));

                    pline.setScounts(scounts);
                    pline.setMedianRatios(medianRatioList);

                    plines.add(pline);

                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the census out file path
     * @return file
     */
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    /**
     * Get the protein lines from the census out file
     * @return plines
     */
    public List<LabelFreeStatPline> getPlines() {
        return plines;
    }

    public void setPlines(List<LabelFreeStatPline> plines) {
        this.plines = plines;
    }

    /**
     * Get the rep list from the census out file
     * @return repList
     */
    public ArrayList<Replicates> getRepList() {
        return repList;
    }

    public void setRepList(ArrayList<Replicates> repList) {
        this.repList = repList;
    }

    /**
     * Get the scount header
     * @return scountHeader
     */
    public List<String> getScountHeader() {
        return scountHeader;
    }
}
