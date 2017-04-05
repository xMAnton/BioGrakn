//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.12.16 alle 07:55:53 AM CET 
//


package it.cnr.icar.semanticbiograph.go;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="source">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="source_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="source_type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="source_fullpath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="source_path" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="source_md5" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="source_mtime" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="source_parsetime" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="header">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="format-version" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                   &lt;element name="data-version" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="saved-by" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="auto-generated-by" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="subsetdef" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="synonymtypedef">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="scope" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="default-namespace" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ontology" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="term" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="namespace" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="alt_id" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="def">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="defstr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="dbxref" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="acc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="dbname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="subset" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="synonym" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="synonym_text" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="scope" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="xref_analog" minOccurs="0">
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
 *                   &lt;element name="is_a" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="is_obsolete" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
 *                   &lt;element name="consider" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="typedef" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="namespace" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="xref_analog">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="acc" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                             &lt;element name="dbname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="is_metadata_tag" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
 *                   &lt;element name="is_class_level" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
 *                   &lt;element name="is_transitive" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
 *                   &lt;element name="is_a" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="transitive_over" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "source",
    "header",
    "term",
    "typedef"
})
@XmlRootElement(name = "obo")
public class Obo {

    @XmlElement(required = true)
    protected Source source;
    @XmlElement(required = true)
    protected Header header;
    protected List<Term> term;
    protected List<Typedef> typedef;

    /**
     * Recupera il valore della proprietà source.
     * 
     * @return
     *     possible object is
     *     {@link Obo.Source }
     *     
     */
    public Source getSource() {
        return source;
    }

    /**
     * Imposta il valore della proprietà source.
     * 
     * @param value
     *     allowed object is
     *     {@link Obo.Source }
     *     
     */
    public void setSource(Source value) {
        this.source = value;
    }

    /**
     * Recupera il valore della proprietà header.
     * 
     * @return
     *     possible object is
     *     {@link Obo.Header }
     *     
     */
    public Header getHeader() {
        return header;
    }

    /**
     * Imposta il valore della proprietà header.
     * 
     * @param value
     *     allowed object is
     *     {@link Obo.Header }
     *     
     */
    public void setHeader(Header value) {
        this.header = value;
    }

    /**
     * Gets the value of the term property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the term property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTerm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Obo.Term }
     * 
     * 
     */
    public List<Term> getTerm() {
        if (term == null) {
            term = new ArrayList<Term>();
        }
        return this.term;
    }

    /**
     * Gets the value of the typedef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the typedef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTypedef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Obo.Typedef }
     * 
     * 
     */
    public List<Typedef> getTypedef() {
        if (typedef == null) {
            typedef = new ArrayList<Typedef>();
        }
        return this.typedef;
    }
}
