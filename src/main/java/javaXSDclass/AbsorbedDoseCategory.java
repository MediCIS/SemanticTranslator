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
 * <p>Classe Java pour AbsorbedDoseCategory.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="AbsorbedDoseCategory">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="mean absorbed dose normalized to CTDI free in air normalized to tube load"/>
 *     &lt;enumeration value="mean absorbed dose normalized to CTDI vol normalized to tube load"/>
 *     &lt;enumeration value="mean absorbed radiation dose"/>
 *     &lt;enumeration value="maximum absorbed radiation dose"/>
 *     &lt;enumeration value="minimum absorbed radiation dose"/>
 *     &lt;enumeration value="median absorbed radiation dose"/>
 *     &lt;enumeration value="mode absorbed radiation dose"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AbsorbedDoseCategory")
@XmlEnum
public enum AbsorbedDoseCategory {

    @XmlEnumValue("mean absorbed dose normalized to CTDI free in air normalized to tube load")
    MEAN_ABSORBED_DOSE_NORMALIZED_TO_CTDI_FREE_IN_AIR_NORMALIZED_TO_TUBE_LOAD("mean absorbed dose normalized to CTDI free in air normalized to tube load"),
    @XmlEnumValue("mean absorbed dose normalized to CTDI vol normalized to tube load")
    MEAN_ABSORBED_DOSE_NORMALIZED_TO_CTDI_VOL_NORMALIZED_TO_TUBE_LOAD("mean absorbed dose normalized to CTDI vol normalized to tube load"),
    @XmlEnumValue("mean absorbed radiation dose")
    MEAN_ABSORBED_RADIATION_DOSE("mean absorbed radiation dose"),
    @XmlEnumValue("maximum absorbed radiation dose")
    MAXIMUM_ABSORBED_RADIATION_DOSE("maximum absorbed radiation dose"),
    @XmlEnumValue("minimum absorbed radiation dose")
    MINIMUM_ABSORBED_RADIATION_DOSE("minimum absorbed radiation dose"),
    @XmlEnumValue("median absorbed radiation dose")
    MEDIAN_ABSORBED_RADIATION_DOSE("median absorbed radiation dose"),
    @XmlEnumValue("mode absorbed radiation dose")
    MODE_ABSORBED_RADIATION_DOSE("mode absorbed radiation dose");
    private final String value;

    AbsorbedDoseCategory(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AbsorbedDoseCategory fromValue(String v) {
        for (AbsorbedDoseCategory c: AbsorbedDoseCategory.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
