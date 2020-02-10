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
 * <p>Classe Java pour HybridDosimetryPerTimePoint complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="HybridDosimetryPerTimePoint">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EnergyDepositionRateCalculationInHybridDosimetry" type="{https://www.irdbb-medirad.com}EnergyDepositionRateCalculationInHybridDosimetry"/>
 *         &lt;element name="SumAndScalingEnergyDepositionRate" type="{https://www.irdbb-medirad.com}SumAndScalingEnergyDepositionRate"/>
 *         &lt;element name="AbsorbedDoseRateCalculationInHybridDosimetry" type="{https://www.irdbb-medirad.com}AbsorbedDoseRateCalculationInHybridDosimetry"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HybridDosimetryPerTimePoint", propOrder = {
    "energyDepositionRateCalculationInHybridDosimetry",
    "sumAndScalingEnergyDepositionRate",
    "absorbedDoseRateCalculationInHybridDosimetry"
})
public class HybridDosimetryPerTimePoint {

    @XmlElement(name = "EnergyDepositionRateCalculationInHybridDosimetry", required = true)
    protected EnergyDepositionRateCalculationInHybridDosimetry energyDepositionRateCalculationInHybridDosimetry;
    @XmlElement(name = "SumAndScalingEnergyDepositionRate", required = true)
    protected SumAndScalingEnergyDepositionRate sumAndScalingEnergyDepositionRate;
    @XmlElement(name = "AbsorbedDoseRateCalculationInHybridDosimetry", required = true)
    protected AbsorbedDoseRateCalculationInHybridDosimetry absorbedDoseRateCalculationInHybridDosimetry;

    /**
     * Obtient la valeur de la propriété energyDepositionRateCalculationInHybridDosimetry.
     * 
     * @return
     *     possible object is
     *     {@link EnergyDepositionRateCalculationInHybridDosimetry }
     *     
     */
    public EnergyDepositionRateCalculationInHybridDosimetry getEnergyDepositionRateCalculationInHybridDosimetry() {
        return energyDepositionRateCalculationInHybridDosimetry;
    }

    /**
     * Définit la valeur de la propriété energyDepositionRateCalculationInHybridDosimetry.
     * 
     * @param value
     *     allowed object is
     *     {@link EnergyDepositionRateCalculationInHybridDosimetry }
     *     
     */
    public void setEnergyDepositionRateCalculationInHybridDosimetry(EnergyDepositionRateCalculationInHybridDosimetry value) {
        this.energyDepositionRateCalculationInHybridDosimetry = value;
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
     * Obtient la valeur de la propriété absorbedDoseRateCalculationInHybridDosimetry.
     * 
     * @return
     *     possible object is
     *     {@link AbsorbedDoseRateCalculationInHybridDosimetry }
     *     
     */
    public AbsorbedDoseRateCalculationInHybridDosimetry getAbsorbedDoseRateCalculationInHybridDosimetry() {
        return absorbedDoseRateCalculationInHybridDosimetry;
    }

    /**
     * Définit la valeur de la propriété absorbedDoseRateCalculationInHybridDosimetry.
     * 
     * @param value
     *     allowed object is
     *     {@link AbsorbedDoseRateCalculationInHybridDosimetry }
     *     
     */
    public void setAbsorbedDoseRateCalculationInHybridDosimetry(AbsorbedDoseRateCalculationInHybridDosimetry value) {
        this.absorbedDoseRateCalculationInHybridDosimetry = value;
    }

}
