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
 * <p>Classe Java pour SPECTCTcalibrationworkflow complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SPECTCTcalibrationworkflow">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CTnumberCalibrationWorkfow" type="{https://www.irdbb-medirad.com}CTnumberCalibrationWorkfow" minOccurs="0"/>
 *         &lt;element name="SPECTacqCTAcqAndReconstruction" type="{https://www.irdbb-medirad.com}SPECTaccqCTacqAndReconstruction"/>
 *         &lt;element name="SPECTreconstruction" type="{https://www.irdbb-medirad.com}SPECTreconstruction"/>
 *         &lt;element name="CTsegmentation" type="{https://www.irdbb-medirad.com}CTsegmentation"/>
 *         &lt;element name="SPECTsensitivityCalculation" type="{https://www.irdbb-medirad.com}SPECTsensitivityCalculation"/>
 *         &lt;element name="SPECTrecoveryCurveCalculation" type="{https://www.irdbb-medirad.com}SPECTrecoveryCurveCalculation" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SPECTCTcalibrationworkflow", propOrder = {
    "cTnumberCalibrationWorkfow",
    "specTacqCTAcqAndReconstruction",
    "specTreconstruction",
    "cTsegmentation",
    "specTsensitivityCalculation",
    "specTrecoveryCurveCalculation"
})
public class SPECTCTcalibrationworkflow {

    @XmlElement(name = "CTnumberCalibrationWorkfow")
    protected CTnumberCalibrationWorkfow cTnumberCalibrationWorkfow;
    @XmlElement(name = "SPECTacqCTAcqAndReconstruction", required = true)
    protected SPECTaccqCTacqAndReconstruction specTacqCTAcqAndReconstruction;
    @XmlElement(name = "SPECTreconstruction", required = true)
    protected SPECTreconstruction specTreconstruction;
    @XmlElement(name = "CTsegmentation", required = true)
    protected CTsegmentation cTsegmentation;
    @XmlElement(name = "SPECTsensitivityCalculation", required = true)
    protected SPECTsensitivityCalculation specTsensitivityCalculation;
    @XmlElement(name = "SPECTrecoveryCurveCalculation")
    protected SPECTrecoveryCurveCalculation specTrecoveryCurveCalculation;

    /**
     * Obtient la valeur de la propriété cTnumberCalibrationWorkfow.
     * 
     * @return
     *     possible object is
     *     {@link CTnumberCalibrationWorkfow }
     *     
     */
    public CTnumberCalibrationWorkfow getCTnumberCalibrationWorkfow() {
        return cTnumberCalibrationWorkfow;
    }

    /**
     * Définit la valeur de la propriété cTnumberCalibrationWorkfow.
     * 
     * @param value
     *     allowed object is
     *     {@link CTnumberCalibrationWorkfow }
     *     
     */
    public void setCTnumberCalibrationWorkfow(CTnumberCalibrationWorkfow value) {
        this.cTnumberCalibrationWorkfow = value;
    }

    /**
     * Obtient la valeur de la propriété specTacqCTAcqAndReconstruction.
     * 
     * @return
     *     possible object is
     *     {@link SPECTaccqCTacqAndReconstruction }
     *     
     */
    public SPECTaccqCTacqAndReconstruction getSPECTacqCTAcqAndReconstruction() {
        return specTacqCTAcqAndReconstruction;
    }

    /**
     * Définit la valeur de la propriété specTacqCTAcqAndReconstruction.
     * 
     * @param value
     *     allowed object is
     *     {@link SPECTaccqCTacqAndReconstruction }
     *     
     */
    public void setSPECTacqCTAcqAndReconstruction(SPECTaccqCTacqAndReconstruction value) {
        this.specTacqCTAcqAndReconstruction = value;
    }

    /**
     * Obtient la valeur de la propriété specTreconstruction.
     * 
     * @return
     *     possible object is
     *     {@link SPECTreconstruction }
     *     
     */
    public SPECTreconstruction getSPECTreconstruction() {
        return specTreconstruction;
    }

    /**
     * Définit la valeur de la propriété specTreconstruction.
     * 
     * @param value
     *     allowed object is
     *     {@link SPECTreconstruction }
     *     
     */
    public void setSPECTreconstruction(SPECTreconstruction value) {
        this.specTreconstruction = value;
    }

    /**
     * Obtient la valeur de la propriété cTsegmentation.
     * 
     * @return
     *     possible object is
     *     {@link CTsegmentation }
     *     
     */
    public CTsegmentation getCTsegmentation() {
        return cTsegmentation;
    }

    /**
     * Définit la valeur de la propriété cTsegmentation.
     * 
     * @param value
     *     allowed object is
     *     {@link CTsegmentation }
     *     
     */
    public void setCTsegmentation(CTsegmentation value) {
        this.cTsegmentation = value;
    }

    /**
     * Obtient la valeur de la propriété specTsensitivityCalculation.
     * 
     * @return
     *     possible object is
     *     {@link SPECTsensitivityCalculation }
     *     
     */
    public SPECTsensitivityCalculation getSPECTsensitivityCalculation() {
        return specTsensitivityCalculation;
    }

    /**
     * Définit la valeur de la propriété specTsensitivityCalculation.
     * 
     * @param value
     *     allowed object is
     *     {@link SPECTsensitivityCalculation }
     *     
     */
    public void setSPECTsensitivityCalculation(SPECTsensitivityCalculation value) {
        this.specTsensitivityCalculation = value;
    }

    /**
     * Obtient la valeur de la propriété specTrecoveryCurveCalculation.
     * 
     * @return
     *     possible object is
     *     {@link SPECTrecoveryCurveCalculation }
     *     
     */
    public SPECTrecoveryCurveCalculation getSPECTrecoveryCurveCalculation() {
        return specTrecoveryCurveCalculation;
    }

    /**
     * Définit la valeur de la propriété specTrecoveryCurveCalculation.
     * 
     * @param value
     *     allowed object is
     *     {@link SPECTrecoveryCurveCalculation }
     *     
     */
    public void setSPECTrecoveryCurveCalculation(SPECTrecoveryCurveCalculation value) {
        this.specTrecoveryCurveCalculation = value;
    }

}
