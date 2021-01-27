//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.07.30 à 02:06:37 PM CEST 
//


package javaXSDclass;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour DataActivityPerVOIAtTimePoint complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DataActivityPerVOIAtTimePoint">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DataActivityValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="ActivityUnit" type="{https://www.irdbb-medirad.com}ActivityUnit"/>
 *         &lt;element name="VOIIdentifier" type="{http://www.w3.org/2001/XMLSchema}integer"/>
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
@XmlType(name = "DataActivityPerVOIAtTimePoint", propOrder = {
    "dataActivityValue",
    "activityUnit",
    "voiIdentifier",
    "timePointIdentifier"
})
public class DataActivityPerVOIAtTimePoint {

    @XmlElement(name = "DataActivityValue")
    protected float dataActivityValue;
    @XmlElement(name = "ActivityUnit", required = true)
    @XmlSchemaType(name = "string")
    protected ActivityUnit activityUnit;
    @XmlElement(name = "VOIIdentifier", required = true)
    protected BigInteger voiIdentifier;
    @XmlElement(name = "TimePointIdentifier", required = true)
    protected String timePointIdentifier;

    /**
     * Obtient la valeur de la propriété dataActivityValue.
     * 
     */
    public float getDataActivityValue() {
        return dataActivityValue;
    }

    /**
     * Définit la valeur de la propriété dataActivityValue.
     * 
     */
    public void setDataActivityValue(float value) {
        this.dataActivityValue = value;
    }

    /**
     * Obtient la valeur de la propriété activityUnit.
     * 
     * @return
     *     possible object is
     *     {@link ActivityUnit }
     *     
     */
    public ActivityUnit getActivityUnit() {
        return activityUnit;
    }

    /**
     * Définit la valeur de la propriété activityUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivityUnit }
     *     
     */
    public void setActivityUnit(ActivityUnit value) {
        this.activityUnit = value;
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
