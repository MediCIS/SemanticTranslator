//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.07.30 à 02:06:37 PM CEST 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour ActivityScaling complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ActivityScaling">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="TimePointIdentifierUsed" type="{https://www.irdbb-medirad.com}TimePointIdentifierContainer"/>
 *         &lt;element name="VOIIdentifierUsed" type="{https://www.irdbb-medirad.com}VOIIdentifierContainer"/>
 *         &lt;element name="ROIIdentifierUsed" type="{https://www.irdbb-medirad.com}ROIIdentifierContainer"/>
 *         &lt;element name="DataActivityPerROIAtTimePoint" type="{https://www.irdbb-medirad.com}DataActivityPerROIAtTimePointContainer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActivityScaling", propOrder = {
    "processExecutionContext",
    "timePointIdentifierUsed",
    "voiIdentifierUsed",
    "roiIdentifierUsed",
    "dataActivityPerROIAtTimePoint"
})
public class ActivityScaling {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "TimePointIdentifierUsed", required = true)
    protected TimePointIdentifierContainer timePointIdentifierUsed;
    @XmlElement(name = "VOIIdentifierUsed", required = true)
    protected VOIIdentifierContainer voiIdentifierUsed;
    @XmlElement(name = "ROIIdentifierUsed", required = true)
    protected ROIIdentifierContainer roiIdentifierUsed;
    @XmlElement(name = "DataActivityPerROIAtTimePoint", required = true)
    protected DataActivityPerROIAtTimePointContainer dataActivityPerROIAtTimePoint;

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
     * Obtient la valeur de la propriété timePointIdentifierUsed.
     * 
     * @return
     *     possible object is
     *     {@link TimePointIdentifierContainer }
     *     
     */
    public TimePointIdentifierContainer getTimePointIdentifierUsed() {
        return timePointIdentifierUsed;
    }

    /**
     * Définit la valeur de la propriété timePointIdentifierUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePointIdentifierContainer }
     *     
     */
    public void setTimePointIdentifierUsed(TimePointIdentifierContainer value) {
        this.timePointIdentifierUsed = value;
    }

    /**
     * Obtient la valeur de la propriété voiIdentifierUsed.
     * 
     * @return
     *     possible object is
     *     {@link VOIIdentifierContainer }
     *     
     */
    public VOIIdentifierContainer getVOIIdentifierUsed() {
        return voiIdentifierUsed;
    }

    /**
     * Définit la valeur de la propriété voiIdentifierUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link VOIIdentifierContainer }
     *     
     */
    public void setVOIIdentifierUsed(VOIIdentifierContainer value) {
        this.voiIdentifierUsed = value;
    }

    /**
     * Obtient la valeur de la propriété roiIdentifierUsed.
     * 
     * @return
     *     possible object is
     *     {@link ROIIdentifierContainer }
     *     
     */
    public ROIIdentifierContainer getROIIdentifierUsed() {
        return roiIdentifierUsed;
    }

    /**
     * Définit la valeur de la propriété roiIdentifierUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link ROIIdentifierContainer }
     *     
     */
    public void setROIIdentifierUsed(ROIIdentifierContainer value) {
        this.roiIdentifierUsed = value;
    }

    /**
     * Obtient la valeur de la propriété dataActivityPerROIAtTimePoint.
     * 
     * @return
     *     possible object is
     *     {@link DataActivityPerROIAtTimePointContainer }
     *     
     */
    public DataActivityPerROIAtTimePointContainer getDataActivityPerROIAtTimePoint() {
        return dataActivityPerROIAtTimePoint;
    }

    /**
     * Définit la valeur de la propriété dataActivityPerROIAtTimePoint.
     * 
     * @param value
     *     allowed object is
     *     {@link DataActivityPerROIAtTimePointContainer }
     *     
     */
    public void setDataActivityPerROIAtTimePoint(DataActivityPerROIAtTimePointContainer value) {
        this.dataActivityPerROIAtTimePoint = value;
    }

}
