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
 * <p>Classe Java pour SumAndScalingAbsorbedDoseRateCalculationContainer complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SumAndScalingAbsorbedDoseRateCalculationContainer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SumAndScalingAbsorbedDoseRateCalculation" type="{https://www.irdbb-medirad.com}SumAndScalingAbsorbedDoseRateCalculation" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SumAndScalingAbsorbedDoseRateCalculationContainer", propOrder = {
    "sumAndScalingAbsorbedDoseRateCalculation"
})
public class SumAndScalingAbsorbedDoseRateCalculationContainer {

    @XmlElement(name = "SumAndScalingAbsorbedDoseRateCalculation", required = true)
    protected List<SumAndScalingAbsorbedDoseRateCalculation> sumAndScalingAbsorbedDoseRateCalculation;

    /**
     * Gets the value of the sumAndScalingAbsorbedDoseRateCalculation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sumAndScalingAbsorbedDoseRateCalculation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSumAndScalingAbsorbedDoseRateCalculation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SumAndScalingAbsorbedDoseRateCalculation }
     * 
     * 
     */
    public List<SumAndScalingAbsorbedDoseRateCalculation> getSumAndScalingAbsorbedDoseRateCalculation() {
        if (sumAndScalingAbsorbedDoseRateCalculation == null) {
            sumAndScalingAbsorbedDoseRateCalculation = new ArrayList<SumAndScalingAbsorbedDoseRateCalculation>();
        }
        return this.sumAndScalingAbsorbedDoseRateCalculation;
    }

}
