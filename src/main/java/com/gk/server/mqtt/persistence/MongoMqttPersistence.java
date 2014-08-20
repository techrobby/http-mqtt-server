package com.gk.server.mqtt.persistence;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoMqttPersistence implements IMqttPersistence
{
	private static final String DB_NAME = "mqttPersistenceDB";

	private static final String DB_COLLECTION = "mqttPersistenceColl";

	private static final String UUID = "uuid";

	private String host = "127.0.0.1";

	private int port = 27017;

	private MongoClient dbconn;

	private DB db;

	private DBCollection collection;

	private String DATA = "data";

	static final Logger logger = LoggerFactory.getLogger(MongoMqttPersistence.class);

	public MongoMqttPersistence()
	{

	}

	@Override
	public void open(String clientId, String serverURI) throws MqttPersistenceException
	{
		try
		{
			dbconn = new MongoClient(host, port);
			db = dbconn.getDB(DB_NAME);
			collection = db.getCollection(DB_COLLECTION);
			logger.info("Events DB non-null : {}", dbconn);
			ensureIndex();
		}
		catch (UnknownHostException e)
		{
			logger.error("Exception while instantiating MongoClient", e);
		}
	}

	private void ensureIndex()
	{
		// here 1 represents ascending order
		collection.createIndex(new BasicDBObject(UUID, 1));
	}

	@Override
	public void close() throws MqttPersistenceException
	{
		dbconn.close();
	}

	@Override
	public void put(String key, Object persistable) throws MqttPersistenceException
	{
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put(UUID, key);
		dbObject.put(DATA, persistable);
		collection.insert(dbObject);
	}

	@Override
	public Object get(String key) throws MqttPersistenceException
	{
		BasicDBObject obj = new BasicDBObject(UUID, key);
		DBCursor cursor = collection.find(obj);
		List<JSONObject> list = new ArrayList<>(cursor.count());
		try
		{
			while (cursor.hasNext())
			{
				list.add(new JSONObject(cursor.next().get(DATA)));
			}
			return list;
		}
		finally
		{
			cursor.close();
		}
	}

	@Override
	public void remove(String key) throws MqttPersistenceException
	{
		BasicDBObject obj = new BasicDBObject(UUID, key);
		collection.remove(obj);
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
		collection.drop();
	}

	@Override
	public boolean containsKey(String key) throws MqttPersistenceException
	{
		BasicDBObject obj = new BasicDBObject(UUID, key);
		DBCursor cursor = collection.find(obj);
		try
		{
			if (cursor.count() > 0)
				return true;
			return false;
		}
		finally
		{
			cursor.close();
		}
	}
}
