package com.ipa.ip2.api.util;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Amit on 03-01-2017.
 */
public class IDCompareGeneReader {
    public static Set<String> readGeneResult(String filename) throws IOException, Exception {
        Set<String> geneList = new HashSet<String>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filename));
            String eachLine;
            while ((eachLine = br.readLine()) != null) {
                if (eachLine.startsWith("PLine\tAccession")) {
                    break;
                }
            }
            String[] tmpArr = eachLine.split("\t");
            int descriptionIndex = -1;
            for (int i = 0; i < tmpArr.length; i++) {
                if (tmpArr[i].startsWith("description")) {
                    descriptionIndex = i;
                }
            }
            while ((eachLine = br.readLine()) != null) {
                if (!eachLine.startsWith("P\t")) {
                    continue;
                }
                String[] arr = eachLine.split("\t");
                String geneName = extractGeneName(arr[descriptionIndex]);
                if(!StringUtils.isBlank(geneName)){
                    geneList.add(geneName);
                }
            }

        } catch (Exception e) {
            Logger.getLogger(IDCompareGeneReader.class.getName()).log(Level.SEVERE, null, e);
            try {
                if (null != br) {
                    br.close();
                }
            } catch (IOException ie) {

            }
        }
        return geneList;
    }

    public static String extractGeneName (String descriptiveName) {
        String gene="";
        String[] arr = descriptiveName.split("GN=");
        if(arr.length<=1) return gene;

        String[] arr2 = arr[1].split(" ");

        gene = arr2[0];
        return gene;

    }
}
