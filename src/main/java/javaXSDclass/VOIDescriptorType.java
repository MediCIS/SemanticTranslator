//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2018.10.16 à 03:58:02 PM CEST 
//


package javaXSDclass;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour VOIDescriptorType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="VOIDescriptorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VOIId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VOILabel">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="bone"/>
 *               &lt;enumeration value="breast"/>
 *               &lt;enumeration value="esophagus"/>
 *               &lt;enumeration value="heart"/>
 *               &lt;enumeration value="lungs"/>
 *               &lt;enumeration value="skin"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="VOIData" type="{https://www.irdbb-medirad.com}NonDICOMDataType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VOIDescriptorType", propOrder = {
    "voiId",
    "voiLabel",
    "voiData"
})
public class VOIDescriptorType {

    @XmlElement(name = "VOIId", required = true)
    protected String voiId;
    @XmlElement(name = "VOILabel", required = true)
    protected String voiLabel;
    @XmlElement(name = "VOIData")
    protected List<NonDICOMDataType> voiData;

    /**
     * Obtient la valeur de la propriété voiId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVOIId() {
        return voiId;
    }

    /**
     * Définit la valeur de la propriété voiId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVOIId(String value) {
        this.voiId = value;
    }

    /**
     * Obtient la valeur de la propriété voiLabel.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVOILabel() {
        return voiLabel;
    }

    /**
     * Définit la valeur de la propriété voiLabel.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVOILabel(String value) {
        this.voiLabel = value;
    }

    /**
     * Gets the value of the voiData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the voiData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVOIData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NonDICOMDataType }
     * 
     * 
     */
    public List<NonDICOMDataType> getVOIData() {
        if (voiData == null) {
            voiData = new ArrayList<NonDICOMDataType>();
        }
        return this.voiData;
    }

}
