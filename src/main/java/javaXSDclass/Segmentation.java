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
 * <p>Classe Java pour Segmentation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Segmentation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentationIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VOIContainer" type="{https://www.irdbb-medirad.com}VoiProducedContainer"/>
 *         &lt;element name="DICOMDataContainer" type="{https://www.irdbb-medirad.com}DICOMDataContainer" minOccurs="0"/>
 *         &lt;element name="NonDICOMDataContainer" type="{https://www.irdbb-medirad.com}NonDICOMDataContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Segmentation", propOrder = {
    "segmentationIdentifier",
    "voiContainer",
    "dicomDataContainer",
    "nonDICOMDataContainer"
})
public class Segmentation {

    @XmlElement(name = "SegmentationIdentifier", required = true)
    protected String segmentationIdentifier;
    @XmlElement(name = "VOIContainer", required = true)
    protected VoiProducedContainer voiContainer;
    @XmlElement(name = "DICOMDataContainer")
    protected DICOMDataContainer dicomDataContainer;
    @XmlElement(name = "NonDICOMDataContainer")
    protected NonDICOMDataContainer nonDICOMDataContainer;

    /**
     * Obtient la valeur de la propriété segmentationIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegmentationIdentifier() {
        return segmentationIdentifier;
    }

    /**
     * Définit la valeur de la propriété segmentationIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegmentationIdentifier(String value) {
        this.segmentationIdentifier = value;
    }

    /**
     * Obtient la valeur de la propriété voiContainer.
     * 
     * @return
     *     possible object is
     *     {@link VoiProducedContainer }
     *     
     */
    public VoiProducedContainer getVOIContainer() {
        return voiContainer;
    }

    /**
     * Définit la valeur de la propriété voiContainer.
     * 
     * @param value
     *     allowed object is
     *     {@link VoiProducedContainer }
     *     
     */
    public void setVOIContainer(VoiProducedContainer value) {
        this.voiContainer = value;
    }

    /**
     * Obtient la valeur de la propriété dicomDataContainer.
     * 
     * @return
     *     possible object is
     *     {@link DICOMDataContainer }
     *     
     */
    public DICOMDataContainer getDICOMDataContainer() {
        return dicomDataContainer;
    }

    /**
     * Définit la valeur de la propriété dicomDataContainer.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMDataContainer }
     *     
     */
    public void setDICOMDataContainer(DICOMDataContainer value) {
        this.dicomDataContainer = value;
    }

    /**
     * Obtient la valeur de la propriété nonDICOMDataContainer.
     * 
     * @return
     *     possible object is
     *     {@link NonDICOMDataContainer }
     *     
     */
    public NonDICOMDataContainer getNonDICOMDataContainer() {
        return nonDICOMDataContainer;
    }

    /**
     * Définit la valeur de la propriété nonDICOMDataContainer.
     * 
     * @param value
     *     allowed object is
     *     {@link NonDICOMDataContainer }
     *     
     */
    public void setNonDICOMDataContainer(NonDICOMDataContainer value) {
        this.nonDICOMDataContainer = value;
    }

}
