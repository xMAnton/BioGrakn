//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.12.01 alle 07:40:41 AM CET 
//


package it.cnr.icar.biograkn.uniprot;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per sequenceType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="sequenceType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="length" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="mass" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="checksum" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="modified" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="precursor" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="fragment">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="single"/>
 *             &lt;enumeration value="multiple"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sequenceType", propOrder = {
    "value"
})
public class SequenceType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "length", required = true)
    protected int length;
    @XmlAttribute(name = "mass", required = true)
    protected int mass;
    @XmlAttribute(name = "checksum", required = true)
    protected String checksum;
    @XmlAttribute(name = "modified", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar modified;
    @XmlAttribute(name = "version", required = true)
    protected int version;
    @XmlAttribute(name = "precursor")
    protected Boolean precursor;
    @XmlAttribute(name = "fragment")
    protected String fragment;

    /**
     * Recupera il valore della proprietà value.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Imposta il valore della proprietà value.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Recupera il valore della proprietà length.
     * 
     */
    public int getLength() {
        return length;
    }

    /**
     * Imposta il valore della proprietà length.
     * 
     */
    public void setLength(int value) {
        this.length = value;
    }

    /**
     * Recupera il valore della proprietà mass.
     * 
     */
    public int getMass() {
        return mass;
    }

    /**
     * Imposta il valore della proprietà mass.
     * 
     */
    public void setMass(int value) {
        this.mass = value;
    }

    /**
     * Recupera il valore della proprietà checksum.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChecksum() {
        return checksum;
    }

    /**
     * Imposta il valore della proprietà checksum.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChecksum(String value) {
        this.checksum = value;
    }

    /**
     * Recupera il valore della proprietà modified.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getModified() {
        return modified;
    }

    /**
     * Imposta il valore della proprietà modified.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setModified(XMLGregorianCalendar value) {
        this.modified = value;
    }

    /**
     * Recupera il valore della proprietà version.
     * 
     */
    public int getVersion() {
        return version;
    }

    /**
     * Imposta il valore della proprietà version.
     * 
     */
    public void setVersion(int value) {
        this.version = value;
    }

    /**
     * Recupera il valore della proprietà precursor.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPrecursor() {
        return precursor;
    }

    /**
     * Imposta il valore della proprietà precursor.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrecursor(Boolean value) {
        this.precursor = value;
    }

    /**
     * Recupera il valore della proprietà fragment.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFragment() {
        return fragment;
    }

    /**
     * Imposta il valore della proprietà fragment.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFragment(String value) {
        this.fragment = value;
    }

}
