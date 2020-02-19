//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.12 à 04:30:41 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour ClinicalStudyType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ClinicalStudyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ClinicalStudyID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ClinicalStudyTitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClinicalStudyType", propOrder = {
    "clinicalStudyID",
    "clinicalStudyTitle"
})
public class ClinicalStudyType {

    @XmlElement(name = "ClinicalStudyID", required = true)
    protected String clinicalStudyID;
    @XmlElement(name = "ClinicalStudyTitle", required = true)
    protected String clinicalStudyTitle;

    /**
     * Obtient la valeur de la propriété clinicalStudyID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClinicalStudyID() {
        return clinicalStudyID;
    }

    /**
     * Définit la valeur de la propriété clinicalStudyID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClinicalStudyID(String value) {
        this.clinicalStudyID = value;
    }

    /**
     * Obtient la valeur de la propriété clinicalStudyTitle.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClinicalStudyTitle() {
        return clinicalStudyTitle;
    }

    /**
     * Définit la valeur de la propriété clinicalStudyTitle.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClinicalStudyTitle(String value) {
        this.clinicalStudyTitle = value;
    }

}
