package com.ipa.ip2.api.model;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ipa.ip2.api.dao.CensusOutExportJsonBo;
import com.ipa.ip2.api.db.HibernateUtils;
import com.ipa.ip2.api.exception.APIException;
import com.ipa.ip2.api.util.OsCheck;
import com.ipa.ip2.api.util.PathUtil;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * Created by amit on 15/02/17.
 */
@Entity
@Table(name = "quantitation")
@NamedQueries({@NamedQuery(name = "Quantitation.findById", query = "SELECT q FROM Quantitation q WHERE q.id = :id"), @NamedQuery(name = "Quantitation.findByProjectId", query = "SELECT q FROM Quantitation q WHERE q.projectId = :projectId"), @NamedQuery(name = "Quantitation.findByPath", query = "SELECT q FROM Quantitation q WHERE q.path = :path"), @NamedQuery(name = "Quantitation.findByParameterFile", query = "SELECT q FROM Quantitation q WHERE q.parameterFile = :parameterFile"), @NamedQuery(name = "Quantitation.findByXmlResultFile", query = "SELECT q FROM Quantitation q WHERE q.xmlResultFile = :xmlResultFile"), @NamedQuery(name = "Quantitation.findByTextResultFile", query = "SELECT q FROM Quantitation q WHERE q.textResultFile = :textResultFile"), @NamedQuery(name = "Quantitation.findByDeterminationFactor", query = "SELECT q FROM Quantitation q WHERE q.determinationFactor = :determinationFactor"), @NamedQuery(name = "Quantitation.findByOutlierPvalue", query = "SELECT q FROM Quantitation q WHERE q.outlierPvalue = :outlierPvalue"), @NamedQuery(name = "Quantitation.findByMs2Pvalue", query = "SELECT q FROM Quantitation q WHERE q.ms2Pvalue = :ms2Pvalue"), @NamedQuery(name = "Quantitation.findByCorrectionFactor", query = "SELECT q FROM Quantitation q WHERE q.correctionFactor = :correctionFactor"), @NamedQuery(name = "Quantitation.findByAllNoneBoundLow", query = "SELECT q FROM Quantitation q WHERE q.allNoneBoundLow = :allNoneBoundLow"), @NamedQuery(name = "Quantitation.findByAllNoneFoundUpper", query = "SELECT q FROM Quantitation q WHERE q.allNoneFoundUpper = :allNoneFoundUpper"), @NamedQuery(name = "Quantitation.findByAllNoneScoreCutoff", query = "SELECT q FROM Quantitation q WHERE q.allNoneScoreCutoff = :allNoneScoreCutoff"), @NamedQuery(name = "Quantitation.findByUniquePeptideOnly", query = "SELECT q FROM Quantitation q WHERE q.uniquePeptideOnly = :uniquePeptideOnly")})
public class Quantitation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "project_id", nullable = false)
    private int projectId;
    @Column(name = "path")
    private String path;
    @Column(name = "parameter_file")
    private String parameterFile;
    @Lob
    @Column(name = "description")
    private String description;
    @Column(name = "xml_result_file")
    private String xmlResultFile;
    @Column(name = "text_result_file")
    private String textResultFile;
    @Column(name = "determination_factor")
    private Float determinationFactor;
    @Column(name = "outlier_pvalue")
    private Float outlierPvalue;
    @Column(name = "ms2_pvalue")
    private Float ms2Pvalue;
    @Column(name = "correction_factor")
    private Float correctionFactor;
    @Column(name = "all_none_bound_low")
    private Float allNoneBoundLow;
    @Column(name = "all_none_found_upper")
    private Float allNoneFoundUpper;
    @Column(name = "all_none_score_cutoff")
    private Float allNoneScoreCutoff;
    @Column(name = "unique_peptide_only")
    private Boolean uniquePeptideOnly;
    @Column(name = "db_search_ids")
    private String dbSearchIds;
    @Column(name = "msp_experiment_ids")
    private String mspExperimentIds;
    @Column(name = "is_finished")
    private Boolean isFinished;
    @Column(name = "quantitation_date")
    private Date quantitationDate;
    @Column(name = "quantitation_type")
    private String quantitationType;
    @Column(name = "quantitation_flag")
    private String quantitationFlag;
    @Column(name = "export_path")
    private String exportPath;
    @Column(name = "verified")
    private boolean verified;

    @Transient
    private String contextPath=null;

    public Quantitation() {
    }

    public Quantitation(Integer id) {
        this.id = id;
    }

    public Quantitation(Integer id, int projectId) {
        this.id = id;
        this.projectId = projectId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getPath(){
        if(!org.apache.commons.lang.StringUtils.isBlank(path)){
            try {
                return Paths.get(HibernateUtils.getInstance().getRelativePath(), path).toString();
            } catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getParameterFile() {
        return parameterFile;
    }

    public void setParameterFile(String parameterFile) {
        this.parameterFile = parameterFile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getXmlResultFile() {
        return xmlResultFile;
    }

    public void setXmlResultFile(String xmlResultFile) {
        this.xmlResultFile = xmlResultFile;
    }

    public String getTextResultFile() {
        return textResultFile;
    }

    public void setTextResultFile(String textResultFile) {
        this.textResultFile = textResultFile;
    }

    public Float getDeterminationFactor() {
        return determinationFactor;
    }

    public void setDeterminationFactor(Float determinationFactor) {
        this.determinationFactor = determinationFactor;
    }

    public Float getOutlierPvalue() {
        return outlierPvalue;
    }

    public void setOutlierPvalue(Float outlierPvalue) {
        this.outlierPvalue = outlierPvalue;
    }

    public Float getMs2Pvalue() {
        return ms2Pvalue;
    }

    public void setMs2Pvalue(Float ms2Pvalue) {
        this.ms2Pvalue = ms2Pvalue;
    }

    public Float getCorrectionFactor() {
        return correctionFactor;
    }

    public void setCorrectionFactor(Float correctionFactor) {
        this.correctionFactor = correctionFactor;
    }

    public Float getAllNoneBoundLow() {
        return allNoneBoundLow;
    }

    public void setAllNoneBoundLow(Float allNoneBoundLow) {
        this.allNoneBoundLow = allNoneBoundLow;
    }

    public Float getAllNoneFoundUpper() {
        return allNoneFoundUpper;
    }

    public void setAllNoneFoundUpper(Float allNoneFoundUpper) {
        this.allNoneFoundUpper = allNoneFoundUpper;
    }

    public Float getAllNoneScoreCutoff() {
        return allNoneScoreCutoff;
    }

    public void setAllNoneScoreCutoff(Float allNoneScoreCutoff) {
        this.allNoneScoreCutoff = allNoneScoreCutoff;
    }

    public Boolean getUniquePeptideOnly() {
        return uniquePeptideOnly;
    }

    public void setUniquePeptideOnly(Boolean uniquePeptideOnly) {
        this.uniquePeptideOnly = uniquePeptideOnly;
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
        if (!(object instanceof Quantitation)) {
            return false;
        }
        Quantitation other = (Quantitation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ipa.ip2.model.db.Quantitation[id=" + id + "]";
    }

    public String getDbSearchIds() {
        return dbSearchIds;
    }

    public void setDbSearchIds(String dbSearchIds) {
        this.dbSearchIds = dbSearchIds;
    }

    public String getMspExperimentIds() {
        return mspExperimentIds;
    }

    public void setMspExperimentIds(String mspExperimentIds) {
        this.mspExperimentIds = mspExperimentIds;
    }

    public Boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Boolean isFinished) {
        this.isFinished = isFinished;
    }

    public Date getQuantitationDate() {
        return this.quantitationDate;
    }

    public void setQuantitationDate(Date quantitationDate) {
        this.quantitationDate = quantitationDate;
    }

    public String getQuantitationType() {
        return this.quantitationType;
    }

    public void setQuantitationType(String quantitationType) {
        this.quantitationType = quantitationType;
    }

    public String getContextPath() {

        if(null == contextPath || "".equals(contextPath)){
            try {
                contextPath = PathUtil.getContextPath(path);
            } catch (Exception e) {
                System.out.println("Path is missing ::" + e.getMessage());
            }

        }

        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getQuantitationFlag() {
        return quantitationFlag;
    }

    public void setQuantitationFlag(String quantitationFlag) {
        this.quantitationFlag = quantitationFlag;
    }

    public String getExportPath() throws APIException{
        if(!org.apache.commons.lang.StringUtils.isBlank(exportPath)){
            try {
                return Paths.get(HibernateUtils.getInstance().getRelativePath(), exportPath).toString();
            } catch (Exception e){
                throw new APIException("Export path is null");
            }
        }
        return exportPath;
    }

    public void setExportPath(String exportPath) {
        this.exportPath = exportPath;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    @Transient
    public String getCensusFilePath(){
        if(!StringUtils.isEmpty(quantitationType)) {
            if(quantitationType.equalsIgnoreCase("labelfree")) {
                return Paths.get(this.getPath() , "census_labelfree_out_" + id + "_stat.txt").toString();
                //} else if(quantitationType.equalsIgnoreCase("SILAC")){
            } else {
                return Paths.get(this.getPath() , "census-out.txt").toString();
            }
        } else {
            return null;
        }
    }

    @Transient
    public CensusOutExportJsonBo parseExportedFile() throws APIException{
        CensusOutExportJsonBo censusOutExportJsonBo = null;
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            censusOutExportJsonBo = gson.fromJson(new FileReader(Paths.get(this.getExportPath(), "census_labelfree_out_meta.JSON").toString()), CensusOutExportJsonBo.class);
        } catch (Exception e){
            if(e instanceof APIException){
                throw e;
            }else{
                throw new APIException(e.getMessage());
            }
        } finally {
            return censusOutExportJsonBo;
        }

    }

}
