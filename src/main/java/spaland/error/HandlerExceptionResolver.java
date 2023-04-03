package spaland.error;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public interface HandlerExceptionResolver {
    ModelAndView resolveException(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception e);


}
