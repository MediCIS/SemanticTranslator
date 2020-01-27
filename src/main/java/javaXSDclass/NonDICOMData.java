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
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="NonDICOMDataClass" type="{https://www.irdbb-medirad.com}NonDICOMDataClass"/>
 *         &lt;element name="NonDICOMDataFormat" type="{https://www.irdbb-medirad.com}NonDICOMDataFormat"/>
 *         &lt;element name="FileNameList" type="{https://www.irdbb-medirad.com}FileNameList"/>
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
    "fileNameList",
    "fhirIdentifier"
})
public class NonDICOMData {

    @XmlElement(name = "NonDICOMDataClass", required = true)
    protected String nonDICOMDataClass;
    @XmlElement(name = "NonDICOMDataFormat", required = true)
    @XmlSchemaType(name = "string")
    protected NonDICOMDataFormat nonDICOMDataFormat;
    @XmlElement(name = "FileNameList", required = true)
    protected FileNameList fileNameList;
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
     *     {@link NonDICOMDataFormat }
     *     
     */
    public NonDICOMDataFormat getNonDICOMDataFormat() {
        return nonDICOMDataFormat;
    }

    /**
     * Définit la valeur de la propriété nonDICOMDataFormat.
     * 
     * @param value
     *     allowed object is
     *     {@link NonDICOMDataFormat }
     *     
     */
    public void setNonDICOMDataFormat(NonDICOMDataFormat value) {
        this.nonDICOMDataFormat = value;
    }

    /**
     * Obtient la valeur de la propriété fileNameList.
     * 
     * @return
     *     possible object is
     *     {@link FileNameList }
     *     
     */
    public FileNameList getFileNameList() {
        return fileNameList;
    }

    /**
     * Définit la valeur de la propriété fileNameList.
     * 
     * @param value
     *     allowed object is
     *     {@link FileNameList }
     *     
     */
    public void setFileNameList(FileNameList value) {
        this.fileNameList = value;
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
