//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.01.24 à 06:05:31 PM CET 
//


package javaXSDclass;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour PostAdminActivity.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="PostAdminActivity">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="becquerel"/>
 *     &lt;enumeration value="kilobecquerel"/>
 *     &lt;enumeration value="megabecquerel"/>
 *     &lt;enumeration value="curie"/>
 *     &lt;enumeration value="millicurie"/>
 *     &lt;enumeration value="microcurie"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PostAdminActivity")
@XmlEnum
public enum PostAdminActivity {

    @XmlEnumValue("becquerel")
    BECQUEREL("becquerel"),
    @XmlEnumValue("kilobecquerel")
    KILOBECQUEREL("kilobecquerel"),
    @XmlEnumValue("megabecquerel")
    MEGABECQUEREL("megabecquerel"),
    @XmlEnumValue("curie")
    CURIE("curie"),
    @XmlEnumValue("millicurie")
    MILLICURIE("millicurie"),
    @XmlEnumValue("microcurie")
    MICROCURIE("microcurie");
    private final String value;

    PostAdminActivity(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PostAdminActivity fromValue(String v) {
        for (PostAdminActivity c: PostAdminActivity.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
