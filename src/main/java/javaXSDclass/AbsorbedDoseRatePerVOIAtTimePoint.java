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
 * <p>Classe Java pour AbsorbedDoseRatePerVOIAtTimePoint complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="AbsorbedDoseRatePerVOIAtTimePoint">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AbsorbedDoseRateValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="AbsorbedDoseRateUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VOIIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TimePointIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbsorbedDoseRatePerVOIAtTimePoint", propOrder = {
    "absorbedDoseRateValue",
    "absorbedDoseRateUnit",
    "voiIdentifier",
    "timePointIdentifier"
})
public class AbsorbedDoseRatePerVOIAtTimePoint {

    @XmlElement(name = "AbsorbedDoseRateValue")
    protected float absorbedDoseRateValue;
    @XmlElement(name = "AbsorbedDoseRateUnit", required = true)
    protected String absorbedDoseRateUnit;
    @XmlElement(name = "VOIIdentifier", required = true)
    protected String voiIdentifier;
    @XmlElement(name = "TimePointIdentifier", required = true)
    protected String timePointIdentifier;

    /**
     * Obtient la valeur de la propriété absorbedDoseRateValue.
     * 
     */
    public float getAbsorbedDoseRateValue() {
        return absorbedDoseRateValue;
    }

    /**
     * Définit la valeur de la propriété absorbedDoseRateValue.
     * 
     */
    public void setAbsorbedDoseRateValue(float value) {
        this.absorbedDoseRateValue = value;
    }

    /**
     * Obtient la valeur de la propriété absorbedDoseRateUnit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbsorbedDoseRateUnit() {
        return absorbedDoseRateUnit;
    }

    /**
     * Définit la valeur de la propriété absorbedDoseRateUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbsorbedDoseRateUnit(String value) {
        this.absorbedDoseRateUnit = value;
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

    /**
     * Obtient la valeur de la propriété timePointIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimePointIdentifier() {
        return timePointIdentifier;
    }

    /**
     * Définit la valeur de la propriété timePointIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimePointIdentifier(String value) {
        this.timePointIdentifier = value;
    }

}
