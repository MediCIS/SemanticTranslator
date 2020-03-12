package repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.jena.ontology.Individual;
import org.apache.jena.rdf.model.ModelFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javaXSDclass.AbsorbedDoseCalculationInVOI;
import javaXSDclass.AbsorbedDoseInVOI;
import javaXSDclass.AbsorbedDoseInVOIContainer;
import javaXSDclass.AbsorbedDosePerVOIType;
import javaXSDclass.AbsorbedDoseRatePerVOIAtTimePoint;
import javaXSDclass.AcquisitionSettings;
import javaXSDclass.ActivityUnit;
import javaXSDclass.AdministeredActivity;
import javaXSDclass.AttenuatorType;
import javaXSDclass.CTNumberCalibrationCurve;
import javaXSDclass.CTNumberCalibrationCurveReference;
import javaXSDclass.CTSegmentation;
import javaXSDclass.CalculationAlgorithmUsed;
import javaXSDclass.CalculationOfAbsorbedDosesInVOIs;
import javaXSDclass.CalculationOfVoxelMap;
import javaXSDclass.CalibrationWorkflow;
import javaXSDclass.CountsPerVOIAtTimePoint;
import javaXSDclass.CountsUnit;
import javaXSDclass.CurveFittingMethod;
import javaXSDclass.DICOMData;
import javaXSDclass.DataActivityPerVOIAtTimePoint;
import javaXSDclass.DoseRateCurveFitVOITimeIntegration;
import javaXSDclass.ElementOfCTNumberCalibrationCurve;
import javaXSDclass.EnergyDepositionRateCalculationIn3DDosimetry;
import javaXSDclass.GeometricalTransformation;
import javaXSDclass.GeometricalTransformationContainer;
import javaXSDclass.HybridDosimetryworkflow;
import javaXSDclass.Isotope;
import javaXSDclass.KernelLimitForConvolutions;
import javaXSDclass.MassPerVOIAtTimePoint;
import javaXSDclass.MethodSettingType;
import javaXSDclass.NMRelevantCalibrationReference;
import javaXSDclass.NonDICOMData;
import javaXSDclass.NonDICOMDataFormat;
import javaXSDclass.NonDicomFileSetDescriptor;
import javaXSDclass.OrganMass;
import javaXSDclass.OrganOrTissue;
import javaXSDclass.PlanarCalibrationWorkflow;
import javaXSDclass.ProcessExecutionContext;
import javaXSDclass.RadioBiologicalCalculationIn3DSlide1Dosimetry;
import javaXSDclass.RadioBiologicalCalculationInHybridOr3DSlide2Dosimetry;
import javaXSDclass.Radiopharmaceutical;
import javaXSDclass.RealDensityOfMaterialUnit;
import javaXSDclass.RegistrationVOISegmentationAndPropagation;
import javaXSDclass.RegistrationVOISegmentationAndPropagationContainer;
import javaXSDclass.SPECTAcqCTAcqAndReconstruction;
import javaXSDclass.SPECTCTCalibrationWorkflow;
import javaXSDclass.SPECTDataAcquisitionAndReconstruction;
import javaXSDclass.SPECTReconstruction;
import javaXSDclass.Segmentation;
import javaXSDclass.SimpleCTMonteCarloDosimetry;
import javaXSDclass.SumAndScalingAbsorbedDoseRateCalculation;
import javaXSDclass.ThreeDimDosimetrySlide1Workflow;
import javaXSDclass.ThreeDimDosimetrySlide2Workflow;
import javaXSDclass.TimeActivityCurveFitIn3DDosimetry;
import javaXSDclass.TimeIntegratedActivityCoefficientPerVOI;
import javaXSDclass.TimeIntegratedActivityPerVOI;
import javaXSDclass.TimePointDescriptionElement;
import javaXSDclass.TimeUnit;
import javaXSDclass.TwoDimDosimetryworkflow;
import javaXSDclass.VOI;
import javaXSDclass.VOIActivityDetermination;
import javaXSDclass.VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation;
import javaXSDclass.VOISegmentationVOIMassDetermination;
import javaXSDclass.VoxelBasedDistributionOfAbsorbedDoseType;
import javaXSDclass.WP2Subtask212WorkflowData;

// XSD Version 104

public class TranslateNonDicomData extends OntologyPopulator {

	static Hashtable<String, Individual> tableVOI = new Hashtable<String, Individual>();
	static Hashtable<String, Individual> tableROI = new Hashtable<String, Individual>();
	static Hashtable<String, Individual> tableVOIorgans = new Hashtable<String, Individual>();
	static Hashtable<String, String> tableVOIname = new Hashtable<String, String>();
	static Hashtable<String, Individual> tableTimePoint = new Hashtable<String, Individual>();
	static Hashtable<String, Individual> tableInputOutput = new Hashtable<String, Individual>();
	static Hashtable<String, Individual> tableSegmentation = new Hashtable<String, Individual>();
	static Hashtable<String, Individual> tableVoxelAbsorbedDoseMap = new Hashtable<String, Individual>();
	static Hashtable<String, Individual> tableTransformation = new Hashtable<String, Individual>();
	static Hashtable<String, Individual> tableInternalRadiotherapy = new Hashtable<String, Individual>();
	static Hashtable<String, Individual> tableRadiopharmaceuticalAdmin = new Hashtable<String, Individual>();

	static Individual clinicalresearchStudy;
	static Individual human;
	static Individual patient;
	static Individual internalRadiotherapy;
	static Individual RadiopharmaceuticalAdmin;
	static String patientID;
	static Individual preAdministeredActivityIndiv;
	static Individual postAdministeredActivityIndiv;
	static Individual roleOfOrganization;
	static String keyInternalRadioTherapyRadiophamarceuticalAdminsitration;


	public static void translateNonDicomData(NonDicomFileSetDescriptor nonDicomFileSetDescriptor) { // 1st function to read XML, check what is inside and call the appropritae function
		populateModel = ModelFactory.createOntologyModel();
		if (model==null) {model = Application.getModel();}
		if (memory==null) {memory = Application.memory;}
		logger.error("translateNonDicomData is Work in Progress");

		clinicalresearchStudy = retrieveClinicalResearchStudy(nonDicomFileSetDescriptor.getReferencedClinicalResearchStudy().getClinicalResearchStudyID());
		patientID = nonDicomFileSetDescriptor.getPatientId();
		patient = memory.getPatientbyId(patientID);
		human = memory.getHuman(patientID);

		if (nonDicomFileSetDescriptor.getAcquisitionSettings()!= null) {
			translateAcquisitionSettings(nonDicomFileSetDescriptor.getAcquisitionSettings());
		}

		if (nonDicomFileSetDescriptor.getWP2Subtask212WorkflowData()!=null) {
			clinicalresearchStudy = createIndiv("clinical_research_study_755523_subtask2.1.2", model.getResource("http://medicis.univ-rennes1.fr/ontologies/ontospm/OntoMEDIRAD.owl#clinical_research_study_755523_subtask2.1.2"));
			retrieveSubtastk212(nonDicomFileSetDescriptor);
		}

		/*
		if (nonDicomFileSetDescriptor.getCalibrationWorkflow()!=null) {
			CalibrationWorkflow calibrationWorkflow = nonDicomFileSetDescriptor.getCalibrationWorkflow();
			SPECTCTCalibrationWorkflow SPECTCTCalibrationWorkflow = calibrationWorkflow.getSPECTCTCalibrationWorkflow();
			//retreiveSPECTCTCalibrationWorkflow(SPECTCTCalibrationWorkflow);
			PlanarCalibrationWorkflow planarCalibrationWorkflow = calibrationWorkflow.getPlanarCalibrationWorkflow();
			//retreivePlanarCalibrationWorkflow(planarCalibrationWorkflow);
		}

		if (nonDicomFileSetDescriptor.getHybridDosimetryworkflow()!=null) {
			HybridDosimetryworkflow hybridDosimetryworkflow = nonDicomFileSetDescriptor.getHybridDosimetryworkflow();
			//retrieveHybridDosimetryworkflow(hybridDosimetryworkflow);
		}

		if (nonDicomFileSetDescriptor.getTwoDimDosimetryworkflow()!=null) {
			TwoDimDosimetryworkflow twoDimDosimetryworkflow = nonDicomFileSetDescriptor.getTwoDimDosimetryworkflow();
			//retrieveTwoDimDosimetryworkflow(twoDimDosimetryworkflow);
		}
		*/

		if (nonDicomFileSetDescriptor.getThreeDimDosimetrySlide1Workflow()!=null) {
			ThreeDimDosimetrySlide1Workflow threeDimDosimetrySlide1Workflow = nonDicomFileSetDescriptor.getThreeDimDosimetrySlide1Workflow();
			retreiveThreeDimDosimetrySlide1Workflow(threeDimDosimetrySlide1Workflow);
		} 

		if (nonDicomFileSetDescriptor.getThreeDimDosimetrySlide2Workflow()!=null) {
			ThreeDimDosimetrySlide2Workflow threeDimDosimetrySlide2Workflow = nonDicomFileSetDescriptor.getThreeDimDosimetrySlide2Workflow();
			retreiveThreeDimDosimetrySlide2Workflow(threeDimDosimetrySlide2Workflow);
		}
		// Insérer ici les autres workflows

	}

	
	public static void translateTotalAbsorbedDosePerVOI(AbsorbedDoseInVOIContainer absorbedDoseInVOIContainer, Individual absorbedDoseMethod, Individual calculationOfMeanAbsorbedDosesInVOI) {
		Iterator<AbsorbedDoseInVOI> absorbedDoseInVOIProducedIterator = absorbedDoseInVOIContainer.getAbsorbedDoseInVOIProduced().iterator();
		while (absorbedDoseInVOIProducedIterator.hasNext()) {
			AbsorbedDoseInVOI absorbedDoseInVOI = absorbedDoseInVOIProducedIterator.next();
			Individual absorbedDose = createIndiv(generateName("total_absorbed_dose_per_VOI"), model.getResource(racineURI+"total_absorbed_dose_per_VOI"));
			addDataProperty(absorbedDose, "http://purl.obolibrary.org/obo/IAO_0000004",absorbedDoseInVOI.getAbsorbedDoseInVOIValue());
			addObjectProperty(absorbedDose, racineURI+"is_about", clinicalresearchStudy);
			//addObjectProperty(absorbedDose, racineObo+"BFO_0000132", clinicalresearchStudy);
			addObjectProperty(absorbedDose, racineURI+"has_protocol", absorbedDoseMethod);
			addObjectProperty(absorbedDose, racineURI+"is_specified_output_of", calculationOfMeanAbsorbedDosesInVOI);
			switch (absorbedDoseInVOI.getAbsorbedDoseUnit()) {
			case GRAY:
				addObjectProperty(absorbedDose, racineObo+"IAO_0000039", getUnit("gray"));
				break;
			case MILLIGRAY:
				addObjectProperty(absorbedDose, racineURI+"IAO_0000039", getUnit("mGy"));
				break;
			case MILLIGRAY_PER_100_MILLIAMPERE_SECOND:
				addObjectProperty(absorbedDose, racineURI+"IAO_0000039", getUnit("milligray per (100 milliampere second)"));
				break;
			case MILLIGRAY_PER_MILLIGRAY_PER_100_MILLIAMPERE_SECOND:
				addObjectProperty(absorbedDose, racineURI+"IAO_0000039", getUnit("milligray per (milligray per (100 milliampere second)"));
				break;
			case MILLIGRAY_PER_MILLIGRAY_PER_1_MILLIAMPERE_SECOND:
				addObjectProperty(absorbedDose, racineURI+"IAO_0000039", getUnit("milligray per (milligray per (1 milliampere second))"));
				break;
			default:
				break;
			}

			if (absorbedDoseInVOI.getAbsorbedDoseInVOIUncertainty()!=null) {
				Individual uncertainty = createIndiv(generateName("range_of_uncertainty_value"), model.getResource(racineURI+"range_of_uncertainty_value"));
				addDataProperty(uncertainty, "http://purl.obolibrary.org/obo/IAO_0000004", absorbedDoseInVOI.getAbsorbedDoseInVOIUncertainty()); 
				addObjectProperty(uncertainty, racineURI+"is_about", absorbedDose);
				switch (absorbedDoseInVOI.getAbsorbedDoseUnit()) {
				case GRAY:
					addObjectProperty(uncertainty, racineURI+"IAO_0000039", getUnit("gray"));
					break;
				case MILLIGRAY:
					addObjectProperty(uncertainty, racineObo+"IAO_0000039", getUnit("mGy"));
					break;
				case MILLIGRAY_PER_100_MILLIAMPERE_SECOND:
					addObjectProperty(uncertainty, racineObo+"IAO_0000039", getUnit("milligray per (100 milliampere second)"));
					break;
				case MILLIGRAY_PER_MILLIGRAY_PER_100_MILLIAMPERE_SECOND:
					addObjectProperty(uncertainty, racineObo+"IAO_0000039", getUnit("milligray per (milligray per (100 milliampere second)"));
					break;
				case MILLIGRAY_PER_MILLIGRAY_PER_1_MILLIAMPERE_SECOND:
					addObjectProperty(uncertainty, racineObo+"IAO_0000039", getUnit("milligray per (milligray per (1 milliampere second))"));
					break;
				default:
					break;
				}
			}

			Iterator<BigInteger> VOIIdentifierUsedIterator = absorbedDoseInVOI.getVOIIdentifierList().getVOIIdentifierUsed().iterator();
			while (VOIIdentifierUsedIterator.hasNext()) {
				BigInteger VOIIdentifierUsed = VOIIdentifierUsedIterator.next();
				Individual voi = tableVOI.get(VOIIdentifierUsed.toString());
				addObjectProperty(calculationOfMeanAbsorbedDosesInVOI, racineURI+"has_specified_input", voi);
				addObjectProperty(absorbedDose, racineURI+"is_quantity_measured_in", voi);
			}	
			addObjectProperty(calculationOfMeanAbsorbedDosesInVOI, racineURI+"has_specified_output", absorbedDose);
		}
	}
	
	public static Individual translateSegmentation(Segmentation segmentation) {
		Individual imageSegmentation = createIndiv(generateName("segmentation_dataset"), model.getResource(racineURI+"segmentation_dataset"));
		addDataProperty(imageSegmentation, racineURI+"has_id", segmentation.getSegmentationIdentifier().toString());
		tableSegmentation.put(segmentation.getSegmentationIdentifier().toString(), imageSegmentation);
		Iterator<VOI> VOIProducedIterator = segmentation.getVOIContainer().getVOIProduced().iterator();
		while (VOIProducedIterator.hasNext()) {
			VOI VOIProduced = VOIProducedIterator.next();
			Individual voi; Individual organ = null;
			String VOIIdentifier = VOIProduced.getVOIIdentifier().toString();
			if (tableVOI.contains(VOIIdentifier)) {
				voi = tableVOI.get(VOIIdentifier);
			} else {
				voi = createIndiv(generateName("VOI"), model.getResource(racineURI+"VOI"));
				tableVOI.put(VOIIdentifier, voi);
				addDataProperty(voi, racineURI+"has_id", VOIIdentifier);
				organ = getOrganOrTissue(VOIProduced.getOrganOrTissue());
				addObjectProperty(organ,racineObo+"BFO_0000175", human);
				addObjectProperty(voi,racineURI+"represents", organ);
				addObjectProperty(voi,racineURI+"has_patient", human);
				addObjectProperty(imageSegmentation, racineObo+"BFO_0000110", voi);
			}

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

			OrganMass organMass = VOIProduced.getOrganMass();
			Individual mass = createIndiv(generateName("patient_organ_mass_at_timepoint"), model.getResource(racineURI+"patient_organ_mass_at_timepoint"));
			addDataProperty(mass, "http://purl.obolibrary.org/obo/IAO_0000004", organMass.getOrganMassValue());
			switch (organMass.getOrganMassUnit()) {
			case GRAM:
				addObjectProperty(mass, racineObo+"IAO_0000039", getUnit("gram"));
				break;
			case KILOGRAM:
				addObjectProperty(mass, racineObo+"IAO_0000039", getUnit("kilogram"));
				break;
			}
			addObjectProperty(mass, racineURI+"is_quantity_measured_at", timePoint);
			addObjectProperty(mass, racineURI+"is_quantity_measured_in", organ);

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
		return imageSegmentation;
	}

	public static void translateAcquisitionSettings(AcquisitionSettings settings) {	
		String siteadministeringthetreatment = settings.getSiteadministeringthetreatment();

		String dateandtimeofinjection = settings.getDateandtimeofinjection();
		
		keyInternalRadioTherapyRadiophamarceuticalAdminsitration = siteadministeringthetreatment+"_"+dateandtimeofinjection;
		
		Individual RadiopharmaceuticalAdmin;
		if (tableRadiopharmaceuticalAdmin.containsKey(keyInternalRadioTherapyRadiophamarceuticalAdminsitration)) {
			RadiopharmaceuticalAdmin = tableRadiopharmaceuticalAdmin.get(keyInternalRadioTherapyRadiophamarceuticalAdminsitration);
		} else {
			RadiopharmaceuticalAdmin = createIndiv(generateName("radiopharmaceutical_administration"), model.getResource(racineURI+"radiopharmaceutical_administration"));
			tableRadiopharmaceuticalAdmin.put(keyInternalRadioTherapyRadiophamarceuticalAdminsitration, RadiopharmaceuticalAdmin);
			addObjectProperty(roleOfOrganization,racineObo+"BFO_0000054",internalRadiotherapy);
			if (dateandtimeofinjection.contains(" ")) {
				addDataProperty(RadiopharmaceuticalAdmin, racineURI+"has_beginning_date", dateandtimeofinjection.split(" ")[0]);
				addDataProperty(RadiopharmaceuticalAdmin, racineURI+"has_beginning_time", dateandtimeofinjection.split(" ")[1]);
			} else {
				addDataProperty(RadiopharmaceuticalAdmin, racineURI+"has_beginning_date", dateandtimeofinjection);
				addDataProperty(RadiopharmaceuticalAdmin, racineURI+"has_beginning_time", dateandtimeofinjection);
			}
		}

		AdministeredActivity preAdministeredActivity = settings.getPreAdministeredActivity();
		preAdministeredActivityIndiv = createIndiv(generateName("pre-administration_measured_activity"), model.getResource(racineDCM+"113508"));
		ActivityUnit preActivityUnit = preAdministeredActivity.getActivityUnit();
		addObjectProperty(preAdministeredActivityIndiv,"http://purl.obolibrary.org/obo/IAO_0000039", getUnit(preActivityUnit.toString()));
		float preAdministeredActivityValue = preAdministeredActivity.getAdministeredActivityValue();
		addDataProperty(preAdministeredActivityIndiv,"http://purl.obolibrary.org/obo/IAO_0000004", preAdministeredActivityValue);
		addObjectProperty(preAdministeredActivityIndiv, racineURI+"is_about", RadiopharmaceuticalAdmin);
		addDataProperty(preAdministeredActivityIndiv, racineURI+"has_measurement_date_and_time", dateandtimeofinjection);
		
		if (settings.getPostAdministeredActivity()!=null) {
			AdministeredActivity postAdministeredActivity = settings.getPostAdministeredActivity();
			postAdministeredActivityIndiv = createIndiv(generateName("post-administration_measured_activity"), model.getResource(racineDCM+"113509"));
			ActivityUnit postActivityUnit = postAdministeredActivity.getActivityUnit();
			addObjectProperty(postAdministeredActivityIndiv,"http://purl.obolibrary.org/obo/IAO_0000039", getUnit(postActivityUnit.toString()));
			float postAdministeredActivityValue = postAdministeredActivity.getAdministeredActivityValue();
			addDataProperty(postAdministeredActivityIndiv,"http://purl.obolibrary.org/obo/IAO_0000004", postAdministeredActivityValue);
			addObjectProperty(postAdministeredActivityIndiv, racineURI+"is_about", RadiopharmaceuticalAdmin);
			addDataProperty(postAdministeredActivityIndiv, racineURI+"has_measurement_date_and_time", dateandtimeofinjection);

		}

		Individual radiopharmaceuticalIndiv = null;
		switch(settings.getRadiopharmaceutical()) {
		case SODIUM_IODIDE_I_131:
			radiopharmaceuticalIndiv = createIndiv(model.getResource("http://purl.bioontology.org/ontology/SNMI/C-B1086"));
			break;
		default: 
			break;
		}
		addObjectProperty(RadiopharmaceuticalAdmin, racineURI+"has_specified_input", radiopharmaceuticalIndiv);

		Isotope radionuclide = settings.getIsotope();
		Individual atom = null;
		switch (settings.getIsotope()) {
		case IODINE_131:
			atom = createIndiv(model.getResource("http://medicis.univ-rennes1.fr/ontologies/ontospm/Radionuclides_for_OntoMEDIRAD.owl#iodine_131_atom"));
			break;
		case LUTETIUM_177:
			atom = createIndiv(model.getResource("http://medicis.univ-rennes1.fr/ontologies/ontospm/Radionuclides_for_OntoMEDIRAD.owl#lutetium_177_atom"));
			break;
		case RADIUM_223:
			atom = createIndiv(model.getResource("http://medicis.univ-rennes1.fr/ontologies/ontospm/Radionuclides_for_OntoMEDIRAD.owl#radium_223_atom"));
			break;
		case RHENIUM_188:
			atom = createIndiv(model.getResource("http://medicis.univ-rennes1.fr/ontologies/ontospm/Radionuclides_for_OntoMEDIRAD.owl#rhenium_188_atom"));
			break;
		case TECHNETIUM_99_M:
			atom = createIndiv(model.getResource("http://medicis.univ-rennes1.fr/ontologies/ontospm/Radionuclides_for_OntoMEDIRAD.owl#technetium_99m_atom"));
			break;
		case TERBIUM_161:
			//TODO classe manquante
			break;
		case YTTRIUM_90:
			atom = createIndiv(model.getResource("http://medicis.univ-rennes1.fr/ontologies/ontospm/Radionuclides_for_OntoMEDIRAD.owl#yttrium_90_atom"));
			break;
		}
		addObjectProperty(RadiopharmaceuticalAdmin, racineURI+"has_specified_input", atom);
		addObjectProperty(RadiopharmaceuticalAdmin, racineObo+"BFO_0000132", internalRadiotherapy);
	}

	public static void translateProcessExecutionContext(ProcessExecutionContext processExecutionContext, Individual indiv) {
		addDataProperty(indiv, racineURI+"has_beginning", processExecutionContext.getDateTimeProcessStarted());
		String performingInstitution = processExecutionContext.getPerformingInstitution();
		Individual institution = memory.getInstitution(performingInstitution);
		roleOfOrganization = memory.getRoleOfResponsibleOrganization(performingInstitution);
		addObjectProperty( roleOfOrganization, racineObo+"BFO_0000054", indiv);
		addObjectProperty(institution, racineObo+"BFO_0000161", roleOfOrganization);
	}

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
			nonDICOMDataIndividual.addOntClass(model.getResource(racineURI+"segmentation_dataset")); 
			break;
		case "voxel activity map":
			nonDICOMDataIndividual.addOntClass(model.getResource(racineURI+"voxel_data_activity")); 
			break;
		case "3D Energy Deposition Rate Matrix":
			nonDICOMDataIndividual.addOntClass(model.getResource(racineURI+"3D_energy_deposition_rate_at_timepoint")); 
			break;
		case "NM Tomo Reconstruction":
			nonDICOMDataIndividual.addOntClass(model.getResource(racineURI+"NM_tomo_dataset")); 
			break;
		case "CT Reconstruction":
			nonDICOMDataIndividual.addOntClass(model.getResource(racineURI+"CT_image_dataset")); 
			break;
		case "Linear transformation matrix":
			nonDICOMDataIndividual.addOntClass(model.getResource(racineURI+"linear_transformation_matrix")); 
			break;
		case "BSpline transformation":
			nonDICOMDataIndividual.addOntClass(model.getResource(racineURI+"B_spline_transformation")); 
			break;
		case "Advanced elastix transformation":
			nonDICOMDataIndividual.addOntClass(model.getResource(racineURI+"advanced_elastix_transformation")); 
			break;
		case "Density Image":
			nonDICOMDataIndividual.addOntClass(model.getResource(racineURI+"density_image")); 
			break;
		}

		NonDICOMDataFormat format = nonDICOMData.getNonDICOMDataFormat();
		switch (format) {
		case GIF_FORMAT_EMBEDDING_IMAGE_J_CONTOURS:
			addObjectProperty(nonDICOMDataIndividual, racineURI+"has_format", createIndiv(model.getResource(racineURI+"GIF_format_embedding_imageJ_contours")));
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
		case HDF_4_FORMAT:
			addObjectProperty(nonDICOMDataIndividual, racineURI+"has_format", createIndiv(model.getResource(racineURI+"HDF4_format")));
			break;
		case HDF_5_FORMAT:
			addObjectProperty(nonDICOMDataIndividual, racineURI+"has_format", createIndiv(model.getResource(racineURI+"HDF5_format")));
			break;
		default:
			break;						
		}

		String fileName = nonDICOMData.getNonDICOMDataFileName();
		addDataProperty(nonDICOMDataIndividual, racineURI+"has_name", fileName);

		addDataProperty(nonDICOMDataIndividual, racineURI+"has_IRDBB_FHIR_handle", nonDICOMData.getFHIRIdentifier());

		if (nonDICOMData.getTransformationsUsed()!=null) {
			List<String> transformationList = nonDICOMData.getTransformationsUsed().getTransformationIdentifier();
			for (int i = 0; i<transformationList.size(); i++) {
				String transformationId = transformationList.get(i);
				Individual transformation = tableTransformation.get(transformationId);
				addDataProperty(transformation, racineURI+"has_id", i);
			}
		}

	}

	public static void translateGeometricalTransformation(GeometricalTransformationContainer geometricalTransformationContainer, Individual outputFrom, Individual timepoint ) {
		List<GeometricalTransformation> geometricalTransformationList = geometricalTransformationContainer.getGeometricalTransformation();
		for (int i = 0; i<geometricalTransformationList.size(); i++) {
			GeometricalTransformation geometricalTransformation = geometricalTransformationList.get(i);
			Individual geometricalTransfo;
			switch (geometricalTransformation.getTransformationType() ) {
			case ADVANCED_ELASTIX_TRANSFORMATION:
				geometricalTransfo = createIndiv(generateName("advanced_elastix_transformation"), model.getResource(racineURI+"advanced_elastix_transformation"));
				break;
			case B_SPLINE_TRANSFORMATION:
				geometricalTransfo = createIndiv(generateName("B_spline_transformation"), model.getResource(racineURI+"B_spline_transformation"));
				break;
			case LINEAR_TRANSFORMATION_MATRIX:
				geometricalTransfo = createIndiv(generateName("linear_transformation_matrix"), model.getResource(racineURI+"linear_transformation_matrix"));
				break;
			default:
				geometricalTransfo = createIndiv(generateName("geometrical_transformation"), model.getResource(racineURI+"geometrical_transformation"));
				break;
			}

			String transfoIdent = geometricalTransformation.getTransformationIdentifier();
			tableTransformation.put(transfoIdent, geometricalTransfo);
			addDataProperty(geometricalTransfo, racineURI+"has_id", transfoIdent);
			addDataProperty(geometricalTransfo, racineURI+"has_id", i+1); // à améliorer pour classer les transformations dans l'ordre
			addObjectProperty(outputFrom, racineURI+"has_specified_output", geometricalTransfo);
			addObjectProperty(geometricalTransfo, racineURI+"has_patient", human);

			// output
			Iterator<NonDICOMData> transfoOutputIterator = geometricalTransformation.getGeometricalTransformationValue().getNonDICOMData().iterator();
			while (transfoOutputIterator.hasNext()) {
				NonDICOMData transfoOutput = transfoOutputIterator.next();

				//Individual transfoOutputIndiv = createIndiv(generateName("geometrical_transformation"), model.getResource(racineURI+"geometrical_transformation"));
				translateNonDicomDataObject(transfoOutput, geometricalTransfo);
				addObjectProperty(geometricalTransfo, racineURI+"has_destination_coordinate_space_specified_by", geometricalTransfo);
			}

			//inputs 
			if (geometricalTransformation.getDICOMCTDestinationCoordinateSpaceUsed()!=null) {
				Iterator<DICOMData> DICOMSourceCoordinateSpaceUsedIterator = geometricalTransformation.getDICOMCTDestinationCoordinateSpaceUsed().getDICOMData().iterator();
				while (DICOMSourceCoordinateSpaceUsedIterator.hasNext()) {
					DICOMData DICOMSourceCoordinateSpaceUsed = DICOMSourceCoordinateSpaceUsedIterator.next();
					Individual input = getDicomInputOutput(DICOMSourceCoordinateSpaceUsed.getDICOMStudyUID(), DICOMSourceCoordinateSpaceUsed.getDICOMSeriesUID(), typeInputOutput.CTRecon);
					addObjectProperty(geometricalTransfo, racineURI+"has_source_coordinate_space_specified_by", input);
				}
			}

			if (geometricalTransformation.getDICOMCTSourceCoordinateSpaceUsed()!=null) {
				Iterator<DICOMData> DICOMSourceCoordinateSpaceUsedIterator = geometricalTransformation.getDICOMCTDestinationCoordinateSpaceUsed().getDICOMData().iterator();
				while (DICOMSourceCoordinateSpaceUsedIterator.hasNext()) {
					DICOMData DICOMSourceCoordinateSpaceUsed = DICOMSourceCoordinateSpaceUsedIterator.next();
					Individual input = getDicomInputOutput(DICOMSourceCoordinateSpaceUsed.getDICOMStudyUID(), DICOMSourceCoordinateSpaceUsed.getDICOMSeriesUID(), typeInputOutput.CTRecon);
					addObjectProperty(geometricalTransfo, racineURI+"has_source_coordinate_space_specified_by", input);
				}
			}

			if (geometricalTransformation.getDICOMSPECTDestinationCoordinateSpaceUsed()!=null) {
				Iterator<DICOMData> DICOMSourceCoordinateSpaceUsedIterator = geometricalTransformation.getDICOMSPECTDestinationCoordinateSpaceUsed().getDICOMData().iterator();
				while (DICOMSourceCoordinateSpaceUsedIterator.hasNext()) {
					DICOMData DICOMSourceCoordinateSpaceUsed = DICOMSourceCoordinateSpaceUsedIterator.next();
					Individual input = getDicomInputOutput(DICOMSourceCoordinateSpaceUsed.getDICOMStudyUID(), DICOMSourceCoordinateSpaceUsed.getDICOMSeriesUID(), typeInputOutput.SPECTCTacq);
					addObjectProperty(geometricalTransfo, racineURI+"has_source_coordinate_space_specified_by", input);
				}
			}

			if (geometricalTransformation.getDICOMSPECTSourceCoordinateSpaceUsed()!=null) {
				Iterator<DICOMData> DICOMSourceCoordinateSpaceUsedIterator = geometricalTransformation.getDICOMSPECTDestinationCoordinateSpaceUsed().getDICOMData().iterator();
				while (DICOMSourceCoordinateSpaceUsedIterator.hasNext()) {
					DICOMData DICOMSourceCoordinateSpaceUsed = DICOMSourceCoordinateSpaceUsedIterator.next();
					Individual input = getDicomInputOutput(DICOMSourceCoordinateSpaceUsed.getDICOMStudyUID(), DICOMSourceCoordinateSpaceUsed.getDICOMSeriesUID(), typeInputOutput.SPECTCTacq);
					addObjectProperty(geometricalTransfo, racineURI+"has_source_coordinate_space_specified_by", input);
				}
			}

			if (geometricalTransformation.getNonDICOMSourceCoordinateSpaceUsed() !=null) {
				Iterator<NonDICOMData> NonDICOMSourceCoordinateSpaceUsedIterator = geometricalTransformation.getNonDICOMSourceCoordinateSpaceUsed().getNonDICOMData().iterator();
				while (NonDICOMSourceCoordinateSpaceUsedIterator.hasNext()) {
					NonDICOMData NonDICOMSourceCoordinateSpaceUsed = NonDICOMSourceCoordinateSpaceUsedIterator.next();
					Individual input = getNonDicomInputOutput(NonDICOMSourceCoordinateSpaceUsed.getNonDICOMDataFileName(), NonDICOMSourceCoordinateSpaceUsed.getNonDICOMDataClass(), NonDICOMSourceCoordinateSpaceUsed.getNonDICOMDataFormat());
					addObjectProperty(geometricalTransfo, racineURI+"has_source_coordinate_space_specified_by", input);
				}
			}

			if (geometricalTransformation.getNonDICOMDestinationCoordinateSpaceUsed() !=null) {
				Iterator<NonDICOMData> NonDICOMSourceCoordinateSpaceUsedIterator = geometricalTransformation.getNonDICOMDestinationCoordinateSpaceUsed().getNonDICOMData().iterator();
				while (NonDICOMSourceCoordinateSpaceUsedIterator.hasNext()) {
					NonDICOMData NonDICOMSourceCoordinateSpaceUsed = NonDICOMSourceCoordinateSpaceUsedIterator.next();
					Individual input = getNonDicomInputOutput(NonDICOMSourceCoordinateSpaceUsed.getNonDICOMDataFileName(), NonDICOMSourceCoordinateSpaceUsed.getNonDICOMDataClass(), NonDICOMSourceCoordinateSpaceUsed.getNonDICOMDataFormat());
					addObjectProperty(geometricalTransfo, racineURI+"has_source_coordinate_space_specified_by", input);
				}
			}
		}
	}

	public static Individual translateTimepointDescriptionElement(TimePointDescriptionElement TimePointDescriptionElement) {
		Individual timePoint;
		String TimePointIdentifier = TimePointDescriptionElement.getTimePointIdentifier();

		if (tableTimePoint.containsKey(TimePointIdentifier)) {
			return tableTimePoint.get(TimePointIdentifier);
		}
		
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
		//addObjectProperty(timePoint, racineURI+"is_about", reconstruction);

		addDataProperty(timePoint,racineURI+"has_id", TimePointIdentifier);
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
		tableTimePoint.put(TimePointIdentifier, timePoint);
		return timePoint;
	}
	
	public static void retreiveThreeDimDosimetrySlide1Workflow(ThreeDimDosimetrySlide1Workflow threeDimDosimetrySlide1Workflow) {
		System.out.println("retreiveThreeDimDosimetrySlide1Workflow");
		
		Individual dosimetryOfTRT =  createIndiv(model.getResource(racineURI+"3D_dosimetry_of_TRT_using_time_integration_on_activity"));
		
		SPECTDataAcquisitionAndReconstruction SPECTDataAcquisitionAndReconstruction = threeDimDosimetrySlide1Workflow.getSPECTDataAcquisitionAndReconstruction();

		Iterator<SPECTAcqCTAcqAndReconstruction> SPECTAcqCTAcqAndReconstructionIterator = SPECTDataAcquisitionAndReconstruction.getSPECTAcqCTAcqAndReconstructionContainer().getSPECTAcqCTAcqAndReconstruction().iterator();
		SPECTAcqCTAcqAndReconstruction SPECTAcqCTAcqAndReconstruction; 
		while (SPECTAcqCTAcqAndReconstructionIterator.hasNext()) {
			SPECTAcqCTAcqAndReconstruction = SPECTAcqCTAcqAndReconstructionIterator.next();
			
			if (tableInternalRadiotherapy.containsKey(keyInternalRadioTherapyRadiophamarceuticalAdminsitration)) {
				internalRadiotherapy = tableInternalRadiotherapy.get(keyInternalRadioTherapyRadiophamarceuticalAdminsitration);
			} else {
				internalRadiotherapy = createIndiv(generateName("internal_radiotherapy"), model.getResource(racineURI+"internal_radiotherapy"));
				tableInternalRadiotherapy.put(keyInternalRadioTherapyRadiophamarceuticalAdminsitration, internalRadiotherapy);
			}
			
			addObjectProperty(RadiopharmaceuticalAdmin, racineObo+"BFO_0000132", internalRadiotherapy);
			
			addObjectProperty(roleOfOrganization,racineObo+"BFO_0000054",internalRadiotherapy);
			addObjectProperty(internalRadiotherapy, racineURI+"part_of_study", clinicalresearchStudy);
			addObjectProperty(internalRadiotherapy, racineURI+"treats", human);

			Individual reconstruction = createIndiv(generateName("SPECT_data_reconstruction"), model.getResource(racineURI+"SPECT_data_reconstruction"));
			
			addObjectProperty(reconstruction,racineObo+"BFO_0000132",dosimetryOfTRT);
			
			DICOMData NMTomoProduced = SPECTAcqCTAcqAndReconstruction.getNMTomoProduced();
			String studyId = NMTomoProduced.getDICOMStudyUID();
			String seriesId = NMTomoProduced.getDICOMSeriesUID();
			Individual NMtomo;
			if (hasDicomInputOutput(studyId, seriesId, typeInputOutput.NMTomo) == true) {
				NMtomo = getDicomInputOutput(studyId, seriesId, typeInputOutput.NMTomo);
				addObjectProperty(reconstruction, racineURI+"has_specified_output", NMtomo);
			} else {
				NMtomo = createIndiv(generateName("NM_tomo_dataset"), model.getResource(racineURI+"NM_tomo_dataset"));
				addDataProperty(NMtomo, racineURI+"has_DICOM_series_instance_UID", NMTomoProduced.getDICOMSeriesUID());
				addDataProperty(NMtomo, racineURI+"has_DICOM_study_instance_UID", NMTomoProduced.getDICOMStudyUID());
				addObjectProperty(reconstruction, racineURI+"has_specified_output", NMtomo);
				setDicomInputOutput(studyId, seriesId, typeInputOutput.NMTomo, NMtomo);
			}

			DICOMData CTReconProduced = SPECTAcqCTAcqAndReconstruction.getCTReconProduced();
			studyId = CTReconProduced.getDICOMStudyUID();
			seriesId = CTReconProduced.getDICOMSeriesUID();
			Individual CTrecon;
			if (hasDicomInputOutput(studyId, seriesId, typeInputOutput.CTRecon) == true) {
				CTrecon = getDicomInputOutput(studyId, seriesId, typeInputOutput.CTRecon);
			} else {
				CTrecon = createIndiv(generateName("CT_image_dataset"), model.getResource(racineURI+"CT_image_dataset"));
				addDataProperty(CTrecon, racineURI+"has_DICOM_series_instance_UID", CTReconProduced.getDICOMSeriesUID());
				addDataProperty(CTrecon, racineURI+"has_DICOM_study_instance_UID", CTReconProduced.getDICOMStudyUID());
				setDicomInputOutput(studyId, seriesId, typeInputOutput.CTRecon, CTrecon);
			}
			addObjectProperty(reconstruction, racineURI+"has_specified_output", CTrecon);
			Individual timepoint = null;
			Iterator<TimePointDescriptionElement> timePointDescriptionIterator = SPECTAcqCTAcqAndReconstruction.getTimePointDescription().getTimePointDescriptionElement().iterator();
			while (timePointDescriptionIterator.hasNext()) {
				TimePointDescriptionElement timePointDescriptionElement = timePointDescriptionIterator.next();
				timepoint = translateTimepointDescriptionElement(timePointDescriptionElement);
				addObjectProperty(CTrecon, racineURI+"is_About", timepoint);
				addObjectProperty(NMtomo, racineURI+"is_About", timepoint);
			}

			NMRelevantCalibrationReference NMRelevantCalibrationReference = SPECTAcqCTAcqAndReconstruction.getNMRelevantCalibrationReference();
			String NMreferenceCalibrationDate = NMRelevantCalibrationReference.getReferenceCalibrationDate();
			Isotope Isotope = NMRelevantCalibrationReference.getIsotope();
			// TODO

			String CTreferenceCalibrationDate = SPECTAcqCTAcqAndReconstruction.getCTRelevantCalibrationReference().getReferenceCalibrationDate();
			// TODO
		}

		Individual NMreconTomoDataset = null;

		if (SPECTDataAcquisitionAndReconstruction.getSPECTReconstructionContainer() != null) {
			Iterator<SPECTReconstruction> SPECTDataAcquisitionAndReconstructionIterator = SPECTDataAcquisitionAndReconstruction.getSPECTReconstructionContainer().getSPECTReconstruction().iterator();
			while (SPECTDataAcquisitionAndReconstructionIterator.hasNext()) {

				SPECTReconstruction SPECTDataAcquisitionAndReconstruction1 = SPECTDataAcquisitionAndReconstructionIterator.next();
				Individual spectAcquisition = createIndiv(generateName("SPECT_data_acquisition"), model.getResource(racineURI+"SPECT_data_acquisition"));
				Individual spectReconstruction = createIndiv(generateName("SPECT_data_reconstruction"), model.getResource(racineURI+"SPECT_data_reconstructionn"));

				addObjectProperty(spectAcquisition, racineObo+"BFO_0000132", internalRadiotherapy);

				ProcessExecutionContext processExecutionContext = SPECTDataAcquisitionAndReconstruction1.getProcessExecutionContext();
				translateProcessExecutionContext(processExecutionContext, spectAcquisition);
				translateProcessExecutionContext(processExecutionContext, spectReconstruction);

				String timePointIdentifierUsed = SPECTDataAcquisitionAndReconstruction1.getTimePointIdentifierUsed();
				Individual timePoint = tableTimePoint.get(timePointIdentifierUsed);

				DICOMData NMTomoUsed = SPECTDataAcquisitionAndReconstruction1.getNMTomoUsed();
				String seriesId = NMTomoUsed.getDICOMSeriesUID();
				String studyId = NMTomoUsed.getDICOMStudyUID();
				Individual NMtomo;
				if (hasDicomInputOutput(studyId, seriesId, typeInputOutput.NMTomo) == true) {
					NMtomo = getDicomInputOutput(studyId, seriesId, typeInputOutput.NMTomo);
				} else {
					NMtomo = createIndiv(generateName("NM_tomo_dataset"), model.getResource(racineURI+"NM_tomo_dataset"));
					addDataProperty(NMtomo, racineURI+"has_DICOM_series_instance_UID", seriesId);
					addDataProperty(NMtomo, racineURI+"has_DICOM_study_instance_UID", studyId);
					setDicomInputOutput(studyId, seriesId, typeInputOutput.NMTomo, NMtomo);
				}
				addObjectProperty(spectAcquisition, racineURI+"has_specified_output", NMtomo);
				addObjectProperty(spectReconstruction, racineURI+"has_specified_output", NMtomo);
				addObjectProperty(NMtomo, racineURI+"is_about", timePoint);

				DICOMData CTReconUsed = SPECTDataAcquisitionAndReconstruction1.getCTReconUsed();
				seriesId = CTReconUsed.getDICOMSeriesUID();
				studyId = CTReconUsed.getDICOMStudyUID();
				Individual CTRecon;
				if (hasDicomInputOutput(studyId, seriesId, typeInputOutput.CTRecon) == true) {
					CTRecon = getDicomInputOutput(studyId, seriesId, typeInputOutput.CTRecon);
				} else {
					CTRecon = createIndiv(generateName("CT_image_dataset"), model.getResource(racineURI+"CT_image_dataset"));
					addDataProperty(CTRecon, racineURI+"has_DICOM_series_instance_UID", seriesId);
					addDataProperty(CTRecon, racineURI+"has_DICOM_study_instance_UID", studyId);
					setDicomInputOutput(studyId, seriesId, typeInputOutput.CTRecon, CTRecon);
				}
				addObjectProperty(spectAcquisition, racineURI+"has_specified_output", CTRecon);
				addObjectProperty(spectReconstruction, racineURI+"has_specified_output", CTRecon);
				addObjectProperty(CTRecon, racineURI+"is_about", timePoint);

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
				NMreconTomoDataset = createIndiv(generateName("NM_recon_tomo_dataset"), model.getResource(racineURI+"NM_recon_tomo_dataset"));
				addObjectProperty(spectReconstruction, racineURI+"has_specified_output", NMreconTomoDataset);
				addDataProperty(spectReconstruction, racineURI+"has_DICOM_series_instance_UID", NMTomoReconProduced.getDICOMSeriesUID());
				addDataProperty(spectReconstruction, racineURI+"has_DICOM_study_instance_UID", NMTomoReconProduced.getDICOMStudyUID());
				setDicomInputOutput(NMTomoReconProduced.getDICOMStudyUID(), NMTomoReconProduced.getDICOMSeriesUID(), typeInputOutput.NMTomo, NMreconTomoDataset);
				addObjectProperty(NMreconTomoDataset, racineURI+"is_about", timePoint);
			}
		}

		RegistrationVOISegmentationAndPropagationContainer RegistrationVOISegmentationAndPropagationContainer = threeDimDosimetrySlide1Workflow.getRegistrationVOISegmentationAndPropagationContainer();
		Iterator<RegistrationVOISegmentationAndPropagation> RegistrationVOISegmentationAndPropagationIterator = RegistrationVOISegmentationAndPropagationContainer.getRegistrationVOISegmentationAndPropagation().iterator();
		while (RegistrationVOISegmentationAndPropagationIterator.hasNext()) {
			RegistrationVOISegmentationAndPropagation registrationVOISegmentationAndPropagation = RegistrationVOISegmentationAndPropagationIterator.next();
			Individual registration = createIndiv(generateName("registration_VOI_segmentation_and_propagation"), model.getResource(racineURI+"registration_VOI_segmentation_and_propagation"));
			addObjectProperty(registration,racineObo+"BFO_0000132", dosimetryOfTRT);
			addObjectProperty(registration, racineURI+"part_of_study", clinicalresearchStudy);
			addObjectProperty(registration, racineObo+"BFO_0000132", internalRadiotherapy);
			addObjectProperty(registration,racineURI+"has_specified_input", NMreconTomoDataset);

			ProcessExecutionContext processExecutionContext = registrationVOISegmentationAndPropagation.getProcessExecutionContext();
			translateProcessExecutionContext(processExecutionContext, registration);

			Individual timePointUsed = tableTimePoint.get(registrationVOISegmentationAndPropagation.getTimePointIdentifierUsed());

			Segmentation segmentation = registrationVOISegmentationAndPropagation.getSegmentation();
			Individual segmentationIndiv = translateSegmentation(segmentation);
			addObjectProperty(segmentationIndiv, racineURI+"is_specified_output_of", registration);

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
						CTReconResampled = createIndiv(generateName("resampled_CT_image_dataset"), model.getResource(racineURI+"resampled_CT_image_dataset"));
						setDicomInputOutput(studyID, seriesID, typeInputOutput.CTRecon, CTReconResampled);
						addDataProperty(CTReconResampled, racineURI+"has_DICOM_series_instance_UID", seriesID);
						addDataProperty(CTReconResampled, racineURI+"has_DICOM_study_instance_UID", studyID);

					}
					addObjectProperty(registration, racineURI+"has_specified_output", CTReconResampled);
					addObjectProperty(CTReconResampled, racineURI+"is_about", timePointUsed);
					addObjectProperty(CTReconResampled, racineURI+"has_patient", patient);
					addObjectProperty(segmentationIndiv, racineURI+"is_specified_output_of", CTReconResampled);
				}
			}

			if (registrationVOISegmentationAndPropagation.getCountsPerVOIAtTimePointProducedContainer()!=null) {
				Iterator<CountsPerVOIAtTimePoint> countsPerVOIAtTimePointProducedIterator = registrationVOISegmentationAndPropagation.getCountsPerVOIAtTimePointProducedContainer().getCountsPerVOIAtTimePointProduced().iterator();
				while (countsPerVOIAtTimePointProducedIterator.hasNext()) {
					CountsPerVOIAtTimePoint countsPerVOIAtTimePointProduced = countsPerVOIAtTimePointProducedIterator.next();
					Individual count = createIndiv(generateName("counts_per_VOI_at_timepoint"), model.getResource(racineURI+"counts_per_VOI_at_timepoint"));
					addObjectProperty(registration, racineURI+"has_specified_output", count);
					addDataProperty(count, "http://purl.obolibrary.org/obo/IAO_0000004", countsPerVOIAtTimePointProduced.getCountsValue());
					addObjectProperty(count, racineURI+"has_patient", patient);

					CountsUnit unit = countsPerVOIAtTimePointProduced.getCountsUnit();
					switch (countsPerVOIAtTimePointProduced.getCountsUnit()) {
					case COUNTS:
						addObjectProperty(count, "has_measuement_unit_label", getUnit("counts"));
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
				Individual NMtomo;
				if (hasDicomInputOutput(DICOMStudyUID, DICOMSeriesUID, typeInputOutput.NMTomo) == true) {
					NMtomo = getDicomInputOutput(DICOMStudyUID, DICOMSeriesUID, typeInputOutput.NMTomo);
					addObjectProperty(registration, racineURI+"has_specified_input", NMtomo);
				} else {
					NMtomo = createIndiv(generateName("NM_tomo_dataset"), model.getResource(racineURI+"NM_tomo_dataset"));
					addDataProperty(NMtomo, racineURI+"has_DICOM_series_instance_UID", DICOMSeriesUID);
					addDataProperty(NMtomo, racineURI+"has_DICOM_study_instance_UID", DICOMStudyUID);
					setDicomInputOutput(DICOMStudyUID, DICOMSeriesUID, typeInputOutput.NMTomo, NMtomo);
					addObjectProperty(NMtomo, racineURI+"is_about", timePointUsed);

					
				}
				addObjectProperty(NMtomo, racineURI+"is_about", timePointUsed);
				addObjectProperty(registration, racineURI+"has_specified_input", NMtomo);
				addObjectProperty(NMtomo, racineURI+"has_patient", patient);
			}

			Iterator<DICOMData> CTReconUsedIterator = registrationVOISegmentationAndPropagation.getCTReconUsed().getDICOMData().iterator();
			while (CTReconUsedIterator.hasNext()) {
				DICOMData CTReconUsed = CTReconUsedIterator.next();
				String DICOMSeriesUID = CTReconUsed.getDICOMSeriesUID();
				String DICOMStudyUID = CTReconUsed.getDICOMStudyUID();
				Individual CTRecon;
				if (hasDicomInputOutput(DICOMStudyUID, DICOMSeriesUID, typeInputOutput.CTRecon) == true) {
					CTRecon = getDicomInputOutput(DICOMStudyUID, DICOMSeriesUID, typeInputOutput.CTRecon);
				} else {
					CTRecon = createIndiv(generateName("resampled_CT_image_dataset"), model.getResource(racineURI+"resampled_CT_image_dataset"));
					addDataProperty(CTRecon, racineURI+"has_DICOM_series_instance_UID", DICOMSeriesUID);
					addDataProperty(CTRecon, racineURI+"has_DICOM_study_instance_UID", DICOMStudyUID);
					setDicomInputOutput(DICOMStudyUID, DICOMSeriesUID, typeInputOutput.CTRecon, CTRecon);
				}
				addObjectProperty(registration, racineURI+"has_specified_input", CTRecon);
				addObjectProperty(CTRecon, racineURI+"is_about", timePointUsed);
				addObjectProperty(CTRecon, racineURI+"has_patient", patient);
			}

			String imageProcessingMethodMethodUsed = registrationVOISegmentationAndPropagation.getImageProcessingMethodMethodUsed();
			Individual imageProcessingMethod = createIndiv(generateName("image_processing_method"), model.getResource(racineURI+"image_processing_method"));
			addObjectProperty(registration, racineURI+"has_protocol", imageProcessingMethod);
			addDataProperty(imageProcessingMethod, racineURI+"has_name", imageProcessingMethodMethodUsed);

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
						NMTomoRecon = createIndiv(generateName("resampled_NM_recon_tomo_image_dataset"), model.getResource(racineURI+"resampled_NM_recon_tomo_image_dataset"));
						setDicomInputOutput(studyID, seriesID, typeInputOutput.NMTomo, NMTomoRecon);
						addDataProperty(NMTomoRecon, racineURI+"has_DICOM_series_instance_UID", seriesID);
						addDataProperty(NMTomoRecon, racineURI+"has_DICOM_study_instance_UID", studyID);
					}
					addObjectProperty(registration, racineURI+"has_specified_output", NMTomoRecon);
					addObjectProperty(NMTomoRecon, racineURI+"is_about", timePointUsed);
					addObjectProperty(NMTomoRecon, racineURI+"has_patient", patient);
					addObjectProperty(segmentationIndiv, racineURI+"is_specified_output_of", NMTomoRecon);
				}
			}

			if (registrationVOISegmentationAndPropagation.getNonDICOMCTReconResampledOnCommonReferenceProduced() != null) {
				Iterator<NonDICOMData> nonDICOMDataIterator = registrationVOISegmentationAndPropagation.getNonDICOMCTReconResampledOnCommonReferenceProduced().getNonDICOMData().iterator();
				while (nonDICOMDataIterator.hasNext()) {
					NonDICOMData nonDICOMData = nonDICOMDataIterator.next();
					String nonDICOMDataClass = nonDICOMData.getNonDICOMDataClass();
					NonDICOMDataFormat nonDICOMDataClassFormat = nonDICOMData.getNonDICOMDataFormat();
					Individual nonDICOMCTReconResampled;
					if (hasNonDicomInputOutput(nonDICOMData.getNonDICOMDataFileName(), nonDICOMDataClass, nonDICOMDataClassFormat)) {
						nonDICOMCTReconResampled = getNonDicomInputOutput(nonDICOMData.getNonDICOMDataFileName(), nonDICOMDataClass, nonDICOMDataClassFormat);
					} else {
						nonDICOMCTReconResampled = createIndiv(generateName("resampled_CT_image_dataset"), model.getResource(racineURI+"resampled_CT_image_dataset"));
						setNonDicomInputOutput(nonDICOMData.getNonDICOMDataFileName(), nonDICOMData.getNonDICOMDataClass(), nonDICOMCTReconResampled, nonDICOMData.getNonDICOMDataFormat());
						translateNonDicomDataObject(nonDICOMData, nonDICOMCTReconResampled);
					}
					addObjectProperty(registration, racineURI+"has_specified_output", nonDICOMCTReconResampled);
					addObjectProperty(nonDICOMCTReconResampled, racineURI+"is_about", timePointUsed);
					addObjectProperty(nonDICOMCTReconResampled, racineURI+"has_patient", patient);
					addObjectProperty(segmentationIndiv, racineURI+"is_specified_output_of", nonDICOMCTReconResampled);
				}
			}

			if (registrationVOISegmentationAndPropagation.getNonDICOMNMTomoReconResampledOnCommonReferenceProduced() != null) {
				Iterator<NonDICOMData> nonDICOMDataIterator = registrationVOISegmentationAndPropagation.getNonDICOMNMTomoReconResampledOnCommonReferenceProduced().getNonDICOMData().iterator();
				while (nonDICOMDataIterator.hasNext()) {
					NonDICOMData nonDICOMData = nonDICOMDataIterator.next();
					Individual nonDICOMNMTomoRecon;
					String nonDICOMDataClass = nonDICOMData.getNonDICOMDataClass();
					NonDICOMDataFormat nonDICOMDataClassFormat = nonDICOMData.getNonDICOMDataFormat();
					if (hasNonDicomInputOutput(nonDICOMData.getNonDICOMDataFileName(), nonDICOMDataClass, nonDICOMDataClassFormat)) {
						nonDICOMNMTomoRecon = getNonDicomInputOutput(nonDICOMData.getNonDICOMDataFileName(), nonDICOMDataClass, nonDICOMDataClassFormat);
					} else {
						nonDICOMNMTomoRecon = createIndiv(generateName("resampled_NM_recon_tomo_image_dataset"), model.getResource(racineURI+"resampled_NM_recon_tomo_image_dataset"));
						translateNonDicomDataObject(nonDICOMData, nonDICOMNMTomoRecon);
						setNonDicomInputOutput(nonDICOMData.getNonDICOMDataFileName(), nonDICOMData.getNonDICOMDataClass(), nonDICOMNMTomoRecon, nonDICOMData.getNonDICOMDataFormat());
					}
					addObjectProperty(registration, racineURI+"has_specified_output", nonDICOMNMTomoRecon);
					addObjectProperty(nonDICOMNMTomoRecon, racineURI+"is_about", timePointUsed);
					addObjectProperty(nonDICOMNMTomoRecon, racineURI+"has_patient", patient);
					addObjectProperty(segmentationIndiv, racineURI+"is_specified_output_of", nonDICOMNMTomoRecon);
				}
			}

			if (registrationVOISegmentationAndPropagation.getDensityImageProduced()!= null) {
				Iterator<NonDICOMData> nonDICOMDataIterator = registrationVOISegmentationAndPropagation.getNonDICOMNMTomoReconResampledOnCommonReferenceProduced().getNonDICOMData().iterator();
				while (nonDICOMDataIterator.hasNext()) {
					NonDICOMData nonDICOMData = nonDICOMDataIterator.next();
					Individual densityImage;
					String nonDICOMDataClass = nonDICOMData.getNonDICOMDataClass();
					NonDICOMDataFormat nonDICOMDataClassFormat = nonDICOMData.getNonDICOMDataFormat();
					if (hasNonDicomInputOutput(nonDICOMData.getNonDICOMDataFileName(), nonDICOMDataClass, nonDICOMDataClassFormat)) {
						densityImage = getNonDicomInputOutput(nonDICOMData.getNonDICOMDataFileName(), nonDICOMDataClass, nonDICOMDataClassFormat);
					} else {
						densityImage = createIndiv(generateName("density_image"), model.getResource(racineURI+"density_image")); 
						translateNonDicomDataObject(nonDICOMData, densityImage);
						setNonDicomInputOutput(nonDICOMData.getNonDICOMDataFileName(), nonDICOMData.getNonDICOMDataClass(), densityImage, nonDICOMData.getNonDICOMDataFormat());
					}
					addObjectProperty(registration, racineURI+"has_specified_output", densityImage);
					addObjectProperty(registration, racineURI+"is_about", timePointUsed);
					addObjectProperty(registration, racineURI+"has_patient", patient);
				}
			}

			if (registrationVOISegmentationAndPropagation.getGeometricalTransformationContainer() != null) {
				translateGeometricalTransformation(registrationVOISegmentationAndPropagation.getGeometricalTransformationContainer(), registration, timePointUsed);
			}

			Iterator<VOIActivityDetermination> VOIActivityDeterminationIterator = threeDimDosimetrySlide1Workflow.getVOIActivityDeterminationContainer().getVOIActivityDetermination().iterator();
			while(VOIActivityDeterminationIterator.hasNext()) {
				VOIActivityDetermination VOIActivityDetermination = VOIActivityDeterminationIterator.next();
				Individual VOIActivityDeterminationIndiv = createIndiv(generateName("VOI_activity_determination"), model.getResource(racineURI+"VOI_activity_determination"));
				addObjectProperty(VOIActivityDeterminationIndiv, racineObo+"BFO_0000132", dosimetryOfTRT);
				addObjectProperty(VOIActivityDeterminationIndiv, racineURI+"part_of_study", clinicalresearchStudy);
				addObjectProperty(VOIActivityDeterminationIndiv, racineObo+"BFO_0000132", internalRadiotherapy);
				
				processExecutionContext = VOIActivityDetermination.getProcessExecutionContext();
				translateProcessExecutionContext(processExecutionContext, VOIActivityDeterminationIndiv);

				Individual segmentation1 = tableSegmentation.get(VOIActivityDetermination.getSegmentationIdentifierUsed());
				addObjectProperty(VOIActivityDeterminationIndiv, racineURI+"has_specified_input", segmentation1);
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
					Individual voi1 = tableVOI.get(dataActivityPerVOIAtTimePoint.getVOIIdentifier().toString());
					addDataProperty(dataActivity, "http://purl.obolibrary.org/obo/IAO_0000004", dataActivityPerVOIAtTimePoint.getDataActivityValue());
					ActivityUnit unit = dataActivityPerVOIAtTimePoint.getActivityUnit();
					addDataProperty(dataActivity, racineObo+"IAO_0000039", unit.toString().toLowerCase());
					addObjectProperty(dataActivity, racineURI+"is_quantity_measured_in", voi1);
					addObjectProperty(dataActivity, racineURI+"is_quantity_measured_at", timePoint1);
					addObjectProperty(VOIActivityDeterminationIndiv, racineURI+"has_specified_output", voi1);
				}

				Iterator<NonDICOMData> VoxelActivityMapProducedIterator = VOIActivityDetermination.getVoxelActivityMapProduced().getNonDICOMData().iterator();
				while (VoxelActivityMapProducedIterator.hasNext()) {
					NonDICOMData nonDICOMData = VoxelActivityMapProducedIterator.next();
					Individual voxelActivityMap = createIndiv(generateName("voxel_data_activity"), model.getResource(racineURI+"voxel_data_activity"));
					translateNonDicomDataObject(nonDICOMData,voxelActivityMap);
					setNonDicomInputOutput(nonDICOMData.getNonDICOMDataFileName(), nonDICOMData.getNonDICOMDataClass(), voxelActivityMap, nonDICOMData.getNonDICOMDataFormat());
					addObjectProperty(VOIActivityDeterminationIndiv, racineURI+"has_specified_output", voxelActivityMap);
					addObjectProperty(voxelActivityMap, racineURI+"is_about", timePoint);
					addObjectProperty(voxelActivityMap, racineURI+"has_patient", human);

				}
			}

			Iterator<TimeActivityCurveFitIn3DDosimetry> timeActivityCurveFitIn3DDosimetryIterator = threeDimDosimetrySlide1Workflow.getTimeActivityCurveFitIn3DDosimetryContainer().getTimeActivityCurveFitIn3DDosimetry().iterator();
			while (timeActivityCurveFitIn3DDosimetryIterator.hasNext()) {
				TimeActivityCurveFitIn3DDosimetry timeActivityCurveFitIn3DDosimetry = timeActivityCurveFitIn3DDosimetryIterator.next();
				Individual timeActivityCurveFit = createIndiv(generateName("time_activity_curve_fitting"), model.getResource(racineURI+"time_activity_curve_fitting"));
				addObjectProperty(timeActivityCurveFit, racineObo+"BFO_0000132", dosimetryOfTRT);
				addObjectProperty(timeActivityCurveFit, racineURI+"part_of_study", clinicalresearchStudy);
				addObjectProperty(timeActivityCurveFit, racineObo+"BFO_0000132", internalRadiotherapy);

				processExecutionContext = timeActivityCurveFitIn3DDosimetry.getProcessExecutionContext();
				translateProcessExecutionContext(processExecutionContext, timeActivityCurveFit);

				Individual voi1;
				if (timeActivityCurveFitIn3DDosimetry.getVOIIdentifierUsed()!=null) {
					voi1 = tableVOI.get(timeActivityCurveFitIn3DDosimetry.getVOIIdentifierUsed().toString());
					addObjectProperty(timeActivityCurveFit, racineURI+"has_specified_input", voi1);
				} else {
					voi1 = null;
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
				} else {
					timepoint = null;
				}
				
				
				//AdministeredActivity preAdministeredActivityUsed = timeActivityCurveFitIn3DDosimetry.getPreAdministeredActivityUsed();
				//Individual preAdminActivity = createIndiv(generateName("pre_administration_measured_activity"), model.getResource(racineDCM+"113508"));
				//addDataProperty(preAdminActivity, "http://purl.obolibrary.org/obo/IAO_0000004", preAdministeredActivityUsed.getAdministeredActivityValue());
				//ActivityUnit unit = preAdministeredActivityUsed.getActivityUnit();
				//addObjectProperty(preAdminActivity, racineObo+"IAO_0000039", getUnit(unit.toString().toLowerCase()));
				addObjectProperty(timeActivityCurveFit, racineURI+"has_specified_input", preAdministeredActivityIndiv);

				//AdministeredActivity postAdministeredActivityUsed = timeActivityCurveFitIn3DDosimetry.getPostAdministeredActivityUsed();
				//Individual postAdminActivity = createIndiv(generateName("post_administration_measured_activity"), model.getResource(racineDCM+"113509"));
				//addDataProperty(postAdminActivity, "http://purl.obolibrary.org/obo/IAO_0000004", postAdministeredActivityUsed.getAdministeredActivityValue());
				//ActivityUnit unit1 = postAdministeredActivityUsed.getActivityUnit();
				//addObjectProperty(postAdminActivity, racineObo+"IAO_0000039", getUnit(unit1.toString().toLowerCase()));
				addObjectProperty(timeActivityCurveFit, racineURI+"has_specified_input", postAdministeredActivityIndiv);

				if (timeActivityCurveFitIn3DDosimetry.getPKAssessmentMethodUsed()!=null) { 
					addDataProperty(timeActivityCurveFit, racineURI+"has_method_name", timeActivityCurveFitIn3DDosimetry.getPKAssessmentMethodUsed().toString());
				}

				Individual voi;
				if (timeActivityCurveFitIn3DDosimetry.getTimeIntegratedActivityCoefficientPerVOIProduced()!=null) { 
					TimeIntegratedActivityCoefficientPerVOI timeIntegratedActivityCoefficientPerVOIProduced = timeActivityCurveFitIn3DDosimetry.getTimeIntegratedActivityCoefficientPerVOIProduced();
					Individual timeIntegratedActivityCoefficientPerVOI = createIndiv(generateName("time_integrated_activity_coefficient"), model.getResource(racineURI+"time_integrated_activity_coefficient"));
					TimeUnit unit2 = timeIntegratedActivityCoefficientPerVOIProduced.getTimeUnit();
					addDataProperty(timeIntegratedActivityCoefficientPerVOI, racineURI+unit2.toString().toLowerCase(), timeIntegratedActivityCoefficientPerVOIProduced.getTimeIntegratedActivityCoefficientPerVOIValue());
					Iterator<BigInteger> VOIIdentifierUsedIterator = timeIntegratedActivityCoefficientPerVOIProduced.getVOIIdentifierList().getVOIIdentifierUsed().iterator();
					while (VOIIdentifierUsedIterator.hasNext() ) {
						BigInteger VOIIdentifierUsed = VOIIdentifierUsedIterator.next();
						voi = tableVOI.get(VOIIdentifierUsed.toString());
						addObjectProperty(timeIntegratedActivityCoefficientPerVOI, racineURI+"is_quantity_measured_in", voi);
					}
					addObjectProperty(timeActivityCurveFit, racineURI+"has_specified_output", timeIntegratedActivityCoefficientPerVOI);
				}

				TimeIntegratedActivityPerVOI timeIntegratedActivityPerVOIProduced = timeActivityCurveFitIn3DDosimetry.getTimeIntegratedActivityPerVOIProduced();
				Individual timeIntegratedActivityPerVOI = createIndiv(generateName("cumulated_activity"), model.getResource(racineURI+"cumulated_activity"));

				float residenceTimePerVOIValue = timeIntegratedActivityPerVOIProduced.getResidenceTimePerVOIValue();
				addDataProperty(timeIntegratedActivityPerVOI, racineURI+timeIntegratedActivityPerVOIProduced.getTimeUnit().toString().toLowerCase(), residenceTimePerVOIValue);

				float timeIntegratedActivityPerVOIValue = timeIntegratedActivityPerVOIProduced.getTimeIntegratedActivityPerVOIValue();
				addDataProperty(timeIntegratedActivityPerVOI, "http://purl.obolibrary.org/obo/IAO_0000004", timeIntegratedActivityPerVOIValue);

				switch (timeIntegratedActivityPerVOIProduced.getTimeIntegratedActivityPerVOIUnit()) {
				case MEGABECQUEREL_X_HOUR:
					addObjectProperty(timeIntegratedActivityPerVOI, racineObo+"IAO_0000039", getUnit("MEGABECQUEREL_X_HOUR"));
					break;
				case MEGABECQUEREL_X_SECOND:
					addObjectProperty(timeIntegratedActivityPerVOI, racineObo+"IAO_0000039", getUnit("MEGABECQUEREL_X_SECOND"));
					break;
				}

				Iterator<BigInteger> voiIterator = timeIntegratedActivityPerVOIProduced.getVOIIdentifierList().getVOIIdentifierUsed().iterator();
				while(voiIterator.hasNext()) {
					BigInteger voiIdent = voiIterator.next();
					voi = tableVOI.get(voiIdent.toString());
					addObjectProperty(timeIntegratedActivityPerVOI, racineURI+"is_quantity_measured_in", voi);
				}
				
				timeIntegratedActivityPerVOIProduced.getPKAssessmentMethodUsed();
				Individual CurveFittingMethod = createIndiv(generateName("curve_fitting_method"), model.getResource(racineURI+"curve_fitting_method"));
				
				Individual IncorporationFunction;
				switch (timeIntegratedActivityPerVOIProduced.getPKAssessmentMethodUsed().getIncorporationFunction()) {
				case CONSTANT:
					IncorporationFunction = createIndiv(generateName("constant_incorporation_mode"), model.getResource(racineURI+"constant_incorporation_mode"));
					break;
				case EXPONENTIAL:
					IncorporationFunction = createIndiv(generateName("exponential_incorporation_mode"), model.getResource(racineURI+"exponential_incorporation_mode"));
					break;
				case LINEAR:
					IncorporationFunction = createIndiv(generateName("linear_incorporation_mode"), model.getResource(racineURI+"linear_incorporation_mode"));
					break;
				default:
					IncorporationFunction = createIndiv(generateName("curve_fitting_method"), model.getResource(racineURI+"curve_fitting_method"));
					break;			
				}
				addObjectProperty(CurveFittingMethod, racineObo+"BFO_0000110", IncorporationFunction);

				Individual integrationAlgorithm;
				switch (timeIntegratedActivityPerVOIProduced.getPKAssessmentMethodUsed().getIntegrationAlgorithm()) {
				case BI_EXPONENTIAL:
					integrationAlgorithm = createIndiv(generateName("bi_exponential_integration_algorithm"), model.getResource(racineURI+"bi_exponential_integration_algorithm"));
					break;
				case MONO_EXPONENTIAL:
					integrationAlgorithm = createIndiv(generateName("mono_exponential_integration_algorithm"), model.getResource(racineURI+"mono_exponential_integration_algorithm"));
					break;
				case OTHER:
					integrationAlgorithm = createIndiv(generateName("other_integration_algorithm"), model.getResource(racineURI+"other_integration_algorithm"));
					break;
				case TRAPEZOID:
					integrationAlgorithm = createIndiv(generateName("trapezoid_integration_algorithm"), model.getResource(racineURI+"trapezoid_integration_algorithm"));
					break;
				case TRI_EXPONENTIAL:
					integrationAlgorithm = createIndiv(generateName("tri_exponential_integration_algorithm"), model.getResource(racineURI+"tri_exponential_integration_algorithm"));
					break;
				case X_EXPONENTIAL:
					integrationAlgorithm = createIndiv(generateName("x_exponential_integration_algorithm"), model.getResource(racineURI+"x_exponential_integration_algorithm"));
					break;
				default:
					integrationAlgorithm = createIndiv(generateName("other_integration_algorithm"), model.getResource(racineURI+"other_integration_algorithm"));
					break;
				}
				addObjectProperty(CurveFittingMethod, racineObo+"BFO_0000110", integrationAlgorithm);
				
				addObjectProperty(timeActivityCurveFit, racineURI+"has_protocol", CurveFittingMethod);

				String fittingFunction = timeIntegratedActivityPerVOIProduced.getPKAssessmentMethodUsed().getFittingFunction();
				addDataProperty(CurveFittingMethod, racineURI+"has_name", fittingFunction);
				addObjectProperty(CurveFittingMethod, racineURI+"is_protocol_used_in", timeIntegratedActivityPerVOI);
			}	
		}

		AbsorbedDoseCalculationInVOI absorbedDoseCalculationInVOI = threeDimDosimetrySlide1Workflow.getAbsorbedDoseCalculationInVOI();
		Individual calculationOfMeanAbsorbedDosesInVOI = createIndiv(generateName("calculation_of_mean_absorbed_doses_in_VOIs"), model.getResource(racineURI+"calculation_of_mean_absorbed_doses_in_VOIs"));
		addObjectProperty(calculationOfMeanAbsorbedDosesInVOI,racineObo+"BFO_0000132", dosimetryOfTRT);

		ProcessExecutionContext processExecutionContext = absorbedDoseCalculationInVOI.getProcessExecutionContext();
		translateProcessExecutionContext(processExecutionContext, calculationOfMeanAbsorbedDosesInVOI);

		addObjectProperty(calculationOfMeanAbsorbedDosesInVOI, racineObo+"BFO_0000132", internalRadiotherapy);
		
		if (absorbedDoseCalculationInVOI.getSegmentationIdentifierUsed()!=null) {
			logger.debug("segmentation Id : "+absorbedDoseCalculationInVOI.getSegmentationIdentifierUsed().toString());
			Individual segmentation = tableSegmentation.get(absorbedDoseCalculationInVOI.getSegmentationIdentifierUsed());
			addObjectProperty(calculationOfMeanAbsorbedDosesInVOI, racineURI+"has_specified_input", segmentation);
		}

		Individual absorbedDoseMethod = createIndiv(generateName("absorbed_dose_estimate_method"), model.getResource(racineURI+"absorbed_dose_estimate_method"));
		addDataProperty(absorbedDoseMethod, racineURI+"has_name", absorbedDoseCalculationInVOI.getAbsorbedDoseCalculationMethodUsed());
		addObjectProperty(calculationOfMeanAbsorbedDosesInVOI, racineURI+"has_protocol", absorbedDoseMethod);

		if (absorbedDoseCalculationInVOI.getVoxelAbsorbedDoseMapProduced()!=null) {
			Iterator<NonDICOMData> voxelAbsorbedDoseMapProducedIterator = absorbedDoseCalculationInVOI.getVoxelAbsorbedDoseMapProduced().getNonDICOMData().iterator();
			while (voxelAbsorbedDoseMapProducedIterator.hasNext()) {
				NonDICOMData voxelAbsorbedDoseMapProduced = voxelAbsorbedDoseMapProducedIterator.next();
				Individual voxelAbsorbedDoseMap = createIndiv(generateName("3D_absorbed_dose_map"), model.getResource(racineDCM+"128487"));
				translateNonDicomDataObject(voxelAbsorbedDoseMapProduced, voxelAbsorbedDoseMap);
				setNonDicomInputOutput(voxelAbsorbedDoseMapProduced.getNonDICOMDataFileName(), voxelAbsorbedDoseMapProduced.getNonDICOMDataClass(), voxelAbsorbedDoseMap, voxelAbsorbedDoseMapProduced.getNonDICOMDataFormat());
			}
		}

		if (absorbedDoseCalculationInVOI.getAbsorbedDoseInVOIContainer()!=null) {
			translateTotalAbsorbedDosePerVOI(absorbedDoseCalculationInVOI.getAbsorbedDoseInVOIContainer(), absorbedDoseMethod, calculationOfMeanAbsorbedDosesInVOI);
		}

		if (threeDimDosimetrySlide1Workflow.getRadioBiologicalCalculationIn3DSlide1Dosimetry()!=null) {
			RadioBiologicalCalculationIn3DSlide1Dosimetry radioBiologicalCalculationIn3DSlide1Dosimetry = threeDimDosimetrySlide1Workflow.getRadioBiologicalCalculationIn3DSlide1Dosimetry();
			Individual radioBiologicalCalculation = createIndiv(generateName("radio_biological_calculation"), model.getResource(racineURI+"radio_biological_calculation"));

			addObjectProperty(radioBiologicalCalculation, racineURI+"has_patient", clinicalresearchStudy);
			addObjectProperty(radioBiologicalCalculation, racineObo+"BFO_0000132", internalRadiotherapy);


			processExecutionContext = radioBiologicalCalculationIn3DSlide1Dosimetry.getProcessExecutionContext();
			translateProcessExecutionContext(processExecutionContext, radioBiologicalCalculation);

			Individual voi = tableVOI.get(radioBiologicalCalculationIn3DSlide1Dosimetry.getVOIIdentifierUsed().toString());
			addObjectProperty(radioBiologicalCalculation,racineURI+"has_specified_input",voi);

			if (radioBiologicalCalculationIn3DSlide1Dosimetry.getVoxelAbsorbedDoseMapIdentifierUsed()!= null) {
				Iterator<String> voxelAbsorbedDoseMapIdentifierUsedIterator = radioBiologicalCalculationIn3DSlide1Dosimetry.getVoxelAbsorbedDoseMapIdentifierUsed().getVoxelAbsorbedDoseMapIdentifierUsed().iterator();
				while (voxelAbsorbedDoseMapIdentifierUsedIterator.hasNext()) {
					String voxelAbsorbedDoseMapIdentifierUsed = voxelAbsorbedDoseMapIdentifierUsedIterator.next();
					Individual voxelAbsorbedDoseMap = tableVoxelAbsorbedDoseMap.get(voxelAbsorbedDoseMapIdentifierUsed);
					addObjectProperty(radioBiologicalCalculation, racineURI+"has_specified_input", voxelAbsorbedDoseMap);
					addObjectProperty(voxelAbsorbedDoseMap, racineURI+"has_patient", human);
				}
			}

			String RadioBiologicalCalculationMethod = radioBiologicalCalculationIn3DSlide1Dosimetry.getRadioBiologicalCalculationMethod();
			addDataProperty(radioBiologicalCalculation, racineURI+"has_method_name", RadioBiologicalCalculationMethod);
			String RadioBiologicalCalculationParameters = radioBiologicalCalculationIn3DSlide1Dosimetry.getRadioBiologicalCalculationParameters();
			addDataProperty(radioBiologicalCalculation, racineURI+"has_description", RadioBiologicalCalculationParameters);

			String BiologicalEffectiveDose = radioBiologicalCalculationIn3DSlide1Dosimetry.getBiologicalEffectiveDose();
			Individual effectiveDose = createIndiv(generateName("effective_dose"), model.getResource(racineURI+"effective_dose"));
			addDataProperty(effectiveDose, "http://purl.obolibrary.org/obo/IAO_0000004", BiologicalEffectiveDose);
			addObjectProperty(radioBiologicalCalculation, racineURI+"has_specified_output", effectiveDose);
			addObjectProperty(radioBiologicalCalculation, racineURI+"has_patient", human);
		}
	}

	public static void retreiveThreeDimDosimetrySlide2Workflow(ThreeDimDosimetrySlide2Workflow threeDimDosimetrySlide2Workflow) {
		SPECTDataAcquisitionAndReconstruction SPECTDataAcquisitionAndReconstruction = threeDimDosimetrySlide2Workflow.getSPECTDataAcquisitionAndReconstruction();
		
		Individual dosimetryOfTRT2 = createIndiv(model.getResource(racineURI+"3D_dosimetry_of_TRT_using_time_integration_on_absorbed_dose_rates"));
		
		if (tableInternalRadiotherapy.containsKey(keyInternalRadioTherapyRadiophamarceuticalAdminsitration)) {
			internalRadiotherapy = tableInternalRadiotherapy.get(keyInternalRadioTherapyRadiophamarceuticalAdminsitration);
		} else {
			internalRadiotherapy = createIndiv(generateName("internal_radiotherapy"), model.getResource(racineURI+"internal_radiotherapy"));
			tableInternalRadiotherapy.put(keyInternalRadioTherapyRadiophamarceuticalAdminsitration, internalRadiotherapy);
		}
		
		Iterator<SPECTAcqCTAcqAndReconstruction> SPECTAcqCTAcqAndReconstructionIterator = SPECTDataAcquisitionAndReconstruction.getSPECTAcqCTAcqAndReconstructionContainer().getSPECTAcqCTAcqAndReconstruction().iterator();
		SPECTAcqCTAcqAndReconstruction SPECTAcqCTAcqAndReconstruction; 
		while (SPECTAcqCTAcqAndReconstructionIterator.hasNext()) {
			SPECTAcqCTAcqAndReconstruction = SPECTAcqCTAcqAndReconstructionIterator.next();
			Individual reconstruction = createIndiv(generateName("SPECT_data_reconstruction"), model.getResource(racineURI+"SPECT_data_reconstruction"));
			addObjectProperty(reconstruction, racineObo+"BFO_0000132", dosimetryOfTRT2);
			
			Individual NMtomo;  Individual CTrecon;
			DICOMData NMTomoProduced = SPECTAcqCTAcqAndReconstruction.getNMTomoProduced();
			String studyId = NMTomoProduced.getDICOMStudyUID();
			String seriesId = NMTomoProduced.getDICOMSeriesUID();
			if (hasDicomInputOutput(studyId, seriesId, typeInputOutput.NMTomo) == true) {
				NMtomo = getDicomInputOutput(studyId, seriesId, typeInputOutput.NMTomo);
				addObjectProperty(reconstruction, racineURI+"has_specified_output", NMtomo);
			} else {
				NMtomo = createIndiv(generateName("NM_tomo_dataset"), model.getResource(racineURI+"NM_tomo_dataset"));
				addDataProperty(NMtomo, racineURI+"has_DICOM_series_instance_UID", NMTomoProduced.getDICOMSeriesUID());
				addDataProperty(NMtomo, racineURI+"has_DICOM_study_instance_UID", NMTomoProduced.getDICOMStudyUID());
				addObjectProperty(reconstruction, racineURI+"has_specified_output", NMtomo);
				setDicomInputOutput(studyId, seriesId, typeInputOutput.NMTomo, NMtomo);
			}

			DICOMData CTReconProduced = SPECTAcqCTAcqAndReconstruction.getCTReconProduced();
			studyId = CTReconProduced.getDICOMStudyUID();
			seriesId = CTReconProduced.getDICOMSeriesUID();
			if (hasDicomInputOutput(studyId, seriesId, typeInputOutput.CTRecon) == true) {
				CTrecon = getDicomInputOutput(studyId, seriesId, typeInputOutput.CTRecon);
				addObjectProperty(reconstruction, racineURI+"has_specified_output", CTrecon);
			} else {
				CTrecon = createIndiv(generateName("CT_image_dataset"), model.getResource(racineURI+"CT_image_dataset"));
				addDataProperty(CTrecon, racineURI+"has_DICOM_series_instance_UID", CTReconProduced.getDICOMSeriesUID());
				addDataProperty(CTrecon, racineURI+"has_DICOM_study_instance_UID", CTReconProduced.getDICOMStudyUID());
				addObjectProperty(reconstruction, racineURI+"has_specified_output", CTrecon);
				setDicomInputOutput(studyId, seriesId, typeInputOutput.CTRecon, CTrecon);
			}

			Iterator<TimePointDescriptionElement> timePointDescriptionIterator = SPECTAcqCTAcqAndReconstruction.getTimePointDescription().getTimePointDescriptionElement().iterator();
			while (timePointDescriptionIterator.hasNext()) {
				TimePointDescriptionElement timePointDescriptionElement = timePointDescriptionIterator.next();
				Individual timepoint = translateTimepointDescriptionElement(timePointDescriptionElement);
				addObjectProperty(NMtomo, racineURI+"is_about", timepoint);
				addObjectProperty(CTrecon, racineURI+"is_about", timepoint);
			}

			NMRelevantCalibrationReference NMRelevantCalibrationReference = SPECTAcqCTAcqAndReconstruction.getNMRelevantCalibrationReference();
			String NMreferenceCalibrationDate = NMRelevantCalibrationReference.getReferenceCalibrationDate();
			Isotope Isotope = NMRelevantCalibrationReference.getIsotope();
			// TODO

			String CTreferenceCalibrationDate = SPECTAcqCTAcqAndReconstruction.getCTRelevantCalibrationReference().getReferenceCalibrationDate();
			// TODO


		}

		if (SPECTDataAcquisitionAndReconstruction.getSPECTReconstructionContainer()!=null) {
			Iterator<SPECTReconstruction> SPECTReconstructionIterator = SPECTDataAcquisitionAndReconstruction.getSPECTReconstructionContainer().getSPECTReconstruction().iterator();
			while (SPECTReconstructionIterator.hasNext()) {
				SPECTReconstruction SPECTReconstruction = SPECTReconstructionIterator.next();
				Individual SPECTdataReconstruction = createIndiv(generateName("SPECT_data_reconstruction"), model.getResource(racineURI+"SPECT_data_reconstruction"));
				addObjectProperty(SPECTdataReconstruction, racineObo+"BFO_0000132", dosimetryOfTRT2);
				
				ProcessExecutionContext processExecutionContext = SPECTReconstruction.getProcessExecutionContext();
				translateProcessExecutionContext(processExecutionContext, SPECTdataReconstruction);

				String timePointIdentifierUsed = SPECTReconstruction.getTimePointIdentifierUsed();
				Individual timePoint = tableTimePoint.get(timePointIdentifierUsed);
				Individual NMtomo; Individual CTRecon;
				DICOMData NMTomoUsed = SPECTReconstruction.getNMTomoUsed();
				String seriesId = NMTomoUsed.getDICOMSeriesUID();
				String studyId = NMTomoUsed.getDICOMStudyUID();
				if (hasDicomInputOutput(studyId, seriesId, typeInputOutput.NMTomo) == true) {
					NMtomo = getDicomInputOutput(studyId, seriesId, typeInputOutput.NMTomo);
					addObjectProperty(SPECTdataReconstruction, racineURI+"has_specified_output", NMtomo);
				} else {
					NMtomo = createIndiv(generateName("NM_tomo_dataset"), model.getResource(racineURI+"NM_tomo_dataset"));
					addDataProperty(NMtomo, racineURI+"has_DICOM_series_instance_UID", seriesId);
					addDataProperty(NMtomo, racineURI+"has_DICOM_study_instance_UID", studyId);
					addObjectProperty(SPECTdataReconstruction, racineURI+"has_specified_output", NMtomo);
					setDicomInputOutput(studyId, seriesId, typeInputOutput.NMTomo, NMtomo);
				}
				addObjectProperty(NMtomo, racineURI+"is_about", timePoint);


				DICOMData CTReconUsed = SPECTReconstruction.getCTReconUsed();
				seriesId = CTReconUsed.getDICOMSeriesUID();
				studyId = CTReconUsed.getDICOMStudyUID();
				if (hasDicomInputOutput(studyId, seriesId, typeInputOutput.CTRecon) == true) {
					CTRecon = getDicomInputOutput(studyId, seriesId, typeInputOutput.CTRecon);
					addObjectProperty(SPECTdataReconstruction, racineURI+"has_specified_output", CTRecon);
					addObjectProperty(CTRecon, racineURI+"is_about", timePoint);
				} else {
					CTRecon = createIndiv(generateName("CT_image_dataset"), model.getResource(racineURI+"CT_image_dataset"));
					addDataProperty(CTRecon, racineURI+"has_DICOM_series_instance_UID", seriesId);
					addDataProperty(CTRecon, racineURI+"has_DICOM_study_instance_UID", studyId);
					addObjectProperty(SPECTdataReconstruction, racineURI+"has_specified_output", CTRecon);
					setDicomInputOutput(studyId, seriesId, typeInputOutput.CTRecon, CTRecon);
				}
				addObjectProperty(CTRecon, racineURI+"is_about", timePoint);

				String reconstructionMethodAndCorrectionsUsed = SPECTReconstruction.getReconstructionMethodAndCorrectionsUsed();

				if (SPECTReconstruction.getCTNumberCalibrationCurveUsed()!=null) {
					CTNumberCalibrationCurve CTNumberCalibrationCurveUsed = SPECTReconstruction.getCTNumberCalibrationCurveUsed();

					Iterator<ElementOfCTNumberCalibrationCurve> ElementOfCTNumberCalibrationCurveIterator = CTNumberCalibrationCurveUsed.getElementsOfCTNumberCalibrationCurve().getElementOfCTNumberCalibrationCurve().iterator();
					while (ElementOfCTNumberCalibrationCurveIterator.hasNext()) {
						ElementOfCTNumberCalibrationCurve ElementOfCTNumberCalibrationCurve = ElementOfCTNumberCalibrationCurveIterator.next();
						//TODO crééer individus
					}

					String referenceCalibrationDate = CTNumberCalibrationCurveUsed.getReferenceCalibrationDate();
				}

				if (SPECTReconstruction.getCTNumberCalibrationCurveReference()!=null) {
					String referenceCalibrationDate = SPECTReconstruction.getCTNumberCalibrationCurveReference().getReferenceCalibrationDate();
				}

				DICOMData NMTomoReconProduced = SPECTReconstruction.getNMTomoReconProduced();
				Individual NMreconTomoDataset = createIndiv(generateName("NM_recon_tomo_dataset"), model.getResource(racineURI+"NM_recon_tomo_dataset"));
				addObjectProperty(SPECTdataReconstruction, racineURI+"has_specified_output", NMreconTomoDataset);
				addDataProperty(SPECTdataReconstruction, racineURI+"has_DICOM_series_instance_UID", NMTomoReconProduced.getDICOMSeriesUID());
				addDataProperty(SPECTdataReconstruction, racineURI+"has_DICOM_study_instance_UID", NMTomoReconProduced.getDICOMStudyUID());
				setDicomInputOutput(NMTomoReconProduced.getDICOMStudyUID(), NMTomoReconProduced.getDICOMSeriesUID(), typeInputOutput.NMTomo, NMreconTomoDataset);
			}
		}

		Iterator<VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation> VOISegmentationEnergyDepositionCalculationIterator = threeDimDosimetrySlide2Workflow.getVOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculationContainer().getVOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation().iterator();
		while (VOISegmentationEnergyDepositionCalculationIterator.hasNext()) {
			VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation VOISegmentationEnergyDepositionCalculation = VOISegmentationEnergyDepositionCalculationIterator.next();

			VOISegmentationVOIMassDetermination VOISegmentationVOIMassDetermination = VOISegmentationEnergyDepositionCalculation.getVOISegmentationVOIMassDetermination();
			Individual VOIsegmentation = createIndiv(generateName("VOI_segmentation_VOI_mass_determination"), model.getResource(racineURI+"VOI_segmentation_VOI_mass_determination"));
			addObjectProperty(VOIsegmentation, racineObo+"BFO_0000132", dosimetryOfTRT2);
			
			ProcessExecutionContext processExecutionContext = VOISegmentationVOIMassDetermination.getProcessExecutionContext();
			translateProcessExecutionContext(processExecutionContext, VOIsegmentation);

			Individual timepoint = tableTimePoint.get(VOISegmentationVOIMassDetermination.getTimePointIdentifierUsed());
			Individual NMtomo; Individual CTrecon;
			DICOMData NMTomoReconUsed = VOISegmentationVOIMassDetermination.getNMTomoReconUsed();
			String DICOMSeriesUID = NMTomoReconUsed.getDICOMSeriesUID();
			String DICOMStudyUID = NMTomoReconUsed.getDICOMStudyUID();
			if (hasDicomInputOutput(DICOMStudyUID, DICOMSeriesUID, typeInputOutput.NMTomo) == true) {
				NMtomo = getDicomInputOutput(DICOMStudyUID, DICOMSeriesUID, typeInputOutput.NMTomo);
			} else {
				NMtomo = createIndiv(generateName("NM_tomo_dataset"), model.getResource(racineURI+"NM_tomo_dataset"));
				addDataProperty(NMtomo, racineURI+"has_DICOM_series_instance_UID", DICOMSeriesUID);
				addDataProperty(NMtomo, racineURI+"has_DICOM_study_instance_UID", DICOMStudyUID);
				setDicomInputOutput(DICOMStudyUID, DICOMSeriesUID, typeInputOutput.NMTomo, NMtomo);
			}
			addObjectProperty(VOIsegmentation, racineURI+"has_specified_input", NMtomo);
			addObjectProperty(NMtomo, racineURI+"is_about", timepoint);

			DICOMData CTReconUsed = VOISegmentationVOIMassDetermination.getCTReconUsed();
			DICOMSeriesUID = CTReconUsed.getDICOMSeriesUID();
			DICOMStudyUID = CTReconUsed.getDICOMStudyUID();
			if (hasDicomInputOutput(DICOMStudyUID, DICOMSeriesUID, typeInputOutput.CTRecon) == true) {
				CTrecon = getDicomInputOutput(DICOMStudyUID, DICOMSeriesUID, typeInputOutput.CTRecon);
			} else {
				CTrecon = createIndiv(generateName("NM_tomo_dataset"), model.getResource(racineURI+"NM_tomo_dataset"));
				addDataProperty(CTrecon, racineURI+"has_DICOM_series_instance_UID", DICOMSeriesUID);
				addDataProperty(CTrecon, racineURI+"has_DICOM_study_instance_UID", DICOMStudyUID);
				setDicomInputOutput(DICOMStudyUID, DICOMSeriesUID, typeInputOutput.CTRecon, CTrecon);
			}
			addObjectProperty(VOIsegmentation, racineURI+"has_specified_input", CTrecon);
			addObjectProperty(CTrecon, racineURI+"is_about", timepoint);

			String VOISegmentationMethodUsed = VOISegmentationVOIMassDetermination.getVOISegmentationMethodUsed();
			addDataProperty(VOIsegmentation,racineURI+"has_method_name",VOISegmentationMethodUsed);

			if (VOISegmentationVOIMassDetermination.getVOIProduced() != null) {
				Iterator<VOI> VOIIterator = VOISegmentationVOIMassDetermination.getVOIProduced().getVOIProduced().iterator();
				while (VOIIterator.hasNext()) {
					VOI VOIProduced = VOIIterator.next();
					String VOIIdentifier = VOIProduced.getVOIIdentifier().toString();
					Individual voi; Individual organ = null;
					if (tableVOI.contains(VOIIdentifier)) {
						voi = tableVOI.get(VOIIdentifier);
					} else {
						voi = createIndiv(generateName("VOI"), model.getResource(racineURI+"VOI"));
						tableVOI.put(VOIIdentifier, voi);
						addDataProperty(voi, racineURI+"has_id", VOIIdentifier);
						organ = getOrganOrTissue(VOIProduced.getOrganOrTissue());
						addObjectProperty(organ,racineObo+"BFO_0000175", human);
						addObjectProperty(voi,racineURI+"represents", organ);
						addObjectProperty(VOIsegmentation,racineURI+"has_specified_output", voi);
						addObjectProperty(voi,racineURI+"has_patient", human);
					}

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

					OrganMass organMass = VOIProduced.getOrganMass();
					Individual mass = createIndiv(generateName("patient_organ_mass_at_timepoint"), model.getResource(racineURI+"patient_organ_mass_at_timepoint"));
					addDataProperty(mass, "http://purl.obolibrary.org/obo/IAO_0000004", organMass.getOrganMassValue());
					switch (organMass.getOrganMassUnit()) {
					case GRAM:
						addObjectProperty(mass, racineObo+"IAO_0000039", getUnit("gram"));
						break;
					case KILOGRAM:
						addObjectProperty(mass, racineObo+"IAO_0000039", getUnit("kilogram"));
						break;
					}
					addObjectProperty(mass, racineURI+"is_quantity_measured_in", organ);
				}
			}

			if (VOISegmentationVOIMassDetermination.getSegmentationProduced()!=null) {
				Segmentation segmentation = VOISegmentationVOIMassDetermination.getSegmentationProduced();
				Individual segmentationIndiv = translateSegmentation(segmentation);
				addObjectProperty(segmentationIndiv, racineURI+"is_specified_output_of", VOIsegmentation);
			}

			if (VOISegmentationVOIMassDetermination.getCountsPerVOIAtTimePointContainer()!=null) {
				Iterator<CountsPerVOIAtTimePoint> countsPerVOIAtTimePointProducedIterator = VOISegmentationVOIMassDetermination.getCountsPerVOIAtTimePointContainer().getCountsPerVOIAtTimePointProduced().iterator();
				while (countsPerVOIAtTimePointProducedIterator.hasNext()) {
					CountsPerVOIAtTimePoint countsPerVOIAtTimePointProduced = countsPerVOIAtTimePointProducedIterator.next();
					Individual count = createIndiv(generateName("counts_per_VOI_at_timepoint"), model.getResource(racineURI+"counts_per_VOI_at_timepoint"));

					addDataProperty(count, "http://purl.obolibrary.org/obo/IAO_0000004", countsPerVOIAtTimePointProduced.getCountsValue());
					switch (countsPerVOIAtTimePointProduced.getCountsUnit()) {
					case COUNTS:
						addObjectProperty(count, racineObo+"IAO_0000039", getUnit("counts"));
						break;
					}

					Individual voi = tableVOI.get(countsPerVOIAtTimePointProduced.getVOIIdentifier().toString());
					addObjectProperty(count,  racineURI+"is_quantity_measured_in", voi);

					String timePointId = countsPerVOIAtTimePointProduced.getTimePointIdentifier();
					Individual timePoint = tableTimePoint.get(timePointId);
					addObjectProperty(count,  racineURI+"is_quantity_measured_at", timePoint);			
				}
			}


			Iterator<DICOMData> CTReconResampledOnNMReferenceProducedIterator = VOISegmentationVOIMassDetermination.getCTReconResampledOnNMReferenceProduced().getDICOMData().iterator();
			while (CTReconResampledOnNMReferenceProducedIterator.hasNext()) {
				DICOMData CTReconResampledOnNMReferenceProduced = CTReconResampledOnNMReferenceProducedIterator.next();
				Individual CTReconResampled;
				String studyID = CTReconResampledOnNMReferenceProduced.getDICOMStudyUID();
				String seriesID = CTReconResampledOnNMReferenceProduced.getDICOMSeriesUID();
				if (hasDicomInputOutput(studyID, seriesID, typeInputOutput.CTRecon)) {
					CTReconResampled = getDicomInputOutput(studyID, seriesID, typeInputOutput.CTRecon);
				} else { 
					CTReconResampled = createIndiv(generateName("resampled_CT_image_dataset"), model.getResource(racineURI+"resampled_CT_image_dataset"));
					setDicomInputOutput(studyID, seriesID, typeInputOutput.CTRecon, CTReconResampled);
					addDataProperty(CTReconResampled, racineURI+"has_DICOM_series_instance_UID", seriesID);
					addDataProperty(CTReconResampled, racineURI+"has_DICOM_study_instance_UID", studyID);
				}
				addObjectProperty(VOIsegmentation, racineURI+"has_specified_output", CTReconResampled);
			}

			Iterator<MassPerVOIAtTimePoint> massPerVOIAtTimePointProducedIterator = VOISegmentationVOIMassDetermination.getMassPerVOIAtTimePointContainer().getMassPerVOIAtTimePointProduced().iterator();
			while (massPerVOIAtTimePointProducedIterator.hasNext()) {
				MassPerVOIAtTimePoint massPerVOIAtTimePointProduced = massPerVOIAtTimePointProducedIterator.next();
				Individual voi = tableVOI.get(massPerVOIAtTimePointProduced.getVOIIdentifier().toString());
				Individual timePoint = tableTimePoint.get(massPerVOIAtTimePointProduced.getTimePointIdentifier());
				Individual mass = createIndiv(generateName("mass"), model.getResource(racineObo+"PATO_0000125"));
				addDataProperty(mass, "http://purl.obolibrary.org/obo/IAO_0000004", massPerVOIAtTimePointProduced.getMassValue());
				addObjectProperty(mass, racineObo+"IAO_0000039", getUnit(massPerVOIAtTimePointProduced.getMassUnit().toString()));
				addObjectProperty(mass,  racineURI+"is_quantity_measured_in", voi);
				addObjectProperty(mass,  racineURI+"is_quantity_measured_at", timePoint);							
			}

			if (VOISegmentationEnergyDepositionCalculation.getVOIActivityDetermination() != null) {
				VOIActivityDetermination VOIActivityDetermination = VOISegmentationEnergyDepositionCalculation.getVOIActivityDetermination();
				Individual VOIActivityDeterminationIndividual = createIndiv(generateName("VOI_activity_determination"), model.getResource(racineURI+"VOI_activity_determination"));

				addObjectProperty(VOIActivityDeterminationIndividual, racineURI+"part_of_study", clinicalresearchStudy);
				addObjectProperty(VOIActivityDeterminationIndividual, racineObo+"BFO_0000132", internalRadiotherapy);
				
				ProcessExecutionContext processExecutionContext1 = VOIActivityDetermination.getProcessExecutionContext();
				translateProcessExecutionContext(processExecutionContext1, VOIActivityDeterminationIndividual);

				Individual segmentation = tableSegmentation.get(VOIActivityDetermination.getSegmentationIdentifierUsed());
				addObjectProperty(VOIActivityDeterminationIndividual, racineURI+"has_specified_input", segmentation);

				String timePointId = VOIActivityDetermination.getTimePointIdentifierUsed();
				Individual timePoint = tableTimePoint.get(timePointId);

				NMRelevantCalibrationReference SPECTCalibrationFactorReferenceUsed = VOIActivityDetermination.getSPECTCalibrationFactorReferenceUsed();

				NMRelevantCalibrationReference SPECTRecoveryCoefficientCurveReferenceUsed = VOIActivityDetermination.getSPECTRecoveryCoefficientCurveReferenceUsed();

				if (VOIActivityDetermination.getDataActivityPerVOIAtTimePointContainer()!=null) {
					Iterator<DataActivityPerVOIAtTimePoint> DataActivityPerVOIAtTimePointProducedIterator = VOIActivityDetermination.getDataActivityPerVOIAtTimePointContainer().getDataActivityPerVOIAtTimePointProduced().iterator();
					while (DataActivityPerVOIAtTimePointProducedIterator.hasNext()) {
						DataActivityPerVOIAtTimePoint DataActivityPerVOIAtTimePointProduced = DataActivityPerVOIAtTimePointProducedIterator.next();
						Individual dataActivity = createIndiv(generateName("radiopharmaceutical_activity_per_VOI_at_timepoint"), model.getResource(racineURI+"radiopharmaceutical_activity_per_VOI_at_timepoint"));
						Individual timePoint1 = tableTimePoint.get(DataActivityPerVOIAtTimePointProduced.getTimePointIdentifier());
						Individual voi1 = tableVOI.get(DataActivityPerVOIAtTimePointProduced.getVOIIdentifier().toString());
						addDataProperty(dataActivity, "http://purl.obolibrary.org/obo/IAO_0000004", DataActivityPerVOIAtTimePointProduced.getDataActivityValue());
						ActivityUnit unit = DataActivityPerVOIAtTimePointProduced.getActivityUnit();
						addDataProperty(dataActivity, racineObo+"IAO_0000039", unit.toString().toLowerCase());
						addObjectProperty(dataActivity, racineURI+"is_quantity_measured_in", voi1);
						addObjectProperty(dataActivity, racineURI+"is_quantity_measured_at", timePoint1);
						addObjectProperty(VOIActivityDeterminationIndividual, racineURI+"has_specified_output", dataActivity);
					}
				}

				Iterator<NonDICOMData> voxelActivityMapProducedIterator = VOIActivityDetermination.getVoxelActivityMapProduced().getNonDICOMData().iterator();
				while (voxelActivityMapProducedIterator.hasNext()) {
					NonDICOMData voxelActivityMapProduced = voxelActivityMapProducedIterator.next();
					Individual voxelDataActivity = createIndiv(generateName("voxel_data_activity"), model.getResource(racineURI+"voxel_data_activity"));
					translateNonDicomDataObject(voxelActivityMapProduced, voxelDataActivity);
					addObjectProperty(VOIActivityDeterminationIndividual, racineURI+"has_specified_output", voxelDataActivity);
					addObjectProperty(VOIActivityDeterminationIndividual, racineURI+"has_patient", human);
				}
			}

			EnergyDepositionRateCalculationIn3DDosimetry energyDepositionRateCalculationIn3DDosimetry = VOISegmentationEnergyDepositionCalculation.getEnergyDepositionRateCalculationIn3DDosimetry();
			Individual calculationEnergyDeposition1 = createIndiv(generateName("calculation_of_energy_deposition_rate"), model.getResource(racineURI+"calculation_of_energy_deposition_rate"));
			addObjectProperty(calculationEnergyDeposition1, racineObo+"BFO_0000132", dosimetryOfTRT2);
			
			processExecutionContext = energyDepositionRateCalculationIn3DDosimetry.getProcessExecutionContext();
			translateProcessExecutionContext(processExecutionContext, calculationEnergyDeposition1);

			if (energyDepositionRateCalculationIn3DDosimetry.getVOIIdentifierUsed()!=null) {
				Iterator<BigInteger> VOIIdentifierUsedIterator = energyDepositionRateCalculationIn3DDosimetry.getVOIIdentifierUsed().getVOIIdentifierUsed().iterator();
				while (VOIIdentifierUsedIterator.hasNext()) {
					BigInteger VOIIdentifierUsed = VOIIdentifierUsedIterator.next();
					Individual voi = tableVOI.get(VOIIdentifierUsed.toString());
					addObjectProperty(calculationEnergyDeposition1, racineURI+"has_specified_input", voi);
				}
			}

			Individual timePoint = tableTimePoint.get(energyDepositionRateCalculationIn3DDosimetry.getTimePointIdentifierUsed());

			DICOMData CTReconResampledOnNMReferenceUsed = energyDepositionRateCalculationIn3DDosimetry.getCTReconResampledOnNMReferenceUsed();
			Individual CTReconUsedIndiv = getDicomInputOutput(CTReconResampledOnNMReferenceUsed.getDICOMStudyUID(), CTReconResampledOnNMReferenceUsed.getDICOMSeriesUID(), typeInputOutput.CTRecon);
			addObjectProperty(calculationEnergyDeposition1, racineURI+"has_specified_input", CTReconUsedIndiv);

			NMTomoReconUsed = energyDepositionRateCalculationIn3DDosimetry.getNMTomoReconUsed();
			Individual NMTomoReconUsedIndiv = getDicomInputOutput(NMTomoReconUsed.getDICOMStudyUID(), NMTomoReconUsed.getDICOMSeriesUID(), typeInputOutput.NMTomo);
			addObjectProperty(calculationEnergyDeposition1, racineURI+"has_specified_input", NMTomoReconUsedIndiv);

			CalculationAlgorithmUsed calculationAlgorithmUsed = energyDepositionRateCalculationIn3DDosimetry.getCalculationAlgorithmUsed();
			addDataProperty(calculationEnergyDeposition1, racineURI+"has_method_name", calculationAlgorithmUsed.toString());

			if (energyDepositionRateCalculationIn3DDosimetry.getKernelLimitForConvolutionsUsed()!=null) {
				KernelLimitForConvolutions kernelLimitForConvolutionsUsed = energyDepositionRateCalculationIn3DDosimetry.getKernelLimitForConvolutionsUsed();
				//TODO classe ?
			}

			NonDICOMData EnergyDepositionRateMatrixAtTimePointProduced = energyDepositionRateCalculationIn3DDosimetry.getThreeDimEnergyDepositionRateMatrixAtTimePointProduced();
			Individual EnergyDepositionRateMatrixAtTimePointProducedIndiv = createIndiv(generateName("3D_energy_deposition_rate_at_timepoint"), model.getResource(racineURI+"3D_energy_deposition_rate_at_timepoint"));
			translateNonDicomDataObject(EnergyDepositionRateMatrixAtTimePointProduced, EnergyDepositionRateMatrixAtTimePointProducedIndiv);
			addObjectProperty(calculationEnergyDeposition1, racineURI+"has_specified_output", EnergyDepositionRateMatrixAtTimePointProducedIndiv);

			Iterator<SumAndScalingAbsorbedDoseRateCalculation> sumAndScalingAbsorbedDoseRateCalculationIterator = VOISegmentationEnergyDepositionCalculation.getSumAndScalingAbsorbedDoseRateCalculationContainer().getSumAndScalingAbsorbedDoseRateCalculation().iterator();
			while (sumAndScalingAbsorbedDoseRateCalculationIterator.hasNext()) {
				SumAndScalingAbsorbedDoseRateCalculation sumAndScalingAbsorbedDoseRateCalculation = sumAndScalingAbsorbedDoseRateCalculationIterator.next();
				Individual SumAndScalingAbsorbedDoseRateCalculationIndiv = createIndiv(generateName("summing_and_scaling_absorbed_dose_rate_calculation"), model.getResource(racineURI+"summing_and_scaling_absorbed_dose_rate_calculation"));
				addObjectProperty(SumAndScalingAbsorbedDoseRateCalculationIndiv, racineObo+"BFO_0000132", dosimetryOfTRT2);
				translateProcessExecutionContext(sumAndScalingAbsorbedDoseRateCalculation.getProcessExecutionContext(), SumAndScalingAbsorbedDoseRateCalculationIndiv);

				if (sumAndScalingAbsorbedDoseRateCalculation.getVOIIdentifierUsed()!=null) {
					Iterator<BigInteger> VOIIdentifierUsedIterator = sumAndScalingAbsorbedDoseRateCalculation.getVOIIdentifierUsed().getVOIIdentifierUsed().iterator();
					while (VOIIdentifierUsedIterator.hasNext()) {
						BigInteger VOIIdentifierUsed = VOIIdentifierUsedIterator.next();
						Individual voi = tableVOI.get(VOIIdentifierUsed.toString());
						addObjectProperty(SumAndScalingAbsorbedDoseRateCalculationIndiv, racineURI+"has_specified_input", voi);
					}
				}

				String TimePointId = sumAndScalingAbsorbedDoseRateCalculation.getTimePointIdentifierUsed();
				timepoint = tableTimePoint.get(TimePointId);
				addObjectProperty(SumAndScalingAbsorbedDoseRateCalculationIndiv, racineURI+"is_about", timepoint);

				if (sumAndScalingAbsorbedDoseRateCalculation.getSegmentationUsed()!=null) {
					Individual segmentation = tableSegmentation.get(sumAndScalingAbsorbedDoseRateCalculation.getSegmentationUsed());
					addObjectProperty(SumAndScalingAbsorbedDoseRateCalculationIndiv, racineURI+"has_specified_input", segmentation);
				}

				NonDICOMData EnergyDepositionRateMatrixAtTimePointUsed = sumAndScalingAbsorbedDoseRateCalculation.getThreeDimEnergyDepositionRateMatrixAtTimePointUsed();
				Individual EnergyDepositionRateMatrixAtTimePointProducedIndiv1 = createIndiv(generateName("3D_energy_deposition_rate_at_timepoint"), model.getResource(racineURI+"3D_energy_deposition_rate_at_timepoint"));
				translateNonDicomDataObject(EnergyDepositionRateMatrixAtTimePointUsed, EnergyDepositionRateMatrixAtTimePointProducedIndiv1);
				addObjectProperty(SumAndScalingAbsorbedDoseRateCalculationIndiv, racineURI+"has_specified_input", EnergyDepositionRateMatrixAtTimePointProducedIndiv1);

				Iterator<AbsorbedDoseRatePerVOIAtTimePoint> AbsorbedDoseRatePerVOIAtTimePointProducedIterator = sumAndScalingAbsorbedDoseRateCalculation.getAbsorbedDoseRatePerVOIAtTimePointProduced().getAbsorbedDoseRatePerVOIAtTimePointProduced().iterator();
				while (AbsorbedDoseRatePerVOIAtTimePointProducedIterator.hasNext()) {
					AbsorbedDoseRatePerVOIAtTimePoint AbsorbedDoseRatePerVOIAtTimePointProduced = AbsorbedDoseRatePerVOIAtTimePointProducedIterator.next();
					Individual AbsorbedDoseRatePerVOIAtTimePointProducedIndiv = createIndiv(generateName("absorbed_dose_rate_per_VOI_at_timepoint"), model.getResource(racineURI+"absorbed_dose_rate_per_VOI_at_timepoint"));

					addDataProperty(AbsorbedDoseRatePerVOIAtTimePointProducedIndiv, "http://purl.obolibrary.org/obo/IAO_0000004", AbsorbedDoseRatePerVOIAtTimePointProduced.getAbsorbedDoseRateValue());
					addDataProperty(AbsorbedDoseRatePerVOIAtTimePointProducedIndiv, racineObo+"IAO_0000039", AbsorbedDoseRatePerVOIAtTimePointProduced.getAbsorbedDoseRateUnit().toString().toLowerCase());

					Individual voi = tableVOI.get(AbsorbedDoseRatePerVOIAtTimePointProduced.getVOIIdentifier().toString());
					Individual timePoint1 = tableTimePoint.get(AbsorbedDoseRatePerVOIAtTimePointProduced.getTimePointIdentifier());
					addObjectProperty(AbsorbedDoseRatePerVOIAtTimePointProducedIndiv, racineURI+"is_quantity_measured_in", voi);
					addObjectProperty(AbsorbedDoseRatePerVOIAtTimePointProducedIndiv, racineURI+"is_quantity_measured_at", timePoint1);
				}
			}
		}

		Iterator<DoseRateCurveFitVOITimeIntegration> doseRateCurveFitVOITimeIntegrationIterator = threeDimDosimetrySlide2Workflow.getDoseRateCurveFitVOITimeIntegrationContainer().getDoseRateCurveFitVOITimeIntegration().iterator();
		while (doseRateCurveFitVOITimeIntegrationIterator.hasNext()) {
			DoseRateCurveFitVOITimeIntegration doseRateCurveFitVOITimeIntegration = doseRateCurveFitVOITimeIntegrationIterator.next();
			Individual doseRateCurveFitVOITimeIntegrationIndiv = createIndiv(generateName("time_absorbed_dose_rate_curve_fitting"), model.getResource(racineURI+"time_absorbed_dose_rate_curve_fitting"));
			addObjectProperty(doseRateCurveFitVOITimeIntegrationIndiv, racineObo+"BFO_0000132", dosimetryOfTRT2);
			translateProcessExecutionContext(doseRateCurveFitVOITimeIntegration.getProcessExecutionContext(), doseRateCurveFitVOITimeIntegrationIndiv);

			Iterator<BigInteger> VOIIdentifierUsedIiterator = doseRateCurveFitVOITimeIntegration.getVOIIdentifierUsed().getVOIIdentifierUsed().iterator();
			while (VOIIdentifierUsedIiterator.hasNext()) {
				BigInteger VOIIdentifierUsed = VOIIdentifierUsedIiterator.next();
				Individual voi = tableVOI.get(VOIIdentifierUsed.toString());
				addObjectProperty(doseRateCurveFitVOITimeIntegrationIndiv, racineURI+"has_specified_input", voi);
			}

			Iterator<String> timePointIdentifierUsedIterator = doseRateCurveFitVOITimeIntegration.getTimePointIdentifierUsed().getTimePointIdentifierUsed().iterator();
			while (timePointIdentifierUsedIterator.hasNext()) {
				String timePointIdentifierUsed = timePointIdentifierUsedIterator.next();
				Individual timepoint = tableTimePoint.get(timePointIdentifierUsed);
			}

			doseRateCurveFitVOITimeIntegration.getPKAssessmentMethodUsed();
			Individual CurveFittingMethod = createIndiv(generateName("curve_fitting_method"), model.getResource(racineURI+"curve_fitting_method"));
			
			Individual IncorporationFunction;
			switch (doseRateCurveFitVOITimeIntegration.getPKAssessmentMethodUsed().getIncorporationFunction()) {
			case CONSTANT:
				IncorporationFunction = createIndiv(generateName("constant_incorporation_mode"), model.getResource(racineURI+"constant_incorporation_mode"));
				break;
			case EXPONENTIAL:
				IncorporationFunction = createIndiv(generateName("exponential_incorporation_mode"), model.getResource(racineURI+"exponential_incorporation_mode"));
				break;
			case LINEAR:
				IncorporationFunction = createIndiv(generateName("linear_incorporation_mode"), model.getResource(racineURI+"linear_incorporation_mode"));
				break;
			default:
				IncorporationFunction = createIndiv(generateName("curve_fitting_method"), model.getResource(racineURI+"curve_fitting_method"));
				break;			
			}
			addObjectProperty(CurveFittingMethod, racineObo+"BFO_0000110", IncorporationFunction);

			Individual integrationAlgorithm;
			switch (doseRateCurveFitVOITimeIntegration.getPKAssessmentMethodUsed().getIntegrationAlgorithm()) {
			case BI_EXPONENTIAL:
				integrationAlgorithm = createIndiv(generateName("bi_exponential_integration_algorithm"), model.getResource(racineURI+"bi_exponential_integration_algorithm"));
				break;
			case MONO_EXPONENTIAL:
				integrationAlgorithm = createIndiv(generateName("mono_exponential_integration_algorithm"), model.getResource(racineURI+"mono_exponential_integration_algorithm"));
				break;
			case OTHER:
				integrationAlgorithm = createIndiv(generateName("other_integration_algorithm"), model.getResource(racineURI+"other_integration_algorithm"));
				break;
			case TRAPEZOID:
				integrationAlgorithm = createIndiv(generateName("trapezoid_integration_algorithm"), model.getResource(racineURI+"trapezoid_integration_algorithm"));
				break;
			case TRI_EXPONENTIAL:
				integrationAlgorithm = createIndiv(generateName("tri_exponential_integration_algorithm"), model.getResource(racineURI+"tri_exponential_integration_algorithm"));
				break;
			case X_EXPONENTIAL:
				integrationAlgorithm = createIndiv(generateName("x_exponential_integration_algorithm"), model.getResource(racineURI+"x_exponential_integration_algorithm"));
				break;
			default:
				integrationAlgorithm = createIndiv(generateName("other_integration_algorithm"), model.getResource(racineURI+"other_integration_algorithm"));
				break;
			}
			addObjectProperty(CurveFittingMethod, racineObo+"BFO_0000110", integrationAlgorithm);
			
			addObjectProperty(doseRateCurveFitVOITimeIntegrationIndiv, racineURI+"has_protocol", CurveFittingMethod);

			String fittingFunction = doseRateCurveFitVOITimeIntegration.getPKAssessmentMethodUsed().getFittingFunction();
			addDataProperty(CurveFittingMethod, racineURI+"has_name", fittingFunction);
			addObjectProperty(CurveFittingMethod, racineURI+"is_protocol_used_in", doseRateCurveFitVOITimeIntegrationIndiv);
			
	
			//AdministeredActivity preAdministeredActivityUsed = doseRateCurveFitVOITimeIntegration.getPreAdministeredActivityUsed();
			//Individual preActivity = createIndiv(generateName("pre-administration_measured_activity"), model.getResource(racineDCM+"113508"));
			//addDataProperty(preActivity, "http://purl.obolibrary.org/obo/IAO_0000004", preAdministeredActivityUsed.getAdministeredActivityValue());
			//addObjectProperty(preActivity, racineObo+"IAO_0000039", getUnit(preAdministeredActivityUsed.getActivityUnit().toString()));
			addObjectProperty(preAdministeredActivityIndiv, racineURI+"is_about", RadiopharmaceuticalAdmin);

			AdministeredActivity postAdministeredActivityUsed = doseRateCurveFitVOITimeIntegration.getPostAdministeredActivityUsed();
			
			if (postAdministeredActivityUsed!=null) {
				//Individual postActivity = createIndiv(generateName("post-administration_measured_activity"), model.getResource(racineDCM+"113509"));
				//addDataProperty(postActivity, "http://purl.obolibrary.org/obo/IAO_0000004", postAdministeredActivityUsed.getAdministeredActivityValue());
				//addObjectProperty(postActivity, racineObo+"IAO_0000039", getUnit(postAdministeredActivityUsed.getActivityUnit().toString()));
				addObjectProperty(postAdministeredActivityIndiv, racineURI+"is_about", RadiopharmaceuticalAdmin);
			}

			Iterator<AbsorbedDoseInVOI> AbsorbedDoseInVOIProducedIterator = doseRateCurveFitVOITimeIntegration.getAbsorbedDoseInVOIProduced().getAbsorbedDoseInVOIProduced().iterator();
			while (AbsorbedDoseInVOIProducedIterator.hasNext()) {
				AbsorbedDoseInVOI AbsorbedDoseInVOIProduced = AbsorbedDoseInVOIProducedIterator.next();
				Individual absorbedDose = createIndiv(generateName("absorbed_dose_rate_per_VOI_at_timepoint"), model.getResource(racineURI+"absorbed_dose_rate_per_VOI_at_timepoint"));
				addDataProperty(absorbedDose, "http://purl.obolibrary.org/obo/IAO_0000004", AbsorbedDoseInVOIProduced.getAbsorbedDoseInVOIValue());
				addObjectProperty(absorbedDose, racineObo+"IAO_0000039", getUnit(AbsorbedDoseInVOIProduced.getAbsorbedDoseUnit().toString()));
				if (AbsorbedDoseInVOIProduced.getAbsorbedDoseInVOIUncertainty()!=null) {
					Individual rangeOfUncertaintyValue = createIndiv(generateName("range_of_uncertainty_value"), model.getResource(racineURI+"range_of_uncertainty_value"));
					addDataProperty(rangeOfUncertaintyValue, "http://purl.obolibrary.org/obo/IAO_0000004", AbsorbedDoseInVOIProduced.getAbsorbedDoseInVOIUncertainty());
					addObjectProperty(rangeOfUncertaintyValue, racineURI+"is_about", absorbedDose);
					switch (AbsorbedDoseInVOIProduced.getAbsorbedDoseUnit()) {
					case GRAY:
						addObjectProperty(rangeOfUncertaintyValue, racineObo+"IAO_0000039", getUnit("gray"));
						break;
					case MILLIGRAY:
						addObjectProperty(rangeOfUncertaintyValue, racineObo+"IAO_0000039", getUnit("mGy"));
						break;
					case MILLIGRAY_PER_100_MILLIAMPERE_SECOND:
						addObjectProperty(rangeOfUncertaintyValue, racineObo+"IAO_0000039", getUnit("milligray per (100 milliampere second)"));
						break;
					case MILLIGRAY_PER_MILLIGRAY_PER_100_MILLIAMPERE_SECOND:
						addObjectProperty(rangeOfUncertaintyValue, racineObo+"IAO_0000039", getUnit("milligray per (milligray per (100 milliampere second)"));
						break;
					case MILLIGRAY_PER_MILLIGRAY_PER_1_MILLIAMPERE_SECOND:
						addObjectProperty(rangeOfUncertaintyValue, racineObo+"IAO_0000039", getUnit("milligray per (milligray per (1 milliampere second))"));
						break;
					default:
						break;
					}
				}
				Iterator<BigInteger> VOIIdentifierUsedIterator = AbsorbedDoseInVOIProduced.getVOIIdentifierList().getVOIIdentifierUsed().iterator();
				while (VOIIdentifierUsedIterator.hasNext()) {
					BigInteger VOIIdentifierUsed = VOIIdentifierUsedIterator.next();
					Individual voi = tableVOI.get(VOIIdentifierUsed.toString());
					addObjectProperty(absorbedDose, racineURI+"is_quantity_measured_in", voi);
				}
			}

			if (threeDimDosimetrySlide2Workflow.getRadioBiologicalCalculationInHybridOr3DSlide2Dosimetry()!=null) {
				RadioBiologicalCalculationInHybridOr3DSlide2Dosimetry radioBiologicalCalculationInHybridOr3DSlide2Dosimetry = threeDimDosimetrySlide2Workflow.getRadioBiologicalCalculationInHybridOr3DSlide2Dosimetry();
				Individual radioBiologicalCalculation = createIndiv(generateName("radio_biological_calculation"), model.getResource(racineURI+"radio_biological_calculation"));
				translateProcessExecutionContext(radioBiologicalCalculationInHybridOr3DSlide2Dosimetry.getProcessExecutionContext(), radioBiologicalCalculation);

				addObjectProperty(radioBiologicalCalculation, racineURI+"has_patient", clinicalresearchStudy);
				addObjectProperty(radioBiologicalCalculation, racineObo+"BFO_0000132", internalRadiotherapy);
				
				String biologicalEffectiveDose = radioBiologicalCalculationInHybridOr3DSlide2Dosimetry.getBiologicalEffectiveDose();
				Individual effectiveDose = createIndiv(generateName("effective_dose"), model.getResource("effective_dose"));
				addDataProperty(effectiveDose, "http://purl.obolibrary.org/obo/IAO_0000004", biologicalEffectiveDose);

				Iterator<BigInteger> VOIIdentifierUsedIterator = radioBiologicalCalculationInHybridOr3DSlide2Dosimetry.getVOIIdentifierUsed().getVOIIdentifierUsed().iterator();
				while (VOIIdentifierUsedIterator.hasNext()) {
					BigInteger VOIIdentifierUsed = VOIIdentifierUsedIterator.next();
					Individual voi = tableVOI.get(VOIIdentifierUsed.toString());
					addObjectProperty(radioBiologicalCalculation, racineURI+"has_specified_input", voi);
					addObjectProperty(effectiveDose, racineURI+"is_quantity_measured_in", voi);
				}

				String radioBiologicalCalculationMethod = radioBiologicalCalculationInHybridOr3DSlide2Dosimetry.getRadioBiologicalCalculationMethod();
				addDataProperty(radioBiologicalCalculation, racineURI+"has_method_name",radioBiologicalCalculationMethod);

				String radioBiologicalCalculationParameters = radioBiologicalCalculationInHybridOr3DSlide2Dosimetry.getRadioBiologicalCalculationParameters();
			}

			if (threeDimDosimetrySlide2Workflow.getRegistrationVOISegmentationAndPropagationContainer() !=null) {
				Iterator<RegistrationVOISegmentationAndPropagation> registrationVOISegmentationAndPropagationIterator = threeDimDosimetrySlide2Workflow.getRegistrationVOISegmentationAndPropagationContainer().getRegistrationVOISegmentationAndPropagation().iterator();
				while (registrationVOISegmentationAndPropagationIterator.hasNext()) {
					RegistrationVOISegmentationAndPropagation registrationVOISegmentationAndPropagation = registrationVOISegmentationAndPropagationIterator.next();
					Individual registration = createIndiv(generateName("registration_VOI_segmentation_and_propagation"), model.getResource(racineURI+"registration_VOI_segmentation_and_propagation"));
					addObjectProperty(registration, racineURI+"part_of_study", clinicalresearchStudy);
					addObjectProperty(registration, racineObo+"BFO_0000132", internalRadiotherapy);
					//addObjectProperty(registration,racineURI+"has_specified_input", NMreconTomoDataset);

					ProcessExecutionContext processExecutionContext = registrationVOISegmentationAndPropagation.getProcessExecutionContext();
					translateProcessExecutionContext(processExecutionContext, registration);

					Individual timePointUsed = tableTimePoint.get(registrationVOISegmentationAndPropagation.getTimePointIdentifierUsed());

					Segmentation segmentation = registrationVOISegmentationAndPropagation.getSegmentation();
					Individual segmentationIndiv = translateSegmentation(segmentation);

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
								CTReconResampled = createIndiv(generateName("resampled_CT_image_dataset"), model.getResource(racineURI+"resampled_CT_image_dataset"));
								setDicomInputOutput(studyID, seriesID, typeInputOutput.CTRecon, CTReconResampled);
								addDataProperty(CTReconResampled, racineURI+"has_DICOM_series_instance_UID", seriesID);
								addDataProperty(CTReconResampled, racineURI+"has_DICOM_study_instance_UID", studyID);
							}
							addObjectProperty(registration, racineURI+"has_specified_output", CTReconResampled);
							addObjectProperty(CTReconResampled, racineURI+"has_patient", patient);
							addObjectProperty(segmentationIndiv, racineURI+"is_specified_output_of", CTReconResampled);
						}
					}

					if (registrationVOISegmentationAndPropagation.getCountsPerVOIAtTimePointProducedContainer()!=null) {
						Iterator<CountsPerVOIAtTimePoint> countsPerVOIAtTimePointProducedIterator = registrationVOISegmentationAndPropagation.getCountsPerVOIAtTimePointProducedContainer().getCountsPerVOIAtTimePointProduced().iterator();
						while (countsPerVOIAtTimePointProducedIterator.hasNext()) {
							CountsPerVOIAtTimePoint countsPerVOIAtTimePointProduced = countsPerVOIAtTimePointProducedIterator.next();
							Individual count = createIndiv(generateName("counts_per_VOI_at_timepoint"), model.getResource(racineURI+"counts_per_VOI_at_timepoint"));
							addObjectProperty(registration, racineURI+"has_specified_output", count);
							addDataProperty(count, "http://purl.obolibrary.org/obo/IAO_0000004", countsPerVOIAtTimePointProduced.getCountsValue());
							addObjectProperty(count, racineURI+"has_patient", patient);

							CountsUnit unit = countsPerVOIAtTimePointProduced.getCountsUnit();
							switch (countsPerVOIAtTimePointProduced.getCountsUnit()) {
							case COUNTS:
								addObjectProperty(count, "has_measuement_unit_label", getUnit("counts"));
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
						Individual NMtomo;
						if (hasDicomInputOutput(DICOMStudyUID, DICOMSeriesUID, typeInputOutput.NMTomo) == true) {
							NMtomo = getDicomInputOutput(DICOMStudyUID, DICOMSeriesUID, typeInputOutput.NMTomo);
							addObjectProperty(registration, racineURI+"has_specified_input", NMtomo);
						} else {
							NMtomo = createIndiv(generateName("resampled_NM_recon_tomo_image_dataset"), model.getResource(racineURI+"resampled_NM_recon_tomo_image_dataset"));
							addDataProperty(NMtomo, racineURI+"has_DICOM_series_instance_UID", DICOMSeriesUID);
							addDataProperty(NMtomo, racineURI+"has_DICOM_study_instance_UID", DICOMStudyUID);
							setDicomInputOutput(DICOMStudyUID, DICOMSeriesUID, typeInputOutput.NMTomo, NMtomo);
						}
						addObjectProperty(registration, racineURI+"has_specified_input", NMtomo);
						addObjectProperty(NMtomo, racineURI+"has_patient", patient);
					}

					Iterator<DICOMData> CTReconUsedIterator = registrationVOISegmentationAndPropagation.getCTReconUsed().getDICOMData().iterator();
					while (CTReconUsedIterator.hasNext()) {
						DICOMData CTReconUsed = CTReconUsedIterator.next();
						String DICOMSeriesUID = CTReconUsed.getDICOMSeriesUID();
						String DICOMStudyUID = CTReconUsed.getDICOMStudyUID();
						Individual CTRecon;
						if (hasDicomInputOutput(DICOMStudyUID, DICOMSeriesUID, typeInputOutput.CTRecon) == true) {
							CTRecon = getDicomInputOutput(DICOMStudyUID, DICOMSeriesUID, typeInputOutput.CTRecon);
						} else {
							CTRecon = createIndiv(generateName("resampled_CT_image_dataset"), model.getResource(racineURI+"resampled_CT_image_dataset"));
							addDataProperty(CTRecon, racineURI+"has_DICOM_series_instance_UID", DICOMSeriesUID);
							addDataProperty(CTRecon, racineURI+"has_DICOM_study_instance_UID", DICOMStudyUID);
							setDicomInputOutput(DICOMStudyUID, DICOMSeriesUID, typeInputOutput.CTRecon, CTRecon);
						}
						addObjectProperty(registration, racineURI+"has_specified_input", CTRecon);
						addObjectProperty(CTRecon, racineURI+"has_patient", patient);
					}

					String imageProcessingMethodMethodUsed = registrationVOISegmentationAndPropagation.getImageProcessingMethodMethodUsed();
					Individual imageProcessingMethod = createIndiv(generateName("image_processing_method"), model.getResource(racineURI+"image_processing_method"));
					addObjectProperty(registration, racineURI+"has_protocol", imageProcessingMethod);
					addDataProperty(registration, racineURI+"has_name", imageProcessingMethodMethodUsed);

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
								NMTomoRecon = createIndiv(generateName("resampled_NM_recon_tomo_image_dataset"), model.getResource(racineURI+"resampled_NM_recon_tomo_image_dataset"));
								setDicomInputOutput(studyID, seriesID, typeInputOutput.NMTomo, NMTomoRecon);
								addDataProperty(NMTomoRecon, racineURI+"has_DICOM_series_instance_UID", seriesID);
								addDataProperty(NMTomoRecon, racineURI+"has_DICOM_study_instance_UID", studyID);
							}
							addObjectProperty(registration, racineURI+"has_specified_output", NMTomoRecon);
							addObjectProperty(NMTomoRecon, racineURI+"has_patient", patient);
							addObjectProperty(segmentationIndiv, racineURI+"is_specified_output_of", NMTomoRecon);
						}
					}

					if (registrationVOISegmentationAndPropagation.getNonDICOMCTReconResampledOnCommonReferenceProduced() != null) {
						Iterator<NonDICOMData> nonDICOMDataIterator = registrationVOISegmentationAndPropagation.getNonDICOMCTReconResampledOnCommonReferenceProduced().getNonDICOMData().iterator();
						while (nonDICOMDataIterator.hasNext()) {
							NonDICOMData nonDICOMData = nonDICOMDataIterator.next();
							String nonDICOMDataClass = nonDICOMData.getNonDICOMDataClass();
							NonDICOMDataFormat nonDICOMDataClassFormat = nonDICOMData.getNonDICOMDataFormat();
							Individual nonDICOMCTReconResampled;
							if (hasNonDicomInputOutput(nonDICOMData.getNonDICOMDataFileName(), nonDICOMDataClass, nonDICOMDataClassFormat)) {
								nonDICOMCTReconResampled = getNonDicomInputOutput(nonDICOMData.getNonDICOMDataFileName(), nonDICOMDataClass, nonDICOMDataClassFormat);
							} else {
								nonDICOMCTReconResampled = createIndiv(generateName("resampled_CT_image_dataset"), model.getResource(racineURI+"resampled_CT_image_dataset"));
								setNonDicomInputOutput(nonDICOMData.getNonDICOMDataFileName(), nonDICOMData.getNonDICOMDataClass(), nonDICOMCTReconResampled, nonDICOMData.getNonDICOMDataFormat());
								translateNonDicomDataObject(nonDICOMData, nonDICOMCTReconResampled);
							}
							addObjectProperty(registration, racineURI+"has_specified_output", nonDICOMCTReconResampled);
							addObjectProperty(nonDICOMCTReconResampled, racineURI+"has_patient", patient);
							addObjectProperty(segmentationIndiv, racineURI+"is_specified_output_of", nonDICOMCTReconResampled);
						}
					}

					if (registrationVOISegmentationAndPropagation.getNonDICOMNMTomoReconResampledOnCommonReferenceProduced() != null) {
						Iterator<NonDICOMData> nonDICOMDataIterator = registrationVOISegmentationAndPropagation.getNonDICOMNMTomoReconResampledOnCommonReferenceProduced().getNonDICOMData().iterator();
						while (nonDICOMDataIterator.hasNext()) {
							NonDICOMData nonDICOMData = nonDICOMDataIterator.next();
							Individual nonDICOMNMTomoRecon;
							String nonDICOMDataClass = nonDICOMData.getNonDICOMDataClass();
							NonDICOMDataFormat nonDICOMDataClassFormat = nonDICOMData.getNonDICOMDataFormat();
							if (hasNonDicomInputOutput(nonDICOMData.getNonDICOMDataFileName(), nonDICOMDataClass, nonDICOMDataClassFormat)) {
								nonDICOMNMTomoRecon = getNonDicomInputOutput(nonDICOMData.getNonDICOMDataFileName(), nonDICOMDataClass, nonDICOMDataClassFormat);
							} else {
								nonDICOMNMTomoRecon = createIndiv(generateName("resampled_NM_recon_tomo_image_dataset"), model.getResource(racineURI+"resampled_NM_recon_tomo_image_dataset"));
								translateNonDicomDataObject(nonDICOMData, nonDICOMNMTomoRecon);
								setNonDicomInputOutput(nonDICOMData.getNonDICOMDataFileName(), nonDICOMData.getNonDICOMDataClass(), nonDICOMNMTomoRecon, nonDICOMData.getNonDICOMDataFormat());
							}
							addObjectProperty(registration, racineURI+"has_specified_output", nonDICOMNMTomoRecon);
							addObjectProperty(nonDICOMNMTomoRecon, racineURI+"has_patient", patient);
							addObjectProperty(segmentationIndiv, racineURI+"is_specified_output_of", nonDICOMNMTomoRecon);
						}
					}

					if (registrationVOISegmentationAndPropagation.getDensityImageProduced()!= null) {
						Iterator<NonDICOMData> nonDICOMDataIterator = registrationVOISegmentationAndPropagation.getNonDICOMNMTomoReconResampledOnCommonReferenceProduced().getNonDICOMData().iterator();
						while (nonDICOMDataIterator.hasNext()) {
							NonDICOMData nonDICOMData = nonDICOMDataIterator.next();
							Individual densityImage;
							String nonDICOMDataClass = nonDICOMData.getNonDICOMDataClass();
							NonDICOMDataFormat nonDICOMDataClassFormat = nonDICOMData.getNonDICOMDataFormat();
							if (hasNonDicomInputOutput(nonDICOMData.getNonDICOMDataFileName(), nonDICOMDataClass, nonDICOMDataClassFormat)) {
								densityImage = getNonDicomInputOutput(nonDICOMData.getNonDICOMDataFileName(), nonDICOMDataClass, nonDICOMDataClassFormat);
							} else {
								densityImage = createIndiv(generateName("density_image"), model.getResource(racineURI+"density_image")); 
								translateNonDicomDataObject(nonDICOMData, densityImage);
								setNonDicomInputOutput(nonDICOMData.getNonDICOMDataFileName(), nonDICOMData.getNonDICOMDataClass(), densityImage, nonDICOMData.getNonDICOMDataFormat());
							}
							addObjectProperty(registration, racineURI+"has_specified_output", densityImage);
							addObjectProperty(registration, racineURI+"has_patient", patient);
						}
					}

					if (registrationVOISegmentationAndPropagation.getGeometricalTransformationContainer() != null) {
						translateGeometricalTransformation(registrationVOISegmentationAndPropagation.getGeometricalTransformationContainer(), registration, timePointUsed);
					}
				}


				if (threeDimDosimetrySlide2Workflow.getAbsorbedDoseCalculationInVOI()!=null) {
					AbsorbedDoseCalculationInVOI absorbedDoseCalculationInVOI = threeDimDosimetrySlide2Workflow.getAbsorbedDoseCalculationInVOI();
					Individual calculationOfMeanAbsorbedDosesInVOI = createIndiv(generateName("calculation_of_mean_absorbed_doses_in_VOIs"), model.getResource(racineURI+"calculation_of_mean_absorbed_doses_in_VOIs"));
					ProcessExecutionContext processExecutionContext = absorbedDoseCalculationInVOI.getProcessExecutionContext();
					translateProcessExecutionContext(processExecutionContext, calculationOfMeanAbsorbedDosesInVOI);
					addObjectProperty(calculationOfMeanAbsorbedDosesInVOI, racineObo+"BFO_0000132", dosimetryOfTRT2);
					addObjectProperty(calculationOfMeanAbsorbedDosesInVOI, racineObo+"BFO_0000132", internalRadiotherapy);
					
					if (absorbedDoseCalculationInVOI.getSegmentationIdentifierUsed()!=null) {
						logger.debug("segmentation Id : "+absorbedDoseCalculationInVOI.getSegmentationIdentifierUsed().toString());
						Individual segmentation = tableSegmentation.get(absorbedDoseCalculationInVOI.getSegmentationIdentifierUsed());
						addObjectProperty(calculationOfMeanAbsorbedDosesInVOI, racineURI+"has_specified_input", segmentation);
					}
					
					Individual absorbedDoseMethod = createIndiv(generateName("absorbed_dose_estimate_method"), model.getResource(racineURI+"absorbed_dose_estimate_method"));
					addDataProperty(absorbedDoseMethod, racineURI+"has_name", absorbedDoseCalculationInVOI.getAbsorbedDoseCalculationMethodUsed());
					addObjectProperty(calculationOfMeanAbsorbedDosesInVOI, racineURI+"has_protocol", absorbedDoseMethod);

					if (absorbedDoseCalculationInVOI.getVoxelAbsorbedDoseMapProduced()!=null) {
						Iterator<NonDICOMData> voxelAbsorbedDoseMapProducedIterator = absorbedDoseCalculationInVOI.getVoxelAbsorbedDoseMapProduced().getNonDICOMData().iterator();
						while (voxelAbsorbedDoseMapProducedIterator.hasNext()) {
							NonDICOMData voxelAbsorbedDoseMapProduced = voxelAbsorbedDoseMapProducedIterator.next();
							Individual voxelAbsorbedDoseMap = createIndiv(generateName("3D_absorbed_dose_map"), model.getResource(racineDCM+"128487"));
							translateNonDicomDataObject(voxelAbsorbedDoseMapProduced, voxelAbsorbedDoseMap);
							setNonDicomInputOutput(voxelAbsorbedDoseMapProduced.getNonDICOMDataFileName(), voxelAbsorbedDoseMapProduced.getNonDICOMDataClass(), voxelAbsorbedDoseMap, voxelAbsorbedDoseMapProduced.getNonDICOMDataFormat());
						}
					}

					if (absorbedDoseCalculationInVOI.getAbsorbedDoseInVOIContainer()!=null) {
						translateTotalAbsorbedDosePerVOI(absorbedDoseCalculationInVOI.getAbsorbedDoseInVOIContainer(), absorbedDoseMethod, calculationOfMeanAbsorbedDosesInVOI);
					}
				}
			}
		}
	}

	public static void retrieveSubtastk212(NonDicomFileSetDescriptor nonDicomFileSetDescriptor) {
		WP2Subtask212WorkflowData subtask212;
		CTSegmentation ctSegmentation =  null; Individual imageSegmentation = null; Individual institution;
		Individual image; Individual segMeth; MethodSettingType settingTest;
		Individual ctDosiMc = null; Individual absorbedDoseVoi = null;
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
		Individual ctImageDataSet = null; 
		Individual calculationOfMeanAbsorbedDosesinVOIs; 
		ArrayList<Individual> listeDosiMc = new ArrayList<Individual>();
		//Individual modelAttenuator;

		subtask212 = nonDicomFileSetDescriptor.getWP2Subtask212WorkflowData();
		SimpleCTMonteCarloDosimetry ctMonteCarloDosimetry; DICOMData imageUsed;

		if (subtask212.getSimpleCTMonteCarloDosimetry() !=null) {
			Iterator<SimpleCTMonteCarloDosimetry> ctMonteCarloDosimetryIter = subtask212.getSimpleCTMonteCarloDosimetry().iterator();
			while (ctMonteCarloDosimetryIter.hasNext()) {
				ctMonteCarloDosimetry = ctMonteCarloDosimetryIter.next();
				ctDosiMc = createIndiv(generateName("Monte_Carlo_CT_dosimetry"), model.getResource(racineURI+"Monte_Carlo_CT_dosimetry")); // process
				addObjectProperty(ctDosiMc, racineURI+"part_of_study", clinicalresearchStudy);

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
			addObjectProperty(imageSegmentation, racineURI+"part_of_study", clinicalresearchStudy);
			
			translateProcessExecutionContext(ctSegmentation.getProcessExecutionContext(), imageSegmentation);

			if (ctImageDataSet!=null) {
				addObjectProperty(imageSegmentation, racineURI+"has_specified_input", ctImageDataSet);
			}


			String institName = subtask212.getCTSegmentation().getProcessExecutionContext().getPerformingInstitution();
			institution = memory.getInstitution(institName);
			roleOfOrganization = memory.getRoleOfResponsibleOrganization(institName);

			addObjectProperty(institution, racineObo+"BFO_0000161", roleOfOrganization);
			addObjectProperty(roleOfOrganization,racineObo+"BFO_0000054",imageSegmentation);

			if (ctSegmentation.getDICOMImageUsed().getDICOMSeriesUID()!=null) {
				addDataProperty(imageSegmentation, racineURI+"has_DICOM_series_instance_UID", ctSegmentation.getDICOMImageUsed().getDICOMSeriesUID());
			}
			if (ctSegmentation.getDICOMImageUsed().getDICOMStudyUID()!=null) {
				addDataProperty(imageSegmentation, racineURI+"has_DICOM_study_instance_UID", ctSegmentation.getDICOMImageUsed().getDICOMStudyUID());
			}

			if (ctSegmentation.getSegmentationMethodUsed().getSegmentationMethod()!=null) {
				if (ctSegmentation.getSegmentationMethodUsed().getSegmentationMethod().compareToIgnoreCase("Interactive contour segmentation")==0) {
					segMeth = createIndiv(generateName("interactive_contour_segmentation_method"), model.getResource(racineURI+"interactive_contour_segmentation_method"));
				} else {
					segMeth = createIndiv(generateName("image_segmentation_method"), model.getResource(racineURI+"image_segmentation_method"));
				}
				addObjectProperty(imageSegmentation, racineURI+"has_protocol", segMeth);
				if (ctSegmentation.getSegmentationMethodUsed().getSoftwareNamesUsed()!=null) {
					for (int i =0; i<ctSegmentation.getSegmentationMethodUsed().getSoftwareNamesUsed().getSoftwareName().size(); i++) {
						addObjectProperty(imageSegmentation, racineURI+"has_agent", memory.getSoftware(ctSegmentation.getSegmentationMethodUsed().getSoftwareNamesUsed().getSoftwareName().get(i)));
					}
				}

				if (ctSegmentation.getSegmentationMethodUsed().getSegmentationMethodSetting()!=null) {
					for (int i =0; i<ctSegmentation.getSegmentationMethodUsed().getSegmentationMethodSetting().getMethodSettingType().size(); i++) {
						settingTest = ctSegmentation.getSegmentationMethodUsed().getSegmentationMethodSetting().getMethodSettingType().get(i);
						switch (settingTest.getMethodSetting()) {
						case("KVP"):
							indivSetting = createIndiv(generateName("KVP"), model.getResource(racineDCM+"113733"));break;
						default:
							indivSetting = createIndiv(generateName(settingTest.getMethodSettingValue()), model.getResource(racineURI+"device_setting"));break;
						}
						addObjectProperty(indivSetting, racineObo+"IAO_0000039",  getUnit(settingTest.getMethodSettingUnit()));
						addDataProperty(indivSetting,racineObo+"IAO_0000004",settingTest.getMethodSettingValue());
						addObjectProperty(segMeth,racineURI+"is_about",indivSetting);
					}
				}
			}

			if (ctSegmentation.getVOIDescriptorProduced()!=null) {
				Iterator<VOI> voiIterator = ctSegmentation.getVOIDescriptorProduced().getVOI().iterator();
				while(voiIterator.hasNext()) {
					VOI voiInformations= voiIterator.next();
					String voiId = voiInformations.getVOIIdentifier().toString();
					Individual organ = getOrganOrTissue(voiInformations.getOrganOrTissue());
					tableVOIorgans.put(voiId, organ);

					Iterator<NonDICOMData> voiContainerIter = voiInformations.getNonDICOMDataContainer().getNonDICOMData().iterator();
					while (voiContainerIter.hasNext()) {
						Individual voiFile = createIndiv(generateName("non_DICOM_file"),model.getResource(racineURI+"non_DICOM_file"));

						addObjectProperty(voiFile, racineURI+"has_patient",patient);
						addObjectProperty(imageSegmentation,racineURI+"has_specified_output", voiFile);
						addObjectProperty(voiFile, racineURI+"is_specified_output_of", imageSegmentation);
						addObjectProperty(organ,racineObo+"BFO_0000175", human);	
						addObjectProperty(voiFile,racineURI+"represents", organ);

						NonDICOMData voiContainer = voiContainerIter.next();
						translateNonDicomDataObject(voiContainer, voiFile);
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
			addObjectProperty(calculationof3Ddosemap, racineURI+"part_of_study", clinicalresearchStudy);

			addObjectProperty(listeDosiMc.get(j), racineObo+"BFO_0000117", calculationof3Ddosemap);
			addDataProperty(calculationof3Ddosemap, racineURI+"has_beginning", calculationOfVoxelMap.getProcessExecutionContext().getDateTimeProcessStarted());

			String institutionName = calculationOfVoxelMap.getProcessExecutionContext().getPerformingInstitution();
			institution = memory.getInstitution(institutionName);
			role_of_responsible_organization = memory.getRoleOfResponsibleOrganization(institutionName);

			addObjectProperty(role_of_responsible_organization, racineObo+"BFO_0000054", calculationof3Ddosemap);

			if (institution!=null) {
				addObjectProperty(institution, racineObo+"BFO_0000161", role_of_responsible_organization); //has role at all time
			}
			if (ctDosiMc!=null) {
				addObjectProperty(role_of_responsible_organization,racineObo+"BFO_0000054", ctDosiMc);
			}

			if (calculationOfVoxelMap.getAttenuatorUsed()!=null) {
				attenuatorUsedIterator = calculationOfVoxelMap.getAttenuatorUsed().getAttenuatorType().iterator();
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
					mcSettingsIter = calculationOfVoxelMap.getMonteCarloMethodUsed().getMonteCarloMethodSetting().getMethodSettingType().iterator();
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
							settingMC = createIndiv(generateName("device_setting"), model.getResource(racineURI+"is_device_setting_of"));
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

				imagingDeviceIter = calculationOfVoxelMap.getMonteCarloMethodUsed().getSimulatedImagingDevices().getSimulatedImagingDevice().iterator();;
				while (imagingDeviceIter.hasNext()) {
					imagingDevice = imagingDeviceIter.next();
					i = createIndiv(generateName("medical_imaging_device"), model.getResource(racineURI+"medical_imaging_device")); 
					addDataProperty(i, racineURI+"has_name", imagingDevice);
					modelOfImagingDevice = createIndiv(generateName("model_of_medical_imaging_device"), model.getResource(racineURI+"model_of_medical_imaging_device"));
					addObjectProperty(modelOfImagingDevice, racineURI+"refers_to_device", i);
					addObjectProperty(modelOfImagingDevice, racineURI+"used_as_instrument_in", ctDosiMc); 

				}

				MCsoftwareIter = calculationOfVoxelMap.getMonteCarloMethodUsed().getSoftwareNames().getSoftwareName().iterator();
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
					addObjectProperty(voxelBasedDistributionOfAbsorbedDoseType, racineObo+"IAO_0000039", getUnit(voxelBasedDistribution.getAbsorbedDoseUnit().toString()));
				}	

				Individual absorbedDoseCat;
				switch (voxelBasedDistribution.getVoxelBasedDistributionOfAbsorbedDoseCategory() ) {
				case ABSORBED_DOSE:
					absorbedDoseCat = createIndiv(model.getResource(racineDCM+"128513"));
					break;
				case ABSORBED_DOSE_NORMALIZED_TO_CTDI_FREE_IN_AIR_NORMALIZED_TO_TUBE_LOAD:
					absorbedDoseCat = createIndiv(model.getResource(racineURI+"absorbed_dose_normalized_to_CTDI_free_in_air_normalized_to_tube_load"));
					break;
				case ABSORBED_DOSE_NORMALIZED_TO_CTDI_VOL_NORMALIZED_TO_TUBE_LOAD:
					absorbedDoseCat = createIndiv(model.getResource(racineURI+"absorbed_dose_normalized_to_CTDI_vol_normalized_to_tube_load"));
					break;
				default:
					absorbedDoseCat = createIndiv(model.getResource(racineDCM+"128513"));
					break;
				}
				addObjectProperty(voxelBasedDistributionOfAbsorbedDoseType, racineObo+"BFO_0000172", absorbedDoseCat);
				
				if (voxelBasedDistribution.getNonDICOMVoxelBasedAbsorbedDoseDistribution()!=null) {
					NonDICOMData NonDICOM = voxelBasedDistribution.getNonDICOMVoxelBasedAbsorbedDoseDistribution();
					
					Individual voi = createIndiv(generateName("VOI"), model.getResource(racineURI+"VOI"));
					addObjectProperty(voi, racineURI+"has_patient", human);
					translateNonDicomDataObject(NonDICOM, voi);
				}
			} // fin calculationOf3D`

			if (ctMonteCarloDosimetry.getCalculationOfAbsorbedDosesInVOIs()!=null) {
				CalculationOfAbsorbedDosesInVOIs calculationOfAbsorbedDoses = ctMonteCarloDosimetry.getCalculationOfAbsorbedDosesInVOIs();

				calculationOfMeanAbsorbedDosesinVOIs = createIndiv(generateName("calculation_of_mean_absorbed_doses_in_VOIs"), model.getResource(racineURI+"calculation_of_mean_absorbed_doses_in_VOIs"));
				addObjectProperty(calculationOfMeanAbsorbedDosesinVOIs, racineURI+"part_of_study", clinicalresearchStudy);
				addObjectProperty(calculationOfMeanAbsorbedDosesinVOIs, racineObo+"BFO_0000132", internalRadiotherapy);

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
					absorbedDosePerVOIlist = ctMonteCarloDosimetry.getCalculationOfAbsorbedDosesInVOIs().getAbsorbedDosePerVOIProduced().getAbsorbedDosePerVOIType().iterator();
					while (absorbedDosePerVOIlist.hasNext()) {
						absorbedDosePerVOI = absorbedDosePerVOIlist.next();
						switch (absorbedDosePerVOI.getAbsorbedDoseCategory()) {
						case MAXIMUM_ABSORBED_RADIATION_DOSE:
							break;
						case MEAN_ABSORBED_DOSE_NORMALIZED_TO_CTDI_FREE_IN_AIR_NORMALIZED_TO_TUBE_LOAD:
							absorbedDoseVoi = createIndiv(generateName("mean_absorbed_dose_normalized_to_CTDI_free_in_air_normalized_to_tube_load"),
									model.getResource(racineURI+"mean_absorbed_dose_normalized_to_CTDI_free_in_air_normalized_to_tube_load"));
							break;
						case MEAN_ABSORBED_DOSE_NORMALIZED_TO_CTDI_VOL_NORMALIZED_TO_TUBE_LOAD:
							absorbedDoseVoi = createIndiv(generateName("mean_absorbed_dose_normalized_to_CTDI_vol_normalized_to_tube_load"),
									model.getResource(racineURI+"mean_absorbed_dose_normalized_to_CTDI_vol_normalized_to_tube_load"));
							break;
						case MEAN_ABSORBED_RADIATION_DOSE:
							absorbedDoseVoi = createIndiv(generateName("Mean_Absorbed_Radiation_Dose"), model.getResource(racineDCM+"128533"));
							break;
						case MEDIAN_ABSORBED_RADIATION_DOSE:
							absorbedDoseVoi = createIndiv(generateName("Maximum_Absorbed_Radiation_Dose"), model.getResource(racineDCM+"128531"));
							break;
						case MINIMUM_ABSORBED_RADIATION_DOSE:
							absorbedDoseVoi = createIndiv(generateName("Minimum_Absorbed_Radiation_Dose"), model.getResource(racineDCM+"128532"));
							break;
						case MODE_ABSORBED_RADIATION_DOSE:
							absorbedDoseVoi = createIndiv(generateName("Mode_Absorbed_Radiation_Dose"), model.getResource(racineDCM+"128534"));
							break;
						default:
							logger.warn("Unknown absorbedDosePerVOICategory : "+absorbedDosePerVOI.getAbsorbedDoseCategory());
							absorbedDoseVoi = createIndiv(generateName("Absorbed_Dose"), model.getResource(racineDCM+"128513"));
							break;
						}
						addObjectProperty(absorbedDoseVoi, racineURI+"has_patient", patient);

						if (absorbedDosePerVOI.getStatisticalSubCategory()!=null) {
							switch (absorbedDosePerVOI.getStatisticalSubCategory()) {
							case MAXIMUM:
								absorbedDoseVoi.addOntClass(model.getResource(racineDCM+"128531"));
								break;
							case MEAN:
								absorbedDoseVoi.addOntClass(model.getResource(racineDCM+"128533"));
								break;
							case MEDIAN:
								absorbedDoseVoi.addOntClass(model.getResource(racineDCM+"128539"));
								break;
							case MINIMUM:
								absorbedDoseVoi.addOntClass(model.getResource(racineDCM+"128532"));
								break;
							case MODE:
								absorbedDoseVoi.addOntClass(model.getResource(racineDCM+"128534"));
								break;
							default:
								break;
							}
						}

						if (patient!=null) {
							addObjectProperty(absorbedDoseVoi, racineURI+"has_patient", patient);
						}

						addObjectProperty(calculationOfMeanAbsorbedDosesinVOIs,racineURI+"has_specified_output",absorbedDoseVoi);
						addDataProperty(absorbedDoseVoi, racineObo+"IAO_0000004", absorbedDosePerVOI.getAbsorbedDoseValue()); 
						addObjectProperty(absorbedDoseVoi, racineObo+"IAO_0000039", getUnit(absorbedDosePerVOI.getAbsorbedDoseUnit().toString()));

						addObjectProperty(absorbedDoseVoi,racineURI+"is_dose_absorbed_by",tableVOIorgans.get(absorbedDosePerVOI.getVOIIdentifier().toString()));

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

	public static Individual getOrganOrTissue(OrganOrTissue organ) {
		Individual organIndiv = null;
		switch (organ) {
		case ADRENALS:
			organIndiv = createIndiv("Adrenals_"+patientID,  model.getResource(racineObo+"FMA_264816"));
			break;
		case AIR:
			organIndiv = createIndiv("Air_"+patientID, model.getResource(racineObo+"FMA_84580"));
			break;
		case BLADDER:
			organIndiv = createIndiv("Bladder_"+patientID, model.getResource(racineObo+"FMA_15900"));
			break;
		case BODY_SURFACE:
			organIndiv = createIndiv("Body_Surface_"+patientID, model.getResource(racineObo+"FMA_61695"));
			break;
		case BONE:
			organIndiv = createIndiv("Bone_"+patientID, model.getResource(racineObo+"FMA_30317"));
			break;
		case BONE_SURFACES:
			organIndiv = createIndiv("Bone_Surfaces_"+patientID, model.getResource(racineObo+"FMA_33535"));
			break;
		case BRAIN:
			organIndiv = createIndiv("Brain_"+patientID, model.getResource(racineObo+"FMA_50801"));
			break;
		case BREASTS:
			organIndiv = createIndiv("Breasts_"+patientID, model.getResource(racineURI+"breasts"));
			break;
		case COLON:
			organIndiv = createIndiv("Colon_"+patientID, model.getResource(racineObo+"FMA_7201"));
			break;
		case ESOPHAGUS:
			organIndiv = createIndiv("Esophagus_"+patientID, model.getResource(racineObo+"FMA_7131"));
			break;
		case EXTRATHORACIC_REGION:
			break;
		case GALLBLADDER:
			organIndiv = createIndiv("Gallbladder_"+patientID, model.getResource(racineObo+"FMA_7202"));
			break;
		case HEART:
			organIndiv = createIndiv("Heart_"+patientID, model.getResource(racineObo+"FMA_7088"));
			break;
		case KIDNEYS:
			organIndiv = createIndiv("Kidneys_"+patientID, model.getResource(racineObo+"FMA_264815"));
			break;
		case LEFT_FEMALE_BREAST:
			organIndiv = createIndiv("Left_Female_Breasts_"+patientID, model.getResource(racineObo+"FMA_19910"));
			break;
		case LEFT_KIDNEY:
			organIndiv = createIndiv("Left_Kidney_"+patientID, model.getResource(racineObo+"FMA_7205"));
			break;
		case LEFT_LUNG:
			organIndiv = createIndiv("Left_Lung_"+patientID, model.getResource(racineObo+"FMA_7310"));
			break;
		case LEFT_MALE_BREAST:
			organIndiv = createIndiv("Left_Male_Breast_"+patientID, model.getResource(racineObo+"FMA_19913"));
			break;
		case LEFT_OVARY:
			organIndiv = createIndiv("Left_Ovary_"+patientID, model.getResource(racineObo+"FMA_7214"));
			break;
		case LEFT_PAROTID_GLAND:
			organIndiv = createIndiv("Left_parotid_gland_"+patientID, model.getResource(racineObo+"FMA_59798"));
			break;
		case LEFT_SUBMANDIBULAR_GLAND:
			organIndiv = createIndiv("Left_Submandibular_gland_"+patientID, model.getResource(racineObo+"FMA_59803"));
			break;
		case LEFT_TESTIS:
			organIndiv = createIndiv("Left_testis_"+patientID, model.getResource(racineObo+"FMA_7212"));
			break;
		case LIVER:
			organIndiv = createIndiv("Liver_"+patientID, model.getResource(racineObo+"FMA_7197"));
			break;
		case LUNGS:
			organIndiv = createIndiv("Lungs_"+patientID, model.getResource(racineObo+"FMA_68877"));
			break;
		case LYMPHATIC_NODES:
			organIndiv = createIndiv("Lymphatic_Nodes_"+patientID, model.getResource(racineObo+"FMA_70776"));
			break;
		case MUSCLE:
			organIndiv = createIndiv("Muscle_"+patientID, model.getResource(racineObo+"FMA_30316"));
			break;
		case ORAL_MUCOSA:
			organIndiv = createIndiv("Oral_Mucosa_"+patientID, model.getResource(racineObo+"FMA_54933"));
			break;
		case OVARIES:
			organIndiv = createIndiv("Ovaries_"+patientID, model.getResource(racineURI+"ovaries"));
			break;
		case PANCREAS:
			organIndiv = createIndiv("Pancreas_"+patientID, model.getResource(racineObo+"FMA_7198"));
			break;
		case PAROTID_GLANDS:
			organIndiv = createIndiv("Parotid_Glands_"+patientID, model.getResource(racineObo+"FMA_320436"));
			break;
		case PROSTATE:
			organIndiv = createIndiv("Prostate_"+patientID, model.getResource(racineObo+"FMA_7600"));
			break;
		case RED_BONE_MARROW:
			organIndiv = createIndiv("Red_Bone_Marrow_"+patientID, model.getResource(racineObo+"FMA_74595"));
			break;
		case RIGHT_FEMALE_BREAST:
			organIndiv = createIndiv("Right_Female_Breats_"+patientID, model.getResource(racineObo+"FMA_19908"));
			break;
		case RIGHT_KIDNEY:
			organIndiv = createIndiv("Right_Kidney_"+patientID, model.getResource(racineObo+"FMA_7204"));
			break;
		case RIGHT_LUNG:
			organIndiv = createIndiv("Right_Lung_"+patientID, model.getResource(racineObo+"FMA_7309"));
			break;
		case RIGHT_MALE_BREAST:
			organIndiv = createIndiv("Right_Male_Breats_"+patientID, model.getResource(racineObo+"FMA_19912"));
			break;
		case RIGHT_OVARY:
			organIndiv = createIndiv("Right_Ovary_"+patientID, model.getResource(racineObo+"FMA_7213"));
			break;
		case RIGHT_PAROTID_GLAND:
			organIndiv = createIndiv("Right_Parotid_Gland_"+patientID, model.getResource(racineObo+"FMA_59797"));
			break;
		case RIGHT_SUBMANDIBULAR_GLAND:
			organIndiv = createIndiv("Right_Submandibular_Gland_"+patientID, model.getResource(racineObo+"FMA_59802"));
			break;
		case RIGHT_TESTIS:
			organIndiv = createIndiv("Right_Testis_"+patientID, model.getResource(racineObo+"FMA_7211"));
			break;
		case SALIVARY_GLANDS:
			organIndiv = createIndiv("Salivary_Glands_"+patientID, model.getResource(racineObo+"FMA_322428"));
			break;
		case SKIN:
			organIndiv = createIndiv("Skin_"+patientID, model.getResource(racineObo+"FMA_7163"));
			break;
		case SMALL_INTESTINE:
			organIndiv = createIndiv("Small_Intestine_"+patientID, model.getResource(racineObo+"FMA_7200"));
			break;
		case SPLEEN:
			organIndiv = createIndiv("Spleen_"+patientID, model.getResource(racineObo+"FMA_7196"));
			break;
		case STOMACH:
			organIndiv = createIndiv("Stomach_"+patientID, model.getResource(racineObo+"FMA_7148"));
			break;
		case SUBMANDIBULAR_GLANDS:
			organIndiv = createIndiv("Submandibular_Glands_"+patientID, model.getResource(racineObo+"FMA_320442"));
			break;
		case TESTES:
			organIndiv = createIndiv("Testes_"+patientID, model.getResource(racineURI+"testes"));
			break;
		case THYMUS:
			organIndiv = createIndiv("Thymus_"+patientID, model.getResource(racineObo+"FMA_9607"));
			break;
		case THYROID:
			organIndiv = createIndiv("Thyroid_"+patientID, model.getResource(racineObo+"FMA_9603"));
			break;
		case UTERUS:
			organIndiv = createIndiv("Uterus_"+patientID, model.getResource(racineObo+"FMA_17558"));
			break;
		case L_2:
			organIndiv = createIndiv("L3_L5_bone_marrow_"+patientID, model.getResource(racineURI+"L3_L5_bone_marrow"));
			break;
		case SOFT_TISSUE:
			organIndiv = createIndiv("Soft_Tissue_"+patientID, model.getResource(racineURI+"soft_tissue"));
			break;
		case TUMOR:
			break;
		default:
			break; 	
		}
		return organIndiv;
	}

	public enum typeInputOutput {SPECTCTacq,CTRecon,NMTomo;}


	public static String generateKeyDicomInputOutput(String studyId, String seriesId, typeInputOutput type) {
		return studyId + "_" + seriesId + "_" + type.toString() ;
	}

	public static String generateKeyNonDicomInputOutput(String fileName, String nonDicomDataClass, NonDICOMDataFormat NonDICOMDataFormat) {
		return fileName + "_" + nonDicomDataClass + "_" + NonDICOMDataFormat.toString() ;
	}

	public static Individual getNonDicomInputOutput(String fileName, String nonDicomDataClass, NonDICOMDataFormat NonDICOMDataFormat) {
		return tableInputOutput.get(generateKeyNonDicomInputOutput(fileName, nonDicomDataClass, NonDICOMDataFormat));
	}

	public static Individual getDicomInputOutput(String studyId, String seriesId, typeInputOutput type) {
		return tableInputOutput.get(generateKeyDicomInputOutput(studyId, seriesId, type));
	}

	public static void setNonDicomInputOutput(String fileName, String nonDicomDataClass, Individual NonDicomInputOutput, NonDICOMDataFormat NonDICOMDataFormat) {
		tableInputOutput.put(generateKeyNonDicomInputOutput(fileName, nonDicomDataClass, NonDICOMDataFormat), NonDicomInputOutput);
	}

	public static void setDicomInputOutput(String studyId, String seriesId, typeInputOutput type, Individual dicomInputOutput) {
		tableInputOutput.put(generateKeyDicomInputOutput(studyId, seriesId, type), dicomInputOutput);
	}

	public static Boolean hasNonDicomInputOutput(String fileName, String nonDicomDataClass, NonDICOMDataFormat NonDICOMDataFormat) {
		return tableInputOutput.containsKey(generateKeyNonDicomInputOutput(fileName, nonDicomDataClass, NonDICOMDataFormat));
	}

	public static Boolean hasDicomInputOutput(String studyId, String seriesId, typeInputOutput type) {
		return tableInputOutput.containsKey(generateKeyDicomInputOutput(studyId, seriesId, type));
	}

}
