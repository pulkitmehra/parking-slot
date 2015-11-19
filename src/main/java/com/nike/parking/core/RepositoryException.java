/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.parking.core;


/**
 * The Class RepositoryException.
 *
 * @author pulkit.mehra
 * Created: Nov 19, 2015
 */
public class RepositoryException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2713294957116343278L;

    /**
     * Instantiates a new repository exception.
     *
     * @param msg the msg
     */
    public RepositoryException(String msg) {
        super(msg);
    }

    /**
     * Instantiates a new repository exception.
     *
     * @param msg the msg
     * @param error the error
     */
    public RepositoryException(String msg, Throwable error) {
        super(msg, error);
    }
}
