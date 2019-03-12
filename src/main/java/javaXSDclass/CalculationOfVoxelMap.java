//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2018.12.11 à 04:47:30 PM CET 
//


package javaXSDclass;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="AttenuatorUsed" type="{https://www.irdbb-medirad.com}AttenuatorType" maxOccurs="unbounded" minOccurs="0"/>
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
    protected List<AttenuatorType> attenuatorUsed;
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
     * Gets the value of the attenuatorUsed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attenuatorUsed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttenuatorUsed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AttenuatorType }
     * 
     * 
     */
    public List<AttenuatorType> getAttenuatorUsed() {
        if (attenuatorUsed == null) {
            attenuatorUsed = new ArrayList<AttenuatorType>();
        }
        return this.attenuatorUsed;
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
