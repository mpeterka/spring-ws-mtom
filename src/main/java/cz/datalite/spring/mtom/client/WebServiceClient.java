package cz.datalite.spring.mtom.client;

import cz.datalite.spring.mtom.model.File;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.oxm.Marshaller;
import org.springframework.ws.client.core.SourceExtractor;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

import static javax.xml.XMLConstants.ACCESS_EXTERNAL_DTD;
import static javax.xml.XMLConstants.ACCESS_EXTERNAL_STYLESHEET;
import static javax.xml.transform.OutputKeys.ENCODING;
import static javax.xml.transform.OutputKeys.INDENT;
import static javax.xml.transform.OutputKeys.METHOD;
import static javax.xml.transform.OutputKeys.OMIT_XML_DECLARATION;

public class WebServiceClient {
    private final Log logger = LogFactory.getLog(WebServiceClient.class);

    private final WebServiceTemplate webServiceTemplate = new WebServiceTemplate();

    public WebServiceClient(String defaultUri, Marshaller marshaller) {
        webServiceTemplate.setDefaultUri(defaultUri);
        webServiceTemplate.setMarshaller(marshaller);
    }

    public Object sendMarshal(String filename, byte[] content) {
        final File message = createMessage(filename, content);
        final Object result = webServiceTemplate.marshalSendAndReceive(message);
        logger.debug("Result: %s%n" + result);
        return result;
    }

    public String sendPlain(String xml) {
        String result = webServiceTemplate.sendSourceAndReceive(new StringSource(xml), new StringSourceExtractor());
        logger.debug("Result: %s%n" + result);
        return result;
    }


    protected File createMessage(String filename, byte[] content) {
        File message = new File();
        message.setFilename(filename);
        message.setContent(content);
        return message;
    }

    private static class StringSourceExtractor implements SourceExtractor<String> {
        public String extractData(Source source) {
            try {
                TransformerFactory tf = TransformerFactory.newInstance();
                tf.setAttribute(ACCESS_EXTERNAL_DTD, "");
                tf.setAttribute(ACCESS_EXTERNAL_STYLESHEET, "");
                Transformer transformer = tf.newTransformer();
                transformer.setOutputProperty(OMIT_XML_DECLARATION, "no");
                transformer.setOutputProperty(METHOD, "xml");
                transformer.setOutputProperty(INDENT, "yes");
                transformer.setOutputProperty(ENCODING, "UTF-8");
                StringWriter sw = new StringWriter();
                transformer.transform(source, new StreamResult(sw));
                return sw.toString();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
