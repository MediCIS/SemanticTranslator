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
 * <p>Classe Java pour EnergyDepositionRateCalculation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="EnergyDepositionRateCalculation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="VOIIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="TimePointIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VOIUsed" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="CTReconResampledOnNMReferenceUsed" type="{https://www.irdbb-medirad.com}DICOMData"/>
 *         &lt;element name="NMTomoReconUsed" type="{https://www.irdbb-medirad.com}DICOMData"/>
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
@XmlType(name = "EnergyDepositionRateCalculation", propOrder = {
    "processExecutionContext",
    "voiIdentifierUsed",
    "timePointIdentifierUsed",
    "voiUsed",
    "ctReconResampledOnNMReferenceUsed",
    "nmTomoReconUsed",
    "threeDimEnergyDepositionRateMatrixAtTimePointProduced"
})
public class EnergyDepositionRateCalculation {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "VOIIdentifierUsed", required = true)
    protected List<String> voiIdentifierUsed;
    @XmlElement(name = "TimePointIdentifierUsed", required = true)
    protected String timePointIdentifierUsed;
    @XmlElement(name = "VOIUsed", required = true)
    protected List<String> voiUsed;
    @XmlElement(name = "CTReconResampledOnNMReferenceUsed", required = true)
    protected DICOMData ctReconResampledOnNMReferenceUsed;
    @XmlElement(name = "NMTomoReconUsed", required = true)
    protected DICOMData nmTomoReconUsed;
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
     * Gets the value of the voiIdentifierUsed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the voiIdentifierUsed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVOIIdentifierUsed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getVOIIdentifierUsed() {
        if (voiIdentifierUsed == null) {
            voiIdentifierUsed = new ArrayList<String>();
        }
        return this.voiIdentifierUsed;
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
     * Gets the value of the voiUsed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the voiUsed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVOIUsed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getVOIUsed() {
        if (voiUsed == null) {
            voiUsed = new ArrayList<String>();
        }
        return this.voiUsed;
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
