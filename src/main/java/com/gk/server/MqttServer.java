package com.gk.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gk.server.mqtt.MqttMessageHandler;
import com.gk.server.mqtt.MqttMessageDecoder;
import com.gk.server.mqtt.MqttMessageEncoder;

/**
 * Discards any incoming data.
 */
public class MqttServer
{
	private int port;

	private static final Logger l = LoggerFactory.getLogger(MqttServer.class);

	public MqttServer(int port)
	{
		this.port = port;
	}

	public void run() throws Exception
	{
		final DefaultEventExecutorGroup group = new DefaultEventExecutorGroup(100);
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try
		{
			ServerBootstrap b = new ServerBootstrap(); // (2)
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
			// (3)
					.childHandler(new ChannelInitializer<SocketChannel>()
					{
						@Override
						public void initChannel(SocketChannel ch) throws Exception
						{
							ChannelPipeline pipeline = ch.pipeline();
							pipeline.addFirst("idleStateHandler", new IdleStateHandler(0, 0, 60000));
							pipeline.addLast("decoder", new MqttMessageDecoder());
							pipeline.addLast("encoder", new MqttMessageEncoder());
							pipeline.addLast("handler", new MqttMessageHandler());
						}
					});
			b.option(ChannelOption.TCP_NODELAY, true);
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.option(ChannelOption.SO_REUSEADDR, true);
			b.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 60000);
			l.info("Created server on port {}", port);
			
			// Bind and start to accept incoming connections.
			ChannelFuture f = b.bind(port).sync(); // (7)
			// Wait until the server socket is closed.
			// In this example, this does not happen, but you can do that to gracefully
			// shut down your server.
			f.channel().closeFuture().sync();

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception
	{
		int port;
		if (args.length > 0)
		{
			port = Integer.parseInt(args[0]);
		}
		else
		{
			port = 1883;
		}

		new MqttServer(port).run();
	}
}
