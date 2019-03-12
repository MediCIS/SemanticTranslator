package repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.jena.ontology.Individual;
import org.apache.jena.rdf.model.ModelFactory;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Sequence;
import org.dcm4che3.data.Tag;

import com.pixelmed.dicom.ContentItem;

import javaXSDclass.CTImageAcquisitionDescriptorType;
import javaXSDclass.DICOMSOPInstanceDescriptorType;
import javaXSDclass.DICOMSeriesDescriptorType;
import javaXSDclass.DICOMSeriesType;
import javaXSDclass.DICOMStudyDescriptorType;
import javaXSDclass.DICOMStudyType;
import javaXSDclass.DicomFileSetDescriptor;
import javaXSDclass.NonDicomFileSetDescriptor;
import javaXSDclass.PatientDescriptorType;

public class TranslateDicomData extends OntologyPopulator {

	static String value; static String name; static String studyInstUID;
	static Individual radioPharSR; static Individual study; static Individual accDose;
	static Individual irradEvent; static  Individual scanningLength; static Individual CTDI;
	static Individual DLP; static Individual CTDIwPhantomType; static Individual CTradSR;
	static String startValue; static String endValue; static String unit;
	static DicomFileSetDescriptor DicomFileSetDescriptorContent;
	static NonDicomFileSetDescriptor nonDicomFileSetDescriptorContent;
	static Individual patient;
	static Iterator<Attributes> iter;

	public static void readingCT(Iterator<DICOMStudyType> iterFileSet, String studyInstanceUID, String seriesInstanceUID, Individual clinicalResearchStudy, PatientDescriptorType patientDescriptor) {
		DICOMStudyType study; DICOMStudyDescriptorType studyDescriptor; 
		Individual imagingStudy; String organName; ArrayList<Individual> organs;
		List<DICOMSeriesType> dicomSeries; Iterator<DICOMSeriesType> serieIterator; DICOMSeriesType serie;
		DICOMSeriesDescriptorType serieDescriptorType;  Individual institution = null; Individual protocol;
		List<DICOMSOPInstanceDescriptorType> dicomsopInstanceDescriptor;
		DICOMSOPInstanceDescriptorType dicomsopInstanceDescriptorType;
		CTImageAcquisitionDescriptorType ctimageAcquisitionDescriptorType; Individual ctImageAcquisition = null;
		Individual scanner; Individual imageDataSet; Individual imageAccRole;
		Individual role_of_responsible_organization = null; Iterator<DICOMSOPInstanceDescriptorType> dicomsopInstanceDescriptorIter;

		if (dataModel==null) {model = Application.dataModel;}

		if (memory==null) {memory = Application.memory;}

		patient = retrievePatientData(patientDescriptor);

		while (iterFileSet.hasNext()) {					// allow to iter on multiple fileset
			study = iterFileSet.next();
			studyDescriptor = study.getDICOMStudyDescriptor(); 

			handle = "/pacs/studies/"+studyInstanceUID+"/series/"+seriesInstanceUID; 

			memory.setPatient(seriesInstanceUID, studyInstanceUID, patient);
			addObjectProperty(clinicalResearchStudy, racineURI+"has_patient", patient);

			imagingStudy = createIndiv(generateName("imaging_Study"), model.getResource(racineURI+"imaging_study")); 

			addDataProperty(imagingStudy, racineURI+"has_description", studyDescriptor.getStudyDescription00081030());
			addDataProperty(imagingStudy, racineURI+"has_beginning_date", studyDescriptor.getStudyDate00080020());
			addDataProperty(imagingStudy, racineURI+"has_beginning_time", studyDescriptor.getStudyTime00080030());

			organName = studyDescriptor.getBodyPartExamined00180015();
			organName = organName.toLowerCase();

			organs = createIndividualOrgan(organName);
			for (int i=0; i<organs.size(); i++) {			// organs are provided as a list (because 1 name can be many organs as FMA)
				addObjectProperty(imagingStudy, racineURI+"has_target_region", organs.get(i));
			}

			dicomSeries = study.getDICOMSeries();
			serieIterator = dicomSeries.iterator();

			while (serieIterator.hasNext()) {
				serie = serieIterator.next();
				serieDescriptorType = serie.getDICOMSeriesDescriptor();

				imageDataSet = createIndiv(generateName("CT_image_dataset"), model.getResource(racineURI+"CT_image_dataset"));
				addDataProperty(imageDataSet, racineURI+"has_IRDBB_WADO_handle", handle);
				if (memory==null) {logger.debug("memory : NULL");}
				memory.setCtDataSet(seriesInstanceUID, studyInstanceUID, imageDataSet);

				addObjectProperty(clinicalResearchStudy, racineURI+"has_patient",patientRole);

				ctImageAcquisition = createIndiv(generateName("CT_acquisition"),model.getResource(racineURI+"CT_acquisition"));
				ctImageAcquisition.addOntClass(model.getResource(racineURI+"CT_irradiation_event"));
				addObjectProperty(patientRole, racineObo+"BFO_0000054" , ctImageAcquisition);

				for (int i=0; i<organs.size(); i++) {
					addObjectProperty(ctImageAcquisition, racineURI+"has_target_region", organs.get(i));
				}

				addObjectProperty(ctImageAcquisition, racineURI+"part_of_occurent", imagingStudy);
				addObjectProperty(imageDataSet, racineURI+"is_specified_output_of", ctImageAcquisition);
				addDataProperty(ctImageAcquisition, racineURI+"has_beginning_date", serieDescriptorType.getSeriesDate00080021());
				addDataProperty(ctImageAcquisition, racineURI+"has_beginning_time", serieDescriptorType.getSeriesTime00080031());
				addDataProperty(imageDataSet, racineURI+"has_DICOM_series_instance_UID", serieDescriptorType.getSeriesInstanceUID0020000E());

				scanner = createIndiv(generateName("CT_scanner"), model.getResource(racineURI+"CT_scanner"));
				imageAccRole = createIndiv(generateName("image_acquisition_role"), model.getResource(racineURI+"image_acquisition_role"));

				addObjectProperty(imageAccRole, racineObo+"BFO_0000052", scanner);
				addObjectProperty(imageAccRole, racineObo+"BFO_0000054", ctImageAcquisition);
				addDataProperty(scanner, racineURI+"has_manufacturer_name", serieDescriptorType.getManufacturer00080070());
				addDataProperty(scanner, racineURI+"has_model_name", serieDescriptorType.getManufacturersModelName00081090());

				if (serieDescriptorType.getInstitutionName00080080() != null && serieDescriptorType.getInstitutionName00080080() !=null) {
					institution = memory.getInstitution(serieDescriptorType.getInstitutionName00080080());
					role_of_responsible_organization = memory.getRoleOfResponsibleOrganization(serieDescriptorType.getInstitutionName00080080());
				}

				addObjectProperty(role_of_responsible_organization, racineObo+"BFO_0000052", institution);
				addObjectProperty(role_of_responsible_organization, racineObo+"BFO_0000054", ctImageAcquisition);

				protocol = createIndiv(generateName("Protocol"), model.getResource(racineURI+"CT_acquisition_protocol"));
				addDataProperty(protocol, racineURI+"has_name", serieDescriptorType.getProtocolName00181030());
				addObjectProperty(ctImageAcquisition, racineURI+"has_protocol", protocol);

				dicomsopInstanceDescriptor = serie.getDICOMSOPInstanceDescriptor();
				dicomsopInstanceDescriptorIter = dicomsopInstanceDescriptor.iterator();

				while (dicomsopInstanceDescriptorIter.hasNext()) {
					dicomsopInstanceDescriptorType = dicomsopInstanceDescriptorIter.next();
					ctimageAcquisitionDescriptorType = dicomsopInstanceDescriptorType.getCTImageAcquisitionDescriptor();

					i =  createIndiv(generateName("KVP"), model.getResource(racineDCM+"113733"));
					addDataProperty(i, racineObo+"IAO_0000004", ctimageAcquisitionDescriptorType.getKVP00180060());
					addObjectProperty(i, racineObo+"IAO_0000039", getUnit("kV"));
					addObjectProperty(ctImageAcquisition,racineURI+"has_setting",i);
					addObjectProperty(i,racineURI+"is_device_setting",scanner);

					i =  createIndiv(generateName("Exposure_Time"), model.getResource(racineDCM+"113824"));
					addDataProperty(i, racineObo+"IAO_0000004", ctimageAcquisitionDescriptorType.getExposureTime00181150());
					addObjectProperty(i, racineObo+"IAO_0000039", getUnit("ms")); 
					addObjectProperty(ctImageAcquisition,racineURI+"has_setting",i);
					addObjectProperty(i,racineURI+"is_device_setting",scanner);

					i =  createIndiv(generateName("X-Ray_Tube_Current"), model.getResource(racineDCM+"113734"));
					addDataProperty(i, racineObo+"IAO_0000004", ctimageAcquisitionDescriptorType.getXRayTubeCurrent00181151());
					addObjectProperty(i, racineObo+"IAO_0000039", getUnit("mA"));
					addObjectProperty(ctImageAcquisition,racineURI+"has_setting",i);
					addObjectProperty(i,racineURI+"is_device_setting",scanner);
				}
			}
		}
	}

	public static void readingSR(ContentItem root) throws IOException { // recursive function for read SR tree
		if (model==null) {model = Application.getModel();}
		if (dataModel==null) {model = Application.dataModel;}

		ContentItem child;

		for (int i=0; i<root.getChildCount(); i++) {  // Iterate on each child elements
			child = (ContentItem) root.getChildAt(i);
			TranslateSR(child);						  // Translate the element
			if (!child.isLeaf()) {readingSR(child);}  // Iter on the child element(s)
		}
	}

	public static void TranslateSR(ContentItem e) throws IOException {
		String name = e.getConceptNameCodeMeaning().trim().replaceAll(" ", "_").replaceAll("\n", ""); 
		String value = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
		logger.debug("Element Translation in Ontology : "+name+" : "+value);

		switch (name) { // Cases are ordered as SR references description table for make them more human readable

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
				if (patientRole!=null) {
					addObjectProperty(patientRole, "http://purl.obolibrary.org/obo/BFO_0000054", study );
				}
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

	public enum TypeDicom {CT,SR}

	@SuppressWarnings("unused")
	public static void translateDicomMetaData(Attributes root, TypeDicom typeDicom) {
		if (model==null) {model = Application.getModel();}
		if (dataModel==null) {model = Application.dataModel;}
		populateModel = ModelFactory.createOntologyModel();
		if (memory==null) {memory = Application.memory;}

		// Commmon MeteDatas

		Individual imagingStudy = createIndiv(generateName("imaging_study"), model.getResource(racineURI+"imaging_study"));	

		String PatientID = root.getString(Tag.PatientID);
		logger.debug("PatientID : "+PatientID);
		if (PatientID!=null) {
			patient = memory.getHuman(PatientID);														// create human
			patientRole = createIndiv(generateName("Patient"), model.getResource(racineURI+"patient")); // create patient role		
			addObjectProperty(patient, racineObo+"BFO_0000087", patientRole);							// link both of them
			addDataProperty(patient, racineURI+"has_id", PatientID);

		}

		String PatientBirthDate = root.getString(Tag.PatientBirthDate);
		logger.debug("PatientBirthDate : "+PatientBirthDate);
		if (PatientBirthDate!=null) {
			addDataProperty(patient, racineURI+"has_birthdate", PatientBirthDate);
		}

		String PatientSex = root.getString(Tag.PatientSex);
		logger.debug("PatientSex : "+PatientSex);

		if (PatientSex!=null) {
			Individual sexe;
			switch (PatientSex) {
			case "M":
				sexe = createIndiv(model.getResource(racineObo+"PATO_0000384"));
				addObjectProperty(patient, racineURI+"has_sex", sexe); break;
			case "F":
				sexe = createIndiv(model.getResource(racineObo+"PATO_0000383")); 
				addObjectProperty(patient, racineURI+"has_sex", sexe); break;	
			default:break;
			}
		}

		String PatientAge = root.getString(Tag.PatientAge);
		logger.debug("PatientAge : "+PatientAge);
		if (PatientAge != null) {
			i = createIndiv(generateName("age_of_patient_undergoing_medical_procedure"), model.getResource(racineURI+"age_of_patient_undergoing_medical_procedure"));
			String test = PatientAge.substring(0, PatientAge.length()-1);
			switch (test) {
			case "M":
				PatientAge = PatientAge.replaceAll("M", "");
				addDataProperty(i, racineURI+"months", PatientAge); break;
			case "Y":
				PatientAge = PatientAge.replaceAll("Y", "");
				addDataProperty(i, racineURI+"years", PatientAge); break;
			default:
				addDataProperty(i, "http://purl.obolibrary.org/obo/IAO_0000004", PatientAge); break;
			}
			addObjectProperty(i, racineURI+"is_about", patient);
			addObjectProperty(i, racineURI+"is_about_procedure", imagingStudy);
		}

		String PatientSize = root.getString(Tag.PatientSize);
		logger.debug("PatientSize : "+PatientSize);
		if (PatientSize != null) {
			i = createIndiv(generateName("Patient_Height"), model.getResource(racineURI+"patient_height"));
			addObjectProperty(i, racineURI+"is_about", patient);
			addObjectProperty(i, racineURI+"is_about_procedure", imagingStudy);
			addDataProperty(i, "http://purl.obolibrary.org/obo/IAO_0000004", PatientSize);
			addObjectProperty(i, "http://purl.obolibrary.org/obo/IAO_0000039", createIndiv(model.getResource(racineObo+"UO_0000008")));
		}

		String PatientWeight = root.getString(Tag.PatientWeight);
		logger.debug("PatientWeight : "+PatientWeight);
		if (PatientWeight!=null) {
			i = createIndiv(generateName("Patient_Weight"), model.getResource(racineURI+"patient_weight"));
			addDataProperty(i, "http://purl.obolibrary.org/obo/IAO_0000004", PatientWeight);
			addObjectProperty(i, racineURI+"is_about", patient);
			addObjectProperty(i, racineURI+"is_about_procedure", imagingStudy);
			addObjectProperty(i, "http://purl.obolibrary.org/obo/IAO_0000039", createIndiv(model.getResource(racineObo+"UO_0000009")));
		}

		String StudyInstanceUID = root.getString(Tag.StudyInstanceUID);
		logger.debug("StudyInstanceUID : "+StudyInstanceUID);
		if (StudyInstanceUID!=null) {
			addDataProperty(imagingStudy, racineURI+"has_DICOM_study_instance_UID", StudyInstanceUID);
		}

		String StudyDate = root.getString(Tag.StudyDate);
		logger.debug("StudyDate : "+StudyDate);
		if (StudyDate!=null) {
			addDataProperty(imagingStudy, racineURI+"has_beginning_date", StudyDate);
		}

		String StudyTime = root.getString(Tag.StudyTime);
		logger.debug("StudyTime : "+StudyTime);
		if (StudyTime!=null) {
			addDataProperty(imagingStudy, racineURI+"has_beginning_time", StudyTime);
		}

		String BodyPartExamined = root.getString(Tag.BodyPartExamined);
		logger.debug("BodyPartExamined : "+BodyPartExamined);
		if (BodyPartExamined!=null) {
			if (!BodyPartExamined.contains("null")) {
				ArrayList<Individual> organList = createIndividualOrgan(BodyPartExamined);
				Iterator<Individual> iterOrgan = organList.iterator();
				while (iterOrgan.hasNext()) { 
					addObjectProperty(imagingStudy, racineURI+"has_target_region", iterOrgan.next());
				}
			}
		}

		String StudyDescription = root.getString(Tag.StudyDescription);
		logger.debug("StudyDescription : "+StudyDescription);
		if (StudyDescription!=null) {
			addDataProperty(imagingStudy, racineURI+"has_description", StudyDescription);
		}

		Individual acquisition = null;
		Individual acquisitionProtocol = null;
		Individual acquisitionDevice = null;
		//String ReferencedSOPClassUID = root.getString(Tag.ReferencedSOPClassUID);
		String SOPClassUID = root.getString(Tag.SOPClassUID);
		logger.debug("SOPClassUID : "+SOPClassUID);
		
		String[] ImageType = root.getStrings(Tag.ImageType);
		logger.debug("ImageType : "+ImageType);
		
		if (SOPClassUID.contains("1.2.840.10008.5.1.4.1.1.2")) {
			//ontomedirad:CT_acquisition
			acquisition = createIndiv(generateName("CT_acquisition"), model.getResource(racineURI+"CT_acquisition"));
			acquisitionDevice = createIndiv(generateName("CT_scanner"), model.getResource(racineURI+"CT_scanner"));
			acquisitionProtocol = createIndiv(generateName("CT_acquisition_protocol"), model.getResource(racineURI+"CT_acquisition_protocol"));
		} else if ( SOPClassUID.contains("1.2.840.10008.5.1.4.1.1.128") || SOPClassUID.contains("1.2.840.10008.5.1.4.1.1.130")) {
			//ontomedirad:PET_acquisition
			acquisition = createIndiv(generateName("PET_data_acquisition"), model.getResource(racineURI+"PET_data_acquisition"));
			acquisitionDevice = createIndiv(generateName("PET_scanner"), model.getResource(racineURI+"PET_scanner"));
			acquisitionProtocol = createIndiv(generateName("PET_acquisition_protocol"), model.getResource(racineURI+"PET_acquisition_protocol"));
		} else if ( ImageType[2].contains("TOMO PROJ") || ImageType[2].contains("TOMO RECON") || ImageType[2].contains("STATIC") || ImageType[2].contains("WHOLE BODY") ) { //NM
			acquisition = createIndiv(generateName("planar_NM_data_acquisition"), model.getResource(racineURI+"PET_data_acquisition"));
			acquisitionProtocol = createIndiv(generateName("NM_acquisition_protocol"), model.getResource(racineURI+"NM_acquisition_protocol"));
		}
		addObjectProperty(acquisition, racineURI+"has_protocol", acquisitionProtocol);
		
		String SeriesInstanceUID = root.getString(Tag.SeriesInstanceUID);
		logger.debug("SeriesInstanceUID : "+SeriesInstanceUID);
		if (SeriesInstanceUID!=null) {
			addDataProperty(acquisition, racineURI+"has_DICOM_series_instance_UID", SeriesInstanceUID);
		}
		//ontomedirad:SPECT_acquisition

		if (acquisition!=null) {
			addObjectProperty(acquisition, racineObo+"BFO_0000132", imagingStudy);

			String SeriesNumber = root.getString(Tag.SeriesNumber);
			logger.debug("SeriesNumber : "+SeriesNumber);
			if (SeriesNumber!=null) {
				addDataProperty(acquisition, racineURI+"has_id", SeriesNumber);
			}

			String SeriesDate = root.getString(Tag.SeriesDate);
			logger.debug("SeriesDate : "+SeriesDate);
			if (SeriesDate!=null) {
				addDataProperty(acquisition, racineURI+"has_beginning_date", SeriesDate);
			}

			String SeriesTime = root.getString(Tag.SeriesTime);
			logger.debug("SeriesTime : "+SeriesTime);
			if (SeriesTime!=null) {
				addDataProperty(acquisition, racineURI+"has_beginning_time", SeriesTime);
			}
		}

		if (acquisitionDevice!=null) {
			String Manufacturer = root.getString(Tag.Manufacturer);
			logger.debug("Manufacturer : "+Manufacturer);
			if (Manufacturer!=null) {
				addDataProperty(acquisitionDevice, racineURI+"has_manufacturer_name", Manufacturer);
			}

			String ManufacturersModelName = root.getString(Tag.ManufacturerModelName);
			logger.debug("ManufacturersModelName : "+ManufacturersModelName);
			if (ManufacturersModelName!=null) {
				addDataProperty(acquisitionDevice, racineURI+"has_model_name", ManufacturersModelName);
			}
			i = createIndiv(generateName("image_acquisition_role"), model.getResource(racineURI+"image_acquisition_role"));
			addObjectProperty(acquisitionDevice, racineObo+"BFO_0000087", i);
			addObjectProperty(i, racineObo+"BFO_0000054", acquisition);
		}

		String InstitutionName = root.getString(Tag.InstitutionName);
		logger.debug("InstitutionName : "+InstitutionName);
		if (InstitutionName!=null) {
			Individual institution = memory.getInstitution(InstitutionName);
			if (acquisition!=null) {
				addObjectProperty(acquisition, racineObo+"BFO_0000054", memory.getRoleOfResponsibleOrganization(InstitutionName));
			}
		}

		if (acquisitionProtocol!=null) {
			String ProtocolName = root.getString(Tag.ProtocolName);
			logger.debug("ProtocolName : "+ProtocolName);
			if (ProtocolName!=null) {
				addDataProperty(acquisitionProtocol, racineURI+"has_name", ProtocolName);
			}

			String SeriesDescription = root.getString(Tag.SeriesDescription);
			logger.debug("SeriesDescription : "+SeriesDescription);
			if (SeriesDescription!=null) {
				addDataProperty(acquisitionProtocol, racineURI+"has_description", SeriesDescription);
			}
		}
				
		if (SOPClassUID.contains("1.2.840.10008.5.1.4.1.1.2") && ImageType[2].contains("AXIAL")) { // 3.1
			logger.debug("CT_image_dataset");
			Individual dataSet = createIndiv(generateName("CT_image_dataset"), model.getResource(racineURI+"CT_image_dataset"));
			
			logger.debug("SOPClassUID : "+SOPClassUID);
			addObjectProperty(dataSet, racineURI+"has_format", createIndiv(model.getResource(racineURI+"DICOM_CT_image_storage_SOP_class")));

			SeriesInstanceUID = root.getString(Tag.SeriesInstanceUID);
			logger.debug("SeriesInstanceUID : "+SeriesInstanceUID);
			addDataProperty(dataSet, racineURI+"has_DICOM_series_instance_UID", SeriesInstanceUID);
			addObjectProperty(dataSet, racineURI+"is_specified_output_of", acquisition);

			String FrameOfReferenceUID = root.getString(Tag.FrameOfReferenceUID);
			logger.debug("FrameOfReferenceUID : "+FrameOfReferenceUID);
			addDataProperty(dataSet, racineURI+"has_DICOM_frame_of_reference_UID", FrameOfReferenceUID);

			String AcquisitionDate = root.getString(Tag.AcquisitionDate);
			logger.debug("AcquisitionDate : "+AcquisitionDate);
			if (AcquisitionDate != root.getString(Tag.SeriesDate)) {
				logger.warn("AcquisitionDate and SeriesDate are different");
			}

			String AcquisitionTime = root.getString(Tag.AcquisitionTime);
			logger.debug("AcquisitionTime : "+AcquisitionTime);
			if (AcquisitionTime != root.getString(Tag.SeriesTime)) {
				logger.warn("AcquisitionTime and SeriesTime are different");
			}

			// AcquisitionTypeOnly
			
			// RevolutionTime
			
			String TotalCollimationWidth = root.getString(Tag.TotalCollimationWidth);
			logger.debug("TotalCollimationWidth : "+TotalCollimationWidth);
			if (TotalCollimationWidth!=null) {
				i = createIndiv(generateName("Nominal_Total_Collimation_Width"),model.getResource(racineDCM+"113827"));
				addDataProperty(i, racineObo+"IAO_0000004", TotalCollimationWidth);
				addObjectProperty(i, racineObo+"IAO_0000039", getUnit("mm"));
				addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
				addObjectProperty(i, racineURI+"is_device_setting_of", acquisitionDevice);
				addObjectProperty(acquisition, racineURI+"has_setting", i);
			}

			String GantryDetectorTilt = root.getString(Tag.GantryDetectorTilt);
			logger.debug("GantryDetectorTilt : "+GantryDetectorTilt);

			String TableFeedPerRotation = root.getString(Tag.TableFeedPerRotation);
			logger.debug("TableFeedPerRotation : "+TableFeedPerRotation);

			String SpiralPitchFactor = root.getString(Tag.SpiralPitchFactor);
			logger.debug("SpiralPitchFactor : "+SpiralPitchFactor);
			if (SpiralPitchFactor!=null) {
				i = createIndiv(generateName("Pitch_Factor"),model.getResource(racineDCM+"113828"));
				addDataProperty(i, racineObo+"IAO_0000004", SpiralPitchFactor);
				addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
				addObjectProperty(i, racineURI+"is_device_setting_of", acquisitionDevice);
				addObjectProperty(acquisition, racineURI+"has_setting", i);
			}

			String KVP = root.getString(Tag.KVP);
			logger.debug("KVP : "+KVP);
			if (KVP!=null) {
				i = createIndiv(generateName("KVP"),model.getResource(racineDCM+"113733"));
				addDataProperty(i, racineObo+"IAO_0000004", KVP);
				addObjectProperty(i, racineObo+"IAO_0000039", getUnit("kilovolt"));
				addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
				addObjectProperty(i, racineURI+"is_device_setting_of", acquisitionDevice);
				addObjectProperty(acquisition, racineURI+"has_setting", i);
			}

			String ExposureTime = root.getString(Tag.ExposureTime);
			logger.debug("ExposureTime : "+ExposureTime);
			if (ExposureTime!=null) {
				i = createIndiv(generateName("KVP"),model.getResource(racineDCM+"113733"));
				addDataProperty(i, racineObo+"IAO_0000004", ExposureTime);
				addObjectProperty(i, racineObo+"IAO_0000039", getUnit("ms"));
				addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
				addObjectProperty(i, racineURI+"is_device_setting_of", acquisitionDevice);
				addObjectProperty(acquisition, racineURI+"has_setting", i);
			}
			
			String XRayTubeCurrent = root.getString(Tag.XRayTubeCurrent);
			logger.debug("XRayTubeCurrent : "+XRayTubeCurrent);
			if (XRayTubeCurrent!=null) {
				i = createIndiv(generateName("XRayTubeCurrent"),model.getResource(racineDCM+"113734"));
				addDataProperty(i, racineObo+"IAO_0000004", XRayTubeCurrent);
				addObjectProperty(i, racineObo+"IAO_0000039", getUnit("ma"));
				addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
				addObjectProperty(i, racineURI+"is_device_setting_of", acquisitionDevice);
				addObjectProperty(acquisition, racineURI+"has_setting", i);
			}

			String Exposure = root.getString(Tag.Exposure);
			logger.debug("ExposureTime : "+Exposure);
			if (Exposure!=null) {
				i = createIndiv(generateName("exposure_time"),model.getResource(racineDCM+"113824"));
				addDataProperty(i, racineObo+"IAO_0000004", Exposure);
				addObjectProperty(i, racineObo+"IAO_0000039", getUnit("microa/s"));
				addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
				addObjectProperty(i, racineURI+"is_device_setting_of", acquisitionDevice);
				//addObjectProperty(acquisition, racineURI+"has_setting", i);
			}
			
			String ExposureInmAs = root.getString(Tag.ExposureInmAs);
			logger.debug("ExposureTime : "+ExposureInmAs);
			if (ExposureInmAs!=null) {
				i = createIndiv(generateName("exposure_time"),model.getResource(racineDCM+"113824"));
				addDataProperty(i, racineObo+"IAO_0000004", ExposureInmAs);
				addObjectProperty(i, racineObo+"IAO_0000039", getUnit("ma/s"));
				addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
				addObjectProperty(i, racineURI+"is_device_setting_of", acquisitionDevice);
				//addObjectProperty(acquisition, racineURI+"has_setting", i);
			}
			
			String FilterType = root.getString(Tag.FilterType);
			logger.debug("FilterType : "+FilterType);
			Individual filter = null;

			if (FilterType!=null) {
				if (FilterType.toLowerCase().contains("butterfly")) {
					filter = createIndiv(generateName("butterfly_filter"), model.getResource(racineDCM+"113652"));
				} else if (FilterType.toLowerCase().contains("flat")) {
					filter = createIndiv(generateName("flat_filter"), model.getResource(racineDCM+"113653"));
				} else if (FilterType.toLowerCase().contains("strip")) {
					filter = createIndiv(generateName("strip_filter"), model.getResource(racineDCM+"113650"));
				} else if (FilterType.toLowerCase().contains("wedge")) {
					filter = createIndiv(generateName("wedge_filter"), model.getResource(racineDCM+"113651"));
				} else {
					filter = createIndiv(generateName("x-ray_filter"), model.getResource(racineDCM+"113771"));
				}
			}
			
			String FilterMaterial = root.getString(Tag.FilterMaterial);
			logger.debug("FilterMaterial : "+FilterMaterial);
			if (FilterMaterial!=null) {
				Individual filterMaterial;
				if (FilterMaterial.toLowerCase().contains("aluminium")) {
					filterMaterial = createIndiv(model.getResource(racineURI+"aluminum_or_aluminum_compound"));
				} else if (FilterMaterial.toLowerCase().contains("carbon")) {
					filterMaterial = createIndiv(model.getResource(racineURI+"carbon_fiber"));
				} else if (FilterMaterial.toLowerCase().contains("copper")) {
					filterMaterial = createIndiv(model.getResource(racineURI+"copper_or_copper_compound"));
				} else if (FilterMaterial.toLowerCase().contains("europium")) {
					filterMaterial = createIndiv(model.getResource(racineURI+"europium_or_europium_compound"));
				} else if (FilterMaterial.toLowerCase().contains("lead")) {
					filterMaterial = createIndiv(model.getResource(racineURI+"lead_or_lead_compound"));
				} else if (FilterMaterial.toLowerCase().contains("molybdenum")) {
					filterMaterial = createIndiv(model.getResource(racineURI+"molybdenum_or_molybdenum_compound"));
				} else if (FilterMaterial.toLowerCase().contains("niobium")) {
					filterMaterial = createIndiv(model.getResource(racineURI+"niobium_or_niobium_compound"));
				} else if (FilterMaterial.toLowerCase().contains("rhodium")) {
					filterMaterial = createIndiv(model.getResource(racineURI+"rhodium_or_rhodium_compound"));
				} else if (FilterMaterial.toLowerCase().contains("silver")) {
					filterMaterial = createIndiv(model.getResource(racineURI+"silver_or_silver_compound"));
				} else if (FilterMaterial.toLowerCase().contains("tantalum")) {
					filterMaterial = createIndiv(model.getResource(racineURI+"tantalum_or_tantalum_compound"));
				} else if (FilterMaterial.toLowerCase().contains("tin")) {
					filterMaterial = createIndiv(model.getResource(racineURI+"tin_or_tin_compound"));
				} else {
					filterMaterial = createIndiv(generateName(FilterMaterial),model.getResource(racineDCM+"113757"));
				}
				if (filter!=null) {
					addObjectProperty(filter,racineObo+"BFO_0000110",filterMaterial);
				}
			}
			
			String FocalSpots = root.getString(Tag.FocalSpots);
			logger.debug("FocalSpots : "+FocalSpots);
			if (FocalSpots!=null) {
				i = createIndiv(generateName("focal_spot"),model.getResource(racineURI+"focal_spot"));
				addDataProperty(i, racineObo+"IAO_0000004", FocalSpots);
				addObjectProperty(i, racineObo+"IAO_0000039", getUnit("ma/s"));
				addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
				addObjectProperty(i, racineURI+"is_device_setting_of", acquisitionDevice);
				addObjectProperty(acquisition, racineURI+"has_setting", i);
			}
			
			String ConvolutionKernel = root.getString(Tag.ConvolutionKernel);
			logger.debug("ConvolutionKernel : "+ConvolutionKernel);
			if (ConvolutionKernel!=null) {
				i = createIndiv(generateName("convolution_kernel"), model.getResource(racineURI+"convolution_kernel"));
				addDataProperty(i, racineURI+"has_name", ConvolutionKernel);
				addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
			}

			String DistanceSourceToPatient = root.getString(Tag.DistanceSourceToPatient);
			logger.debug("DistanceSourceToPatient : "+DistanceSourceToPatient);
			if (DistanceSourceToPatient!=null) {
				i = createIndiv(generateName("distance_source_to_patient"),model.getResource(racineURI+"distance_source_to_patient"));
				addDataProperty(i, racineObo+"IAO_0000004", DistanceSourceToPatient);
				addObjectProperty(i, racineObo+"IAO_0000039", getUnit("mm"));
				addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
				addObjectProperty(i, racineURI+"is_device_setting_of", acquisitionDevice);
				addObjectProperty(acquisition, racineURI+"has_setting", i);
			}
			
			String ExposureModulationType = root.getString(Tag.ExposureModulationType);
			logger.debug("ExposureModulationType : "+ExposureModulationType);
			if (ExposureModulationType==null || ExposureModulationType.equalsIgnoreCase("none")) {
				addObjectProperty(createIndiv(model.getResource(racineURI+"no_use_of_X_ray_modulation")), racineObo+"BFO_0000177", acquisitionProtocol);
			} else {
				i = createIndiv(model.getResource(racineURI+"use_of_x_ray_modulation"));
				addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
				addDataProperty(i,racineURI+"has_name",ExposureModulationType);
			}
			
			Sequence CTExposureSequence = root.getSequence(Tag.CTExposureSequence);
			if (CTExposureSequence != null) {
				iter = CTExposureSequence.iterator();
				while (iter.hasNext()) {
					Attributes t = iter.next();
					if (ExposureTime==null && t.getString(Tag.ExposureTimeInms)!=null) {
						String ExposureTimeInms = t.getString(Tag.ExposureTimeInms);
						logger.debug("ExposureTimeInms : "+ExposureTimeInms);
						i = createIndiv(generateName("KVP"),model.getResource(racineDCM+"113733"));
						addDataProperty(i, racineObo+"IAO_0000004", ExposureTimeInms);
						addObjectProperty(i, racineObo+"IAO_0000039", getUnit("ms"));
						addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
						addObjectProperty(i, racineURI+"is_device_setting_of", acquisitionDevice);
						addObjectProperty(acquisition, racineURI+"has_setting", i);
					}

					if (XRayTubeCurrent==null && t.getString(Tag.XRayTubeCurrentInmA)!=null) {
						String XRayTubeCurrentInMA = t.getString(Tag.XRayTubeCurrentInmA);
						logger.debug("XRayTubeCurrentInMA : "+XRayTubeCurrentInMA);
						i = createIndiv(generateName("XRayTubeCurrent"),model.getResource(racineDCM+"113734"));
						addDataProperty(i, racineObo+"IAO_0000004", XRayTubeCurrentInMA);
						addObjectProperty(i, racineObo+"IAO_0000039", getUnit("ma"));
						addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
						addObjectProperty(i, racineURI+"is_device_setting_of", acquisitionDevice);
						addObjectProperty(acquisition, racineURI+"has_setting", i);
					}

					if (ExposureInmAs==null && t.getString(Tag.ExposureInmAs)!=null) {
						String ExposureInMAs = t.getString(Tag.ExposureInmAs);
						logger.debug("ExposureInMAs : "+ExposureInMAs);
						i = createIndiv(generateName("exposure_time"),model.getResource(racineDCM+"113824"));
						addDataProperty(i, racineObo+"IAO_0000004", ExposureInMAs);
						addObjectProperty(i, racineObo+"IAO_0000039", getUnit("ma/s"));
						addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
						addObjectProperty(i, racineURI+"is_device_setting_of", acquisitionDevice);
					}
					if (ExposureModulationType==null && t.getString(Tag.ExposureModulationType)!=null) {
						ExposureModulationType = t.getString(Tag.ExposureModulationType);
						if (ExposureModulationType.equalsIgnoreCase("none")) {
							addObjectProperty(createIndiv(model.getResource(racineURI+"no_use_of_X_ray_modulation")), racineObo+"BFO_0000177", acquisitionProtocol);
						} else {
							logger.debug("ExposureModulationType : "+ExposureModulationType);
							i = createIndiv(model.getResource(racineURI+"use_of_x_ray_modulation"));
							addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
							addDataProperty(i,racineURI+"has_name",ExposureModulationType);
						}
					}
				}
				
				Sequence CTAcquisitionDetailsSequence = root.getSequence(Tag.CTAcquisitionDetailsSequence);
				if (CTExposureSequence != null) {
					if (CTExposureSequence != null) {
						iter = CTExposureSequence.iterator();
						while (iter.hasNext()) {
							Attributes t = iter.next();
							
							// RevolutionTime
							
							if (TotalCollimationWidth==null && t.getString(Tag.TotalCollimationWidth)!=null) {
								TotalCollimationWidth = t.getString(Tag.TotalCollimationWidth);
								i = createIndiv(generateName("Nominal_Total_Collimation_Width"),model.getResource(racineDCM+"113827"));
								addDataProperty(i, racineObo+"IAO_0000004", TotalCollimationWidth);
								addObjectProperty(i, racineObo+"IAO_0000039", getUnit("mm"));
								addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
								addObjectProperty(i, racineURI+"is_device_setting_of", acquisitionDevice);
								addObjectProperty(acquisition, racineURI+"has_setting", i);
							}
							
							// GantryDetectorTilt
							
							// TableFeedPerRotation
														
							if (SpiralPitchFactor==null && t.getString(Tag.SpiralPitchFactor)!=null) {
								SpiralPitchFactor = t.getString(Tag.SpiralPitchFactor);
								i = createIndiv(generateName("Pitch_Factor"),model.getResource(racineDCM+"113828"));
								addDataProperty(i, racineObo+"IAO_0000004", SpiralPitchFactor);
								addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
								addObjectProperty(i, racineURI+"is_device_setting_of", acquisitionDevice);
								addObjectProperty(acquisition, racineURI+"has_setting", i);
							}
						}
					}
				}
				
				// CTXRayDetailsSequence
				Sequence CTXRayDetailsSequence = root.getSequence(Tag.CTXRayDetailsSequence);
				if (CTXRayDetailsSequence != null) {
					if (CTXRayDetailsSequence != null) {
						iter = CTXRayDetailsSequence.iterator();
						while (iter.hasNext()) {
							Attributes t = iter.next();
							if (KVP==null &&  t.getString(Tag.KVP)!=null) {
								KVP = t.getString(Tag.KVP);
								logger.debug("KVP : "+KVP);
								i = createIndiv(generateName("KVP"),model.getResource(racineDCM+"113733"));
								addDataProperty(i, racineObo+"IAO_0000004", KVP);
								addObjectProperty(i, racineObo+"IAO_0000039", getUnit("kilovolt"));
								addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
								addObjectProperty(i, racineURI+"is_device_setting_of", acquisitionDevice);
								addObjectProperty(acquisition, racineURI+"has_setting", i);
							}
							
							// FilterType
							if (FilterType==null  && t.getString(Tag.FilterType)!=null) {
								FilterType = t.getString(Tag.FilterType);
								logger.debug("FilterType : "+FilterType);
								filter = null;

								if (FilterType!=null) {
									if (FilterType.toLowerCase().contains("butterfly")) {
										filter = createIndiv(generateName("butterfly_filter"), model.getResource(racineDCM+"113652"));
									} else if (FilterType.toLowerCase().contains("flat")) {
										filter = createIndiv(generateName("flat_filter"), model.getResource(racineDCM+"113653"));
									} else if (FilterType.toLowerCase().contains("strip")) {
										filter = createIndiv(generateName("strip_filter"), model.getResource(racineDCM+"113650"));
									} else if (FilterType.toLowerCase().contains("wedge")) {
										filter = createIndiv(generateName("wedge_filter"), model.getResource(racineDCM+"113651"));
									} else {
										filter = createIndiv(generateName("x-ray_filter"), model.getResource(racineDCM+"113771"));
									}
								}
							}
							if (FilterMaterial==null && t.getString(Tag.FilterMaterial)!=null) {
								FilterMaterial = t.getString(Tag.FilterMaterial);
								logger.debug("FilterMaterial : "+FilterMaterial);
								if (FilterMaterial!=null) {
									Individual filterMaterial;
									if (FilterMaterial.toLowerCase().contains("aluminium")) {
										filterMaterial = createIndiv(model.getResource(racineURI+"aluminum_or_aluminum_compound"));
									} else if (FilterMaterial.toLowerCase().contains("carbon")) {
										filterMaterial = createIndiv(model.getResource(racineURI+"carbon_fiber"));
									} else if (FilterMaterial.toLowerCase().contains("copper")) {
										filterMaterial = createIndiv(model.getResource(racineURI+"copper_or_copper_compound"));
									} else if (FilterMaterial.toLowerCase().contains("europium")) {
										filterMaterial = createIndiv(model.getResource(racineURI+"europium_or_europium_compound"));
									} else if (FilterMaterial.toLowerCase().contains("lead")) {
										filterMaterial = createIndiv(model.getResource(racineURI+"lead_or_lead_compound"));
									} else if (FilterMaterial.toLowerCase().contains("molybdenum")) {
										filterMaterial = createIndiv(model.getResource(racineURI+"molybdenum_or_molybdenum_compound"));
									} else if (FilterMaterial.toLowerCase().contains("niobium")) {
										filterMaterial = createIndiv(model.getResource(racineURI+"niobium_or_niobium_compound"));
									} else if (FilterMaterial.toLowerCase().contains("rhodium")) {
										filterMaterial = createIndiv(model.getResource(racineURI+"rhodium_or_rhodium_compound"));
									} else if (FilterMaterial.toLowerCase().contains("silver")) {
										filterMaterial = createIndiv(model.getResource(racineURI+"silver_or_silver_compound"));
									} else if (FilterMaterial.toLowerCase().contains("tantalum")) {
										filterMaterial = createIndiv(model.getResource(racineURI+"tantalum_or_tantalum_compound"));
									} else if (FilterMaterial.toLowerCase().contains("tin")) {
										filterMaterial = createIndiv(model.getResource(racineURI+"tin_or_tin_compound"));
									} else {
										filterMaterial = createIndiv(generateName(FilterMaterial),model.getResource(racineDCM+"113757"));
									}
									if (filter!=null) {
										addObjectProperty(filter,racineObo+"BFO_0000110",filterMaterial);
									}
								}
							}
							
							if (FocalSpots==null && t.getString(Tag.FocalSpots)!=null) {
								FocalSpots = t.getString(Tag.FocalSpots);
								i = createIndiv(generateName("focal_spot"),model.getResource(racineURI+"focal_spot"));
								addDataProperty(i, racineObo+"IAO_0000004", FocalSpots);
								addObjectProperty(i, racineObo+"IAO_0000039", getUnit("ma/s"));
								addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
								addObjectProperty(i, racineURI+"is_device_setting_of", acquisitionDevice);
								addObjectProperty(acquisition, racineURI+"has_setting", i);
							}
						}
					}
				}
			}


		} else if (SOPClassUID.contains("1.2.840.10008.5.1.4.1.1.2.1") && ImageType[2].contains("AXIAL")) { //3.2
			Individual dataSet = createIndiv(generateName("CT_image_dataset"),model.getResource(racineURI+"CT_image_dataset"));

			logger.debug("SOPClassUID : "+SOPClassUID);
			i = createIndiv(model.getResource(racineURI+"DICOM_enhanced_CT_image_storage_SOP_class"));
			addObjectProperty(dataSet,racineURI+"has_format",i);
			
			SeriesInstanceUID = root.getString(Tag.SeriesInstanceUID);
			logger.debug("SeriesInstanceUID : "+SeriesInstanceUID);
			addDataProperty(dataSet, racineURI+"has_DICOM_series_instance_UID", SeriesInstanceUID);
			addObjectProperty(dataSet, racineURI+"is_specified_output_of", acquisition);

			String FrameOfReferenceUID = root.getString(Tag.FrameOfReferenceUID);
			logger.debug("FrameOfReferenceUID : "+FrameOfReferenceUID);
			addDataProperty(dataSet, racineURI+"has_DICOM_frame_of_reference_UID", FrameOfReferenceUID);

			logger.debug("ImageType : "+ImageType);

			String AcquisitionDate = root.getString(Tag.AcquisitionDate);
			logger.debug("AcquisitionDate : "+AcquisitionDate);
			if (AcquisitionDate != root.getString(Tag.SeriesDate)) {
				logger.warn("AcquisitionDate and SeriesDate are different");
			}

			String AcquisitionTime = root.getString(Tag.AcquisitionTime);
			logger.debug("AcquisitionTime : "+AcquisitionTime);
			if (AcquisitionTime != root.getString(Tag.SeriesTime)) {
				logger.warn("AcquisitionTime and SeriesTime are different");
			}

			String AcquisitionDuration = root.getString(Tag.AcquisitionDuration);
			logger.debug("AcquisitionDuration : "+AcquisitionDuration);
			if (AcquisitionDuration!=null) {
				addDataProperty(acquisition, racineURI+"has_duration", AcquisitionDuration);
			}
			
			Sequence CTAcquisitionTypeSequence = root.getSequence(Tag.CTAcquisitionTypeSequence);
			iter = CTAcquisitionTypeSequence.iterator();
			while (iter.hasNext()) {
				Attributes t = iter.next();
				if (t.getString(Tag.AcquisitionType)!=null) {
					String AcquisitionType = t.getString(Tag.AcquisitionType);
					logger.debug("AcquisitionType : "+AcquisitionType);
					switch (AcquisitionType) {
					case "SEQUENCED":
						acquisition.addOntClass(model.getResource(racineDCM+"sequenced_CT_acquisition"));
						break;
					case "SPIRAL":
						acquisition.addOntClass(model.getResource(racineDCM+"spiral_CT_acquisition"));
						break;
					case "CONSTANT_ANGLE":
						acquisition.addOntClass(model.getResource(racineDCM+"constant_angle_CT_acquisition"));
						break;
					case "STATIONARY":
						acquisition.addOntClass(model.getResource(racineDCM+"stationary_CT_acquisition"));
						break;
					case "FREE":
						acquisition.addOntClass(model.getResource(racineDCM+"free_CT_acquisition"));
						break;
					}
				}
			}

			Sequence CTAcquisitionDetailsSequence = root.getSequence(Tag.CTAcquisitionDetailsSequence);
			iter = CTAcquisitionDetailsSequence.iterator();
			while (iter.hasNext()) {
				Attributes t = iter.next();
				if (t.getString(Tag.RevolutionTime)!=null) {
					String RevolutionTime = t.getString(Tag.RevolutionTime);
					logger.debug("RevolutionTime : "+RevolutionTime);
				} else if (t.getString(Tag.TotalCollimationWidth)!=null) {
					String TotalCollimationWidth = t.getString(Tag.TotalCollimationWidth);
					logger.debug("TotalCollimationWidth : "+TotalCollimationWidth);
					if (TotalCollimationWidth!=null) {
						i = createIndiv(generateName("Nominal_Total_Collimation_Width"),model.getResource(racineDCM+"113827"));
						addDataProperty(i, racineObo+"IAO_0000004", TotalCollimationWidth);
						addObjectProperty(i, racineObo+"IAO_0000039", getUnit("mm"));
						addObjectProperty(i, racineObo+"BFO_0000177", acquisition);
						addObjectProperty(i, racineURI+"is_device_setting_of", acquisitionDevice);
						addObjectProperty(acquisition, racineURI+"has_setting", i);
					}
				} else if (t.getString(Tag.GantryDetectorTilt)!=null) {
					String GantryDetectorTilt = t.getString(Tag.GantryDetectorTilt);
					logger.debug("GantryDetectorTilt : "+GantryDetectorTilt);
				} else if (t.getString(Tag.TableFeedPerRotation)!=null) {
					String TableFeedPerRotation = t.getString(Tag.TableFeedPerRotation);
					logger.debug("TableFeedPerRotation : "+TableFeedPerRotation);
				} else if (t.getString(Tag.SpiralPitchFactor)!=null) {
					String SpiralPitchFactor = t.getString(Tag.SpiralPitchFactor);
					logger.debug("SpiralPitchFactor : "+SpiralPitchFactor);
					if (SpiralPitchFactor!=null) {
						i = createIndiv(generateName("Pitch_Factor"),model.getResource(racineDCM+"113828"));
						addDataProperty(i, racineObo+"IAO_0000004", SpiralPitchFactor);
						addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
						addObjectProperty(i, racineURI+"is_device_setting_of", acquisitionDevice);
						addObjectProperty(acquisition, racineURI+"has_setting", i);
					}
				}
			}
			
			Sequence CTXRayDetailsSequence = root.getSequence(Tag.CTXRayDetailsSequence);
			iter = CTXRayDetailsSequence.iterator();
			while (iter.hasNext()) {
				Attributes t = iter.next();
				Individual filter = null;
				Individual filterMaterial;
				if (t.getString(Tag.KVP)!=null) {
					String KVP = t.getString(Tag.KVP);
					logger.debug("KVP : "+KVP);
					i = createIndiv(generateName("KVP"),model.getResource(racineDCM+"113733"));
					addDataProperty(i, racineObo+"IAO_0000004", KVP);
					addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
					addObjectProperty(i, racineURI+"is_device_setting_of", acquisitionDevice);
					addObjectProperty(acquisition, racineURI+"has_setting", i);
				} else if (t.getString(Tag.FilterType)!=null) {
					String FilterType = t.getString(Tag.FilterType);
					logger.debug("FilterType : "+FilterType);
					if (FilterType!=null) {
						if (FilterType.toLowerCase().contains("butterfly")) {
							filter = createIndiv(generateName("butterfly_filter"), model.getResource(racineDCM+"113652"));
						} else if (FilterType.toLowerCase().contains("flat")) {
							filter = createIndiv(generateName("flat_filter"), model.getResource(racineDCM+"113653"));
						} else if (FilterType.toLowerCase().contains("strip")) {
							filter = createIndiv(generateName("strip_filter"), model.getResource(racineDCM+"113650"));
						} else if (FilterType.toLowerCase().contains("wedge")) {
							filter = createIndiv(generateName("wedge_filter"), model.getResource(racineDCM+"113651"));
						} else {
							filter = createIndiv(generateName("x-ray_filter"), model.getResource(racineDCM+"113771"));
						}
					}
				} else if (t.getString(Tag.FilterMaterial)!=null) {
					String FilterMaterial = t.getString(Tag.FilterMaterial);
					logger.debug("FilterMaterial : "+FilterMaterial);
					if (FilterMaterial!=null) {
						if (FilterMaterial.toLowerCase().contains("aluminium")) {
							filterMaterial = createIndiv(model.getResource(racineURI+"aluminum_or_aluminum_compound"));
						} else if (FilterMaterial.toLowerCase().contains("carbon")) {
							filterMaterial = createIndiv(model.getResource(racineURI+"carbon_fiber"));
						} else if (FilterMaterial.toLowerCase().contains("copper")) {
							filterMaterial = createIndiv(model.getResource(racineURI+"copper_or_copper_compound"));
						} else if (FilterMaterial.toLowerCase().contains("europium")) {
							filterMaterial = createIndiv(model.getResource(racineURI+"europium_or_europium_compound"));
						} else if (FilterMaterial.toLowerCase().contains("lead")) {
							filterMaterial = createIndiv(model.getResource(racineURI+"lead_or_lead_compound"));
						} else if (FilterMaterial.toLowerCase().contains("molybdenum")) {
							filterMaterial = createIndiv(model.getResource(racineURI+"molybdenum_or_molybdenum_compound"));
						} else if (FilterMaterial.toLowerCase().contains("niobium")) {
							filterMaterial = createIndiv(model.getResource(racineURI+"niobium_or_niobium_compound"));
						} else if (FilterMaterial.toLowerCase().contains("rhodium")) {
							filterMaterial = createIndiv(model.getResource(racineURI+"rhodium_or_rhodium_compound"));
						} else if (FilterMaterial.toLowerCase().contains("silver")) {
							filterMaterial = createIndiv(model.getResource(racineURI+"silver_or_silver_compound"));
						} else if (FilterMaterial.toLowerCase().contains("tantalum")) {
							filterMaterial = createIndiv(model.getResource(racineURI+"tantalum_or_tantalum_compound"));
						} else if (FilterMaterial.toLowerCase().contains("tin")) {
							filterMaterial = createIndiv(model.getResource(racineURI+"tin_or_tin_compound"));
						} else {
							filterMaterial = createIndiv(generateName(FilterMaterial),model.getResource(racineDCM+"113757"));
						}
						if (filter!=null && filterMaterial!=null ) {
							addObjectProperty(filter,racineObo+"BFO_0000110",filterMaterial);
						}
					}

				} else if (t.getString(Tag.FocalSpots)!=null) {
					String FocalSpots = t.getString(Tag.FocalSpots);
					logger.debug("FocalSpots : "+FocalSpots);
					if (FocalSpots!=null) {
						i = createIndiv(generateName("focal_spot"),model.getResource(racineURI+"focal_spot"));
						addDataProperty(i, racineObo+"IAO_0000004", FocalSpots);
						addObjectProperty(i, racineObo+"IAO_0000039", getUnit("ma/s"));
						addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
						addObjectProperty(i, racineURI+"is_device_setting_of", acquisitionDevice);
						addObjectProperty(acquisition, racineURI+"has_setting", i);
					}
				} 
			}

			Sequence CTExposureSequence = root.getSequence(Tag.CTExposureSequence);
			iter = CTExposureSequence.iterator();
			while (iter.hasNext()) {
				Attributes t = iter.next();
				if (t.getString(Tag.ExposureTimeInms)!=null) {
					String ExposureTimeInms = t.getString(Tag.ExposureTimeInms);
					logger.debug("ExposureTimeInms : "+ExposureTimeInms);
					i = createIndiv(generateName("exposure_time"),model.getResource(racineDCM+"113824"));
					addDataProperty(i, racineObo+"IAO_0000004", ExposureTimeInms);
					addObjectProperty(i, racineObo+"IAO_0000039", getUnit("ms"));
					addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
					addObjectProperty(i, racineURI+"is_device_setting_of", acquisitionDevice);
					addObjectProperty(acquisition, racineURI+"has_setting", i);
				} else if (t.getString(Tag.XRayTubeCurrentInmA)!=null) {
					String XRayTubeCurrentInmA = t.getString(Tag.XRayTubeCurrentInmA);
					logger.debug("XRayTubeCurrentInmA : "+XRayTubeCurrentInmA);
					i = createIndiv(generateName("XRayTubeCurrent"),model.getResource(racineDCM+"113734"));
					addDataProperty(i, racineObo+"IAO_0000004", XRayTubeCurrentInmA);
					addObjectProperty(i, racineObo+"IAO_0000039", getUnit("ma"));
					addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
					addObjectProperty(i, racineURI+"is_device_setting_of", acquisitionDevice);
					addObjectProperty(acquisition, racineURI+"has_setting", i);
				} else if (t.getString(Tag.ExposureInmAs)!=null) {
					String ExposureInmAs = t.getString(Tag.ExposureInmAs);
					logger.debug("ExposureInmAs : "+ExposureInmAs);
					i = createIndiv(generateName("exposure_time"),model.getResource(racineDCM+"113824"));
					addDataProperty(i, racineObo+"IAO_0000004", ExposureInmAs);
					addObjectProperty(i, racineObo+"IAO_0000039", getUnit("ma/s"));
					addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
					addObjectProperty(i, racineURI+"is_device_setting_of", acquisitionDevice);
					addObjectProperty(acquisition, racineURI+"has_setting", i);
				} else if (t.getString(Tag.ExposureModulationType)!=null) {
					String ExposureModulationType = t.getString(Tag.ExposureModulationType);
					logger.debug("ExposureModulationType : "+ExposureModulationType);
					if (ExposureModulationType==null || ExposureModulationType.equalsIgnoreCase("none")) {
						addObjectProperty(createIndiv(model.getResource(racineURI+"no_use_of_X_ray_modulation")), racineObo+"BFO_0000177", acquisition);
					} else {
						i = createIndiv(model.getResource(racineURI+"use_of_x_ray_modulation"));
						addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
						addDataProperty(i,racineURI+"has_name",ExposureModulationType);
					}
				}
			}
			
			String ConvolutionKernel = root.getString(Tag.ConvolutionKernel);
			logger.debug("ConvolutionKernel : "+ConvolutionKernel);
			if (ConvolutionKernel!=null) {
				i = createIndiv(generateName("convolution_kernel"), model.getResource(racineURI+"convolution_kernel"));
				addDataProperty(i, racineURI+"has_name", ConvolutionKernel);
				addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
			}
			
			
			//TODO : DistanceSourceToPatient 

		} else if (ImageType.length>=3) {
			if (ImageType[2].contains("TOMO PROJ") ) { // 4.1 NM
				logger.debug("\n");
				logger.debug("Nuclear Medicine Image Storage");
				logger.debug("\n");
	
				//String SOPClassUID = root.getString(Tag.SOPClassUID);
				//logger.debug("SOPClassUID : "+SOPClassUID);
				Individual dataset = createIndiv(generateName("NM_tomo_dataset"), model.getResource(racineURI+"NM_tomo_dataset"));
				addObjectProperty(dataset, racineURI+"has_format", createIndiv(model.getResource(racineURI+"DICOM_NM_image_storage_SOP_class")));
				
				
				
				SeriesInstanceUID = root.getString(Tag.SeriesInstanceUID);
				logger.debug("SeriesInstanceUID : "+SeriesInstanceUID);
				addDataProperty(dataset, racineURI+"has_DICOM_series_instance_UID", SeriesInstanceUID);
				addObjectProperty(dataset, racineURI+"is_specified_output_of", acquisition);
				
				String FrameOfReferenceUID = root.getString(Tag.FrameOfReferenceUID);
				logger.debug("FrameOfReferenceUID : "+FrameOfReferenceUID);
				if (FrameOfReferenceUID!=null) {
					addDataProperty(dataset, racineURI+"has_DICOM_frame_of_reference_UID", FrameOfReferenceUID);
				}
				
				ImageType = root.getStrings(Tag.ImageType);
				logger.debug("ImageType : "+ImageType);
				if (ImageType[2].contains("TOMO PROJ")) {
					//TODO
				}
				
				String AcquisitionDate = root.getString(Tag.AcquisitionDate);
				logger.debug("AcquisitionDate : "+AcquisitionDate);
				if (AcquisitionDate != root.getString(Tag.SeriesDate)) {
					logger.warn("AcquisitionDate and SeriesDate are different");
				}
	
				String AcquisitionTime = root.getString(Tag.AcquisitionTime);
				logger.debug("AcquisitionTime : "+AcquisitionTime);
				if (AcquisitionTime != root.getString(Tag.SeriesTime)) {
					logger.warn("AcquisitionTime and SeriesTime are different");
				}
				
				Sequence EnergyWindowInformationSequence = root.getSequence(Tag.EnergyWindowInformationSequence);
				if (EnergyWindowInformationSequence!=null) {
					Individual energyWindow = createIndiv(generateName("energy_window"), model.getResource(racineURI+"energy_window"));
					addObjectProperty(energyWindow, racineObo+"BFO_0000177", acquisitionProtocol);
					addObjectProperty(energyWindow, racineURI+"device_setting", acquisitionDevice);
					addObjectProperty(acquisition, racineURI+"has_setting ", energyWindow);

					
					iter = EnergyWindowInformationSequence.iterator();
					while (iter.hasNext()) {
						Attributes t = iter.next();
						if (t.getString(Tag.EnergyWindowName)!=null) {
							String EnergyWindowName = t.getString(Tag.EnergyWindowName);
							logger.debug("EnergyWindowName : "+EnergyWindowName);
							if (EnergyWindowName!=null) {
								addDataProperty(energyWindow, racineURI+"has_name", EnergyWindowName);
							}
						} else if (t.getString(Tag.EnergyWindowLowerLimit)!=null) {
							String EnergyWindowLowerLimit = t.getString(Tag.EnergyWindowLowerLimit);
							logger.debug("EnergyWindowLowerLimit : "+EnergyWindowLowerLimit);
							if (EnergyWindowLowerLimit!=null) {
								addDataProperty(energyWindow, racineURI+"has_lower_limit", EnergyWindowLowerLimit);
								addObjectProperty(energyWindow, racineURI+"has_measurement_unit_label", getUnit("kiloelectronvolt"));
							}
						}  else if (t.getString(Tag.EnergyWindowUpperLimit)!=null) {
							String EnergyWindowUpperLimit = t.getString(Tag.EnergyWindowUpperLimit);
							logger.debug("EnergyWindowUpperLimit : "+EnergyWindowUpperLimit);
							if (EnergyWindowUpperLimit!=null) {
								addDataProperty(energyWindow, racineURI+"has_upper_limit", EnergyWindowUpperLimit);
								addObjectProperty(energyWindow, racineURI+"has_measurement_unit_label", getUnit("kiloelectronvolt"));
							}
						} 
					}
				}
			
				Sequence RadiopharmaceuticalInformationSequence = root.getSequence(Tag.RadiopharmaceuticalInformationSequence);
				iter = RadiopharmaceuticalInformationSequence.iterator();
				while (iter.hasNext()) {
					Attributes t = iter.next();
					if (t.getSequence(Tag.RadionuclideCodeSequence)!=null) {
						Sequence RadionuclideCodeSequence = t.getSequence(Tag.RadionuclideCodeSequence);
						logger.debug("RadionuclideCodeSequence : "+RadionuclideCodeSequence);
						Iterator<Attributes> iterRadioNucl = RadiopharmaceuticalInformationSequence.iterator();
						while (iterRadioNucl.hasNext()) {
							Attributes radioNucl = iter.next();
							if (radioNucl.getString(Tag.CodeMeaning)!=null) {
								String CodeMeaning = t.getString(Tag.CodeMeaning);
								logger.debug("CodeMeaning : "+CodeMeaning);
							} else if (radioNucl.getString(Tag.CodingSchemeDesignator)!=null) {
								String CodingSchemeDesignator = t.getString(Tag.CodingSchemeDesignator);
								logger.debug("CodingSchemeDesignator : "+CodingSchemeDesignator);
							} else if (radioNucl.getString(Tag.CodeValue)!=null) {
								String CodeValue = t.getString(Tag.CodeValue);
								logger.debug("CodeValue : "+CodeValue);
							}
						}
					}
				}
					
				Sequence DetectorInformationSequence = root.getSequence(Tag.DetectorInformationSequence);
				iter = DetectorInformationSequence.iterator();
				while (iter.hasNext()) {
					Attributes t = iter.next();
					if (t.getString(Tag.CollimatorGridName)!=null) {
						String CollimatorGridName = t.getString(Tag.CollimatorGridName);
						logger.debug("CollimatorGridName : "+CollimatorGridName);
					} else if (t.getString(Tag.CollimatorType)!=null) {
						String CollimatorType = t.getString(Tag.CollimatorType);
						logger.debug("CollimatorType : "+CollimatorType);
						if (CollimatorType!=null) {
							Individual collimator;
							if (CollimatorType.contains("PARA")) {
								collimator = createIndiv(generateName("parallel_collimator"), model.getResource(racineURI+"parallel_collimator"));
							} else if (CollimatorType.contains("PINH")) {
								collimator = createIndiv(generateName("pinhole_collimator"), model.getResource(racineURI+"pinhole_collimator"));
							} else if (CollimatorType.contains("FANB")) {
								collimator = createIndiv(generateName("fan_beam_collimator"), model.getResource(racineURI+"fan_beam_collimator"));
							} else if (CollimatorType.contains("CONE")) {
								collimator = createIndiv(generateName("cone_beam_collimator"), model.getResource(racineURI+"cone_beam_collimator"));
							} else if (CollimatorType.contains("SLNT")) {
								collimator = createIndiv(generateName("slant_hole_collimator"), model.getResource(racineURI+"slant_hole_collimator"));
							} else if (CollimatorType.contains("ASTG")) {
								collimator = createIndiv(generateName("astigmatic_collimator"), model.getResource(racineURI+"astigmatic_collimator"));
							} else if (CollimatorType.contains("DIVG")) {
								collimator = createIndiv(generateName("diverging_collimator"), model.getResource(racineURI+"diverging_collimator"));
							} else if (CollimatorType.contains("NONE")) {
								collimator = null;
							} else {
								collimator = createIndiv(generateName("collimator_"+CollimatorType), model.getResource(racineURI+"collimator"));
							}
						}
					}
				}
				
				Sequence RotationInformationSequence = root.getSequence(Tag.RotationInformationSequence);
				iter = RotationInformationSequence.iterator();
				while (iter.hasNext()) {
					Attributes t = iter.next();
					if (t.getString(Tag.StartAngle)!=null) {
						String StartAngle = t.getString(Tag.StartAngle);
						logger.debug("StartAngle : "+StartAngle);
					} else if (t.getString(Tag.RotationDirection)!=null) {
						String RotationDirection = t.getString(Tag.RotationDirection);
						logger.debug("RotationDirection : "+RotationDirection);
					} else if (t.getString(Tag.NumberOfFramesInRotation)!=null) {
						String NumberOfFramesInRotation = t.getString(Tag.NumberOfFramesInRotation);
						logger.debug("NumberOfFramesInRotation : "+NumberOfFramesInRotation);
					} else if (t.getString(Tag.TypeOfDetectorMotion)!=null) {
						String TypeOfDetectorMotion = t.getString(Tag.TypeOfDetectorMotion);
						logger.debug("TypeOfDetectorMotion : "+TypeOfDetectorMotion);
					} else if (t.getString(Tag.ActualFrameDuration)!=null) {
						String ActualFrameDuration = t.getString(Tag.ActualFrameDuration);
						logger.debug("ActualFrameDuration : "+ActualFrameDuration);
					}
					
					
				}

			} else if (ImageType[2].contains("TOMO RECON")) { // 4.2 NM
				logger.debug("Positron Emission Tomography Image Storage");
				Individual NMDataSet = createIndiv(generateName("NM_recon_tomo_dataset"), model.getResource(racineURI+"NM_recon_tomo_dataset"));
				
				logger.debug("SOPClassUID : "+SOPClassUID);
				i = createIndiv(model.getResource(racineURI+"DICOM_NM_image_storage_SOP_class"));
				addObjectProperty(NMDataSet, racineURI+"has_format", i);
				
				logger.debug("SeriesInstanceUID : "+SeriesInstanceUID);
				addDataProperty(NMDataSet, racineURI+"has_DICOM_series_instance_UID", SeriesInstanceUID);
				addObjectProperty(NMDataSet, racineURI+"is_specified_output_of", acquisition);

				String FrameOfReferenceUID = root.getString(Tag.FrameOfReferenceUID);
				logger.debug("FrameOfReferenceUID : "+FrameOfReferenceUID);	
				if (FrameOfReferenceUID!=null) {
					addDataProperty(NMDataSet, racineURI+"has_DICOM_frame_of_reference_UID", FrameOfReferenceUID);
				}
				
				ImageType = root.getStrings(Tag.ImageType);
				logger.debug("ImageType : "+ImageType);		
				
				String AcquisitionDate = root.getString(Tag.AcquisitionDate);
				logger.debug("AcquisitionDate : "+AcquisitionDate);
				if (AcquisitionDate != root.getString(Tag.SeriesDate)) {
					logger.warn("AcquisitionDate and SeriesDate are different");
				}

				String AcquisitionTime = root.getString(Tag.AcquisitionTime);
				logger.debug("AcquisitionTime : "+AcquisitionTime);
				if (AcquisitionTime != root.getString(Tag.SeriesTime)) {
					logger.warn("AcquisitionTime and SeriesTime are different");
				}
				
				Sequence EnergyWindowInformationSequence = root.getSequence(Tag.EnergyWindowInformationSequence);
				if (EnergyWindowInformationSequence!=null) {
					Individual energyWindow = createIndiv(generateName("energy_window"), model.getResource(racineURI+"energy_window"));
					addObjectProperty(energyWindow, racineObo+"BFO_0000177", acquisitionProtocol);
					addObjectProperty(energyWindow, racineURI+"device_setting", acquisitionDevice);
					addObjectProperty(acquisition, racineURI+"has_setting ", energyWindow);
					iter = EnergyWindowInformationSequence.iterator();
					while (iter.hasNext()) {
						Attributes t = iter.next();
						if (t.getString(Tag.EnergyWindowName)!=null) {
							String EnergyWindowName = t.getString(Tag.EnergyWindowName);
							logger.debug("EnergyWindowName : "+EnergyWindowName);
							if (EnergyWindowName!=null) {
								addDataProperty(energyWindow, racineURI+"has_name", EnergyWindowName);
							}
						} else if (t.getString(Tag.EnergyWindowLowerLimit)!=null) {
							String EnergyWindowLowerLimit = t.getString(Tag.EnergyWindowLowerLimit);
							logger.debug("EnergyWindowLowerLimit : "+EnergyWindowLowerLimit);
							if (EnergyWindowLowerLimit!=null) {
								addDataProperty(energyWindow, racineURI+"has_lower_limit", EnergyWindowLowerLimit);
								addObjectProperty(energyWindow, racineURI+"has_measurement_unit_label", getUnit("kiloelectronvolt"));
							}
						}  else if (t.getString(Tag.EnergyWindowUpperLimit)!=null) {
							String EnergyWindowUpperLimit = t.getString(Tag.EnergyWindowUpperLimit);
							logger.debug("EnergyWindowUpperLimit : "+EnergyWindowUpperLimit);
							if (EnergyWindowUpperLimit!=null) {
								addDataProperty(energyWindow, racineURI+"has_upper_limit", EnergyWindowUpperLimit);
								addObjectProperty(energyWindow, racineURI+"has_measurement_unit_label", getUnit("kiloelectronvolt"));
							}
						} 
					}
				}
				
				Sequence RadiopharmaceuticalInformationSequence = root.getSequence(Tag.RadiopharmaceuticalInformationSequence);
				iter = RadiopharmaceuticalInformationSequence.iterator();
				while (iter.hasNext()) {
					Attributes t = iter.next();
					if (t.getSequence(Tag.RadionuclideCodeSequence)!=null) {
						Sequence RadionuclideCodeSequence = t.getSequence(Tag.RadionuclideCodeSequence);
						logger.debug("RadionuclideCodeSequence : "+RadionuclideCodeSequence);
						Iterator<Attributes> iterRadioNucl = RadiopharmaceuticalInformationSequence.iterator();
						while (iterRadioNucl.hasNext()) {
							Attributes radioNucl = iter.next();
							if (radioNucl.getString(Tag.CodeMeaning)!=null) {
								String CodeMeaning = t.getString(Tag.CodeMeaning);
								logger.debug("CodeMeaning : "+CodeMeaning);
							} else if (radioNucl.getString(Tag.CodingSchemeDesignator)!=null) {
								String CodingSchemeDesignator = t.getString(Tag.CodingSchemeDesignator);
								logger.debug("CodingSchemeDesignator : "+CodingSchemeDesignator);
							} else if (radioNucl.getString(Tag.CodeValue)!=null) {
								String CodeValue = t.getString(Tag.CodeValue);
								logger.debug("CodeValue : "+CodeValue);
							}
						}
					}
				}
				
				Sequence DetectorInformationSequence = root.getSequence(Tag.DetectorInformationSequence);
				iter = DetectorInformationSequence.iterator();
				while (iter.hasNext()) {
					Attributes t = iter.next();
					if (t.getString(Tag.CollimatorGridName)!=null) {
						String CollimatorGridName = t.getString(Tag.CollimatorGridName);
						logger.debug("CollimatorGridName : "+CollimatorGridName);
					} else if (t.getString(Tag.CollimatorType)!=null) {
						String CollimatorType = t.getString(Tag.CollimatorType);
						logger.debug("CollimatorType : "+CollimatorType);
						if (CollimatorType!=null) {
							Individual collimator;
							if (CollimatorType.contains("PARA")) {
								collimator = createIndiv(generateName("parallel_collimator"), model.getResource(racineURI+"parallel_collimator"));
							} else if (CollimatorType.contains("PINH")) {
								collimator = createIndiv(generateName("pinhole_collimator"), model.getResource(racineURI+"pinhole_collimator"));
							} else if (CollimatorType.contains("FANB")) {
								collimator = createIndiv(generateName("fan_beam_collimator"), model.getResource(racineURI+"fan_beam_collimator"));
							} else if (CollimatorType.contains("CONE")) {
								collimator = createIndiv(generateName("cone_beam_collimator"), model.getResource(racineURI+"cone_beam_collimator"));
							} else if (CollimatorType.contains("SLNT")) {
								collimator = createIndiv(generateName("slant_hole_collimator"), model.getResource(racineURI+"slant_hole_collimator"));
							} else if (CollimatorType.contains("ASTG")) {
								collimator = createIndiv(generateName("astigmatic_collimator"), model.getResource(racineURI+"astigmatic_collimator"));
							} else if (CollimatorType.contains("DIVG")) {
								collimator = createIndiv(generateName("diverging_collimator"), model.getResource(racineURI+"diverging_collimator"));
							} else if (CollimatorType.contains("NONE")) {
								collimator = null;
							} else {
								collimator = createIndiv(generateName("collimator_"+CollimatorType), model.getResource(racineURI+"collimator"));
							}
						}
					}
				}
				
			} else if (ImageType[2].contains("STATIC") || ImageType[2].contains("WHOLE BODY")) { // 4.3 NM
				Individual NMDataSet = createIndiv(generateName("NM_recon_tomo_dataset"), model.getResource(racineURI+"NM_static_dataset"));

				logger.debug("SOPClassUID : "+SOPClassUID);
				i = createIndiv(model.getResource(racineURI+"DICOM_NM_image_storage_SOP_class"));
				addObjectProperty(NMDataSet, racineURI+"has_format", i);
				
				logger.debug("SeriesInstanceUID : "+SeriesInstanceUID);
				addDataProperty(NMDataSet, racineURI+"has_DICOM_series_instance_UID", SeriesInstanceUID);
				i = createIndiv(generateName("SPECT_data_acquisition"), model.getResource(racineURI+"SPECT_data_acquisition"));
				addObjectProperty(NMDataSet, racineURI+"is_specified_output_of", i);

				String FrameOfReferenceUID = root.getString(Tag.FrameOfReferenceUID);
				logger.debug("FrameOfReferenceUID : "+FrameOfReferenceUID);
				addDataProperty(NMDataSet, racineURI+"has_DICOM_frame_of_reference_UID", FrameOfReferenceUID);
				
				logger.debug("ImageType : "+ImageType);
				
				String AcquisitionDate = root.getString(Tag.AcquisitionDate);
				logger.debug("AcquisitionDate : "+AcquisitionDate);
				if (AcquisitionDate != root.getString(Tag.SeriesDate)) {
					logger.warn("AcquisitionDate and SeriesDate are different");
				}
	
				String AcquisitionTime = root.getString(Tag.AcquisitionTime);
				logger.debug("AcquisitionTime : "+AcquisitionTime);
				if (AcquisitionTime != root.getString(Tag.SeriesTime)) {
					logger.warn("AcquisitionTime and SeriesTime are different");
				}
				
				Sequence EnergyWindowInformationSequence = root.getSequence(Tag.EnergyWindowInformationSequence);
				if (EnergyWindowInformationSequence!=null) {
					Individual energyWindow = createIndiv(generateName("energy_window"), model.getResource(racineURI+"energy_window"));
					addObjectProperty(energyWindow, racineObo+"BFO_0000177", acquisitionProtocol);
					addObjectProperty(energyWindow, racineURI+"device_setting", acquisitionDevice);
					addObjectProperty(acquisition, racineURI+"has_setting ", energyWindow);
					iter = EnergyWindowInformationSequence.iterator();
					while (iter.hasNext()) {
						Attributes t = iter.next();
						if (t.getString(Tag.EnergyWindowName)!=null) {
							String EnergyWindowName = t.getString(Tag.EnergyWindowName);
							logger.debug("EnergyWindowName : "+EnergyWindowName);
							if (EnergyWindowName!=null) {
								addDataProperty(energyWindow, racineURI+"has_name", EnergyWindowName);
							}
						} else if (t.getString(Tag.EnergyWindowLowerLimit)!=null) {
							String EnergyWindowLowerLimit = t.getString(Tag.EnergyWindowLowerLimit);
							logger.debug("EnergyWindowLowerLimit : "+EnergyWindowLowerLimit);
							if (EnergyWindowLowerLimit!=null) {
								addDataProperty(energyWindow, racineURI+"has_lower_limit", EnergyWindowLowerLimit);
								addObjectProperty(energyWindow, racineURI+"has_measurement_unit_label", getUnit("kiloelectronvolt"));
							}
						} else if (t.getString(Tag.EnergyWindowUpperLimit)!=null) {
							String EnergyWindowUpperLimit = t.getString(Tag.EnergyWindowUpperLimit);
							logger.debug("EnergyWindowUpperLimit : "+EnergyWindowUpperLimit);
							if (EnergyWindowUpperLimit!=null) {
								addDataProperty(energyWindow, racineURI+"has_upper_limit", EnergyWindowUpperLimit);
								addObjectProperty(energyWindow, racineURI+"has_measurement_unit_label", getUnit("kiloelectronvolt"));
							}
						} 
					}
				}
				
				Sequence RadiopharmaceuticalInformationSequence = root.getSequence(Tag.RadiopharmaceuticalInformationSequence);
				iter = RadiopharmaceuticalInformationSequence.iterator();
				while (iter.hasNext()) {
					Attributes t = iter.next();
					if (t.getSequence(Tag.RadionuclideCodeSequence)!=null) {
						Sequence RadionuclideCodeSequence = t.getSequence(Tag.RadionuclideCodeSequence);
						logger.debug("RadionuclideCodeSequence : "+RadionuclideCodeSequence);
						Iterator<Attributes> iterRadioNucl = RadiopharmaceuticalInformationSequence.iterator();
						while (iterRadioNucl.hasNext()) {
							Attributes radioNucl = iter.next();
							if (radioNucl.getString(Tag.CodeMeaning)!=null) {
								String CodeMeaning = t.getString(Tag.CodeMeaning);
								logger.debug("CodeMeaning : "+CodeMeaning);
							} else if (radioNucl.getString(Tag.CodingSchemeDesignator)!=null) {
								String CodingSchemeDesignator = t.getString(Tag.CodingSchemeDesignator);
								logger.debug("CodingSchemeDesignator : "+CodingSchemeDesignator);
							} else if (radioNucl.getString(Tag.CodeValue)!=null) {
								String CodeValue = t.getString(Tag.CodeValue);
								logger.debug("CodeValue : "+CodeValue);
							}
						}
					}
				}
				
				

				Sequence DetectorInformationSequence = root.getSequence(Tag.DetectorInformationSequence);
				iter = DetectorInformationSequence.iterator();
				while (iter.hasNext()) {
					Attributes t = iter.next();
					if (t.getString(Tag.CollimatorGridName)!=null) {
						String CollimatorGridName = t.getString(Tag.CollimatorGridName);
						logger.debug("CollimatorGridName : "+CollimatorGridName);
					} else if (t.getString(Tag.CollimatorType)!=null) {
						String CollimatorType = t.getString(Tag.CollimatorType);
						logger.debug("CollimatorType : "+CollimatorType);
						if (CollimatorType!=null) {
							Individual collimator;
							if (CollimatorType.contains("PARA")) {
								collimator = createIndiv(generateName("parallel_collimator"), model.getResource(racineURI+"parallel_collimator"));
							} else if (CollimatorType.contains("PINH")) {
								collimator = createIndiv(generateName("pinhole_collimator"), model.getResource(racineURI+"pinhole_collimator"));
							} else if (CollimatorType.contains("FANB")) {
								collimator = createIndiv(generateName("fan_beam_collimator"), model.getResource(racineURI+"fan_beam_collimator"));
							} else if (CollimatorType.contains("CONE")) {
								collimator = createIndiv(generateName("cone_beam_collimator"), model.getResource(racineURI+"cone_beam_collimator"));
							} else if (CollimatorType.contains("SLNT")) {
								collimator = createIndiv(generateName("slant_hole_collimator"), model.getResource(racineURI+"slant_hole_collimator"));
							} else if (CollimatorType.contains("ASTG")) {
								collimator = createIndiv(generateName("astigmatic_collimator"), model.getResource(racineURI+"astigmatic_collimator"));
							} else if (CollimatorType.contains("DIVG")) {
								collimator = createIndiv(generateName("diverging_collimator"), model.getResource(racineURI+"diverging_collimator"));
							} else if (CollimatorType.contains("NONE")) {
								collimator = null;
							} else {
								collimator = createIndiv(generateName("collimator_"+CollimatorType), model.getResource(racineURI+"collimator"));
							}
						}
					} else if (t.getString(Tag.StartAngle)!=null) {
						String StartAngle = t.getString(Tag.StartAngle);
						logger.debug("StartAngle : "+StartAngle);
					} 
				}
				
			}
			
		} else if (SOPClassUID.contains("1.2.840.10008.5.1.4.1.1.128")) { // 5.1
			logger.debug("Positron Emission Tomography Image Storage");
			Individual PETDataSet = createIndiv(generateName("PET_recon_tomo_dataset "), model.getResource(racineURI+"PET_recon_tomo_dataset "));
			
			logger.debug("SOPClassUID : "+SOPClassUID);
			i = createIndiv(model.getResource(racineURI+"DICOM_PET_image_storage_SOP_class"));
			addObjectProperty(PETDataSet, racineURI+"has_format", i);
			
			logger.debug("SeriesInstanceUID : "+SeriesInstanceUID);
			addDataProperty(PETDataSet, racineURI+"has_DICOM_series_instance_UID", SeriesInstanceUID);
			Individual PETDataAcquisition = createIndiv(generateName("SPECT_data_acquisition"), model.getResource(racineURI+"PET_data_acquisition"));
			addObjectProperty(PETDataSet, racineURI+"is_specified_output_of", PETDataAcquisition);
			
			String FrameOfReferenceUID = root.getString(Tag.FrameOfReferenceUID);
			logger.debug("FrameOfReferenceUID : "+FrameOfReferenceUID);
			if (FrameOfReferenceUID!=null) {
				addDataProperty(PETDataSet, racineURI+"has_DICOM_frame_of_reference_UID", FrameOfReferenceUID);
			}
			
			logger.debug("ImageType : "+ImageType);

			String AcquisitionDate = root.getString(Tag.AcquisitionDate);
			logger.debug("AcquisitionDate : "+AcquisitionDate);
			if (AcquisitionDate != root.getString(Tag.SeriesDate)) {
				logger.warn("AcquisitionDate and SeriesDate are different");
			}

			String AcquisitionTime = root.getString(Tag.AcquisitionTime);
			logger.debug("AcquisitionTime : "+AcquisitionTime);
			if (AcquisitionTime != root.getString(Tag.SeriesTime)) {
				logger.warn("AcquisitionTime and SeriesTime are different");
			}
			
			Sequence RadiopharmaceuticalInformationSequence = root.getSequence(Tag.RadiopharmaceuticalInformationSequence);
			iter = RadiopharmaceuticalInformationSequence.iterator();
			while (iter.hasNext()) {
				Attributes t = iter.next();
				if (t.getSequence(Tag.RadionuclideCodeSequence)!=null) {
					Sequence RadionuclideCodeSequence = t.getSequence(Tag.RadionuclideCodeSequence);
					logger.debug("RadionuclideCodeSequence : "+RadionuclideCodeSequence);
					Iterator<Attributes> iterRadioNucl = RadiopharmaceuticalInformationSequence.iterator();
					while (iterRadioNucl.hasNext()) {
						Attributes radioNucl = iterRadioNucl.next();
						if (radioNucl.getString(Tag.CodeMeaning)!=null) {
							String CodeMeaning = t.getString(Tag.CodeMeaning);
							logger.debug("CodeMeaning : "+CodeMeaning);
						} else if (radioNucl.getString(Tag.CodingSchemeDesignator)!=null) {
							String CodingSchemeDesignator = t.getString(Tag.CodingSchemeDesignator);
							logger.debug("CodingSchemeDesignator : "+CodingSchemeDesignator);
						} else if (radioNucl.getString(Tag.CodeValue)!=null) {
							String CodeValue = t.getString(Tag.CodeValue);
							logger.debug("CodeValue : "+CodeValue);
						}
					}
				} else if (t.getSequence(Tag.RadionuclideTotalDose)!=null) {
					String RadionuclideTotalDose = t.getString(Tag.RadionuclideTotalDose);
					logger.debug("RadionuclideTotalDose : "+RadionuclideTotalDose);
				}
			}
			
			Sequence EnergyWindowInformationSequence = root.getSequence(Tag.EnergyWindowInformationSequence);
			if (EnergyWindowInformationSequence!=null) {
				Individual energyWindow = createIndiv(generateName("energy_window"), model.getResource(racineURI+"energy_window"));
				addObjectProperty(energyWindow, racineObo+"BFO_0000177", acquisitionProtocol);
				addObjectProperty(energyWindow, racineURI+"device_setting", acquisitionDevice);
				addObjectProperty(acquisition, racineURI+"has_setting ", energyWindow);
				iter = EnergyWindowInformationSequence.iterator();
				while (iter.hasNext()) {
					Attributes t = iter.next();
					if (t.getString(Tag.EnergyWindowLowerLimit)!=null) {
						String EnergyWindowLowerLimit = t.getString(Tag.EnergyWindowLowerLimit);
						logger.debug("EnergyWindowLowerLimit : "+EnergyWindowLowerLimit);
						if (EnergyWindowLowerLimit!=null) {
							addDataProperty(energyWindow, racineURI+"has_lower_limit", EnergyWindowLowerLimit);
							addObjectProperty(energyWindow, racineURI+"has_measurement_unit_label", getUnit("kiloelectronvolt"));
						}
					} else if (t.getString(Tag.EnergyWindowUpperLimit)!=null) {
						String EnergyWindowUpperLimit = t.getString(Tag.EnergyWindowUpperLimit);
						logger.debug("EnergyWindowUpperLimit : "+EnergyWindowUpperLimit);
						if (EnergyWindowUpperLimit!=null) {
							addDataProperty(energyWindow, racineURI+"has_upper_limit", EnergyWindowUpperLimit);
							addObjectProperty(energyWindow, racineURI+"has_measurement_unit_label", getUnit("kiloelectronvolt"));
						}
					} 
				}
			}
			
			String ReconstructionMethod = root.getString(Tag.ReconstructionMethod);
			logger.debug("ReconstructionMethod : "+ReconstructionMethod);
			
			String CollimatorType = root.getString(Tag.CollimatorType);
			logger.debug("CollimatorType : "+CollimatorType);
			
			String TypeOfDetectorMotion = root.getString(Tag.TypeOfDetectorMotion);
			logger.debug("TypeOfDetectorMotion : "+TypeOfDetectorMotion);
			if (TypeOfDetectorMotion!=null) {
				if (TypeOfDetectorMotion.contains("STEP AND SHOOT")) {
					PETDataAcquisition.addOntClass(model.getResource(racineURI+"step_and_shoot_PET_data_acquisition"));
				} else if (TypeOfDetectorMotion.contains("CONTINUOUS")) {
					PETDataAcquisition.addOntClass(model.getResource(racineURI+"continuous_PET_data_acquisition"));
				} else if (TypeOfDetectorMotion.contains("WOBBLE")) {
					PETDataAcquisition.addOntClass(model.getResource(racineURI+"wobble_PET_data_acquisition"));
				} else if (TypeOfDetectorMotion.contains("CLAMSHELL")) {
					PETDataAcquisition.addOntClass(model.getResource(racineURI+"clamshell_PET_data_acquisition"));
				} 
			}

		} else if (SOPClassUID.contains("1.2.840.10008.5.1.4.1.1.130")) { // 5.2
			logger.debug("Enhanced PET Image Storage");
			Individual PETDataSet = createIndiv(generateName("PET_recon_tomo_dataset "), model.getResource(racineURI+"PET_recon_tomo_dataset "));			
			
			logger.debug("SOPClassUID : "+SOPClassUID);
			i = createIndiv(generateName("DICOM_enhanced_PET_image_storage_SOP_class"), model.getResource(racineURI+"DICOM_enhanced_PET_image_storage_SOP_class"));
			addObjectProperty(PETDataSet, racineURI+"has_format", i);
			
			logger.debug("SeriesInstanceUID : "+SeriesInstanceUID);
			addDataProperty(PETDataSet, racineURI+"has_DICOM_series_instance_UID", SeriesInstanceUID);
			i = createIndiv(generateName("SPECT_data_acquisition"), model.getResource(racineURI+"PET_data_acquisition"));
			addObjectProperty(PETDataSet, racineURI+"is_specified_output_of", i);

			String FrameOfReferenceUID = root.getString(Tag.FrameOfReferenceUID);
			logger.debug("FrameOfReferenceUID : "+FrameOfReferenceUID);
			if (FrameOfReferenceUID!=null) {
				addDataProperty(PETDataSet, racineURI+"has_DICOM_frame_of_reference_UID", FrameOfReferenceUID);
			}
			
			logger.debug("ImageType : "+ImageType);
			
			String AcquisitionDate = root.getString(Tag.AcquisitionDate);
			logger.debug("AcquisitionDate : "+AcquisitionDate);
			if (AcquisitionDate != root.getString(Tag.SeriesDate)) {
				logger.warn("AcquisitionDate and SeriesDate are different");
			}

			String AcquisitionTime = root.getString(Tag.AcquisitionTime);
			logger.debug("AcquisitionTime : "+AcquisitionTime);
			if (AcquisitionTime != root.getString(Tag.SeriesTime)) {
				logger.warn("AcquisitionTime and SeriesTime are different");
			}

			Sequence RadiopharmaceuticalInformationSequence = root.getSequence(Tag.RadiopharmaceuticalInformationSequence);
			iter = RadiopharmaceuticalInformationSequence.iterator();
			while (iter.hasNext()) {
				Attributes t = iter.next();
				if (t.getSequence(Tag.RadionuclideCodeSequence)!=null) {
					Sequence RadionuclideCodeSequence = t.getSequence(Tag.RadionuclideCodeSequence);
					logger.debug("RadionuclideCodeSequence : "+RadionuclideCodeSequence);
					Iterator<Attributes> iterRadioNucl = RadiopharmaceuticalInformationSequence.iterator();
					while (iterRadioNucl.hasNext()) {
						Attributes radioNucl = iter.next();
						if (radioNucl.getString(Tag.CodeMeaning)!=null) {
							String CodeMeaning = t.getString(Tag.CodeMeaning);
							logger.debug("CodeMeaning : "+CodeMeaning);
						} else if (radioNucl.getString(Tag.CodingSchemeDesignator)!=null) {
							String CodingSchemeDesignator = t.getString(Tag.CodingSchemeDesignator);
							logger.debug("CodingSchemeDesignator : "+CodingSchemeDesignator);
						} else if (radioNucl.getString(Tag.CodeValue)!=null) {
							String CodeValue = t.getString(Tag.CodeValue);
							logger.debug("CodeValue : "+CodeValue);
						}
					}
				} else if (t.getSequence(Tag.RadionuclideTotalDose)!=null) {
					String RadionuclideTotalDose = t.getString(Tag.RadionuclideTotalDose);
					logger.debug("RadionuclideTotalDose : "+RadionuclideTotalDose);
				}
			}
			
			Sequence EnergyWindowInformationSequence = root.getSequence(Tag.EnergyWindowInformationSequence);
			iter = EnergyWindowInformationSequence.iterator();
			while (iter.hasNext()) {
				Attributes t = iter.next();
				if (t.getString(Tag.EnergyWindowName)!=null) {
					String EnergyWindowName = t.getString(Tag.EnergyWindowName);
					logger.debug("EnergyWindowName : "+EnergyWindowName);
				} else if (t.getString(Tag.EnergyWindowLowerLimit)!=null) {
					String EnergyWindowLowerLimit = t.getString(Tag.EnergyWindowLowerLimit);
					logger.debug("EnergyWindowLowerLimit : "+EnergyWindowLowerLimit);
				}  else if (t.getString(Tag.EnergyWindowUpperLimit)!=null) {
					String EnergyWindowUpperLimit = t.getString(Tag.EnergyWindowUpperLimit);
					logger.debug("EnergyWindowUpperLimit : "+EnergyWindowUpperLimit);
				} 
			}
			
			String CollimatorType = root.getString(Tag.CollimatorType);
			logger.debug("CollimatorType : "+CollimatorType);
			if (CollimatorType!=null) {
				//if (CollimatorType.contains("RING"))
			}
			
			
			Sequence PETReconstructionSequence = root.getSequence(Tag.PETReconstructionSequence);
			iter = PETReconstructionSequence.iterator();
			Individual reconstructionAlgorithm = null;
			while (iter.hasNext()) {
				Attributes t = iter.next();
				if (t.getString(Tag.ReconstructionAlgorithm)!=null) {
					String ReconstructionAlgorithm = t.getString(Tag.ReconstructionAlgorithm);
					logger.debug("ReconstructionAlgorithm : "+ReconstructionAlgorithm);
					if (ReconstructionAlgorithm.contains("FILTER_BACK_PROJ")) {
						reconstructionAlgorithm = createIndiv(model.getResource(racineDCM+"113962"));
					} else if (ReconstructionAlgorithm.contains("RAMLA")) {
						reconstructionAlgorithm = createIndiv(model.getResource(racineURI+"RAMLA"));
					} else if (ReconstructionAlgorithm.contains("MLEM")) {
						reconstructionAlgorithm = createIndiv(model.getResource(racineDCM+"MLEM"));
					}  
					
				} else if (t.getString(Tag.IterativeReconstructionMethod)!=null) {
					String IterativeReconstructionMethod = t.getString(Tag.IterativeReconstructionMethod);
					logger.debug("IterativeReconstructionMethod : "+IterativeReconstructionMethod);
					reconstructionAlgorithm.addOntClass(model.getResource(racineDCM+"113963"));
				}
			}
			           
			String TypeOfDetectorMotion = root.getString(Tag.TypeOfDetectorMotion);
			logger.debug("TypeOfDetectorMotion : "+TypeOfDetectorMotion);
			if (TypeOfDetectorMotion!=null) {
				Individual PETDataAcquisition = createIndiv(generateName("SPECT_data_acquisition"), model.getResource(racineURI+"PET_data_acquisition"));
				addObjectProperty(PETDataSet, racineURI+"is_specified_output_of", PETDataAcquisition);
				if (TypeOfDetectorMotion.contains("STATIONARY")) {
					PETDataAcquisition.addOntClass(model.getResource(racineURI+"stationary_PET_data_acquisition"));
				} else if (TypeOfDetectorMotion.contains("STEP AND SHOOT")) {
					PETDataAcquisition.addOntClass(model.getResource(racineURI+"step_and_shoot_PET_data_acquisition"));
				} else if (TypeOfDetectorMotion.contains("CONTINUOUS")) {
					PETDataAcquisition.addOntClass(model.getResource(racineURI+"continuous_PET_data_acquisition"));
				} else if (TypeOfDetectorMotion.contains("WOBBLE")) {
					PETDataAcquisition.addOntClass(model.getResource(racineURI+"wobble_PET_data_acquisition"));
				} else if (TypeOfDetectorMotion.contains("CLAMSHELL")) {
					PETDataAcquisition.addOntClass(model.getResource(racineURI+"clamshell_PET_data_acquisition"));
				}
			}
			
			
		}
	} // Fin MetaData

}