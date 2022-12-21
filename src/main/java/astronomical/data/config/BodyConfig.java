package astronomical.data.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Spring does not automatically create a {@link RestTemplate} object so we must
 * do it ourselves. Managed Beans must be created in a class annotated
 * with @Configuration (or, at least this is the most straightforward way).
 * 
 * @author Promineo
 *
 */
@Configuration
public class BodyConfig {
  /**
   * Create and return a RestTemplate object using default settings. Using the
   * 
   * @Bean annotation means that the RestTemplate object is available for
   *       Dependency Injection (DI).
   * 
   * @return The REST template.
   */
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
