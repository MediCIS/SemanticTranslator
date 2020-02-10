//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.06 à 05:03:32 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour AbsorbedDoseInVOI complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="AbsorbedDoseInVOI">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AbsorbedDoseInVOIValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="AbsorbedDoseUnit" type="{https://www.irdbb-medirad.com}AbsorbedDoseUnit"/>
 *         &lt;element name="AbsorbedDoseInVOIUncertainty" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="VOIIdentifierList" type="{https://www.irdbb-medirad.com}VOIIdentifierContainer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbsorbedDoseInVOI", propOrder = {
    "absorbedDoseInVOIValue",
    "absorbedDoseUnit",
    "absorbedDoseInVOIUncertainty",
    "voiIdentifierList"
})
public class AbsorbedDoseInVOI {

    @XmlElement(name = "AbsorbedDoseInVOIValue")
    protected float absorbedDoseInVOIValue;
    @XmlElement(name = "AbsorbedDoseUnit", required = true)
    @XmlSchemaType(name = "string")
    protected AbsorbedDoseUnit absorbedDoseUnit;
    @XmlElement(name = "AbsorbedDoseInVOIUncertainty")
    protected Float absorbedDoseInVOIUncertainty;
    @XmlElement(name = "VOIIdentifierList", required = true)
    protected VOIIdentifierContainer voiIdentifierList;

    /**
     * Obtient la valeur de la propriété absorbedDoseInVOIValue.
     * 
     */
    public float getAbsorbedDoseInVOIValue() {
        return absorbedDoseInVOIValue;
    }

    /**
     * Définit la valeur de la propriété absorbedDoseInVOIValue.
     * 
     */
    public void setAbsorbedDoseInVOIValue(float value) {
        this.absorbedDoseInVOIValue = value;
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
     * Obtient la valeur de la propriété absorbedDoseInVOIUncertainty.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getAbsorbedDoseInVOIUncertainty() {
        return absorbedDoseInVOIUncertainty;
    }

    /**
     * Définit la valeur de la propriété absorbedDoseInVOIUncertainty.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setAbsorbedDoseInVOIUncertainty(Float value) {
        this.absorbedDoseInVOIUncertainty = value;
    }

    /**
     * Obtient la valeur de la propriété voiIdentifierList.
     * 
     * @return
     *     possible object is
     *     {@link VOIIdentifierContainer }
     *     
     */
    public VOIIdentifierContainer getVOIIdentifierList() {
        return voiIdentifierList;
    }

    /**
     * Définit la valeur de la propriété voiIdentifierList.
     * 
     * @param value
     *     allowed object is
     *     {@link VOIIdentifierContainer }
     *     
     */
    public void setVOIIdentifierList(VOIIdentifierContainer value) {
        this.voiIdentifierList = value;
    }

}
