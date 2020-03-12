package repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.jena.ontology.Individual;

import javaXSDclass.CTImageAcquisitionDescriptorType;
import javaXSDclass.DICOMSOPInstanceDescriptorType;
import javaXSDclass.DICOMSeriesDescriptorType;
import javaXSDclass.DICOMSeriesType;
import javaXSDclass.DICOMStudyDescriptorType;
import javaXSDclass.DICOMStudyType;
import javaXSDclass.PatientDescriptorType;

public class TranslateDicomCT extends OntologyPopulator {

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

			organs = createIndividualOrgan(organName, patientDescriptor.getPatientID00100020());
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
				addDataProperty(imageDataSet, racineURI+"has_DICOM_study_instance_UID", studyInstanceUID);

				
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

				addObjectProperty(institution, racineObo+"BFO_0000052", role_of_responsible_organization);
				addObjectProperty(role_of_responsible_organization, racineObo+"BFO_0000054", ctImageAcquisition);

				protocol = createIndiv(generateName("Protocol"), model.getResource(racineURI+"CT_acquisition_protocol"));
				addDataProperty(protocol, racineURI+"has_name", serieDescriptorType.getProtocolName00181030());
				addObjectProperty(ctImageAcquisition, racineURI+"has_protocol", protocol);

				dicomsopInstanceDescriptor = serie.getDICOMSOPInstanceDescriptor();
				dicomsopInstanceDescriptorIter = dicomsopInstanceDescriptor.iterator();

				while (dicomsopInstanceDescriptorIter.hasNext()) {
					dicomsopInstanceDescriptorType = dicomsopInstanceDescriptorIter.next();
					ctimageAcquisitionDescriptorType = dicomsopInstanceDescriptorType.getCTImageAcquisitionDescriptor();

					if (ctimageAcquisitionDescriptorType!=null) {
						i =  createIndiv(generateName("KVP"), model.getResource(racineDCM+"113733"));
						addDataProperty(i, racineObo+"IAO_0000004", ctimageAcquisitionDescriptorType.getKVP00180060());
						addObjectProperty(i, racineObo+"IAO_0000039", getUnit("kV"));
						addObjectProperty(ctImageAcquisition,racineURI+"has_setting",i);
						addObjectProperty(i,racineURI+"is_device_setting_of",scanner);
	
						i =  createIndiv(generateName("Exposure_Time"), model.getResource(racineDCM+"113824"));
						addDataProperty(i, racineObo+"IAO_0000004", ctimageAcquisitionDescriptorType.getExposureTime00181150());
						addObjectProperty(i, racineObo+"IAO_0000039", getUnit("ms")); 
						addObjectProperty(ctImageAcquisition,racineURI+"has_setting",i);
						addObjectProperty(i,racineURI+"is_device_setting_of",scanner);
	
						i =  createIndiv(generateName("X-Ray_Tube_Current"), model.getResource(racineDCM+"113734"));
						addDataProperty(i, racineObo+"IAO_0000004", ctimageAcquisitionDescriptorType.getXRayTubeCurrent00181151());
						addObjectProperty(i, racineObo+"IAO_0000039", getUnit("mA"));
						addObjectProperty(ctImageAcquisition,racineURI+"has_setting",i);
						addObjectProperty(i,racineURI+"is_device_setting_of",scanner);
					}
				}
			}
		}
	}

	
	
	
}
