package repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.jena.ontology.Individual;
import org.dcm4che3.data.Attributes;

import com.pixelmed.dicom.ContentItem;

import javaXSDclass.DicomFileSetDescriptor;
import javaXSDclass.NonDicomFileSetDescriptor;

public class TranslateDicomSR extends OntologyPopulator {

	static String value; static String name; static String studyInstUID;
	static Individual radioPharSR; static Individual study; static Individual accDose;
	static Individual irradEvent; static  Individual scanningLength; static Individual CTDI;
	static Individual DLP; static Individual CTDIwPhantomType; static Individual CTradSR;
	static String startValue; static String endValue; static String unit;
	static DicomFileSetDescriptor DicomFileSetDescriptorContent;
	static NonDicomFileSetDescriptor nonDicomFileSetDescriptorContent;
	static Individual patient; static String patientId = null;
	static Iterator<Attributes> iter;


	public static void readingSR(ContentItem root, String patientID ) throws IOException { // recursive function for read SR tree
		if (model==null) {model = Application.getModel();}
		if (patientId==null) {patientId=patientID;}
		ContentItem child;

		for (int i=0; i<root.getChildCount(); i++) {  // Iterate on each child elements
			child = (ContentItem) root.getChildAt(i);
			TranslateSR(child);						  // Translate the element
			if (!child.isLeaf()) {readingSR(child, patientID);}  // Iter on the child element(s)
		}
	}

	public static void TranslateSR(ContentItem e) throws IOException {
		String name = e.getConceptNameCodeMeaning().trim().replaceAll(" ", "_").replaceAll("\n", ""); 
		String value = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
		logger.debug(name+" : "+value);

		switch (name) { // Cases are ordered as SR references description table for make them more human readable

		// Table 10011 CT Radiation Dose (part 16, p423)
		case "Procedure_reported":
			switch (value) {
			case "Computed_Tomography_X-Ray":
				CTradSR = createIndiv(generateName(value),model.getResource("http://medicis.univ-rennes1.fr/ontologies/ontospm/OntoMEDIRAD.owl#CT_Radiation_dose_SR"));
				addDataProperty(CTradSR, racineURI+"has_IRDBB_WADO_handle", handle);
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
				imagingStudy = memory.getImagingStudy(ImportController.studyInstanceUID);
				System.out.println(imagingStudy);
				if (imagingStudy==null) {
					logger.warn("Unknown imaging study in SR");
					imagingStudy = createIndiv(generateName("imaging_study"), model.getResource("http://medicis.univ-rennes1.fr/ontologies/ontospm/OntoMEDIRAD.owl#imaging_study"));
				}
				addObjectProperty(CTradSR, racineURI+"has_scope_of_accumulation", imagingStudy);
				addObjectProperty(CTradSR, racineURI+"is_about", imagingStudy);  
				addDataProperty(study, racineURI+"has_dicom_UID", ImportController.studyInstanceUID);
				if (memory==null) {memory=Application.memory;}
				patient = memory.getHuman(patientId);														// create human
				addDataProperty(patient, racineURI+"has_name", patientId);
				patientRole = createIndiv(generateName("Patient"), model.getResource(racineURI+"patient")); // create patient role
				addObjectProperty(patient, racineObo+"BFO_0000087", patientRole);							// link both of them
				addObjectProperty(patientRole, "http://purl.obolibrary.org/obo/BFO_0000054", imagingStudy);
				break;
			default:
				logger.warn("Field "+name+" with Unknown Value "+value);
			} break;	
		case "CT_Accumulated_Dose_Data":
			ContentItem test = verifChild(e, "CT_Dose_Length_Product_Total");
			if (test!=null) {
				accDose  = createIndivWithUnit(test.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", ""), "CT_Dose_Length_Product_Total", "http://dicom.nema.org/resources/ontology/DCM/113813", CTradSR , racineURI+"has_dose");
				addObjectProperty(CTradSR, racineURI+"has_dose", accDose);
			} else {
				accDose = createIndiv(generateName("CT_Accumulated_Dose_Data"), model.getResource("http://medicis.univ-rennes1.fr/ontologies/ontospm/OntoMEDIRAD.owl#accumulated_dose"));
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
				irradEvent = createIndiv(generateName(nameType),model.getResource("http://dicom.nema.org/resources/ontology/DCM/113805"));
				break;
			case "Cone_Beam_Acquisition":
				irradEvent = createIndiv(generateName(nameType),model.getResource("http://medicis.univ-rennes1.fr/ontologies/ontospm/OntoMEDIRAD.owl#cone_beam_acquisition"));
				break;
			case "Free_Acquisition":
				irradEvent = createIndiv(generateName(nameType),model.getResource("http://dicom.nema.org/resources/ontology/DCM/113807"));
				break;
			case "Sequenced_Acquisition":
				irradEvent = createIndiv(generateName(nameType),model.getResource("http://dicom.nema.org/resources/ontology/DCM/113804"));
				break;
			case "Stationary_Acquisition":
				irradEvent = createIndiv(generateName(nameType),model.getResource("http://dicom.nema.org/resources/ontology/DCM/113806"));
				break;
			default:
				logger.warn("Field "+name+" with Unknown Value "+value);
				irradEvent = createIndiv(generateName(nameType),model.getResource("http://medicis.univ-rennes1.fr/ontologies/ontospm/OntoMEDIRAD.owl#CT_acquisition"));
			}
			irradEvent.addOntClass(model.getResource(racineURI+"CT_irradiation_event"));
			addObjectProperty(irradEvent, "http://purl.obolibrary.org/obo/BFO_0000132", imagingStudy); //part of occurrent
			//addObjectProperty(irradEvent, "http://purl.obolibrary.org/obo/BFO_0000132", study); //part of occurrent
			addObjectProperty(CTradSR, racineURI+"is_about_irradiation_event", irradEvent);	
			addDataProperty(irradEvent, racineURI+"has_beginning", startValue.toString()); 
			addDataProperty(irradEvent, racineURI+"has_end",       endValue.toString()); 
			addObjectProperty(patientRole, "http://purl.obolibrary.org/obo/BFO_0000054", irradEvent );

			break;
		case "Source_of_Dose_Information":
			switch (value) {
			case "Automated_Data_Collection":
                    i = createIndiv(generateName(value),model.getResource("http://dicom.nema.org/resources/ontology/DCM/113856"));
				addObjectProperty(CTradSR, racineURI+"is_specified_output_of", i);
				break;
			case "Computed_From_Image_Attributes":
				i = createIndiv(generateName(value),model.getResource("http://dicom.nema.org/resources/ontology/DCM/113867"));
				addObjectProperty(CTradSR, racineURI+"is_specified_output_of", i);
				break;
			case "Copied_From_Image_Attributes":
				i = createIndiv(generateName(value),model.getResource("http://dicom.nema.org/resources/ontology/DCM/113866"));
				addObjectProperty(CTradSR, racineURI+"is_specified_output_of", i);
				break;
			case "Derived_From Human-readable_Reports":
				i = createIndiv(generateName(value),model.getResource("http://dicom.nema.org/resources/ontology/DCM/11386"));
				addObjectProperty(CTradSR, racineURI+"is_specified_output_of", i);
				break;
			case "Manual_Entry":
				i = createIndiv(generateName(value),model.getResource("http://dicom.nema.org/resources/ontology/DCM/113857"));
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
                    i = createIndiv(generateName(value),model.getResource("http://dicom.nema.org/resources/ontology/DCM/113800"));
				addObjectProperty(irradEvent, "http://medicis.univ-rennes1.fr/ontologies/ontospm/OntoMEDIRAD.owl#has_caracteristic", i);
				break;
			case "CTDIfreeair_to_E_conversion_via_MC_computation": 
				i = createIndiv(generateName(value),model.getResource("http://dicom.nema.org/resources/ontology/DCM/113801"));
				addObjectProperty(irradEvent, "http://medicis.univ-rennes1.fr/ontologies/ontospm/OntoMEDIRAD.owl#has_caracteristic", i);
				break;
			case "DLP_to_E_conversion_via_measurement":		     
				i = createIndiv(generateName(value),model.getResource("DLP_to_E_conversion_via_measurement"));
				addObjectProperty(irradEvent, "http://medicis.univ-rennes1.fr/ontologies/ontospm/OntoMEDIRAD.owl#has_caracteristic", i);
				break;
			case "CTDIfreeair_to_E_conversion_via_measurement":    
				i = createIndiv(generateName(value),model.getResource("http://dicom.nema.org/resources/ontology/DCM/113802"));
				addObjectProperty(irradEvent, "http://medicis.univ-rennes1.fr/ontologies/ontospm/OntoMEDIRAD.owl#has_caracteristic", i);
				break;  
			case "AAPM_220 ": //From Table 10013
				i = createIndiv(generateName(value),model.getResource("http://dicom.nema.org/resources/ontology/DCM/113987"));
				addObjectProperty(irradEvent, "http://medicis.univ-rennes1.fr/ontologies/ontospm/OntoMEDIRAD.owl#has_caracteristic", i);
				break;
			}
		case "Patient_Model":
			logger.warn("Field "+name+" with Unknown Value "+value); break;	

			// Table 10013 CT Irradiation Event data (part 16, p426)		
		case "Acquisition Protocol":
			i = createIndiv(generateName("CT_acquisition_protocol"), model.getResource("http://medicis.univ-rennes1.fr/ontologies/ontospm/OntoMEDIRAD.owl#CT_acquisition"));
			addObjectProperty(irradEvent, racineURI+"has_protocol", i);
			addDataProperty(i, racineURI+"has_name", value);
			break;
		case "Target_Region":
			ArrayList<Individual> listOrgan = createIndividualOrgan(value, e, null);
			for (int i = 0; i<listOrgan.size(); i++) {
				if (listOrgan.get(i).getURI().contains("phantom_device")) {
					addObjectProperty(irradEvent, racineURI+"scans", listOrgan.get(i));
				} else {
					addObjectProperty(irradEvent, racineURI+"has_target_region", listOrgan.get(i));
				}			
			} break;
		case "CT_Acquisition_Type":break;
		case "Reconstruction_Algorithm": 
			switch(value) {
			case "Filtered_Back_Projection":
				i = createIndiv(generateName("Filtered_Back_Projection"),model.getResource("http://dicom.nema.org/resources/ontology/DCM/113962"));
				addObjectProperty(CTradSR, racineURI+"use_algorithm", i);
				break;
			case "Iterative_Reconstruction":
                    i = createIndiv(generateName("Iterative_Reconstruction"),model.getResource("http://dicom.nema.org/resources/ontology/DCM/113963"));
				addObjectProperty(CTradSR, racineURI+"use_algorithm", i);
				break;
			} break;
		case "Irradiation_Event_UID":
			addDataProperty(irradEvent, racineURI+"has_DICOM_UID", value); break; 
		case "Irradiation_Event_Label":
			addDataProperty(irradEvent, racineURI+"has_id", value); break;	// A vérifier
		case "Label_Type":break;                                            // A vérifier
		case "Is_repeated_Acquisition":
			if (value=="Yes") {irradEvent.addOntClass(model.getResource("http://dicom.nema.org/resources/ontology/DCM/128551"));}
			break;
		case "Reason_for_Repeating_Acquisition":
			switch (value) {
			case "Patient_motion":
				addObjectProperty(irradEvent, racineURI+"has_reason_for_repeating_acquisition", createIndiv(generateName("Patient_motion"),model.getResource("http://dicom.nema.org/resources/ontology/DCM/128553")));
				break;
			case "Suboptimal_contrast_timing":
				addObjectProperty(irradEvent, racineURI+"has_reason_for_repeating_acquisition", createIndiv(generateName("Suboptimal_contrast_timing"),model.getResource("http://dicom.nema.org/resources/ontology/DCM/128554")));
				break;
			default:
				logger.warn("Undefined Field : "+name+" with Unknown Value "+value); 
			} break;
		case "CT_Acquisition_Parameters":break;
		case "Exposure_Time":
			createIndivWithUnit(value, "Exposure_Time", "http://dicom.nema.org/resources/ontology/DCM/113824", irradEvent, racineURI+"has_setting");
			break;
		case "Scanning_Length":
			scanningLength = createIndivWithUnit(value, "Scanning_Length", "http://dicom.nema.org/resources/ontology/DCM/113825", irradEvent, racineURI+"has_setting");
			break;
		case "Nominal_Single_Collimation_Width":
			createIndivWithUnit(value, "Nominal_Single_Collimation_Width", "http://dicom.nema.org/resources/ontology/DCM/113826", irradEvent, racineURI+"has_setting");
			break;
		case "Nominal_Total_Collimation_Width":
			createIndivWithUnit(value, "Nominal_Total_Collimation_Width", "http://dicom.nema.org/resources/ontology/DCM/113826", irradEvent, racineURI+"has_setting");
			break;	
		case "Pitch_Factor":
			createIndivWithUnit(value, "Pitch_Factor", "http://dicom.nema.org/resources/ontology/DCM/113828", irradEvent, racineURI+"has_setting");
			break;
		case "CT_X-Ray_Source_Parameters":break;
		case "Number_of_X-Ray_Sources":
			if (Integer.parseInt(value.split("_")[0])==1) {
                i = createIndiv(generateName("CT_scanner"), model.getResource("http://medicis.univ-rennes1.fr/ontologies/ontospm/OntoMEDIRAD.owl#CT_scanner"));
			} else {
				i = createIndiv(generateName("multi_source_CT_scanner"),model.getResource("http://medicis.univ-rennes1.fr/ontologies/ontospm/OntoMEDIRAD.owl#multi_source_CT_scanner"));
				addDataProperty(i, racineURI+"has_number_of_X-Ray_sources", value.split("_")[0]);
			}
			addObjectProperty(i, racineURI+"used_as_instrument_in", irradEvent);
			break;		
		case "Identification_of_the_X-Ray_Source":
			i = createIndiv(generateName("X-Ray_Source"),model.getResource("http://medicis.univ-rennes1.fr/ontologies/ontospm/OntoMEDIRAD.owl#X-Ray_source"));
			addDataProperty(i, racineURI+"has_id", value); break;	
		case "KVP":			
			createIndivWithUnit(value, "KVP", "http://dicom.nema.org/resources/ontology/DCM/113733", irradEvent, racineURI+"has_setting");
			break;	
		case "Maximum_X-Ray_Tube_Current":
			createIndivWithUnit(value, "Maximum_X-Ray_Tube_Current", "http://dicom.nema.org/resources/ontology/DCM/113833", irradEvent, racineURI+"has_setting");
			break;
		case "X-Ray_Tube_Current":
			createIndivWithUnit(value, "X-Ray_Tube_Current", "http://dicom.nema.org/resources/ontology/DCM/113734", irradEvent, racineURI+"has_setting");
			break;
		case "Exposure_Time_per_Rotation":
			createIndivWithUnit(value, "Exposure_Time_per_Rotation", "http://dicom.nema.org/resources/ontology/DCM/113824", irradEvent, racineURI+"has_setting");
			break;
		case "CT_Dose":break;
		case "X-Ray_Filter_Aluminium_Equivalent":
			createIndivWithUnit(value, "X-Ray_Filter_Aluminium_Equivalent", "http://dicom.nema.org/resources/ontology/DCM/113821", irradEvent, racineURI+"has_setting");
			break;		
		case "Mean_CTDIvol":
			CTDI = createIndivWithUnit(value, "Mean_CTDIvol", "http://dicom.nema.org/resources/ontology/DCM/113830", CTradSR, racineURI+"has_dose");
			addObjectProperty(CTDI, racineURI+"is_about_irradiation_event", irradEvent);
			break;	
		case "CTDIw_Phantom_Type":
			switch (value ) {
			case "IEC_Body_Dosimetry_Phantom":
				CTDIwPhantomType = createIndiv(generateName(value),model.getResource("http://dicom.nema.org/resources/ontology/DCM/113691"));
				addObjectProperty(CTDI, racineURI+"estimated_from_phantom", CTDIwPhantomType); 
				break;
			case "IEC_Head_Dosimetry_Phantom":
				i = createIndiv(generateName(value),model.getResource("http://dicom.nema.org/resources/ontology/DCM/113690"));
				addObjectProperty(CTDI, racineURI+"estimated_from_phantom", CTDIwPhantomType); 
				break;
			case "NEMA_XR21-2000_Phantom":
				i = createIndiv(generateName(value),model.getResource("http://dicom.nema.org/resources/ontology/DCM/113692"));
				addObjectProperty(CTDI, racineURI+"estimated_from_phantom", CTDIwPhantomType); 
				break;
			default:
				logger.warn("Field "+name+" with value : "+value);
			} break;
		case "CTDIfreeair_Calculation_factor": 
			createIndivWithUnit(value, "CTDIfreeair_Calculation_factor", "http://dicom.nema.org/resources/ontology/DCM/113836", CTDI, racineURI+"has_setting");
			break;	
		case "Mean_CTDIfreeair":
			createIndivWithUnit(value, "Mean_CTDIfreeair", "http://dicom.nema.org/resources/ontology/DCM/113837", CTDI, racineURI+"has_specified_input");
			break;
		case "DLP":
			DLP = createIndivWithUnit(value, "DLP", "http://dicom.nema.org/resources/ontology/DCM/113838", CTradSR, racineURI+"has_dose");
			addObjectProperty(DLP, racineURI+"is_about_irradiation_event", irradEvent);
			addObjectProperty(DLP, racineURI+"estimated_from_phantom", CTDIwPhantomType); 
			break;	
		case "Measured_Lateral_Dimension":
			createIndivWithUnit(value, "Measured_Lateral_Dimension", "http://dicom.nema.org/resources/ontology/DCM/113931", irradEvent, racineURI+"has_measure");
			break;
		case "Measured_AP_Dimension":
			createIndivWithUnit(value, "Measured_AP_Dimension", "http://dicom.nema.org/resources/ontology/DCM/113932", irradEvent, racineURI+"has_measure");
			break;
		case "Derived_Effective_Diameter":
			createIndivWithUnit(value, "Derived_Effective_Diameter", "http://dicom.nema.org/resources/ontology/DCM/113933", irradEvent, racineURI+"has_measure");
			break;
		case "Water_Equivalent_Diameter":
			createIndivWithUnit(value, "Water_Equivalent_Diameter", "http://dicom.nema.org/resources/ontology/DCM/113980", irradEvent, racineURI+"has_measure");
			break;
		case "X-Ray_Modulation_Type":
			i = createIndiv(generateName("X-Ray_Modulation_Type"),model.getResource("http://dicom.nema.org/resources/ontology/DCM/113842"));
			addDataProperty(i, racineURI+"has_name",value);
			addObjectProperty(irradEvent, racineURI+"has_description", i);                // A REEVALUER
			break; 
		case "Person_Participant":
		case "Device_Participant":
			logger.warn("Field "+name+" with value : "+value);
			break;

			// Table 10014 Scanning Length (part 16, page 432)
		case "Length_of_Reconstructable_Volume":break;
		case "Exposed_Range":
			i = createIndivWithUnit(value, "Exposed_Range", racineDCM+"113899", scanningLength, racineURI+"has_measure"); // relier à l'acquisition
			addObjectProperty(irradEvent, racineURI+"has_measure", i);
			break;
		case "Top_Z_Location_of_Reconstructable_Volume":
			i = createIndivWithUnit(value, "Top_Z_Location_of_Reconstructable_Volume", "http://dicom.nema.org/resources/ontology/DCM/113895", scanningLength, racineURI+"has_measure");// relier à l'acquisition
			addObjectProperty(irradEvent, racineURI+"has_measure", i);
			break;		
		case "Bottom_Z_Location_of_Reconstructable_Volume":
			i = createIndivWithUnit(value, "Bottom_Z_Location_of_Reconstructable_Volume", "http://dicom.nema.org/resources/ontology/DCM/113896", scanningLength, racineURI+"has_measure");// relier à l'acquisition
			addObjectProperty(irradEvent, racineURI+"has_measure", i);
			break;	
		case "Top_Z_Location_of_Scanning_Length":
			i = createIndivWithUnit(value, "Top_Z_Location_of_Scanning_Length", "http://dicom.nema.org/resources/ontology/DCM/113897", scanningLength, racineURI+"has_measure");// relier à l'acquisition
			addObjectProperty(irradEvent, racineURI+"has_measure", i);
			break;		
		case "Bottom_Z_Location_of_Scanning_Length":
			i = createIndivWithUnit(value, "Bottom_Z_Location_of_Scanning_Length", "http://dicom.nema.org/resources/ontology/DCM/113898", scanningLength, racineURI+"has_measure");// relier à l'acquisition
			addObjectProperty(irradEvent, racineURI+"has_measure", i);
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
