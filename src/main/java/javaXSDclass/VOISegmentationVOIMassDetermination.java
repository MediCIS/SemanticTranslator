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
 * <p>Classe Java pour VOISegmentationVOIMassDetermination complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="VOISegmentationVOIMassDetermination">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="TimePointIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NMTomoReconUsed" type="{https://www.irdbb-medirad.com}DICOMData"/>
 *         &lt;element name="CTReconUsed" type="{https://www.irdbb-medirad.com}DICOMData"/>
 *         &lt;element name="VOISegmentationMethodUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CountsPerVOIAtTimePointContainer" type="{https://www.irdbb-medirad.com}CountsPerVOIAtTimePointContainer" minOccurs="0"/>
 *         &lt;element name="VOIProduced" type="{https://www.irdbb-medirad.com}VoiProducedContainer" minOccurs="0"/>
 *         &lt;element name="SegmentationProduced" type="{https://www.irdbb-medirad.com}Segmentation" minOccurs="0"/>
 *         &lt;element name="CTReconResampledOnNMReferenceProduced" type="{https://www.irdbb-medirad.com}DICOMDataContainer"/>
 *         &lt;element name="MassPerVOIAtTimePointContainer" type="{https://www.irdbb-medirad.com}MassPerVOIAtTimePointContainer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VOISegmentationVOIMassDetermination", propOrder = {
    "processExecutionContext",
    "timePointIdentifierUsed",
    "nmTomoReconUsed",
    "ctReconUsed",
    "voiSegmentationMethodUsed",
    "countsPerVOIAtTimePointContainer",
    "voiProduced",
    "segmentationProduced",
    "ctReconResampledOnNMReferenceProduced",
    "massPerVOIAtTimePointContainer"
})
public class VOISegmentationVOIMassDetermination {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "TimePointIdentifierUsed", required = true)
    protected String timePointIdentifierUsed;
    @XmlElement(name = "NMTomoReconUsed", required = true)
    protected DICOMData nmTomoReconUsed;
    @XmlElement(name = "CTReconUsed", required = true)
    protected DICOMData ctReconUsed;
    @XmlElement(name = "VOISegmentationMethodUsed", required = true)
    protected String voiSegmentationMethodUsed;
    @XmlElement(name = "CountsPerVOIAtTimePointContainer")
    protected CountsPerVOIAtTimePointContainer countsPerVOIAtTimePointContainer;
    @XmlElement(name = "VOIProduced")
    protected VoiProducedContainer voiProduced;
    @XmlElement(name = "SegmentationProduced")
    protected Segmentation segmentationProduced;
    @XmlElement(name = "CTReconResampledOnNMReferenceProduced", required = true)
    protected DICOMDataContainer ctReconResampledOnNMReferenceProduced;
    @XmlElement(name = "MassPerVOIAtTimePointContainer", required = true)
    protected MassPerVOIAtTimePointContainer massPerVOIAtTimePointContainer;

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
     * Obtient la valeur de la propriété nmTomoReconUsed.
     * 
     * @return
     *     possible object is
     *     {@link DICOMData }
     *     
     */
    public DICOMData getNMTomoReconUsed() {
        return nmTomoReconUsed;
    }

    /**
     * Définit la valeur de la propriété nmTomoReconUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMData }
     *     
     */
    public void setNMTomoReconUsed(DICOMData value) {
        this.nmTomoReconUsed = value;
    }

    /**
     * Obtient la valeur de la propriété ctReconUsed.
     * 
     * @return
     *     possible object is
     *     {@link DICOMData }
     *     
     */
    public DICOMData getCTReconUsed() {
        return ctReconUsed;
    }

    /**
     * Définit la valeur de la propriété ctReconUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMData }
     *     
     */
    public void setCTReconUsed(DICOMData value) {
        this.ctReconUsed = value;
    }

    /**
     * Obtient la valeur de la propriété voiSegmentationMethodUsed.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVOISegmentationMethodUsed() {
        return voiSegmentationMethodUsed;
    }

    /**
     * Définit la valeur de la propriété voiSegmentationMethodUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVOISegmentationMethodUsed(String value) {
        this.voiSegmentationMethodUsed = value;
    }

    /**
     * Obtient la valeur de la propriété countsPerVOIAtTimePointContainer.
     * 
     * @return
     *     possible object is
     *     {@link CountsPerVOIAtTimePointContainer }
     *     
     */
    public CountsPerVOIAtTimePointContainer getCountsPerVOIAtTimePointContainer() {
        return countsPerVOIAtTimePointContainer;
    }

    /**
     * Définit la valeur de la propriété countsPerVOIAtTimePointContainer.
     * 
     * @param value
     *     allowed object is
     *     {@link CountsPerVOIAtTimePointContainer }
     *     
     */
    public void setCountsPerVOIAtTimePointContainer(CountsPerVOIAtTimePointContainer value) {
        this.countsPerVOIAtTimePointContainer = value;
    }

    /**
     * Obtient la valeur de la propriété voiProduced.
     * 
     * @return
     *     possible object is
     *     {@link VoiProducedContainer }
     *     
     */
    public VoiProducedContainer getVOIProduced() {
        return voiProduced;
    }

    /**
     * Définit la valeur de la propriété voiProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link VoiProducedContainer }
     *     
     */
    public void setVOIProduced(VoiProducedContainer value) {
        this.voiProduced = value;
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

    /**
     * Obtient la valeur de la propriété ctReconResampledOnNMReferenceProduced.
     * 
     * @return
     *     possible object is
     *     {@link DICOMDataContainer }
     *     
     */
    public DICOMDataContainer getCTReconResampledOnNMReferenceProduced() {
        return ctReconResampledOnNMReferenceProduced;
    }

    /**
     * Définit la valeur de la propriété ctReconResampledOnNMReferenceProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMDataContainer }
     *     
     */
    public void setCTReconResampledOnNMReferenceProduced(DICOMDataContainer value) {
        this.ctReconResampledOnNMReferenceProduced = value;
    }

    /**
     * Obtient la valeur de la propriété massPerVOIAtTimePointContainer.
     * 
     * @return
     *     possible object is
     *     {@link MassPerVOIAtTimePointContainer }
     *     
     */
    public MassPerVOIAtTimePointContainer getMassPerVOIAtTimePointContainer() {
        return massPerVOIAtTimePointContainer;
    }

    /**
     * Définit la valeur de la propriété massPerVOIAtTimePointContainer.
     * 
     * @param value
     *     allowed object is
     *     {@link MassPerVOIAtTimePointContainer }
     *     
     */
    public void setMassPerVOIAtTimePointContainer(MassPerVOIAtTimePointContainer value) {
        this.massPerVOIAtTimePointContainer = value;
    }

}
