//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2018.12.11 à 04:47:30 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour VoxelBasedDistributionOfAbsorbedDoseType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="VoxelBasedDistributionOfAbsorbedDoseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VoxelBasedDistributionOfAbsorbedDoseIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VoxelBasedDistributionOfAbsorbedDoseCategory">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="absorbed dose normalized to CTDI free in air normalized to tube load"/>
 *               &lt;enumeration value="absorbed dose normalized to CTDI vol normalized to tube load"/>
 *               &lt;enumeration value="absorbed dose"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="AbsorbedDoseUnit">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="milligray per (milligray per (100 milliampere second))"/>
 *               &lt;enumeration value="milligray per (100 milliampere second)"/>
 *               &lt;enumeration value="milligray"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="NonDICOMVoxelBasedAbsorbedDoseDistribution" type="{https://www.irdbb-medirad.com}NonDICOMData" minOccurs="0"/>
 *         &lt;element name="DICOMVoxelBasedAbsorbedDoseDistribution" type="{https://www.irdbb-medirad.com}DICOMData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VoxelBasedDistributionOfAbsorbedDoseType", propOrder = {
    "voxelBasedDistributionOfAbsorbedDoseIdentifier",
    "voxelBasedDistributionOfAbsorbedDoseCategory",
    "absorbedDoseUnit",
    "nonDICOMVoxelBasedAbsorbedDoseDistribution",
    "dicomVoxelBasedAbsorbedDoseDistribution"
})
public class VoxelBasedDistributionOfAbsorbedDoseType {

    @XmlElement(name = "VoxelBasedDistributionOfAbsorbedDoseIdentifier", required = true)
    protected String voxelBasedDistributionOfAbsorbedDoseIdentifier;
    @XmlElement(name = "VoxelBasedDistributionOfAbsorbedDoseCategory", required = true)
    protected String voxelBasedDistributionOfAbsorbedDoseCategory;
    @XmlElement(name = "AbsorbedDoseUnit", required = true)
    protected String absorbedDoseUnit;
    @XmlElement(name = "NonDICOMVoxelBasedAbsorbedDoseDistribution")
    protected NonDICOMData nonDICOMVoxelBasedAbsorbedDoseDistribution;
    @XmlElement(name = "DICOMVoxelBasedAbsorbedDoseDistribution")
    protected DICOMData dicomVoxelBasedAbsorbedDoseDistribution;

    /**
     * Obtient la valeur de la propriété voxelBasedDistributionOfAbsorbedDoseIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVoxelBasedDistributionOfAbsorbedDoseIdentifier() {
        return voxelBasedDistributionOfAbsorbedDoseIdentifier;
    }

    /**
     * Définit la valeur de la propriété voxelBasedDistributionOfAbsorbedDoseIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVoxelBasedDistributionOfAbsorbedDoseIdentifier(String value) {
        this.voxelBasedDistributionOfAbsorbedDoseIdentifier = value;
    }

    /**
     * Obtient la valeur de la propriété voxelBasedDistributionOfAbsorbedDoseCategory.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVoxelBasedDistributionOfAbsorbedDoseCategory() {
        return voxelBasedDistributionOfAbsorbedDoseCategory;
    }

    /**
     * Définit la valeur de la propriété voxelBasedDistributionOfAbsorbedDoseCategory.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVoxelBasedDistributionOfAbsorbedDoseCategory(String value) {
        this.voxelBasedDistributionOfAbsorbedDoseCategory = value;
    }

    /**
     * Obtient la valeur de la propriété absorbedDoseUnit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbsorbedDoseUnit() {
        return absorbedDoseUnit;
    }

    /**
     * Définit la valeur de la propriété absorbedDoseUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbsorbedDoseUnit(String value) {
        this.absorbedDoseUnit = value;
    }

    /**
     * Obtient la valeur de la propriété nonDICOMVoxelBasedAbsorbedDoseDistribution.
     * 
     * @return
     *     possible object is
     *     {@link NonDICOMData }
     *     
     */
    public NonDICOMData getNonDICOMVoxelBasedAbsorbedDoseDistribution() {
        return nonDICOMVoxelBasedAbsorbedDoseDistribution;
    }

    /**
     * Définit la valeur de la propriété nonDICOMVoxelBasedAbsorbedDoseDistribution.
     * 
     * @param value
     *     allowed object is
     *     {@link NonDICOMData }
     *     
     */
    public void setNonDICOMVoxelBasedAbsorbedDoseDistribution(NonDICOMData value) {
        this.nonDICOMVoxelBasedAbsorbedDoseDistribution = value;
    }

    /**
     * Obtient la valeur de la propriété dicomVoxelBasedAbsorbedDoseDistribution.
     * 
     * @return
     *     possible object is
     *     {@link DICOMData }
     *     
     */
    public DICOMData getDICOMVoxelBasedAbsorbedDoseDistribution() {
        return dicomVoxelBasedAbsorbedDoseDistribution;
    }

    /**
     * Définit la valeur de la propriété dicomVoxelBasedAbsorbedDoseDistribution.
     * 
     * @param value
     *     allowed object is
     *     {@link DICOMData }
     *     
     */
    public void setDICOMVoxelBasedAbsorbedDoseDistribution(DICOMData value) {
        this.dicomVoxelBasedAbsorbedDoseDistribution = value;
    }

}
