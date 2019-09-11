package repository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.jena.ontology.Individual;
import org.apache.jena.rdf.model.ModelFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.query.TupleQueryResultHandlerException;
import org.openrdf.query.resultio.QueryResultIO;
import org.openrdf.query.resultio.TupleQueryResultFormat;
import org.openrdf.query.resultio.UnsupportedQueryResultFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.ConnectionPool;
import com.complexible.stardog.api.ConnectionPoolConfig;
import com.complexible.stardog.api.SelectQuery;

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
import repository.ImportController.database;
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
			researchClinicalStudy = createIndiv("clinical_research_study_755523_subtask2.1.2", 
					model.getResource("http://medicis.univ-rennes1.fr/ontologies/ontospm/OntoMEDIRAD.owl#clinical_research_study_755523_subtask2.1.2"));
			retrieveSubtastk212(nonDicomFileSetDescriptor);
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

		
		// to be completed when with other subtasks

	}
	
 	public static void retrieveSubtastk212(NonDicomFileSetDescriptor nonDicomFileSetDescriptor) {
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
		
		String patientID = nonDicomFileSetDescriptor.getPatientId();
		String resPatient = executeQuerry("SELECT DISTINCT ?human ?id ?patient WHERE {\n" + 
				"	?human rdf:type ontomedirad:human .\n" + 
				"  	?human ontomedirad:has_id ?id .\n" + 
				"    ?human purl:BFO_0000087 ?patient .\n" + 
				"} ORDER BY ?human", "false");
		
		try {
			JSONArray resJsonPatient = new JSONObject(resPatient).getJSONObject("results").getJSONArray("bindings");
			JSONObject objPatient; String id; String human; String patientJson;
			for (int i=0; i<resJsonPatient.length(); i++) {
				objPatient = resJsonPatient.getJSONObject(i);
				id = objPatient.getJSONObject("id").getString("value");
				human = objPatient.getJSONObject("human").getString("value");
				patientJson = objPatient.getJSONObject("patient").getString("value");
				System.out.println("human : "+human);
				System.out.println("patient : "+patientJson);
				System.out.println("id : "+id);
				
				if (id.contains(patientID)) {
					patient = model.getIndividual(patientJson);
				}

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		System.out.println("patient : "+patient);
		
		
		Iterator<WP2Subtask212WorkflowData> subtask212Iter = nonDicomFileSetDescriptor.getWP2Subtask212WorkflowData().iterator();
		while (subtask212Iter.hasNext()) {
			subtask212 = subtask212Iter.next();
						
			SimpleCTMonteCarloDosimetry ctMonteCarloDosimetry; DICOMData imageUsed;
			
			if (subtask212.getSimpleCTMonteCarloDosimetry() !=null) {
				Iterator<SimpleCTMonteCarloDosimetry> ctMonteCarloDosimetryIter = subtask212.getSimpleCTMonteCarloDosimetry().iterator();
				while (ctMonteCarloDosimetryIter.hasNext()) {
					ctMonteCarloDosimetry = ctMonteCarloDosimetryIter.next();
					ctDosiMc = createIndiv(generateName("Monte_Carlo_CT_dosimetry"), model.getResource(racineURI+"Monte_Carlo_CT_dosimetry")); // process
					addObjectProperty(ctDosiMc, racineURI+"part_of_study", researchClinicalStudy);

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
				addObjectProperty(imageSegmentation, racineURI+"part_of_study", researchClinicalStudy);

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
								indivSetting = createIndiv(generateName(settingTest.getMethodSettingValue()), model.getResource(racineURI+"is_device_setting"));break;
							}
							addObjectProperty(indivSetting, racineObo+"IAO_0000039",  getUnit(settingTest.getMethodSettingUnit()));
							addDataProperty(indivSetting,racineObo+"IAO_0000004",settingTest.getMethodSettingUnit());
						}
					}
				}

				if (ctSegmentation.getVOIDescriptorProduced()!=null) {
					Iterator<VOI> voiIterator = ctSegmentation.getVOIDescriptorProduced().iterator();
					while(voiIterator.hasNext()) {
						VOI voiInformations= voiIterator.next();
						String voiId = voiInformations.getVOIIdentifier();
						Individual organ = getOrgan(voiInformations.getOrganOrTissue());

						Iterator<NonDICOMData> voiContainerIter = voiInformations.getNonDICOMVOIContainer().iterator();
						while (voiContainerIter.hasNext()) {
							Individual voiFile = createIndiv(generateName("non_DICOM_file"),model.getResource(racineURI+"non_DICOM_file"));
							
							addObjectProperty(voiFile, racineURI+"has_patient",patient);
							addObjectProperty(imageSegmentation,racineURI+"has_specified_output", voiFile);
							addObjectProperty(voiFile, racineURI+"is_specified_output_of", imageSegmentation);
							addObjectProperty(organ,racineURI+"part_of_continuant_at_some_time", patient);	
							addObjectProperty(voiFile,racineURI+"represents", organ);
							
							NonDICOMData voiContainer = voiContainerIter.next();
							Iterator<String> fileNameIter = voiContainer.getNonDICOMDataFileName().iterator();
							while (fileNameIter.hasNext()) {
								String fileName = fileNameIter.next();
								addDataProperty(voiFile, racineURI+"has_name", fileName);
							}
							switch (voiContainer.getNonDICOMDataClass()) {
							case ("VOI"):
								voiFile.addOntClass(model.getResource(racineURI+"VOI"));
								break;
							case ("VOI superimposed on images"):
								voiFile.addOntClass(model.getResource(racineURI+"VOI_superimposed_on_images"));
								break;
							case ("3D dose map"):
								voiFile.addOntClass(model.getResource(racineDCM+"128487"));
							default:
								logger.warn("Unknown : voxelBasedDistribution.getNonDICOMVoxelBasedAbsorbedDoseDistribution().nonDICOMDataClass");
							}							switch (voiContainer.getNonDICOMDataFormat()) {
							case ("zipped imageJ contours format"):
								i = createIndiv(model.getResource(racineURI+"zipped_imageJ_contours_format"));
								addObjectProperty(voiFile, racineURI+"has_format", i);
								break;
							case ("TIFF format embedding imageJ contours"):
								i = createIndiv(model.getResource(racineURI+"TIFF_format_embedding_imageJ_contours"));
								addObjectProperty(voiFile, racineURI+"has_format", i);
								break;	
							case ("zipped pseudo_DICOM ImpactMC"):
							case ("zipped pseudo DICOM ImpactMC"):
								i = createIndiv(model.getResource(racineURI+"zipped_pseudo_DICOM_ImpactMC"));
								addObjectProperty(voiFile, racineURI+"has_format", i);
								break;		
							default: 
								logger.warn("Unknown nonDICOMDataFormat : "+voiContainer.getNonDICOMDataFormat());
								break;
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
				addObjectProperty(calculationof3Ddosemap, racineURI+"part_of_study", researchClinicalStudy);

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
						addObjectProperty(attenuator, racineURI+"has_setting", modelAttenuator);
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
								settingMC = createIndiv(generateName("device_setting"), model.getResource(racineURI+"is_device_setting"));
								logger.warn("Unknown mcSetting : "+mcSetting.getMethodSetting());
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
						addObjectProperty(modelOfImagingDevice, racineURI+"refers_to_device", i);
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
					
					addObjectProperty(voxelBasedDistributionOfAbsorbedDoseType, racineURI+"has_patient", patient);
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
							Individual voi = null;
							//System.out.println("NonDICOMDataClass : "+voxelBasedDistribution.getNonDICOMVoxelBasedAbsorbedDoseDistribution().getNonDICOMDataClass());
							switch (voxelBasedDistribution.getNonDICOMVoxelBasedAbsorbedDoseDistribution().getNonDICOMDataClass()) {
							case ("VOI"):
								voi = createIndiv(generateName("VOI"), model.getResource(racineURI+"VOI"));
								break;
							case ("VOI superimposed on images"):
								voi = createIndiv(generateName("VOI_superimposed_on_images"), model.getResource(racineURI+"VOI_superimposed_on_images"));
								break;
							case ("3D dose map"):
								voi = createIndiv(generateName("3D_dose_map"), model.getResource(racineDCM+"128487"));
								break;
							default:
								logger.warn("Unknown : voxelBasedDistribution.getNonDICOMVoxelBasedAbsorbedDoseDistribution().nonDICOMDataClass");
							}	
							if(voxelBasedDistribution.getNonDICOMVoxelBasedAbsorbedDoseDistribution().getFHIRIdentifier()!=null) {
								addDataProperty(voi, racineURI+"has_IRDBB_FHIR_handle", 
										"/fhir/Binary/"+voxelBasedDistribution.getNonDICOMVoxelBasedAbsorbedDoseDistribution().getFHIRIdentifier());
								addObjectProperty(voi,racineURI+"has_patient",patient);
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
								break;
							}
						}
					}
				} // fin calculationOf3D`

				if (ctMonteCarloDosimetry.getCalculationOfAbsorbedDosesInVOIs()!=null) {
					CalculationOfAbsorbedDosesInVOIs calculationOfAbsorbedDoses = ctMonteCarloDosimetry.getCalculationOfAbsorbedDosesInVOIs();
		
					calculationOfMeanAbsorbedDosesinVOIs = createIndiv(generateName("calculation_of_mean_absorbed_doses_in_VOIs"), model.getResource(racineURI+"calculation_of_mean_absorbed_doses_in_VOIs"));
					addObjectProperty(calculationOfMeanAbsorbedDosesinVOIs, racineURI+"has_patient", patient);
					addObjectProperty(calculationOfMeanAbsorbedDosesinVOIs, racineURI+"part_of_study", researchClinicalStudy);

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
								absorbedDoseVoi = createIndiv(generateName("Absorbed_Dose"), model.getResource(racineDCM+"128513"));
								break;
							}
							addObjectProperty(absorbedDoseVoi, racineURI+"has_patient", patient);

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
							//patient = memory.getPatient(series, imageDataUsed.getDICOMStudyUID()); 
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
 		String UID = UUID.randomUUID().toString();
 		switch (organName) {
		case "bone":
			indOrgane = createIndiv(organName+"_"+UID, model.getResource(racineObo+"FMA_5018")); 
			break;
		case "breast": case "breasts":
			indOrgane =  createIndiv("breasts"+"_"+UID, model.getResource(racineURI+"breasts")); 
			break;
		case "esophagus":
			indOrgane =  createIndiv(organName+"_"+UID, model.getResource(racineObo+"FMA_7131")); 
			break;
		case "heart":
			indOrgane =  createIndiv(organName+"_"+UID, model.getResource(racineObo+"FMA_7088")); 
			break;
		case "lungs":
			indOrgane =  createIndiv(organName+"_"+UID, model.getResource(racineObo+"FMA_68877")); 
			break;
		case "skin":
			indOrgane =  createIndiv(organName+"_"+UID, model.getResource(racineObo+"FMA_7163")); 
			break;
 		}
 		return indOrgane;
 	}
 	
 	static Connection starDogConnection;

	public static String executeQuerry(String request, String isReasoning) { // Execute a querry (querry is passed as a string)
		createAdminConnection(database.ontoMedirad, false);  	  	// Create a connection to a stardog database WITHOUT reasoning

		logger.debug("request : ");
		logger.debug(request);

		SelectQuery aQuery = starDogConnection.select(request);         // Put the request to the StarDog

		TupleQueryResult aResult = null;							    // Create an object to receive the result

		aResult = aQuery.execute();										// Execute Request (StarDog Exception)

		ByteArrayOutputStream out = new ByteArrayOutputStream();	    // Create an OuputStream to receive result
		try {
			QueryResultIO.writeTuple(aResult,                           // Write the request result in the ByteArrayOutputStream
					TupleQueryResultFormat.JSON, out);
		} catch (TupleQueryResultHandlerException | QueryEvaluationException | UnsupportedQueryResultFormatException
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		starDogConnection.close();										// Close The Connection to Stardog (despite the errors)
		aResult.close();												// Close The Object (despite the errors)

		logger.debug("result : ");
		logger.debug(out.toString());


		return out.toString(); // Convert the ByteArrayOutputStream as a string and return it
	}
	
	
	public static void createAdminConnection(database db, boolean paramreasoner) {									// Main method to open a connection to a Stardog database
		String pacsUrl = Application.pacsUrl ;
		String starDogUrl = Application.starDogUrl;
		logger.debug("Creation of the StarDog connection (Database : "+db.toString()+") at "+starDogUrl);
		ConnectionConfiguration connectionConfig = ConnectionConfiguration									// Configuration of the connection
				.to(db.toString())																			// Select Database (from the enumeration)
				.server(starDogUrl)																			// StarDog URL 
				.credentials("admin", "admin")															// Login and Pasword of Stardog
				.reasoning(paramreasoner);																	// Will it use reasoning (boollean)

		logger.debug("StarDog connection (reasoning : "+ConnectionConfiguration.REASONING_ENABLED.toString()+")"); 

		ConnectionPool connectionPool = createConnectionPool(connectionConfig);								// Create the Stardog connection 
		starDogConnection = connectionPool.obtain();														// Return the Stardog connection as a java Object
		logger.debug("Sucessfully created the StarDog connection (Database : "+db.toString()+")");
	}
	
	private static ConnectionPool createConnectionPool (ConnectionConfiguration connectionConfig) {				// Create the connection pool
		ConnectionPoolConfig poolConfig = ConnectionPoolConfig.using(connectionConfig)
				.minPool(0).maxPool(50)
				.expiration(30, TimeUnit.MINUTES)
				.blockAtCapacity(1, TimeUnit.MINUTES);
		return poolConfig.create();
	}

	
}
