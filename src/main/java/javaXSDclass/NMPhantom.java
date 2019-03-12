//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2018.12.11 à 04:47:30 PM CET 
//


package javaXSDclass;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="HotInsert" type="{https://www.irdbb-medirad.com}HotInsert" maxOccurs="unbounded"/>
 *         &lt;element name="ColdInsert" type="{https://www.irdbb-medirad.com}ColdInsert" maxOccurs="unbounded"/>
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
    "hotInsert",
    "coldInsert",
    "tank"
})
public class NMPhantom {

    @XmlElement(name = "NMPhantomName", required = true)
    protected String nmPhantomName;
    @XmlElement(name = "NMPhantomIdentifier", required = true)
    protected String nmPhantomIdentifier;
    @XmlElement(name = "HotInsert", required = true)
    protected List<HotInsert> hotInsert;
    @XmlElement(name = "ColdInsert", required = true)
    protected List<ColdInsert> coldInsert;
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
     * Gets the value of the hotInsert property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hotInsert property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHotInsert().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HotInsert }
     * 
     * 
     */
    public List<HotInsert> getHotInsert() {
        if (hotInsert == null) {
            hotInsert = new ArrayList<HotInsert>();
        }
        return this.hotInsert;
    }

    /**
     * Gets the value of the coldInsert property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the coldInsert property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getColdInsert().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ColdInsert }
     * 
     * 
     */
    public List<ColdInsert> getColdInsert() {
        if (coldInsert == null) {
            coldInsert = new ArrayList<ColdInsert>();
        }
        return this.coldInsert;
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
