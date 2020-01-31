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
 * <p>Classe Java pour PatientOrganMassUnit.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="PatientOrganMassUnit">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="gram"/>
 *     &lt;enumeration value="kilogram"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PatientOrganMassUnit")
@XmlEnum
public enum PatientOrganMassUnit {

    @XmlEnumValue("gram")
    GRAM("gram"),
    @XmlEnumValue("kilogram")
    KILOGRAM("kilogram");
    private final String value;

    PatientOrganMassUnit(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PatientOrganMassUnit fromValue(String v) {
        for (PatientOrganMassUnit c: PatientOrganMassUnit.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
