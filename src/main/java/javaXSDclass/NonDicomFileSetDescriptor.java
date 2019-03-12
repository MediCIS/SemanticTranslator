//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2018.12.11 à 04:47:30 PM CET 
//


package javaXSDclass;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ReferencedClinicalResearchStudy">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ClinicalResearchStudyID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ClinicalResearchStudyTitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="PatientId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CalibrationWorkflow" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SPECTCTCalibrationWorkflow" type="{https://www.irdbb-medirad.com}SPECTCTCalibrationWorkflow"/>
 *                   &lt;element name="PlanarCalibrationWorkflow" type="{https://www.irdbb-medirad.com}PlanarCalibrationWorkflow"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ElementOfSPECTRecoveryCoefficientCurve" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="RatioMeasuredActivityToTrueActivity" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                   &lt;element name="HotInsertVolumeValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                   &lt;element name="HotInsertVolumeUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="HotInsertIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="WP2subtask212WorkflowData" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CTSegmentation" type="{https://www.irdbb-medirad.com}CTSegmentation" minOccurs="0"/>
 *                   &lt;element name="SimpleCTMonteCarloDosimetry" type="{https://www.irdbb-medirad.com}SimpleCTMonteCarloDosimetry" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ThreeDimDosimetrySlide1workflow" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SPECTDataAcquisitionAndProcessing" type="{https://www.irdbb-medirad.com}SPECTDataAcquisitionAndProcessing" maxOccurs="unbounded"/>
 *                   &lt;element name="RegistrationVOISegmentationAndPropagation" type="{https://www.irdbb-medirad.com}RegistrationVOISegmentationAndPropagation"/>
 *                   &lt;element name="VOIActivityDetermination" type="{https://www.irdbb-medirad.com}VOIActivityDetermination" maxOccurs="unbounded"/>
 *                   &lt;element name="TimeActivityCurveFit" type="{https://www.irdbb-medirad.com}TimeActivityCurveFit" maxOccurs="unbounded"/>
 *                   &lt;element name="AbsorbedDoseCalculation" type="{https://www.irdbb-medirad.com}AbsorbedDoseCalculation"/>
 *                   &lt;element name="RadioBiologicalCalculation" type="{https://www.irdbb-medirad.com}RadioBiologicalCalculation" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="TimeIntegratedActivityCoefficientPerVOI" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TimeIntegratedActivityCoefficientPerVOIValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                   &lt;element name="TimeIntegratedActivityCoefficientPerVOIUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="VOIIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="TimeIntegratedActivityPerVOI" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TimeIntegratedActivityPerVOIValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                   &lt;element name="TimeIntegratedActivityPerVOIUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="VOIIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="TwoDimDosimetryworkflow" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PlanarDataAcquisitionAndProcessing" type="{https://www.irdbb-medirad.com}PlanarDataAcquisitionAndProcessing" maxOccurs="unbounded"/>
 *                   &lt;element name="TwoDimDosimetryViaAbsorbedDoseRateCalculation" type="{https://www.irdbb-medirad.com}TwoDimDosimetryViaAbsorbedDoseRateCalculation" minOccurs="0"/>
 *                   &lt;element name="TwoDimDosimetryViaTimeActivityCurveFit" type="{https://www.irdbb-medirad.com}TwoDimDosimetryViaTimeActivityCurveFit" minOccurs="0"/>
 *                   &lt;element name="RadioBiologicalCalculation" type="{https://www.irdbb-medirad.com}RadioBiologicalCalculation" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="HybridDosimetryworkflow" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PlanarDataAcquisitionAndProcessing" type="{https://www.irdbb-medirad.com}PlanarDataAcquisitionAndProcessing" maxOccurs="unbounded"/>
 *                   &lt;element name="SPECTDataAcquisitionAndProcessing" type="{https://www.irdbb-medirad.com}SPECTDataAcquisitionAndProcessing"/>
 *                   &lt;element name="HybridDosimetryViaAbsorbedDoseRateCalculation" type="{https://www.irdbb-medirad.com}HybridDosimetryViaAbsorbedDoseRateCalculation" minOccurs="0"/>
 *                   &lt;element name="HybridDosimetryViaTimeActivityCurveFit" type="{https://www.irdbb-medirad.com}HybridDosimetryViaTimeActivityCurveFit" minOccurs="0"/>
 *                   &lt;element name="RadioBiologicalCalculation" type="{https://www.irdbb-medirad.com}RadioBiologicalCalculation" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="RegistrationVOISegmentation" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *                   &lt;element name="TimePointIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="NMTomoReconUsed" type="{https://www.irdbb-medirad.com}DICOMData"/>
 *                   &lt;element name="CTReconUsed" type="{https://www.irdbb-medirad.com}DICOMData"/>
 *                   &lt;element name="RegistrationAndSegmentationMethodMethodUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CountsPerVOIAtTimePointProduced" type="{https://www.irdbb-medirad.com}CountsPerVOIAtTimePoint" maxOccurs="unbounded"/>
 *                   &lt;element name="VOIInSPECTProduced" type="{https://www.irdbb-medirad.com}VOI" maxOccurs="unbounded"/>
 *                   &lt;element name="CTReconResampledOnSPECTProduced" type="{https://www.irdbb-medirad.com}DICOMData"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ThreeDimDosimetrySlide2workflow" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SPECTDataAcquisitionAndProcessing" type="{https://www.irdbb-medirad.com}SPECTDataAcquisitionAndProcessing" maxOccurs="unbounded"/>
 *                   &lt;element name="VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation" type="{https://www.irdbb-medirad.com}VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation" maxOccurs="unbounded"/>
 *                   &lt;element name="DoseRateCurveFitVOITimeIntegration" type="{https://www.irdbb-medirad.com}DoseRateCurveFitVOITimeIntegration"/>
 *                   &lt;element name="RadioBiologicalCalculation" type="{https://www.irdbb-medirad.com}RadioBiologicalCalculation" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "referencedClinicalResearchStudy",
    "patientId",
    "calibrationWorkflow",
    "elementOfSPECTRecoveryCoefficientCurve",
    "wp2Subtask212WorkflowData",
    "threeDimDosimetrySlide1Workflow",
    "timeIntegratedActivityCoefficientPerVOI",
    "timeIntegratedActivityPerVOI",
    "twoDimDosimetryworkflow",
    "hybridDosimetryworkflow",
    "registrationVOISegmentation",
    "threeDimDosimetrySlide2Workflow"
})
@XmlRootElement(name = "NonDicomFileSetDescriptor")
public class NonDicomFileSetDescriptor {

    @XmlElement(name = "ReferencedClinicalResearchStudy", required = true)
    protected NonDicomFileSetDescriptor.ReferencedClinicalResearchStudy referencedClinicalResearchStudy;
    @XmlElement(name = "PatientId", required = true)
    protected String patientId;
    @XmlElement(name = "CalibrationWorkflow")
    protected List<NonDicomFileSetDescriptor.CalibrationWorkflow> calibrationWorkflow;
    @XmlElement(name = "ElementOfSPECTRecoveryCoefficientCurve")
    protected List<NonDicomFileSetDescriptor.ElementOfSPECTRecoveryCoefficientCurve> elementOfSPECTRecoveryCoefficientCurve;
    @XmlElement(name = "WP2subtask212WorkflowData")
    protected List<NonDicomFileSetDescriptor.WP2Subtask212WorkflowData> wp2Subtask212WorkflowData;
    @XmlElement(name = "ThreeDimDosimetrySlide1workflow")
    protected List<NonDicomFileSetDescriptor.ThreeDimDosimetrySlide1Workflow> threeDimDosimetrySlide1Workflow;
    @XmlElement(name = "TimeIntegratedActivityCoefficientPerVOI")
    protected List<NonDicomFileSetDescriptor.TimeIntegratedActivityCoefficientPerVOI> timeIntegratedActivityCoefficientPerVOI;
    @XmlElement(name = "TimeIntegratedActivityPerVOI")
    protected List<NonDicomFileSetDescriptor.TimeIntegratedActivityPerVOI> timeIntegratedActivityPerVOI;
    @XmlElement(name = "TwoDimDosimetryworkflow")
    protected List<NonDicomFileSetDescriptor.TwoDimDosimetryworkflow> twoDimDosimetryworkflow;
    @XmlElement(name = "HybridDosimetryworkflow")
    protected List<NonDicomFileSetDescriptor.HybridDosimetryworkflow> hybridDosimetryworkflow;
    @XmlElement(name = "RegistrationVOISegmentation")
    protected List<NonDicomFileSetDescriptor.RegistrationVOISegmentation> registrationVOISegmentation;
    @XmlElement(name = "ThreeDimDosimetrySlide2workflow")
    protected List<NonDicomFileSetDescriptor.ThreeDimDosimetrySlide2Workflow> threeDimDosimetrySlide2Workflow;

    /**
     * Obtient la valeur de la propriété referencedClinicalResearchStudy.
     * 
     * @return
     *     possible object is
     *     {@link NonDicomFileSetDescriptor.ReferencedClinicalResearchStudy }
     *     
     */
    public NonDicomFileSetDescriptor.ReferencedClinicalResearchStudy getReferencedClinicalResearchStudy() {
        return referencedClinicalResearchStudy;
    }

    /**
     * Définit la valeur de la propriété referencedClinicalResearchStudy.
     * 
     * @param value
     *     allowed object is
     *     {@link NonDicomFileSetDescriptor.ReferencedClinicalResearchStudy }
     *     
     */
    public void setReferencedClinicalResearchStudy(NonDicomFileSetDescriptor.ReferencedClinicalResearchStudy value) {
        this.referencedClinicalResearchStudy = value;
    }

    /**
     * Obtient la valeur de la propriété patientId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * Définit la valeur de la propriété patientId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientId(String value) {
        this.patientId = value;
    }

    /**
     * Gets the value of the calibrationWorkflow property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the calibrationWorkflow property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCalibrationWorkflow().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NonDicomFileSetDescriptor.CalibrationWorkflow }
     * 
     * 
     */
    public List<NonDicomFileSetDescriptor.CalibrationWorkflow> getCalibrationWorkflow() {
        if (calibrationWorkflow == null) {
            calibrationWorkflow = new ArrayList<NonDicomFileSetDescriptor.CalibrationWorkflow>();
        }
        return this.calibrationWorkflow;
    }

    /**
     * Gets the value of the elementOfSPECTRecoveryCoefficientCurve property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the elementOfSPECTRecoveryCoefficientCurve property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getElementOfSPECTRecoveryCoefficientCurve().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NonDicomFileSetDescriptor.ElementOfSPECTRecoveryCoefficientCurve }
     * 
     * 
     */
    public List<NonDicomFileSetDescriptor.ElementOfSPECTRecoveryCoefficientCurve> getElementOfSPECTRecoveryCoefficientCurve() {
        if (elementOfSPECTRecoveryCoefficientCurve == null) {
            elementOfSPECTRecoveryCoefficientCurve = new ArrayList<NonDicomFileSetDescriptor.ElementOfSPECTRecoveryCoefficientCurve>();
        }
        return this.elementOfSPECTRecoveryCoefficientCurve;
    }

    /**
     * Gets the value of the wp2Subtask212WorkflowData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wp2Subtask212WorkflowData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWP2Subtask212WorkflowData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NonDicomFileSetDescriptor.WP2Subtask212WorkflowData }
     * 
     * 
     */
    public List<NonDicomFileSetDescriptor.WP2Subtask212WorkflowData> getWP2Subtask212WorkflowData() {
        if (wp2Subtask212WorkflowData == null) {
            wp2Subtask212WorkflowData = new ArrayList<NonDicomFileSetDescriptor.WP2Subtask212WorkflowData>();
        }
        return this.wp2Subtask212WorkflowData;
    }

    /**
     * Gets the value of the threeDimDosimetrySlide1Workflow property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the threeDimDosimetrySlide1Workflow property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getThreeDimDosimetrySlide1Workflow().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NonDicomFileSetDescriptor.ThreeDimDosimetrySlide1Workflow }
     * 
     * 
     */
    public List<NonDicomFileSetDescriptor.ThreeDimDosimetrySlide1Workflow> getThreeDimDosimetrySlide1Workflow() {
        if (threeDimDosimetrySlide1Workflow == null) {
            threeDimDosimetrySlide1Workflow = new ArrayList<NonDicomFileSetDescriptor.ThreeDimDosimetrySlide1Workflow>();
        }
        return this.threeDimDosimetrySlide1Workflow;
    }

    /**
     * Gets the value of the timeIntegratedActivityCoefficientPerVOI property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the timeIntegratedActivityCoefficientPerVOI property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTimeIntegratedActivityCoefficientPerVOI().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NonDicomFileSetDescriptor.TimeIntegratedActivityCoefficientPerVOI }
     * 
     * 
     */
    public List<NonDicomFileSetDescriptor.TimeIntegratedActivityCoefficientPerVOI> getTimeIntegratedActivityCoefficientPerVOI() {
        if (timeIntegratedActivityCoefficientPerVOI == null) {
            timeIntegratedActivityCoefficientPerVOI = new ArrayList<NonDicomFileSetDescriptor.TimeIntegratedActivityCoefficientPerVOI>();
        }
        return this.timeIntegratedActivityCoefficientPerVOI;
    }

    /**
     * Gets the value of the timeIntegratedActivityPerVOI property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the timeIntegratedActivityPerVOI property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTimeIntegratedActivityPerVOI().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NonDicomFileSetDescriptor.TimeIntegratedActivityPerVOI }
     * 
     * 
     */
    public List<NonDicomFileSetDescriptor.TimeIntegratedActivityPerVOI> getTimeIntegratedActivityPerVOI() {
        if (timeIntegratedActivityPerVOI == null) {
            timeIntegratedActivityPerVOI = new ArrayList<NonDicomFileSetDescriptor.TimeIntegratedActivityPerVOI>();
        }
        return this.timeIntegratedActivityPerVOI;
    }

    /**
     * Gets the value of the twoDimDosimetryworkflow property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the twoDimDosimetryworkflow property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTwoDimDosimetryworkflow().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NonDicomFileSetDescriptor.TwoDimDosimetryworkflow }
     * 
     * 
     */
    public List<NonDicomFileSetDescriptor.TwoDimDosimetryworkflow> getTwoDimDosimetryworkflow() {
        if (twoDimDosimetryworkflow == null) {
            twoDimDosimetryworkflow = new ArrayList<NonDicomFileSetDescriptor.TwoDimDosimetryworkflow>();
        }
        return this.twoDimDosimetryworkflow;
    }

    /**
     * Gets the value of the hybridDosimetryworkflow property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hybridDosimetryworkflow property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHybridDosimetryworkflow().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NonDicomFileSetDescriptor.HybridDosimetryworkflow }
     * 
     * 
     */
    public List<NonDicomFileSetDescriptor.HybridDosimetryworkflow> getHybridDosimetryworkflow() {
        if (hybridDosimetryworkflow == null) {
            hybridDosimetryworkflow = new ArrayList<NonDicomFileSetDescriptor.HybridDosimetryworkflow>();
        }
        return this.hybridDosimetryworkflow;
    }

    /**
     * Gets the value of the registrationVOISegmentation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the registrationVOISegmentation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRegistrationVOISegmentation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NonDicomFileSetDescriptor.RegistrationVOISegmentation }
     * 
     * 
     */
    public List<NonDicomFileSetDescriptor.RegistrationVOISegmentation> getRegistrationVOISegmentation() {
        if (registrationVOISegmentation == null) {
            registrationVOISegmentation = new ArrayList<NonDicomFileSetDescriptor.RegistrationVOISegmentation>();
        }
        return this.registrationVOISegmentation;
    }

    /**
     * Gets the value of the threeDimDosimetrySlide2Workflow property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the threeDimDosimetrySlide2Workflow property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getThreeDimDosimetrySlide2Workflow().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NonDicomFileSetDescriptor.ThreeDimDosimetrySlide2Workflow }
     * 
     * 
     */
    public List<NonDicomFileSetDescriptor.ThreeDimDosimetrySlide2Workflow> getThreeDimDosimetrySlide2Workflow() {
        if (threeDimDosimetrySlide2Workflow == null) {
            threeDimDosimetrySlide2Workflow = new ArrayList<NonDicomFileSetDescriptor.ThreeDimDosimetrySlide2Workflow>();
        }
        return this.threeDimDosimetrySlide2Workflow;
    }


    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="SPECTCTCalibrationWorkflow" type="{https://www.irdbb-medirad.com}SPECTCTCalibrationWorkflow"/>
     *         &lt;element name="PlanarCalibrationWorkflow" type="{https://www.irdbb-medirad.com}PlanarCalibrationWorkflow"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "spectctCalibrationWorkflow",
        "planarCalibrationWorkflow"
    })
    public static class CalibrationWorkflow {

        @XmlElement(name = "SPECTCTCalibrationWorkflow", required = true)
        protected SPECTCTCalibrationWorkflow spectctCalibrationWorkflow;
        @XmlElement(name = "PlanarCalibrationWorkflow", required = true)
        protected PlanarCalibrationWorkflow planarCalibrationWorkflow;

        /**
         * Obtient la valeur de la propriété spectctCalibrationWorkflow.
         * 
         * @return
         *     possible object is
         *     {@link SPECTCTCalibrationWorkflow }
         *     
         */
        public SPECTCTCalibrationWorkflow getSPECTCTCalibrationWorkflow() {
            return spectctCalibrationWorkflow;
        }

        /**
         * Définit la valeur de la propriété spectctCalibrationWorkflow.
         * 
         * @param value
         *     allowed object is
         *     {@link SPECTCTCalibrationWorkflow }
         *     
         */
        public void setSPECTCTCalibrationWorkflow(SPECTCTCalibrationWorkflow value) {
            this.spectctCalibrationWorkflow = value;
        }

        /**
         * Obtient la valeur de la propriété planarCalibrationWorkflow.
         * 
         * @return
         *     possible object is
         *     {@link PlanarCalibrationWorkflow }
         *     
         */
        public PlanarCalibrationWorkflow getPlanarCalibrationWorkflow() {
            return planarCalibrationWorkflow;
        }

        /**
         * Définit la valeur de la propriété planarCalibrationWorkflow.
         * 
         * @param value
         *     allowed object is
         *     {@link PlanarCalibrationWorkflow }
         *     
         */
        public void setPlanarCalibrationWorkflow(PlanarCalibrationWorkflow value) {
            this.planarCalibrationWorkflow = value;
        }

    }


    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="RatioMeasuredActivityToTrueActivity" type="{http://www.w3.org/2001/XMLSchema}float"/>
     *         &lt;element name="HotInsertVolumeValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
     *         &lt;element name="HotInsertVolumeUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="HotInsertIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "ratioMeasuredActivityToTrueActivity",
        "hotInsertVolumeValue",
        "hotInsertVolumeUnit",
        "hotInsertIdentifier"
    })
    public static class ElementOfSPECTRecoveryCoefficientCurve {

        @XmlElement(name = "RatioMeasuredActivityToTrueActivity")
        protected float ratioMeasuredActivityToTrueActivity;
        @XmlElement(name = "HotInsertVolumeValue")
        protected float hotInsertVolumeValue;
        @XmlElement(name = "HotInsertVolumeUnit", required = true)
        protected String hotInsertVolumeUnit;
        @XmlElement(name = "HotInsertIdentifier", required = true)
        protected String hotInsertIdentifier;

        /**
         * Obtient la valeur de la propriété ratioMeasuredActivityToTrueActivity.
         * 
         */
        public float getRatioMeasuredActivityToTrueActivity() {
            return ratioMeasuredActivityToTrueActivity;
        }

        /**
         * Définit la valeur de la propriété ratioMeasuredActivityToTrueActivity.
         * 
         */
        public void setRatioMeasuredActivityToTrueActivity(float value) {
            this.ratioMeasuredActivityToTrueActivity = value;
        }

        /**
         * Obtient la valeur de la propriété hotInsertVolumeValue.
         * 
         */
        public float getHotInsertVolumeValue() {
            return hotInsertVolumeValue;
        }

        /**
         * Définit la valeur de la propriété hotInsertVolumeValue.
         * 
         */
        public void setHotInsertVolumeValue(float value) {
            this.hotInsertVolumeValue = value;
        }

        /**
         * Obtient la valeur de la propriété hotInsertVolumeUnit.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHotInsertVolumeUnit() {
            return hotInsertVolumeUnit;
        }

        /**
         * Définit la valeur de la propriété hotInsertVolumeUnit.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHotInsertVolumeUnit(String value) {
            this.hotInsertVolumeUnit = value;
        }

        /**
         * Obtient la valeur de la propriété hotInsertIdentifier.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHotInsertIdentifier() {
            return hotInsertIdentifier;
        }

        /**
         * Définit la valeur de la propriété hotInsertIdentifier.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHotInsertIdentifier(String value) {
            this.hotInsertIdentifier = value;
        }

    }


    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="PlanarDataAcquisitionAndProcessing" type="{https://www.irdbb-medirad.com}PlanarDataAcquisitionAndProcessing" maxOccurs="unbounded"/>
     *         &lt;element name="SPECTDataAcquisitionAndProcessing" type="{https://www.irdbb-medirad.com}SPECTDataAcquisitionAndProcessing"/>
     *         &lt;element name="HybridDosimetryViaAbsorbedDoseRateCalculation" type="{https://www.irdbb-medirad.com}HybridDosimetryViaAbsorbedDoseRateCalculation" minOccurs="0"/>
     *         &lt;element name="HybridDosimetryViaTimeActivityCurveFit" type="{https://www.irdbb-medirad.com}HybridDosimetryViaTimeActivityCurveFit" minOccurs="0"/>
     *         &lt;element name="RadioBiologicalCalculation" type="{https://www.irdbb-medirad.com}RadioBiologicalCalculation" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "planarDataAcquisitionAndProcessing",
        "spectDataAcquisitionAndProcessing",
        "hybridDosimetryViaAbsorbedDoseRateCalculation",
        "hybridDosimetryViaTimeActivityCurveFit",
        "radioBiologicalCalculation"
    })
    public static class HybridDosimetryworkflow {

        @XmlElement(name = "PlanarDataAcquisitionAndProcessing", required = true)
        protected List<PlanarDataAcquisitionAndProcessing> planarDataAcquisitionAndProcessing;
        @XmlElement(name = "SPECTDataAcquisitionAndProcessing", required = true)
        protected SPECTDataAcquisitionAndProcessing spectDataAcquisitionAndProcessing;
        @XmlElement(name = "HybridDosimetryViaAbsorbedDoseRateCalculation")
        protected HybridDosimetryViaAbsorbedDoseRateCalculation hybridDosimetryViaAbsorbedDoseRateCalculation;
        @XmlElement(name = "HybridDosimetryViaTimeActivityCurveFit")
        protected HybridDosimetryViaTimeActivityCurveFit hybridDosimetryViaTimeActivityCurveFit;
        @XmlElement(name = "RadioBiologicalCalculation")
        protected RadioBiologicalCalculation radioBiologicalCalculation;

        /**
         * Gets the value of the planarDataAcquisitionAndProcessing property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the planarDataAcquisitionAndProcessing property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPlanarDataAcquisitionAndProcessing().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PlanarDataAcquisitionAndProcessing }
         * 
         * 
         */
        public List<PlanarDataAcquisitionAndProcessing> getPlanarDataAcquisitionAndProcessing() {
            if (planarDataAcquisitionAndProcessing == null) {
                planarDataAcquisitionAndProcessing = new ArrayList<PlanarDataAcquisitionAndProcessing>();
            }
            return this.planarDataAcquisitionAndProcessing;
        }

        /**
         * Obtient la valeur de la propriété spectDataAcquisitionAndProcessing.
         * 
         * @return
         *     possible object is
         *     {@link SPECTDataAcquisitionAndProcessing }
         *     
         */
        public SPECTDataAcquisitionAndProcessing getSPECTDataAcquisitionAndProcessing() {
            return spectDataAcquisitionAndProcessing;
        }

        /**
         * Définit la valeur de la propriété spectDataAcquisitionAndProcessing.
         * 
         * @param value
         *     allowed object is
         *     {@link SPECTDataAcquisitionAndProcessing }
         *     
         */
        public void setSPECTDataAcquisitionAndProcessing(SPECTDataAcquisitionAndProcessing value) {
            this.spectDataAcquisitionAndProcessing = value;
        }

        /**
         * Obtient la valeur de la propriété hybridDosimetryViaAbsorbedDoseRateCalculation.
         * 
         * @return
         *     possible object is
         *     {@link HybridDosimetryViaAbsorbedDoseRateCalculation }
         *     
         */
        public HybridDosimetryViaAbsorbedDoseRateCalculation getHybridDosimetryViaAbsorbedDoseRateCalculation() {
            return hybridDosimetryViaAbsorbedDoseRateCalculation;
        }

        /**
         * Définit la valeur de la propriété hybridDosimetryViaAbsorbedDoseRateCalculation.
         * 
         * @param value
         *     allowed object is
         *     {@link HybridDosimetryViaAbsorbedDoseRateCalculation }
         *     
         */
        public void setHybridDosimetryViaAbsorbedDoseRateCalculation(HybridDosimetryViaAbsorbedDoseRateCalculation value) {
            this.hybridDosimetryViaAbsorbedDoseRateCalculation = value;
        }

        /**
         * Obtient la valeur de la propriété hybridDosimetryViaTimeActivityCurveFit.
         * 
         * @return
         *     possible object is
         *     {@link HybridDosimetryViaTimeActivityCurveFit }
         *     
         */
        public HybridDosimetryViaTimeActivityCurveFit getHybridDosimetryViaTimeActivityCurveFit() {
            return hybridDosimetryViaTimeActivityCurveFit;
        }

        /**
         * Définit la valeur de la propriété hybridDosimetryViaTimeActivityCurveFit.
         * 
         * @param value
         *     allowed object is
         *     {@link HybridDosimetryViaTimeActivityCurveFit }
         *     
         */
        public void setHybridDosimetryViaTimeActivityCurveFit(HybridDosimetryViaTimeActivityCurveFit value) {
            this.hybridDosimetryViaTimeActivityCurveFit = value;
        }

        /**
         * Obtient la valeur de la propriété radioBiologicalCalculation.
         * 
         * @return
         *     possible object is
         *     {@link RadioBiologicalCalculation }
         *     
         */
        public RadioBiologicalCalculation getRadioBiologicalCalculation() {
            return radioBiologicalCalculation;
        }

        /**
         * Définit la valeur de la propriété radioBiologicalCalculation.
         * 
         * @param value
         *     allowed object is
         *     {@link RadioBiologicalCalculation }
         *     
         */
        public void setRadioBiologicalCalculation(RadioBiologicalCalculation value) {
            this.radioBiologicalCalculation = value;
        }

    }


    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="ClinicalResearchStudyID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ClinicalResearchStudyTitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "clinicalResearchStudyID",
        "clinicalResearchStudyTitle"
    })
    public static class ReferencedClinicalResearchStudy {

        @XmlElement(name = "ClinicalResearchStudyID", required = true)
        protected String clinicalResearchStudyID;
        @XmlElement(name = "ClinicalResearchStudyTitle", required = true)
        protected String clinicalResearchStudyTitle;

        /**
         * Obtient la valeur de la propriété clinicalResearchStudyID.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getClinicalResearchStudyID() {
            return clinicalResearchStudyID;
        }

        /**
         * Définit la valeur de la propriété clinicalResearchStudyID.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setClinicalResearchStudyID(String value) {
            this.clinicalResearchStudyID = value;
        }

        /**
         * Obtient la valeur de la propriété clinicalResearchStudyTitle.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getClinicalResearchStudyTitle() {
            return clinicalResearchStudyTitle;
        }

        /**
         * Définit la valeur de la propriété clinicalResearchStudyTitle.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setClinicalResearchStudyTitle(String value) {
            this.clinicalResearchStudyTitle = value;
        }

    }


    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
     *         &lt;element name="TimePointIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="NMTomoReconUsed" type="{https://www.irdbb-medirad.com}DICOMData"/>
     *         &lt;element name="CTReconUsed" type="{https://www.irdbb-medirad.com}DICOMData"/>
     *         &lt;element name="RegistrationAndSegmentationMethodMethodUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CountsPerVOIAtTimePointProduced" type="{https://www.irdbb-medirad.com}CountsPerVOIAtTimePoint" maxOccurs="unbounded"/>
     *         &lt;element name="VOIInSPECTProduced" type="{https://www.irdbb-medirad.com}VOI" maxOccurs="unbounded"/>
     *         &lt;element name="CTReconResampledOnSPECTProduced" type="{https://www.irdbb-medirad.com}DICOMData"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "processExecutionContext",
        "timePointIdentifierUsed",
        "nmTomoReconUsed",
        "ctReconUsed",
        "registrationAndSegmentationMethodMethodUsed",
        "countsPerVOIAtTimePointProduced",
        "voiInSPECTProduced",
        "ctReconResampledOnSPECTProduced"
    })
    public static class RegistrationVOISegmentation {

        @XmlElement(name = "ProcessExecutionContext", required = true)
        protected ProcessExecutionContext processExecutionContext;
        @XmlElement(name = "TimePointIdentifierUsed", required = true)
        protected String timePointIdentifierUsed;
        @XmlElement(name = "NMTomoReconUsed", required = true)
        protected DICOMData nmTomoReconUsed;
        @XmlElement(name = "CTReconUsed", required = true)
        protected DICOMData ctReconUsed;
        @XmlElement(name = "RegistrationAndSegmentationMethodMethodUsed", required = true)
        protected String registrationAndSegmentationMethodMethodUsed;
        @XmlElement(name = "CountsPerVOIAtTimePointProduced", required = true)
        protected List<CountsPerVOIAtTimePoint> countsPerVOIAtTimePointProduced;
        @XmlElement(name = "VOIInSPECTProduced", required = true)
        protected List<VOI> voiInSPECTProduced;
        @XmlElement(name = "CTReconResampledOnSPECTProduced", required = true)
        protected DICOMData ctReconResampledOnSPECTProduced;

        /**
         * Obtient la valeur de la propriété processExecutionContext.
         * 
         * @return
         *     possible object is
         *     {@link ProcessExecutionContext }
         *     
         */
        public ProcessExecutionContext getProcessExecutionContext() {
            return processExecutionContext;
        }

        /**
         * Définit la valeur de la propriété processExecutionContext.
         * 
         * @param value
         *     allowed object is
         *     {@link ProcessExecutionContext }
         *     
         */
        public void setProcessExecutionContext(ProcessExecutionContext value) {
            this.processExecutionContext = value;
        }

        /**
         * Obtient la valeur de la propriété timePointIdentifierUsed.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTimePointIdentifierUsed() {
            return timePointIdentifierUsed;
        }

        /**
         * Définit la valeur de la propriété timePointIdentifierUsed.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTimePointIdentifierUsed(String value) {
            this.timePointIdentifierUsed = value;
        }

        /**
         * Obtient la valeur de la propriété nmTomoReconUsed.
         * 
         * @return
         *     possible object is
         *     {@link DICOMData }
         *     
         */
        public DICOMData getNMTomoReconUsed() {
            return nmTomoReconUsed;
        }

        /**
         * Définit la valeur de la propriété nmTomoReconUsed.
         * 
         * @param value
         *     allowed object is
         *     {@link DICOMData }
         *     
         */
        public void setNMTomoReconUsed(DICOMData value) {
            this.nmTomoReconUsed = value;
        }

        /**
         * Obtient la valeur de la propriété ctReconUsed.
         * 
         * @return
         *     possible object is
         *     {@link DICOMData }
         *     
         */
        public DICOMData getCTReconUsed() {
            return ctReconUsed;
        }

        /**
         * Définit la valeur de la propriété ctReconUsed.
         * 
         * @param value
         *     allowed object is
         *     {@link DICOMData }
         *     
         */
        public void setCTReconUsed(DICOMData value) {
            this.ctReconUsed = value;
        }

        /**
         * Obtient la valeur de la propriété registrationAndSegmentationMethodMethodUsed.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRegistrationAndSegmentationMethodMethodUsed() {
            return registrationAndSegmentationMethodMethodUsed;
        }

        /**
         * Définit la valeur de la propriété registrationAndSegmentationMethodMethodUsed.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRegistrationAndSegmentationMethodMethodUsed(String value) {
            this.registrationAndSegmentationMethodMethodUsed = value;
        }

        /**
         * Gets the value of the countsPerVOIAtTimePointProduced property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the countsPerVOIAtTimePointProduced property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCountsPerVOIAtTimePointProduced().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CountsPerVOIAtTimePoint }
         * 
         * 
         */
        public List<CountsPerVOIAtTimePoint> getCountsPerVOIAtTimePointProduced() {
            if (countsPerVOIAtTimePointProduced == null) {
                countsPerVOIAtTimePointProduced = new ArrayList<CountsPerVOIAtTimePoint>();
            }
            return this.countsPerVOIAtTimePointProduced;
        }

        /**
         * Gets the value of the voiInSPECTProduced property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the voiInSPECTProduced property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getVOIInSPECTProduced().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link VOI }
         * 
         * 
         */
        public List<VOI> getVOIInSPECTProduced() {
            if (voiInSPECTProduced == null) {
                voiInSPECTProduced = new ArrayList<VOI>();
            }
            return this.voiInSPECTProduced;
        }

        /**
         * Obtient la valeur de la propriété ctReconResampledOnSPECTProduced.
         * 
         * @return
         *     possible object is
         *     {@link DICOMData }
         *     
         */
        public DICOMData getCTReconResampledOnSPECTProduced() {
            return ctReconResampledOnSPECTProduced;
        }

        /**
         * Définit la valeur de la propriété ctReconResampledOnSPECTProduced.
         * 
         * @param value
         *     allowed object is
         *     {@link DICOMData }
         *     
         */
        public void setCTReconResampledOnSPECTProduced(DICOMData value) {
            this.ctReconResampledOnSPECTProduced = value;
        }

    }


    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="SPECTDataAcquisitionAndProcessing" type="{https://www.irdbb-medirad.com}SPECTDataAcquisitionAndProcessing" maxOccurs="unbounded"/>
     *         &lt;element name="RegistrationVOISegmentationAndPropagation" type="{https://www.irdbb-medirad.com}RegistrationVOISegmentationAndPropagation"/>
     *         &lt;element name="VOIActivityDetermination" type="{https://www.irdbb-medirad.com}VOIActivityDetermination" maxOccurs="unbounded"/>
     *         &lt;element name="TimeActivityCurveFit" type="{https://www.irdbb-medirad.com}TimeActivityCurveFit" maxOccurs="unbounded"/>
     *         &lt;element name="AbsorbedDoseCalculation" type="{https://www.irdbb-medirad.com}AbsorbedDoseCalculation"/>
     *         &lt;element name="RadioBiologicalCalculation" type="{https://www.irdbb-medirad.com}RadioBiologicalCalculation" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "spectDataAcquisitionAndProcessing",
        "registrationVOISegmentationAndPropagation",
        "voiActivityDetermination",
        "timeActivityCurveFit",
        "absorbedDoseCalculation",
        "radioBiologicalCalculation"
    })
    public static class ThreeDimDosimetrySlide1Workflow {

        @XmlElement(name = "SPECTDataAcquisitionAndProcessing", required = true)
        protected List<SPECTDataAcquisitionAndProcessing> spectDataAcquisitionAndProcessing;
        @XmlElement(name = "RegistrationVOISegmentationAndPropagation", required = true)
        protected RegistrationVOISegmentationAndPropagation registrationVOISegmentationAndPropagation;
        @XmlElement(name = "VOIActivityDetermination", required = true)
        protected List<VOIActivityDetermination> voiActivityDetermination;
        @XmlElement(name = "TimeActivityCurveFit", required = true)
        protected List<TimeActivityCurveFit> timeActivityCurveFit;
        @XmlElement(name = "AbsorbedDoseCalculation", required = true)
        protected AbsorbedDoseCalculation absorbedDoseCalculation;
        @XmlElement(name = "RadioBiologicalCalculation")
        protected RadioBiologicalCalculation radioBiologicalCalculation;

        /**
         * Gets the value of the spectDataAcquisitionAndProcessing property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the spectDataAcquisitionAndProcessing property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSPECTDataAcquisitionAndProcessing().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SPECTDataAcquisitionAndProcessing }
         * 
         * 
         */
        public List<SPECTDataAcquisitionAndProcessing> getSPECTDataAcquisitionAndProcessing() {
            if (spectDataAcquisitionAndProcessing == null) {
                spectDataAcquisitionAndProcessing = new ArrayList<SPECTDataAcquisitionAndProcessing>();
            }
            return this.spectDataAcquisitionAndProcessing;
        }

        /**
         * Obtient la valeur de la propriété registrationVOISegmentationAndPropagation.
         * 
         * @return
         *     possible object is
         *     {@link RegistrationVOISegmentationAndPropagation }
         *     
         */
        public RegistrationVOISegmentationAndPropagation getRegistrationVOISegmentationAndPropagation() {
            return registrationVOISegmentationAndPropagation;
        }

        /**
         * Définit la valeur de la propriété registrationVOISegmentationAndPropagation.
         * 
         * @param value
         *     allowed object is
         *     {@link RegistrationVOISegmentationAndPropagation }
         *     
         */
        public void setRegistrationVOISegmentationAndPropagation(RegistrationVOISegmentationAndPropagation value) {
            this.registrationVOISegmentationAndPropagation = value;
        }

        /**
         * Gets the value of the voiActivityDetermination property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the voiActivityDetermination property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getVOIActivityDetermination().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link VOIActivityDetermination }
         * 
         * 
         */
        public List<VOIActivityDetermination> getVOIActivityDetermination() {
            if (voiActivityDetermination == null) {
                voiActivityDetermination = new ArrayList<VOIActivityDetermination>();
            }
            return this.voiActivityDetermination;
        }

        /**
         * Gets the value of the timeActivityCurveFit property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the timeActivityCurveFit property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTimeActivityCurveFit().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TimeActivityCurveFit }
         * 
         * 
         */
        public List<TimeActivityCurveFit> getTimeActivityCurveFit() {
            if (timeActivityCurveFit == null) {
                timeActivityCurveFit = new ArrayList<TimeActivityCurveFit>();
            }
            return this.timeActivityCurveFit;
        }

        /**
         * Obtient la valeur de la propriété absorbedDoseCalculation.
         * 
         * @return
         *     possible object is
         *     {@link AbsorbedDoseCalculation }
         *     
         */
        public AbsorbedDoseCalculation getAbsorbedDoseCalculation() {
            return absorbedDoseCalculation;
        }

        /**
         * Définit la valeur de la propriété absorbedDoseCalculation.
         * 
         * @param value
         *     allowed object is
         *     {@link AbsorbedDoseCalculation }
         *     
         */
        public void setAbsorbedDoseCalculation(AbsorbedDoseCalculation value) {
            this.absorbedDoseCalculation = value;
        }

        /**
         * Obtient la valeur de la propriété radioBiologicalCalculation.
         * 
         * @return
         *     possible object is
         *     {@link RadioBiologicalCalculation }
         *     
         */
        public RadioBiologicalCalculation getRadioBiologicalCalculation() {
            return radioBiologicalCalculation;
        }

        /**
         * Définit la valeur de la propriété radioBiologicalCalculation.
         * 
         * @param value
         *     allowed object is
         *     {@link RadioBiologicalCalculation }
         *     
         */
        public void setRadioBiologicalCalculation(RadioBiologicalCalculation value) {
            this.radioBiologicalCalculation = value;
        }

    }


    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="SPECTDataAcquisitionAndProcessing" type="{https://www.irdbb-medirad.com}SPECTDataAcquisitionAndProcessing" maxOccurs="unbounded"/>
     *         &lt;element name="VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation" type="{https://www.irdbb-medirad.com}VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation" maxOccurs="unbounded"/>
     *         &lt;element name="DoseRateCurveFitVOITimeIntegration" type="{https://www.irdbb-medirad.com}DoseRateCurveFitVOITimeIntegration"/>
     *         &lt;element name="RadioBiologicalCalculation" type="{https://www.irdbb-medirad.com}RadioBiologicalCalculation" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "spectDataAcquisitionAndProcessing",
        "voiSegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation",
        "doseRateCurveFitVOITimeIntegration",
        "radioBiologicalCalculation"
    })
    public static class ThreeDimDosimetrySlide2Workflow {

        @XmlElement(name = "SPECTDataAcquisitionAndProcessing", required = true)
        protected List<SPECTDataAcquisitionAndProcessing> spectDataAcquisitionAndProcessing;
        @XmlElement(name = "VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation", required = true)
        protected List<VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation> voiSegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation;
        @XmlElement(name = "DoseRateCurveFitVOITimeIntegration", required = true)
        protected DoseRateCurveFitVOITimeIntegration doseRateCurveFitVOITimeIntegration;
        @XmlElement(name = "RadioBiologicalCalculation")
        protected RadioBiologicalCalculation radioBiologicalCalculation;

        /**
         * Gets the value of the spectDataAcquisitionAndProcessing property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the spectDataAcquisitionAndProcessing property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSPECTDataAcquisitionAndProcessing().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SPECTDataAcquisitionAndProcessing }
         * 
         * 
         */
        public List<SPECTDataAcquisitionAndProcessing> getSPECTDataAcquisitionAndProcessing() {
            if (spectDataAcquisitionAndProcessing == null) {
                spectDataAcquisitionAndProcessing = new ArrayList<SPECTDataAcquisitionAndProcessing>();
            }
            return this.spectDataAcquisitionAndProcessing;
        }

        /**
         * Gets the value of the voiSegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the voiSegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getVOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation }
         * 
         * 
         */
        public List<VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation> getVOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation() {
            if (voiSegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation == null) {
                voiSegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation = new ArrayList<VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation>();
            }
            return this.voiSegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation;
        }

        /**
         * Obtient la valeur de la propriété doseRateCurveFitVOITimeIntegration.
         * 
         * @return
         *     possible object is
         *     {@link DoseRateCurveFitVOITimeIntegration }
         *     
         */
        public DoseRateCurveFitVOITimeIntegration getDoseRateCurveFitVOITimeIntegration() {
            return doseRateCurveFitVOITimeIntegration;
        }

        /**
         * Définit la valeur de la propriété doseRateCurveFitVOITimeIntegration.
         * 
         * @param value
         *     allowed object is
         *     {@link DoseRateCurveFitVOITimeIntegration }
         *     
         */
        public void setDoseRateCurveFitVOITimeIntegration(DoseRateCurveFitVOITimeIntegration value) {
            this.doseRateCurveFitVOITimeIntegration = value;
        }

        /**
         * Obtient la valeur de la propriété radioBiologicalCalculation.
         * 
         * @return
         *     possible object is
         *     {@link RadioBiologicalCalculation }
         *     
         */
        public RadioBiologicalCalculation getRadioBiologicalCalculation() {
            return radioBiologicalCalculation;
        }

        /**
         * Définit la valeur de la propriété radioBiologicalCalculation.
         * 
         * @param value
         *     allowed object is
         *     {@link RadioBiologicalCalculation }
         *     
         */
        public void setRadioBiologicalCalculation(RadioBiologicalCalculation value) {
            this.radioBiologicalCalculation = value;
        }

    }


    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="TimeIntegratedActivityCoefficientPerVOIValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
     *         &lt;element name="TimeIntegratedActivityCoefficientPerVOIUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="VOIIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "timeIntegratedActivityCoefficientPerVOIValue",
        "timeIntegratedActivityCoefficientPerVOIUnit",
        "voiIdentifier"
    })
    public static class TimeIntegratedActivityCoefficientPerVOI {

        @XmlElement(name = "TimeIntegratedActivityCoefficientPerVOIValue")
        protected float timeIntegratedActivityCoefficientPerVOIValue;
        @XmlElement(name = "TimeIntegratedActivityCoefficientPerVOIUnit", required = true)
        protected String timeIntegratedActivityCoefficientPerVOIUnit;
        @XmlElement(name = "VOIIdentifier", required = true)
        protected String voiIdentifier;

        /**
         * Obtient la valeur de la propriété timeIntegratedActivityCoefficientPerVOIValue.
         * 
         */
        public float getTimeIntegratedActivityCoefficientPerVOIValue() {
            return timeIntegratedActivityCoefficientPerVOIValue;
        }

        /**
         * Définit la valeur de la propriété timeIntegratedActivityCoefficientPerVOIValue.
         * 
         */
        public void setTimeIntegratedActivityCoefficientPerVOIValue(float value) {
            this.timeIntegratedActivityCoefficientPerVOIValue = value;
        }

        /**
         * Obtient la valeur de la propriété timeIntegratedActivityCoefficientPerVOIUnit.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTimeIntegratedActivityCoefficientPerVOIUnit() {
            return timeIntegratedActivityCoefficientPerVOIUnit;
        }

        /**
         * Définit la valeur de la propriété timeIntegratedActivityCoefficientPerVOIUnit.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTimeIntegratedActivityCoefficientPerVOIUnit(String value) {
            this.timeIntegratedActivityCoefficientPerVOIUnit = value;
        }

        /**
         * Obtient la valeur de la propriété voiIdentifier.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVOIIdentifier() {
            return voiIdentifier;
        }

        /**
         * Définit la valeur de la propriété voiIdentifier.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVOIIdentifier(String value) {
            this.voiIdentifier = value;
        }

    }


    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="TimeIntegratedActivityPerVOIValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
     *         &lt;element name="TimeIntegratedActivityPerVOIUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="VOIIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "timeIntegratedActivityPerVOIValue",
        "timeIntegratedActivityPerVOIUnit",
        "voiIdentifier"
    })
    public static class TimeIntegratedActivityPerVOI {

        @XmlElement(name = "TimeIntegratedActivityPerVOIValue")
        protected float timeIntegratedActivityPerVOIValue;
        @XmlElement(name = "TimeIntegratedActivityPerVOIUnit", required = true)
        protected String timeIntegratedActivityPerVOIUnit;
        @XmlElement(name = "VOIIdentifier", required = true)
        protected String voiIdentifier;

        /**
         * Obtient la valeur de la propriété timeIntegratedActivityPerVOIValue.
         * 
         */
        public float getTimeIntegratedActivityPerVOIValue() {
            return timeIntegratedActivityPerVOIValue;
        }

        /**
         * Définit la valeur de la propriété timeIntegratedActivityPerVOIValue.
         * 
         */
        public void setTimeIntegratedActivityPerVOIValue(float value) {
            this.timeIntegratedActivityPerVOIValue = value;
        }

        /**
         * Obtient la valeur de la propriété timeIntegratedActivityPerVOIUnit.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTimeIntegratedActivityPerVOIUnit() {
            return timeIntegratedActivityPerVOIUnit;
        }

        /**
         * Définit la valeur de la propriété timeIntegratedActivityPerVOIUnit.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTimeIntegratedActivityPerVOIUnit(String value) {
            this.timeIntegratedActivityPerVOIUnit = value;
        }

        /**
         * Obtient la valeur de la propriété voiIdentifier.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVOIIdentifier() {
            return voiIdentifier;
        }

        /**
         * Définit la valeur de la propriété voiIdentifier.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVOIIdentifier(String value) {
            this.voiIdentifier = value;
        }

    }


    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="PlanarDataAcquisitionAndProcessing" type="{https://www.irdbb-medirad.com}PlanarDataAcquisitionAndProcessing" maxOccurs="unbounded"/>
     *         &lt;element name="TwoDimDosimetryViaAbsorbedDoseRateCalculation" type="{https://www.irdbb-medirad.com}TwoDimDosimetryViaAbsorbedDoseRateCalculation" minOccurs="0"/>
     *         &lt;element name="TwoDimDosimetryViaTimeActivityCurveFit" type="{https://www.irdbb-medirad.com}TwoDimDosimetryViaTimeActivityCurveFit" minOccurs="0"/>
     *         &lt;element name="RadioBiologicalCalculation" type="{https://www.irdbb-medirad.com}RadioBiologicalCalculation" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "planarDataAcquisitionAndProcessing",
        "twoDimDosimetryViaAbsorbedDoseRateCalculation",
        "twoDimDosimetryViaTimeActivityCurveFit",
        "radioBiologicalCalculation"
    })
    public static class TwoDimDosimetryworkflow {

        @XmlElement(name = "PlanarDataAcquisitionAndProcessing", required = true)
        protected List<PlanarDataAcquisitionAndProcessing> planarDataAcquisitionAndProcessing;
        @XmlElement(name = "TwoDimDosimetryViaAbsorbedDoseRateCalculation")
        protected TwoDimDosimetryViaAbsorbedDoseRateCalculation twoDimDosimetryViaAbsorbedDoseRateCalculation;
        @XmlElement(name = "TwoDimDosimetryViaTimeActivityCurveFit")
        protected TwoDimDosimetryViaTimeActivityCurveFit twoDimDosimetryViaTimeActivityCurveFit;
        @XmlElement(name = "RadioBiologicalCalculation")
        protected RadioBiologicalCalculation radioBiologicalCalculation;

        /**
         * Gets the value of the planarDataAcquisitionAndProcessing property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the planarDataAcquisitionAndProcessing property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPlanarDataAcquisitionAndProcessing().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PlanarDataAcquisitionAndProcessing }
         * 
         * 
         */
        public List<PlanarDataAcquisitionAndProcessing> getPlanarDataAcquisitionAndProcessing() {
            if (planarDataAcquisitionAndProcessing == null) {
                planarDataAcquisitionAndProcessing = new ArrayList<PlanarDataAcquisitionAndProcessing>();
            }
            return this.planarDataAcquisitionAndProcessing;
        }

        /**
         * Obtient la valeur de la propriété twoDimDosimetryViaAbsorbedDoseRateCalculation.
         * 
         * @return
         *     possible object is
         *     {@link TwoDimDosimetryViaAbsorbedDoseRateCalculation }
         *     
         */
        public TwoDimDosimetryViaAbsorbedDoseRateCalculation getTwoDimDosimetryViaAbsorbedDoseRateCalculation() {
            return twoDimDosimetryViaAbsorbedDoseRateCalculation;
        }

        /**
         * Définit la valeur de la propriété twoDimDosimetryViaAbsorbedDoseRateCalculation.
         * 
         * @param value
         *     allowed object is
         *     {@link TwoDimDosimetryViaAbsorbedDoseRateCalculation }
         *     
         */
        public void setTwoDimDosimetryViaAbsorbedDoseRateCalculation(TwoDimDosimetryViaAbsorbedDoseRateCalculation value) {
            this.twoDimDosimetryViaAbsorbedDoseRateCalculation = value;
        }

        /**
         * Obtient la valeur de la propriété twoDimDosimetryViaTimeActivityCurveFit.
         * 
         * @return
         *     possible object is
         *     {@link TwoDimDosimetryViaTimeActivityCurveFit }
         *     
         */
        public TwoDimDosimetryViaTimeActivityCurveFit getTwoDimDosimetryViaTimeActivityCurveFit() {
            return twoDimDosimetryViaTimeActivityCurveFit;
        }

        /**
         * Définit la valeur de la propriété twoDimDosimetryViaTimeActivityCurveFit.
         * 
         * @param value
         *     allowed object is
         *     {@link TwoDimDosimetryViaTimeActivityCurveFit }
         *     
         */
        public void setTwoDimDosimetryViaTimeActivityCurveFit(TwoDimDosimetryViaTimeActivityCurveFit value) {
            this.twoDimDosimetryViaTimeActivityCurveFit = value;
        }

        /**
         * Obtient la valeur de la propriété radioBiologicalCalculation.
         * 
         * @return
         *     possible object is
         *     {@link RadioBiologicalCalculation }
         *     
         */
        public RadioBiologicalCalculation getRadioBiologicalCalculation() {
            return radioBiologicalCalculation;
        }

        /**
         * Définit la valeur de la propriété radioBiologicalCalculation.
         * 
         * @param value
         *     allowed object is
         *     {@link RadioBiologicalCalculation }
         *     
         */
        public void setRadioBiologicalCalculation(RadioBiologicalCalculation value) {
            this.radioBiologicalCalculation = value;
        }

    }


    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="CTSegmentation" type="{https://www.irdbb-medirad.com}CTSegmentation" minOccurs="0"/>
     *         &lt;element name="SimpleCTMonteCarloDosimetry" type="{https://www.irdbb-medirad.com}SimpleCTMonteCarloDosimetry" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "ctSegmentation",
        "simpleCTMonteCarloDosimetry"
    })
    public static class WP2Subtask212WorkflowData {

        @XmlElement(name = "CTSegmentation")
        protected CTSegmentation ctSegmentation;
        @XmlElement(name = "SimpleCTMonteCarloDosimetry")
        protected List<SimpleCTMonteCarloDosimetry> simpleCTMonteCarloDosimetry;

        /**
         * Obtient la valeur de la propriété ctSegmentation.
         * 
         * @return
         *     possible object is
         *     {@link CTSegmentation }
         *     
         */
        public CTSegmentation getCTSegmentation() {
            return ctSegmentation;
        }

        /**
         * Définit la valeur de la propriété ctSegmentation.
         * 
         * @param value
         *     allowed object is
         *     {@link CTSegmentation }
         *     
         */
        public void setCTSegmentation(CTSegmentation value) {
            this.ctSegmentation = value;
        }

        /**
         * Gets the value of the simpleCTMonteCarloDosimetry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the simpleCTMonteCarloDosimetry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSimpleCTMonteCarloDosimetry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SimpleCTMonteCarloDosimetry }
         * 
         * 
         */
        public List<SimpleCTMonteCarloDosimetry> getSimpleCTMonteCarloDosimetry() {
            if (simpleCTMonteCarloDosimetry == null) {
                simpleCTMonteCarloDosimetry = new ArrayList<SimpleCTMonteCarloDosimetry>();
            }
            return this.simpleCTMonteCarloDosimetry;
        }

    }

}
