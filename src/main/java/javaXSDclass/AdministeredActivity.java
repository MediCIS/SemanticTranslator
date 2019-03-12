//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2018.12.11 à 04:47:30 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour AdministeredActivity complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="AdministeredActivity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AdministeredActivityValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="AdministeredActivityUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Timestamp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdministeredActivity", propOrder = {
    "administeredActivityValue",
    "administeredActivityUnit",
    "timestamp"
})
public class AdministeredActivity {

    @XmlElement(name = "AdministeredActivityValue")
    protected float administeredActivityValue;
    @XmlElement(name = "AdministeredActivityUnit", required = true)
    protected String administeredActivityUnit;
    @XmlElement(name = "Timestamp", required = true)
    protected String timestamp;

    /**
     * Obtient la valeur de la propriété administeredActivityValue.
     * 
     */
    public float getAdministeredActivityValue() {
        return administeredActivityValue;
    }

    /**
     * Définit la valeur de la propriété administeredActivityValue.
     * 
     */
    public void setAdministeredActivityValue(float value) {
        this.administeredActivityValue = value;
    }

    /**
     * Obtient la valeur de la propriété administeredActivityUnit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdministeredActivityUnit() {
        return administeredActivityUnit;
    }

    /**
     * Définit la valeur de la propriété administeredActivityUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdministeredActivityUnit(String value) {
        this.administeredActivityUnit = value;
    }

    /**
     * Obtient la valeur de la propriété timestamp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Définit la valeur de la propriété timestamp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimestamp(String value) {
        this.timestamp = value;
    }

}
