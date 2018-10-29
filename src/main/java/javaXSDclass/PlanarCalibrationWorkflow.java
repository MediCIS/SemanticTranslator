//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2018.10.16 à 03:58:01 PM CEST 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour planarCalibrationWorkflow complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="planarCalibrationWorkflow">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="planarAcquisition" type="{https://www.irdbb-medirad.com}complexUnknown"/>
 *         &lt;element name="planarImageCorrections" type="{https://www.irdbb-medirad.com}complexUnknown"/>
 *         &lt;element name="planarImageSegmentation" type="{https://www.irdbb-medirad.com}complexUnknown"/>
 *         &lt;element name="planarSensitivityCalculation" type="{https://www.irdbb-medirad.com}complexUnknown"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "planarCalibrationWorkflow", propOrder = {
    "planarAcquisition",
    "planarImageCorrections",
    "planarImageSegmentation",
    "planarSensitivityCalculation"
})
public class PlanarCalibrationWorkflow {

    @XmlElement(required = true)
    protected ComplexUnknown planarAcquisition;
    @XmlElement(required = true)
    protected ComplexUnknown planarImageCorrections;
    @XmlElement(required = true)
    protected ComplexUnknown planarImageSegmentation;
    @XmlElement(required = true)
    protected ComplexUnknown planarSensitivityCalculation;

    /**
     * Obtient la valeur de la propriété planarAcquisition.
     * 
     * @return
     *     possible object is
     *     {@link ComplexUnknown }
     *     
     */
    public ComplexUnknown getPlanarAcquisition() {
        return planarAcquisition;
    }

    /**
     * Définit la valeur de la propriété planarAcquisition.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexUnknown }
     *     
     */
    public void setPlanarAcquisition(ComplexUnknown value) {
        this.planarAcquisition = value;
    }

    /**
     * Obtient la valeur de la propriété planarImageCorrections.
     * 
     * @return
     *     possible object is
     *     {@link ComplexUnknown }
     *     
     */
    public ComplexUnknown getPlanarImageCorrections() {
        return planarImageCorrections;
    }

    /**
     * Définit la valeur de la propriété planarImageCorrections.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexUnknown }
     *     
     */
    public void setPlanarImageCorrections(ComplexUnknown value) {
        this.planarImageCorrections = value;
    }

    /**
     * Obtient la valeur de la propriété planarImageSegmentation.
     * 
     * @return
     *     possible object is
     *     {@link ComplexUnknown }
     *     
     */
    public ComplexUnknown getPlanarImageSegmentation() {
        return planarImageSegmentation;
    }

    /**
     * Définit la valeur de la propriété planarImageSegmentation.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexUnknown }
     *     
     */
    public void setPlanarImageSegmentation(ComplexUnknown value) {
        this.planarImageSegmentation = value;
    }

    /**
     * Obtient la valeur de la propriété planarSensitivityCalculation.
     * 
     * @return
     *     possible object is
     *     {@link ComplexUnknown }
     *     
     */
    public ComplexUnknown getPlanarSensitivityCalculation() {
        return planarSensitivityCalculation;
    }

    /**
     * Définit la valeur de la propriété planarSensitivityCalculation.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexUnknown }
     *     
     */
    public void setPlanarSensitivityCalculation(ComplexUnknown value) {
        this.planarSensitivityCalculation = value;
    }

}
