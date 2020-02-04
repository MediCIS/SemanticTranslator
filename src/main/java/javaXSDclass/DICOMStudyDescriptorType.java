//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.04 à 12:01:51 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour DICOMStudyDescriptorType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DICOMStudyDescriptorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StudyInstanceUID-0020000d" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="StudyDate-00080020" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="StudyTime-00080030" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BodyPartExamined-00180015" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="StudyDescription-00081030" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DICOMStudyDescriptorType", propOrder = {
    "studyInstanceUID0020000D",
    "studyDate00080020",
    "studyTime00080030",
    "bodyPartExamined00180015",
    "studyDescription00081030"
})
public class DICOMStudyDescriptorType {

    @XmlElement(name = "StudyInstanceUID-0020000d", required = true)
    protected String studyInstanceUID0020000D;
    @XmlElement(name = "StudyDate-00080020", required = true)
    protected String studyDate00080020;
    @XmlElement(name = "StudyTime-00080030", required = true)
    protected String studyTime00080030;
    @XmlElement(name = "BodyPartExamined-00180015", required = true)
    protected String bodyPartExamined00180015;
    @XmlElement(name = "StudyDescription-00081030", required = true)
    protected String studyDescription00081030;

    /**
     * Obtient la valeur de la propriété studyInstanceUID0020000D.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudyInstanceUID0020000D() {
        return studyInstanceUID0020000D;
    }

    /**
     * Définit la valeur de la propriété studyInstanceUID0020000D.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudyInstanceUID0020000D(String value) {
        this.studyInstanceUID0020000D = value;
    }

    /**
     * Obtient la valeur de la propriété studyDate00080020.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudyDate00080020() {
        return studyDate00080020;
    }

    /**
     * Définit la valeur de la propriété studyDate00080020.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudyDate00080020(String value) {
        this.studyDate00080020 = value;
    }

    /**
     * Obtient la valeur de la propriété studyTime00080030.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudyTime00080030() {
        return studyTime00080030;
    }

    /**
     * Définit la valeur de la propriété studyTime00080030.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudyTime00080030(String value) {
        this.studyTime00080030 = value;
    }

    /**
     * Obtient la valeur de la propriété bodyPartExamined00180015.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBodyPartExamined00180015() {
        return bodyPartExamined00180015;
    }

    /**
     * Définit la valeur de la propriété bodyPartExamined00180015.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBodyPartExamined00180015(String value) {
        this.bodyPartExamined00180015 = value;
    }

    /**
     * Obtient la valeur de la propriété studyDescription00081030.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudyDescription00081030() {
        return studyDescription00081030;
    }

    /**
     * Définit la valeur de la propriété studyDescription00081030.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudyDescription00081030(String value) {
        this.studyDescription00081030 = value;
    }

}
