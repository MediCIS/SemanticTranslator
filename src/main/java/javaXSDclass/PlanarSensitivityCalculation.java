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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ROI" type="{https://www.irdbb-medirad.com}ROIdescriptor"/>
 *         &lt;element name="NetplanarSourceActivity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CalibrationCoefficient" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PreAdminMeasuredActivity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PostAdminMeasuredActivity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "roi",
    "netplanarSourceActivity",
    "calibrationCoefficient",
    "preAdminMeasuredActivity",
    "postAdminMeasuredActivity"
})
@XmlRootElement(name = "planarSensitivityCalculation")
public class PlanarSensitivityCalculation {

    @XmlElement(name = "ROI", required = true)
    protected ROIdescriptor roi;
    @XmlElement(name = "NetplanarSourceActivity", required = true)
    protected String netplanarSourceActivity;
    @XmlElement(name = "CalibrationCoefficient", required = true)
    protected String calibrationCoefficient;
    @XmlElement(name = "PreAdminMeasuredActivity", required = true)
    protected String preAdminMeasuredActivity;
    @XmlElement(name = "PostAdminMeasuredActivity", required = true)
    protected String postAdminMeasuredActivity;

    /**
     * Obtient la valeur de la propriété roi.
     * 
     * @return
     *     possible object is
     *     {@link ROIdescriptor }
     *     
     */
    public ROIdescriptor getROI() {
        return roi;
    }

    /**
     * Définit la valeur de la propriété roi.
     * 
     * @param value
     *     allowed object is
     *     {@link ROIdescriptor }
     *     
     */
    public void setROI(ROIdescriptor value) {
        this.roi = value;
    }

    /**
     * Obtient la valeur de la propriété netplanarSourceActivity.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetplanarSourceActivity() {
        return netplanarSourceActivity;
    }

    /**
     * Définit la valeur de la propriété netplanarSourceActivity.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetplanarSourceActivity(String value) {
        this.netplanarSourceActivity = value;
    }

    /**
     * Obtient la valeur de la propriété calibrationCoefficient.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalibrationCoefficient() {
        return calibrationCoefficient;
    }

    /**
     * Définit la valeur de la propriété calibrationCoefficient.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalibrationCoefficient(String value) {
        this.calibrationCoefficient = value;
    }

    /**
     * Obtient la valeur de la propriété preAdminMeasuredActivity.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreAdminMeasuredActivity() {
        return preAdminMeasuredActivity;
    }

    /**
     * Définit la valeur de la propriété preAdminMeasuredActivity.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreAdminMeasuredActivity(String value) {
        this.preAdminMeasuredActivity = value;
    }

    /**
     * Obtient la valeur de la propriété postAdminMeasuredActivity.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostAdminMeasuredActivity() {
        return postAdminMeasuredActivity;
    }

    /**
     * Définit la valeur de la propriété postAdminMeasuredActivity.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostAdminMeasuredActivity(String value) {
        this.postAdminMeasuredActivity = value;
    }

}
