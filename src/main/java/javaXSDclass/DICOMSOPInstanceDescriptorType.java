//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.09.16 à 04:44:09 PM CEST 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour DICOMSOPInstanceDescriptorType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DICOMSOPInstanceDescriptorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="CTImageAcquisitionDescriptor" type="{https://www.irdbb-medirad.com}CTImageAcquisitionDescriptorType"/>
 *         &lt;element name="NMImageAcquisitionDescriptor" type="{https://www.irdbb-medirad.com}NMImageAcquisitionDescriptorType"/>
 *         &lt;element name="PETImageAcquisitionDescriptor" type="{https://www.irdbb-medirad.com}PETImageAcquisitionDescriptorType"/>
 *         &lt;element name="StructuredReportDescriptor" type="{https://www.irdbb-medirad.com}StructuredReportDescriptorType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DICOMSOPInstanceDescriptorType", propOrder = {
    "ctImageAcquisitionDescriptor",
    "nmImageAcquisitionDescriptor",
    "petImageAcquisitionDescriptor",
    "structuredReportDescriptor"
})
public class DICOMSOPInstanceDescriptorType {

    @XmlElement(name = "CTImageAcquisitionDescriptor")
    protected CTImageAcquisitionDescriptorType ctImageAcquisitionDescriptor;
    @XmlElement(name = "NMImageAcquisitionDescriptor")
    protected NMImageAcquisitionDescriptorType nmImageAcquisitionDescriptor;
    @XmlElement(name = "PETImageAcquisitionDescriptor")
    protected PETImageAcquisitionDescriptorType petImageAcquisitionDescriptor;
    @XmlElement(name = "StructuredReportDescriptor")
    protected StructuredReportDescriptorType structuredReportDescriptor;

    /**
     * Obtient la valeur de la propriété ctImageAcquisitionDescriptor.
     * 
     * @return
     *     possible object is
     *     {@link CTImageAcquisitionDescriptorType }
     *     
     */
    public CTImageAcquisitionDescriptorType getCTImageAcquisitionDescriptor() {
        return ctImageAcquisitionDescriptor;
    }

    /**
     * Définit la valeur de la propriété ctImageAcquisitionDescriptor.
     * 
     * @param value
     *     allowed object is
     *     {@link CTImageAcquisitionDescriptorType }
     *     
     */
    public void setCTImageAcquisitionDescriptor(CTImageAcquisitionDescriptorType value) {
        this.ctImageAcquisitionDescriptor = value;
    }

    /**
     * Obtient la valeur de la propriété nmImageAcquisitionDescriptor.
     * 
     * @return
     *     possible object is
     *     {@link NMImageAcquisitionDescriptorType }
     *     
     */
    public NMImageAcquisitionDescriptorType getNMImageAcquisitionDescriptor() {
        return nmImageAcquisitionDescriptor;
    }

    /**
     * Définit la valeur de la propriété nmImageAcquisitionDescriptor.
     * 
     * @param value
     *     allowed object is
     *     {@link NMImageAcquisitionDescriptorType }
     *     
     */
    public void setNMImageAcquisitionDescriptor(NMImageAcquisitionDescriptorType value) {
        this.nmImageAcquisitionDescriptor = value;
    }

    /**
     * Obtient la valeur de la propriété petImageAcquisitionDescriptor.
     * 
     * @return
     *     possible object is
     *     {@link PETImageAcquisitionDescriptorType }
     *     
     */
    public PETImageAcquisitionDescriptorType getPETImageAcquisitionDescriptor() {
        return petImageAcquisitionDescriptor;
    }

    /**
     * Définit la valeur de la propriété petImageAcquisitionDescriptor.
     * 
     * @param value
     *     allowed object is
     *     {@link PETImageAcquisitionDescriptorType }
     *     
     */
    public void setPETImageAcquisitionDescriptor(PETImageAcquisitionDescriptorType value) {
        this.petImageAcquisitionDescriptor = value;
    }

    /**
     * Obtient la valeur de la propriété structuredReportDescriptor.
     * 
     * @return
     *     possible object is
     *     {@link StructuredReportDescriptorType }
     *     
     */
    public StructuredReportDescriptorType getStructuredReportDescriptor() {
        return structuredReportDescriptor;
    }

    /**
     * Définit la valeur de la propriété structuredReportDescriptor.
     * 
     * @param value
     *     allowed object is
     *     {@link StructuredReportDescriptorType }
     *     
     */
    public void setStructuredReportDescriptor(StructuredReportDescriptorType value) {
        this.structuredReportDescriptor = value;
    }

}
