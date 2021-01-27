//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.07.30 à 02:06:37 PM CEST 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour KernelLimitForConvolutionsUnit.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="KernelLimitForConvolutionsUnit">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="millimeter"/>
 *     &lt;enumeration value="centimeter"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "KernelLimitForConvolutionsUnit")
@XmlEnum
public enum KernelLimitForConvolutionsUnit {

    @XmlEnumValue("millimeter")
    MILLIMETER("millimeter"),
    @XmlEnumValue("centimeter")
    CENTIMETER("centimeter");
    private final String value;

    KernelLimitForConvolutionsUnit(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static KernelLimitForConvolutionsUnit fromValue(String v) {
        for (KernelLimitForConvolutionsUnit c: KernelLimitForConvolutionsUnit.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
