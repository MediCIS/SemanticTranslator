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
 * <p>Classe Java pour TimeIntegratedActivityPerROI complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TimeIntegratedActivityPerROI">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TimeIntegratedActivityPerROIValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="TimeIntegratedActivityPerROIUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "TimeIntegratedActivityPerROI", propOrder = {
    "timeIntegratedActivityPerROIValue",
    "timeIntegratedActivityPerROIUnit",
    "roiIdentifier"
})
public class TimeIntegratedActivityPerROI {

    @XmlElement(name = "TimeIntegratedActivityPerROIValue")
    protected float timeIntegratedActivityPerROIValue;
    @XmlElement(name = "TimeIntegratedActivityPerROIUnit", required = true)
    protected String timeIntegratedActivityPerROIUnit;
    @XmlElement(name = "ROIIdentifier", required = true)
    protected String roiIdentifier;

    /**
     * Obtient la valeur de la propriété timeIntegratedActivityPerROIValue.
     * 
     */
    public float getTimeIntegratedActivityPerROIValue() {
        return timeIntegratedActivityPerROIValue;
    }

    /**
     * Définit la valeur de la propriété timeIntegratedActivityPerROIValue.
     * 
     */
    public void setTimeIntegratedActivityPerROIValue(float value) {
        this.timeIntegratedActivityPerROIValue = value;
    }

    /**
     * Obtient la valeur de la propriété timeIntegratedActivityPerROIUnit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeIntegratedActivityPerROIUnit() {
        return timeIntegratedActivityPerROIUnit;
    }

    /**
     * Définit la valeur de la propriété timeIntegratedActivityPerROIUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeIntegratedActivityPerROIUnit(String value) {
        this.timeIntegratedActivityPerROIUnit = value;
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
