/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.parking.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.fasterxml.jackson.annotation.JsonTypeName;


/**
 * The Class Vehicle.
 *
 * @author pulkit.mehra
 * Created: Nov 18, 2015
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "vehicleID", "vehicleRegisteration" })
@XmlRootElement(name = "Vehicle")
@JsonTypeName("Vehicle")
public class Vehicle {

    /** The vehicle id. */
    private int vehicleID;

    /** The vehicle registeration. */
    private String vehicleRegisteration;

    /**
     * Gets the vehicle id.
     *
     * @return the vehicle id
     */
    public int getVehicleID() {
        return vehicleID;
    }

    /**
     * Sets the vehicle id.
     *
     * @param vehicleID the new vehicle id
     */
    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    /**
     * Gets the vehicle registeration.
     *
     * @return the vehicle registeration
     */
    public String getVehicleRegisteration() {
        return vehicleRegisteration;
    }

    /**
     * Sets the vehicle registeration.
     *
     * @param vehicleRegisteration the new vehicle registeration
     */
    public void setVehicleRegisteration(String vehicleRegisteration) {
        this.vehicleRegisteration = vehicleRegisteration;
    }

}
