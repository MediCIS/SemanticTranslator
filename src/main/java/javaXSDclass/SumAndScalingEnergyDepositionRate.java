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
 * <p>Classe Java pour SumAndScalingEnergyDepositionRate complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SumAndScalingEnergyDepositionRate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="TimePointIdentifierUsed" type="{https://www.irdbb-medirad.com}TimePointIdentifierContainer"/>
 *         &lt;element name="ROIIdentifierUsed" type="{https://www.irdbb-medirad.com}ROIIdentifierContainer"/>
 *         &lt;element name="VOIIdentifierUsed" type="{https://www.irdbb-medirad.com}VOIIdentifierContainer"/>
 *         &lt;element name="VOIInSPECTIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EnergyDepositionRatePerVOIAtTimePointProduced" type="{https://www.irdbb-medirad.com}EnergyDepositionRatePerVOIAtTimePointContainer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SumAndScalingEnergyDepositionRate", propOrder = {
    "processExecutionContext",
    "timePointIdentifierUsed",
    "roiIdentifierUsed",
    "voiIdentifierUsed",
    "voiInSPECTIdentifierUsed",
    "energyDepositionRatePerVOIAtTimePointProduced"
})
public class SumAndScalingEnergyDepositionRate {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "TimePointIdentifierUsed", required = true)
    protected TimePointIdentifierContainer timePointIdentifierUsed;
    @XmlElement(name = "ROIIdentifierUsed", required = true)
    protected ROIIdentifierContainer roiIdentifierUsed;
    @XmlElement(name = "VOIIdentifierUsed", required = true)
    protected VOIIdentifierContainer voiIdentifierUsed;
    @XmlElement(name = "VOIInSPECTIdentifierUsed", required = true)
    protected String voiInSPECTIdentifierUsed;
    @XmlElement(name = "EnergyDepositionRatePerVOIAtTimePointProduced", required = true)
    protected EnergyDepositionRatePerVOIAtTimePointContainer energyDepositionRatePerVOIAtTimePointProduced;

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
     * Obtient la valeur de la propriété timePointIdentifierUsed.
     * 
     * @return
     *     possible object is
     *     {@link TimePointIdentifierContainer }
     *     
     */
    public TimePointIdentifierContainer getTimePointIdentifierUsed() {
        return timePointIdentifierUsed;
    }

    /**
     * Définit la valeur de la propriété timePointIdentifierUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePointIdentifierContainer }
     *     
     */
    public void setTimePointIdentifierUsed(TimePointIdentifierContainer value) {
        this.timePointIdentifierUsed = value;
    }

    /**
     * Obtient la valeur de la propriété roiIdentifierUsed.
     * 
     * @return
     *     possible object is
     *     {@link ROIIdentifierContainer }
     *     
     */
    public ROIIdentifierContainer getROIIdentifierUsed() {
        return roiIdentifierUsed;
    }

    /**
     * Définit la valeur de la propriété roiIdentifierUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link ROIIdentifierContainer }
     *     
     */
    public void setROIIdentifierUsed(ROIIdentifierContainer value) {
        this.roiIdentifierUsed = value;
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
     * Obtient la valeur de la propriété voiInSPECTIdentifierUsed.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVOIInSPECTIdentifierUsed() {
        return voiInSPECTIdentifierUsed;
    }

    /**
     * Définit la valeur de la propriété voiInSPECTIdentifierUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVOIInSPECTIdentifierUsed(String value) {
        this.voiInSPECTIdentifierUsed = value;
    }

    /**
     * Obtient la valeur de la propriété energyDepositionRatePerVOIAtTimePointProduced.
     * 
     * @return
     *     possible object is
     *     {@link EnergyDepositionRatePerVOIAtTimePointContainer }
     *     
     */
    public EnergyDepositionRatePerVOIAtTimePointContainer getEnergyDepositionRatePerVOIAtTimePointProduced() {
        return energyDepositionRatePerVOIAtTimePointProduced;
    }

    /**
     * Définit la valeur de la propriété energyDepositionRatePerVOIAtTimePointProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link EnergyDepositionRatePerVOIAtTimePointContainer }
     *     
     */
    public void setEnergyDepositionRatePerVOIAtTimePointProduced(EnergyDepositionRatePerVOIAtTimePointContainer value) {
        this.energyDepositionRatePerVOIAtTimePointProduced = value;
    }

}
