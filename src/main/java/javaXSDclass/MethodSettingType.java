//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2018.12.11 à 04:47:30 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour MethodSettingType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="MethodSettingType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MethodSetting" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MethodSettingValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MethodSettingUnit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MethodSettingType", propOrder = {
    "methodSetting",
    "methodSettingValue",
    "methodSettingUnit"
})
public class MethodSettingType {

    @XmlElement(name = "MethodSetting")
    protected String methodSetting;
    @XmlElement(name = "MethodSettingValue")
    protected String methodSettingValue;
    @XmlElement(name = "MethodSettingUnit")
    protected String methodSettingUnit;

    /**
     * Obtient la valeur de la propriété methodSetting.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMethodSetting() {
        return methodSetting;
    }

    /**
     * Définit la valeur de la propriété methodSetting.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMethodSetting(String value) {
        this.methodSetting = value;
    }

    /**
     * Obtient la valeur de la propriété methodSettingValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMethodSettingValue() {
        return methodSettingValue;
    }

    /**
     * Définit la valeur de la propriété methodSettingValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMethodSettingValue(String value) {
        this.methodSettingValue = value;
    }

    /**
     * Obtient la valeur de la propriété methodSettingUnit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMethodSettingUnit() {
        return methodSettingUnit;
    }

    /**
     * Définit la valeur de la propriété methodSettingUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMethodSettingUnit(String value) {
        this.methodSettingUnit = value;
    }

}
