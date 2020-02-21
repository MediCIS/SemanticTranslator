//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.12 à 04:30:42 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour HybridDosimetryViaAbsorbedDoseRateCalculation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="HybridDosimetryViaAbsorbedDoseRateCalculation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HybridDosimetryPerTimePointContainer" type="{https://www.irdbb-medirad.com}HybridDosimetryPerTimePointContainer"/>
 *         &lt;element name="TimeAbsorbedDoseRateCurveFit" type="{https://www.irdbb-medirad.com}TimeAbsorbedDoseRateCurveFit"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HybridDosimetryViaAbsorbedDoseRateCalculation", propOrder = {
    "hybridDosimetryPerTimePointContainer",
    "timeAbsorbedDoseRateCurveFit"
})
public class HybridDosimetryViaAbsorbedDoseRateCalculation {

    @XmlElement(name = "HybridDosimetryPerTimePointContainer", required = true)
    protected HybridDosimetryPerTimePointContainer hybridDosimetryPerTimePointContainer;
    @XmlElement(name = "TimeAbsorbedDoseRateCurveFit", required = true)
    protected TimeAbsorbedDoseRateCurveFit timeAbsorbedDoseRateCurveFit;

    /**
     * Obtient la valeur de la propriété hybridDosimetryPerTimePointContainer.
     * 
     * @return
     *     possible object is
     *     {@link HybridDosimetryPerTimePointContainer }
     *     
     */
    public HybridDosimetryPerTimePointContainer getHybridDosimetryPerTimePointContainer() {
        return hybridDosimetryPerTimePointContainer;
    }

    /**
     * Définit la valeur de la propriété hybridDosimetryPerTimePointContainer.
     * 
     * @param value
     *     allowed object is
     *     {@link HybridDosimetryPerTimePointContainer }
     *     
     */
    public void setHybridDosimetryPerTimePointContainer(HybridDosimetryPerTimePointContainer value) {
        this.hybridDosimetryPerTimePointContainer = value;
    }

    /**
     * Obtient la valeur de la propriété timeAbsorbedDoseRateCurveFit.
     * 
     * @return
     *     possible object is
     *     {@link TimeAbsorbedDoseRateCurveFit }
     *     
     */
    public TimeAbsorbedDoseRateCurveFit getTimeAbsorbedDoseRateCurveFit() {
        return timeAbsorbedDoseRateCurveFit;
    }

    /**
     * Définit la valeur de la propriété timeAbsorbedDoseRateCurveFit.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeAbsorbedDoseRateCurveFit }
     *     
     */
    public void setTimeAbsorbedDoseRateCurveFit(TimeAbsorbedDoseRateCurveFit value) {
        this.timeAbsorbedDoseRateCurveFit = value;
    }

}
