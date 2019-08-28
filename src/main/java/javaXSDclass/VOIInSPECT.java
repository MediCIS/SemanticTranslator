//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.06.25 à 10:38:25 AM CEST 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour VOIInSPECT complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="VOIInSPECT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VOIIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CountsInVOIValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="CountsInVOIUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PhantomPartIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DICOMVOIContainer" type="{https://www.irdbb-medirad.com}DICOMData" minOccurs="0"/>
 *         &lt;element name="NonDICOMVOIContainer" type="{https://www.irdbb-medirad.com}NonDICOMData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VOIInSPECT", propOrder = {
    "voiIdentifier",
    "countsInVOIValue",
    "countsInVOIUnit",
    "phantomPartIdentifier",
    "dicomvoiContainer",
    "nonDICOMVOIContainer"
})
public class VOIInSPECT {

    @XmlElement(name = "VOIIdentifier", required = true)
    protected String voiIdentifier;
    @XmlElement(name = "CountsInVOIValue")
    protected float countsInVOIValue;
    @XmlElement(name = "CountsInVOIUnit", required = true)
    protected String countsInVOIUnit;
    @XmlElement(name = "PhantomPartIdentifier", required = true)
    protected String phantomPartIdentifier;
    @XmlElement(name = "DICOMVOIContainer")
    protected DICOMData dicomvoiContainer;
    @XmlElement(name = "NonDICOMVOIContainer")
    protected NonDICOMData nonDICOMVOIContainer;

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
     * Obtient la valeur de la propriété countsInVOIValue.
     * 
     */
    public float getCountsInVOIValue() {
        return countsInVOIValue;
    }

    /**
     * Définit la valeur de la propriété countsInVOIValue.
     * 
     */
    public void setCountsInVOIValue(float value) {
        this.countsInVOIValue = value;
    }

    /**
     * Obtient la valeur de la propriété countsInVOIUnit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountsInVOIUnit() {
        return countsInVOIUnit;
    }

    /**
     * Définit la valeur de la propriété countsInVOIUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountsInVOIUnit(String value) {
        this.countsInVOIUnit = value;
    }

    /**
     * Obtient la valeur de la propriété phantomPartIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhantomPartIdentifier() {
        return phantomPartIdentifier;
    }

    /**
     * Définit la valeur de la propriété phantomPartIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhantomPartIdentifier(String value) {
        this.phantomPartIdentifier = value;
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
     * Obtient la valeur de la propriété nonDICOMVOIContainer.
     * 
     * @return
     *     possible object is
     *     {@link NonDICOMData }
     *     
     */
    public NonDICOMData getNonDICOMVOIContainer() {
        return nonDICOMVOIContainer;
    }

    /**
     * Définit la valeur de la propriété nonDICOMVOIContainer.
     * 
     * @param value
     *     allowed object is
     *     {@link NonDICOMData }
     *     
     */
    public void setNonDICOMVOIContainer(NonDICOMData value) {
        this.nonDICOMVOIContainer = value;
    }

}
