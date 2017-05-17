package com.ipa.ip2.api.util.seq;


import java.io.*;
import java.util.*;


/**
 * Created by amit on 15/02/17.
 */
public class FastaReader
{

    public static final char FIRSTCHAROFDEFLINE = '>';
    public static final int DEFAULTSEQENCELENGTH = 1000;

    // Becareful, might need lots of memory
    public static List <Fasta> getFastaList(InputStream is) throws IOException {
        List fastaList = new LinkedList();
        for (Iterator <Fasta> fastas = getFastas(is); fastas.hasNext();) {
            fastaList.add(fastas.next());
        }
        return fastaList;
    }
    public static Iterator<Fasta> getFastas(String fastaFileName) throws IOException {
        FileInputStream fis = new FileInputStream(fastaFileName);
        System.out.println("==========Iterator====="+fastaFileName);
        return getFastas(fis);
    }
    public static Iterator <Fasta> getFastas(final InputStream is) throws IOException {
        return new Iterator() {

            private String lastLine = ""; // remember the last line read
            private BufferedReader br;

            {
                br = new BufferedReader(new InputStreamReader(is));
                // remove the potential empty lines and get the first defline
                while ((lastLine = br.readLine()) != null && lastLine.equals(""));
                if (lastLine.charAt(0) != FIRSTCHAROFDEFLINE) {
                    throw new IOException("File Format Unknown Exception");
                }
            }

            public boolean hasNext() {
                return lastLine != null;
            }

            public Object next() {

                Fasta fasta = null;
                try {
                    fasta = getFasta();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                return fasta;
            }

            public void remove() {
                throw new UnsupportedOperationException("Not supported");
            }

            private Fasta getFasta() throws IOException {

                StringBuffer sb = new StringBuffer(DEFAULTSEQENCELENGTH);
                String defline = lastLine;

                // if the line read is a empty string, ignore it
                while ( (lastLine = br.readLine()) != null && (lastLine.equals("")
                        || lastLine.charAt(0) != FIRSTCHAROFDEFLINE)) {
                    //System.out.println(lastLine);
                    if (!lastLine.equals("")) {
                        String line = lastLine.trim();
                        sb.append(line);
                    }
                }

                // the lastLine should be the defline
                // and sb.toString should be the sequence
                return new Fasta(defline, sb.toString());
            }

            protected void finalize() throws IOException {
                br.close();
                //System.out.println("Finalized");
            }
        };
    }


    public static void main(String args[]) throws IOException
    {
/*
        for (Iterator<Fasta> itr = FastaReader.getFastas(new FileInputStream(args[0])); itr.hasNext(); ) {
            Fasta fasta = itr.next();
            String defline = fasta.getDefline();
            if(defline.contains("Escherichia coli")) {
                System.out.println(">" + defline);
                System.out.println(fasta.getSequence());
            }
        }
*/

        Hashtable<String,String> ht = new Hashtable<String,String>();
        for (Iterator itr = FastaReader.getFastas(new FileInputStream("/home/rpark/ident.fasta")); itr.hasNext(); ) {
            Fasta fasta = (Fasta) itr.next();
            if(fasta.getAccession().startsWith("Reverse") || fasta.getAccession().startsWith("contaminant")) continue;


            //   System.out.println( (fasta.getAccession().startsWith("Reverse") || fasta.getAccession().startsWith("contaminant")) + " " + fasta.getAccession());

            ht.put(fasta.getDefline(), fasta.getSequence());

            //    System.out.println(fasta.getDefline());
            // Fasta ff = ht.get("Q9HCJ5");
            //if(null != ff)
            //   System.out.println("a==aaaaaaaaaaaaa" + ht.get("Q9HCJ5"));
        }

        //System.out.println(ht);


        //     System.out.println("a==aaaaaaaaaaaaa" + ht.get("Q9HCJ5"));

        //     if(true) return;

//        System.out.println("aaaaaaaaaaaaaa");
        /*for (Iterator itr = FastaReader.getFastas(new FileInputStream("/home/rpark/identified2.fasta")); itr.hasNext(); ) {
            Fasta fasta = (Fasta) itr.next();
            if(fasta.getAccession().startsWith("Reverse") || fasta.getAccession().startsWith("contaminant")) continue;

            ht.put(fasta.getDefline(), fasta.getSequence());

//            System.out.println(fasta.getDefline());


         //   System.out.println("a==aaaaaaaaaaaaa" + ht.get("O43670").getDefline());

        }*/

        Set<String> keys = ht.keySet();
        for(String str:keys) {
            //  Fasta f = ht.get(str);
            System.out.println(">" + str);
            System.out.println(ht.get(str));
        }

//System.out.println(ht.size());
        if(true) return;


        int numEntries = 0;
        HashSet<String> accessions = new HashSet<String>(1000000);
        HashSet<String> sequestLikeAccs = new HashSet<String>(1000000);
        for (Iterator itr = FastaReader.getFastas(new FileInputStream(args[0])); itr.hasNext(); ) {
            Fasta fasta = (Fasta) itr.next();
//System.out.println(fasta.getSequestLikeAccession());
            numEntries++;

            String defLine = fasta.getDefline();
            String seq = fasta.getSequence();


            accessions.add(fasta.getAccession());
            String sequestlikeac = fasta.getSequestLikeAccession();
            if(sequestlikeac.length() > 40) {
                sequestlikeac = fasta.getSequestLikeAccession().substring(0, 41);
            }
            sequestLikeAccs.add(sequestlikeac);
        }

        System.out.println("In fasta file " + args[0] + ":");
        System.out.println("Number of protein entries: " + numEntries);
        System.out.println("Number of unique accessions: " + accessions.size());
        System.out.println("Number of unique SEQUEST like accessions: " + sequestLikeAccs.size());


/*
        for (Iterator itr = FastaReader.getFastas(new FileInputStream(args[0])); itr.hasNext(); ) {
            Fasta fasta = (Fasta) itr.next();
            String defLine = fasta.getDefline();
            String seq = fasta.getSequence();
            if(defLine.startsWith("Rever")) {
                //System.out.println("Reversed: " + defLine);
                if(seq.endsWith("M")) {
                    seq = seq.substring(0, seq.length() -1);
                }
            } else {
                if(seq.startsWith("M")) {
                    seq = seq.substring(1, seq.length());

                }
                //System.out.println("Regular: " + defLine);
            }

           System.out.println(">" + defLine);
           System.out.println(seq);
        }
  */

    }
}
