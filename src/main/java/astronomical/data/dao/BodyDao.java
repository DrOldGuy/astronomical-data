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

/**
 * This class is responsible for contacting the downstream REST API to
 * retrieve astronomical body data. This class expects the downstream
 * REST API to be available at all times. Obviously, this is an
 * invalid assumption, yet this class does nothing to mitigate that
 * obvious flaw. Good luck fixing that. You can start by researching
 * the Spring Cloud Circuit Breaker pattern.
 * <p>
 * Class-level annotations:
 * 
 * @Component This tells Spring to treat this class as a Managed Bean.
 * 
 * @Slf4j This is a Lombok annotation that creates an SLF4J logger for
 *        this class.
 * 
 * @author Promineo
 *
 */
@Component
@Slf4j
public class BodyDao {

  /*
   * This annotation is used to inject application configuration data
   * (defined in application.yaml) into the annotated variable. In
   * this case, the URL of the downstream REST API is injected. Note
   * that this isn't the best way of doing this as it inseparable
   * links this class to configuration management. This is pretty-much
   * viewed as bad and evil, but it *is* convenient.
   */
  @Value("${application.astronomical.data.bodies-url}")
  private String bodiesUrl;

  /*
   * This is the REST Template used to connect with the downstream
   * service.
   */
  @Autowired
  private RestTemplate restTemplate;

  /**
   * This method retrieves all astronomical body data. Note how easy
   * it is to retrieve the data: simply call a method on the REST
   * Template. The template automatically converts the JSON reply to a
   * {@link Bodies} object.
   * 
   * @return A {@link Bodies} object that contains a list of
   *         {@link Body} objects.
   */
  public Bodies retrieveAllBodies() {
    log.info("dao: retrieveAllBodies called...");
    return restTemplate.getForObject(bodiesUrl, Bodies.class);
  }

  /**
   * Retrieve details on a single astronomical body, given the body
   * ID.
   * 
   * @param id The ID of the body to retrieve.
   * @return An Optional containing the body data if successful. If
   *         the body is not found an empty Optional is returned.
   */
  public Optional<Body> retrieveBody(String id) {
    log.info("dao: retrieving body with ID={}", id);
    String uri = bodiesUrl + "/" + id;
    Body body = null;

    try {
      /*
       * Try retrieving the object. The REST Template throws an
       * HTTPClientErrorException if the returned status code is not
       * in the 200 family.
       */
      body = restTemplate.getForObject(uri, Body.class);
    }
    catch (HttpClientErrorException e) {
      /*
       * If the status code in the exception isn't 404 (Not Found),
       * simply rethrow the exception.
       */
      if(e.getStatusCode() != HttpStatus.NOT_FOUND) {
        throw e;
      }
    }

    /*
     * If the status from the downstream REST API is 200, body will
     * not be null and so the Optional will contain an object. If the
     * REST API returns a 404 (Not Found) status, the Optional will be
     * empty. Otherwise, an exception is thrown, above.
     */
    return Optional.ofNullable(body);
  }

}
