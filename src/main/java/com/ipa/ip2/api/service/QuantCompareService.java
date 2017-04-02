package com.ipa.ip2.api.service;

import com.ipa.ip2.api.db.HibernateUtils;
import com.ipa.ip2.api.exception.APIException;
import com.ipa.ip2.api.model.QuantCompare;
import com.ipa.ip2.api.model.Quantitation;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amit on 17/02/17.
 */
public class QuantCompareService {

    public static List<QuantCompare> getAllANOVACompare(Integer projectId) throws APIException {
        List<QuantCompare> quantCompareList = new ArrayList<QuantCompare>();
        Session session = null;
        try {
            session = HibernateUtils.getInstance().getSessionFactory().openSession();
            quantCompareList = session.createQuery("SELECT q FROM QuantCompare q where q.projectId=" + projectId).list();
        } catch(Exception e) {
            throw new APIException(e.getMessage());
        } finally {
            if(session != null){
                session.close();
            }
        }
        return quantCompareList;
    }

    public static QuantCompare getQuantCompare(Integer id) throws APIException{
        QuantCompare quantCompare = null;
        Session session = null;
        try {
            session = HibernateUtils.getInstance().getSessionFactory().openSession();
            quantCompare = (QuantCompare) session.createQuery("SELECT q FROM QuantCompare q WHERE q.id=" + id).uniqueResult();
        } catch(Exception e) {
            throw new APIException(e.getMessage());
        } finally {
            if(session != null){
                session.close();
            }
        }
        return quantCompare;
    }
}
