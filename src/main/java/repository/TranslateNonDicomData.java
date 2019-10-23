package repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.UUID;

import org.apache.jena.ontology.Individual;
import org.apache.jena.rdf.model.ModelFactory;

import javaXSDclass.AbsorbedDoseCalculationInVOI;
import javaXSDclass.AbsorbedDosePerVOIType;
import javaXSDclass.AbsorbedDoseRatePerVOIAtTimePoint;
import javaXSDclass.AdministeredActivity;
import javaXSDclass.AttenuatorType;
import javaXSDclass.CTAcqForCTNumberCalibrationCurve;
import javaXSDclass.CTNumberCalibrationCurve;
import javaXSDclass.CTNumberCalibrationCurveReference;
import javaXSDclass.CTNumberCalibrationWorkfow;
import javaXSDclass.CTRelevantCalibrationReference;
import javaXSDclass.CTSegmentation;
import javaXSDclass.CTSegmentationInCalibration;
import javaXSDclass.CalculationOfAbsorbedDosesInVOIs;
import javaXSDclass.CalculationOfCTNumberCalibrationCurve;
import javaXSDclass.CalculationOfVoxelMap;
import javaXSDclass.ColdInsert;
import javaXSDclass.CountsPerVOIAtTimePoint;
import javaXSDclass.DICOMData;
import javaXSDclass.DataActivityPerVOIAtTimePoint;
import javaXSDclass.DensityPhantom;
import javaXSDclass.DoseRateCurveFitVOITimeIntegration;
import javaXSDclass.ElementOfCTNumberCalibrationCurve;
import javaXSDclass.EnergyDepositionRateCalculationIn3DDosimetry;
import javaXSDclass.HotInsert;
import javaXSDclass.MassPerVOIAtTimePoint;
import javaXSDclass.MeanAbsorbedDoseInVOI;
import javaXSDclass.MethodSettingType;
import javaXSDclass.NMPhantom;
import javaXSDclass.NMRelevantCalibrationReference;
import javaXSDclass.NonDICOMData;
import javaXSDclass.NonDicomFileSetDescriptor;
import javaXSDclass.NonDicomFileSetDescriptor.CalibrationWorkflow;
import javaXSDclass.NonDicomFileSetDescriptor.ThreeDimDosimetrySlide1Workflow;
import javaXSDclass.NonDicomFileSetDescriptor.ThreeDimDosimetrySlide2Workflow;
import javaXSDclass.NonDicomFileSetDescriptor.TwoDimDosimetryworkflow;
import javaXSDclass.NonDicomFileSetDescriptor.WP2Subtask212WorkflowData;
import javaXSDclass.PlanarCalibrationWorkflow;
import javaXSDclass.PlanarDataAcquisition;
import javaXSDclass.PlanarDataAcquisitionAndProcessing;
import javaXSDclass.ProcessExecutionContext;
import javaXSDclass.RadioBiologicalCalculation;
import javaXSDclass.RadioBiologicalCalculationIn3DSlide1Dosimetry;
import javaXSDclass.RadioBiologicalCalculationInHybridOr3DSlide2Dosimetry;
import javaXSDclass.RegistrationVOISegmentationAndPropagation;
import javaXSDclass.SPECTAcqCTAcqAndReconstruction;
import javaXSDclass.SPECTAcqCTAcqAndReconstructionInCalibration;
import javaXSDclass.SPECTCTCalibrationWorkflow;
import javaXSDclass.SPECTDataAcquisitionAndReconstruction;
import javaXSDclass.SPECTReconstruction;
import javaXSDclass.SPECTReconstructionInCalibration;
import javaXSDclass.SPECTRecoveryCoefficientCurveCalculation;
import javaXSDclass.SPECTSensitivityCalculation;
import javaXSDclass.SimpleCTMonteCarloDosimetry;
import javaXSDclass.SumAndScalingAbsorbedDoseRateCalculation;
import javaXSDclass.Tank;
import javaXSDclass.TimeActivityCurveFitIn3DDosimetry;
import javaXSDclass.TimeIntegratedActivityCoefficientPerVOI;
import javaXSDclass.TimeIntegratedActivityPerVOI;
import javaXSDclass.TimePointDescription;
import javaXSDclass.TwoDimDosimetryViaAbsorbedDoseRateCalculation;
import javaXSDclass.TwoDimDosimetryViaTimeActivityCurveFit;
import javaXSDclass.VOI;
import javaXSDclass.VOIActivityDetermination;
import javaXSDclass.VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation;
import javaXSDclass.VOISegmentationVOIMassDetermination;
import javaXSDclass.VoxelBasedDistributionOfAbsorbedDoseType;



public class TranslateNonDicomData extends OntologyPopulator {

	static Hashtable<String, Individual> tableVOI = new Hashtable<String, Individual>();
	static Hashtable<String, Individual> tableVOIorgans = new Hashtable<String, Individual>();
	static Hashtable<String, String> tableVOIname = new Hashtable<String, String>();

	static Individual researchClinicalStudy;
	
	public static void translateNonDicomData(NonDicomFileSetDescriptor nonDicomFileSetDescriptor) { // 1st function to read XML, check what is inside and call the appropritae function
		populateModel = ModelFactory.createOntologyModel();
		if (model==null) {model = Application.getModel();}
		if (memory==null) {memory = Application.memory;}
		
		researchClinicalStudy = retrieveClinicalResearchStudy(nonDicomFileSetDescriptor.getReferencedClinicalResearchStudy().getClinicalResearchStudyID());
		String patientID = nonDicomFileSetDescriptor.getPatientId();
		memory.getPatientbyId(patientID);
		
		if (nonDicomFileSetDescriptor.getWP2Subtask212WorkflowData()!=null) {
			//researchClinicalStudy = createIndiv("clinical_research_study_755523_subtask2.1.2", 
			//		model.getResource("http://medicis.univ-rennes1.fr/ontologies/ontospm/OntoMEDIRAD.owl#clinical_research_study_755523_subtask2.1.2"));
			retrieveSubtastk212(nonDicomFileSetDescriptor);
		}
		
		if (nonDicomFileSetDescriptor.getCalibrationWorkflow()!=null) {
			Iterator<CalibrationWorkflow> calibrationWorkflowIterator = nonDicomFileSetDescriptor.getCalibrationWorkflow().iterator();
			while (calibrationWorkflowIterator.hasNext()) {
				CalibrationWorkflow calibrationWorkflow = calibrationWorkflowIterator.next();
				SPECTCTCalibrationWorkflow SPECTCTCalibrationWorkflow = calibrationWorkflow.getSPECTCTCalibrationWorkflow();
				retreiveSPECTCTCalibrationWorkflow(SPECTCTCalibrationWorkflow);
				PlanarCalibrationWorkflow planarCalibrationWorkflow = calibrationWorkflow.getPlanarCalibrationWorkflow();
				retreivePlanarCalibrationWorkflow(planarCalibrationWorkflow);
			}
		}
		
		if (nonDicomFileSetDescriptor.getHybridDosimetryworkflow()!=null) {
			
		}
		
		if (nonDicomFileSetDescriptor.getTwoDimDosimetryworkflow()!=null) {
			Iterator<TwoDimDosimetryworkflow> twoDimDosimetryworkflowIterator = nonDicomFileSetDescriptor.getTwoDimDosimetryworkflow().iterator();
			while (twoDimDosimetryworkflowIterator.hasNext()) {
				TwoDimDosimetryworkflow twoDimDosimetryworkflow = twoDimDosimetryworkflowIterator.next();
			}
		}
		
		if (nonDicomFileSetDescriptor.getThreeDimDosimetrySlide1Workflow()!=null) {
			Iterator<ThreeDimDosimetrySlide1Workflow> threeDimDosimetrySlide1WorkflowIterator = nonDicomFileSetDescriptor.getThreeDimDosimetrySlide1Workflow().iterator();
			while(threeDimDosimetrySlide1WorkflowIterator.hasNext()) {
				ThreeDimDosimetrySlide1Workflow threeDimDosimetrySlide1Workflow = threeDimDosimetrySlide1WorkflowIterator.next();
				retreiveThreeDimDosimetrySlide1Workflow(threeDimDosimetrySlide1Workflow);
			}
		}
		
		if (nonDicomFileSetDescriptor.getThreeDimDosimetrySlide2Workflow()!=null) {
			Iterator<ThreeDimDosimetrySlide2Workflow> threeDimDosimetrySlide2WorkflowIterator = nonDicomFileSetDescriptor.getThreeDimDosimetrySlide2Workflow().iterator();
			while (threeDimDosimetrySlide2WorkflowIterator.hasNext()) {
				ThreeDimDosimetrySlide2Workflow threeDimDosimetrySlide2Workflow = threeDimDosimetrySlide2WorkflowIterator.next();
				retreiveThreeDimDosimetrySlide2Workflow(threeDimDosimetrySlide2Workflow);
			}
		}

		
		// to be completed when with other subtasks

	}
	
	public static void retreiveTwoDimDosimetryworkflow(TwoDimDosimetryworkflow twoDimDosimetryworkflow) {
		Iterator<PlanarDataAcquisitionAndProcessing> planarDataAcquisitionAndProcessingIterator = twoDimDosimetryworkflow.getPlanarDataAcquisitionAndProcessing().iterator();
		while (planarDataAcquisitionAndProcessingIterator.hasNext()) {
			PlanarDataAcquisitionAndProcessing planarDataAcquisitionAndProcessing = planarDataAcquisitionAndProcessingIterator.next();
			PlanarDataAcquisition planarDataAcquisition = planarDataAcquisitionAndProcessing.getPlanarDataAcquisition();
			planarDataAcquisitionAndProcessing.getPlanarDataCorrections();
			planarDataAcquisitionAndProcessing.getPlanarDataSegmentationWithoutRegistration();
			planarDataAcquisitionAndProcessing.getPlanarDataSegmentationWithRegistrationAndPropagation();
			planarDataAcquisitionAndProcessing.getROIPlanarActivityDetermination();
			
		}
		
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
						Iterator<ElementOfCTNumberCalibrationCurve> elementOfCTNumberCalibrationCurveIterator = CTNumberCalibrationCurveProduced.getElementOfCTNumberCalibrationCurve().iterator();
						ElementOfCTNumberCalibrationCurve elementOfCTNumberCalibrationCurve;
						while (elementOfCTNumberCalibrationCurveIterator.hasNext()) {
							elementOfCTNumberCalibrationCurve = elementOfCTNumberCalibrationCurveIterator.next();
							BigInteger hounsfieldMeasuredValue = elementOfCTNumberCalibrationCurve.getHounsfieldMeasuredValue();
							float realDensityOfMaterialValue = elementOfCTNumberCalibrationCurve.getRealDensityOfMaterialValue();
							String realDensityOfMaterialUnit = elementOfCTNumberCalibrationCurve.getRealDensityOfMaterialUnit();
							if (elementOfCTNumberCalibrationCurve.getMaterialIdentifier()!=null) {
								String materialIdentifier = elementOfCTNumberCalibrationCurve.getMaterialIdentifier();
							}
						}
		}
		
		SPECTAcqCTAcqAndReconstructionInCalibration SPECTAcqCTAcqAndReconstructionInCalibration = SPECTCTCalibrationWorkflow.getSPECTAcqCTAcqAndReconstructionInCalibration();
			NMPhantom phantomUsed = SPECTAcqCTAcqAndReconstructionInCalibration.getPhantomUsed();
				String NMPhantomName = phantomUsed.getNMPhantomName();
				String NMPhantomIdentifier = phantomUsed.getNMPhantomIdentifier();
				Iterator<HotInsert> HotInsertIterator = phantomUsed.getHotInsert().iterator();
				while (HotInsertIterator.hasNext()) {
					HotInsert HotInsert = HotInsertIterator.next();
					float volumeValue = HotInsert.getVolumeValue();
					String volumeUnit = HotInsert.getVolumeUnit();
					float preAdminActivityValue = HotInsert.getPreAdminActivityValue();
					String preAdminActivityUnit = HotInsert.getPreAdminActivityUnit();
					String preAdminActivityTimestamp = HotInsert.getPreAdminActivityTimestamp();
					float postAdminActivityValue = HotInsert.getPostAdminActivityValue();
					String postAdminActivityUnit = HotInsert.getPostAdminActivityUnit();
					String postAdminActivityTimestamp = HotInsert.getPostAdminActivityTimestamp();
					String HotInsertIdentifier = HotInsert.getHotInsertIdentifier();
					String Isotope = HotInsert.getIsotope();
				}
				Iterator<ColdInsert> ColdInsertIterator = phantomUsed.getColdInsert().iterator();
				while (HotInsertIterator.hasNext()) {
					ColdInsert ColdInsert = ColdInsertIterator.next();
					float volumeValue = ColdInsert.getVolumeValue();
					String volumeUnit = ColdInsert.getVolumeUnit();
					String coldInsertIdentifier = ColdInsert.getColdInsertIdentifier();
				}
				Tank tank = phantomUsed.getTank();
					float volumeValue = tank.getVolumeValue();
					String volumeUnit = tank.getVolumeUnit();
					float preAdminBackgroundActivityValue = tank.getPreAdminBackgroundActivityValue();
					String preAdminBackgroundActivityUnit = tank.getPreAdminBackgroundActivityUnit();
					String preAdminBackgroundActivityTimestamp = tank.getPreAdminBackgroundActivityTimestamp();
					float postAdminBackgroundActivityValue = tank.getPostAdminBackgroundActivityValue();
					String postAdminBackgroundActivityUnit = tank.getPostAdminBackgroundActivityUnit();
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
		AbsorbedDoseCalculationInVOI absorbedDoseCalculationInVOI = threeDimDosimetrySlide1Workflow.getAbsorbedDoseCalculationInVOI();	
			String AbsorbedDoseCalculationMethodUsed = absorbedDoseCalculationInVOI.getAbsorbedDoseCalculationMethodUsed();
			Iterator<DICOMData> CTReconResampledOnCommonReferenceUsedIterator = absorbedDoseCalculationInVOI.getCTReconResampledOnCommonReferenceUsed().iterator();
			while (CTReconResampledOnCommonReferenceUsedIterator.hasNext()) {
				DICOMData CTReconResampledOnCommonReferenceUsed = CTReconResampledOnCommonReferenceUsedIterator.next();
					String CTReconResampledOnCommonReferenceUsed_DICOMSeriesUID = CTReconResampledOnCommonReferenceUsed.getDICOMSeriesUID();
					String CTReconResampledOnCommonReferenceUsed_DICOMStudyUID = CTReconResampledOnCommonReferenceUsed.getDICOMStudyUID();
			}
			Iterator<MeanAbsorbedDoseInVOI> MeanAbsorbedDoseInVOIProducedIterator = absorbedDoseCalculationInVOI.getMeanAbsorbedDoseInVOIProduced().iterator();
			while (MeanAbsorbedDoseInVOIProducedIterator.hasNext()) {
				MeanAbsorbedDoseInVOI meanAbsorbedDoseInVOIProduced = MeanAbsorbedDoseInVOIProducedIterator.next();
					String meanAbsorbedDoseInVOIUnit = meanAbsorbedDoseInVOIProduced.getMeanAbsorbedDoseInVOIUnit();
					float meanAbsorbedDoseInVOIValue = meanAbsorbedDoseInVOIProduced.getMeanAbsorbedDoseInVOIValue();
					String VOIIdentifier = meanAbsorbedDoseInVOIProduced.getVOIIdentifier();
			}
			Iterator<DICOMData> NMTomoReconResampledOnCommonReferenceUsedIterator = absorbedDoseCalculationInVOI.getNMTomoReconResampledOnCommonReferenceUsed().iterator();
			while (NMTomoReconResampledOnCommonReferenceUsedIterator.hasNext()) {
				boolean NMTomoReconResampledOnCommonReferenceUsed = NMTomoReconResampledOnCommonReferenceUsedIterator.hasNext();
			}
			ProcessExecutionContext processExecutionContext = absorbedDoseCalculationInVOI.getProcessExecutionContext();
			Iterator<String> VOIIdentifierUsedIterator = absorbedDoseCalculationInVOI.getVOIIdentifierUsed().iterator();
			while (VOIIdentifierUsedIterator.hasNext()) {
				String VOIIdentifierUsed = VOIIdentifierUsedIterator.next();
			}
			Iterator<NonDICOMData> voxelAbsorbedDoseMapProducedIterator = absorbedDoseCalculationInVOI.getVoxelAbsorbedDoseMapProduced().iterator();
			while (voxelAbsorbedDoseMapProducedIterator.hasNext()) {
				NonDICOMData voxelAbsorbedDoseMapProduced = voxelAbsorbedDoseMapProducedIterator.next();
					String voxelAbsorbedDoseMapProduced_FHIRIdentifier = voxelAbsorbedDoseMapProduced.getFHIRIdentifier();
					String voxelAbsorbedDoseMapProduced_NonDICOMDataClass = voxelAbsorbedDoseMapProduced.getNonDICOMDataClass();
					Iterator<String> voxelAbsorbedDoseMapProduced_NonDICOMDataFileNameIterator = voxelAbsorbedDoseMapProduced.getNonDICOMDataFileName().iterator();
					while (voxelAbsorbedDoseMapProduced_NonDICOMDataFileNameIterator.hasNext()) {
						String voxelAbsorbedDoseMapProduced_NonDICOMDataFileName = voxelAbsorbedDoseMapProduced_NonDICOMDataFileNameIterator.next();
					}
					String voxelAbsorbedDoseMapProduced_NonDICOMDataFormat = voxelAbsorbedDoseMapProduced.getNonDICOMDataFormat();
			}
		
		RadioBiologicalCalculationIn3DSlide1Dosimetry radioBiologicalCalculationIn3DSlide1Dosimetry = threeDimDosimetrySlide1Workflow.getRadioBiologicalCalculationIn3DSlide1Dosimetry();
		String biologicalEffectiveDose = radioBiologicalCalculationIn3DSlide1Dosimetry.getBiologicalEffectiveDose();
		processExecutionContext = radioBiologicalCalculationIn3DSlide1Dosimetry.getProcessExecutionContext();
			String dateTimeProcessStarted = processExecutionContext.getDateTimeProcessStarted();
			String performingInstitution = processExecutionContext.getPerformingInstitution();
		String radioBiologicalCalculationMethod = radioBiologicalCalculationIn3DSlide1Dosimetry.getRadioBiologicalCalculationMethod();
		String radioBiologicalCalculationParameters = radioBiologicalCalculationIn3DSlide1Dosimetry.getRadioBiologicalCalculationParameters();
		Iterator<String> VOIIdentifierUseIterator = radioBiologicalCalculationIn3DSlide1Dosimetry.getVOIIdentifierUsed().iterator();
		while (VOIIdentifierUseIterator.hasNext()) {
			String VOIIdentifierUse = VOIIdentifierUseIterator.next();
		}
		Iterator<String> voxelAbsorbedDoseMapIdentifierUsedIterator = radioBiologicalCalculationIn3DSlide1Dosimetry.getVoxelAbsorbedDoseMapIdentifierUsed().iterator();
		while (voxelAbsorbedDoseMapIdentifierUsedIterator.hasNext()) {
			String voxelAbsorbedDoseMapIdentifierUsed = voxelAbsorbedDoseMapIdentifierUsedIterator.next();
		}
		
		RegistrationVOISegmentationAndPropagation registrationVOISegmentationAndPropagation = threeDimDosimetrySlide1Workflow.getRegistrationVOISegmentationAndPropagation();
			Iterator<CountsPerVOIAtTimePoint> countsPerVOIAtTimePointProducedIterator = registrationVOISegmentationAndPropagation.getCountsPerVOIAtTimePointProduced().iterator();			
			while (countsPerVOIAtTimePointProducedIterator.hasNext()) {
				CountsPerVOIAtTimePoint countsPerVOIAtTimePointProduced = countsPerVOIAtTimePointProducedIterator.next();
					String countsUnit = countsPerVOIAtTimePointProduced.getCountsUnit();
					float countsValue = countsPerVOIAtTimePointProduced.getCountsValue();
					String timePointIdentifier = countsPerVOIAtTimePointProduced.getTimePointIdentifier();
					String VOIIdentifier = countsPerVOIAtTimePointProduced.getVOIIdentifier();
			}
			Iterator<DICOMData> CTReconResampledOnCommonReferenceProducedIterator = registrationVOISegmentationAndPropagation.getCTReconResampledOnCommonReferenceProduced().iterator();
			while (CTReconResampledOnCommonReferenceProducedIterator.hasNext()) {
				DICOMData CTReconResampledOnCommonReferenceProduced = CTReconResampledOnCommonReferenceProducedIterator.next();
					String CTReconResampledOnCommonReferenceProduced_DICOMSeriesUID = CTReconResampledOnCommonReferenceProduced.getDICOMSeriesUID();
					String CTReconResampledOnCommonReferenceProduced_DICOMStudyUID = CTReconResampledOnCommonReferenceProduced.getDICOMStudyUID();
			}
			Iterator<DICOMData> CTReconUsedIterator = registrationVOISegmentationAndPropagation.getCTReconUsed().iterator();
			while (CTReconUsedIterator.hasNext()) {
				DICOMData CTReconUsed = CTReconUsedIterator.next();
					String CTReconUsed_DICOMSeriesUID = CTReconUsed.getDICOMSeriesUID();
					String CTReconUsed_DICOMStudyUID = CTReconUsed.getDICOMStudyUID();
			}
			String imageProcessingMethodMethodUsed = registrationVOISegmentationAndPropagation.getImageProcessingMethodMethodUsed();
			Iterator<DICOMData> NMTomoReconResampledOnCommonReferenceProducedIterator = registrationVOISegmentationAndPropagation.getNMTomoReconResampledOnCommonReferenceProduced().iterator();
			while (NMTomoReconResampledOnCommonReferenceProducedIterator.hasNext()) {
				DICOMData NMTomoReconResampledOnCommonReferenceProduced = NMTomoReconResampledOnCommonReferenceProducedIterator.next();
				String NMTomoReconResampledOnCommonReferenceProduced_DICOMSeriesUID = NMTomoReconResampledOnCommonReferenceProduced.getDICOMSeriesUID();
				String NMTomoReconResampledOnCommonReferenceProduced_DICOMStudyUID = NMTomoReconResampledOnCommonReferenceProduced.getDICOMStudyUID();
			}
			Iterator<DICOMData> NMTomoReconUsedIterator = registrationVOISegmentationAndPropagation.getNMTomoReconUsed().iterator();
			while (NMTomoReconUsedIterator.hasNext()) {
				DICOMData NMTomoReconUsed = NMTomoReconUsedIterator.next();
					String NMTomoReconUsed_DICOMSeriesUID = NMTomoReconUsed.getDICOMSeriesUID();
					String NMTomoReconUsed_DICOMStudyUID = NMTomoReconUsed.getDICOMStudyUID();
			}
			ProcessExecutionContext timePointIdentifierUsed = processExecutionContext = registrationVOISegmentationAndPropagation.getProcessExecutionContext();
				dateTimeProcessStarted = timePointIdentifierUsed.getDateTimeProcessStarted();
				performingInstitution = timePointIdentifierUsed.getPerformingInstitution();
			Iterator<String> timePointIdentifierUsedIteratorIterator = registrationVOISegmentationAndPropagation.getTimePointIdentifierUsed().iterator();
			while (timePointIdentifierUsedIteratorIterator.hasNext()) {
				String timePointIdentifierUsedIterator = timePointIdentifierUsedIteratorIterator.next();
			}
			Iterator<VOI> VOIProducedIterator = registrationVOISegmentationAndPropagation.getVOIProduced().iterator();
			while (VOIProducedIterator.hasNext()) {
				VOI VOIProduced = VOIProducedIterator.next();
				Iterator<DICOMData> DICOMVOIContainerIterator = VOIProduced.getDICOMVOIContainer().iterator();
				while (DICOMVOIContainerIterator.hasNext()) {
					DICOMData DICOMVOIContainer = DICOMVOIContainerIterator.next();
					String DICOMVOIContainer_DICOMSeriesUID = DICOMVOIContainer.getDICOMSeriesUID();
					String DICOMVOIContainer_DICOMStudyUID = DICOMVOIContainer.getDICOMStudyUID();
				}
				Iterator<NonDICOMData> nonDICOMVOIContainerIterator = VOIProduced.getNonDICOMVOIContainer().iterator();
				while (nonDICOMVOIContainerIterator.hasNext()) {
					NonDICOMData nonDICOMVOIContainer = nonDICOMVOIContainerIterator.next();
					String FHIRIdentifier = nonDICOMVOIContainer.getFHIRIdentifier();
					String NonDICOMDataClass = nonDICOMVOIContainer.getNonDICOMDataClass();
					Iterator<String> NonDICOMDataFileNameIterator = nonDICOMVOIContainer.getNonDICOMDataFileName().iterator();
					while (NonDICOMDataFileNameIterator.hasNext()) {
						String NonDICOMDataFileName = NonDICOMDataFileNameIterator.next();	
					}
					String NonDICOMDataFormat = nonDICOMVOIContainer.getNonDICOMDataFormat();
				}
				String organOrTissue = VOIProduced.getOrganOrTissue();
				String VOIIdentifier = VOIProduced.getVOIIdentifier();
			}
		
		Iterator<SPECTDataAcquisitionAndReconstruction> SPECTDataAcquisitionAndReconstructionIterator = threeDimDosimetrySlide1Workflow.getSPECTDataAcquisitionAndReconstruction().iterator();
		while (SPECTDataAcquisitionAndReconstructionIterator.hasNext()) {
			SPECTDataAcquisitionAndReconstruction SPECTDataAcquisitionAndReconstruction = SPECTDataAcquisitionAndReconstructionIterator.next();			
				SPECTAcqCTAcqAndReconstruction SPECTAcqCTAcqAndReconstruction = SPECTDataAcquisitionAndReconstruction.getSPECTAcqCTAcqAndReconstruction();
					TimePointDescription timePointDescription = SPECTAcqCTAcqAndReconstruction.getTimePointDescription();
						String timePointCategory = timePointDescription.getTimePointCategory();
						String timePointIdentifier = timePointDescription.getTimePointIdentifier();
						String timePointDistanceFromReferenceEventUnit = timePointDescription.getTimePointDistanceFromReferenceEventUnit();
						BigInteger timePointDistanceFromReferenceEventValue = timePointDescription.getTimePointDistanceFromReferenceEventValue();
					NMRelevantCalibrationReference NMRelevantCalibrationReference = SPECTAcqCTAcqAndReconstruction.getNMRelevantCalibrationReference();
						String NMReferenceCalibrationDate = NMRelevantCalibrationReference.getReferenceCalibrationDate();
						String isotope = NMRelevantCalibrationReference.getIsotope();
					CTRelevantCalibrationReference CTRelevantCalibrationReference = SPECTAcqCTAcqAndReconstruction.getCTRelevantCalibrationReference();
						String CTReferenceCalibrationDate = CTRelevantCalibrationReference.getReferenceCalibrationDate();
					DICOMData NMTomoProduced = SPECTAcqCTAcqAndReconstruction.getNMTomoProduced();
						String NMTomoProduced_DICOMSeriesUID = NMTomoProduced.getDICOMSeriesUID();
						String NMTomoProduced_DICOMStudyUID= NMTomoProduced.getDICOMStudyUID();
					DICOMData CTReconProduced = SPECTAcqCTAcqAndReconstruction.getCTReconProduced();
						String CTReconProduced_DICOMSeriesUID = CTReconProduced.getDICOMSeriesUID();
						String CTReconProduced_DICOMStudyUID= CTReconProduced.getDICOMStudyUID();
				SPECTReconstruction SPECTReconstruction = SPECTDataAcquisitionAndReconstruction.getSPECTReconstruction();
					String referenceCalibrationDate;
					ProcessExecutionContext processExecutionContext1 = SPECTReconstruction.getProcessExecutionContext();
						String DateTimeProcessStarted = processExecutionContext1.getDateTimeProcessStarted();
						String PerformingInstitution = processExecutionContext1.getPerformingInstitution();
					String timePointIdentifierUsed1 = SPECTReconstruction.getTimePointIdentifierUsed();
					DICOMData NMTomoUsed = SPECTReconstruction.getNMTomoUsed();
						String NMTomoUsed_DICOMSeriesUID = NMTomoUsed.getDICOMSeriesUID();
						String NMTomoUsed_DICOMStudyUID= NMTomoUsed.getDICOMStudyUID();
					DICOMData CTReconUsed = SPECTReconstruction.getCTReconUsed();
						String CTReconUsed_DICOMSeriesUID = CTReconUsed.getDICOMSeriesUID();
						String CTReconUsed_DICOMStudyUID= CTReconUsed.getDICOMStudyUID();
					String ReconstructionMethodAndCorrectionsUsed = SPECTReconstruction.getReconstructionMethodAndCorrectionsUsed();
					if (SPECTReconstruction.getCTNumberCalibrationCurveUsed()!=null) {
						CTNumberCalibrationCurve CTNumberCalibrationCurveUsed = SPECTReconstruction.getCTNumberCalibrationCurveUsed();
							Iterator<ElementOfCTNumberCalibrationCurve> elementOfCTNumberCalibrationCurveIterator = CTNumberCalibrationCurveUsed.getElementOfCTNumberCalibrationCurve().iterator();
							while (elementOfCTNumberCalibrationCurveIterator.hasNext()) {
								ElementOfCTNumberCalibrationCurve elementOfCTNumberCalibrationCurve = elementOfCTNumberCalibrationCurveIterator.next();
									BigInteger hounsfieldMeasuredValue = elementOfCTNumberCalibrationCurve.getHounsfieldMeasuredValue();
									if (elementOfCTNumberCalibrationCurve.getMaterialIdentifier()!=null) {
										String materialIdentifier = elementOfCTNumberCalibrationCurve.getMaterialIdentifier();
									}
									float realDensityOfMaterialValue = elementOfCTNumberCalibrationCurve.getRealDensityOfMaterialValue();
									String realDensityOfMaterialUnit = elementOfCTNumberCalibrationCurve.getRealDensityOfMaterialUnit();
							}
						referenceCalibrationDate = CTNumberCalibrationCurveUsed.getReferenceCalibrationDate();
					}
					if (SPECTReconstruction.getCTNumberCalibrationCurveReference()!=null) {
						CTNumberCalibrationCurveReference CTNumberCalibrationCurveReference = SPECTReconstruction.getCTNumberCalibrationCurveReference();
							referenceCalibrationDate = CTNumberCalibrationCurveReference.getReferenceCalibrationDate();	
					}
					DICOMData NMTomoReconProduced = SPECTReconstruction.getNMTomoReconProduced();
						String NMTomoReconProduced_DICOMSeriesUID = NMTomoReconProduced.getDICOMSeriesUID();
						String NMTomoReconProduced_DICOMStudyUID = NMTomoReconProduced.getDICOMStudyUID();	
		}
		Iterator<TimeActivityCurveFitIn3DDosimetry> timeActivityCurveFitIn3DDosimetryIterator = threeDimDosimetrySlide1Workflow.getTimeActivityCurveFitIn3DDosimetry().iterator();
		while (timeActivityCurveFitIn3DDosimetryIterator.hasNext()) {
			TimeActivityCurveFitIn3DDosimetry timeActivityCurveFitIn3DDosimetry = timeActivityCurveFitIn3DDosimetryIterator.next();
				String PKAssessmentMethodUsed = timeActivityCurveFitIn3DDosimetry.getPKAssessmentMethodUsed();
				AdministeredActivity PostAdministeredActivityUsed = timeActivityCurveFitIn3DDosimetry.getPostAdministeredActivityUsed();
					String administeredActivityUnit = PostAdministeredActivityUsed.getAdministeredActivityUnit();
					float administeredActivityValue = PostAdministeredActivityUsed.getAdministeredActivityValue();
					String timestamp = PostAdministeredActivityUsed.getTimestamp();
				AdministeredActivity PreAdministeredActivityUsed = timeActivityCurveFitIn3DDosimetry.getPreAdministeredActivityUsed();
					administeredActivityUnit = PreAdministeredActivityUsed.getAdministeredActivityUnit();
					administeredActivityValue = PreAdministeredActivityUsed.getAdministeredActivityValue();
					timestamp = PreAdministeredActivityUsed.getTimestamp();
				processExecutionContext = timeActivityCurveFitIn3DDosimetry.getProcessExecutionContext();
				TimeIntegratedActivityCoefficientPerVOI TimeIntegratedActivityCoefficientPerVOIProduced = timeActivityCurveFitIn3DDosimetry.getTimeIntegratedActivityCoefficientPerVOIProduced();
					String timeIntegratedActivityCoefficientPerVOIUnit = TimeIntegratedActivityCoefficientPerVOIProduced.getTimeIntegratedActivityCoefficientPerVOIUnit();
					float timeIntegratedActivityCoefficientPerVOIValue = TimeIntegratedActivityCoefficientPerVOIProduced.getTimeIntegratedActivityCoefficientPerVOIValue();
					String VOIIdentifier = TimeIntegratedActivityCoefficientPerVOIProduced.getVOIIdentifier();
				TimeIntegratedActivityPerVOI TimeIntegratedActivityPerVOIProduced = timeActivityCurveFitIn3DDosimetry.getTimeIntegratedActivityPerVOIProduced();
					String timeIntegratedActivityPerVOIUnit = TimeIntegratedActivityPerVOIProduced.getTimeIntegratedActivityPerVOIUnit();
					float timeIntegratedActivityPerVOIValue = TimeIntegratedActivityPerVOIProduced.getTimeIntegratedActivityPerVOIValue();
					VOIIdentifier = TimeIntegratedActivityPerVOIProduced.getVOIIdentifier();
				Iterator<String> timePointIdentifierUsedIterator = timeActivityCurveFitIn3DDosimetry.getTimePointIdentifierUsed().iterator();
				while (timePointIdentifierUsedIterator.hasNext()) {
					String timePointIdentifier = timePointIdentifierUsedIterator.next();
				}
				String VOIIdentifierUsed = timeActivityCurveFitIn3DDosimetry.getVOIIdentifierUsed();
		}
		
		Iterator<VOIActivityDetermination> VOIActivityDeterminationIterator = threeDimDosimetrySlide1Workflow.getVOIActivityDetermination().iterator();
		while (VOIActivityDeterminationIterator.hasNext()) {
			VOIActivityDetermination VOIActivityDetermination = VOIActivityDeterminationIterator.next();
				Iterator<DataActivityPerVOIAtTimePoint> dataActivityPerVOIAtTimePointProducedIterator = VOIActivityDetermination.getDataActivityPerVOIAtTimePointProduced().iterator();
				while (dataActivityPerVOIAtTimePointProducedIterator.hasNext()) {
					DataActivityPerVOIAtTimePoint dataActivityPerVOIAtTimePointProduced = dataActivityPerVOIAtTimePointProducedIterator.next();
						String DataActivityUnit = dataActivityPerVOIAtTimePointProduced.getDataActivityUnit();
						float DataActivityValue = dataActivityPerVOIAtTimePointProduced.getDataActivityValue();
						String TimePointIdentifier = dataActivityPerVOIAtTimePointProduced.getTimePointIdentifier();
						String VOIIdentifier = dataActivityPerVOIAtTimePointProduced.getVOIIdentifier();
				}
				processExecutionContext = VOIActivityDetermination.getProcessExecutionContext();
					dateTimeProcessStarted = processExecutionContext.getDateTimeProcessStarted();
					performingInstitution = processExecutionContext.getPerformingInstitution();
				NMRelevantCalibrationReference SPECTCalibrationFactorReferenceUsed = VOIActivityDetermination.getSPECTCalibrationFactorReferenceUsed();
					String isotope = SPECTCalibrationFactorReferenceUsed.getIsotope();
					String referenceCalibrationDate = SPECTCalibrationFactorReferenceUsed.getReferenceCalibrationDate();
				NMRelevantCalibrationReference SPECTRecoveryCoefficientCurveReferenceUsed = VOIActivityDetermination.getSPECTRecoveryCoefficientCurveReferenceUsed();
					isotope = SPECTRecoveryCoefficientCurveReferenceUsed.getIsotope();
					referenceCalibrationDate = SPECTRecoveryCoefficientCurveReferenceUsed.getReferenceCalibrationDate();
				String timePointIdentifier = VOIActivityDetermination.getTimePointIdentifierUsed();
				VOIIdentifierUsedIterator = VOIActivityDetermination.getVOIIdentifierUsed().iterator();
				while (VOIIdentifierUsedIterator.hasNext()) {
					String VOIIdentifierUsed = VOIIdentifierUsedIterator.next();
				}
		}
	}
	
	public static void retreiveThreeDimDosimetrySlide2Workflow(ThreeDimDosimetrySlide2Workflow threeDimDosimetrySlide2Workflow) {
		DoseRateCurveFitVOITimeIntegration doseRateCurveFitVOITimeIntegration = threeDimDosimetrySlide2Workflow.getDoseRateCurveFitVOITimeIntegration();
			Iterator<MeanAbsorbedDoseInVOI> meanAbsorbedDoseInVOIProducedIterator = doseRateCurveFitVOITimeIntegration.getMeanAbsorbedDoseInVOIProduced().iterator();
			while (meanAbsorbedDoseInVOIProducedIterator.hasNext()) {
				MeanAbsorbedDoseInVOI meanAbsorbedDoseInVOIProduced = meanAbsorbedDoseInVOIProducedIterator.next();
					String meanAbsorbedDoseInVOIUnit = meanAbsorbedDoseInVOIProduced.getMeanAbsorbedDoseInVOIUnit();
					float meanAbsorbedDoseInVOIValue = meanAbsorbedDoseInVOIProduced.getMeanAbsorbedDoseInVOIValue();
					String VOIIdentifier = meanAbsorbedDoseInVOIProduced.getVOIIdentifier();
			}
			String PKAssessmentMethodUsed = doseRateCurveFitVOITimeIntegration.getPKAssessmentMethodUsed();
			ProcessExecutionContext processExecutionContext = doseRateCurveFitVOITimeIntegration.getProcessExecutionContext();
			Iterator<String> timePointIdentifierUsedIterator = doseRateCurveFitVOITimeIntegration.getTimePointIdentifierUsed().iterator();
			while (timePointIdentifierUsedIterator.hasNext()) {
				String timePointIdentifierUsed = timePointIdentifierUsedIterator.next();
			}
			Iterator<String> VOIIdentifierUsedIterator = doseRateCurveFitVOITimeIntegration.getVOIIdentifierUsed().iterator();
			while (VOIIdentifierUsedIterator.hasNext()) {
				String VOIIdentifierUsed = VOIIdentifierUsedIterator.next();
			}
			
		
		if (threeDimDosimetrySlide2Workflow.getRadioBiologicalCalculationInHybridOr3DSlide2Dosimetry()!=null) {
			RadioBiologicalCalculationInHybridOr3DSlide2Dosimetry RadioBiologicalCalculationInHybridOr3DSlide2Dosimetry = threeDimDosimetrySlide2Workflow.getRadioBiologicalCalculationInHybridOr3DSlide2Dosimetry();
				String effectiveDose = RadioBiologicalCalculationInHybridOr3DSlide2Dosimetry.getBiologicalEffectiveDose();
				processExecutionContext = RadioBiologicalCalculationInHybridOr3DSlide2Dosimetry.getProcessExecutionContext();
					String dateTimeProcessStarted = processExecutionContext.getDateTimeProcessStarted();
					String performingInstitution = processExecutionContext.getPerformingInstitution();
				String RadioBiologicalCalculationMethod = RadioBiologicalCalculationInHybridOr3DSlide2Dosimetry.getRadioBiologicalCalculationMethod();
				String RadioBiologicalCalculationParameters = RadioBiologicalCalculationInHybridOr3DSlide2Dosimetry.getRadioBiologicalCalculationParameters();
				VOIIdentifierUsedIterator = RadioBiologicalCalculationInHybridOr3DSlide2Dosimetry.getVOIIdentifierUsed().iterator();
				while (VOIIdentifierUsedIterator.hasNext()) {
					String VOIIdentifierUsed = VOIIdentifierUsedIterator.next();
				}
		}

		String dateTimeProcessStarted;
		String performingInstitution;
		if (threeDimDosimetrySlide2Workflow.getSPECTDataAcquisitionAndReconstruction()!=null) {
			Iterator<SPECTDataAcquisitionAndReconstruction> SPECTDataAcquisitionAndReconstructionIterator = threeDimDosimetrySlide2Workflow.getSPECTDataAcquisitionAndReconstruction().iterator();
			while (SPECTDataAcquisitionAndReconstructionIterator.hasNext()) {
				SPECTDataAcquisitionAndReconstruction SPECTDataAcquisitionAndReconstruction = SPECTDataAcquisitionAndReconstructionIterator.next();
					SPECTAcqCTAcqAndReconstruction SPECTAcqCTAcqAndReconstruction = SPECTDataAcquisitionAndReconstruction.getSPECTAcqCTAcqAndReconstruction();
						DICOMData CTReconProduced = SPECTAcqCTAcqAndReconstruction.getCTReconProduced();
							String CTReconProduced_DICOMSeriesUID = CTReconProduced.getDICOMSeriesUID();
							String CTReconProduced_DICOMStudyUID = CTReconProduced.getDICOMStudyUID();
						CTRelevantCalibrationReference CTRelevantCalibrationReference = SPECTAcqCTAcqAndReconstruction.getCTRelevantCalibrationReference();
							String CTRelevantCalibrationReferenceCalibrationDate = CTRelevantCalibrationReference.getReferenceCalibrationDate();
						NMRelevantCalibrationReference NMRelevantCalibrationReference = SPECTAcqCTAcqAndReconstruction.getNMRelevantCalibrationReference();
							String NMRelevantCalibrationReferenceCalibrationDate = NMRelevantCalibrationReference.getReferenceCalibrationDate();
						DICOMData NMTomoProduced = SPECTAcqCTAcqAndReconstruction.getNMTomoProduced();
							String NMTomoProduced_DICOMSeriesUID = NMTomoProduced.getDICOMSeriesUID();
							String NMTomoProduced_DICOMStudyUID = NMTomoProduced.getDICOMStudyUID();
					TimePointDescription timePointDescription = SPECTAcqCTAcqAndReconstruction.getTimePointDescription();
						String timePointCategory = timePointDescription.getTimePointCategory();
						String timePointDistanceFromReferenceEventUnit = timePointDescription.getTimePointDistanceFromReferenceEventUnit();
						BigInteger timePointDistanceFromReferenceEventValue = timePointDescription.getTimePointDistanceFromReferenceEventValue();
						String timePointIdentifier = timePointDescription.getTimePointIdentifier();
					SPECTReconstruction SPECTReconstruction = SPECTDataAcquisitionAndReconstruction.getSPECTReconstruction();
						CTNumberCalibrationCurveReference CTNumberCalibrationCurveReference = SPECTReconstruction.getCTNumberCalibrationCurveReference();
							String ReferenceCalibrationDate = CTNumberCalibrationCurveReference.getReferenceCalibrationDate();
						CTNumberCalibrationCurve CTNumberCalibrationCurveUsed = SPECTReconstruction.getCTNumberCalibrationCurveUsed();
							Iterator<ElementOfCTNumberCalibrationCurve> ElementOfCTNumberCalibrationCurveIterator = CTNumberCalibrationCurveUsed.getElementOfCTNumberCalibrationCurve().iterator();
							ReferenceCalibrationDate = CTNumberCalibrationCurveUsed.getReferenceCalibrationDate();
						DICOMData CTReconUsed = SPECTReconstruction.getCTReconUsed();
							String CTReconUsed_DICOMSeriesUID = CTReconUsed.getDICOMSeriesUID();
							String CTReconUsed_DICOMStudyUID = CTReconUsed.getDICOMStudyUID();
						DICOMData NMTomoReconProduced = SPECTReconstruction.getNMTomoReconProduced();
							String NMTomoReconProduced_DICOMSeriesUID = NMTomoReconProduced.getDICOMSeriesUID();
							String NMTomoReconProduced_DICOMStudyUID = NMTomoReconProduced.getDICOMStudyUID();
						DICOMData NMTomoUsed = SPECTReconstruction.getNMTomoUsed();
							String NMTomoUsed_DICOMSeriesUID = NMTomoUsed.getDICOMSeriesUID();
							String NMTomoUsed_DICOMStudyUID = NMTomoUsed.getDICOMStudyUID();
						processExecutionContext = SPECTReconstruction.getProcessExecutionContext();
							dateTimeProcessStarted = processExecutionContext.getDateTimeProcessStarted();
							performingInstitution = processExecutionContext.getPerformingInstitution();
						String reconstructionMethodAndCorrectionsUsed = SPECTReconstruction.getReconstructionMethodAndCorrectionsUsed();
						String timePointIdentifierUsed = SPECTReconstruction.getTimePointIdentifierUsed();
			}
		}
		
		Iterator<VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation> VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculationIterator = threeDimDosimetrySlide2Workflow.getVOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation().iterator();
		while (VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculationIterator.hasNext()) {
			VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation = VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculationIterator.next();
				EnergyDepositionRateCalculationIn3DDosimetry energyDepositionRateCalculationIn3DDosimetry = VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation.getEnergyDepositionRateCalculationIn3DDosimetry();
					DICOMData CTReconResampledOnNMReferenceUsed = energyDepositionRateCalculationIn3DDosimetry.getCTReconResampledOnNMReferenceUsed();
						String CTReconResampledOnNMReferenceUsed_DICOMSeriesUID = CTReconResampledOnNMReferenceUsed.getDICOMSeriesUID();
						String CTReconResampledOnNMReferenceUsed_DICOMStudyUID = CTReconResampledOnNMReferenceUsed.getDICOMStudyUID();
					DICOMData NMTomoReconUsed = energyDepositionRateCalculationIn3DDosimetry.getNMTomoReconUsed();
						String NMTomoReconUsed_DICOMSeriesUID = NMTomoReconUsed.getDICOMSeriesUID();
						String NMTomoReconUsed_DICOMStudyUID = NMTomoReconUsed.getDICOMStudyUID();
					processExecutionContext = energyDepositionRateCalculationIn3DDosimetry.getProcessExecutionContext();
						dateTimeProcessStarted = processExecutionContext.getDateTimeProcessStarted();
						performingInstitution = processExecutionContext.getPerformingInstitution();
					NonDICOMData ThreeDimEnergyDepositionRateMatrixAtTimePointProduced = energyDepositionRateCalculationIn3DDosimetry.getThreeDimEnergyDepositionRateMatrixAtTimePointProduced();
					if (ThreeDimEnergyDepositionRateMatrixAtTimePointProduced.getFHIRIdentifier()!=null) {
						String FHIRIdentifier = ThreeDimEnergyDepositionRateMatrixAtTimePointProduced.getFHIRIdentifier();
					}
					String nonDICOMDataClass = ThreeDimEnergyDepositionRateMatrixAtTimePointProduced.getNonDICOMDataClass();
					Iterator<String> nonDICOMDataFileNameIterator = ThreeDimEnergyDepositionRateMatrixAtTimePointProduced.getNonDICOMDataFileName().iterator();
					while (nonDICOMDataFileNameIterator.hasNext()) {
						String nonDICOMDataFileName = nonDICOMDataFileNameIterator.next();
					}
					String nonDICOMDataFormat = ThreeDimEnergyDepositionRateMatrixAtTimePointProduced.getNonDICOMDataFormat();
					String TimePointIdentifierUsed = energyDepositionRateCalculationIn3DDosimetry.getTimePointIdentifierUsed();
					VOIIdentifierUsedIterator = energyDepositionRateCalculationIn3DDosimetry.getVOIIdentifierUsed().iterator();
					while (VOIIdentifierUsedIterator.hasNext()) {
						String VOIIdentifierUsed = VOIIdentifierUsedIterator.next();
					}
					Iterator<String> VOIUsedIterator = energyDepositionRateCalculationIn3DDosimetry.getVOIUsed().iterator();
					while (VOIUsedIterator.hasNext()) {
						String VOIUsed = VOIUsedIterator.next();
					}
				SumAndScalingAbsorbedDoseRateCalculation sumAndScalingAbsorbedDoseRateCalculation = VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation.getSumAndScalingAbsorbedDoseRateCalculation();
					Iterator<AbsorbedDoseRatePerVOIAtTimePoint> absorbedDoseRatePerVOIAtTimePointProducedIterator = sumAndScalingAbsorbedDoseRateCalculation.getAbsorbedDoseRatePerVOIAtTimePointProduced().iterator();
					while (absorbedDoseRatePerVOIAtTimePointProducedIterator.hasNext()) {
						AbsorbedDoseRatePerVOIAtTimePoint absorbedDoseRatePerVOIAtTimePointProduced = absorbedDoseRatePerVOIAtTimePointProducedIterator.next();
							String absorbedDoseRateUnit = absorbedDoseRatePerVOIAtTimePointProduced.getAbsorbedDoseRateUnit();
							float absorbedDoseRateValue = absorbedDoseRatePerVOIAtTimePointProduced.getAbsorbedDoseRateValue();
					}
					processExecutionContext = sumAndScalingAbsorbedDoseRateCalculation.getProcessExecutionContext();
						dateTimeProcessStarted = processExecutionContext.getDateTimeProcessStarted();
						performingInstitution = processExecutionContext.getPerformingInstitution();
					String threeDimEnergyDepositionRateMatrixAtTimePointIdentifierUsed = sumAndScalingAbsorbedDoseRateCalculation.getThreeDimEnergyDepositionRateMatrixAtTimePointIdentifierUsed();
					TimePointIdentifierUsed = sumAndScalingAbsorbedDoseRateCalculation.getTimePointIdentifierUsed();
					VOIIdentifierUsedIterator = sumAndScalingAbsorbedDoseRateCalculation.getVOIIdentifierUsed().iterator();
					while(VOIIdentifierUsedIterator.hasNext()) {
						String VOIIdentifierUsed = VOIIdentifierUsedIterator.next();
					}
					VOIUsedIterator = sumAndScalingAbsorbedDoseRateCalculation.getVOIUsed().iterator();
					while (VOIUsedIterator.hasNext()) {
						String VOIUsed = VOIUsedIterator.next();
					}
				
				VOIActivityDetermination VOIActivityDetermination = VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation.getVOIActivityDetermination();
					Iterator<DataActivityPerVOIAtTimePoint> dataActivityPerVOIAtTimePointProducedIterator = VOIActivityDetermination.getDataActivityPerVOIAtTimePointProduced().iterator();
					while(dataActivityPerVOIAtTimePointProducedIterator.hasNext()) {
						DataActivityPerVOIAtTimePoint dataActivityPerVOIAtTimePointProduced = dataActivityPerVOIAtTimePointProducedIterator.next();
							String dataActivityUnit = dataActivityPerVOIAtTimePointProduced.getDataActivityUnit();
							float dataActivityValue = dataActivityPerVOIAtTimePointProduced.getDataActivityValue();
							String timePointIdentifier = dataActivityPerVOIAtTimePointProduced.getTimePointIdentifier();
							String VOIIdentifier = dataActivityPerVOIAtTimePointProduced.getVOIIdentifier();
					}
					processExecutionContext = VOIActivityDetermination.getProcessExecutionContext();
						dateTimeProcessStarted = processExecutionContext.getDateTimeProcessStarted();
						performingInstitution = processExecutionContext.getPerformingInstitution();
					NMRelevantCalibrationReference SPECTCalibrationFactorReferenceUsed = VOIActivityDetermination.getSPECTCalibrationFactorReferenceUsed();
						String isotope = SPECTCalibrationFactorReferenceUsed.getIsotope();
						String referenceCalibrationDate = SPECTCalibrationFactorReferenceUsed.getReferenceCalibrationDate();
					NMRelevantCalibrationReference SPECTRecoveryCoefficientCurveReferenceUsed = VOIActivityDetermination.getSPECTRecoveryCoefficientCurveReferenceUsed();
						isotope = SPECTRecoveryCoefficientCurveReferenceUsed.getIsotope();
						referenceCalibrationDate = SPECTRecoveryCoefficientCurveReferenceUsed.getReferenceCalibrationDate();
					TimePointIdentifierUsed = VOIActivityDetermination.getTimePointIdentifierUsed();
					VOIIdentifierUsedIterator = VOIActivityDetermination.getVOIIdentifierUsed().iterator();
					while (VOIIdentifierUsedIterator.hasNext()) {
						String VOIIdentifierUsed = VOIIdentifierUsedIterator.next();
					}
				
				VOISegmentationVOIMassDetermination VOISegmentationVOIMassDetermination = VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation.getVOISegmentationVOIMassDetermination();
					Iterator<CountsPerVOIAtTimePoint> CountsPerVOIAtTimePointProducedIterator = VOISegmentationVOIMassDetermination.getCountsPerVOIAtTimePointProduced().iterator();
					while (CountsPerVOIAtTimePointProducedIterator.hasNext()) {
						CountsPerVOIAtTimePoint CountsPerVOIAtTimePointProduced = CountsPerVOIAtTimePointProducedIterator.next();
							String countsUnit = CountsPerVOIAtTimePointProduced.getCountsUnit();
							float countsValue = CountsPerVOIAtTimePointProduced.getCountsValue();
							String timePointIdentifier = CountsPerVOIAtTimePointProduced.getTimePointIdentifier();
							String VOIIdentifier = CountsPerVOIAtTimePointProduced.getVOIIdentifier();
					}
					Iterator<DICOMData> CTReconResampledOnNMReferenceProducedIterator = VOISegmentationVOIMassDetermination.getCTReconResampledOnNMReferenceProduced().iterator();
					while(CTReconResampledOnNMReferenceProducedIterator.hasNext()) {
						DICOMData CTReconResampledOnNMReferenceProduced = CTReconResampledOnNMReferenceProducedIterator.next();
							String CTReconResampledOnNMReferenceProduced_DICOMSeriesUID = CTReconResampledOnNMReferenceProduced.getDICOMSeriesUID();
							String CTReconResampledOnNMReferenceProduced_DICOMSStudyUID = CTReconResampledOnNMReferenceProduced.getDICOMStudyUID();
					}
					Iterator<MassPerVOIAtTimePoint> MassPerVOIAtTimePointProducedIterator = VOISegmentationVOIMassDetermination.getMassPerVOIAtTimePointProduced().iterator();
					while (MassPerVOIAtTimePointProducedIterator.hasNext()) {
						MassPerVOIAtTimePoint MassPerVOIAtTimePointProduced = MassPerVOIAtTimePointProducedIterator.next();
							String massUnit = MassPerVOIAtTimePointProduced.getMassUnit();
							float massValue = MassPerVOIAtTimePointProduced.getMassValue();
							String VOIIdentifier = MassPerVOIAtTimePointProduced.getVOIIdentifier();
					}
					NMTomoReconUsed = VOISegmentationVOIMassDetermination.getNMTomoReconUsed();
						NMTomoReconUsed_DICOMSeriesUID = NMTomoReconUsed.getDICOMSeriesUID();
						NMTomoReconUsed_DICOMStudyUID= NMTomoReconUsed.getDICOMStudyUID();
					processExecutionContext = VOISegmentationVOIMassDetermination.getProcessExecutionContext();
						dateTimeProcessStarted = processExecutionContext.getDateTimeProcessStarted();
						performingInstitution = processExecutionContext.getPerformingInstitution();
					TimePointIdentifierUsed = VOISegmentationVOIMassDetermination.getTimePointIdentifierUsed();
					Iterator<VOI> VOIProducedIterator = VOISegmentationVOIMassDetermination.getVOIProduced().iterator();
					while(VOIProducedIterator.hasNext()) {
						VOI VOIProduced = VOIProducedIterator.next();
							if (VOIProduced.getDICOMVOIContainer()!=null) {
								Iterator<DICOMData> DICOMVOIContainerIterator = VOIProduced.getDICOMVOIContainer().iterator();
								while (DICOMVOIContainerIterator.hasNext()) {
									DICOMData DICOMVOIContainer = DICOMVOIContainerIterator.next();
										String DICOMVOIContainer_DICOMSeriesUID = DICOMVOIContainer.getDICOMSeriesUID();
										String DICOMVOIContainer_DICOMStudyUID = DICOMVOIContainer.getDICOMStudyUID();
								}
							}
							if (VOIProduced.getNonDICOMVOIContainer()!=null) {
								Iterator<NonDICOMData> nonDICOMVOIContainerIterator = VOIProduced.getNonDICOMVOIContainer().iterator();
								while (nonDICOMVOIContainerIterator.hasNext()) {
									NonDICOMData nonDICOMVOIContainer = nonDICOMVOIContainerIterator.next();
										if (nonDICOMVOIContainer.getFHIRIdentifier()!=null) {
											String nonDICOMVOIContainer_FHIRIdentifier = nonDICOMVOIContainer.getFHIRIdentifier();
										}
										String nonDICOMVOIContainer_NonDICOMDataClass = nonDICOMVOIContainer.getNonDICOMDataClass();
										String nonDICOMVOIContainer_NonDICOMDataFormat = nonDICOMVOIContainer.getNonDICOMDataFormat();
										Iterator<String> nonDICOMVOIContainer_NonDICOMDataFileNameIterator = nonDICOMVOIContainer.getNonDICOMDataFileName().iterator();		
										while (nonDICOMVOIContainer_NonDICOMDataFileNameIterator.hasNext()) {
											String nonDICOMVOIContainer_NonDICOMDataFileName = nonDICOMVOIContainer_NonDICOMDataFileNameIterator.next();
										}
								}
							}
							String organOrTissue = VOIProduced.getOrganOrTissue();
							String VOIIdentifier = VOIProduced.getVOIIdentifier();
					}
					String VOISegmentationMethodUsed = VOISegmentationVOIMassDetermination.getVOISegmentationMethodUsed();
		}
		
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
							
							//DICOMData imageDataUsed = ctMonteCarloDosimetry.getCalculationOfVoxelMap().getDICOMCTImageDataUsed();
							//String series = imageDataUsed.getDICOMSeriesUID();
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
 	

	
}
