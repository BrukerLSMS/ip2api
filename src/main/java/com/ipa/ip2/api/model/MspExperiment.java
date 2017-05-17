package com.ipa.ip2.api.model;

import com.ipa.ip2.api.db.HibernateUtils;
import com.ipa.ip2.api.util.OsCheck;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by amit on 15/02/17.
 */

@Entity
@Table(name = "msp_experiment")
@NamedQueries({@NamedQuery(name = "MspExperiment.findById", query = "SELECT m FROM MspExperiment m WHERE m.id = :id"), @NamedQuery(name = "MspExperiment.findByProjectId", query = "SELECT m FROM MspExperiment m WHERE m.projectId = :projectId"), @NamedQuery(name = "MspExperiment.findByMassSpecInstrumentId", query = "SELECT m FROM MspExperiment m WHERE m.massSpecInstrumentId = :massSpecInstrumentId"), @NamedQuery(name = "MspExperiment.findByUsersId", query = "SELECT m FROM MspExperiment m WHERE m.usersId = :usersId"), @NamedQuery(name = "MspExperiment.findByStartDate", query = "SELECT m FROM MspExperiment m WHERE m.startDate = :startDate"), @NamedQuery(name = "MspExperiment.findByLatestChangeTime", query = "SELECT m FROM MspExperiment m WHERE m.latestChangeTime = :latestChangeTime"), @NamedQuery(name = "MspExperiment.findBySampleName", query = "SELECT m FROM MspExperiment m WHERE m.sampleName = :sampleName"), @NamedQuery(name = "MspExperiment.findByHomeFolder", query = "SELECT m FROM MspExperiment m WHERE m.homeFolder = :homeFolder"), @NamedQuery(name = "MspExperiment.findByExtractor", query = "SELECT m FROM MspExperiment m WHERE m.extractor = :extractor"), @NamedQuery(name = "MspExperiment.findByCreatedDate", query = "SELECT m FROM MspExperiment m WHERE m.createdDate = :createdDate")})
public class MspExperiment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "project_id", nullable = false)
    private int projectId;
    @Column(name = "mass_spec_instrument_id", nullable = false)
    private int massSpecInstrumentId;
    @Column(name = "users_id")
    private Integer usersId;
    @Lob
    @Column(name = "description")
    private String description;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "latest_change_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date latestChangeTime;
    @Column(name = "sample_name")
    private String sampleName;
    @Lob
    @Column(name = "sample_description")
    private String sampleDescription;
    @Column(name = "home_folder")
    private String homeFolder;
    @Lob
    @Column(name = "spectra_file_names")
    private String spectraFileNames;
    @Column(name = "extractor")
    private String extractor;
    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "protein_ids")
    private int proteinIds;
    @Lob
    @Column(name = "search_params")
    private String searchParams;
    @Column(name = "protein_FP")
    private double proteinFP;
    @Column(name = "peptide_FP")
    private double peptideFP;
    @Column(name = "spectrum_FP")
    private double spectrumFP;
    @Column(name = "scan_count")
    private Integer scanCount;

    @Transient
    private String instrumentName;
    @Transient
    private List<DbSearch> dbSearch;
    @Transient
    private Boolean isLabeledQuantitationDone;
    @Transient
    private String year;
    @Transient
    private String month;
    @Transient
    private String date;
    @Transient
    private int fileSize;
    @Transient
    private int ms1FileSize=-1;
    @Transient
    private int ms2FileSize=-1;
    @Transient
    private Boolean isIdentificationDone;


    public MspExperiment() {
    }

    public MspExperiment(Integer id) {
        this.id = id;
    }

    public MspExperiment(Integer id, int projectId, int massSpecInstrumentId, Date createdDate) {
        this.id = id;
        this.projectId = projectId;
        this.massSpecInstrumentId = massSpecInstrumentId;
        this.createdDate = createdDate;
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

    public int getMassSpecInstrumentId() {
        return massSpecInstrumentId;
    }

    public void setMassSpecInstrumentId(int massSpecInstrumentId) {
        this.massSpecInstrumentId = massSpecInstrumentId;
    }

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getLatestChangeTime() {
        return latestChangeTime;
    }

    public void setLatestChangeTime(Date latestChangeTime) {
        this.latestChangeTime = latestChangeTime;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getSampleDescription() {
        return sampleDescription;
    }

    public void setSampleDescription(String sampleDescription) {
        this.sampleDescription = sampleDescription;
    }

    public String getHomeFolder() {
        if(!StringUtils.isBlank(homeFolder)){
            try {
                OsCheck.OSType ostype = OsCheck.getOperatingSystemType();
                switch (ostype) {
                    case Windows:
                        homeFolder = (HibernateUtils.getInstance().getRelativePath() + homeFolder).replaceAll("/", File.separator);
                        break;
                    case MacOS:
                    case Linux:
                    case Other:
                        homeFolder = HibernateUtils.getInstance().getRelativePath() + homeFolder;
                        break;
                }
            } catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
        return homeFolder;
    }

    public void setHomeFolder(String homeFolder) {
        this.homeFolder = homeFolder;
    }

    public String getSpectraFileNames() {
        return spectraFileNames;
    }

    public void setSpectraFileNames(String spectraFileNames) {
        this.spectraFileNames = spectraFileNames;
    }

    public String getExtractor() {
        return extractor;
    }

    public void setExtractor(String extractor) {
        this.extractor = extractor;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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
        if (!(object instanceof MspExperiment)) {
            return false;
        }
        MspExperiment other = (MspExperiment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ipa.ip2.model.db.MspExperiment[id=" + id + "]";
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    public List<DbSearch> getDbSearch() {
        return dbSearch;
    }

    public void setDbSearch(List<DbSearch> dbSearch) {
        this.dbSearch = dbSearch;
    }

    public int getProteinIds() {
        /*if (this.proteinIds <= 0) {
            new MspExperimentUtils().caclutateProteinIdsAndSearchParams(this);
        }*/
        return this.proteinIds;
    }

    public String getSearchParams() {
        /*if (StringUtils.isBlank(this.searchParams)) {
            new MspExperimentUtils().caclutateProteinIdsAndSearchParams(this);
        }*/
        return this.searchParams;
    }

    public void setSearchParams(String searchParams) {
        this.searchParams = searchParams;
    }


    public void setProteinIds(int proteinIds) {
        this.proteinIds = proteinIds;
    }

    public Boolean getIsLabeledQuantitationDone() {

        try {
            if(null == dbSearch)
                return false;

            DbSearch search = dbSearch.get(0);
            Quantitation q = search.getQuantitation();

            if(null != q) {
                String quantPath = search.getQuantitation().getPath();
                File f = new File(quantPath + File.separator + "census-out.txt");

                if(f.exists() && f.length()>0)
                    return true;
            }

            return search.getIsLabeledQuantitationDone();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
            return false;
        }
    }

    public void setIsLabeledQuantitationDone(Boolean f) {
        isLabeledQuantitationDone = f;
    }


    public void setProteinFP(double proteinFP)
    {
        this.proteinFP = proteinFP;
    }

    public double getProteinFP() {
        /*if (this.proteinFP <= 0) {
            new MspExperimentUtils().caclutateProteinIdsAndSearchParams(this);
        }*/
        return proteinFP;
    }

    public void setPeptideFP(double peptideFP)
    {
        this.peptideFP = peptideFP;
    }

    public double getPeptideFP() {
        /*if (this.peptideFP <= 0) {
            new MspExperimentUtils().caclutateProteinIdsAndSearchParams(this);
        }*/
        return peptideFP;
    }
    public void setSpectrumFP(double spectrumFP)
    {
        this.spectrumFP = spectrumFP;
    }

    public double getSpectrumFP() {
        /*if (this.spectrumFP <= 0) {
            new MspExperimentUtils().caclutateProteinIdsAndSearchParams(this);
        }*/
        return spectrumFP;
    }

    public String getYear() {
        return getDateValue(0);
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return getDateValue(1);
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return getDateValue(2);
    }

    private String getDateValue(int index) {
        String str = this.startDate.toString();
        String[] arr = str.split("-");

        if(arr.length>=3)
            return arr[index];

        return "NA";

    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getFileSize() {

        int count=0;

        File f = new File( this.getHomeFolder() + File.separator + "spectra");

        if(fileSize>0)
            return fileSize;

        String[] arr = f.list();

        if(null == arr)
            return count;

        for(String each:arr) {

            if(each.endsWith("ms1") || each.endsWith("ms2"))
                count++;
        }

        return count;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    private int getFileSize(String extension) {
        int count=0;

        File f = new File( this.getHomeFolder() + File.separator + "spectra");

        String[] arr = f.list();

        if(null == arr)
            return count;

        for(String each:arr) {

            if(each.endsWith(extension))
                count++;
        }

        return count;
    }


    public int getMs1FileSize() {

        if(this.ms1FileSize>0)
            return this.ms1FileSize;

        this.ms1FileSize = getFileSize("ms1");

        return ms1FileSize;
    }

    public void setMs1FileSize(int ms1FileSize) {
        this.ms1FileSize = ms1FileSize;
    }

    public int getMs2FileSize() {
        if(this.ms2FileSize>0)
            return this.ms2FileSize;

        this.ms2FileSize = getFileSize("ms2");

        return ms2FileSize;
    }

    public void setMs2FileSize(int ms2FileSize) {
        this.ms2FileSize = ms2FileSize;
    }

    public Boolean getIsIdentificationDone() {
        try {
            if(null == dbSearch || dbSearch.size() <= 0) {
                return false;
            }
            else {
                boolean allSearchFinished = true;
                for (DbSearch search : dbSearch) {
                    if (!search.getIsFinished()) {
                        allSearchFinished = false;
                    }
                }
                return allSearchFinished;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
            return false;
        }
    }

    public Integer getScanCount() {
        return scanCount;
    }

    public void setScanCount(Integer scanCount) {
        this.scanCount = scanCount;
    }

}
