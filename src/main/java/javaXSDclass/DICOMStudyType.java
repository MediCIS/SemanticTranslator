//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2018.12.11 à 04:47:29 PM CET 
//


package javaXSDclass;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour DICOMStudyType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DICOMStudyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DICOMStudyDescriptor" type="{https://www.irdbb-medirad.com}DICOMStudyDescriptorType"/>
 *         &lt;element name="DICOMSeries" type="{https://www.irdbb-medirad.com}DICOMSeriesType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DICOMStudyType", propOrder = {
    "dicomStudyDescriptor",
    "dicomSeries"
})
public class DICOMStudyType {

    @XmlElement(name = "DICOMStudyDescriptor", required = true)
    protected DICOMStudyDescriptorType dicomStudyDescriptor;
    @XmlElement(name = "DICOMSeries", required = true)
    protected List<DICOMSeriesType> dicomSeries;

    /**
     * Obtient la valeur de la propriété dicomStudyDescriptor.
     * 
     * @return
     *     possible object is
     *     {@link DICOMStudyDescriptorType }
     *     
     */
    public DICOMStudyDescriptorType getDICOMStudyDescriptor() {
        return dicomStudyDescriptor;
    }

    /**
     * Définit la valeur de la propriété dicomStudyDescriptor.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMStudyDescriptorType }
     *     
     */
    public void setDICOMStudyDescriptor(DICOMStudyDescriptorType value) {
        this.dicomStudyDescriptor = value;
    }

    /**
     * Gets the value of the dicomSeries property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dicomSeries property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDICOMSeries().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DICOMSeriesType }
     * 
     * 
     */
    public List<DICOMSeriesType> getDICOMSeries() {
        if (dicomSeries == null) {
            dicomSeries = new ArrayList<DICOMSeriesType>();
        }
        return this.dicomSeries;
    }

}
