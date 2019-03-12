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
 * <p>Classe Java pour TimeAbsorbedDoseCurveFit complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TimeAbsorbedDoseCurveFit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="ROIIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="TimePointIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="PKAssessmentMethodUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MeanAbsorbedDoseInROIProduced" type="{https://www.irdbb-medirad.com}MeanAbsorbedDoseInROI" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeAbsorbedDoseCurveFit", propOrder = {
    "processExecutionContext",
    "roiIdentifierUsed",
    "timePointIdentifierUsed",
    "pkAssessmentMethodUsed",
    "meanAbsorbedDoseInROIProduced"
})
public class TimeAbsorbedDoseCurveFit {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "ROIIdentifierUsed", required = true)
    protected List<String> roiIdentifierUsed;
    @XmlElement(name = "TimePointIdentifierUsed", required = true)
    protected List<String> timePointIdentifierUsed;
    @XmlElement(name = "PKAssessmentMethodUsed", required = true)
    protected String pkAssessmentMethodUsed;
    @XmlElement(name = "MeanAbsorbedDoseInROIProduced", required = true)
    protected List<MeanAbsorbedDoseInROI> meanAbsorbedDoseInROIProduced;

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
     * Gets the value of the timePointIdentifierUsed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the timePointIdentifierUsed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTimePointIdentifierUsed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getTimePointIdentifierUsed() {
        if (timePointIdentifierUsed == null) {
            timePointIdentifierUsed = new ArrayList<String>();
        }
        return this.timePointIdentifierUsed;
    }

    /**
     * Obtient la valeur de la propriété pkAssessmentMethodUsed.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPKAssessmentMethodUsed() {
        return pkAssessmentMethodUsed;
    }

    /**
     * Définit la valeur de la propriété pkAssessmentMethodUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPKAssessmentMethodUsed(String value) {
        this.pkAssessmentMethodUsed = value;
    }

    /**
     * Gets the value of the meanAbsorbedDoseInROIProduced property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the meanAbsorbedDoseInROIProduced property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMeanAbsorbedDoseInROIProduced().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MeanAbsorbedDoseInROI }
     * 
     * 
     */
    public List<MeanAbsorbedDoseInROI> getMeanAbsorbedDoseInROIProduced() {
        if (meanAbsorbedDoseInROIProduced == null) {
            meanAbsorbedDoseInROIProduced = new ArrayList<MeanAbsorbedDoseInROI>();
        }
        return this.meanAbsorbedDoseInROIProduced;
    }

}
