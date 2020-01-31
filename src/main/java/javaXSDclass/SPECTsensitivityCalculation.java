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
 * <p>Classe Java pour SPECTSensitivityCalculation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SPECTSensitivityCalculation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="VOIInCTIdentifierUsed" type="{https://www.irdbb-medirad.com}VOIIdentifierContainer"/>
 *         &lt;element name="VOIInSPECTIdentifierUsed" type="{https://www.irdbb-medirad.com}VOIIdentifierContainer"/>
 *         &lt;element name="SPECTCalibrationCoefficientProduced" type="{https://www.irdbb-medirad.com}CalibrationCoefficient"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SPECTSensitivityCalculation", propOrder = {
    "processExecutionContext",
    "voiInCTIdentifierUsed",
    "voiInSPECTIdentifierUsed",
    "spectCalibrationCoefficientProduced"
})
public class SPECTSensitivityCalculation {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "VOIInCTIdentifierUsed", required = true)
    protected VOIIdentifierContainer voiInCTIdentifierUsed;
    @XmlElement(name = "VOIInSPECTIdentifierUsed", required = true)
    protected VOIIdentifierContainer voiInSPECTIdentifierUsed;
    @XmlElement(name = "SPECTCalibrationCoefficientProduced", required = true)
    protected CalibrationCoefficient spectCalibrationCoefficientProduced;

    /**
     * Obtient la valeur de la propriété processExecutionContext.
     * 
     * @return
     *     possible object is
     *     {@link ProcessExecutionContext }
     *     
     */
    public ProcessExecutionContext getProcessExecutionContext() {
        return processExecutionContext;
    }

    /**
     * Définit la valeur de la propriété processExecutionContext.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcessExecutionContext }
     *     
     */
    public void setProcessExecutionContext(ProcessExecutionContext value) {
        this.processExecutionContext = value;
    }

    /**
     * Obtient la valeur de la propriété voiInCTIdentifierUsed.
     * 
     * @return
     *     possible object is
     *     {@link VOIIdentifierContainer }
     *     
     */
    public VOIIdentifierContainer getVOIInCTIdentifierUsed() {
        return voiInCTIdentifierUsed;
    }

    /**
     * Définit la valeur de la propriété voiInCTIdentifierUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link VOIIdentifierContainer }
     *     
     */
    public void setVOIInCTIdentifierUsed(VOIIdentifierContainer value) {
        this.voiInCTIdentifierUsed = value;
    }

    /**
     * Obtient la valeur de la propriété voiInSPECTIdentifierUsed.
     * 
     * @return
     *     possible object is
     *     {@link VOIIdentifierContainer }
     *     
     */
    public VOIIdentifierContainer getVOIInSPECTIdentifierUsed() {
        return voiInSPECTIdentifierUsed;
    }

    /**
     * Définit la valeur de la propriété voiInSPECTIdentifierUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link VOIIdentifierContainer }
     *     
     */
    public void setVOIInSPECTIdentifierUsed(VOIIdentifierContainer value) {
        this.voiInSPECTIdentifierUsed = value;
    }

    /**
     * Obtient la valeur de la propriété spectCalibrationCoefficientProduced.
     * 
     * @return
     *     possible object is
     *     {@link CalibrationCoefficient }
     *     
     */
    public CalibrationCoefficient getSPECTCalibrationCoefficientProduced() {
        return spectCalibrationCoefficientProduced;
    }

    /**
     * Définit la valeur de la propriété spectCalibrationCoefficientProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link CalibrationCoefficient }
     *     
     */
    public void setSPECTCalibrationCoefficientProduced(CalibrationCoefficient value) {
        this.spectCalibrationCoefficientProduced = value;
    }

}
