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
 * <p>Classe Java pour SegmentationMethodType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SegmentationMethodType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentationMethod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SoftwareName" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SegmentationMethodSetting" type="{https://www.irdbb-medirad.com}MethodSettingType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SegmentationMethodType", propOrder = {
    "segmentationMethod",
    "softwareName",
    "segmentationMethodSetting"
})
public class SegmentationMethodType {

    @XmlElement(name = "SegmentationMethod", required = true)
    protected String segmentationMethod;
    @XmlElement(name = "SoftwareName")
    protected List<String> softwareName;
    @XmlElement(name = "SegmentationMethodSetting")
    protected List<MethodSettingType> segmentationMethodSetting;

    /**
     * Obtient la valeur de la propriété segmentationMethod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegmentationMethod() {
        return segmentationMethod;
    }

    /**
     * Définit la valeur de la propriété segmentationMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegmentationMethod(String value) {
        this.segmentationMethod = value;
    }

    /**
     * Gets the value of the softwareName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the softwareName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSoftwareName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSoftwareName() {
        if (softwareName == null) {
            softwareName = new ArrayList<String>();
        }
        return this.softwareName;
    }

    /**
     * Gets the value of the segmentationMethodSetting property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the segmentationMethodSetting property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSegmentationMethodSetting().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MethodSettingType }
     * 
     * 
     */
    public List<MethodSettingType> getSegmentationMethodSetting() {
        if (segmentationMethodSetting == null) {
            segmentationMethodSetting = new ArrayList<MethodSettingType>();
        }
        return this.segmentationMethodSetting;
    }

}
