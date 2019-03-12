//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2018.12.11 à 04:47:30 PM CET 
//


package javaXSDclass;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour VOIActivityDetermination complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="VOIActivityDetermination">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="VOIIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="TimePointIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CalibrationFactorUsed" type="{https://www.irdbb-medirad.com}CalibrationFactor"/>
 *         &lt;element name="SPECTRecoveryCoefficientCurveUsed" type="{https://www.irdbb-medirad.com}SPECTRecoveryCoefficientCurve"/>
 *         &lt;element name="DataActivityPerVOIAtTimePointProduced" type="{https://www.irdbb-medirad.com}DataActivityPerVOIAtTimePoint" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VOIActivityDetermination", propOrder = {
    "processExecutionContext",
    "voiIdentifierUsed",
    "timePointIdentifierUsed",
    "calibrationFactorUsed",
    "spectRecoveryCoefficientCurveUsed",
    "dataActivityPerVOIAtTimePointProduced"
})
public class VOIActivityDetermination {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "VOIIdentifierUsed", required = true)
    protected List<String> voiIdentifierUsed;
    @XmlElement(name = "TimePointIdentifierUsed", required = true)
    protected String timePointIdentifierUsed;
    @XmlElement(name = "CalibrationFactorUsed", required = true)
    protected CalibrationFactor calibrationFactorUsed;
    @XmlElement(name = "SPECTRecoveryCoefficientCurveUsed", required = true)
    protected SPECTRecoveryCoefficientCurve spectRecoveryCoefficientCurveUsed;
    @XmlElement(name = "DataActivityPerVOIAtTimePointProduced", required = true)
    protected List<DataActivityPerVOIAtTimePoint> dataActivityPerVOIAtTimePointProduced;

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
     * Gets the value of the voiIdentifierUsed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the voiIdentifierUsed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVOIIdentifierUsed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getVOIIdentifierUsed() {
        if (voiIdentifierUsed == null) {
            voiIdentifierUsed = new ArrayList<String>();
        }
        return this.voiIdentifierUsed;
    }

    /**
     * Obtient la valeur de la propriété timePointIdentifierUsed.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimePointIdentifierUsed() {
        return timePointIdentifierUsed;
    }

    /**
     * Définit la valeur de la propriété timePointIdentifierUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimePointIdentifierUsed(String value) {
        this.timePointIdentifierUsed = value;
    }

    /**
     * Obtient la valeur de la propriété calibrationFactorUsed.
     * 
     * @return
     *     possible object is
     *     {@link CalibrationFactor }
     *     
     */
    public CalibrationFactor getCalibrationFactorUsed() {
        return calibrationFactorUsed;
    }

    /**
     * Définit la valeur de la propriété calibrationFactorUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link CalibrationFactor }
     *     
     */
    public void setCalibrationFactorUsed(CalibrationFactor value) {
        this.calibrationFactorUsed = value;
    }

    /**
     * Obtient la valeur de la propriété spectRecoveryCoefficientCurveUsed.
     * 
     * @return
     *     possible object is
     *     {@link SPECTRecoveryCoefficientCurve }
     *     
     */
    public SPECTRecoveryCoefficientCurve getSPECTRecoveryCoefficientCurveUsed() {
        return spectRecoveryCoefficientCurveUsed;
    }

    /**
     * Définit la valeur de la propriété spectRecoveryCoefficientCurveUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link SPECTRecoveryCoefficientCurve }
     *     
     */
    public void setSPECTRecoveryCoefficientCurveUsed(SPECTRecoveryCoefficientCurve value) {
        this.spectRecoveryCoefficientCurveUsed = value;
    }

    /**
     * Gets the value of the dataActivityPerVOIAtTimePointProduced property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataActivityPerVOIAtTimePointProduced property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataActivityPerVOIAtTimePointProduced().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataActivityPerVOIAtTimePoint }
     * 
     * 
     */
    public List<DataActivityPerVOIAtTimePoint> getDataActivityPerVOIAtTimePointProduced() {
        if (dataActivityPerVOIAtTimePointProduced == null) {
            dataActivityPerVOIAtTimePointProduced = new ArrayList<DataActivityPerVOIAtTimePoint>();
        }
        return this.dataActivityPerVOIAtTimePointProduced;
    }

}
