//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.01.28 à 04:59:07 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="ActivityUnit" type="{https://www.irdbb-medirad.com}ActivityUnit"/>
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
    "activityUnit"
})
public class AdministeredActivity {

    @XmlElement(name = "AdministeredActivityValue")
    protected float administeredActivityValue;
    @XmlElement(name = "ActivityUnit", required = true)
    @XmlSchemaType(name = "string")
    protected ActivityUnit activityUnit;

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
     * Obtient la valeur de la propriété activityUnit.
     * 
     * @return
     *     possible object is
     *     {@link ActivityUnit }
     *     
     */
    public ActivityUnit getActivityUnit() {
        return activityUnit;
    }

    /**
     * Définit la valeur de la propriété activityUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivityUnit }
     *     
     */
    public void setActivityUnit(ActivityUnit value) {
        this.activityUnit = value;
    }

}
