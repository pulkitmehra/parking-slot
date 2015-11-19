/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.parking.core;

/**
 * The Interface ParkingFeeCalculatorService.
 *
 * @author pulkit.mehra Created: Nov 18, 2015
 */
public interface ParkingFeeCalculatorService {

    /**
     * Calculate fee.
     *
     * @param garageID the garage id
     * @param parkingSlotID the parking slot id
     * @return the double
     * @throws ResourceNotFoundException the resource not found exception
     * @throws ParkingFeeException the parking fee exception
     */
    public double calculateFee(int garageID, int parkingSlotID)
        throws ResourceNotFoundException, ParkingFeeException;
}
