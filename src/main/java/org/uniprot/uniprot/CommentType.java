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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * Describes different types of general annotations.
 *             Equivalent to the flat file CC-line.
 * 
 * <p>Classe Java per commentType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="commentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="molecule" type="{http://uniprot.org/uniprot}moleculeType" minOccurs="0"/>
 *         &lt;choice minOccurs="0">
 *           &lt;group ref="{http://uniprot.org/uniprot}bpcCommentGroup"/>
 *           &lt;sequence>
 *             &lt;element name="cofactor" type="{http://uniprot.org/uniprot}cofactorType" maxOccurs="unbounded"/>
 *           &lt;/sequence>
 *           &lt;sequence>
 *             &lt;element name="subcellularLocation" type="{http://uniprot.org/uniprot}subcellularLocationType" maxOccurs="unbounded"/>
 *           &lt;/sequence>
 *           &lt;element name="conflict">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="sequence" minOccurs="0">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;attribute name="resource" use="required">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;enumeration value="EMBL-CDS"/>
 *                                   &lt;enumeration value="EMBL"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/attribute>
 *                             &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                             &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                   &lt;/sequence>
 *                   &lt;attribute name="type" use="required">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;enumeration value="frameshift"/>
 *                         &lt;enumeration value="erroneous initiation"/>
 *                         &lt;enumeration value="erroneous termination"/>
 *                         &lt;enumeration value="erroneous gene model prediction"/>
 *                         &lt;enumeration value="erroneous translation"/>
 *                         &lt;enumeration value="miscellaneous discrepancy"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/attribute>
 *                   &lt;attribute name="ref" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;sequence>
 *             &lt;element name="link" maxOccurs="unbounded" minOccurs="0">
 *               &lt;complexType>
 *                 &lt;complexContent>
 *                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                     &lt;attribute name="uri" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *                   &lt;/restriction>
 *                 &lt;/complexContent>
 *               &lt;/complexType>
 *             &lt;/element>
 *           &lt;/sequence>
 *           &lt;sequence>
 *             &lt;element name="event" type="{http://uniprot.org/uniprot}eventType" maxOccurs="4"/>
 *             &lt;element name="isoform" type="{http://uniprot.org/uniprot}isoformType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;/sequence>
 *           &lt;sequence>
 *             &lt;element name="interactant" type="{http://uniprot.org/uniprot}interactantType" maxOccurs="2" minOccurs="2"/>
 *             &lt;element name="organismsDiffer" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *             &lt;element name="experiments" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *           &lt;/sequence>
 *           &lt;element name="disease">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;element name="acronym" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;element name="dbReference" type="{http://uniprot.org/uniprot}dbReferenceType"/>
 *                   &lt;/sequence>
 *                   &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *         &lt;/choice>
 *         &lt;element name="location" type="{http://uniprot.org/uniprot}locationType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="text" type="{http://uniprot.org/uniprot}evidencedStringType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="type" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="allergen"/>
 *             &lt;enumeration value="alternative products"/>
 *             &lt;enumeration value="biotechnology"/>
 *             &lt;enumeration value="biophysicochemical properties"/>
 *             &lt;enumeration value="catalytic activity"/>
 *             &lt;enumeration value="caution"/>
 *             &lt;enumeration value="cofactor"/>
 *             &lt;enumeration value="developmental stage"/>
 *             &lt;enumeration value="disease"/>
 *             &lt;enumeration value="domain"/>
 *             &lt;enumeration value="disruption phenotype"/>
 *             &lt;enumeration value="activity regulation"/>
 *             &lt;enumeration value="function"/>
 *             &lt;enumeration value="induction"/>
 *             &lt;enumeration value="miscellaneous"/>
 *             &lt;enumeration value="pathway"/>
 *             &lt;enumeration value="pharmaceutical"/>
 *             &lt;enumeration value="polymorphism"/>
 *             &lt;enumeration value="PTM"/>
 *             &lt;enumeration value="RNA editing"/>
 *             &lt;enumeration value="similarity"/>
 *             &lt;enumeration value="subcellular location"/>
 *             &lt;enumeration value="sequence caution"/>
 *             &lt;enumeration value="subunit"/>
 *             &lt;enumeration value="tissue specificity"/>
 *             &lt;enumeration value="toxic dose"/>
 *             &lt;enumeration value="online information"/>
 *             &lt;enumeration value="mass spectrometry"/>
 *             &lt;enumeration value="interaction"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="locationType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="mass" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="error" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="method" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="evidence" type="{http://uniprot.org/uniprot}intListType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "commentType", propOrder = {
    "molecule",
    "absorption",
    "kinetics",
    "phDependence",
    "redoxPotential",
    "temperatureDependence",
    "cofactor",
    "subcellularLocation",
    "conflict",
    "link",
    "event",
    "isoform",
    "interactant",
    "organismsDiffer",
    "experiments",
    "disease",
    "location",
    "text"
})
public class CommentType {

    protected MoleculeType molecule;
    protected CommentType.Absorption absorption;
    protected CommentType.Kinetics kinetics;
    protected CommentType.PhDependence phDependence;
    protected CommentType.RedoxPotential redoxPotential;
    protected CommentType.TemperatureDependence temperatureDependence;
    protected List<CofactorType> cofactor;
    protected List<SubcellularLocationType> subcellularLocation;
    protected CommentType.Conflict conflict;
    protected List<CommentType.Link> link;
    protected List<EventType> event;
    protected List<IsoformType> isoform;
    protected List<InteractantType> interactant;
    @XmlElement(defaultValue = "false")
    protected Boolean organismsDiffer;
    protected Integer experiments;
    protected CommentType.Disease disease;
    protected List<LocationType> location;
    protected List<EvidencedStringType> text;
    @XmlAttribute(name = "type", required = true)
    protected String type;
    @XmlAttribute(name = "locationType")
    protected String locationType;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "mass")
    protected Float mass;
    @XmlAttribute(name = "error")
    protected String error;
    @XmlAttribute(name = "method")
    protected String method;
    @XmlAttribute(name = "evidence")
    protected List<Integer> evidence;

    /**
     * Recupera il valore della proprietà molecule.
     * 
     * @return
     *     possible object is
     *     {@link MoleculeType }
     *     
     */
    public MoleculeType getMolecule() {
        return molecule;
    }

    /**
     * Imposta il valore della proprietà molecule.
     * 
     * @param value
     *     allowed object is
     *     {@link MoleculeType }
     *     
     */
    public void setMolecule(MoleculeType value) {
        this.molecule = value;
    }

    /**
     * Recupera il valore della proprietà absorption.
     * 
     * @return
     *     possible object is
     *     {@link CommentType.Absorption }
     *     
     */
    public CommentType.Absorption getAbsorption() {
        return absorption;
    }

    /**
     * Imposta il valore della proprietà absorption.
     * 
     * @param value
     *     allowed object is
     *     {@link CommentType.Absorption }
     *     
     */
    public void setAbsorption(CommentType.Absorption value) {
        this.absorption = value;
    }

    /**
     * Recupera il valore della proprietà kinetics.
     * 
     * @return
     *     possible object is
     *     {@link CommentType.Kinetics }
     *     
     */
    public CommentType.Kinetics getKinetics() {
        return kinetics;
    }

    /**
     * Imposta il valore della proprietà kinetics.
     * 
     * @param value
     *     allowed object is
     *     {@link CommentType.Kinetics }
     *     
     */
    public void setKinetics(CommentType.Kinetics value) {
        this.kinetics = value;
    }

    /**
     * Recupera il valore della proprietà phDependence.
     * 
     * @return
     *     possible object is
     *     {@link CommentType.PhDependence }
     *     
     */
    public CommentType.PhDependence getPhDependence() {
        return phDependence;
    }

    /**
     * Imposta il valore della proprietà phDependence.
     * 
     * @param value
     *     allowed object is
     *     {@link CommentType.PhDependence }
     *     
     */
    public void setPhDependence(CommentType.PhDependence value) {
        this.phDependence = value;
    }

    /**
     * Recupera il valore della proprietà redoxPotential.
     * 
     * @return
     *     possible object is
     *     {@link CommentType.RedoxPotential }
     *     
     */
    public CommentType.RedoxPotential getRedoxPotential() {
        return redoxPotential;
    }

    /**
     * Imposta il valore della proprietà redoxPotential.
     * 
     * @param value
     *     allowed object is
     *     {@link CommentType.RedoxPotential }
     *     
     */
    public void setRedoxPotential(CommentType.RedoxPotential value) {
        this.redoxPotential = value;
    }

    /**
     * Recupera il valore della proprietà temperatureDependence.
     * 
     * @return
     *     possible object is
     *     {@link CommentType.TemperatureDependence }
     *     
     */
    public CommentType.TemperatureDependence getTemperatureDependence() {
        return temperatureDependence;
    }

    /**
     * Imposta il valore della proprietà temperatureDependence.
     * 
     * @param value
     *     allowed object is
     *     {@link CommentType.TemperatureDependence }
     *     
     */
    public void setTemperatureDependence(CommentType.TemperatureDependence value) {
        this.temperatureDependence = value;
    }

    /**
     * Gets the value of the cofactor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cofactor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCofactor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CofactorType }
     * 
     * 
     */
    public List<CofactorType> getCofactor() {
        if (cofactor == null) {
            cofactor = new ArrayList<CofactorType>();
        }
        return this.cofactor;
    }

    /**
     * Gets the value of the subcellularLocation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subcellularLocation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubcellularLocation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubcellularLocationType }
     * 
     * 
     */
    public List<SubcellularLocationType> getSubcellularLocation() {
        if (subcellularLocation == null) {
            subcellularLocation = new ArrayList<SubcellularLocationType>();
        }
        return this.subcellularLocation;
    }

    /**
     * Recupera il valore della proprietà conflict.
     * 
     * @return
     *     possible object is
     *     {@link CommentType.Conflict }
     *     
     */
    public CommentType.Conflict getConflict() {
        return conflict;
    }

    /**
     * Imposta il valore della proprietà conflict.
     * 
     * @param value
     *     allowed object is
     *     {@link CommentType.Conflict }
     *     
     */
    public void setConflict(CommentType.Conflict value) {
        this.conflict = value;
    }

    /**
     * Gets the value of the link property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the link property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLink().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CommentType.Link }
     * 
     * 
     */
    public List<CommentType.Link> getLink() {
        if (link == null) {
            link = new ArrayList<CommentType.Link>();
        }
        return this.link;
    }

    /**
     * Gets the value of the event property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the event property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEvent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EventType }
     * 
     * 
     */
    public List<EventType> getEvent() {
        if (event == null) {
            event = new ArrayList<EventType>();
        }
        return this.event;
    }

    /**
     * Gets the value of the isoform property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the isoform property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIsoform().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IsoformType }
     * 
     * 
     */
    public List<IsoformType> getIsoform() {
        if (isoform == null) {
            isoform = new ArrayList<IsoformType>();
        }
        return this.isoform;
    }

    /**
     * Gets the value of the interactant property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the interactant property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInteractant().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InteractantType }
     * 
     * 
     */
    public List<InteractantType> getInteractant() {
        if (interactant == null) {
            interactant = new ArrayList<InteractantType>();
        }
        return this.interactant;
    }

    /**
     * Recupera il valore della proprietà organismsDiffer.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOrganismsDiffer() {
        return organismsDiffer;
    }

    /**
     * Imposta il valore della proprietà organismsDiffer.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOrganismsDiffer(Boolean value) {
        this.organismsDiffer = value;
    }

    /**
     * Recupera il valore della proprietà experiments.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getExperiments() {
        return experiments;
    }

    /**
     * Imposta il valore della proprietà experiments.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setExperiments(Integer value) {
        this.experiments = value;
    }

    /**
     * Recupera il valore della proprietà disease.
     * 
     * @return
     *     possible object is
     *     {@link CommentType.Disease }
     *     
     */
    public CommentType.Disease getDisease() {
        return disease;
    }

    /**
     * Imposta il valore della proprietà disease.
     * 
     * @param value
     *     allowed object is
     *     {@link CommentType.Disease }
     *     
     */
    public void setDisease(CommentType.Disease value) {
        this.disease = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the location property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LocationType }
     * 
     * 
     */
    public List<LocationType> getLocation() {
        if (location == null) {
            location = new ArrayList<LocationType>();
        }
        return this.location;
    }

    /**
     * Gets the value of the text property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the text property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getText().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EvidencedStringType }
     * 
     * 
     */
    public List<EvidencedStringType> getText() {
        if (text == null) {
            text = new ArrayList<EvidencedStringType>();
        }
        return this.text;
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
     * Recupera il valore della proprietà locationType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationType() {
        return locationType;
    }

    /**
     * Imposta il valore della proprietà locationType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationType(String value) {
        this.locationType = value;
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
     * Recupera il valore della proprietà mass.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getMass() {
        return mass;
    }

    /**
     * Imposta il valore della proprietà mass.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setMass(Float value) {
        this.mass = value;
    }

    /**
     * Recupera il valore della proprietà error.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getError() {
        return error;
    }

    /**
     * Imposta il valore della proprietà error.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setError(String value) {
        this.error = value;
    }

    /**
     * Recupera il valore della proprietà method.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMethod() {
        return method;
    }

    /**
     * Imposta il valore della proprietà method.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMethod(String value) {
        this.method = value;
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
     *         &lt;element name="max" type="{http://uniprot.org/uniprot}evidencedStringType" minOccurs="0"/>
     *         &lt;element name="text" type="{http://uniprot.org/uniprot}evidencedStringType" maxOccurs="unbounded" minOccurs="0"/>
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
        "max",
        "text"
    })
    public static class Absorption {

        protected EvidencedStringType max;
        protected List<EvidencedStringType> text;

        /**
         * Recupera il valore della proprietà max.
         * 
         * @return
         *     possible object is
         *     {@link EvidencedStringType }
         *     
         */
        public EvidencedStringType getMax() {
            return max;
        }

        /**
         * Imposta il valore della proprietà max.
         * 
         * @param value
         *     allowed object is
         *     {@link EvidencedStringType }
         *     
         */
        public void setMax(EvidencedStringType value) {
            this.max = value;
        }

        /**
         * Gets the value of the text property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the text property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getText().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EvidencedStringType }
         * 
         * 
         */
        public List<EvidencedStringType> getText() {
            if (text == null) {
                text = new ArrayList<EvidencedStringType>();
            }
            return this.text;
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
     *         &lt;element name="sequence" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="resource" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                       &lt;enumeration value="EMBL-CDS"/>
     *                       &lt;enumeration value="EMBL"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}int" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="type" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;enumeration value="frameshift"/>
     *             &lt;enumeration value="erroneous initiation"/>
     *             &lt;enumeration value="erroneous termination"/>
     *             &lt;enumeration value="erroneous gene model prediction"/>
     *             &lt;enumeration value="erroneous translation"/>
     *             &lt;enumeration value="miscellaneous discrepancy"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="ref" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "sequence"
    })
    public static class Conflict {

        protected CommentType.Conflict.Sequence sequence;
        @XmlAttribute(name = "type", required = true)
        protected String type;
        @XmlAttribute(name = "ref")
        protected String ref;

        /**
         * Recupera il valore della proprietà sequence.
         * 
         * @return
         *     possible object is
         *     {@link CommentType.Conflict.Sequence }
         *     
         */
        public CommentType.Conflict.Sequence getSequence() {
            return sequence;
        }

        /**
         * Imposta il valore della proprietà sequence.
         * 
         * @param value
         *     allowed object is
         *     {@link CommentType.Conflict.Sequence }
         *     
         */
        public void setSequence(CommentType.Conflict.Sequence value) {
            this.sequence = value;
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


        /**
         * <p>Classe Java per anonymous complex type.
         * 
         * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="resource" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *             &lt;enumeration value="EMBL-CDS"/>
         *             &lt;enumeration value="EMBL"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}int" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Sequence {

            @XmlAttribute(name = "resource", required = true)
            protected String resource;
            @XmlAttribute(name = "id", required = true)
            protected String id;
            @XmlAttribute(name = "version")
            protected Integer version;

            /**
             * Recupera il valore della proprietà resource.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getResource() {
                return resource;
            }

            /**
             * Imposta il valore della proprietà resource.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setResource(String value) {
                this.resource = value;
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
             * Recupera il valore della proprietà version.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getVersion() {
                return version;
            }

            /**
             * Imposta il valore della proprietà version.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setVersion(Integer value) {
                this.version = value;
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
     *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="acronym" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="dbReference" type="{http://uniprot.org/uniprot}dbReferenceType"/>
     *       &lt;/sequence>
     *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "name",
        "acronym",
        "description",
        "dbReference"
    })
    public static class Disease {

        @XmlElement(required = true)
        protected String name;
        @XmlElement(required = true)
        protected String acronym;
        @XmlElement(required = true)
        protected String description;
        @XmlElement(required = true)
        protected DbReferenceType dbReference;
        @XmlAttribute(name = "id", required = true)
        protected String id;

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
         * Recupera il valore della proprietà acronym.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAcronym() {
            return acronym;
        }

        /**
         * Imposta il valore della proprietà acronym.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAcronym(String value) {
            this.acronym = value;
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
     *         &lt;element name="KM" type="{http://uniprot.org/uniprot}evidencedStringType" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="Vmax" type="{http://uniprot.org/uniprot}evidencedStringType" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="text" type="{http://uniprot.org/uniprot}evidencedStringType" maxOccurs="unbounded" minOccurs="0"/>
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
        "km",
        "vmax",
        "text"
    })
    public static class Kinetics {

        @XmlElement(name = "KM")
        protected List<EvidencedStringType> km;
        @XmlElement(name = "Vmax")
        protected List<EvidencedStringType> vmax;
        protected List<EvidencedStringType> text;

        /**
         * Gets the value of the km property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the km property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getKM().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EvidencedStringType }
         * 
         * 
         */
        public List<EvidencedStringType> getKM() {
            if (km == null) {
                km = new ArrayList<EvidencedStringType>();
            }
            return this.km;
        }

        /**
         * Gets the value of the vmax property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the vmax property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getVmax().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EvidencedStringType }
         * 
         * 
         */
        public List<EvidencedStringType> getVmax() {
            if (vmax == null) {
                vmax = new ArrayList<EvidencedStringType>();
            }
            return this.vmax;
        }

        /**
         * Gets the value of the text property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the text property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getText().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EvidencedStringType }
         * 
         * 
         */
        public List<EvidencedStringType> getText() {
            if (text == null) {
                text = new ArrayList<EvidencedStringType>();
            }
            return this.text;
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
     *       &lt;attribute name="uri" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Link {

        @XmlAttribute(name = "uri", required = true)
        @XmlSchemaType(name = "anyURI")
        protected String uri;

        /**
         * Recupera il valore della proprietà uri.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUri() {
            return uri;
        }

        /**
         * Imposta il valore della proprietà uri.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUri(String value) {
            this.uri = value;
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
     *         &lt;element name="text" type="{http://uniprot.org/uniprot}evidencedStringType" maxOccurs="unbounded"/>
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
        "text"
    })
    public static class PhDependence {

        @XmlElement(required = true)
        protected List<EvidencedStringType> text;

        /**
         * Gets the value of the text property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the text property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getText().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EvidencedStringType }
         * 
         * 
         */
        public List<EvidencedStringType> getText() {
            if (text == null) {
                text = new ArrayList<EvidencedStringType>();
            }
            return this.text;
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
     *         &lt;element name="text" type="{http://uniprot.org/uniprot}evidencedStringType" maxOccurs="unbounded"/>
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
        "text"
    })
    public static class RedoxPotential {

        @XmlElement(required = true)
        protected List<EvidencedStringType> text;

        /**
         * Gets the value of the text property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the text property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getText().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EvidencedStringType }
         * 
         * 
         */
        public List<EvidencedStringType> getText() {
            if (text == null) {
                text = new ArrayList<EvidencedStringType>();
            }
            return this.text;
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
     *         &lt;element name="text" type="{http://uniprot.org/uniprot}evidencedStringType" maxOccurs="unbounded"/>
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
        "text"
    })
    public static class TemperatureDependence {

        @XmlElement(required = true)
        protected List<EvidencedStringType> text;

        /**
         * Gets the value of the text property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the text property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getText().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EvidencedStringType }
         * 
         * 
         */
        public List<EvidencedStringType> getText() {
            if (text == null) {
                text = new ArrayList<EvidencedStringType>();
            }
            return this.text;
        }

    }

}
