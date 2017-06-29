package com.ipa.ip2.api.samples;

import com.ipa.ip2.api.db.HibernateUtils;
import com.ipa.ip2.api.model.Project;
import com.ipa.ip2.api.model.Quantitation;
import com.ipa.ip2.api.service.ProjectService;
import com.ipa.ip2.api.service.QuantitationService;

import java.util.List;

public class ExportLabelfree {

    public static void main(String[] args) throws Exception {

        // Retrieve the list of projects by specific username

        ProjectService.getAllProjects("ip2test");

        // Retrieve the database relative path specified in jdbc.properties file

        System.out.println(HibernateUtils.getInstance().getRelativePath());
        System.out.println(HibernateUtils.getInstance().getIp2HomePath());

        String username = "ip2test";
        // Traverse all projects by username
        for( Project project : ProjectService.getAllProjects(username)){

            // Retrieve the project name
            System.out.println("Project name -> " + project.getName());

            // Retrieve all the verified labelfree
            List<Quantitation> verifiedLabelfreeList = QuantitationService.getAllVerifiedLabelfree(project.getId());

            for(Quantitation labelfree : verifiedLabelfreeList){

                // Export the labelfree only then..
                QuantitationService.exportLabelfree(labelfree, username);
            }
        }
    }
}

