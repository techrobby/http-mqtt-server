package com.gk.server.mqtt;

/**
 * Created by IntelliJ IDEA.
 * User: bhuvangupta
 * Date: 23/01/12
 * Time: 1:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class MqttException extends Exception
{

    public MqttException(String message)
    {
        super(message);
    }

    public MqttException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}
