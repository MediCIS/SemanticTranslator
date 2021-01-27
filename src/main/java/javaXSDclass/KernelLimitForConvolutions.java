//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.07.30 à 02:06:37 PM CEST 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour KernelLimitForConvolutions complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="KernelLimitForConvolutions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="KernelLimitForConvolutionsValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="KernelLimitForConvolutionsUnit" type="{https://www.irdbb-medirad.com}KernelLimitForConvolutionsUnit"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KernelLimitForConvolutions", propOrder = {
    "kernelLimitForConvolutionsValue",
    "kernelLimitForConvolutionsUnit"
})
public class KernelLimitForConvolutions {

    @XmlElement(name = "KernelLimitForConvolutionsValue")
    protected float kernelLimitForConvolutionsValue;
    @XmlElement(name = "KernelLimitForConvolutionsUnit", required = true)
    @XmlSchemaType(name = "string")
    protected KernelLimitForConvolutionsUnit kernelLimitForConvolutionsUnit;

    /**
     * Obtient la valeur de la propriété kernelLimitForConvolutionsValue.
     * 
     */
    public float getKernelLimitForConvolutionsValue() {
        return kernelLimitForConvolutionsValue;
    }

    /**
     * Définit la valeur de la propriété kernelLimitForConvolutionsValue.
     * 
     */
    public void setKernelLimitForConvolutionsValue(float value) {
        this.kernelLimitForConvolutionsValue = value;
    }

    /**
     * Obtient la valeur de la propriété kernelLimitForConvolutionsUnit.
     * 
     * @return
     *     possible object is
     *     {@link KernelLimitForConvolutionsUnit }
     *     
     */
    public KernelLimitForConvolutionsUnit getKernelLimitForConvolutionsUnit() {
        return kernelLimitForConvolutionsUnit;
    }

    /**
     * Définit la valeur de la propriété kernelLimitForConvolutionsUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link KernelLimitForConvolutionsUnit }
     *     
     */
    public void setKernelLimitForConvolutionsUnit(KernelLimitForConvolutionsUnit value) {
        this.kernelLimitForConvolutionsUnit = value;
    }

}
