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
 *         &lt;element name="SegmentationMethod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ROI" type="{https://www.irdbb-medirad.com}ROIdescriptor"/>
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
    "segmentationMethod",
    "roi"
})
@XmlRootElement(name = "planarImageSegmentation")
public class PlanarImageSegmentation {

    @XmlElement(name = "SegmentationMethod", required = true)
    protected String segmentationMethod;
    @XmlElement(name = "ROI", required = true)
    protected ROIdescriptor roi;

    /**
     * Obtient la valeur de la propriété segmentationMethod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegmentationMethod() {
        return segmentationMethod;
    }

    /**
     * Définit la valeur de la propriété segmentationMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegmentationMethod(String value) {
        this.segmentationMethod = value;
    }

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

}
