package com.gk.server.mqtt;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gk.server.MqttServer;
import com.gk.server.SessionManager;
import com.gk.server.mqtt.msg.ConnAckMessage;
import com.gk.server.mqtt.msg.ConnAckMessage.ConnectionStatus;
import com.gk.server.mqtt.msg.ConnectMessage;
import com.gk.server.mqtt.msg.DisconnectMessage;
import com.gk.server.mqtt.msg.Message;
import com.gk.server.mqtt.msg.PingReqMessage;
import com.gk.server.mqtt.msg.PubAckMessage;
import com.gk.server.mqtt.msg.PubRelMessage;
import com.gk.server.mqtt.msg.PublishMessage;
import com.gk.server.mqtt.msg.SubscribeMessage;
import com.gk.server.mqtt.msg.UnsubscribeMessage;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MqttMessageHandler extends SimpleChannelInboundHandler<Message>
{

	private static final Logger logger = LoggerFactory.getLogger(MqttMessageHandler.class);
	
	private SessionManager sm = SessionManager.getInstance();
	
	private ConnAckMessage.ConnectionStatus connectionStatus = ConnectionStatus.NOT_AUTHORIZED;
	
	String clientId;
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception
	{
		System.out.println("Mqtt message received");
        switch (msg.getType())
        {
            case CONNECT:
                handleMessage(ctx, (ConnectMessage) msg);
                break;
            case DISCONNECT:
                handleMessage(ctx, (DisconnectMessage) msg);
                break;
            case PINGREQ:
                handleMessage(ctx, (PingReqMessage) msg);
                break;
            case PUBLISH:
                handleMessage(ctx, (PublishMessage) msg);
                break;
            case PUBACK:
                handleMessage(ctx, (PubAckMessage) msg);
                break;
            case PUBREL:
                handleMessage(ctx, (PubRelMessage) msg);
                break;
            case SUBSCRIBE:
                handleMessage(ctx, (SubscribeMessage) msg);
                break;
            case UNSUBSCRIBE:
                handleMessage(ctx, (UnsubscribeMessage) msg);
                break;
            default: //SUBACK,UNSUBACK,PINGRESP,CONNACK, PUBCOMP ,PUBREC
            	break;
        }
		
	}

	private void handleMessage(ChannelHandlerContext ctx, UnsubscribeMessage msg)
	{
		// TODO Auto-generated method stub
		
	}

	private void handleMessage(ChannelHandlerContext ctx, SubscribeMessage msg)
	{
		// TODO Auto-generated method stub
		
	}

	private void handleMessage(ChannelHandlerContext ctx, PubRelMessage msg)
	{
		// TODO Auto-generated method stub
		
	}

	private void handleMessage(ChannelHandlerContext ctx, PubAckMessage msg)
	{
		// TODO Auto-generated method stub
		
	}

	private void handleMessage(ChannelHandlerContext ctx, PublishMessage msg)
	{
		// TODO Auto-generated method stub
		
	}

	private void handleMessage(ChannelHandlerContext ctx, PingReqMessage msg)
	{
		// TODO Auto-generated method stub
		
	}

	private void handleMessage(ChannelHandlerContext ctx, DisconnectMessage msg)
	{
		// TODO Auto-generated method stub
		
	}

	private void handleMessage(final ChannelHandlerContext ctx, ConnectMessage msg)
	{
		ConnAckMessage.ConnectionStatus status = ConnAckMessage.ConnectionStatus.ACCEPTED;
		clientId = msg.getClientId();
		if (StringUtils.isEmpty(clientId))
		{
			logger.info("[" + msg.getClientId() + "] : AUTH_FAILURE. Null clientId : " + msg.getClientId());
			status = ConnAckMessage.ConnectionStatus.BAD_USERNAME_OR_PASSWORD;
		}
		else
		{

		}
		ConnAckMessage ackMessage = new ConnAckMessage(status);
		ChannelFuture future = ctx.writeAndFlush(ackMessage);
		future.addListener(new ChannelFutureListener()
		{
			@Override
			public void operationComplete(ChannelFuture channelFuture) throws Exception
			{
				if (!channelFuture.isSuccess())
				{
					Throwable cause = channelFuture.cause();
					if (cause != null)
					{
						cause.printStackTrace();
						logger.error("Channel write failed while writing connack", cause);
					}
					ctx.close();
				}
				else
				{
					// register context in sessions
					sm.addSession(clientId, ctx);
					connectionStatus = ConnectionStatus.ACCEPTED;
				}
			}
		});	
	}

}
