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
 * <p>Classe Java pour PlanarDataAcquisition complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PlanarDataAcquisition">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TimePointDescription" type="{https://www.irdbb-medirad.com}TimePointDescription"/>
 *         &lt;element name="NMRelevantCalibrationReference" type="{https://www.irdbb-medirad.com}NMRelevantCalibrationReference"/>
 *         &lt;element name="NMStaticProduced" type="{https://www.irdbb-medirad.com}DICOMData" minOccurs="0"/>
 *         &lt;element name="NonDicomNMStaticProduced" type="{https://www.irdbb-medirad.com}NonDICOMData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlanarDataAcquisition", propOrder = {
    "timePointDescription",
    "nmRelevantCalibrationReference",
    "nmStaticProduced",
    "nonDicomNMStaticProduced"
})
public class PlanarDataAcquisition {

    @XmlElement(name = "TimePointDescription", required = true)
    protected TimePointDescription timePointDescription;
    @XmlElement(name = "NMRelevantCalibrationReference", required = true)
    protected NMRelevantCalibrationReference nmRelevantCalibrationReference;
    @XmlElement(name = "NMStaticProduced")
    protected DICOMData nmStaticProduced;
    @XmlElement(name = "NonDicomNMStaticProduced")
    protected NonDICOMData nonDicomNMStaticProduced;

    /**
     * Obtient la valeur de la propriété timePointDescription.
     * 
     * @return
     *     possible object is
     *     {@link TimePointDescription }
     *     
     */
    public TimePointDescription getTimePointDescription() {
        return timePointDescription;
    }

    /**
     * Définit la valeur de la propriété timePointDescription.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePointDescription }
     *     
     */
    public void setTimePointDescription(TimePointDescription value) {
        this.timePointDescription = value;
    }

    /**
     * Obtient la valeur de la propriété nmRelevantCalibrationReference.
     * 
     * @return
     *     possible object is
     *     {@link NMRelevantCalibrationReference }
     *     
     */
    public NMRelevantCalibrationReference getNMRelevantCalibrationReference() {
        return nmRelevantCalibrationReference;
    }

    /**
     * Définit la valeur de la propriété nmRelevantCalibrationReference.
     * 
     * @param value
     *     allowed object is
     *     {@link NMRelevantCalibrationReference }
     *     
     */
    public void setNMRelevantCalibrationReference(NMRelevantCalibrationReference value) {
        this.nmRelevantCalibrationReference = value;
    }

    /**
     * Obtient la valeur de la propriété nmStaticProduced.
     * 
     * @return
     *     possible object is
     *     {@link DICOMData }
     *     
     */
    public DICOMData getNMStaticProduced() {
        return nmStaticProduced;
    }

    /**
     * Définit la valeur de la propriété nmStaticProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMData }
     *     
     */
    public void setNMStaticProduced(DICOMData value) {
        this.nmStaticProduced = value;
    }

    /**
     * Obtient la valeur de la propriété nonDicomNMStaticProduced.
     * 
     * @return
     *     possible object is
     *     {@link NonDICOMData }
     *     
     */
    public NonDICOMData getNonDicomNMStaticProduced() {
        return nonDicomNMStaticProduced;
    }

    /**
     * Définit la valeur de la propriété nonDicomNMStaticProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link NonDICOMData }
     *     
     */
    public void setNonDicomNMStaticProduced(NonDICOMData value) {
        this.nonDicomNMStaticProduced = value;
    }

}
