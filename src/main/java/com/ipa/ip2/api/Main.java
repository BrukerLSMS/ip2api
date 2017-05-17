package com.ipa.ip2.api;

import com.ipa.ip2.api.model.MspExperiment;
import com.ipa.ip2.api.model.Project;
import com.ipa.ip2.api.model.Quantitation;
import com.ipa.ip2.api.reader.LabelFreeStatReader;
import com.ipa.ip2.api.service.ExperimentService;
import com.ipa.ip2.api.service.ProjectService;
import com.ipa.ip2.api.service.QuantitationService;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        // 1) Retrieve the list of projects by specific username

        ProjectService.getAllProjects("ip2test");

        // 2)

        for( Project project : ProjectService.getAllProjects("ip2test")){
            System.out.println("Project name -> " + project.getName());
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

        /*for( Project project : ProjectService.getAllProjects("ip2test")){
            List<QuantCompare> annovaCompareList = QuantCompareService.list(project.getId());
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

