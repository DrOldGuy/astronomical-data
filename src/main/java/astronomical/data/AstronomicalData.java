package astronomical.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class contains the entry point of a Java application. It starts and
 * configures Spring Boot.
 * 
 * The class-level annotation @SpringBootApplication is a combination
 * annotation. First, it starts the component scan in this package and all
 * subpackages. Second, it enables auto-configuration, which is Spring Boot's
 * way of adding an opinionation layer on top of Spring.
 * 
 * @author Promineo
 *
 */
@SpringBootApplication
public class AstronomicalData {

  /**
   * Start Spring Boot.
   * 
   * @param args
   */
  public static void main(String[] args) {
    SpringApplication.run(AstronomicalData.class, args);
  }
}
