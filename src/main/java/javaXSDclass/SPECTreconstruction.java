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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour SPECTreconstruction complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SPECTreconstruction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Attenuation" type="{https://www.irdbb-medirad.com}complexUnknown" minOccurs="0"/>
 *         &lt;element name="Scatter" type="{https://www.irdbb-medirad.com}complexUnknown" minOccurs="0"/>
 *         &lt;element name="PVE" type="{https://www.irdbb-medirad.com}complexUnknown" minOccurs="0"/>
 *         &lt;element name="PSF" type="{https://www.irdbb-medirad.com}complexUnknown" minOccurs="0"/>
 *         &lt;element name="NMTomoRecon" type="{https://www.irdbb-medirad.com}complexUnknown"/>
 *         &lt;element name="CT" type="{https://www.irdbb-medirad.com}complexUnknown"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SPECTreconstruction", propOrder = {
    "attenuation",
    "scatter",
    "pve",
    "psf",
    "nmTomoRecon",
    "ct"
})
public class SPECTreconstruction {

    @XmlElement(name = "Attenuation")
    protected ComplexUnknown attenuation;
    @XmlElement(name = "Scatter")
    protected ComplexUnknown scatter;
    @XmlElement(name = "PVE")
    protected ComplexUnknown pve;
    @XmlElement(name = "PSF")
    protected ComplexUnknown psf;
    @XmlElement(name = "NMTomoRecon", required = true)
    protected ComplexUnknown nmTomoRecon;
    @XmlElement(name = "CT", required = true)
    protected ComplexUnknown ct;

    /**
     * Obtient la valeur de la propriété attenuation.
     * 
     * @return
     *     possible object is
     *     {@link ComplexUnknown }
     *     
     */
    public ComplexUnknown getAttenuation() {
        return attenuation;
    }

    /**
     * Définit la valeur de la propriété attenuation.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexUnknown }
     *     
     */
    public void setAttenuation(ComplexUnknown value) {
        this.attenuation = value;
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
     * Obtient la valeur de la propriété pve.
     * 
     * @return
     *     possible object is
     *     {@link ComplexUnknown }
     *     
     */
    public ComplexUnknown getPVE() {
        return pve;
    }

    /**
     * Définit la valeur de la propriété pve.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexUnknown }
     *     
     */
    public void setPVE(ComplexUnknown value) {
        this.pve = value;
    }

    /**
     * Obtient la valeur de la propriété psf.
     * 
     * @return
     *     possible object is
     *     {@link ComplexUnknown }
     *     
     */
    public ComplexUnknown getPSF() {
        return psf;
    }

    /**
     * Définit la valeur de la propriété psf.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexUnknown }
     *     
     */
    public void setPSF(ComplexUnknown value) {
        this.psf = value;
    }

    /**
     * Obtient la valeur de la propriété nmTomoRecon.
     * 
     * @return
     *     possible object is
     *     {@link ComplexUnknown }
     *     
     */
    public ComplexUnknown getNMTomoRecon() {
        return nmTomoRecon;
    }

    /**
     * Définit la valeur de la propriété nmTomoRecon.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexUnknown }
     *     
     */
    public void setNMTomoRecon(ComplexUnknown value) {
        this.nmTomoRecon = value;
    }

    /**
     * Obtient la valeur de la propriété ct.
     * 
     * @return
     *     possible object is
     *     {@link ComplexUnknown }
     *     
     */
    public ComplexUnknown getCT() {
        return ct;
    }

    /**
     * Définit la valeur de la propriété ct.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexUnknown }
     *     
     */
    public void setCT(ComplexUnknown value) {
        this.ct = value;
    }

}
