//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.01.28 à 04:59:07 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TwoDimDosimetryworkflow complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TwoDimDosimetryworkflow">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PlanarDataAcquisitionAndProcessingContainer" type="{https://www.irdbb-medirad.com}PlanarDataAcquisitionAndProcessingContainer"/>
 *         &lt;element name="TwoDimDosimetryViaAbsorbedDoseRateCalculation" type="{https://www.irdbb-medirad.com}TwoDimDosimetryViaAbsorbedDoseRateCalculation" minOccurs="0"/>
 *         &lt;element name="TwoDimDosimetryViaTimeActivityCurveFit" type="{https://www.irdbb-medirad.com}TwoDimDosimetryViaTimeActivityCurveFit" minOccurs="0"/>
 *         &lt;element name="RadioBiologicalCalculation" type="{https://www.irdbb-medirad.com}RadioBiologicalCalculation" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TwoDimDosimetryworkflow", propOrder = {
    "planarDataAcquisitionAndProcessingContainer",
    "twoDimDosimetryViaAbsorbedDoseRateCalculation",
    "twoDimDosimetryViaTimeActivityCurveFit",
    "radioBiologicalCalculation"
})
public class TwoDimDosimetryworkflow {

    @XmlElement(name = "PlanarDataAcquisitionAndProcessingContainer", required = true)
    protected PlanarDataAcquisitionAndProcessingContainer planarDataAcquisitionAndProcessingContainer;
    @XmlElement(name = "TwoDimDosimetryViaAbsorbedDoseRateCalculation")
    protected TwoDimDosimetryViaAbsorbedDoseRateCalculation twoDimDosimetryViaAbsorbedDoseRateCalculation;
    @XmlElement(name = "TwoDimDosimetryViaTimeActivityCurveFit")
    protected TwoDimDosimetryViaTimeActivityCurveFit twoDimDosimetryViaTimeActivityCurveFit;
    @XmlElement(name = "RadioBiologicalCalculation")
    protected RadioBiologicalCalculation radioBiologicalCalculation;

    /**
     * Obtient la valeur de la propriété planarDataAcquisitionAndProcessingContainer.
     * 
     * @return
     *     possible object is
     *     {@link PlanarDataAcquisitionAndProcessingContainer }
     *     
     */
    public PlanarDataAcquisitionAndProcessingContainer getPlanarDataAcquisitionAndProcessingContainer() {
        return planarDataAcquisitionAndProcessingContainer;
    }

    /**
     * Définit la valeur de la propriété planarDataAcquisitionAndProcessingContainer.
     * 
     * @param value
     *     allowed object is
     *     {@link PlanarDataAcquisitionAndProcessingContainer }
     *     
     */
    public void setPlanarDataAcquisitionAndProcessingContainer(PlanarDataAcquisitionAndProcessingContainer value) {
        this.planarDataAcquisitionAndProcessingContainer = value;
    }

    /**
     * Obtient la valeur de la propriété twoDimDosimetryViaAbsorbedDoseRateCalculation.
     * 
     * @return
     *     possible object is
     *     {@link TwoDimDosimetryViaAbsorbedDoseRateCalculation }
     *     
     */
    public TwoDimDosimetryViaAbsorbedDoseRateCalculation getTwoDimDosimetryViaAbsorbedDoseRateCalculation() {
        return twoDimDosimetryViaAbsorbedDoseRateCalculation;
    }

    /**
     * Définit la valeur de la propriété twoDimDosimetryViaAbsorbedDoseRateCalculation.
     * 
     * @param value
     *     allowed object is
     *     {@link TwoDimDosimetryViaAbsorbedDoseRateCalculation }
     *     
     */
    public void setTwoDimDosimetryViaAbsorbedDoseRateCalculation(TwoDimDosimetryViaAbsorbedDoseRateCalculation value) {
        this.twoDimDosimetryViaAbsorbedDoseRateCalculation = value;
    }

    /**
     * Obtient la valeur de la propriété twoDimDosimetryViaTimeActivityCurveFit.
     * 
     * @return
     *     possible object is
     *     {@link TwoDimDosimetryViaTimeActivityCurveFit }
     *     
     */
    public TwoDimDosimetryViaTimeActivityCurveFit getTwoDimDosimetryViaTimeActivityCurveFit() {
        return twoDimDosimetryViaTimeActivityCurveFit;
    }

    /**
     * Définit la valeur de la propriété twoDimDosimetryViaTimeActivityCurveFit.
     * 
     * @param value
     *     allowed object is
     *     {@link TwoDimDosimetryViaTimeActivityCurveFit }
     *     
     */
    public void setTwoDimDosimetryViaTimeActivityCurveFit(TwoDimDosimetryViaTimeActivityCurveFit value) {
        this.twoDimDosimetryViaTimeActivityCurveFit = value;
    }

    /**
     * Obtient la valeur de la propriété radioBiologicalCalculation.
     * 
     * @return
     *     possible object is
     *     {@link RadioBiologicalCalculation }
     *     
     */
    public RadioBiologicalCalculation getRadioBiologicalCalculation() {
        return radioBiologicalCalculation;
    }

    /**
     * Définit la valeur de la propriété radioBiologicalCalculation.
     * 
     * @param value
     *     allowed object is
     *     {@link RadioBiologicalCalculation }
     *     
     */
    public void setRadioBiologicalCalculation(RadioBiologicalCalculation value) {
        this.radioBiologicalCalculation = value;
    }

}
