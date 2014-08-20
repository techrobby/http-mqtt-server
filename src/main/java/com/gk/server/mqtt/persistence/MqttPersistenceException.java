package com.gk.server.mqtt.persistence;

public class MqttPersistenceException extends Exception
{
	/** Persistence is already being used by another client. */
	public static final String REASON_CODE_PERSISTENCE_IN_USE = "Persistence in use";

	public static final String REASON_CODE_CLIENT_EXCEPTION = "Client Exception";

	/**
	 * Constructs a new <code>MqttPersistenceException</code>
	 */
	public MqttPersistenceException()
	{
		super(REASON_CODE_CLIENT_EXCEPTION);
	}

	/**
	 * Constructs a new <code>MqttPersistenceException</code> with the specified code as the underlying reason.
	 * 
	 * @param reasonCode
	 *            the reason code for the exception.
	 */
	public MqttPersistenceException(String reason)
	{
		super(reason);
	}

	/**
	 * Constructs a new <code>MqttPersistenceException</code> with the specified <code>Throwable</code> as the
	 * underlying reason.
	 * 
	 * @param cause
	 *            the underlying cause of the exception.
	 */
	public MqttPersistenceException(Throwable cause)
	{
		super(cause);
	}

	/**
	 * Constructs a new <code>MqttPersistenceException</code> with the specified <code>Throwable</code> as the
	 * underlying reason.
	 * 
	 * @param reason
	 *            the reason code for the exception.
	 * @param cause
	 *            the underlying cause of the exception.
	 */
	public MqttPersistenceException(String reason, Throwable cause)
	{
		super(reason, cause);
	}
}
