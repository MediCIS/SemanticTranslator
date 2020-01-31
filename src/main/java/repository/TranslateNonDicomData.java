package repository;

import java.math.BigInteger;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.UUID;

import org.apache.jena.ontology.Individual;
import org.apache.jena.rdf.model.ModelFactory;

import javaXSDclass.AbsorbedDoseCalculationInVOI;
import javaXSDclass.AbsorbedDoseInVOI;
import javaXSDclass.AbsorbedDoseInVOIUnit;
import javaXSDclass.ActivityUnit;
import javaXSDclass.AdministeredActivity;
import javaXSDclass.CTAcqForCTNumberCalibrationCurve;
import javaXSDclass.CTNumberCalibrationCurve;
import javaXSDclass.CTNumberCalibrationCurveReference;
import javaXSDclass.CTNumberCalibrationWorkfow;
import javaXSDclass.CTSegmentationInCalibration;
import javaXSDclass.CalculationOfCTNumberCalibrationCurve;
import javaXSDclass.CalibrationWorkflow;
import javaXSDclass.ColdInsert;
import javaXSDclass.CountsPerVOIAtTimePoint;
import javaXSDclass.CountsUnit;
import javaXSDclass.DICOMData;
import javaXSDclass.DataActivityPerVOIAtTimePoint;
import javaXSDclass.DensityPhantom;
import javaXSDclass.ElementOfCTNumberCalibrationCurve;
import javaXSDclass.ElementsOfCTNumberCalibrationCurve;
import javaXSDclass.HotInsert;
import javaXSDclass.HybridDosimetryworkflow;
import javaXSDclass.Isotope;
import javaXSDclass.NMPhantom;
import javaXSDclass.NMRelevantCalibrationReference;
import javaXSDclass.NonDICOMData;
import javaXSDclass.NonDICOMDataFormat;
import javaXSDclass.NonDicomFileSetDescriptor;
import javaXSDclass.OrganMass;
import javaXSDclass.OrganOrTissue;
import javaXSDclass.PlanarCalibrationWorkflow;
import javaXSDclass.PlanarDataAcquisitionAndProcessingContainer;
import javaXSDclass.PostAdminBackgroundActivity;
import javaXSDclass.PreAdminBackgroundActivity;
import javaXSDclass.ProcessExecutionContext;
import javaXSDclass.RadioBiologicalCalculation;
import javaXSDclass.RadioBiologicalCalculationIn3DSlide1Dosimetry;
import javaXSDclass.RealDensityOfMaterialUnit;
import javaXSDclass.RegistrationVOISegmentationAndPropagation;
import javaXSDclass.RegistrationVOISegmentationAndPropagationContainer;
import javaXSDclass.SPECTAcqCTAcqAndReconstruction;
import javaXSDclass.SPECTAcqCTAcqAndReconstructionInCalibration;
import javaXSDclass.SPECTCTCalibrationWorkflow;
import javaXSDclass.SPECTDataAcquisitionAndReconstruction;
import javaXSDclass.SPECTReconstruction;
import javaXSDclass.SPECTReconstructionInCalibration;
import javaXSDclass.SPECTRecoveryCoefficientCurveCalculation;
import javaXSDclass.SPECTSensitivityCalculation;
import javaXSDclass.Segmentation;
import javaXSDclass.Tank;
import javaXSDclass.ThreeDimDosimetrySlide1Workflow;
import javaXSDclass.ThreeDimDosimetrySlide2Workflow;
import javaXSDclass.TimeActivityCurveFitIn3DDosimetry;
import javaXSDclass.TimeIntegratedActivityCoefficientPerVOI;
import javaXSDclass.TimeIntegratedActivityPerVOI;
import javaXSDclass.TimePointDescriptionElement;
import javaXSDclass.TimeUnit;
import javaXSDclass.TwoDimDosimetryViaAbsorbedDoseRateCalculation;
import javaXSDclass.TwoDimDosimetryViaTimeActivityCurveFit;
import javaXSDclass.TwoDimDosimetryworkflow;
import javaXSDclass.VOI;
import javaXSDclass.VOIActivityDetermination;
import javaXSDclass.VolumeUnit;

// XSD Version 52

public class TranslateNonDicomData extends OntologyPopulator {

	static Hashtable<String, Individual> tableVOI = new Hashtable<String, Individual>();
	static Hashtable<String, Individual> tableVOIorgans = new Hashtable<String, Individual>();
	static Hashtable<String, String> tableVOIname = new Hashtable<String, String>();
	static Hashtable<String, Individual> tableTimePoint = new Hashtable<String, Individual>();
	static Hashtable<String, Individual> tableInputOutput = new Hashtable<String, Individual>();
	static Hashtable<String, Individual> tableSegmentation = new Hashtable<String, Individual>();
	static Hashtable<String, Individual> tableVoxelAbsorbedDoseMap = new Hashtable<String, Individual>();


	static Individual researchClinicalStudy;
	
	public static void translateNonDicomDataObject(NonDICOMData nonDICOMData, Individual nonDICOMDataIndividual) {		
		String dataClass = nonDICOMData.getNonDICOMDataClass();
		switch (dataClass) {
		case "VOI":
			nonDICOMDataIndividual.addOntClass(model.getResource(racineURI+"VOI"));
			break;
		case "VOI superimposed on images":
			nonDICOMDataIndividual.addOntClass(model.getResource(racineURI+"VOI_superimposed_on_images"));
			break;
		case "3D absorbed dose map":
			nonDICOMDataIndividual.addOntClass(model.getResource(racineDCM+"128487"));
			break;
		case "segmentation":
			nonDICOMDataIndividual.addOntClass(model.getResource(racineURI+"image_segmentation")); 
			break;
		}
		
		NonDICOMDataFormat format = nonDICOMData.getNonDICOMDataFormat();
		switch (format) {
		case GIF_FORMAT_EMBEDDING_IMAGE_J_CONTOURS:
			addObjectProperty(nonDICOMDataIndividual, racineURI+"has_format", createIndiv(model.getResource(racineURI+"GIF_format_embedding_imageJ_contours")));
			break;
		case HDF_FORMAT:

			break;
		case NRRD_FORMAT:
			addObjectProperty(nonDICOMDataIndividual, racineURI+"has_format", createIndiv(model.getResource(racineURI+"NRRD_format")));
			break;
		case STL_FORMAT:
			addObjectProperty(nonDICOMDataIndividual, racineURI+"has_format", createIndiv(model.getResource(racineURI+"STL_format")));
			break;
		case TIFF_FORMAT_EMBEDDING_IMAGE_J_CONTOURS:
			addObjectProperty(nonDICOMDataIndividual, racineURI+"has_format", createIndiv(model.getResource(racineURI+"TIFF_format_embedding_imageJ_contours")));
			break;
		case ZIPPED_IMAGE_J_CONTOURS_FORMAT:
			addObjectProperty(nonDICOMDataIndividual, racineURI+"has_format", createIndiv(model.getResource(racineURI+"zipped_imageJ_contours_format")));
			break;
		case ZIPPED_PSEUDO_DICOM_IMPACT_MC:
			addObjectProperty(nonDICOMDataIndividual, racineURI+"has_format", createIndiv(model.getResource(racineURI+"zipped_pseudo_DICOM_ImpactMC")));
			break;						
		}
		
		String fileName = nonDICOMData.getNonDICOMDataFileName();
		addDataProperty(nonDICOMDataIndividual, racineURI+"has_name", fileName);

		addDataProperty(nonDICOMDataIndividual, racineURI+"has_IRDBB_FHIR_handle", nonDICOMData.getFHIRIdentifier());
	}
	
	public static void translateNonDicomData(NonDicomFileSetDescriptor nonDicomFileSetDescriptor) { // 1st function to read XML, check what is inside and call the appropritae function
		populateModel = ModelFactory.createOntologyModel();
		if (model==null) {model = Application.getModel();}
		if (memory==null) {memory = Application.memory;}
		logger.error("translateNonDicomData is Work in Progress");
		
		researchClinicalStudy = retrieveClinicalResearchStudy(nonDicomFileSetDescriptor.getReferencedClinicalResearchStudy().getClinicalResearchStudyID());
		String patientID = nonDicomFileSetDescriptor.getPatientId();
		memory.getPatientbyId(patientID);
		
		if (nonDicomFileSetDescriptor.getWP2Subtask212WorkflowData()!=null) {
			//researchClinicalStudy = createIndiv("clinical_research_study_755523_subtask2.1.2", 
			//		model.getResource("http://medicis.univ-rennes1.fr/ontologies/ontospm/OntoMEDIRAD.owl#clinical_research_study_755523_subtask2.1.2"));
			//retrieveSubtastk212(nonDicomFileSetDescriptor);
		}
				
		if (nonDicomFileSetDescriptor.getCalibrationWorkflow()!=null) {
			CalibrationWorkflow calibrationWorkflow = nonDicomFileSetDescriptor.getCalibrationWorkflow();
			SPECTCTCalibrationWorkflow SPECTCTCalibrationWorkflow = calibrationWorkflow.getSPECTCTCalibrationWorkflow();
			retreiveSPECTCTCalibrationWorkflow(SPECTCTCalibrationWorkflow);
			PlanarCalibrationWorkflow planarCalibrationWorkflow = calibrationWorkflow.getPlanarCalibrationWorkflow();
			retreivePlanarCalibrationWorkflow(planarCalibrationWorkflow);
		}
		
		
		if (nonDicomFileSetDescriptor.getHybridDosimetryworkflow()!=null) {
			HybridDosimetryworkflow hybridDosimetryworkflow = nonDicomFileSetDescriptor.getHybridDosimetryworkflow();
		}
		
		if (nonDicomFileSetDescriptor.getTwoDimDosimetryworkflow()!=null) {
			TwoDimDosimetryworkflow twoDimDosimetryworkflow = nonDicomFileSetDescriptor.getTwoDimDosimetryworkflow();
		}
			
		if (nonDicomFileSetDescriptor.getThreeDimDosimetrySlide1Workflow()!=null) {
			logger.error("ThreeDimDosimetrySlide1Workflow : cool");
			ThreeDimDosimetrySlide1Workflow threeDimDosimetrySlide1Workflow = nonDicomFileSetDescriptor.getThreeDimDosimetrySlide1Workflow();
			retreiveThreeDimDosimetrySlide1Workflow(threeDimDosimetrySlide1Workflow);
		} 
		
		if (nonDicomFileSetDescriptor.getThreeDimDosimetrySlide2Workflow()!=null) {
			ThreeDimDosimetrySlide2Workflow threeDimDosimetrySlide2Workflow = nonDicomFileSetDescriptor.getThreeDimDosimetrySlide2Workflow();
			retreiveThreeDimDosimetrySlide2Workflow(threeDimDosimetrySlide2Workflow);
		}

		
		// to be completed when with other subtasks
	
	}
	
	public static void retreiveTwoDimDosimetryworkflow(TwoDimDosimetryworkflow twoDimDosimetryworkflow) {
		PlanarDataAcquisitionAndProcessingContainer planarDataAcquisitionAndProcessingContainer = twoDimDosimetryworkflow.getPlanarDataAcquisitionAndProcessingContainer();
		RadioBiologicalCalculation radioBiologicalCalculation = twoDimDosimetryworkflow.getRadioBiologicalCalculation();
		TwoDimDosimetryViaAbsorbedDoseRateCalculation twoDimDosimetryViaAbsorbedDoseRateCalculation = twoDimDosimetryworkflow.getTwoDimDosimetryViaAbsorbedDoseRateCalculation();
		TwoDimDosimetryViaTimeActivityCurveFit twoDimDosimetryViaTimeActivityCurveFit = twoDimDosimetryworkflow.getTwoDimDosimetryViaTimeActivityCurveFit();
	}
	
	public static void retreiveSPECTCTCalibrationWorkflow(SPECTCTCalibrationWorkflow SPECTCTCalibrationWorkflow) {
		if (SPECTCTCalibrationWorkflow.getCTNumberCalibrationWorkfow()!=null) {
			CTNumberCalibrationWorkfow CTNumberCalibrationWorkfow = SPECTCTCalibrationWorkflow.getCTNumberCalibrationWorkfow();
				CTAcqForCTNumberCalibrationCurve CTAcqForCTNumberCalibrationCurve = CTNumberCalibrationWorkfow.getCTAcqForCTNumberCalibrationCurve();
					DensityPhantom densityPhantomUsed = CTAcqForCTNumberCalibrationCurve.getDensityPhantomUsed();
						String densityPhantomName = densityPhantomUsed.getDensityPhantomName();
						String densityPhantomIdentifier = densityPhantomUsed.getDensityPhantomIdentifier();
					DICOMData CTReconProduced = CTAcqForCTNumberCalibrationCurve.getCTReconProduced();
						String CTReconProduced_DICOMSeriesUID = CTReconProduced.getDICOMSeriesUID();
						String CTReconProduced_DICOMStudyUID = CTReconProduced.getDICOMStudyUID();
				
				CalculationOfCTNumberCalibrationCurve CalculationOfCTNumberCalibrationCurve = CTNumberCalibrationWorkfow.getCalculationOfCTNumberCalibrationCurve();
					ProcessExecutionContext ProcessExecutionContext = CalculationOfCTNumberCalibrationCurve.getProcessExecutionContext();
						String DateTimeProcessStarted = ProcessExecutionContext.getDateTimeProcessStarted();
						String PerformingInstitution = ProcessExecutionContext.getPerformingInstitution();
					DICOMData CTReconUsed = CalculationOfCTNumberCalibrationCurve.getCTReconUsed();
						String CTReconUsed_DICOMSeriesUID = CTReconUsed.getDICOMSeriesUID();
						String CTReconUsed_DICOMStudyUID = CTReconUsed.getDICOMStudyUID();
					CTNumberCalibrationCurve CTNumberCalibrationCurveProduced = CalculationOfCTNumberCalibrationCurve.getCTNumberCalibrationCurveProduced();
						String referenceCalibrationDate = CTNumberCalibrationCurveProduced.getReferenceCalibrationDate();
						ElementsOfCTNumberCalibrationCurve elementsOfCTNumberCalibrationCurve = CTNumberCalibrationCurveProduced.getElementsOfCTNumberCalibrationCurve();
						referenceCalibrationDate = CTNumberCalibrationCurveProduced.getReferenceCalibrationDate();
						
		}
		
		SPECTAcqCTAcqAndReconstructionInCalibration SPECTAcqCTAcqAndReconstructionInCalibration = SPECTCTCalibrationWorkflow.getSPECTAcqCTAcqAndReconstructionInCalibration();
			NMPhantom phantomUsed = SPECTAcqCTAcqAndReconstructionInCalibration.getPhantomUsed();
				String NMPhantomName = phantomUsed.getNMPhantomName();
				String NMPhantomIdentifier = phantomUsed.getNMPhantomIdentifier();
				Iterator<HotInsert> HotInsertIterator = phantomUsed.getHotInsertContainer().getHotInsert().iterator();
				while (HotInsertIterator.hasNext()) {
					HotInsert HotInsert = HotInsertIterator.next();
					float volumeValue = HotInsert.getVolumeValue();
					VolumeUnit volumeUnit = HotInsert.getVolumeUnit();
					float preAdminActivityValue = HotInsert.getPreAdminActivityValue();
					String preAdminActivityTimestamp = HotInsert.getPreAdminActivityTimestamp();
					float postAdminActivityValue = HotInsert.getPostAdminActivityValue();
					String postAdminActivityTimestamp = HotInsert.getPostAdminActivityTimestamp();
					String HotInsertIdentifier = HotInsert.getHotInsertIdentifier();
					Isotope Isotope = HotInsert.getIsotope();
				}
				Iterator<ColdInsert> ColdInsertIterator = phantomUsed.getColdInsertContainer().getColdInsert().iterator();
				while (HotInsertIterator.hasNext()) {
					ColdInsert ColdInsert = ColdInsertIterator.next();
					float volumeValue = ColdInsert.getVolumeValue();
					VolumeUnit volumeUnit = ColdInsert.getVolumeUnit();
					String coldInsertIdentifier = ColdInsert.getColdInsertIdentifier();
				}
				Tank tank = phantomUsed.getTank();
					float volumeValue = tank.getVolumeValue();
					VolumeUnit volumeUnit = tank.getVolumeUnit();
					Isotope isotope = tank.getIsotope();
					PreAdminBackgroundActivity preAdminBackgroundActivity = tank.getPreAdminBackgroundActivity();
					float preAdminBackgroundActivityValue = tank.getPreAdminBackgroundActivityValue();
					String preAdminBackgroundActivityTimestamp = tank.getPreAdminBackgroundActivityTimestamp();
					PostAdminBackgroundActivity postAdminBackgroundActivity = tank.getPostAdminBackgroundActivity();
					float postAdminBackgroundActivityValue = tank.getPostAdminBackgroundActivityValue();
					String postAdminBackgroundActivityTimestamp = tank.getPostAdminBackgroundActivityTimestamp();
					String tankIdentifier = tank.getTankIdentifier();
			
			DICOMData NMTomoProduced = SPECTAcqCTAcqAndReconstructionInCalibration.getNMTomoProduced();
				String NMTomoProduced_DICOMSeriesUID = NMTomoProduced.getDICOMSeriesUID();
				String NMTomoProduced_DICOMStudyUID = NMTomoProduced.getDICOMStudyUID();
			
			DICOMData CTReconProduced = SPECTAcqCTAcqAndReconstructionInCalibration.getCTReconProduced();
				String CTReconProduced_DICOMSeriesUID = CTReconProduced .getDICOMSeriesUID();
				String CTReconProduced_DICOMStudyUID = CTReconProduced .getDICOMStudyUID();			
		
		SPECTReconstructionInCalibration SPECTReconstructionInCalibration = SPECTCTCalibrationWorkflow.getSPECTReconstructionInCalibration();
			ProcessExecutionContext processExecutionContext = SPECTReconstructionInCalibration.getProcessExecutionContext();
				String performingInstitution = processExecutionContext.getPerformingInstitution();
				String dateTimeProcessStarted = processExecutionContext.getDateTimeProcessStarted();
			DICOMData NMTomoUsed = SPECTReconstructionInCalibration.getNMTomoUsed();
				String NMTomoUsed_DICOMSeriesUID = NMTomoUsed.getDICOMSeriesUID();
				String NMTomoUsed_DICOMStudyUID = NMTomoUsed.getDICOMStudyUID();
			DICOMData CTReconUsed = SPECTReconstructionInCalibration.getCTReconUsed();
				String CTReconUsed_DICOMSeriesUID = CTReconUsed.getDICOMSeriesUID();
				String CTReconUsed_DICOMStudyUID = CTReconUsed.getDICOMStudyUID();
			String ReconstructionMethodAndCorrectionsUsed = SPECTReconstructionInCalibration.getReconstructionMethodAndCorrectionsUsed();
			CTNumberCalibrationCurve CTNumberCalibrationCurveUsed = SPECTReconstructionInCalibration.getCTNumberCalibrationCurveUsed();
				String ReferenceCalibrationDate = CTNumberCalibrationCurveUsed.getReferenceCalibrationDate();
				
			CTNumberCalibrationCurveReference CTNumberCalibrationCurveReference = SPECTReconstructionInCalibration.getCTNumberCalibrationCurveReference();
			DICOMData NMTomoReconProduced = SPECTReconstructionInCalibration.getNMTomoReconProduced();
			
			
		CTSegmentationInCalibration CTSegmentationInCalibration = SPECTCTCalibrationWorkflow.getCTSegmentationInCalibration();
		SPECTSensitivityCalculation SPECTSensitivityCalculation = SPECTCTCalibrationWorkflow.getSPECTSensitivityCalculation();
		
		if (SPECTCTCalibrationWorkflow.getSPECTRecoveryCoefficientCurveCalculation()!=null) {
			SPECTRecoveryCoefficientCurveCalculation SPECTRecoveryCoefficientCurveCalculation = SPECTCTCalibrationWorkflow.getSPECTRecoveryCoefficientCurveCalculation();
		}
	}
	
	public static void retreivePlanarCalibrationWorkflow(PlanarCalibrationWorkflow planarCalibrationWorkflow) {
		
	}

	public static void retreiveThreeDimDosimetrySlide1Workflow(ThreeDimDosimetrySlide1Workflow threeDimDosimetrySlide1Workflow) {
		System.out.println("retreiveThreeDimDosimetrySlide1Workflow");
		SPECTDataAcquisitionAndReconstruction SPECTDataAcquisitionAndReconstruction = threeDimDosimetrySlide1Workflow.getSPECTDataAcquisitionAndReconstruction();
		
		Iterator<SPECTAcqCTAcqAndReconstruction> SPECTAcqCTAcqAndReconstructionIterator = SPECTDataAcquisitionAndReconstruction.getSPECTAcqCTAcqAndReconstructionContainer().getSPECTAcqCTAcqAndReconstruction().iterator();
		SPECTAcqCTAcqAndReconstruction SPECTAcqCTAcqAndReconstruction; 
		while (SPECTAcqCTAcqAndReconstructionIterator.hasNext()) {
			SPECTAcqCTAcqAndReconstruction = SPECTAcqCTAcqAndReconstructionIterator.next();
			Individual reconstruction = createIndiv(generateName("SPECT_data_reconstruction"), model.getResource(racineURI+"SPECT_data_reconstruction"));
			
			Iterator<TimePointDescriptionElement> timePointDescriptionIterator = SPECTAcqCTAcqAndReconstruction.getTimePointDescription().getTimePointDescriptionElement().iterator();
			while (timePointDescriptionIterator.hasNext()) {
				TimePointDescriptionElement TimePointDescriptionElement = timePointDescriptionIterator.next();
				
				Individual timePoint;
				switch (TimePointDescriptionElement.getTimePointCategory()) {
				case "168h plus or minus 24h post RAIT timepoint":
					timePoint=createIndiv(generateName("168h_plus_or_minus_24h_post_RAIT_timepoint"), model.getResource(racineURI+"168h_plus_or_minus_24h_post_RAIT_timepoint"));
					break;
				case "24h plus or minus 4h post RAIT timepoint":
					timePoint=createIndiv(generateName("24h_plus_or_minus_4h_post_RAIT_timepoint"), model.getResource(racineURI+"24h_plus_or_minus_4h_post_RAIT_timepoint"));
					break;
				case "48h plus or minus 4h post RAIT timepoint":
					timePoint=createIndiv(generateName("48h_plus_or_minus_4h_post_RAIT_timepoint"), model.getResource(racineURI+"48h_plus_or_minus_4h_post_RAIT_timepoint"));
					break;
				case "6h plus or minus 2h post RAIT timepoint":
					timePoint=createIndiv(generateName("6h_plus_or_minus_2h_post_RAIT_timepoint"), model.getResource(racineURI+"6h_plus_or_minus_2h_post_RAIT_timepoint"));
					break;
				case "72h plus or minus 12h post RAIT timepoint":
					timePoint=createIndiv(generateName("72h_plus_or_minus_12h_post_RAIT_timepoint"), model.getResource(racineURI+"72h_plus_or_minus_12h_post_RAIT_timepoint"));
					break;
				case "96h plus or minus 12h post RAIT timepoint":
					timePoint=createIndiv(generateName("96h_plus_or_minus_12h_post_RAIT_timepoint"), model.getResource(racineURI+"96h_plus_or_minus_12h_post_RAIT_timepoint"));
					break;
				default:
					timePoint=createIndiv(generateName("timepoint_description"), model.getResource(racineURI+"timepoint_description"));
					break;
				}
				addObjectProperty(timePoint, racineURI+"is_about", reconstruction);
				
				String TimePointIdentifier = TimePointDescriptionElement.getTimePointIdentifier();
				addDataProperty(timePoint,racineURI+"has_id", TimePointIdentifier);
				tableTimePoint.put(TimePointIdentifier, timePoint);
				BigInteger TimePointDistanceFromReferenceEventValue = TimePointDescriptionElement.getTimePointDistanceFromReferenceEventValue();
				switch(TimePointDescriptionElement.getTimeUnit())  {
				case DAYS:
					addDataProperty(timePoint,racineURI+"days", TimePointDistanceFromReferenceEventValue.intValue());
					break;
				case HOURS:
					addDataProperty(timePoint,racineURI+"hours", TimePointDistanceFromReferenceEventValue.intValue());
					break;
				case MINUTES:
					addDataProperty(timePoint,racineURI+"minutes", TimePointDistanceFromReferenceEventValue.intValue());
					break;
				case MONTHS:
					addDataProperty(timePoint,racineURI+"months", TimePointDistanceFromReferenceEventValue.intValue());
					break;
				case SECONDS:
					addDataProperty(timePoint,racineURI+"seconds", TimePointDistanceFromReferenceEventValue.intValue());
					break;
				case YEARS:
					addDataProperty(timePoint,racineURI+"years", TimePointDistanceFromReferenceEventValue.intValue());
					break;
				}
			}
			
			NMRelevantCalibrationReference NMRelevantCalibrationReference = SPECTAcqCTAcqAndReconstruction.getNMRelevantCalibrationReference();
			String NMreferenceCalibrationDate = NMRelevantCalibrationReference.getReferenceCalibrationDate();
			Isotope Isotope = NMRelevantCalibrationReference.getIsotope();
			// TODO
			
			String CTreferenceCalibrationDate = SPECTAcqCTAcqAndReconstruction.getCTRelevantCalibrationReference().getReferenceCalibrationDate();
			// TODO
			
			DICOMData NMTomoProduced = SPECTAcqCTAcqAndReconstruction.getNMTomoProduced();
			String studyId = NMTomoProduced.getDICOMStudyUID();
			String seriesId = NMTomoProduced.getDICOMSeriesUID();
			if (hasDicomInputOutput(studyId, seriesId, typeInputOutput.NMTomo) != null) {
				Individual NMtomo = getDicomInputOutput(studyId, seriesId, typeInputOutput.NMTomo);
				addObjectProperty(reconstruction, racineURI+"has_specified_output", NMtomo);
			} else {
				Individual NMtomo = createIndiv(generateName("NM_tomo_dataset"), model.getResource(racineURI+"NM_tomo_dataset"));
				addDataProperty(NMtomo, racineURI+"has_DICOM_series_instance_UID", NMTomoProduced.getDICOMSeriesUID());
				addDataProperty(NMtomo, racineURI+"has_DICOM_study_instance_UID", NMTomoProduced.getDICOMStudyUID());
				addObjectProperty(reconstruction, racineURI+"has_specified_output", NMtomo);
				setDicomInputOutput(studyId, seriesId, typeInputOutput.NMTomo, NMtomo);
			}
			
			DICOMData CTReconProduced = SPECTAcqCTAcqAndReconstruction.getCTReconProduced();
			studyId = CTReconProduced.getDICOMStudyUID();
			seriesId = CTReconProduced.getDICOMSeriesUID();
			if (hasDicomInputOutput(studyId, seriesId, typeInputOutput.CTRecon) != null) {
				Individual CTrecon = getDicomInputOutput(studyId, seriesId, typeInputOutput.CTRecon);
				addObjectProperty(reconstruction, racineURI+"has_specified_output", CTrecon);
			} else {
				Individual CTrecon = createIndiv(generateName("CT_image_reconstruction"), model.getResource(racineURI+"CT_image_reconstruction"));
				addDataProperty(CTrecon, racineURI+"has_DICOM_series_instance_UID", CTReconProduced.getDICOMSeriesUID());
				addDataProperty(CTrecon, racineURI+"has_DICOM_study_instance_UID", CTReconProduced.getDICOMStudyUID());
				addObjectProperty(reconstruction, racineURI+"has_specified_output", CTrecon);
				setDicomInputOutput(studyId, seriesId, typeInputOutput.CTRecon, CTrecon);
			}
		}
		
		if (SPECTDataAcquisitionAndReconstruction.getSPECTReconstructionContainer() != null) {
			Iterator<SPECTReconstruction> SPECTDataAcquisitionAndReconstructionIterator = SPECTDataAcquisitionAndReconstruction.getSPECTReconstructionContainer().getSPECTReconstruction().iterator();
			while (SPECTDataAcquisitionAndReconstructionIterator.hasNext()) {
				
				SPECTReconstruction SPECTDataAcquisitionAndReconstruction1 = SPECTDataAcquisitionAndReconstructionIterator.next();
				Individual spectAcquisition = createIndiv(generateName("SPECT_data_acquisition"), model.getResource(racineURI+"SPECT_data_acquisition"));
				Individual spectReconstruction = createIndiv(generateName("SPECT_data_reconstruction"), model.getResource(racineURI+"SPECT_data_reconstructionn"));
				
				ProcessExecutionContext processExecutionContext = SPECTDataAcquisitionAndReconstruction1.getProcessExecutionContext();
				String dateTimeProcessStarted = processExecutionContext.getDateTimeProcessStarted();
				addDataProperty(spectAcquisition, racineURI+"has_beginning_date", dateTimeProcessStarted);
				addDataProperty(spectReconstruction, racineURI+"has_beginning_date", dateTimeProcessStarted);
				String performingInstitution = processExecutionContext.getPerformingInstitution();
				Individual institution = memory.getInstitution(performingInstitution);
				addObjectProperty(spectAcquisition, racineObo+"BFO_0000054",memory.getRoleOfResponsibleOrganization(performingInstitution));
				
				String timePointIdentifierUsed = SPECTDataAcquisitionAndReconstruction1.getTimePointIdentifierUsed();
				Individual timePoint = tableTimePoint.get(timePointIdentifierUsed);
				addObjectProperty(spectAcquisition, racineURI+"is_quantity_measured_at", timePoint);
				addObjectProperty(spectReconstruction, racineURI+"is_about", timePoint);

				DICOMData NMTomoUsed = SPECTDataAcquisitionAndReconstruction1.getNMTomoUsed();
				String seriesId = NMTomoUsed.getDICOMSeriesUID();
				String studyId = NMTomoUsed.getDICOMStudyUID();
				if (hasDicomInputOutput(studyId, seriesId, typeInputOutput.NMTomo) != null) {
					Individual NMtomo = getDicomInputOutput(studyId, seriesId, typeInputOutput.NMTomo);
					addObjectProperty(spectAcquisition, racineURI+"has_specified_output", NMtomo);
					addObjectProperty(spectReconstruction, racineURI+"has_specified_output", NMtomo);
				} else {
					Individual NMtomo = createIndiv(generateName("NM_tomo_dataset"), model.getResource(racineURI+"NM_tomo_dataset"));
					addDataProperty(NMtomo, racineURI+"has_DICOM_series_instance_UID", seriesId);
					addDataProperty(NMtomo, racineURI+"has_DICOM_study_instance_UID", studyId);
					addObjectProperty(spectReconstruction, racineURI+"has_specified_output", NMtomo);
					addObjectProperty(spectAcquisition, racineURI+"has_specified_output", NMtomo);
					setDicomInputOutput(studyId, seriesId, typeInputOutput.NMTomo, NMtomo);
				}
				
				DICOMData CTReconUsed = SPECTDataAcquisitionAndReconstruction1.getCTReconUsed();
				seriesId = CTReconUsed.getDICOMSeriesUID();
				studyId = CTReconUsed.getDICOMStudyUID();
				if (hasDicomInputOutput(studyId, seriesId, typeInputOutput.CTRecon) != null) {
					Individual NMtomo = getDicomInputOutput(studyId, seriesId, typeInputOutput.CTRecon);
					addObjectProperty(spectAcquisition, racineURI+"has_specified_output", NMtomo);
					addObjectProperty(spectReconstruction, racineURI+"has_specified_output", NMtomo);
				} else {
					Individual NMtomo = createIndiv(generateName("CT_image_reconstruction"), model.getResource(racineURI+"CT_image_reconstruction"));
					addDataProperty(NMtomo, racineURI+"has_DICOM_series_instance_UID", seriesId);
					addDataProperty(NMtomo, racineURI+"has_DICOM_study_instance_UID", studyId);
					addObjectProperty(spectReconstruction, racineURI+"has_specified_output", NMtomo);
					addObjectProperty(spectAcquisition, racineURI+"has_specified_output", NMtomo);
					setDicomInputOutput(studyId, seriesId, typeInputOutput.CTRecon, NMtomo);
				}
				
				String ReconstructionMethodAndCorrectionsUsed = SPECTDataAcquisitionAndReconstruction1.getReconstructionMethodAndCorrectionsUsed();
				addDataProperty(spectAcquisition, racineURI+"has_method", ReconstructionMethodAndCorrectionsUsed);
				
				if (SPECTDataAcquisitionAndReconstruction1.getCTNumberCalibrationCurveUsed() != null) {
					CTNumberCalibrationCurve CTNumberCalibrationCurveUsed = SPECTDataAcquisitionAndReconstruction1.getCTNumberCalibrationCurveUsed();
					Individual CTNumberCalibrationCurve = createIndiv(generateName("CT_number_calibration_curve"), model.getResource(racineURI+"CT_number_calibration_curve"));
					
					Iterator<ElementOfCTNumberCalibrationCurve> ElementOfCTNumberCalibrationCurveIterator = CTNumberCalibrationCurveUsed.getElementsOfCTNumberCalibrationCurve().getElementOfCTNumberCalibrationCurve().iterator();
					while (ElementOfCTNumberCalibrationCurveIterator.hasNext()) {
						ElementOfCTNumberCalibrationCurve ElementOfCTNumberCalibrationCurve = ElementOfCTNumberCalibrationCurveIterator.next();
						// TODO créer individi
						BigInteger hounsfieldMeasuredValue = ElementOfCTNumberCalibrationCurve.getHounsfieldMeasuredValue();
						float realDensityOfMaterialValue = ElementOfCTNumberCalibrationCurve.getRealDensityOfMaterialValue();
						RealDensityOfMaterialUnit realDensityOfMaterialUnit = ElementOfCTNumberCalibrationCurve.getRealDensityOfMaterialUnit();
						String materialIdentifier = ElementOfCTNumberCalibrationCurve.getMaterialIdentifier();
						
					}
					String referenceCalibrationDate = CTNumberCalibrationCurveUsed.getReferenceCalibrationDate();
					addDataProperty(CTNumberCalibrationCurve, racineURI+"has_date", referenceCalibrationDate);
				}
				
				if (SPECTDataAcquisitionAndReconstruction1.getCTNumberCalibrationCurveReference()!=null) {
					CTNumberCalibrationCurveReference CTNumberCalibrationCurveReference = SPECTDataAcquisitionAndReconstruction1.getCTNumberCalibrationCurveReference();
					String referenceCalibrationDate = CTNumberCalibrationCurveReference.getReferenceCalibrationDate();
				}
				
				DICOMData NMTomoReconProduced = SPECTDataAcquisitionAndReconstruction1.getNMTomoReconProduced();
				Individual NMreconTomoDataset = createIndiv(generateName("NM_recon_tomo_dataset"), model.getResource(racineURI+"NM_recon_tomo_dataset"));
				addObjectProperty(spectReconstruction, racineURI+"has_specified_output", NMreconTomoDataset);
				addDataProperty(spectReconstruction, racineURI+"has_DICOM_series_instance_UID", NMTomoReconProduced.getDICOMSeriesUID());
				addDataProperty(spectReconstruction, racineURI+"has_DICOM_study_instance_UID", NMTomoReconProduced.getDICOMStudyUID());
				setDicomInputOutput(NMTomoReconProduced.getDICOMStudyUID(), NMTomoReconProduced.getDICOMSeriesUID(), typeInputOutput.NMTomo, NMreconTomoDataset);
			}
		}
		

		RegistrationVOISegmentationAndPropagationContainer RegistrationVOISegmentationAndPropagationContainer = threeDimDosimetrySlide1Workflow.getRegistrationVOISegmentationAndPropagationContainer();
		Iterator<RegistrationVOISegmentationAndPropagation> RegistrationVOISegmentationAndPropagationIterator = RegistrationVOISegmentationAndPropagationContainer.getRegistrationVOISegmentationAndPropagation().iterator();
		
		while (RegistrationVOISegmentationAndPropagationIterator.hasNext()) {
			RegistrationVOISegmentationAndPropagation registrationVOISegmentationAndPropagation = RegistrationVOISegmentationAndPropagationIterator.next();
			Individual registration = createIndiv(generateName("registration_VOI_segmentation_and_propagation"), model.getResource(racineURI+"registration_VOI_segmentation_and_propagation"));
			ProcessExecutionContext processExecutionContext = registrationVOISegmentationAndPropagation.getProcessExecutionContext();
			addDataProperty(registration, racineURI+"has_beginning_date" ,processExecutionContext.getDateTimeProcessStarted());
			//Individual institution = memory.getInstitution(processExecutionContext.getPerformingInstitution());
			addObjectProperty(registration, racineObo+"BFO_0000054",memory.getRoleOfResponsibleOrganization(processExecutionContext.getPerformingInstitution()));
		
			Individual timePointUsed = tableTimePoint.get(registrationVOISegmentationAndPropagation.getTimePointIdentifierUsed());
			addObjectProperty(registration, racineURI+"is_about", timePointUsed); 
			
			if (registrationVOISegmentationAndPropagation.getCountsPerVOIAtTimePointProducedContainer()!=null) {
				Iterator<CountsPerVOIAtTimePoint> countsPerVOIAtTimePointProducedIterator = registrationVOISegmentationAndPropagation.getCountsPerVOIAtTimePointProducedContainer().getCountsPerVOIAtTimePointProduced().iterator();
				while (countsPerVOIAtTimePointProducedIterator.hasNext()) {
					CountsPerVOIAtTimePoint countsPerVOIAtTimePointProduced = countsPerVOIAtTimePointProducedIterator.next();
					Individual count = createIndiv(generateName("counts_per_VOI_at_timepoint"), model.getResource(racineURI+"counts_per_VOI_at_timepoint"));
					
					addDataProperty(count, racineURI+"has_measurement_value", countsPerVOIAtTimePointProduced.getCountsValue());
					CountsUnit unit = countsPerVOIAtTimePointProduced.getCountsUnit();
					switch (countsPerVOIAtTimePointProduced.getCountsUnit()) {
					case COUNTS:
						addObjectProperty(count, "has_measurement_unit_label", getUnit("counts"));
						break;
					}
	
					Individual voi = tableVOI.get(countsPerVOIAtTimePointProduced.getVOIIdentifier().toString());
					addObjectProperty(count,  racineURI+"is_quantity_measured_in", voi);
					
					String timePointId = countsPerVOIAtTimePointProduced.getTimePointIdentifier();
					Individual timePoint = tableTimePoint.get(timePointId);
					addObjectProperty(count,  racineURI+"is_quantity_measured_at", timePoint);						
				}
			}
			
			Iterator<DICOMData> NMTomoReconUsedIterator = registrationVOISegmentationAndPropagation.getNMTomoReconUsed().getDICOMData().iterator();
			while (NMTomoReconUsedIterator.hasNext()) {
				DICOMData NMTomoReconUsed = NMTomoReconUsedIterator.next();
				String DICOMSeriesUID = NMTomoReconUsed.getDICOMSeriesUID();
				String DICOMStudyUID = NMTomoReconUsed.getDICOMStudyUID();
				if (hasDicomInputOutput(DICOMStudyUID, DICOMSeriesUID, typeInputOutput.NMTomo) != null) {
					Individual NMtomo = getDicomInputOutput(DICOMStudyUID, DICOMSeriesUID, typeInputOutput.NMTomo);
					addObjectProperty(registration, racineURI+"has_specified_input", NMtomo);
				} else {
					Individual NMtomo = createIndiv(generateName("NM_tomo_dataset"), model.getResource(racineURI+"NM_tomo_dataset"));
					addDataProperty(NMtomo, racineURI+"has_DICOM_series_instance_UID", DICOMSeriesUID);
					addDataProperty(NMtomo, racineURI+"has_DICOM_study_instance_UID", DICOMStudyUID);
					addObjectProperty(registration, racineURI+"has_specified_input", NMtomo);
					setDicomInputOutput(DICOMStudyUID, DICOMSeriesUID, typeInputOutput.NMTomo, NMtomo);
				}
			}
			
			Iterator<DICOMData> CTReconUsedIterator = registrationVOISegmentationAndPropagation.getCTReconUsed().getDICOMData().iterator();
			while (CTReconUsedIterator.hasNext()) {
				DICOMData CTReconUsed = CTReconUsedIterator.next();
				String DICOMSeriesUID = CTReconUsed.getDICOMSeriesUID();
				String DICOMStudyUID = CTReconUsed.getDICOMStudyUID();
				if (hasDicomInputOutput(DICOMStudyUID, DICOMSeriesUID, typeInputOutput.CTRecon) != null) {
					Individual CTRecon = getDicomInputOutput(DICOMStudyUID, DICOMSeriesUID, typeInputOutput.CTRecon);
					addObjectProperty(registration, racineURI+"has_specified_input", CTRecon);
				} else {
					Individual CTRecon = createIndiv(generateName("CT_image_reconstruction"), model.getResource(racineURI+"CT_image_reconstruction"));
					addDataProperty(CTRecon, racineURI+"has_DICOM_series_instance_UID", DICOMSeriesUID);
					addDataProperty(CTRecon, racineURI+"has_DICOM_study_instance_UID", DICOMStudyUID);
					addObjectProperty(registration, racineURI+"has_specified_input", CTRecon);
					setDicomInputOutput(DICOMStudyUID, DICOMSeriesUID, typeInputOutput.CTRecon, CTRecon);
				}
			}
			
			String imageProcessingMethodMethodUse = registrationVOISegmentationAndPropagation.getImageProcessingMethodMethodUsed();
			addDataProperty(registration, racineURI+"has_method", imageProcessingMethodMethodUse);
			
			/////////
			Segmentation segmentation = registrationVOISegmentationAndPropagation.getSegmentation();
			Individual imageSegmentation = createIndiv(generateName("image_segmentation"), model.getResource(racineURI+"image_segmentation"));
			addDataProperty(imageSegmentation, racineURI+"has_id", segmentation.getSegmentationIdentifier().toString());
			tableSegmentation.put(segmentation.getSegmentationIdentifier().toString(), imageSegmentation);
			Iterator<VOI> VOIProducedIterator = segmentation.getVOIContainer().getVOIProduced().iterator();
			while (VOIProducedIterator.hasNext()) {
				VOI VOIProduced = VOIProducedIterator.next();
				Individual voi = createIndiv(generateName("VOI"), model.getResource(racineURI+"VOI"));
				String VOIIdentifier = VOIProduced.getVOIIdentifier().toString();
				tableVOI.put(VOIIdentifier, voi);
				addDataProperty(voi, racineURI+"has_id", VOIIdentifier);
				Individual organ = getOrganOrTissue(VOIProduced.getOrganOrTissue());
				addObjectProperty(organ,racineURI+"part_of_continuant_at_some_time", patient);
				addObjectProperty(voi,racineURI+"represents", organ);
				
				if (VOIProduced.getDICOMDataContainer()!=null) {
					Iterator<DICOMData> VOIiterator = VOIProduced.getDICOMDataContainer().getDICOMData().iterator();
					while (VOIiterator.hasNext())  {
						DICOMData VOIdata = VOIiterator.next();
						addDataProperty(voi, racineURI+"has_DICOM_series_instance_UID", VOIdata.getDICOMSeriesUID());
						addDataProperty(voi, racineURI+"has_DICOM_study_instance_UID", VOIdata.getDICOMStudyUID());
					}
				}
				
				if (VOIProduced.getNonDICOMDataContainer()!=null) {
					Iterator<NonDICOMData> VOIiterator = VOIProduced.getNonDICOMDataContainer().getNonDICOMData().iterator();
					while (VOIiterator.hasNext()) {
						NonDICOMData VOI = VOIiterator.next();
						translateNonDicomDataObject(VOI, voi);						
					}
				}
				
				Individual timePoint = tableTimePoint.get(VOIProduced.getTimePointIdentifier());
				addObjectProperty(timePoint, racineURI+"is_about", voi); 
				
				OrganMass organMass = VOIProduced.getOrganMass();
				Individual mass = createIndiv(generateName("mass"), model.getResource(racineObo+"PATO_0000125"));
				addDataProperty(mass, racineURI+"has_measurement_value", organMass.getOrganMassValue());
				switch (organMass.getOrganMassUnit()) {
				case GRAM:
					addObjectProperty(mass, racineURI+"has_measurement_unit_label", getUnit("gram"));
					break;
				case KILOGRAM:
					addObjectProperty(mass, racineURI+"has_measurement_unit_label", getUnit("kilogram"));
					break;
				}
				addObjectProperty(mass, racineURI+"is_About", organ);
			}
			
			if (segmentation.getDICOMDataContainer()!=null) {
				Iterator<DICOMData> DICOMDataIterator = segmentation.getDICOMDataContainer().getDICOMData().iterator();
				while (DICOMDataIterator.hasNext()) {
					DICOMData DICOMData = DICOMDataIterator.next();
					addDataProperty(imageSegmentation, racineURI+"has_DICOM_series_instance_UID", DICOMData.getDICOMSeriesUID());
					addDataProperty(imageSegmentation, racineURI+"has_DICOM_study_instance_UID", DICOMData.getDICOMStudyUID());
				}
			}
			if (segmentation.getNonDICOMDataContainer()!=null) {
				Iterator<NonDICOMData> NonDICOMDataIterator = segmentation.getNonDICOMDataContainer().getNonDICOMData().iterator();
				while (NonDICOMDataIterator.hasNext()) {
					NonDICOMData nonDICOMData = NonDICOMDataIterator.next();
					translateNonDicomDataObject(nonDICOMData, imageSegmentation);
				}
			}
			///////
		
	
			if (registrationVOISegmentationAndPropagation.getCTReconResampledOnCommonReferenceProduced() != null) {
				Iterator<DICOMData> CTReconResampledOnCommonReferenceProducedIterator = registrationVOISegmentationAndPropagation.getCTReconResampledOnCommonReferenceProduced().getDICOMData().iterator();
				while (CTReconResampledOnCommonReferenceProducedIterator.hasNext()) {
					DICOMData CTReconResampledOnCommonReferenceProduced = CTReconResampledOnCommonReferenceProducedIterator.next();
					Individual CTReconResampled;
					String studyID = CTReconResampledOnCommonReferenceProduced.getDICOMStudyUID();
					String seriesID = CTReconResampledOnCommonReferenceProduced.getDICOMSeriesUID();
					if (hasDicomInputOutput(studyID, seriesID, typeInputOutput.CTRecon)) {
						CTReconResampled = getDicomInputOutput(studyID, seriesID, typeInputOutput.CTRecon);
					} else { 
						CTReconResampled = createIndiv(generateName("CT_image_reconstruction"), model.getResource(racineURI+"CT_image_reconstruction"));
						setDicomInputOutput(studyID, seriesID, typeInputOutput.CTRecon, CTReconResampled);
						addDataProperty(CTReconResampled, racineURI+"has_DICOM_series_instance_UID", seriesID);
						addDataProperty(CTReconResampled, racineURI+"has_DICOM_study_instance_UID", studyID);
					}
					addObjectProperty(registration, racineURI+"has_specified_output", CTReconResampled);
				}
			}
			
			if (registrationVOISegmentationAndPropagation.getNMTomoReconResampledOnCommonReferenceProduced() != null) {
				Iterator<DICOMData> DICOMDataIterator = registrationVOISegmentationAndPropagation.getNMTomoReconResampledOnCommonReferenceProduced().getDICOMData().iterator();
				while (DICOMDataIterator.hasNext()) {
					DICOMData DICOMData = DICOMDataIterator.next();
					String studyID = DICOMData.getDICOMStudyUID();
					String seriesID = DICOMData.getDICOMSeriesUID();
					Individual NMTomoRecon;
					if (hasDicomInputOutput(studyID, seriesID, typeInputOutput.NMTomo)) {
						NMTomoRecon = getDicomInputOutput(studyID, seriesID, typeInputOutput.NMTomo);
					} else { 
						NMTomoRecon = createIndiv(generateName("NM_recon_tomo_dataset"), model.getResource(racineURI+"NM_recon_tomo_dataset"));
						setDicomInputOutput(studyID, seriesID, typeInputOutput.NMTomo, NMTomoRecon);
						addDataProperty(NMTomoRecon, racineURI+"has_DICOM_series_instance_UID", seriesID);
						addDataProperty(NMTomoRecon, racineURI+"has_DICOM_study_instance_UID", studyID);
					}
					addObjectProperty(registration, racineURI+"has_specified_output", NMTomoRecon);
				}
			}

			if (registrationVOISegmentationAndPropagation.getNonDICOMCTReconResampledOnCommonReferenceProduced() != null) {
				Iterator<NonDICOMData> nonDICOMDataIterator = registrationVOISegmentationAndPropagation.getNonDICOMCTReconResampledOnCommonReferenceProduced().getNonDICOMData().iterator();
				while (nonDICOMDataIterator.hasNext()) {
					NonDICOMData nonDICOMData = nonDICOMDataIterator.next();
					String nonDICOMDataClass = nonDICOMData.getNonDICOMDataClass();
					NonDICOMDataFormat nonDICOMDataClassFormat = nonDICOMData.getNonDICOMDataFormat();
					String nonDICOMDataFHIRId = nonDICOMData.getFHIRIdentifier();
					Individual nonDICOMCTReconResampled;
					if (hasNonDicomInputOutput(nonDICOMDataFHIRId, nonDICOMDataClass, nonDICOMDataClassFormat)) {
						nonDICOMCTReconResampled = getNonDicomInputOutput(nonDICOMDataFHIRId, nonDICOMDataClass, nonDICOMDataClassFormat);
					} else {
						nonDICOMCTReconResampled = createIndiv(generateName("CT_image_reconstruction"), model.getResource(racineURI+"CT_image_reconstruction"));
						setNonDicomInputOutput(nonDICOMData.getFHIRIdentifier(), nonDICOMData.getNonDICOMDataClass(), nonDICOMCTReconResampled, nonDICOMData.getNonDICOMDataFormat());
						translateNonDicomDataObject(nonDICOMData, nonDICOMCTReconResampled);
					}
					addObjectProperty(registration, racineURI+"has_specified_output", nonDICOMCTReconResampled);
				}
				
				if (registrationVOISegmentationAndPropagation.getNonDICOMNMTomoReconResampledOnCommonReferenceProduced() != null) {
					nonDICOMDataIterator = registrationVOISegmentationAndPropagation.getNonDICOMNMTomoReconResampledOnCommonReferenceProduced().getNonDICOMData().iterator();
					while (nonDICOMDataIterator.hasNext()) {
						NonDICOMData nonDICOMData = nonDICOMDataIterator.next();
						Individual nonDICOMNMTomoRecon;
						String nonDICOMDataClass = nonDICOMData.getNonDICOMDataClass();
						NonDICOMDataFormat nonDICOMDataClassFormat = nonDICOMData.getNonDICOMDataFormat();
						String nonDICOMDataFHIRId = nonDICOMData.getFHIRIdentifier();
						if (hasNonDicomInputOutput(nonDICOMDataFHIRId, nonDICOMDataClass, nonDICOMDataClassFormat)) {
							nonDICOMNMTomoRecon = getNonDicomInputOutput(nonDICOMDataFHIRId, nonDICOMDataClass, nonDICOMDataClassFormat);
						} else {
							nonDICOMNMTomoRecon = createIndiv(generateName("NM_recon_tomo_dataset"), model.getResource(racineURI+"NM_recon_tomo_dataset"));
							translateNonDicomDataObject(nonDICOMData, nonDICOMNMTomoRecon);
							setNonDicomInputOutput(nonDICOMData.getFHIRIdentifier(), nonDICOMData.getNonDICOMDataClass(), nonDICOMNMTomoRecon, nonDICOMData.getNonDICOMDataFormat());
						}
						addObjectProperty(registration, racineURI+"has_specified_output", nonDICOMNMTomoRecon);
					}
				}
				
				if (registrationVOISegmentationAndPropagation.getDensityImageProduced()!= null) {
					nonDICOMDataIterator = registrationVOISegmentationAndPropagation.getNonDICOMNMTomoReconResampledOnCommonReferenceProduced().getNonDICOMData().iterator();
					while (nonDICOMDataIterator.hasNext()) {
						NonDICOMData nonDICOMData = nonDICOMDataIterator.next();
						Individual densityImage;
						String nonDICOMDataClass = nonDICOMData.getNonDICOMDataClass();
						NonDICOMDataFormat nonDICOMDataClassFormat = nonDICOMData.getNonDICOMDataFormat();
						String nonDICOMDataFHIRId = nonDICOMData.getFHIRIdentifier();
						if (hasNonDicomInputOutput(nonDICOMDataFHIRId, nonDICOMDataClass, nonDICOMDataClassFormat)) {
							densityImage = getNonDicomInputOutput(nonDICOMDataFHIRId, nonDICOMDataClass, nonDICOMDataClassFormat);
						} else {
							densityImage = createIndiv(generateName("density_image"), model.getResource(racineURI+"density_image")); 
							translateNonDicomDataObject(nonDICOMData, densityImage);
							setNonDicomInputOutput(nonDICOMData.getFHIRIdentifier(), nonDICOMData.getNonDICOMDataClass(), densityImage, nonDICOMData.getNonDICOMDataFormat());
						}
						addObjectProperty(registration, racineURI+"has_specified_output", densityImage);
					}
				}
				
				if (registrationVOISegmentationAndPropagation.getRegistrationMatrixUsed() != null) {
					nonDICOMDataIterator = registrationVOISegmentationAndPropagation.getNonDICOMNMTomoReconResampledOnCommonReferenceProduced().getNonDICOMData().iterator();
					while (nonDICOMDataIterator.hasNext()) {
						NonDICOMData nonDICOMData = nonDICOMDataIterator.next();
						Individual registrationMatrix;
						String nonDICOMDataClass = nonDICOMData.getNonDICOMDataClass();
						NonDICOMDataFormat nonDICOMDataClassFormat = nonDICOMData.getNonDICOMDataFormat();
						String nonDICOMDataFHIRId = nonDICOMData.getFHIRIdentifier();
						if (hasNonDicomInputOutput(nonDICOMDataFHIRId, nonDICOMDataClass, nonDICOMDataClassFormat)) {
							registrationMatrix = getNonDicomInputOutput(nonDICOMDataFHIRId, nonDICOMDataClass, nonDICOMDataClassFormat);
						} else {
							registrationMatrix = createIndiv(generateName("registration_matrix"), model.getResource(racineURI+"registration_matrix")); //TODO class à adapter
							translateNonDicomDataObject(nonDICOMData, registrationMatrix);
							setNonDicomInputOutput(nonDICOMData.getFHIRIdentifier(), nonDICOMData.getNonDICOMDataClass(), registrationMatrix, nonDICOMData.getNonDICOMDataFormat());

						}
						addObjectProperty(registration, racineURI+"has_specified_output", registrationMatrix);
					}
				}
			}

			Iterator<VOIActivityDetermination> VOIActivityDeterminationIterator = threeDimDosimetrySlide1Workflow.getVOIActivityDeterminationContainer().getVOIActivityDetermination().iterator();
			while(VOIActivityDeterminationIterator.hasNext()) {
				VOIActivityDetermination VOIActivityDetermination = VOIActivityDeterminationIterator.next();
				
				processExecutionContext = VOIActivityDetermination.getProcessExecutionContext();
				String dateTimeProcessStarted = processExecutionContext.getDateTimeProcessStarted();
				//institution = memory.getInstitution(processExecutionContext.getPerformingInstitution());
				Individual roleInstitution = memory.getRoleOfResponsibleOrganization(processExecutionContext.getPerformingInstitution());
				Individual segmentation1 = tableSegmentation.get(VOIActivityDetermination.getSegmentationIdentifierUsed());
				Individual timePoint = tableTimePoint.get(VOIActivityDetermination.getTimePointIdentifierUsed());
				
				NMRelevantCalibrationReference SPECTCalibrationFactorReferenceUsed = VOIActivityDetermination.getSPECTCalibrationFactorReferenceUsed();
				Isotope isotope = SPECTCalibrationFactorReferenceUsed.getIsotope();
				String referenceCalibrationDate = SPECTCalibrationFactorReferenceUsed.getReferenceCalibrationDate();
				//TODO
				
				NMRelevantCalibrationReference SPECTRecoveryCoefficientCurveReference = VOIActivityDetermination.getSPECTRecoveryCoefficientCurveReferenceUsed();
				Isotope isotope2 = SPECTRecoveryCoefficientCurveReference.getIsotope();
				String referenceCalibrationDate2 = SPECTRecoveryCoefficientCurveReference.getReferenceCalibrationDate();
				// TODO
				
				Iterator<DataActivityPerVOIAtTimePoint> dataActivityPerVOIAtTimePointIterator = VOIActivityDetermination.getDataActivityPerVOIAtTimePointContainer().getDataActivityPerVOIAtTimePointProduced().iterator();
				while (dataActivityPerVOIAtTimePointIterator.hasNext()) {
					DataActivityPerVOIAtTimePoint dataActivityPerVOIAtTimePoint = dataActivityPerVOIAtTimePointIterator.next();
					Individual dataActivity = createIndiv(generateName("radiopharmaceutical_activity_per_VOI_at_timepoint"), model.getResource(racineURI+"radiopharmaceutical_activity_per_VOI_at_timepoint"));
					Individual timePoint1 = tableTimePoint.get(dataActivityPerVOIAtTimePoint.getTimePointIdentifier());
					Individual voi1 = tableVOI.get(dataActivityPerVOIAtTimePoint.getVOIIdentifier());
					addDataProperty(dataActivity, racineURI+"has_measurement_value", dataActivityPerVOIAtTimePoint.getDataActivityValue());
					ActivityUnit unit = dataActivityPerVOIAtTimePoint.getActivityUnit();
					addDataProperty(dataActivity, racineURI+"has_measurement_unit_label", unit.toString().toLowerCase());
					addObjectProperty(dataActivity, racineURI+"is_quantity_measured_in", voi1);
					addObjectProperty(dataActivity, racineURI+"is_quantity_measured_at", timePoint1);
				}
				
				Iterator<NonDICOMData> VoxelActivityMapProducedIterator = VOIActivityDetermination.getVoxelActivityMapProduced().getNonDICOMData().iterator();
				while (VoxelActivityMapProducedIterator.hasNext()) {
					NonDICOMData nonDICOMData = VoxelActivityMapProducedIterator.next();
					Individual voxelActivityMap = createIndiv(generateName("voxel_data_activity"), model.getResource(racineURI+"voxel_data_activity"));
					translateNonDicomDataObject(nonDICOMData,voxelActivityMap);
					setNonDicomInputOutput(nonDICOMData.getFHIRIdentifier(), nonDICOMData.getNonDICOMDataClass(), voxelActivityMap, nonDICOMData.getNonDICOMDataFormat());
				}
			}

			Iterator<TimeActivityCurveFitIn3DDosimetry> timeActivityCurveFitIn3DDosimetryIterator = threeDimDosimetrySlide1Workflow.getTimeActivityCurveFitIn3DDosimetryContainer().getTimeActivityCurveFitIn3DDosimetry().iterator();
			while (timeActivityCurveFitIn3DDosimetryIterator.hasNext()) {
				TimeActivityCurveFitIn3DDosimetry timeActivityCurveFitIn3DDosimetry = timeActivityCurveFitIn3DDosimetryIterator.next();
				Individual timeActivityCurveFit = createIndiv(generateName("time_activity_curve_fitting"), model.getResource(racineURI+"time_activity_curve_fitting"));
				
				processExecutionContext = timeActivityCurveFitIn3DDosimetry.getProcessExecutionContext();
				String dateTimeProcessStarted = processExecutionContext.getDateTimeProcessStarted();
				//institution = memory.getInstitution(processExecutionContext.getPerformingInstitution());
				Individual roleInstitution = memory.getRoleOfResponsibleOrganization(processExecutionContext.getPerformingInstitution());
				
				Individual voi;
				if (timeActivityCurveFitIn3DDosimetry.getVOIIdentifierUsed()!=null) {
					voi = tableVOI.get(timeActivityCurveFitIn3DDosimetry.getVOIIdentifierUsed());
					addObjectProperty(timeActivityCurveFit, racineURI+"has_specified_input", voi);
				} else {
					voi = null;
				}
				
				Individual segmentation1;
				if (timeActivityCurveFitIn3DDosimetry.getSegmentationIdentifier()!=null) {
					segmentation1 = tableSegmentation.get(timeActivityCurveFitIn3DDosimetry.getSegmentationIdentifier().toString());
					addObjectProperty(timeActivityCurveFit, racineURI+"has_specified_input", segmentation1);
				} else {
					segmentation1=null;
				}
				
				Individual timepoint;
				if (timeActivityCurveFitIn3DDosimetry.getTimePointIdentifierUsed()!=null) {
					timepoint = tableTimePoint.get(timeActivityCurveFitIn3DDosimetry.getTimePointIdentifierUsed());
					addObjectProperty(timeActivityCurveFit, racineURI+"is_about", segmentation1);
				} else {
					timepoint = null;
				}
				
				AdministeredActivity preAdministeredActivityUsed = timeActivityCurveFitIn3DDosimetry.getPreAdministeredActivityUsed();
				Individual preAdminActivity = createIndiv(generateName("pre_administration_measured_activity"), model.getResource(racineDCM+"113508"));
				addDataProperty(preAdminActivity, racineURI+"has_measurement_value", preAdministeredActivityUsed.getAdministeredActivityValue());
				ActivityUnit unit = preAdministeredActivityUsed.getActivityUnit();
				addObjectProperty(preAdminActivity, racineURI+"has_measurement_unit_label", getUnit(unit.toString().toLowerCase()));
				addObjectProperty(timeActivityCurveFit, racineURI+"has_specified_input", preAdminActivity);
				
				AdministeredActivity postAdministeredActivityUsed = timeActivityCurveFitIn3DDosimetry.getPostAdministeredActivityUsed();
				Individual postAdminActivity = createIndiv(generateName("post_administration_measured_activity"), model.getResource(racineDCM+"113509"));
				addDataProperty(postAdminActivity, racineURI+"has_measurement_value", preAdministeredActivityUsed.getAdministeredActivityValue());
				ActivityUnit unit1 = postAdministeredActivityUsed.getActivityUnit();
				addObjectProperty(postAdminActivity, racineURI+"has_measurement_unit_label", getUnit(unit1.toString().toLowerCase()));
				addObjectProperty(timeActivityCurveFit, racineURI+"has_specified_input", postAdminActivity);

				if (timeActivityCurveFitIn3DDosimetry.getPKAssessmentMethodUsed()!=null) { 
					addDataProperty(timeActivityCurveFit, racineURI+"has_method_name", timeActivityCurveFitIn3DDosimetry.getPKAssessmentMethodUsed().toString());

				}
				
				if (timeActivityCurveFitIn3DDosimetry.getTimeIntegratedActivityCoefficientPerVOIProduced()!=null) { 
					TimeIntegratedActivityCoefficientPerVOI timeIntegratedActivityCoefficientPerVOIProduced = timeActivityCurveFitIn3DDosimetry.getTimeIntegratedActivityCoefficientPerVOIProduced();
					Individual timeIntegratedActivityCoefficientPerVOI = createIndiv(generateName("time_integrated_activity_coefficient"), model.getResource(racineURI+"time_integrated_activity_coefficient"));
					TimeUnit unit2 = timeIntegratedActivityCoefficientPerVOIProduced.getTimeUnit();
					addDataProperty(timeIntegratedActivityCoefficientPerVOI, racineURI+unit2.toString().toLowerCase(), timeIntegratedActivityCoefficientPerVOIProduced.getTimeIntegratedActivityCoefficientPerVOIValue());
					voi = tableVOI.get(timeIntegratedActivityCoefficientPerVOIProduced.getVOIIdentifier().toString());
					addObjectProperty(timeIntegratedActivityCoefficientPerVOI, racineURI+"is_about", voi);
					addObjectProperty(timeActivityCurveFit, racineURI+"has_specified_output", timeIntegratedActivityCoefficientPerVOI);
				}
				
				TimeIntegratedActivityPerVOI timeIntegratedActivityPerVOIProduced = timeActivityCurveFitIn3DDosimetry.getTimeIntegratedActivityPerVOIProduced();
				Individual timeIntegratedActivityPerVOI = createIndiv(generateName("cumulated_activity"), model.getResource(racineURI+"cumulated activity"));
				
				float residenceTimePerVOIValue = timeIntegratedActivityPerVOIProduced.getResidenceTimePerVOIValue();
				addDataProperty(timeIntegratedActivityPerVOI, racineURI+timeIntegratedActivityPerVOIProduced.getTimeUnit().toString().toLowerCase(), residenceTimePerVOIValue);
				
				float timeIntegratedActivityPerVOIValue = timeIntegratedActivityPerVOIProduced.getTimeIntegratedActivityPerVOIValue();
				addDataProperty(timeIntegratedActivityPerVOI, racineURI+"has_measurement_value", timeIntegratedActivityPerVOIValue);

				switch (timeIntegratedActivityPerVOIProduced.getTimeIntegratedActivityPerVOIUnit()) {
				case MEGABECQUEREL_X_HOUR:
					// TODO
					break;
				case MEGABECQUEREL_X_SECOND:
					break;
					// TODO
				}
				
				Iterator<BigInteger> voiIterator = timeIntegratedActivityPerVOIProduced.getVOIIdentifierList().getVOIIdentifierUsed().iterator();
				while(voiIterator.hasNext()) {
					BigInteger voiIdent = voiIterator.next();
					voi = tableVOI.get(voiIdent.toString());
					addObjectProperty(timeIntegratedActivityPerVOI, racineURI+"is_quantity_measured_at", voi);
				}
				timeIntegratedActivityPerVOIProduced.getPKAssessmentMethodUsed();//TODO
			}	
		}

		AbsorbedDoseCalculationInVOI absorbedDoseCalculationInVOI = threeDimDosimetrySlide1Workflow.getAbsorbedDoseCalculationInVOI();
		Individual calculationOfMeanAbsorbedDosesInVOI = createIndiv(generateName("calculation_of_mean_absorbed_doses_in_VOIs"), model.getResource(racineURI+"calculation_of_mean_absorbed_doses_in_VOIs"));
		ProcessExecutionContext processExecutionContext = absorbedDoseCalculationInVOI.getProcessExecutionContext();
		String dateTimeProcessStarted = processExecutionContext.getDateTimeProcessStarted();
		Individual institution = memory.getInstitution(processExecutionContext.getPerformingInstitution());
		Individual roleInstitution = memory.getRoleOfResponsibleOrganization(processExecutionContext.getPerformingInstitution());
		
		if (absorbedDoseCalculationInVOI.getSegmentationIdentifierUsed()!=null) {
			logger.debug("segmentation Id : "+absorbedDoseCalculationInVOI.getSegmentationIdentifierUsed().toString());
			Individual segmentation = tableSegmentation.get(absorbedDoseCalculationInVOI.getSegmentationIdentifierUsed());
			addObjectProperty(calculationOfMeanAbsorbedDosesInVOI, racineURI+"has_specified_input", segmentation);
		}

		absorbedDoseCalculationInVOI.getAbsorbedDoseCalculationMethodUsed(); //TODO
		
		if (absorbedDoseCalculationInVOI.getVoxelAbsorbedDoseMapProduced()!=null) {
			Iterator<NonDICOMData> voxelAbsorbedDoseMapProducedIterator = absorbedDoseCalculationInVOI.getVoxelAbsorbedDoseMapProduced().getNonDICOMData().iterator();
			while (voxelAbsorbedDoseMapProducedIterator.hasNext()) {
				NonDICOMData voxelAbsorbedDoseMapProduced = voxelAbsorbedDoseMapProducedIterator.next();
				Individual voxelAbsorbedDoseMap = createIndiv(generateName("3D_absorbed_dose_map"), model.getResource(racineDCM+"128487"));
				translateNonDicomDataObject(voxelAbsorbedDoseMapProduced, voxelAbsorbedDoseMap);
				setNonDicomInputOutput(voxelAbsorbedDoseMapProduced.getFHIRIdentifier(), voxelAbsorbedDoseMapProduced.getNonDICOMDataClass(), voxelAbsorbedDoseMap, voxelAbsorbedDoseMapProduced.getNonDICOMDataFormat());
			}
		}
		
		if (absorbedDoseCalculationInVOI.getAbsorbedDoseInVOIContainer()!=null) {
			Iterator<AbsorbedDoseInVOI> absorbedDoseInVOIProducedIterator = absorbedDoseCalculationInVOI.getAbsorbedDoseInVOIContainer().getAbsorbedDoseInVOIProduced().iterator();
			while (absorbedDoseInVOIProducedIterator.hasNext()) {
				AbsorbedDoseInVOI absorbedDoseInVOI = absorbedDoseInVOIProducedIterator.next();
				Individual absorbedDose = createIndiv(generateName("absorbed_dose"), model.getResource(racineURI+"total_absorbed_dose_per_VOI"));
				addDataProperty(absorbedDose, racineURI+"has_measurement_value",absorbedDoseInVOI.getAbsorbedDoseInVOIValue());
				AbsorbedDoseInVOIUnit unit = absorbedDoseInVOI.getAbsorbedDoseInVOIUnit();
				switch (absorbedDoseInVOI.getAbsorbedDoseInVOIUnit()) {
				case GRAY:
					addObjectProperty(absorbedDose, racineURI+"has_measurement_unit_label", getUnit("gray"));
					break;
				case MILLIGRAY:
					addObjectProperty(absorbedDose, racineURI+"has_measurement_unit_label", getUnit("mGy"));
					break;
				}
				
				Individual uncertainty = createIndiv(generateName("range_of_uncertainty_value"), model.getResource(racineURI+"range_of_uncertainty_value"));
				addObjectProperty(uncertainty, racineURI+"is_about", absorbedDose);
				if (absorbedDoseInVOI.getAbsorbedDoseInVOIUncertainty()!=null) {
					addDataProperty(uncertainty, racineURI+"has_measurement_value", absorbedDoseInVOI.getAbsorbedDoseInVOIUncertainty()); //TODO autre property ?
				}
				Iterator<BigInteger> VOIIdentifierUsedIterator = absorbedDoseInVOI.getVOIIdentifierList().getVOIIdentifierUsed().iterator();
				while (VOIIdentifierUsedIterator.hasNext()) {
					BigInteger VOIIdentifierUsed = VOIIdentifierUsedIterator.next();
					Individual voi = tableVOI.get(VOIIdentifierUsed.toString());
				}
			}
		}
		
		if (threeDimDosimetrySlide1Workflow.getRadioBiologicalCalculationIn3DSlide1Dosimetry()!=null) {
			RadioBiologicalCalculationIn3DSlide1Dosimetry radioBiologicalCalculationIn3DSlide1Dosimetry = threeDimDosimetrySlide1Workflow.getRadioBiologicalCalculationIn3DSlide1Dosimetry();
			//TODO
			Individual radioBiologicalCalculation = createIndiv(generateName("radio_biological_calculatio"), model.getResource(racineURI+"radio_biological_calculatio"));
			
			processExecutionContext = radioBiologicalCalculationIn3DSlide1Dosimetry.getProcessExecutionContext();
			dateTimeProcessStarted = processExecutionContext.getDateTimeProcessStarted();
			institution = memory.getInstitution(processExecutionContext.getPerformingInstitution());
			roleInstitution = memory.getRoleOfResponsibleOrganization(processExecutionContext.getPerformingInstitution());
			
			addObjectProperty(roleInstitution,racineObo+"BFO_0000054", radioBiologicalCalculation);
			addObjectProperty(institution,racineObo+"BFO_0000161", roleInstitution); //has role at all time
			addDataProperty(radioBiologicalCalculation,racineURI+"has_beginning_date",dateTimeProcessStarted);

			Individual voi = tableVOI.get(radioBiologicalCalculationIn3DSlide1Dosimetry.getVOIIdentifierUsed().toString());
			addObjectProperty(radioBiologicalCalculation,racineURI+"is_about",voi);

			if (radioBiologicalCalculationIn3DSlide1Dosimetry.getVoxelAbsorbedDoseMapIdentifierUsed()!= null) {
				//TODO
			}
		
			
			String RadioBiologicalCalculationMethod = radioBiologicalCalculationIn3DSlide1Dosimetry.getRadioBiologicalCalculationMethod();
			addDataProperty(radioBiologicalCalculation, racineURI+"has_method_name", RadioBiologicalCalculationMethod);
			String RadioBiologicalCalculationParameters = radioBiologicalCalculationIn3DSlide1Dosimetry.getRadioBiologicalCalculationParameters();
			addDataProperty(radioBiologicalCalculation, racineURI+"has_description", RadioBiologicalCalculationParameters);

			
			String BiologicalEffectiveDose = radioBiologicalCalculationIn3DSlide1Dosimetry.getBiologicalEffectiveDose();
			
		}
		
	}
	
	public static void retreiveThreeDimDosimetrySlide2Workflow(ThreeDimDosimetrySlide2Workflow threeDimDosimetrySlide2Workflow) {
		SPECTDataAcquisitionAndReconstruction SPECTDataAcquisitionAndReconstruction = threeDimDosimetrySlide2Workflow.getSPECTDataAcquisitionAndReconstruction();
		
		Iterator<SPECTAcqCTAcqAndReconstruction> SPECTAcqCTAcqAndReconstructionIterator = SPECTDataAcquisitionAndReconstruction.getSPECTAcqCTAcqAndReconstructionContainer().getSPECTAcqCTAcqAndReconstruction().iterator();
		SPECTAcqCTAcqAndReconstruction SPECTAcqCTAcqAndReconstruction; 
		while (SPECTAcqCTAcqAndReconstructionIterator.hasNext()) {
			SPECTAcqCTAcqAndReconstruction = SPECTAcqCTAcqAndReconstructionIterator.next();
			Individual reconstruction = createIndiv(generateName("SPECT_data_reconstruction"), model.getResource(racineURI+"SPECT_data_reconstruction"));
			
			Iterator<TimePointDescriptionElement> timePointDescriptionIterator = SPECTAcqCTAcqAndReconstruction.getTimePointDescription().getTimePointDescriptionElement().iterator();
			while (timePointDescriptionIterator.hasNext()) {
				TimePointDescriptionElement TimePointDescriptionElement = timePointDescriptionIterator.next();
				
				Individual timePoint;
				switch (TimePointDescriptionElement.getTimePointCategory()) {
				case "168h plus or minus 24h post RAIT timepoint":
					timePoint=createIndiv(generateName("168h_plus_or_minus_24h_post_RAIT_timepoint"), model.getResource(racineURI+"168h_plus_or_minus_24h_post_RAIT_timepoint"));
					break;
				case "24h plus or minus 4h post RAIT timepoint":
					timePoint=createIndiv(generateName("24h_plus_or_minus_4h_post_RAIT_timepoint"), model.getResource(racineURI+"24h_plus_or_minus_4h_post_RAIT_timepoint"));
					break;
				case "48h plus or minus 4h post RAIT timepoint":
					timePoint=createIndiv(generateName("48h_plus_or_minus_4h_post_RAIT_timepoint"), model.getResource(racineURI+"48h_plus_or_minus_4h_post_RAIT_timepoint"));
					break;
				case "6h plus or minus 2h post RAIT timepoint":
					timePoint=createIndiv(generateName("6h_plus_or_minus_2h_post_RAIT_timepoint"), model.getResource(racineURI+"6h_plus_or_minus_2h_post_RAIT_timepoint"));
					break;
				case "72h plus or minus 12h post RAIT timepoint":
					timePoint=createIndiv(generateName("72h_plus_or_minus_12h_post_RAIT_timepoint"), model.getResource(racineURI+"72h_plus_or_minus_12h_post_RAIT_timepoint"));
					break;
				case "96h plus or minus 12h post RAIT timepoint":
					timePoint=createIndiv(generateName("96h_plus_or_minus_12h_post_RAIT_timepoint"), model.getResource(racineURI+"96h_plus_or_minus_12h_post_RAIT_timepoint"));
					break;
				default:
					timePoint=createIndiv(generateName("timepoint_description"), model.getResource(racineURI+"timepoint_description"));
					break;
				}
				addObjectProperty(timePoint, racineURI+"is_about", reconstruction);
				
				String TimePointIdentifier = TimePointDescriptionElement.getTimePointIdentifier();
				addDataProperty(timePoint,racineURI+"has_id", TimePointIdentifier);
				tableTimePoint.put(TimePointIdentifier, timePoint);
				BigInteger TimePointDistanceFromReferenceEventValue = TimePointDescriptionElement.getTimePointDistanceFromReferenceEventValue();
				switch(TimePointDescriptionElement.getTimeUnit())  {
				case DAYS:
					addDataProperty(timePoint,racineURI+"days", TimePointDistanceFromReferenceEventValue.intValue());
					break;
				case HOURS:
					addDataProperty(timePoint,racineURI+"hours", TimePointDistanceFromReferenceEventValue.intValue());
					break;
				case MINUTES:
					addDataProperty(timePoint,racineURI+"minutes", TimePointDistanceFromReferenceEventValue.intValue());
					break;
				case MONTHS:
					addDataProperty(timePoint,racineURI+"months", TimePointDistanceFromReferenceEventValue.intValue());
					break;
				case SECONDS:
					addDataProperty(timePoint,racineURI+"seconds", TimePointDistanceFromReferenceEventValue.intValue());
					break;
				case YEARS:
					addDataProperty(timePoint,racineURI+"years", TimePointDistanceFromReferenceEventValue.intValue());
					break;
				}
			}
			
			NMRelevantCalibrationReference NMRelevantCalibrationReference = SPECTAcqCTAcqAndReconstruction.getNMRelevantCalibrationReference();
			String NMreferenceCalibrationDate = NMRelevantCalibrationReference.getReferenceCalibrationDate();
			Isotope Isotope = NMRelevantCalibrationReference.getIsotope();
			// TODO
			
			String CTreferenceCalibrationDate = SPECTAcqCTAcqAndReconstruction.getCTRelevantCalibrationReference().getReferenceCalibrationDate();
			// TODO
			
			DICOMData NMTomoProduced = SPECTAcqCTAcqAndReconstruction.getNMTomoProduced();
			String studyId = NMTomoProduced.getDICOMStudyUID();
			String seriesId = NMTomoProduced.getDICOMSeriesUID();
			if (hasDicomInputOutput(studyId, seriesId, typeInputOutput.NMTomo) != null) {
				Individual NMtomo = getDicomInputOutput(studyId, seriesId, typeInputOutput.NMTomo);
				addObjectProperty(reconstruction, racineURI+"has_specified_output", NMtomo);
			} else {
				Individual NMtomo = createIndiv(generateName("NM_tomo_dataset"), model.getResource(racineURI+"NM_tomo_dataset"));
				addDataProperty(NMtomo, racineURI+"has_DICOM_series_instance_UID", NMTomoProduced.getDICOMSeriesUID());
				addDataProperty(NMtomo, racineURI+"has_DICOM_study_instance_UID", NMTomoProduced.getDICOMStudyUID());
				addObjectProperty(reconstruction, racineURI+"has_specified_output", NMtomo);
				setDicomInputOutput(studyId, seriesId, typeInputOutput.NMTomo, NMtomo);
			}
			
			DICOMData CTReconProduced = SPECTAcqCTAcqAndReconstruction.getCTReconProduced();
			studyId = CTReconProduced.getDICOMStudyUID();
			seriesId = CTReconProduced.getDICOMSeriesUID();
			if (hasDicomInputOutput(studyId, seriesId, typeInputOutput.CTRecon) != null) {
				Individual CTrecon = getDicomInputOutput(studyId, seriesId, typeInputOutput.CTRecon);
				addObjectProperty(reconstruction, racineURI+"has_specified_output", CTrecon);
			} else {
				Individual CTrecon = createIndiv(generateName("CT_image_reconstruction"), model.getResource(racineURI+"CT_image_reconstruction"));
				addDataProperty(CTrecon, racineURI+"has_DICOM_series_instance_UID", CTReconProduced.getDICOMSeriesUID());
				addDataProperty(CTrecon, racineURI+"has_DICOM_study_instance_UID", CTReconProduced.getDICOMStudyUID());
				addObjectProperty(reconstruction, racineURI+"has_specified_output", CTrecon);
				setDicomInputOutput(studyId, seriesId, typeInputOutput.CTRecon, CTrecon);
			}
		}
		
		Iterator<SPECTReconstruction> SPECTReconstructionIterator = SPECTDataAcquisitionAndReconstruction.getSPECTReconstructionContainer().getSPECTReconstruction().iterator();
		while (SPECTReconstructionIterator.hasNext()) {
			SPECTReconstruction SPECTReconstruction = SPECTReconstructionIterator.next();
			ProcessExecutionContext processExecutionContext = SPECTReconstruction.getProcessExecutionContext();
			
		}
		
		
	}
/*
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
						tableVOIorgans.put(voiId, organ);

						Iterator<NonDICOMData> voiContainerIter = voiInformations.getNonDICOMVOIContainer().iterator();
						while (voiContainerIter.hasNext()) {
							Individual voiFile = createIndiv(generateName("non_DICOM_file"),model.getResource(racineURI+"non_DICOM_file"));
							
							addObjectProperty(voiFile, racineURI+"has_patient",patient);
							addObjectProperty(imageSegmentation,racineURI+"has_specified_output", voiFile);
							addObjectProperty(voiFile, racineURI+"is_specified_output_of", imageSegmentation);
							addObjectProperty(organ,racineURI+"part_of_continuant_at_some_time", patient);	
							addObjectProperty(voiFile,racineURI+"represents", organ);
							
							NonDICOMData voiContainer = voiContainerIter.next();
							if (voiContainer.getFHIRIdentifier()!=null) {
								String fhirID = voiContainer.getFHIRIdentifier();
								if (fhirID.contains("/fhir/Binary/")==false) {
									fhirID="/fhir/Binary/"+fhirID;
								}
								addDataProperty(voiFile, racineURI+"has_IRDBB_FHIR_handle",fhirID);
								
							} else {
								logger.warn("NonDICOMVOIContainer has no FHIRIdentifier");
							}
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
							}

							switch (voiContainer.getNonDICOMDataFormat()) {
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
								addObjectProperty(voi, racineURI+"has_specified_output", voxelBasedDistributionOfAbsorbedDoseType);	
								break;
							default:
								logger.warn("Unknown : voxelBasedDistribution.getNonDICOMVoxelBasedAbsorbedDoseDistribution().nonDICOMDataClass");
							}
							if(voxelBasedDistribution.getNonDICOMVoxelBasedAbsorbedDoseDistribution().getFHIRIdentifier()!=null) {
								String fhirID = voxelBasedDistribution.getNonDICOMVoxelBasedAbsorbedDoseDistribution().getFHIRIdentifier();
								
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
							
							//DICOMData imageDataUsed = ctMonteCarloDosimetry.getCalculationOfVoxelMap().getDICOMCTImageDataUsed();
							//String series = imageDataUsed.getDICOMSeriesUID();
							//patient = memory.getPatient(series, imageDataUsed.getDICOMStudyUID()); 
							if (patient!=null) {
								addObjectProperty(absorbedDoseVoi, racineURI+"has_patient", patient);
								//addObjectProperty(researchClinicalStudy, racineURI+"has_patient", patient);
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
	}*/
 	
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


 	public static Individual getOrganOrTissue(OrganOrTissue organ) {
 		Individual organIndiv = null;
 		switch (organ) {
		case ADRENALS:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_264816"));
			break;
		case AIR:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_84580"));
			break;
		case BLADDER:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_15900"));
			break;
		case BODY_SURFACE:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_61695"));
			break;
		case BONE:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_30317"));
			break;
		case BONE_SURFACES:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_33535"));
			break;
		case BRAIN:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_50801"));
			break;
		case BREASTS:
			organIndiv = createIndiv(model.getResource(racineURI+"breasts"));
			break;
		case COLON:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_7201"));
			break;
		case ESOPHAGUS:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_7131"));
			break;
		case EXTRATHORACIC_REGION:
			break;
		case GALLBLADDER:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_7202"));
			break;
		case HEART:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_7088"));
			break;
		case KIDNEYS:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_264815"));
			break;
		case LEFT_FEMALE_BREAST:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_19910"));
			break;
		case LEFT_KIDNEY:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_7205"));
			break;
		case LEFT_LUNG:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_7310"));
			break;
		case LEFT_MALE_BREAST:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_19913"));
			break;
		case LEFT_OVARY:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_7214"));
			break;
		case LEFT_PAROTID_GLAND:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_59798"));
			break;
		case LEFT_SUBMANDIBULAR_GLAND:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_59803"));
			break;
		case LEFT_TESTIS:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_7212"));
			break;
		case LIVER:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_7197"));
			break;
		case LUNGS:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_68877"));
			break;
		case LYMPHATIC_NODES:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_70776"));
			break;
		case MUSCLE:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_30316"));
			break;
		case ORAL_MUCOSA:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_54933"));
			break;
		case OVARIES:
			organIndiv = createIndiv(model.getResource(racineURI+"ovaries"));
			break;
		case PANCREAS:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_7198"));
			break;
		case PAROTID_GLANDS:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_320436"));
			break;
		case PROSTATE:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_7600"));
			break;
		case RED_BONE_MARROW:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_74595"));
			break;
		case RIGHT_FEMALE_BREAST:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_19908"));
			break;
		case RIGHT_KIDNEY:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_7204"));
			break;
		case RIGHT_LUNG:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_7309"));
			break;
		case RIGHT_MALE_BREAST:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_19912"));
			break;
		case RIGHT_OVARY:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_7213"));
			break;
		case RIGHT_PAROTID_GLAND:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_59797"));
			break;
		case RIGHT_SUBMANDIBULAR_GLAND:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_59802"));
			break;
		case RIGHT_TESTIS:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_7211"));
			break;
		case SALIVARY_GLANDS:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_322428"));
			break;
		case SKIN:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_7163"));
			break;
		case SMALL_INTESTINE:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_7200"));
			break;
		case SPLEEN:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_7196"));
			break;
		case STOMACH:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_7148"));
			break;
		case SUBMANDIBULAR_GLANDS:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_320442"));
			break;
		case TESTES:
			organIndiv = createIndiv(model.getResource(racineURI+"testes"));
			break;
		case THYMUS:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_9607"));
			break;
		case THYROID:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_9603"));
			break;
		case UTERUS:
			organIndiv = createIndiv(model.getResource(racineObo+"FMA_17558"));
			break; 	
 		}
 		return organIndiv;
 	}
 	
	public enum typeInputOutput {
		SPECTCTacq,
		CTRecon,
		NMTomo;
	}
	
	public static String generateKeyDicomInputOutput(String studyId, String seriesId, typeInputOutput type) {
		return studyId + "_" + seriesId + "_" + type.toString() ;
	}
	
	public static String generateKeyNonDicomInputOutput(String fhirId, String nonDicomDataClass, NonDICOMDataFormat NonDICOMDataFormat) {
		return fhirId + "_" + nonDicomDataClass + "_" + NonDICOMDataFormat.toString() ;
	}
	
	public static Individual getNonDicomInputOutput(String fhirId, String nonDicomDataClass, NonDICOMDataFormat NonDICOMDataFormat) {
		return tableInputOutput.get(generateKeyNonDicomInputOutput(fhirId, nonDicomDataClass, NonDICOMDataFormat));
	}
	
	public static Individual getDicomInputOutput(String studyId, String seriesId, typeInputOutput type) {
		return tableInputOutput.get(generateKeyDicomInputOutput(studyId, seriesId, type));
	}
	
	public static void setNonDicomInputOutput(String fhirId, String nonDicomDataClass, Individual NonDicomInputOutput, NonDICOMDataFormat NonDICOMDataFormat) {
		tableInputOutput.put(generateKeyNonDicomInputOutput(fhirId, nonDicomDataClass, NonDICOMDataFormat), NonDicomInputOutput);
	}
	
	public static void setDicomInputOutput(String studyId, String seriesId, typeInputOutput type, Individual dicomInputOutput) {
		tableInputOutput.put(generateKeyDicomInputOutput(studyId, seriesId, type), dicomInputOutput);
	}
 	
	public static Boolean hasNonDicomInputOutput(String fhirId, String nonDicomDataClass, NonDICOMDataFormat NonDICOMDataFormat) {
		return tableInputOutput.containsKey(generateKeyNonDicomInputOutput(fhirId, nonDicomDataClass, NonDICOMDataFormat));
	}
	
	public static Boolean hasDicomInputOutput(String studyId, String seriesId, typeInputOutput type) {
		return tableInputOutput.containsKey(generateKeyDicomInputOutput(studyId, seriesId, type));
	}
	
 	
}
