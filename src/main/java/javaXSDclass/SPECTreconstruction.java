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
 * <p>Classe Java pour SPECTReconstruction complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SPECTReconstruction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="TimePointIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NMTomoUsed" type="{https://www.irdbb-medirad.com}DICOMData"/>
 *         &lt;element name="CTReconUsed" type="{https://www.irdbb-medirad.com}DICOMData"/>
 *         &lt;element name="ReconstructionMethodAndCorrectionsUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CTNumberCalibrationCurveUsed" type="{https://www.irdbb-medirad.com}CTNumberCalibrationCurve" minOccurs="0"/>
 *         &lt;element name="CTNumberCalibrationCurveReference" type="{https://www.irdbb-medirad.com}CTNumberCalibrationCurveReference" minOccurs="0"/>
 *         &lt;element name="NMTomoReconProduced" type="{https://www.irdbb-medirad.com}DICOMData"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SPECTReconstruction", propOrder = {
    "processExecutionContext",
    "timePointIdentifierUsed",
    "nmTomoUsed",
    "ctReconUsed",
    "reconstructionMethodAndCorrectionsUsed",
    "ctNumberCalibrationCurveUsed",
    "ctNumberCalibrationCurveReference",
    "nmTomoReconProduced"
})
public class SPECTReconstruction {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "TimePointIdentifierUsed", required = true)
    protected String timePointIdentifierUsed;
    @XmlElement(name = "NMTomoUsed", required = true)
    protected DICOMData nmTomoUsed;
    @XmlElement(name = "CTReconUsed", required = true)
    protected DICOMData ctReconUsed;
    @XmlElement(name = "ReconstructionMethodAndCorrectionsUsed", required = true)
    protected String reconstructionMethodAndCorrectionsUsed;
    @XmlElement(name = "CTNumberCalibrationCurveUsed")
    protected CTNumberCalibrationCurve ctNumberCalibrationCurveUsed;
    @XmlElement(name = "CTNumberCalibrationCurveReference")
    protected CTNumberCalibrationCurveReference ctNumberCalibrationCurveReference;
    @XmlElement(name = "NMTomoReconProduced", required = true)
    protected DICOMData nmTomoReconProduced;

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
     * Obtient la valeur de la propriété nmTomoUsed.
     * 
     * @return
     *     possible object is
     *     {@link DICOMData }
     *     
     */
    public DICOMData getNMTomoUsed() {
        return nmTomoUsed;
    }

    /**
     * Définit la valeur de la propriété nmTomoUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMData }
     *     
     */
    public void setNMTomoUsed(DICOMData value) {
        this.nmTomoUsed = value;
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
     * Obtient la valeur de la propriété reconstructionMethodAndCorrectionsUsed.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReconstructionMethodAndCorrectionsUsed() {
        return reconstructionMethodAndCorrectionsUsed;
    }

    /**
     * Définit la valeur de la propriété reconstructionMethodAndCorrectionsUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReconstructionMethodAndCorrectionsUsed(String value) {
        this.reconstructionMethodAndCorrectionsUsed = value;
    }

    /**
     * Obtient la valeur de la propriété ctNumberCalibrationCurveUsed.
     * 
     * @return
     *     possible object is
     *     {@link CTNumberCalibrationCurve }
     *     
     */
    public CTNumberCalibrationCurve getCTNumberCalibrationCurveUsed() {
        return ctNumberCalibrationCurveUsed;
    }

    /**
     * Définit la valeur de la propriété ctNumberCalibrationCurveUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link CTNumberCalibrationCurve }
     *     
     */
    public void setCTNumberCalibrationCurveUsed(CTNumberCalibrationCurve value) {
        this.ctNumberCalibrationCurveUsed = value;
    }

    /**
     * Obtient la valeur de la propriété ctNumberCalibrationCurveReference.
     * 
     * @return
     *     possible object is
     *     {@link CTNumberCalibrationCurveReference }
     *     
     */
    public CTNumberCalibrationCurveReference getCTNumberCalibrationCurveReference() {
        return ctNumberCalibrationCurveReference;
    }

    /**
     * Définit la valeur de la propriété ctNumberCalibrationCurveReference.
     * 
     * @param value
     *     allowed object is
     *     {@link CTNumberCalibrationCurveReference }
     *     
     */
    public void setCTNumberCalibrationCurveReference(CTNumberCalibrationCurveReference value) {
        this.ctNumberCalibrationCurveReference = value;
    }

    /**
     * Obtient la valeur de la propriété nmTomoReconProduced.
     * 
     * @return
     *     possible object is
     *     {@link DICOMData }
     *     
     */
    public DICOMData getNMTomoReconProduced() {
        return nmTomoReconProduced;
    }

    /**
     * Définit la valeur de la propriété nmTomoReconProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMData }
     *     
     */
    public void setNMTomoReconProduced(DICOMData value) {
        this.nmTomoReconProduced = value;
    }

}
