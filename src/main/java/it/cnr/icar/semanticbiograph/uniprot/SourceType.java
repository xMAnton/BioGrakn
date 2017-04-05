//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.12.01 alle 07:40:41 AM CET 
//


package it.cnr.icar.semanticbiograph.uniprot;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * Describes the source of the data using a database cross-reference (or a 'ref' attribute when the source cannot be found in a public data source, such as PubMed, and is cited only within the UniProtKB entry).
 * 
 * <p>Classe Java per sourceType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="sourceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dbReference" type="{http://uniprot.org/uniprot}dbReferenceType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ref" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sourceType", propOrder = {
    "dbReference"
})
public class SourceType {

    protected DbReferenceType dbReference;
    @XmlAttribute(name = "ref")
    protected BigInteger ref;

    /**
     * Recupera il valore della proprietà dbReference.
     * 
     * @return
     *     possible object is
     *     {@link DbReferenceType }
     *     
     */
    public DbReferenceType getDbReference() {
        return dbReference;
    }

    /**
     * Imposta il valore della proprietà dbReference.
     * 
     * @param value
     *     allowed object is
     *     {@link DbReferenceType }
     *     
     */
    public void setDbReference(DbReferenceType value) {
        this.dbReference = value;
    }

    /**
     * Recupera il valore della proprietà ref.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRef() {
        return ref;
    }

    /**
     * Imposta il valore della proprietà ref.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRef(BigInteger value) {
        this.ref = value;
    }

}
