/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.parking.web;

import com.nike.parking.core.ParkingFeeException;
import com.nike.parking.core.ResourceNotFoundException;

/**
 * The Interface ParkingFeeWebAdapter.
 *
 * @author pulkit.mehra
 * Created: Nov 19, 2015
 */
public interface ParkingFeeWebAdapter {

    /**
     * Calculate fee.
     *
     * @param garageID the garage id
     * @param parkingSlotID the parking slot id
     * @return the value response
     * @throws ResourceNotFoundException the resource not found exception
     * @throws ParkingFeeException the parking fee exception
     */
    public ValueResponse<Double> calculateFee(int garageID, int parkingSlotID)
        throws ResourceNotFoundException, ParkingFeeException;

}
