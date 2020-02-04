//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.04 à 12:01:52 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CalculationOfAbsorbedDosesInVOIs complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CalculationOfAbsorbedDosesInVOIs">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="VoxelBasedDistributionOfAbsorbedDoseIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VOIIdentifierUsed" type="{https://www.irdbb-medirad.com}VOIIdentifierContainer" minOccurs="0"/>
 *         &lt;element name="AbsorbedDosePerVOIProduced" type="{https://www.irdbb-medirad.com}AbsorbedDosePerVOITypeContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalculationOfAbsorbedDosesInVOIs", propOrder = {
    "processExecutionContext",
    "voxelBasedDistributionOfAbsorbedDoseIdentifierUsed",
    "voiIdentifierUsed",
    "absorbedDosePerVOIProduced"
})
public class CalculationOfAbsorbedDosesInVOIs {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "VoxelBasedDistributionOfAbsorbedDoseIdentifierUsed", required = true)
    protected String voxelBasedDistributionOfAbsorbedDoseIdentifierUsed;
    @XmlElement(name = "VOIIdentifierUsed")
    protected VOIIdentifierContainer voiIdentifierUsed;
    @XmlElement(name = "AbsorbedDosePerVOIProduced")
    protected AbsorbedDosePerVOITypeContainer absorbedDosePerVOIProduced;

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
     * Obtient la valeur de la propriété voxelBasedDistributionOfAbsorbedDoseIdentifierUsed.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVoxelBasedDistributionOfAbsorbedDoseIdentifierUsed() {
        return voxelBasedDistributionOfAbsorbedDoseIdentifierUsed;
    }

    /**
     * Définit la valeur de la propriété voxelBasedDistributionOfAbsorbedDoseIdentifierUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVoxelBasedDistributionOfAbsorbedDoseIdentifierUsed(String value) {
        this.voxelBasedDistributionOfAbsorbedDoseIdentifierUsed = value;
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
     * Obtient la valeur de la propriété absorbedDosePerVOIProduced.
     * 
     * @return
     *     possible object is
     *     {@link AbsorbedDosePerVOITypeContainer }
     *     
     */
    public AbsorbedDosePerVOITypeContainer getAbsorbedDosePerVOIProduced() {
        return absorbedDosePerVOIProduced;
    }

    /**
     * Définit la valeur de la propriété absorbedDosePerVOIProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link AbsorbedDosePerVOITypeContainer }
     *     
     */
    public void setAbsorbedDosePerVOIProduced(AbsorbedDosePerVOITypeContainer value) {
        this.absorbedDosePerVOIProduced = value;
    }

}
