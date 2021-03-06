
package beans;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.0
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "ServiceLogin", targetNamespace = "http://beans/", wsdlLocation = "file:/home/harold/Escritorio/Gestor_Expedientes/src/conf/xml-resources/web-services/NewWebServiceFromWSDL/wsdl/localhost_8080/ServiceLogin/ServiceLogin.wsdl")
public class ServiceLogin_Service
    extends Service
{

    private final static URL SERVICELOGIN_WSDL_LOCATION;
    private final static WebServiceException SERVICELOGIN_EXCEPTION;
    private final static QName SERVICELOGIN_QNAME = new QName("http://beans/", "ServiceLogin");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/home/harold/Escritorio/Gestor_Expedientes/src/conf/xml-resources/web-services/NewWebServiceFromWSDL/wsdl/localhost_8080/ServiceLogin/ServiceLogin.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SERVICELOGIN_WSDL_LOCATION = url;
        SERVICELOGIN_EXCEPTION = e;
    }

    public ServiceLogin_Service() {
        super(__getWsdlLocation(), SERVICELOGIN_QNAME);
    }

    public ServiceLogin_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), SERVICELOGIN_QNAME, features);
    }

    public ServiceLogin_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICELOGIN_QNAME);
    }

    public ServiceLogin_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SERVICELOGIN_QNAME, features);
    }

    public ServiceLogin_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ServiceLogin_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ServiceLogin
     */
    @WebEndpoint(name = "ServiceLoginPort")
    public ServiceLogin getServiceLoginPort() {
        return super.getPort(new QName("http://beans/", "ServiceLoginPort"), ServiceLogin.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ServiceLogin
     */
    @WebEndpoint(name = "ServiceLoginPort")
    public ServiceLogin getServiceLoginPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://beans/", "ServiceLoginPort"), ServiceLogin.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SERVICELOGIN_EXCEPTION!= null) {
            throw SERVICELOGIN_EXCEPTION;
        }
        return SERVICELOGIN_WSDL_LOCATION;
    }

}
