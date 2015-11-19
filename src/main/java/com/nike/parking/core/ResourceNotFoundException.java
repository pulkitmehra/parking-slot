/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.parking.core;

/**
 * The Class ResourceNotFoundException.
 *
 * @author pulkit.mehra
 *  Created: Nov 18, 2015
 */
public class ResourceNotFoundException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -8154273025222286671L;

    /**
     * Instantiates a new resource not found exception.
     *
     * @param msg the msg
     */
    public ResourceNotFoundException(String msg) {
        super(msg);
    }

    /**
     * Instantiates a new resource not found exception.
     *
     * @param msg the msg
     * @param error the error
     */
    public ResourceNotFoundException(String msg, Throwable error) {
        super(msg, error);
    }
}
