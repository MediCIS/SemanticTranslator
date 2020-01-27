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
 * <p>Classe Java pour OrganMass complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="OrganMass">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrganMassValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="OrganMassUnit" type="{https://www.irdbb-medirad.com}OrganMassUnit"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrganMass", propOrder = {
    "organMassValue",
    "organMassUnit"
})
public class OrganMass {

    @XmlElement(name = "OrganMassValue")
    protected float organMassValue;
    @XmlElement(name = "OrganMassUnit", required = true)
    @XmlSchemaType(name = "string")
    protected OrganMassUnit organMassUnit;

    /**
     * Obtient la valeur de la propriété organMassValue.
     * 
     */
    public float getOrganMassValue() {
        return organMassValue;
    }

    /**
     * Définit la valeur de la propriété organMassValue.
     * 
     */
    public void setOrganMassValue(float value) {
        this.organMassValue = value;
    }

    /**
     * Obtient la valeur de la propriété organMassUnit.
     * 
     * @return
     *     possible object is
     *     {@link OrganMassUnit }
     *     
     */
    public OrganMassUnit getOrganMassUnit() {
        return organMassUnit;
    }

    /**
     * Définit la valeur de la propriété organMassUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganMassUnit }
     *     
     */
    public void setOrganMassUnit(OrganMassUnit value) {
        this.organMassUnit = value;
    }

}
