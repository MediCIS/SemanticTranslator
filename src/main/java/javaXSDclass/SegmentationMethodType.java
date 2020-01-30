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
 * <p>Classe Java pour SegmentationMethodType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SegmentationMethodType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentationMethod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SoftwareNamesUsed" type="{https://www.irdbb-medirad.com}SoftwareNameContainer" minOccurs="0"/>
 *         &lt;element name="SegmentationMethodSetting" type="{https://www.irdbb-medirad.com}MethodSettingTypeContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SegmentationMethodType", propOrder = {
    "segmentationMethod",
    "softwareNamesUsed",
    "segmentationMethodSetting"
})
public class SegmentationMethodType {

    @XmlElement(name = "SegmentationMethod", required = true)
    protected String segmentationMethod;
    @XmlElement(name = "SoftwareNamesUsed")
    protected SoftwareNameContainer softwareNamesUsed;
    @XmlElement(name = "SegmentationMethodSetting")
    protected MethodSettingTypeContainer segmentationMethodSetting;

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
     * Obtient la valeur de la propriété softwareNamesUsed.
     * 
     * @return
     *     possible object is
     *     {@link SoftwareNameContainer }
     *     
     */
    public SoftwareNameContainer getSoftwareNamesUsed() {
        return softwareNamesUsed;
    }

    /**
     * Définit la valeur de la propriété softwareNamesUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link SoftwareNameContainer }
     *     
     */
    public void setSoftwareNamesUsed(SoftwareNameContainer value) {
        this.softwareNamesUsed = value;
    }

    /**
     * Obtient la valeur de la propriété segmentationMethodSetting.
     * 
     * @return
     *     possible object is
     *     {@link MethodSettingTypeContainer }
     *     
     */
    public MethodSettingTypeContainer getSegmentationMethodSetting() {
        return segmentationMethodSetting;
    }

    /**
     * Définit la valeur de la propriété segmentationMethodSetting.
     * 
     * @param value
     *     allowed object is
     *     {@link MethodSettingTypeContainer }
     *     
     */
    public void setSegmentationMethodSetting(MethodSettingTypeContainer value) {
        this.segmentationMethodSetting = value;
    }

}
