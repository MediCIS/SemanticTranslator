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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CalculationOfVoxelMap complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CalculationOfVoxelMap">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="AttenuatorUsed" type="{https://www.irdbb-medirad.com}AttenuatorTypeContainer" minOccurs="0"/>
 *         &lt;element name="MonteCarloMethodUsed" type="{https://www.irdbb-medirad.com}MonteCarloMethodType" minOccurs="0"/>
 *         &lt;element name="DICOMCTImageDataUsed" type="{https://www.irdbb-medirad.com}DICOMData"/>
 *         &lt;element name="VoxelBasedDistributionOfAbsorbedDoseProduced" type="{https://www.irdbb-medirad.com}VoxelBasedDistributionOfAbsorbedDoseType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalculationOfVoxelMap", propOrder = {
    "processExecutionContext",
    "attenuatorUsed",
    "monteCarloMethodUsed",
    "dicomctImageDataUsed",
    "voxelBasedDistributionOfAbsorbedDoseProduced"
})
public class CalculationOfVoxelMap {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "AttenuatorUsed")
    protected AttenuatorTypeContainer attenuatorUsed;
    @XmlElement(name = "MonteCarloMethodUsed")
    protected MonteCarloMethodType monteCarloMethodUsed;
    @XmlElement(name = "DICOMCTImageDataUsed", required = true)
    protected DICOMData dicomctImageDataUsed;
    @XmlElement(name = "VoxelBasedDistributionOfAbsorbedDoseProduced")
    protected VoxelBasedDistributionOfAbsorbedDoseType voxelBasedDistributionOfAbsorbedDoseProduced;

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
     * Obtient la valeur de la propriété attenuatorUsed.
     * 
     * @return
     *     possible object is
     *     {@link AttenuatorTypeContainer }
     *     
     */
    public AttenuatorTypeContainer getAttenuatorUsed() {
        return attenuatorUsed;
    }

    /**
     * Définit la valeur de la propriété attenuatorUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link AttenuatorTypeContainer }
     *     
     */
    public void setAttenuatorUsed(AttenuatorTypeContainer value) {
        this.attenuatorUsed = value;
    }

    /**
     * Obtient la valeur de la propriété monteCarloMethodUsed.
     * 
     * @return
     *     possible object is
     *     {@link MonteCarloMethodType }
     *     
     */
    public MonteCarloMethodType getMonteCarloMethodUsed() {
        return monteCarloMethodUsed;
    }

    /**
     * Définit la valeur de la propriété monteCarloMethodUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link MonteCarloMethodType }
     *     
     */
    public void setMonteCarloMethodUsed(MonteCarloMethodType value) {
        this.monteCarloMethodUsed = value;
    }

    /**
     * Obtient la valeur de la propriété dicomctImageDataUsed.
     * 
     * @return
     *     possible object is
     *     {@link DICOMData }
     *     
     */
    public DICOMData getDICOMCTImageDataUsed() {
        return dicomctImageDataUsed;
    }

    /**
     * Définit la valeur de la propriété dicomctImageDataUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMData }
     *     
     */
    public void setDICOMCTImageDataUsed(DICOMData value) {
        this.dicomctImageDataUsed = value;
    }

    /**
     * Obtient la valeur de la propriété voxelBasedDistributionOfAbsorbedDoseProduced.
     * 
     * @return
     *     possible object is
     *     {@link VoxelBasedDistributionOfAbsorbedDoseType }
     *     
     */
    public VoxelBasedDistributionOfAbsorbedDoseType getVoxelBasedDistributionOfAbsorbedDoseProduced() {
        return voxelBasedDistributionOfAbsorbedDoseProduced;
    }

    /**
     * Définit la valeur de la propriété voxelBasedDistributionOfAbsorbedDoseProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link VoxelBasedDistributionOfAbsorbedDoseType }
     *     
     */
    public void setVoxelBasedDistributionOfAbsorbedDoseProduced(VoxelBasedDistributionOfAbsorbedDoseType value) {
        this.voxelBasedDistributionOfAbsorbedDoseProduced = value;
    }

}
