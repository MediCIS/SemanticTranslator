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
 * <p>Classe Java pour SPECTsensitivityCalculation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SPECTsensitivityCalculation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CalibrationCoefficient" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Pre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Post" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SPECTsensitivityCalculation", propOrder = {
    "calibrationCoefficient",
    "pre",
    "post"
})
public class SPECTsensitivityCalculation {

    @XmlElement(name = "CalibrationCoefficient", required = true)
    protected String calibrationCoefficient;
    @XmlElement(name = "Pre", required = true)
    protected String pre;
    @XmlElement(name = "Post", required = true)
    protected String post;

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
     * Obtient la valeur de la propriété pre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPre() {
        return pre;
    }

    /**
     * Définit la valeur de la propriété pre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPre(String value) {
        this.pre = value;
    }

    /**
     * Obtient la valeur de la propriété post.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPost() {
        return post;
    }

    /**
     * Définit la valeur de la propriété post.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPost(String value) {
        this.post = value;
    }

}
