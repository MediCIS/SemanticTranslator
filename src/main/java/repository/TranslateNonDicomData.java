package repository;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import org.apache.jena.ontology.Individual;
import org.apache.jena.rdf.model.ModelFactory;

import repository.NonDicomFileSetDescriptor.ReferencedClinicalResearchStudy;
import repository.NonDicomFileSetDescriptor.WP2Subtask212WorkflowData;
import repository.NonDicomFileSetDescriptor.WP2Subtask212WorkflowData.CTSegmentation;
import repository.NonDicomFileSetDescriptor.WP2Subtask212WorkflowData.SimpleCTMonteCarloDosimetry;
import repository.NonDicomFileSetDescriptor.WP2Subtask212WorkflowData.SimpleCTMonteCarloDosimetry.CalculationOfAbsorbedDosesInVOIs;
import repository.NonDicomFileSetDescriptor.WP2Subtask212WorkflowData.SimpleCTMonteCarloDosimetry.CalculationOfVoxelMap;

public class TranslateNonDicomData extends OntologyPopulator {

	static Hashtable<String, Individual> tableVOI = new Hashtable<String, Individual>();
	static Individual researchClinicalStudy;
	
	public static void translateNonDicomData(NonDicomFileSetDescriptor nonDicomFileSetDescriptor) { // 1st function to read XML, check what is inside and call the appropritae function
		populateModel = ModelFactory.createOntologyModel();
		if (model==null) {model = Application.getModel();}
		
		if (nonDicomFileSetDescriptor.wp2Subtask212WorkflowData!=null) {
			researchClinicalStudy = createIndiv("clinical_research_study_subtask2.1.2", model.getResource(racineURI+"clinical_research_study"));
			retrieveSubtastk212(nonDicomFileSetDescriptor.wp2Subtask212WorkflowData.iterator());
		}
		
		// to be completed when with other subtasks
		
	}
	
 	public static void retrieveSubtastk212(Iterator<WP2Subtask212WorkflowData> subtask212Iter) {
		WP2Subtask212WorkflowData subtask212;
		CTSegmentation ctSegmentation =  null; Individual imageSegmentation = null; Individual institution;
		Individual image; Individual segMeth; MethodSettingType settingTest;
		Iterator<VOIDescriptorType> voiIterator; Individual voi; Iterator<NonDICOMDataType> voiDataList;
		SimpleCTMonteCarloDosimetry ctMonteCarloDosimetry; Individual ctDosiMc = null; Individual absorbedDoseVoi;
		Iterator<AbsorbedDosePerVOIType> absorbedDosePerVOIlist; Iterator<SimpleCTMonteCarloDosimetry> ctMonteCarloDosimetryIter;
		AbsorbedDosePerVOIType absorbedDosePerVOI; NonDICOMDataType voiData;
		Iterator<VOIDescriptorType> listVoiDescriptor; VOIDescriptorType voidescriptor;
		Iterator<VoxelBasedDistributionOfAbsorbedDoseType> listVoxelBasedDistributionOfAbsorbedDoseUsed;
		VoxelBasedDistributionOfAbsorbedDoseType voxelBasedDistributionOfAbsorbedDoseUsed;
		Individual settingMC; Individual indivSetting; VOIDescriptorType voiDescriptor; ArrayList<Individual> listOrgans;
		Iterator<NonDICOMDataType> voiDataIterator; Individual voiFile; Individual nonDicomFile;
		Individual nonDICOMVoxelBasedAbsorbedDoseDistribution; Individual doseMap = null;
		CalculationOfVoxelMap calculationOfVoxelMap; Iterator<DICOMDataType> imageUsedIter;
		Iterator<AttenuatorType> attenuatorUsedIterator; Individual attenuator;
		Individual calculationof3Ddosemap = null; DICOMDataType imageUsed;
		Individual MCmethod ; AttenuatorType attenuatorParam; Iterator<MethodSettingType> mcSettingsIter;
		MethodSettingType mcSetting; Iterator<String> imagingDeviceIter; Iterator<DICOMDataType> imageUsedIter3;
		String imagingDevice; Iterator<String> MCsoftwareIter; Individual role_of_responsible_organization = null;
		String MCsoftware; Iterator<VoxelBasedDistributionOfAbsorbedDoseType> voxelBasedDistributionIter;
		Individual voxelBasedDistributionOfAbsorbedDoseType = null; Individual modelOfImagingDevice;
		Iterator<String> seriesIter; String serie; VoxelBasedDistributionOfAbsorbedDoseType voxelBasedDistribution;
		Individual ctImageDataSet = null; Individual roleOfOrganization;
		Iterator<SimpleCTMonteCarloDosimetry> ctMonteCarloDosimetryIter2; 
		CalculationOfAbsorbedDosesInVOIs calculationOfAbsorbedDoses; 
		Individual calculationOfMeanAbsorbedDosesinVOIs; 
		ArrayList<Individual> listeDosiMc = new ArrayList<Individual>();
		
		@SuppressWarnings("unused") VOIDescriptorType voiNDescriptorUsed;
		@SuppressWarnings("unused") Individual modelAttenuator;
		
		if (memory==null) {memory = Application.memory;}
		
		while (subtask212Iter.hasNext()) {
			subtask212 = subtask212Iter.next();
			if (subtask212.simpleCTMonteCarloDosimetry!=null) {
				ctMonteCarloDosimetryIter = subtask212.simpleCTMonteCarloDosimetry.iterator();
				while (ctMonteCarloDosimetryIter.hasNext()) {
					ctMonteCarloDosimetry = ctMonteCarloDosimetryIter.next();
					ctDosiMc = createIndiv(generateName("Monte_Carlo_CT_dosimetry"), model.getResource(racineURI+"Monte_Carlo_CT_dosimetry")); // process
					listeDosiMc.add(ctDosiMc);
					addDataProperty(ctDosiMc, racineURI+"has_beginning", ctMonteCarloDosimetry.calculationOfAbsorbedDosesInVOIs.dateTimeProcessStarted);

					imageUsedIter = ctMonteCarloDosimetry.calculationOfVoxelMap.dicomctImageDataUsed.iterator();
					while(imageUsedIter.hasNext()) {
						imageUsed = imageUsedIter.next();
						seriesIter = imageUsed.dicomSeriesUID.iterator();
						while (seriesIter.hasNext()) {
							serie = seriesIter.next();
							ctImageDataSet = memory.getCtDataSet(serie, imageUsed.dicomStudyUID);
 						}
					}
				}
			} //fin simpleCTMonteCarloDosimetry

			if (subtask212.ctSegmentation!=null) {
				ctSegmentation = subtask212.ctSegmentation;

				imageSegmentation = createIndiv(generateName("image_segmentation"), model.getResource(racineURI+"image_segmentation")); //process
				addObjectProperty(imageSegmentation, racineURI+"has_specified_input", ctImageDataSet);
				addDataProperty(imageSegmentation, racineURI+"has_beginning", ctSegmentation.dateTimeProcessStarted);

				institution = memory.getInstitution(ctSegmentation.performingInstitution);
				roleOfOrganization = memory.getRoleOfResponsibleOrganization(ctSegmentation.performingInstitution);
				addObjectProperty(institution, racineObo+"BFO_0000161", roleOfOrganization);
				addObjectProperty(roleOfOrganization,racineObo+"BFO_0000054",imageSegmentation);

				image = createIndiv(generateName("Image"), model.getResource(racineURI+"CT_image_reconstruction"));
				for(int i = 0; i<ctSegmentation.dicomImageUsed.dicomSeriesUID.size(); i++) {
					addDataProperty(image, racineURI+"has_DICOM_series_instance_UID", ctSegmentation.dicomImageUsed.dicomSeriesUID.get(i));
				}
				addDataProperty(image, racineURI+"has_DICOM_series_instance_UID", ctSegmentation.dicomImageUsed.dicomStudyUID);

				if (ctSegmentation.segmentationMethodUsed.segmentationMethod!=null) {
					if (ctSegmentation.segmentationMethodUsed.segmentationMethod.compareToIgnoreCase("Interactive contour segmentation")==0) {
						segMeth = createIndiv(generateName("interactive_contour_segmentation_method"), model.getResource(racineURI+"interactive_contour_segmentation_method"));
					} else {
						segMeth = createIndiv(generateName("image_segmentation_method"), model.getResource(racineURI+"image_segmentation_method"));
					}
					addObjectProperty(imageSegmentation, racineURI+"has_protocol", segMeth);
					if (ctSegmentation.segmentationMethodUsed.softwareName!=null) {
						for (int i =0; i<ctSegmentation.segmentationMethodUsed.softwareName.size(); i++) {
							addObjectProperty(imageSegmentation, racineURI+"has_agent", memory.getSoftware(ctSegmentation.segmentationMethodUsed.softwareName.get(i)));
						}
					}

					if (ctSegmentation.segmentationMethodUsed.segmentationMethodSetting!=null) {
						for (int i =0; i<ctSegmentation.segmentationMethodUsed.segmentationMethodSetting.size(); i++) {
							settingTest = ctSegmentation.segmentationMethodUsed.segmentationMethodSetting.get(i);
							switch (settingTest.methodSettingValue) {
							case("KVP"):
								indivSetting = createIndiv(generateName("KVP"), model.getResource(racineDCM+"113733"));break;
							default:
								indivSetting = createIndiv(generateName(settingTest.methodSettingValue), model.getResource(racineURI+"device_setting"));break;
							}
							addObjectProperty(indivSetting, racineObo+"IAO_0000039",  getUnit(settingTest.methodSettingUnit));
							addDataProperty(indivSetting,racineObo+"IAO_0000004",settingTest.methodSettingUnit);
						}
					}
				}

				if (ctSegmentation.voiDescriptorProduced!=null) {
					voiIterator = ctSegmentation.voiDescriptorProduced.iterator();
					while (voiIterator.hasNext()) {
						voiDescriptor = voiIterator.next();
						voiDataList = voiDescriptor.voiData.iterator();
						while (voiDataList.hasNext()) {
							voiData = voiDataList.next();
							
							voi = voi(voiData.nonDICOMDataFileName.get(0), voiData.nonDICOMDataClass);
							
							if (voiData.fhirId!=null) {
								addDataProperty(voi,racineURI+"has_IRDBB_FHIR_handle","/fhir/Binary/"+voiData.fhirId);
							}

							addObjectProperty(imageSegmentation,racineURI+"has_specified_output",voi);

							listOrgans = createIndividualOrgan(voiDescriptor.voiLabel);
							for (int i=0; i<listOrgans.size(); i++) {
								addObjectProperty(voi,racineURI+"represents",listOrgans.get(i));
							}

							addObjectProperty(voi, racineURI+"is_specified_output_of", imageSegmentation);

							for (int i=0; i<voiData.nonDICOMDataFileName.size(); i++) {
								addDataProperty(voi, racineURI+"has_name", voiData.nonDICOMDataFileName.get(i));
							}

							voi.addOntClass(model.getResource(racineURI+"non_DICOM_file"));
							if (voiData.fhirId!=null) {
								addDataProperty(voi,racineURI+"has_IRDBB_FHIR_handle","/fhir/Binary/"+voiData.fhirId);
							}
							switch (voiData.nonDICOMDataFormat) {
							case ("zipped imageJ contours format"):
								i = createIndiv(model.getResource(racineURI+"zipped_imageJ_contours_format"));
								addObjectProperty(voi, racineURI+"has_format", i);
								break;
							case ("TIFF format embedding imageJ contours"):
								i = createIndiv(model.getResource(racineURI+"TIFF_format_embedding_imageJ_contours"));
								addObjectProperty(voi, racineURI+"has_format", i);
								break;	
							case ("zipped pseudo-DICOM ImpactMC"):
								i = createIndiv(model.getResource(racineURI+"zipped_pseudo_DICOM_ImpactMC"));
								addObjectProperty(voi, racineURI+"has_format", i);
								break;		
							default: 
								logger.warn("Unknown nonDICOMDataFormat : "+voiData.nonDICOMDataFormat);
								break;
							}
						}
					}
				}
			} //fin CT segmentation

			ctMonteCarloDosimetryIter2 = subtask212.simpleCTMonteCarloDosimetry.iterator();
			int j=0;
			while (ctMonteCarloDosimetryIter2.hasNext()) {
				ctMonteCarloDosimetry = ctMonteCarloDosimetryIter2.next();
				calculationOfVoxelMap = ctMonteCarloDosimetry.calculationOfVoxelMap ;
				calculationof3Ddosemap = createIndiv(generateName("calculation_of_a_3D_dose_map"), model.getResource(racineURI+"calculation_of_a_3D_dose_map"));  

				addObjectProperty(listeDosiMc.get(j), racineObo+"BFO_0000117", calculationof3Ddosemap);
				addDataProperty(calculationof3Ddosemap, racineURI+"has_beginning", calculationOfVoxelMap.dateTimeProcessStarted);

				institution = memory.getInstitution(ctSegmentation.performingInstitution);	
				role_of_responsible_organization = memory.getRoleOfResponsibleOrganization(ctSegmentation.performingInstitution);
				addObjectProperty(role_of_responsible_organization,racineObo+"BFO_0000054", calculationof3Ddosemap);

				if (institution!=null) {
					addObjectProperty(institution,racineObo+"BFO_0000161",role_of_responsible_organization); //has role at all time
				}
				if (ctDosiMc!=null) {
					addObjectProperty(role_of_responsible_organization,racineObo+"BFO_0000054", ctDosiMc);
				}

				if (calculationOfVoxelMap.attenuatorUsed!=null) {
					attenuatorUsedIterator = calculationOfVoxelMap.attenuatorUsed.iterator();
					while (attenuatorUsedIterator.hasNext()) {
						attenuatorParam = attenuatorUsedIterator.next();

						attenuator = createIndiv(generateName("Attenuator"), model.createResource(racineDCM+"128472"));  

						addObjectProperty(calculationof3Ddosemap, racineURI+"has_setting", attenuator); 

						addDataProperty(attenuator, racineURI+"has_description", attenuatorParam.attenuatorCategory);
						addDataProperty(attenuator, racineURI+"has_description", attenuatorParam.equivalentAttenuatorDescription);

						modelAttenuator = createIndiv(generateName("Attenuator_Model"), model.createResource(racineDCM+"128472"));
						addDataProperty(attenuator, racineURI+"has_name", attenuatorParam.equivalentAttenuatorModel);
						//addObjectProperty(attenuator, racineURI+"has_setting", attenuatorParam.equivalentAttenuatorMaterial);
						//TODO décommneter au dessus et compléter

						i = createIndiv(generateName("Attenuator_Thickness"), model.getResource(racineDCM+"128469"));

						addObjectProperty(i, racineObo+"IAO_0000039", getUnit(attenuatorParam.equivalentAttenuatorThicknessUnit));
						addDataProperty(i, racineObo+"IAO_0000004", attenuatorParam.equivalentAttenuatorThicknessValue);				
					}
				}

				if (calculationOfVoxelMap.monteCarloMethodUsed!=null) {
					
					MCmethod = memory.getMCmethod(calculationOfVoxelMap.monteCarloMethodUsed.monteCarloMethod);

					addObjectProperty(calculationof3Ddosemap, racineURI+"has_protocol", MCmethod);
					
					if (calculationOfVoxelMap.monteCarloMethodUsed.monteCarloMethodSetting!=null) {
						mcSettingsIter = calculationOfVoxelMap.monteCarloMethodUsed.monteCarloMethodSetting.iterator();
						while (mcSettingsIter.hasNext()) {
							mcSetting = mcSettingsIter.next();
							switch (mcSetting.methodSetting) {
							case ("Simulation of intensity modulation"):
								if (mcSetting.methodSettingValue.equalsIgnoreCase("no use of X-ray modulation")) {
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
								logger.warn("Unknown : mcSetting.methodSetting");
								break;
							}
							if (mcSetting.methodSettingUnit!=null) {
								addObjectProperty(settingMC, racineObo+"IAO_0000039", getUnit(mcSetting.methodSettingUnit));
							}
							if (mcSetting.methodSettingValue!=null && mcSetting.methodSetting!="Simulation of intensity modulation") {
								addDataProperty(settingMC, racineObo+"IAO_0000004", mcSetting.methodSettingValue);	
							}
							if (settingMC!=null) {
								addObjectProperty(calculationof3Ddosemap, racineURI+"has_setting", settingMC);
							}
						}
					}

					imagingDeviceIter = calculationOfVoxelMap.monteCarloMethodUsed.simulatedImagingDevice.iterator();
					while (imagingDeviceIter.hasNext()) {
						imagingDevice = imagingDeviceIter.next();
						i = createIndiv(model.getResource(racineURI+"medical_imaging_device")); 
						addDataProperty(i, racineURI+"has_name", imagingDevice);
						addObjectProperty(i, racineURI+"used_as_instrument_in", ctDosiMc); 
						modelOfImagingDevice = createIndiv(model.getResource(racineURI+"model_of_medical_imaging_device"));
						addObjectProperty(i, racineURI+"refers_to_device", modelOfImagingDevice);
					}

					MCsoftwareIter = calculationOfVoxelMap.monteCarloMethodUsed.softwareName.iterator();
					while (MCsoftwareIter.hasNext()) {
						MCsoftware = MCsoftwareIter.next();
						addObjectProperty(calculationof3Ddosemap, racineURI+"has_agent", memory.getSoftware(MCsoftware)); 
					}
				}

				if (calculationOfVoxelMap.voxelBasedDistributionOfAbsorbedDoseProduced!=null) {
					voxelBasedDistributionIter = calculationOfVoxelMap.voxelBasedDistributionOfAbsorbedDoseProduced.iterator();
					while (voxelBasedDistributionIter.hasNext()) {
						voxelBasedDistribution = voxelBasedDistributionIter.next();
						voxelBasedDistributionOfAbsorbedDoseType = createIndiv(generateName("3D_dose_map"), model.getResource(racineDCM+"128487"));
						doseMap = voxelBasedDistributionOfAbsorbedDoseType;

						if (calculationof3Ddosemap!=null) {
							addObjectProperty(calculationof3Ddosemap, racineURI+"has_specified_output", voxelBasedDistributionOfAbsorbedDoseType);
							addObjectProperty(calculationof3Ddosemap, racineURI+"has_specified_input", ctImageDataSet);
						}
						if (voxelBasedDistribution.absorbedDoseUnit!=null) {
							addObjectProperty(voxelBasedDistributionOfAbsorbedDoseType, racineObo+"IAO_0000039", getUnit(voxelBasedDistribution.absorbedDoseUnit));
						}	
						
						addDataProperty(voxelBasedDistributionOfAbsorbedDoseType, racineURI+"has_description", voxelBasedDistribution.voxelBasedDistributionOfAbsorbedDoseCategory);

						if (voxelBasedDistribution.nonDICOMVoxelBasedAbsorbedDoseDistribution!=null) {

							for (int n=0; n<voxelBasedDistribution.nonDICOMVoxelBasedAbsorbedDoseDistribution.nonDICOMDataFileName.size(); n++) {

								switch (voxelBasedDistribution.nonDICOMVoxelBasedAbsorbedDoseDistribution.nonDICOMDataClass) {
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
									logger.warn("Unknown : voxelBasedDistribution.nonDICOMVoxelBasedAbsorbedDoseDistribution.nonDICOMDataClass");
								}	
								if(voxelBasedDistribution.nonDICOMVoxelBasedAbsorbedDoseDistribution.fhirId!=null) {
									addDataProperty(voxelBasedDistributionOfAbsorbedDoseType, racineURI+"has_IRDBB_FHIR_handle", 
											"/fhir/Binary/"+voxelBasedDistribution.nonDICOMVoxelBasedAbsorbedDoseDistribution.fhirId);
								}
								nonDicomFile = createIndiv(generateName("nonDicomFile"),model.getResource(racineURI+"non_DICOM_file"));
								addDataProperty(nonDicomFile, racineURI+"has_name", voxelBasedDistribution.nonDICOMVoxelBasedAbsorbedDoseDistribution.nonDICOMDataFileName.get(n));
								switch (voxelBasedDistribution.nonDICOMVoxelBasedAbsorbedDoseDistribution.nonDICOMDataFormat) {
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
								case ("zipped pseudo-DICOM ImpactMC"):
									i = createIndiv(model.getResource(racineURI+"zipped_pseudo_DICOM_ImpactMC"));
									addObjectProperty(nonDicomFile, racineURI+"has_format", i);
									addObjectProperty(voxelBasedDistributionOfAbsorbedDoseType, racineURI+"has_format", i);
									break;	
								default: 
									logger.warn("Unknown nonDICOMDataFormat : "+voxelBasedDistribution.nonDICOMVoxelBasedAbsorbedDoseDistribution.nonDICOMDataFormat);
									break;
								}
							}
						}
					}
				} // fin calculationOf3D`

				if (ctMonteCarloDosimetry.calculationOfAbsorbedDosesInVOIs!=null) {
					calculationOfAbsorbedDoses = ctMonteCarloDosimetry.calculationOfAbsorbedDosesInVOIs;

					calculationOfMeanAbsorbedDosesinVOIs = createIndiv(generateName("calculation_of_mean_absorbed_doses_in_VOIs"), model.getResource(racineURI+"calculation_of_mean_absorbed_doses_in_VOIs"));

					addObjectProperty(listeDosiMc.get(j), racineObo+"BFO_0000117", calculationOfMeanAbsorbedDosesinVOIs);
					j++;

					if (voxelBasedDistributionOfAbsorbedDoseType!=null) {
						addObjectProperty(calculationOfMeanAbsorbedDosesinVOIs, racineURI+"has_specified_input", voxelBasedDistributionOfAbsorbedDoseType);
					}

					institution = memory.getInstitution(calculationOfAbsorbedDoses.performingInstitution);		
					role_of_responsible_organization = memory.getRoleOfResponsibleOrganization(calculationOfAbsorbedDoses.performingInstitution);
					addObjectProperty(role_of_responsible_organization,racineObo+"BFO_0000054", calculationOfMeanAbsorbedDosesinVOIs);
					addObjectProperty(institution,racineObo+"BFO_0000161",role_of_responsible_organization); //has role at all time
					addDataProperty(calculationOfMeanAbsorbedDosesinVOIs, racineURI+"has_beginning", calculationOfAbsorbedDoses.dateTimeProcessStarted);

					if (ctMonteCarloDosimetry.calculationOfAbsorbedDosesInVOIs.absorbedDosePerVOI!=null) {
						absorbedDosePerVOIlist = ctMonteCarloDosimetry.calculationOfAbsorbedDosesInVOIs.absorbedDosePerVOI.iterator();
						while (absorbedDosePerVOIlist.hasNext()) {
							absorbedDosePerVOI = absorbedDosePerVOIlist.next();
							switch (absorbedDosePerVOI.absorbedDoseCategory) {
							case "mean absorbed dose normalized to CTDI-free-in-air normalized to tube load":
								absorbedDoseVoi = createIndiv(generateName("mean_absorbed_dose_normalized_to_CTDI_free_in_air_normalized_to_tube_load"),
										model.getResource(racineURI+"mean_absorbed_dose_normalized_to_CTDI_free_in_air_normalized_to_tube_load"));
								break;
							case "mean absorbed dose normalized to CTDI-vol normalized to tube load":
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
								logger.warn("Unknown absorbedDosePerVOICategory : "+absorbedDosePerVOI.absorbedDoseCategory);
								absorbedDoseVoi = createIndiv(generateName("Absorbed_Dose"), model.getResource(racineDCM+"128513"));
								break;
							}
							if (absorbedDosePerVOI.statisticalSubCategory!=null) {
								switch (absorbedDosePerVOI.statisticalSubCategory) {
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
							
							imageUsedIter3 = ctMonteCarloDosimetry.calculationOfVoxelMap.dicomctImageDataUsed.iterator();
							while(imageUsedIter3.hasNext()) {
								imageUsed = imageUsedIter3.next();
								seriesIter = imageUsed.dicomSeriesUID.iterator();
								while (seriesIter.hasNext()) {
									serie = seriesIter.next();
									patient = memory.getPatient(serie, imageUsed.dicomStudyUID); 
									addObjectProperty(absorbedDoseVoi, racineURI+"has_patient", patient);
									addObjectProperty(researchClinicalStudy, racineURI+"has_patient", patient);
									
								}
							}
							
							addObjectProperty(calculationOfMeanAbsorbedDosesinVOIs,racineURI+"has_specified_output",absorbedDoseVoi);
							addDataProperty(absorbedDoseVoi, racineObo+"IAO_0000004", absorbedDosePerVOI.absorbedDoseValue); 
							addObjectProperty(absorbedDoseVoi, racineObo+"IAO_0000039", getUnit(absorbedDosePerVOI.absorbedDoseUnit));

							listOrgans = createIndividualOrgan(absorbedDosePerVOI.voi.voiLabel);
							logger.debug("listOrgans "+listOrgans+" ("+listOrgans.size()+")");
							for (int i=0; i<listOrgans.size(); i++) {
								//addObjectProperty(voi,racineURI+"represents",listOrgans.get(i));
								addObjectProperty(absorbedDoseVoi,racineURI+"is_dose_absorbed_by",listOrgans.get(i));
							}

							if (absorbedDosePerVOI.voi.voiData!=null) {
								voiDataIterator = absorbedDosePerVOI.voi.voiData.iterator();
								while (voiDataIterator.hasNext()) {
									voiData = voiDataIterator.next();

									voi = voi(voiData.nonDICOMDataFileName.get(0), voiData.nonDICOMDataClass);
									voiFile = createIndiv(generateName("nonDicomFile_Voi"), model.getResource(racineURI+"non_DICOM_file"));

									addObjectProperty(calculationOfMeanAbsorbedDosesinVOIs,racineURI+" ",voiFile);
									addObjectProperty(absorbedDoseVoi,racineObo+"BFO_0000170",voi);

									for (int i=0; i<voiData.nonDICOMDataFileName.size();i++) {
										addDataProperty(voiFile,racineURI+"has_name",voiData.nonDICOMDataFileName.get(i));
									}
									
									switch (voiData.nonDICOMDataFormat) {
									case ("zipped imageJ contours format"):
										i = createIndiv(model.getResource(racineURI+"zipped_imageJ_contours_format"));
										addObjectProperty(voiFile, racineURI+"has_format", i);
										addObjectProperty(voi, racineURI+"has_format", i);
										break;
									case ("TIFF format embedding imageJ contours"):
										i = createIndiv(model.getResource(racineURI+"TIFF_format_embedding_imageJ_contours"));
										addObjectProperty(voiFile, racineURI+"has_format", i);
										addObjectProperty(voi, racineURI+"has_format", i);
										break;	
									case ("zipped pseudo-DICOM ImpactMC"):
										i = createIndiv(model.getResource(racineURI+"zipped_pseudo_DICOM_ImpactMC"));
										addObjectProperty(voiFile, racineURI+"has_format", i);
										addObjectProperty(voi, racineURI+"has_format", i);
										break;	
									default: 
										logger.warn("Unknown nonDICOMDataFormat : "+voiData.nonDICOMDataFormat);
										break;
									}					
								}
							}
						}
					}

					if (ctMonteCarloDosimetry.calculationOfAbsorbedDosesInVOIs.voiDescriptorUsed!=null) {
						listVoiDescriptor = ctMonteCarloDosimetry.calculationOfAbsorbedDosesInVOIs.voiDescriptorUsed.iterator();
						while (listVoiDescriptor.hasNext()) {
							voidescriptor = listVoiDescriptor.next();
							if (voidescriptor.voiData!=null) {
								voiDataList = voidescriptor.voiData.iterator();
								while (voiDataList.hasNext()) {
									voiData = voiDataList.next();	
									voi = voi(voiData.nonDICOMDataFileName.get(0), voiData.nonDICOMDataClass);
									if (voiData.fhirId!=null) {
										addDataProperty(voi,racineURI+"has_IRDBB_FHIR_handle","/fhir/Binary/"+voiData.fhirId);
									}
									addObjectProperty(calculationOfMeanAbsorbedDosesinVOIs,racineURI+"has_specified_input",voi);
									addDataProperty(voi, racineURI+"has_id", voidescriptor.voiId);
									listOrgans = createIndividualOrgan(voidescriptor.voiLabel);
									for (int i=0; i<listOrgans.size(); i++) {
										addObjectProperty(voi,racineURI+"represents",listOrgans.get(i));
									}
									addObjectProperty(voi, racineObo+"BFO_OOOO132", imageSegmentation); //part of occurent

									for (int n=0; n<voiData.nonDICOMDataFileName.size(); n++) {
										voiFile = createIndiv(generateName("non_Dicom_File_Voi"), model.getResource(racineURI+"non_DICOM_file"));
										addObjectProperty(calculationOfMeanAbsorbedDosesinVOIs,racineURI+"has_specified_input",voiFile);
										addDataProperty(voiFile, racineURI+"has_name", voiData.nonDICOMDataFileName.get(n));
										addObjectProperty(voiFile, racineObo+"BFO_OOOO054", voi);
										switch (voiData.nonDICOMDataFormat) {
										case ("zipped imageJ contours format"):
											i = createIndiv(model.getResource(racineURI+"zipped_imageJ_contours_format"));
											addObjectProperty(voiFile, racineURI+"has_format", i);
											break;
										case ("TIFF format embedding imageJ contours"):
											i = createIndiv(model.getResource(racineURI+"TIFF_format_embedding_imageJ_contours"));
											addObjectProperty(voiFile, racineURI+"has_format", i);
											break;	
										case ("zipped pseudo-DICOM ImpactMC"):
											i = createIndiv(model.getResource(racineURI+"zipped_pseudo_DICOM_ImpactMC"));
											addObjectProperty(voiFile, racineURI+"has_format", i);
											break;	
										default: 
											logger.warn("Unknown nonDICOMDataFormat : "+voiData.nonDICOMDataFormat);
											break;
										}
									}
								}
							}
						}
					}

					if (ctMonteCarloDosimetry.calculationOfAbsorbedDosesInVOIs.voxelBasedDistributionOfAbsorbedDoseUsed!=null) {
						listVoxelBasedDistributionOfAbsorbedDoseUsed = ctMonteCarloDosimetry.calculationOfAbsorbedDosesInVOIs.voxelBasedDistributionOfAbsorbedDoseUsed.iterator();
						int k = 0;
						while (listVoxelBasedDistributionOfAbsorbedDoseUsed.hasNext()) {
							
							if (calculationof3Ddosemap!=null) {
								addObjectProperty(calculationof3Ddosemap, racineURI+"has_specified_output", doseMap);
							}

							voxelBasedDistributionOfAbsorbedDoseUsed = listVoxelBasedDistributionOfAbsorbedDoseUsed.next();
							voiNDescriptorUsed = ctMonteCarloDosimetry.calculationOfAbsorbedDosesInVOIs.voiDescriptorUsed.get(k);
							
							if (voxelBasedDistributionOfAbsorbedDoseUsed.nonDICOMVoxelBasedAbsorbedDoseDistribution!=null) {
								nonDICOMVoxelBasedAbsorbedDoseDistribution = doseMap;
								if ( voxelBasedDistributionOfAbsorbedDoseUsed.nonDICOMVoxelBasedAbsorbedDoseDistribution.fhirId!=null) {
									addDataProperty(nonDICOMVoxelBasedAbsorbedDoseDistribution, racineURI+"has_IRDBB_FHIR_handle", 
											"/fhir/Binary/"+voxelBasedDistributionOfAbsorbedDoseUsed.nonDICOMVoxelBasedAbsorbedDoseDistribution.fhirId);
								}
								switch (voxelBasedDistributionOfAbsorbedDoseUsed.nonDICOMVoxelBasedAbsorbedDoseDistribution.nonDICOMDataClass) {
								case ("VOI"):
									nonDICOMVoxelBasedAbsorbedDoseDistribution.addOntClass(model.getResource(racineURI+"VOI"));
									break;
								case ("VOI superimposed on images"):
									nonDICOMVoxelBasedAbsorbedDoseDistribution.addOntClass(model.getResource(racineURI+"VOI_superimposed_on_images"));
									break;
								case ("3D dose map"):
									nonDICOMVoxelBasedAbsorbedDoseDistribution.addOntClass(model.getResource(racineDCM+"128487"));
									break;
								default:
									logger.warn("Unknown : voxelBasedDistribution.nonDICOMVoxelBasedAbsorbedDoseDistribution.nonDICOMDataClass");
								}

								for (int n=0; n<voxelBasedDistributionOfAbsorbedDoseUsed.nonDICOMVoxelBasedAbsorbedDoseDistribution.nonDICOMDataFileName.size(); n++) {

									voi = voi(voxelBasedDistributionOfAbsorbedDoseUsed.nonDICOMVoxelBasedAbsorbedDoseDistribution.nonDICOMDataFileName.get(0), voxelBasedDistributionOfAbsorbedDoseUsed.nonDICOMVoxelBasedAbsorbedDoseDistribution.nonDICOMDataClass); 
									k++;
									
									addObjectProperty(voi, racineURI+"has_dose", doseMap);
									

									if (voxelBasedDistributionOfAbsorbedDoseUsed.absorbedDoseUnit!=null) {
										addObjectProperty(doseMap,racineObo+"IAO_0000039",getUnit(voxelBasedDistributionOfAbsorbedDoseUsed.absorbedDoseUnit));
									}
									if (voxelBasedDistributionOfAbsorbedDoseUsed.dicomVoxelBasedAbsorbedDoseDistribution!=null) {
										if (voxelBasedDistributionOfAbsorbedDoseUsed.dicomVoxelBasedAbsorbedDoseDistribution.dicomSeriesUID!=null) {
											for (int i=0;i<voxelBasedDistributionOfAbsorbedDoseUsed.dicomVoxelBasedAbsorbedDoseDistribution.dicomSeriesUID.size(); i++) {
												addDataProperty(doseMap, racineURI+"has_DICOM_series_instance_UID", voxelBasedDistributionOfAbsorbedDoseUsed.dicomVoxelBasedAbsorbedDoseDistribution.dicomSeriesUID.get(i));
											}
										}
										if (voxelBasedDistributionOfAbsorbedDoseUsed.dicomVoxelBasedAbsorbedDoseDistribution.dicomStudyUID!=null) {
											addDataProperty(doseMap, racineURI+"has_DICOM_SOP_instance_UID", voxelBasedDistributionOfAbsorbedDoseUsed.dicomVoxelBasedAbsorbedDoseDistribution.dicomStudyUID);
										}
									}

									nonDicomFile = createIndiv(generateName("nonDicomFile"),model.getResource(racineURI+"non_DICOM_file"));
									addDataProperty(nonDicomFile, racineURI+"has_name", voxelBasedDistributionOfAbsorbedDoseUsed.nonDICOMVoxelBasedAbsorbedDoseDistribution.nonDICOMDataFileName.get(n));
									switch (voxelBasedDistributionOfAbsorbedDoseUsed.nonDICOMVoxelBasedAbsorbedDoseDistribution.nonDICOMDataFormat) {
									case ("zipped imageJ contours format"):
										i = createIndiv(model.getResource(racineURI+"zipped_imageJ_contours_format"));
										addObjectProperty(nonDicomFile, racineURI+"has_format", i);
										break;
									case ("TIFF format embedding imageJ contours"):
										i = createIndiv(model.getResource(racineURI+"TIFF_format_embedding_imageJ_contours"));
										addObjectProperty(nonDicomFile, racineURI+"has_format", i);
										break;	
									case ("zipped pseudo-DICOM ImpactMC"):
										i = createIndiv(model.getResource(racineURI+"zipped_pseudo_DICOM_ImpactMC"));
										addObjectProperty(nonDicomFile, racineURI+"has_format", i);
										break;	
									default: 
										logger.warn("Unknown nonDICOMDataFormat : "+voxelBasedDistributionOfAbsorbedDoseUsed.nonDICOMVoxelBasedAbsorbedDoseDistribution.nonDICOMDataFormat);
										break;
									}
								}
							}
						}
					}
				}
			} //fin while iter2
		}
	}
	
	public static Individual voi(String voiId, String voiClass) { //function to check if a VOI has been already described (and make then unic)
		// If the voi exist return these VOI, else return a new VOI
		if (!tableVOI.containsKey(voiId)) {
			Individual voi;
			if (voiClass!=null) {
				switch (voiClass) {
				case "VOI superimposed on images":
					voi = createIndiv(generateName("VOI_superimposed_on_images"),model.getResource(racineURI+"VOI_superimposed_on_images"));
					break;
				case ("VOI"):
					voi = createIndiv(generateName("VOI"),model.getResource(racineURI+"VOI"));
				break;
				case ("3D dose map"):
					voi = createIndiv(generateName("3D_dose_map"),model.getResource(racineDCM+"128487"));
				break;		
				default:
					voi = createIndiv(generateName("VOI"),model.getResource(racineURI+"VOI"));
					logger.warn("Unknown : voiData.nonDICOMDataClas");
					break;
				} 
			} else {
				return null;
			}
			addDataProperty(voi, racineURI+"has_name", voiId); 
			tableVOI.put(voiId, voi);
		}
		return tableVOI.get(voiId);
	}
}