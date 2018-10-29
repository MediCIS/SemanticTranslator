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
 * <p>Classe Java pour SPECTaccqCTacqAndReconstruction complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SPECTaccqCTacqAndReconstruction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AcquisitionSettingsSPECTCT" type="{https://www.irdbb-medirad.com}complexUnknown"/>
 *         &lt;element name="Phantom" type="{https://www.irdbb-medirad.com}Phantomdescriptor"/>
 *         &lt;element name="NMTomo" type="{https://www.irdbb-medirad.com}complexUnknown"/>
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
@XmlType(name = "SPECTaccqCTacqAndReconstruction", propOrder = {
    "acquisitionSettingsSPECTCT",
    "phantom",
    "nmTomo",
    "ct"
})
public class SPECTaccqCTacqAndReconstruction {

    @XmlElement(name = "AcquisitionSettingsSPECTCT", required = true)
    protected ComplexUnknown acquisitionSettingsSPECTCT;
    @XmlElement(name = "Phantom", required = true)
    protected Phantomdescriptor phantom;
    @XmlElement(name = "NMTomo", required = true)
    protected ComplexUnknown nmTomo;
    @XmlElement(name = "CT", required = true)
    protected ComplexUnknown ct;

    /**
     * Obtient la valeur de la propriété acquisitionSettingsSPECTCT.
     * 
     * @return
     *     possible object is
     *     {@link ComplexUnknown }
     *     
     */
    public ComplexUnknown getAcquisitionSettingsSPECTCT() {
        return acquisitionSettingsSPECTCT;
    }

    /**
     * Définit la valeur de la propriété acquisitionSettingsSPECTCT.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexUnknown }
     *     
     */
    public void setAcquisitionSettingsSPECTCT(ComplexUnknown value) {
        this.acquisitionSettingsSPECTCT = value;
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
     * Obtient la valeur de la propriété nmTomo.
     * 
     * @return
     *     possible object is
     *     {@link ComplexUnknown }
     *     
     */
    public ComplexUnknown getNMTomo() {
        return nmTomo;
    }

    /**
     * Définit la valeur de la propriété nmTomo.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexUnknown }
     *     
     */
    public void setNMTomo(ComplexUnknown value) {
        this.nmTomo = value;
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
