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
 * <p>Classe Java pour CalculationAlgorithmUsed.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CalculationAlgorithmUsed">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="local energy deposition"/>
 *     &lt;enumeration value="FFT convolution"/>
 *     &lt;enumeration value="homogeneous matrix convolution"/>
 *     &lt;enumeration value="heterogeneous matrix convolution"/>
 *     &lt;enumeration value="Monte Carlo"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CalculationAlgorithmUsed")
@XmlEnum
public enum CalculationAlgorithmUsed {

    @XmlEnumValue("local energy deposition")
    LOCAL_ENERGY_DEPOSITION("local energy deposition"),
    @XmlEnumValue("FFT convolution")
    FFT_CONVOLUTION("FFT convolution"),
    @XmlEnumValue("homogeneous matrix convolution")
    HOMOGENEOUS_MATRIX_CONVOLUTION("homogeneous matrix convolution"),
    @XmlEnumValue("heterogeneous matrix convolution")
    HETEROGENEOUS_MATRIX_CONVOLUTION("heterogeneous matrix convolution"),
    @XmlEnumValue("Monte Carlo")
    MONTE_CARLO("Monte Carlo");
    private final String value;

    CalculationAlgorithmUsed(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CalculationAlgorithmUsed fromValue(String v) {
        for (CalculationAlgorithmUsed c: CalculationAlgorithmUsed.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
