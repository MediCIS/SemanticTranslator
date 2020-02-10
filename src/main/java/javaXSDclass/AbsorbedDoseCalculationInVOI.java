//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.06 à 05:03:32 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour AbsorbedDoseCalculationInVOI complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="AbsorbedDoseCalculationInVOI">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="SegmentationIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AbsorbedDoseCalculationMethodUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VoxelAbsorbedDoseMapProduced" type="{https://www.irdbb-medirad.com}NonDICOMDataContainer" minOccurs="0"/>
 *         &lt;element name="AbsorbedDoseInVOIContainer" type="{https://www.irdbb-medirad.com}AbsorbedDoseInVOIContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbsorbedDoseCalculationInVOI", propOrder = {
    "processExecutionContext",
    "segmentationIdentifierUsed",
    "absorbedDoseCalculationMethodUsed",
    "voxelAbsorbedDoseMapProduced",
    "absorbedDoseInVOIContainer"
})
public class AbsorbedDoseCalculationInVOI {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "SegmentationIdentifierUsed")
    protected String segmentationIdentifierUsed;
    @XmlElement(name = "AbsorbedDoseCalculationMethodUsed", required = true)
    protected String absorbedDoseCalculationMethodUsed;
    @XmlElement(name = "VoxelAbsorbedDoseMapProduced")
    protected NonDICOMDataContainer voxelAbsorbedDoseMapProduced;
    @XmlElement(name = "AbsorbedDoseInVOIContainer")
    protected AbsorbedDoseInVOIContainer absorbedDoseInVOIContainer;

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
     * Obtient la valeur de la propriété segmentationIdentifierUsed.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegmentationIdentifierUsed() {
        return segmentationIdentifierUsed;
    }

    /**
     * Définit la valeur de la propriété segmentationIdentifierUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegmentationIdentifierUsed(String value) {
        this.segmentationIdentifierUsed = value;
    }

    /**
     * Obtient la valeur de la propriété absorbedDoseCalculationMethodUsed.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbsorbedDoseCalculationMethodUsed() {
        return absorbedDoseCalculationMethodUsed;
    }

    /**
     * Définit la valeur de la propriété absorbedDoseCalculationMethodUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbsorbedDoseCalculationMethodUsed(String value) {
        this.absorbedDoseCalculationMethodUsed = value;
    }

    /**
     * Obtient la valeur de la propriété voxelAbsorbedDoseMapProduced.
     * 
     * @return
     *     possible object is
     *     {@link NonDICOMDataContainer }
     *     
     */
    public NonDICOMDataContainer getVoxelAbsorbedDoseMapProduced() {
        return voxelAbsorbedDoseMapProduced;
    }

    /**
     * Définit la valeur de la propriété voxelAbsorbedDoseMapProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link NonDICOMDataContainer }
     *     
     */
    public void setVoxelAbsorbedDoseMapProduced(NonDICOMDataContainer value) {
        this.voxelAbsorbedDoseMapProduced = value;
    }

    /**
     * Obtient la valeur de la propriété absorbedDoseInVOIContainer.
     * 
     * @return
     *     possible object is
     *     {@link AbsorbedDoseInVOIContainer }
     *     
     */
    public AbsorbedDoseInVOIContainer getAbsorbedDoseInVOIContainer() {
        return absorbedDoseInVOIContainer;
    }

    /**
     * Définit la valeur de la propriété absorbedDoseInVOIContainer.
     * 
     * @param value
     *     allowed object is
     *     {@link AbsorbedDoseInVOIContainer }
     *     
     */
    public void setAbsorbedDoseInVOIContainer(AbsorbedDoseInVOIContainer value) {
        this.absorbedDoseInVOIContainer = value;
    }

}
