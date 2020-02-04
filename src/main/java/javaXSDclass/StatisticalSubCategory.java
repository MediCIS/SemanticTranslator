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
 * <p>Classe Java pour StatisticalSubCategory.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="StatisticalSubCategory">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="mean"/>
 *     &lt;enumeration value="maximum"/>
 *     &lt;enumeration value="minimum"/>
 *     &lt;enumeration value="median"/>
 *     &lt;enumeration value="mode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "StatisticalSubCategory")
@XmlEnum
public enum StatisticalSubCategory {

    @XmlEnumValue("mean")
    MEAN("mean"),
    @XmlEnumValue("maximum")
    MAXIMUM("maximum"),
    @XmlEnumValue("minimum")
    MINIMUM("minimum"),
    @XmlEnumValue("median")
    MEDIAN("median"),
    @XmlEnumValue("mode")
    MODE("mode");
    private final String value;

    StatisticalSubCategory(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StatisticalSubCategory fromValue(String v) {
        for (StatisticalSubCategory c: StatisticalSubCategory.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
