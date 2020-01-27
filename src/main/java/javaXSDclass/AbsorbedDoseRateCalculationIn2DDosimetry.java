//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.01.24 à 06:05:31 PM CET 
//


package javaXSDclass;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour AbsorbedDoseRateCalculationIn2DDosimetry complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="AbsorbedDoseRateCalculationIn2DDosimetry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="TimePointIdentifierUsedContainer" type="{https://www.irdbb-medirad.com}TimePointIdentifierUsedContainer"/>
 *         &lt;element name="ROIIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="PreAdministeredActivityUsed" type="{https://www.irdbb-medirad.com}AdministeredActivity"/>
 *         &lt;element name="PostAdministeredActivityUsed" type="{https://www.irdbb-medirad.com}AdministeredActivity"/>
 *         &lt;element name="OrganSValueUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ReferenceOrganMassUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PatientOrganMassUsed" type="{https://www.irdbb-medirad.com}PatientOrganMassUsedContainer"/>
 *         &lt;element name="MeanAbsorbedDoseRateInROIProduced" type="{https://www.irdbb-medirad.com}MeanAbsorbedDoseRateInROIContainer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbsorbedDoseRateCalculationIn2DDosimetry", propOrder = {
    "processExecutionContext",
    "timePointIdentifierUsedContainer",
    "roiIdentifierUsed",
    "preAdministeredActivityUsed",
    "postAdministeredActivityUsed",
    "organSValueUsed",
    "referenceOrganMassUsed",
    "patientOrganMassUsed",
    "meanAbsorbedDoseRateInROIProduced"
})
public class AbsorbedDoseRateCalculationIn2DDosimetry {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "TimePointIdentifierUsedContainer", required = true)
    protected TimePointIdentifierUsedContainer timePointIdentifierUsedContainer;
    @XmlElement(name = "ROIIdentifierUsed", required = true)
    protected List<String> roiIdentifierUsed;
    @XmlElement(name = "PreAdministeredActivityUsed", required = true)
    protected AdministeredActivity preAdministeredActivityUsed;
    @XmlElement(name = "PostAdministeredActivityUsed", required = true)
    protected AdministeredActivity postAdministeredActivityUsed;
    @XmlElement(name = "OrganSValueUsed", required = true)
    protected String organSValueUsed;
    @XmlElement(name = "ReferenceOrganMassUsed", required = true)
    protected String referenceOrganMassUsed;
    @XmlElement(name = "PatientOrganMassUsed", required = true)
    protected PatientOrganMassUsedContainer patientOrganMassUsed;
    @XmlElement(name = "MeanAbsorbedDoseRateInROIProduced", required = true)
    protected MeanAbsorbedDoseRateInROIContainer meanAbsorbedDoseRateInROIProduced;

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
     * Obtient la valeur de la propriété timePointIdentifierUsedContainer.
     * 
     * @return
     *     possible object is
     *     {@link TimePointIdentifierUsedContainer }
     *     
     */
    public TimePointIdentifierUsedContainer getTimePointIdentifierUsedContainer() {
        return timePointIdentifierUsedContainer;
    }

    /**
     * Définit la valeur de la propriété timePointIdentifierUsedContainer.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePointIdentifierUsedContainer }
     *     
     */
    public void setTimePointIdentifierUsedContainer(TimePointIdentifierUsedContainer value) {
        this.timePointIdentifierUsedContainer = value;
    }

    /**
     * Gets the value of the roiIdentifierUsed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roiIdentifierUsed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getROIIdentifierUsed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getROIIdentifierUsed() {
        if (roiIdentifierUsed == null) {
            roiIdentifierUsed = new ArrayList<String>();
        }
        return this.roiIdentifierUsed;
    }

    /**
     * Obtient la valeur de la propriété preAdministeredActivityUsed.
     * 
     * @return
     *     possible object is
     *     {@link AdministeredActivity }
     *     
     */
    public AdministeredActivity getPreAdministeredActivityUsed() {
        return preAdministeredActivityUsed;
    }

    /**
     * Définit la valeur de la propriété preAdministeredActivityUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link AdministeredActivity }
     *     
     */
    public void setPreAdministeredActivityUsed(AdministeredActivity value) {
        this.preAdministeredActivityUsed = value;
    }

    /**
     * Obtient la valeur de la propriété postAdministeredActivityUsed.
     * 
     * @return
     *     possible object is
     *     {@link AdministeredActivity }
     *     
     */
    public AdministeredActivity getPostAdministeredActivityUsed() {
        return postAdministeredActivityUsed;
    }

    /**
     * Définit la valeur de la propriété postAdministeredActivityUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link AdministeredActivity }
     *     
     */
    public void setPostAdministeredActivityUsed(AdministeredActivity value) {
        this.postAdministeredActivityUsed = value;
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
     * Obtient la valeur de la propriété referenceOrganMassUsed.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceOrganMassUsed() {
        return referenceOrganMassUsed;
    }

    /**
     * Définit la valeur de la propriété referenceOrganMassUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceOrganMassUsed(String value) {
        this.referenceOrganMassUsed = value;
    }

    /**
     * Obtient la valeur de la propriété patientOrganMassUsed.
     * 
     * @return
     *     possible object is
     *     {@link PatientOrganMassUsedContainer }
     *     
     */
    public PatientOrganMassUsedContainer getPatientOrganMassUsed() {
        return patientOrganMassUsed;
    }

    /**
     * Définit la valeur de la propriété patientOrganMassUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientOrganMassUsedContainer }
     *     
     */
    public void setPatientOrganMassUsed(PatientOrganMassUsedContainer value) {
        this.patientOrganMassUsed = value;
    }

    /**
     * Obtient la valeur de la propriété meanAbsorbedDoseRateInROIProduced.
     * 
     * @return
     *     possible object is
     *     {@link MeanAbsorbedDoseRateInROIContainer }
     *     
     */
    public MeanAbsorbedDoseRateInROIContainer getMeanAbsorbedDoseRateInROIProduced() {
        return meanAbsorbedDoseRateInROIProduced;
    }

    /**
     * Définit la valeur de la propriété meanAbsorbedDoseRateInROIProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link MeanAbsorbedDoseRateInROIContainer }
     *     
     */
    public void setMeanAbsorbedDoseRateInROIProduced(MeanAbsorbedDoseRateInROIContainer value) {
        this.meanAbsorbedDoseRateInROIProduced = value;
    }

}
