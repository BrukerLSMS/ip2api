package com.ipa.ip2.api.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;

/**
 * Created by amit on 15/02/17.
 */

@Entity
@Table(name = "result_selection")
@NamedQueries({@NamedQuery(name = "ResultSelection.findById", query = "SELECT r FROM ResultSelection r WHERE r.id = :id"), @NamedQuery(name = "ResultSelection.findByDbSearchId", query = "SELECT r FROM ResultSelection r WHERE r.dbSearchId = :dbSearchId"), @NamedQuery(name = "ResultSelection.findByNumRedundantProteins", query = "SELECT r FROM ResultSelection r WHERE r.numRedundantProteins = :numRedundantProteins"), @NamedQuery(name = "ResultSelection.findByNumNonredundantProteins", query = "SELECT r FROM ResultSelection r WHERE r.numNonredundantProteins = :numNonredundantProteins")})
public class ResultSelection implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "db_search_id", nullable = false)
    private int dbSearchId;
    @Lob
    @Column(name = "criteria")
    private String criteria;
    @Lob
    @Column(name = "result_summary")
    private String resultSummary;
    @Column(name = "num_redundant_proteins")
    private Integer numRedundantProteins;
    @Column(name = "num_nonredundant_proteins")
    private Integer numNonredundantProteins;

    public ResultSelection() {
    }

    public ResultSelection(Integer id) {
        this.id = id;
    }

    public ResultSelection(Integer id, int dbSearchId) {
        this.id = id;
        this.dbSearchId = dbSearchId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getDbSearchId() {
        return dbSearchId;
    }

    public void setDbSearchId(int dbSearchId) {
        this.dbSearchId = dbSearchId;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getResultSummary() {
        return resultSummary;
    }

    public void setResultSummary(String resultSummary) {
        this.resultSummary = resultSummary;
    }

    public Integer getNumRedundantProteins() {
        return numRedundantProteins;
    }

    public void setNumRedundantProteins(Integer numRedundantProteins) {
        this.numRedundantProteins = numRedundantProteins;
    }

    public Integer getNumNonredundantProteins() {
        return numNonredundantProteins;
    }

    public void setNumNonredundantProteins(Integer numNonredundantProteins) {
        this.numNonredundantProteins = numNonredundantProteins;
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
        if (!(object instanceof ResultSelection)) {
            return false;
        }
        ResultSelection other = (ResultSelection) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ipa.ip2.model.db.ResultSelection[id=" + id + "]";
    }

}
