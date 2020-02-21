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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour Tank complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Tank">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VolumeValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="VolumeUnit" type="{https://www.irdbb-medirad.com}VolumeUnit"/>
 *         &lt;element name="PreAdminBackgroundActivityValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="PreAdminBackgroundActivity" type="{https://www.irdbb-medirad.com}PreAdminBackgroundActivity"/>
 *         &lt;element name="PreAdminBackgroundActivityTimestamp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PostAdminBackgroundActivityValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="PostAdminBackgroundActivity" type="{https://www.irdbb-medirad.com}PostAdminBackgroundActivity"/>
 *         &lt;element name="PostAdminBackgroundActivityTimestamp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TankIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "Tank", propOrder = {
    "volumeValue",
    "volumeUnit",
    "preAdminBackgroundActivityValue",
    "preAdminBackgroundActivity",
    "preAdminBackgroundActivityTimestamp",
    "postAdminBackgroundActivityValue",
    "postAdminBackgroundActivity",
    "postAdminBackgroundActivityTimestamp",
    "tankIdentifier",
    "isotope"
})
public class Tank {

    @XmlElement(name = "VolumeValue")
    protected float volumeValue;
    @XmlElement(name = "VolumeUnit", required = true)
    @XmlSchemaType(name = "string")
    protected VolumeUnit volumeUnit;
    @XmlElement(name = "PreAdminBackgroundActivityValue")
    protected float preAdminBackgroundActivityValue;
    @XmlElement(name = "PreAdminBackgroundActivity", required = true)
    @XmlSchemaType(name = "string")
    protected PreAdminBackgroundActivity preAdminBackgroundActivity;
    @XmlElement(name = "PreAdminBackgroundActivityTimestamp", required = true)
    protected String preAdminBackgroundActivityTimestamp;
    @XmlElement(name = "PostAdminBackgroundActivityValue")
    protected float postAdminBackgroundActivityValue;
    @XmlElement(name = "PostAdminBackgroundActivity", required = true)
    @XmlSchemaType(name = "string")
    protected PostAdminBackgroundActivity postAdminBackgroundActivity;
    @XmlElement(name = "PostAdminBackgroundActivityTimestamp", required = true)
    protected String postAdminBackgroundActivityTimestamp;
    @XmlElement(name = "TankIdentifier", required = true)
    protected String tankIdentifier;
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
     * Obtient la valeur de la propriété preAdminBackgroundActivityValue.
     * 
     */
    public float getPreAdminBackgroundActivityValue() {
        return preAdminBackgroundActivityValue;
    }

    /**
     * Définit la valeur de la propriété preAdminBackgroundActivityValue.
     * 
     */
    public void setPreAdminBackgroundActivityValue(float value) {
        this.preAdminBackgroundActivityValue = value;
    }

    /**
     * Obtient la valeur de la propriété preAdminBackgroundActivity.
     * 
     * @return
     *     possible object is
     *     {@link PreAdminBackgroundActivity }
     *     
     */
    public PreAdminBackgroundActivity getPreAdminBackgroundActivity() {
        return preAdminBackgroundActivity;
    }

    /**
     * Définit la valeur de la propriété preAdminBackgroundActivity.
     * 
     * @param value
     *     allowed object is
     *     {@link PreAdminBackgroundActivity }
     *     
     */
    public void setPreAdminBackgroundActivity(PreAdminBackgroundActivity value) {
        this.preAdminBackgroundActivity = value;
    }

    /**
     * Obtient la valeur de la propriété preAdminBackgroundActivityTimestamp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreAdminBackgroundActivityTimestamp() {
        return preAdminBackgroundActivityTimestamp;
    }

    /**
     * Définit la valeur de la propriété preAdminBackgroundActivityTimestamp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreAdminBackgroundActivityTimestamp(String value) {
        this.preAdminBackgroundActivityTimestamp = value;
    }

    /**
     * Obtient la valeur de la propriété postAdminBackgroundActivityValue.
     * 
     */
    public float getPostAdminBackgroundActivityValue() {
        return postAdminBackgroundActivityValue;
    }

    /**
     * Définit la valeur de la propriété postAdminBackgroundActivityValue.
     * 
     */
    public void setPostAdminBackgroundActivityValue(float value) {
        this.postAdminBackgroundActivityValue = value;
    }

    /**
     * Obtient la valeur de la propriété postAdminBackgroundActivity.
     * 
     * @return
     *     possible object is
     *     {@link PostAdminBackgroundActivity }
     *     
     */
    public PostAdminBackgroundActivity getPostAdminBackgroundActivity() {
        return postAdminBackgroundActivity;
    }

    /**
     * Définit la valeur de la propriété postAdminBackgroundActivity.
     * 
     * @param value
     *     allowed object is
     *     {@link PostAdminBackgroundActivity }
     *     
     */
    public void setPostAdminBackgroundActivity(PostAdminBackgroundActivity value) {
        this.postAdminBackgroundActivity = value;
    }

    /**
     * Obtient la valeur de la propriété postAdminBackgroundActivityTimestamp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostAdminBackgroundActivityTimestamp() {
        return postAdminBackgroundActivityTimestamp;
    }

    /**
     * Définit la valeur de la propriété postAdminBackgroundActivityTimestamp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostAdminBackgroundActivityTimestamp(String value) {
        this.postAdminBackgroundActivityTimestamp = value;
    }

    /**
     * Obtient la valeur de la propriété tankIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTankIdentifier() {
        return tankIdentifier;
    }

    /**
     * Définit la valeur de la propriété tankIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTankIdentifier(String value) {
        this.tankIdentifier = value;
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
