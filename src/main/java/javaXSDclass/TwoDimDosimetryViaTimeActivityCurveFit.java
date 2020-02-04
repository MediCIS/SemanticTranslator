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
 *         &lt;element name="AbsorbedDoseCalculationInROIIn2DDosimetry" type="{https://www.irdbb-medirad.com}AbsorbedDoseCalculationInROIIn2DDosimetry"/>
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
    "absorbedDoseCalculationInROIIn2DDosimetry"
})
public class TwoDimDosimetryViaTimeActivityCurveFit {

    @XmlElement(name = "TimeActivityCurveFit", required = true)
    protected TimeActivityCurveFit timeActivityCurveFit;
    @XmlElement(name = "AbsorbedDoseCalculationInROIIn2DDosimetry", required = true)
    protected AbsorbedDoseCalculationInROIIn2DDosimetry absorbedDoseCalculationInROIIn2DDosimetry;

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
     * Obtient la valeur de la propriété absorbedDoseCalculationInROIIn2DDosimetry.
     * 
     * @return
     *     possible object is
     *     {@link AbsorbedDoseCalculationInROIIn2DDosimetry }
     *     
     */
    public AbsorbedDoseCalculationInROIIn2DDosimetry getAbsorbedDoseCalculationInROIIn2DDosimetry() {
        return absorbedDoseCalculationInROIIn2DDosimetry;
    }

    /**
     * Définit la valeur de la propriété absorbedDoseCalculationInROIIn2DDosimetry.
     * 
     * @param value
     *     allowed object is
     *     {@link AbsorbedDoseCalculationInROIIn2DDosimetry }
     *     
     */
    public void setAbsorbedDoseCalculationInROIIn2DDosimetry(AbsorbedDoseCalculationInROIIn2DDosimetry value) {
        this.absorbedDoseCalculationInROIIn2DDosimetry = value;
    }

}
