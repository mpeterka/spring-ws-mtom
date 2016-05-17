package cz.datalite.spring.mtom.client;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Tests for {@link WebServiceClient}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-ws-context.xml"})
public class WebServiceClientTest {


	@Autowired
	WebServiceClient webServiceClient;

	@org.junit.Test
	public void testMarshall() throws Exception {
		Object result = webServiceClient.sendMarshal("file.bin", "file content".getBytes());
	}

	/**
	 * Plain XML, no MTOM transform?
	 * @throws Exception
	 */
	@org.junit.Test
	public void testPlain() throws Exception {
		String message = "<ns:file xmlns:ns=\"http://datalite.cz/spring/mtom-message/1.0\">\n" +
				"  <ns:filename>filename</ns:filename>\n" +
				"  <ns:content>ZQ==</ns:content>\n" +
				"</ns:file>";
		Object result = webServiceClient.sendPlain(message);
	}
}