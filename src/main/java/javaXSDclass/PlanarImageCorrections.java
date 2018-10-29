//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2018.10.16 à 03:58:01 PM CEST 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Background" type="{https://www.irdbb-medirad.com}complexUnknown"/>
 *         &lt;element name="Scatter" type="{https://www.irdbb-medirad.com}complexUnknown"/>
 *         &lt;element name="correctedNMstatic" type="{https://www.irdbb-medirad.com}complexUnknown"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "background",
    "scatter",
    "correctedNMstatic"
})
@XmlRootElement(name = "planarImageCorrections")
public class PlanarImageCorrections {

    @XmlElement(name = "Background", required = true)
    protected ComplexUnknown background;
    @XmlElement(name = "Scatter", required = true)
    protected ComplexUnknown scatter;
    @XmlElement(required = true)
    protected ComplexUnknown correctedNMstatic;

    /**
     * Obtient la valeur de la propriété background.
     * 
     * @return
     *     possible object is
     *     {@link ComplexUnknown }
     *     
     */
    public ComplexUnknown getBackground() {
        return background;
    }

    /**
     * Définit la valeur de la propriété background.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexUnknown }
     *     
     */
    public void setBackground(ComplexUnknown value) {
        this.background = value;
    }

    /**
     * Obtient la valeur de la propriété scatter.
     * 
     * @return
     *     possible object is
     *     {@link ComplexUnknown }
     *     
     */
    public ComplexUnknown getScatter() {
        return scatter;
    }

    /**
     * Définit la valeur de la propriété scatter.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexUnknown }
     *     
     */
    public void setScatter(ComplexUnknown value) {
        this.scatter = value;
    }

    /**
     * Obtient la valeur de la propriété correctedNMstatic.
     * 
     * @return
     *     possible object is
     *     {@link ComplexUnknown }
     *     
     */
    public ComplexUnknown getCorrectedNMstatic() {
        return correctedNMstatic;
    }

    /**
     * Définit la valeur de la propriété correctedNMstatic.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexUnknown }
     *     
     */
    public void setCorrectedNMstatic(ComplexUnknown value) {
        this.correctedNMstatic = value;
    }

}
