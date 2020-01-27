//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.01.24 à 06:05:30 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour DICOMSOPInstanceType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DICOMSOPInstanceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DICOMSOPInstanceUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DICOMSOPClassUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DICOMSOPInstanceType", propOrder = {
    "dicomsopInstanceUID",
    "dicomsopClassUID"
})
public class DICOMSOPInstanceType {

    @XmlElement(name = "DICOMSOPInstanceUID", required = true)
    protected String dicomsopInstanceUID;
    @XmlElement(name = "DICOMSOPClassUID", required = true)
    protected String dicomsopClassUID;

    /**
     * Obtient la valeur de la propriété dicomsopInstanceUID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDICOMSOPInstanceUID() {
        return dicomsopInstanceUID;
    }

    /**
     * Définit la valeur de la propriété dicomsopInstanceUID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDICOMSOPInstanceUID(String value) {
        this.dicomsopInstanceUID = value;
    }

    /**
     * Obtient la valeur de la propriété dicomsopClassUID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDICOMSOPClassUID() {
        return dicomsopClassUID;
    }

    /**
     * Définit la valeur de la propriété dicomsopClassUID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDICOMSOPClassUID(String value) {
        this.dicomsopClassUID = value;
    }

}
