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
 *         &lt;element name="MeanAbsorbedDoseInVOIUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "MeanAbsorbedDoseInVOI", propOrder = {
    "meanAbsorbedDoseInVOIValue",
    "meanAbsorbedDoseInVOIUnit",
    "voiIdentifier"
})
public class MeanAbsorbedDoseInVOI {

    @XmlElement(name = "MeanAbsorbedDoseInVOIValue")
    protected float meanAbsorbedDoseInVOIValue;
    @XmlElement(name = "MeanAbsorbedDoseInVOIUnit", required = true)
    protected String meanAbsorbedDoseInVOIUnit;
    @XmlElement(name = "VOIIdentifier", required = true)
    protected String voiIdentifier;

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
     * Obtient la valeur de la propriété meanAbsorbedDoseInVOIUnit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMeanAbsorbedDoseInVOIUnit() {
        return meanAbsorbedDoseInVOIUnit;
    }

    /**
     * Définit la valeur de la propriété meanAbsorbedDoseInVOIUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMeanAbsorbedDoseInVOIUnit(String value) {
        this.meanAbsorbedDoseInVOIUnit = value;
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
