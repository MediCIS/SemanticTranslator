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
 * <p>Classe Java pour IncorporationFunction.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="IncorporationFunction">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="constant"/>
 *     &lt;enumeration value="linear"/>
 *     &lt;enumeration value="exponential"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "IncorporationFunction")
@XmlEnum
public enum IncorporationFunction {

    @XmlEnumValue("constant")
    CONSTANT("constant"),
    @XmlEnumValue("linear")
    LINEAR("linear"),
    @XmlEnumValue("exponential")
    EXPONENTIAL("exponential");
    private final String value;

    IncorporationFunction(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IncorporationFunction fromValue(String v) {
        for (IncorporationFunction c: IncorporationFunction.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
