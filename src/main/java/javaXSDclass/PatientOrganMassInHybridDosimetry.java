//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.06 à 05:03:32 PM CET 
//


package javaXSDclass;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour PatientOrganMassInHybridDosimetry complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PatientOrganMassInHybridDosimetry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PatientOrganMassValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="MassUnit" type="{https://www.irdbb-medirad.com}MassUnit"/>
 *         &lt;element name="VOIIdentifier" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PatientOrganMassInHybridDosimetry", propOrder = {
    "patientOrganMassValue",
    "massUnit",
    "voiIdentifier"
})
public class PatientOrganMassInHybridDosimetry {

    @XmlElement(name = "PatientOrganMassValue")
    protected float patientOrganMassValue;
    @XmlElement(name = "MassUnit", required = true)
    @XmlSchemaType(name = "string")
    protected MassUnit massUnit;
    @XmlElement(name = "VOIIdentifier", required = true)
    protected BigInteger voiIdentifier;

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
     * Obtient la valeur de la propriété massUnit.
     * 
     * @return
     *     possible object is
     *     {@link MassUnit }
     *     
     */
    public MassUnit getMassUnit() {
        return massUnit;
    }

    /**
     * Définit la valeur de la propriété massUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link MassUnit }
     *     
     */
    public void setMassUnit(MassUnit value) {
        this.massUnit = value;
    }

    /**
     * Obtient la valeur de la propriété voiIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getVOIIdentifier() {
        return voiIdentifier;
    }

    /**
     * Définit la valeur de la propriété voiIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setVOIIdentifier(BigInteger value) {
        this.voiIdentifier = value;
    }

}
