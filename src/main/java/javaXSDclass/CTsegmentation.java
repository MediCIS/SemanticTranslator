//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.01.24 à 06:05:31 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CTSegmentation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CTSegmentation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="DICOMImageUsed" type="{https://www.irdbb-medirad.com}DICOMData"/>
 *         &lt;element name="SegmentationMethodUsed" type="{https://www.irdbb-medirad.com}SegmentationMethodType" minOccurs="0"/>
 *         &lt;element name="VOIDescriptorProduced" type="{https://www.irdbb-medirad.com}VOIList"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CTSegmentation", propOrder = {
    "processExecutionContext",
    "dicomImageUsed",
    "segmentationMethodUsed",
    "voiDescriptorProduced"
})
public class CTSegmentation {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "DICOMImageUsed", required = true)
    protected DICOMData dicomImageUsed;
    @XmlElement(name = "SegmentationMethodUsed")
    protected SegmentationMethodType segmentationMethodUsed;
    @XmlElement(name = "VOIDescriptorProduced", required = true)
    protected VOIList voiDescriptorProduced;

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
     * Obtient la valeur de la propriété dicomImageUsed.
     * 
     * @return
     *     possible object is
     *     {@link DICOMData }
     *     
     */
    public DICOMData getDICOMImageUsed() {
        return dicomImageUsed;
    }

    /**
     * Définit la valeur de la propriété dicomImageUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMData }
     *     
     */
    public void setDICOMImageUsed(DICOMData value) {
        this.dicomImageUsed = value;
    }

    /**
     * Obtient la valeur de la propriété segmentationMethodUsed.
     * 
     * @return
     *     possible object is
     *     {@link SegmentationMethodType }
     *     
     */
    public SegmentationMethodType getSegmentationMethodUsed() {
        return segmentationMethodUsed;
    }

    /**
     * Définit la valeur de la propriété segmentationMethodUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link SegmentationMethodType }
     *     
     */
    public void setSegmentationMethodUsed(SegmentationMethodType value) {
        this.segmentationMethodUsed = value;
    }

    /**
     * Obtient la valeur de la propriété voiDescriptorProduced.
     * 
     * @return
     *     possible object is
     *     {@link VOIList }
     *     
     */
    public VOIList getVOIDescriptorProduced() {
        return voiDescriptorProduced;
    }

    /**
     * Définit la valeur de la propriété voiDescriptorProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link VOIList }
     *     
     */
    public void setVOIDescriptorProduced(VOIList value) {
        this.voiDescriptorProduced = value;
    }

}
