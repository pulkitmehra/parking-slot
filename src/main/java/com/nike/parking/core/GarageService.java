/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.parking.core;

import java.util.Set;
import com.nike.parking.model.ParkingSlot;


/**
 * The Interface GarageService.
 *
 * @author pulkit.mehra
 * Created: Nov 18, 2015
 */
public interface GarageService {

    /**
     * Gets the parking slots.
     *
     * @param garageID the garage id
     * @param isAvailable the is available
     * @return the parking slots
     * @throws GarageServiceException the garage service exception
     * @throws ResourceNotFoundException the resource not found exception
     */
    public Set<ParkingSlot> getParkingSlots(int garageID, boolean isAvailable)
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
