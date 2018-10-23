
package beans;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the beans package. 
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

    private final static QName _LDAPapellidos_QNAME = new QName("http://beans/", "LDAPapellidos");
    private final static QName _LDAPapellidosResponse_QNAME = new QName("http://beans/", "LDAPapellidosResponse");
    private final static QName _LDAPmail_QNAME = new QName("http://beans/", "LDAPmail");
    private final static QName _LDAPmailResponse_QNAME = new QName("http://beans/", "LDAPmailResponse");
    private final static QName _LDAPnombre_QNAME = new QName("http://beans/", "LDAPnombre");
    private final static QName _LDAPnombreResponse_QNAME = new QName("http://beans/", "LDAPnombreResponse");
    private final static QName _LoginLDAP_QNAME = new QName("http://beans/", "loginLDAP");
    private final static QName _LoginLDAPResponse_QNAME = new QName("http://beans/", "loginLDAPResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: beans
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LDAPapellidos }
     * 
     */
    public LDAPapellidos createLDAPapellidos() {
        return new LDAPapellidos();
    }

    /**
     * Create an instance of {@link LDAPapellidosResponse }
     * 
     */
    public LDAPapellidosResponse createLDAPapellidosResponse() {
        return new LDAPapellidosResponse();
    }

    /**
     * Create an instance of {@link LDAPmail }
     * 
     */
    public LDAPmail createLDAPmail() {
        return new LDAPmail();
    }

    /**
     * Create an instance of {@link LDAPmailResponse }
     * 
     */
    public LDAPmailResponse createLDAPmailResponse() {
        return new LDAPmailResponse();
    }

    /**
     * Create an instance of {@link LDAPnombre }
     * 
     */
    public LDAPnombre createLDAPnombre() {
        return new LDAPnombre();
    }

    /**
     * Create an instance of {@link LDAPnombreResponse }
     * 
     */
    public LDAPnombreResponse createLDAPnombreResponse() {
        return new LDAPnombreResponse();
    }

    /**
     * Create an instance of {@link LoginLDAP }
     * 
     */
    public LoginLDAP createLoginLDAP() {
        return new LoginLDAP();
    }

    /**
     * Create an instance of {@link LoginLDAPResponse }
     * 
     */
    public LoginLDAPResponse createLoginLDAPResponse() {
        return new LoginLDAPResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LDAPapellidos }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LDAPapellidos }{@code >}
     */
    @XmlElementDecl(namespace = "http://beans/", name = "LDAPapellidos")
    public JAXBElement<LDAPapellidos> createLDAPapellidos(LDAPapellidos value) {
        return new JAXBElement<LDAPapellidos>(_LDAPapellidos_QNAME, LDAPapellidos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LDAPapellidosResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LDAPapellidosResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://beans/", name = "LDAPapellidosResponse")
    public JAXBElement<LDAPapellidosResponse> createLDAPapellidosResponse(LDAPapellidosResponse value) {
        return new JAXBElement<LDAPapellidosResponse>(_LDAPapellidosResponse_QNAME, LDAPapellidosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LDAPmail }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LDAPmail }{@code >}
     */
    @XmlElementDecl(namespace = "http://beans/", name = "LDAPmail")
    public JAXBElement<LDAPmail> createLDAPmail(LDAPmail value) {
        return new JAXBElement<LDAPmail>(_LDAPmail_QNAME, LDAPmail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LDAPmailResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LDAPmailResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://beans/", name = "LDAPmailResponse")
    public JAXBElement<LDAPmailResponse> createLDAPmailResponse(LDAPmailResponse value) {
        return new JAXBElement<LDAPmailResponse>(_LDAPmailResponse_QNAME, LDAPmailResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LDAPnombre }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LDAPnombre }{@code >}
     */
    @XmlElementDecl(namespace = "http://beans/", name = "LDAPnombre")
    public JAXBElement<LDAPnombre> createLDAPnombre(LDAPnombre value) {
        return new JAXBElement<LDAPnombre>(_LDAPnombre_QNAME, LDAPnombre.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LDAPnombreResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LDAPnombreResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://beans/", name = "LDAPnombreResponse")
    public JAXBElement<LDAPnombreResponse> createLDAPnombreResponse(LDAPnombreResponse value) {
        return new JAXBElement<LDAPnombreResponse>(_LDAPnombreResponse_QNAME, LDAPnombreResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginLDAP }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LoginLDAP }{@code >}
     */
    @XmlElementDecl(namespace = "http://beans/", name = "loginLDAP")
    public JAXBElement<LoginLDAP> createLoginLDAP(LoginLDAP value) {
        return new JAXBElement<LoginLDAP>(_LoginLDAP_QNAME, LoginLDAP.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginLDAPResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LoginLDAPResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://beans/", name = "loginLDAPResponse")
    public JAXBElement<LoginLDAPResponse> createLoginLDAPResponse(LoginLDAPResponse value) {
        return new JAXBElement<LoginLDAPResponse>(_LoginLDAPResponse_QNAME, LoginLDAPResponse.class, null, value);
    }

}
