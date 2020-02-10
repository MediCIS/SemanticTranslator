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
 * <p>Classe Java pour MeanAbsorbedDoseInROI complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="MeanAbsorbedDoseInROI">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MeanAbsorbedDoseInROIValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="AbsorbedDoseUnit" type="{https://www.irdbb-medirad.com}AbsorbedDoseUnit"/>
 *         &lt;element name="ROIIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MeanAbsorbedDoseInROI", propOrder = {
    "meanAbsorbedDoseInROIValue",
    "absorbedDoseUnit",
    "roiIdentifier"
})
public class MeanAbsorbedDoseInROI {

    @XmlElement(name = "MeanAbsorbedDoseInROIValue")
    protected float meanAbsorbedDoseInROIValue;
    @XmlElement(name = "AbsorbedDoseUnit", required = true)
    @XmlSchemaType(name = "string")
    protected AbsorbedDoseUnit absorbedDoseUnit;
    @XmlElement(name = "ROIIdentifier", required = true)
    protected String roiIdentifier;

    /**
     * Obtient la valeur de la propriété meanAbsorbedDoseInROIValue.
     * 
     */
    public float getMeanAbsorbedDoseInROIValue() {
        return meanAbsorbedDoseInROIValue;
    }

    /**
     * Définit la valeur de la propriété meanAbsorbedDoseInROIValue.
     * 
     */
    public void setMeanAbsorbedDoseInROIValue(float value) {
        this.meanAbsorbedDoseInROIValue = value;
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

}
