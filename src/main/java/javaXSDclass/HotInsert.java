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
 * <p>Classe Java pour HotInsert complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="HotInsert">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VolumeValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="VolumeUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PreAdminActivityValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="PreAdminActivityUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PreAdminActivityTimestamp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PostAdminActivityValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="PostAdminActivityUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PostAdminActivityTimestamp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HotInsertIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Isotope">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="radium223"/>
 *               &lt;enumeration value="iodine131"/>
 *               &lt;enumeration value="lutetium177"/>
 *               &lt;enumeration value="yttrium90"/>
 *               &lt;enumeration value="radium223"/>
 *               &lt;enumeration value="rhenium188"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HotInsert", propOrder = {
    "volumeValue",
    "volumeUnit",
    "preAdminActivityValue",
    "preAdminActivityUnit",
    "preAdminActivityTimestamp",
    "postAdminActivityValue",
    "postAdminActivityUnit",
    "postAdminActivityTimestamp",
    "hotInsertIdentifier",
    "isotope"
})
public class HotInsert {

    @XmlElement(name = "VolumeValue")
    protected float volumeValue;
    @XmlElement(name = "VolumeUnit", required = true)
    protected String volumeUnit;
    @XmlElement(name = "PreAdminActivityValue")
    protected float preAdminActivityValue;
    @XmlElement(name = "PreAdminActivityUnit", required = true)
    protected String preAdminActivityUnit;
    @XmlElement(name = "PreAdminActivityTimestamp", required = true)
    protected String preAdminActivityTimestamp;
    @XmlElement(name = "PostAdminActivityValue")
    protected float postAdminActivityValue;
    @XmlElement(name = "PostAdminActivityUnit", required = true)
    protected String postAdminActivityUnit;
    @XmlElement(name = "PostAdminActivityTimestamp", required = true)
    protected String postAdminActivityTimestamp;
    @XmlElement(name = "HotInsertIdentifier", required = true)
    protected String hotInsertIdentifier;
    @XmlElement(name = "Isotope", required = true)
    protected String isotope;

    /**
     * Obtient la valeur de la propriété volumeValue.
     * 
     */
    public float getVolumeValue() {
        return volumeValue;
    }

    /**
     * Définit la valeur de la propriété volumeValue.
     * 
     */
    public void setVolumeValue(float value) {
        this.volumeValue = value;
    }

    /**
     * Obtient la valeur de la propriété volumeUnit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVolumeUnit() {
        return volumeUnit;
    }

    /**
     * Définit la valeur de la propriété volumeUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVolumeUnit(String value) {
        this.volumeUnit = value;
    }

    /**
     * Obtient la valeur de la propriété preAdminActivityValue.
     * 
     */
    public float getPreAdminActivityValue() {
        return preAdminActivityValue;
    }

    /**
     * Définit la valeur de la propriété preAdminActivityValue.
     * 
     */
    public void setPreAdminActivityValue(float value) {
        this.preAdminActivityValue = value;
    }

    /**
     * Obtient la valeur de la propriété preAdminActivityUnit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreAdminActivityUnit() {
        return preAdminActivityUnit;
    }

    /**
     * Définit la valeur de la propriété preAdminActivityUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreAdminActivityUnit(String value) {
        this.preAdminActivityUnit = value;
    }

    /**
     * Obtient la valeur de la propriété preAdminActivityTimestamp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreAdminActivityTimestamp() {
        return preAdminActivityTimestamp;
    }

    /**
     * Définit la valeur de la propriété preAdminActivityTimestamp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreAdminActivityTimestamp(String value) {
        this.preAdminActivityTimestamp = value;
    }

    /**
     * Obtient la valeur de la propriété postAdminActivityValue.
     * 
     */
    public float getPostAdminActivityValue() {
        return postAdminActivityValue;
    }

    /**
     * Définit la valeur de la propriété postAdminActivityValue.
     * 
     */
    public void setPostAdminActivityValue(float value) {
        this.postAdminActivityValue = value;
    }

    /**
     * Obtient la valeur de la propriété postAdminActivityUnit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostAdminActivityUnit() {
        return postAdminActivityUnit;
    }

    /**
     * Définit la valeur de la propriété postAdminActivityUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostAdminActivityUnit(String value) {
        this.postAdminActivityUnit = value;
    }

    /**
     * Obtient la valeur de la propriété postAdminActivityTimestamp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostAdminActivityTimestamp() {
        return postAdminActivityTimestamp;
    }

    /**
     * Définit la valeur de la propriété postAdminActivityTimestamp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostAdminActivityTimestamp(String value) {
        this.postAdminActivityTimestamp = value;
    }

    /**
     * Obtient la valeur de la propriété hotInsertIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHotInsertIdentifier() {
        return hotInsertIdentifier;
    }

    /**
     * Définit la valeur de la propriété hotInsertIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHotInsertIdentifier(String value) {
        this.hotInsertIdentifier = value;
    }

    /**
     * Obtient la valeur de la propriété isotope.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsotope() {
        return isotope;
    }

    /**
     * Définit la valeur de la propriété isotope.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsotope(String value) {
        this.isotope = value;
    }

}
