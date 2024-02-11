package jee.ensas.accountservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTests {

    @Value("${urls.testClientService}")
    private String clientServiceUrl;
    @Value("${urls.testGatewayService}")
    private String gatewayTestUrl;

    TestRestTemplate testRestTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();


    @Test
    public void testIntegrationWithClientService()throws Exception{
        HttpEntity<Object> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = testRestTemplate.exchange(
                clientServiceUrl,HttpMethod.GET, entity, String.class);
        assertNotNull(responseEntity);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testIntegrationWithGatewayService()throws Exception{
        HttpEntity<Object> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = testRestTemplate.exchange(
                gatewayTestUrl,HttpMethod.GET, entity, String.class);
        assertNotNull(responseEntity);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testIntegrationWithCardService()throws Exception{
        HttpEntity<Object> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = testRestTemplate.exchange(
                gatewayTestUrl,HttpMethod.GET, entity, String.class);
        assertNotNull(responseEntity);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testIntegrationWithAuthService()throws Exception{
        HttpEntity<Object> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = testRestTemplate.exchange(
                gatewayTestUrl,HttpMethod.GET, entity, String.class);
        assertNotNull(responseEntity);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testIntegrationWithParametrageService()throws Exception{
        HttpEntity<Object> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = testRestTemplate.exchange(
                gatewayTestUrl,HttpMethod.GET, entity, String.class);
        assertNotNull(responseEntity);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testIntegrationWithDiscoveryService()throws Exception{
        HttpEntity<Object> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = testRestTemplate.exchange(
                gatewayTestUrl,HttpMethod.GET, entity, String.class);
        assertNotNull(responseEntity);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
