//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.07.30 à 02:06:37 PM CEST 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour NonDicomFileSetDescriptor complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="NonDicomFileSetDescriptor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ReferencedClinicalResearchStudy" type="{https://www.irdbb-medirad.com}ReferencedClinicalResearchStudy"/>
 *         &lt;element name="PatientId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AcquisitionSettings" type="{https://www.irdbb-medirad.com}AcquisitionSettings" minOccurs="0"/>
 *         &lt;element name="CalibrationWorkflow" type="{https://www.irdbb-medirad.com}CalibrationWorkflow" minOccurs="0"/>
 *         &lt;element name="WP2subtask212WorkflowData" type="{https://www.irdbb-medirad.com}WP2subtask212WorkflowData" minOccurs="0"/>
 *         &lt;element name="ThreeDimDosimetrySlide1workflow" type="{https://www.irdbb-medirad.com}ThreeDimDosimetrySlide1workflow" minOccurs="0"/>
 *         &lt;element name="TwoDimDosimetryworkflow" type="{https://www.irdbb-medirad.com}TwoDimDosimetryworkflow" minOccurs="0"/>
 *         &lt;element name="HybridDosimetryworkflow" type="{https://www.irdbb-medirad.com}HybridDosimetryworkflow" minOccurs="0"/>
 *         &lt;element name="ThreeDimDosimetrySlide2workflow" type="{https://www.irdbb-medirad.com}ThreeDimDosimetrySlide2workflow" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NonDicomFileSetDescriptor", propOrder = {
    "referencedClinicalResearchStudy",
    "patientId",
    "acquisitionSettings",
    "calibrationWorkflow",
    "wp2Subtask212WorkflowData",
    "threeDimDosimetrySlide1Workflow",
    "twoDimDosimetryworkflow",
    "hybridDosimetryworkflow",
    "threeDimDosimetrySlide2Workflow"
})
public class NonDicomFileSetDescriptor {

    @XmlElement(name = "ReferencedClinicalResearchStudy", required = true)
    protected ReferencedClinicalResearchStudy referencedClinicalResearchStudy;
    @XmlElement(name = "PatientId", required = true)
    protected String patientId;
    @XmlElement(name = "AcquisitionSettings")
    protected AcquisitionSettings acquisitionSettings;
    @XmlElement(name = "CalibrationWorkflow")
    protected CalibrationWorkflow calibrationWorkflow;
    @XmlElement(name = "WP2subtask212WorkflowData")
    protected WP2Subtask212WorkflowData wp2Subtask212WorkflowData;
    @XmlElement(name = "ThreeDimDosimetrySlide1workflow")
    protected ThreeDimDosimetrySlide1Workflow threeDimDosimetrySlide1Workflow;
    @XmlElement(name = "TwoDimDosimetryworkflow")
    protected TwoDimDosimetryworkflow twoDimDosimetryworkflow;
    @XmlElement(name = "HybridDosimetryworkflow")
    protected HybridDosimetryworkflow hybridDosimetryworkflow;
    @XmlElement(name = "ThreeDimDosimetrySlide2workflow")
    protected ThreeDimDosimetrySlide2Workflow threeDimDosimetrySlide2Workflow;

    /**
     * Obtient la valeur de la propriété referencedClinicalResearchStudy.
     * 
     * @return
     *     possible object is
     *     {@link ReferencedClinicalResearchStudy }
     *     
     */
    public ReferencedClinicalResearchStudy getReferencedClinicalResearchStudy() {
        return referencedClinicalResearchStudy;
    }

    /**
     * Définit la valeur de la propriété referencedClinicalResearchStudy.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferencedClinicalResearchStudy }
     *     
     */
    public void setReferencedClinicalResearchStudy(ReferencedClinicalResearchStudy value) {
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
     * Obtient la valeur de la propriété acquisitionSettings.
     * 
     * @return
     *     possible object is
     *     {@link AcquisitionSettings }
     *     
     */
    public AcquisitionSettings getAcquisitionSettings() {
        return acquisitionSettings;
    }

    /**
     * Définit la valeur de la propriété acquisitionSettings.
     * 
     * @param value
     *     allowed object is
     *     {@link AcquisitionSettings }
     *     
     */
    public void setAcquisitionSettings(AcquisitionSettings value) {
        this.acquisitionSettings = value;
    }

    /**
     * Obtient la valeur de la propriété calibrationWorkflow.
     * 
     * @return
     *     possible object is
     *     {@link CalibrationWorkflow }
     *     
     */
    public CalibrationWorkflow getCalibrationWorkflow() {
        return calibrationWorkflow;
    }

    /**
     * Définit la valeur de la propriété calibrationWorkflow.
     * 
     * @param value
     *     allowed object is
     *     {@link CalibrationWorkflow }
     *     
     */
    public void setCalibrationWorkflow(CalibrationWorkflow value) {
        this.calibrationWorkflow = value;
    }

    /**
     * Obtient la valeur de la propriété wp2Subtask212WorkflowData.
     * 
     * @return
     *     possible object is
     *     {@link WP2Subtask212WorkflowData }
     *     
     */
    public WP2Subtask212WorkflowData getWP2Subtask212WorkflowData() {
        return wp2Subtask212WorkflowData;
    }

    /**
     * Définit la valeur de la propriété wp2Subtask212WorkflowData.
     * 
     * @param value
     *     allowed object is
     *     {@link WP2Subtask212WorkflowData }
     *     
     */
    public void setWP2Subtask212WorkflowData(WP2Subtask212WorkflowData value) {
        this.wp2Subtask212WorkflowData = value;
    }

    /**
     * Obtient la valeur de la propriété threeDimDosimetrySlide1Workflow.
     * 
     * @return
     *     possible object is
     *     {@link ThreeDimDosimetrySlide1Workflow }
     *     
     */
    public ThreeDimDosimetrySlide1Workflow getThreeDimDosimetrySlide1Workflow() {
        return threeDimDosimetrySlide1Workflow;
    }

    /**
     * Définit la valeur de la propriété threeDimDosimetrySlide1Workflow.
     * 
     * @param value
     *     allowed object is
     *     {@link ThreeDimDosimetrySlide1Workflow }
     *     
     */
    public void setThreeDimDosimetrySlide1Workflow(ThreeDimDosimetrySlide1Workflow value) {
        this.threeDimDosimetrySlide1Workflow = value;
    }

    /**
     * Obtient la valeur de la propriété twoDimDosimetryworkflow.
     * 
     * @return
     *     possible object is
     *     {@link TwoDimDosimetryworkflow }
     *     
     */
    public TwoDimDosimetryworkflow getTwoDimDosimetryworkflow() {
        return twoDimDosimetryworkflow;
    }

    /**
     * Définit la valeur de la propriété twoDimDosimetryworkflow.
     * 
     * @param value
     *     allowed object is
     *     {@link TwoDimDosimetryworkflow }
     *     
     */
    public void setTwoDimDosimetryworkflow(TwoDimDosimetryworkflow value) {
        this.twoDimDosimetryworkflow = value;
    }

    /**
     * Obtient la valeur de la propriété hybridDosimetryworkflow.
     * 
     * @return
     *     possible object is
     *     {@link HybridDosimetryworkflow }
     *     
     */
    public HybridDosimetryworkflow getHybridDosimetryworkflow() {
        return hybridDosimetryworkflow;
    }

    /**
     * Définit la valeur de la propriété hybridDosimetryworkflow.
     * 
     * @param value
     *     allowed object is
     *     {@link HybridDosimetryworkflow }
     *     
     */
    public void setHybridDosimetryworkflow(HybridDosimetryworkflow value) {
        this.hybridDosimetryworkflow = value;
    }

    /**
     * Obtient la valeur de la propriété threeDimDosimetrySlide2Workflow.
     * 
     * @return
     *     possible object is
     *     {@link ThreeDimDosimetrySlide2Workflow }
     *     
     */
    public ThreeDimDosimetrySlide2Workflow getThreeDimDosimetrySlide2Workflow() {
        return threeDimDosimetrySlide2Workflow;
    }

    /**
     * Définit la valeur de la propriété threeDimDosimetrySlide2Workflow.
     * 
     * @param value
     *     allowed object is
     *     {@link ThreeDimDosimetrySlide2Workflow }
     *     
     */
    public void setThreeDimDosimetrySlide2Workflow(ThreeDimDosimetrySlide2Workflow value) {
        this.threeDimDosimetrySlide2Workflow = value;
    }

}
