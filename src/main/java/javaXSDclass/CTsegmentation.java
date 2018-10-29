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
 * <p>Classe Java pour CTsegmentation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CTsegmentation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentationMethod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CTVOI" type="{https://www.irdbb-medirad.com}VOIDescriptorType"/>
 *         &lt;element name="SPECTVOI" type="{https://www.irdbb-medirad.com}VOIDescriptorType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CTsegmentation", propOrder = {
    "segmentationMethod",
    "ctvoi",
    "spectvoi"
})
public class CTsegmentation {

    @XmlElement(name = "SegmentationMethod", required = true)
    protected String segmentationMethod;
    @XmlElement(name = "CTVOI", required = true)
    protected VOIDescriptorType ctvoi;
    @XmlElement(name = "SPECTVOI", required = true)
    protected VOIDescriptorType spectvoi;

    /**
     * Obtient la valeur de la propriété segmentationMethod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegmentationMethod() {
        return segmentationMethod;
    }

    /**
     * Définit la valeur de la propriété segmentationMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegmentationMethod(String value) {
        this.segmentationMethod = value;
    }

    /**
     * Obtient la valeur de la propriété ctvoi.
     * 
     * @return
     *     possible object is
     *     {@link VOIDescriptorType }
     *     
     */
    public VOIDescriptorType getCTVOI() {
        return ctvoi;
    }

    /**
     * Définit la valeur de la propriété ctvoi.
     * 
     * @param value
     *     allowed object is
     *     {@link VOIDescriptorType }
     *     
     */
    public void setCTVOI(VOIDescriptorType value) {
        this.ctvoi = value;
    }

    /**
     * Obtient la valeur de la propriété spectvoi.
     * 
     * @return
     *     possible object is
     *     {@link VOIDescriptorType }
     *     
     */
    public VOIDescriptorType getSPECTVOI() {
        return spectvoi;
    }

    /**
     * Définit la valeur de la propriété spectvoi.
     * 
     * @param value
     *     allowed object is
     *     {@link VOIDescriptorType }
     *     
     */
    public void setSPECTVOI(VOIDescriptorType value) {
        this.spectvoi = value;
    }

}
