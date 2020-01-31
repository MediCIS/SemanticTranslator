//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.01.28 à 04:59:06 PM CET 
//


package javaXSDclass;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour DICOMSeriesType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DICOMSeriesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DICOMSeriesDescriptor" type="{https://www.irdbb-medirad.com}DICOMSeriesDescriptorType"/>
 *         &lt;element name="DICOMSOPInstanceDescriptor" type="{https://www.irdbb-medirad.com}DICOMSOPInstanceDescriptorType" maxOccurs="unbounded"/>
 *         &lt;element name="DICOMSOPInstance" type="{https://www.irdbb-medirad.com}DICOMSOPInstanceType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DICOMSeriesType", propOrder = {
    "dicomSeriesDescriptor",
    "dicomsopInstanceDescriptor",
    "dicomsopInstance"
})
public class DICOMSeriesType {

    @XmlElement(name = "DICOMSeriesDescriptor", required = true)
    protected DICOMSeriesDescriptorType dicomSeriesDescriptor;
    @XmlElement(name = "DICOMSOPInstanceDescriptor", required = true)
    protected List<DICOMSOPInstanceDescriptorType> dicomsopInstanceDescriptor;
    @XmlElement(name = "DICOMSOPInstance", required = true)
    protected List<DICOMSOPInstanceType> dicomsopInstance;

    /**
     * Obtient la valeur de la propriété dicomSeriesDescriptor.
     * 
     * @return
     *     possible object is
     *     {@link DICOMSeriesDescriptorType }
     *     
     */
    public DICOMSeriesDescriptorType getDICOMSeriesDescriptor() {
        return dicomSeriesDescriptor;
    }

    /**
     * Définit la valeur de la propriété dicomSeriesDescriptor.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMSeriesDescriptorType }
     *     
     */
    public void setDICOMSeriesDescriptor(DICOMSeriesDescriptorType value) {
        this.dicomSeriesDescriptor = value;
    }

    /**
     * Gets the value of the dicomsopInstanceDescriptor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dicomsopInstanceDescriptor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDICOMSOPInstanceDescriptor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DICOMSOPInstanceDescriptorType }
     * 
     * 
     */
    public List<DICOMSOPInstanceDescriptorType> getDICOMSOPInstanceDescriptor() {
        if (dicomsopInstanceDescriptor == null) {
            dicomsopInstanceDescriptor = new ArrayList<DICOMSOPInstanceDescriptorType>();
        }
        return this.dicomsopInstanceDescriptor;
    }

    /**
     * Gets the value of the dicomsopInstance property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dicomsopInstance property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDICOMSOPInstance().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DICOMSOPInstanceType }
     * 
     * 
     */
    public List<DICOMSOPInstanceType> getDICOMSOPInstance() {
        if (dicomsopInstance == null) {
            dicomsopInstance = new ArrayList<DICOMSOPInstanceType>();
        }
        return this.dicomsopInstance;
    }

}
