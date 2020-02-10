//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.06 à 05:03:32 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CalibrationWorkflow complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CalibrationWorkflow">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SPECTCTCalibrationWorkflow" type="{https://www.irdbb-medirad.com}SPECTCTCalibrationWorkflow"/>
 *         &lt;element name="PlanarCalibrationWorkflow" type="{https://www.irdbb-medirad.com}PlanarCalibrationWorkflow"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalibrationWorkflow", propOrder = {
    "spectctCalibrationWorkflow",
    "planarCalibrationWorkflow"
})
public class CalibrationWorkflow {

    @XmlElement(name = "SPECTCTCalibrationWorkflow", required = true)
    protected SPECTCTCalibrationWorkflow spectctCalibrationWorkflow;
    @XmlElement(name = "PlanarCalibrationWorkflow", required = true)
    protected PlanarCalibrationWorkflow planarCalibrationWorkflow;

    /**
     * Obtient la valeur de la propriété spectctCalibrationWorkflow.
     * 
     * @return
     *     possible object is
     *     {@link SPECTCTCalibrationWorkflow }
     *     
     */
    public SPECTCTCalibrationWorkflow getSPECTCTCalibrationWorkflow() {
        return spectctCalibrationWorkflow;
    }

    /**
     * Définit la valeur de la propriété spectctCalibrationWorkflow.
     * 
     * @param value
     *     allowed object is
     *     {@link SPECTCTCalibrationWorkflow }
     *     
     */
    public void setSPECTCTCalibrationWorkflow(SPECTCTCalibrationWorkflow value) {
        this.spectctCalibrationWorkflow = value;
    }

    /**
     * Obtient la valeur de la propriété planarCalibrationWorkflow.
     * 
     * @return
     *     possible object is
     *     {@link PlanarCalibrationWorkflow }
     *     
     */
    public PlanarCalibrationWorkflow getPlanarCalibrationWorkflow() {
        return planarCalibrationWorkflow;
    }

    /**
     * Définit la valeur de la propriété planarCalibrationWorkflow.
     * 
     * @param value
     *     allowed object is
     *     {@link PlanarCalibrationWorkflow }
     *     
     */
    public void setPlanarCalibrationWorkflow(PlanarCalibrationWorkflow value) {
        this.planarCalibrationWorkflow = value;
    }

}
