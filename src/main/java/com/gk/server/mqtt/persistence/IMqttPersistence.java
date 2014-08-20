package com.gk.server.mqtt.persistence;

import java.util.Iterator;

public interface IMqttPersistence
{
	/**
	 * Initialise the persistent store. If a persistent store exists for this client ID then open it, otherwise create a
	 * new one. If the persistent store is already open then just return. An application may use the same client ID to
	 * connect to many different servers, so the client ID in conjunction with the connection will uniquely identify the
	 * persistence store required.
	 * 
	 * @param clientId
	 *            The client for which the persistent store should be opened.
	 * @param serverURI
	 *            The connection string as specified when the MQTT client instance was created.
	 * @throws MqttPersistenceException
	 *             if there was a problem opening the persistent store.
	 */
	public void open(String clientId, String serverURI) throws MqttPersistenceException;

	/**
	 * Close the persistent store that was previously opened. This will be called when a client application disconnects
	 * from the broker.
	 * 
	 * @throws MqttPersistenceException
	 */
	public void close() throws MqttPersistenceException;

	/**
	 * Puts the specified data into the persistent store.
	 * 
	 * @param key
	 *            the key for the data, which will be used later to retrieve it.
	 * @param persistable
	 *            the data to persist
	 * @throws MqttPersistenceException
	 *             if there was a problem putting the data into the persistent store.
	 */
	public void put(String key, Object persistable) throws MqttPersistenceException;

	/**
	 * Gets the specified data out of the persistent store.
	 * 
	 * @param key
	 *            the key for the data, which was used when originally saving it.
	 * @return the un-persisted data
	 * @throws MqttPersistenceException
	 *             if there was a problem getting the data from the persistent store.
	 */
	public Object get(String key) throws MqttPersistenceException;

	/**
	 * Remove the data for the specified key.
	 */
	public void remove(String key) throws MqttPersistenceException;

	/**
	 * Returns an Enumeration over the keys in this persistent data store.
	 * 
	 * @return an enumeration of {@link String} objects.
	 */
	public Iterator<String> keys() throws MqttPersistenceException;

	/**
	 * Clears persistence, so that it no longer contains any persisted data.
	 */
	public void clear() throws MqttPersistenceException;

	/**
	 * Returns whether or not data is persisted using the specified key.
	 * 
	 * @param key
	 *            the key for data, which was used when originally saving it.
	 */
	public boolean containsKey(String key) throws MqttPersistenceException;
}
