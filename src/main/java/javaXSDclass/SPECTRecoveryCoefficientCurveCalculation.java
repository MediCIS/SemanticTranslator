//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.10.24 à 03:08:52 PM CEST 
//


package javaXSDclass;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="VOIInCTIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="VOIInSPECTIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
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
    protected List<String> voiInCTIdentifierUsed;
    @XmlElement(name = "VOIInSPECTIdentifierUsed", required = true)
    protected List<String> voiInSPECTIdentifierUsed;
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
     * Gets the value of the voiInCTIdentifierUsed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the voiInCTIdentifierUsed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVOIInCTIdentifierUsed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getVOIInCTIdentifierUsed() {
        if (voiInCTIdentifierUsed == null) {
            voiInCTIdentifierUsed = new ArrayList<String>();
        }
        return this.voiInCTIdentifierUsed;
    }

    /**
     * Gets the value of the voiInSPECTIdentifierUsed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the voiInSPECTIdentifierUsed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVOIInSPECTIdentifierUsed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getVOIInSPECTIdentifierUsed() {
        if (voiInSPECTIdentifierUsed == null) {
            voiInSPECTIdentifierUsed = new ArrayList<String>();
        }
        return this.voiInSPECTIdentifierUsed;
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
