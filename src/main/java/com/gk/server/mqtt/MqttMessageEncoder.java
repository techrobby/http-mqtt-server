package com.gk.server.mqtt;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.MessageToByteEncoder;

import com.gk.server.mqtt.msg.Message;

public class MqttMessageEncoder extends MessageToByteEncoder<Message>
{
	@Override
	protected void encode(io.netty.channel.ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception
	{
		if (!(msg instanceof Message))
        {
            return;
        }
        byte[] data = ((Message) msg).toBytes();
        out.writeBytes(data); 
	}

}
