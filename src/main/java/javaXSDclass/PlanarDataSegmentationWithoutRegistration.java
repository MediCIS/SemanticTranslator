//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.01.28 à 04:59:07 PM CET 
//


package javaXSDclass;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour PlanarDataSegmentationWithoutRegistration complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PlanarDataSegmentationWithoutRegistration">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="TimePointIdentifierUsedContainer" type="{https://www.irdbb-medirad.com}TimePointIdentifierUsedContainer"/>
 *         &lt;element name="NMStaticCorrectedUsed" type="{https://www.irdbb-medirad.com}DICOMDataContainer"/>
 *         &lt;element name="SegmentationMethodUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CountsPerROIAtTimePointContainer" type="{https://www.irdbb-medirad.com}CountsPerROIAtTimePointContainer"/>
 *         &lt;element name="ROIProduced" type="{https://www.irdbb-medirad.com}ROIcontainer" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlanarDataSegmentationWithoutRegistration", propOrder = {
    "processExecutionContext",
    "timePointIdentifierUsedContainer",
    "nmStaticCorrectedUsed",
    "segmentationMethodUsed",
    "countsPerROIAtTimePointContainer",
    "roiProduced"
})
public class PlanarDataSegmentationWithoutRegistration {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "TimePointIdentifierUsedContainer", required = true)
    protected TimePointIdentifierUsedContainer timePointIdentifierUsedContainer;
    @XmlElement(name = "NMStaticCorrectedUsed", required = true)
    protected DICOMDataContainer nmStaticCorrectedUsed;
    @XmlElement(name = "SegmentationMethodUsed", required = true)
    protected String segmentationMethodUsed;
    @XmlElement(name = "CountsPerROIAtTimePointContainer", required = true)
    protected CountsPerROIAtTimePointContainer countsPerROIAtTimePointContainer;
    @XmlElement(name = "ROIProduced", required = true)
    protected List<ROIcontainer> roiProduced;

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
     * Obtient la valeur de la propriété nmStaticCorrectedUsed.
     * 
     * @return
     *     possible object is
     *     {@link DICOMDataContainer }
     *     
     */
    public DICOMDataContainer getNMStaticCorrectedUsed() {
        return nmStaticCorrectedUsed;
    }

    /**
     * Définit la valeur de la propriété nmStaticCorrectedUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMDataContainer }
     *     
     */
    public void setNMStaticCorrectedUsed(DICOMDataContainer value) {
        this.nmStaticCorrectedUsed = value;
    }

    /**
     * Obtient la valeur de la propriété segmentationMethodUsed.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegmentationMethodUsed() {
        return segmentationMethodUsed;
    }

    /**
     * Définit la valeur de la propriété segmentationMethodUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegmentationMethodUsed(String value) {
        this.segmentationMethodUsed = value;
    }

    /**
     * Obtient la valeur de la propriété countsPerROIAtTimePointContainer.
     * 
     * @return
     *     possible object is
     *     {@link CountsPerROIAtTimePointContainer }
     *     
     */
    public CountsPerROIAtTimePointContainer getCountsPerROIAtTimePointContainer() {
        return countsPerROIAtTimePointContainer;
    }

    /**
     * Définit la valeur de la propriété countsPerROIAtTimePointContainer.
     * 
     * @param value
     *     allowed object is
     *     {@link CountsPerROIAtTimePointContainer }
     *     
     */
    public void setCountsPerROIAtTimePointContainer(CountsPerROIAtTimePointContainer value) {
        this.countsPerROIAtTimePointContainer = value;
    }

    /**
     * Gets the value of the roiProduced property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roiProduced property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getROIProduced().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ROIcontainer }
     * 
     * 
     */
    public List<ROIcontainer> getROIProduced() {
        if (roiProduced == null) {
            roiProduced = new ArrayList<ROIcontainer>();
        }
        return this.roiProduced;
    }

}
