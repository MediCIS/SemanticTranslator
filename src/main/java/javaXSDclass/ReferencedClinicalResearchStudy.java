//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.12 à 04:30:42 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour ReferencedClinicalResearchStudy complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ReferencedClinicalResearchStudy">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ClinicalResearchStudyID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ClinicalResearchStudyTitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReferencedClinicalResearchStudy", propOrder = {
    "clinicalResearchStudyID",
    "clinicalResearchStudyTitle"
})
public class ReferencedClinicalResearchStudy {

    @XmlElement(name = "ClinicalResearchStudyID", required = true)
    protected String clinicalResearchStudyID;
    @XmlElement(name = "ClinicalResearchStudyTitle", required = true)
    protected String clinicalResearchStudyTitle;

    /**
     * Obtient la valeur de la propriété clinicalResearchStudyID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClinicalResearchStudyID() {
        return clinicalResearchStudyID;
    }

    /**
     * Définit la valeur de la propriété clinicalResearchStudyID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClinicalResearchStudyID(String value) {
        this.clinicalResearchStudyID = value;
    }

    /**
     * Obtient la valeur de la propriété clinicalResearchStudyTitle.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClinicalResearchStudyTitle() {
        return clinicalResearchStudyTitle;
    }

    /**
     * Définit la valeur de la propriété clinicalResearchStudyTitle.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClinicalResearchStudyTitle(String value) {
        this.clinicalResearchStudyTitle = value;
    }

}
