//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2018.10.16 à 03:58:01 PM CEST 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CalculationOfCTnumberCalibrationCurve complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CalculationOfCTnumberCalibrationCurve">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CTnumberCalibrationCurve" type="{https://www.irdbb-medirad.com}complexUnknown"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalculationOfCTnumberCalibrationCurve", propOrder = {
    "cTnumberCalibrationCurve"
})
public class CalculationOfCTnumberCalibrationCurve {

    @XmlElement(name = "CTnumberCalibrationCurve", required = true)
    protected ComplexUnknown cTnumberCalibrationCurve;

    /**
     * Obtient la valeur de la propriété cTnumberCalibrationCurve.
     * 
     * @return
     *     possible object is
     *     {@link ComplexUnknown }
     *     
     */
    public ComplexUnknown getCTnumberCalibrationCurve() {
        return cTnumberCalibrationCurve;
    }

    /**
     * Définit la valeur de la propriété cTnumberCalibrationCurve.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexUnknown }
     *     
     */
    public void setCTnumberCalibrationCurve(ComplexUnknown value) {
        this.cTnumberCalibrationCurve = value;
    }

}
