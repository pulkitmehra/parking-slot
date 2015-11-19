/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.parking.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import net.sf.oval.constraint.Min;
import net.sf.oval.guard.Guarded;
import com.nike.parking.core.GarageRepository;
import com.nike.parking.core.ParkingFeeCalculatorService;
import com.nike.parking.core.ParkingFeeException;
import com.nike.parking.core.RepositoryException;
import com.nike.parking.core.ResourceNotFoundException;
import com.nike.parking.model.ParkingSlot;

/**
 * The Class ParkingFeeCalculatorServiceImpl.
 *
 * @author pulkit.mehra
 * Created: Nov 18, 2015
 */
@Guarded
public class ParkingFeeCalculatorServiceImpl implements ParkingFeeCalculatorService {

    /** The garage repository. */
    private GarageRepository garageRepository;

    /** The get parking fee. */
    private List<Function<Long, Double>> getParkingFee;

    {
        Function<Long, Double> lessThanTwoHours = parkingFeeFunction(0, 2, 5);
        Function<Long, Double> threeToTenHours = parkingFeeFunction(3, 10, 10);
        Function<Long, Double> moreThanElevenHours = parkingFeeFunction(11, Long.MAX_VALUE, 15);

        getParkingFee = Arrays.asList(lessThanTwoHours, threeToTenHours, moreThanElevenHours);

    }

    /**
     * Instantiates a new parking fee calculator service impl.
     *
     * @param garageRepository the garage repository
     */
    public ParkingFeeCalculatorServiceImpl(GarageRepository garageRepository) {
        this.garageRepository = garageRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double calculateFee(@Min(1) int garageID, @Min(1) int parkingSlotID)
        throws ResourceNotFoundException, ParkingFeeException {

        Optional<ParkingSlot> parkingSlot = Optional.empty();
        try {
            parkingSlot = garageRepository.findParkingSlot(garageID, parkingSlotID);
        }
        catch (RepositoryException e) {
            throw new ParkingFeeException(
                "Error occured in finding garage " + garageID + " or parkingId " + parkingSlotID);
        }

        if (!parkingSlot.isPresent()) {
            throw new ResourceNotFoundException(
                String.format("resource not found %s and %s ", garageID, parkingSlotID));
        }

        if (parkingSlot.get().isAvailable()) {
            throw new ParkingFeeException(
                String.format("garage id %s and parking slot %s is not occupied", garageID, parkingSlotID));
        }

        LocalDateTime parkedSince = parkingSlot.get().getParkedSince();
        final long hours = Duration.between(parkedSince, LocalDateTime.now()).toHours();

        Optional<Double> fee = getParkingFee.stream().filter((f) -> f.apply(hours) > -1)
            .map((f) -> f.apply(hours)).findFirst();

        if (!fee.isPresent() || fee.get() < 0d) {
            throw new ParkingFeeException("Error occured in calculating parking fee");
        }

        return fee.get();
    }

    /**
     * Parking fee function.
     *
     * @param lo the lo
     * @param hi the hi
     * @param charge the charge
     * @return the function
     */
    private Function<Long, Double> parkingFeeFunction(final long lo, final long hi, final double charge) {
        return (duration) -> {
            if (lo <= duration && duration <= hi) {
                return Double.valueOf(charge);
            }
            return Double.valueOf(-1);
        };
    }

}
