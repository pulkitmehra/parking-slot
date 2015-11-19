/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.parking.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * The Class Application.
 *
 * @author pulkit.mehra
 * Created: Nov 19, 2015
 */
@Configuration
@RestController
@Import({ WebConfig.class, ServiceConfig.class })
@EnableAutoConfiguration
public class Application {

    private static final String GARAGE_URL_MAPPING = "/api/services/1.0/*";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Info.
     *
     * @return the string
     */
    @RequestMapping("/hello")
    public @ResponseBody String info() {
        return "Garage app is alive";
    }

    /**
     * Handle request mapping to the dispatcherServlet.
     *
     * @param dispatcherServlet the dispatcher servlet
     * @return the servlet registration bean
     */
    @Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean reg = new ServletRegistrationBean(dispatcherServlet);
        reg.addUrlMappings(GARAGE_URL_MAPPING);
        return reg;
    }

}
