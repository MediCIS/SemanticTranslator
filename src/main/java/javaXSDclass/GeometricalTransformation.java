//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.04 à 12:01:52 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour GeometricalTransformation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="GeometricalTransformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GeometricalTransformationValue" type="{https://www.irdbb-medirad.com}NonDICOMDataContainer"/>
 *         &lt;element name="TransformationType" type="{https://www.irdbb-medirad.com}TransformationType"/>
 *         &lt;element name="TransformationIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DICOMCTSourceCoordinateSpaceUsed" type="{https://www.irdbb-medirad.com}DICOMDataContainer" minOccurs="0"/>
 *         &lt;element name="DICOMCTDestinationCoordinateSpaceUsed" type="{https://www.irdbb-medirad.com}DICOMDataContainer" minOccurs="0"/>
 *         &lt;element name="DICOMSPECTSourceCoordinateSpaceUsed" type="{https://www.irdbb-medirad.com}DICOMDataContainer" minOccurs="0"/>
 *         &lt;element name="DICOMSPECTDestinationCoordinateSpaceUsed" type="{https://www.irdbb-medirad.com}DICOMDataContainer" minOccurs="0"/>
 *         &lt;element name="NonDICOMSourceCoordinateSpaceUsed" type="{https://www.irdbb-medirad.com}NonDICOMDataContainer" minOccurs="0"/>
 *         &lt;element name="NonDICOMDestinationCoordinateSpaceUsed" type="{https://www.irdbb-medirad.com}NonDICOMDataContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeometricalTransformation", propOrder = {
    "geometricalTransformationValue",
    "transformationType",
    "transformationIdentifier",
    "dicomctSourceCoordinateSpaceUsed",
    "dicomctDestinationCoordinateSpaceUsed",
    "dicomspectSourceCoordinateSpaceUsed",
    "dicomspectDestinationCoordinateSpaceUsed",
    "nonDICOMSourceCoordinateSpaceUsed",
    "nonDICOMDestinationCoordinateSpaceUsed"
})
public class GeometricalTransformation {

    @XmlElement(name = "GeometricalTransformationValue", required = true)
    protected NonDICOMDataContainer geometricalTransformationValue;
    @XmlElement(name = "TransformationType", required = true)
    @XmlSchemaType(name = "string")
    protected TransformationType transformationType;
    @XmlElement(name = "TransformationIdentifier", required = true)
    protected String transformationIdentifier;
    @XmlElement(name = "DICOMCTSourceCoordinateSpaceUsed")
    protected DICOMDataContainer dicomctSourceCoordinateSpaceUsed;
    @XmlElement(name = "DICOMCTDestinationCoordinateSpaceUsed")
    protected DICOMDataContainer dicomctDestinationCoordinateSpaceUsed;
    @XmlElement(name = "DICOMSPECTSourceCoordinateSpaceUsed")
    protected DICOMDataContainer dicomspectSourceCoordinateSpaceUsed;
    @XmlElement(name = "DICOMSPECTDestinationCoordinateSpaceUsed")
    protected DICOMDataContainer dicomspectDestinationCoordinateSpaceUsed;
    @XmlElement(name = "NonDICOMSourceCoordinateSpaceUsed")
    protected NonDICOMDataContainer nonDICOMSourceCoordinateSpaceUsed;
    @XmlElement(name = "NonDICOMDestinationCoordinateSpaceUsed")
    protected NonDICOMDataContainer nonDICOMDestinationCoordinateSpaceUsed;

    /**
     * Obtient la valeur de la propriété geometricalTransformationValue.
     * 
     * @return
     *     possible object is
     *     {@link NonDICOMDataContainer }
     *     
     */
    public NonDICOMDataContainer getGeometricalTransformationValue() {
        return geometricalTransformationValue;
    }

    /**
     * Définit la valeur de la propriété geometricalTransformationValue.
     * 
     * @param value
     *     allowed object is
     *     {@link NonDICOMDataContainer }
     *     
     */
    public void setGeometricalTransformationValue(NonDICOMDataContainer value) {
        this.geometricalTransformationValue = value;
    }

    /**
     * Obtient la valeur de la propriété transformationType.
     * 
     * @return
     *     possible object is
     *     {@link TransformationType }
     *     
     */
    public TransformationType getTransformationType() {
        return transformationType;
    }

    /**
     * Définit la valeur de la propriété transformationType.
     * 
     * @param value
     *     allowed object is
     *     {@link TransformationType }
     *     
     */
    public void setTransformationType(TransformationType value) {
        this.transformationType = value;
    }

    /**
     * Obtient la valeur de la propriété transformationIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransformationIdentifier() {
        return transformationIdentifier;
    }

    /**
     * Définit la valeur de la propriété transformationIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransformationIdentifier(String value) {
        this.transformationIdentifier = value;
    }

    /**
     * Obtient la valeur de la propriété dicomctSourceCoordinateSpaceUsed.
     * 
     * @return
     *     possible object is
     *     {@link DICOMDataContainer }
     *     
     */
    public DICOMDataContainer getDICOMCTSourceCoordinateSpaceUsed() {
        return dicomctSourceCoordinateSpaceUsed;
    }

    /**
     * Définit la valeur de la propriété dicomctSourceCoordinateSpaceUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMDataContainer }
     *     
     */
    public void setDICOMCTSourceCoordinateSpaceUsed(DICOMDataContainer value) {
        this.dicomctSourceCoordinateSpaceUsed = value;
    }

    /**
     * Obtient la valeur de la propriété dicomctDestinationCoordinateSpaceUsed.
     * 
     * @return
     *     possible object is
     *     {@link DICOMDataContainer }
     *     
     */
    public DICOMDataContainer getDICOMCTDestinationCoordinateSpaceUsed() {
        return dicomctDestinationCoordinateSpaceUsed;
    }

    /**
     * Définit la valeur de la propriété dicomctDestinationCoordinateSpaceUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMDataContainer }
     *     
     */
    public void setDICOMCTDestinationCoordinateSpaceUsed(DICOMDataContainer value) {
        this.dicomctDestinationCoordinateSpaceUsed = value;
    }

    /**
     * Obtient la valeur de la propriété dicomspectSourceCoordinateSpaceUsed.
     * 
     * @return
     *     possible object is
     *     {@link DICOMDataContainer }
     *     
     */
    public DICOMDataContainer getDICOMSPECTSourceCoordinateSpaceUsed() {
        return dicomspectSourceCoordinateSpaceUsed;
    }

    /**
     * Définit la valeur de la propriété dicomspectSourceCoordinateSpaceUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMDataContainer }
     *     
     */
    public void setDICOMSPECTSourceCoordinateSpaceUsed(DICOMDataContainer value) {
        this.dicomspectSourceCoordinateSpaceUsed = value;
    }

    /**
     * Obtient la valeur de la propriété dicomspectDestinationCoordinateSpaceUsed.
     * 
     * @return
     *     possible object is
     *     {@link DICOMDataContainer }
     *     
     */
    public DICOMDataContainer getDICOMSPECTDestinationCoordinateSpaceUsed() {
        return dicomspectDestinationCoordinateSpaceUsed;
    }

    /**
     * Définit la valeur de la propriété dicomspectDestinationCoordinateSpaceUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMDataContainer }
     *     
     */
    public void setDICOMSPECTDestinationCoordinateSpaceUsed(DICOMDataContainer value) {
        this.dicomspectDestinationCoordinateSpaceUsed = value;
    }

    /**
     * Obtient la valeur de la propriété nonDICOMSourceCoordinateSpaceUsed.
     * 
     * @return
     *     possible object is
     *     {@link NonDICOMDataContainer }
     *     
     */
    public NonDICOMDataContainer getNonDICOMSourceCoordinateSpaceUsed() {
        return nonDICOMSourceCoordinateSpaceUsed;
    }

    /**
     * Définit la valeur de la propriété nonDICOMSourceCoordinateSpaceUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link NonDICOMDataContainer }
     *     
     */
    public void setNonDICOMSourceCoordinateSpaceUsed(NonDICOMDataContainer value) {
        this.nonDICOMSourceCoordinateSpaceUsed = value;
    }

    /**
     * Obtient la valeur de la propriété nonDICOMDestinationCoordinateSpaceUsed.
     * 
     * @return
     *     possible object is
     *     {@link NonDICOMDataContainer }
     *     
     */
    public NonDICOMDataContainer getNonDICOMDestinationCoordinateSpaceUsed() {
        return nonDICOMDestinationCoordinateSpaceUsed;
    }

    /**
     * Définit la valeur de la propriété nonDICOMDestinationCoordinateSpaceUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link NonDICOMDataContainer }
     *     
     */
    public void setNonDICOMDestinationCoordinateSpaceUsed(NonDICOMDataContainer value) {
        this.nonDICOMDestinationCoordinateSpaceUsed = value;
    }

}
