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
 * <p>Classe Java pour RegistrationVOISegmentation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="RegistrationVOISegmentation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="TimePointIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NMTomoReconUsed" type="{https://www.irdbb-medirad.com}DICOMData"/>
 *         &lt;element name="CTReconUsed" type="{https://www.irdbb-medirad.com}DICOMData"/>
 *         &lt;element name="RegistrationAndSegmentationMethodMethodUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CountsPerVOIAtTimePointProduced" type="{https://www.irdbb-medirad.com}CountsPerVOIAtTimePointContainer"/>
 *         &lt;element name="VOIInSPECTProduced" type="{https://www.irdbb-medirad.com}VOIIdentifierContainer"/>
 *         &lt;element name="CTReconResampledOnSPECTProduced" type="{https://www.irdbb-medirad.com}DICOMData"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegistrationVOISegmentation", propOrder = {
    "processExecutionContext",
    "timePointIdentifierUsed",
    "nmTomoReconUsed",
    "ctReconUsed",
    "registrationAndSegmentationMethodMethodUsed",
    "countsPerVOIAtTimePointProduced",
    "voiInSPECTProduced",
    "ctReconResampledOnSPECTProduced"
})
public class RegistrationVOISegmentation {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "TimePointIdentifierUsed", required = true)
    protected String timePointIdentifierUsed;
    @XmlElement(name = "NMTomoReconUsed", required = true)
    protected DICOMData nmTomoReconUsed;
    @XmlElement(name = "CTReconUsed", required = true)
    protected DICOMData ctReconUsed;
    @XmlElement(name = "RegistrationAndSegmentationMethodMethodUsed", required = true)
    protected String registrationAndSegmentationMethodMethodUsed;
    @XmlElement(name = "CountsPerVOIAtTimePointProduced", required = true)
    protected CountsPerVOIAtTimePointContainer countsPerVOIAtTimePointProduced;
    @XmlElement(name = "VOIInSPECTProduced", required = true)
    protected VOIIdentifierContainer voiInSPECTProduced;
    @XmlElement(name = "CTReconResampledOnSPECTProduced", required = true)
    protected DICOMData ctReconResampledOnSPECTProduced;

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
     * Obtient la valeur de la propriété registrationAndSegmentationMethodMethodUsed.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistrationAndSegmentationMethodMethodUsed() {
        return registrationAndSegmentationMethodMethodUsed;
    }

    /**
     * Définit la valeur de la propriété registrationAndSegmentationMethodMethodUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistrationAndSegmentationMethodMethodUsed(String value) {
        this.registrationAndSegmentationMethodMethodUsed = value;
    }

    /**
     * Obtient la valeur de la propriété countsPerVOIAtTimePointProduced.
     * 
     * @return
     *     possible object is
     *     {@link CountsPerVOIAtTimePointContainer }
     *     
     */
    public CountsPerVOIAtTimePointContainer getCountsPerVOIAtTimePointProduced() {
        return countsPerVOIAtTimePointProduced;
    }

    /**
     * Définit la valeur de la propriété countsPerVOIAtTimePointProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link CountsPerVOIAtTimePointContainer }
     *     
     */
    public void setCountsPerVOIAtTimePointProduced(CountsPerVOIAtTimePointContainer value) {
        this.countsPerVOIAtTimePointProduced = value;
    }

    /**
     * Obtient la valeur de la propriété voiInSPECTProduced.
     * 
     * @return
     *     possible object is
     *     {@link VOIIdentifierContainer }
     *     
     */
    public VOIIdentifierContainer getVOIInSPECTProduced() {
        return voiInSPECTProduced;
    }

    /**
     * Définit la valeur de la propriété voiInSPECTProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link VOIIdentifierContainer }
     *     
     */
    public void setVOIInSPECTProduced(VOIIdentifierContainer value) {
        this.voiInSPECTProduced = value;
    }

    /**
     * Obtient la valeur de la propriété ctReconResampledOnSPECTProduced.
     * 
     * @return
     *     possible object is
     *     {@link DICOMData }
     *     
     */
    public DICOMData getCTReconResampledOnSPECTProduced() {
        return ctReconResampledOnSPECTProduced;
    }

    /**
     * Définit la valeur de la propriété ctReconResampledOnSPECTProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMData }
     *     
     */
    public void setCTReconResampledOnSPECTProduced(DICOMData value) {
        this.ctReconResampledOnSPECTProduced = value;
    }

}
