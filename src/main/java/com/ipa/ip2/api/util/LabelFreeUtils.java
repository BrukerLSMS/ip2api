package com.ipa.ip2.api.util;

/**
 * Label free utils.
 * @author Amit
 * @version 1.0
 */
public class LabelFreeUtils {
    /**
     * Extract the gene name from the provided string
     * @param descriptiveName
     * @return
     */
    public static String extractGeneName (String descriptiveName) {
        String gene="";
        String[] arr = descriptiveName.split("GN=");
        if(arr.length<=1){
            return gene;
        }
        String[] arr2 = arr[1].split(" ");
        gene = arr2[0];
        return gene;
    }
}
