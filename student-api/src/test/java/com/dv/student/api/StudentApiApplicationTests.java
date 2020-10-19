package com.dv.student.api;

import com.dv.student.api.continer.AbstractContainerBaseTest;
import com.dv.student.api.model.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentApiApplicationTests extends AbstractContainerBaseTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private URI getStudentEndpoint(String contextRoot) {
		try {
			return new URI("http://localhost:" + port + "/" + contextRoot);
		} catch (URISyntaxException e) {
			Assert.fail("Uanble to create url");
		}
		return null;
	}


	@Before
	public void setup() {
		restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
	}

	@DynamicPropertySource
	static void postgreSQLProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgres::getJdbcUrl);
		registry.add("spring.datasource.username", postgres::getUsername);
		registry.add("spring.datasource.password", postgres::getPassword);

	}

	@Test
	@DisplayName("+When testing e2e for search user")
	void testUserGet(){

		ResponseEntity<List> getStudentsResponse =
				this.restTemplate.getForEntity(getStudentEndpoint("students/Dee"),List.class);

		Assert.assertEquals(200, getStudentsResponse.getStatusCodeValue());
		Assert.assertEquals(2, getStudentsResponse.getBody().size());

	}

	@Test
	@DisplayName("- When testing e2e for search user invalid")
	void testUserGetWithInvalidSearch(){

		ResponseEntity<List> getStudentsResponse =
				this.restTemplate.getForEntity(getStudentEndpoint("students/ABC"),List.class);
		Assert.assertEquals(200, getStudentsResponse.getStatusCodeValue());
		Assert.assertEquals(0, getStudentsResponse.getBody().size());

	}

	@Test
	@DisplayName("+ When testing e2e for search user valid")
	void testUserGetWithValidSearch(){

		ResponseEntity<List> getStudentsResponse =
				this.restTemplate.getForEntity(getStudentEndpoint("students/Deepak"),List.class);
		Assert.assertEquals(200, getStudentsResponse.getStatusCodeValue());
		Assert.assertEquals(1, getStudentsResponse.getBody().size());

	}

}
