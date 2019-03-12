//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2018.12.11 à 04:47:30 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour PatientOrganMass complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PatientOrganMass">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PatientOrganMassValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="PatientOrganMassUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "PatientOrganMass", propOrder = {
    "patientOrganMassValue",
    "patientOrganMassUnit",
    "voiIdentifier"
})
public class PatientOrganMass {

    @XmlElement(name = "PatientOrganMassValue")
    protected float patientOrganMassValue;
    @XmlElement(name = "PatientOrganMassUnit", required = true)
    protected String patientOrganMassUnit;
    @XmlElement(name = "VOIIdentifier", required = true)
    protected String voiIdentifier;

    /**
     * Obtient la valeur de la propriété patientOrganMassValue.
     * 
     */
    public float getPatientOrganMassValue() {
        return patientOrganMassValue;
    }

    /**
     * Définit la valeur de la propriété patientOrganMassValue.
     * 
     */
    public void setPatientOrganMassValue(float value) {
        this.patientOrganMassValue = value;
    }

    /**
     * Obtient la valeur de la propriété patientOrganMassUnit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientOrganMassUnit() {
        return patientOrganMassUnit;
    }

    /**
     * Définit la valeur de la propriété patientOrganMassUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientOrganMassUnit(String value) {
        this.patientOrganMassUnit = value;
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
