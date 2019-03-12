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
 * <p>Classe Java pour VOI complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="VOI">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VOIIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OrganOrTissue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DICOMVOIContainer" type="{https://www.irdbb-medirad.com}DICOMData" minOccurs="0"/>
 *         &lt;element name="NonDICOMVOIContainer" type="{https://www.irdbb-medirad.com}NonDICOMData" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VOI", propOrder = {
    "voiIdentifier",
    "organOrTissue",
    "dicomvoiContainer",
    "nonDICOMVOIContainer"
})
public class VOI {

    @XmlElement(name = "VOIIdentifier", required = true)
    protected String voiIdentifier;
    @XmlElement(name = "OrganOrTissue", required = true)
    protected String organOrTissue;
    @XmlElement(name = "DICOMVOIContainer")
    protected DICOMData dicomvoiContainer;
    @XmlElement(name = "NonDICOMVOIContainer")
    protected List<NonDICOMData> nonDICOMVOIContainer;

    /**
     * Obtient la valeur de la propriété voiIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVOIIdentifier() {
        return voiIdentifier;
    }

    /**
     * Définit la valeur de la propriété voiIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVOIIdentifier(String value) {
        this.voiIdentifier = value;
    }

    /**
     * Obtient la valeur de la propriété organOrTissue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganOrTissue() {
        return organOrTissue;
    }

    /**
     * Définit la valeur de la propriété organOrTissue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganOrTissue(String value) {
        this.organOrTissue = value;
    }

    /**
     * Obtient la valeur de la propriété dicomvoiContainer.
     * 
     * @return
     *     possible object is
     *     {@link DICOMData }
     *     
     */
    public DICOMData getDICOMVOIContainer() {
        return dicomvoiContainer;
    }

    /**
     * Définit la valeur de la propriété dicomvoiContainer.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMData }
     *     
     */
    public void setDICOMVOIContainer(DICOMData value) {
        this.dicomvoiContainer = value;
    }

    /**
     * Gets the value of the nonDICOMVOIContainer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nonDICOMVOIContainer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNonDICOMVOIContainer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NonDICOMData }
     * 
     * 
     */
    public List<NonDICOMData> getNonDICOMVOIContainer() {
        if (nonDICOMVOIContainer == null) {
            nonDICOMVOIContainer = new ArrayList<NonDICOMData>();
        }
        return this.nonDICOMVOIContainer;
    }

}
