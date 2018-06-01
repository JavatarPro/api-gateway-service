package pro.javatar.services.apigateway.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
public class ApplicationConfiguration {

    @Bean
    @ConditionalOnProperty(name = "cors.enabled", havingValue = "true", matchIfMissing = false)
    public CorsFilter corsFilter() {

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        final CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        addExposedHeaders(config);

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

    private void addExposedHeaders(CorsConfiguration config) {
        config.addExposedHeader(HttpHeaders.WWW_AUTHENTICATE);
        config.addExposedHeader(HttpHeaders.AUTHORIZATION);
        config.addExposedHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
