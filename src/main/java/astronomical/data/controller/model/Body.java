package astronomical.data.controller.model;

import java.util.List;
import lombok.Data;

@Data
public class Body {
  private String id;
  private String name;
  private String englishName;
  private Boolean isPlanet;
  private List<Moon> moons;
  private Long semimajorAxis;
  private Long perihelion;
  private Long aphelion;
  private Double eccentricity;
  private Double inclination;
  private Mass mass;
  private Volume vol;
  private Double density;
  private Double gravity;
  private Double escape;
  private Double meanRadius;
  private Double equaRadius;
  private Double polarRadius;
  private Double flattening;
  private String dimension;
  private Double sideralOrbit;
  private Double sideralRotation;
  private AroundPlanet aroundPlanet;
  private String discoveredBy;
  private String discoveryDate;
  private String alternativeName;
  private Integer axialTilt;
  private Integer avgTemp;
  private Double mainAnomaly;
  private Double argPeriapsis;
  private Double longAscNode;
  private String bodyType;
  private String rel;
}
