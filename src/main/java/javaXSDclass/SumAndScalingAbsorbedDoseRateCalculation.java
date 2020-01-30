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
 * <p>Classe Java pour SumAndScalingAbsorbedDoseRateCalculation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SumAndScalingAbsorbedDoseRateCalculation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="VOIIdentifierUsed" type="{https://www.irdbb-medirad.com}VOIIdentifierContainer" minOccurs="0"/>
 *         &lt;element name="TimePointIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SegmentationUsed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ThreeDimEnergyDepositionRateMatrixAtTimePointUsed" type="{https://www.irdbb-medirad.com}NonDICOMData"/>
 *         &lt;element name="AbsorbedDoseRatePerVOIAtTimePointProduced" type="{https://www.irdbb-medirad.com}AbsorbedDoseRatePerVOIAtTimePointContainer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SumAndScalingAbsorbedDoseRateCalculation", propOrder = {
    "processExecutionContext",
    "voiIdentifierUsed",
    "timePointIdentifierUsed",
    "segmentationUsed",
    "threeDimEnergyDepositionRateMatrixAtTimePointUsed",
    "absorbedDoseRatePerVOIAtTimePointProduced"
})
public class SumAndScalingAbsorbedDoseRateCalculation {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "VOIIdentifierUsed")
    protected VOIIdentifierContainer voiIdentifierUsed;
    @XmlElement(name = "TimePointIdentifierUsed", required = true)
    protected String timePointIdentifierUsed;
    @XmlElement(name = "SegmentationUsed")
    protected String segmentationUsed;
    @XmlElement(name = "ThreeDimEnergyDepositionRateMatrixAtTimePointUsed", required = true)
    protected NonDICOMData threeDimEnergyDepositionRateMatrixAtTimePointUsed;
    @XmlElement(name = "AbsorbedDoseRatePerVOIAtTimePointProduced", required = true)
    protected AbsorbedDoseRatePerVOIAtTimePointContainer absorbedDoseRatePerVOIAtTimePointProduced;

    /**
     * Obtient la valeur de la propriété processExecutionContext.
     * 
     * @return
     *     possible object is
     *     {@link ProcessExecutionContext }
     *     
     */
    public ProcessExecutionContext getProcessExecutionContext() {
        return processExecutionContext;
    }

    /**
     * Définit la valeur de la propriété processExecutionContext.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcessExecutionContext }
     *     
     */
    public void setProcessExecutionContext(ProcessExecutionContext value) {
        this.processExecutionContext = value;
    }

    /**
     * Obtient la valeur de la propriété voiIdentifierUsed.
     * 
     * @return
     *     possible object is
     *     {@link VOIIdentifierContainer }
     *     
     */
    public VOIIdentifierContainer getVOIIdentifierUsed() {
        return voiIdentifierUsed;
    }

    /**
     * Définit la valeur de la propriété voiIdentifierUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link VOIIdentifierContainer }
     *     
     */
    public void setVOIIdentifierUsed(VOIIdentifierContainer value) {
        this.voiIdentifierUsed = value;
    }

    /**
     * Obtient la valeur de la propriété timePointIdentifierUsed.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimePointIdentifierUsed() {
        return timePointIdentifierUsed;
    }

    /**
     * Définit la valeur de la propriété timePointIdentifierUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimePointIdentifierUsed(String value) {
        this.timePointIdentifierUsed = value;
    }

    /**
     * Obtient la valeur de la propriété segmentationUsed.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegmentationUsed() {
        return segmentationUsed;
    }

    /**
     * Définit la valeur de la propriété segmentationUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegmentationUsed(String value) {
        this.segmentationUsed = value;
    }

    /**
     * Obtient la valeur de la propriété threeDimEnergyDepositionRateMatrixAtTimePointUsed.
     * 
     * @return
     *     possible object is
     *     {@link NonDICOMData }
     *     
     */
    public NonDICOMData getThreeDimEnergyDepositionRateMatrixAtTimePointUsed() {
        return threeDimEnergyDepositionRateMatrixAtTimePointUsed;
    }

    /**
     * Définit la valeur de la propriété threeDimEnergyDepositionRateMatrixAtTimePointUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link NonDICOMData }
     *     
     */
    public void setThreeDimEnergyDepositionRateMatrixAtTimePointUsed(NonDICOMData value) {
        this.threeDimEnergyDepositionRateMatrixAtTimePointUsed = value;
    }

    /**
     * Obtient la valeur de la propriété absorbedDoseRatePerVOIAtTimePointProduced.
     * 
     * @return
     *     possible object is
     *     {@link AbsorbedDoseRatePerVOIAtTimePointContainer }
     *     
     */
    public AbsorbedDoseRatePerVOIAtTimePointContainer getAbsorbedDoseRatePerVOIAtTimePointProduced() {
        return absorbedDoseRatePerVOIAtTimePointProduced;
    }

    /**
     * Définit la valeur de la propriété absorbedDoseRatePerVOIAtTimePointProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link AbsorbedDoseRatePerVOIAtTimePointContainer }
     *     
     */
    public void setAbsorbedDoseRatePerVOIAtTimePointProduced(AbsorbedDoseRatePerVOIAtTimePointContainer value) {
        this.absorbedDoseRatePerVOIAtTimePointProduced = value;
    }

}
