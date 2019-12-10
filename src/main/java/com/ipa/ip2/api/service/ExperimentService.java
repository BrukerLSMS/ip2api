package com.ipa.ip2.api.service;

import com.ipa.ip2.api.db.HibernateUtils;
import com.ipa.ip2.api.exception.APIException;
import com.ipa.ip2.api.model.DbSearch;
import com.ipa.ip2.api.model.MspExperiment;
import com.ipa.ip2.api.model.Quantitation;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by amit on 17/02/17.
 */
public class ExperimentService {

    /**
     * Return list of experiments for specific project.
     * @param projectId
     * @return
     * @throws APIException
     */
    public static List<MspExperiment> getAllExperiments(Integer projectId) throws APIException {
        List<MspExperiment> mspExperiments = new ArrayList<MspExperiment>();
        Session session = null;
        try {
            session = HibernateUtils.getInstance().getSessionFactory().openSession();
            
            mspExperiments = session.createQuery("SELECT e FROM MspExperiment e where e.projectId=" + projectId).list();
            
            Criteria dbcrit = null;
            
            List<DbSearch> searchList = null;
            
            
            for(MspExperiment eachExperiemnt : mspExperiments) {
            	
            	dbcrit = session.createCriteria(DbSearch.class).add(Restrictions.eq("mspExperimentId",eachExperiemnt.getId()));
            	
            	searchList = (List<DbSearch>) dbcrit.list();
            	
            	eachExperiemnt.setDbSearch(searchList);
            }
            
        } catch(Exception e) {
            throw new APIException(e.getMessage());
        } finally {
            if(session != null){
                session.close();
            }
        }
        return mspExperiments;
    }

    /**
     * Return Exepriment based on the id.
     * @param id
     * @return
     * @throws APIException
     */
	public static MspExperiment get(Integer id) throws APIException {
		MspExperiment experiment = null;
		Session session = null;
		try {
			session = HibernateUtils.getInstance().getSessionFactory().openSession();
			Criteria expcrit = session.createCriteria(MspExperiment.class).add(Restrictions.eq("id", id));
			// experiment = (MspExperiment) session.createQuery("SELECT e FROM MspExperiment
			// e WHERE e.id=" + id).uniqueResult();
			experiment = (MspExperiment) expcrit.uniqueResult();

			Criteria dbcrit = null;

			List<DbSearch> searchList = null;

			dbcrit = session.createCriteria(DbSearch.class).add(Restrictions.eq("mspExperimentId", experiment.getId()));

			searchList = (List<DbSearch>) dbcrit.list();

			experiment.setDbSearch(searchList);

			Query query = null;

			for (Iterator<DbSearch> sitr = searchList.iterator(); sitr.hasNext();) {

				DbSearch search = sitr.next();

				query = session.createQuery("select count(*) from Quantitation q where q.mspExperimentIds=:expId");

				query.setString("expId", String.valueOf(experiment.getId()));

				if (query.list().size() > 0) {
					search.setIsQuant(true);
				}

				query = session.createQuery("from Quantitation q where q.dbSearchIds=:dsId order by q.id desc");
				query.setString("dsId", String.valueOf(search.getId()));
				List qList = query.list();

				if (search.isIsQuant() && qList.size() > 0) {

					try {
						Quantitation quantitation = (Quantitation) qList.get(0);

						if (quantitation != null && quantitation.getIsFinished()) {
							search.setIsLabeledQuantitationDone(new Boolean(true));
						}

						search.setQuantitation(quantitation);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}

		} catch (Exception e) {
			throw new APIException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return experiment;
	}

    /**
     * Return list of Experiment objects based on the provided quantitation ids. quantitationIds can be
     * multiple delimated by the space.
     * @param quantitationIds
     * @return
     * @throws APIException
     */
    public static List<MspExperiment> getAllExperimentsForQuantitationIds(String quantitationIds) throws APIException {
        List<MspExperiment> mspExperiments = new ArrayList<MspExperiment>();
        Set<String> expIds = new HashSet<String>();
        Session session = null;
        try {
            if(!StringUtils.isBlank(quantitationIds)) {
                for (String id : quantitationIds.split(" ")) {
                    if (!StringUtils.isBlank(id)) {
                        Quantitation quantitation = QuantitationService.get(id);
                        if (null != quantitation) {
                            String expPath = quantitation.getPath().split("/quant/")[0];
                            expIds.add(expPath.substring(expPath.lastIndexOf("_") + 1, expPath.length()));
                        }
                    }
                }
                for (String id : expIds) {
                    if (!StringUtils.isBlank(id)) {
                        MspExperiment exp = get(Integer.parseInt(id));
                        if (exp != null) {
                            mspExperiments.add(exp);
                        }
                    }
                }
            }
        } catch(Exception e) {
            throw new APIException(e.getMessage());
        } finally {
            if(session != null){
                session.close();
            }
        }
        return mspExperiments;
    }

    /**
     * Return list of Experiment objects based on the provided experiment ids. experimentIds can be
     * multiple delimated by the space.
     * @param experimentIds
     * @return
     * @throws APIException
     */
    public static List<MspExperiment> getAllExperiments(String experimentIds) throws APIException {
        List<MspExperiment> mspExperiments = new ArrayList<MspExperiment>();
        Set<String> expIds = new HashSet<String>();
        Session session = null;
        try {
            if(!StringUtils.isBlank(experimentIds)) {
                if(experimentIds.contains(",")) {
                    for (String id : experimentIds.split(",")) {
                        if (!StringUtils.isBlank(id)) {
                            expIds.add(id.trim());
                        }
                    }
                }else{
                    for (String id : experimentIds.split(" ")) {
                        if (!StringUtils.isBlank(id)) {
                            expIds.add(id.trim());
                        }
                    }
                }
                for (String id : expIds) {
                    if (!StringUtils.isBlank(id)) {
                        MspExperiment exp = get(Integer.parseInt(id));
                        if (exp != null) {
                            mspExperiments.add(exp);
                        }
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            throw new APIException(e.getMessage());
        } finally {
            if(session != null){
                session.close();
            }
        }
        return mspExperiments;
    }
}
