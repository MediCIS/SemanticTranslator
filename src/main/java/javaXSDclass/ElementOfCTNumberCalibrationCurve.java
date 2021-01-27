//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.07.30 à 02:06:37 PM CEST 
//


package javaXSDclass;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour ElementOfCTNumberCalibrationCurve complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ElementOfCTNumberCalibrationCurve">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HounsfieldMeasuredValue" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="RealDensityOfMaterialValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="RealDensityOfMaterialUnit" type="{https://www.irdbb-medirad.com}RealDensityOfMaterialUnit"/>
 *         &lt;element name="MaterialIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ElementOfCTNumberCalibrationCurve", propOrder = {
    "hounsfieldMeasuredValue",
    "realDensityOfMaterialValue",
    "realDensityOfMaterialUnit",
    "materialIdentifier"
})
public class ElementOfCTNumberCalibrationCurve {

    @XmlElement(name = "HounsfieldMeasuredValue", required = true)
    protected BigInteger hounsfieldMeasuredValue;
    @XmlElement(name = "RealDensityOfMaterialValue")
    protected float realDensityOfMaterialValue;
    @XmlElement(name = "RealDensityOfMaterialUnit", required = true)
    @XmlSchemaType(name = "string")
    protected RealDensityOfMaterialUnit realDensityOfMaterialUnit;
    @XmlElement(name = "MaterialIdentifier")
    protected String materialIdentifier;

    /**
     * Obtient la valeur de la propriété hounsfieldMeasuredValue.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getHounsfieldMeasuredValue() {
        return hounsfieldMeasuredValue;
    }

    /**
     * Définit la valeur de la propriété hounsfieldMeasuredValue.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setHounsfieldMeasuredValue(BigInteger value) {
        this.hounsfieldMeasuredValue = value;
    }

    /**
     * Obtient la valeur de la propriété realDensityOfMaterialValue.
     * 
     */
    public float getRealDensityOfMaterialValue() {
        return realDensityOfMaterialValue;
    }

    /**
     * Définit la valeur de la propriété realDensityOfMaterialValue.
     * 
     */
    public void setRealDensityOfMaterialValue(float value) {
        this.realDensityOfMaterialValue = value;
    }

    /**
     * Obtient la valeur de la propriété realDensityOfMaterialUnit.
     * 
     * @return
     *     possible object is
     *     {@link RealDensityOfMaterialUnit }
     *     
     */
    public RealDensityOfMaterialUnit getRealDensityOfMaterialUnit() {
        return realDensityOfMaterialUnit;
    }

    /**
     * Définit la valeur de la propriété realDensityOfMaterialUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link RealDensityOfMaterialUnit }
     *     
     */
    public void setRealDensityOfMaterialUnit(RealDensityOfMaterialUnit value) {
        this.realDensityOfMaterialUnit = value;
    }

    /**
     * Obtient la valeur de la propriété materialIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaterialIdentifier() {
        return materialIdentifier;
    }

    /**
     * Définit la valeur de la propriété materialIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaterialIdentifier(String value) {
        this.materialIdentifier = value;
    }

}
