/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.parking.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nike.parking.core.ParkingFeeCalculatorService;
import com.nike.parking.core.ParkingFeeException;
import com.nike.parking.core.ResourceNotFoundException;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * The Class SpringParkingFeeWebAdapter.
 *
 * @author pulkit.mehra
 * Created: Nov 19, 2015
 */
@Controller
@RequestMapping(value = "/parkingfee")
public class SpringParkingFeeWebAdapter implements ParkingFeeWebAdapter {

    /** The parking fee calculator. */
    private ParkingFeeCalculatorService parkingFeeCalculator;

    /**
     * Instantiates a new spring parking fee web adapter.
     *
     * @param parkingFeeCalculator the parking fee calculator
     */
    @Autowired
    public SpringParkingFeeWebAdapter(ParkingFeeCalculatorService parkingFeeCalculator) {
        this.parkingFeeCalculator = parkingFeeCalculator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/garageID/{garageID}/parkingSlot/{parkingSlotID}", method = RequestMethod.GET,
        produces = { APPLICATION_JSON_VALUE })
    public @ResponseBody ValueResponse<Double> calculateFee(@PathVariable("garageID") int garageID,
        @PathVariable("parkingSlotID") int parkingSlotID)
            throws ResourceNotFoundException, ParkingFeeException {
        double calculatedFee = parkingFeeCalculator.calculateFee(garageID, parkingSlotID);
        return new ValueResponse<>(Double.valueOf(calculatedFee));
    }

}
