package it.cnr.icar.semanticbiograph.go;

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
 *         &lt;element name="source_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="source_type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="source_fullpath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="source_path" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="source_md5" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="source_mtime" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="source_parsetime" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "sourceId",
    "sourceType",
    "sourceFullpath",
    "sourcePath",
    "sourceMd5",
    "sourceMtime",
    "sourceParsetime"
})
@XmlRootElement(name = "source")
public class Source {

    @XmlElement(name = "source_id", required = true)
    protected String sourceId;
    @XmlElement(name = "source_type", required = true)
    protected String sourceType;
    @XmlElement(name = "source_fullpath", required = true)
    protected String sourceFullpath;
    @XmlElement(name = "source_path", required = true)
    protected String sourcePath;
    @XmlElement(name = "source_md5", required = true)
    protected String sourceMd5;
    @XmlElement(name = "source_mtime")
    protected int sourceMtime;
    @XmlElement(name = "source_parsetime")
    protected int sourceParsetime;

    /**
     * Recupera il valore della proprietà sourceId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceId() {
        return sourceId;
    }

    /**
     * Imposta il valore della proprietà sourceId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceId(String value) {
        this.sourceId = value;
    }

    /**
     * Recupera il valore della proprietà sourceType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceType() {
        return sourceType;
    }

    /**
     * Imposta il valore della proprietà sourceType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceType(String value) {
        this.sourceType = value;
    }

    /**
     * Recupera il valore della proprietà sourceFullpath.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceFullpath() {
        return sourceFullpath;
    }

    /**
     * Imposta il valore della proprietà sourceFullpath.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceFullpath(String value) {
        this.sourceFullpath = value;
    }

    /**
     * Recupera il valore della proprietà sourcePath.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourcePath() {
        return sourcePath;
    }

    /**
     * Imposta il valore della proprietà sourcePath.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourcePath(String value) {
        this.sourcePath = value;
    }

    /**
     * Recupera il valore della proprietà sourceMd5.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceMd5() {
        return sourceMd5;
    }

    /**
     * Imposta il valore della proprietà sourceMd5.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceMd5(String value) {
        this.sourceMd5 = value;
    }

    /**
     * Recupera il valore della proprietà sourceMtime.
     * 
     */
    public int getSourceMtime() {
        return sourceMtime;
    }

    /**
     * Imposta il valore della proprietà sourceMtime.
     * 
     */
    public void setSourceMtime(int value) {
        this.sourceMtime = value;
    }

    /**
     * Recupera il valore della proprietà sourceParsetime.
     * 
     */
    public int getSourceParsetime() {
        return sourceParsetime;
    }

    /**
     * Imposta il valore della proprietà sourceParsetime.
     * 
     */
    public void setSourceParsetime(int value) {
        this.sourceParsetime = value;
    }

}
