//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.12.01 alle 07:40:41 AM CET 
//


package it.cnr.icar.biograkn.uniprot;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * Describes non-nuclear gene locations (organelles and plasmids).
 *             Equivalent to the flat file OG-line.
 * 
 * <p>Classe Java per geneLocationType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="geneLocationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://uniprot.org/uniprot}statusType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="type" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="apicoplast"/>
 *             &lt;enumeration value="chloroplast"/>
 *             &lt;enumeration value="organellar chromatophore"/>
 *             &lt;enumeration value="cyanelle"/>
 *             &lt;enumeration value="hydrogenosome"/>
 *             &lt;enumeration value="mitochondrion"/>
 *             &lt;enumeration value="non-photosynthetic plastid"/>
 *             &lt;enumeration value="nucleomorph"/>
 *             &lt;enumeration value="plasmid"/>
 *             &lt;enumeration value="plastid"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="evidence" type="{http://uniprot.org/uniprot}intListType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "geneLocationType", propOrder = {
    "name"
})
public class GeneLocationType {

    protected List<StatusType> name;
    @XmlAttribute(name = "type", required = true)
    protected String type;
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
     * {@link StatusType }
     * 
     * 
     */
    public List<StatusType> getName() {
        if (name == null) {
            name = new ArrayList<StatusType>();
        }
        return this.name;
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

}
