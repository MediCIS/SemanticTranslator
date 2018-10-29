//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2018.10.16 à 03:58:02 PM CEST 
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
 *         &lt;element name="WP2subtask212WorkflowData" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CTSegmentation" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="DateTimeProcessStarted" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="PerformingInstitution" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="DICOMImageUsed" type="{https://www.irdbb-medirad.com}DICOMDataType"/>
 *                             &lt;element name="SegmentationMethodUsed" type="{https://www.irdbb-medirad.com}SegmentationMethodType" minOccurs="0"/>
 *                             &lt;element name="VOIDescriptorProduced" type="{https://www.irdbb-medirad.com}VOIDescriptorType" maxOccurs="unbounded" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="SimpleCTMonteCarloDosimetry" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="CalculationOfVoxelMap">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="DateTimeProcessStarted" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="PerformingInstitution" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="AttenuatorUsed" type="{https://www.irdbb-medirad.com}AttenuatorType" maxOccurs="unbounded" minOccurs="0"/>
 *                                       &lt;element name="MonteCarloMethodUsed" type="{https://www.irdbb-medirad.com}MonteCarloMethodType" minOccurs="0"/>
 *                                       &lt;element name="DICOMCTImageDataUsed" type="{https://www.irdbb-medirad.com}DICOMDataType" maxOccurs="unbounded" minOccurs="0"/>
 *                                       &lt;element name="VoxelBasedDistributionOfAbsorbedDoseProduced" type="{https://www.irdbb-medirad.com}VoxelBasedDistributionOfAbsorbedDoseType" maxOccurs="unbounded" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="CalculationOfAbsorbedDosesInVOIs">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="DateTimeProcessStarted" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="PerformingInstitution" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="VoxelBasedDistributionOfAbsorbedDoseUsed" type="{https://www.irdbb-medirad.com}VoxelBasedDistributionOfAbsorbedDoseType" maxOccurs="unbounded" minOccurs="0"/>
 *                                       &lt;element name="VOIDescriptorUsed" type="{https://www.irdbb-medirad.com}VOIDescriptorType" maxOccurs="unbounded" minOccurs="0"/>
 *                                       &lt;element name="AbsorbedDosePerVOI" type="{https://www.irdbb-medirad.com}AbsorbedDosePerVOIType" maxOccurs="unbounded" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
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
    "wp2Subtask212WorkflowData"
})
@XmlRootElement(name = "NonDicomFileSetDescriptor")
public class NonDicomFileSetDescriptor {

    @XmlElement(name = "ReferencedClinicalResearchStudy", required = true)
    protected NonDicomFileSetDescriptor.ReferencedClinicalResearchStudy referencedClinicalResearchStudy;
    @XmlElement(name = "PatientId", required = true)
    protected String patientId;
    @XmlElement(name = "WP2subtask212WorkflowData", required = true)
    protected List<NonDicomFileSetDescriptor.WP2Subtask212WorkflowData> wp2Subtask212WorkflowData;

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
     *         &lt;element name="CTSegmentation" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="DateTimeProcessStarted" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="PerformingInstitution" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="DICOMImageUsed" type="{https://www.irdbb-medirad.com}DICOMDataType"/>
     *                   &lt;element name="SegmentationMethodUsed" type="{https://www.irdbb-medirad.com}SegmentationMethodType" minOccurs="0"/>
     *                   &lt;element name="VOIDescriptorProduced" type="{https://www.irdbb-medirad.com}VOIDescriptorType" maxOccurs="unbounded" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="SimpleCTMonteCarloDosimetry" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="CalculationOfVoxelMap">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="DateTimeProcessStarted" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="PerformingInstitution" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="AttenuatorUsed" type="{https://www.irdbb-medirad.com}AttenuatorType" maxOccurs="unbounded" minOccurs="0"/>
     *                             &lt;element name="MonteCarloMethodUsed" type="{https://www.irdbb-medirad.com}MonteCarloMethodType" minOccurs="0"/>
     *                             &lt;element name="DICOMCTImageDataUsed" type="{https://www.irdbb-medirad.com}DICOMDataType" maxOccurs="unbounded" minOccurs="0"/>
     *                             &lt;element name="VoxelBasedDistributionOfAbsorbedDoseProduced" type="{https://www.irdbb-medirad.com}VoxelBasedDistributionOfAbsorbedDoseType" maxOccurs="unbounded" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="CalculationOfAbsorbedDosesInVOIs">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="DateTimeProcessStarted" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="PerformingInstitution" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="VoxelBasedDistributionOfAbsorbedDoseUsed" type="{https://www.irdbb-medirad.com}VoxelBasedDistributionOfAbsorbedDoseType" maxOccurs="unbounded" minOccurs="0"/>
     *                             &lt;element name="VOIDescriptorUsed" type="{https://www.irdbb-medirad.com}VOIDescriptorType" maxOccurs="unbounded" minOccurs="0"/>
     *                             &lt;element name="AbsorbedDosePerVOI" type="{https://www.irdbb-medirad.com}AbsorbedDosePerVOIType" maxOccurs="unbounded" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
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
        "ctSegmentation",
        "simpleCTMonteCarloDosimetry"
    })
    public static class WP2Subtask212WorkflowData {

        @XmlElement(name = "CTSegmentation")
        protected NonDicomFileSetDescriptor.WP2Subtask212WorkflowData.CTSegmentation ctSegmentation;
        @XmlElement(name = "SimpleCTMonteCarloDosimetry")
        protected List<NonDicomFileSetDescriptor.WP2Subtask212WorkflowData.SimpleCTMonteCarloDosimetry> simpleCTMonteCarloDosimetry;

        /**
         * Obtient la valeur de la propriété ctSegmentation.
         * 
         * @return
         *     possible object is
         *     {@link NonDicomFileSetDescriptor.WP2Subtask212WorkflowData.CTSegmentation }
         *     
         */
        public NonDicomFileSetDescriptor.WP2Subtask212WorkflowData.CTSegmentation getCTSegmentation() {
            return ctSegmentation;
        }

        /**
         * Définit la valeur de la propriété ctSegmentation.
         * 
         * @param value
         *     allowed object is
         *     {@link NonDicomFileSetDescriptor.WP2Subtask212WorkflowData.CTSegmentation }
         *     
         */
        public void setCTSegmentation(NonDicomFileSetDescriptor.WP2Subtask212WorkflowData.CTSegmentation value) {
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
         * {@link NonDicomFileSetDescriptor.WP2Subtask212WorkflowData.SimpleCTMonteCarloDosimetry }
         * 
         * 
         */
        public List<NonDicomFileSetDescriptor.WP2Subtask212WorkflowData.SimpleCTMonteCarloDosimetry> getSimpleCTMonteCarloDosimetry() {
            if (simpleCTMonteCarloDosimetry == null) {
                simpleCTMonteCarloDosimetry = new ArrayList<NonDicomFileSetDescriptor.WP2Subtask212WorkflowData.SimpleCTMonteCarloDosimetry>();
            }
            return this.simpleCTMonteCarloDosimetry;
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
         *         &lt;element name="DateTimeProcessStarted" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="PerformingInstitution" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="DICOMImageUsed" type="{https://www.irdbb-medirad.com}DICOMDataType"/>
         *         &lt;element name="SegmentationMethodUsed" type="{https://www.irdbb-medirad.com}SegmentationMethodType" minOccurs="0"/>
         *         &lt;element name="VOIDescriptorProduced" type="{https://www.irdbb-medirad.com}VOIDescriptorType" maxOccurs="unbounded" minOccurs="0"/>
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
            "dateTimeProcessStarted",
            "performingInstitution",
            "dicomImageUsed",
            "segmentationMethodUsed",
            "voiDescriptorProduced"
        })
        public static class CTSegmentation {

            @XmlElement(name = "DateTimeProcessStarted", required = true)
            protected String dateTimeProcessStarted;
            @XmlElement(name = "PerformingInstitution", required = true)
            protected String performingInstitution;
            @XmlElement(name = "DICOMImageUsed", required = true)
            protected DICOMDataType dicomImageUsed;
            @XmlElement(name = "SegmentationMethodUsed")
            protected SegmentationMethodType segmentationMethodUsed;
            @XmlElement(name = "VOIDescriptorProduced")
            protected List<VOIDescriptorType> voiDescriptorProduced;

            /**
             * Obtient la valeur de la propriété dateTimeProcessStarted.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDateTimeProcessStarted() {
                return dateTimeProcessStarted;
            }

            /**
             * Définit la valeur de la propriété dateTimeProcessStarted.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDateTimeProcessStarted(String value) {
                this.dateTimeProcessStarted = value;
            }

            /**
             * Obtient la valeur de la propriété performingInstitution.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPerformingInstitution() {
                return performingInstitution;
            }

            /**
             * Définit la valeur de la propriété performingInstitution.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPerformingInstitution(String value) {
                this.performingInstitution = value;
            }

            /**
             * Obtient la valeur de la propriété dicomImageUsed.
             * 
             * @return
             *     possible object is
             *     {@link DICOMDataType }
             *     
             */
            public DICOMDataType getDICOMImageUsed() {
                return dicomImageUsed;
            }

            /**
             * Définit la valeur de la propriété dicomImageUsed.
             * 
             * @param value
             *     allowed object is
             *     {@link DICOMDataType }
             *     
             */
            public void setDICOMImageUsed(DICOMDataType value) {
                this.dicomImageUsed = value;
            }

            /**
             * Obtient la valeur de la propriété segmentationMethodUsed.
             * 
             * @return
             *     possible object is
             *     {@link SegmentationMethodType }
             *     
             */
            public SegmentationMethodType getSegmentationMethodUsed() {
                return segmentationMethodUsed;
            }

            /**
             * Définit la valeur de la propriété segmentationMethodUsed.
             * 
             * @param value
             *     allowed object is
             *     {@link SegmentationMethodType }
             *     
             */
            public void setSegmentationMethodUsed(SegmentationMethodType value) {
                this.segmentationMethodUsed = value;
            }

            /**
             * Gets the value of the voiDescriptorProduced property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the voiDescriptorProduced property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getVOIDescriptorProduced().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link VOIDescriptorType }
             * 
             * 
             */
            public List<VOIDescriptorType> getVOIDescriptorProduced() {
                if (voiDescriptorProduced == null) {
                    voiDescriptorProduced = new ArrayList<VOIDescriptorType>();
                }
                return this.voiDescriptorProduced;
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
         *         &lt;element name="CalculationOfVoxelMap">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="DateTimeProcessStarted" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="PerformingInstitution" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="AttenuatorUsed" type="{https://www.irdbb-medirad.com}AttenuatorType" maxOccurs="unbounded" minOccurs="0"/>
         *                   &lt;element name="MonteCarloMethodUsed" type="{https://www.irdbb-medirad.com}MonteCarloMethodType" minOccurs="0"/>
         *                   &lt;element name="DICOMCTImageDataUsed" type="{https://www.irdbb-medirad.com}DICOMDataType" maxOccurs="unbounded" minOccurs="0"/>
         *                   &lt;element name="VoxelBasedDistributionOfAbsorbedDoseProduced" type="{https://www.irdbb-medirad.com}VoxelBasedDistributionOfAbsorbedDoseType" maxOccurs="unbounded" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="CalculationOfAbsorbedDosesInVOIs">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="DateTimeProcessStarted" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="PerformingInstitution" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="VoxelBasedDistributionOfAbsorbedDoseUsed" type="{https://www.irdbb-medirad.com}VoxelBasedDistributionOfAbsorbedDoseType" maxOccurs="unbounded" minOccurs="0"/>
         *                   &lt;element name="VOIDescriptorUsed" type="{https://www.irdbb-medirad.com}VOIDescriptorType" maxOccurs="unbounded" minOccurs="0"/>
         *                   &lt;element name="AbsorbedDosePerVOI" type="{https://www.irdbb-medirad.com}AbsorbedDosePerVOIType" maxOccurs="unbounded" minOccurs="0"/>
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
            "calculationOfVoxelMap",
            "calculationOfAbsorbedDosesInVOIs"
        })
        public static class SimpleCTMonteCarloDosimetry {

            @XmlElement(name = "CalculationOfVoxelMap", required = true)
            protected NonDicomFileSetDescriptor.WP2Subtask212WorkflowData.SimpleCTMonteCarloDosimetry.CalculationOfVoxelMap calculationOfVoxelMap;
            @XmlElement(name = "CalculationOfAbsorbedDosesInVOIs", required = true)
            protected NonDicomFileSetDescriptor.WP2Subtask212WorkflowData.SimpleCTMonteCarloDosimetry.CalculationOfAbsorbedDosesInVOIs calculationOfAbsorbedDosesInVOIs;

            /**
             * Obtient la valeur de la propriété calculationOfVoxelMap.
             * 
             * @return
             *     possible object is
             *     {@link NonDicomFileSetDescriptor.WP2Subtask212WorkflowData.SimpleCTMonteCarloDosimetry.CalculationOfVoxelMap }
             *     
             */
            public NonDicomFileSetDescriptor.WP2Subtask212WorkflowData.SimpleCTMonteCarloDosimetry.CalculationOfVoxelMap getCalculationOfVoxelMap() {
                return calculationOfVoxelMap;
            }

            /**
             * Définit la valeur de la propriété calculationOfVoxelMap.
             * 
             * @param value
             *     allowed object is
             *     {@link NonDicomFileSetDescriptor.WP2Subtask212WorkflowData.SimpleCTMonteCarloDosimetry.CalculationOfVoxelMap }
             *     
             */
            public void setCalculationOfVoxelMap(NonDicomFileSetDescriptor.WP2Subtask212WorkflowData.SimpleCTMonteCarloDosimetry.CalculationOfVoxelMap value) {
                this.calculationOfVoxelMap = value;
            }

            /**
             * Obtient la valeur de la propriété calculationOfAbsorbedDosesInVOIs.
             * 
             * @return
             *     possible object is
             *     {@link NonDicomFileSetDescriptor.WP2Subtask212WorkflowData.SimpleCTMonteCarloDosimetry.CalculationOfAbsorbedDosesInVOIs }
             *     
             */
            public NonDicomFileSetDescriptor.WP2Subtask212WorkflowData.SimpleCTMonteCarloDosimetry.CalculationOfAbsorbedDosesInVOIs getCalculationOfAbsorbedDosesInVOIs() {
                return calculationOfAbsorbedDosesInVOIs;
            }

            /**
             * Définit la valeur de la propriété calculationOfAbsorbedDosesInVOIs.
             * 
             * @param value
             *     allowed object is
             *     {@link NonDicomFileSetDescriptor.WP2Subtask212WorkflowData.SimpleCTMonteCarloDosimetry.CalculationOfAbsorbedDosesInVOIs }
             *     
             */
            public void setCalculationOfAbsorbedDosesInVOIs(NonDicomFileSetDescriptor.WP2Subtask212WorkflowData.SimpleCTMonteCarloDosimetry.CalculationOfAbsorbedDosesInVOIs value) {
                this.calculationOfAbsorbedDosesInVOIs = value;
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
             *         &lt;element name="DateTimeProcessStarted" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="PerformingInstitution" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="VoxelBasedDistributionOfAbsorbedDoseUsed" type="{https://www.irdbb-medirad.com}VoxelBasedDistributionOfAbsorbedDoseType" maxOccurs="unbounded" minOccurs="0"/>
             *         &lt;element name="VOIDescriptorUsed" type="{https://www.irdbb-medirad.com}VOIDescriptorType" maxOccurs="unbounded" minOccurs="0"/>
             *         &lt;element name="AbsorbedDosePerVOI" type="{https://www.irdbb-medirad.com}AbsorbedDosePerVOIType" maxOccurs="unbounded" minOccurs="0"/>
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
                "dateTimeProcessStarted",
                "performingInstitution",
                "voxelBasedDistributionOfAbsorbedDoseUsed",
                "voiDescriptorUsed",
                "absorbedDosePerVOI"
            })
            public static class CalculationOfAbsorbedDosesInVOIs {

                @XmlElement(name = "DateTimeProcessStarted", required = true)
                protected String dateTimeProcessStarted;
                @XmlElement(name = "PerformingInstitution", required = true)
                protected String performingInstitution;
                @XmlElement(name = "VoxelBasedDistributionOfAbsorbedDoseUsed")
                protected List<VoxelBasedDistributionOfAbsorbedDoseType> voxelBasedDistributionOfAbsorbedDoseUsed;
                @XmlElement(name = "VOIDescriptorUsed")
                protected List<VOIDescriptorType> voiDescriptorUsed;
                @XmlElement(name = "AbsorbedDosePerVOI")
                protected List<AbsorbedDosePerVOIType> absorbedDosePerVOI;

                /**
                 * Obtient la valeur de la propriété dateTimeProcessStarted.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDateTimeProcessStarted() {
                    return dateTimeProcessStarted;
                }

                /**
                 * Définit la valeur de la propriété dateTimeProcessStarted.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDateTimeProcessStarted(String value) {
                    this.dateTimeProcessStarted = value;
                }

                /**
                 * Obtient la valeur de la propriété performingInstitution.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getPerformingInstitution() {
                    return performingInstitution;
                }

                /**
                 * Définit la valeur de la propriété performingInstitution.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setPerformingInstitution(String value) {
                    this.performingInstitution = value;
                }

                /**
                 * Gets the value of the voxelBasedDistributionOfAbsorbedDoseUsed property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the voxelBasedDistributionOfAbsorbedDoseUsed property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getVoxelBasedDistributionOfAbsorbedDoseUsed().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link VoxelBasedDistributionOfAbsorbedDoseType }
                 * 
                 * 
                 */
                public List<VoxelBasedDistributionOfAbsorbedDoseType> getVoxelBasedDistributionOfAbsorbedDoseUsed() {
                    if (voxelBasedDistributionOfAbsorbedDoseUsed == null) {
                        voxelBasedDistributionOfAbsorbedDoseUsed = new ArrayList<VoxelBasedDistributionOfAbsorbedDoseType>();
                    }
                    return this.voxelBasedDistributionOfAbsorbedDoseUsed;
                }

                /**
                 * Gets the value of the voiDescriptorUsed property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the voiDescriptorUsed property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getVOIDescriptorUsed().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link VOIDescriptorType }
                 * 
                 * 
                 */
                public List<VOIDescriptorType> getVOIDescriptorUsed() {
                    if (voiDescriptorUsed == null) {
                        voiDescriptorUsed = new ArrayList<VOIDescriptorType>();
                    }
                    return this.voiDescriptorUsed;
                }

                /**
                 * Gets the value of the absorbedDosePerVOI property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the absorbedDosePerVOI property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getAbsorbedDosePerVOI().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link AbsorbedDosePerVOIType }
                 * 
                 * 
                 */
                public List<AbsorbedDosePerVOIType> getAbsorbedDosePerVOI() {
                    if (absorbedDosePerVOI == null) {
                        absorbedDosePerVOI = new ArrayList<AbsorbedDosePerVOIType>();
                    }
                    return this.absorbedDosePerVOI;
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
             *         &lt;element name="DateTimeProcessStarted" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="PerformingInstitution" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="AttenuatorUsed" type="{https://www.irdbb-medirad.com}AttenuatorType" maxOccurs="unbounded" minOccurs="0"/>
             *         &lt;element name="MonteCarloMethodUsed" type="{https://www.irdbb-medirad.com}MonteCarloMethodType" minOccurs="0"/>
             *         &lt;element name="DICOMCTImageDataUsed" type="{https://www.irdbb-medirad.com}DICOMDataType" maxOccurs="unbounded" minOccurs="0"/>
             *         &lt;element name="VoxelBasedDistributionOfAbsorbedDoseProduced" type="{https://www.irdbb-medirad.com}VoxelBasedDistributionOfAbsorbedDoseType" maxOccurs="unbounded" minOccurs="0"/>
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
                "dateTimeProcessStarted",
                "performingInstitution",
                "attenuatorUsed",
                "monteCarloMethodUsed",
                "dicomctImageDataUsed",
                "voxelBasedDistributionOfAbsorbedDoseProduced"
            })
            public static class CalculationOfVoxelMap {

                @XmlElement(name = "DateTimeProcessStarted", required = true)
                protected String dateTimeProcessStarted;
                @XmlElement(name = "PerformingInstitution", required = true)
                protected String performingInstitution;
                @XmlElement(name = "AttenuatorUsed")
                protected List<AttenuatorType> attenuatorUsed;
                @XmlElement(name = "MonteCarloMethodUsed")
                protected MonteCarloMethodType monteCarloMethodUsed;
                @XmlElement(name = "DICOMCTImageDataUsed")
                protected List<DICOMDataType> dicomctImageDataUsed;
                @XmlElement(name = "VoxelBasedDistributionOfAbsorbedDoseProduced")
                protected List<VoxelBasedDistributionOfAbsorbedDoseType> voxelBasedDistributionOfAbsorbedDoseProduced;

                /**
                 * Obtient la valeur de la propriété dateTimeProcessStarted.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDateTimeProcessStarted() {
                    return dateTimeProcessStarted;
                }

                /**
                 * Définit la valeur de la propriété dateTimeProcessStarted.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDateTimeProcessStarted(String value) {
                    this.dateTimeProcessStarted = value;
                }

                /**
                 * Obtient la valeur de la propriété performingInstitution.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getPerformingInstitution() {
                    return performingInstitution;
                }

                /**
                 * Définit la valeur de la propriété performingInstitution.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setPerformingInstitution(String value) {
                    this.performingInstitution = value;
                }

                /**
                 * Gets the value of the attenuatorUsed property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the attenuatorUsed property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getAttenuatorUsed().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link AttenuatorType }
                 * 
                 * 
                 */
                public List<AttenuatorType> getAttenuatorUsed() {
                    if (attenuatorUsed == null) {
                        attenuatorUsed = new ArrayList<AttenuatorType>();
                    }
                    return this.attenuatorUsed;
                }

                /**
                 * Obtient la valeur de la propriété monteCarloMethodUsed.
                 * 
                 * @return
                 *     possible object is
                 *     {@link MonteCarloMethodType }
                 *     
                 */
                public MonteCarloMethodType getMonteCarloMethodUsed() {
                    return monteCarloMethodUsed;
                }

                /**
                 * Définit la valeur de la propriété monteCarloMethodUsed.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link MonteCarloMethodType }
                 *     
                 */
                public void setMonteCarloMethodUsed(MonteCarloMethodType value) {
                    this.monteCarloMethodUsed = value;
                }

                /**
                 * Gets the value of the dicomctImageDataUsed property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the dicomctImageDataUsed property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getDICOMCTImageDataUsed().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link DICOMDataType }
                 * 
                 * 
                 */
                public List<DICOMDataType> getDICOMCTImageDataUsed() {
                    if (dicomctImageDataUsed == null) {
                        dicomctImageDataUsed = new ArrayList<DICOMDataType>();
                    }
                    return this.dicomctImageDataUsed;
                }

                /**
                 * Gets the value of the voxelBasedDistributionOfAbsorbedDoseProduced property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the voxelBasedDistributionOfAbsorbedDoseProduced property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getVoxelBasedDistributionOfAbsorbedDoseProduced().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link VoxelBasedDistributionOfAbsorbedDoseType }
                 * 
                 * 
                 */
                public List<VoxelBasedDistributionOfAbsorbedDoseType> getVoxelBasedDistributionOfAbsorbedDoseProduced() {
                    if (voxelBasedDistributionOfAbsorbedDoseProduced == null) {
                        voxelBasedDistributionOfAbsorbedDoseProduced = new ArrayList<VoxelBasedDistributionOfAbsorbedDoseType>();
                    }
                    return this.voxelBasedDistributionOfAbsorbedDoseProduced;
                }

            }

        }

    }

}
