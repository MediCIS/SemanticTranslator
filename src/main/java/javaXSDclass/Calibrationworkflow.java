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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SPECTCTcalibrationWorkflow" type="{https://www.irdbb-medirad.com}SPECTCTcalibrationworkflow" minOccurs="0"/>
 *         &lt;element name="planarCalibrationWorkflow" type="{https://www.irdbb-medirad.com}planarCalibrationWorkflow" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "spectcTcalibrationWorkflow",
    "planarCalibrationWorkflow"
})
@XmlRootElement(name = "calibrationworkflow")
public class Calibrationworkflow {

    @XmlElement(name = "SPECTCTcalibrationWorkflow")
    protected SPECTCTcalibrationworkflow spectcTcalibrationWorkflow;
    protected PlanarCalibrationWorkflow planarCalibrationWorkflow;

    /**
     * Obtient la valeur de la propriété spectcTcalibrationWorkflow.
     * 
     * @return
     *     possible object is
     *     {@link SPECTCTcalibrationworkflow }
     *     
     */
    public SPECTCTcalibrationworkflow getSPECTCTcalibrationWorkflow() {
        return spectcTcalibrationWorkflow;
    }

    /**
     * Définit la valeur de la propriété spectcTcalibrationWorkflow.
     * 
     * @param value
     *     allowed object is
     *     {@link SPECTCTcalibrationworkflow }
     *     
     */
    public void setSPECTCTcalibrationWorkflow(SPECTCTcalibrationworkflow value) {
        this.spectcTcalibrationWorkflow = value;
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
