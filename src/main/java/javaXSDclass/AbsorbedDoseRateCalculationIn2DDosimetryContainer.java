//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.01.28 à 04:59:07 PM CET 
//


package javaXSDclass;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour AbsorbedDoseRateCalculationIn2DDosimetryContainer complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="AbsorbedDoseRateCalculationIn2DDosimetryContainer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AbsorbedDoseRateCalculationIn2DDosimetry" type="{https://www.irdbb-medirad.com}AbsorbedDoseRateCalculationIn2DDosimetry" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbsorbedDoseRateCalculationIn2DDosimetryContainer", propOrder = {
    "absorbedDoseRateCalculationIn2DDosimetry"
})
public class AbsorbedDoseRateCalculationIn2DDosimetryContainer {

    @XmlElement(name = "AbsorbedDoseRateCalculationIn2DDosimetry", required = true)
    protected List<AbsorbedDoseRateCalculationIn2DDosimetry> absorbedDoseRateCalculationIn2DDosimetry;

    /**
     * Gets the value of the absorbedDoseRateCalculationIn2DDosimetry property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the absorbedDoseRateCalculationIn2DDosimetry property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAbsorbedDoseRateCalculationIn2DDosimetry().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AbsorbedDoseRateCalculationIn2DDosimetry }
     * 
     * 
     */
    public List<AbsorbedDoseRateCalculationIn2DDosimetry> getAbsorbedDoseRateCalculationIn2DDosimetry() {
        if (absorbedDoseRateCalculationIn2DDosimetry == null) {
            absorbedDoseRateCalculationIn2DDosimetry = new ArrayList<AbsorbedDoseRateCalculationIn2DDosimetry>();
        }
        return this.absorbedDoseRateCalculationIn2DDosimetry;
    }

}
