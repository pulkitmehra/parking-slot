/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.parking.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.nike.parking.core.GarageRepository;
import com.nike.parking.core.GarageService;
import com.nike.parking.core.GarageServiceException;
import com.nike.parking.core.RepositoryException;
import com.nike.parking.core.ResourceNotFoundException;
import com.nike.parking.model.Garage;
import com.nike.parking.model.ParkingSlot;
import static java.util.stream.Collectors.toSet;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;


/**
 * The Class GarageServiceImplTest.
 *
 * @author pulkit.mehra
 * Created: Nov 19, 2015
 */
public class GarageServiceImplTest {

    /** The Constant GARAGE_ID_A. */
    private static final int GARAGE_ID_A = 1;

    /** The Constant GARAGE_A_PS_2. */
    private static final int GARAGE_A_PS_2 = 12;

    /** The Constant GARAGE_A_PS_1. */
    private static final int GARAGE_A_PS_1 = 11;

    /** The garage repository. */
    @Mock
    private GarageRepository garageRepository;

    /** The garage service. */
    private GarageService garageService;

    /**
     * Sets the up.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        garageService = new GarageServiceImpl(garageRepository);
    }

    /**
     * Gets the parking slots test.
     *
     * @return the parking slots test
     * @throws GarageServiceException the garage service exception
     * @throws ResourceNotFoundException the resource not found exception
     * @throws RepositoryException the repository exception
     */
    @Test
    public void getParkingSlotsTest()
        throws GarageServiceException, ResourceNotFoundException, RepositoryException {

        when(garageRepository.findGarage(GARAGE_ID_A)).thenReturn(Optional.of(getGarage()));
        Set<ParkingSlot> parkingSlots = garageService.getParkingSlots(GARAGE_ID_A, false);

        assertThat(parkingSlots, hasSize(2));
        assertThat(parkingSlots.stream().map(ParkingSlot::getParkingSlotID).collect(toSet()),
            Matchers.containsInAnyOrder(GARAGE_A_PS_1, GARAGE_A_PS_2));
    }

    /**
     * Gets the available parking slots test.
     *
     * @return the available parking slots test
     * @throws GarageServiceException the garage service exception
     * @throws ResourceNotFoundException the resource not found exception
     * @throws RepositoryException the repository exception
     */
    @Test
    public void getAvailableParkingSlotsTest()
        throws GarageServiceException, ResourceNotFoundException, RepositoryException {

        when(garageRepository.findGarage(GARAGE_ID_A)).thenReturn(Optional.of(getGarage()));
        Set<ParkingSlot> parkingSlots = garageService.getParkingSlots(GARAGE_ID_A, true);

        assertThat(parkingSlots, hasSize(1));
        assertThat(parkingSlots.stream().map(ParkingSlot::getParkingSlotID).collect(toSet()),
            Matchers.containsInAnyOrder(GARAGE_A_PS_2));
    }

    /**
     * Gets the available parking slots invalid garage id test.
     *
     * @return the available parking slots invalid garage id test
     * @throws GarageServiceException the garage service exception
     * @throws ResourceNotFoundException the resource not found exception
     * @throws RepositoryException the repository exception
     */
    @Test(expected = ResourceNotFoundException.class)
    public void getAvailableParkingSlotsInvalidGarageIDTest()
        throws GarageServiceException, ResourceNotFoundException, RepositoryException {

        int invalidGarageID = 100;
        when(garageRepository.findGarage(invalidGarageID)).thenReturn(Optional.empty());
        garageService.getParkingSlots(invalidGarageID, true);
    }

    /**
     * Gets the empty parking slots test.
     *
     * @return the empty parking slots test
     * @throws GarageServiceException the garage service exception
     * @throws ResourceNotFoundException the resource not found exception
     * @throws RepositoryException the repository exception
     */
    @Test
    public void getEmptyParkingSlotsTest()
        throws GarageServiceException, ResourceNotFoundException, RepositoryException {
        when(garageRepository.findGarage(GARAGE_ID_A)).thenReturn(Optional.of(new Garage()));
        Set<ParkingSlot> parkingSlots = garageService.getParkingSlots(GARAGE_ID_A, true);

        assertThat(parkingSlots, empty());
    }

    /**
     * Gets the parking slot test.
     *
     * @return the parking slot test
     * @throws GarageServiceException the garage service exception
     * @throws ResourceNotFoundException the resource not found exception
     * @throws RepositoryException the repository exception
     */
    @Test
    public void getParkingSlotTest()
        throws GarageServiceException, ResourceNotFoundException, RepositoryException {

        when(garageRepository.findParkingSlot(GARAGE_ID_A, GARAGE_A_PS_1))
            .thenReturn(Optional.of(getParkingSlot()));
        ParkingSlot parkingSlot = garageService.getParkingSlot(GARAGE_ID_A, GARAGE_A_PS_1);

        assertThat(parkingSlot.getParkingSlotID(), is(GARAGE_A_PS_1));
    }

    /**
     * Invalid parking slot test.
     *
     * @throws GarageServiceException the garage service exception
     * @throws ResourceNotFoundException the resource not found exception
     * @throws RepositoryException the repository exception
     */
    @Test(expected = ResourceNotFoundException.class)
    public void invalidParkingSlotTest()
        throws GarageServiceException, ResourceNotFoundException, RepositoryException {
        int invalidGarageID = 100;
        when(garageRepository.findParkingSlot(invalidGarageID, GARAGE_A_PS_1)).thenReturn(Optional.empty());
        garageService.getParkingSlot(100, GARAGE_A_PS_1);
    }

    /**
     * Gets the parking slot.
     *
     * @param id the id
     * @param parkedSince the parked since
     * @return the parking slot
     */
    private ParkingSlot getParkingSlot(int id, LocalDateTime parkedSince) {
        ParkingSlot parkingSlot = new ParkingSlot();
        parkingSlot.setParkingSlotID(id);
        parkingSlot.setParkedSince(parkedSince);
        if (parkedSince != null) {
            parkingSlot.setAvailable(true);
        }
        return parkingSlot;
    }

    /**
     * Gets the garage.
     *
     * @return the garage
     */
    private static Garage getGarage() {

        Set<ParkingSlot> parkingSlotsSet = new HashSet<>();
        ParkingSlot p = getParkingSlot();
        parkingSlotsSet.add(p);

        p = new ParkingSlot();
        p.setParkingSlotID(GARAGE_A_PS_2);
        parkingSlotsSet.add(p);

        Garage g = new Garage();
        g.setGarageID(GARAGE_ID_A);
        g.setParkingSlots(parkingSlotsSet);
        return g;
    }

    /**
     * Minus hours by.
     *
     * @param hoursBefore the hours before
     * @return the local date time
     */
    private static LocalDateTime minusHoursBy(int hoursBefore) {
        return LocalDateTime.now().minusHours(hoursBefore);
    }

    /**
     * Gets the parking slot.
     *
     * @return the parking slot
     */
    private static ParkingSlot getParkingSlot() {
        ParkingSlot p = new ParkingSlot();
        p.setParkingSlotID(GARAGE_A_PS_1);
        p.setAvailable(false);
        p.setParkedSince(minusHoursBy(5));
        return p;
    }

}
