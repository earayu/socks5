package socks5.local.boot;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import socks5.local.handler.ClientChannelFactory;

/**
 * Created by earayu on 2017/8/29.
 */
@Component
@EnableConfigurationProperties(LocalServerConfig.class)
public class LocalSever {

    @Autowired
    private LocalServerConfig config;

    public void start() {
        NioEventLoopGroup boss = new NioEventLoopGroup();//TODO 线程可设置
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ClientChannelFactory(config));
            ChannelFuture future = b.bind(config.getLocalServer().getAddr(), config.getLocalServer().getPort());
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();//TODO 日志
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

}
