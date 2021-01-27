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
 * <p>Classe Java pour VoxelBasedDistributionOfAbsorbedDoseCategory.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="VoxelBasedDistributionOfAbsorbedDoseCategory">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="absorbed dose normalized to CTDI free in air normalized to tube load"/>
 *     &lt;enumeration value="absorbed dose normalized to CTDI vol normalized to tube load"/>
 *     &lt;enumeration value="absorbed dose"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "VoxelBasedDistributionOfAbsorbedDoseCategory")
@XmlEnum
public enum VoxelBasedDistributionOfAbsorbedDoseCategory {

    @XmlEnumValue("absorbed dose normalized to CTDI free in air normalized to tube load")
    ABSORBED_DOSE_NORMALIZED_TO_CTDI_FREE_IN_AIR_NORMALIZED_TO_TUBE_LOAD("absorbed dose normalized to CTDI free in air normalized to tube load"),
    @XmlEnumValue("absorbed dose normalized to CTDI vol normalized to tube load")
    ABSORBED_DOSE_NORMALIZED_TO_CTDI_VOL_NORMALIZED_TO_TUBE_LOAD("absorbed dose normalized to CTDI vol normalized to tube load"),
    @XmlEnumValue("absorbed dose")
    ABSORBED_DOSE("absorbed dose");
    private final String value;

    VoxelBasedDistributionOfAbsorbedDoseCategory(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static VoxelBasedDistributionOfAbsorbedDoseCategory fromValue(String v) {
        for (VoxelBasedDistributionOfAbsorbedDoseCategory c: VoxelBasedDistributionOfAbsorbedDoseCategory.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
