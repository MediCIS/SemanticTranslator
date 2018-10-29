//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2018.10.16 à 03:58:02 PM CEST 
//


package javaXSDclass;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour DICOMDataType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DICOMDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DICOMStudyUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DICOMSeriesUID" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DICOMDataType", propOrder = {
    "dicomStudyUID",
    "dicomSeriesUID"
})
public class DICOMDataType {

    @XmlElement(name = "DICOMStudyUID", required = true)
    protected String dicomStudyUID;
    @XmlElement(name = "DICOMSeriesUID", required = true)
    protected List<String> dicomSeriesUID;

    /**
     * Obtient la valeur de la propriété dicomStudyUID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDICOMStudyUID() {
        return dicomStudyUID;
    }

    /**
     * Définit la valeur de la propriété dicomStudyUID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDICOMStudyUID(String value) {
        this.dicomStudyUID = value;
    }

    /**
     * Gets the value of the dicomSeriesUID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dicomSeriesUID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDICOMSeriesUID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDICOMSeriesUID() {
        if (dicomSeriesUID == null) {
            dicomSeriesUID = new ArrayList<String>();
        }
        return this.dicomSeriesUID;
    }

}
