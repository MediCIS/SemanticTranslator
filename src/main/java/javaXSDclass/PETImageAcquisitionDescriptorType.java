//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.01.24 à 06:05:30 PM CET 
//


package javaXSDclass;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour PETImageAcquisitionDescriptorType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PETImageAcquisitionDescriptorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FrameOfReferenceUID-00200052" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImageType-00080008" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AcquisitionDate-00080022" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AcquisitionTime-00080032" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EnergyWindowInformationSequence-00540012" type="{https://www.irdbb-medirad.com}EnergyWindowType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="RadiopharmaceuticalInformationSequence-00540016" type="{https://www.irdbb-medirad.com}RadiopharmaceuticalType" minOccurs="0"/>
 *         &lt;element name="ReconstructionMethod-00541103" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PETImageAcquisitionDescriptorType", propOrder = {
    "frameOfReferenceUID00200052",
    "imageType00080008",
    "acquisitionDate00080022",
    "acquisitionTime00080032",
    "energyWindowInformationSequence00540012",
    "radiopharmaceuticalInformationSequence00540016",
    "reconstructionMethod00541103"
})
public class PETImageAcquisitionDescriptorType {

    @XmlElement(name = "FrameOfReferenceUID-00200052")
    protected String frameOfReferenceUID00200052;
    @XmlElement(name = "ImageType-00080008")
    protected String imageType00080008;
    @XmlElement(name = "AcquisitionDate-00080022")
    protected String acquisitionDate00080022;
    @XmlElement(name = "AcquisitionTime-00080032")
    protected String acquisitionTime00080032;
    @XmlElement(name = "EnergyWindowInformationSequence-00540012")
    protected List<EnergyWindowType> energyWindowInformationSequence00540012;
    @XmlElement(name = "RadiopharmaceuticalInformationSequence-00540016")
    protected RadiopharmaceuticalType radiopharmaceuticalInformationSequence00540016;
    @XmlElement(name = "ReconstructionMethod-00541103")
    protected String reconstructionMethod00541103;

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
     * Gets the value of the energyWindowInformationSequence00540012 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the energyWindowInformationSequence00540012 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEnergyWindowInformationSequence00540012().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EnergyWindowType }
     * 
     * 
     */
    public List<EnergyWindowType> getEnergyWindowInformationSequence00540012() {
        if (energyWindowInformationSequence00540012 == null) {
            energyWindowInformationSequence00540012 = new ArrayList<EnergyWindowType>();
        }
        return this.energyWindowInformationSequence00540012;
    }

    /**
     * Obtient la valeur de la propriété radiopharmaceuticalInformationSequence00540016.
     * 
     * @return
     *     possible object is
     *     {@link RadiopharmaceuticalType }
     *     
     */
    public RadiopharmaceuticalType getRadiopharmaceuticalInformationSequence00540016() {
        return radiopharmaceuticalInformationSequence00540016;
    }

    /**
     * Définit la valeur de la propriété radiopharmaceuticalInformationSequence00540016.
     * 
     * @param value
     *     allowed object is
     *     {@link RadiopharmaceuticalType }
     *     
     */
    public void setRadiopharmaceuticalInformationSequence00540016(RadiopharmaceuticalType value) {
        this.radiopharmaceuticalInformationSequence00540016 = value;
    }

    /**
     * Obtient la valeur de la propriété reconstructionMethod00541103.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReconstructionMethod00541103() {
        return reconstructionMethod00541103;
    }

    /**
     * Définit la valeur de la propriété reconstructionMethod00541103.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReconstructionMethod00541103(String value) {
        this.reconstructionMethod00541103 = value;
    }

}
