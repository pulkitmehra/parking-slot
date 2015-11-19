/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.parking.core;

// TODO: Auto-generated Javadoc
/**
 * The Class GarageServiceException.
 *
 * @author pulkit.mehra Created: Nov 18, 2015
 */
public class GarageServiceException extends Exception {

    /** */
    private static final long serialVersionUID = -7530974299939577832L;

    /**
     * Instantiates a new garage service exception.
     *
     * @param msg the msg
     */
    public GarageServiceException(String msg) {
        super(msg);
    }

    /**
     * Instantiates a new garage service exception.
     *
     * @param msg the msg
     * @param error the error
     */
    public GarageServiceException(String msg, Throwable error) {
        super(msg, error);
    }

}
