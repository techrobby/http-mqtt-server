package com.gk.server.mqtt.msg;

import java.io.IOException;

public class UnsubAckMessage extends RetryableMessage
{

    public UnsubAckMessage(Header header)
            throws IOException
    {
        super(header);
    }

    public UnsubAckMessage(short messageId)
    {
        super(Type.UNSUBACK);
        this.messageId = messageId;
    }

}
