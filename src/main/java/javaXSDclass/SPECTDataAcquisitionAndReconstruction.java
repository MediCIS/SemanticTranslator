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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour SPECTDataAcquisitionAndReconstruction complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SPECTDataAcquisitionAndReconstruction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SPECTAcqCTAcqAndReconstructionContainer" type="{https://www.irdbb-medirad.com}SPECTAcqCTAcqAndReconstructionContainer"/>
 *         &lt;element name="SPECTReconstructionContainer" type="{https://www.irdbb-medirad.com}SPECTReconstructionContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SPECTDataAcquisitionAndReconstruction", propOrder = {
    "spectAcqCTAcqAndReconstructionContainer",
    "spectReconstructionContainer"
})
public class SPECTDataAcquisitionAndReconstruction {

    @XmlElement(name = "SPECTAcqCTAcqAndReconstructionContainer", required = true)
    protected SPECTAcqCTAcqAndReconstructionContainer spectAcqCTAcqAndReconstructionContainer;
    @XmlElement(name = "SPECTReconstructionContainer")
    protected SPECTReconstructionContainer spectReconstructionContainer;

    /**
     * Obtient la valeur de la propriété spectAcqCTAcqAndReconstructionContainer.
     * 
     * @return
     *     possible object is
     *     {@link SPECTAcqCTAcqAndReconstructionContainer }
     *     
     */
    public SPECTAcqCTAcqAndReconstructionContainer getSPECTAcqCTAcqAndReconstructionContainer() {
        return spectAcqCTAcqAndReconstructionContainer;
    }

    /**
     * Définit la valeur de la propriété spectAcqCTAcqAndReconstructionContainer.
     * 
     * @param value
     *     allowed object is
     *     {@link SPECTAcqCTAcqAndReconstructionContainer }
     *     
     */
    public void setSPECTAcqCTAcqAndReconstructionContainer(SPECTAcqCTAcqAndReconstructionContainer value) {
        this.spectAcqCTAcqAndReconstructionContainer = value;
    }

    /**
     * Obtient la valeur de la propriété spectReconstructionContainer.
     * 
     * @return
     *     possible object is
     *     {@link SPECTReconstructionContainer }
     *     
     */
    public SPECTReconstructionContainer getSPECTReconstructionContainer() {
        return spectReconstructionContainer;
    }

    /**
     * Définit la valeur de la propriété spectReconstructionContainer.
     * 
     * @param value
     *     allowed object is
     *     {@link SPECTReconstructionContainer }
     *     
     */
    public void setSPECTReconstructionContainer(SPECTReconstructionContainer value) {
        this.spectReconstructionContainer = value;
    }

}
