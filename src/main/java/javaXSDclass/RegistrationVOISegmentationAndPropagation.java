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
 *         &lt;element name="TimePointIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="NMTomoReconUsed" type="{https://www.irdbb-medirad.com}DICOMData" maxOccurs="unbounded"/>
 *         &lt;element name="CTReconUsed" type="{https://www.irdbb-medirad.com}DICOMData" maxOccurs="unbounded"/>
 *         &lt;element name="ImageProcessingMethodMethodUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CountsPerVOIAtTimePointProduced" type="{https://www.irdbb-medirad.com}CountsPerVOIAtTimePoint" maxOccurs="unbounded"/>
 *         &lt;element name="VOIProduced" type="{https://www.irdbb-medirad.com}VOI" maxOccurs="unbounded"/>
 *         &lt;element name="CTReconResampledOnCommonReferenceProduced" type="{https://www.irdbb-medirad.com}DICOMData" maxOccurs="unbounded"/>
 *         &lt;element name="NMTomoReconResampledOnCommonReferenceProduced" type="{https://www.irdbb-medirad.com}DICOMData" maxOccurs="unbounded"/>
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
    "nmTomoReconUsed",
    "ctReconUsed",
    "imageProcessingMethodMethodUsed",
    "countsPerVOIAtTimePointProduced",
    "voiProduced",
    "ctReconResampledOnCommonReferenceProduced",
    "nmTomoReconResampledOnCommonReferenceProduced"
})
public class RegistrationVOISegmentationAndPropagation {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "TimePointIdentifierUsed", required = true)
    protected List<String> timePointIdentifierUsed;
    @XmlElement(name = "NMTomoReconUsed", required = true)
    protected List<DICOMData> nmTomoReconUsed;
    @XmlElement(name = "CTReconUsed", required = true)
    protected List<DICOMData> ctReconUsed;
    @XmlElement(name = "ImageProcessingMethodMethodUsed", required = true)
    protected String imageProcessingMethodMethodUsed;
    @XmlElement(name = "CountsPerVOIAtTimePointProduced", required = true)
    protected List<CountsPerVOIAtTimePoint> countsPerVOIAtTimePointProduced;
    @XmlElement(name = "VOIProduced", required = true)
    protected List<VOI> voiProduced;
    @XmlElement(name = "CTReconResampledOnCommonReferenceProduced", required = true)
    protected List<DICOMData> ctReconResampledOnCommonReferenceProduced;
    @XmlElement(name = "NMTomoReconResampledOnCommonReferenceProduced", required = true)
    protected List<DICOMData> nmTomoReconResampledOnCommonReferenceProduced;

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
     * Gets the value of the timePointIdentifierUsed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the timePointIdentifierUsed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTimePointIdentifierUsed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getTimePointIdentifierUsed() {
        if (timePointIdentifierUsed == null) {
            timePointIdentifierUsed = new ArrayList<String>();
        }
        return this.timePointIdentifierUsed;
    }

    /**
     * Gets the value of the nmTomoReconUsed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nmTomoReconUsed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNMTomoReconUsed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DICOMData }
     * 
     * 
     */
    public List<DICOMData> getNMTomoReconUsed() {
        if (nmTomoReconUsed == null) {
            nmTomoReconUsed = new ArrayList<DICOMData>();
        }
        return this.nmTomoReconUsed;
    }

    /**
     * Gets the value of the ctReconUsed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ctReconUsed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCTReconUsed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DICOMData }
     * 
     * 
     */
    public List<DICOMData> getCTReconUsed() {
        if (ctReconUsed == null) {
            ctReconUsed = new ArrayList<DICOMData>();
        }
        return this.ctReconUsed;
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
     * Gets the value of the countsPerVOIAtTimePointProduced property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the countsPerVOIAtTimePointProduced property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCountsPerVOIAtTimePointProduced().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CountsPerVOIAtTimePoint }
     * 
     * 
     */
    public List<CountsPerVOIAtTimePoint> getCountsPerVOIAtTimePointProduced() {
        if (countsPerVOIAtTimePointProduced == null) {
            countsPerVOIAtTimePointProduced = new ArrayList<CountsPerVOIAtTimePoint>();
        }
        return this.countsPerVOIAtTimePointProduced;
    }

    /**
     * Gets the value of the voiProduced property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the voiProduced property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVOIProduced().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VOI }
     * 
     * 
     */
    public List<VOI> getVOIProduced() {
        if (voiProduced == null) {
            voiProduced = new ArrayList<VOI>();
        }
        return this.voiProduced;
    }

    /**
     * Gets the value of the ctReconResampledOnCommonReferenceProduced property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ctReconResampledOnCommonReferenceProduced property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCTReconResampledOnCommonReferenceProduced().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DICOMData }
     * 
     * 
     */
    public List<DICOMData> getCTReconResampledOnCommonReferenceProduced() {
        if (ctReconResampledOnCommonReferenceProduced == null) {
            ctReconResampledOnCommonReferenceProduced = new ArrayList<DICOMData>();
        }
        return this.ctReconResampledOnCommonReferenceProduced;
    }

    /**
     * Gets the value of the nmTomoReconResampledOnCommonReferenceProduced property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nmTomoReconResampledOnCommonReferenceProduced property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNMTomoReconResampledOnCommonReferenceProduced().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DICOMData }
     * 
     * 
     */
    public List<DICOMData> getNMTomoReconResampledOnCommonReferenceProduced() {
        if (nmTomoReconResampledOnCommonReferenceProduced == null) {
            nmTomoReconResampledOnCommonReferenceProduced = new ArrayList<DICOMData>();
        }
        return this.nmTomoReconResampledOnCommonReferenceProduced;
    }

}
