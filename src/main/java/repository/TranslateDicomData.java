package repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.jena.ontology.Individual;

import com.pixelmed.dicom.ContentItem;

public class TranslateDicomData extends OntologyPopulator {
	
	static String value; static String name; static String studyInstUID;
	static Individual CTradSR;
	static Individual radioPharSR; static Individual study; static Individual accDose;
	static Individual irradEvent; static  Individual scanningLength; static Individual CTDI;
	static Individual DLP; static Individual CTDIwPhantomType; 
	static String startValue; static String endValue; static String unit;
	static DicomFileSetDescriptor DicomFileSetDescriptorContent;
	static NonDicomFileSetDescriptor nonDicomFileSetDescriptorContent;
	
	public static void readingCT(Iterator<DICOMStudyType> iterFileSet, String studyInstanceUID, String seriesInstanceUID) {
		DICOMStudyType study; DICOMStudyDescriptorType studyDescriptor; 
		Individual imagingStudy; String organName; ArrayList<Individual> organs;
		List<DICOMSeriesType> dicomSeries; Iterator<DICOMSeriesType> serieIterator; DICOMSeriesType serie;
		DICOMSeriesDescriptorType serieDescriptorType;  Individual institution = null; Individual protocol;
		List<DICOMSOPInstanceDescriptorType> dicomsopInstanceDescriptor;
		DICOMSOPInstanceDescriptorType dicomsopInstanceDescriptorType;
		CTImageAcquisitionDescriptorType ctimageAcquisitionDescriptorType; Individual ctImageAcquisition = null;
		Individual scanner; Individual imageDataSet; Individual imageAccRole;
		Individual role_of_responsible_organization = null; Iterator<DICOMSOPInstanceDescriptorType> dicomsopInstanceDescriptorIter;
		
		if (memory==null) {memory = Application.memory;}

		while (iterFileSet.hasNext()) {
			study = iterFileSet.next();
			studyDescriptor = study.getDICOMStudyDescriptor();
			
			handle = "/pacs/studies/"+studyInstanceUID+"/series/"+seriesInstanceUID;
			
			memory.setPatient(seriesInstanceUID, studyInstanceUID, patient);

			imagingStudy = createIndiv(generateName("imaging_Study"), model.getResource(racineURI+"imaging_study")); 

			addDataProperty(imagingStudy, racineURI+"has_description", studyDescriptor.getStudyDescription00081030());
			addDataProperty(imagingStudy, racineURI+"has_beginning_date", studyDescriptor.getStudyDate00080020());
			addDataProperty(imagingStudy, racineURI+"has_beginning_time", studyDescriptor.getStudyTime00080030());

			organName = studyDescriptor.getBodyPartExamined00180015();
			organName = organName.toLowerCase();

			organs = createIndividualOrgan(organName);
			for (int i=0; i<organs.size(); i++) {
				addObjectProperty(imagingStudy, racineURI+"has_target_region", organs.get(i));
			}

			dicomSeries = study.dicomSeries;
			serieIterator = dicomSeries.iterator();

			while (serieIterator.hasNext()) {
				serie = serieIterator.next();
				serieDescriptorType = serie.getDICOMSeriesDescriptor();

				imageDataSet = createIndiv(generateName("CT_image_dataset"), model.getResource(racineURI+"CT_image_dataset"));
				addDataProperty(imageDataSet, racineURI+"has_IRDBB_WADO_handle", handle);
				if (memory==null) {System.out.println("memory : NULL");}
				memory.setCtDataSet(seriesInstanceUID, studyInstanceUID, imageDataSet);

				ctImageAcquisition = createIndiv(generateName("CT_acquisition"),model.getResource(racineURI+"CT_acquisition"));
				ctImageAcquisition.addOntClass(model.getResource(racineURI+"CT_irradiation_event"));
				addObjectProperty(patientRole, racineObo+"BFO_0000054" , ctImageAcquisition);

				for (int i=0; i<organs.size(); i++) {
					addObjectProperty(ctImageAcquisition, racineURI+"has_target_region", organs.get(i));
				}

				addObjectProperty(ctImageAcquisition, racineURI+"part_of_occurent", imagingStudy);
				addObjectProperty(imageDataSet, racineURI+"is_specified_output_of", ctImageAcquisition);
				addDataProperty(ctImageAcquisition, racineURI+"has_beginning_date", serieDescriptorType.seriesDate00080021);
				addDataProperty(ctImageAcquisition, racineURI+"has_beginning_time", serieDescriptorType.seriesTime00080031);
				addDataProperty(imageDataSet, racineURI+"has_DICOM_series_instance_UID", serieDescriptorType.seriesInstanceUID0020000E);

				scanner = createIndiv(generateName("CT_scanner"), model.getResource(racineURI+"CT_scanner"));
				imageAccRole = createIndiv(generateName("image_acquisition_role"), model.getResource(racineURI+"image_acquisition_role"));
				
				addObjectProperty(imageAccRole, racineObo+"BFO_0000052", scanner);
				addObjectProperty(imageAccRole, racineObo+"BFO_0000054", ctImageAcquisition);
				addDataProperty(scanner, racineURI+"has_manufacturer_name", serieDescriptorType.manufacturer00080070);
				addDataProperty(scanner, racineURI+"has_model_name", serieDescriptorType.manufacturersModelName00081090);

				if (serieDescriptorType.institutionName00080080!= null && serieDescriptorType.institutionName00080080!=null) {
					institution = memory.getInstitution(serieDescriptorType.institutionName00080080);
					role_of_responsible_organization = memory.getRoleOfResponsibleOrganization(serieDescriptorType.institutionName00080080);
				}
				
				addObjectProperty(role_of_responsible_organization, racineObo+"BFO_0000052", institution);
				addObjectProperty(role_of_responsible_organization, racineObo+"BFO_0000054", ctImageAcquisition);

				protocol = createIndiv(generateName("Protocol"), model.getResource(racineURI+"CT_acquisition_protocol"));
				addDataProperty(protocol, racineURI+"has_name", serieDescriptorType.protocolName00181030);
				addObjectProperty(ctImageAcquisition, racineURI+"has_protocol", protocol);

				dicomsopInstanceDescriptor = serie.dicomsopInstanceDescriptor;
				dicomsopInstanceDescriptorIter = dicomsopInstanceDescriptor.iterator();

				while (dicomsopInstanceDescriptorIter.hasNext()) {
					dicomsopInstanceDescriptorType = dicomsopInstanceDescriptorIter.next();
					ctimageAcquisitionDescriptorType = dicomsopInstanceDescriptorType.ctImageAcquisitionDescriptor;

					i =  createIndiv(generateName("KVP"), model.getResource(racineDCM+"113733"));
					addDataProperty(i, racineObo+"IAO_0000004", ctimageAcquisitionDescriptorType.kvp00180060);
					addObjectProperty(i, racineObo+"IAO_0000039", getUnit("kV"));
					addObjectProperty(ctImageAcquisition,racineURI+"has_setting",i);
					addObjectProperty(i,racineURI+"is_device_setting",scanner);

					i =  createIndiv(generateName("Exposure_Time"), model.getResource(racineDCM+"113824"));
					addDataProperty(i, racineObo+"IAO_0000004", ctimageAcquisitionDescriptorType.exposureTime00181150);
					addObjectProperty(i, racineObo+"IAO_0000039", getUnit("ms"));
					addObjectProperty(ctImageAcquisition,racineURI+"has_setting",i);
					addObjectProperty(i,racineURI+"is_device_setting",scanner);

					i =  createIndiv(generateName("X-Ray_Tube_Current"), model.getResource(racineDCM+"113734"));
					addDataProperty(i, racineObo+"IAO_0000004", ctimageAcquisitionDescriptorType.xRayTubeCurrent00181151);
					addObjectProperty(i, racineObo+"IAO_0000039", getUnit("mA"));
					addObjectProperty(ctImageAcquisition,racineURI+"has_setting",i);
					addObjectProperty(i,racineURI+"is_device_setting",scanner);
				}
			}
		}
	}

	public static void readingSR(ContentItem root) {
		if (model==null) {model = Application.getModel();}
		ContentItem child;
		
		for (int i=0; i<root.getChildCount(); i++) {
			child = (ContentItem) root.getChildAt(i);
			TranslateSR(child);
			if (!child.isLeaf()) {readingSR(child);}
		}
	}
	
	public static void TranslateSR(ContentItem e) {
		String name = e.getConceptNameCodeMeaning().trim().replaceAll(" ", "_").replaceAll("\n", "");;
		String value = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
		logger.debug("Element Translation in Ontology : "+name+" : "+value);

		switch (name) {
		// Table 10011 CT Radiation Dose (part 16, p423)
		case "Procedure_reported":
			switch (value) {
			case "Computed_Tomography_X-Ray":
				CTradSR = createIndiv(generateName(value), getResource("CT_radiation_dose_SR"));
				addDataProperty(CTradSR, racineURI+"has_IRDBB_WADO_handle", ImportController.handle);
				break;
			default:
				logger.warn("Field "+name+" with Unknown Value "+value);
			} break;
		case "Start_of_X-Ray_Irradiation":
			startValue = value; break;
		case "End_of_X-Ray_Irradiation":
			endValue = value; break;
		case "Scope_of_Accumulation":
			switch (value) {
			case "Study":
				study = createIndiv(generateName("Imaging_study"), getResource("imaging_study"));
				addObjectProperty(CTradSR, racineURI+"has_scope_of_accumulation", study);
				addObjectProperty(CTradSR, racineURI+"is_about", study);  
				addDataProperty(study, racineURI+"has_dicom_UID", ImportController.studyInstanceUID);
				addObjectProperty(patientRole, "http://purl.obolibrary.org/obo/BFO_0000054", study );
				break;
			default:
				logger.warn("Field "+name+" with Unknown Value "+value);
			} break;	
		case "CT_Accumulated_Dose_Data":
			ContentItem test = verifChild(e, "CT_Dose_Length_Product_Total");
			if (test!=null) {
				accDose  = createIndivWithUnit(test.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", ""), "CT_Dose_Length_Product_Total", getURI("CT dose length product total"), CTradSR , racineURI+"has_dose");
				addObjectProperty(CTradSR, racineURI+"has_dose", accDose);
			} else {
				accDose = createIndiv(generateName("CT_Accumulated_Dose_Data"), getResource("accumulated_dose"));
				addObjectProperty(CTradSR, racineURI+"has_dose", accDose);
			} break;
		case "CT_Irradiation_Event_Data":
		case "CT_Acquisition":
			ContentItem type = verifChild(e, "CT_Acquisition_Type");
			String nameType = type.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
			switch (nameType) {
			case "Spiral_Acquisition":
				irradEvent = createIndiv(generateName(nameType), model.getResource("http://medicis.univ-rennes1.fr/ontologies/ontospm/OntoMEDIRAD.owl#spiral_acquisition"));
				break;
			case "Constant_Angle_Acquisition":
				irradEvent = createIndiv(generateName(nameType), getResource("constant_angle_CT_acquisition"));
				break;
			case "Cone_Beam_Acquisition":
				irradEvent = createIndiv(generateName(nameType), getResource("cone_beam_CT_acquisition"));
				break;
			case "Free_Acquisition":
				irradEvent = createIndiv(generateName(nameType), getResource("free_CT_acquisition"));
				break;
			case "Sequenced_Acquisition":
				irradEvent = createIndiv(generateName(nameType), getResource("sequenced_CT_acquisition"));
				break;
			case "Stationary_Acquisition":
				irradEvent = createIndiv(generateName(nameType), getResource("stationary_CT_acquisition"));
				break;
			default:
				logger.warn("Field "+name+" with Unknown Value "+value);
				irradEvent = createIndiv(generateName(nameType), getResource("CT_acquisition"));
			}
			addObjectProperty(irradEvent, "http://purl.obolibrary.org/obo/BFO_0000132", study); //part of occurrent
			irradEvent.addOntClass(model.getResource(racineURI+"CT_irradiation_event"));
			addObjectProperty(CTradSR, racineURI+"is_about_irradiation_event", irradEvent);	
			addDataProperty(irradEvent, racineURI+"has_beginning", startValue.toString()); 
			addDataProperty(irradEvent, racineURI+"has_end",       endValue.toString()); 
			addObjectProperty(patientRole, "http://purl.obolibrary.org/obo/BFO_0000054", irradEvent );
			break;
		case "Source_of_Dose_Information":
			switch (value) {
			case "Automated_Data_Collection":
				i = createIndiv(generateName(value), getResource("automated_data_collection"));
				addObjectProperty(CTradSR, racineURI+"is_specified_output_of", i);
				break;
			case "Computed_From_Image_Attributes":
				i = createIndiv(generateName(value), getResource("computed_from_image_attributes"));
				addObjectProperty(CTradSR, racineURI+"is_specified_output_of", i);
				break;
			case "Copied_From_Image_Attributes":
				i = createIndiv(generateName(value), getResource("copied_from_image_attributes"));
				addObjectProperty(CTradSR, racineURI+"is_specified_output_of", i);
				break;
			case "Derived_From Human-readable_Reports":
				i = createIndiv(generateName(value), getResource("derived_from_human-readable_reports"));
				addObjectProperty(CTradSR, racineURI+"is_specified_output_of", i);
				break;
			case "Manual_Entry":
				i = createIndiv(generateName(value), getResource("manual_entry"));
				addObjectProperty(CTradSR, racineURI+"is_specified_output_of", i);
				break;
			default:
				logger.warn("Field "+name+" with Unknown Value "+value);
			} break;

			// Table 10012 CT Accumulated Dose data (part 16, p424)
		case "Total_Number_of_Irradiation_Events":
			addDataProperty(study, racineURI+"has_number_of_irradiation_events", value.split("_")[0]);
			break;	
		case "CT_Dose_Length_Product_Total":	
			break;
		case "Measurement_Method":
			switch (value) {
			case "DLP_to_E_conversion_via_MC_computation":	     
				i = createIndiv(generateName(value), getResource("DLP_to_E_conversion_via_MC_computation"));
				addObjectProperty(irradEvent, getURI("has_caracteristic"), i);
				break;
			case "CTDIfreeair_to_E_conversion_via_MC_computation": 
				i = createIndiv(generateName(value), getResource("CTDIfreeair_to_E_conversion_via_MC computation"));
				addObjectProperty(irradEvent, getURI("has_caracteristic"), i);
				break;
			case "DLP_to_E_conversion_via_measurement":		     
				i = createIndiv(generateName(value), getResource("DLP_to_E_conversion_via_measurement"));
				addObjectProperty(irradEvent, getURI("has_caracteristic"), i);
				break;
			case "CTDIfreeair_to_E_conversion_via_measurement":    
				i = createIndiv(generateName(value), getResource("CTDIfreeair_to_E_conversion_via_measurement"));
				addObjectProperty(irradEvent, getURI("has_caracteristic"), i);
				break;  
			case "AAPM_220 ": //From Table 10013
				i = createIndiv(generateName(value), getResource("AAPM_220"));
				addObjectProperty(irradEvent, getURI("has_caracteristic"), i);
				break;
			}
		case "Patient_Model":
			logger.warn("Field "+name+" with Unknown Value "+value); break;	

			// Table 10013 CT Irradiation Event data (part 16, p426)		
		case "Acquisition Protocol":
			i = createIndiv(generateName("CT_acquisition_protocol"), getResource("CT acquisition protocol"));
			addObjectProperty(irradEvent, getURI("has_protocol"), i);
			addDataProperty(i, getURI("has_name"), value);
			break;
		case "Target_Region":
			ArrayList<Individual> listOrgan = createIndividualOrgan(value, e);
			for (int i = 0; i<listOrgan.size(); i++) {
				if (listOrgan.get(i).getURI().contains("phantom_device")) {
					addObjectProperty(irradEvent, getURI("scans"), listOrgan.get(i));
				} else {
					addObjectProperty(irradEvent, racineURI+"has_target_region", listOrgan.get(i));
				}			
			} break;
		case "CT_Acquisition_Type":break;
		case "Reconstruction_Algorithm": 
			switch(value) {
			case "Filtered_Back_Projection":
				i = createIndiv(generateName("Filtered_Back_Projection"), getResource("filtered_back_projection"));
				addObjectProperty(CTradSR, racineURI+"use_algorithm", i);
				break;
			case "Iterative_Reconstruction":
				i = createIndiv(generateName("Iterative_Reconstruction"), getResource("iterative_reconstruction"));
				addObjectProperty(CTradSR, racineURI+"use_algorithm", i);
				break;
			} break;
		case "Irradiation_Event_UID":
			addDataProperty(irradEvent, racineURI+"has_DICOM_UID", value); break; 
		case "Irradiation_Event_Label":
			addDataProperty(irradEvent, racineURI+"has_id", value); break;	// A vérifier
		case "Label_Type":break;                                            // A vérifier
		case "Is_repeated_Acquisition":
			if (value=="Yes") {irradEvent.addOntClass(getResource("is_repeated_acquisition"));}
			break;
		case "Reason_for_Repeating_Acquisition":
			switch (value) {
			case "Patient_motion":
				addObjectProperty(irradEvent, getURI("has_reason_for_repeating_acquisition"), createIndiv(generateName("Patient_motion"), getResource("patient_motion")));
				break;
			case "Suboptimal_contrast_timing":
				addObjectProperty(irradEvent, getURI("has_reason_for_repeating_acquisition"), createIndiv(generateName("Suboptimal_contrast_timing"), getResource("suboptimal_contrast_timing")));
				break;
			default:
				logger.warn("Undefined Field : "+name+" with Unknown Value "+value); 
			} break;
		case "CT_Acquisition_Parameters":break;
		case "Exposure_Time":
			createIndivWithUnit(value, "Exposure_Time", getURI("exposure time"), irradEvent, racineURI+"has_setting");
			break;
		case "Scanning_Length":
			scanningLength = createIndivWithUnit(value, "Scanning_Length", getURI("scanning_length"), irradEvent, racineURI+"has_setting");
			break;
		case "Nominal_Single_Collimation_Width":
			createIndivWithUnit(value, "Nominal_Single_Collimation_Width", getURI("nominal_single_collimation_width"), irradEvent, racineURI+"has_setting");
			break;
		case "Nominal_Total_Collimation_Width":
			createIndivWithUnit(value, "Nominal_Total_Collimation_Width", getURI("nominal_total_collimation_width"), irradEvent, racineURI+"has_setting");
			break;	
		case "Pitch_Factor":
			createIndivWithUnit(value, "Pitch_Factor", getURI("pitch_factor"), irradEvent, racineURI+"has_setting");
			break;
		case "CT_X-Ray_Source_Parameters":break;
		case "Number_of_X-Ray_Sources":
			if (Integer.parseInt(value.split("_")[0])==1) {
				i = createIndiv(generateName("CT_scanner"), getResource("CT_scanner"));
			} else {
				i = createIndiv(generateName("multi_source_CT_scanner"), getResource("multi_source_CT_scanner"));
				addDataProperty(i, getURI("has_number_of_X-Ray_sources"), value.split("_")[0]);
			}
			addObjectProperty(i, racineURI+"used_as_instrument_in", irradEvent);
			break;		
		case "Identification_of_the_X-Ray_Source":
			i = createIndiv(generateName("X-Ray_Source"), getResource("X_Ray_source")); 
			addDataProperty(i, racineURI+"has_id", value); break;	
		case "KVP":			
			createIndivWithUnit(value, "KVP", getURI("KVP"), irradEvent, racineURI+"has_setting");
			break;	
		case "Maximum_X-Ray_Tube_Current":
			createIndivWithUnit(value, "Maximum_X-Ray_Tube_Current", getURI("maximum_X_ray_tube_current"), irradEvent, racineURI+"has_setting");
			break;
		case "X-Ray_Tube_Current":
			createIndivWithUnit(value, "X-Ray_Tube_Current", getURI("X_ray_tube_current"), irradEvent, racineURI+"has_setting");
			break;
		case "Exposure_Time_per_Rotation":
			createIndivWithUnit(value, "Exposure_Time_per_Rotation", getURI("exposure_time"), irradEvent, racineURI+"has_setting");
			break;
		case "CT_Dose":break;
		case "X-Ray_Filter_Aluminium_Equivalent":
			createIndivWithUnit(value, "X-Ray_Filter_Aluminium_Equivalent", getURI("X-ray_filter_aluminum_equivalent"), irradEvent, racineURI+"has_setting");
			break;		
		case "Mean_CTDIvol":
			CTDI = createIndivWithUnit(value, "Mean_CTDIvol", getURI("mean_CTDIvol"), CTradSR, racineURI+"has_dose");
			addObjectProperty(CTDI, racineURI+"is_about_irradiation_event", irradEvent);
			break;	
		case "CTDIw_Phantom_Type":
			switch (value ) {
			case "IEC_Body_Dosimetry_Phantom":
				CTDIwPhantomType = createIndiv(generateName(value), getResource("IEC_body_dosimetry_phantom"));
				addObjectProperty(CTDI, racineURI+"estimated_from_phantom", CTDIwPhantomType); 
				break;
			case "IEC_Head_Dosimetry_Phantom":
				i = createIndiv(generateName(value), getResource("IEC_head_dosimetry_phantom"));
				addObjectProperty(CTDI, racineURI+"estimated_from_phantom", CTDIwPhantomType); 
				break;
			case "NEMA_XR21-2000_Phantom":
				i = createIndiv(generateName(value), getResource("NEMA_xr21-2000_phantom"));
				addObjectProperty(CTDI, racineURI+"estimated_from_phantom", CTDIwPhantomType); 
				break;
			default:
				logger.warn("Field "+name+" with value : "+value);
			} break;
		case "CTDIfreeair_Calculation_factor": 
			createIndivWithUnit(value, "CTDIfreeair_Calculation_factor", getURI("CTDIfreeair_calculation_factor"), CTDI, racineURI+"has_setting");
			break;	
		case "Mean_CTDIfreeair":
			createIndivWithUnit(value, "Mean_CTDIfreeair", getURI("mean CTDIfreeair"), CTDI, racineURI+"has_specified_input");
			break;
		case "DLP":
			DLP = createIndivWithUnit(value, "DLP", getURI("DLP"), CTradSR, racineURI+"has_dose");
			addObjectProperty(DLP, racineURI+"is_about_irradiation_event", irradEvent);
			addObjectProperty(DLP, racineURI+"estimated_from_phantom", CTDIwPhantomType); 
			break;	
		case "Measured_Lateral_Dimension":
			createIndivWithUnit(value, "Measured_Lateral_Dimension", getURI("measured_lateral_dimension"), irradEvent, getURI("has_measure"));
			break;
		case "Measured_AP_Dimension":
			createIndivWithUnit(value, "Measured_AP_Dimension", getURI("measured_AP_dimension"), irradEvent, getURI("has_measure"));
			break;
		case "Derived_Effective_Diameter":
			createIndivWithUnit(value, "Derived_Effective_Diameter", getURI("derived_effective_diameter"), irradEvent, getURI("has_measure"));
			break;
		case "Water_Equivalent_Diameter":
			createIndivWithUnit(value, "Water_Equivalent_Diameter", getURI("water_equivalent_diameter"), irradEvent, getURI("has_measure"));
			break;
		case "X-Ray_Modulation_Type":
			i = createIndiv(generateName("X-Ray_Modulation_Type"), getResource("X-ray_modulation_type"));
			addDataProperty(i, getURI("has name"),value);
			addObjectProperty(irradEvent, getURI("has description"), i);                // A REEVALUER
			break; 
		case "Person_Participant":
		case "Device_Participant":
			logger.warn("Field "+name+" with value : "+value);
			break;

			// Table 10014 Scanning Length (part 16, page 432)
		case "Length_of_Reconstructable_Volume":break;
		case "Exposed_Range":
			i = createIndivWithUnit(value, "Exposed_Range", racineURI+"exposed_range", scanningLength, getURI("has_measure")); // relier à l'acquisition
			addObjectProperty(irradEvent, getURI("has_measure"), i);
			break;
		case "Top_Z_Location_of_Reconstructable_Volume":
			i = createIndivWithUnit(value, "Top_Z_Location_of_Reconstructable_Volume", getURI("top_z_location_of_reconstructable_volume"), scanningLength, getURI("has_measure"));// relier à l'acquisition
			addObjectProperty(irradEvent, getURI("has_measure"), i);
			break;		
		case "Bottom_Z_Location_of_Reconstructable_Volume":
			i = createIndivWithUnit(value, "Bottom_Z_Location_of_Reconstructable_Volume", getURI("bottom_z_location_of_reconstructable_volume"), scanningLength, getURI("has_measure"));// relier à l'acquisition
			addObjectProperty(irradEvent, getURI("has_measure"), i);
			break;	
		case "Top_Z_Location_of_Scanning_Length":
			i = createIndivWithUnit(value, "Top_Z_Location_of_Scanning_Length", getURI("top_z_location_of_scanning_length"), scanningLength, getURI("has_measure"));// relier à l'acquisition
			addObjectProperty(irradEvent, getURI("has_measure"), i);
			break;		
		case "Bottom_Z_Location_of_Scanning_Length":
			i = createIndivWithUnit(value, "Bottom_Z_Location_of_Scanning_Length", getURI("bottom z location of scanning length"), scanningLength, getURI("has_measure"));// relier à l'acquisition
			addObjectProperty(irradEvent, getURI("has_measure"), i);
			break;		

			// Ignorer
		case "CTDIvol_Alert_Value":
		case "CTDIvol_Notification_Value_Configured":
		case "DLP_Notification_Value_Configured":
		case "Dose_Check_Notification_Details":
		case "Dose_Check_Alert_Details":
		case "CTDIvol_Alert_Value_Configured":
		case "DLP_Alert_Value_Configured":	
		case "Comment": break;

		default:
			logger.warn("Unknown Field : "+name);
		}
	}

}