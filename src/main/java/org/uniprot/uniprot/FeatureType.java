//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.03 alle 02:27:34 PM CEST 
//


package org.uniprot.uniprot;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Describes different types of sequence annotations.
 *             Equivalent to the flat file FT-line.
 * 
 * <p>Classe Java per featureType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="featureType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="original" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="variation" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="location" type="{http://uniprot.org/uniprot}locationType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="type" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="active site"/>
 *             &lt;enumeration value="binding site"/>
 *             &lt;enumeration value="calcium-binding region"/>
 *             &lt;enumeration value="chain"/>
 *             &lt;enumeration value="coiled-coil region"/>
 *             &lt;enumeration value="compositionally biased region"/>
 *             &lt;enumeration value="cross-link"/>
 *             &lt;enumeration value="disulfide bond"/>
 *             &lt;enumeration value="DNA-binding region"/>
 *             &lt;enumeration value="domain"/>
 *             &lt;enumeration value="glycosylation site"/>
 *             &lt;enumeration value="helix"/>
 *             &lt;enumeration value="initiator methionine"/>
 *             &lt;enumeration value="lipid moiety-binding region"/>
 *             &lt;enumeration value="metal ion-binding site"/>
 *             &lt;enumeration value="modified residue"/>
 *             &lt;enumeration value="mutagenesis site"/>
 *             &lt;enumeration value="non-consecutive residues"/>
 *             &lt;enumeration value="non-terminal residue"/>
 *             &lt;enumeration value="nucleotide phosphate-binding region"/>
 *             &lt;enumeration value="peptide"/>
 *             &lt;enumeration value="propeptide"/>
 *             &lt;enumeration value="region of interest"/>
 *             &lt;enumeration value="repeat"/>
 *             &lt;enumeration value="non-standard amino acid"/>
 *             &lt;enumeration value="sequence conflict"/>
 *             &lt;enumeration value="sequence variant"/>
 *             &lt;enumeration value="short sequence motif"/>
 *             &lt;enumeration value="signal peptide"/>
 *             &lt;enumeration value="site"/>
 *             &lt;enumeration value="splice variant"/>
 *             &lt;enumeration value="strand"/>
 *             &lt;enumeration value="topological domain"/>
 *             &lt;enumeration value="transit peptide"/>
 *             &lt;enumeration value="transmembrane region"/>
 *             &lt;enumeration value="turn"/>
 *             &lt;enumeration value="unsure residue"/>
 *             &lt;enumeration value="zinc finger region"/>
 *             &lt;enumeration value="intramembrane region"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="status">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="by similarity"/>
 *             &lt;enumeration value="probable"/>
 *             &lt;enumeration value="potential"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="evidence" type="{http://uniprot.org/uniprot}intListType" />
 *       &lt;attribute name="ref" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "featureType", propOrder = {
    "original",
    "variation",
    "location"
})
public class FeatureType {

    protected String original;
    protected List<String> variation;
    @XmlElement(required = true)
    protected LocationType location;
    @XmlAttribute(name = "type", required = true)
    protected String type;
    @XmlAttribute(name = "status")
    protected String status;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "description")
    protected String description;
    @XmlAttribute(name = "evidence")
    protected List<Integer> evidence;
    @XmlAttribute(name = "ref")
    protected String ref;

    /**
     * Recupera il valore della proprietà original.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginal() {
        return original;
    }

    /**
     * Imposta il valore della proprietà original.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginal(String value) {
        this.original = value;
    }

    /**
     * Gets the value of the variation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the variation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVariation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getVariation() {
        if (variation == null) {
            variation = new ArrayList<String>();
        }
        return this.variation;
    }

    /**
     * Recupera il valore della proprietà location.
     * 
     * @return
     *     possible object is
     *     {@link LocationType }
     *     
     */
    public LocationType getLocation() {
        return location;
    }

    /**
     * Imposta il valore della proprietà location.
     * 
     * @param value
     *     allowed object is
     *     {@link LocationType }
     *     
     */
    public void setLocation(LocationType value) {
        this.location = value;
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
     * Recupera il valore della proprietà status.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Imposta il valore della proprietà status.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

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
     * Recupera il valore della proprietà description.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Imposta il valore della proprietà description.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the evidence property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the evidence property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEvidence().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getEvidence() {
        if (evidence == null) {
            evidence = new ArrayList<Integer>();
        }
        return this.evidence;
    }

    /**
     * Recupera il valore della proprietà ref.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRef() {
        return ref;
    }

    /**
     * Imposta il valore della proprietà ref.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRef(String value) {
        this.ref = value;
    }

}
