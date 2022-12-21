package astronomical.data.controller.error;

import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Spring maps all unhandled exceptions to methods in this class.
 * <p>
 * Class-level annotations:
 * 
 * @RestControllerAdvice This tells Spring that this class acts as a global
 *                       error handler.
 * 
 * @author Promineo
 *
 */
@RestControllerAdvice
public class GlobalErrorHandler {
  /**
   * This method handles a {@link NoSuchElementException}. This is thrown by the
   * body service if a resource with the specific ID is not found.
   * <p>
   * Method-level annotations:
   * 
   * @ExceptionHandler This specifies the type of exception handled by this
   *                   method.
   * 
   * @ResponseStatus This tells Spring to return the requested response status,
   *                 in this case, 404 (Not Found).
   * 
   * @return A message that is converted to a JSON object.
   */
  @ExceptionHandler(NoSuchElementException.class)
  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  public Map<String, String> handleNoSuchElementException(
      NoSuchElementException e) {
    return Map.of("message", e.getMessage());
  }
}
