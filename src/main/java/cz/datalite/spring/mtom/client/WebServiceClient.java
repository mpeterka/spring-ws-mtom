package cz.datalite.spring.mtom.client;

import cz.datalite.spring.mtom.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.SourceExtractor;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class WebServiceClient {

	private final WebServiceTemplate webServiceTemplate = new WebServiceTemplate();

	@Autowired
	Jaxb2Marshaller jaxb2Marshaller;

	public void setDefaultUri(String defaultUri) {
		webServiceTemplate.setDefaultUri(defaultUri);
	}

	public void setMarshaller(org.springframework.oxm.Marshaller marshaller) {
		webServiceTemplate.setMarshaller(marshaller);
	}

	public Object sendMarshal(String filename, byte[] content) throws UnsupportedEncodingException {

		File message = createMessage(filename, content);

		Object result = webServiceTemplate.marshalSendAndReceive(message);

		System.out.printf("Result: %s%n", result);

		return result;
	}

	public Object sendPlain(String xml) throws UnsupportedEncodingException {

		Object result = webServiceTemplate.sendSourceAndReceive(new StringSource(xml), new SourceExtractor<Object>() {
			public Object extractData(Source source) throws IOException, TransformerException {
				return "ok: " + source;
			}
		});

		System.out.printf("Result: %s%n", result);

		return result;
	}


	protected File createMessage(String filename, byte[] content) {
		File message = new File();
		message.setFilename(filename);
		message.setContent(content);
		return message;
	}
}
