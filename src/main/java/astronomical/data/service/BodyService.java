package astronomical.data.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import astronomical.data.controller.model.Body;
import astronomical.data.dao.BodyDao;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BodyService {
  @Autowired
  private BodyDao bodyDao;

  public List<String> retrieveAllBodies() {
    log.info("Service: retrieveAllBodies()");

    // formatter:off
    return bodyDao.retrieveAllBodies()
        .getBodies()
        .stream()
        // .map(body -> body.getId())
        .map(Body::getId)
        .sorted()
        .toList();
    // formatter:on
  }

  public Body retrieveBody(String id) {
    log.info("Retrieving body with ID={}", id);
    return bodyDao.retrieveBody(id)
        .orElseThrow(() -> new NoSuchElementException(
            "Body with ID=" + id + " was not found!"));
  }

}
