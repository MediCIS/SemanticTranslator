//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.01.28 à 04:59:07 PM CET 
//


package javaXSDclass;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour HybridDosimetryworkflow complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="HybridDosimetryworkflow">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PlanarDataAcquisitionAndProcessing" type="{https://www.irdbb-medirad.com}PlanarDataAcquisitionAndProcessing" maxOccurs="unbounded"/>
 *         &lt;element name="SPECTDataAcquisitionAndProcessing" type="{https://www.irdbb-medirad.com}SPECTDataAcquisitionAndProcessing"/>
 *         &lt;element name="HybridDosimetryViaAbsorbedDoseRateCalculation" type="{https://www.irdbb-medirad.com}HybridDosimetryViaAbsorbedDoseRateCalculation" minOccurs="0"/>
 *         &lt;element name="HybridDosimetryViaTimeActivityCurveFit" type="{https://www.irdbb-medirad.com}HybridDosimetryViaTimeActivityCurveFit" minOccurs="0"/>
 *         &lt;element name="RadioBiologicalCalculationInHybridOr3DSlide2Dosimetry" type="{https://www.irdbb-medirad.com}RadioBiologicalCalculationInHybridOr3DSlide2Dosimetry" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HybridDosimetryworkflow", propOrder = {
    "planarDataAcquisitionAndProcessing",
    "spectDataAcquisitionAndProcessing",
    "hybridDosimetryViaAbsorbedDoseRateCalculation",
    "hybridDosimetryViaTimeActivityCurveFit",
    "radioBiologicalCalculationInHybridOr3DSlide2Dosimetry"
})
public class HybridDosimetryworkflow {

    @XmlElement(name = "PlanarDataAcquisitionAndProcessing", required = true)
    protected List<PlanarDataAcquisitionAndProcessing> planarDataAcquisitionAndProcessing;
    @XmlElement(name = "SPECTDataAcquisitionAndProcessing", required = true)
    protected SPECTDataAcquisitionAndProcessing spectDataAcquisitionAndProcessing;
    @XmlElement(name = "HybridDosimetryViaAbsorbedDoseRateCalculation")
    protected HybridDosimetryViaAbsorbedDoseRateCalculation hybridDosimetryViaAbsorbedDoseRateCalculation;
    @XmlElement(name = "HybridDosimetryViaTimeActivityCurveFit")
    protected HybridDosimetryViaTimeActivityCurveFit hybridDosimetryViaTimeActivityCurveFit;
    @XmlElement(name = "RadioBiologicalCalculationInHybridOr3DSlide2Dosimetry")
    protected RadioBiologicalCalculationInHybridOr3DSlide2Dosimetry radioBiologicalCalculationInHybridOr3DSlide2Dosimetry;

    /**
     * Gets the value of the planarDataAcquisitionAndProcessing property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the planarDataAcquisitionAndProcessing property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlanarDataAcquisitionAndProcessing().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlanarDataAcquisitionAndProcessing }
     * 
     * 
     */
    public List<PlanarDataAcquisitionAndProcessing> getPlanarDataAcquisitionAndProcessing() {
        if (planarDataAcquisitionAndProcessing == null) {
            planarDataAcquisitionAndProcessing = new ArrayList<PlanarDataAcquisitionAndProcessing>();
        }
        return this.planarDataAcquisitionAndProcessing;
    }

    /**
     * Obtient la valeur de la propriété spectDataAcquisitionAndProcessing.
     * 
     * @return
     *     possible object is
     *     {@link SPECTDataAcquisitionAndProcessing }
     *     
     */
    public SPECTDataAcquisitionAndProcessing getSPECTDataAcquisitionAndProcessing() {
        return spectDataAcquisitionAndProcessing;
    }

    /**
     * Définit la valeur de la propriété spectDataAcquisitionAndProcessing.
     * 
     * @param value
     *     allowed object is
     *     {@link SPECTDataAcquisitionAndProcessing }
     *     
     */
    public void setSPECTDataAcquisitionAndProcessing(SPECTDataAcquisitionAndProcessing value) {
        this.spectDataAcquisitionAndProcessing = value;
    }

    /**
     * Obtient la valeur de la propriété hybridDosimetryViaAbsorbedDoseRateCalculation.
     * 
     * @return
     *     possible object is
     *     {@link HybridDosimetryViaAbsorbedDoseRateCalculation }
     *     
     */
    public HybridDosimetryViaAbsorbedDoseRateCalculation getHybridDosimetryViaAbsorbedDoseRateCalculation() {
        return hybridDosimetryViaAbsorbedDoseRateCalculation;
    }

    /**
     * Définit la valeur de la propriété hybridDosimetryViaAbsorbedDoseRateCalculation.
     * 
     * @param value
     *     allowed object is
     *     {@link HybridDosimetryViaAbsorbedDoseRateCalculation }
     *     
     */
    public void setHybridDosimetryViaAbsorbedDoseRateCalculation(HybridDosimetryViaAbsorbedDoseRateCalculation value) {
        this.hybridDosimetryViaAbsorbedDoseRateCalculation = value;
    }

    /**
     * Obtient la valeur de la propriété hybridDosimetryViaTimeActivityCurveFit.
     * 
     * @return
     *     possible object is
     *     {@link HybridDosimetryViaTimeActivityCurveFit }
     *     
     */
    public HybridDosimetryViaTimeActivityCurveFit getHybridDosimetryViaTimeActivityCurveFit() {
        return hybridDosimetryViaTimeActivityCurveFit;
    }

    /**
     * Définit la valeur de la propriété hybridDosimetryViaTimeActivityCurveFit.
     * 
     * @param value
     *     allowed object is
     *     {@link HybridDosimetryViaTimeActivityCurveFit }
     *     
     */
    public void setHybridDosimetryViaTimeActivityCurveFit(HybridDosimetryViaTimeActivityCurveFit value) {
        this.hybridDosimetryViaTimeActivityCurveFit = value;
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

}
