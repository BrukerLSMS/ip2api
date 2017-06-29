package com.ipa.ip2.api.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ipa.ip2.api.dao.CensusOutExportJsonBo;
import com.ipa.ip2.api.db.HibernateUtils;
import com.ipa.ip2.api.exception.APIException;
import com.ipa.ip2.api.model.MspExperiment;
import com.ipa.ip2.api.model.Project;
import com.ipa.ip2.api.model.Quantitation;
import com.ipa.ip2.api.util.FileUtil;
import com.ipa.ip2.api.util.census.CensusOutProteinPeptideGeneration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amit on 17/02/17.
 */
public class QuantitationService {

    /**
     * Return list of labelfree Quantitation for specified project id.
     * @param projectId
     * @return
     * @throws APIException
     */
    public static List<Quantitation> getAllLabelfree(Integer projectId) throws APIException {
        List<Quantitation> quantitationList = new ArrayList<Quantitation>();
        Session session = null;
        try {
            session = HibernateUtils.getInstance().getSessionFactory().openSession();
            quantitationList = session.createQuery("SELECT q FROM Quantitation q where q.quantitationType = 'labelfree' AND q.projectId=" + projectId).list();
        } catch(Exception e) {
            throw new APIException(e.getMessage());
        } finally {
            if(session != null){
                session.close();
            }
        }
        return quantitationList;
    }

    /**
     * Return list of exported labelfree Quantitation for specified project id.
     * @param projectId
     * @return
     * @throws APIException
     */
    public static List<Quantitation> getAllExportedLabelfree(Integer projectId) throws APIException {
        List<Quantitation> quantitationList = new ArrayList<Quantitation>();
        Session session = null;
        try {
            session = HibernateUtils.getInstance().getSessionFactory().openSession();
            quantitationList = session.createQuery("SELECT q FROM Quantitation q where q.exportPath IS NOT NULL AND q.quantitationType = 'labelfree' AND q.projectId=" + projectId).list();
        } catch(Exception e) {
            throw new APIException(e.getMessage());
        } finally {
            if(session != null){
                session.close();
            }
        }
        return quantitationList;
    }

    /**
     * Return list of exported TMT Quantitation for specified project id.
     * @param projectId
     * @return
     * @throws APIException
     */
    public static List<Quantitation> getAllExportedTMT(Integer projectId) throws APIException {
        List<Quantitation> quantitationList = new ArrayList<Quantitation>();
        Session session = null;
        try {
            session = HibernateUtils.getInstance().getSessionFactory().openSession();
            quantitationList = session.createQuery("SELECT q FROM Quantitation q where q.exportPath IS NOT NULL AND q.quantitationType = 'TMT' AND q.projectId=" + projectId).list();
        } catch(Exception e) {
            throw new APIException(e.getMessage());
        } finally {
            if(session != null){
                session.close();
            }
        }
        return quantitationList;
    }

    /**
     * Return list of verified labelfree Quantitation for specified project id.
     * @param projectId
     * @return
     * @throws APIException
     */
    public static List<Quantitation> getAllVerifiedLabelfree(Integer projectId) throws APIException {
        List<Quantitation> quantitationList = new ArrayList<Quantitation>();
        Session session = null;
        try {
            session = HibernateUtils.getInstance().getSessionFactory().openSession();
            quantitationList = session.createQuery("SELECT q FROM Quantitation q where q.verified = true AND q.quantitationType = 'labelfree' AND q.projectId=" + projectId).list();
        } catch(Exception e) {
            throw new APIException(e.getMessage());
        } finally {
            if(session != null){
                session.close();
            }
        }
        return quantitationList;
    }

    /**
     * Return list of all verified Quantitation for specified project id.
     * @param projectId
     * @return
     * @throws APIException
     */
    public static List<Quantitation> getAllVerified(Integer projectId) throws APIException {
        List<Quantitation> quantitationList = new ArrayList<Quantitation>();
        Session session = null;
        try {
            session = HibernateUtils.getInstance().getSessionFactory().openSession();
            quantitationList = session.createQuery("SELECT q FROM Quantitation q where q.verified = true AND q.projectId=" + projectId).list();
        } catch(Exception e) {
            throw new APIException(e.getMessage());
        } finally {
            if(session != null){
                session.close();
            }
        }
        return quantitationList;
    }

    /**
     * Return list of all exported Quantitation for specified project id.
     * @param projectId
     * @return
     * @throws APIException
     */
    public static List<Quantitation> getAllExported(Integer projectId) throws APIException {
        List<Quantitation> quantitationList = new ArrayList<Quantitation>();
        Session session = null;
        try {
            session = HibernateUtils.getInstance().getSessionFactory().openSession();
            quantitationList = session.createQuery("SELECT q FROM Quantitation q where q.exportPath IS NOT NULL  AND q.projectId=" + projectId).list();
        } catch(Exception e) {
            throw new APIException(e.getMessage());
        } finally {
            if(session != null){
                session.close();
            }
        }
        return quantitationList;
    }

    /**
     * Return list of all Quantitation for specified project id.
     * @param projectId
     * @return
     * @throws APIException
     */
    public static List<Quantitation> getAll(Integer projectId) throws APIException {
        List<Quantitation> quantitationList = new ArrayList<Quantitation>();
        Session session = null;
        try {
            session = HibernateUtils.getInstance().getSessionFactory().openSession();
            quantitationList = session.createQuery("SELECT q FROM Quantitation q where q.projectId=" + projectId).list();
        } catch(Exception e) {
            throw new APIException(e.getMessage());
        } finally {
            if(session != null){
                session.close();
            }
        }
        return quantitationList;
    }

    /**
     * Return list of SILAC Quantitation for specified project id.
     * @param projectId
     * @return
     * @throws APIException
     */
    public static List<Quantitation> getAllSILAC(Integer projectId) throws APIException {
        List<Quantitation> quantitationList = new ArrayList<Quantitation>();
        Session session = null;
        try {
            session = HibernateUtils.getInstance().getSessionFactory().openSession();
            quantitationList = session.createQuery("SELECT q FROM Quantitation q where q.quantitationType = 'SILAC' AND q.projectId=" + projectId).list();
        } catch(Exception e) {
            throw new APIException(e.getMessage());
        } finally {
            if(session != null){
                session.close();
            }
        }
        return quantitationList;
    }

    /**
     * Return list of N15 Quantitation for specified project id.
     * @param projectId
     * @return
     * @throws APIException
     */
    public static List<Quantitation> getAllN15(Integer projectId) throws APIException {
        List<Quantitation> quantitationList = new ArrayList<Quantitation>();
        Session session = null;
        try {
            session = HibernateUtils.getInstance().getSessionFactory().openSession();
            quantitationList = session.createQuery("SELECT q FROM Quantitation q where q.quantitationType = 'N15' AND q.projectId=" + projectId).list();
        } catch(Exception e) {
            throw new APIException(e.getMessage());
        } finally {
            if(session != null){
                session.close();
            }
        }
        return quantitationList;
    }

    /**
     * Return Quantitation for specified id.
     * @param id
     * @return
     * @throws APIException
     */
    public static Quantitation get(String id) throws APIException{
        Quantitation quantitation = null;
        Session session = null;
        try {
            session = HibernateUtils.getInstance().getSessionFactory().openSession();
            quantitation = (Quantitation) session.createQuery("SELECT q FROM Quantitation q WHERE q.id=" + id).uniqueResult();
        } catch(Exception e) {
            throw new APIException(e.getMessage());
        } finally {
            if(session != null){
                session.close();
            }
        }
        return quantitation;
    }

    /**
     * Export label free.
     * @param quantitation
     * @return
     * @throws APIException
     */
    public static void exportLabelfree(Quantitation quantitation, String username) throws APIException{

        Transaction tx = null;
        Session session = null;
        try {
            session = HibernateUtils.getInstance().getSessionFactory().openSession();
            tx = session.beginTransaction();

            String exportDir = Paths.get(HibernateUtils.getInstance().getIp2HomePath(), "exportdata", username, String.valueOf(quantitation.getProjectId()), "labelfree").toString();

            File statFile = null;
            File pepFile = null;

            File exportFile = null;
            File exportFile2 = null;

            CensusOutProteinPeptideGeneration censusOutProteinPeptideGeneration = new CensusOutProteinPeptideGeneration();

            String ip2DataPath = quantitation.getPath();

            String fileName = "census_labelfree_out_" + quantitation.getId() + "_stat.txt";
            String fileName2 = "census_labelfree_out_" + quantitation.getId() + "_pep.txt";

            System.out.println("Export Files ::"+ Paths.get(ip2DataPath, fileName).toString());
            System.out.println("Export Files ::"+ Paths.get(ip2DataPath, fileName2).toString());

            FileUtil.makeDir(exportDir);

            statFile = new File(Paths.get(ip2DataPath, fileName).toString());
            pepFile = new File(Paths.get(ip2DataPath, fileName).toString());

            if (statFile.exists() && pepFile.exists()) {

                exportFile = new File(Paths.get(exportDir, fileName).toString());
                exportFile2 = new File(Paths.get(exportDir, fileName2).toString());

                if (exportFile.exists()) {
                    exportFile.delete();
                }
                if (exportFile2.exists()) {
                    exportFile2.delete();
                }

                if (FileUtil.copyFile(ip2DataPath, fileName, exportDir) &&
                        FileUtil.copyFile(ip2DataPath, fileName2, exportDir)) {

                    String experimentIds = quantitation.getMspExperimentIds();
                    String sampleNames = "";
                    String expCreatedDate = "";
                    if(experimentIds != null){
                        String[] expIdArr = experimentIds.split(",");
                        for (String expId : expIdArr) {
                            MspExperiment experiment = getExperimentDetails(expId, session);
                            sampleNames = sampleNames + "," + experiment.getSampleName();
                            expCreatedDate = expCreatedDate + "," + experiment.getCreatedDate().toString();
                        }
                    }else{
                        experimentIds = "NA";
                    }

                    String jsonFile = Paths.get(exportDir, "census_labelfree_out_meta.JSON").toString();
                    Project project = getProjectDetails(quantitation.getProjectId(), session);

                    CensusOutExportJsonBo jsonDataObj = new CensusOutExportJsonBo();
                    jsonDataObj.setExperimentId(experimentIds);
                    jsonDataObj.setPid(project.getId().toString());
                    jsonDataObj.setQuantType("labelfree");
                    jsonDataObj.setProjectName(project.getName());
                    jsonDataObj.setQuantationId(quantitation.getId().toString());
                    jsonDataObj.setProjectCreatedDate(project.getCreatedDate().toString());
                    jsonDataObj.setExperimentCreatedDate(expCreatedDate);
                    jsonDataObj.setSampleName(sampleNames);

                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();

                    FileUtil.writeJSON(gson.toJson(jsonDataObj), jsonFile);

                    tx.begin();

                    String hql = "UPDATE Quantitation set quantitationFlag =:flag,exportPath =:quantExportPath WHERE id = :qunatId";
                    Query query = session.createQuery(hql);
                    query.setParameter("flag", "EXP");
                    query.setParameter("quantExportPath", exportDir);
                    query.setParameter("qunatId", quantitation.getId());
                    int result = query.executeUpdate();
                    System.out.println("Rows affected: " + result);

                    tx.commit();

                    censusOutProteinPeptideGeneration
                            .generateCensusProteinLabelfree(Paths.get(exportDir, fileName).toString(), quantitation.getId());
                    censusOutProteinPeptideGeneration
                            .generateCensusPeptideLabelfree(Paths.get(exportDir, fileName2).toString(), quantitation.getId());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Project getProjectDetails(int projectId, Session session) throws APIException{
        Project project = null;
        try {
            List<Project> projectList = session.createQuery(
                    "from Project prj " + "where prj.id=" + projectId )
                    .list();
            if(projectList.size() > 0){
                project = projectList.get(0);
            }
            return project;
        } catch (Exception e) {
            throw new APIException(e.getMessage());
        }
    }

    private static MspExperiment getExperimentDetails(String expId, Session session) throws APIException{
        MspExperiment  mspExperiment = null;
        try {
            List<MspExperiment> mspExperimentList = session.createQuery(
                    "from MspExperiment exp " + "where exp.id=" + expId)
                    .list();
            if(mspExperimentList.size() > 0){
                mspExperiment = mspExperimentList.get(0);
            }
            return mspExperiment;
        } catch (Exception e) {
            throw new APIException(e.getMessage());
        }
    }
}
