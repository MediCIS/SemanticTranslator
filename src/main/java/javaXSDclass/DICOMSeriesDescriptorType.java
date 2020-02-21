//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.12 à 04:30:41 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour DICOMSeriesDescriptorType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DICOMSeriesDescriptorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SeriesNumber-00200011" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SeriesInstanceUID-0020000e" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SeriesDate-00080021" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SeriesTime-00080031" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Modality-00080060" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Manufacturer-00080070" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ManufacturersModelName-00081090" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="InstitutionName-00080080" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ProtocolName-00181030" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SeriesDescription-0008103e" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DICOMSeriesDescriptorType", propOrder = {
    "seriesNumber00200011",
    "seriesInstanceUID0020000E",
    "seriesDate00080021",
    "seriesTime00080031",
    "modality00080060",
    "manufacturer00080070",
    "manufacturersModelName00081090",
    "institutionName00080080",
    "protocolName00181030",
    "seriesDescription0008103E"
})
public class DICOMSeriesDescriptorType {

    @XmlElement(name = "SeriesNumber-00200011", required = true)
    protected String seriesNumber00200011;
    @XmlElement(name = "SeriesInstanceUID-0020000e", required = true)
    protected String seriesInstanceUID0020000E;
    @XmlElement(name = "SeriesDate-00080021", required = true)
    protected String seriesDate00080021;
    @XmlElement(name = "SeriesTime-00080031", required = true)
    protected String seriesTime00080031;
    @XmlElement(name = "Modality-00080060", required = true)
    protected String modality00080060;
    @XmlElement(name = "Manufacturer-00080070", required = true)
    protected String manufacturer00080070;
    @XmlElement(name = "ManufacturersModelName-00081090", required = true)
    protected String manufacturersModelName00081090;
    @XmlElement(name = "InstitutionName-00080080", required = true)
    protected String institutionName00080080;
    @XmlElement(name = "ProtocolName-00181030", required = true)
    protected String protocolName00181030;
    @XmlElement(name = "SeriesDescription-0008103e", required = true)
    protected String seriesDescription0008103E;

    /**
     * Obtient la valeur de la propriété seriesNumber00200011.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeriesNumber00200011() {
        return seriesNumber00200011;
    }

    /**
     * Définit la valeur de la propriété seriesNumber00200011.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeriesNumber00200011(String value) {
        this.seriesNumber00200011 = value;
    }

    /**
     * Obtient la valeur de la propriété seriesInstanceUID0020000E.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeriesInstanceUID0020000E() {
        return seriesInstanceUID0020000E;
    }

    /**
     * Définit la valeur de la propriété seriesInstanceUID0020000E.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeriesInstanceUID0020000E(String value) {
        this.seriesInstanceUID0020000E = value;
    }

    /**
     * Obtient la valeur de la propriété seriesDate00080021.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeriesDate00080021() {
        return seriesDate00080021;
    }

    /**
     * Définit la valeur de la propriété seriesDate00080021.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeriesDate00080021(String value) {
        this.seriesDate00080021 = value;
    }

    /**
     * Obtient la valeur de la propriété seriesTime00080031.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeriesTime00080031() {
        return seriesTime00080031;
    }

    /**
     * Définit la valeur de la propriété seriesTime00080031.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeriesTime00080031(String value) {
        this.seriesTime00080031 = value;
    }

    /**
     * Obtient la valeur de la propriété modality00080060.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModality00080060() {
        return modality00080060;
    }

    /**
     * Définit la valeur de la propriété modality00080060.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModality00080060(String value) {
        this.modality00080060 = value;
    }

    /**
     * Obtient la valeur de la propriété manufacturer00080070.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManufacturer00080070() {
        return manufacturer00080070;
    }

    /**
     * Définit la valeur de la propriété manufacturer00080070.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManufacturer00080070(String value) {
        this.manufacturer00080070 = value;
    }

    /**
     * Obtient la valeur de la propriété manufacturersModelName00081090.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManufacturersModelName00081090() {
        return manufacturersModelName00081090;
    }

    /**
     * Définit la valeur de la propriété manufacturersModelName00081090.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManufacturersModelName00081090(String value) {
        this.manufacturersModelName00081090 = value;
    }

    /**
     * Obtient la valeur de la propriété institutionName00080080.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstitutionName00080080() {
        return institutionName00080080;
    }

    /**
     * Définit la valeur de la propriété institutionName00080080.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstitutionName00080080(String value) {
        this.institutionName00080080 = value;
    }

    /**
     * Obtient la valeur de la propriété protocolName00181030.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProtocolName00181030() {
        return protocolName00181030;
    }

    /**
     * Définit la valeur de la propriété protocolName00181030.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProtocolName00181030(String value) {
        this.protocolName00181030 = value;
    }

    /**
     * Obtient la valeur de la propriété seriesDescription0008103E.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeriesDescription0008103E() {
        return seriesDescription0008103E;
    }

    /**
     * Définit la valeur de la propriété seriesDescription0008103E.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeriesDescription0008103E(String value) {
        this.seriesDescription0008103E = value;
    }

}
