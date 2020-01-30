//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.01.28 à 04:59:07 PM CET 
//


package javaXSDclass;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour AbsorbedDosePerVOIType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="AbsorbedDosePerVOIType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AbsorbedDoseCategory" type="{https://www.irdbb-medirad.com}AbsorbedDoseCategory"/>
 *         &lt;element name="StatisticalSubCategory" type="{https://www.irdbb-medirad.com}StatisticalSubCategory" minOccurs="0"/>
 *         &lt;element name="AbsorbedDoseValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AbsorbedDoseUnit" type="{https://www.irdbb-medirad.com}AbsorbedDoseUnit"/>
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
@XmlType(name = "AbsorbedDosePerVOIType", propOrder = {
    "absorbedDoseCategory",
    "statisticalSubCategory",
    "absorbedDoseValue",
    "absorbedDoseUnit",
    "voiIdentifier"
})
public class AbsorbedDosePerVOIType {

    @XmlElement(name = "AbsorbedDoseCategory", required = true)
    @XmlSchemaType(name = "string")
    protected AbsorbedDoseCategory absorbedDoseCategory;
    @XmlElement(name = "StatisticalSubCategory")
    @XmlSchemaType(name = "string")
    protected StatisticalSubCategory statisticalSubCategory;
    @XmlElement(name = "AbsorbedDoseValue", required = true)
    protected String absorbedDoseValue;
    @XmlElement(name = "AbsorbedDoseUnit", required = true)
    @XmlSchemaType(name = "string")
    protected AbsorbedDoseUnit absorbedDoseUnit;
    @XmlElement(name = "VOIIdentifier", required = true)
    protected BigInteger voiIdentifier;

    /**
     * Obtient la valeur de la propriété absorbedDoseCategory.
     * 
     * @return
     *     possible object is
     *     {@link AbsorbedDoseCategory }
     *     
     */
    public AbsorbedDoseCategory getAbsorbedDoseCategory() {
        return absorbedDoseCategory;
    }

    /**
     * Définit la valeur de la propriété absorbedDoseCategory.
     * 
     * @param value
     *     allowed object is
     *     {@link AbsorbedDoseCategory }
     *     
     */
    public void setAbsorbedDoseCategory(AbsorbedDoseCategory value) {
        this.absorbedDoseCategory = value;
    }

    /**
     * Obtient la valeur de la propriété statisticalSubCategory.
     * 
     * @return
     *     possible object is
     *     {@link StatisticalSubCategory }
     *     
     */
    public StatisticalSubCategory getStatisticalSubCategory() {
        return statisticalSubCategory;
    }

    /**
     * Définit la valeur de la propriété statisticalSubCategory.
     * 
     * @param value
     *     allowed object is
     *     {@link StatisticalSubCategory }
     *     
     */
    public void setStatisticalSubCategory(StatisticalSubCategory value) {
        this.statisticalSubCategory = value;
    }

    /**
     * Obtient la valeur de la propriété absorbedDoseValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbsorbedDoseValue() {
        return absorbedDoseValue;
    }

    /**
     * Définit la valeur de la propriété absorbedDoseValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbsorbedDoseValue(String value) {
        this.absorbedDoseValue = value;
    }

    /**
     * Obtient la valeur de la propriété absorbedDoseUnit.
     * 
     * @return
     *     possible object is
     *     {@link AbsorbedDoseUnit }
     *     
     */
    public AbsorbedDoseUnit getAbsorbedDoseUnit() {
        return absorbedDoseUnit;
    }

    /**
     * Définit la valeur de la propriété absorbedDoseUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link AbsorbedDoseUnit }
     *     
     */
    public void setAbsorbedDoseUnit(AbsorbedDoseUnit value) {
        this.absorbedDoseUnit = value;
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
