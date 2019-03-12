//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2018.11.09 à 04:59:00 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TimeIntegratedActivityCoefficientPerVOI complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TimeIntegratedActivityCoefficientPerVOI">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TimeIntegratedActivityCoefficientPerVOIValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="TimeIntegratedActivityCoefficientPerVOIUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "TimeIntegratedActivityCoefficientPerVOI", propOrder = {
    "timeIntegratedActivityCoefficientPerVOIValue",
    "timeIntegratedActivityCoefficientPerVOIUnit",
    "voiIdentifier"
})
public class TimeIntegratedActivityCoefficientPerVOI {

    @XmlElement(name = "TimeIntegratedActivityCoefficientPerVOIValue")
    protected float timeIntegratedActivityCoefficientPerVOIValue;
    @XmlElement(name = "TimeIntegratedActivityCoefficientPerVOIUnit", required = true)
    protected String timeIntegratedActivityCoefficientPerVOIUnit;
    @XmlElement(name = "VOIIdentifier", required = true)
    protected String voiIdentifier;

    /**
     * Obtient la valeur de la propriété timeIntegratedActivityCoefficientPerVOIValue.
     * 
     */
    public float getTimeIntegratedActivityCoefficientPerVOIValue() {
        return timeIntegratedActivityCoefficientPerVOIValue;
    }

    /**
     * Définit la valeur de la propriété timeIntegratedActivityCoefficientPerVOIValue.
     * 
     */
    public void setTimeIntegratedActivityCoefficientPerVOIValue(float value) {
        this.timeIntegratedActivityCoefficientPerVOIValue = value;
    }

    /**
     * Obtient la valeur de la propriété timeIntegratedActivityCoefficientPerVOIUnit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeIntegratedActivityCoefficientPerVOIUnit() {
        return timeIntegratedActivityCoefficientPerVOIUnit;
    }

    /**
     * Définit la valeur de la propriété timeIntegratedActivityCoefficientPerVOIUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeIntegratedActivityCoefficientPerVOIUnit(String value) {
        this.timeIntegratedActivityCoefficientPerVOIUnit = value;
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
