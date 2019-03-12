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
 * <p>Classe Java pour TimeActivityCurveFit complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TimeActivityCurveFit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="ROIIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="TimePointIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="PreAdministeredActivityUsed" type="{https://www.irdbb-medirad.com}AdministeredActivity"/>
 *         &lt;element name="PostAdministeredActivityUsed" type="{https://www.irdbb-medirad.com}AdministeredActivity"/>
 *         &lt;element name="PKAssessmentMethodUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TimeIntegratedActivityCoefficientPerROIProduced" type="{https://www.irdbb-medirad.com}TimeIntegratedActivityCoefficientPerROI" maxOccurs="unbounded"/>
 *         &lt;element name="TimeIntegratedActivityPerROIProduced" type="{https://www.irdbb-medirad.com}TimeIntegratedActivityPerROI" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeActivityCurveFit", propOrder = {
    "processExecutionContext",
    "roiIdentifierUsed",
    "timePointIdentifierUsed",
    "preAdministeredActivityUsed",
    "postAdministeredActivityUsed",
    "pkAssessmentMethodUsed",
    "timeIntegratedActivityCoefficientPerROIProduced",
    "timeIntegratedActivityPerROIProduced"
})
public class TimeActivityCurveFit {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "ROIIdentifierUsed", required = true)
    protected List<String> roiIdentifierUsed;
    @XmlElement(name = "TimePointIdentifierUsed", required = true)
    protected List<String> timePointIdentifierUsed;
    @XmlElement(name = "PreAdministeredActivityUsed", required = true)
    protected AdministeredActivity preAdministeredActivityUsed;
    @XmlElement(name = "PostAdministeredActivityUsed", required = true)
    protected AdministeredActivity postAdministeredActivityUsed;
    @XmlElement(name = "PKAssessmentMethodUsed", required = true)
    protected String pkAssessmentMethodUsed;
    @XmlElement(name = "TimeIntegratedActivityCoefficientPerROIProduced", required = true)
    protected List<TimeIntegratedActivityCoefficientPerROI> timeIntegratedActivityCoefficientPerROIProduced;
    @XmlElement(name = "TimeIntegratedActivityPerROIProduced", required = true)
    protected List<TimeIntegratedActivityPerROI> timeIntegratedActivityPerROIProduced;

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
     * Obtient la valeur de la propriété preAdministeredActivityUsed.
     * 
     * @return
     *     possible object is
     *     {@link AdministeredActivity }
     *     
     */
    public AdministeredActivity getPreAdministeredActivityUsed() {
        return preAdministeredActivityUsed;
    }

    /**
     * Définit la valeur de la propriété preAdministeredActivityUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link AdministeredActivity }
     *     
     */
    public void setPreAdministeredActivityUsed(AdministeredActivity value) {
        this.preAdministeredActivityUsed = value;
    }

    /**
     * Obtient la valeur de la propriété postAdministeredActivityUsed.
     * 
     * @return
     *     possible object is
     *     {@link AdministeredActivity }
     *     
     */
    public AdministeredActivity getPostAdministeredActivityUsed() {
        return postAdministeredActivityUsed;
    }

    /**
     * Définit la valeur de la propriété postAdministeredActivityUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link AdministeredActivity }
     *     
     */
    public void setPostAdministeredActivityUsed(AdministeredActivity value) {
        this.postAdministeredActivityUsed = value;
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
     * Gets the value of the timeIntegratedActivityCoefficientPerROIProduced property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the timeIntegratedActivityCoefficientPerROIProduced property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTimeIntegratedActivityCoefficientPerROIProduced().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TimeIntegratedActivityCoefficientPerROI }
     * 
     * 
     */
    public List<TimeIntegratedActivityCoefficientPerROI> getTimeIntegratedActivityCoefficientPerROIProduced() {
        if (timeIntegratedActivityCoefficientPerROIProduced == null) {
            timeIntegratedActivityCoefficientPerROIProduced = new ArrayList<TimeIntegratedActivityCoefficientPerROI>();
        }
        return this.timeIntegratedActivityCoefficientPerROIProduced;
    }

    /**
     * Gets the value of the timeIntegratedActivityPerROIProduced property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the timeIntegratedActivityPerROIProduced property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTimeIntegratedActivityPerROIProduced().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TimeIntegratedActivityPerROI }
     * 
     * 
     */
    public List<TimeIntegratedActivityPerROI> getTimeIntegratedActivityPerROIProduced() {
        if (timeIntegratedActivityPerROIProduced == null) {
            timeIntegratedActivityPerROIProduced = new ArrayList<TimeIntegratedActivityPerROI>();
        }
        return this.timeIntegratedActivityPerROIProduced;
    }

}
