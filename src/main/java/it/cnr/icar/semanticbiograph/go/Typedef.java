package it.cnr.icar.semanticbiograph.go;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="namespace" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="xref_analog">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="acc" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                   &lt;element name="dbname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="is_metadata_tag" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
 *         &lt;element name="is_class_level" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
 *         &lt;element name="is_transitive" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
 *         &lt;element name="is_a" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transitive_over" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "name",
    "namespace",
    "xrefAnalog",
    "isMetadataTag",
    "isClassLevel",
    "isTransitive",
    "isA",
    "transitiveOver"
})
@XmlRootElement(name = "typedef")
public class Typedef {

    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String namespace;
    @XmlElement(name = "xref_analog", required = true)
    protected Typedef.XrefAnalog xrefAnalog;
    @XmlElement(name = "is_metadata_tag")
    protected Byte isMetadataTag;
    @XmlElement(name = "is_class_level")
    protected Byte isClassLevel;
    @XmlElement(name = "is_transitive")
    protected Byte isTransitive;
    @XmlElement(name = "is_a")
    protected String isA;
    @XmlElement(name = "transitive_over")
    protected String transitiveOver;

    /**
     * Recupera il valore della proprietà id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Imposta il valore della proprietà id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Recupera il valore della proprietà name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il valore della proprietà name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Recupera il valore della proprietà namespace.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     * Imposta il valore della proprietà namespace.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNamespace(String value) {
        this.namespace = value;
    }

    /**
     * Recupera il valore della proprietà xrefAnalog.
     * 
     * @return
     *     possible object is
     *     {@link Obo.Typedef.XrefAnalog }
     *     
     */
    public Typedef.XrefAnalog getXrefAnalog() {
        return xrefAnalog;
    }

    /**
     * Imposta il valore della proprietà xrefAnalog.
     * 
     * @param value
     *     allowed object is
     *     {@link Obo.Typedef.XrefAnalog }
     *     
     */
    public void setXrefAnalog(Typedef.XrefAnalog value) {
        this.xrefAnalog = value;
    }

    /**
     * Recupera il valore della proprietà isMetadataTag.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getIsMetadataTag() {
        return isMetadataTag;
    }

    /**
     * Imposta il valore della proprietà isMetadataTag.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setIsMetadataTag(Byte value) {
        this.isMetadataTag = value;
    }

    /**
     * Recupera il valore della proprietà isClassLevel.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getIsClassLevel() {
        return isClassLevel;
    }

    /**
     * Imposta il valore della proprietà isClassLevel.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setIsClassLevel(Byte value) {
        this.isClassLevel = value;
    }

    /**
     * Recupera il valore della proprietà isTransitive.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getIsTransitive() {
        return isTransitive;
    }

    /**
     * Imposta il valore della proprietà isTransitive.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setIsTransitive(Byte value) {
        this.isTransitive = value;
    }

    /**
     * Recupera il valore della proprietà isA.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsA() {
        return isA;
    }

    /**
     * Imposta il valore della proprietà isA.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsA(String value) {
        this.isA = value;
    }

    /**
     * Recupera il valore della proprietà transitiveOver.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransitiveOver() {
        return transitiveOver;
    }

    /**
     * Imposta il valore della proprietà transitiveOver.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransitiveOver(String value) {
        this.transitiveOver = value;
    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="acc" type="{http://www.w3.org/2001/XMLSchema}short"/>
     *         &lt;element name="dbname" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "acc",
        "dbname"
    })
    public static class XrefAnalog {

        protected short acc;
        @XmlElement(required = true)
        protected String dbname;

        /**
         * Recupera il valore della proprietà acc.
         * 
         */
        public short getAcc() {
            return acc;
        }

        /**
         * Imposta il valore della proprietà acc.
         * 
         */
        public void setAcc(short value) {
            this.acc = value;
        }

        /**
         * Recupera il valore della proprietà dbname.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDbname() {
            return dbname;
        }

        /**
         * Imposta il valore della proprietà dbname.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDbname(String value) {
            this.dbname = value;
        }

    }

}

