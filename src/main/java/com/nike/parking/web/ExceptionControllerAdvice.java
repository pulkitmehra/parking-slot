/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.parking.web;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.nike.parking.core.ResourceNotFoundException;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * The Class ExceptionControllerAdvice.
 *
 * @author pulkit.mehra
 * Created: Nov 19, 2015
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    /** The log. */
    private static Logger LOG = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    /**
     * Handle resorce not found.
     *
     * @param exception the exception
     * @param request the request
     * @return the error response
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = NOT_FOUND)
    public @ResponseBody ErrorResponse handleResorceNotFound(final ResourceNotFoundException exception,
        final HttpServletRequest request) {
        LOG.debug("Resource not Found", exception);
        return buildErrorResponse(exception, request, NOT_FOUND.value());
    }

    /**
     * Handle exception.
     *
     * @param exception the exception
     * @param request the request
     * @return the error response
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorResponse handleException(final Throwable exception,
        final HttpServletRequest request) {
        LOG.debug("Internal Server Error", exception);
        return buildErrorResponse(exception, request, INTERNAL_SERVER_ERROR.value());
    }

    /**
     * Builds the error response.
     *
     * @param exception the exception
     * @param request the request
     * @param httpStatusCode the http status code
     * @return the error response
     */
    private ErrorResponse buildErrorResponse(Throwable exception, HttpServletRequest request,
        int httpStatusCode) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorMessage(exception.getMessage());
        error.setRequestedURI(request.getRequestURI());
        error.setStatusCode(httpStatusCode);
        return error;
    }
}
