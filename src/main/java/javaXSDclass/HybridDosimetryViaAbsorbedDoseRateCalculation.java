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
 * <p>Classe Java pour HybridDosimetryViaAbsorbedDoseRateCalculation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="HybridDosimetryViaAbsorbedDoseRateCalculation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EnergyDepositionRateCalculation" type="{https://www.irdbb-medirad.com}EnergyDepositionRateCalculation"/>
 *         &lt;element name="SumAndScalingEnergyDepositionRate" type="{https://www.irdbb-medirad.com}SumAndScalingEnergyDepositionRate"/>
 *         &lt;element name="AbsorbedDoseRateCalculation" type="{https://www.irdbb-medirad.com}AbsorbedDoseRateCalculation"/>
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
    "energyDepositionRateCalculation",
    "sumAndScalingEnergyDepositionRate",
    "absorbedDoseRateCalculation",
    "timeAbsorbedDoseRateCurveFit"
})
public class HybridDosimetryViaAbsorbedDoseRateCalculation {

    @XmlElement(name = "EnergyDepositionRateCalculation", required = true)
    protected EnergyDepositionRateCalculation energyDepositionRateCalculation;
    @XmlElement(name = "SumAndScalingEnergyDepositionRate", required = true)
    protected SumAndScalingEnergyDepositionRate sumAndScalingEnergyDepositionRate;
    @XmlElement(name = "AbsorbedDoseRateCalculation", required = true)
    protected AbsorbedDoseRateCalculation absorbedDoseRateCalculation;
    @XmlElement(name = "TimeAbsorbedDoseRateCurveFit", required = true)
    protected TimeAbsorbedDoseRateCurveFit timeAbsorbedDoseRateCurveFit;

    /**
     * Obtient la valeur de la propriété energyDepositionRateCalculation.
     * 
     * @return
     *     possible object is
     *     {@link EnergyDepositionRateCalculation }
     *     
     */
    public EnergyDepositionRateCalculation getEnergyDepositionRateCalculation() {
        return energyDepositionRateCalculation;
    }

    /**
     * Définit la valeur de la propriété energyDepositionRateCalculation.
     * 
     * @param value
     *     allowed object is
     *     {@link EnergyDepositionRateCalculation }
     *     
     */
    public void setEnergyDepositionRateCalculation(EnergyDepositionRateCalculation value) {
        this.energyDepositionRateCalculation = value;
    }

    /**
     * Obtient la valeur de la propriété sumAndScalingEnergyDepositionRate.
     * 
     * @return
     *     possible object is
     *     {@link SumAndScalingEnergyDepositionRate }
     *     
     */
    public SumAndScalingEnergyDepositionRate getSumAndScalingEnergyDepositionRate() {
        return sumAndScalingEnergyDepositionRate;
    }

    /**
     * Définit la valeur de la propriété sumAndScalingEnergyDepositionRate.
     * 
     * @param value
     *     allowed object is
     *     {@link SumAndScalingEnergyDepositionRate }
     *     
     */
    public void setSumAndScalingEnergyDepositionRate(SumAndScalingEnergyDepositionRate value) {
        this.sumAndScalingEnergyDepositionRate = value;
    }

    /**
     * Obtient la valeur de la propriété absorbedDoseRateCalculation.
     * 
     * @return
     *     possible object is
     *     {@link AbsorbedDoseRateCalculation }
     *     
     */
    public AbsorbedDoseRateCalculation getAbsorbedDoseRateCalculation() {
        return absorbedDoseRateCalculation;
    }

    /**
     * Définit la valeur de la propriété absorbedDoseRateCalculation.
     * 
     * @param value
     *     allowed object is
     *     {@link AbsorbedDoseRateCalculation }
     *     
     */
    public void setAbsorbedDoseRateCalculation(AbsorbedDoseRateCalculation value) {
        this.absorbedDoseRateCalculation = value;
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
