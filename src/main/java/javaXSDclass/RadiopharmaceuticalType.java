//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.07.30 à 02:06:36 PM CEST 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour RadiopharmaceuticalType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="RadiopharmaceuticalType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Radiopharmaceutical-00180031" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RadiopharmaceuticalCodeSequence-00540304" type="{https://www.irdbb-medirad.com}DICOMCodeSequenceType"/>
 *         &lt;element name="RadionuclideCodeSequence-005400300" type="{https://www.irdbb-medirad.com}DICOMCodeSequenceType"/>
 *         &lt;element name="RadiopharmaceuticalVolume-00181071" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RadiopharmaceuticalStartTime-00181072" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RadiopharmaceuticalStopTime-00181073" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RadionuclideTotalDose-00181074" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RadiopharmaceuticalAdministrationEventUID-00083012" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RadiopharmaceuticalType", propOrder = {
    "radiopharmaceutical00180031",
    "radiopharmaceuticalCodeSequence00540304",
    "radionuclideCodeSequence005400300",
    "radiopharmaceuticalVolume00181071",
    "radiopharmaceuticalStartTime00181072",
    "radiopharmaceuticalStopTime00181073",
    "radionuclideTotalDose00181074",
    "radiopharmaceuticalAdministrationEventUID00083012"
})
public class RadiopharmaceuticalType {

    @XmlElement(name = "Radiopharmaceutical-00180031", required = true)
    protected String radiopharmaceutical00180031;
    @XmlElement(name = "RadiopharmaceuticalCodeSequence-00540304", required = true)
    protected DICOMCodeSequenceType radiopharmaceuticalCodeSequence00540304;
    @XmlElement(name = "RadionuclideCodeSequence-005400300", required = true)
    protected DICOMCodeSequenceType radionuclideCodeSequence005400300;
    @XmlElement(name = "RadiopharmaceuticalVolume-00181071", required = true)
    protected String radiopharmaceuticalVolume00181071;
    @XmlElement(name = "RadiopharmaceuticalStartTime-00181072", required = true)
    protected String radiopharmaceuticalStartTime00181072;
    @XmlElement(name = "RadiopharmaceuticalStopTime-00181073", required = true)
    protected String radiopharmaceuticalStopTime00181073;
    @XmlElement(name = "RadionuclideTotalDose-00181074", required = true)
    protected String radionuclideTotalDose00181074;
    @XmlElement(name = "RadiopharmaceuticalAdministrationEventUID-00083012", required = true)
    protected String radiopharmaceuticalAdministrationEventUID00083012;

    /**
     * Obtient la valeur de la propriété radiopharmaceutical00180031.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRadiopharmaceutical00180031() {
        return radiopharmaceutical00180031;
    }

    /**
     * Définit la valeur de la propriété radiopharmaceutical00180031.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRadiopharmaceutical00180031(String value) {
        this.radiopharmaceutical00180031 = value;
    }

    /**
     * Obtient la valeur de la propriété radiopharmaceuticalCodeSequence00540304.
     * 
     * @return
     *     possible object is
     *     {@link DICOMCodeSequenceType }
     *     
     */
    public DICOMCodeSequenceType getRadiopharmaceuticalCodeSequence00540304() {
        return radiopharmaceuticalCodeSequence00540304;
    }

    /**
     * Définit la valeur de la propriété radiopharmaceuticalCodeSequence00540304.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMCodeSequenceType }
     *     
     */
    public void setRadiopharmaceuticalCodeSequence00540304(DICOMCodeSequenceType value) {
        this.radiopharmaceuticalCodeSequence00540304 = value;
    }

    /**
     * Obtient la valeur de la propriété radionuclideCodeSequence005400300.
     * 
     * @return
     *     possible object is
     *     {@link DICOMCodeSequenceType }
     *     
     */
    public DICOMCodeSequenceType getRadionuclideCodeSequence005400300() {
        return radionuclideCodeSequence005400300;
    }

    /**
     * Définit la valeur de la propriété radionuclideCodeSequence005400300.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMCodeSequenceType }
     *     
     */
    public void setRadionuclideCodeSequence005400300(DICOMCodeSequenceType value) {
        this.radionuclideCodeSequence005400300 = value;
    }

    /**
     * Obtient la valeur de la propriété radiopharmaceuticalVolume00181071.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRadiopharmaceuticalVolume00181071() {
        return radiopharmaceuticalVolume00181071;
    }

    /**
     * Définit la valeur de la propriété radiopharmaceuticalVolume00181071.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRadiopharmaceuticalVolume00181071(String value) {
        this.radiopharmaceuticalVolume00181071 = value;
    }

    /**
     * Obtient la valeur de la propriété radiopharmaceuticalStartTime00181072.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRadiopharmaceuticalStartTime00181072() {
        return radiopharmaceuticalStartTime00181072;
    }

    /**
     * Définit la valeur de la propriété radiopharmaceuticalStartTime00181072.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRadiopharmaceuticalStartTime00181072(String value) {
        this.radiopharmaceuticalStartTime00181072 = value;
    }

    /**
     * Obtient la valeur de la propriété radiopharmaceuticalStopTime00181073.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRadiopharmaceuticalStopTime00181073() {
        return radiopharmaceuticalStopTime00181073;
    }

    /**
     * Définit la valeur de la propriété radiopharmaceuticalStopTime00181073.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRadiopharmaceuticalStopTime00181073(String value) {
        this.radiopharmaceuticalStopTime00181073 = value;
    }

    /**
     * Obtient la valeur de la propriété radionuclideTotalDose00181074.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRadionuclideTotalDose00181074() {
        return radionuclideTotalDose00181074;
    }

    /**
     * Définit la valeur de la propriété radionuclideTotalDose00181074.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRadionuclideTotalDose00181074(String value) {
        this.radionuclideTotalDose00181074 = value;
    }

    /**
     * Obtient la valeur de la propriété radiopharmaceuticalAdministrationEventUID00083012.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRadiopharmaceuticalAdministrationEventUID00083012() {
        return radiopharmaceuticalAdministrationEventUID00083012;
    }

    /**
     * Définit la valeur de la propriété radiopharmaceuticalAdministrationEventUID00083012.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRadiopharmaceuticalAdministrationEventUID00083012(String value) {
        this.radiopharmaceuticalAdministrationEventUID00083012 = value;
    }

}
