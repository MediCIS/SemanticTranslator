//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.07.30 à 02:06:36 PM CEST 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour EnergyWindowType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="EnergyWindowType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EnergyWindowName-00540018" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EnergyWindowLowerLimit-00540014" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EnergyWindowUpperLimit-00540015" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnergyWindowType", propOrder = {
    "energyWindowName00540018",
    "energyWindowLowerLimit00540014",
    "energyWindowUpperLimit00540015"
})
public class EnergyWindowType {

    @XmlElement(name = "EnergyWindowName-00540018", required = true)
    protected String energyWindowName00540018;
    @XmlElement(name = "EnergyWindowLowerLimit-00540014", required = true)
    protected String energyWindowLowerLimit00540014;
    @XmlElement(name = "EnergyWindowUpperLimit-00540015", required = true)
    protected String energyWindowUpperLimit00540015;

    /**
     * Obtient la valeur de la propriété energyWindowName00540018.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnergyWindowName00540018() {
        return energyWindowName00540018;
    }

    /**
     * Définit la valeur de la propriété energyWindowName00540018.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnergyWindowName00540018(String value) {
        this.energyWindowName00540018 = value;
    }

    /**
     * Obtient la valeur de la propriété energyWindowLowerLimit00540014.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnergyWindowLowerLimit00540014() {
        return energyWindowLowerLimit00540014;
    }

    /**
     * Définit la valeur de la propriété energyWindowLowerLimit00540014.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnergyWindowLowerLimit00540014(String value) {
        this.energyWindowLowerLimit00540014 = value;
    }

    /**
     * Obtient la valeur de la propriété energyWindowUpperLimit00540015.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnergyWindowUpperLimit00540015() {
        return energyWindowUpperLimit00540015;
    }

    /**
     * Définit la valeur de la propriété energyWindowUpperLimit00540015.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnergyWindowUpperLimit00540015(String value) {
        this.energyWindowUpperLimit00540015 = value;
    }

}
