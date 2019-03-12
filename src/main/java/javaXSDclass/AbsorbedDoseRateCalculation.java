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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour AbsorbedDoseRateCalculation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="AbsorbedDoseRateCalculation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="TimePointIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VOIIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="PreAdministeredActivityUsed" type="{https://www.irdbb-medirad.com}AdministeredActivity"/>
 *         &lt;element name="PostAdministeredActivityUsed" type="{https://www.irdbb-medirad.com}AdministeredActivity"/>
 *         &lt;element name="PatientOrganMassUsed" type="{https://www.irdbb-medirad.com}PatientOrganMass" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="MeanAbsorbedDoseRateInVOIProduced" type="{https://www.irdbb-medirad.com}MeanAbsorbedDoseRateInVOI" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbsorbedDoseRateCalculation", propOrder = {
    "processExecutionContext",
    "timePointIdentifierUsed",
    "voiIdentifierUsed",
    "preAdministeredActivityUsed",
    "postAdministeredActivityUsed",
    "patientOrganMassUsed",
    "meanAbsorbedDoseRateInVOIProduced"
})
public class AbsorbedDoseRateCalculation {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "TimePointIdentifierUsed", required = true)
    protected String timePointIdentifierUsed;
    @XmlElement(name = "VOIIdentifierUsed", required = true)
    protected List<String> voiIdentifierUsed;
    @XmlElement(name = "PreAdministeredActivityUsed", required = true)
    protected AdministeredActivity preAdministeredActivityUsed;
    @XmlElement(name = "PostAdministeredActivityUsed", required = true)
    protected AdministeredActivity postAdministeredActivityUsed;
    @XmlElement(name = "PatientOrganMassUsed")
    protected List<PatientOrganMass> patientOrganMassUsed;
    @XmlElement(name = "MeanAbsorbedDoseRateInVOIProduced", required = true)
    protected List<MeanAbsorbedDoseRateInVOI> meanAbsorbedDoseRateInVOIProduced;

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
     * Gets the value of the voiIdentifierUsed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the voiIdentifierUsed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVOIIdentifierUsed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getVOIIdentifierUsed() {
        if (voiIdentifierUsed == null) {
            voiIdentifierUsed = new ArrayList<String>();
        }
        return this.voiIdentifierUsed;
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
     * Gets the value of the patientOrganMassUsed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the patientOrganMassUsed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPatientOrganMassUsed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PatientOrganMass }
     * 
     * 
     */
    public List<PatientOrganMass> getPatientOrganMassUsed() {
        if (patientOrganMassUsed == null) {
            patientOrganMassUsed = new ArrayList<PatientOrganMass>();
        }
        return this.patientOrganMassUsed;
    }

    /**
     * Gets the value of the meanAbsorbedDoseRateInVOIProduced property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the meanAbsorbedDoseRateInVOIProduced property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMeanAbsorbedDoseRateInVOIProduced().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MeanAbsorbedDoseRateInVOI }
     * 
     * 
     */
    public List<MeanAbsorbedDoseRateInVOI> getMeanAbsorbedDoseRateInVOIProduced() {
        if (meanAbsorbedDoseRateInVOIProduced == null) {
            meanAbsorbedDoseRateInVOIProduced = new ArrayList<MeanAbsorbedDoseRateInVOI>();
        }
        return this.meanAbsorbedDoseRateInVOIProduced;
    }

}
