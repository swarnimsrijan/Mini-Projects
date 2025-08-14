package PaymentManagementSystem.config;


import PaymentManagementSystem.DTO.response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
/* This class handles unauthorized access attempts by returning a JSON response with an error message.
   It implements the AuthenticationEntryPoint interface from Spring Security.
   When an unauthenticated user tries to access a protected resource, this class will be invoked.
*/
// This class is part of the Payment Management System project, which uses Spring Boot for backend development.
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    // This method is called when an unauthenticated user tries to access a protected resource.
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ApiResponse<Object> apiResponse = ApiResponse.error("Unauthorized: " + authException.getMessage());
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), apiResponse);
    }
}
