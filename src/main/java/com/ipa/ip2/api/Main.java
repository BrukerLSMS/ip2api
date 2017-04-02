package com.ipa.ip2.api;

import com.ipa.ip2.api.model.*;
import com.ipa.ip2.api.reader.LabelFreeStatReader;
import com.ipa.ip2.api.reader.QuantStatReader;
import com.ipa.ip2.api.service.*;
import com.ipa.ip2.api.reader.TandemQuantReader;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        for( Project project : ProjectService.getAllProjects("rpark")){
            List<Quantitation> labelfreeList = QuantitationService.getAllLabelfree(project.getId());
            for(Quantitation labelfree : labelfreeList){
                if(labelfree.isVerified()) {
                    System.out.println(labelfree.getMspExperimentIds());
                    for( MspExperiment exp : ExperimentService.getAllExperiments(labelfree.getMspExperimentIds())){
                        System.out.println(exp.getId());
                    }
                    LabelFreeStatReader labelFreeStatReader = new LabelFreeStatReader(labelfree.getCensusFilePath());
                    System.out.println(labelFreeStatReader.getPlines().size());
                }
            }
        }

        /*for( Project project : ProjectService.getAllProjects("rpark")){
            List<QuantCompare> annovaCompareList = QuantCompareService.getAllANOVACompare(project.getId());
            for(QuantCompare quantCompare : annovaCompareList){
                for( MspExperiment exp : ExperimentService.getAllExperimentsForQuantitationIds(quantCompare.getQuantitationIds())){
                    System.out.println(exp.getId());
                }
                QuantStatReader reader = new QuantStatReader(quantCompare.getId(), quantCompare.getPath());
                System.out.println(reader.getQuantAnovaCompares().size());
            }
        }*/
    }
}

