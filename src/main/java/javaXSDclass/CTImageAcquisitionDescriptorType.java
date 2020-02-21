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
 * <p>Classe Java pour CTImageAcquisitionDescriptorType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CTImageAcquisitionDescriptorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FrameOfReferenceUID-00200052" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ImageType-00080008" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AcquisitionDate-00080022" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AcquisitionTime-00080032" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AcquisitionType-00189302" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RevolutionTime-00189305" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalCollimationWidth-00189307" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GantryDetectorTilt-00181120" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TableFeedPerRotation-00189310" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SpiralPitchFactor-00189311" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="KVP-00180060" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExposureTime-00181150" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="XRayTubeCurrent-00181151" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExposureInMicroAs-00181153" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FilterType-00181160" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FocalSpots-00181190" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExposureModulationType-00189323" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CTImageAcquisitionDescriptorType", propOrder = {
    "frameOfReferenceUID00200052",
    "imageType00080008",
    "acquisitionDate00080022",
    "acquisitionTime00080032",
    "acquisitionType00189302",
    "revolutionTime00189305",
    "totalCollimationWidth00189307",
    "gantryDetectorTilt00181120",
    "tableFeedPerRotation00189310",
    "spiralPitchFactor00189311",
    "kvp00180060",
    "exposureTime00181150",
    "xRayTubeCurrent00181151",
    "exposureInMicroAs00181153",
    "filterType00181160",
    "focalSpots00181190",
    "exposureModulationType00189323"
})
public class CTImageAcquisitionDescriptorType {

    @XmlElement(name = "FrameOfReferenceUID-00200052", required = true)
    protected String frameOfReferenceUID00200052;
    @XmlElement(name = "ImageType-00080008")
    protected String imageType00080008;
    @XmlElement(name = "AcquisitionDate-00080022")
    protected String acquisitionDate00080022;
    @XmlElement(name = "AcquisitionTime-00080032")
    protected String acquisitionTime00080032;
    @XmlElement(name = "AcquisitionType-00189302")
    protected String acquisitionType00189302;
    @XmlElement(name = "RevolutionTime-00189305")
    protected String revolutionTime00189305;
    @XmlElement(name = "TotalCollimationWidth-00189307")
    protected String totalCollimationWidth00189307;
    @XmlElement(name = "GantryDetectorTilt-00181120")
    protected String gantryDetectorTilt00181120;
    @XmlElement(name = "TableFeedPerRotation-00189310")
    protected String tableFeedPerRotation00189310;
    @XmlElement(name = "SpiralPitchFactor-00189311")
    protected String spiralPitchFactor00189311;
    @XmlElement(name = "KVP-00180060")
    protected String kvp00180060;
    @XmlElement(name = "ExposureTime-00181150")
    protected String exposureTime00181150;
    @XmlElement(name = "XRayTubeCurrent-00181151")
    protected String xRayTubeCurrent00181151;
    @XmlElement(name = "ExposureInMicroAs-00181153")
    protected String exposureInMicroAs00181153;
    @XmlElement(name = "FilterType-00181160")
    protected String filterType00181160;
    @XmlElement(name = "FocalSpots-00181190")
    protected String focalSpots00181190;
    @XmlElement(name = "ExposureModulationType-00189323")
    protected String exposureModulationType00189323;

    /**
     * Obtient la valeur de la propriété frameOfReferenceUID00200052.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrameOfReferenceUID00200052() {
        return frameOfReferenceUID00200052;
    }

    /**
     * Définit la valeur de la propriété frameOfReferenceUID00200052.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrameOfReferenceUID00200052(String value) {
        this.frameOfReferenceUID00200052 = value;
    }

    /**
     * Obtient la valeur de la propriété imageType00080008.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImageType00080008() {
        return imageType00080008;
    }

    /**
     * Définit la valeur de la propriété imageType00080008.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImageType00080008(String value) {
        this.imageType00080008 = value;
    }

    /**
     * Obtient la valeur de la propriété acquisitionDate00080022.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcquisitionDate00080022() {
        return acquisitionDate00080022;
    }

    /**
     * Définit la valeur de la propriété acquisitionDate00080022.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcquisitionDate00080022(String value) {
        this.acquisitionDate00080022 = value;
    }

    /**
     * Obtient la valeur de la propriété acquisitionTime00080032.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcquisitionTime00080032() {
        return acquisitionTime00080032;
    }

    /**
     * Définit la valeur de la propriété acquisitionTime00080032.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcquisitionTime00080032(String value) {
        this.acquisitionTime00080032 = value;
    }

    /**
     * Obtient la valeur de la propriété acquisitionType00189302.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcquisitionType00189302() {
        return acquisitionType00189302;
    }

    /**
     * Définit la valeur de la propriété acquisitionType00189302.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcquisitionType00189302(String value) {
        this.acquisitionType00189302 = value;
    }

    /**
     * Obtient la valeur de la propriété revolutionTime00189305.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRevolutionTime00189305() {
        return revolutionTime00189305;
    }

    /**
     * Définit la valeur de la propriété revolutionTime00189305.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRevolutionTime00189305(String value) {
        this.revolutionTime00189305 = value;
    }

    /**
     * Obtient la valeur de la propriété totalCollimationWidth00189307.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalCollimationWidth00189307() {
        return totalCollimationWidth00189307;
    }

    /**
     * Définit la valeur de la propriété totalCollimationWidth00189307.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalCollimationWidth00189307(String value) {
        this.totalCollimationWidth00189307 = value;
    }

    /**
     * Obtient la valeur de la propriété gantryDetectorTilt00181120.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGantryDetectorTilt00181120() {
        return gantryDetectorTilt00181120;
    }

    /**
     * Définit la valeur de la propriété gantryDetectorTilt00181120.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGantryDetectorTilt00181120(String value) {
        this.gantryDetectorTilt00181120 = value;
    }

    /**
     * Obtient la valeur de la propriété tableFeedPerRotation00189310.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTableFeedPerRotation00189310() {
        return tableFeedPerRotation00189310;
    }

    /**
     * Définit la valeur de la propriété tableFeedPerRotation00189310.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTableFeedPerRotation00189310(String value) {
        this.tableFeedPerRotation00189310 = value;
    }

    /**
     * Obtient la valeur de la propriété spiralPitchFactor00189311.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpiralPitchFactor00189311() {
        return spiralPitchFactor00189311;
    }

    /**
     * Définit la valeur de la propriété spiralPitchFactor00189311.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpiralPitchFactor00189311(String value) {
        this.spiralPitchFactor00189311 = value;
    }

    /**
     * Obtient la valeur de la propriété kvp00180060.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKVP00180060() {
        return kvp00180060;
    }

    /**
     * Définit la valeur de la propriété kvp00180060.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKVP00180060(String value) {
        this.kvp00180060 = value;
    }

    /**
     * Obtient la valeur de la propriété exposureTime00181150.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExposureTime00181150() {
        return exposureTime00181150;
    }

    /**
     * Définit la valeur de la propriété exposureTime00181150.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExposureTime00181150(String value) {
        this.exposureTime00181150 = value;
    }

    /**
     * Obtient la valeur de la propriété xRayTubeCurrent00181151.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXRayTubeCurrent00181151() {
        return xRayTubeCurrent00181151;
    }

    /**
     * Définit la valeur de la propriété xRayTubeCurrent00181151.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXRayTubeCurrent00181151(String value) {
        this.xRayTubeCurrent00181151 = value;
    }

    /**
     * Obtient la valeur de la propriété exposureInMicroAs00181153.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExposureInMicroAs00181153() {
        return exposureInMicroAs00181153;
    }

    /**
     * Définit la valeur de la propriété exposureInMicroAs00181153.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExposureInMicroAs00181153(String value) {
        this.exposureInMicroAs00181153 = value;
    }

    /**
     * Obtient la valeur de la propriété filterType00181160.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilterType00181160() {
        return filterType00181160;
    }

    /**
     * Définit la valeur de la propriété filterType00181160.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilterType00181160(String value) {
        this.filterType00181160 = value;
    }

    /**
     * Obtient la valeur de la propriété focalSpots00181190.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFocalSpots00181190() {
        return focalSpots00181190;
    }

    /**
     * Définit la valeur de la propriété focalSpots00181190.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFocalSpots00181190(String value) {
        this.focalSpots00181190 = value;
    }

    /**
     * Obtient la valeur de la propriété exposureModulationType00189323.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExposureModulationType00189323() {
        return exposureModulationType00189323;
    }

    /**
     * Définit la valeur de la propriété exposureModulationType00189323.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExposureModulationType00189323(String value) {
        this.exposureModulationType00189323 = value;
    }

}
