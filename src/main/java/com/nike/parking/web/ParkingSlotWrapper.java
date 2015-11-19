/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.parking.web;

import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.nike.parking.model.ParkingSlot;

/**
 * The Class ParkingSlotWrapper.
 *
 * @author pulkit.mehra
 * Created: Nov 19, 2015
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "parkingSlots" })
@XmlRootElement(name = "ParkingSlots")
@JsonTypeName("ParkingSlots")
public class ParkingSlotWrapper {

    /** The parking slots. */
    @XmlElement(name = "ParkingSlots")
    @JsonProperty("ParkingSlots")
    private Set<ParkingSlot> parkingSlots;

    /**
     * Gets the parking slots.
     *
     * @return the parking slots
     */
    public Set<ParkingSlot> getParkingSlots() {
        if (parkingSlots == null) {
            parkingSlots = new HashSet<>();
        }
        return this.parkingSlots;
    }

}
