//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.04 à 12:01:52 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour SPECTRecoveryCoefficientCurve complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SPECTRecoveryCoefficientCurve">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SPECTRecoveryCoefficientCurve" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SPECTRecoveryCoefficientCurve", propOrder = {
    "spectRecoveryCoefficientCurve"
})
public class SPECTRecoveryCoefficientCurve {

    @XmlElement(name = "SPECTRecoveryCoefficientCurve", required = true)
    protected String spectRecoveryCoefficientCurve;

    /**
     * Obtient la valeur de la propriété spectRecoveryCoefficientCurve.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSPECTRecoveryCoefficientCurve() {
        return spectRecoveryCoefficientCurve;
    }

    /**
     * Définit la valeur de la propriété spectRecoveryCoefficientCurve.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSPECTRecoveryCoefficientCurve(String value) {
        this.spectRecoveryCoefficientCurve = value;
    }

}
