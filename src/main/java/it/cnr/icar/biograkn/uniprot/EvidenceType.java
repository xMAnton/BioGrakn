//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.12.01 alle 07:40:41 AM CET 
//


package it.cnr.icar.biograkn.uniprot;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * Describes the evidence for an annotation.
 *             No flat file equivalent.
 * 
 * <p>Classe Java per evidenceType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="evidenceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="source" type="{http://uniprot.org/uniprot}sourceType" minOccurs="0"/>
 *         &lt;element name="importedFrom" type="{http://uniprot.org/uniprot}importedFromType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="key" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "evidenceType", propOrder = {
    "source",
    "importedFrom"
})
public class EvidenceType {

    protected SourceType source;
    protected ImportedFromType importedFrom;
    @XmlAttribute(name = "type", required = true)
    protected String type;
    @XmlAttribute(name = "key", required = true)
    protected BigInteger key;

    /**
     * Recupera il valore della proprietà source.
     * 
     * @return
     *     possible object is
     *     {@link SourceType }
     *     
     */
    public SourceType getSource() {
        return source;
    }

    /**
     * Imposta il valore della proprietà source.
     * 
     * @param value
     *     allowed object is
     *     {@link SourceType }
     *     
     */
    public void setSource(SourceType value) {
        this.source = value;
    }

    /**
     * Recupera il valore della proprietà importedFrom.
     * 
     * @return
     *     possible object is
     *     {@link ImportedFromType }
     *     
     */
    public ImportedFromType getImportedFrom() {
        return importedFrom;
    }

    /**
     * Imposta il valore della proprietà importedFrom.
     * 
     * @param value
     *     allowed object is
     *     {@link ImportedFromType }
     *     
     */
    public void setImportedFrom(ImportedFromType value) {
        this.importedFrom = value;
    }

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
     * Recupera il valore della proprietà key.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getKey() {
        return key;
    }

    /**
     * Imposta il valore della proprietà key.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setKey(BigInteger value) {
        this.key = value;
    }

}
