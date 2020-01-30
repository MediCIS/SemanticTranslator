//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.01.28 à 04:59:07 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour MeanAbsorbedDoseRateInROI complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="MeanAbsorbedDoseRateInROI">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MeanAbsorbedDoseRateInROIValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="MeanAbsorbedDoseRateInROIUnit" type="{https://www.irdbb-medirad.com}MeanAbsorbedDoseRateInROIUnit"/>
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
@XmlType(name = "MeanAbsorbedDoseRateInROI", propOrder = {
    "meanAbsorbedDoseRateInROIValue",
    "meanAbsorbedDoseRateInROIUnit",
    "roiIdentifier"
})
public class MeanAbsorbedDoseRateInROI {

    @XmlElement(name = "MeanAbsorbedDoseRateInROIValue")
    protected float meanAbsorbedDoseRateInROIValue;
    @XmlElement(name = "MeanAbsorbedDoseRateInROIUnit", required = true)
    @XmlSchemaType(name = "string")
    protected MeanAbsorbedDoseRateInROIUnit meanAbsorbedDoseRateInROIUnit;
    @XmlElement(name = "ROIIdentifier", required = true)
    protected String roiIdentifier;

    /**
     * Obtient la valeur de la propriété meanAbsorbedDoseRateInROIValue.
     * 
     */
    public float getMeanAbsorbedDoseRateInROIValue() {
        return meanAbsorbedDoseRateInROIValue;
    }

    /**
     * Définit la valeur de la propriété meanAbsorbedDoseRateInROIValue.
     * 
     */
    public void setMeanAbsorbedDoseRateInROIValue(float value) {
        this.meanAbsorbedDoseRateInROIValue = value;
    }

    /**
     * Obtient la valeur de la propriété meanAbsorbedDoseRateInROIUnit.
     * 
     * @return
     *     possible object is
     *     {@link MeanAbsorbedDoseRateInROIUnit }
     *     
     */
    public MeanAbsorbedDoseRateInROIUnit getMeanAbsorbedDoseRateInROIUnit() {
        return meanAbsorbedDoseRateInROIUnit;
    }

    /**
     * Définit la valeur de la propriété meanAbsorbedDoseRateInROIUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link MeanAbsorbedDoseRateInROIUnit }
     *     
     */
    public void setMeanAbsorbedDoseRateInROIUnit(MeanAbsorbedDoseRateInROIUnit value) {
        this.meanAbsorbedDoseRateInROIUnit = value;
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
