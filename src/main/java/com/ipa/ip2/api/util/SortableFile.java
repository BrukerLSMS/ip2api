package com.ipa.ip2.api.util;

import java.io.File;

/**
 * Created by amit on 17/02/17.
 */
public class SortableFile implements Comparable {
    private String fileName;
    private long fileSize;
    public SortableFile(String file) {
        fileName = file;
        fileSize = new File(file).length();

    }

    public String getFileName() {
        return fileName;
    }
    public long getFileSize() {
        return fileSize;
    }

    public int compareTo(Object obj) {
        SortableFile sf = (SortableFile) obj;

        long diff = fileSize - sf.fileSize;
        if( diff == 0 ) {
            return 0;
        } else if(diff < 0) {
            return -1;

        } else {
            return 1;
        }
    }
}
