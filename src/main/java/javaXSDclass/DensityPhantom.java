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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour DensityPhantom complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DensityPhantom">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DensityPhantomName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DensityPhantomIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DensityPhantom", propOrder = {
    "densityPhantomName",
    "densityPhantomIdentifier"
})
public class DensityPhantom {

    @XmlElement(name = "DensityPhantomName", required = true)
    protected String densityPhantomName;
    @XmlElement(name = "DensityPhantomIdentifier", required = true)
    protected String densityPhantomIdentifier;

    /**
     * Obtient la valeur de la propriété densityPhantomName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDensityPhantomName() {
        return densityPhantomName;
    }

    /**
     * Définit la valeur de la propriété densityPhantomName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDensityPhantomName(String value) {
        this.densityPhantomName = value;
    }

    /**
     * Obtient la valeur de la propriété densityPhantomIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDensityPhantomIdentifier() {
        return densityPhantomIdentifier;
    }

    /**
     * Définit la valeur de la propriété densityPhantomIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDensityPhantomIdentifier(String value) {
        this.densityPhantomIdentifier = value;
    }

}
