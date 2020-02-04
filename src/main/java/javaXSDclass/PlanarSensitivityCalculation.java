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
 *         &lt;element name="ROIIdentifierUsed" type="{https://www.irdbb-medirad.com}ROIIdentifierContainer"/>
 *         &lt;element name="NMStaticCorrectedUsed" type="{https://www.irdbb-medirad.com}DICOMData"/>
 *         &lt;element name="PlanarCalibrationCoefficientProduced" type="{https://www.irdbb-medirad.com}CalibrationCoefficient"/>
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
    "planarCalibrationCoefficientProduced"
})
public class PlanarSensitivityCalculation {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "ROIIdentifierUsed", required = true)
    protected ROIIdentifierContainer roiIdentifierUsed;
    @XmlElement(name = "NMStaticCorrectedUsed", required = true)
    protected DICOMData nmStaticCorrectedUsed;
    @XmlElement(name = "PlanarCalibrationCoefficientProduced", required = true)
    protected CalibrationCoefficient planarCalibrationCoefficientProduced;

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
     * Obtient la valeur de la propriété planarCalibrationCoefficientProduced.
     * 
     * @return
     *     possible object is
     *     {@link CalibrationCoefficient }
     *     
     */
    public CalibrationCoefficient getPlanarCalibrationCoefficientProduced() {
        return planarCalibrationCoefficientProduced;
    }

    /**
     * Définit la valeur de la propriété planarCalibrationCoefficientProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link CalibrationCoefficient }
     *     
     */
    public void setPlanarCalibrationCoefficientProduced(CalibrationCoefficient value) {
        this.planarCalibrationCoefficientProduced = value;
    }

}
