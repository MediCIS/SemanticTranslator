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
 * <p>Classe Java pour SPECTAcqCTAcqAndReconstruction complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SPECTAcqCTAcqAndReconstruction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TimePointDescription" type="{https://www.irdbb-medirad.com}TimePointDescription"/>
 *         &lt;element name="NMRelevantCalibrationReference" type="{https://www.irdbb-medirad.com}NMRelevantCalibrationReference"/>
 *         &lt;element name="CTRelevantCalibrationReference" type="{https://www.irdbb-medirad.com}CTRelevantCalibrationReference"/>
 *         &lt;element name="NMTomoProduced" type="{https://www.irdbb-medirad.com}DICOMData"/>
 *         &lt;element name="CTReconProduced" type="{https://www.irdbb-medirad.com}DICOMData"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SPECTAcqCTAcqAndReconstruction", propOrder = {
    "timePointDescription",
    "nmRelevantCalibrationReference",
    "ctRelevantCalibrationReference",
    "nmTomoProduced",
    "ctReconProduced"
})
public class SPECTAcqCTAcqAndReconstruction {

    @XmlElement(name = "TimePointDescription", required = true)
    protected TimePointDescription timePointDescription;
    @XmlElement(name = "NMRelevantCalibrationReference", required = true)
    protected NMRelevantCalibrationReference nmRelevantCalibrationReference;
    @XmlElement(name = "CTRelevantCalibrationReference", required = true)
    protected CTRelevantCalibrationReference ctRelevantCalibrationReference;
    @XmlElement(name = "NMTomoProduced", required = true)
    protected DICOMData nmTomoProduced;
    @XmlElement(name = "CTReconProduced", required = true)
    protected DICOMData ctReconProduced;

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
     * Obtient la valeur de la propriété ctRelevantCalibrationReference.
     * 
     * @return
     *     possible object is
     *     {@link CTRelevantCalibrationReference }
     *     
     */
    public CTRelevantCalibrationReference getCTRelevantCalibrationReference() {
        return ctRelevantCalibrationReference;
    }

    /**
     * Définit la valeur de la propriété ctRelevantCalibrationReference.
     * 
     * @param value
     *     allowed object is
     *     {@link CTRelevantCalibrationReference }
     *     
     */
    public void setCTRelevantCalibrationReference(CTRelevantCalibrationReference value) {
        this.ctRelevantCalibrationReference = value;
    }

    /**
     * Obtient la valeur de la propriété nmTomoProduced.
     * 
     * @return
     *     possible object is
     *     {@link DICOMData }
     *     
     */
    public DICOMData getNMTomoProduced() {
        return nmTomoProduced;
    }

    /**
     * Définit la valeur de la propriété nmTomoProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMData }
     *     
     */
    public void setNMTomoProduced(DICOMData value) {
        this.nmTomoProduced = value;
    }

    /**
     * Obtient la valeur de la propriété ctReconProduced.
     * 
     * @return
     *     possible object is
     *     {@link DICOMData }
     *     
     */
    public DICOMData getCTReconProduced() {
        return ctReconProduced;
    }

    /**
     * Définit la valeur de la propriété ctReconProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMData }
     *     
     */
    public void setCTReconProduced(DICOMData value) {
        this.ctReconProduced = value;
    }

}
