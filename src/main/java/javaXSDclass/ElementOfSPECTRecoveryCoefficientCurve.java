//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.10.24 à 03:08:52 PM CEST 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour ElementOfSPECTRecoveryCoefficientCurve complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ElementOfSPECTRecoveryCoefficientCurve">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RatioMeasuredActivityToTrueActivity" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="HotInsertVolumeValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="HotInsertVolumeUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HotInsertIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ElementOfSPECTRecoveryCoefficientCurve", propOrder = {
    "ratioMeasuredActivityToTrueActivity",
    "hotInsertVolumeValue",
    "hotInsertVolumeUnit",
    "hotInsertIdentifier"
})
public class ElementOfSPECTRecoveryCoefficientCurve {

    @XmlElement(name = "RatioMeasuredActivityToTrueActivity")
    protected float ratioMeasuredActivityToTrueActivity;
    @XmlElement(name = "HotInsertVolumeValue")
    protected float hotInsertVolumeValue;
    @XmlElement(name = "HotInsertVolumeUnit", required = true)
    protected String hotInsertVolumeUnit;
    @XmlElement(name = "HotInsertIdentifier", required = true)
    protected String hotInsertIdentifier;

    /**
     * Obtient la valeur de la propriété ratioMeasuredActivityToTrueActivity.
     * 
     */
    public float getRatioMeasuredActivityToTrueActivity() {
        return ratioMeasuredActivityToTrueActivity;
    }

    /**
     * Définit la valeur de la propriété ratioMeasuredActivityToTrueActivity.
     * 
     */
    public void setRatioMeasuredActivityToTrueActivity(float value) {
        this.ratioMeasuredActivityToTrueActivity = value;
    }

    /**
     * Obtient la valeur de la propriété hotInsertVolumeValue.
     * 
     */
    public float getHotInsertVolumeValue() {
        return hotInsertVolumeValue;
    }

    /**
     * Définit la valeur de la propriété hotInsertVolumeValue.
     * 
     */
    public void setHotInsertVolumeValue(float value) {
        this.hotInsertVolumeValue = value;
    }

    /**
     * Obtient la valeur de la propriété hotInsertVolumeUnit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHotInsertVolumeUnit() {
        return hotInsertVolumeUnit;
    }

    /**
     * Définit la valeur de la propriété hotInsertVolumeUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHotInsertVolumeUnit(String value) {
        this.hotInsertVolumeUnit = value;
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

}
