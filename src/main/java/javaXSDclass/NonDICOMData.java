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
 * <p>Classe Java pour NonDICOMData complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="NonDICOMData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NonDICOMDataClass">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="VOI"/>
 *               &lt;enumeration value="VOI superimposed on images"/>
 *               &lt;enumeration value="3D dose map"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="NonDICOMDataFormat">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="zipped imageJ contours format"/>
 *               &lt;enumeration value="TIFF format embedding imageJ contours"/>
 *               &lt;enumeration value="zipped pseudo_DICOM ImpactMC"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="NonDICOMDataFileName" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="FHIRIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NonDICOMData", propOrder = {
    "nonDICOMDataClass",
    "nonDICOMDataFormat",
    "nonDICOMDataFileName",
    "fhirIdentifier"
})
public class NonDICOMData {

    @XmlElement(name = "NonDICOMDataClass", required = true)
    protected String nonDICOMDataClass;
    @XmlElement(name = "NonDICOMDataFormat", required = true)
    protected String nonDICOMDataFormat;
    @XmlElement(name = "NonDICOMDataFileName", required = true)
    protected List<String> nonDICOMDataFileName;
    @XmlElement(name = "FHIRIdentifier")
    protected String fhirIdentifier;

    /**
     * Obtient la valeur de la propriété nonDICOMDataClass.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNonDICOMDataClass() {
        return nonDICOMDataClass;
    }

    /**
     * Définit la valeur de la propriété nonDICOMDataClass.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNonDICOMDataClass(String value) {
        this.nonDICOMDataClass = value;
    }

    /**
     * Obtient la valeur de la propriété nonDICOMDataFormat.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNonDICOMDataFormat() {
        return nonDICOMDataFormat;
    }

    /**
     * Définit la valeur de la propriété nonDICOMDataFormat.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNonDICOMDataFormat(String value) {
        this.nonDICOMDataFormat = value;
    }

    /**
     * Gets the value of the nonDICOMDataFileName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nonDICOMDataFileName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNonDICOMDataFileName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getNonDICOMDataFileName() {
        if (nonDICOMDataFileName == null) {
            nonDICOMDataFileName = new ArrayList<String>();
        }
        return this.nonDICOMDataFileName;
    }

    /**
     * Obtient la valeur de la propriété fhirIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFHIRIdentifier() {
        return fhirIdentifier;
    }

    /**
     * Définit la valeur de la propriété fhirIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFHIRIdentifier(String value) {
        this.fhirIdentifier = value;
    }

}
