package astronomical.data.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import astronomical.data.controller.model.Body;
import astronomical.data.dao.BodyDao;
import lombok.extern.slf4j.Slf4j;

/**
 * This class is part of the service layer for the astronomical body
 * data REST API.
 * <p>
 * Class-level annotations:
 * 
 * @Service This annotation tells Spring that this class is a Managed
 *          Bean in the service layer.
 * 
 * @Slf4j Instructs Lombok to create an SLF4J logger for this class.
 * 
 * @author Promineo
 *
 */
@Service
@Slf4j
public class BodyService {
  /* Requests that the body DAO object is injected here. */
  @Autowired
  private BodyDao bodyDao;

  /**
   * Retrieve the IDs of all bodies. A list of {@link Body} data is
   * returned by the DAO. This method pulls the IDs out of the body
   * details and returns that list.
   * 
   * @return The list of body IDs.
   */
  public List<String> retrieveAllBodies() {
    log.info("Service: retrieveAllBodies()");

    /*
     * This retrieves the List of Body objects from the DAO, then
     * converts it to a Stream of Body objects. The body ID is then
     * pulled out of the objects to form a Stream of bodyID (String).
     * Two ways are supplied for you to look at: 1) a commented-out
     * method that employs a Lambda expression, and 2) a map method
     * that uses a method reference. They both do exactly the same
     * thing. Finally, the IDs are sorted alphabetically and returned
     * as a List of String.
     */
    return bodyDao.retrieveAllBodies()
        .getBodies() // @formatter:off
        .stream()
        // .map(body -> body.getId())
        .map(Body::getId)
        .sorted()
        .toList(); // @formatter:on
  }

  /**
   * Retrieves a single {@link Body} details object from the
   * downstream REST API.
   * 
   * @param id The ID of the body to retrieve.
   * @return The body details.
   * @throws NoSuchElementException Thrown if the given body ID is not
   *         found.
   */
  public Body retrieveBody(String id) {
    log.info("Retrieving body with ID={}", id);

    return bodyDao.retrieveBody(id)
        .orElseThrow(() -> new NoSuchElementException(
            "Body with ID=" + id + " was not found!"));
  }

}
