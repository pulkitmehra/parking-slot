/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.parking.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.junit.Before;
import org.junit.Test;
import com.nike.parking.core.GarageRepository;
import com.nike.parking.core.RepositoryException;
import com.nike.parking.model.Garage;
import com.nike.parking.model.ParkingSlot;
import static java.util.stream.Collectors.toSet;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * The Class InMemoryGarageRepositoryTest.
 *
 * @author pulkit.mehra
 * Created: Nov 18, 2015
 */
public class InMemoryGarageRepositoryTest {

    /** The Constant GARAGE_ID_A. */
    private static final int GARAGE_ID_A = 1;

    /** The Constant GARAGE_A_PS_2. */
    private static final int GARAGE_A_PS_2 = 12;

    /** The Constant GARAGE_A_PS_1. */
    private static final int GARAGE_A_PS_1 = 11;

    /** The Constant GARAGE_ID_B. */
    private static final int GARAGE_ID_B = 2;

    /** The Constant GARAGE_B_PS_2. */
    private static final int GARAGE_B_PS_2 = 22;

    /** The Constant GARAGE_B_PS_1. */
    private static final int GARAGE_B_PS_1 = 21;

    /** The Constant GARAGE_B_PS_3. */
    private static final int GARAGE_B_PS_3 = 23;

    /** The garage map. */
    private ConcurrentMap<Integer, Garage> garageMap = new ConcurrentHashMap<>();

    /** The garage repository. */
    private GarageRepository garageRepository = new InMemoryGarageRepository(garageMap);

    /**
     * Sets the up.
     */
    @Before
    public void setUp() {
        Garage garageA = getGarage(GARAGE_ID_A, GARAGE_A_PS_1, GARAGE_A_PS_2);
        garageMap.put(garageA.getGarageID(), garageA);

        Garage garageB = getGarage(GARAGE_ID_B, GARAGE_B_PS_1, GARAGE_B_PS_2, GARAGE_B_PS_3);
        garageMap.put(garageB.getGarageID(), garageB);

    }

    /**
     * Find all garage test.
     *
     * @throws RepositoryException the repository exception
     */
    @Test
    public void findAllGarageTest() throws RepositoryException {
        Collection<Garage> garages = garageRepository.findAllGarage();
        assertThat(garages.size(), is(2));

        assertThat(garages.stream().map(Garage::getGarageID).collect(toSet()),
            containsInAnyOrder(GARAGE_ID_A, GARAGE_ID_B));
    }

    /**
     * Find garage test.
     *
     * @throws RepositoryException the repository exception
     */
    @Test
    public void findGarageTest() throws RepositoryException {
        Optional<Garage> garage = garageRepository.findGarage(GARAGE_ID_A);
        assertTrue(garage.isPresent());
        assertThat(garage.get().getGarageID(), is(GARAGE_ID_A));

        garage = garageRepository.findGarage(GARAGE_ID_B);
        assertTrue(garage.isPresent());
        assertThat(garage.get().getGarageID(), is(GARAGE_ID_B));
    }

    /**
     * Find all parking slots test.
     *
     * @throws RepositoryException the repository exception
     */
    @Test
    public void findAllParkingSlotsTest() throws RepositoryException {
        Collection<ParkingSlot> findAllParkingSlots = garageRepository.findAllParkingSlots(GARAGE_ID_A);
        assertThat(findAllParkingSlots, hasSize(2));
        assertThat(findAllParkingSlots.stream().map(ParkingSlot::getParkingSlotID).collect(toSet()),
            containsInAnyOrder(GARAGE_A_PS_1, GARAGE_A_PS_2));
    }

    /**
     * Find parking slot test.
     *
     * @throws RepositoryException the repository exception
     */
    @Test
    public void findParkingSlotTest() throws RepositoryException {
        Optional<ParkingSlot> parkingSlot = garageRepository.findParkingSlot(GARAGE_ID_A, GARAGE_A_PS_1);
        assertTrue(parkingSlot.isPresent());
        assertThat(parkingSlot.get().getParkingSlotID(), is(GARAGE_A_PS_1));
    }

    /**
     * Gets the garage.
     *
     * @param garageID the garage id
     * @param parkingslots the parkingslots
     * @return the garage
     */
    private static Garage getGarage(int garageID, int... parkingslots) {

        Set<ParkingSlot> parkingSlotsSet = new HashSet<>();
        for (int parkingSlotIds : parkingslots) {
            ParkingSlot p = new ParkingSlot();
            p.setParkingSlotID(parkingSlotIds);
            parkingSlotsSet.add(p);
        }

        Garage g = new Garage();
        g.setGarageID(garageID);
        g.setParkingSlots(parkingSlotsSet);
        return g;
    }
}
