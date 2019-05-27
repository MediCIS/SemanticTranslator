package repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.jena.ontology.Individual;
import org.apache.jena.rdf.model.ModelFactory;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Sequence;
import org.dcm4che3.data.Tag;

public class TranslateDicomMetadatas extends OntologyPopulator {

	static Iterator<Attributes> iter;
	
	public static void translateCodingSchemeDesignator(Sequence RadiopharmaceuticalInformationSequence, Individual acquisition) {
		System.out.println("-----------------------------------------------");
		logger.debug("RadiopharmaceuticalInformationSequence : "+RadiopharmaceuticalInformationSequence);
		iter = RadiopharmaceuticalInformationSequence.iterator();
		while (iter.hasNext()) {
			Attributes t = iter.next();
			if (t.getSequence(Tag.RadionuclideCodeSequence)!=null) {
				Sequence RadionuclideCodeSequence = t.getSequence(Tag.RadionuclideCodeSequence);
				logger.debug("RadionuclideCodeSequence : "+RadionuclideCodeSequence);
				Iterator<Attributes> iterRadioNucl = RadionuclideCodeSequence.iterator();
				while (iterRadioNucl.hasNext()) {
					Attributes radioNucl = iterRadioNucl.next();
					if (radioNucl.getString(Tag.CodeMeaning)!=null) {
						String CodeMeaning = radioNucl.getString(Tag.CodeMeaning);
						logger.debug("CodeMeaning : "+CodeMeaning);
						Individual atom;
						if (CodeMeaning.contains("18F")) {
							atom = createIndiv(model.getResource(racineURI+"fluorine_18_atom"));
						} else if (CodeMeaning.contains("^177^Lutetium")) {
							atom = createIndiv(model.getResource(racineURI+"lutetium_177_atom"));
						} else {
							atom = createIndiv("Uknown_Nucleotide_"+CodeMeaning, model.getResource(racineURI+"radionuclide"));
						}
						addObjectProperty(acquisition, racineURI+"has_target_radionuclide", atom);
					}
					if (radioNucl.getString(Tag.CodingSchemeDesignator)!=null) {
						String CodingSchemeDesignator = radioNucl.getString(Tag.CodingSchemeDesignator);
						logger.debug("CodingSchemeDesignator : "+CodingSchemeDesignator);
					}
					if (radioNucl.getString(Tag.CodeValue)!=null) {
						String CodeValue = radioNucl.getString(Tag.CodeValue);
						logger.debug("CodeValue : "+CodeValue);
					}
				}
			}
			if (t.getSequence(Tag.RadionuclideTotalDose)!=null) {
				String RadionuclideTotalDose = t.getString(Tag.RadionuclideTotalDose);
				logger.debug("RadionuclideTotalDose : "+RadionuclideTotalDose);
			}
		}
		System.out.println("-----------------------------------------------");
	}
	
	public static void translateDicomMetaData(Attributes root, String ClinicalResearchStudyId) {
		logger.info("translateDicomMetaData");
		if (model==null) {model = Application.getModel();}
		if (dataModel==null) {model = Application.dataModel;}
		populateModel = ModelFactory.createOntologyModel();
		if (memory==null) {memory = Application.memory;}

		// Commmon MetaDatas
		logger.info("Commmon MetaDatas");

		Individual imagingStudy = createIndiv(generateName("imaging_study"), model.getResource("http://medicis.univ-rennes1.fr/ontologies/ontospm/OntoMEDIRAD.owl#imaging_study"));
		
		Individual clinicalResearchStudy = OntologyPopulator.retrieveClinicalResearchStudy(ClinicalResearchStudyId);
		addObjectProperty(imagingStudy, racineURI+"part_of_study", clinicalResearchStudy);			
		
		String PatientID = root.getString(Tag.PatientID);
		logger.debug("PatientID : "+PatientID);
		if (PatientID!=null) {
			patient = memory.getHuman(PatientID);														// create human
			patientRole = createIndiv(generateName("Patient"), model.getResource(racineURI+"patient")); // create patient role		
			addObjectProperty(patient, racineObo+"BFO_0000087", patientRole);							// link both of them
			//addObjectProperty(patientRole, racineObo+"BFO_0000054", imagingStudy);							// link both of them
			addDataProperty(patient, racineURI+"has_id", PatientID);
		}
		
		String PatientName = root.getString(Tag.PatientName);
		logger.debug("PatientBirthDate : "+PatientName);
		if (PatientName!=null) {
			addDataProperty(patient, racineURI+"has_name", PatientName);
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
			String number = PatientAge.substring(0, PatientAge.length()-1);
			String lettre = PatientAge.substring(PatientAge.length()-1, PatientAge.length());
			switch (lettre) {
			case "M":
				addDataProperty(i, racineURI+"months", new BigDecimal(number) ); break;
			case "Y":
				addDataProperty(i, racineURI+"years", new BigDecimal(number)); break;
			default:
				addDataProperty(i, "http://purl.obolibrary.org/obo/IAO_0000004", Integer.valueOf(number)); break;
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
				ArrayList<Individual> organList = createIndividualOrgan(BodyPartExamined, PatientID);
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
		Individual imageAccRole = null;

		System.out.println("---------------------------------");
		String SOPClassUID = root.getString(Tag.SOPClassUID);
		logger.debug("SOPClassUID : "+SOPClassUID);
		
		String[] ImageType = root.getStrings(Tag.ImageType);
		String ImageTypeLog = "";
		for (int i = 0; i<ImageType.length; i++) {
			ImageTypeLog+=ImageType[i]+" | ";
		}
		logger.debug("ImageType : "+ImageTypeLog);
		System.out.println("---------------------------------");

		
		if (SOPClassUID.equals("1.2.840.10008.5.1.4.1.1.128") || SOPClassUID.equals("1.2.840.10008.5.1.4.1.1.130")) {
			logger.debug("Type : PET");
			//ontomedirad:PET_acquisition
			acquisition = createIndiv(generateName("PET_data_acquisition"), model.getResource(racineURI+"PET_data_acquisition"));
			acquisitionDevice = createIndiv(generateName("PET_scanner"), model.getResource(racineURI+"PET_scanner"));
			acquisitionProtocol = createIndiv(generateName("PET_acquisition_protocol"), model.getResource(racineURI+"PET_acquisition_protocol"));
			imageAccRole = createIndiv(generateName("image_acquisition_role"), model.getResource(racineURI+"image_acquisition_role"));
		} else if (SOPClassUID.equals("1.2.840.10008.5.1.4.1.1.20")) {
			if (ImageType!=null && ImageType.length>=2) {
				if ( ImageType[2].contains("TOMO")  || ImageType[2].contains("STATIC") || ImageType[2].contains("WHOLE BODY") ) { //NM
					logger.debug("Type : NM");
					//ontomedirad:NM_acquisition
					acquisition = createIndiv(generateName("SPECT_data_acquisition"), model.getResource(racineURI+"SPECT_data_acquisition"));
					acquisitionDevice = createIndiv(generateName("SPECT_scanner"), model.getResource(racineURI+"SPECT_scanner"));
					acquisitionProtocol = createIndiv(generateName("NM_acquisition_protocol"), model.getResource(racineURI+"NM_acquisition_protocol"));
					imageAccRole = createIndiv(generateName("image_acquisition_role"), model.getResource(racineURI+"image_acquisition_role"));
				} else {
					return;
				}
			}
		} else if (SOPClassUID.equals("1.2.840.10008.5.1.4.1.1.2") || SOPClassUID.equals("1.2.840.10008.5.1.4.1.1.2.1") ) {
			logger.debug("Type : CT");
			//ontomedirad:CT_acquisition
			acquisition = createIndiv(generateName("CT_acquisition"), model.getResource(racineURI+"CT_acquisition"));
			acquisitionDevice = createIndiv(generateName("CT_scanner"), model.getResource(racineURI+"CT_scanner"));
			acquisitionProtocol = createIndiv(generateName("CT_acquisition_protocol"), model.getResource(racineURI+"CT_acquisition_protocol"));
			imageAccRole = createIndiv(generateName("image_acquisition_role"), model.getResource(racineURI+"image_acquisition_role"));
		} else {
			return;
		}
		
		System.out.println("acquisition : "+acquisition);
		System.out.println("acquisitionProtocol : "+acquisitionProtocol);
		System.out.println("acquisitionDevice : "+acquisitionDevice);
	
		addObjectProperty(patientRole, racineObo+"BFO_0000054", imagingStudy);	 
		addObjectProperty(acquisition, racineURI+"has_protocol", acquisitionProtocol);
		addObjectProperty(patientRole, racineObo+"BFO_0000054", acquisition);	 
		addObjectProperty(imageAccRole, racineObo+"BFO_0000054", acquisition);
		addObjectProperty(acquisitionDevice, racineURI+"used_as_instrument_in", acquisition);	
		
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

		String SeriesInstanceUID = root.getString(Tag.SeriesInstanceUID);
		logger.debug("SeriesInstanceUID : "+SeriesInstanceUID);
		if (SeriesInstanceUID!=null) {
			addDataProperty(acquisition, racineURI+"has_DICOM_series_instance_UID", SeriesInstanceUID);
		}
		
		//ontomedirad:SPECT_acquisition

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
			addObjectProperty(acquisitionDevice, racineObo+"BFO_0000087", imageAccRole);
			//addObjectProperty(i, racineObo+"BFO_0000054", acquisition);
		}

		String InstitutionName = root.getString(Tag.InstitutionName);
		logger.debug("InstitutionName : "+InstitutionName);
		if (InstitutionName!=null) {
			Individual institution = memory.getInstitution(InstitutionName);
			Individual role_of_responsible_organization = memory.getRoleOfResponsibleOrganization(InstitutionName);
			addObjectProperty(role_of_responsible_organization, racineObo+"BFO_0000052", institution);
			if (acquisition!=null) {
			//	addObjectProperty(role_of_responsible_organization, racineObo+"BFO_0000054", acquisition);
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
		
			
		if (SOPClassUID.equals("1.2.840.10008.5.1.4.1.1.2") && (ImageType[2].contains("AXIAL") || ImageType[2].contains("LOCALIZER"))) { // 3.1  
			logger.debug("CT_image_dataset");
			Individual dataSet = null;
			if (ImageType[2].contains("LOCALIZER")) {
				dataSet = createIndiv(generateName("CT_localizer"), model.getResource(racineURI+"CT_localizer"));
			} else if (ImageType[2].contains("AXIAL")) {
				dataSet = createIndiv(generateName("CT_image_dataset"), model.getResource(racineURI+"CT_image_dataset"));
			}
				
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
				i = createIndiv(generateName("ExposureTime"),model.getResource(racineDCM+"113824"));
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
				i = createIndiv(generateName("exposure"),model.getResource(racineURI+"exposure"));
				addDataProperty(i, racineObo+"IAO_0000004", Exposure);
				addObjectProperty(i, racineObo+"IAO_0000039", getUnit("ma"));
				addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
				addObjectProperty(i, racineURI+"is_device_setting_of", acquisitionDevice);
				//addObjectProperty(acquisition, racineURI+"has_setting", i);
			}
			
			String ExposureInmAs = root.getString(Tag.ExposureInmAs);
			logger.debug("ExposureTime : "+ExposureInmAs);
			if (ExposureInmAs!=null) {
				i = createIndiv(generateName("exposure"),model.getResource(racineURI+"exposure"));
				addDataProperty(i, racineObo+"IAO_0000004", ExposureInmAs);
				addObjectProperty(i, racineObo+"IAO_0000039", getUnit("milliampere"));
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
				addObjectProperty(filter, racineURI+"BFO_0000177", acquisitionDevice);
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
				addObjectProperty(i, racineObo+"IAO_0000039", getUnit("mm"));
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
						addObjectProperty(i, racineObo+"IAO_0000039", getUnit("milliampere"));
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
				if (CTAcquisitionDetailsSequence != null) {
					iter = CTAcquisitionDetailsSequence.iterator();
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
		} else if (SOPClassUID.equals("1.2.840.10008.5.1.4.1.1.2.1") && ImageType[2].contains("AXIAL")) { //3.2
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
				// Sinon aller dans : 
				// SharedFunctionnal // CTAcquisitionTypeSeq //A quisitionType
			}

			Sequence CTAcquisitionDetailsSequence = root.getSequence(Tag.CTAcquisitionDetailsSequence);
			iter = CTAcquisitionDetailsSequence.iterator();
			while (iter.hasNext()) {
				Attributes t = iter.next();
				if (t.getString(Tag.RevolutionTime)!=null) {
					String RevolutionTime = t.getString(Tag.RevolutionTime);
					logger.debug("RevolutionTime : "+RevolutionTime);
				}
				if (t.getString(Tag.TotalCollimationWidth)!=null) {
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
				}
				if (t.getString(Tag.GantryDetectorTilt)!=null) {
					String GantryDetectorTilt = t.getString(Tag.GantryDetectorTilt);
					logger.debug("GantryDetectorTilt : "+GantryDetectorTilt);
				}
				if (t.getString(Tag.TableFeedPerRotation)!=null) {
					String TableFeedPerRotation = t.getString(Tag.TableFeedPerRotation);
					logger.debug("TableFeedPerRotation : "+TableFeedPerRotation);
				}
				if (t.getString(Tag.SpiralPitchFactor)!=null) {
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
						if (filterMaterial!=null ) {
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
				}
				if (t.getString(Tag.XRayTubeCurrentInmA)!=null) {
					String XRayTubeCurrentInmA = t.getString(Tag.XRayTubeCurrentInmA);
					logger.debug("XRayTubeCurrentInmA : "+XRayTubeCurrentInmA);
					i = createIndiv(generateName("XRayTubeCurrent"),model.getResource(racineDCM+"113734"));
					addDataProperty(i, racineObo+"IAO_0000004", XRayTubeCurrentInmA);
					addObjectProperty(i, racineObo+"IAO_0000039", getUnit("ma"));
					addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
					addObjectProperty(i, racineURI+"is_device_setting_of", acquisitionDevice);
					addObjectProperty(acquisition, racineURI+"has_setting", i);
				}
				if (t.getString(Tag.ExposureInmAs)!=null) {
					String ExposureInmAs = t.getString(Tag.ExposureInmAs);
					logger.debug("ExposureInmAs : "+ExposureInmAs);
					i = createIndiv(generateName("exposure_time"),model.getResource(racineDCM+"113824"));
					addDataProperty(i, racineObo+"IAO_0000004", ExposureInmAs);
					addObjectProperty(i, racineObo+"IAO_0000039", getUnit("millampere"));
					addObjectProperty(i, racineObo+"BFO_0000177", acquisitionProtocol);
					addObjectProperty(i, racineURI+"is_device_setting_of", acquisitionDevice);
					addObjectProperty(acquisition, racineURI+"has_setting", i);
				}
				if (t.getString(Tag.ExposureModulationType)!=null) {
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

		} else if (ImageType!=null && ImageType.length>=3) {
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
				logger.debug("EnergyWindowInformationSequence : "+EnergyWindowInformationSequence);
				if (EnergyWindowInformationSequence!=null) {
					Individual energyWindow = createIndiv(generateName("energy_window"), model.getResource(racineURI+"energy_window"));
					addObjectProperty(energyWindow, racineObo+"BFO_0000177", acquisitionProtocol);
					addObjectProperty(energyWindow, racineURI+"is_device_setting", acquisitionDevice);
					addObjectProperty(acquisition, racineURI+"has_setting", energyWindow);

					iter = EnergyWindowInformationSequence.iterator();
					while (iter.hasNext()) {
						Attributes t = iter.next();
						if (t.getString(Tag.EnergyWindowName)!=null) {
							String EnergyWindowName = t.getString(Tag.EnergyWindowName);
							logger.debug("EnergyWindowName : "+EnergyWindowName);
							if (EnergyWindowName!=null) {
								addDataProperty(energyWindow, racineURI+"has_name", EnergyWindowName);
							}
						}
						if (t.getString(Tag.EnergyWindowLowerLimit)!=null) {
							String EnergyWindowLowerLimit = t.getString(Tag.EnergyWindowLowerLimit);
							logger.debug("EnergyWindowLowerLimit : "+EnergyWindowLowerLimit);
							if (EnergyWindowLowerLimit!=null) {
								addDataProperty(energyWindow, racineURI+"has_lower_limit", EnergyWindowLowerLimit);
								addObjectProperty(energyWindow, "http://purl.obolibrary.org/obo/IAO_0000039", getUnit("kiloelectronvolt"));
							}
						}
						if (t.getString(Tag.EnergyWindowUpperLimit)!=null) {
							String EnergyWindowUpperLimit = t.getString(Tag.EnergyWindowUpperLimit);
							logger.debug("EnergyWindowUpperLimit : "+EnergyWindowUpperLimit);
							if (EnergyWindowUpperLimit!=null) {
								addDataProperty(energyWindow, racineURI+"has_upper_limit", EnergyWindowUpperLimit);
								addObjectProperty(energyWindow, "http://purl.obolibrary.org/obo/IAO_0000039", getUnit("kiloelectronvolt"));
							} 
						} 
					}
				}
			
				translateCodingSchemeDesignator(root.getSequence(Tag.RadiopharmaceuticalInformationSequence),  acquisition);
					
				Sequence DetectorInformationSequence = root.getSequence(Tag.DetectorInformationSequence);
				iter = DetectorInformationSequence.iterator();
				while (iter.hasNext()) {
					Attributes t = iter.next();
					if (t.getString(Tag.CollimatorGridName)!=null) {
						String CollimatorGridName = t.getString(Tag.CollimatorGridName);
						logger.debug("CollimatorGridName : "+CollimatorGridName);
					}
					if (t.getString(Tag.CollimatorType)!=null) {
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
					}
					if (t.getString(Tag.RotationDirection)!=null) {
						String RotationDirection = t.getString(Tag.RotationDirection);
						logger.debug("RotationDirection : "+RotationDirection);
					}
					if (t.getString(Tag.NumberOfFramesInRotation)!=null) {
						String NumberOfFramesInRotation = t.getString(Tag.NumberOfFramesInRotation);
						logger.debug("NumberOfFramesInRotation : "+NumberOfFramesInRotation);
					}
					if (t.getString(Tag.TypeOfDetectorMotion)!=null) {
						String TypeOfDetectorMotion = t.getString(Tag.TypeOfDetectorMotion);
						logger.debug("TypeOfDetectorMotion : "+TypeOfDetectorMotion);
					} 
					if (t.getString(Tag.ActualFrameDuration)!=null) {
						String ActualFrameDuration = t.getString(Tag.ActualFrameDuration);
						logger.debug("ActualFrameDuration : "+ActualFrameDuration);
					}
					
					
				}

			} else if (ImageType[2].contains("TOMO RECON")  || ImageType[2].contains("RECON TOMO")) { // 4.2 NM
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
					addObjectProperty(energyWindow, racineURI+"is_device_setting", acquisitionDevice);
					addObjectProperty(acquisition, racineURI+"has_setting", energyWindow);
					iter = EnergyWindowInformationSequence.iterator();
					while (iter.hasNext()) {
						Attributes t = iter.next();
						if (t.getString(Tag.EnergyWindowName)!=null) {
							String EnergyWindowName = t.getString(Tag.EnergyWindowName);
							logger.debug("EnergyWindowName : "+EnergyWindowName);
							if (EnergyWindowName!=null) {
								addDataProperty(energyWindow, racineURI+"has_name", EnergyWindowName);
							}
						}
						if (t.getString(Tag.EnergyWindowLowerLimit)!=null) {
							String EnergyWindowLowerLimit = t.getString(Tag.EnergyWindowLowerLimit);
							logger.debug("EnergyWindowLowerLimit : "+EnergyWindowLowerLimit);
							if (EnergyWindowLowerLimit!=null) {
								addDataProperty(energyWindow, racineURI+"has_lower_limit", EnergyWindowLowerLimit);
								addObjectProperty(energyWindow, "http://purl.obolibrary.org/obo/IAO_0000039", getUnit("kiloelectronvolt"));
							}
						}
						if (t.getString(Tag.EnergyWindowUpperLimit)!=null) {
							String EnergyWindowUpperLimit = t.getString(Tag.EnergyWindowUpperLimit);
							logger.debug("EnergyWindowUpperLimit : "+EnergyWindowUpperLimit);
							if (EnergyWindowUpperLimit!=null) {
								addDataProperty(energyWindow, racineURI+"has_upper_limit", EnergyWindowUpperLimit);
								addObjectProperty(energyWindow, "http://purl.obolibrary.org/obo/IAO_0000039", getUnit("kiloelectronvolt"));
							}
						} 
					}
				}
				
				translateCodingSchemeDesignator(root.getSequence(Tag.RadiopharmaceuticalInformationSequence),  acquisition);
				
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
					addObjectProperty(energyWindow, racineURI+"is_device_setting", acquisitionDevice);
					addObjectProperty(acquisition, racineURI+"has_setting", energyWindow);
					iter = EnergyWindowInformationSequence.iterator();
					while (iter.hasNext()) {
						Attributes t = iter.next();
						if (t.getString(Tag.EnergyWindowName)!=null) {
							String EnergyWindowName = t.getString(Tag.EnergyWindowName);
							logger.debug("EnergyWindowName : "+EnergyWindowName);
							if (EnergyWindowName!=null) {
								addDataProperty(energyWindow, racineURI+"has_name", EnergyWindowName);
							}
						}
						if (t.getString(Tag.EnergyWindowLowerLimit)!=null) {
							String EnergyWindowLowerLimit = t.getString(Tag.EnergyWindowLowerLimit);
							logger.debug("EnergyWindowLowerLimit : "+EnergyWindowLowerLimit);
							if (EnergyWindowLowerLimit!=null) {
								addDataProperty(energyWindow, racineURI+"has_lower_limit", EnergyWindowLowerLimit);
								addObjectProperty(energyWindow, "http://purl.obolibrary.org/obo/IAO_0000039", getUnit("kiloelectronvolt"));
							}
						}
						if (t.getString(Tag.EnergyWindowUpperLimit)!=null) {
							String EnergyWindowUpperLimit = t.getString(Tag.EnergyWindowUpperLimit);
							logger.debug("EnergyWindowUpperLimit : "+EnergyWindowUpperLimit);
							if (EnergyWindowUpperLimit!=null) {
								addDataProperty(energyWindow, racineURI+"has_upper_limit", EnergyWindowUpperLimit);
								addObjectProperty(energyWindow, "http://purl.obolibrary.org/obo/IAO_0000039", getUnit("kiloelectronvolt"));
							}
						} 
					}
				}
				
				translateCodingSchemeDesignator(root.getSequence(Tag.RadiopharmaceuticalInformationSequence),  acquisition);

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
			
		} else if (SOPClassUID.equals("1.2.840.10008.5.1.4.1.1.128")) { // 5.1
			logger.debug("Positron Emission Tomography Image Storage");
			Individual PETDataSet = createIndiv(generateName("PET_recon_tomo_dataset"), model.getResource(racineURI+"PET_recon_tomo_dataset"));
			
			logger.debug("SOPClassUID : "+SOPClassUID);
			i = createIndiv(model.getResource(racineURI+"DICOM_PET_image_storage_SOP_class"));
			addObjectProperty(PETDataSet, racineURI+"has_format", i);
			
			logger.debug("SeriesInstanceUID : "+SeriesInstanceUID);
			addDataProperty(PETDataSet, racineURI+"has_DICOM_series_instance_UID", SeriesInstanceUID);
			//Individual PETDataAcquisition = createIndiv(generateName("PET_data_acquisition"), model.getResource(racineURI+"PET_data_acquisition"));
			addObjectProperty(PETDataSet, racineURI+"is_specified_output_of", acquisition);
			
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
			
			translateCodingSchemeDesignator(root.getSequence(Tag.RadiopharmaceuticalInformationSequence),  acquisition);
			
			Sequence EnergyWindowRangeSequence = root.getSequence(Tag.EnergyWindowRangeSequence);
			logger.debug("EnergyWindowRangeSequence : "+EnergyWindowRangeSequence);

			if (EnergyWindowRangeSequence!=null) {
				Individual energyWindow = createIndiv(generateName("energy_window"), model.getResource(racineURI+"energy_window"));
				addObjectProperty(energyWindow, racineObo+"BFO_0000177", acquisitionProtocol);
				addObjectProperty(energyWindow, racineURI+"is_device_setting", acquisitionDevice);
				addObjectProperty(acquisition, racineURI+"has_setting", energyWindow);
				iter = EnergyWindowRangeSequence.iterator();
				Attributes t;
				while (iter.hasNext()) {
					t = iter.next();
					if (t.getString(Tag.EnergyWindowLowerLimit)!=null) {
						String EnergyWindowLowerLimit = t.getString(Tag.EnergyWindowLowerLimit);
						logger.debug("EnergyWindowLowerLimit : "+EnergyWindowLowerLimit);
						if (EnergyWindowLowerLimit!=null) {
							addDataProperty(energyWindow, racineURI+"has_lower_limit", EnergyWindowLowerLimit);
							addObjectProperty(energyWindow, "http://purl.obolibrary.org/obo/IAO_0000039", getUnit("kiloelectronvolt"));
						}
					}  
					if (t.getString(Tag.EnergyWindowUpperLimit)!=null) {
						String EnergyWindowUpperLimit = t.getString(Tag.EnergyWindowUpperLimit);
						logger.debug("EnergyWindowUpperLimit : "+EnergyWindowUpperLimit);
						if (EnergyWindowUpperLimit!=null) {
							addDataProperty(energyWindow, racineURI+"has_upper_limit", EnergyWindowUpperLimit);
							addObjectProperty(energyWindow, "http://purl.obolibrary.org/obo/IAO_0000039", getUnit("kiloelectronvolt"));
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
					acquisition.addOntClass(model.getResource(racineURI+"step_and_shoot_PET_data_acquisition"));
				} else if (TypeOfDetectorMotion.contains("CONTINUOUS")) {
					acquisition.addOntClass(model.getResource(racineURI+"continuous_PET_data_acquisition"));
				} else if (TypeOfDetectorMotion.contains("WOBBLE")) {
					acquisition.addOntClass(model.getResource(racineURI+"wobble_PET_data_acquisition"));
				} else if (TypeOfDetectorMotion.contains("CLAMSHELL")) {
					acquisition.addOntClass(model.getResource(racineURI+"clamshell_PET_data_acquisition"));
				} 
			}

		} else if (SOPClassUID.equals("1.2.840.10008.5.1.4.1.1.130")) { // 5.2
			logger.debug("Enhanced PET Image Storage");
			Individual PETDataSet = createIndiv(generateName("PET_recon_tomo_dataset"), model.getResource(racineURI+"PET_recon_tomo_dataset"));			
			
			logger.debug("SOPClassUID : "+SOPClassUID);
			i = createIndiv(generateName("DICOM_enhanced_PET_image_storage_SOP_class"), model.getResource(racineURI+"DICOM_enhanced_PET_image_storage_SOP_class"));
			addObjectProperty(PETDataSet, racineURI+"has_format", i);
			
			Individual PETDataAcquisition = createIndiv(generateName("SPECT_data_acquisition"), model.getResource(racineURI+"SPECT_data_acquisition"));
			
			logger.debug("SeriesInstanceUID : "+SeriesInstanceUID);
			addDataProperty(PETDataSet, racineURI+"has_DICOM_series_instance_UID", SeriesInstanceUID);
			addObjectProperty(PETDataSet, racineURI+"is_specified_output_of", acquisition);

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

			translateCodingSchemeDesignator(root.getSequence(Tag.RadiopharmaceuticalInformationSequence), acquisition);
			
			Sequence EnergyWindowInformationSequence = root.getSequence(Tag.EnergyWindowInformationSequence);
			iter = EnergyWindowInformationSequence.iterator();
			while (iter.hasNext()) {
				Attributes t = iter.next();
				if (t.getString(Tag.EnergyWindowName)!=null) {
					String EnergyWindowName = t.getString(Tag.EnergyWindowName);
					logger.debug("EnergyWindowName : "+EnergyWindowName);
				}
				if (t.getString(Tag.EnergyWindowLowerLimit)!=null) {
					String EnergyWindowLowerLimit = t.getString(Tag.EnergyWindowLowerLimit);
					logger.debug("EnergyWindowLowerLimit : "+EnergyWindowLowerLimit);
				}
				if (t.getString(Tag.EnergyWindowUpperLimit)!=null) {
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
					}
					if (ReconstructionAlgorithm.contains("RAMLA")) {
						reconstructionAlgorithm = createIndiv(model.getResource(racineURI+"RAMLA"));
					}
					if (ReconstructionAlgorithm.contains("MLEM")) {
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