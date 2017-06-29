/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipa.ip2.api.util.labelfree;

import com.ipa.ip2.api.dao.ProteinQuant;
import com.ipa.ip2.api.util.Formatter;
import rpark.statistics.CommonStat;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rohan
 */
public class LabelfreeProtein {
    private List<ProteinQuant> proList = new ArrayList<>();
    private List<LabelfreePeptide> peptideList = new ArrayList<>();
    private List<Double> medianRatioList = new ArrayList<>();

    public List<ProteinQuant> getProList() {
        return proList;
    }

    public void setProList(List<ProteinQuant> proList) {
        this.proList = proList;
    }


    public void addQuantProtein(ProteinQuant pro) {
        proList.add(pro);
    }

    public void addLabelfreePeptide(LabelfreePeptide pep) {
        peptideList.add(pep);
    }


    public List<LabelfreePeptide> getPeptideList() {
        return peptideList;
    }

    public void setPeptideList(List<LabelfreePeptide> peptideList) {
        this.peptideList = peptideList;
    }

    public List<Double> getMedianRatioList() {

        if(medianRatioList.size()>0) return medianRatioList;

        if(peptideList.size()<=0) return new ArrayList<Double>();
        if(peptideList.size()==1) {

            this.medianRatioList = peptideList.get(0).getRatioList();
            return this.medianRatioList;
        }

        int ratioCount = peptideList.get(0).getRatioList().size();

        for(int i=0;i<ratioCount;i++) {

            double[] ratioArr = new double[peptideList.size()];

            for(int j=0;j<this.peptideList.size();j++) {
                ratioArr[j] = this.peptideList.get(j).getRatioList().get(i);
            }

            double median = CommonStat.getMedianValue(ratioArr);
            this.medianRatioList.add(Formatter.round(median, 2));

        }

        return medianRatioList;
    }

    public void setMedianRatioList(List<Double> medianRatioList) {
        this.medianRatioList = medianRatioList;
    }
}
