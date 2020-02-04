//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.04 à 12:01:52 PM CET 
//


package javaXSDclass;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour MeanAbsorbedDoseInROIcontainer complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="MeanAbsorbedDoseInROIcontainer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MeanAbsorbedDoseInROIProduced" type="{https://www.irdbb-medirad.com}MeanAbsorbedDoseInROI" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MeanAbsorbedDoseInROIcontainer", propOrder = {
    "meanAbsorbedDoseInROIProduced"
})
public class MeanAbsorbedDoseInROIcontainer {

    @XmlElement(name = "MeanAbsorbedDoseInROIProduced", required = true)
    protected List<MeanAbsorbedDoseInROI> meanAbsorbedDoseInROIProduced;

    /**
     * Gets the value of the meanAbsorbedDoseInROIProduced property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the meanAbsorbedDoseInROIProduced property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMeanAbsorbedDoseInROIProduced().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MeanAbsorbedDoseInROI }
     * 
     * 
     */
    public List<MeanAbsorbedDoseInROI> getMeanAbsorbedDoseInROIProduced() {
        if (meanAbsorbedDoseInROIProduced == null) {
            meanAbsorbedDoseInROIProduced = new ArrayList<MeanAbsorbedDoseInROI>();
        }
        return this.meanAbsorbedDoseInROIProduced;
    }

}
