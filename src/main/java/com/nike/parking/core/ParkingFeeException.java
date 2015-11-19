/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.parking.core;

/**
 * The Class ParkingFeeException.
 *
 * @author pulkit.mehra
 * Created: Nov 18, 2015
 */
public class ParkingFeeException extends Exception {

    /** */
    private static final long serialVersionUID = -6087183432162775253L;

    /**
     * Instantiates a new parking fee exception.
     *
     * @param msg the msg
     */
    public ParkingFeeException(String msg) {
        super(msg);
    }

    /**
     * Instantiates a new parking fee exception.
     *
     * @param msg the msg
     * @param error the error
     */
    public ParkingFeeException(String msg, Throwable error) {
        super(msg, error);
    }
}
