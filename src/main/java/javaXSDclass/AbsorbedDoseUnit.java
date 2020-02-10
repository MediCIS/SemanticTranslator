//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.06 à 05:03:32 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour AbsorbedDoseUnit.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="AbsorbedDoseUnit">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="milligray per (milligray per (100 milliampere second))"/>
 *     &lt;enumeration value="milligray per (100 milliampere second)"/>
 *     &lt;enumeration value="milligray"/>
 *     &lt;enumeration value="milligray per (milligray per (1 milliampere second))"/>
 *     &lt;enumeration value="gray"/>
 *     &lt;enumeration value="milligray"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AbsorbedDoseUnit")
@XmlEnum
public enum AbsorbedDoseUnit {

    @XmlEnumValue("milligray per (milligray per (100 milliampere second))")
    MILLIGRAY_PER_MILLIGRAY_PER_100_MILLIAMPERE_SECOND("milligray per (milligray per (100 milliampere second))"),
    @XmlEnumValue("milligray per (100 milliampere second)")
    MILLIGRAY_PER_100_MILLIAMPERE_SECOND("milligray per (100 milliampere second)"),
    @XmlEnumValue("milligray")
    MILLIGRAY("milligray"),
    @XmlEnumValue("milligray per (milligray per (1 milliampere second))")
    MILLIGRAY_PER_MILLIGRAY_PER_1_MILLIAMPERE_SECOND("milligray per (milligray per (1 milliampere second))"),
    @XmlEnumValue("gray")
    GRAY("gray");
    private final String value;

    AbsorbedDoseUnit(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AbsorbedDoseUnit fromValue(String v) {
        for (AbsorbedDoseUnit c: AbsorbedDoseUnit.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
