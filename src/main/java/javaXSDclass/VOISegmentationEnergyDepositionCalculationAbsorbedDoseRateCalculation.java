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
 * <p>Classe Java pour VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VOISegmentationVOIMassDetermination" type="{https://www.irdbb-medirad.com}VOISegmentationVOIMassDetermination"/>
 *         &lt;element name="VOIActivityDetermination" type="{https://www.irdbb-medirad.com}VOIActivityDetermination"/>
 *         &lt;element name="EnergyDepositionRateCalculation" type="{https://www.irdbb-medirad.com}EnergyDepositionRateCalculation"/>
 *         &lt;element name="SumAndScalingAbsorbedDoseRateCalculation" type="{https://www.irdbb-medirad.com}SumAndScalingAbsorbedDoseRateCalculation"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation", propOrder = {
    "voiSegmentationVOIMassDetermination",
    "voiActivityDetermination",
    "energyDepositionRateCalculation",
    "sumAndScalingAbsorbedDoseRateCalculation"
})
public class VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation {

    @XmlElement(name = "VOISegmentationVOIMassDetermination", required = true)
    protected VOISegmentationVOIMassDetermination voiSegmentationVOIMassDetermination;
    @XmlElement(name = "VOIActivityDetermination", required = true)
    protected VOIActivityDetermination voiActivityDetermination;
    @XmlElement(name = "EnergyDepositionRateCalculation", required = true)
    protected EnergyDepositionRateCalculation energyDepositionRateCalculation;
    @XmlElement(name = "SumAndScalingAbsorbedDoseRateCalculation", required = true)
    protected SumAndScalingAbsorbedDoseRateCalculation sumAndScalingAbsorbedDoseRateCalculation;

    /**
     * Obtient la valeur de la propriété voiSegmentationVOIMassDetermination.
     * 
     * @return
     *     possible object is
     *     {@link VOISegmentationVOIMassDetermination }
     *     
     */
    public VOISegmentationVOIMassDetermination getVOISegmentationVOIMassDetermination() {
        return voiSegmentationVOIMassDetermination;
    }

    /**
     * Définit la valeur de la propriété voiSegmentationVOIMassDetermination.
     * 
     * @param value
     *     allowed object is
     *     {@link VOISegmentationVOIMassDetermination }
     *     
     */
    public void setVOISegmentationVOIMassDetermination(VOISegmentationVOIMassDetermination value) {
        this.voiSegmentationVOIMassDetermination = value;
    }

    /**
     * Obtient la valeur de la propriété voiActivityDetermination.
     * 
     * @return
     *     possible object is
     *     {@link VOIActivityDetermination }
     *     
     */
    public VOIActivityDetermination getVOIActivityDetermination() {
        return voiActivityDetermination;
    }

    /**
     * Définit la valeur de la propriété voiActivityDetermination.
     * 
     * @param value
     *     allowed object is
     *     {@link VOIActivityDetermination }
     *     
     */
    public void setVOIActivityDetermination(VOIActivityDetermination value) {
        this.voiActivityDetermination = value;
    }

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
     * Obtient la valeur de la propriété sumAndScalingAbsorbedDoseRateCalculation.
     * 
     * @return
     *     possible object is
     *     {@link SumAndScalingAbsorbedDoseRateCalculation }
     *     
     */
    public SumAndScalingAbsorbedDoseRateCalculation getSumAndScalingAbsorbedDoseRateCalculation() {
        return sumAndScalingAbsorbedDoseRateCalculation;
    }

    /**
     * Définit la valeur de la propriété sumAndScalingAbsorbedDoseRateCalculation.
     * 
     * @param value
     *     allowed object is
     *     {@link SumAndScalingAbsorbedDoseRateCalculation }
     *     
     */
    public void setSumAndScalingAbsorbedDoseRateCalculation(SumAndScalingAbsorbedDoseRateCalculation value) {
        this.sumAndScalingAbsorbedDoseRateCalculation = value;
    }

}
