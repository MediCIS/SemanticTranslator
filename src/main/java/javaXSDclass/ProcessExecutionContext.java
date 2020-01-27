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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour ProcessExecutionContext complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ProcessExecutionContext">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DateTimeProcessStarted" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PerformingInstitution" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcessExecutionContext", propOrder = {
    "dateTimeProcessStarted",
    "performingInstitution"
})
public class ProcessExecutionContext {

    @XmlElement(name = "DateTimeProcessStarted", required = true)
    protected String dateTimeProcessStarted;
    @XmlElement(name = "PerformingInstitution", required = true)
    protected String performingInstitution;

    /**
     * Obtient la valeur de la propriété dateTimeProcessStarted.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateTimeProcessStarted() {
        return dateTimeProcessStarted;
    }

    /**
     * Définit la valeur de la propriété dateTimeProcessStarted.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateTimeProcessStarted(String value) {
        this.dateTimeProcessStarted = value;
    }

    /**
     * Obtient la valeur de la propriété performingInstitution.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPerformingInstitution() {
        return performingInstitution;
    }

    /**
     * Définit la valeur de la propriété performingInstitution.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPerformingInstitution(String value) {
        this.performingInstitution = value;
    }

}
