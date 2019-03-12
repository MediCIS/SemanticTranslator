//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2018.12.11 à 04:47:30 PM CET 
//


package javaXSDclass;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TwoDimDosimetryViaAbsorbedDoseRateCalculation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TwoDimDosimetryViaAbsorbedDoseRateCalculation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AbsorbedDoseRateCalculation" type="{https://www.irdbb-medirad.com}AbsorbedDoseRateCalculation" maxOccurs="unbounded"/>
 *         &lt;element name="TimeAbsorbedDoseCurveFit" type="{https://www.irdbb-medirad.com}TimeAbsorbedDoseCurveFit"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TwoDimDosimetryViaAbsorbedDoseRateCalculation", propOrder = {
    "absorbedDoseRateCalculation",
    "timeAbsorbedDoseCurveFit"
})
public class TwoDimDosimetryViaAbsorbedDoseRateCalculation {

    @XmlElement(name = "AbsorbedDoseRateCalculation", required = true)
    protected List<AbsorbedDoseRateCalculation> absorbedDoseRateCalculation;
    @XmlElement(name = "TimeAbsorbedDoseCurveFit", required = true)
    protected TimeAbsorbedDoseCurveFit timeAbsorbedDoseCurveFit;

    /**
     * Gets the value of the absorbedDoseRateCalculation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the absorbedDoseRateCalculation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAbsorbedDoseRateCalculation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AbsorbedDoseRateCalculation }
     * 
     * 
     */
    public List<AbsorbedDoseRateCalculation> getAbsorbedDoseRateCalculation() {
        if (absorbedDoseRateCalculation == null) {
            absorbedDoseRateCalculation = new ArrayList<AbsorbedDoseRateCalculation>();
        }
        return this.absorbedDoseRateCalculation;
    }

    /**
     * Obtient la valeur de la propriété timeAbsorbedDoseCurveFit.
     * 
     * @return
     *     possible object is
     *     {@link TimeAbsorbedDoseCurveFit }
     *     
     */
    public TimeAbsorbedDoseCurveFit getTimeAbsorbedDoseCurveFit() {
        return timeAbsorbedDoseCurveFit;
    }

    /**
     * Définit la valeur de la propriété timeAbsorbedDoseCurveFit.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeAbsorbedDoseCurveFit }
     *     
     */
    public void setTimeAbsorbedDoseCurveFit(TimeAbsorbedDoseCurveFit value) {
        this.timeAbsorbedDoseCurveFit = value;
    }

}
