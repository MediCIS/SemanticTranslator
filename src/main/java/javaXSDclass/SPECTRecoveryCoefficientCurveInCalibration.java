//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.09.16 à 04:44:10 PM CEST 
//


package javaXSDclass;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour SPECTRecoveryCoefficientCurveInCalibration complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SPECTRecoveryCoefficientCurveInCalibration">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ElementOfSPECTRecoveryCoefficientCurve" type="{https://www.irdbb-medirad.com}ElementOfSPECTRecoveryCoefficientCurve" maxOccurs="unbounded"/>
 *         &lt;element name="ReferenceCalibrationDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "SPECTRecoveryCoefficientCurveInCalibration", propOrder = {
    "elementOfSPECTRecoveryCoefficientCurve",
    "referenceCalibrationDate",
    "isotope"
})
public class SPECTRecoveryCoefficientCurveInCalibration {

    @XmlElement(name = "ElementOfSPECTRecoveryCoefficientCurve", required = true)
    protected List<ElementOfSPECTRecoveryCoefficientCurve> elementOfSPECTRecoveryCoefficientCurve;
    @XmlElement(name = "ReferenceCalibrationDate", required = true)
    protected String referenceCalibrationDate;
    @XmlElement(name = "Isotope", required = true)
    protected String isotope;

    /**
     * Gets the value of the elementOfSPECTRecoveryCoefficientCurve property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the elementOfSPECTRecoveryCoefficientCurve property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getElementOfSPECTRecoveryCoefficientCurve().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ElementOfSPECTRecoveryCoefficientCurve }
     * 
     * 
     */
    public List<ElementOfSPECTRecoveryCoefficientCurve> getElementOfSPECTRecoveryCoefficientCurve() {
        if (elementOfSPECTRecoveryCoefficientCurve == null) {
            elementOfSPECTRecoveryCoefficientCurve = new ArrayList<ElementOfSPECTRecoveryCoefficientCurve>();
        }
        return this.elementOfSPECTRecoveryCoefficientCurve;
    }

    /**
     * Obtient la valeur de la propriété referenceCalibrationDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceCalibrationDate() {
        return referenceCalibrationDate;
    }

    /**
     * Définit la valeur de la propriété referenceCalibrationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceCalibrationDate(String value) {
        this.referenceCalibrationDate = value;
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