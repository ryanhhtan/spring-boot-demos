package com.example.testspringcloudcontractwiremock;

import static org.assertj.core.api.Assertions.*;
import java.util.List;
import com.example.testspringcloudcontractwiremock.pojo.Todo;
import com.example.testspringcloudcontractwiremock.service.JsonPlaceHolderService;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.wiremock.WireMockSpring;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Slf4j
public class DemoApplicationTests {
  @Autowired
  private JsonPlaceHolderService jshs;

  @Autowired
  private TestRestTemplate rest;

  @ClassRule
	public static WireMockClassRule wiremock = new WireMockClassRule(
			WireMockSpring.options().dynamicPort().extensions(new ResponseTemplateTransformer(false)));
			// WireMockSpring.options().dynamicPort());

  @Before
  public void setUp() {
    jshs.setUrl(
        "http://localhost:" + wiremock.port() + "/jshs");
  }

  @Test
  public void testGetOneTodo() {
    ResponseEntity<Todo> response = rest.getForEntity("/todos/1", Todo.class);
    log.info("todo: {}", response.getBody());
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().getId()).isNotNull();
    assertThat(response.getBody().getUserId()).isNotNull();
    assertThat(response.getBody().getTitle()).isNotNull();
    assertThat(response.getBody().getCompleted()).isNotNull();
  }

  @Test
  public void testGetTodoLists_shouldSucceed() {
    ResponseEntity<List<Todo>> response =
        rest.exchange("/todos", HttpMethod.GET, null, new ParameterizedTypeReference<List<Todo>>() {
        });
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().size()).isGreaterThan(0);
  }

  @Test
  public void testCreateTodo_shouldSucceed() {
    final Todo newTodo = Todo.builder().userId(1L).title("new todo").completed(false).build(); 
    final ResponseEntity<Todo> response = rest.postForEntity("/todos", newTodo, Todo.class);
    log.info("created todo: {}", response.getBody());
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().getId()).isNotNull();
    assertThat(response.getBody().getUserId()).isEqualTo(newTodo.getUserId());
    assertThat(response.getBody().getTitle()).isEqualTo(newTodo.getTitle());
    assertThat(response.getBody().getCompleted()).isEqualTo(newTodo.getCompleted());
  }
}
