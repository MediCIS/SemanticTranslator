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
 * <p>Classe Java pour PlanarSensitivityCalculation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PlanarSensitivityCalculation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="ROIIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="NMStaticCorrectedUsed" type="{https://www.irdbb-medirad.com}DICOMData"/>
 *         &lt;element name="CalibrationCoefficientProduced" type="{https://www.irdbb-medirad.com}CalibrationCoefficient"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlanarSensitivityCalculation", propOrder = {
    "processExecutionContext",
    "roiIdentifierUsed",
    "nmStaticCorrectedUsed",
    "calibrationCoefficientProduced"
})
public class PlanarSensitivityCalculation {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "ROIIdentifierUsed", required = true)
    protected List<String> roiIdentifierUsed;
    @XmlElement(name = "NMStaticCorrectedUsed", required = true)
    protected DICOMData nmStaticCorrectedUsed;
    @XmlElement(name = "CalibrationCoefficientProduced", required = true)
    protected CalibrationCoefficient calibrationCoefficientProduced;

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
     * Gets the value of the roiIdentifierUsed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roiIdentifierUsed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getROIIdentifierUsed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getROIIdentifierUsed() {
        if (roiIdentifierUsed == null) {
            roiIdentifierUsed = new ArrayList<String>();
        }
        return this.roiIdentifierUsed;
    }

    /**
     * Obtient la valeur de la propriété nmStaticCorrectedUsed.
     * 
     * @return
     *     possible object is
     *     {@link DICOMData }
     *     
     */
    public DICOMData getNMStaticCorrectedUsed() {
        return nmStaticCorrectedUsed;
    }

    /**
     * Définit la valeur de la propriété nmStaticCorrectedUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMData }
     *     
     */
    public void setNMStaticCorrectedUsed(DICOMData value) {
        this.nmStaticCorrectedUsed = value;
    }

    /**
     * Obtient la valeur de la propriété calibrationCoefficientProduced.
     * 
     * @return
     *     possible object is
     *     {@link CalibrationCoefficient }
     *     
     */
    public CalibrationCoefficient getCalibrationCoefficientProduced() {
        return calibrationCoefficientProduced;
    }

    /**
     * Définit la valeur de la propriété calibrationCoefficientProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link CalibrationCoefficient }
     *     
     */
    public void setCalibrationCoefficientProduced(CalibrationCoefficient value) {
        this.calibrationCoefficientProduced = value;
    }

}
