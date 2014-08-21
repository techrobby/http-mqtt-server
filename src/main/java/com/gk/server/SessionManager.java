package com.gk.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;

public class SessionManager
{
	private static final Logger logger = LoggerFactory.getLogger(SessionManager.class);

	private static SessionManager _instance;

	Map<String, ChannelHandlerContext> sessions;

	private SessionManager()
	{
		sessions = new ConcurrentHashMap<String, ChannelHandlerContext>();
	}
	
	public static SessionManager getInstance()
	{
		if(_instance == null)
			_instance = new SessionManager();
		return _instance;
	}
	
	public void addSession(String clientId, ChannelHandlerContext ctx)
	{
		sessions.put(clientId, ctx);
	}
	
	public ChannelHandlerContext getSessionContext(String clientId)
	{
		return sessions.get(clientId);
	}
}
