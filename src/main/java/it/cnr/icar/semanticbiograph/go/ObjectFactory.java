//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.12.16 alle 07:55:53 AM CET 
//


package it.cnr.icar.semanticbiograph.go;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.cnr.icar.biorient.models.go package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.cnr.icar.biorient.models.go
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Obo }
     * 
     */
    public Obo createObo() {
        return new Obo();
    }

    /**
     * Create an instance of {@link Obo.Typedef }
     * 
     */
    public Typedef createOboTypedef() {
        return new Typedef();
    }

    /**
     * Create an instance of {@link Obo.Term }
     * 
     */
    public Term createOboTerm() {
        return new Term();
    }

    /**
     * Create an instance of {@link Obo.Term.Def }
     * 
     */
    public Term.Def createOboTermDef() {
        return new Term.Def();
    }

    /**
     * Create an instance of {@link Obo.Header }
     * 
     */
    public Header createOboHeader() {
        return new Header();
    }

    /**
     * Create an instance of {@link Obo.Source }
     * 
     */
    public Source createSource() {
        return new Source();
    }

    /**
     * Create an instance of {@link Obo.Typedef.XrefAnalog }
     * 
     */
    public Typedef.XrefAnalog createOboTypedefXrefAnalog() {
        return new Typedef.XrefAnalog();
    }

    /**
     * Create an instance of {@link Obo.Term.Synonym }
     * 
     */
    public Term.Synonym createOboTermSynonym() {
        return new Term.Synonym();
    }

    public Term.Relationship createOboTermRelationship() {
    	return new Term.Relationship();
    }
    
    /**
     * Create an instance of {@link Obo.Term.XrefAnalog }
     * 
     */
    public Term.XrefAnalog createOboTermXrefAnalog() {
        return new Term.XrefAnalog();
    }

    /**
     * Create an instance of {@link Obo.Term.Def.Dbxref }
     * 
     */
    public Term.Def.Dbxref createOboTermDefDbxref() {
        return new Term.Def.Dbxref();
    }

    /**
     * Create an instance of {@link Obo.Header.Subsetdef }
     * 
     */
    public Header.Subsetdef createOboHeaderSubsetdef() {
        return new Header.Subsetdef();
    }

    /**
     * Create an instance of {@link Obo.Header.Synonymtypedef }
     * 
     */
    public Header.Synonymtypedef createOboHeaderSynonymtypedef() {
        return new Header.Synonymtypedef();
    }

}
