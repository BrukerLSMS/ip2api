package com.ipa.ip2.api.samples;

import com.ipa.ip2.api.dao.CensusOutExportJsonBo;
import com.ipa.ip2.api.db.HibernateUtils;
import com.ipa.ip2.api.model.MspExperiment;
import com.ipa.ip2.api.model.Project;
import com.ipa.ip2.api.model.Quantitation;
import com.ipa.ip2.api.reader.LabelFreeStatReader;
import com.ipa.ip2.api.service.ExperimentService;
import com.ipa.ip2.api.service.ProjectService;
import com.ipa.ip2.api.service.QuantitationService;

import java.io.File;
import java.util.List;

public class BasicExample {

    public static void main(String[] args) throws Exception {

        // Retrieve the list of projects by specific username

        ProjectService.getAllProjects("ip2test");

        // Retrieve the database relative path specified in jdbc.properties file

        System.out.println(HibernateUtils.getInstance().getRelativePath());

        String username = "ip2test";
        // Traverse all projects by username
        for( Project project : ProjectService.getAllProjects(username)){

            // Retrieve the project name
            System.out.println("Project name -> " + project.getName());

            // Retrieve all the exported labelfree
            List<Quantitation> exportedLabelfreeList = QuantitationService.getAllExportedLabelfree(project.getId());

            // Retrieve all the verified labelfree
            List<Quantitation> verifiedLabelfreeList = QuantitationService.getAllVerifiedLabelfree(project.getId());

            // Retrieve all the exported TMT
            List<Quantitation> exportedTMTList = QuantitationService.getAllExportedTMT(project.getId());

            // Retrieve all the labelfree
            List<Quantitation> labelfreeList = QuantitationService.getAllLabelfree(project.getId());
            for(Quantitation labelfree : labelfreeList){
                // Check the labelfree is verified or not? This is not required when you get all label free using getAllVerifiedLabelfree() method.
                if(labelfree.isVerified()) {

                    //Retrieve list of experiment Ids associated with labelfree
                    System.out.println(labelfree.getMspExperimentIds());

                    // Retrieve all experiments using Ids.
                    List<MspExperiment> exps = ExperimentService.getAllExperiments(labelfree.getMspExperimentIds());

                    for( MspExperiment exp : exps){
                        // Retrieve experiment id
                        System.out.println(exp.getId());
                        // Retrieve experiment sample name
                        System.out.println(exp.getSampleName());
                        // Retrieve experiment created date
                        System.out.println(exp.getCreatedDate());
                        // Retrieve list of search for experiment
                        System.out.println(exp.getDbSearch());
                        // Retrieve experiment instrument name
                        System.out.println(exp.getInstrumentName());
                        // For more methods please check MspExperiment javadoc (class)
                    }


                    // labelfree.getCensusFilePath() is exists or size is greater than 0)
                    File censusFile = new File(labelfree.getCensusFilePath());
                    if(censusFile.exists() && censusFile.length() > 0) {
                        // Read labelfree -- NOTE (Please make sure file exists or size is greater than 0)
                        LabelFreeStatReader labelFreeStatReader = new LabelFreeStatReader(labelfree.getCensusFilePath());
                        System.out.println(labelFreeStatReader.getPlines().size());
                    }
                }
                // If you fetch all lable free and you manually want to check whether that label free is exported or not then you can use following method.
                if(labelfree.getQuantitationFlag() != null && labelfree.getQuantitationFlag().equals("EXP")){
                    // Retrieve exported path
                    System.out.println("EXPORTED PATH >>>> " + labelfree.getExportPath());

                    // Parse the exported JSON
                    CensusOutExportJsonBo censusOutExportJsonBo = labelfree.parseExportedFile();
                    if(censusOutExportJsonBo != null) {
                        // Please read CensusOutExportJsonBo class properties for more available methods.
                        System.out.println(censusOutExportJsonBo.getExperimentId());
                        System.out.println(censusOutExportJsonBo.getSampleName());
                    }
                }
            }
        }
    }
}

