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
 * <p>Classe Java pour DoseRateCurveFitVOITimeIntegration complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DoseRateCurveFitVOITimeIntegration">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="VOIIdentifierUsed" type="{https://www.irdbb-medirad.com}VOIIdentifierContainer"/>
 *         &lt;element name="TimePointIdentifierUsed" type="{https://www.irdbb-medirad.com}TimePointIdentifierUsedContainer"/>
 *         &lt;element name="PKAssessmentMethodUsed" type="{https://www.irdbb-medirad.com}CurveFittingMethod"/>
 *         &lt;element name="PreAdministeredActivityUsed" type="{https://www.irdbb-medirad.com}AdministeredActivity"/>
 *         &lt;element name="PostAdministeredActivityUsed" type="{https://www.irdbb-medirad.com}AdministeredActivity"/>
 *         &lt;element name="AbsorbedDoseInVOIProduced" type="{https://www.irdbb-medirad.com}AbsorbedDoseInVOIContainer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DoseRateCurveFitVOITimeIntegration", propOrder = {
    "processExecutionContext",
    "voiIdentifierUsed",
    "timePointIdentifierUsed",
    "pkAssessmentMethodUsed",
    "preAdministeredActivityUsed",
    "postAdministeredActivityUsed",
    "absorbedDoseInVOIProduced"
})
public class DoseRateCurveFitVOITimeIntegration {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "VOIIdentifierUsed", required = true)
    protected VOIIdentifierContainer voiIdentifierUsed;
    @XmlElement(name = "TimePointIdentifierUsed", required = true)
    protected TimePointIdentifierUsedContainer timePointIdentifierUsed;
    @XmlElement(name = "PKAssessmentMethodUsed", required = true)
    protected CurveFittingMethod pkAssessmentMethodUsed;
    @XmlElement(name = "PreAdministeredActivityUsed", required = true)
    protected AdministeredActivity preAdministeredActivityUsed;
    @XmlElement(name = "PostAdministeredActivityUsed", required = true)
    protected AdministeredActivity postAdministeredActivityUsed;
    @XmlElement(name = "AbsorbedDoseInVOIProduced", required = true)
    protected AbsorbedDoseInVOIContainer absorbedDoseInVOIProduced;

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
     * Obtient la valeur de la propriété timePointIdentifierUsed.
     * 
     * @return
     *     possible object is
     *     {@link TimePointIdentifierUsedContainer }
     *     
     */
    public TimePointIdentifierUsedContainer getTimePointIdentifierUsed() {
        return timePointIdentifierUsed;
    }

    /**
     * Définit la valeur de la propriété timePointIdentifierUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePointIdentifierUsedContainer }
     *     
     */
    public void setTimePointIdentifierUsed(TimePointIdentifierUsedContainer value) {
        this.timePointIdentifierUsed = value;
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
     * Obtient la valeur de la propriété absorbedDoseInVOIProduced.
     * 
     * @return
     *     possible object is
     *     {@link AbsorbedDoseInVOIContainer }
     *     
     */
    public AbsorbedDoseInVOIContainer getAbsorbedDoseInVOIProduced() {
        return absorbedDoseInVOIProduced;
    }

    /**
     * Définit la valeur de la propriété absorbedDoseInVOIProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link AbsorbedDoseInVOIContainer }
     *     
     */
    public void setAbsorbedDoseInVOIProduced(AbsorbedDoseInVOIContainer value) {
        this.absorbedDoseInVOIProduced = value;
    }

}
