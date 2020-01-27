//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.01.24 à 06:05:31 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="VolumeUnit" type="{https://www.irdbb-medirad.com}VolumeUnit"/>
 *         &lt;element name="PreAdminActivityValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="PreAdminActivity" type="{https://www.irdbb-medirad.com}PreAdminActivity"/>
 *         &lt;element name="PreAdminActivityTimestamp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PostAdminActivityValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="PostAdminActivity" type="{https://www.irdbb-medirad.com}PostAdminActivity"/>
 *         &lt;element name="PostAdminActivityTimestamp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HotInsertIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Isotope" type="{https://www.irdbb-medirad.com}Isotope"/>
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
    "preAdminActivity",
    "preAdminActivityTimestamp",
    "postAdminActivityValue",
    "postAdminActivity",
    "postAdminActivityTimestamp",
    "hotInsertIdentifier",
    "isotope"
})
public class HotInsert {

    @XmlElement(name = "VolumeValue")
    protected float volumeValue;
    @XmlElement(name = "VolumeUnit", required = true)
    @XmlSchemaType(name = "string")
    protected VolumeUnit volumeUnit;
    @XmlElement(name = "PreAdminActivityValue")
    protected float preAdminActivityValue;
    @XmlElement(name = "PreAdminActivity", required = true)
    @XmlSchemaType(name = "string")
    protected PreAdminActivity preAdminActivity;
    @XmlElement(name = "PreAdminActivityTimestamp", required = true)
    protected String preAdminActivityTimestamp;
    @XmlElement(name = "PostAdminActivityValue")
    protected float postAdminActivityValue;
    @XmlElement(name = "PostAdminActivity", required = true)
    @XmlSchemaType(name = "string")
    protected PostAdminActivity postAdminActivity;
    @XmlElement(name = "PostAdminActivityTimestamp", required = true)
    protected String postAdminActivityTimestamp;
    @XmlElement(name = "HotInsertIdentifier", required = true)
    protected String hotInsertIdentifier;
    @XmlElement(name = "Isotope", required = true)
    @XmlSchemaType(name = "string")
    protected Isotope isotope;

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
     *     {@link VolumeUnit }
     *     
     */
    public VolumeUnit getVolumeUnit() {
        return volumeUnit;
    }

    /**
     * Définit la valeur de la propriété volumeUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link VolumeUnit }
     *     
     */
    public void setVolumeUnit(VolumeUnit value) {
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
     * Obtient la valeur de la propriété preAdminActivity.
     * 
     * @return
     *     possible object is
     *     {@link PreAdminActivity }
     *     
     */
    public PreAdminActivity getPreAdminActivity() {
        return preAdminActivity;
    }

    /**
     * Définit la valeur de la propriété preAdminActivity.
     * 
     * @param value
     *     allowed object is
     *     {@link PreAdminActivity }
     *     
     */
    public void setPreAdminActivity(PreAdminActivity value) {
        this.preAdminActivity = value;
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
     * Obtient la valeur de la propriété postAdminActivity.
     * 
     * @return
     *     possible object is
     *     {@link PostAdminActivity }
     *     
     */
    public PostAdminActivity getPostAdminActivity() {
        return postAdminActivity;
    }

    /**
     * Définit la valeur de la propriété postAdminActivity.
     * 
     * @param value
     *     allowed object is
     *     {@link PostAdminActivity }
     *     
     */
    public void setPostAdminActivity(PostAdminActivity value) {
        this.postAdminActivity = value;
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
     *     {@link Isotope }
     *     
     */
    public Isotope getIsotope() {
        return isotope;
    }

    /**
     * Définit la valeur de la propriété isotope.
     * 
     * @param value
     *     allowed object is
     *     {@link Isotope }
     *     
     */
    public void setIsotope(Isotope value) {
        this.isotope = value;
    }

}
