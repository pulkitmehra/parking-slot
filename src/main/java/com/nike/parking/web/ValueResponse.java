/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.parking.web;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The Class ValueResponse.
 *
 * @author pulkit.mehra
 * Created: Nov 19, 2015
 * @param <T> the generic type
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "value" })
@XmlRootElement(name = "SingleResponse")
@JsonTypeName("SingleResponse")
public class ValueResponse<T> {

    /** The value. */
    private T value;

    /**
     * Instantiates a new value response.
     */
    public ValueResponse() {
    }

    /**
     * Instantiates a new value response.
     *
     * @param value the value
     */
    public ValueResponse(T value) {
        this.value = value;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public T getValue() {
        return value;
    }

    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setValue(T value) {
        this.value = value;
    }
}
