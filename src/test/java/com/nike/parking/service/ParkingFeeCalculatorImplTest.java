/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.parking.service;

import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.nike.parking.core.GarageRepository;
import com.nike.parking.core.ParkingFeeCalculatorService;
import com.nike.parking.core.ParkingFeeException;
import com.nike.parking.core.RepositoryException;
import com.nike.parking.core.ResourceNotFoundException;
import com.nike.parking.model.ParkingSlot;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * The Class ParkingFeeCalculatorImplTest.
 *
 * @author pulkit.mehra
 * Created: Nov 18, 2015
 */
public class ParkingFeeCalculatorImplTest {

    /** The garage repository. */
    @Mock
    private GarageRepository garageRepository;

    /** The parking fee calculator. */
    private ParkingFeeCalculatorService parkingFeeCalculator;

    /**
     * Sets the up.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        parkingFeeCalculator = new ParkingFeeCalculatorServiceImpl(garageRepository);
    }

    /**
     * Calculate fee within two hours test.
     *
     * @throws ResourceNotFoundException the resource not found exception
     * @throws ParkingFeeException the parking fee exception
     * @throws RepositoryException the repository exception
     */
    @Test
    public void calculateFeeWithinTwoHoursTest()
        throws ResourceNotFoundException, ParkingFeeException, RepositoryException {

        when(garageRepository.findParkingSlot(1, 12))
            .thenReturn(Optional.of(getParkingSlot(12, minusHoursBy(1))));
        double calculateFee = parkingFeeCalculator.calculateFee(1, 12);
        verify(garageRepository).findParkingSlot(1, 12);
        assertThat(calculateFee, is(5d));
    }

    /**
     * Calculate fee within ten hours test.
     *
     * @throws ResourceNotFoundException the resource not found exception
     * @throws ParkingFeeException the parking fee exception
     * @throws RepositoryException the repository exception
     */
    @Test
    public void calculateFeeWithinTenHoursTest()
        throws ResourceNotFoundException, ParkingFeeException, RepositoryException {

        when(garageRepository.findParkingSlot(1, 12))
            .thenReturn(Optional.of(getParkingSlot(12, minusHoursBy(6))));
        double calculateFee = parkingFeeCalculator.calculateFee(1, 12);
        verify(garageRepository).findParkingSlot(1, 12);
        assertThat(calculateFee, is(10d));
    }

    /**
     * Calculate fee more thanten hours test.
     *
     * @throws ResourceNotFoundException the resource not found exception
     * @throws ParkingFeeException the parking fee exception
     * @throws RepositoryException the repository exception
     */
    @Test
    public void calculateFeeMoreThantenHoursTest()
        throws ResourceNotFoundException, ParkingFeeException, RepositoryException {

        when(garageRepository.findParkingSlot(1, 12))
            .thenReturn(Optional.of(getParkingSlot(12, minusHoursBy(11))));
        double calculateFee = parkingFeeCalculator.calculateFee(1, 12);
        verify(garageRepository).findParkingSlot(1, 12);
        assertThat(calculateFee, is(15d));

    }

    /**
     * Parking slot not found test.
     *
     * @throws RepositoryException the repository exception
     * @throws ResourceNotFoundException the resource not found exception
     * @throws ParkingFeeException the parking fee exception
     */
    @Test(expected = ResourceNotFoundException.class)
    public void parkingSlotNotFoundTest()
        throws RepositoryException, ResourceNotFoundException, ParkingFeeException {
        when(garageRepository.findParkingSlot(1, 12)).thenReturn(Optional.empty());
        parkingFeeCalculator.calculateFee(1, 12);
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
            parkingSlot.setAvailable(false);
        }
        return parkingSlot;
    }

    /**
     * Minus hours by.
     *
     * @param hoursBefore the hours before
     * @return the local date time
     */
    public LocalDateTime minusHoursBy(int hoursBefore) {
        return LocalDateTime.now().minusHours(hoursBefore);
    }

    /**
     * Adds the hours by.
     *
     * @param hoursAfter the hours after
     * @return the local date time
     */
    public LocalDateTime addHoursBy(int hoursAfter) {
        return LocalDateTime.now().plusHours(hoursAfter);
    }

}
