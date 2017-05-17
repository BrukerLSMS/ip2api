package com.ipa.ip2.api.reader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.ipa.ip2.api.dao.QuantAnovaCompare;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amit on 20/02/17.
 */
public class QuantStatReader {

    List<QuantAnovaCompare> quantAnovaCompares = new ArrayList<QuantAnovaCompare>();

    /**
     * Current JSON file.
     */
    private String file = null;

    public QuantStatReader(int qid, String file) throws Exception {
        File qpathFile = new File(file);
        if (qpathFile == null) {
            System.err.println("Path not found for " + qid);
        } else {
            this.file = qpathFile.getParent() + File.separator + "compare_anova" + qid + ".JSON";
            System.out.println(this.file);
            File jsonFile = new File(this.file);
            if (jsonFile.exists()){
                parse();
            } else {
                System.err.println("AVOVA Json file not exists for " + qid);
            }
        }
    }

    /**
     * This will parse the JSON file.
     */
    @SuppressWarnings("resource")
    private void parse() throws Exception{
        Gson g = new Gson();
        JsonReader reader = new JsonReader(new FileReader(file));
        QuantAnovaCompareWrapper response = g.fromJson(reader, QuantAnovaCompareWrapper.class);
        quantAnovaCompares.clear();
        quantAnovaCompares.addAll(response.rows);
    }

    class QuantAnovaCompareWrapper{
        List<QuantAnovaCompare> rows;
    }

    public List<QuantAnovaCompare> getQuantAnovaCompares() {
        return quantAnovaCompares;
    }

    public void setQuantAnovaCompares(List<QuantAnovaCompare> quantAnovaCompares) {
        this.quantAnovaCompares = quantAnovaCompares;
    }
}
