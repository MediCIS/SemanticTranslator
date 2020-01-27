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
 * <p>Classe Java pour PlanarDataAcquisitionAndProcessing complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PlanarDataAcquisitionAndProcessing">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PlanarDataAcquisition" type="{https://www.irdbb-medirad.com}PlanarDataAcquisition"/>
 *         &lt;element name="PlanarDataCorrections" type="{https://www.irdbb-medirad.com}PlanarDataCorrections"/>
 *         &lt;element name="PlanarDataSegmentationWithoutRegistration" type="{https://www.irdbb-medirad.com}PlanarDataSegmentationWithoutRegistration" minOccurs="0"/>
 *         &lt;element name="PlanarDataSegmentationWithRegistrationAndPropagation" type="{https://www.irdbb-medirad.com}PlanarDataSegmentationWithRegistrationAndPropagation" minOccurs="0"/>
 *         &lt;element name="ROIPlanarActivityDetermination" type="{https://www.irdbb-medirad.com}ROIPlanarActivityDetermination" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlanarDataAcquisitionAndProcessing", propOrder = {
    "planarDataAcquisition",
    "planarDataCorrections",
    "planarDataSegmentationWithoutRegistration",
    "planarDataSegmentationWithRegistrationAndPropagation",
    "roiPlanarActivityDetermination"
})
public class PlanarDataAcquisitionAndProcessing {

    @XmlElement(name = "PlanarDataAcquisition", required = true)
    protected PlanarDataAcquisition planarDataAcquisition;
    @XmlElement(name = "PlanarDataCorrections", required = true)
    protected PlanarDataCorrections planarDataCorrections;
    @XmlElement(name = "PlanarDataSegmentationWithoutRegistration")
    protected PlanarDataSegmentationWithoutRegistration planarDataSegmentationWithoutRegistration;
    @XmlElement(name = "PlanarDataSegmentationWithRegistrationAndPropagation")
    protected PlanarDataSegmentationWithRegistrationAndPropagation planarDataSegmentationWithRegistrationAndPropagation;
    @XmlElement(name = "ROIPlanarActivityDetermination")
    protected ROIPlanarActivityDetermination roiPlanarActivityDetermination;

    /**
     * Obtient la valeur de la propriété planarDataAcquisition.
     * 
     * @return
     *     possible object is
     *     {@link PlanarDataAcquisition }
     *     
     */
    public PlanarDataAcquisition getPlanarDataAcquisition() {
        return planarDataAcquisition;
    }

    /**
     * Définit la valeur de la propriété planarDataAcquisition.
     * 
     * @param value
     *     allowed object is
     *     {@link PlanarDataAcquisition }
     *     
     */
    public void setPlanarDataAcquisition(PlanarDataAcquisition value) {
        this.planarDataAcquisition = value;
    }

    /**
     * Obtient la valeur de la propriété planarDataCorrections.
     * 
     * @return
     *     possible object is
     *     {@link PlanarDataCorrections }
     *     
     */
    public PlanarDataCorrections getPlanarDataCorrections() {
        return planarDataCorrections;
    }

    /**
     * Définit la valeur de la propriété planarDataCorrections.
     * 
     * @param value
     *     allowed object is
     *     {@link PlanarDataCorrections }
     *     
     */
    public void setPlanarDataCorrections(PlanarDataCorrections value) {
        this.planarDataCorrections = value;
    }

    /**
     * Obtient la valeur de la propriété planarDataSegmentationWithoutRegistration.
     * 
     * @return
     *     possible object is
     *     {@link PlanarDataSegmentationWithoutRegistration }
     *     
     */
    public PlanarDataSegmentationWithoutRegistration getPlanarDataSegmentationWithoutRegistration() {
        return planarDataSegmentationWithoutRegistration;
    }

    /**
     * Définit la valeur de la propriété planarDataSegmentationWithoutRegistration.
     * 
     * @param value
     *     allowed object is
     *     {@link PlanarDataSegmentationWithoutRegistration }
     *     
     */
    public void setPlanarDataSegmentationWithoutRegistration(PlanarDataSegmentationWithoutRegistration value) {
        this.planarDataSegmentationWithoutRegistration = value;
    }

    /**
     * Obtient la valeur de la propriété planarDataSegmentationWithRegistrationAndPropagation.
     * 
     * @return
     *     possible object is
     *     {@link PlanarDataSegmentationWithRegistrationAndPropagation }
     *     
     */
    public PlanarDataSegmentationWithRegistrationAndPropagation getPlanarDataSegmentationWithRegistrationAndPropagation() {
        return planarDataSegmentationWithRegistrationAndPropagation;
    }

    /**
     * Définit la valeur de la propriété planarDataSegmentationWithRegistrationAndPropagation.
     * 
     * @param value
     *     allowed object is
     *     {@link PlanarDataSegmentationWithRegistrationAndPropagation }
     *     
     */
    public void setPlanarDataSegmentationWithRegistrationAndPropagation(PlanarDataSegmentationWithRegistrationAndPropagation value) {
        this.planarDataSegmentationWithRegistrationAndPropagation = value;
    }

    /**
     * Obtient la valeur de la propriété roiPlanarActivityDetermination.
     * 
     * @return
     *     possible object is
     *     {@link ROIPlanarActivityDetermination }
     *     
     */
    public ROIPlanarActivityDetermination getROIPlanarActivityDetermination() {
        return roiPlanarActivityDetermination;
    }

    /**
     * Définit la valeur de la propriété roiPlanarActivityDetermination.
     * 
     * @param value
     *     allowed object is
     *     {@link ROIPlanarActivityDetermination }
     *     
     */
    public void setROIPlanarActivityDetermination(ROIPlanarActivityDetermination value) {
        this.roiPlanarActivityDetermination = value;
    }

}
