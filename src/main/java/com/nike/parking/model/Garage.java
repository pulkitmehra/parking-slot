/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.parking.model;

import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The Class Garage.
 *
 * @author pulkit.mehra
 * Created: Nov 18, 2015
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "garageID", "parkingSlots" })
@XmlRootElement(name = "Garages")
@JsonTypeName("Garages")
public class Garage {

    /** The garage id. */
    private int garageID;

    /** The parking slots. */
    @XmlElement(name = "ParkingSlots")
    @JsonProperty("ParkingSlots")
    private Set<ParkingSlot> parkingSlots;

    /**
     * Gets the garage id.
     *
     * @return the garage id
     */
    public int getGarageID() {
        return garageID;
    }

    /**
     * Sets the garage id.
     *
     * @param garageID the new garage id
     */
    public void setGarageID(int garageID) {
        this.garageID = garageID;
    }

    /**
     * Gets the parking slots.
     *
     * @return the parking slots
     */
    public Set<ParkingSlot> getParkingSlots() {
        if (parkingSlots == null) {
            return new HashSet<>();
        }
        return parkingSlots;
    }

    /**
     * Sets the parking slots.
     *
     * @param parkingSlots the new parking slots
     */
    public void setParkingSlots(Set<ParkingSlot> parkingSlots) {
        this.parkingSlots = parkingSlots;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + garageID;
        result = prime * result + ((parkingSlots == null) ? 0 : parkingSlots.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Garage other = (Garage) obj;
        if (garageID != other.garageID)
            return false;
        if (parkingSlots == null) {
            if (other.parkingSlots != null)
                return false;
        }
        else if (!parkingSlots.equals(other.parkingSlots))
            return false;
        return true;
    }

}
