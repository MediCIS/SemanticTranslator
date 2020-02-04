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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TimeIntegratedActivityPerVOI complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TimeIntegratedActivityPerVOI">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResidenceTimePerVOIValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="TimeUnit" type="{https://www.irdbb-medirad.com}TimeUnit"/>
 *         &lt;element name="TimeIntegratedActivityPerVOIValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="TimeIntegratedActivityPerVOIUnit" type="{https://www.irdbb-medirad.com}TimeIntegratedActivityPerVOIUnit"/>
 *         &lt;element name="VOIIdentifierList" type="{https://www.irdbb-medirad.com}VOIIdentifierContainer"/>
 *         &lt;element name="PKAssessmentMethodUsed" type="{https://www.irdbb-medirad.com}CurveFittingMethod"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeIntegratedActivityPerVOI", propOrder = {
    "residenceTimePerVOIValue",
    "timeUnit",
    "timeIntegratedActivityPerVOIValue",
    "timeIntegratedActivityPerVOIUnit",
    "voiIdentifierList",
    "pkAssessmentMethodUsed"
})
public class TimeIntegratedActivityPerVOI {

    @XmlElement(name = "ResidenceTimePerVOIValue")
    protected float residenceTimePerVOIValue;
    @XmlElement(name = "TimeUnit", required = true)
    @XmlSchemaType(name = "string")
    protected TimeUnit timeUnit;
    @XmlElement(name = "TimeIntegratedActivityPerVOIValue")
    protected float timeIntegratedActivityPerVOIValue;
    @XmlElement(name = "TimeIntegratedActivityPerVOIUnit", required = true)
    @XmlSchemaType(name = "string")
    protected TimeIntegratedActivityPerVOIUnit timeIntegratedActivityPerVOIUnit;
    @XmlElement(name = "VOIIdentifierList", required = true)
    protected VOIIdentifierContainer voiIdentifierList;
    @XmlElement(name = "PKAssessmentMethodUsed", required = true)
    protected CurveFittingMethod pkAssessmentMethodUsed;

    /**
     * Obtient la valeur de la propriété residenceTimePerVOIValue.
     * 
     */
    public float getResidenceTimePerVOIValue() {
        return residenceTimePerVOIValue;
    }

    /**
     * Définit la valeur de la propriété residenceTimePerVOIValue.
     * 
     */
    public void setResidenceTimePerVOIValue(float value) {
        this.residenceTimePerVOIValue = value;
    }

    /**
     * Obtient la valeur de la propriété timeUnit.
     * 
     * @return
     *     possible object is
     *     {@link TimeUnit }
     *     
     */
    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    /**
     * Définit la valeur de la propriété timeUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeUnit }
     *     
     */
    public void setTimeUnit(TimeUnit value) {
        this.timeUnit = value;
    }

    /**
     * Obtient la valeur de la propriété timeIntegratedActivityPerVOIValue.
     * 
     */
    public float getTimeIntegratedActivityPerVOIValue() {
        return timeIntegratedActivityPerVOIValue;
    }

    /**
     * Définit la valeur de la propriété timeIntegratedActivityPerVOIValue.
     * 
     */
    public void setTimeIntegratedActivityPerVOIValue(float value) {
        this.timeIntegratedActivityPerVOIValue = value;
    }

    /**
     * Obtient la valeur de la propriété timeIntegratedActivityPerVOIUnit.
     * 
     * @return
     *     possible object is
     *     {@link TimeIntegratedActivityPerVOIUnit }
     *     
     */
    public TimeIntegratedActivityPerVOIUnit getTimeIntegratedActivityPerVOIUnit() {
        return timeIntegratedActivityPerVOIUnit;
    }

    /**
     * Définit la valeur de la propriété timeIntegratedActivityPerVOIUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeIntegratedActivityPerVOIUnit }
     *     
     */
    public void setTimeIntegratedActivityPerVOIUnit(TimeIntegratedActivityPerVOIUnit value) {
        this.timeIntegratedActivityPerVOIUnit = value;
    }

    /**
     * Obtient la valeur de la propriété voiIdentifierList.
     * 
     * @return
     *     possible object is
     *     {@link VOIIdentifierContainer }
     *     
     */
    public VOIIdentifierContainer getVOIIdentifierList() {
        return voiIdentifierList;
    }

    /**
     * Définit la valeur de la propriété voiIdentifierList.
     * 
     * @param value
     *     allowed object is
     *     {@link VOIIdentifierContainer }
     *     
     */
    public void setVOIIdentifierList(VOIIdentifierContainer value) {
        this.voiIdentifierList = value;
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

}
