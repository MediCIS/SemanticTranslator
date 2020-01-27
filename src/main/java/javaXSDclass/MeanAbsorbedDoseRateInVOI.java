//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.01.24 à 06:05:31 PM CET 
//


package javaXSDclass;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour MeanAbsorbedDoseRateInVOI complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="MeanAbsorbedDoseRateInVOI">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MeanAbsorbedDoseRateInVOIValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="MeanAbsorbedDoseRateInVOIUnit" type="{https://www.irdbb-medirad.com}MeanAbsorbedDoseRateInVOIUnit"/>
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
@XmlType(name = "MeanAbsorbedDoseRateInVOI", propOrder = {
    "meanAbsorbedDoseRateInVOIValue",
    "meanAbsorbedDoseRateInVOIUnit",
    "voiIdentifier"
})
public class MeanAbsorbedDoseRateInVOI {

    @XmlElement(name = "MeanAbsorbedDoseRateInVOIValue")
    protected float meanAbsorbedDoseRateInVOIValue;
    @XmlElement(name = "MeanAbsorbedDoseRateInVOIUnit", required = true)
    @XmlSchemaType(name = "string")
    protected MeanAbsorbedDoseRateInVOIUnit meanAbsorbedDoseRateInVOIUnit;
    @XmlElement(name = "VOIIdentifier", required = true)
    protected BigInteger voiIdentifier;

    /**
     * Obtient la valeur de la propriété meanAbsorbedDoseRateInVOIValue.
     * 
     */
    public float getMeanAbsorbedDoseRateInVOIValue() {
        return meanAbsorbedDoseRateInVOIValue;
    }

    /**
     * Définit la valeur de la propriété meanAbsorbedDoseRateInVOIValue.
     * 
     */
    public void setMeanAbsorbedDoseRateInVOIValue(float value) {
        this.meanAbsorbedDoseRateInVOIValue = value;
    }

    /**
     * Obtient la valeur de la propriété meanAbsorbedDoseRateInVOIUnit.
     * 
     * @return
     *     possible object is
     *     {@link MeanAbsorbedDoseRateInVOIUnit }
     *     
     */
    public MeanAbsorbedDoseRateInVOIUnit getMeanAbsorbedDoseRateInVOIUnit() {
        return meanAbsorbedDoseRateInVOIUnit;
    }

    /**
     * Définit la valeur de la propriété meanAbsorbedDoseRateInVOIUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link MeanAbsorbedDoseRateInVOIUnit }
     *     
     */
    public void setMeanAbsorbedDoseRateInVOIUnit(MeanAbsorbedDoseRateInVOIUnit value) {
        this.meanAbsorbedDoseRateInVOIUnit = value;
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
