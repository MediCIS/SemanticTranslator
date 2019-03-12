//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2018.12.11 à 04:47:30 PM CET 
//


package javaXSDclass;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour ROIPlanarActivityDetermination complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ROIPlanarActivityDetermination">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessExecutionContext" type="{https://www.irdbb-medirad.com}ProcessExecutionContext"/>
 *         &lt;element name="TimePointIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="ROIIdentifierUsed" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="PlanarCalibrationFactorUsed" type="{https://www.irdbb-medirad.com}PlanarCalibrationFactor" maxOccurs="unbounded"/>
 *         &lt;element name="DataActivityPerROIAtTimePointProduced" type="{https://www.irdbb-medirad.com}DataActivityPerROIAtTimePoint" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ROIPlanarActivityDetermination", propOrder = {
    "processExecutionContext",
    "timePointIdentifierUsed",
    "roiIdentifierUsed",
    "planarCalibrationFactorUsed",
    "dataActivityPerROIAtTimePointProduced"
})
public class ROIPlanarActivityDetermination {

    @XmlElement(name = "ProcessExecutionContext", required = true)
    protected ProcessExecutionContext processExecutionContext;
    @XmlElement(name = "TimePointIdentifierUsed", required = true)
    protected List<String> timePointIdentifierUsed;
    @XmlElement(name = "ROIIdentifierUsed", required = true)
    protected List<String> roiIdentifierUsed;
    @XmlElement(name = "PlanarCalibrationFactorUsed", required = true)
    protected List<PlanarCalibrationFactor> planarCalibrationFactorUsed;
    @XmlElement(name = "DataActivityPerROIAtTimePointProduced", required = true)
    protected List<DataActivityPerROIAtTimePoint> dataActivityPerROIAtTimePointProduced;

    /**
     * Obtient la valeur de la propriété processExecutionContext.
     * 
     * @return
     *     possible object is
     *     {@link ProcessExecutionContext }
     *     
     */
    public ProcessExecutionContext getProcessExecutionContext() {
        return processExecutionContext;
    }

    /**
     * Définit la valeur de la propriété processExecutionContext.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcessExecutionContext }
     *     
     */
    public void setProcessExecutionContext(ProcessExecutionContext value) {
        this.processExecutionContext = value;
    }

    /**
     * Gets the value of the timePointIdentifierUsed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the timePointIdentifierUsed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTimePointIdentifierUsed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getTimePointIdentifierUsed() {
        if (timePointIdentifierUsed == null) {
            timePointIdentifierUsed = new ArrayList<String>();
        }
        return this.timePointIdentifierUsed;
    }

    /**
     * Gets the value of the roiIdentifierUsed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roiIdentifierUsed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getROIIdentifierUsed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getROIIdentifierUsed() {
        if (roiIdentifierUsed == null) {
            roiIdentifierUsed = new ArrayList<String>();
        }
        return this.roiIdentifierUsed;
    }

    /**
     * Gets the value of the planarCalibrationFactorUsed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the planarCalibrationFactorUsed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlanarCalibrationFactorUsed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlanarCalibrationFactor }
     * 
     * 
     */
    public List<PlanarCalibrationFactor> getPlanarCalibrationFactorUsed() {
        if (planarCalibrationFactorUsed == null) {
            planarCalibrationFactorUsed = new ArrayList<PlanarCalibrationFactor>();
        }
        return this.planarCalibrationFactorUsed;
    }

    /**
     * Gets the value of the dataActivityPerROIAtTimePointProduced property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataActivityPerROIAtTimePointProduced property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataActivityPerROIAtTimePointProduced().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataActivityPerROIAtTimePoint }
     * 
     * 
     */
    public List<DataActivityPerROIAtTimePoint> getDataActivityPerROIAtTimePointProduced() {
        if (dataActivityPerROIAtTimePointProduced == null) {
            dataActivityPerROIAtTimePointProduced = new ArrayList<DataActivityPerROIAtTimePoint>();
        }
        return this.dataActivityPerROIAtTimePointProduced;
    }

}
