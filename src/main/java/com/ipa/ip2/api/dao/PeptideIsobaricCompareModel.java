/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipa.ip2.api.dao;

import java.util.*;

/**
 *
 * @author rpark
 */
public class PeptideIsobaricCompareModel extends PeptideCompareModel {
    
    private Set<String> proteinSet = new HashSet<String>();
    private List<Long> intensityList = new ArrayList<Long>();
    private Set<String> csSet = new HashSet<String>();
    private int pepCount=1;
    private boolean unique=false;
    
    public Set<String> getProteinSet() {
        return proteinSet;
    }

    public void setProteinSet(Set<String> proteinSet) {
        this.proteinSet = proteinSet;
    }

    public void addProteinList(List<String> plist) {
        this.proteinSet.addAll(plist);
    }

    public List<Long> getIntensityList() {
        return intensityList;
    }

    public List<Long> getAverageIntensityList() {
        List<Long> l = new ArrayList<Long>();
        
        for(int i=0;i<this.intensityList.size();i++) {
            l.add(this.intensityList.get(i)/this.getPepCount());
        }
        
        return l;        
    }

       
        
    public void setIntensityList(List<Long> intensityList) {
        this.intensityList = intensityList;
    }

    public void setValue(List<String> proteinList, String[] arr, List<Integer> intIndexList) {
        this.proteinSet.addAll(proteinList);

        for(int i=0;i<intIndexList.size();i++) {
            //System.out.println(this.getSequence() + " " + arr[intIndexList.get(i)]);
            
            this.intensityList.add(i, Long.parseLong(arr[intIndexList.get(i)]));            
        }
        
        //printIntensity();
    }    
   
    public void mergePeptide(List<String> proteinList, String[] arr, List<Integer> intIndexList, String cs) {
        this.proteinSet.addAll(proteinList);
        this.csSet.add(cs);
        for(int i=0;i<intIndexList.size();i++) {
            long tvalue = this.intensityList.get(i);
            tvalue += Long.parseLong(arr[intIndexList.get(i)]);
            
            this.intensityList.set(i, tvalue);
            
        }
        
        pepCount++;
    }

    public int getPepCount() {
        return pepCount;
    }

    public void setPepCount(int pepCount) {
        this.pepCount = pepCount;
    }
    
    
    public void printIntensity() {
                
        System.out.print(this.getSequence() + "\n");
        System.out.print("Total intensity\t");
        for(int i=0;i<this.intensityList.size();i++) {
            System.out.print(this.intensityList.get(i) + "\t");            
        }
        System.out.println("");
        
        System.out.print("Average intensity\t");
        for(int i=0;i<this.intensityList.size();i++) {
            System.out.print(this.intensityList.get(i)/this.getPepCount() + "\t");            
        }
        System.out.println("");
        
    }

    public Set<String> getCsSet() {
        return csSet;
    }

    public void setCsSet(Set<String> csSet) {
        this.csSet = csSet;
    }

    public void addCs(String cs) {
        this.csSet.add(cs);
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }
    
    
}
