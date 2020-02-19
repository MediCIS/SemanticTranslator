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
 * <p>Classe Java pour NMPhantom complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="NMPhantom">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NMPhantomName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NMPhantomIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HotInsertContainer" type="{https://www.irdbb-medirad.com}HotInsertContainer"/>
 *         &lt;element name="ColdInsertContainer" type="{https://www.irdbb-medirad.com}ColdInsertContainer"/>
 *         &lt;element name="Tank" type="{https://www.irdbb-medirad.com}Tank"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NMPhantom", propOrder = {
    "nmPhantomName",
    "nmPhantomIdentifier",
    "hotInsertContainer",
    "coldInsertContainer",
    "tank"
})
public class NMPhantom {

    @XmlElement(name = "NMPhantomName", required = true)
    protected String nmPhantomName;
    @XmlElement(name = "NMPhantomIdentifier", required = true)
    protected String nmPhantomIdentifier;
    @XmlElement(name = "HotInsertContainer", required = true)
    protected HotInsertContainer hotInsertContainer;
    @XmlElement(name = "ColdInsertContainer", required = true)
    protected ColdInsertContainer coldInsertContainer;
    @XmlElement(name = "Tank", required = true)
    protected Tank tank;

    /**
     * Obtient la valeur de la propriété nmPhantomName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNMPhantomName() {
        return nmPhantomName;
    }

    /**
     * Définit la valeur de la propriété nmPhantomName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNMPhantomName(String value) {
        this.nmPhantomName = value;
    }

    /**
     * Obtient la valeur de la propriété nmPhantomIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNMPhantomIdentifier() {
        return nmPhantomIdentifier;
    }

    /**
     * Définit la valeur de la propriété nmPhantomIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNMPhantomIdentifier(String value) {
        this.nmPhantomIdentifier = value;
    }

    /**
     * Obtient la valeur de la propriété hotInsertContainer.
     * 
     * @return
     *     possible object is
     *     {@link HotInsertContainer }
     *     
     */
    public HotInsertContainer getHotInsertContainer() {
        return hotInsertContainer;
    }

    /**
     * Définit la valeur de la propriété hotInsertContainer.
     * 
     * @param value
     *     allowed object is
     *     {@link HotInsertContainer }
     *     
     */
    public void setHotInsertContainer(HotInsertContainer value) {
        this.hotInsertContainer = value;
    }

    /**
     * Obtient la valeur de la propriété coldInsertContainer.
     * 
     * @return
     *     possible object is
     *     {@link ColdInsertContainer }
     *     
     */
    public ColdInsertContainer getColdInsertContainer() {
        return coldInsertContainer;
    }

    /**
     * Définit la valeur de la propriété coldInsertContainer.
     * 
     * @param value
     *     allowed object is
     *     {@link ColdInsertContainer }
     *     
     */
    public void setColdInsertContainer(ColdInsertContainer value) {
        this.coldInsertContainer = value;
    }

    /**
     * Obtient la valeur de la propriété tank.
     * 
     * @return
     *     possible object is
     *     {@link Tank }
     *     
     */
    public Tank getTank() {
        return tank;
    }

    /**
     * Définit la valeur de la propriété tank.
     * 
     * @param value
     *     allowed object is
     *     {@link Tank }
     *     
     */
    public void setTank(Tank value) {
        this.tank = value;
    }

}
