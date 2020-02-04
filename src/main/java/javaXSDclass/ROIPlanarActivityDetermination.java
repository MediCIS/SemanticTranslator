//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.04 à 12:01:52 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour ROIPlanarActivityDetermination complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ROIPlanarActivityDetermination">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="TimePointIdentifierUsedContainer" type="{https://www.irdbb-medirad.com}TimePointIdentifierUsedContainer"/>
 *         &lt;element name="ROIIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PlanarCalibrationFactorReference" type="{https://www.irdbb-medirad.com}NMRelevantCalibrationReference"/>
 *         &lt;element name="DataActivityPerROIAtTimePointContainer" type="{https://www.irdbb-medirad.com}DataActivityPerROIAtTimePointContainer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ROIPlanarActivityDetermination", propOrder = {
    "processExecutionContext",
    "timePointIdentifierUsedContainer",
    "roiIdentifierUsed",
    "planarCalibrationFactorReference",
    "dataActivityPerROIAtTimePointContainer"
})
public class ROIPlanarActivityDetermination {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "TimePointIdentifierUsedContainer", required = true)
    protected TimePointIdentifierUsedContainer timePointIdentifierUsedContainer;
    @XmlElement(name = "ROIIdentifierUsed", required = true)
    protected String roiIdentifierUsed;
    @XmlElement(name = "PlanarCalibrationFactorReference", required = true)
    protected NMRelevantCalibrationReference planarCalibrationFactorReference;
    @XmlElement(name = "DataActivityPerROIAtTimePointContainer", required = true)
    protected DataActivityPerROIAtTimePointContainer dataActivityPerROIAtTimePointContainer;

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
     * Obtient la valeur de la propriété timePointIdentifierUsedContainer.
     * 
     * @return
     *     possible object is
     *     {@link TimePointIdentifierUsedContainer }
     *     
     */
    public TimePointIdentifierUsedContainer getTimePointIdentifierUsedContainer() {
        return timePointIdentifierUsedContainer;
    }

    /**
     * Définit la valeur de la propriété timePointIdentifierUsedContainer.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePointIdentifierUsedContainer }
     *     
     */
    public void setTimePointIdentifierUsedContainer(TimePointIdentifierUsedContainer value) {
        this.timePointIdentifierUsedContainer = value;
    }

    /**
     * Obtient la valeur de la propriété roiIdentifierUsed.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getROIIdentifierUsed() {
        return roiIdentifierUsed;
    }

    /**
     * Définit la valeur de la propriété roiIdentifierUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setROIIdentifierUsed(String value) {
        this.roiIdentifierUsed = value;
    }

    /**
     * Obtient la valeur de la propriété planarCalibrationFactorReference.
     * 
     * @return
     *     possible object is
     *     {@link NMRelevantCalibrationReference }
     *     
     */
    public NMRelevantCalibrationReference getPlanarCalibrationFactorReference() {
        return planarCalibrationFactorReference;
    }

    /**
     * Définit la valeur de la propriété planarCalibrationFactorReference.
     * 
     * @param value
     *     allowed object is
     *     {@link NMRelevantCalibrationReference }
     *     
     */
    public void setPlanarCalibrationFactorReference(NMRelevantCalibrationReference value) {
        this.planarCalibrationFactorReference = value;
    }

    /**
     * Obtient la valeur de la propriété dataActivityPerROIAtTimePointContainer.
     * 
     * @return
     *     possible object is
     *     {@link DataActivityPerROIAtTimePointContainer }
     *     
     */
    public DataActivityPerROIAtTimePointContainer getDataActivityPerROIAtTimePointContainer() {
        return dataActivityPerROIAtTimePointContainer;
    }

    /**
     * Définit la valeur de la propriété dataActivityPerROIAtTimePointContainer.
     * 
     * @param value
     *     allowed object is
     *     {@link DataActivityPerROIAtTimePointContainer }
     *     
     */
    public void setDataActivityPerROIAtTimePointContainer(DataActivityPerROIAtTimePointContainer value) {
        this.dataActivityPerROIAtTimePointContainer = value;
    }

}
