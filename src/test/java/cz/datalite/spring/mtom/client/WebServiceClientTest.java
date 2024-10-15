package cz.datalite.spring.mtom.client;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link WebServiceClient}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-ws-context.xml"})
public class WebServiceClientTest {

    private static final Log logger = LogFactory.getLog(WebServiceClientTest.class);

    @Autowired
    WebServiceClient webServiceClient;

    private MockWebServer mockWebServer;

    @Before
    public void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start(3333);
        mockWebServer.setDispatcher(new EchoDispatcher());
        logger.info("Started " + mockWebServer);
    }

    @After
    public void tearDown() throws IOException {
        mockWebServer.close();
    }

    /**
     * This method throws exception because of MTOM.
     * <p>
     * This is the proof that there is an error.
     */
    @Test(expected = org.springframework.oxm.MarshallingFailureException.class)
    public void testMarshallMtom() {
        webServiceClient.sendMarshal("file.bin", "file content".getBytes());
    }

    /**
     * Plain XML, no MTOM transform?
     */
    @Test
    public void testPlain() {
        String message = """
                <ns:file xmlns:ns="http://datalite.cz/spring/mtom-message/1.0">
                  <ns:filename>filename</ns:filename>
                  <ns:content>ZQ==</ns:content>
                </ns:file>""";
        String result = webServiceClient.sendPlain(message);
        assertTrue(result, result.contains("ZQ=="));
    }

    private static class EchoDispatcher extends Dispatcher {
        @NotNull
        @Override
        public MockResponse dispatch(@NotNull RecordedRequest recordedRequest) {
            return new MockResponse()
                    .setResponseCode(HttpStatus.OK.value())
                    .setBody(recordedRequest.getBody());
        }
    }
}