package com.ipa.ip2.api.util;

import com.ipa.ip2.api.model.MspExperiment;

/**
 * Created by amit on 15/02/17.
 */
public class PathUtil {
    public static String getQuantPath(MspExperiment exp) {

        return null;
    }

    public static String replaceSpaceForPath(String path) {
        path = path.replaceAll(" ", "_");
        path = path.replaceAll("-", "_");

        return path;
    }

    public static String replaceSpecialCharsForPath(String path) {
        path = path.replaceAll(" ", "_");
        path = path.replaceAll("-", "_");
        path = path.replaceAll("\\.", "_");

        String pattern = "[^a-zA-Z0-9_]";
        String newStr = path.replaceAll(pattern, "");


        return newStr;
    }

    public static String getContextPath(String absPath) {
        /*String contextPath = absPath.substring( absPath.indexOf(Configuration.getDataHome()) + Configuration.getDataHome().length() );
        if(!contextPath.startsWith(File.separator))
            contextPath = File.separator + contextPath;

        contextPath = Configuration.getContext() + "/ip2_data" + contextPath;
        if(!contextPath.endsWith(File.separator))
            contextPath += File.separator;

        return contextPath;*/
        return null;

    }
}
