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
 * <p>Classe Java per interactantType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="interactantType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;group ref="{http://uniprot.org/uniprot}interactantGroup" minOccurs="0"/>
 *       &lt;attribute name="intactId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "interactantType", propOrder = {
    "id",
    "label"
})
public class InteractantType {

    protected String id;
    protected String label;
    @XmlAttribute(name = "intactId", required = true)
    protected String intactId;

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
     * Recupera il valore della proprietà label.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabel() {
        return label;
    }

    /**
     * Imposta il valore della proprietà label.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabel(String value) {
        this.label = value;
    }

    /**
     * Recupera il valore della proprietà intactId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntactId() {
        return intactId;
    }

    /**
     * Imposta il valore della proprietà intactId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntactId(String value) {
        this.intactId = value;
    }

}
