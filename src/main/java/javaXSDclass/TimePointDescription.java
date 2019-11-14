//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.10.24 à 03:08:52 PM CEST 
//


package javaXSDclass;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TimePointDescription complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TimePointDescription">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TimePointCategory">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="168h plus or minus 24h post RAIT timepoint"/>
 *               &lt;enumeration value="96h plus or minus 12h post RAIT timepoint"/>
 *               &lt;enumeration value="72h plus or minus 12h post RAIT timepoint"/>
 *               &lt;enumeration value="48h plus or minus 4h post RAIT timepoint"/>
 *               &lt;enumeration value="24h plus or minus 4h post RAIT timepoint"/>
 *               &lt;enumeration value="6h plus or minus 2h post RAIT timepoint"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="TimePointIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TimePointDistanceFromReferenceEventValue" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="TimePointDistanceFromReferenceEventUnit">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="minutes"/>
 *               &lt;enumeration value="hours"/>
 *               &lt;enumeration value="days"/>
 *               &lt;enumeration value="months"/>
 *               &lt;enumeration value="years"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimePointDescription", propOrder = {
    "timePointCategory",
    "timePointIdentifier",
    "timePointDistanceFromReferenceEventValue",
    "timePointDistanceFromReferenceEventUnit"
})
public class TimePointDescription {

    @XmlElement(name = "TimePointCategory", required = true)
    protected String timePointCategory;
    @XmlElement(name = "TimePointIdentifier", required = true)
    protected String timePointIdentifier;
    @XmlElement(name = "TimePointDistanceFromReferenceEventValue", required = true)
    protected BigInteger timePointDistanceFromReferenceEventValue;
    @XmlElement(name = "TimePointDistanceFromReferenceEventUnit", required = true)
    protected String timePointDistanceFromReferenceEventUnit;

    /**
     * Obtient la valeur de la propriété timePointCategory.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimePointCategory() {
        return timePointCategory;
    }

    /**
     * Définit la valeur de la propriété timePointCategory.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimePointCategory(String value) {
        this.timePointCategory = value;
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

    /**
     * Obtient la valeur de la propriété timePointDistanceFromReferenceEventValue.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTimePointDistanceFromReferenceEventValue() {
        return timePointDistanceFromReferenceEventValue;
    }

    /**
     * Définit la valeur de la propriété timePointDistanceFromReferenceEventValue.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTimePointDistanceFromReferenceEventValue(BigInteger value) {
        this.timePointDistanceFromReferenceEventValue = value;
    }

    /**
     * Obtient la valeur de la propriété timePointDistanceFromReferenceEventUnit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimePointDistanceFromReferenceEventUnit() {
        return timePointDistanceFromReferenceEventUnit;
    }

    /**
     * Définit la valeur de la propriété timePointDistanceFromReferenceEventUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimePointDistanceFromReferenceEventUnit(String value) {
        this.timePointDistanceFromReferenceEventUnit = value;
    }

}
