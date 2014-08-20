package com.gk.server.mqtt.persistence;

import java.util.Iterator;

public class MqttPersistence implements IMqttPersistence
{

	private static MqttPersistence _pers;

	private IMqttPersistence _instance;

	private MqttPersistence()
	{
		// TODO Auto-generated constructor stub
	}

	public static MqttPersistence getInstance()
	{
		if (_pers == null)
			_pers = new MqttPersistence();
		return _pers;
	}

	public void init(IMqttPersistence instance)
	{
		if(instance != null)
			_instance = instance;
		else
			_instance = new MemoryMqttPersistence();
	}

	@Override
	public void open(String clientId, String serverURI) throws MqttPersistenceException
	{
		_instance.open(clientId, serverURI);
	}

	@Override
	public void close() throws MqttPersistenceException
	{
		_instance.close();
	}

	@Override
	public void put(String key, Object persistable) throws MqttPersistenceException
	{
		_instance.put(key, persistable);
	}

	@Override
	public Object get(String key) throws MqttPersistenceException
	{
		return _instance.get(key);
	}

	@Override
	public void remove(String key) throws MqttPersistenceException
	{
		_instance.remove(key);
	}

	@Override
	public Iterator<String> keys() throws MqttPersistenceException
	{
		return _instance.keys();
	}

	@Override
	public void clear() throws MqttPersistenceException
	{
		_instance.clear();
	}

	@Override
	public boolean containsKey(String key) throws MqttPersistenceException
	{
		return _instance.containsKey(key);
	}
}
