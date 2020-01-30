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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour ColdInsert complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ColdInsert">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VolumeValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="VolumeUnit" type="{https://www.irdbb-medirad.com}VolumeUnit"/>
 *         &lt;element name="ColdInsertIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ColdInsert", propOrder = {
    "volumeValue",
    "volumeUnit",
    "coldInsertIdentifier"
})
public class ColdInsert {

    @XmlElement(name = "VolumeValue")
    protected float volumeValue;
    @XmlElement(name = "VolumeUnit", required = true)
    @XmlSchemaType(name = "string")
    protected VolumeUnit volumeUnit;
    @XmlElement(name = "ColdInsertIdentifier", required = true)
    protected String coldInsertIdentifier;

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
     * Obtient la valeur de la propriété coldInsertIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColdInsertIdentifier() {
        return coldInsertIdentifier;
    }

    /**
     * Définit la valeur de la propriété coldInsertIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColdInsertIdentifier(String value) {
        this.coldInsertIdentifier = value;
    }

}
