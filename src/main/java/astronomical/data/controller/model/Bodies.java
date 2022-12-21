package astronomical.data.controller.model;

import java.util.List;
import lombok.Data;

/**
 * This class is used to accept a JSON object from the downstream service.
 * <p>
 * Class-level annotations:
 * 
 * @Data This is a Lombok annotation. It creates getters and setters for all
 *       instance variables, as well as toString, hashCode, and equals methods.
 * 
 * @author Promineo
 *
 */
@Data
public class Bodies {
  List<Body> bodies;
}
