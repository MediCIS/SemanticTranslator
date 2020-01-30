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
 * <p>Classe Java pour Isotope.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="Isotope">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="radium223"/>
 *     &lt;enumeration value="iodine131"/>
 *     &lt;enumeration value="lutetium177"/>
 *     &lt;enumeration value="yttrium90"/>
 *     &lt;enumeration value="radium223"/>
 *     &lt;enumeration value="rhenium188"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Isotope")
@XmlEnum
public enum Isotope {

    @XmlEnumValue("radium223")
    RADIUM_223("radium223"),
    @XmlEnumValue("iodine131")
    IODINE_131("iodine131"),
    @XmlEnumValue("lutetium177")
    LUTETIUM_177("lutetium177"),
    @XmlEnumValue("yttrium90")
    YTTRIUM_90("yttrium90"),
    @XmlEnumValue("rhenium188")
    RHENIUM_188("rhenium188");
    private final String value;

    Isotope(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Isotope fromValue(String v) {
        for (Isotope c: Isotope.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
