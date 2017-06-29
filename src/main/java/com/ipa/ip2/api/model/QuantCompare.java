
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

/**
 *
 * @author Sung Kyu, Robin, Park
 * @email robinparky@yahoo.com
 * Created on Aug 22, 2008 
 * $Revision: 1.1.1.1 $
 * $Date: 2010/12/19 23:44:05 $
 */
@Entity
@Table(name = "quant_compare")
public class QuantCompare implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "project_id")
    private Integer projectId;
    @Column(name = "quantitation_ids", nullable = false)
    private String quantitationIds;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "qc_level")
    private String level;
    @Column(name = "path")
    private String path;
    @Column(name = "qc_date")
    private Date date;


    public QuantCompare() {
    }

    public QuantCompare(Integer id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public Integer getId() {
	return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
	this.id = id;
    }

    /**
     * @return the quantitationIds
     */
    public String getQuantitationIds() {
	return quantitationIds;
    }

    /**
     * @param quantitationIds the quantitationIds to set
     */
    public void setQuantitationIds(String quantitationIds) {
	this.quantitationIds = quantitationIds;
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * @return the type
     */
    public String getType() {
	return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
	this.type = type;
    }

    /**
     * @return the level 
     */
    public String getLevel() {
	return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(String level) {
	this.level = level;
    }

    /**
     * @return the path
     */
    public String getPath() {
        if(!StringUtils.isBlank(path)){
            try {
                return Paths.get(HibernateUtils.getInstance().getRelativePath(), path).toString();
            } catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
	this.path = path;
    }

    /**
     * @return the date
     */
    public Date getDate() {
	return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
	this.date = date;
    }

    /**
     * @return the projectId
     */
    public Integer getProjectId() {
	return projectId;
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(Integer projectId) {
	this.projectId = projectId;
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
        if (!(object instanceof QuantCompare)) {
            return false;
        }
        QuantCompare other = (QuantCompare) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ipa.ip2.model.db.QuantCompare[id=" + id + "]";
    }

}
