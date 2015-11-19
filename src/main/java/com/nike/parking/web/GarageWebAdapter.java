/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.parking.web;

import com.nike.parking.core.GarageServiceException;
import com.nike.parking.core.ResourceNotFoundException;
import com.nike.parking.model.ParkingSlot;

/**
 * The Interface GarageWebAdapter.
 *
 * @author pulkit.mehra
 * Created: Nov 19, 2015
 */
public interface GarageWebAdapter {

    /**
     * Gets the parking slots.
     *
     * @param garageID the garage id
     * @param isAvailable the is available
     * @return the parking slots
     * @throws GarageServiceException the garage service exception
     * @throws ResourceNotFoundException the resource not found exception
     */
    public ParkingSlotWrapper getParkingSlots(int garageID, boolean isAvailable)
        throws GarageServiceException, ResourceNotFoundException;

    /**
     * Gets the parking slot.
     *
     * @param garageID the garage id
     * @param parkingSlotID the parking slot id
     * @return the parking slot
     * @throws GarageServiceException the garage service exception
     * @throws ResourceNotFoundException the resource not found exception
     */
    public ParkingSlot getParkingSlot(int garageID, int parkingSlotID)
        throws GarageServiceException, ResourceNotFoundException;

}
