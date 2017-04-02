package com.ipa.ip2.api.model;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;

import javax.persistence.Transient;
import java.util.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;


/**
 * Created by amit on 15/02/17.
 */
@Entity
@Table(name = "protein_group")
@NamedQueries({@NamedQuery(name = "ProteinGroup.findById", query = "SELECT p FROM ProteinGroup p WHERE p.id = :id"), @NamedQuery(name = "ProteinGroup.findByResultSelectionId", query = "SELECT p FROM ProteinGroup p WHERE p.resultSelectionId = :resultSelectionId"), @NamedQuery(name = "ProteinGroup.findBySequenceCount", query = "SELECT p FROM ProteinGroup p WHERE p.sequenceCount = :sequenceCount"), @NamedQuery(name = "ProteinGroup.findBySpectraCount", query = "SELECT p FROM ProteinGroup p WHERE p.spectraCount = :spectraCount"), @NamedQuery(name = "ProteinGroup.findByConfidence", query = "SELECT p FROM ProteinGroup p WHERE p.confidence = :confidence"), @NamedQuery(name = "ProteinGroup.findByNumProteinHit", query = "SELECT p FROM ProteinGroup p WHERE p.numProteinHit = :numProteinHit")})
public class ProteinGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "result_selection_id", nullable = false)
    private int resultSelectionId;
    @Column(name = "sequence_count")
    private Integer sequenceCount;
    @Column(name = "spectra_count")
    private Integer spectraCount;
    @Column(name = "confidence")
    private Float confidence;
    @Column(name = "num_protein_hit")
    private Integer numProteinHit;


    @Transient
    private List<ProteinHit> proteinHitList = new ArrayList<ProteinHit>();
    @Transient
    private List<DtaselectBestPeptide> peptideList = new ArrayList<DtaselectBestPeptide>();
    @Transient
    private String proteinAccessions;
    @Transient
    private String proteinAccessionDescs;
    @Transient
    private String proteinSequenceCoverages;
    private String proteinHeavySpec;
    private String proteinLightSpec;
    private boolean hasHL = false;
    private ArrayList<String> stringProteinSequenceCoverages;
    private double nsaf;
    private double empai;
    private String nsafOneline;
    private String empaiOneline;

    public ProteinGroup() {
    }

    public ProteinGroup(Integer id) {
        this.id = id;
    }

    public ProteinGroup(Integer id, int resultSelectionId) {
        this.id = id;
        this.resultSelectionId = resultSelectionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getResultSelectionId() {
        return resultSelectionId;
    }

    public void setResultSelectionId(int resultSelectionId) {
        this.resultSelectionId = resultSelectionId;
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

    public Float getConfidence() {
        return confidence;
    }

    public void setConfidence(Float confidence) {
        this.confidence = confidence;
    }

    public Integer getNumProteinHit() {
        return numProteinHit;
    }

    public void setNumProteinHit(Integer numProteinHit) {
        this.numProteinHit = numProteinHit;
    }

    public void setProteinHeavySpec(String hs){
        this.proteinHeavySpec = hs;
    }
    public String getProteinHeavySpec(){
        return proteinHeavySpec;
    }

    public void setProteinLightSpec(String ls){
        this.proteinLightSpec = ls;
    }
    public String getProteinLightSpec(){
        return proteinLightSpec;
    }

    public void setHasHL(boolean hhl){
        this.hasHL = hhl;
    }
    public boolean getHasHL(){
        return hasHL;
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
        if (!(object instanceof ProteinGroup)) {
            return false;
        }
        ProteinGroup other = (ProteinGroup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ipa.ip2.model.db.ProteinGroup[id=" + id + "]";
    }

    public List<ProteinHit> getProteinHitList() {
        return proteinHitList;
    }

    public void setProteinHitList(List<ProteinHit> proteinHitList) {
        this.proteinHitList = proteinHitList;
    }

    public List<DtaselectBestPeptide> getPeptideList() {
        return peptideList;
    }

    public void setPeptideList(List<DtaselectBestPeptide> peptideList) {
        this.peptideList = peptideList;
    }

    public void addPeptide(DtaselectBestPeptide peptide) {

        this.peptideList.add(peptide);
    }

    public void addProtein(ProteinHit protein) {
        this.proteinHitList.add(protein);
    }

    public ProteinHit getFirstProtein() {
        return this.proteinHitList.get(0);
    }

    public String getProteinSequenceCoverages() {

        StringBuffer sb = new StringBuffer();
        for(Iterator<ProteinHit> itr=proteinHitList.iterator(); itr.hasNext(); ) {
            ProteinHit ph = itr.next();
            NumberFormat formatter = new DecimalFormat("###.#");
            String cvgString = formatter.format(ph.getSequenceCoverage());
            sb.append(cvgString).append(";");
        }

        proteinSequenceCoverages= sb.toString();

        return proteinSequenceCoverages;
    }

    public void setProteinSequenceCoverages(String proteinSequenceCoverages) {
        this.proteinSequenceCoverages = proteinSequenceCoverages;
    }

    // build an arraylist to fill empty digits with "0"
    public ArrayList<String> getStringProteinSequenceCoverages(){
        StringBuffer sb = new StringBuffer();
        ArrayList<String> coverageList = new ArrayList<String>();

        for(Iterator<ProteinHit> itr=proteinHitList.iterator(); itr.hasNext(); ) {
            ProteinHit ph = itr.next();
            String tmp = ph.getSequenceCoverage().toString();
            int indexOfDot = tmp.indexOf(".");	// index of "."
            int offset = 3-indexOfDot;		// number of needed "0"
            for(int j=0;j<offset;j++){		// add "0"s to the string
                tmp = "0"+tmp;
            }
            coverageList.add(tmp);
        }
        return coverageList;
    }
    public void setStringProteinSequenceCoverages(ArrayList<String> spsc){
        this.stringProteinSequenceCoverages = spsc;
    }


    public String getProteinAccessionDescs() {

        StringBuffer sb = new StringBuffer();

        for(Iterator<ProteinHit> itr=proteinHitList.iterator(); itr.hasNext(); ) {
            ProteinHit ph = itr.next();
            // sb.append(ph.getAccession()).append(" ");
            sb.append(ph.getDescription()).append(";");
        }

        proteinAccessionDescs = sb.toString();

        return proteinAccessionDescs;
    }


    public void setProteinAccessionDescs(String proteinAccessionDescs) {
        this.proteinAccessionDescs = proteinAccessionDescs;
    }

    public String getProteinAccessions() {

        StringBuffer sb = new StringBuffer();

        for(Iterator<ProteinHit> itr=proteinHitList.iterator(); itr.hasNext(); ) {
            ProteinHit ph = itr.next();
            sb.append(ph.getAccession()).append(";");
        }

        proteinAccessions = sb.toString();

        return proteinAccessions;
    }

    public void setProteinAccessions(String proteinAccessions) {
        this.proteinAccessions = proteinAccessions;
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

    public String getNsafOneline() {

        StringBuffer sb = new StringBuffer();

        for(Iterator<ProteinHit> itr=proteinHitList.iterator(); itr.hasNext(); ) {
            ProteinHit ph = itr.next();
            sb.append(ph.getNsaf()).append(";");
        }

        this.nsafOneline = sb.toString();

        return nsafOneline;
    }

    public void setNsafOneline(String nsafOneline) {
        this.nsafOneline = nsafOneline;
    }

    public String getEmpaiOneline() {
        StringBuffer sb = new StringBuffer();

        for(Iterator<ProteinHit> itr=proteinHitList.iterator(); itr.hasNext(); ) {
            ProteinHit ph = itr.next();
            sb.append(ph.getEmpai()).append(";");
        }

        this.empaiOneline = sb.toString();

        return empaiOneline;
    }

    public void setEmpaiOneline(String empaiOneline) {
        this.empaiOneline = empaiOneline;
    }




}