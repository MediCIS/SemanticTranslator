//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2018.10.16 à 03:58:01 PM CEST 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CTacqForCTnumberCalibrationCurve complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CTacqForCTnumberCalibrationCurve">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DensityPhantom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AcquisitionSettingsCT" type="{https://www.irdbb-medirad.com}complexUnknown"/>
 *         &lt;element name="CTimage" type="{https://www.irdbb-medirad.com}CTimage"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CTacqForCTnumberCalibrationCurve", propOrder = {
    "densityPhantom",
    "acquisitionSettingsCT",
    "cTimage"
})
public class CTacqForCTnumberCalibrationCurve {

    @XmlElement(name = "DensityPhantom", required = true)
    protected String densityPhantom;
    @XmlElement(name = "AcquisitionSettingsCT", required = true)
    protected ComplexUnknown acquisitionSettingsCT;
    @XmlElement(name = "CTimage", required = true)
    protected CTimage cTimage;

    /**
     * Obtient la valeur de la propriété densityPhantom.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDensityPhantom() {
        return densityPhantom;
    }

    /**
     * Définit la valeur de la propriété densityPhantom.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDensityPhantom(String value) {
        this.densityPhantom = value;
    }

    /**
     * Obtient la valeur de la propriété acquisitionSettingsCT.
     * 
     * @return
     *     possible object is
     *     {@link ComplexUnknown }
     *     
     */
    public ComplexUnknown getAcquisitionSettingsCT() {
        return acquisitionSettingsCT;
    }

    /**
     * Définit la valeur de la propriété acquisitionSettingsCT.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexUnknown }
     *     
     */
    public void setAcquisitionSettingsCT(ComplexUnknown value) {
        this.acquisitionSettingsCT = value;
    }

    /**
     * Obtient la valeur de la propriété cTimage.
     * 
     * @return
     *     possible object is
     *     {@link CTimage }
     *     
     */
    public CTimage getCTimage() {
        return cTimage;
    }

    /**
     * Définit la valeur de la propriété cTimage.
     * 
     * @param value
     *     allowed object is
     *     {@link CTimage }
     *     
     */
    public void setCTimage(CTimage value) {
        this.cTimage = value;
    }

}
