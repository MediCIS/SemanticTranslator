//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.01.28 à 04:59:06 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour DICOMCodeSequenceType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DICOMCodeSequenceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodingSchemeDesignator-00080102" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CodeValue-00080100" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CodeMeaning-00080104" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DICOMCodeSequenceType", propOrder = {
    "codingSchemeDesignator00080102",
    "codeValue00080100",
    "codeMeaning00080104"
})
public class DICOMCodeSequenceType {

    @XmlElement(name = "CodingSchemeDesignator-00080102", required = true)
    protected String codingSchemeDesignator00080102;
    @XmlElement(name = "CodeValue-00080100", required = true)
    protected String codeValue00080100;
    @XmlElement(name = "CodeMeaning-00080104", required = true)
    protected String codeMeaning00080104;

    /**
     * Obtient la valeur de la propriété codingSchemeDesignator00080102.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodingSchemeDesignator00080102() {
        return codingSchemeDesignator00080102;
    }

    /**
     * Définit la valeur de la propriété codingSchemeDesignator00080102.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodingSchemeDesignator00080102(String value) {
        this.codingSchemeDesignator00080102 = value;
    }

    /**
     * Obtient la valeur de la propriété codeValue00080100.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeValue00080100() {
        return codeValue00080100;
    }

    /**
     * Définit la valeur de la propriété codeValue00080100.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeValue00080100(String value) {
        this.codeValue00080100 = value;
    }

    /**
     * Obtient la valeur de la propriété codeMeaning00080104.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeMeaning00080104() {
        return codeMeaning00080104;
    }

    /**
     * Définit la valeur de la propriété codeMeaning00080104.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeMeaning00080104(String value) {
        this.codeMeaning00080104 = value;
    }

}
