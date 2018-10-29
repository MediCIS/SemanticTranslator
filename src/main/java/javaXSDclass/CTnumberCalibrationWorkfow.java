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
 * <p>Classe Java pour CTnumberCalibrationWorkfow complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CTnumberCalibrationWorkfow">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CTAcqForCTnumberCalibrationCurve" type="{https://www.irdbb-medirad.com}CTacqForCTnumberCalibrationCurve"/>
 *         &lt;element name="CalculationofCTnumberCalibrationcurve" type="{https://www.irdbb-medirad.com}CalculationOfCTnumberCalibrationCurve"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CTnumberCalibrationWorkfow", propOrder = {
    "ctAcqForCTnumberCalibrationCurve",
    "calculationofCTnumberCalibrationcurve"
})
public class CTnumberCalibrationWorkfow {

    @XmlElement(name = "CTAcqForCTnumberCalibrationCurve", required = true)
    protected CTacqForCTnumberCalibrationCurve ctAcqForCTnumberCalibrationCurve;
    @XmlElement(name = "CalculationofCTnumberCalibrationcurve", required = true)
    protected CalculationOfCTnumberCalibrationCurve calculationofCTnumberCalibrationcurve;

    /**
     * Obtient la valeur de la propriété ctAcqForCTnumberCalibrationCurve.
     * 
     * @return
     *     possible object is
     *     {@link CTacqForCTnumberCalibrationCurve }
     *     
     */
    public CTacqForCTnumberCalibrationCurve getCTAcqForCTnumberCalibrationCurve() {
        return ctAcqForCTnumberCalibrationCurve;
    }

    /**
     * Définit la valeur de la propriété ctAcqForCTnumberCalibrationCurve.
     * 
     * @param value
     *     allowed object is
     *     {@link CTacqForCTnumberCalibrationCurve }
     *     
     */
    public void setCTAcqForCTnumberCalibrationCurve(CTacqForCTnumberCalibrationCurve value) {
        this.ctAcqForCTnumberCalibrationCurve = value;
    }

    /**
     * Obtient la valeur de la propriété calculationofCTnumberCalibrationcurve.
     * 
     * @return
     *     possible object is
     *     {@link CalculationOfCTnumberCalibrationCurve }
     *     
     */
    public CalculationOfCTnumberCalibrationCurve getCalculationofCTnumberCalibrationcurve() {
        return calculationofCTnumberCalibrationcurve;
    }

    /**
     * Définit la valeur de la propriété calculationofCTnumberCalibrationcurve.
     * 
     * @param value
     *     allowed object is
     *     {@link CalculationOfCTnumberCalibrationCurve }
     *     
     */
    public void setCalculationofCTnumberCalibrationcurve(CalculationOfCTnumberCalibrationCurve value) {
        this.calculationofCTnumberCalibrationcurve = value;
    }

}
