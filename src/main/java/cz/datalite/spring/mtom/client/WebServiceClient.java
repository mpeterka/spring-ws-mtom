package cz.datalite.spring.mtom.client;

import cz.datalite.spring.mtom.model.File;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.io.UnsupportedEncodingException;

public class WebServiceClient {

	private final WebServiceTemplate webServiceTemplate = new WebServiceTemplate();

	public void setDefaultUri(String defaultUri) {
		webServiceTemplate.setDefaultUri(defaultUri);
	}

	public void setMarshaller(org.springframework.oxm.Marshaller marshaller) {
		webServiceTemplate.setMarshaller(marshaller);
	}

	public Object sendMtom(String filename, byte[] content) throws UnsupportedEncodingException {

		File message = createMessage(filename, content);

		Object result = webServiceTemplate.marshalSendAndReceive(message);

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
