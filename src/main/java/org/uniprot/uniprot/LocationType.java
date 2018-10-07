//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.03 alle 02:27:34 PM CEST 
//


package org.uniprot.uniprot;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * Describes a sequence location as either a range with a begin and end or as a position. The 'sequence' attribute is only used when the location is not on the canonical sequence displayed in the current entry.
 * 
 * <p>Classe Java per locationType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="locationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;sequence>
 *           &lt;element name="begin" type="{http://uniprot.org/uniprot}positionType"/>
 *           &lt;element name="end" type="{http://uniprot.org/uniprot}positionType"/>
 *         &lt;/sequence>
 *         &lt;element name="position" type="{http://uniprot.org/uniprot}positionType"/>
 *       &lt;/choice>
 *       &lt;attribute name="sequence" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "locationType", propOrder = {
    "begin",
    "end",
    "position"
})
public class LocationType {

    protected PositionType begin;
    protected PositionType end;
    protected PositionType position;
    @XmlAttribute(name = "sequence")
    protected String sequence;

    /**
     * Recupera il valore della proprietà begin.
     * 
     * @return
     *     possible object is
     *     {@link PositionType }
     *     
     */
    public PositionType getBegin() {
        return begin;
    }

    /**
     * Imposta il valore della proprietà begin.
     * 
     * @param value
     *     allowed object is
     *     {@link PositionType }
     *     
     */
    public void setBegin(PositionType value) {
        this.begin = value;
    }

    /**
     * Recupera il valore della proprietà end.
     * 
     * @return
     *     possible object is
     *     {@link PositionType }
     *     
     */
    public PositionType getEnd() {
        return end;
    }

    /**
     * Imposta il valore della proprietà end.
     * 
     * @param value
     *     allowed object is
     *     {@link PositionType }
     *     
     */
    public void setEnd(PositionType value) {
        this.end = value;
    }

    /**
     * Recupera il valore della proprietà position.
     * 
     * @return
     *     possible object is
     *     {@link PositionType }
     *     
     */
    public PositionType getPosition() {
        return position;
    }

    /**
     * Imposta il valore della proprietà position.
     * 
     * @param value
     *     allowed object is
     *     {@link PositionType }
     *     
     */
    public void setPosition(PositionType value) {
        this.position = value;
    }

    /**
     * Recupera il valore della proprietà sequence.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSequence() {
        return sequence;
    }

    /**
     * Imposta il valore della proprietà sequence.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSequence(String value) {
        this.sequence = value;
    }

}
