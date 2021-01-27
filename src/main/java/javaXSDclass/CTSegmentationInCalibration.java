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
 * <p>Classe Java pour CTSegmentationInCalibration complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CTSegmentationInCalibration">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="NMTomoReconUsed" type="{https://www.irdbb-medirad.com}DICOMData"/>
 *         &lt;element name="CTReconUsed" type="{https://www.irdbb-medirad.com}DICOMData"/>
 *         &lt;element name="SegmentationMethodMethodUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VOIInCTProduced" type="{https://www.irdbb-medirad.com}VOIInCTcontainer"/>
 *         &lt;element name="VOIInSPECTProduced" type="{https://www.irdbb-medirad.com}VOIInSPECTcontainer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CTSegmentationInCalibration", propOrder = {
    "processExecutionContext",
    "nmTomoReconUsed",
    "ctReconUsed",
    "segmentationMethodMethodUsed",
    "voiInCTProduced",
    "voiInSPECTProduced"
})
public class CTSegmentationInCalibration {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "NMTomoReconUsed", required = true)
    protected DICOMData nmTomoReconUsed;
    @XmlElement(name = "CTReconUsed", required = true)
    protected DICOMData ctReconUsed;
    @XmlElement(name = "SegmentationMethodMethodUsed", required = true)
    protected String segmentationMethodMethodUsed;
    @XmlElement(name = "VOIInCTProduced", required = true)
    protected VOIInCTcontainer voiInCTProduced;
    @XmlElement(name = "VOIInSPECTProduced", required = true)
    protected VOIInSPECTcontainer voiInSPECTProduced;

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
     * Obtient la valeur de la propriété segmentationMethodMethodUsed.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegmentationMethodMethodUsed() {
        return segmentationMethodMethodUsed;
    }

    /**
     * Définit la valeur de la propriété segmentationMethodMethodUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegmentationMethodMethodUsed(String value) {
        this.segmentationMethodMethodUsed = value;
    }

    /**
     * Obtient la valeur de la propriété voiInCTProduced.
     * 
     * @return
     *     possible object is
     *     {@link VOIInCTcontainer }
     *     
     */
    public VOIInCTcontainer getVOIInCTProduced() {
        return voiInCTProduced;
    }

    /**
     * Définit la valeur de la propriété voiInCTProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link VOIInCTcontainer }
     *     
     */
    public void setVOIInCTProduced(VOIInCTcontainer value) {
        this.voiInCTProduced = value;
    }

    /**
     * Obtient la valeur de la propriété voiInSPECTProduced.
     * 
     * @return
     *     possible object is
     *     {@link VOIInSPECTcontainer }
     *     
     */
    public VOIInSPECTcontainer getVOIInSPECTProduced() {
        return voiInSPECTProduced;
    }

    /**
     * Définit la valeur de la propriété voiInSPECTProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link VOIInSPECTcontainer }
     *     
     */
    public void setVOIInSPECTProduced(VOIInSPECTcontainer value) {
        this.voiInSPECTProduced = value;
    }

}
