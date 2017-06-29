package com.ipa.ip2.api.model;

import com.ipa.ip2.api.db.HibernateUtils;
import com.ipa.ip2.api.util.DTASelectFilterReader;
import com.ipa.ip2.api.util.DatabaseUtil;
import com.ipa.ip2.api.util.FileFilterUtil;
import com.ipa.ip2.api.util.OsCheck;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by amit on 15/02/17.
 */

@Entity
@Table(name = "db_search")
@NamedQueries({@NamedQuery(name = "DbSearch.findById", query = "SELECT d FROM DbSearch d WHERE d.id = :id"), @NamedQuery(name = "DbSearch.findByProteinDbId", query = "SELECT d FROM DbSearch d WHERE d.proteinDbId = :proteinDbId"), @NamedQuery(name = "DbSearch.findByMspExperimentId", query = "SELECT d FROM DbSearch d WHERE d.mspExperimentId = :mspExperimentId"), @NamedQuery(name = "DbSearch.findByUserId", query = "SELECT d FROM DbSearch d WHERE d.userId = :userId"), @NamedQuery(name = "DbSearch.findByIdDate", query = "SELECT d FROM DbSearch d WHERE d.idDate = :idDate"), @NamedQuery(name = "DbSearch.findByIsModSearch", query = "SELECT d FROM DbSearch d WHERE d.isModSearch = :isModSearch"), @NamedQuery(name = "DbSearch.findBySearchProgram", query = "SELECT d FROM DbSearch d WHERE d.searchProgram = :searchProgram"), @NamedQuery(name = "DbSearch.findByParameterFile", query = "SELECT d FROM DbSearch d WHERE d.parameterFile = :parameterFile"), @NamedQuery(name = "DbSearch.findByPeptideMassTolerance", query = "SELECT d FROM DbSearch d WHERE d.peptideMassTolerance = :peptideMassTolerance"), @NamedQuery(name = "DbSearch.findByFragmentMassTolerance", query = "SELECT d FROM DbSearch d WHERE d.fragmentMassTolerance = :fragmentMassTolerance"), @NamedQuery(name = "DbSearch.findByMaxMissedCleavages", query = "SELECT d FROM DbSearch d WHERE d.maxMissedCleavages = :maxMissedCleavages"), @NamedQuery(name = "DbSearch.findByMassTypeParent", query = "SELECT d FROM DbSearch d WHERE d.massTypeParent = :massTypeParent"), @NamedQuery(name = "DbSearch.findByMassTypeFragment", query = "SELECT d FROM DbSearch d WHERE d.massTypeFragment = :massTypeFragment"), @NamedQuery(name = "DbSearch.findByEnzymeName", query = "SELECT d FROM DbSearch d WHERE d.enzymeName = :enzymeName")})
public class DbSearch implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "protein_db_id", nullable = false)
    private int proteinDbId;
    @Column(name = "msp_experiment_id", nullable = false)
    private int mspExperimentId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "id_date")
    @Temporal(TemporalType.DATE)
    private Date idDate;
    @Lob
    @Column(name = "result_path")
    private String resultPath;
    @Column(name = "is_mod_search")
    private Boolean isModSearch;
    @Column(name = "search_program")
    private String searchProgram;
    @Column(name = "parameter_file")
    private String parameterFile;
    @Lob
    @Column(name = "parameter_file_content")
    private String parameterFileContent;
    @Column(name = "peptide_mass_tolerance")
    private Float peptideMassTolerance;
    @Column(name = "fragment_mass_tolerance")
    private Float fragmentMassTolerance;
    @Column(name = "max_missed_cleavages")
    private Integer maxMissedCleavages;
    @Column(name = "mass_type_parent")
    private Integer massTypeParent;
    @Column(name = "mass_type_fragment")
    private Integer massTypeFragment;
    @Column(name = "enzyme_name")
    private String enzymeName;
    @Column(name = "result_file_names")
    private String resultFileNames;
    @Column(name = "cluster_name")
    private String clusterName;
    @Column(name = "temp_folder")
    private String tempFolder;
    @Column(name = "is_finished")
    private Boolean isFinished;
    @Column(name = "is_submitted")
    private Boolean isSubmitted;
    @Column(name = "is_labeled_quantitation_done")
    private Boolean isLabeledQuantitationDone;
    @Column(name = "name")
    private String name;
    @Column(name = "searched_scan_count")
    private Integer searchedScanCount;

    @Transient
    private Integer searchedScanRatio;
    @Transient
    private List<ResultSelection> resultSelectionList;
    @Transient
    private boolean isQuant;
    @Transient
    private MspExperiment mspExperiment;
    @Transient
    private Quantitation quantitation;
    @Transient
    private String filterParams;
    @Transient
    private int proteinIds;
    @Transient
    private int peptideIds=-1;
    @Transient
    private double proteinFP=-1;
    @Transient
    private String paramFile;
    @Transient
    private String paramHFile = "Hsearch.xml"; //Hsearch.xml
    @Transient
    private boolean paramHExist = false;
    @Transient
    private String paramMFile = "Msearch.xml"; //Msearch.xml
    @Transient
    private boolean paramMExist = false;
    @Transient
    private String contextPath;
    @Transient
    private boolean phospho=false;
    @Transient
    private boolean n15enrich=false;
    @Transient
    private double peptideFP=-1;
    @Transient
    private double spectrumFP=-1;
    @Transient
    private boolean subset = false;
    @Transient
    private int sqtFileSize = -1;
    @Transient
    private String spectrumOutputFilePath = null;
    @Transient
    private boolean spectrumInProgress = false;
    @Transient
    private boolean ms1FileExist = false;

    public DbSearch() {
    }

    public DbSearch(Integer id) {
        this.id = id;
    }

    public DbSearch(Integer id, int proteinDbId, int mspExperimentId) {
        this.id = id;
        this.proteinDbId = proteinDbId;
        this.mspExperimentId = mspExperimentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getProteinDbId() {
        return proteinDbId;
    }

    public void setProteinDbId(int proteinDbId) {
        this.proteinDbId = proteinDbId;
    }

    public int getMspExperimentId() {
        return mspExperimentId;
    }

    public void setMspExperimentId(int mspExperimentId) {
        this.mspExperimentId = mspExperimentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getIdDate() {
        return idDate;
    }

    public void setIdDate(Date idDate) {
        this.idDate = idDate;
    }

    public String getResultPath() {
        if(!StringUtils.isBlank(resultPath)){
            try {
                return Paths.get(HibernateUtils.getInstance().getRelativePath(), resultPath).toString();
            } catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
        return resultPath;
    }

    public void setResultPath(String resultPath) {
        this.resultPath = resultPath;
    }

    public Boolean getIsModSearch() {
        return isModSearch;
    }

    public void setIsModSearch(Boolean isModSearch) {
        this.isModSearch = isModSearch;
    }

    public String getSearchProgram() {
        return searchProgram;
    }

    public void setSearchProgram(String searchProgram) {
        this.searchProgram = searchProgram;
    }

    public String getParameterFile() {
        if(!StringUtils.isBlank(parameterFile)){
            try {
                return Paths.get(HibernateUtils.getInstance().getRelativePath(), parameterFile).toString();
            } catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
        return parameterFile;
    }

    public void setParameterFile(String parameterFile) {
        this.parameterFile = parameterFile;
    }

    public String getParameterFileContent() {
        return parameterFileContent;
    }

    public void setParameterFileContent(String parameterFileContent) {
        this.parameterFileContent = parameterFileContent;
    }

    public Float getPeptideMassTolerance() {
        return peptideMassTolerance;
    }

    public void setPeptideMassTolerance(Float peptideMassTolerance) {
        this.peptideMassTolerance = peptideMassTolerance;
    }

    public Float getFragmentMassTolerance() {
        return fragmentMassTolerance;
    }

    public void setFragmentMassTolerance(Float fragmentMassTolerance) {
        this.fragmentMassTolerance = fragmentMassTolerance;
    }

    public Integer getMaxMissedCleavages() {
        return maxMissedCleavages;
    }

    public void setMaxMissedCleavages(Integer maxMissedCleavages) {
        this.maxMissedCleavages = maxMissedCleavages;
    }

    public Integer getMassTypeParent() {
        return massTypeParent;
    }

    public void setMassTypeParent(Integer massTypeParent) {
        this.massTypeParent = massTypeParent;
    }

    public Integer getMassTypeFragment() {
        return massTypeFragment;
    }

    public void setMassTypeFragment(Integer massTypeFragment) {
        this.massTypeFragment = massTypeFragment;
    }

    public String getEnzymeName() {
        return enzymeName;
    }

    public void setEnzymeName(String enzymeName) {
        this.enzymeName = enzymeName;
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
        if (!(object instanceof DbSearch)) {
            return false;
        }
        DbSearch other = (DbSearch) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ipa.ip2.model.db.DbSearch[id=" + id + "]";
    }

    public String getResultFileNames() {
        return resultFileNames;
    }

    public void setResultFileNames(String resultFileNames) {
        this.resultFileNames = resultFileNames;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getTempFolder() {
        return tempFolder;
    }

    public void setTempFolder(String tempFolder) {
        this.tempFolder = tempFolder;
    }

    public Boolean getIsFinished() {
        String fname = resultPath + java.io.File.separator + "DTASelect-filter.txt";
        String fname2 = resultPath + java.io.File.separator + "mascot.dat";
        String novorFileCheck = resultPath + java.io.File.separator + "done.txt";

        java.io.File f = new java.io.File(fname);
        java.io.File f2 = new java.io.File(fname2);
        java.io.File novorFile = new java.io.File(novorFileCheck);

        if(("NOVOR".equals(this.searchProgram) && !novorFile.exists())
                || (!f.exists() && !f2.exists())) {
            return false;
        }else {
            return true;
        }
    }

    public void setIsFinished(Boolean isFinished) {
        this.isFinished = isFinished;
    }

    public Boolean getIsSubmitted() {
        return isSubmitted;
    }
    public Boolean getIsLabeledQuantitationDone() {

/*
System.out.println("============" +  "aaa");
System.out.println("============" + isLabeledQuantitationDone);
	if(null == quantitation)
		return false;

	String quantPath = quantitation.getPath();

System.out.println("============" +  quantPath);
	File f = new File(quantPath + File.separator + "census-out.txt");

	if(f.exists() && f.length()>0)
		return true;
	else
		return false;
*/
        return isLabeledQuantitationDone;
    }

    public void setIsLabeledQuantitationDone(Boolean f) {
        isLabeledQuantitationDone = f;
    }
    public void setIsSubmitted(Boolean isSubmitted) {
        this.isSubmitted = isSubmitted;
    }
    public List<ResultSelection> getResultSelectionList() {
        return resultSelectionList;
    }

    public void setResultSelectionList(List<ResultSelection> resultSelectionList) {
        this.resultSelectionList = resultSelectionList;
    }

    public boolean isIsQuant() {
        return isQuant;
    }

    public void setIsQuant(boolean isQuant) {
        this.isQuant = isQuant;
    }

    public MspExperiment getMspExperiment() {
        return mspExperiment;
    }

    public void setMspExperiment(MspExperiment mspExperiment) {
        this.mspExperiment = mspExperiment;
    }

    public Quantitation getQuantitation() {
        return quantitation;
    }

    public void setQuantitation(Quantitation quantitation) {
        this.quantitation = quantitation;
    }

    public String getFilterParams() {
        String params = "";

        BufferedReader br = null;
        try {
            String fname = resultPath + java.io.File.separator + "DTASelect-filter.txt";

            java.io.File f = new java.io.File(fname);
            if(!f.exists())
                return "N/A";

            br = new BufferedReader(new FileReader(f));

            String lastLine = null;

            while ( null != (lastLine = br.readLine())) {
                if(lastLine.contains("SQT format"))
                    break;
            }

            lastLine  = br.readLine();

            br.close();

            return lastLine;


        } catch (Exception e) {
            try {   if(null != br) br.close(); }
            catch(IOException ie) {
                e.printStackTrace();
            }

            System.out.println("Error: " + e);
            return "N/A";
        }
    }

    public void setFilterParams(String filterParams) {
        this.filterParams = filterParams;
    }

    public int getProteinIds() {
        if(proteinIds>0)
            return proteinIds;

        DTASelectFilterReader reader = null;
        try {
            String fname = resultPath + java.io.File.separator + "DTASelect-filter.txt";

            java.io.File f = new java.io.File(fname);
            if(!f.exists())
                return 0;

            reader = new DTASelectFilterReader(fname);

            int nonRedNum = reader.getNonRedundantProteinNum();
            int redNum = reader.getRedundantProteinNum();

            this.proteinFP = reader.getProteinFP();
            this.peptideFP = reader.getPeptideFP();
            this.spectrumFP = reader.getSpectrumFP();

            this.peptideIds = reader.getNonRedundantPeptideNum();

            if(nonRedNum<=0)
                return redNum;

            return nonRedNum;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
            return 0;
        } finally {
            try {
                if(null != reader)
                    reader.close();
            } catch(Exception e) {}

        }
    }

    public void setProteinIds(int proteinIds) {
        this.proteinIds = proteinIds;
    }

    public void setProteinFP(double proteinFP)
    {
        this.proteinFP = proteinFP;
    }

    public double getProteinFP() {

        if(proteinFP<=0)
            getProteinIds();

        return proteinFP;
    }

    public void setPeptideFP(double peptideFP)
    {
        this.peptideFP = peptideFP;
    }

    public double getPeptideFP() {
        if(proteinFP<=0)
            getProteinIds();

        return peptideFP;
    }
    public void setSpectrumFP(double spectrumFP)
    {
        this.spectrumFP = spectrumFP;
    }

    public double getSpectrumFP() {
        if(proteinFP<=0)
            getProteinIds();

        return spectrumFP;
    }

    public void setPeptideIds(int peptideIds)
    {
        this.peptideIds = peptideIds;
    }

    public int getPeptideIds() {

        if(peptideIds<=0)
            getProteinIds();

        return peptideIds;
    }

    public String getParamFile() {
        if("SEQUEST".equals(this.searchProgram))
            return "sequest.params";
        else if("ProLuCID".equals(this.searchProgram))
            return "search.xml";
        else if("Blazmass".equals(this.searchProgram))
            return "blazmass.params";
        else if("Comet".equals(this.searchProgram))
            return "comet.params";
        else if("NOVOR".equals(this.searchProgram))
            return "params.txt";
        return null;
    }



    public String getParamHFile() {
        return paramHFile;
    }

    public void setParamHFile(String paramHFile) {
        this.paramHFile = paramHFile;
    }

    public boolean isParamHExist() {
        File hSearchXml = new File(getContextPath() + File.separator + this.paramHFile);
        paramHExist = hSearchXml.exists();
        return paramHExist;
    }

    public void setParamHExist(boolean paramHExist) {
        this.paramHExist = paramHExist;
    }

    public String getParamMFile() {
        return paramMFile;
    }

    public void setParamMFile(String paramMFile) {
        this.paramMFile = paramMFile;
    }

    public boolean isParamMExist() {
        File mSearchXml = new File(getContextPath() + File.separator + this.paramMFile);
        paramMExist = mSearchXml.exists();
        return paramMExist;
    }

    public void setParamMExist(boolean paramMExist) {
        this.paramMExist = paramMExist;
    }

    public void setParamFile(String paramFile) {
        this.paramFile = paramFile;
    }

    public String getContextPath() {

        /*if(null == resultPath || "".equals(resultPath)) return "";

        contextPath = resultPath.substring( resultPath.indexOf(Configuration.getDataHome()) + Configuration.getDataHome().length() );

        if(!contextPath.startsWith(File.separator))
            contextPath = File.separator + contextPath;

        contextPath = "/ip2_data" + contextPath;
        return contextPath;*/

        return null;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPhospho() {
        File f = new File( getResultPath() + File.separator + "phospho/DTASelect-filter.txt.phospho");//SubsetsASCORE.csv" );

        return f.exists();
    }

    public void setPhospho(boolean phospho) {
        this.phospho = phospho;
    }

    public boolean isN15enrich() {
        //boolean getN15enrich() {
        File f = new File( getResultPath() + File.separator + "n15enrich/census-out.txt");
        return f.exists();
    }

    public void setN15enrich(boolean n15enrich) {
        this.n15enrich = n15enrich;
    }

    public boolean isUniprotDb() {
        return DatabaseUtil.isUniprotDb(resultPath + File.separator + "sequest.params");
    }

    public boolean isSubset() {
        return subset;
    }

    public void setSubset(boolean subset) {
        this.subset = subset;
    }


    public int getSqtFileSize() {
        if(this.sqtFileSize>0)
            return this.sqtFileSize;

        int count=0;
        if(this.resultPath == null){
            return count;
        }else {

            File f = new File(this.resultPath);
            String[] arr = f.list();

            if (null == arr)
                return count;

            for (String each : arr) {
                if (each.endsWith(".sqt")) {

                    File sfile = new File(this.resultPath + File.separator + each);
                    if (sfile.length() > 0)
                        count++;
                }
            }

            this.sqtFileSize = count;

            return sqtFileSize;
        }

    }

    public void setSqtFileSize(int sqtFileSize) {
        this.sqtFileSize = sqtFileSize;
    }

    public String getSpectrumOutputFilePath() {
        return spectrumOutputFilePath;
    }

    public void setSpectrumOutputFilePath(String spectrumOutputFilePath) {
        this.spectrumOutputFilePath = spectrumOutputFilePath;
    }

    public boolean isSpectrumInProgress() {
        return spectrumInProgress;
    }

    public void setSpectrumInProgress(boolean spectrumInProgress) {
        this.spectrumInProgress = spectrumInProgress;
    }

    public boolean isMs1FileExist() {

        ArrayList<String> list  = FileFilterUtil.getFilesBySuffix(resultPath + "../../spectra", "ms1");

        if(list.size()<=0) return false;
        else return true;

    }

    public void setMs1FileExist(boolean ms1FileExist) {
        this.ms1FileExist = ms1FileExist;
    }

    public Integer getSearchedScanCount() {
        return searchedScanCount;
    }

    public void setSearchedScanCount(Integer searchedScanCount) {
        this.searchedScanCount = searchedScanCount;
    }

    public double getSearchedScanRatio() {
        return searchedScanRatio;
    }

    public void setSearchedScanRatio(Integer searchedScanRatio) {
        this.searchedScanRatio = searchedScanRatio;
    }



}
