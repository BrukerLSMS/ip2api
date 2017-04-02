package com.ipa.ip2.api.service;

import com.ipa.ip2.api.db.HibernateUtils;
import com.ipa.ip2.api.exception.APIException;
import com.ipa.ip2.api.model.Project;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amit on 17/02/17.
 */
public class ProjectService {

    public static List<Project> getAllProjects(String username) throws APIException{
        if(StringUtils.isEmpty(username)){
            throw new APIException("Username missing!");
        }
        List<Project> projectList = new ArrayList<Project>();
        Session session = null;
        try {
            session = HibernateUtils.getInstance().getSessionFactory().openSession();
            projectList = session.createQuery("SELECT p FROM Project p, ProjectHasUsers pu, User u where u.username='" + username + "' and pu.usersId = u.id and p.id=pu.projectId").list();
        } catch(Exception e) {
            throw new APIException(e.getMessage());
        } finally {
          if(session != null){
              session.close();
          }
        }
        return projectList;
    }

    public static Project getProject(Integer id) throws APIException{
        Project project = null;
        Session session = null;
        try {
            session = HibernateUtils.getInstance().getSessionFactory().openSession();
            project = (Project) session.createQuery("SELECT p FROM Project p WHERE p.id=" + id).uniqueResult();
        } catch(Exception e) {
            throw new APIException(e.getMessage());
        } finally {
            if(session != null){
                session.close();
            }
        }
        return project;
    }
}
