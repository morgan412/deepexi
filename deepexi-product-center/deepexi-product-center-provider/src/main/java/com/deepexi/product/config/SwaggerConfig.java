package com.deepexi.product.config;

import io.swagger.v3.jaxrs2.integration.OpenApiServlet;
import org.apache.catalina.filters.SetCharacterEncodingFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
public class SwaggerConfig {

    @Bean
    public ServletRegistrationBean swaggerRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean();
        registration.setServlet(new OpenApiServlet());
        registration.addInitParameter("openApi.configuration.location", "/openapi-configuration.json");
        registration.addUrlMappings("/openapi/*");
        registration.setOrder(2);
        return registration;
    }
}
