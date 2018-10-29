//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2018.10.16 à 03:58:01 PM CEST 
//


package javaXSDclass;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="AcquisitionSettings" type="{https://www.irdbb-medirad.com}complexUnknown" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="phantom" type="{https://www.irdbb-medirad.com}Phantomdescriptor"/>
 *         &lt;element name="NMstatic" type="{https://www.irdbb-medirad.com}complexUnknown"/>
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
    "acquisitionSettings",
    "phantom",
    "nMstatic"
})
@XmlRootElement(name = "planarAcquisition")
public class PlanarAcquisition {

    @XmlElement(name = "AcquisitionSettings")
    protected List<ComplexUnknown> acquisitionSettings;
    @XmlElement(required = true)
    protected Phantomdescriptor phantom;
    @XmlElement(name = "NMstatic", required = true)
    protected ComplexUnknown nMstatic;

    /**
     * Gets the value of the acquisitionSettings property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the acquisitionSettings property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAcquisitionSettings().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComplexUnknown }
     * 
     * 
     */
    public List<ComplexUnknown> getAcquisitionSettings() {
        if (acquisitionSettings == null) {
            acquisitionSettings = new ArrayList<ComplexUnknown>();
        }
        return this.acquisitionSettings;
    }

    /**
     * Obtient la valeur de la propriété phantom.
     * 
     * @return
     *     possible object is
     *     {@link Phantomdescriptor }
     *     
     */
    public Phantomdescriptor getPhantom() {
        return phantom;
    }

    /**
     * Définit la valeur de la propriété phantom.
     * 
     * @param value
     *     allowed object is
     *     {@link Phantomdescriptor }
     *     
     */
    public void setPhantom(Phantomdescriptor value) {
        this.phantom = value;
    }

    /**
     * Obtient la valeur de la propriété nMstatic.
     * 
     * @return
     *     possible object is
     *     {@link ComplexUnknown }
     *     
     */
    public ComplexUnknown getNMstatic() {
        return nMstatic;
    }

    /**
     * Définit la valeur de la propriété nMstatic.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexUnknown }
     *     
     */
    public void setNMstatic(ComplexUnknown value) {
        this.nMstatic = value;
    }

}
