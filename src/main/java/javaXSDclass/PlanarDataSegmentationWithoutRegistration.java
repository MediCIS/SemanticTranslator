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
 *         &lt;element name="TimePointIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NMStaticCorrectedUsed" type="{https://www.irdbb-medirad.com}DICOMDataContainer" minOccurs="0"/>
 *         &lt;element name="NonDICOMNMStaticCorrectedUsed" type="{https://www.irdbb-medirad.com}NonDICOMDataContainer" minOccurs="0"/>
 *         &lt;element name="SegmentationMethodUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CountsPerROIAtTimePointContainer" type="{https://www.irdbb-medirad.com}CountsPerROIAtTimePointContainer"/>
 *         &lt;element name="ROIProduced" type="{https://www.irdbb-medirad.com}ROIcontainer" minOccurs="0"/>
 *         &lt;element name="SegmentationProduced" type="{https://www.irdbb-medirad.com}Segmentation" minOccurs="0"/>
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
    "timePointIdentifierUsed",
    "nmStaticCorrectedUsed",
    "nonDICOMNMStaticCorrectedUsed",
    "segmentationMethodUsed",
    "countsPerROIAtTimePointContainer",
    "roiProduced",
    "segmentationProduced"
})
public class PlanarDataSegmentationWithoutRegistration {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "TimePointIdentifierUsed", required = true)
    protected String timePointIdentifierUsed;
    @XmlElement(name = "NMStaticCorrectedUsed")
    protected DICOMDataContainer nmStaticCorrectedUsed;
    @XmlElement(name = "NonDICOMNMStaticCorrectedUsed")
    protected NonDICOMDataContainer nonDICOMNMStaticCorrectedUsed;
    @XmlElement(name = "SegmentationMethodUsed", required = true)
    protected String segmentationMethodUsed;
    @XmlElement(name = "CountsPerROIAtTimePointContainer", required = true)
    protected CountsPerROIAtTimePointContainer countsPerROIAtTimePointContainer;
    @XmlElement(name = "ROIProduced")
    protected ROIcontainer roiProduced;
    @XmlElement(name = "SegmentationProduced")
    protected Segmentation segmentationProduced;

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
     * Obtient la valeur de la propriété nonDICOMNMStaticCorrectedUsed.
     * 
     * @return
     *     possible object is
     *     {@link NonDICOMDataContainer }
     *     
     */
    public NonDICOMDataContainer getNonDICOMNMStaticCorrectedUsed() {
        return nonDICOMNMStaticCorrectedUsed;
    }

    /**
     * Définit la valeur de la propriété nonDICOMNMStaticCorrectedUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link NonDICOMDataContainer }
     *     
     */
    public void setNonDICOMNMStaticCorrectedUsed(NonDICOMDataContainer value) {
        this.nonDICOMNMStaticCorrectedUsed = value;
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
     * Obtient la valeur de la propriété roiProduced.
     * 
     * @return
     *     possible object is
     *     {@link ROIcontainer }
     *     
     */
    public ROIcontainer getROIProduced() {
        return roiProduced;
    }

    /**
     * Définit la valeur de la propriété roiProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link ROIcontainer }
     *     
     */
    public void setROIProduced(ROIcontainer value) {
        this.roiProduced = value;
    }

    /**
     * Obtient la valeur de la propriété segmentationProduced.
     * 
     * @return
     *     possible object is
     *     {@link Segmentation }
     *     
     */
    public Segmentation getSegmentationProduced() {
        return segmentationProduced;
    }

    /**
     * Définit la valeur de la propriété segmentationProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link Segmentation }
     *     
     */
    public void setSegmentationProduced(Segmentation value) {
        this.segmentationProduced = value;
    }

}
