package com.gk.server.mqtt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gk.server.MqttServer;
import com.gk.server.mqtt.msg.ConnectMessage;
import com.gk.server.mqtt.msg.DisconnectMessage;
import com.gk.server.mqtt.msg.Message;
import com.gk.server.mqtt.msg.PingReqMessage;
import com.gk.server.mqtt.msg.PubAckMessage;
import com.gk.server.mqtt.msg.PubRelMessage;
import com.gk.server.mqtt.msg.PublishMessage;
import com.gk.server.mqtt.msg.SubscribeMessage;
import com.gk.server.mqtt.msg.UnsubscribeMessage;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MqttMessageHandler extends SimpleChannelInboundHandler<Message>
{

	private static final Logger logger = LoggerFactory.getLogger(MqttMessageHandler.class);
	
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

	private void handleMessage(ChannelHandlerContext ctx, ConnectMessage msg)
	{
		logger.info("Connect Message received.");	
	}

}
