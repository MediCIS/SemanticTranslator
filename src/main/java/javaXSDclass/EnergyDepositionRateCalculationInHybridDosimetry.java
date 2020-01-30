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
 * <p>Classe Java pour EnergyDepositionRateCalculationInHybridDosimetry complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="EnergyDepositionRateCalculationInHybridDosimetry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="TimePointIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VoxelDataActivityAtTimePointIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CTReconResampledOnSPECTUsed" type="{https://www.irdbb-medirad.com}DICOMData"/>
 *         &lt;element name="ThreeDimEnergyDepositionRateMatrixAtTimePointProduced" type="{https://www.irdbb-medirad.com}NonDICOMData"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnergyDepositionRateCalculationInHybridDosimetry", propOrder = {
    "processExecutionContext",
    "timePointIdentifierUsed",
    "voxelDataActivityAtTimePointIdentifier",
    "ctReconResampledOnSPECTUsed",
    "threeDimEnergyDepositionRateMatrixAtTimePointProduced"
})
public class EnergyDepositionRateCalculationInHybridDosimetry {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "TimePointIdentifierUsed", required = true)
    protected String timePointIdentifierUsed;
    @XmlElement(name = "VoxelDataActivityAtTimePointIdentifier", required = true)
    protected String voxelDataActivityAtTimePointIdentifier;
    @XmlElement(name = "CTReconResampledOnSPECTUsed", required = true)
    protected DICOMData ctReconResampledOnSPECTUsed;
    @XmlElement(name = "ThreeDimEnergyDepositionRateMatrixAtTimePointProduced", required = true)
    protected NonDICOMData threeDimEnergyDepositionRateMatrixAtTimePointProduced;

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
     * Obtient la valeur de la propriété voxelDataActivityAtTimePointIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVoxelDataActivityAtTimePointIdentifier() {
        return voxelDataActivityAtTimePointIdentifier;
    }

    /**
     * Définit la valeur de la propriété voxelDataActivityAtTimePointIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVoxelDataActivityAtTimePointIdentifier(String value) {
        this.voxelDataActivityAtTimePointIdentifier = value;
    }

    /**
     * Obtient la valeur de la propriété ctReconResampledOnSPECTUsed.
     * 
     * @return
     *     possible object is
     *     {@link DICOMData }
     *     
     */
    public DICOMData getCTReconResampledOnSPECTUsed() {
        return ctReconResampledOnSPECTUsed;
    }

    /**
     * Définit la valeur de la propriété ctReconResampledOnSPECTUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMData }
     *     
     */
    public void setCTReconResampledOnSPECTUsed(DICOMData value) {
        this.ctReconResampledOnSPECTUsed = value;
    }

    /**
     * Obtient la valeur de la propriété threeDimEnergyDepositionRateMatrixAtTimePointProduced.
     * 
     * @return
     *     possible object is
     *     {@link NonDICOMData }
     *     
     */
    public NonDICOMData getThreeDimEnergyDepositionRateMatrixAtTimePointProduced() {
        return threeDimEnergyDepositionRateMatrixAtTimePointProduced;
    }

    /**
     * Définit la valeur de la propriété threeDimEnergyDepositionRateMatrixAtTimePointProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link NonDICOMData }
     *     
     */
    public void setThreeDimEnergyDepositionRateMatrixAtTimePointProduced(NonDICOMData value) {
        this.threeDimEnergyDepositionRateMatrixAtTimePointProduced = value;
    }

}
