//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.12 à 04:30:41 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour StructuredReportDescriptorType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="StructuredReportDescriptorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ContentTemplateSequence-0040a504" type="{https://www.irdbb-medirad.com}DICOMCodeSequenceType" minOccurs="0"/>
 *         &lt;element name="ConceptNameCodeSequence-0040a043" type="{https://www.irdbb-medirad.com}DICOMCodeSequenceType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StructuredReportDescriptorType", propOrder = {
    "contentTemplateSequence0040A504",
    "conceptNameCodeSequence0040A043"
})
public class StructuredReportDescriptorType {

    @XmlElement(name = "ContentTemplateSequence-0040a504")
    protected DICOMCodeSequenceType contentTemplateSequence0040A504;
    @XmlElement(name = "ConceptNameCodeSequence-0040a043")
    protected DICOMCodeSequenceType conceptNameCodeSequence0040A043;

    /**
     * Obtient la valeur de la propriété contentTemplateSequence0040A504.
     * 
     * @return
     *     possible object is
     *     {@link DICOMCodeSequenceType }
     *     
     */
    public DICOMCodeSequenceType getContentTemplateSequence0040A504() {
        return contentTemplateSequence0040A504;
    }

    /**
     * Définit la valeur de la propriété contentTemplateSequence0040A504.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMCodeSequenceType }
     *     
     */
    public void setContentTemplateSequence0040A504(DICOMCodeSequenceType value) {
        this.contentTemplateSequence0040A504 = value;
    }

    /**
     * Obtient la valeur de la propriété conceptNameCodeSequence0040A043.
     * 
     * @return
     *     possible object is
     *     {@link DICOMCodeSequenceType }
     *     
     */
    public DICOMCodeSequenceType getConceptNameCodeSequence0040A043() {
        return conceptNameCodeSequence0040A043;
    }

    /**
     * Définit la valeur de la propriété conceptNameCodeSequence0040A043.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMCodeSequenceType }
     *     
     */
    public void setConceptNameCodeSequence0040A043(DICOMCodeSequenceType value) {
        this.conceptNameCodeSequence0040A043 = value;
    }

}
