//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.12 à 04:30:42 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour AbsorbedDoseCalculationInROIIn2DDosimetry complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="AbsorbedDoseCalculationInROIIn2DDosimetry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="ROIIdentifierUsed" type="{https://www.irdbb-medirad.com}ROIIdentifierUsedContainer"/>
 *         &lt;element name="OrganSValueUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PatientOrganMassUsed" type="{https://www.irdbb-medirad.com}PatientOrganMassInHybridDosimetry" minOccurs="0"/>
 *         &lt;element name="OrganMassReference" type="{https://www.irdbb-medirad.com}OrganMass" minOccurs="0"/>
 *         &lt;element name="MeanAbsorbedDoseInROIProduced" type="{https://www.irdbb-medirad.com}MeanAbsorbedDoseInROIcontainer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbsorbedDoseCalculationInROIIn2DDosimetry", propOrder = {
    "processExecutionContext",
    "roiIdentifierUsed",
    "organSValueUsed",
    "patientOrganMassUsed",
    "organMassReference",
    "meanAbsorbedDoseInROIProduced"
})
public class AbsorbedDoseCalculationInROIIn2DDosimetry {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "ROIIdentifierUsed", required = true)
    protected ROIIdentifierUsedContainer roiIdentifierUsed;
    @XmlElement(name = "OrganSValueUsed", required = true)
    protected String organSValueUsed;
    @XmlElement(name = "PatientOrganMassUsed")
    protected PatientOrganMassInHybridDosimetry patientOrganMassUsed;
    @XmlElement(name = "OrganMassReference")
    protected OrganMass organMassReference;
    @XmlElement(name = "MeanAbsorbedDoseInROIProduced", required = true)
    protected MeanAbsorbedDoseInROIcontainer meanAbsorbedDoseInROIProduced;

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
     * Obtient la valeur de la propriété roiIdentifierUsed.
     * 
     * @return
     *     possible object is
     *     {@link ROIIdentifierUsedContainer }
     *     
     */
    public ROIIdentifierUsedContainer getROIIdentifierUsed() {
        return roiIdentifierUsed;
    }

    /**
     * Définit la valeur de la propriété roiIdentifierUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link ROIIdentifierUsedContainer }
     *     
     */
    public void setROIIdentifierUsed(ROIIdentifierUsedContainer value) {
        this.roiIdentifierUsed = value;
    }

    /**
     * Obtient la valeur de la propriété organSValueUsed.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganSValueUsed() {
        return organSValueUsed;
    }

    /**
     * Définit la valeur de la propriété organSValueUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganSValueUsed(String value) {
        this.organSValueUsed = value;
    }

    /**
     * Obtient la valeur de la propriété patientOrganMassUsed.
     * 
     * @return
     *     possible object is
     *     {@link PatientOrganMassInHybridDosimetry }
     *     
     */
    public PatientOrganMassInHybridDosimetry getPatientOrganMassUsed() {
        return patientOrganMassUsed;
    }

    /**
     * Définit la valeur de la propriété patientOrganMassUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientOrganMassInHybridDosimetry }
     *     
     */
    public void setPatientOrganMassUsed(PatientOrganMassInHybridDosimetry value) {
        this.patientOrganMassUsed = value;
    }

    /**
     * Obtient la valeur de la propriété organMassReference.
     * 
     * @return
     *     possible object is
     *     {@link OrganMass }
     *     
     */
    public OrganMass getOrganMassReference() {
        return organMassReference;
    }

    /**
     * Définit la valeur de la propriété organMassReference.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganMass }
     *     
     */
    public void setOrganMassReference(OrganMass value) {
        this.organMassReference = value;
    }

    /**
     * Obtient la valeur de la propriété meanAbsorbedDoseInROIProduced.
     * 
     * @return
     *     possible object is
     *     {@link MeanAbsorbedDoseInROIcontainer }
     *     
     */
    public MeanAbsorbedDoseInROIcontainer getMeanAbsorbedDoseInROIProduced() {
        return meanAbsorbedDoseInROIProduced;
    }

    /**
     * Définit la valeur de la propriété meanAbsorbedDoseInROIProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link MeanAbsorbedDoseInROIcontainer }
     *     
     */
    public void setMeanAbsorbedDoseInROIProduced(MeanAbsorbedDoseInROIcontainer value) {
        this.meanAbsorbedDoseInROIProduced = value;
    }

}
