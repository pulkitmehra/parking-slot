/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.parking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import com.nike.parking.core.GarageService;
import com.nike.parking.core.ParkingFeeCalculatorService;
import com.nike.parking.web.ExceptionControllerAdvice;
import com.nike.parking.web.GarageWebAdapter;
import com.nike.parking.web.ParkingFeeWebAdapter;
import com.nike.parking.web.SpringGarageWebAdapter;
import com.nike.parking.web.SpringParkingFeeWebAdapter;

/**
 * The Class WebConfig.
 *
 * @author pulkit.mehra
 * Created: Nov 19, 2015
 */
@Configuration
@EnableWebMvc
@ComponentScan({ "com.nike.parking.web" })
@Import(ServiceConfig.class)
public class WebConfig extends WebMvcConfigurationSupport {

    /**
     * Exception controller advice.
     *
     * @return the exception controller advice
     */
    @Bean
    public ExceptionControllerAdvice exceptionControllerAdvice() {
        return new ExceptionControllerAdvice();
    }

    /**
     * Garage web adapter.
     *
     * @param garageService the garage service
     * @return the garage web adapter
     */
    @Bean
    public GarageWebAdapter garageWebAdapter(GarageService garageService) {
        return new SpringGarageWebAdapter(garageService);
    }

    /**
     * Parking fee web adapter.
     *
     * @param parkingFeeCalculatorService the parking fee calculator service
     * @return the parking fee web adapter
     */
    @Bean
    public ParkingFeeWebAdapter parkingFeeWebAdapter(ParkingFeeCalculatorService parkingFeeCalculatorService) {
        return new SpringParkingFeeWebAdapter(parkingFeeCalculatorService);
    }
}
