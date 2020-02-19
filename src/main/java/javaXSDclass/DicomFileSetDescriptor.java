//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.12 à 04:30:41 PM CET 
//


package javaXSDclass;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ReferencedClinicalStudy" type="{https://www.irdbb-medirad.com}ClinicalStudyType"/>
 *         &lt;element name="PatientDescriptor" type="{https://www.irdbb-medirad.com}PatientDescriptorType"/>
 *         &lt;element name="DICOMStudy" type="{https://www.irdbb-medirad.com}DICOMStudyType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "referencedClinicalStudy",
    "patientDescriptor",
    "dicomStudy"
})
@XmlRootElement(name = "DicomFileSetDescriptor")
public class DicomFileSetDescriptor {

    @XmlElement(name = "ReferencedClinicalStudy", required = true)
    protected ClinicalStudyType referencedClinicalStudy;
    @XmlElement(name = "PatientDescriptor", required = true)
    protected PatientDescriptorType patientDescriptor;
    @XmlElement(name = "DICOMStudy", required = true)
    protected List<DICOMStudyType> dicomStudy;

    /**
     * Obtient la valeur de la propriété referencedClinicalStudy.
     * 
     * @return
     *     possible object is
     *     {@link ClinicalStudyType }
     *     
     */
    public ClinicalStudyType getReferencedClinicalStudy() {
        return referencedClinicalStudy;
    }

    /**
     * Définit la valeur de la propriété referencedClinicalStudy.
     * 
     * @param value
     *     allowed object is
     *     {@link ClinicalStudyType }
     *     
     */
    public void setReferencedClinicalStudy(ClinicalStudyType value) {
        this.referencedClinicalStudy = value;
    }

    /**
     * Obtient la valeur de la propriété patientDescriptor.
     * 
     * @return
     *     possible object is
     *     {@link PatientDescriptorType }
     *     
     */
    public PatientDescriptorType getPatientDescriptor() {
        return patientDescriptor;
    }

    /**
     * Définit la valeur de la propriété patientDescriptor.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientDescriptorType }
     *     
     */
    public void setPatientDescriptor(PatientDescriptorType value) {
        this.patientDescriptor = value;
    }

    /**
     * Gets the value of the dicomStudy property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dicomStudy property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDICOMStudy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DICOMStudyType }
     * 
     * 
     */
    public List<DICOMStudyType> getDICOMStudy() {
        if (dicomStudy == null) {
            dicomStudy = new ArrayList<DICOMStudyType>();
        }
        return this.dicomStudy;
    }

}
