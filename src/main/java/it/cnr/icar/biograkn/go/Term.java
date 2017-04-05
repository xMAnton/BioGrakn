package it.cnr.icar.biograkn.go;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element name="alt_id" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="def">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="defstr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="dbxref" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="acc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="dbname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="subset" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="synonym" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="synonym_text" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="scope" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="xref_analog" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="acc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="dbname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="is_a" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="is_obsolete" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
 *         &lt;element name="consider" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
    "altId",
    "def",
    "subset",
    "comment",
    "synonym",
    "xrefAnalog",
    "isA",
    "isObsolete",
    "consider",
    "relationship"
})
@XmlRootElement(name = "term")
public class Term {

    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String namespace;
    @XmlElement(name = "alt_id")
    protected List<String> altId;
    @XmlElement(required = true)
    protected Term.Def def;
    protected List<String> subset;
    protected String comment;
    protected Term.Synonym synonym;
    @XmlElement(name = "xref_analog")
    protected Term.XrefAnalog xrefAnalog;
    @XmlElement(name = "is_a")
    protected List<String> isA;
    @XmlElement(name = "is_obsolete")
    protected Byte isObsolete;
    protected List<String> consider;
    protected List<Term.Relationship> relationship;

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
     * Gets the value of the altId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the altId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAltId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAltId() {
        if (altId == null) {
            altId = new ArrayList<String>();
        }
        return this.altId;
    }

    /**
     * Recupera il valore della proprietà def.
     * 
     * @return
     *     possible object is
     *     {@link Obo.Term.Def }
     *     
     */
    public Term.Def getDef() {
        return def;
    }

    /**
     * Imposta il valore della proprietà def.
     * 
     * @param value
     *     allowed object is
     *     {@link Obo.Term.Def }
     *     
     */
    public void setDef(Term.Def value) {
        this.def = value;
    }

    /**
     * Gets the value of the subset property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subset property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubset().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSubset() {
        if (subset == null) {
            subset = new ArrayList<String>();
        }
        return this.subset;
    }

    /**
     * Recupera il valore della proprietà comment.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Imposta il valore della proprietà comment.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }

    public List<Term.Relationship> getRelationship() {
    	if (relationship == null) {
    		relationship = new ArrayList<Term.Relationship>();
    	}
    	return this.relationship;
    }
    
    /**
     * Recupera il valore della proprietà synonym.
     * 
     * @return
     *     possible object is
     *     {@link Obo.Term.Synonym }
     *     
     */
    public Term.Synonym getSynonym() {
        return synonym;
    }

    /**
     * Imposta il valore della proprietà synonym.
     * 
     * @param value
     *     allowed object is
     *     {@link Obo.Term.Synonym }
     *     
     */
    public void setSynonym(Term.Synonym value) {
        this.synonym = value;
    }

    /**
     * Recupera il valore della proprietà xrefAnalog.
     * 
     * @return
     *     possible object is
     *     {@link Obo.Term.XrefAnalog }
     *     
     */
    public Term.XrefAnalog getXrefAnalog() {
        return xrefAnalog;
    }

    /**
     * Imposta il valore della proprietà xrefAnalog.
     * 
     * @param value
     *     allowed object is
     *     {@link Obo.Term.XrefAnalog }
     *     
     */
    public void setXrefAnalog(Term.XrefAnalog value) {
        this.xrefAnalog = value;
    }

    /**
     * Gets the value of the isA property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the isA property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIsA().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getIsA() {
        if (isA == null) {
            isA = new ArrayList<String>();
        }
        return this.isA;
    }

    /**
     * Recupera il valore della proprietà isObsolete.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getIsObsolete() {
        return isObsolete;
    }

    /**
     * Imposta il valore della proprietà isObsolete.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setIsObsolete(Byte value) {
        this.isObsolete = value;
    }

    /**
     * Gets the value of the consider property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the consider property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConsider().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getConsider() {
        if (consider == null) {
            consider = new ArrayList<String>();
        }
        return this.consider;
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
     *         &lt;element name="defstr" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="dbxref" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="acc" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="dbname" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "defstr",
        "dbxref"
    })
    public static class Def {

        @XmlElement(required = true)
        protected String defstr;
        protected List<Term.Def.Dbxref> dbxref;

        /**
         * Recupera il valore della proprietà defstr.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDefstr() {
            return defstr;
        }

        /**
         * Imposta il valore della proprietà defstr.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDefstr(String value) {
            this.defstr = value;
        }

        /**
         * Gets the value of the dbxref property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the dbxref property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDbxref().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Obo.Term.Def.Dbxref }
         * 
         * 
         */
        public List<Term.Def.Dbxref> getDbxref() {
            if (dbxref == null) {
                dbxref = new ArrayList<Term.Def.Dbxref>();
            }
            return this.dbxref;
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
         *         &lt;element name="acc" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        public static class Dbxref {

            @XmlElement(required = true)
            protected String acc;
            @XmlElement(required = true)
            protected String dbname;

            /**
             * Recupera il valore della proprietà acc.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAcc() {
                return acc;
            }

            /**
             * Imposta il valore della proprietà acc.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAcc(String value) {
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
     *         &lt;element name="synonym_text" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *       &lt;attribute name="scope" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "synonymText"
    })
    public static class Synonym {

        @XmlElement(name = "synonym_text", required = true)
        protected String synonymText;
        @XmlAttribute(name = "scope")
        protected String scope;

        /**
         * Recupera il valore della proprietà synonymText.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSynonymText() {
            return synonymText;
        }

        /**
         * Imposta il valore della proprietà synonymText.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSynonymText(String value) {
            this.synonymText = value;
        }

        /**
         * Recupera il valore della proprietà scope.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getScope() {
            return scope;
        }

        /**
         * Imposta il valore della proprietà scope.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setScope(String value) {
            this.scope = value;
        }

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
     *         &lt;element name="acc" type="{http://www.w3.org/2001/XMLSchema}string"/>
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

        @XmlElement(required = true)
        protected String acc;
        @XmlElement(required = true)
        protected String dbname;

        /**
         * Recupera il valore della proprietà acc.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAcc() {
            return acc;
        }

        /**
         * Imposta il valore della proprietà acc.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAcc(String value) {
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

    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "type",
        "to"
    })
    public static class Relationship {

        @XmlElement(required = true)
        protected String type;
        @XmlElement(required = true)
        protected String to;

        /**
         * Recupera il valore della proprietà type.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getType() {
            return type;
        }

        /**
         * Imposta il valore della proprietà type.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setType(String value) {
            this.type = value;
        }

        /**
         * Recupera il valore della proprietà to.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTo() {
            return to;
        }

        /**
         * Imposta il valore della proprietà to.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTo(String value) {
            this.to = value;
        }

    }

}

