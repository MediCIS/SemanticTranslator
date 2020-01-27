//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.01.24 à 06:05:31 PM CET 
//


package javaXSDclass;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour VOIInCT complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="VOIInCT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VOIIdentifier" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="VOIVolumeValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="VOIVolumeUnit" type="{https://www.irdbb-medirad.com}VOIVolumeUnit"/>
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
@XmlType(name = "VOIInCT", propOrder = {
    "voiIdentifier",
    "voiVolumeValue",
    "voiVolumeUnit",
    "phantomPartIdentifier",
    "dicomvoiContainer",
    "nonDICOMVOIContainer"
})
public class VOIInCT {

    @XmlElement(name = "VOIIdentifier", required = true)
    protected BigInteger voiIdentifier;
    @XmlElement(name = "VOIVolumeValue")
    protected float voiVolumeValue;
    @XmlElement(name = "VOIVolumeUnit", required = true)
    @XmlSchemaType(name = "string")
    protected VOIVolumeUnit voiVolumeUnit;
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
     *     {@link BigInteger }
     *     
     */
    public BigInteger getVOIIdentifier() {
        return voiIdentifier;
    }

    /**
     * Définit la valeur de la propriété voiIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setVOIIdentifier(BigInteger value) {
        this.voiIdentifier = value;
    }

    /**
     * Obtient la valeur de la propriété voiVolumeValue.
     * 
     */
    public float getVOIVolumeValue() {
        return voiVolumeValue;
    }

    /**
     * Définit la valeur de la propriété voiVolumeValue.
     * 
     */
    public void setVOIVolumeValue(float value) {
        this.voiVolumeValue = value;
    }

    /**
     * Obtient la valeur de la propriété voiVolumeUnit.
     * 
     * @return
     *     possible object is
     *     {@link VOIVolumeUnit }
     *     
     */
    public VOIVolumeUnit getVOIVolumeUnit() {
        return voiVolumeUnit;
    }

    /**
     * Définit la valeur de la propriété voiVolumeUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link VOIVolumeUnit }
     *     
     */
    public void setVOIVolumeUnit(VOIVolumeUnit value) {
        this.voiVolumeUnit = value;
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
