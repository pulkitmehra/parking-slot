/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.parking.core;

import java.util.Collection;
import java.util.Optional;
import com.nike.parking.model.Garage;
import com.nike.parking.model.ParkingSlot;


/**
 * The Interface GarageRepository.
 *
 * @author pulkit.mehra
 * Created: Nov 18, 2015
 */
public interface GarageRepository {

    /**
     * Find all garage.
     *
     * @return the collection
     * @throws RepositoryException the repository exception
     */
    public Collection<Garage> findAllGarage() throws RepositoryException;

    /**
     * Find garage.
     *
     * @param garageId the garage id
     * @return the optional
     * @throws RepositoryException the repository exception
     */
    public Optional<Garage> findGarage(int garageId) throws RepositoryException;

    /**
     * Find all parking slots.
     *
     * @param garageID the garage id
     * @return the collection
     * @throws RepositoryException the repository exception
     */
    public Collection<ParkingSlot> findAllParkingSlots(int garageID) throws RepositoryException;

    /**
     * Find parking slot.
     *
     * @param garageID the garage id
     * @param parkingSlotID the parking slot id
     * @return the optional
     * @throws RepositoryException the repository exception
     */
    public Optional<ParkingSlot> findParkingSlot(int garageID, int parkingSlotID) throws RepositoryException;

}
