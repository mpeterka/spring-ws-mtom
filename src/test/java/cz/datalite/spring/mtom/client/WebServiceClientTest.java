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
	public void testSimpleSendAndReceive() throws Exception {
		Object result = webServiceClient.sendMtom("file.bin", "file content".getBytes());
	}
}