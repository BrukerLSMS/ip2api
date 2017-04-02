package com.ipa.ip2.api.service;

import com.ipa.ip2.api.db.HibernateUtils;
import com.ipa.ip2.api.exception.APIException;
import com.ipa.ip2.api.model.MspExperiment;
import com.ipa.ip2.api.model.Quantitation;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amit on 17/02/17.
 */
public class QuantitationService {

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
}
