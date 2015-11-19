/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.parking.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nike.parking.core.GarageService;
import com.nike.parking.core.GarageServiceException;
import com.nike.parking.core.ResourceNotFoundException;
import com.nike.parking.model.ParkingSlot;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * The Class SpringGarageWebAdapter.
 *
 * @author pulkit.mehra
 * Created: Nov 19, 2015
 */
@Controller
@RequestMapping(value = "/garage")
public class SpringGarageWebAdapter implements GarageWebAdapter {

    /** The garage service. */
    private GarageService garageService;

    /**
     * Instantiates a new spring garage web adapter.
     *
     * @param garageService the garage service
     */
    @Autowired
    public SpringGarageWebAdapter(GarageService garageService) {
        this.garageService = garageService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/{garageID}/parkingSlots", method = RequestMethod.GET,
        produces = { APPLICATION_JSON_VALUE })
    public @ResponseBody ParkingSlotWrapper getParkingSlots(@PathVariable("garageID") int garageID,
        @RequestParam(value = "isAvailable", required = false) boolean isAvailable)
            throws GarageServiceException, ResourceNotFoundException {
        ParkingSlotWrapper parkingSlotWrapper = new ParkingSlotWrapper();
        parkingSlotWrapper.getParkingSlots().addAll(garageService.getParkingSlots(garageID, isAvailable));
        return parkingSlotWrapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/{garageID}/parkingSlot/{parkingSlotID}", method = RequestMethod.GET,
        produces = { APPLICATION_JSON_VALUE })
    public @ResponseBody ParkingSlot getParkingSlot(@PathVariable("garageID") int garageID,
        @PathVariable("parkingSlotID") int parkingSlotID)
            throws GarageServiceException, ResourceNotFoundException {
        return garageService.getParkingSlot(garageID, parkingSlotID);
    }

}
