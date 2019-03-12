package repository;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import org.apache.jena.ontology.Individual;
import org.apache.jena.rdf.model.ModelFactory;

import javaXSDclass.AbsorbedDosePerVOIType;
import javaXSDclass.AttenuatorType;
import javaXSDclass.CTSegmentation;
import javaXSDclass.CalculationOfAbsorbedDosesInVOIs;
import javaXSDclass.CalculationOfVoxelMap;
import javaXSDclass.DICOMData;
import javaXSDclass.MethodSettingType;
import javaXSDclass.NonDICOMData;
import javaXSDclass.NonDicomFileSetDescriptor;
import javaXSDclass.NonDicomFileSetDescriptor.WP2Subtask212WorkflowData;
import javaXSDclass.SimpleCTMonteCarloDosimetry;
import javaXSDclass.VOI;
import javaXSDclass.VoxelBasedDistributionOfAbsorbedDoseType;



public class TranslateNonDicomData extends OntologyPopulator {

	static Hashtable<String, Individual> tableVOI = new Hashtable<String, Individual>();
	static Hashtable<String, Individual> tableVOIorgans = new Hashtable<String, Individual>();
	static Hashtable<String, String> tableVOIname = new Hashtable<String, String>();

	static Individual researchClinicalStudy;
	
	public static void translateNonDicomData(NonDicomFileSetDescriptor nonDicomFileSetDescriptor) { // 1st function to read XML, check what is inside and call the appropritae function
		populateModel = ModelFactory.createOntologyModel();
		if (dataModel==null) {model = Application.dataModel;}

		if (model==null) {model = Application.getModel();}
		
		if (nonDicomFileSetDescriptor.getWP2Subtask212WorkflowData()!=null) {
			researchClinicalStudy = createIndiv("clinical_research_study_subtask2.1.2", model.getResource(racineURI+"clinical_research_study"));
			retrieveSubtastk212(nonDicomFileSetDescriptor.getWP2Subtask212WorkflowData().iterator());
		}
		
		if (nonDicomFileSetDescriptor.getCalibrationWorkflow()!=null) {
			
		}
		
		if (nonDicomFileSetDescriptor.getHybridDosimetryworkflow()!=null) {
			
		}
		
		if (nonDicomFileSetDescriptor.getTwoDimDosimetryworkflow()!=null) {
			
		}
		
		if (nonDicomFileSetDescriptor.getThreeDimDosimetrySlide1Workflow()!=null) {
			
		}
		
		if (nonDicomFileSetDescriptor.getThreeDimDosimetrySlide2Workflow()!=null) {
			
		}
		
		if (nonDicomFileSetDescriptor.getRegistrationVOISegmentation()!=null) {
			
		}
		
		// to be completed when with other subtasks

	}
	
 	public static void retrieveSubtastk212(Iterator<WP2Subtask212WorkflowData> subtask212Iter) {
		WP2Subtask212WorkflowData subtask212;
		CTSegmentation ctSegmentation =  null; Individual imageSegmentation = null; Individual institution;
		Individual image; Individual segMeth; MethodSettingType settingTest;
		Individual ctDosiMc = null; Individual absorbedDoseVoi;
		Iterator<AbsorbedDosePerVOIType> absorbedDosePerVOIlist; 
		AbsorbedDosePerVOIType absorbedDosePerVOI; 

		Individual settingMC; Individual indivSetting;
		Individual nonDicomFile;
		Iterator<AttenuatorType> attenuatorUsedIterator; Individual attenuator;
		Individual calculationof3Ddosemap = null; 
		Individual MCmethod ; AttenuatorType attenuatorParam; Iterator<MethodSettingType> mcSettingsIter;
		MethodSettingType mcSetting; String MCsoftware;  Iterator<String> imagingDeviceIter; 
		String imagingDevice; Iterator<String> MCsoftwareIter; Individual role_of_responsible_organization = null;
		Individual voxelBasedDistributionOfAbsorbedDoseType = null; Individual modelOfImagingDevice;
		VoxelBasedDistributionOfAbsorbedDoseType voxelBasedDistribution;
		Individual ctImageDataSet = null; Individual roleOfOrganization;
		Individual calculationOfMeanAbsorbedDosesinVOIs; 
		ArrayList<Individual> listeDosiMc = new ArrayList<Individual>();
		//Individual modelAttenuator;
		
		if (memory==null) {memory = Application.memory;}
		
		while (subtask212Iter.hasNext()) {
			subtask212 = subtask212Iter.next();

			SimpleCTMonteCarloDosimetry ctMonteCarloDosimetry; DICOMData imageUsed;
			
			if (subtask212.getSimpleCTMonteCarloDosimetry() !=null) {
				Iterator<SimpleCTMonteCarloDosimetry> ctMonteCarloDosimetryIter = subtask212.getSimpleCTMonteCarloDosimetry().iterator();
				while (ctMonteCarloDosimetryIter.hasNext()) {
					ctMonteCarloDosimetry = ctMonteCarloDosimetryIter.next();
					ctDosiMc = createIndiv(generateName("Monte_Carlo_CT_dosimetry"), model.getResource(racineURI+"Monte_Carlo_CT_dosimetry")); // process
					listeDosiMc.add(ctDosiMc);

					imageUsed = ctMonteCarloDosimetry.getCalculationOfVoxelMap().getDICOMCTImageDataUsed();
					
					String series = imageUsed.getDICOMSeriesUID();
					String study = imageUsed.getDICOMStudyUID();

					ctImageDataSet = memory.getCtDataSet(series, study);
				}
			} //fin simpleCTMonteCarloDosimetry

			if (subtask212.getCTSegmentation()!=null) {
				ctSegmentation = subtask212.getCTSegmentation();

				imageSegmentation = createIndiv(generateName("image_segmentation"), model.getResource(racineURI+"image_segmentation")); //process
				if (ctImageDataSet!=null) {
					addObjectProperty(imageSegmentation, racineURI+"has_specified_input", ctImageDataSet);
				}

				String institName = subtask212.getCTSegmentation().getProcessExecutionContext().getPerformingInstitution();
				institution = memory.getInstitution(institName);
				roleOfOrganization = memory.getRoleOfResponsibleOrganization(institName);
				
				addObjectProperty(institution, racineObo+"BFO_0000161", roleOfOrganization);
				addObjectProperty(roleOfOrganization,racineObo+"BFO_0000054",imageSegmentation);

				image = createIndiv(generateName("Image"), model.getResource(racineURI+"CT_image_reconstruction"));
				if (ctSegmentation.getDICOMImageUsed().getDICOMSeriesUID()!=null) {
					addDataProperty(image, racineURI+"has_DICOM_series_instance_UID", ctSegmentation.getDICOMImageUsed().getDICOMSeriesUID());
				}
				if (ctSegmentation.getDICOMImageUsed().getDICOMStudyUID()!=null) {
					addDataProperty(image, racineURI+"has_DICOM_study_instance_UID", ctSegmentation.getDICOMImageUsed().getDICOMStudyUID());
				}

				if (ctSegmentation.getSegmentationMethodUsed().getSegmentationMethod()!=null) {
					if (ctSegmentation.getSegmentationMethodUsed().getSegmentationMethod().compareToIgnoreCase("Interactive contour segmentation")==0) {
						segMeth = createIndiv(generateName("interactive_contour_segmentation_method"), model.getResource(racineURI+"interactive_contour_segmentation_method"));
					} else {
						segMeth = createIndiv(generateName("image_segmentation_method"), model.getResource(racineURI+"image_segmentation_method"));
					}
					addObjectProperty(imageSegmentation, racineURI+"has_protocol", segMeth);
					if (ctSegmentation.getSegmentationMethodUsed().getSoftwareName()!=null) {
						for (int i =0; i<ctSegmentation.getSegmentationMethodUsed().getSoftwareName().size(); i++) {
							addObjectProperty(imageSegmentation, racineURI+"has_agent", memory.getSoftware(ctSegmentation.getSegmentationMethodUsed().getSoftwareName().get(i)));
						}
					}

					if (ctSegmentation.getSegmentationMethodUsed().getSegmentationMethodSetting()!=null) {
						for (int i =0; i<ctSegmentation.getSegmentationMethodUsed().getSegmentationMethodSetting().size(); i++) {
							settingTest = ctSegmentation.getSegmentationMethodUsed().getSegmentationMethodSetting().get(i);
							switch (settingTest.getMethodSettingValue()) {
							case("KVP"):
								indivSetting = createIndiv(generateName("KVP"), model.getResource(racineDCM+"113733"));break;
							default:
								indivSetting = createIndiv(generateName(settingTest.getMethodSettingValue()), model.getResource(racineURI+"device_setting"));break;
							}
							addObjectProperty(indivSetting, racineObo+"IAO_0000039",  getUnit(settingTest.getMethodSettingUnit()));
							addDataProperty(indivSetting,racineObo+"IAO_0000004",settingTest.getMethodSettingUnit());
						}
					}
				}

				if (ctSegmentation.getVOIDescriptorProduced()!=null) {
					Iterator<VOI> voiIterator = ctSegmentation.getVOIDescriptorProduced().iterator();
					while (voiIterator.hasNext()) {
						VOI voiDescriptor = voiIterator.next();

						Individual voiIndiv = createIndiv(generateName("VOI"),model.getResource(racineURI+"VOI"));
						tableVOI.put(voiDescriptor.getVOIIdentifier(), voiIndiv);
						
						if (ctSegmentation.getDICOMImageUsed().getDICOMStudyUID()!=null && ctSegmentation.getDICOMImageUsed().getDICOMSeriesUID()!=null) {
							patient = memory.getPatient(ctSegmentation.getDICOMImageUsed().getDICOMSeriesUID(), ctSegmentation.getDICOMImageUsed().getDICOMStudyUID()); 
							addObjectProperty(voiIndiv, racineURI+"has_patient",patient);
						}
						
						addObjectProperty(imageSegmentation,racineURI+"has_specified_output", voiIndiv);
						addObjectProperty(voiIndiv, racineURI+"is_specified_output_of", imageSegmentation);
						
						Individual organ = getOrgan(voiDescriptor.getOrganOrTissue());
						addObjectProperty(voiIndiv,racineURI+"represents", organ);	
						tableVOIorgans.put(voiDescriptor.getVOIIdentifier(), organ);
						
						if (voiDescriptor.getNonDICOMVOIContainer() != null) {
							Iterator<NonDICOMData> listeVOI = voiDescriptor.getNonDICOMVOIContainer().iterator();
							while (listeVOI.hasNext()) {
								NonDICOMData voicontainer = listeVOI.next();
								voiIndiv.addOntClass(model.getResource(racineURI+"non_DICOM_file"));

								if (voicontainer.getFHIRIdentifier() != null) {
									String fhirId = voicontainer.getFHIRIdentifier();
									addDataProperty(voiIndiv,racineURI+"has_IRDBB_FHIR_handle","/fhir/Binary/"+fhirId);
									
									Iterator<String> listFileNamesIter = voicontainer.getNonDICOMDataFileName().iterator();
									while (listFileNamesIter.hasNext()) {
										String name = listFileNamesIter.next();
										addDataProperty(voiIndiv, racineURI+"has_name", name);
										tableVOIname.put(voiDescriptor.getVOIIdentifier(), name);
									}
								}
								
								switch (voicontainer.getNonDICOMDataFormat()) {
								case ("zipped imageJ contours format"):
									i = createIndiv(model.getResource(racineURI+"zipped_imageJ_contours_format"));
								addObjectProperty(voiIndiv, racineURI+"has_format", i);
								break;
								case ("TIFF format embedding imageJ contours"):
									i = createIndiv(model.getResource(racineURI+"TIFF_format_embedding_imageJ_contours"));
								addObjectProperty(voiIndiv, racineURI+"has_format", i);
								break;	
								case ("zipped pseudo DICOM ImpactMC"):
									i = createIndiv(model.getResource(racineURI+"zipped_pseudo_DICOM_ImpactMC"));
								addObjectProperty(voiIndiv, racineURI+"has_format", i);
								break;		
								default: 
									logger.warn("Unknown nonDICOMDataFormat : "+voicontainer.getNonDICOMDataFormat());
									System.out.println("Unknown nonDICOMDataFormat : "+voicontainer.getNonDICOMDataFormat());
									break;
								}
							}
						}
					}
				}
			} //fin CT segmentation

			Iterator<SimpleCTMonteCarloDosimetry> ctMonteCarloDosimetryIter2 = subtask212.getSimpleCTMonteCarloDosimetry().iterator();
			int j=0;
			while (ctMonteCarloDosimetryIter2.hasNext()) {
				ctMonteCarloDosimetry = ctMonteCarloDosimetryIter2.next();
				CalculationOfVoxelMap calculationOfVoxelMap = ctMonteCarloDosimetry.getCalculationOfVoxelMap() ;
				calculationof3Ddosemap = createIndiv(generateName("calculation_of_a_3D_dose_map"), model.getResource(racineURI+"calculation_of_a_3D_dose_map"));  

				addObjectProperty(listeDosiMc.get(j), racineObo+"BFO_0000117", calculationof3Ddosemap);
				addDataProperty(calculationof3Ddosemap, racineURI+"has_beginning", calculationOfVoxelMap.getProcessExecutionContext().getDateTimeProcessStarted());

				String institutionName = calculationOfVoxelMap.getProcessExecutionContext().getPerformingInstitution();
				institution = memory.getInstitution(institutionName);
				role_of_responsible_organization = memory.getRoleOfResponsibleOrganization(institutionName);

				addObjectProperty(role_of_responsible_organization,racineObo+"BFO_0000054", calculationof3Ddosemap);
				
				if (institution!=null) {
					addObjectProperty(institution,racineObo+"BFO_0000161", role_of_responsible_organization); //has role at all time
				}
				if (ctDosiMc!=null) {
					addObjectProperty(role_of_responsible_organization,racineObo+"BFO_0000054", ctDosiMc);
				}

				if (calculationOfVoxelMap.getAttenuatorUsed()!=null) {
					attenuatorUsedIterator = calculationOfVoxelMap.getAttenuatorUsed().iterator();
					while (attenuatorUsedIterator.hasNext()) {
						attenuatorParam = attenuatorUsedIterator.next();

						attenuator = createIndiv(generateName("Attenuator"), model.createResource(racineDCM+"128472"));  

						addObjectProperty(calculationof3Ddosemap, racineURI+"has_setting", attenuator); 

						addDataProperty(attenuator, racineURI+"has_description", attenuatorParam.getAttenuatorCategory());
						addDataProperty(attenuator, racineURI+"has_description", attenuatorParam.getEquivalentAttenuatorDescription());

						Individual modelAttenuator = createIndiv(generateName("Attenuator_Model"), model.createResource(racineDCM+"128472"));
						addDataProperty(attenuator, racineURI+"has_name", attenuatorParam.getEquivalentAttenuatorModel());
						//addObjectProperty(attenuator, racineURI+"has_setting", attenuatorParam.equivalentAttenuatorMaterial);
						//TODO décommneter au dessus et compléter

						i = createIndiv(generateName("Attenuator_Thickness"), model.getResource(racineDCM+"128469"));

						addObjectProperty(i, racineObo+"IAO_0000039", getUnit(attenuatorParam.getEquivalentAttenuatorThicknessUnit()));
						addDataProperty(i, racineObo+"IAO_0000004", attenuatorParam.getEquivalentAttenuatorThicknessValue());				
					}
				}

				if (calculationOfVoxelMap.getMonteCarloMethodUsed()!=null) {
					
					MCmethod = memory.getMCmethod(calculationOfVoxelMap.getMonteCarloMethodUsed().getMonteCarloMethod());

					addObjectProperty(calculationof3Ddosemap, racineURI+"has_protocol", MCmethod);
					
					if (calculationOfVoxelMap.getMonteCarloMethodUsed().getMonteCarloMethodSetting()!=null) {
						mcSettingsIter = calculationOfVoxelMap.getMonteCarloMethodUsed().getMonteCarloMethodSetting().iterator();
						while (mcSettingsIter.hasNext()) {
							mcSetting = mcSettingsIter.next();
							switch (mcSetting.getMethodSetting()) {
							case ("Simulation of intensity modulation"):
								if (mcSetting.getMethodSettingValue().equalsIgnoreCase("no use of X ray modulation")) {
									settingMC = createIndiv(generateName("no_use_of_X_ray_modulation"), model.getResource(racineURI+"no_use_of_X_ray_modulation")); 
								} else {
									settingMC = createIndiv(generateName("use_of_X_ray_modulation"), model.getResource(racineURI+"use_of_X_ray_modulation")); 
								}
								break;
							case ("KVP"):
								settingMC = createIndiv(generateName("KVP"), model.getResource(racineDCM+"113733"));
								break;
							default:
								settingMC = createIndiv(generateName("device_setting"), model.getResource(racineURI+"device_setting"));
								logger.warn("Unknown mcSetting : "+mcSetting.getMethodSetting());
								System.out.println("Unknown mcSetting : "+mcSetting.getMethodSetting());
								break;
							}
							if (mcSetting.getMethodSettingUnit()!=null) {
								addObjectProperty(settingMC, racineObo+"IAO_0000039", getUnit(mcSetting.getMethodSettingUnit()));
							}
							if (mcSetting.getMethodSettingValue()!=null && mcSetting.getMethodSetting()!="Simulation of intensity modulation") {
								addDataProperty(settingMC, racineObo+"IAO_0000004", mcSetting.getMethodSettingValue());	
							}
							if (settingMC!=null) {
								addObjectProperty(calculationof3Ddosemap, racineURI+"has_setting", settingMC);
							}
						}
					}

					imagingDeviceIter = calculationOfVoxelMap.getMonteCarloMethodUsed().getSimulatedImagingDevice().iterator();
					while (imagingDeviceIter.hasNext()) {
						imagingDevice = imagingDeviceIter.next();
						i = createIndiv(model.getResource(racineURI+"medical_imaging_device")); 
						addDataProperty(i, racineURI+"has_name", imagingDevice);
						addObjectProperty(i, racineURI+"used_as_instrument_in", ctDosiMc); 
						modelOfImagingDevice = createIndiv(model.getResource(racineURI+"model_of_medical_imaging_device"));
						addObjectProperty(i, racineURI+"refers_to_device", modelOfImagingDevice);
					}

					MCsoftwareIter = calculationOfVoxelMap.getMonteCarloMethodUsed().getSoftwareName().iterator();
					while (MCsoftwareIter.hasNext()) {
						MCsoftware = MCsoftwareIter.next();
						addObjectProperty(calculationof3Ddosemap, racineURI+"has_agent", memory.getSoftware(MCsoftware)); 
					}
				}

				if (calculationOfVoxelMap.getVoxelBasedDistributionOfAbsorbedDoseProduced()!=null) {
					voxelBasedDistribution = calculationOfVoxelMap.getVoxelBasedDistributionOfAbsorbedDoseProduced();
					voxelBasedDistributionOfAbsorbedDoseType = createIndiv(generateName("3D_dose_map"), model.getResource(racineDCM+"128487"));
					//Individual doseMap = voxelBasedDistributionOfAbsorbedDoseType;

					if (calculationof3Ddosemap!=null) {
						addObjectProperty(calculationof3Ddosemap, racineURI+"has_specified_output", voxelBasedDistributionOfAbsorbedDoseType);
						if (ctImageDataSet!=null) {
							addObjectProperty(calculationof3Ddosemap, racineURI+"has_specified_input", ctImageDataSet);
						}
					}
					if (voxelBasedDistribution.getAbsorbedDoseUnit()!=null) {
						addObjectProperty(voxelBasedDistributionOfAbsorbedDoseType, racineObo+"IAO_0000039", getUnit(voxelBasedDistribution.getAbsorbedDoseUnit()));
					}	
					
					addDataProperty(voxelBasedDistributionOfAbsorbedDoseType, racineURI+"has_description", voxelBasedDistribution.getVoxelBasedDistributionOfAbsorbedDoseCategory());

					if (voxelBasedDistribution.getNonDICOMVoxelBasedAbsorbedDoseDistribution()!=null) {

						for (int n=0; n<voxelBasedDistribution.getNonDICOMVoxelBasedAbsorbedDoseDistribution().getNonDICOMDataFileName().size(); n++) {

							switch (voxelBasedDistribution.getNonDICOMVoxelBasedAbsorbedDoseDistribution().getNonDICOMDataClass()) {
							case ("VOI"):
								voxelBasedDistributionOfAbsorbedDoseType.addOntClass(model.getResource(racineURI+"VOI"));
								break;
							case ("VOI superimposed on images"):
								voxelBasedDistributionOfAbsorbedDoseType.addOntClass(model.getResource(racineURI+"VOI_superimposed_on_images"));
								break;
							case ("3D dose map"):
								voxelBasedDistributionOfAbsorbedDoseType.addOntClass(model.getResource(racineDCM+"128487"));
								break;
							default:
								logger.warn("Unknown : voxelBasedDistribution.getNonDICOMVoxelBasedAbsorbedDoseDistribution().nonDICOMDataClass");
								System.out.println("Unknown : voxelBasedDistribution.getNonDICOMVoxelBasedAbsorbedDoseDistribution().nonDICOMDataClass");
							}	
							if(voxelBasedDistribution.getNonDICOMVoxelBasedAbsorbedDoseDistribution().getFHIRIdentifier()!=null) {
								addDataProperty(voxelBasedDistributionOfAbsorbedDoseType, racineURI+"has_IRDBB_FHIR_handle", 
										"/fhir/Binary/"+voxelBasedDistribution.getNonDICOMVoxelBasedAbsorbedDoseDistribution().getFHIRIdentifier());
								addObjectProperty(voxelBasedDistributionOfAbsorbedDoseType,racineURI+"has_patient",patient);
							}
							nonDicomFile = createIndiv(generateName("nonDicomFile"),model.getResource(racineURI+"non_DICOM_file"));
							addDataProperty(nonDicomFile, racineURI+"has_name", voxelBasedDistribution.getNonDICOMVoxelBasedAbsorbedDoseDistribution().getNonDICOMDataFileName().get(n));
							switch (voxelBasedDistribution.getNonDICOMVoxelBasedAbsorbedDoseDistribution().getNonDICOMDataFormat()) {
							case ("zipped imageJ contours format"):
								i = createIndiv(model.getResource(racineURI+"zipped_imageJ_contours_format"));
								addObjectProperty(nonDicomFile, racineURI+"has_format", i);
								addObjectProperty(voxelBasedDistributionOfAbsorbedDoseType, racineURI+"has_format", i);
								break;
							case ("TIFF format embedding imageJ contours"):
								i = createIndiv(model.getResource(racineURI+"TIFF_format_embedding_imageJ_contours"));
								addObjectProperty(nonDicomFile, racineURI+"has_format", i);
								addObjectProperty(voxelBasedDistributionOfAbsorbedDoseType, racineURI+"has_format", i);
								break;	
							case ("zipped pseudo DICOM ImpactMC"):
								i = createIndiv(model.getResource(racineURI+"zipped_pseudo_DICOM_ImpactMC"));
								addObjectProperty(nonDicomFile, racineURI+"has_format", i);
								addObjectProperty(voxelBasedDistributionOfAbsorbedDoseType, racineURI+"has_format", i);
								break;	
							default: 
								logger.warn("Unknown nonDICOMDataFormat : "+voxelBasedDistribution.getNonDICOMVoxelBasedAbsorbedDoseDistribution().getNonDICOMDataFormat());
								System.out.println("Unknown nonDICOMDataFormat : "+voxelBasedDistribution.getNonDICOMVoxelBasedAbsorbedDoseDistribution().getNonDICOMDataFormat());
								break;
							}
						}
					}
				} // fin calculationOf3D`

				if (ctMonteCarloDosimetry.getCalculationOfAbsorbedDosesInVOIs()!=null) {
					CalculationOfAbsorbedDosesInVOIs calculationOfAbsorbedDoses = ctMonteCarloDosimetry.getCalculationOfAbsorbedDosesInVOIs();

					calculationOfMeanAbsorbedDosesinVOIs = createIndiv(generateName("calculation_of_mean_absorbed_doses_in_VOIs"), model.getResource(racineURI+"calculation_of_mean_absorbed_doses_in_VOIs"));

					addObjectProperty(listeDosiMc.get(j), racineObo+"BFO_0000117", calculationOfMeanAbsorbedDosesinVOIs);
					j++;

					if (voxelBasedDistributionOfAbsorbedDoseType!=null) {
						addObjectProperty(calculationOfMeanAbsorbedDosesinVOIs, racineURI+"has_specified_input", voxelBasedDistributionOfAbsorbedDoseType);
					}

					institution = memory.getInstitution(calculationOfAbsorbedDoses.getProcessExecutionContext().getPerformingInstitution());		
					role_of_responsible_organization = memory.getRoleOfResponsibleOrganization(calculationOfAbsorbedDoses.getProcessExecutionContext().getPerformingInstitution());
					addObjectProperty(role_of_responsible_organization,racineObo+"BFO_0000054", calculationOfMeanAbsorbedDosesinVOIs);
					addObjectProperty(institution,racineObo+"BFO_0000161",role_of_responsible_organization); //has role at all time
					addDataProperty(calculationOfMeanAbsorbedDosesinVOIs, racineURI+"has_beginning", calculationOfAbsorbedDoses.getProcessExecutionContext().getDateTimeProcessStarted());

					if (ctMonteCarloDosimetry.getCalculationOfAbsorbedDosesInVOIs().getAbsorbedDosePerVOIProduced()!=null) {
						absorbedDosePerVOIlist = ctMonteCarloDosimetry.getCalculationOfAbsorbedDosesInVOIs().getAbsorbedDosePerVOIProduced().iterator();
						while (absorbedDosePerVOIlist.hasNext()) {
							//System.out.println("\nabsorbedDosePerVOIlist.next\n");
							absorbedDosePerVOI = absorbedDosePerVOIlist.next();
							switch (absorbedDosePerVOI.getAbsorbedDoseCategory()) {
							case "mean absorbed dose normalized to CTDI free in air normalized to tube load":
								absorbedDoseVoi = createIndiv(generateName("mean_absorbed_dose_normalized_to_CTDI_free_in_air_normalized_to_tube_load"),
										model.getResource(racineURI+"mean_absorbed_dose_normalized_to_CTDI_free_in_air_normalized_to_tube_load"));
								break;
							case "mean absorbed dose normalized to CTDI vol normalized to tube load":
								absorbedDoseVoi = createIndiv(generateName("mean_absorbed_dose_normalized_to_CTDI_vol_normalized_to_tube_load"),
										model.getResource(racineURI+"mean_absorbed_dose_normalized_to_CTDI_vol_normalized_to_tube_load"));
								break;
							case "mean absorbed radiation dose":
								absorbedDoseVoi = createIndiv(generateName("Mean_Absorbed_Radiation_Dose"), model.getResource(racineDCM+"128533"));
								break;
							case "maximum absorbed radiation dose":
								absorbedDoseVoi = createIndiv(generateName("Maximum_Absorbed_Radiation_Dose"), model.getResource(racineDCM+"128531"));
								break;
							case "minimum absorbed radiation dose":
								absorbedDoseVoi = createIndiv(generateName("Minimum_Absorbed_Radiation_Dose"), model.getResource(racineDCM+"128532"));
								break;
							case "median absorbed radiation dose":
								absorbedDoseVoi = createIndiv(generateName("Median_Absorbed_Radiation_Dose"), model.getResource(racineDCM+"128539"));
								break;
							case "mode absorbed radiation dose":
								absorbedDoseVoi = createIndiv(generateName("Mode_Absorbed_Radiation_Dose"), model.getResource(racineDCM+"128534"));
								break;
							default : 
								logger.warn("Unknown absorbedDosePerVOICategory : "+absorbedDosePerVOI.getAbsorbedDoseCategory());
								System.out.println("Unknown absorbedDosePerVOICategory : "+absorbedDosePerVOI.getAbsorbedDoseCategory());
								absorbedDoseVoi = createIndiv(generateName("Absorbed_Dose"), model.getResource(racineDCM+"128513"));
								break;
							}
							if (absorbedDosePerVOI.getStatisticalSubCategory()!=null) {
								switch (absorbedDosePerVOI.getStatisticalSubCategory()) {
								case "mean":
									absorbedDoseVoi.addOntClass(model.getResource(racineDCM+"128533"));
									break;
								case "maximum":
									absorbedDoseVoi.addOntClass(model.getResource(racineDCM+"128531"));
									break;
								case "minimum":
									absorbedDoseVoi.addOntClass(model.getResource(racineDCM+"128532"));
									break;
								case "median":
									absorbedDoseVoi.addOntClass(model.getResource(racineDCM+"128539"));
									break;
								case "mode":
									absorbedDoseVoi.addOntClass(model.getResource(racineDCM+"128534"));
									break;
								}
							}
							
							DICOMData imageDataUsed = ctMonteCarloDosimetry.getCalculationOfVoxelMap().getDICOMCTImageDataUsed();
							String series = imageDataUsed.getDICOMSeriesUID();
							patient = memory.getPatient(series, imageDataUsed.getDICOMStudyUID()); 
							if (patient!=null) {
								addObjectProperty(absorbedDoseVoi, racineURI+"has_patient", patient);
								addObjectProperty(researchClinicalStudy, racineURI+"has_patient", patient);
							}
							
							addObjectProperty(calculationOfMeanAbsorbedDosesinVOIs,racineURI+"has_specified_output",absorbedDoseVoi);
							addDataProperty(absorbedDoseVoi, racineObo+"IAO_0000004", absorbedDosePerVOI.getAbsorbedDoseValue()); 
							addObjectProperty(absorbedDoseVoi, racineObo+"IAO_0000039", getUnit(absorbedDosePerVOI.getAbsorbedDoseUnit()));

							addObjectProperty(absorbedDoseVoi,racineURI+"is_dose_absorbed_by",tableVOIorgans.get(absorbedDosePerVOI.getVOIIdentifier()));

						}
					}
				}
			} 
		}
	}
 	
 	public static Individual getOrgan(String organName) {
 		Individual indOrgane = null; 
 		switch (organName) {
		case "bone":
			indOrgane = createIndiv(organName+"_"+patientID, model.getResource(racineObo+"FMA_5018")); 
			break;
		case "breast": case "breasts":
			indOrgane =  createIndiv("breasts"+"_"+patientID, model.getResource(racineURI+"breasts")); 
			break;
		case "esophagus":
			indOrgane =  createIndiv(organName+"_"+patientID, model.getResource(racineObo+"FMA_7131")); 
			break;
		case "heart":
			indOrgane =  createIndiv(organName+"_"+patientID, model.getResource(racineObo+"FMA_7088")); 
			break;
		case "lungs":
			indOrgane =  createIndiv(organName+"_"+patientID, model.getResource(racineObo+"FMA_68877")); 
			break;
		case "skin":
			indOrgane =  createIndiv(organName+"_"+patientID, model.getResource(racineObo+"FMA_7163")); 
			break;
 		}
 		return indOrgane;
 	}
	
}
