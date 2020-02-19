//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.12 à 04:30:42 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour SPECTRecoveryCoefficientCurveCalculation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SPECTRecoveryCoefficientCurveCalculation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="VOIInCTIdentifierUsed" type="{https://www.irdbb-medirad.com}VOIIdentifierContainer"/>
 *         &lt;element name="VOIInSPECTIdentifierUsed" type="{https://www.irdbb-medirad.com}VOIIdentifierContainer"/>
 *         &lt;element name="PhantomIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SPECTCalibrationCoefficientReferenceUsed" type="{https://www.irdbb-medirad.com}NMRelevantCalibrationReference"/>
 *         &lt;element name="SPECTRecoveryCoefficientCurveInCalibrationProduced" type="{https://www.irdbb-medirad.com}SPECTRecoveryCoefficientCurveInCalibration"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SPECTRecoveryCoefficientCurveCalculation", propOrder = {
    "processExecutionContext",
    "voiInCTIdentifierUsed",
    "voiInSPECTIdentifierUsed",
    "phantomIdentifierUsed",
    "spectCalibrationCoefficientReferenceUsed",
    "spectRecoveryCoefficientCurveInCalibrationProduced"
})
public class SPECTRecoveryCoefficientCurveCalculation {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "VOIInCTIdentifierUsed", required = true)
    protected VOIIdentifierContainer voiInCTIdentifierUsed;
    @XmlElement(name = "VOIInSPECTIdentifierUsed", required = true)
    protected VOIIdentifierContainer voiInSPECTIdentifierUsed;
    @XmlElement(name = "PhantomIdentifierUsed", required = true)
    protected String phantomIdentifierUsed;
    @XmlElement(name = "SPECTCalibrationCoefficientReferenceUsed", required = true)
    protected NMRelevantCalibrationReference spectCalibrationCoefficientReferenceUsed;
    @XmlElement(name = "SPECTRecoveryCoefficientCurveInCalibrationProduced", required = true)
    protected SPECTRecoveryCoefficientCurveInCalibration spectRecoveryCoefficientCurveInCalibrationProduced;

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
     * Obtient la valeur de la propriété phantomIdentifierUsed.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhantomIdentifierUsed() {
        return phantomIdentifierUsed;
    }

    /**
     * Définit la valeur de la propriété phantomIdentifierUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhantomIdentifierUsed(String value) {
        this.phantomIdentifierUsed = value;
    }

    /**
     * Obtient la valeur de la propriété spectCalibrationCoefficientReferenceUsed.
     * 
     * @return
     *     possible object is
     *     {@link NMRelevantCalibrationReference }
     *     
     */
    public NMRelevantCalibrationReference getSPECTCalibrationCoefficientReferenceUsed() {
        return spectCalibrationCoefficientReferenceUsed;
    }

    /**
     * Définit la valeur de la propriété spectCalibrationCoefficientReferenceUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link NMRelevantCalibrationReference }
     *     
     */
    public void setSPECTCalibrationCoefficientReferenceUsed(NMRelevantCalibrationReference value) {
        this.spectCalibrationCoefficientReferenceUsed = value;
    }

    /**
     * Obtient la valeur de la propriété spectRecoveryCoefficientCurveInCalibrationProduced.
     * 
     * @return
     *     possible object is
     *     {@link SPECTRecoveryCoefficientCurveInCalibration }
     *     
     */
    public SPECTRecoveryCoefficientCurveInCalibration getSPECTRecoveryCoefficientCurveInCalibrationProduced() {
        return spectRecoveryCoefficientCurveInCalibrationProduced;
    }

    /**
     * Définit la valeur de la propriété spectRecoveryCoefficientCurveInCalibrationProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link SPECTRecoveryCoefficientCurveInCalibration }
     *     
     */
    public void setSPECTRecoveryCoefficientCurveInCalibrationProduced(SPECTRecoveryCoefficientCurveInCalibration value) {
        this.spectRecoveryCoefficientCurveInCalibrationProduced = value;
    }

}
