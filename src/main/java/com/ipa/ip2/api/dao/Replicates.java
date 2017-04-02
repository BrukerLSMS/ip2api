package com.ipa.ip2.api.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Amit on 03-01-2017.
 */
public class Replicates {

    private String name;
//    private ArrayList<String> pathList;
//    private ArrayList<Integer> searchIdList;

    private List<Replicate> replicateList;

    private int[] columnIndexArr;
    private int[] regIndexArr;

    private String tandemMass;
    List<String> tandemMassArrList=new ArrayList<String>();

    public Replicates() {
//	pathList = new ArrayList<String>();
//	searchIdList = new ArrayList<Integer>();
        replicateList = new ArrayList<Replicate>();
    }

    public void addReplicate(Replicate replicate) {
        getReplicateList().add(replicate);
    }


    /*
    public void addPath(String path) {
	this.pathList.add(path);
    }

    public void addSearchId(Integer searchId) {
	this.searchIdList.add(searchId);
    }
    */

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public int[] getColumnIndexArr() {
        return columnIndexArr;
    }

    public void setColumnIndexArr(int[] columnIndexArr) {
        this.columnIndexArr = columnIndexArr;
    }

    public String getTandemMass() {
        return tandemMass;
    }

    public void setTandemMass(String tandemMass) {
        this.tandemMass = tandemMass;
    }

    public int[] getRegIndexArr() {
        return regIndexArr;
    }

    public void setRegIndexArr(int[] regIndexArr) {
        this.regIndexArr = regIndexArr;
    }


    public void sort() {
        Collections.sort(this.replicateList);
    }

    public List<Replicate> getReplicateList() {
        return replicateList;
    }

    public void setReplicateList(List<Replicate> replicateList) {
        this.replicateList = replicateList;
    }

    public List<String> getTandemMassArrList() {
        return tandemMassArrList;
    }



    public void setTandemMassArrList(List<String> tandemMassArrList) {

        for(Iterator<String> itr=tandemMassArrList.iterator(); itr.hasNext(); ) {
            String each = itr.next();
            each = each.replace("norm_median", "");
            each = each.replace("norm_average", "");
            each = each.replace("mz", "");
            each = each.trim();

            this.tandemMassArrList.add(each);
        }
        //this.tandemMassArrList = tandemMassArrList;
    }
}
