//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.06 à 05:03:31 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour PatientDescriptorType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PatientDescriptorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PatientID-00100020" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PatientBirthDate-00100030" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PatientSex-00100040" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PatientAge-00101010" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PatientSize-00101020" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PatientWeight-00101030" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PatientDescriptorType", propOrder = {
    "patientID00100020",
    "patientBirthDate00100030",
    "patientSex00100040",
    "patientAge00101010",
    "patientSize00101020",
    "patientWeight00101030"
})
public class PatientDescriptorType {

    @XmlElement(name = "PatientID-00100020", required = true)
    protected String patientID00100020;
    @XmlElement(name = "PatientBirthDate-00100030", required = true)
    protected String patientBirthDate00100030;
    @XmlElement(name = "PatientSex-00100040", required = true)
    protected String patientSex00100040;
    @XmlElement(name = "PatientAge-00101010", required = true)
    protected String patientAge00101010;
    @XmlElement(name = "PatientSize-00101020", required = true)
    protected String patientSize00101020;
    @XmlElement(name = "PatientWeight-00101030", required = true)
    protected String patientWeight00101030;

    /**
     * Obtient la valeur de la propriété patientID00100020.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientID00100020() {
        return patientID00100020;
    }

    /**
     * Définit la valeur de la propriété patientID00100020.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientID00100020(String value) {
        this.patientID00100020 = value;
    }

    /**
     * Obtient la valeur de la propriété patientBirthDate00100030.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientBirthDate00100030() {
        return patientBirthDate00100030;
    }

    /**
     * Définit la valeur de la propriété patientBirthDate00100030.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientBirthDate00100030(String value) {
        this.patientBirthDate00100030 = value;
    }

    /**
     * Obtient la valeur de la propriété patientSex00100040.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientSex00100040() {
        return patientSex00100040;
    }

    /**
     * Définit la valeur de la propriété patientSex00100040.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientSex00100040(String value) {
        this.patientSex00100040 = value;
    }

    /**
     * Obtient la valeur de la propriété patientAge00101010.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientAge00101010() {
        return patientAge00101010;
    }

    /**
     * Définit la valeur de la propriété patientAge00101010.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientAge00101010(String value) {
        this.patientAge00101010 = value;
    }

    /**
     * Obtient la valeur de la propriété patientSize00101020.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientSize00101020() {
        return patientSize00101020;
    }

    /**
     * Définit la valeur de la propriété patientSize00101020.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientSize00101020(String value) {
        this.patientSize00101020 = value;
    }

    /**
     * Obtient la valeur de la propriété patientWeight00101030.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientWeight00101030() {
        return patientWeight00101030;
    }

    /**
     * Définit la valeur de la propriété patientWeight00101030.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientWeight00101030(String value) {
        this.patientWeight00101030 = value;
    }

}
