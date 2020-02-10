//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.06 à 05:03:32 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CTNumberCalibrationCurve complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CTNumberCalibrationCurve">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ElementsOfCTNumberCalibrationCurve" type="{https://www.irdbb-medirad.com}ElementsOfCTNumberCalibrationCurve"/>
 *         &lt;element name="ReferenceCalibrationDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CTNumberCalibrationCurve", propOrder = {
    "elementsOfCTNumberCalibrationCurve",
    "referenceCalibrationDate"
})
public class CTNumberCalibrationCurve {

    @XmlElement(name = "ElementsOfCTNumberCalibrationCurve", required = true)
    protected ElementsOfCTNumberCalibrationCurve elementsOfCTNumberCalibrationCurve;
    @XmlElement(name = "ReferenceCalibrationDate", required = true)
    protected String referenceCalibrationDate;

    /**
     * Obtient la valeur de la propriété elementsOfCTNumberCalibrationCurve.
     * 
     * @return
     *     possible object is
     *     {@link ElementsOfCTNumberCalibrationCurve }
     *     
     */
    public ElementsOfCTNumberCalibrationCurve getElementsOfCTNumberCalibrationCurve() {
        return elementsOfCTNumberCalibrationCurve;
    }

    /**
     * Définit la valeur de la propriété elementsOfCTNumberCalibrationCurve.
     * 
     * @param value
     *     allowed object is
     *     {@link ElementsOfCTNumberCalibrationCurve }
     *     
     */
    public void setElementsOfCTNumberCalibrationCurve(ElementsOfCTNumberCalibrationCurve value) {
        this.elementsOfCTNumberCalibrationCurve = value;
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

}
