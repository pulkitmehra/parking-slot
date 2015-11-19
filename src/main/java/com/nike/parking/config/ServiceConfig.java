/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.parking.config;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.nike.parking.core.GarageRepository;
import com.nike.parking.core.GarageService;
import com.nike.parking.core.ParkingFeeCalculatorService;
import com.nike.parking.model.Garage;
import com.nike.parking.model.ParkingSlot;
import com.nike.parking.model.Vehicle;
import com.nike.parking.service.GarageServiceImpl;
import com.nike.parking.service.InMemoryGarageRepository;
import com.nike.parking.service.ParkingFeeCalculatorServiceImpl;


/**
 * Service side spring config.
 *
 * @author pulkit.mehra
 * Created: Nov 19, 2015
 */
@Configuration
public class ServiceConfig {

    /**
     * Garage repository.
     *
     * @return the garage repository
     */
    @Bean
    public GarageRepository garageRepository() {
        return new InMemoryGarageRepository(getStaticData());
    }

    /**
     * Garage service.
     *
     * @param garageRepository the garage repository
     * @return the garage service
     */
    @Bean
    public GarageService garageService(GarageRepository garageRepository) {
        return new GarageServiceImpl(garageRepository);
    }

    /**
     * Parking fee calculator service.
     *
     * @param garageRepository the garage repository
     * @return the parking fee calculator service
     */
    @Bean
    public ParkingFeeCalculatorService parkingFeeCalculatorService(GarageRepository garageRepository) {
        return new ParkingFeeCalculatorServiceImpl(garageRepository);
    }

    /**
     * Gets the static data.
     *
     * @return the static data
     */
    private ConcurrentMap<Integer, Garage> getStaticData() {
        ConcurrentMap<Integer, Garage> map = new ConcurrentHashMap<Integer, Garage>();

        Vehicle v = vehicle(111, "VRG 1234");
        ParkingSlot ps1 = parkingSlot(false, minusHoursBy(1), 11, v);

        Vehicle v2 = vehicle(112, "BOS 2291");
        ParkingSlot ps2 = parkingSlot(false, minusHoursBy(5), 12, v2);
        ParkingSlot ps3 = parkingSlot(true, null, 13, null);

        map.putIfAbsent(1, garage(1, ps1, ps2, ps3));

        Vehicle v3 = vehicle(113, "AUX 5167");
        ParkingSlot ps4 = parkingSlot(false, minusHoursBy(6), 21, v3);

        Vehicle v4 = vehicle(114, "CA 121");
        ParkingSlot ps5 = parkingSlot(false, minusHoursBy(1), 22, v4);

        map.putIfAbsent(2, garage(2, ps4, ps5));

        return map;
    }

    /**
     * Minus hours by.
     *
     * @param hoursBefore the hours before
     * @return the local date time
     */
    private LocalDateTime minusHoursBy(int hoursBefore) {
        return LocalDateTime.now().minusHours(hoursBefore);
    }

    /**
     * Parking slot.
     *
     * @param isAvailable the is available
     * @param parkedSince the parked since
     * @param parkingSlotID the parking slot id
     * @param parkedVehicle the parked vehicle
     * @return the parking slot
     */
    private ParkingSlot parkingSlot(boolean isAvailable, LocalDateTime parkedSince, int parkingSlotID,
        Vehicle parkedVehicle) {
        ParkingSlot ps = new ParkingSlot();
        ps.setAvailable(isAvailable);
        ps.setParkedSince(parkedSince);
        ps.setParkingSlotID(parkingSlotID);
        ps.setParkedVehicle(parkedVehicle);
        return ps;
    }

    /**
     * Vehicle.
     *
     * @param vehicleID the vehicle id
     * @param veichleRegisteration the veichle registeration
     * @return the vehicle
     */
    private Vehicle vehicle(int vehicleID, String veichleRegisteration) {
        Vehicle v = new Vehicle();
        v.setVehicleID(vehicleID);
        v.setVehicleRegisteration(veichleRegisteration);
        return v;
    }

    /**
     * Garage.
     *
     * @param garageID the garage id
     * @param parkingSlots the parking slots
     * @return the garage
     */
    private Garage garage(int garageID, ParkingSlot... parkingSlots) {

        Set<ParkingSlot> parkingSlotSet = new HashSet<>();
        for (ParkingSlot parkingSlot : parkingSlots) {
            parkingSlotSet.add(parkingSlot);
        }

        Garage garage = new Garage();
        garage.setGarageID(garageID);
        garage.setParkingSlots(parkingSlotSet);
        return garage;
    }

}
