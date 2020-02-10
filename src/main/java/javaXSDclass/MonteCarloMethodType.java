//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.06 à 05:03:32 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour MonteCarloMethodType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="MonteCarloMethodType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MonteCarloMethod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SoftwareNames" type="{https://www.irdbb-medirad.com}SoftwareNameContainer" minOccurs="0"/>
 *         &lt;element name="MonteCarloMethodSetting" type="{https://www.irdbb-medirad.com}MethodSettingTypeContainer" minOccurs="0"/>
 *         &lt;element name="SimulatedImagingDevices" type="{https://www.irdbb-medirad.com}SimulatedImagingDevicesContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MonteCarloMethodType", propOrder = {
    "monteCarloMethod",
    "softwareNames",
    "monteCarloMethodSetting",
    "simulatedImagingDevices"
})
public class MonteCarloMethodType {

    @XmlElement(name = "MonteCarloMethod", required = true)
    protected String monteCarloMethod;
    @XmlElement(name = "SoftwareNames")
    protected SoftwareNameContainer softwareNames;
    @XmlElement(name = "MonteCarloMethodSetting")
    protected MethodSettingTypeContainer monteCarloMethodSetting;
    @XmlElement(name = "SimulatedImagingDevices")
    protected SimulatedImagingDevicesContainer simulatedImagingDevices;

    /**
     * Obtient la valeur de la propriété monteCarloMethod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonteCarloMethod() {
        return monteCarloMethod;
    }

    /**
     * Définit la valeur de la propriété monteCarloMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonteCarloMethod(String value) {
        this.monteCarloMethod = value;
    }

    /**
     * Obtient la valeur de la propriété softwareNames.
     * 
     * @return
     *     possible object is
     *     {@link SoftwareNameContainer }
     *     
     */
    public SoftwareNameContainer getSoftwareNames() {
        return softwareNames;
    }

    /**
     * Définit la valeur de la propriété softwareNames.
     * 
     * @param value
     *     allowed object is
     *     {@link SoftwareNameContainer }
     *     
     */
    public void setSoftwareNames(SoftwareNameContainer value) {
        this.softwareNames = value;
    }

    /**
     * Obtient la valeur de la propriété monteCarloMethodSetting.
     * 
     * @return
     *     possible object is
     *     {@link MethodSettingTypeContainer }
     *     
     */
    public MethodSettingTypeContainer getMonteCarloMethodSetting() {
        return monteCarloMethodSetting;
    }

    /**
     * Définit la valeur de la propriété monteCarloMethodSetting.
     * 
     * @param value
     *     allowed object is
     *     {@link MethodSettingTypeContainer }
     *     
     */
    public void setMonteCarloMethodSetting(MethodSettingTypeContainer value) {
        this.monteCarloMethodSetting = value;
    }

    /**
     * Obtient la valeur de la propriété simulatedImagingDevices.
     * 
     * @return
     *     possible object is
     *     {@link SimulatedImagingDevicesContainer }
     *     
     */
    public SimulatedImagingDevicesContainer getSimulatedImagingDevices() {
        return simulatedImagingDevices;
    }

    /**
     * Définit la valeur de la propriété simulatedImagingDevices.
     * 
     * @param value
     *     allowed object is
     *     {@link SimulatedImagingDevicesContainer }
     *     
     */
    public void setSimulatedImagingDevices(SimulatedImagingDevicesContainer value) {
        this.simulatedImagingDevices = value;
    }

}
