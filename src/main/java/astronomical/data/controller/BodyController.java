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

@RestController
@RequestMapping("/astronomical/data/body")
@Slf4j
public class BodyController {
  @Autowired
  private BodyService bodyService;
  
  @GetMapping
  public List<String> retrieveAllBodies() {
    log.info("retrieveAllBodies called...");
    return bodyService.retrieveAllBodies();
  }
  
  /**
   *  /astronomical/data/body/lune
   */
  @GetMapping("/{id}")
  public Body retrieveBody(@PathVariable String id) {
    log.info("Retrieving body with ID={}", id);
    return bodyService.retrieveBody(id);
  }
}
