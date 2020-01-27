//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.01.24 à 06:05:31 PM CET 
//


package javaXSDclass;

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
 *         &lt;element name="ROIIdentifierUsed" type="{https://www.irdbb-medirad.com}ROIIdentifierUsedContainer"/>
 *         &lt;element name="TimePointIdentifierUsedContainer" type="{https://www.irdbb-medirad.com}TimePointIdentifierUsedContainer"/>
 *         &lt;element name="PreAdministeredActivityUsed" type="{https://www.irdbb-medirad.com}AdministeredActivity"/>
 *         &lt;element name="PostAdministeredActivityUsed" type="{https://www.irdbb-medirad.com}AdministeredActivity"/>
 *         &lt;element name="PKAssessmentMethodUsed" type="{https://www.irdbb-medirad.com}CurveFittingMethod"/>
 *         &lt;element name="TimeIntegratedActivityCoefficientPerROIcontainer" type="{https://www.irdbb-medirad.com}TimeIntegratedActivityCoefficientPerROIcontainer"/>
 *         &lt;element name="TimeIntegratedActivityPerROIcontainer" type="{https://www.irdbb-medirad.com}TimeIntegratedActivityPerROIcontainer"/>
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
    "timePointIdentifierUsedContainer",
    "preAdministeredActivityUsed",
    "postAdministeredActivityUsed",
    "pkAssessmentMethodUsed",
    "timeIntegratedActivityCoefficientPerROIcontainer",
    "timeIntegratedActivityPerROIcontainer"
})
public class TimeActivityCurveFit {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "ROIIdentifierUsed", required = true)
    protected ROIIdentifierUsedContainer roiIdentifierUsed;
    @XmlElement(name = "TimePointIdentifierUsedContainer", required = true)
    protected TimePointIdentifierUsedContainer timePointIdentifierUsedContainer;
    @XmlElement(name = "PreAdministeredActivityUsed", required = true)
    protected AdministeredActivity preAdministeredActivityUsed;
    @XmlElement(name = "PostAdministeredActivityUsed", required = true)
    protected AdministeredActivity postAdministeredActivityUsed;
    @XmlElement(name = "PKAssessmentMethodUsed", required = true)
    protected CurveFittingMethod pkAssessmentMethodUsed;
    @XmlElement(name = "TimeIntegratedActivityCoefficientPerROIcontainer", required = true)
    protected TimeIntegratedActivityCoefficientPerROIcontainer timeIntegratedActivityCoefficientPerROIcontainer;
    @XmlElement(name = "TimeIntegratedActivityPerROIcontainer", required = true)
    protected TimeIntegratedActivityPerROIcontainer timeIntegratedActivityPerROIcontainer;

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
     *     {@link ROIIdentifierUsedContainer }
     *     
     */
    public ROIIdentifierUsedContainer getROIIdentifierUsed() {
        return roiIdentifierUsed;
    }

    /**
     * Définit la valeur de la propriété roiIdentifierUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link ROIIdentifierUsedContainer }
     *     
     */
    public void setROIIdentifierUsed(ROIIdentifierUsedContainer value) {
        this.roiIdentifierUsed = value;
    }

    /**
     * Obtient la valeur de la propriété timePointIdentifierUsedContainer.
     * 
     * @return
     *     possible object is
     *     {@link TimePointIdentifierUsedContainer }
     *     
     */
    public TimePointIdentifierUsedContainer getTimePointIdentifierUsedContainer() {
        return timePointIdentifierUsedContainer;
    }

    /**
     * Définit la valeur de la propriété timePointIdentifierUsedContainer.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePointIdentifierUsedContainer }
     *     
     */
    public void setTimePointIdentifierUsedContainer(TimePointIdentifierUsedContainer value) {
        this.timePointIdentifierUsedContainer = value;
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
     *     {@link CurveFittingMethod }
     *     
     */
    public CurveFittingMethod getPKAssessmentMethodUsed() {
        return pkAssessmentMethodUsed;
    }

    /**
     * Définit la valeur de la propriété pkAssessmentMethodUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link CurveFittingMethod }
     *     
     */
    public void setPKAssessmentMethodUsed(CurveFittingMethod value) {
        this.pkAssessmentMethodUsed = value;
    }

    /**
     * Obtient la valeur de la propriété timeIntegratedActivityCoefficientPerROIcontainer.
     * 
     * @return
     *     possible object is
     *     {@link TimeIntegratedActivityCoefficientPerROIcontainer }
     *     
     */
    public TimeIntegratedActivityCoefficientPerROIcontainer getTimeIntegratedActivityCoefficientPerROIcontainer() {
        return timeIntegratedActivityCoefficientPerROIcontainer;
    }

    /**
     * Définit la valeur de la propriété timeIntegratedActivityCoefficientPerROIcontainer.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeIntegratedActivityCoefficientPerROIcontainer }
     *     
     */
    public void setTimeIntegratedActivityCoefficientPerROIcontainer(TimeIntegratedActivityCoefficientPerROIcontainer value) {
        this.timeIntegratedActivityCoefficientPerROIcontainer = value;
    }

    /**
     * Obtient la valeur de la propriété timeIntegratedActivityPerROIcontainer.
     * 
     * @return
     *     possible object is
     *     {@link TimeIntegratedActivityPerROIcontainer }
     *     
     */
    public TimeIntegratedActivityPerROIcontainer getTimeIntegratedActivityPerROIcontainer() {
        return timeIntegratedActivityPerROIcontainer;
    }

    /**
     * Définit la valeur de la propriété timeIntegratedActivityPerROIcontainer.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeIntegratedActivityPerROIcontainer }
     *     
     */
    public void setTimeIntegratedActivityPerROIcontainer(TimeIntegratedActivityPerROIcontainer value) {
        this.timeIntegratedActivityPerROIcontainer = value;
    }

}
