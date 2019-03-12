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
 * <p>Classe Java pour CalibrationCoefficient complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CalibrationCoefficient">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CalibrationCoefficientValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="CalibrationCoefficientUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ReferenceCalibrationDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Isotope">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="radium223"/>
 *               &lt;enumeration value="iodine131"/>
 *               &lt;enumeration value="lutetium177"/>
 *               &lt;enumeration value="yttrium90"/>
 *               &lt;enumeration value="radium223"/>
 *               &lt;enumeration value="rhenium188"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalibrationCoefficient", propOrder = {
    "calibrationCoefficientValue",
    "calibrationCoefficientUnit",
    "referenceCalibrationDate",
    "isotope"
})
public class CalibrationCoefficient {

    @XmlElement(name = "CalibrationCoefficientValue")
    protected float calibrationCoefficientValue;
    @XmlElement(name = "CalibrationCoefficientUnit", required = true)
    protected String calibrationCoefficientUnit;
    @XmlElement(name = "ReferenceCalibrationDate", required = true)
    protected String referenceCalibrationDate;
    @XmlElement(name = "Isotope", required = true)
    protected String isotope;

    /**
     * Obtient la valeur de la propriété calibrationCoefficientValue.
     * 
     */
    public float getCalibrationCoefficientValue() {
        return calibrationCoefficientValue;
    }

    /**
     * Définit la valeur de la propriété calibrationCoefficientValue.
     * 
     */
    public void setCalibrationCoefficientValue(float value) {
        this.calibrationCoefficientValue = value;
    }

    /**
     * Obtient la valeur de la propriété calibrationCoefficientUnit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalibrationCoefficientUnit() {
        return calibrationCoefficientUnit;
    }

    /**
     * Définit la valeur de la propriété calibrationCoefficientUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalibrationCoefficientUnit(String value) {
        this.calibrationCoefficientUnit = value;
    }

    /**
     * Obtient la valeur de la propriété referenceCalibrationDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceCalibrationDate() {
        return referenceCalibrationDate;
    }

    /**
     * Définit la valeur de la propriété referenceCalibrationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceCalibrationDate(String value) {
        this.referenceCalibrationDate = value;
    }

    /**
     * Obtient la valeur de la propriété isotope.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsotope() {
        return isotope;
    }

    /**
     * Définit la valeur de la propriété isotope.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsotope(String value) {
        this.isotope = value;
    }

}
