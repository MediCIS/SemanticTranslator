//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.06 à 05:03:32 PM CET 
//


package javaXSDclass;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour MeanAbsorbedDoseInVOI complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="MeanAbsorbedDoseInVOI">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MeanAbsorbedDoseInVOIValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
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
@XmlType(name = "MeanAbsorbedDoseInVOI", propOrder = {
    "meanAbsorbedDoseInVOIValue",
    "absorbedDoseUnit",
    "voiIdentifier"
})
public class MeanAbsorbedDoseInVOI {

    @XmlElement(name = "MeanAbsorbedDoseInVOIValue")
    protected float meanAbsorbedDoseInVOIValue;
    @XmlElement(name = "AbsorbedDoseUnit", required = true)
    @XmlSchemaType(name = "string")
    protected AbsorbedDoseUnit absorbedDoseUnit;
    @XmlElement(name = "VOIIdentifier", required = true)
    protected BigInteger voiIdentifier;

    /**
     * Obtient la valeur de la propriété meanAbsorbedDoseInVOIValue.
     * 
     */
    public float getMeanAbsorbedDoseInVOIValue() {
        return meanAbsorbedDoseInVOIValue;
    }

    /**
     * Définit la valeur de la propriété meanAbsorbedDoseInVOIValue.
     * 
     */
    public void setMeanAbsorbedDoseInVOIValue(float value) {
        this.meanAbsorbedDoseInVOIValue = value;
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
