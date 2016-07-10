package com.jasshine.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jasshine_xxg on 2016/7/10.
 * 协议
 *
 * vesrsion + length + type  + data(request/response)
 * (2个字节)   （4字节） （2字节）
 *
 * 协议很简单，就是将msg编码成byte.
 *
 * version：版本号，2个字节表示
 * length :  data数据 + 类型（oneway,request,response）长度 ===》data.length + 2
 *
 *
 *
 */
public class MessageEncode extends MessageToByteEncoder<Message> {

    static final Logger logger = LoggerFactory.getLogger(MessageEncode.class);
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, ByteBuf byteBuf) throws Exception {
        logger.info("begin to encode message to byte ," + message);
        byteBuf.writeShort(message.getVersion());
        byteBuf.writeInt(message.getData().length + 2); // 这个数据长度 加上 请求类型的长度固定为2字节
        byteBuf.writeShort(message.getType());
        byteBuf.writeBytes(message.getData());
        channelHandlerContext.flush();
    }
}
