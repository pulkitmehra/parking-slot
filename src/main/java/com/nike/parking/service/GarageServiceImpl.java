/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.parking.service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import net.sf.oval.constraint.Min;
import com.nike.parking.core.GarageRepository;
import com.nike.parking.core.GarageService;
import com.nike.parking.core.GarageServiceException;
import com.nike.parking.core.RepositoryException;
import com.nike.parking.core.ResourceNotFoundException;
import com.nike.parking.model.Garage;
import com.nike.parking.model.ParkingSlot;
import static java.util.stream.Collectors.toSet;


/**
 * The Class GarageServiceImpl.
 *
 * @author pulkit.mehra
 * Created: Nov 18, 2015
 */
public class GarageServiceImpl implements GarageService {

    /** The garage repository. */
    private GarageRepository garageRepository;

    /**
     * Instantiates a new garage service impl.
     *
     * @param garageRepository the garage repository
     */
    public GarageServiceImpl(GarageRepository garageRepository) {
        this.garageRepository = garageRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<ParkingSlot> getParkingSlots(@Min(1) int garageID, boolean isAvailable)
        throws GarageServiceException, ResourceNotFoundException {

        Optional<Garage> garage = Optional.empty();
        try {
            garage = garageRepository.findGarage(garageID);
        }
        catch (RepositoryException e) {
            throw new GarageServiceException("Error occured in finding garage " + garageID, e);
        }

        if (!garage.isPresent()) {
            throw new ResourceNotFoundException("Resource not found " + garageID);
        }

        if (garage.get().getParkingSlots() == null) {
            return Collections.emptySet();
        }

        if (isAvailable) {
            return garage.get().getParkingSlots().stream().filter(ParkingSlot::isAvailable).collect(toSet());
        }

        return garage.get().getParkingSlots();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParkingSlot getParkingSlot(@Min(1) int garageID, @Min(1) int parkingSlotID)
        throws GarageServiceException, ResourceNotFoundException {

        Optional<ParkingSlot> parkingSlot = Optional.empty();
        try {
            parkingSlot = garageRepository.findParkingSlot(garageID, parkingSlotID);
        }
        catch (RepositoryException e) {
            throw new GarageServiceException("Error occured in finding garage " + garageID, e);
        }

        if (!parkingSlot.isPresent()) {
            throw new ResourceNotFoundException("Resource not found " + parkingSlotID);
        }

        return parkingSlot.get();
    }

}
