package com.jasshine.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by jasshine_xxg on 2016/7/10.
 *
 * 解码，将字节转成message，然后写进list里面去。
 *
 * 即List<Object> list 参数里面去
 */
public class MessageDecode extends ByteToMessageDecoder{

    int state = 0;
    Message message ;
    int dataLenght = 0; //数据长度

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

          //将data长度 解析出来
        if (state == 0 && byteBuf.readableBytes() >= (2 + 4 + 2)) {  //因为整个包不包括data部分长度为8个字节。判断是否大于表示 判断data里面是否有数据。
            message = new Message(); //有数据 就构造message对象。
            message.setVersion(byteBuf.readShort());
            dataLenght = byteBuf.readInt() - 2;
            message.setType(byteBuf.readShort());
            state = 1;
        }
        if (state == 1 && byteBuf.readableBytes() >= dataLenght) {  //如果bytebuf 里面有data数据，就进行解析
            byte[] data = new byte[dataLenght];
            byteBuf.readBytes(data);   //将bytebuf 读取到的数据，放入到data 字节数组里面去
            message.setData(data);
            list.add(message);

            //数据状态reset
            message = null;
            dataLenght = 0;
            state = 0;
        }

    }
}
