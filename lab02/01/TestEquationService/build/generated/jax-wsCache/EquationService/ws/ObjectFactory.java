
package ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws package. 
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

    private final static QName _SolveQuadratic_QNAME = new QName("http://ws/", "solveQuadratic");
    private final static QName _SolveQuadraticResponse_QNAME = new QName("http://ws/", "solveQuadraticResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SolveQuadratic }
     * 
     */
    public SolveQuadratic createSolveQuadratic() {
        return new SolveQuadratic();
    }

    /**
     * Create an instance of {@link SolveQuadraticResponse }
     * 
     */
    public SolveQuadraticResponse createSolveQuadraticResponse() {
        return new SolveQuadraticResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SolveQuadratic }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "solveQuadratic")
    public JAXBElement<SolveQuadratic> createSolveQuadratic(SolveQuadratic value) {
        return new JAXBElement<SolveQuadratic>(_SolveQuadratic_QNAME, SolveQuadratic.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SolveQuadraticResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "solveQuadraticResponse")
    public JAXBElement<SolveQuadraticResponse> createSolveQuadraticResponse(SolveQuadraticResponse value) {
        return new JAXBElement<SolveQuadraticResponse>(_SolveQuadraticResponse_QNAME, SolveQuadraticResponse.class, null, value);
    }

}
