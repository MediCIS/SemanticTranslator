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
 * <p>Classe Java pour CTAcqForCTNumberCalibrationCurve complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CTAcqForCTNumberCalibrationCurve">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DensityPhantomUsed" type="{https://www.irdbb-medirad.com}DensityPhantom"/>
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
@XmlType(name = "CTAcqForCTNumberCalibrationCurve", propOrder = {
    "densityPhantomUsed",
    "ctReconProduced"
})
public class CTAcqForCTNumberCalibrationCurve {

    @XmlElement(name = "DensityPhantomUsed", required = true)
    protected DensityPhantom densityPhantomUsed;
    @XmlElement(name = "CTReconProduced", required = true)
    protected DICOMData ctReconProduced;

    /**
     * Obtient la valeur de la propriété densityPhantomUsed.
     * 
     * @return
     *     possible object is
     *     {@link DensityPhantom }
     *     
     */
    public DensityPhantom getDensityPhantomUsed() {
        return densityPhantomUsed;
    }

    /**
     * Définit la valeur de la propriété densityPhantomUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link DensityPhantom }
     *     
     */
    public void setDensityPhantomUsed(DensityPhantom value) {
        this.densityPhantomUsed = value;
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
