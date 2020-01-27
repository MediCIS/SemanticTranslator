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
 * <p>Classe Java pour SPECTAcqCTAcqAndReconstructionInCalibration complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SPECTAcqCTAcqAndReconstructionInCalibration">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PhantomUsed" type="{https://www.irdbb-medirad.com}NMPhantom"/>
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
@XmlType(name = "SPECTAcqCTAcqAndReconstructionInCalibration", propOrder = {
    "phantomUsed",
    "nmTomoProduced",
    "ctReconProduced"
})
public class SPECTAcqCTAcqAndReconstructionInCalibration {

    @XmlElement(name = "PhantomUsed", required = true)
    protected NMPhantom phantomUsed;
    @XmlElement(name = "NMTomoProduced", required = true)
    protected DICOMData nmTomoProduced;
    @XmlElement(name = "CTReconProduced", required = true)
    protected DICOMData ctReconProduced;

    /**
     * Obtient la valeur de la propriété phantomUsed.
     * 
     * @return
     *     possible object is
     *     {@link NMPhantom }
     *     
     */
    public NMPhantom getPhantomUsed() {
        return phantomUsed;
    }

    /**
     * Définit la valeur de la propriété phantomUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link NMPhantom }
     *     
     */
    public void setPhantomUsed(NMPhantom value) {
        this.phantomUsed = value;
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
