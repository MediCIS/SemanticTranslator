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
 * <p>Classe Java pour TwoDimDosimetryViaAbsorbedDoseRateCalculation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TwoDimDosimetryViaAbsorbedDoseRateCalculation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AbsorbedDoseRateCalculationIn2DDosimetryContainer" type="{https://www.irdbb-medirad.com}AbsorbedDoseRateCalculationIn2DDosimetryContainer"/>
 *         &lt;element name="TimeAbsorbedDoseCurveFit" type="{https://www.irdbb-medirad.com}TimeAbsorbedDoseCurveFit"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TwoDimDosimetryViaAbsorbedDoseRateCalculation", propOrder = {
    "absorbedDoseRateCalculationIn2DDosimetryContainer",
    "timeAbsorbedDoseCurveFit"
})
public class TwoDimDosimetryViaAbsorbedDoseRateCalculation {

    @XmlElement(name = "AbsorbedDoseRateCalculationIn2DDosimetryContainer", required = true)
    protected AbsorbedDoseRateCalculationIn2DDosimetryContainer absorbedDoseRateCalculationIn2DDosimetryContainer;
    @XmlElement(name = "TimeAbsorbedDoseCurveFit", required = true)
    protected TimeAbsorbedDoseCurveFit timeAbsorbedDoseCurveFit;

    /**
     * Obtient la valeur de la propriété absorbedDoseRateCalculationIn2DDosimetryContainer.
     * 
     * @return
     *     possible object is
     *     {@link AbsorbedDoseRateCalculationIn2DDosimetryContainer }
     *     
     */
    public AbsorbedDoseRateCalculationIn2DDosimetryContainer getAbsorbedDoseRateCalculationIn2DDosimetryContainer() {
        return absorbedDoseRateCalculationIn2DDosimetryContainer;
    }

    /**
     * Définit la valeur de la propriété absorbedDoseRateCalculationIn2DDosimetryContainer.
     * 
     * @param value
     *     allowed object is
     *     {@link AbsorbedDoseRateCalculationIn2DDosimetryContainer }
     *     
     */
    public void setAbsorbedDoseRateCalculationIn2DDosimetryContainer(AbsorbedDoseRateCalculationIn2DDosimetryContainer value) {
        this.absorbedDoseRateCalculationIn2DDosimetryContainer = value;
    }

    /**
     * Obtient la valeur de la propriété timeAbsorbedDoseCurveFit.
     * 
     * @return
     *     possible object is
     *     {@link TimeAbsorbedDoseCurveFit }
     *     
     */
    public TimeAbsorbedDoseCurveFit getTimeAbsorbedDoseCurveFit() {
        return timeAbsorbedDoseCurveFit;
    }

    /**
     * Définit la valeur de la propriété timeAbsorbedDoseCurveFit.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeAbsorbedDoseCurveFit }
     *     
     */
    public void setTimeAbsorbedDoseCurveFit(TimeAbsorbedDoseCurveFit value) {
        this.timeAbsorbedDoseCurveFit = value;
    }

}
