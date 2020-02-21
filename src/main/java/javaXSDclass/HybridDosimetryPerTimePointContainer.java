//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.12 à 04:30:42 PM CET 
//


package javaXSDclass;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour HybridDosimetryPerTimePointContainer complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="HybridDosimetryPerTimePointContainer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HybridDosimetryPerTimePoint" type="{https://www.irdbb-medirad.com}HybridDosimetryPerTimePoint" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HybridDosimetryPerTimePointContainer", propOrder = {
    "hybridDosimetryPerTimePoint"
})
public class HybridDosimetryPerTimePointContainer {

    @XmlElement(name = "HybridDosimetryPerTimePoint", required = true)
    protected List<HybridDosimetryPerTimePoint> hybridDosimetryPerTimePoint;

    /**
     * Gets the value of the hybridDosimetryPerTimePoint property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hybridDosimetryPerTimePoint property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHybridDosimetryPerTimePoint().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HybridDosimetryPerTimePoint }
     * 
     * 
     */
    public List<HybridDosimetryPerTimePoint> getHybridDosimetryPerTimePoint() {
        if (hybridDosimetryPerTimePoint == null) {
            hybridDosimetryPerTimePoint = new ArrayList<HybridDosimetryPerTimePoint>();
        }
        return this.hybridDosimetryPerTimePoint;
    }

}
