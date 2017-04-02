package com.ipa.ip2.api.util;

/**
 * Protein Utils
 * @author Amit
 * @version 1.0
 */
public class ProteinUtils {
    public static String removeRef(String gene) {
        int index = gene.indexOf("-Ref");
        if(index<=0) return gene;
        return gene.substring(0,index);
    }
}
