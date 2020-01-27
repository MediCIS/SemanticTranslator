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
 * <p>Classe Java pour SPECTDataAcquisitionAndProcessing complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SPECTDataAcquisitionAndProcessing">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SPECTAcqCTAcqAndReconstruction" type="{https://www.irdbb-medirad.com}SPECTAcqCTAcqAndReconstruction" minOccurs="0"/>
 *         &lt;element name="SPECTReconstruction" type="{https://www.irdbb-medirad.com}SPECTReconstruction" minOccurs="0"/>
 *         &lt;element name="RegistrationVOISegmentation" type="{https://www.irdbb-medirad.com}RegistrationVOISegmentation"/>
 *         &lt;element name="VOIActivityDetermination" type="{https://www.irdbb-medirad.com}VOIActivityDetermination"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SPECTDataAcquisitionAndProcessing", propOrder = {
    "spectAcqCTAcqAndReconstruction",
    "spectReconstruction",
    "registrationVOISegmentation",
    "voiActivityDetermination"
})
public class SPECTDataAcquisitionAndProcessing {

    @XmlElement(name = "SPECTAcqCTAcqAndReconstruction")
    protected SPECTAcqCTAcqAndReconstruction spectAcqCTAcqAndReconstruction;
    @XmlElement(name = "SPECTReconstruction")
    protected SPECTReconstruction spectReconstruction;
    @XmlElement(name = "RegistrationVOISegmentation", required = true)
    protected RegistrationVOISegmentation registrationVOISegmentation;
    @XmlElement(name = "VOIActivityDetermination", required = true)
    protected VOIActivityDetermination voiActivityDetermination;

    /**
     * Obtient la valeur de la propriété spectAcqCTAcqAndReconstruction.
     * 
     * @return
     *     possible object is
     *     {@link SPECTAcqCTAcqAndReconstruction }
     *     
     */
    public SPECTAcqCTAcqAndReconstruction getSPECTAcqCTAcqAndReconstruction() {
        return spectAcqCTAcqAndReconstruction;
    }

    /**
     * Définit la valeur de la propriété spectAcqCTAcqAndReconstruction.
     * 
     * @param value
     *     allowed object is
     *     {@link SPECTAcqCTAcqAndReconstruction }
     *     
     */
    public void setSPECTAcqCTAcqAndReconstruction(SPECTAcqCTAcqAndReconstruction value) {
        this.spectAcqCTAcqAndReconstruction = value;
    }

    /**
     * Obtient la valeur de la propriété spectReconstruction.
     * 
     * @return
     *     possible object is
     *     {@link SPECTReconstruction }
     *     
     */
    public SPECTReconstruction getSPECTReconstruction() {
        return spectReconstruction;
    }

    /**
     * Définit la valeur de la propriété spectReconstruction.
     * 
     * @param value
     *     allowed object is
     *     {@link SPECTReconstruction }
     *     
     */
    public void setSPECTReconstruction(SPECTReconstruction value) {
        this.spectReconstruction = value;
    }

    /**
     * Obtient la valeur de la propriété registrationVOISegmentation.
     * 
     * @return
     *     possible object is
     *     {@link RegistrationVOISegmentation }
     *     
     */
    public RegistrationVOISegmentation getRegistrationVOISegmentation() {
        return registrationVOISegmentation;
    }

    /**
     * Définit la valeur de la propriété registrationVOISegmentation.
     * 
     * @param value
     *     allowed object is
     *     {@link RegistrationVOISegmentation }
     *     
     */
    public void setRegistrationVOISegmentation(RegistrationVOISegmentation value) {
        this.registrationVOISegmentation = value;
    }

    /**
     * Obtient la valeur de la propriété voiActivityDetermination.
     * 
     * @return
     *     possible object is
     *     {@link VOIActivityDetermination }
     *     
     */
    public VOIActivityDetermination getVOIActivityDetermination() {
        return voiActivityDetermination;
    }

    /**
     * Définit la valeur de la propriété voiActivityDetermination.
     * 
     * @param value
     *     allowed object is
     *     {@link VOIActivityDetermination }
     *     
     */
    public void setVOIActivityDetermination(VOIActivityDetermination value) {
        this.voiActivityDetermination = value;
    }

}
