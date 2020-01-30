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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour SPECTCTCalibrationWorkflow complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SPECTCTCalibrationWorkflow">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CTNumberCalibrationWorkfow" type="{https://www.irdbb-medirad.com}CTNumberCalibrationWorkfow" minOccurs="0"/>
 *         &lt;element name="SPECTAcqCTAcqAndReconstructionInCalibration" type="{https://www.irdbb-medirad.com}SPECTAcqCTAcqAndReconstructionInCalibration"/>
 *         &lt;element name="SPECTReconstructionInCalibration" type="{https://www.irdbb-medirad.com}SPECTReconstructionInCalibration"/>
 *         &lt;element name="CTSegmentationInCalibration" type="{https://www.irdbb-medirad.com}CTSegmentationInCalibration"/>
 *         &lt;element name="SPECTSensitivityCalculation" type="{https://www.irdbb-medirad.com}SPECTSensitivityCalculation"/>
 *         &lt;element name="SPECTRecoveryCoefficientCurveCalculation" type="{https://www.irdbb-medirad.com}SPECTRecoveryCoefficientCurveCalculation" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SPECTCTCalibrationWorkflow", propOrder = {
    "ctNumberCalibrationWorkfow",
    "spectAcqCTAcqAndReconstructionInCalibration",
    "spectReconstructionInCalibration",
    "ctSegmentationInCalibration",
    "spectSensitivityCalculation",
    "spectRecoveryCoefficientCurveCalculation"
})
public class SPECTCTCalibrationWorkflow {

    @XmlElement(name = "CTNumberCalibrationWorkfow")
    protected CTNumberCalibrationWorkfow ctNumberCalibrationWorkfow;
    @XmlElement(name = "SPECTAcqCTAcqAndReconstructionInCalibration", required = true)
    protected SPECTAcqCTAcqAndReconstructionInCalibration spectAcqCTAcqAndReconstructionInCalibration;
    @XmlElement(name = "SPECTReconstructionInCalibration", required = true)
    protected SPECTReconstructionInCalibration spectReconstructionInCalibration;
    @XmlElement(name = "CTSegmentationInCalibration", required = true)
    protected CTSegmentationInCalibration ctSegmentationInCalibration;
    @XmlElement(name = "SPECTSensitivityCalculation", required = true)
    protected SPECTSensitivityCalculation spectSensitivityCalculation;
    @XmlElement(name = "SPECTRecoveryCoefficientCurveCalculation")
    protected SPECTRecoveryCoefficientCurveCalculation spectRecoveryCoefficientCurveCalculation;

    /**
     * Obtient la valeur de la propriété ctNumberCalibrationWorkfow.
     * 
     * @return
     *     possible object is
     *     {@link CTNumberCalibrationWorkfow }
     *     
     */
    public CTNumberCalibrationWorkfow getCTNumberCalibrationWorkfow() {
        return ctNumberCalibrationWorkfow;
    }

    /**
     * Définit la valeur de la propriété ctNumberCalibrationWorkfow.
     * 
     * @param value
     *     allowed object is
     *     {@link CTNumberCalibrationWorkfow }
     *     
     */
    public void setCTNumberCalibrationWorkfow(CTNumberCalibrationWorkfow value) {
        this.ctNumberCalibrationWorkfow = value;
    }

    /**
     * Obtient la valeur de la propriété spectAcqCTAcqAndReconstructionInCalibration.
     * 
     * @return
     *     possible object is
     *     {@link SPECTAcqCTAcqAndReconstructionInCalibration }
     *     
     */
    public SPECTAcqCTAcqAndReconstructionInCalibration getSPECTAcqCTAcqAndReconstructionInCalibration() {
        return spectAcqCTAcqAndReconstructionInCalibration;
    }

    /**
     * Définit la valeur de la propriété spectAcqCTAcqAndReconstructionInCalibration.
     * 
     * @param value
     *     allowed object is
     *     {@link SPECTAcqCTAcqAndReconstructionInCalibration }
     *     
     */
    public void setSPECTAcqCTAcqAndReconstructionInCalibration(SPECTAcqCTAcqAndReconstructionInCalibration value) {
        this.spectAcqCTAcqAndReconstructionInCalibration = value;
    }

    /**
     * Obtient la valeur de la propriété spectReconstructionInCalibration.
     * 
     * @return
     *     possible object is
     *     {@link SPECTReconstructionInCalibration }
     *     
     */
    public SPECTReconstructionInCalibration getSPECTReconstructionInCalibration() {
        return spectReconstructionInCalibration;
    }

    /**
     * Définit la valeur de la propriété spectReconstructionInCalibration.
     * 
     * @param value
     *     allowed object is
     *     {@link SPECTReconstructionInCalibration }
     *     
     */
    public void setSPECTReconstructionInCalibration(SPECTReconstructionInCalibration value) {
        this.spectReconstructionInCalibration = value;
    }

    /**
     * Obtient la valeur de la propriété ctSegmentationInCalibration.
     * 
     * @return
     *     possible object is
     *     {@link CTSegmentationInCalibration }
     *     
     */
    public CTSegmentationInCalibration getCTSegmentationInCalibration() {
        return ctSegmentationInCalibration;
    }

    /**
     * Définit la valeur de la propriété ctSegmentationInCalibration.
     * 
     * @param value
     *     allowed object is
     *     {@link CTSegmentationInCalibration }
     *     
     */
    public void setCTSegmentationInCalibration(CTSegmentationInCalibration value) {
        this.ctSegmentationInCalibration = value;
    }

    /**
     * Obtient la valeur de la propriété spectSensitivityCalculation.
     * 
     * @return
     *     possible object is
     *     {@link SPECTSensitivityCalculation }
     *     
     */
    public SPECTSensitivityCalculation getSPECTSensitivityCalculation() {
        return spectSensitivityCalculation;
    }

    /**
     * Définit la valeur de la propriété spectSensitivityCalculation.
     * 
     * @param value
     *     allowed object is
     *     {@link SPECTSensitivityCalculation }
     *     
     */
    public void setSPECTSensitivityCalculation(SPECTSensitivityCalculation value) {
        this.spectSensitivityCalculation = value;
    }

    /**
     * Obtient la valeur de la propriété spectRecoveryCoefficientCurveCalculation.
     * 
     * @return
     *     possible object is
     *     {@link SPECTRecoveryCoefficientCurveCalculation }
     *     
     */
    public SPECTRecoveryCoefficientCurveCalculation getSPECTRecoveryCoefficientCurveCalculation() {
        return spectRecoveryCoefficientCurveCalculation;
    }

    /**
     * Définit la valeur de la propriété spectRecoveryCoefficientCurveCalculation.
     * 
     * @param value
     *     allowed object is
     *     {@link SPECTRecoveryCoefficientCurveCalculation }
     *     
     */
    public void setSPECTRecoveryCoefficientCurveCalculation(SPECTRecoveryCoefficientCurveCalculation value) {
        this.spectRecoveryCoefficientCurveCalculation = value;
    }

}
