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
 * <p>Classe Java pour MeanAbsorbedDoseRateInROIUnit.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="MeanAbsorbedDoseRateInROIUnit">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="gram"/>
 *     &lt;enumeration value="kilogram"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MeanAbsorbedDoseRateInROIUnit")
@XmlEnum
public enum MeanAbsorbedDoseRateInROIUnit {

    @XmlEnumValue("gram")
    GRAM("gram"),
    @XmlEnumValue("kilogram")
    KILOGRAM("kilogram");
    private final String value;

    MeanAbsorbedDoseRateInROIUnit(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MeanAbsorbedDoseRateInROIUnit fromValue(String v) {
        for (MeanAbsorbedDoseRateInROIUnit c: MeanAbsorbedDoseRateInROIUnit.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
