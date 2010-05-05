/**
 * 
 */
package com.google.code.stackoverflow.client.exception;

import java.util.Date;

import com.google.code.stackoverflow.client.constant.ErrorCodes;

/**
 * The Class RequestLimitExceededException.
 */
public class RequestLimitExceededException extends StackOverflowApiClientException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4116844039359929855L;

	/**
	 * Instantiates a new request limit exceeded exception.
	 */
	public RequestLimitExceededException() {
		super();
	}

	/**
	 * Instantiates a new request limit exceeded exception.
	 * 
	 * @param message the message
	 */
	public RequestLimitExceededException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new request limit exceeded exception.
	 * 
	 * @param cause the cause
	 */
	public RequestLimitExceededException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new request limit exceeded exception.
	 * 
	 * @param message the message
	 * @param cause the cause
	 */
	public RequestLimitExceededException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new request limit exceeded exception.
	 * 
	 * @param message the message
	 * @param statusCode the status code
	 * @param errorCode the error code
	 * @param timestamp the timestamp
	 */
	public RequestLimitExceededException(String message, int statusCode, int errorCode,
			Date timestamp) {
		super(message, statusCode, errorCode, timestamp);
	}
	
	/**
	 * Instantiates a new request limit exceeded exception.
	 * 
	 * @param message the message
	 * @param timestamp the timestamp
	 */
	public RequestLimitExceededException(String message, Date timestamp) {
		super(message, 403, ErrorCodes.REQUEST_LIMIT_EXCEEDED, timestamp);
	}
}
