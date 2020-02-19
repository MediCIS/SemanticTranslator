//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.12 à 04:30:42 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TransformationType.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="TransformationType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Linear transformation matrix"/>
 *     &lt;enumeration value="BSpline transformation"/>
 *     &lt;enumeration value="Advanced elastix transformation"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TransformationType")
@XmlEnum
public enum TransformationType {

    @XmlEnumValue("Linear transformation matrix")
    LINEAR_TRANSFORMATION_MATRIX("Linear transformation matrix"),
    @XmlEnumValue("BSpline transformation")
    B_SPLINE_TRANSFORMATION("BSpline transformation"),
    @XmlEnumValue("Advanced elastix transformation")
    ADVANCED_ELASTIX_TRANSFORMATION("Advanced elastix transformation");
    private final String value;

    TransformationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TransformationType fromValue(String v) {
        for (TransformationType c: TransformationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
