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
 * <p>Classe Java pour CountsPerROIAtTimePoint complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CountsPerROIAtTimePoint">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CountsValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="CountsUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ROIIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "CountsPerROIAtTimePoint", propOrder = {
    "countsValue",
    "countsUnit",
    "roiIdentifier",
    "timePointIdentifier"
})
public class CountsPerROIAtTimePoint {

    @XmlElement(name = "CountsValue")
    protected float countsValue;
    @XmlElement(name = "CountsUnit", required = true)
    protected String countsUnit;
    @XmlElement(name = "ROIIdentifier", required = true)
    protected String roiIdentifier;
    @XmlElement(name = "TimePointIdentifier", required = true)
    protected String timePointIdentifier;

    /**
     * Obtient la valeur de la propriété countsValue.
     * 
     */
    public float getCountsValue() {
        return countsValue;
    }

    /**
     * Définit la valeur de la propriété countsValue.
     * 
     */
    public void setCountsValue(float value) {
        this.countsValue = value;
    }

    /**
     * Obtient la valeur de la propriété countsUnit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountsUnit() {
        return countsUnit;
    }

    /**
     * Définit la valeur de la propriété countsUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountsUnit(String value) {
        this.countsUnit = value;
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
