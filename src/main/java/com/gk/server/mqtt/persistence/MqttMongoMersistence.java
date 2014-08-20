package com.gk.server.mqtt.persistence;

import java.util.Iterator;

public class MqttMongoMersistence implements IMqttPersistence
{
	
	private MqttMongoMersistence()
	{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void open(String clientId, String serverURI) throws MqttPersistenceException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() throws MqttPersistenceException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void put(String key, Object persistable) throws MqttPersistenceException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object get(String key) throws MqttPersistenceException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(String key) throws MqttPersistenceException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterator<String> keys() throws MqttPersistenceException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() throws MqttPersistenceException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean containsKey(String key) throws MqttPersistenceException
	{
		// TODO Auto-generated method stub
		return false;
	}

}
