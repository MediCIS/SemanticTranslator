//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.07.30 à 02:06:37 PM CEST 
//


package javaXSDclass;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour AbsorbedDoseRatePerVOIAtTimePointContainer complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="AbsorbedDoseRatePerVOIAtTimePointContainer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AbsorbedDoseRatePerVOIAtTimePointProduced" type="{https://www.irdbb-medirad.com}AbsorbedDoseRatePerVOIAtTimePoint" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbsorbedDoseRatePerVOIAtTimePointContainer", propOrder = {
    "absorbedDoseRatePerVOIAtTimePointProduced"
})
public class AbsorbedDoseRatePerVOIAtTimePointContainer {

    @XmlElement(name = "AbsorbedDoseRatePerVOIAtTimePointProduced", required = true)
    protected List<AbsorbedDoseRatePerVOIAtTimePoint> absorbedDoseRatePerVOIAtTimePointProduced;

    /**
     * Gets the value of the absorbedDoseRatePerVOIAtTimePointProduced property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the absorbedDoseRatePerVOIAtTimePointProduced property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAbsorbedDoseRatePerVOIAtTimePointProduced().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AbsorbedDoseRatePerVOIAtTimePoint }
     * 
     * 
     */
    public List<AbsorbedDoseRatePerVOIAtTimePoint> getAbsorbedDoseRatePerVOIAtTimePointProduced() {
        if (absorbedDoseRatePerVOIAtTimePointProduced == null) {
            absorbedDoseRatePerVOIAtTimePointProduced = new ArrayList<AbsorbedDoseRatePerVOIAtTimePoint>();
        }
        return this.absorbedDoseRatePerVOIAtTimePointProduced;
    }

}
