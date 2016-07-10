package com.jasshine.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.channels.SocketChannel;

/**
 * Created by jasshine_xxg on 2016/7/10.
 *
 * 配置中心客户端
 *
 */
public class ProtocolClient {

    static final Logger LOGGER = LoggerFactory.getLogger(ProtocolClient.class);

    String serverAddress = "127.0.0.1";
    final int port = 9999;
    int readTimeout = 15;
    int writeTimeout = 5;

    //netty

    EventLoopGroup eventLoopGroup = new NioEventLoopGroup(); //构造线程池组
    Bootstrap bootstrap = new Bootstrap();



    //初始化channle
    public ChannelFuture init() {
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5 * 1000)
                .option(ChannelOption.TCP_NODELAY, true)
                .group(eventLoopGroup).channel(NioSocketChannel.class).remoteAddress(serverAddress,port)
                .handler(new ChannelInitializer<io.netty.channel.socket.SocketChannel>() {
                    @Override
                    protected void initChannel(io.netty.channel.socket.SocketChannel socketChannel) throws Exception {
                   
                    }
                });

    }





}
