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
 * <p>Classe Java pour RegistrationVOISegmentationAndPropagation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="RegistrationVOISegmentationAndPropagation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="TimePointIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CountsPerVOIAtTimePointProducedContainer" type="{https://www.irdbb-medirad.com}CountsPerVOIAtTimePointProducedContainer" minOccurs="0"/>
 *         &lt;element name="NMTomoReconUsed" type="{https://www.irdbb-medirad.com}DICOMDataContainer"/>
 *         &lt;element name="CTReconUsed" type="{https://www.irdbb-medirad.com}DICOMDataContainer"/>
 *         &lt;element name="ImageProcessingMethodMethodUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Segmentation" type="{https://www.irdbb-medirad.com}Segmentation"/>
 *         &lt;element name="CTReconResampledOnCommonReferenceProduced" type="{https://www.irdbb-medirad.com}DICOMDataContainer" minOccurs="0"/>
 *         &lt;element name="NMTomoReconResampledOnCommonReferenceProduced" type="{https://www.irdbb-medirad.com}DICOMDataContainer" minOccurs="0"/>
 *         &lt;element name="NonDICOMCTReconResampledOnCommonReferenceProduced" type="{https://www.irdbb-medirad.com}NonDICOMDataContainer" minOccurs="0"/>
 *         &lt;element name="NonDICOMNMTomoReconResampledOnCommonReferenceProduced" type="{https://www.irdbb-medirad.com}NonDICOMDataContainer" minOccurs="0"/>
 *         &lt;element name="DensityImageProduced" type="{https://www.irdbb-medirad.com}NonDICOMDataContainer" minOccurs="0"/>
 *         &lt;element name="GeometricalTransformationContainer" type="{https://www.irdbb-medirad.com}GeometricalTransformationContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegistrationVOISegmentationAndPropagation", propOrder = {
    "processExecutionContext",
    "timePointIdentifierUsed",
    "countsPerVOIAtTimePointProducedContainer",
    "nmTomoReconUsed",
    "ctReconUsed",
    "imageProcessingMethodMethodUsed",
    "segmentation",
    "ctReconResampledOnCommonReferenceProduced",
    "nmTomoReconResampledOnCommonReferenceProduced",
    "nonDICOMCTReconResampledOnCommonReferenceProduced",
    "nonDICOMNMTomoReconResampledOnCommonReferenceProduced",
    "densityImageProduced",
    "geometricalTransformationContainer"
})
public class RegistrationVOISegmentationAndPropagation {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "TimePointIdentifierUsed", required = true)
    protected String timePointIdentifierUsed;
    @XmlElement(name = "CountsPerVOIAtTimePointProducedContainer")
    protected CountsPerVOIAtTimePointProducedContainer countsPerVOIAtTimePointProducedContainer;
    @XmlElement(name = "NMTomoReconUsed", required = true)
    protected DICOMDataContainer nmTomoReconUsed;
    @XmlElement(name = "CTReconUsed", required = true)
    protected DICOMDataContainer ctReconUsed;
    @XmlElement(name = "ImageProcessingMethodMethodUsed", required = true)
    protected String imageProcessingMethodMethodUsed;
    @XmlElement(name = "Segmentation", required = true)
    protected Segmentation segmentation;
    @XmlElement(name = "CTReconResampledOnCommonReferenceProduced")
    protected DICOMDataContainer ctReconResampledOnCommonReferenceProduced;
    @XmlElement(name = "NMTomoReconResampledOnCommonReferenceProduced")
    protected DICOMDataContainer nmTomoReconResampledOnCommonReferenceProduced;
    @XmlElement(name = "NonDICOMCTReconResampledOnCommonReferenceProduced")
    protected NonDICOMDataContainer nonDICOMCTReconResampledOnCommonReferenceProduced;
    @XmlElement(name = "NonDICOMNMTomoReconResampledOnCommonReferenceProduced")
    protected NonDICOMDataContainer nonDICOMNMTomoReconResampledOnCommonReferenceProduced;
    @XmlElement(name = "DensityImageProduced")
    protected NonDICOMDataContainer densityImageProduced;
    @XmlElement(name = "GeometricalTransformationContainer")
    protected GeometricalTransformationContainer geometricalTransformationContainer;

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
     * Obtient la valeur de la propriété countsPerVOIAtTimePointProducedContainer.
     * 
     * @return
     *     possible object is
     *     {@link CountsPerVOIAtTimePointProducedContainer }
     *     
     */
    public CountsPerVOIAtTimePointProducedContainer getCountsPerVOIAtTimePointProducedContainer() {
        return countsPerVOIAtTimePointProducedContainer;
    }

    /**
     * Définit la valeur de la propriété countsPerVOIAtTimePointProducedContainer.
     * 
     * @param value
     *     allowed object is
     *     {@link CountsPerVOIAtTimePointProducedContainer }
     *     
     */
    public void setCountsPerVOIAtTimePointProducedContainer(CountsPerVOIAtTimePointProducedContainer value) {
        this.countsPerVOIAtTimePointProducedContainer = value;
    }

    /**
     * Obtient la valeur de la propriété nmTomoReconUsed.
     * 
     * @return
     *     possible object is
     *     {@link DICOMDataContainer }
     *     
     */
    public DICOMDataContainer getNMTomoReconUsed() {
        return nmTomoReconUsed;
    }

    /**
     * Définit la valeur de la propriété nmTomoReconUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMDataContainer }
     *     
     */
    public void setNMTomoReconUsed(DICOMDataContainer value) {
        this.nmTomoReconUsed = value;
    }

    /**
     * Obtient la valeur de la propriété ctReconUsed.
     * 
     * @return
     *     possible object is
     *     {@link DICOMDataContainer }
     *     
     */
    public DICOMDataContainer getCTReconUsed() {
        return ctReconUsed;
    }

    /**
     * Définit la valeur de la propriété ctReconUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMDataContainer }
     *     
     */
    public void setCTReconUsed(DICOMDataContainer value) {
        this.ctReconUsed = value;
    }

    /**
     * Obtient la valeur de la propriété imageProcessingMethodMethodUsed.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImageProcessingMethodMethodUsed() {
        return imageProcessingMethodMethodUsed;
    }

    /**
     * Définit la valeur de la propriété imageProcessingMethodMethodUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImageProcessingMethodMethodUsed(String value) {
        this.imageProcessingMethodMethodUsed = value;
    }

    /**
     * Obtient la valeur de la propriété segmentation.
     * 
     * @return
     *     possible object is
     *     {@link Segmentation }
     *     
     */
    public Segmentation getSegmentation() {
        return segmentation;
    }

    /**
     * Définit la valeur de la propriété segmentation.
     * 
     * @param value
     *     allowed object is
     *     {@link Segmentation }
     *     
     */
    public void setSegmentation(Segmentation value) {
        this.segmentation = value;
    }

    /**
     * Obtient la valeur de la propriété ctReconResampledOnCommonReferenceProduced.
     * 
     * @return
     *     possible object is
     *     {@link DICOMDataContainer }
     *     
     */
    public DICOMDataContainer getCTReconResampledOnCommonReferenceProduced() {
        return ctReconResampledOnCommonReferenceProduced;
    }

    /**
     * Définit la valeur de la propriété ctReconResampledOnCommonReferenceProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMDataContainer }
     *     
     */
    public void setCTReconResampledOnCommonReferenceProduced(DICOMDataContainer value) {
        this.ctReconResampledOnCommonReferenceProduced = value;
    }

    /**
     * Obtient la valeur de la propriété nmTomoReconResampledOnCommonReferenceProduced.
     * 
     * @return
     *     possible object is
     *     {@link DICOMDataContainer }
     *     
     */
    public DICOMDataContainer getNMTomoReconResampledOnCommonReferenceProduced() {
        return nmTomoReconResampledOnCommonReferenceProduced;
    }

    /**
     * Définit la valeur de la propriété nmTomoReconResampledOnCommonReferenceProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMDataContainer }
     *     
     */
    public void setNMTomoReconResampledOnCommonReferenceProduced(DICOMDataContainer value) {
        this.nmTomoReconResampledOnCommonReferenceProduced = value;
    }

    /**
     * Obtient la valeur de la propriété nonDICOMCTReconResampledOnCommonReferenceProduced.
     * 
     * @return
     *     possible object is
     *     {@link NonDICOMDataContainer }
     *     
     */
    public NonDICOMDataContainer getNonDICOMCTReconResampledOnCommonReferenceProduced() {
        return nonDICOMCTReconResampledOnCommonReferenceProduced;
    }

    /**
     * Définit la valeur de la propriété nonDICOMCTReconResampledOnCommonReferenceProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link NonDICOMDataContainer }
     *     
     */
    public void setNonDICOMCTReconResampledOnCommonReferenceProduced(NonDICOMDataContainer value) {
        this.nonDICOMCTReconResampledOnCommonReferenceProduced = value;
    }

    /**
     * Obtient la valeur de la propriété nonDICOMNMTomoReconResampledOnCommonReferenceProduced.
     * 
     * @return
     *     possible object is
     *     {@link NonDICOMDataContainer }
     *     
     */
    public NonDICOMDataContainer getNonDICOMNMTomoReconResampledOnCommonReferenceProduced() {
        return nonDICOMNMTomoReconResampledOnCommonReferenceProduced;
    }

    /**
     * Définit la valeur de la propriété nonDICOMNMTomoReconResampledOnCommonReferenceProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link NonDICOMDataContainer }
     *     
     */
    public void setNonDICOMNMTomoReconResampledOnCommonReferenceProduced(NonDICOMDataContainer value) {
        this.nonDICOMNMTomoReconResampledOnCommonReferenceProduced = value;
    }

    /**
     * Obtient la valeur de la propriété densityImageProduced.
     * 
     * @return
     *     possible object is
     *     {@link NonDICOMDataContainer }
     *     
     */
    public NonDICOMDataContainer getDensityImageProduced() {
        return densityImageProduced;
    }

    /**
     * Définit la valeur de la propriété densityImageProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link NonDICOMDataContainer }
     *     
     */
    public void setDensityImageProduced(NonDICOMDataContainer value) {
        this.densityImageProduced = value;
    }

    /**
     * Obtient la valeur de la propriété geometricalTransformationContainer.
     * 
     * @return
     *     possible object is
     *     {@link GeometricalTransformationContainer }
     *     
     */
    public GeometricalTransformationContainer getGeometricalTransformationContainer() {
        return geometricalTransformationContainer;
    }

    /**
     * Définit la valeur de la propriété geometricalTransformationContainer.
     * 
     * @param value
     *     allowed object is
     *     {@link GeometricalTransformationContainer }
     *     
     */
    public void setGeometricalTransformationContainer(GeometricalTransformationContainer value) {
        this.geometricalTransformationContainer = value;
    }

}
