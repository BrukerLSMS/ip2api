/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipa.ip2.api.util.labelfree;

import com.ipa.ip2.api.dao.Replicate;
import com.ipa.ip2.api.dao.Replicates;
import rpark.statistics.AnovaUtil;
import rpark.statistics.BHCorrection;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rohan
 * @author Sanghvi
 */
public class LabelfreePeptideParser {
	private List<Integer> sequenceindex = new ArrayList<>();
	private List<Integer> chargestateindex = new ArrayList<>();
	private List<Integer> intensityindex = new ArrayList<>();
	private List<Integer> filenameindex = new ArrayList<>();
	private List<Integer> xCorrindex = new ArrayList<>();
	private List<Integer> dcnindex = new ArrayList<>();
	private List<Integer> dmassindex = new ArrayList<>();
	private List<Integer> sprankindex = new ArrayList<>();
	private List<Integer> spscoreindex = new ArrayList<>();
	private List<Integer> redundancyindex = new ArrayList<>();
	private List<Integer> startrangeindex = new ArrayList<>();
	private List<Integer> endrangeindex = new ArrayList<>();
	private List<Integer> retentiontimeindex = new ArrayList<>();
	private List<Integer> ioninjectionindex = new ArrayList<>();
	private List<Integer> proteinindex = new ArrayList<>();
	private List<Integer> descriptionindex = new ArrayList<>();
        private List<Integer> corrioninjectionintensityindex = new ArrayList<>();
	private ArrayList<Replicates> repList = new ArrayList<Replicates>();
        private List<Integer> scanindex = new ArrayList<>();
	
	private LabelFreePeptideDisplay pepditeDisplay = null;

	private List<LabelfreePeptide> pepList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		//String inputpath = "/data/2/rpark/ip2_data/rpark/Eddie_Huang/labelfree_quant/labelfree_12512/census_labelfree_out_12512_pep.txt";
		//String inputpath = "/data/2/rpark/ip2_data/xudong/AC_mousebrain_exp2_3_repeats_separate/labelfree_quant/labelfree_12806/census_labelfree_out_12806_pep.txt";
		String inputpath=args[0]; //"/home/rampuria/test_data/DIA/census_labelfree_out_13916_pep.txt";
               // LabelfreePeptideParser l = new LabelfreePeptideParser();
		//l.read(inputpath);

		String outputpath= args[1]; //"/home/rampuria/test_data/DIA/msstats_input.csv";
                   
		//List<LabelfreePeptide> phosphoList = l.getPepList();
                System.out.println("");

		exportMSStatsInput(inputpath,outputpath);
                /*
		inputpath = "/data/2/rpark/ip2_data/rpark/Eddie_Huang/labelfree_quant/labelfree_12512/census_labelfree_out_12512_pep.txt-";
		LabelfreePeptideParser l2 = new LabelfreePeptideParser();
		l2.read(inputpath);

		List<LabelfreePeptide> phosphoListAll = l2.getPepList();
		Hashtable<String, LabelfreePeptide> pepAllHt = new Hashtable<>();

		for(LabelfreePeptide pep:phosphoListAll) {
			if(!pep.getSequence().contains("(79.9"))
				pepAllHt.put(pep.getSequence(), pep);
		}

		for(LabelfreePeptide pep:phosphoList) {
			String key = pep.getSequence().replaceAll("\\(79.9663\\)", "");
			//System.out.println(pep.getSequence() + "\t" + key + "\t" + pepAllHt.get(key));
			System.out.println(key);
		}



		//ArrayList<Replicates> repList = l.getRepList();
		System.out.println(l.getPepList().size());*/
	}

	public static void exportMSStatsInput(String inputpath,String outputpath) throws IOException {
		try {
			LabelfreePeptideParser l = new LabelfreePeptideParser();
			l.read(inputpath);
			ArrayList<Replicates> repList = l.getRepList();

			String filePath = inputpath.substring(0, inputpath.lastIndexOf(File.separator));
			//String outfile = filePath+File.separator+"msstats_input.csv";
			BufferedWriter bw = new BufferedWriter(new FileWriter(outputpath));

			List<LabelfreePeptide> phosphoList = l.getPepList();

			bw.write("ProteinName\tPeptideSequence\tPrecursorCharge\tFragmention\tProductCharge\tisotopeLabelType\tCondition\tBioReplicate\tRun\tIntensity\n");
			for (int i = 0; i < phosphoList.size(); i++) {
				LabelfreePeptide peptide = phosphoList.get(i);
				int size = peptide.getIntensity().size();

				int k = 0, count = 0;
				for (int j = 0; j < size; j++) {
					bw.write(peptide.getProtein() + "\t");
					bw.write(peptide.getSequence() + "\t");
					bw.write(peptide.getChargestate() + "\t");
					bw.write("NA\t");
					bw.write("NA\t");
					bw.write("L\t");
					if (count == repList.get(k).getReplicateList().size()) {
						k++;
						count = 0;
					}
					bw.write(repList.get(k).getName() + "\t");
					if (j % 2 == 0) {
						bw.write(1 + "\t");
					} else {
						bw.write(2 + "\t");
					}
					int val = ((j + 1) % size == 0 ? size : (j + 1) % size);
					bw.write(val + "\t");
					bw.write(peptide.getIntensity().get(j) + "\n");
					count++;
				}
			}
			bw.close();
		}catch(Exception ex){
			System.out.println("index out of bound:"+ex.getMessage());
			System.out.println("Can't create msstats_input.csv file");
		}


	}
	
	public void getRepList(String inputpath){
		
		System.out.println("inputpath::"+inputpath);
		File file = new File(inputpath);
		
		try {
			
			FileReader fread = new FileReader(file);
			BufferedReader br = new BufferedReader(fread);
			String eachLine = null;

            while( (eachLine = br.readLine()) != null && !eachLine.startsWith("PLINE")){
            	
            	if(eachLine.startsWith("H\tGROUP_SAMPLE")) {
            	
					String[] arr = eachLine.split("\t");

					Replicates replicate = new Replicates();
					replicate.setName(arr[2]);

					for(int i=3;i<arr.length;i++) {
						Replicate r = new Replicate();
						r.setSampleName(arr[i]);
						replicate.addReplicate(r);
					}

					String words[] = eachLine.split("\t");
					int sampleCount = words.length - 3;

					repList.add(replicate);
            	}
            	if (eachLine.startsWith("SLINE\t")) {
            		break;
    			}
            }
            	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void read(String inputpath) {
		
		pepditeDisplay = new LabelFreePeptideDisplay();
		
		File file = new File(inputpath);
		try {
			FileReader fread = new FileReader(file);
			BufferedReader br = new BufferedReader(fread);
			String eachLine = null;


			int sampleNum=0;
                        List<Double> pvalueList = new ArrayList<>();
			while( (eachLine = br.readLine()) != null && !eachLine.startsWith("PLINE"))
			{
				if(eachLine.startsWith("H\tGROUP_SAMPLE")) {
					String[] arr = eachLine.split("\t");

					Replicates replicate = new Replicates();
					replicate.setName(arr[2]);

					for(int i=3;i<arr.length;i++) {
						Replicate r = new Replicate();
						r.setSampleName(arr[i]);
						replicate.addReplicate(r);

						sampleNum++;
					}

					String words[] = eachLine.split("\t");
					int sampleCount = words.length - 3;

					pepditeDisplay.setGroupSample(sampleCount);


					repList.add(replicate);
				}
			


			if (eachLine.startsWith("SLINE\t")) {
				readHeaderLine(eachLine);
			}
                        
			//while ((eachLine = br.readLine()) != null) {

				if (eachLine.startsWith("S\t")) {
					String[] words = eachLine.split("\t");
					LabelfreePeptide peptide = new LabelfreePeptide();
					for (int i = 0; i < words.length; i++) {
						if (sequenceindex.contains(i)) {
							if (!words[i].startsWith("NA")){
								peptide.setSequence(words[i]);
							}
						} else if (chargestateindex.contains(i)) {
							if (!(words[i].contains("NA"))) {
								peptide.setChargestate(Integer.parseInt(words[i]));
							}
						} else if (intensityindex.contains(i)) {
							peptide.addIntensity(words[i]);
						} else if (filenameindex.contains(i)) {
							peptide.addFilename(words[i]);
						} else if (xCorrindex.contains(i)) {
							peptide.addXCorr(words[i]);
						} else if (descriptionindex.contains(i)) {
							peptide.setDescription(words[i]);
						} else if (proteinindex.contains(i)) {
							peptide.setProtein(words[i]);
						} else if (retentiontimeindex.contains(i)) {
							peptide.addrt(words[i]);
						} else if (ioninjectionindex.contains(i)) {
							peptide.addion(words[i]);
						} else if (dcnindex.contains(i)) {
							peptide.adddcn(words[i]);
						} else if (dmassindex.contains(i)) {
							peptide.adddmass(words[i]);
						} else if (sprankindex.contains(i)) {
							peptide.addsprank(words[i]);
						} else if (startrangeindex.contains(i)) {
							peptide.addstart(words[i]);
						} else if (endrangeindex.contains(i)) {
							peptide.addend(words[i]);
						} else if (redundancyindex.contains(i)) {
							peptide.addred(words[i]);
						} else if (spscoreindex.contains(i)) {
							peptide.addsp(words[i]);
						} else if (corrioninjectionintensityindex.contains(i)) {
							peptide.addcorrintensity(words[i]);
						}else if (scanindex.contains(i)) {
							peptide.addScan(words[i]);
						}
					}
                                        int j=0;
                                        List classes = new ArrayList();
                                        for(int h=0;h<repList.size();h++){
                                            
                                            Replicates rep = repList.get(h);
                                            double [] intensity =new double[rep.getReplicateList().size()];
                                            for(int k=0;k<rep.getReplicateList().size();k++){
												if("NA".equals(peptide.getIntensity().get(j))){
													intensity[k]=0;
												}
												else{
													intensity[k] = Double.parseDouble(peptide.getIntensity().get(j));
												}
                                                j++;
                                            }
                                            classes.add(intensity);
                                            
                                        }
                                        double pvalue = AnovaUtil.calculateAnovaPvalue(classes);
                                        if(Double.isInfinite(pvalue) || Double.isNaN(pvalue))
                                            pvalue = 1;
                                        
                                        peptide.setPvalue(pvalue);
                                        pvalueList.add(pvalue);
                                        
                                        
                                        
                                        
                                        
					pepList.add(peptide);
				}
			//}
                        }
                        int i=0;
                        List<Double> bhcorr = BHCorrection.runBhCorrection(pvalueList);
                        for(LabelfreePeptide p : pepList){
                            p.setQvalue(bhcorr.get(i));
                            i++;
                        }
                        
                        System.out.println("");
                        
                        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		pepditeDisplay.setPepList(pepList);

	}

	private void readHeaderLine(String eachLine) {
		String[] words = eachLine.split("\t");
		for (int i = 1; i < words.length; i++) {
			if (words[i].startsWith("SEQUENCE")) {
				sequenceindex.add(i);
			}else if (words[i].startsWith("SCAN")) {
				scanindex.add(i);
                        }
                        else if (words[i].startsWith("FILENAME")) {
				filenameindex.add(i);
			} else if (words[i].startsWith("CSTATE")) {
				chargestateindex.add(i);
			} else if (words[i].startsWith("INTENSITY")) {
				intensityindex.add(i);
			} else if (words[i].startsWith("XCORR")) {
				xCorrindex.add(i);
			} else if (words[i].startsWith("DCN")) {
				dcnindex.add(i);
			} else if (words[i].startsWith("DMASS")) {
				dmassindex.add(i);
			} else if (words[i].startsWith("SPRANK")) {
				sprankindex.add(i);
			} else if (words[i].startsWith("SPSCORE")) {
				spscoreindex.add(i);
			} else if (words[i].startsWith("REDUNDANCY")) {
				redundancyindex.add(i);
			} else if (words[i].startsWith("STARTRANGE")) {
				startrangeindex.add(i);
			} else if (words[i].startsWith("ENDRANGE")) {
				endrangeindex.add(i);
			} else if (words[i].startsWith("RETENTIONTIME")) {
				retentiontimeindex.add(i);
			} else if (words[i].startsWith("IONINJECTIONTIME")) {
				ioninjectionindex.add(i);
			} else if (words[i].contentEquals("PROTEIN")) {
				proteinindex.add(i);
			} else if (words[i].contains("PROTEIN DESCRIPTION")) {
				descriptionindex.add(i);
			} else if (words[i].contains("CORRIONINJECTION_INTENISTY")) {
				corrioninjectionintensityindex.add(i);
			}
		}
	}

	public List<LabelfreePeptide> getPepList() {
		return pepList;
	}

	public void setPepList(List<LabelfreePeptide> pepList) {
		this.pepList = pepList;
	}

	public LabelFreePeptideDisplay getPepditeDisplay() {
		return pepditeDisplay;
	}

	public void setPepditeDisplay(LabelFreePeptideDisplay pepditeDisplay) {
		this.pepditeDisplay = pepditeDisplay;
	}

	public ArrayList<Replicates> getRepList() {
		return repList;
	}

	public void setRepList(ArrayList<Replicates> repList) {
		this.repList = repList;
	}

}
