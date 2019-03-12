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
 * <p>Classe Java pour SimpleCTMonteCarloDosimetry complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SimpleCTMonteCarloDosimetry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CalculationOfVoxelMap" type="{https://www.irdbb-medirad.com}CalculationOfVoxelMap"/>
 *         &lt;element name="CalculationOfAbsorbedDosesInVOIs" type="{https://www.irdbb-medirad.com}CalculationOfAbsorbedDosesInVOIs"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SimpleCTMonteCarloDosimetry", propOrder = {
    "calculationOfVoxelMap",
    "calculationOfAbsorbedDosesInVOIs"
})
public class SimpleCTMonteCarloDosimetry {

    @XmlElement(name = "CalculationOfVoxelMap", required = true)
    protected CalculationOfVoxelMap calculationOfVoxelMap;
    @XmlElement(name = "CalculationOfAbsorbedDosesInVOIs", required = true)
    protected CalculationOfAbsorbedDosesInVOIs calculationOfAbsorbedDosesInVOIs;

    /**
     * Obtient la valeur de la propriété calculationOfVoxelMap.
     * 
     * @return
     *     possible object is
     *     {@link CalculationOfVoxelMap }
     *     
     */
    public CalculationOfVoxelMap getCalculationOfVoxelMap() {
        return calculationOfVoxelMap;
    }

    /**
     * Définit la valeur de la propriété calculationOfVoxelMap.
     * 
     * @param value
     *     allowed object is
     *     {@link CalculationOfVoxelMap }
     *     
     */
    public void setCalculationOfVoxelMap(CalculationOfVoxelMap value) {
        this.calculationOfVoxelMap = value;
    }

    /**
     * Obtient la valeur de la propriété calculationOfAbsorbedDosesInVOIs.
     * 
     * @return
     *     possible object is
     *     {@link CalculationOfAbsorbedDosesInVOIs }
     *     
     */
    public CalculationOfAbsorbedDosesInVOIs getCalculationOfAbsorbedDosesInVOIs() {
        return calculationOfAbsorbedDosesInVOIs;
    }

    /**
     * Définit la valeur de la propriété calculationOfAbsorbedDosesInVOIs.
     * 
     * @param value
     *     allowed object is
     *     {@link CalculationOfAbsorbedDosesInVOIs }
     *     
     */
    public void setCalculationOfAbsorbedDosesInVOIs(CalculationOfAbsorbedDosesInVOIs value) {
        this.calculationOfAbsorbedDosesInVOIs = value;
    }

}
