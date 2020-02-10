//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.06 à 05:03:32 PM CET 
//


package javaXSDclass;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="VOIIdentifier" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="OrganOrTissue" type="{https://www.irdbb-medirad.com}OrganOrTissue"/>
 *         &lt;element name="DICOMDataContainer" type="{https://www.irdbb-medirad.com}DICOMDataContainer" minOccurs="0"/>
 *         &lt;element name="NonDICOMDataContainer" type="{https://www.irdbb-medirad.com}NonDICOMDataContainer" minOccurs="0"/>
 *         &lt;element name="TimePointIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrganMass" type="{https://www.irdbb-medirad.com}OrganMass" minOccurs="0"/>
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
    "dicomDataContainer",
    "nonDICOMDataContainer",
    "timePointIdentifier",
    "organMass"
})
public class VOI {

    @XmlElement(name = "VOIIdentifier", required = true)
    protected BigInteger voiIdentifier;
    @XmlElement(name = "OrganOrTissue", required = true)
    @XmlSchemaType(name = "string")
    protected OrganOrTissue organOrTissue;
    @XmlElement(name = "DICOMDataContainer")
    protected DICOMDataContainer dicomDataContainer;
    @XmlElement(name = "NonDICOMDataContainer")
    protected NonDICOMDataContainer nonDICOMDataContainer;
    @XmlElement(name = "TimePointIdentifier")
    protected String timePointIdentifier;
    @XmlElement(name = "OrganMass")
    protected OrganMass organMass;

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
     * Obtient la valeur de la propriété organOrTissue.
     * 
     * @return
     *     possible object is
     *     {@link OrganOrTissue }
     *     
     */
    public OrganOrTissue getOrganOrTissue() {
        return organOrTissue;
    }

    /**
     * Définit la valeur de la propriété organOrTissue.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganOrTissue }
     *     
     */
    public void setOrganOrTissue(OrganOrTissue value) {
        this.organOrTissue = value;
    }

    /**
     * Obtient la valeur de la propriété dicomDataContainer.
     * 
     * @return
     *     possible object is
     *     {@link DICOMDataContainer }
     *     
     */
    public DICOMDataContainer getDICOMDataContainer() {
        return dicomDataContainer;
    }

    /**
     * Définit la valeur de la propriété dicomDataContainer.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMDataContainer }
     *     
     */
    public void setDICOMDataContainer(DICOMDataContainer value) {
        this.dicomDataContainer = value;
    }

    /**
     * Obtient la valeur de la propriété nonDICOMDataContainer.
     * 
     * @return
     *     possible object is
     *     {@link NonDICOMDataContainer }
     *     
     */
    public NonDICOMDataContainer getNonDICOMDataContainer() {
        return nonDICOMDataContainer;
    }

    /**
     * Définit la valeur de la propriété nonDICOMDataContainer.
     * 
     * @param value
     *     allowed object is
     *     {@link NonDICOMDataContainer }
     *     
     */
    public void setNonDICOMDataContainer(NonDICOMDataContainer value) {
        this.nonDICOMDataContainer = value;
    }

    /**
     * Obtient la valeur de la propriété timePointIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimePointIdentifier() {
        return timePointIdentifier;
    }

    /**
     * Définit la valeur de la propriété timePointIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimePointIdentifier(String value) {
        this.timePointIdentifier = value;
    }

    /**
     * Obtient la valeur de la propriété organMass.
     * 
     * @return
     *     possible object is
     *     {@link OrganMass }
     *     
     */
    public OrganMass getOrganMass() {
        return organMass;
    }

    /**
     * Définit la valeur de la propriété organMass.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganMass }
     *     
     */
    public void setOrganMass(OrganMass value) {
        this.organMass = value;
    }

}
