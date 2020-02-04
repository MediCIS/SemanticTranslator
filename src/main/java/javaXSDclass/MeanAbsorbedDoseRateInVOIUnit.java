//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.02.04 à 12:01:52 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour MeanAbsorbedDoseRateInVOIUnit.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="MeanAbsorbedDoseRateInVOIUnit">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="gray per second"/>
 *     &lt;enumeration value="milligray per second"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MeanAbsorbedDoseRateInVOIUnit")
@XmlEnum
public enum MeanAbsorbedDoseRateInVOIUnit {

    @XmlEnumValue("gray per second")
    GRAY_PER_SECOND("gray per second"),
    @XmlEnumValue("milligray per second")
    MILLIGRAY_PER_SECOND("milligray per second");
    private final String value;

    MeanAbsorbedDoseRateInVOIUnit(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MeanAbsorbedDoseRateInVOIUnit fromValue(String v) {
        for (MeanAbsorbedDoseRateInVOIUnit c: MeanAbsorbedDoseRateInVOIUnit.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
