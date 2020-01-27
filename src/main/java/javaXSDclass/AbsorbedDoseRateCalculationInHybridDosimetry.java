//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.01.24 à 06:05:31 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour AbsorbedDoseRateCalculationInHybridDosimetry complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="AbsorbedDoseRateCalculationInHybridDosimetry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="TimePointIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VOIIdentifierUsed" type="{https://www.irdbb-medirad.com}VOIIdentifierContainer"/>
 *         &lt;element name="PreAdministeredActivityUsed" type="{https://www.irdbb-medirad.com}AdministeredActivity"/>
 *         &lt;element name="PostAdministeredActivityUsed" type="{https://www.irdbb-medirad.com}AdministeredActivity"/>
 *         &lt;element name="PatientOrganMassUsed" type="{https://www.irdbb-medirad.com}PatientOrganMassInHybridDosimetryContainer" minOccurs="0"/>
 *         &lt;element name="MeanAbsorbedDoseRateInVOIProduced" type="{https://www.irdbb-medirad.com}MeanAbsorbedDoseRateInVOIContainer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbsorbedDoseRateCalculationInHybridDosimetry", propOrder = {
    "processExecutionContext",
    "timePointIdentifierUsed",
    "voiIdentifierUsed",
    "preAdministeredActivityUsed",
    "postAdministeredActivityUsed",
    "patientOrganMassUsed",
    "meanAbsorbedDoseRateInVOIProduced"
})
public class AbsorbedDoseRateCalculationInHybridDosimetry {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "TimePointIdentifierUsed", required = true)
    protected String timePointIdentifierUsed;
    @XmlElement(name = "VOIIdentifierUsed", required = true)
    protected VOIIdentifierContainer voiIdentifierUsed;
    @XmlElement(name = "PreAdministeredActivityUsed", required = true)
    protected AdministeredActivity preAdministeredActivityUsed;
    @XmlElement(name = "PostAdministeredActivityUsed", required = true)
    protected AdministeredActivity postAdministeredActivityUsed;
    @XmlElement(name = "PatientOrganMassUsed")
    protected PatientOrganMassInHybridDosimetryContainer patientOrganMassUsed;
    @XmlElement(name = "MeanAbsorbedDoseRateInVOIProduced", required = true)
    protected MeanAbsorbedDoseRateInVOIContainer meanAbsorbedDoseRateInVOIProduced;

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
     * Obtient la valeur de la propriété voiIdentifierUsed.
     * 
     * @return
     *     possible object is
     *     {@link VOIIdentifierContainer }
     *     
     */
    public VOIIdentifierContainer getVOIIdentifierUsed() {
        return voiIdentifierUsed;
    }

    /**
     * Définit la valeur de la propriété voiIdentifierUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link VOIIdentifierContainer }
     *     
     */
    public void setVOIIdentifierUsed(VOIIdentifierContainer value) {
        this.voiIdentifierUsed = value;
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
     * Obtient la valeur de la propriété patientOrganMassUsed.
     * 
     * @return
     *     possible object is
     *     {@link PatientOrganMassInHybridDosimetryContainer }
     *     
     */
    public PatientOrganMassInHybridDosimetryContainer getPatientOrganMassUsed() {
        return patientOrganMassUsed;
    }

    /**
     * Définit la valeur de la propriété patientOrganMassUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientOrganMassInHybridDosimetryContainer }
     *     
     */
    public void setPatientOrganMassUsed(PatientOrganMassInHybridDosimetryContainer value) {
        this.patientOrganMassUsed = value;
    }

    /**
     * Obtient la valeur de la propriété meanAbsorbedDoseRateInVOIProduced.
     * 
     * @return
     *     possible object is
     *     {@link MeanAbsorbedDoseRateInVOIContainer }
     *     
     */
    public MeanAbsorbedDoseRateInVOIContainer getMeanAbsorbedDoseRateInVOIProduced() {
        return meanAbsorbedDoseRateInVOIProduced;
    }

    /**
     * Définit la valeur de la propriété meanAbsorbedDoseRateInVOIProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link MeanAbsorbedDoseRateInVOIContainer }
     *     
     */
    public void setMeanAbsorbedDoseRateInVOIProduced(MeanAbsorbedDoseRateInVOIContainer value) {
        this.meanAbsorbedDoseRateInVOIProduced = value;
    }

}
