//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.04 à 12:01:52 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour AttenuatorType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="AttenuatorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AttenuatorCategory" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EquivalentAttenuatorDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EquivalentAttenuatorMaterial" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EquivalentAttenuatorThicknessValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EquivalentAttenuatorThicknessUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EquivalentAttenuatorModel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AttenuatorType", propOrder = {
    "attenuatorCategory",
    "equivalentAttenuatorDescription",
    "equivalentAttenuatorMaterial",
    "equivalentAttenuatorThicknessValue",
    "equivalentAttenuatorThicknessUnit",
    "equivalentAttenuatorModel"
})
public class AttenuatorType {

    @XmlElement(name = "AttenuatorCategory", required = true)
    protected String attenuatorCategory;
    @XmlElement(name = "EquivalentAttenuatorDescription", required = true)
    protected String equivalentAttenuatorDescription;
    @XmlElement(name = "EquivalentAttenuatorMaterial", required = true)
    protected String equivalentAttenuatorMaterial;
    @XmlElement(name = "EquivalentAttenuatorThicknessValue", required = true)
    protected String equivalentAttenuatorThicknessValue;
    @XmlElement(name = "EquivalentAttenuatorThicknessUnit", required = true)
    protected String equivalentAttenuatorThicknessUnit;
    @XmlElement(name = "EquivalentAttenuatorModel", required = true)
    protected String equivalentAttenuatorModel;

    /**
     * Obtient la valeur de la propriété attenuatorCategory.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttenuatorCategory() {
        return attenuatorCategory;
    }

    /**
     * Définit la valeur de la propriété attenuatorCategory.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttenuatorCategory(String value) {
        this.attenuatorCategory = value;
    }

    /**
     * Obtient la valeur de la propriété equivalentAttenuatorDescription.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEquivalentAttenuatorDescription() {
        return equivalentAttenuatorDescription;
    }

    /**
     * Définit la valeur de la propriété equivalentAttenuatorDescription.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEquivalentAttenuatorDescription(String value) {
        this.equivalentAttenuatorDescription = value;
    }

    /**
     * Obtient la valeur de la propriété equivalentAttenuatorMaterial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEquivalentAttenuatorMaterial() {
        return equivalentAttenuatorMaterial;
    }

    /**
     * Définit la valeur de la propriété equivalentAttenuatorMaterial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEquivalentAttenuatorMaterial(String value) {
        this.equivalentAttenuatorMaterial = value;
    }

    /**
     * Obtient la valeur de la propriété equivalentAttenuatorThicknessValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEquivalentAttenuatorThicknessValue() {
        return equivalentAttenuatorThicknessValue;
    }

    /**
     * Définit la valeur de la propriété equivalentAttenuatorThicknessValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEquivalentAttenuatorThicknessValue(String value) {
        this.equivalentAttenuatorThicknessValue = value;
    }

    /**
     * Obtient la valeur de la propriété equivalentAttenuatorThicknessUnit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEquivalentAttenuatorThicknessUnit() {
        return equivalentAttenuatorThicknessUnit;
    }

    /**
     * Définit la valeur de la propriété equivalentAttenuatorThicknessUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEquivalentAttenuatorThicknessUnit(String value) {
        this.equivalentAttenuatorThicknessUnit = value;
    }

    /**
     * Obtient la valeur de la propriété equivalentAttenuatorModel.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEquivalentAttenuatorModel() {
        return equivalentAttenuatorModel;
    }

    /**
     * Définit la valeur de la propriété equivalentAttenuatorModel.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEquivalentAttenuatorModel(String value) {
        this.equivalentAttenuatorModel = value;
    }

}
