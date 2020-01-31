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
 * <p>Classe Java pour OrganOrTissue.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="OrganOrTissue">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="bone"/>
 *     &lt;enumeration value="bone surfaces"/>
 *     &lt;enumeration value="red bone marrow"/>
 *     &lt;enumeration value="bladder"/>
 *     &lt;enumeration value="breasts"/>
 *     &lt;enumeration value="left male breast"/>
 *     &lt;enumeration value="left female breast"/>
 *     &lt;enumeration value="right male breast"/>
 *     &lt;enumeration value="right female breast"/>
 *     &lt;enumeration value="colon"/>
 *     &lt;enumeration value="testes"/>
 *     &lt;enumeration value="left testis"/>
 *     &lt;enumeration value="right testis"/>
 *     &lt;enumeration value="ovaries"/>
 *     &lt;enumeration value="left ovary"/>
 *     &lt;enumeration value="right ovary"/>
 *     &lt;enumeration value="liver"/>
 *     &lt;enumeration value="lungs"/>
 *     &lt;enumeration value="left lung"/>
 *     &lt;enumeration value="right lung"/>
 *     &lt;enumeration value="esophagus"/>
 *     &lt;enumeration value="skin"/>
 *     &lt;enumeration value="stomach"/>
 *     &lt;enumeration value="thyroid"/>
 *     &lt;enumeration value="brain"/>
 *     &lt;enumeration value="salivary glands"/>
 *     &lt;enumeration value="parotid glands"/>
 *     &lt;enumeration value="left parotid gland"/>
 *     &lt;enumeration value="right parotid gland"/>
 *     &lt;enumeration value="submandibular glands"/>
 *     &lt;enumeration value="left submandibular gland"/>
 *     &lt;enumeration value="right submandibular gland"/>
 *     &lt;enumeration value="adrenals"/>
 *     &lt;enumeration value="extrathoracic region"/>
 *     &lt;enumeration value="gallbladder"/>
 *     &lt;enumeration value="heart"/>
 *     &lt;enumeration value="kidneys"/>
 *     &lt;enumeration value="left kidney"/>
 *     &lt;enumeration value="right kidney"/>
 *     &lt;enumeration value="lymphatic nodes"/>
 *     &lt;enumeration value="muscle"/>
 *     &lt;enumeration value="oral mucosa"/>
 *     &lt;enumeration value="pancreas"/>
 *     &lt;enumeration value="prostate"/>
 *     &lt;enumeration value="small intestine"/>
 *     &lt;enumeration value="spleen"/>
 *     &lt;enumeration value="thymus"/>
 *     &lt;enumeration value="uterus"/>
 *     &lt;enumeration value="air"/>
 *     &lt;enumeration value="body surface"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "OrganOrTissue")
@XmlEnum
public enum OrganOrTissue {

    @XmlEnumValue("bone")
    BONE("bone"),
    @XmlEnumValue("bone surfaces")
    BONE_SURFACES("bone surfaces"),
    @XmlEnumValue("red bone marrow")
    RED_BONE_MARROW("red bone marrow"),
    @XmlEnumValue("bladder")
    BLADDER("bladder"),
    @XmlEnumValue("breasts")
    BREASTS("breasts"),
    @XmlEnumValue("left male breast")
    LEFT_MALE_BREAST("left male breast"),
    @XmlEnumValue("left female breast")
    LEFT_FEMALE_BREAST("left female breast"),
    @XmlEnumValue("right male breast")
    RIGHT_MALE_BREAST("right male breast"),
    @XmlEnumValue("right female breast")
    RIGHT_FEMALE_BREAST("right female breast"),
    @XmlEnumValue("colon")
    COLON("colon"),
    @XmlEnumValue("testes")
    TESTES("testes"),
    @XmlEnumValue("left testis")
    LEFT_TESTIS("left testis"),
    @XmlEnumValue("right testis")
    RIGHT_TESTIS("right testis"),
    @XmlEnumValue("ovaries")
    OVARIES("ovaries"),
    @XmlEnumValue("left ovary")
    LEFT_OVARY("left ovary"),
    @XmlEnumValue("right ovary")
    RIGHT_OVARY("right ovary"),
    @XmlEnumValue("liver")
    LIVER("liver"),
    @XmlEnumValue("lungs")
    LUNGS("lungs"),
    @XmlEnumValue("left lung")
    LEFT_LUNG("left lung"),
    @XmlEnumValue("right lung")
    RIGHT_LUNG("right lung"),
    @XmlEnumValue("esophagus")
    ESOPHAGUS("esophagus"),
    @XmlEnumValue("skin")
    SKIN("skin"),
    @XmlEnumValue("stomach")
    STOMACH("stomach"),
    @XmlEnumValue("thyroid")
    THYROID("thyroid"),
    @XmlEnumValue("brain")
    BRAIN("brain"),
    @XmlEnumValue("salivary glands")
    SALIVARY_GLANDS("salivary glands"),
    @XmlEnumValue("parotid glands")
    PAROTID_GLANDS("parotid glands"),
    @XmlEnumValue("left parotid gland")
    LEFT_PAROTID_GLAND("left parotid gland"),
    @XmlEnumValue("right parotid gland")
    RIGHT_PAROTID_GLAND("right parotid gland"),
    @XmlEnumValue("submandibular glands")
    SUBMANDIBULAR_GLANDS("submandibular glands"),
    @XmlEnumValue("left submandibular gland")
    LEFT_SUBMANDIBULAR_GLAND("left submandibular gland"),
    @XmlEnumValue("right submandibular gland")
    RIGHT_SUBMANDIBULAR_GLAND("right submandibular gland"),
    @XmlEnumValue("adrenals")
    ADRENALS("adrenals"),
    @XmlEnumValue("extrathoracic region")
    EXTRATHORACIC_REGION("extrathoracic region"),
    @XmlEnumValue("gallbladder")
    GALLBLADDER("gallbladder"),
    @XmlEnumValue("heart")
    HEART("heart"),
    @XmlEnumValue("kidneys")
    KIDNEYS("kidneys"),
    @XmlEnumValue("left kidney")
    LEFT_KIDNEY("left kidney"),
    @XmlEnumValue("right kidney")
    RIGHT_KIDNEY("right kidney"),
    @XmlEnumValue("lymphatic nodes")
    LYMPHATIC_NODES("lymphatic nodes"),
    @XmlEnumValue("muscle")
    MUSCLE("muscle"),
    @XmlEnumValue("oral mucosa")
    ORAL_MUCOSA("oral mucosa"),
    @XmlEnumValue("pancreas")
    PANCREAS("pancreas"),
    @XmlEnumValue("prostate")
    PROSTATE("prostate"),
    @XmlEnumValue("small intestine")
    SMALL_INTESTINE("small intestine"),
    @XmlEnumValue("spleen")
    SPLEEN("spleen"),
    @XmlEnumValue("thymus")
    THYMUS("thymus"),
    @XmlEnumValue("uterus")
    UTERUS("uterus"),
    @XmlEnumValue("air")
    AIR("air"),
    @XmlEnumValue("body surface")
    BODY_SURFACE("body surface");
    private final String value;

    OrganOrTissue(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OrganOrTissue fromValue(String v) {
        for (OrganOrTissue c: OrganOrTissue.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
