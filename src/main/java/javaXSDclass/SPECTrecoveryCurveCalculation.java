//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2018.10.16 à 03:58:01 PM CEST 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour SPECTrecoveryCurveCalculation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SPECTrecoveryCurveCalculation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SPECTrecoveryCoefficientCurve" type="{https://www.irdbb-medirad.com}complexUnknown"/>
 *         &lt;element name="PhantomActivities" type="{https://www.irdbb-medirad.com}complexUnknown"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SPECTrecoveryCurveCalculation", propOrder = {
    "specTrecoveryCoefficientCurve",
    "phantomActivities"
})
public class SPECTrecoveryCurveCalculation {

    @XmlElement(name = "SPECTrecoveryCoefficientCurve", required = true)
    protected ComplexUnknown specTrecoveryCoefficientCurve;
    @XmlElement(name = "PhantomActivities", required = true)
    protected ComplexUnknown phantomActivities;

    /**
     * Obtient la valeur de la propriété specTrecoveryCoefficientCurve.
     * 
     * @return
     *     possible object is
     *     {@link ComplexUnknown }
     *     
     */
    public ComplexUnknown getSPECTrecoveryCoefficientCurve() {
        return specTrecoveryCoefficientCurve;
    }

    /**
     * Définit la valeur de la propriété specTrecoveryCoefficientCurve.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexUnknown }
     *     
     */
    public void setSPECTrecoveryCoefficientCurve(ComplexUnknown value) {
        this.specTrecoveryCoefficientCurve = value;
    }

    /**
     * Obtient la valeur de la propriété phantomActivities.
     * 
     * @return
     *     possible object is
     *     {@link ComplexUnknown }
     *     
     */
    public ComplexUnknown getPhantomActivities() {
        return phantomActivities;
    }

    /**
     * Définit la valeur de la propriété phantomActivities.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexUnknown }
     *     
     */
    public void setPhantomActivities(ComplexUnknown value) {
        this.phantomActivities = value;
    }

}
