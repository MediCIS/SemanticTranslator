//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.04 à 12:01:52 PM CET 
//


package javaXSDclass;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="TimeUnit" type="{https://www.irdbb-medirad.com}TimeUnit"/>
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
@XmlType(name = "TimeIntegratedActivityCoefficientPerVOI", propOrder = {
    "timeIntegratedActivityCoefficientPerVOIValue",
    "timeUnit",
    "voiIdentifier"
})
public class TimeIntegratedActivityCoefficientPerVOI {

    @XmlElement(name = "TimeIntegratedActivityCoefficientPerVOIValue")
    protected float timeIntegratedActivityCoefficientPerVOIValue;
    @XmlElement(name = "TimeUnit", required = true)
    @XmlSchemaType(name = "string")
    protected TimeUnit timeUnit;
    @XmlElement(name = "VOIIdentifier", required = true)
    protected BigInteger voiIdentifier;

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
     * Obtient la valeur de la propriété timeUnit.
     * 
     * @return
     *     possible object is
     *     {@link TimeUnit }
     *     
     */
    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    /**
     * Définit la valeur de la propriété timeUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeUnit }
     *     
     */
    public void setTimeUnit(TimeUnit value) {
        this.timeUnit = value;
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
