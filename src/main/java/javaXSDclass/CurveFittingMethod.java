//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.07.30 à 02:06:37 PM CEST 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CurveFittingMethod complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CurveFittingMethod">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IncorporationFunction" type="{https://www.irdbb-medirad.com}IncorporationFunction"/>
 *         &lt;element name="IntegrationAlgorithm" type="{https://www.irdbb-medirad.com}IntegrationAlgorithm"/>
 *         &lt;element name="FittingFunction" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CurveFittingMethod", propOrder = {
    "incorporationFunction",
    "integrationAlgorithm",
    "fittingFunction"
})
public class CurveFittingMethod {

    @XmlElement(name = "IncorporationFunction", required = true)
    @XmlSchemaType(name = "string")
    protected IncorporationFunction incorporationFunction;
    @XmlElement(name = "IntegrationAlgorithm", required = true)
    @XmlSchemaType(name = "string")
    protected IntegrationAlgorithm integrationAlgorithm;
    @XmlElement(name = "FittingFunction", required = true)
    protected String fittingFunction;

    /**
     * Obtient la valeur de la propriété incorporationFunction.
     * 
     * @return
     *     possible object is
     *     {@link IncorporationFunction }
     *     
     */
    public IncorporationFunction getIncorporationFunction() {
        return incorporationFunction;
    }

    /**
     * Définit la valeur de la propriété incorporationFunction.
     * 
     * @param value
     *     allowed object is
     *     {@link IncorporationFunction }
     *     
     */
    public void setIncorporationFunction(IncorporationFunction value) {
        this.incorporationFunction = value;
    }

    /**
     * Obtient la valeur de la propriété integrationAlgorithm.
     * 
     * @return
     *     possible object is
     *     {@link IntegrationAlgorithm }
     *     
     */
    public IntegrationAlgorithm getIntegrationAlgorithm() {
        return integrationAlgorithm;
    }

    /**
     * Définit la valeur de la propriété integrationAlgorithm.
     * 
     * @param value
     *     allowed object is
     *     {@link IntegrationAlgorithm }
     *     
     */
    public void setIntegrationAlgorithm(IntegrationAlgorithm value) {
        this.integrationAlgorithm = value;
    }

    /**
     * Obtient la valeur de la propriété fittingFunction.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFittingFunction() {
        return fittingFunction;
    }

    /**
     * Définit la valeur de la propriété fittingFunction.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFittingFunction(String value) {
        this.fittingFunction = value;
    }

}
