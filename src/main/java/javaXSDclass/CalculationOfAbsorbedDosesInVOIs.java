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
 *         &lt;element name="VOIIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AbsorbedDosePerVOIProduced" type="{https://www.irdbb-medirad.com}AbsorbedDosePerVOIType" maxOccurs="unbounded" minOccurs="0"/>
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
    protected List<String> voiIdentifierUsed;
    @XmlElement(name = "AbsorbedDosePerVOIProduced")
    protected List<AbsorbedDosePerVOIType> absorbedDosePerVOIProduced;

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
     * Gets the value of the absorbedDosePerVOIProduced property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the absorbedDosePerVOIProduced property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAbsorbedDosePerVOIProduced().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AbsorbedDosePerVOIType }
     * 
     * 
     */
    public List<AbsorbedDosePerVOIType> getAbsorbedDosePerVOIProduced() {
        if (absorbedDosePerVOIProduced == null) {
            absorbedDosePerVOIProduced = new ArrayList<AbsorbedDosePerVOIType>();
        }
        return this.absorbedDosePerVOIProduced;
    }

}
