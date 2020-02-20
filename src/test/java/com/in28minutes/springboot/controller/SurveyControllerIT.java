package com.in28minutes.springboot.controller;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.springboot.Application;
import com.in28minutes.springboot.model.Question;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerIT {
	
	@LocalServerPort
	private int port;

	@Test
	public void testRetrieveSurveyQuestion() {
		String url = "http://localhost:"+port+"/survey1/questions/Question1";
		TestRestTemplate restTemplate = new TestRestTemplate();
		
		// String output = restTemplate.getForObject(url, String.class);
		
		// HttpEntity - headers
		// Accept : application/json
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		HttpEntity entity = new HttpEntity<String>(null, headers);
	
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity , String.class);
		
		System.out.println("Response : " + response.getBody());
		
		
	}
	
	@Test
	public void addQuestion() {
		String url = "http://localhost:"+port+"/surveys/survey1/questions";
		TestRestTemplate restTemplate = new TestRestTemplate();
		
		// String output = restTemplate.getForObject(url, String.class);
		
		// HttpEntity - headers
		// Accept : application/json
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		Question question = new Question("doesntmatter",
				"Question 1", "Russia", Arrays.asList(
						"India", "Russia", "United States", "China"));
		
		HttpEntity entity = new HttpEntity<Question>(question, headers);
	
		ResponseEntity<String> response = restTemplate.exchange(url,
				HttpMethod.POST, entity , String.class);
		
		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
		
		System.out.println(actual);
		
		assertTrue(actual.contains("/surveys/Survey1/questions/"));
		
		//System.out.println("Response : " + response.getBody());
		
		
	}

}
