/*
 * Formatter.java
 *
 * Created on January 2, 2007, 10:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.ipa.ip2.api.util;


import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.NumberFormat;

/**
 *
 * @author rpark
 */
public class Formatter {
    
    /** Creates a new instance of Formatter */
    private Formatter() {
    }
    
    private static DecimalFormat format = new DecimalFormat("0.00");    
    private static DecimalFormat format5 = new DecimalFormat("0.00000");    
    private static DecimalFormat sciformat =  new DecimalFormat("0.###E0");

    private static DecimalFormat threeDigitFormat =  new DecimalFormat("#.###");

    public static String halfRoundUpThreeDigitFormat(double d) {
        threeDigitFormat.setRoundingMode(RoundingMode.HALF_UP);

        return threeDigitFormat.format(d);
    }

    public static String threeDigitFormat(String s) {

        if(Character.isDigit(s.charAt(0)))

            return threeDigitFormat.format( Double.parseDouble(s) );
        else
            return s;
    }


    public static String formatDecimal(double d)
    {
        return format.format(d);
    }
    
    public static String formatDecimal5(double d)
    {
        return format5.format(d);
    }

    public static double sciRound(double d){
        return Double.parseDouble(sciformat.format(d));      
    }
 
    public static double round(double rval, int rpl) {
        double p = (double)Math.pow(10,rpl);
        rval = rval * p;
        double tmp = Math.round(rval);

        return (double)tmp/p;
    }

    public static void main(String[] args) {

        System.out.println(Formatter.threeDigitFormat("0.33857380385278013"));

        System.out.println(round(321.437732, 3)

        );
    }
}
