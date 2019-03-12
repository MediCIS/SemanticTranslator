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
 * <p>Classe Java pour TimeIntegratedActivityCoefficientPerROI complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TimeIntegratedActivityCoefficientPerROI">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TimeIntegratedActivityCoefficientPerROIValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="TimeIntegratedActivityCoefficientPerROIUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ROIIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeIntegratedActivityCoefficientPerROI", propOrder = {
    "timeIntegratedActivityCoefficientPerROIValue",
    "timeIntegratedActivityCoefficientPerROIUnit",
    "roiIdentifier"
})
public class TimeIntegratedActivityCoefficientPerROI {

    @XmlElement(name = "TimeIntegratedActivityCoefficientPerROIValue")
    protected float timeIntegratedActivityCoefficientPerROIValue;
    @XmlElement(name = "TimeIntegratedActivityCoefficientPerROIUnit", required = true)
    protected String timeIntegratedActivityCoefficientPerROIUnit;
    @XmlElement(name = "ROIIdentifier", required = true)
    protected String roiIdentifier;

    /**
     * Obtient la valeur de la propriété timeIntegratedActivityCoefficientPerROIValue.
     * 
     */
    public float getTimeIntegratedActivityCoefficientPerROIValue() {
        return timeIntegratedActivityCoefficientPerROIValue;
    }

    /**
     * Définit la valeur de la propriété timeIntegratedActivityCoefficientPerROIValue.
     * 
     */
    public void setTimeIntegratedActivityCoefficientPerROIValue(float value) {
        this.timeIntegratedActivityCoefficientPerROIValue = value;
    }

    /**
     * Obtient la valeur de la propriété timeIntegratedActivityCoefficientPerROIUnit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeIntegratedActivityCoefficientPerROIUnit() {
        return timeIntegratedActivityCoefficientPerROIUnit;
    }

    /**
     * Définit la valeur de la propriété timeIntegratedActivityCoefficientPerROIUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeIntegratedActivityCoefficientPerROIUnit(String value) {
        this.timeIntegratedActivityCoefficientPerROIUnit = value;
    }

    /**
     * Obtient la valeur de la propriété roiIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getROIIdentifier() {
        return roiIdentifier;
    }

    /**
     * Définit la valeur de la propriété roiIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setROIIdentifier(String value) {
        this.roiIdentifier = value;
    }

}
