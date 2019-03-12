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
 *         &lt;element name="SPECTAcqCTAcqAndReconstruction" type="{https://www.irdbb-medirad.com}SPECTAcqCTAcqAndReconstruction"/>
 *         &lt;element name="SPECTReconstruction" type="{https://www.irdbb-medirad.com}SPECTReconstruction"/>
 *         &lt;element name="CTSegmentation" type="{https://www.irdbb-medirad.com}CTSegmentation"/>
 *         &lt;element name="SPECTSensitivityCalculation" type="{https://www.irdbb-medirad.com}SPECTSensitivityCalculation"/>
 *         &lt;element name="SPECTRecoveryCurveCalculation" type="{https://www.irdbb-medirad.com}SPECTRecoveryCurveCalculation" minOccurs="0"/>
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
    "spectAcqCTAcqAndReconstruction",
    "spectReconstruction",
    "ctSegmentation",
    "spectSensitivityCalculation",
    "spectRecoveryCurveCalculation"
})
public class SPECTCTCalibrationWorkflow {

    @XmlElement(name = "CTNumberCalibrationWorkfow")
    protected CTNumberCalibrationWorkfow ctNumberCalibrationWorkfow;
    @XmlElement(name = "SPECTAcqCTAcqAndReconstruction", required = true)
    protected SPECTAcqCTAcqAndReconstruction spectAcqCTAcqAndReconstruction;
    @XmlElement(name = "SPECTReconstruction", required = true)
    protected SPECTReconstruction spectReconstruction;
    @XmlElement(name = "CTSegmentation", required = true)
    protected CTSegmentation ctSegmentation;
    @XmlElement(name = "SPECTSensitivityCalculation", required = true)
    protected SPECTSensitivityCalculation spectSensitivityCalculation;
    @XmlElement(name = "SPECTRecoveryCurveCalculation")
    protected SPECTRecoveryCurveCalculation spectRecoveryCurveCalculation;

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
     * Obtient la valeur de la propriété spectAcqCTAcqAndReconstruction.
     * 
     * @return
     *     possible object is
     *     {@link SPECTAcqCTAcqAndReconstruction }
     *     
     */
    public SPECTAcqCTAcqAndReconstruction getSPECTAcqCTAcqAndReconstruction() {
        return spectAcqCTAcqAndReconstruction;
    }

    /**
     * Définit la valeur de la propriété spectAcqCTAcqAndReconstruction.
     * 
     * @param value
     *     allowed object is
     *     {@link SPECTAcqCTAcqAndReconstruction }
     *     
     */
    public void setSPECTAcqCTAcqAndReconstruction(SPECTAcqCTAcqAndReconstruction value) {
        this.spectAcqCTAcqAndReconstruction = value;
    }

    /**
     * Obtient la valeur de la propriété spectReconstruction.
     * 
     * @return
     *     possible object is
     *     {@link SPECTReconstruction }
     *     
     */
    public SPECTReconstruction getSPECTReconstruction() {
        return spectReconstruction;
    }

    /**
     * Définit la valeur de la propriété spectReconstruction.
     * 
     * @param value
     *     allowed object is
     *     {@link SPECTReconstruction }
     *     
     */
    public void setSPECTReconstruction(SPECTReconstruction value) {
        this.spectReconstruction = value;
    }

    /**
     * Obtient la valeur de la propriété ctSegmentation.
     * 
     * @return
     *     possible object is
     *     {@link CTSegmentation }
     *     
     */
    public CTSegmentation getCTSegmentation() {
        return ctSegmentation;
    }

    /**
     * Définit la valeur de la propriété ctSegmentation.
     * 
     * @param value
     *     allowed object is
     *     {@link CTSegmentation }
     *     
     */
    public void setCTSegmentation(CTSegmentation value) {
        this.ctSegmentation = value;
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
     * Obtient la valeur de la propriété spectRecoveryCurveCalculation.
     * 
     * @return
     *     possible object is
     *     {@link SPECTRecoveryCurveCalculation }
     *     
     */
    public SPECTRecoveryCurveCalculation getSPECTRecoveryCurveCalculation() {
        return spectRecoveryCurveCalculation;
    }

    /**
     * Définit la valeur de la propriété spectRecoveryCurveCalculation.
     * 
     * @param value
     *     allowed object is
     *     {@link SPECTRecoveryCurveCalculation }
     *     
     */
    public void setSPECTRecoveryCurveCalculation(SPECTRecoveryCurveCalculation value) {
        this.spectRecoveryCurveCalculation = value;
    }

}
