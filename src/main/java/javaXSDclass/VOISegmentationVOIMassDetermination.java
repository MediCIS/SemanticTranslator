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
 *         &lt;element name="CountsPerVOIAtTimePointProduced" type="{https://www.irdbb-medirad.com}CountsPerVOIAtTimePoint" maxOccurs="unbounded"/>
 *         &lt;element name="VOIProduced" type="{https://www.irdbb-medirad.com}VOI" maxOccurs="unbounded"/>
 *         &lt;element name="CTReconResampledOnNMReferenceProduced" type="{https://www.irdbb-medirad.com}DICOMData" maxOccurs="unbounded"/>
 *         &lt;element name="MassPerVOIAtTimePointProduced" type="{https://www.irdbb-medirad.com}MassPerVOIAtTimePoint" maxOccurs="unbounded"/>
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
    "countsPerVOIAtTimePointProduced",
    "voiProduced",
    "ctReconResampledOnNMReferenceProduced",
    "massPerVOIAtTimePointProduced"
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
    @XmlElement(name = "CountsPerVOIAtTimePointProduced", required = true)
    protected List<CountsPerVOIAtTimePoint> countsPerVOIAtTimePointProduced;
    @XmlElement(name = "VOIProduced", required = true)
    protected List<VOI> voiProduced;
    @XmlElement(name = "CTReconResampledOnNMReferenceProduced", required = true)
    protected List<DICOMData> ctReconResampledOnNMReferenceProduced;
    @XmlElement(name = "MassPerVOIAtTimePointProduced", required = true)
    protected List<MassPerVOIAtTimePoint> massPerVOIAtTimePointProduced;

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
     * Gets the value of the ctReconResampledOnNMReferenceProduced property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ctReconResampledOnNMReferenceProduced property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCTReconResampledOnNMReferenceProduced().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DICOMData }
     * 
     * 
     */
    public List<DICOMData> getCTReconResampledOnNMReferenceProduced() {
        if (ctReconResampledOnNMReferenceProduced == null) {
            ctReconResampledOnNMReferenceProduced = new ArrayList<DICOMData>();
        }
        return this.ctReconResampledOnNMReferenceProduced;
    }

    /**
     * Gets the value of the massPerVOIAtTimePointProduced property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the massPerVOIAtTimePointProduced property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMassPerVOIAtTimePointProduced().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MassPerVOIAtTimePoint }
     * 
     * 
     */
    public List<MassPerVOIAtTimePoint> getMassPerVOIAtTimePointProduced() {
        if (massPerVOIAtTimePointProduced == null) {
            massPerVOIAtTimePointProduced = new ArrayList<MassPerVOIAtTimePoint>();
        }
        return this.massPerVOIAtTimePointProduced;
    }

}
