//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.01.28 à 04:59:07 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour PlanarCalibrationWorkflow complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PlanarCalibrationWorkflow">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PlanarAcquisition" type="{https://www.irdbb-medirad.com}PlanarAcquisition"/>
 *         &lt;element name="PlanarImageCorrections" type="{https://www.irdbb-medirad.com}PlanarImageCorrections"/>
 *         &lt;element name="PlanarImageSegmentation" type="{https://www.irdbb-medirad.com}PlanarImageSegmentation"/>
 *         &lt;element name="PlanarSensitivityCalculation" type="{https://www.irdbb-medirad.com}PlanarSensitivityCalculation"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlanarCalibrationWorkflow", propOrder = {
    "planarAcquisition",
    "planarImageCorrections",
    "planarImageSegmentation",
    "planarSensitivityCalculation"
})
public class PlanarCalibrationWorkflow {

    @XmlElement(name = "PlanarAcquisition", required = true)
    protected PlanarAcquisition planarAcquisition;
    @XmlElement(name = "PlanarImageCorrections", required = true)
    protected PlanarImageCorrections planarImageCorrections;
    @XmlElement(name = "PlanarImageSegmentation", required = true)
    protected PlanarImageSegmentation planarImageSegmentation;
    @XmlElement(name = "PlanarSensitivityCalculation", required = true)
    protected PlanarSensitivityCalculation planarSensitivityCalculation;

    /**
     * Obtient la valeur de la propriété planarAcquisition.
     * 
     * @return
     *     possible object is
     *     {@link PlanarAcquisition }
     *     
     */
    public PlanarAcquisition getPlanarAcquisition() {
        return planarAcquisition;
    }

    /**
     * Définit la valeur de la propriété planarAcquisition.
     * 
     * @param value
     *     allowed object is
     *     {@link PlanarAcquisition }
     *     
     */
    public void setPlanarAcquisition(PlanarAcquisition value) {
        this.planarAcquisition = value;
    }

    /**
     * Obtient la valeur de la propriété planarImageCorrections.
     * 
     * @return
     *     possible object is
     *     {@link PlanarImageCorrections }
     *     
     */
    public PlanarImageCorrections getPlanarImageCorrections() {
        return planarImageCorrections;
    }

    /**
     * Définit la valeur de la propriété planarImageCorrections.
     * 
     * @param value
     *     allowed object is
     *     {@link PlanarImageCorrections }
     *     
     */
    public void setPlanarImageCorrections(PlanarImageCorrections value) {
        this.planarImageCorrections = value;
    }

    /**
     * Obtient la valeur de la propriété planarImageSegmentation.
     * 
     * @return
     *     possible object is
     *     {@link PlanarImageSegmentation }
     *     
     */
    public PlanarImageSegmentation getPlanarImageSegmentation() {
        return planarImageSegmentation;
    }

    /**
     * Définit la valeur de la propriété planarImageSegmentation.
     * 
     * @param value
     *     allowed object is
     *     {@link PlanarImageSegmentation }
     *     
     */
    public void setPlanarImageSegmentation(PlanarImageSegmentation value) {
        this.planarImageSegmentation = value;
    }

    /**
     * Obtient la valeur de la propriété planarSensitivityCalculation.
     * 
     * @return
     *     possible object is
     *     {@link PlanarSensitivityCalculation }
     *     
     */
    public PlanarSensitivityCalculation getPlanarSensitivityCalculation() {
        return planarSensitivityCalculation;
    }

    /**
     * Définit la valeur de la propriété planarSensitivityCalculation.
     * 
     * @param value
     *     allowed object is
     *     {@link PlanarSensitivityCalculation }
     *     
     */
    public void setPlanarSensitivityCalculation(PlanarSensitivityCalculation value) {
        this.planarSensitivityCalculation = value;
    }

}
