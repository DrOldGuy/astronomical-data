package astronomical.data.controller.model;

import lombok.Data;

/**
 * This represents a volume object as defined in the astronomical data returned
 * by the downstream REST API.
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
public class Volume {
  private Double volValue;
  private Integer volExponent;
}
