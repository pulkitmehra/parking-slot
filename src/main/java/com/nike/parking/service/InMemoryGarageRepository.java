/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.parking.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import com.nike.parking.core.GarageRepository;
import com.nike.parking.core.RepositoryException;
import com.nike.parking.model.Garage;
import com.nike.parking.model.ParkingSlot;

/**
 * The Class InMemoryGarageRepository.
 *
 * @author pulkit.mehra
 * Created: Nov 18, 2015
 */
public class InMemoryGarageRepository implements GarageRepository {

    /** The garage map. */
    private ConcurrentMap<Integer, Garage> garageMap;

    /**
     * Instantiates a new in memory garage repository.
     *
     * @param garageMap the garage map
     */
    public InMemoryGarageRepository(ConcurrentMap<Integer, Garage> garageMap) {
        this.garageMap = garageMap;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Garage> findAllGarage() throws RepositoryException {
        return garageMap.values();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Garage> findGarage(int garageId) throws RepositoryException {
        return Optional.ofNullable(garageMap.get(Integer.valueOf(garageId)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<ParkingSlot> findAllParkingSlots(int garageID) throws RepositoryException {
        return Optional.ofNullable(garageMap.get(Integer.valueOf(garageID)).getParkingSlots())
            .orElse(Collections.emptySet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ParkingSlot> findParkingSlot(int garageID, int parkingSlotID) throws RepositoryException {

        Optional<Garage> garage = Optional.ofNullable(garageMap.get(Integer.valueOf(garageID)));

        if (!garage.isPresent()) {
            return Optional.empty();
        }

        return garage.get().getParkingSlots().stream()
            .filter((p) -> Integer.valueOf(parkingSlotID).equals(p.getParkingSlotID())).findFirst();
    }

}
