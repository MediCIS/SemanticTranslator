//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.01.28 à 04:59:07 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour PlanarAcquisition complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PlanarAcquisition">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PhantomUsed" type="{https://www.irdbb-medirad.com}NMPhantom"/>
 *         &lt;element name="NMStaticProduced" type="{https://www.irdbb-medirad.com}DICOMData"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlanarAcquisition", propOrder = {
    "phantomUsed",
    "nmStaticProduced"
})
public class PlanarAcquisition {

    @XmlElement(name = "PhantomUsed", required = true)
    protected NMPhantom phantomUsed;
    @XmlElement(name = "NMStaticProduced", required = true)
    protected DICOMData nmStaticProduced;

    /**
     * Obtient la valeur de la propriété phantomUsed.
     * 
     * @return
     *     possible object is
     *     {@link NMPhantom }
     *     
     */
    public NMPhantom getPhantomUsed() {
        return phantomUsed;
    }

    /**
     * Définit la valeur de la propriété phantomUsed.
     * 
     * @param value
     *     allowed object is
     *     {@link NMPhantom }
     *     
     */
    public void setPhantomUsed(NMPhantom value) {
        this.phantomUsed = value;
    }

    /**
     * Obtient la valeur de la propriété nmStaticProduced.
     * 
     * @return
     *     possible object is
     *     {@link DICOMData }
     *     
     */
    public DICOMData getNMStaticProduced() {
        return nmStaticProduced;
    }

    /**
     * Définit la valeur de la propriété nmStaticProduced.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMData }
     *     
     */
    public void setNMStaticProduced(DICOMData value) {
        this.nmStaticProduced = value;
    }

}
