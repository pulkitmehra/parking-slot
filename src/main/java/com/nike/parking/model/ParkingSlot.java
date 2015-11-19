/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.parking.model;

import java.time.LocalDateTime;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;


/**
 * The Class ParkingSlot.
 *
 * @author pulkit.mehra
 * Created: Nov 18, 2015
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "parkingSlotID", "parkedVehicle", "parkedSince", "isAvailable" })
@XmlRootElement(name = "ParkingSlot")
@JsonTypeName("ParkingSlot")
public class ParkingSlot {

    /** The parking slot id. */
    private int parkingSlotID;

    /** The parked vehicle. */
    private Vehicle parkedVehicle;

    /** The parked since. */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime parkedSince;

    /** The is available. */
    private boolean isAvailable = true;

    /**
     * Gets the parking slot id.
     *
     * @return the parking slot id
     */
    public int getParkingSlotID() {
        return parkingSlotID;
    }

    /**
     * Sets the parking slot id.
     *
     * @param parkingSlotID the new parking slot id
     */
    public void setParkingSlotID(int parkingSlotID) {
        this.parkingSlotID = parkingSlotID;
    }

    /**
     * Gets the parked vehicle.
     *
     * @return the parked vehicle
     */
    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    /**
     * Sets the parked vehicle.
     *
     * @param parkedVehicle the new parked vehicle
     */
    public void setParkedVehicle(Vehicle parkedVehicle) {
        this.parkedVehicle = parkedVehicle;
    }

    /**
     * Gets the parked since.
     *
     * @return the parked since
     */
    public LocalDateTime getParkedSince() {
        return parkedSince;
    }

    /**
     * Sets the parked since.
     *
     * @param parkedSince the new parked since
     */
    public void setParkedSince(LocalDateTime parkedSince) {
        this.parkedSince = parkedSince;
    }

    /**
     * Checks if is available.
     *
     * @return true, if is available
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Sets the available.
     *
     * @param isAvailable the new available
     */
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

}
