package astronomical.data.dao;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import astronomical.data.controller.model.Bodies;
import astronomical.data.controller.model.Body;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BodyDao {

  @Value("${application.astronomical.data.bodies-url}")
  private String bodiesUrl;

  @Autowired
  private RestTemplate restTemplate;

  public Bodies retrieveAllBodies() {
    log.info("dao: retrieveAllBodies called...");
    return restTemplate.getForObject(bodiesUrl, Bodies.class);
  }

  public Optional<Body> retrieveBody(String id) {
    log.info("dao: retrieving body with ID={}", id);
    String uri = bodiesUrl + "/" + id;
    Body body = null;

    try {
      body = restTemplate.getForObject(uri, Body.class);
    }
    catch (HttpClientErrorException e) {
      if(e.getStatusCode() != HttpStatus.NOT_FOUND) {
        throw e;
      }
    }

    return Optional.ofNullable(body);
  }

}
