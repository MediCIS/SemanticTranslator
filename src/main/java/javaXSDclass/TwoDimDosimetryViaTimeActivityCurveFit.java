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
 * <p>Classe Java pour TwoDimDosimetryViaTimeActivityCurveFit complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TwoDimDosimetryViaTimeActivityCurveFit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TimeActivityCurveFit" type="{https://www.irdbb-medirad.com}TimeActivityCurveFit"/>
 *         &lt;element name="AbsorbedDoseCalculation" type="{https://www.irdbb-medirad.com}AbsorbedDoseCalculation"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TwoDimDosimetryViaTimeActivityCurveFit", propOrder = {
    "timeActivityCurveFit",
    "absorbedDoseCalculation"
})
public class TwoDimDosimetryViaTimeActivityCurveFit {

    @XmlElement(name = "TimeActivityCurveFit", required = true)
    protected TimeActivityCurveFit timeActivityCurveFit;
    @XmlElement(name = "AbsorbedDoseCalculation", required = true)
    protected AbsorbedDoseCalculation absorbedDoseCalculation;

    /**
     * Obtient la valeur de la propriété timeActivityCurveFit.
     * 
     * @return
     *     possible object is
     *     {@link TimeActivityCurveFit }
     *     
     */
    public TimeActivityCurveFit getTimeActivityCurveFit() {
        return timeActivityCurveFit;
    }

    /**
     * Définit la valeur de la propriété timeActivityCurveFit.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeActivityCurveFit }
     *     
     */
    public void setTimeActivityCurveFit(TimeActivityCurveFit value) {
        this.timeActivityCurveFit = value;
    }

    /**
     * Obtient la valeur de la propriété absorbedDoseCalculation.
     * 
     * @return
     *     possible object is
     *     {@link AbsorbedDoseCalculation }
     *     
     */
    public AbsorbedDoseCalculation getAbsorbedDoseCalculation() {
        return absorbedDoseCalculation;
    }

    /**
     * Définit la valeur de la propriété absorbedDoseCalculation.
     * 
     * @param value
     *     allowed object is
     *     {@link AbsorbedDoseCalculation }
     *     
     */
    public void setAbsorbedDoseCalculation(AbsorbedDoseCalculation value) {
        this.absorbedDoseCalculation = value;
    }

}
