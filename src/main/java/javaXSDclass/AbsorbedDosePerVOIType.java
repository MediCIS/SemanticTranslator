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
 * <p>Classe Java pour AbsorbedDosePerVOIType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="AbsorbedDosePerVOIType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AbsorbedDoseCategory">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="mean absorbed dose normalized to CTDI free in air normalized to tube load"/>
 *               &lt;enumeration value="mean absorbed dose normalized to CTDI vol normalized to tube load"/>
 *               &lt;enumeration value="mean absorbed radiation dose"/>
 *               &lt;enumeration value="maximum absorbed radiation dose"/>
 *               &lt;enumeration value="minimum absorbed radiation dose"/>
 *               &lt;enumeration value="median absorbed radiation dose"/>
 *               &lt;enumeration value="mode absorbed radiation dose"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="StatisticalSubCategory" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="mean"/>
 *               &lt;enumeration value="maximum"/>
 *               &lt;enumeration value="minimum"/>
 *               &lt;enumeration value="median"/>
 *               &lt;enumeration value="mode"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="AbsorbedDoseValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AbsorbedDoseUnit">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="milligray per (milligray per (100 milliampere second))"/>
 *               &lt;enumeration value="milligray per (100 milliampere second)"/>
 *               &lt;enumeration value="milligray"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
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
@XmlType(name = "AbsorbedDosePerVOIType", propOrder = {
    "absorbedDoseCategory",
    "statisticalSubCategory",
    "absorbedDoseValue",
    "absorbedDoseUnit",
    "voiIdentifier"
})
public class AbsorbedDosePerVOIType {

    @XmlElement(name = "AbsorbedDoseCategory", required = true)
    protected String absorbedDoseCategory;
    @XmlElement(name = "StatisticalSubCategory")
    protected String statisticalSubCategory;
    @XmlElement(name = "AbsorbedDoseValue", required = true)
    protected String absorbedDoseValue;
    @XmlElement(name = "AbsorbedDoseUnit", required = true)
    protected String absorbedDoseUnit;
    @XmlElement(name = "VOIIdentifier", required = true)
    protected String voiIdentifier;

    /**
     * Obtient la valeur de la propriété absorbedDoseCategory.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbsorbedDoseCategory() {
        return absorbedDoseCategory;
    }

    /**
     * Définit la valeur de la propriété absorbedDoseCategory.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbsorbedDoseCategory(String value) {
        this.absorbedDoseCategory = value;
    }

    /**
     * Obtient la valeur de la propriété statisticalSubCategory.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatisticalSubCategory() {
        return statisticalSubCategory;
    }

    /**
     * Définit la valeur de la propriété statisticalSubCategory.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatisticalSubCategory(String value) {
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
     *     {@link String }
     *     
     */
    public String getAbsorbedDoseUnit() {
        return absorbedDoseUnit;
    }

    /**
     * Définit la valeur de la propriété absorbedDoseUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbsorbedDoseUnit(String value) {
        this.absorbedDoseUnit = value;
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
