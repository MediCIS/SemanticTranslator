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
 *         &lt;element name="MeanAbsorbedDoseInROIUnit" type="{https://www.irdbb-medirad.com}MeanAbsorbedDoseInROIUnit"/>
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
    "meanAbsorbedDoseInROIUnit",
    "roiIdentifier"
})
public class MeanAbsorbedDoseInROI {

    @XmlElement(name = "MeanAbsorbedDoseInROIValue")
    protected float meanAbsorbedDoseInROIValue;
    @XmlElement(name = "MeanAbsorbedDoseInROIUnit", required = true)
    @XmlSchemaType(name = "string")
    protected MeanAbsorbedDoseInROIUnit meanAbsorbedDoseInROIUnit;
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
     * Obtient la valeur de la propriété meanAbsorbedDoseInROIUnit.
     * 
     * @return
     *     possible object is
     *     {@link MeanAbsorbedDoseInROIUnit }
     *     
     */
    public MeanAbsorbedDoseInROIUnit getMeanAbsorbedDoseInROIUnit() {
        return meanAbsorbedDoseInROIUnit;
    }

    /**
     * Définit la valeur de la propriété meanAbsorbedDoseInROIUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link MeanAbsorbedDoseInROIUnit }
     *     
     */
    public void setMeanAbsorbedDoseInROIUnit(MeanAbsorbedDoseInROIUnit value) {
        this.meanAbsorbedDoseInROIUnit = value;
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
