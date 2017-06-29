/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipa.ip2.api.dao;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rampuria
 */
public class PeptideIsobaric {
//H	SLINE	UNIQUE	SEQUENCE	m/z_126.127726_int	norm_m/z_126.127726_int	m/z_127.124761_int
       // norm_m/z_127.124761_int	m/z_127.131081_int	norm_m/z_127.131081_int	m/z_128.128116_int
       //         norm_m/z_128.128116_int	m/z_128.134436_int	norm_m/z_128.134436_int	m/z_129.131471_int
                  //      norm_m/z_129.131471_int	m/z_129.13779_int	norm_m/z_129.13779_int	
                 //               m/z_130.134825_int	norm_m/z_130.134825_int	m/z_130.141145_int
                           //             norm_m/z_130.141145_int	m/z_131.13818_int	norm_m/z_131.13818_int	
                           //                     SpC	ScanNum	CState	Filename
    private String unique;
    private String sequence;
    List<Double> normIntensityList = new ArrayList<>();
    List<Double> intensityList = new ArrayList<>();
    private int SpC;
    private int scanNum;
    private int chargeState;
    private String fileName;
    List<String> proteinList = new ArrayList<>();
    private List<String> tandemList = new ArrayList<>();

    public List<String> getTandemList() {
        return tandemList;
    }

    public void setTandemList(List<String> tandemList) {
        this.tandemList = tandemList;
    }
    
    
    

    public List<String> getProteinList() {
        return proteinList;
    }

    public void setProteinList(List<String> proteinList) {
        this.proteinList = proteinList;
    }
    
    public String getUnique() {
        return unique;
    }

    public void setUnique(String unique) {
        this.unique = unique;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
    
    public void addNormIntensity(double d) {
        this.normIntensityList.add(d);
    }

    public List<Double> getNormIntensityList() {
        return normIntensityList;
    }

    public void setNormIntensityList(List<Double> normIntensityList) {
        this.normIntensityList = normIntensityList;
    }
    
    public void addIntensity(double d) {
        this.intensityList.add(d);
    }

    public List<Double> getIntensityList() {
        return intensityList;
    }

    public void setIntensityList(List<Double> intensityList) {
        this.intensityList = intensityList;
    }

    public int getSpC() {
        return SpC;
    }

    public void setSpC(int SpC) {
        this.SpC = SpC;
    }

    public int getScanNum() {
        return scanNum;
    }

    public void setScanNum(int scanNum) {
        this.scanNum = scanNum;
    }

    public int getChargeState() {
        return chargeState;
    }

    public void setChargeState(int chargeState) {
        this.chargeState = chargeState;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    
}
