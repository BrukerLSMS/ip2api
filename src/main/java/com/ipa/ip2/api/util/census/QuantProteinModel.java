/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipa.ip2.api.util.census;

import com.ipa.ip2.api.dao.ProteinQuant;
import com.ipa.ip2.api.util.dtaselect.CensusPeptide;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rohan
 */
public class QuantProteinModel {
    private List<ProteinQuant> proList = new ArrayList<>();
    private List<CensusPeptide> peptideList = new ArrayList<>();

    public List<ProteinQuant> getProList() {
        return proList;
    }

    public void setProList(List<ProteinQuant> proList) {
        this.proList = proList;
    }

    public List<CensusPeptide> getPeptideList() {
        return peptideList;
    }

    public void setPeptideList(List<CensusPeptide> peptideList) {
        this.peptideList = peptideList;
    }

    public void addQuantProtein(ProteinQuant pro) {
        proList.add(pro);
    }
    public void addQuantPeptide(CensusPeptide pep) {
        peptideList.add(pep);
    }


}
