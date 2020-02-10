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
 * <p>Classe Java pour EnergyDepositionRateUnit.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="EnergyDepositionRateUnit">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="joule per second"/>
 *     &lt;enumeration value="megaelectronvolt per second"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnergyDepositionRateUnit")
@XmlEnum
public enum EnergyDepositionRateUnit {

    @XmlEnumValue("joule per second")
    JOULE_PER_SECOND("joule per second"),
    @XmlEnumValue("megaelectronvolt per second")
    MEGAELECTRONVOLT_PER_SECOND("megaelectronvolt per second");
    private final String value;

    EnergyDepositionRateUnit(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnergyDepositionRateUnit fromValue(String v) {
        for (EnergyDepositionRateUnit c: EnergyDepositionRateUnit.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
