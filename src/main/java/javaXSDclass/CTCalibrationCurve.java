//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.09.16 à 10:29:38 AM CEST 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CTCalibrationCurve complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CTCalibrationCurve">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CalibrationFactorValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="CalibrationFactorUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CTCalibrationCurve", propOrder = {
    "calibrationFactorValue",
    "calibrationFactorUnit"
})
public class CTCalibrationCurve {

    @XmlElement(name = "CalibrationFactorValue")
    protected float calibrationFactorValue;
    @XmlElement(name = "CalibrationFactorUnit", required = true)
    protected String calibrationFactorUnit;

    /**
     * Obtient la valeur de la propriété calibrationFactorValue.
     * 
     */
    public float getCalibrationFactorValue() {
        return calibrationFactorValue;
    }

    /**
     * Définit la valeur de la propriété calibrationFactorValue.
     * 
     */
    public void setCalibrationFactorValue(float value) {
        this.calibrationFactorValue = value;
    }

    /**
     * Obtient la valeur de la propriété calibrationFactorUnit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalibrationFactorUnit() {
        return calibrationFactorUnit;
    }

    /**
     * Définit la valeur de la propriété calibrationFactorUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalibrationFactorUnit(String value) {
        this.calibrationFactorUnit = value;
    }

}
