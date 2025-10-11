package cz.scarecrows.eventmanager.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfiguration {

    // CORS is handled by API Gateway
    // Return null to completely disable CORS processing in this service
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        return null;
    }

}
