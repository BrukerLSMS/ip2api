package com.ipa.ip2.api.model;


import com.ipa.ip2.api.util.dtaselect.Protein;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Created by amit on 15/02/17.
 */
@Entity
@Table(name = "protein_hit")
@NamedQueries({@NamedQuery(name = "ProteinHit.findById", query = "SELECT p FROM ProteinHit p WHERE p.id = :id"), @NamedQuery(name = "ProteinHit.findByProteinGroupId", query = "SELECT p FROM ProteinHit p WHERE p.proteinGroupId = :proteinGroupId"), @NamedQuery(name = "ProteinHit.findBySequenceCoverage", query = "SELECT p FROM ProteinHit p WHERE p.sequenceCoverage = :sequenceCoverage"), @NamedQuery(name = "ProteinHit.findByAccession", query = "SELECT p FROM ProteinHit p WHERE p.accession = :accession"), @NamedQuery(name = "ProteinHit.findByEvaluationState", query = "SELECT p FROM ProteinHit p WHERE p.evaluationState = :evaluationState")})
public class ProteinHit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "protein_group_id", nullable = false)
    private int proteinGroupId;
    @Column(name = "sequence_coverage")
    private Float sequenceCoverage;
    @Column(name = "accession")
    private String accession;
    @Lob
    @Column(name = "description")
    private String description;
    @Column(name = "evaluation_state")
    private Integer evaluationState;
    @Column(name = "protein_length")
    private Integer proteinLength;
    @Column(name = "molecular_weight")
    private Float molecularWeight;
    @Column(name = "pi")
    private Float pi;
    @Transient
    private double nsaf=-1;
    private double empai=-1;
    private String geneName;

    /*
     * Excel Export
     */

    private Integer sequenceCount;
    private Integer spectraCount;
    private boolean hasHL = false;
    private String proteinHeavySpec;
    private String proteinLightSpec;



    public void convertProteinHit(Protein protein) {
        this.id=0;

        this.sequenceCoverage = Float.parseFloat(protein.getSeqCoverage().replaceAll("%",""));
        this.accession = protein.getLocus();
        this.description = protein.getDescription();
        this.proteinLength = Integer.parseInt(protein.getLength());
        this.molecularWeight = Float.parseFloat(protein.getMolWt());
        this.pi = Float.parseFloat(protein.getPI());
        this.nsaf = protein.getNsaf();
        this.empai = protein.getEmpai();

    }

    public ProteinHit() {
    }

    public ProteinHit(Integer id) {
        this.id = id;
    }

    public ProteinHit(Integer id, int proteinGroupId) {
        this.id = id;
        this.proteinGroupId = proteinGroupId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProteinLength() {
        return proteinLength;
    }
    public void setProteinLength(Integer p) {
        proteinLength = p;
    }
    public Float getMolecularWeight() {
        return molecularWeight;
    }
    public void setMolecularWeight(Float m) {
        molecularWeight = m;
    }
    public Float getPi() {
        return pi;
    }
    public void setPi(Float p) {
        pi = p;
    }
    public int getProteinGroupId() {
        return proteinGroupId;
    }

    public void setProteinGroupId(int proteinGroupId) {
        this.proteinGroupId = proteinGroupId;
    }

    public Float getSequenceCoverage() {
        return sequenceCoverage;
    }

    public void setSequenceCoverage(Float sequenceCoverage) {
        this.sequenceCoverage = sequenceCoverage;
    }

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEvaluationState() {
        return evaluationState;
    }

    public void setEvaluationState(Integer evaluationState) {
        this.evaluationState = evaluationState;
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
        if (!(object instanceof ProteinHit)) {
            return false;
        }
        ProteinHit other = (ProteinHit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ipa.ip2.model.db.ProteinHit[id=" + id + "]";
    }

    public double getNsaf() {
        return nsaf;
    }

    public void setNsaf(double nsaf) {
        this.nsaf = nsaf;
    }

    public double getEmpai() {
        return empai;
    }

    public void setEmpai(double empai) {
        this.empai = empai;
    }

    public String getGeneName() {
        return geneName;
    }

    public void setGeneName(String geneName) {
        this.geneName = geneName;
    }

    public Integer getSequenceCount() {
        return sequenceCount;
    }

    public void setSequenceCount(Integer sequenceCount) {
        this.sequenceCount = sequenceCount;
    }

    public Integer getSpectraCount() {
        return spectraCount;
    }

    public void setSpectraCount(Integer spectraCount) {
        this.spectraCount = spectraCount;
    }

    public boolean isHasHL() {
        return hasHL;
    }

    public void setHasHL(boolean hasHL) {
        this.hasHL = hasHL;
    }

    public String getProteinHeavySpec() {
        return proteinHeavySpec;
    }

    public void setProteinHeavySpec(String proteinHeavySpec) {
        this.proteinHeavySpec = proteinHeavySpec;
    }

    public String getProteinLightSpec() {
        return proteinLightSpec;
    }

    public void setProteinLightSpec(String proteinLightSpec) {
        this.proteinLightSpec = proteinLightSpec;
    }



}
