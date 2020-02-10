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
 * <p>Classe Java pour ThreeDimDosimetrySlide2workflow complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ThreeDimDosimetrySlide2workflow">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SPECTDataAcquisitionAndReconstruction" type="{https://www.irdbb-medirad.com}SPECTDataAcquisitionAndReconstruction"/>
 *         &lt;element name="VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculationContainer" type="{https://www.irdbb-medirad.com}VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculationContainer"/>
 *         &lt;element name="DoseRateCurveFitVOITimeIntegrationContainer" type="{https://www.irdbb-medirad.com}DoseRateCurveFitVOITimeIntegrationContainer"/>
 *         &lt;element name="RadioBiologicalCalculationInHybridOr3DSlide2Dosimetry" type="{https://www.irdbb-medirad.com}RadioBiologicalCalculationInHybridOr3DSlide2Dosimetry" minOccurs="0"/>
 *         &lt;element name="RegistrationVOISegmentationAndPropagationContainer" type="{https://www.irdbb-medirad.com}RegistrationVOISegmentationAndPropagationContainer" minOccurs="0"/>
 *         &lt;element name="AbsorbedDoseCalculationInVOI" type="{https://www.irdbb-medirad.com}AbsorbedDoseCalculationInVOI" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ThreeDimDosimetrySlide2workflow", propOrder = {
    "spectDataAcquisitionAndReconstruction",
    "voiSegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculationContainer",
    "doseRateCurveFitVOITimeIntegrationContainer",
    "radioBiologicalCalculationInHybridOr3DSlide2Dosimetry",
    "registrationVOISegmentationAndPropagationContainer",
    "absorbedDoseCalculationInVOI"
})
public class ThreeDimDosimetrySlide2Workflow {

    @XmlElement(name = "SPECTDataAcquisitionAndReconstruction", required = true)
    protected SPECTDataAcquisitionAndReconstruction spectDataAcquisitionAndReconstruction;
    @XmlElement(name = "VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculationContainer", required = true)
    protected VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculationContainer voiSegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculationContainer;
    @XmlElement(name = "DoseRateCurveFitVOITimeIntegrationContainer", required = true)
    protected DoseRateCurveFitVOITimeIntegrationContainer doseRateCurveFitVOITimeIntegrationContainer;
    @XmlElement(name = "RadioBiologicalCalculationInHybridOr3DSlide2Dosimetry")
    protected RadioBiologicalCalculationInHybridOr3DSlide2Dosimetry radioBiologicalCalculationInHybridOr3DSlide2Dosimetry;
    @XmlElement(name = "RegistrationVOISegmentationAndPropagationContainer")
    protected RegistrationVOISegmentationAndPropagationContainer registrationVOISegmentationAndPropagationContainer;
    @XmlElement(name = "AbsorbedDoseCalculationInVOI")
    protected AbsorbedDoseCalculationInVOI absorbedDoseCalculationInVOI;

    /**
     * Obtient la valeur de la propriété spectDataAcquisitionAndReconstruction.
     * 
     * @return
     *     possible object is
     *     {@link SPECTDataAcquisitionAndReconstruction }
     *     
     */
    public SPECTDataAcquisitionAndReconstruction getSPECTDataAcquisitionAndReconstruction() {
        return spectDataAcquisitionAndReconstruction;
    }

    /**
     * Définit la valeur de la propriété spectDataAcquisitionAndReconstruction.
     * 
     * @param value
     *     allowed object is
     *     {@link SPECTDataAcquisitionAndReconstruction }
     *     
     */
    public void setSPECTDataAcquisitionAndReconstruction(SPECTDataAcquisitionAndReconstruction value) {
        this.spectDataAcquisitionAndReconstruction = value;
    }

    /**
     * Obtient la valeur de la propriété voiSegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculationContainer.
     * 
     * @return
     *     possible object is
     *     {@link VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculationContainer }
     *     
     */
    public VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculationContainer getVOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculationContainer() {
        return voiSegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculationContainer;
    }

    /**
     * Définit la valeur de la propriété voiSegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculationContainer.
     * 
     * @param value
     *     allowed object is
     *     {@link VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculationContainer }
     *     
     */
    public void setVOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculationContainer(VOISegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculationContainer value) {
        this.voiSegmentationEnergyDepositionCalculationAbsorbedDoseRateCalculationContainer = value;
    }

    /**
     * Obtient la valeur de la propriété doseRateCurveFitVOITimeIntegrationContainer.
     * 
     * @return
     *     possible object is
     *     {@link DoseRateCurveFitVOITimeIntegrationContainer }
     *     
     */
    public DoseRateCurveFitVOITimeIntegrationContainer getDoseRateCurveFitVOITimeIntegrationContainer() {
        return doseRateCurveFitVOITimeIntegrationContainer;
    }

    /**
     * Définit la valeur de la propriété doseRateCurveFitVOITimeIntegrationContainer.
     * 
     * @param value
     *     allowed object is
     *     {@link DoseRateCurveFitVOITimeIntegrationContainer }
     *     
     */
    public void setDoseRateCurveFitVOITimeIntegrationContainer(DoseRateCurveFitVOITimeIntegrationContainer value) {
        this.doseRateCurveFitVOITimeIntegrationContainer = value;
    }

    /**
     * Obtient la valeur de la propriété radioBiologicalCalculationInHybridOr3DSlide2Dosimetry.
     * 
     * @return
     *     possible object is
     *     {@link RadioBiologicalCalculationInHybridOr3DSlide2Dosimetry }
     *     
     */
    public RadioBiologicalCalculationInHybridOr3DSlide2Dosimetry getRadioBiologicalCalculationInHybridOr3DSlide2Dosimetry() {
        return radioBiologicalCalculationInHybridOr3DSlide2Dosimetry;
    }

    /**
     * Définit la valeur de la propriété radioBiologicalCalculationInHybridOr3DSlide2Dosimetry.
     * 
     * @param value
     *     allowed object is
     *     {@link RadioBiologicalCalculationInHybridOr3DSlide2Dosimetry }
     *     
     */
    public void setRadioBiologicalCalculationInHybridOr3DSlide2Dosimetry(RadioBiologicalCalculationInHybridOr3DSlide2Dosimetry value) {
        this.radioBiologicalCalculationInHybridOr3DSlide2Dosimetry = value;
    }

    /**
     * Obtient la valeur de la propriété registrationVOISegmentationAndPropagationContainer.
     * 
     * @return
     *     possible object is
     *     {@link RegistrationVOISegmentationAndPropagationContainer }
     *     
     */
    public RegistrationVOISegmentationAndPropagationContainer getRegistrationVOISegmentationAndPropagationContainer() {
        return registrationVOISegmentationAndPropagationContainer;
    }

    /**
     * Définit la valeur de la propriété registrationVOISegmentationAndPropagationContainer.
     * 
     * @param value
     *     allowed object is
     *     {@link RegistrationVOISegmentationAndPropagationContainer }
     *     
     */
    public void setRegistrationVOISegmentationAndPropagationContainer(RegistrationVOISegmentationAndPropagationContainer value) {
        this.registrationVOISegmentationAndPropagationContainer = value;
    }

    /**
     * Obtient la valeur de la propriété absorbedDoseCalculationInVOI.
     * 
     * @return
     *     possible object is
     *     {@link AbsorbedDoseCalculationInVOI }
     *     
     */
    public AbsorbedDoseCalculationInVOI getAbsorbedDoseCalculationInVOI() {
        return absorbedDoseCalculationInVOI;
    }

    /**
     * Définit la valeur de la propriété absorbedDoseCalculationInVOI.
     * 
     * @param value
     *     allowed object is
     *     {@link AbsorbedDoseCalculationInVOI }
     *     
     */
    public void setAbsorbedDoseCalculationInVOI(AbsorbedDoseCalculationInVOI value) {
        this.absorbedDoseCalculationInVOI = value;
    }

}
