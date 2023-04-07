//package spaland.config;
//
//import io.swagger.models.HttpMethod;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@EnableWebMvc
//public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowCredentials(true).maxAge(3600)
//                .allowedMethods("GET", "POST", "PUT", HttpMethod.DELETE.name(), "PATCH", HttpMethod.OPTIONS.name())
//                .allowedHeaders("*")
//                .exposedHeaders("*")
//                .allowedOriginPatterns("*");
//    }
//
//}
