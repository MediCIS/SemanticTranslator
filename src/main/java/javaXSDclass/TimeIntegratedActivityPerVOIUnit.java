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
 * <p>Classe Java pour TimeIntegratedActivityPerVOIUnit.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="TimeIntegratedActivityPerVOIUnit">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="MegabecquerelXSecond"/>
 *     &lt;enumeration value="MegabecquerelXHour"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TimeIntegratedActivityPerVOIUnit")
@XmlEnum
public enum TimeIntegratedActivityPerVOIUnit {

    @XmlEnumValue("MegabecquerelXSecond")
    MEGABECQUEREL_X_SECOND("MegabecquerelXSecond"),
    @XmlEnumValue("MegabecquerelXHour")
    MEGABECQUEREL_X_HOUR("MegabecquerelXHour");
    private final String value;

    TimeIntegratedActivityPerVOIUnit(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TimeIntegratedActivityPerVOIUnit fromValue(String v) {
        for (TimeIntegratedActivityPerVOIUnit c: TimeIntegratedActivityPerVOIUnit.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
