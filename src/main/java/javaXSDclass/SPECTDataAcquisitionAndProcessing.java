//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2018.12.11 à 04:47:30 PM CET 
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
 *         &lt;element name="SPECTAcqCTAcqAndReconstruction" type="{https://www.irdbb-medirad.com}SPECTAcqCTAcqAndReconstruction"/>
 *         &lt;element name="SPECTReconstruction" type="{https://www.irdbb-medirad.com}SPECTReconstruction"/>
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
    "spectReconstruction"
})
public class SPECTDataAcquisitionAndProcessing {

    @XmlElement(name = "SPECTAcqCTAcqAndReconstruction", required = true)
    protected SPECTAcqCTAcqAndReconstruction spectAcqCTAcqAndReconstruction;
    @XmlElement(name = "SPECTReconstruction", required = true)
    protected SPECTReconstruction spectReconstruction;

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

}
