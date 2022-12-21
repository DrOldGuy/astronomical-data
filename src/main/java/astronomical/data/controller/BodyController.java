package astronomical.data.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import astronomical.data.controller.model.Body;
import astronomical.data.service.BodyService;
import lombok.extern.slf4j.Slf4j;

/**
 * Spring maps HTTP requests to controller methods during the Component Scan.
 * When an HTTP request comes in, it is sent to the internal Dispatcher Servlet.
 * The Dispatcher Servlet then builds the necessary method parameters and calls
 * a method in this class.
 * <p>
 * Class annotations:
 * <p>
 * 
 * @RestController This is a combination annotation. It tells Spring that HTTP
 *                 requests are mapped to methods in this class. It also tells
 *                 Spring that request bodies and responses are to be converted
 *                 to JSON.
 * 
 * @RequestMapping This is the base URI that is used to reach the methods in
 *                 this class. So, the base URI is
 *                 http://localhost:8080/astronomical/data/body.
 * 
 * @Slf4j This is a Lombok annotation that creates an SLF4J logger.
 * 
 * @author Promineo
 *
 */
@RestController
@RequestMapping("/astronomical/data/body")
@Slf4j
public class BodyController {
  /* Request injection of the body service. */
  @Autowired
  private BodyService bodyService;

  /**
   * This method requests the names of all astronomical body data from the
   * downstream REST service. This method is called by sending an HTTP GET
   * request to http://localhost:8080/astronomical/data/body.
   * 
   * @return A list of astronomical body names.
   */
  @GetMapping
  public List<String> retrieveAllBodies() {
    log.info("retrieveAllBodies called...");
    return bodyService.retrieveAllBodies();
  }

  /**
   * This method requests details on the astronomical body with the given
   * resource ID. This method is called by sending an HTTP GET request to
   * http://localhost:8080/astronomical/data/body/{resource-id}. For example,
   * http://localhost:8080/astronomical/data/body/jupiter.
   * 
   * @param id The ID of the astronomical body requested.
   * @return The body details, if found.
   */
  @GetMapping("/{id}")
  public Body retrieveBody(@PathVariable String id) {
    log.info("Retrieving body with ID={}", id);
    return bodyService.retrieveBody(id);
  }
}
