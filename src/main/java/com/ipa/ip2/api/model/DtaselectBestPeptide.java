package com.ipa.ip2.api.model;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by amit on 15/02/17.
 */
@Entity
@Table(name = "dtaselect_best_peptide")
@NamedQueries({@NamedQuery(name = "DtaselectBestPeptide.findById", query = "SELECT d FROM DtaselectBestPeptide d WHERE d.id = :id"), @NamedQuery(name = "DtaselectBestPeptide.findByProteinGroupId", query = "SELECT d FROM DtaselectBestPeptide d WHERE d.proteinGroupId = :proteinGroupId"), @NamedQuery(name = "DtaselectBestPeptide.findBySpectraCount", query = "SELECT d FROM DtaselectBestPeptide d WHERE d.spectraCount = :spectraCount"), @NamedQuery(name = "DtaselectBestPeptide.findBySequence", query = "SELECT d FROM DtaselectBestPeptide d WHERE d.sequence = :sequence"), @NamedQuery(name = "DtaselectBestPeptide.findByIsUnique", query = "SELECT d FROM DtaselectBestPeptide d WHERE d.isUnique = :isUnique"), @NamedQuery(name = "DtaselectBestPeptide.findByConfidence", query = "SELECT d FROM DtaselectBestPeptide d WHERE d.confidence = :confidence"), @NamedQuery(name = "DtaselectBestPeptide.findByEvaluationState", query = "SELECT d FROM DtaselectBestPeptide d WHERE d.evaluationState = :evaluationState"), @NamedQuery(name = "DtaselectBestPeptide.findByFileName", query = "SELECT d FROM DtaselectBestPeptide d WHERE d.fileName = :fileName"), @NamedQuery(name = "DtaselectBestPeptide.findByScan", query = "SELECT d FROM DtaselectBestPeptide d WHERE d.scan = :scan"), @NamedQuery(name = "DtaselectBestPeptide.findByChargeState", query = "SELECT d FROM DtaselectBestPeptide d WHERE d.chargeState = :chargeState"), @NamedQuery(name = "DtaselectBestPeptide.findByPtm", query = "SELECT d FROM DtaselectBestPeptide d WHERE d.ptm = :ptm"), @NamedQuery(name = "DtaselectBestPeptide.findByPrimaryScore", query = "SELECT d FROM DtaselectBestPeptide d WHERE d.primaryScore = :primaryScore"), @NamedQuery(name = "DtaselectBestPeptide.findBySecondaryScroe", query = "SELECT d FROM DtaselectBestPeptide d WHERE d.secondaryScroe = :secondaryScroe"), @NamedQuery(name = "DtaselectBestPeptide.findByDeltaCn", query = "SELECT d FROM DtaselectBestPeptide d WHERE d.deltaCn = :deltaCn"), @NamedQuery(name = "DtaselectBestPeptide.findByThirdScore", query = "SELECT d FROM DtaselectBestPeptide d WHERE d.thirdScore = :thirdScore"), @NamedQuery(name = "DtaselectBestPeptide.findByFourthScore", query = "SELECT d FROM DtaselectBestPeptide d WHERE d.fourthScore = :fourthScore"), @NamedQuery(name = "DtaselectBestPeptide.findByFifthScore", query = "SELECT d FROM DtaselectBestPeptide d WHERE d.fifthScore = :fifthScore"), @NamedQuery(name = "DtaselectBestPeptide.findByPredictedMplush", query = "SELECT d FROM DtaselectBestPeptide d WHERE d.predictedMplush = :predictedMplush"), @NamedQuery(name = "DtaselectBestPeptide.findByMeasuredMplush", query = "SELECT d FROM DtaselectBestPeptide d WHERE d.measuredMplush = :measuredMplush")})
public class DtaselectBestPeptide implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "protein_group_id", nullable = false)
    private int proteinGroupId;
    @Column(name = "spectra_count")
    private Integer spectraCount;
    @Column(name = "sequence")
    private String sequence;
    @Column(name = "is_unique")
    private Boolean isUnique;
    @Column(name = "confidence")
    private Float confidence;
    @Column(name = "evaluation_state")
    private Integer evaluationState;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "scan")
    private Integer scan;
    @Column(name = "charge_state")
    private Integer chargeState;
    @Column(name = "ptm")
    private String ptm;
    @Column(name = "primary_score")
    private Float primaryScore;
    @Column(name = "secondary_scroe")
    private Float secondaryScroe;
    @Column(name = "delta_cn")
    private Float deltaCn;
    @Column(name = "third_score")
    private Float thirdScore;
    @Column(name = "fourth_score")
    private Float fourthScore;
    @Column(name = "fifth_score")
    private Float fifthScore;
    @Column(name = "predicted_mplush")
    private Float predictedMplush;
    @Column(name = "measured_mplush")
    private Float measuredMplush;

    @Transient
    private String ppm;

    public DtaselectBestPeptide() {
    }

    public DtaselectBestPeptide(Integer id) {
        this.id = id;
    }

    public DtaselectBestPeptide(Integer id, int proteinGroupId) {
        this.id = id;
        this.proteinGroupId = proteinGroupId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getProteinGroupId() {
        return proteinGroupId;
    }

    public void setProteinGroupId(int proteinGroupId) {
        this.proteinGroupId = proteinGroupId;
    }

    public Integer getSpectraCount() {
        return spectraCount;
    }

    public void setSpectraCount(Integer spectraCount) {
        this.spectraCount = spectraCount;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public Boolean getIsUnique() {
        return isUnique;
    }

    public void setIsUnique(Boolean isUnique) {
        this.isUnique = isUnique;
    }

    public Float getConfidence() {
        return confidence;
    }

    public void setConfidence(Float confidence) {
        this.confidence = confidence;
    }

    public Integer getEvaluationState() {
        return evaluationState;
    }

    public void setEvaluationState(Integer evaluationState) {
        this.evaluationState = evaluationState;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getScan() {
        return scan;
    }

    public void setScan(Integer scan) {
        this.scan = scan;
    }

    public Integer getChargeState() {
        return chargeState;
    }

    public void setChargeState(Integer chargeState) {
        this.chargeState = chargeState;
    }

    public String getPtm() {
        return ptm;
    }

    public void setPtm(String ptm) {
        this.ptm = ptm;
    }
    public String getPpm() {
        return ppm;
    }

    public void setPpm(String ppm) {
        this.ppm = ppm;
    }

    public void setPpm(int ppm) {
        this.ppm = String.valueOf(ppm);
    }

    public Float getPrimaryScore() {
        return primaryScore;
    }

    public void setPrimaryScore(Float primaryScore) {
        this.primaryScore = primaryScore;
    }

    public Float getSecondaryScroe() {
        return secondaryScroe;
    }

    public void setSecondaryScroe(Float secondaryScroe) {
        this.secondaryScroe = secondaryScroe;
    }

    public Float getDeltaCn() {
        return deltaCn;
    }

    public void setDeltaCn(Float deltaCn) {
        this.deltaCn = deltaCn;
    }

    public Float getThirdScore() {
        return thirdScore;
    }

    public void setThirdScore(Float thirdScore) {
        this.thirdScore = thirdScore;
    }

    public Float getFourthScore() {
        return fourthScore;
    }

    public void setFourthScore(Float fourthScore) {
        this.fourthScore = fourthScore;
    }

    public Float getFifthScore() {
        return fifthScore;
    }

    public void setFifthScore(Float fifthScore) {
        this.fifthScore = fifthScore;
    }

    public Float getPredictedMplush() {
        return predictedMplush;
    }

    public void setPredictedMplush(Float predictedMplush) {
        this.predictedMplush = predictedMplush;
    }

    public Float getMeasuredMplush() {
        return measuredMplush;
    }

    public void setMeasuredMplush(Float measuredMplush) {
        this.measuredMplush = measuredMplush;
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
        if (!(object instanceof DtaselectBestPeptide)) {
            return false;
        }
        DtaselectBestPeptide other = (DtaselectBestPeptide) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ipa.ip2.model.db.DtaselectBestPeptide[id=" + id + "]";
    }

}