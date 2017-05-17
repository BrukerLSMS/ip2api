package com.ipa.ip2.api.util;

import com.ipa.ip2.api.util.seq.Fasta;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by amit on 15/02/17.
 */

public class DatabaseUtil {


    public static boolean isUniprotDb(String sequestParam) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(sequestParam));
            String eachLine;
            while( null != (eachLine=br.readLine()) ) {
                if(eachLine.startsWith("database_name")) break;
            }

            if(eachLine == null) return false;

            if(eachLine.toLowerCase().contains("uniprot")) return true;
            else return false;

        } catch(Exception e) {
            System.out.println("" + e);
        }

        return false;
    }

    public static String getProteinSequence(String fastaInputFile, String accession) throws Exception {
        File fastaFile = new File(fastaInputFile);
        BufferedReader br = null;
        String eachLine;
        String proteinSeq;
        File fastaIndexFile = new File(fastaFile + ".index");
        br = new BufferedReader(new FileReader(fastaFile + ".index"));
        int start = 0;
        int end = 1;
        while(null != (eachLine = br.readLine())){

            String[] ss = eachLine.split("\t");

            if(Fasta.getAccession(ss[0]).equals(accession)) {

                start = Integer.parseInt(ss[ss.length-1]);
                eachLine = br.readLine();
                if(eachLine == null)
                {
                    end = (int) fastaFile.length();
                }
                else{
                    ss = eachLine.split("\t");
                    //end = Integer.parseInt(ss[1]);
                    end = Integer.parseInt(ss[ss.length-1]);
                }

                break;
            }
        }
        br.close();

        RandomAccessFile rfile = new RandomAccessFile(fastaFile,"r");
        rfile.seek(start);


//                                                if(eachLine == null) {
        //                                                      byteSize = (int)(rfile.length() - start);
        //                                            } else {
        //                                                  eachLine = br.readLine();
        //                                                if(eachLine == null)
        //                                                    byteSize = (int)(rfile.length() - start);
        //                                              else {

//                                                                String[] nextArr = eachLine.split("\t");
        int byteSize = (int)(end - start);

//                                                byte[] bytes = new byte[byteSize];
        byte[] sequenceBytes = new byte[byteSize];
        rfile.readFully(sequenceBytes);
        rfile.close();



        proteinSeq = new String(sequenceBytes);

//System.out.println("====11\t"+ access + "\n" + proteinSeq);
        if(proteinSeq.indexOf("\n")<0)
            proteinSeq = "\n";
        proteinSeq = proteinSeq.substring(proteinSeq.indexOf("\n"));
//						proteinSeq = proteinSeq.replace("\n","");
        Pattern p = Pattern.compile("\\s*|\r|\n");
        Matcher m = p.matcher(proteinSeq);
        proteinSeq = m.replaceAll("");



        return proteinSeq.toUpperCase();
    }

}
