//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.10.24 à 03:08:52 PM CEST 
//


package javaXSDclass;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TimeActivityCurveFitIn3DDosimetry complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TimeActivityCurveFitIn3DDosimetry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="VOIIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TimePointIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="PreAdministeredActivityUsed" type="{https://www.irdbb-medirad.com}AdministeredActivity"/>
 *         &lt;element name="PostAdministeredActivityUsed" type="{https://www.irdbb-medirad.com}AdministeredActivity"/>
 *         &lt;element name="PKAssessmentMethodUsed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TimeIntegratedActivityCoefficientPerVOIProduced" type="{https://www.irdbb-medirad.com}TimeIntegratedActivityCoefficientPerVOI"/>
 *         &lt;element name="TimeIntegratedActivityPerVOIProduced" type="{https://www.irdbb-medirad.com}TimeIntegratedActivityPerVOI"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeActivityCurveFitIn3DDosimetry", propOrder = {
    "processExecutionContext",
    "voiIdentifierUsed",
    "timePointIdentifierUsed",
    "preAdministeredActivityUsed",
    "postAdministeredActivityUsed",
    "pkAssessmentMethodUsed",
    "timeIntegratedActivityCoefficientPerVOIProduced",
    "timeIntegratedActivityPerVOIProduced"
})
public class TimeActivityCurveFitIn3DDosimetry {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "VOIIdentifierUsed", required = true)
    protected String voiIdentifierUsed;
    @XmlElement(name = "TimePointIdentifierUsed", required = true)
    protected List<String> timePointIdentifierUsed;
    @XmlElement(name = "PreAdministeredActivityUsed", required = true)
    protected AdministeredActivity preAdministeredActivityUsed;
    @XmlElement(name = "PostAdministeredActivityUsed", required = true)
    protected AdministeredActivity postAdministeredActivityUsed;
    @XmlElement(name = "PKAssessmentMethodUsed", required = true)
    protected String pkAssessmentMethodUsed;
    @XmlElement(name = "TimeIntegratedActivityCoefficientPerVOIProduced", required = true)
    protected TimeIntegratedActivityCoefficientPerVOI timeIntegratedActivityCoefficientPerVOIProduced;
    @XmlElement(name = "TimeIntegratedActivityPerVOIProduced", required = true)
    protected TimeIntegratedActivityPerVOI timeIntegratedActivityPerVOIProduced;

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
     * Obtient la valeur de la propriété voiIdentifierUsed.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVOIIdentifierUsed() {
        return voiIdentifierUsed;
    }

    /**
     * Définit la valeur de la propriété voiIdentifierUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVOIIdentifierUsed(String value) {
        this.voiIdentifierUsed = value;
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
     * Obtient la valeur de la propriété timeIntegratedActivityCoefficientPerVOIProduced.
     * 
     * @return
     *     possible object is
     *     {@link TimeIntegratedActivityCoefficientPerVOI }
     *     
     */
    public TimeIntegratedActivityCoefficientPerVOI getTimeIntegratedActivityCoefficientPerVOIProduced() {
        return timeIntegratedActivityCoefficientPerVOIProduced;
    }

    /**
     * Définit la valeur de la propriété timeIntegratedActivityCoefficientPerVOIProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeIntegratedActivityCoefficientPerVOI }
     *     
     */
    public void setTimeIntegratedActivityCoefficientPerVOIProduced(TimeIntegratedActivityCoefficientPerVOI value) {
        this.timeIntegratedActivityCoefficientPerVOIProduced = value;
    }

    /**
     * Obtient la valeur de la propriété timeIntegratedActivityPerVOIProduced.
     * 
     * @return
     *     possible object is
     *     {@link TimeIntegratedActivityPerVOI }
     *     
     */
    public TimeIntegratedActivityPerVOI getTimeIntegratedActivityPerVOIProduced() {
        return timeIntegratedActivityPerVOIProduced;
    }

    /**
     * Définit la valeur de la propriété timeIntegratedActivityPerVOIProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeIntegratedActivityPerVOI }
     *     
     */
    public void setTimeIntegratedActivityPerVOIProduced(TimeIntegratedActivityPerVOI value) {
        this.timeIntegratedActivityPerVOIProduced = value;
    }

}
