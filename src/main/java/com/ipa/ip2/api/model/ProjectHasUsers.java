
/*
* Copyright (c) 2008 Proteomics Integrated Solutions. All rights reserved.  
*/

package com.ipa.ip2.api.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "project_has_users")
@NamedQueries({@NamedQuery(name = "ProjectHasUsers.findById", query = "SELECT p FROM ProjectHasUsers p WHERE p.id = :id"), @NamedQuery(name = "ProjectHasUsers.findByUsersId", query = "SELECT p FROM ProjectHasUsers p WHERE p.usersId = :usersId"), @NamedQuery(name = "ProjectHasUsers.findByProjectId", query = "SELECT p FROM ProjectHasUsers p WHERE p.projectId = :projectId"), @NamedQuery(name = "ProjectHasUsers.findByIsLeader", query = "SELECT p FROM ProjectHasUsers p WHERE p.isLeader = :isLeader")})
public class ProjectHasUsers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "users_id", nullable = false)
    private int usersId;
    @Column(name = "project_id", nullable = false)
    private int projectId;
    @Column(name = "is_leader")
    private Boolean isLeader;

    public ProjectHasUsers() {
    }

    public ProjectHasUsers(Integer id) {
        this.id = id;
    }

    public ProjectHasUsers(Integer id, int usersId, int projectId) {
        this.id = id;
        this.usersId = usersId;
        this.projectId = projectId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Boolean getIsLeader() {
        return isLeader;
    }

    public void setIsLeader(Boolean isLeader) {
        this.isLeader = isLeader;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectHasUsers)) {
            return false;
        }
        ProjectHasUsers other = (ProjectHasUsers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ipa.ip2.model.db.ProjectHasUsers[id=" + id + "]";
    }

}
