//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.06 à 05:03:32 PM CET 
//


package javaXSDclass;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour DataActivityPerROIAtTimePointContainer complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DataActivityPerROIAtTimePointContainer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DataActivityPerROIAtTimePointProduced" type="{https://www.irdbb-medirad.com}DataActivityPerROIAtTimePoint" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataActivityPerROIAtTimePointContainer", propOrder = {
    "dataActivityPerROIAtTimePointProduced"
})
public class DataActivityPerROIAtTimePointContainer {

    @XmlElement(name = "DataActivityPerROIAtTimePointProduced", required = true)
    protected List<DataActivityPerROIAtTimePoint> dataActivityPerROIAtTimePointProduced;

    /**
     * Gets the value of the dataActivityPerROIAtTimePointProduced property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataActivityPerROIAtTimePointProduced property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataActivityPerROIAtTimePointProduced().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataActivityPerROIAtTimePoint }
     * 
     * 
     */
    public List<DataActivityPerROIAtTimePoint> getDataActivityPerROIAtTimePointProduced() {
        if (dataActivityPerROIAtTimePointProduced == null) {
            dataActivityPerROIAtTimePointProduced = new ArrayList<DataActivityPerROIAtTimePoint>();
        }
        return this.dataActivityPerROIAtTimePointProduced;
    }

}
