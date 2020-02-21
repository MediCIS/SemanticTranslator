//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.12 à 04:30:42 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour RadioBiologicalCalculationInHybridOr3DSlide2Dosimetry complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="RadioBiologicalCalculationInHybridOr3DSlide2Dosimetry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="VOIIdentifierUsed" type="{https://www.irdbb-medirad.com}VOIIdentifierContainer"/>
 *         &lt;element name="RadioBiologicalCalculationMethod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RadioBiologicalCalculationParameters" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BiologicalEffectiveDose" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RadioBiologicalCalculationInHybridOr3DSlide2Dosimetry", propOrder = {
    "processExecutionContext",
    "voiIdentifierUsed",
    "radioBiologicalCalculationMethod",
    "radioBiologicalCalculationParameters",
    "biologicalEffectiveDose"
})
public class RadioBiologicalCalculationInHybridOr3DSlide2Dosimetry {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "VOIIdentifierUsed", required = true)
    protected VOIIdentifierContainer voiIdentifierUsed;
    @XmlElement(name = "RadioBiologicalCalculationMethod", required = true)
    protected String radioBiologicalCalculationMethod;
    @XmlElement(name = "RadioBiologicalCalculationParameters", required = true)
    protected String radioBiologicalCalculationParameters;
    @XmlElement(name = "BiologicalEffectiveDose", required = true)
    protected String biologicalEffectiveDose;

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
     * Obtient la valeur de la propriété radioBiologicalCalculationMethod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRadioBiologicalCalculationMethod() {
        return radioBiologicalCalculationMethod;
    }

    /**
     * Définit la valeur de la propriété radioBiologicalCalculationMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRadioBiologicalCalculationMethod(String value) {
        this.radioBiologicalCalculationMethod = value;
    }

    /**
     * Obtient la valeur de la propriété radioBiologicalCalculationParameters.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRadioBiologicalCalculationParameters() {
        return radioBiologicalCalculationParameters;
    }

    /**
     * Définit la valeur de la propriété radioBiologicalCalculationParameters.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRadioBiologicalCalculationParameters(String value) {
        this.radioBiologicalCalculationParameters = value;
    }

    /**
     * Obtient la valeur de la propriété biologicalEffectiveDose.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBiologicalEffectiveDose() {
        return biologicalEffectiveDose;
    }

    /**
     * Définit la valeur de la propriété biologicalEffectiveDose.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBiologicalEffectiveDose(String value) {
        this.biologicalEffectiveDose = value;
    }

}
