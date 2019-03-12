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
 * <p>Classe Java pour AbsorbedDoseCalculation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="AbsorbedDoseCalculation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="ROIIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="OrganSValueUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PatientOrganMassUsed" type="{https://www.irdbb-medirad.com}PatientOrganMass" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="MeanAbsorbedDoseInVOIProduced" type="{https://www.irdbb-medirad.com}MeanAbsorbedDoseInVOI" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbsorbedDoseCalculation", propOrder = {
    "processExecutionContext",
    "roiIdentifierUsed",
    "organSValueUsed",
    "patientOrganMassUsed",
    "meanAbsorbedDoseInVOIProduced"
})
public class AbsorbedDoseCalculation {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "ROIIdentifierUsed", required = true)
    protected List<String> roiIdentifierUsed;
    @XmlElement(name = "OrganSValueUsed", required = true)
    protected String organSValueUsed;
    @XmlElement(name = "PatientOrganMassUsed")
    protected List<PatientOrganMass> patientOrganMassUsed;
    @XmlElement(name = "MeanAbsorbedDoseInVOIProduced", required = true)
    protected List<MeanAbsorbedDoseInVOI> meanAbsorbedDoseInVOIProduced;

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
     * Gets the value of the meanAbsorbedDoseInVOIProduced property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the meanAbsorbedDoseInVOIProduced property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMeanAbsorbedDoseInVOIProduced().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MeanAbsorbedDoseInVOI }
     * 
     * 
     */
    public List<MeanAbsorbedDoseInVOI> getMeanAbsorbedDoseInVOIProduced() {
        if (meanAbsorbedDoseInVOIProduced == null) {
            meanAbsorbedDoseInVOIProduced = new ArrayList<MeanAbsorbedDoseInVOI>();
        }
        return this.meanAbsorbedDoseInVOIProduced;
    }

}
