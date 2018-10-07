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
 * Describes the source organism.
 * 
 * <p>Classe Java per organismType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="organismType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://uniprot.org/uniprot}organismNameType" maxOccurs="unbounded"/>
 *         &lt;element name="dbReference" type="{http://uniprot.org/uniprot}dbReferenceType" maxOccurs="unbounded"/>
 *         &lt;element name="lineage" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="taxon" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="evidence" type="{http://uniprot.org/uniprot}intListType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "organismType", propOrder = {
    "name",
    "dbReference",
    "lineage"
})
public class OrganismType {

    @XmlElement(required = true)
    protected List<OrganismNameType> name;
    @XmlElement(required = true)
    protected List<DbReferenceType> dbReference;
    protected OrganismType.Lineage lineage;
    @XmlAttribute(name = "evidence")
    protected List<Integer> evidence;

    /**
     * Gets the value of the name property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the name property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrganismNameType }
     * 
     * 
     */
    public List<OrganismNameType> getName() {
        if (name == null) {
            name = new ArrayList<OrganismNameType>();
        }
        return this.name;
    }

    /**
     * Gets the value of the dbReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dbReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDbReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DbReferenceType }
     * 
     * 
     */
    public List<DbReferenceType> getDbReference() {
        if (dbReference == null) {
            dbReference = new ArrayList<DbReferenceType>();
        }
        return this.dbReference;
    }

    /**
     * Recupera il valore della proprietà lineage.
     * 
     * @return
     *     possible object is
     *     {@link OrganismType.Lineage }
     *     
     */
    public OrganismType.Lineage getLineage() {
        return lineage;
    }

    /**
     * Imposta il valore della proprietà lineage.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganismType.Lineage }
     *     
     */
    public void setLineage(OrganismType.Lineage value) {
        this.lineage = value;
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
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="taxon" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
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
        "taxon"
    })
    public static class Lineage {

        @XmlElement(required = true)
        protected List<String> taxon;

        /**
         * Gets the value of the taxon property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the taxon property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTaxon().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getTaxon() {
            if (taxon == null) {
                taxon = new ArrayList<String>();
            }
            return this.taxon;
        }

    }

}