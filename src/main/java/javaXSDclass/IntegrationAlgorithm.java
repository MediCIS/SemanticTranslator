//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.01.28 à 04:59:07 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour IntegrationAlgorithm.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="IntegrationAlgorithm">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="trapezoid"/>
 *     &lt;enumeration value="mono_exponential"/>
 *     &lt;enumeration value="bi_exponential"/>
 *     &lt;enumeration value="tri_exponential"/>
 *     &lt;enumeration value="x_exponential"/>
 *     &lt;enumeration value="other"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "IntegrationAlgorithm")
@XmlEnum
public enum IntegrationAlgorithm {

    @XmlEnumValue("trapezoid")
    TRAPEZOID("trapezoid"),
    @XmlEnumValue("mono_exponential")
    MONO_EXPONENTIAL("mono_exponential"),
    @XmlEnumValue("bi_exponential")
    BI_EXPONENTIAL("bi_exponential"),
    @XmlEnumValue("tri_exponential")
    TRI_EXPONENTIAL("tri_exponential"),
    @XmlEnumValue("x_exponential")
    X_EXPONENTIAL("x_exponential"),
    @XmlEnumValue("other")
    OTHER("other");
    private final String value;

    IntegrationAlgorithm(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IntegrationAlgorithm fromValue(String v) {
        for (IntegrationAlgorithm c: IntegrationAlgorithm.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
