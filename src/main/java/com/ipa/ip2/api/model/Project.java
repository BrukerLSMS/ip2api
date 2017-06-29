/*
* Copyright (c) 2008 Proteomics Integrated Solutions. All rights reserved.
*/
package com.ipa.ip2.api.model;

import com.ipa.ip2.api.db.HibernateUtils;
import com.ipa.ip2.api.util.OsCheck;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.Date;

@Entity
@Table(name = "project")
@NamedQueries({@NamedQuery(name = "Project.findById", query = "SELECT p FROM Project p WHERE p.id = :id"), @NamedQuery(name = "Project.findByName", query = "SELECT p FROM Project p WHERE p.name = :name"), @NamedQuery(name = "Project.findByHomeFolder", query = "SELECT p FROM Project p WHERE p.homeFolder = :homeFolder"), @NamedQuery(name = "Project.findByCreatedDate", query = "SELECT p FROM Project p WHERE p.createdDate = :createdDate")})
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Lob
    @Column(name = "description")
    private String description;
    @Column(name = "home_folder")
    private String homeFolder;
    @Lob
    @Column(name = "progress_status")
    private String progressStatus;
    @Column(name = "quant_type")
    private String quantType;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Transient
    private String viewExperimentLink;
    @Transient
    private long projectSharedCount;
    @Transient
    private boolean sharedWithMe;
    @Transient
    private boolean fullaccess;

    public Project() {
    }

    public Project(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHomeFolder() {
        if(!StringUtils.isBlank(homeFolder)){
            try {
                return Paths.get(HibernateUtils.getInstance().getRelativePath(), homeFolder).toString();
            } catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
        return homeFolder;
    }

    public void setHomeFolder(String homeFolder) {
        this.homeFolder = homeFolder;
    }

    public String getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(String progressStatus) {
        this.progressStatus = progressStatus;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    public String getQuantType() {
        return quantType;
    }

    public void setQuantType(String quantType) {
        this.quantType = quantType;
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
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ipa.ip2.model.db.Project[id=" + id + "]";
    }

    public String getViewExperimentLink() {
        return viewExperimentLink;
    }

    public void setViewExperimentLink(String viewExperimentLink) {
        this.viewExperimentLink = viewExperimentLink;
    }

    public long getProjectSharedCount() {
        return projectSharedCount;
    }

    public void setProjectSharedCount(long projectSharedCount) {
        this.projectSharedCount = projectSharedCount;
    }

    public boolean isSharedWithMe() {
        return sharedWithMe;
    }

    public void setSharedWithMe(boolean sharedWithMe) {
        this.sharedWithMe = sharedWithMe;
    }

    public boolean isFullaccess() {
        return fullaccess;
    }

    public void setFullaccess(boolean fullaccess) {
        this.fullaccess = fullaccess;
    }


}
