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
 *         &lt;element name="VOIActivityDetermination" type="{https://www.irdbb-medirad.com}VOIActivityDetermination" minOccurs="0"/>
 *         &lt;element name="EnergyDepositionRateCalculationIn3DDosimetry" type="{https://www.irdbb-medirad.com}EnergyDepositionRateCalculationIn3DDosimetry"/>
 *         &lt;element name="SumAndScalingAbsorbedDoseRateCalculationContainer" type="{https://www.irdbb-medirad.com}SumAndScalingAbsorbedDoseRateCalculationContainer"/>
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
    "energyDepositionRateCalculationIn3DDosimetry",
    "sumAndScalingAbsorbedDoseRateCalculationContainer"
})
public class VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculation {

    @XmlElement(name = "VOISegmentationVOIMassDetermination", required = true)
    protected VOISegmentationVOIMassDetermination voiSegmentationVOIMassDetermination;
    @XmlElement(name = "VOIActivityDetermination")
    protected VOIActivityDetermination voiActivityDetermination;
    @XmlElement(name = "EnergyDepositionRateCalculationIn3DDosimetry", required = true)
    protected EnergyDepositionRateCalculationIn3DDosimetry energyDepositionRateCalculationIn3DDosimetry;
    @XmlElement(name = "SumAndScalingAbsorbedDoseRateCalculationContainer", required = true)
    protected SumAndScalingAbsorbedDoseRateCalculationContainer sumAndScalingAbsorbedDoseRateCalculationContainer;

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
     * Obtient la valeur de la propriété energyDepositionRateCalculationIn3DDosimetry.
     * 
     * @return
     *     possible object is
     *     {@link EnergyDepositionRateCalculationIn3DDosimetry }
     *     
     */
    public EnergyDepositionRateCalculationIn3DDosimetry getEnergyDepositionRateCalculationIn3DDosimetry() {
        return energyDepositionRateCalculationIn3DDosimetry;
    }

    /**
     * Définit la valeur de la propriété energyDepositionRateCalculationIn3DDosimetry.
     * 
     * @param value
     *     allowed object is
     *     {@link EnergyDepositionRateCalculationIn3DDosimetry }
     *     
     */
    public void setEnergyDepositionRateCalculationIn3DDosimetry(EnergyDepositionRateCalculationIn3DDosimetry value) {
        this.energyDepositionRateCalculationIn3DDosimetry = value;
    }

    /**
     * Obtient la valeur de la propriété sumAndScalingAbsorbedDoseRateCalculationContainer.
     * 
     * @return
     *     possible object is
     *     {@link SumAndScalingAbsorbedDoseRateCalculationContainer }
     *     
     */
    public SumAndScalingAbsorbedDoseRateCalculationContainer getSumAndScalingAbsorbedDoseRateCalculationContainer() {
        return sumAndScalingAbsorbedDoseRateCalculationContainer;
    }

    /**
     * Définit la valeur de la propriété sumAndScalingAbsorbedDoseRateCalculationContainer.
     * 
     * @param value
     *     allowed object is
     *     {@link SumAndScalingAbsorbedDoseRateCalculationContainer }
     *     
     */
    public void setSumAndScalingAbsorbedDoseRateCalculationContainer(SumAndScalingAbsorbedDoseRateCalculationContainer value) {
        this.sumAndScalingAbsorbedDoseRateCalculationContainer = value;
    }

}
