//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.12 à 04:30:42 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour EnergyDepositionRateCalculationIn3DDosimetry complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="EnergyDepositionRateCalculationIn3DDosimetry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="VOIIdentifierUsed" type="{https://www.irdbb-medirad.com}VOIIdentifierContainer" minOccurs="0"/>
 *         &lt;element name="TimePointIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CTReconResampledOnNMReferenceUsed" type="{https://www.irdbb-medirad.com}DICOMData"/>
 *         &lt;element name="NMTomoReconUsed" type="{https://www.irdbb-medirad.com}DICOMData"/>
 *         &lt;element name="CalculationAlgorithmUsed" type="{https://www.irdbb-medirad.com}CalculationAlgorithmUsed"/>
 *         &lt;element name="KernelLimitForConvolutionsUsed" type="{https://www.irdbb-medirad.com}KernelLimitForConvolutions" minOccurs="0"/>
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
@XmlType(name = "EnergyDepositionRateCalculationIn3DDosimetry", propOrder = {
    "processExecutionContext",
    "voiIdentifierUsed",
    "timePointIdentifierUsed",
    "ctReconResampledOnNMReferenceUsed",
    "nmTomoReconUsed",
    "calculationAlgorithmUsed",
    "kernelLimitForConvolutionsUsed",
    "threeDimEnergyDepositionRateMatrixAtTimePointProduced"
})
public class EnergyDepositionRateCalculationIn3DDosimetry {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "VOIIdentifierUsed")
    protected VOIIdentifierContainer voiIdentifierUsed;
    @XmlElement(name = "TimePointIdentifierUsed", required = true)
    protected String timePointIdentifierUsed;
    @XmlElement(name = "CTReconResampledOnNMReferenceUsed", required = true)
    protected DICOMData ctReconResampledOnNMReferenceUsed;
    @XmlElement(name = "NMTomoReconUsed", required = true)
    protected DICOMData nmTomoReconUsed;
    @XmlElement(name = "CalculationAlgorithmUsed", required = true)
    @XmlSchemaType(name = "string")
    protected CalculationAlgorithmUsed calculationAlgorithmUsed;
    @XmlElement(name = "KernelLimitForConvolutionsUsed")
    protected KernelLimitForConvolutions kernelLimitForConvolutionsUsed;
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
     * Obtient la valeur de la propriété ctReconResampledOnNMReferenceUsed.
     * 
     * @return
     *     possible object is
     *     {@link DICOMData }
     *     
     */
    public DICOMData getCTReconResampledOnNMReferenceUsed() {
        return ctReconResampledOnNMReferenceUsed;
    }

    /**
     * Définit la valeur de la propriété ctReconResampledOnNMReferenceUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMData }
     *     
     */
    public void setCTReconResampledOnNMReferenceUsed(DICOMData value) {
        this.ctReconResampledOnNMReferenceUsed = value;
    }

    /**
     * Obtient la valeur de la propriété nmTomoReconUsed.
     * 
     * @return
     *     possible object is
     *     {@link DICOMData }
     *     
     */
    public DICOMData getNMTomoReconUsed() {
        return nmTomoReconUsed;
    }

    /**
     * Définit la valeur de la propriété nmTomoReconUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMData }
     *     
     */
    public void setNMTomoReconUsed(DICOMData value) {
        this.nmTomoReconUsed = value;
    }

    /**
     * Obtient la valeur de la propriété calculationAlgorithmUsed.
     * 
     * @return
     *     possible object is
     *     {@link CalculationAlgorithmUsed }
     *     
     */
    public CalculationAlgorithmUsed getCalculationAlgorithmUsed() {
        return calculationAlgorithmUsed;
    }

    /**
     * Définit la valeur de la propriété calculationAlgorithmUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link CalculationAlgorithmUsed }
     *     
     */
    public void setCalculationAlgorithmUsed(CalculationAlgorithmUsed value) {
        this.calculationAlgorithmUsed = value;
    }

    /**
     * Obtient la valeur de la propriété kernelLimitForConvolutionsUsed.
     * 
     * @return
     *     possible object is
     *     {@link KernelLimitForConvolutions }
     *     
     */
    public KernelLimitForConvolutions getKernelLimitForConvolutionsUsed() {
        return kernelLimitForConvolutionsUsed;
    }

    /**
     * Définit la valeur de la propriété kernelLimitForConvolutionsUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link KernelLimitForConvolutions }
     *     
     */
    public void setKernelLimitForConvolutionsUsed(KernelLimitForConvolutions value) {
        this.kernelLimitForConvolutionsUsed = value;
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
