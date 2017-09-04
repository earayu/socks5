package socks5.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.socksx.v5.Socks5InitialRequestDecoder;
import io.netty.handler.codec.socksx.v5.Socks5ServerEncoder;
import socks5.boot.LocalServerConfig;

/**
 * Created by earayu on 2017/8/29.
 */
public class ClientChannelFactory extends ChannelInitializer<NioSocketChannel> {

    private LocalServerConfig config;

    public ClientChannelFactory(LocalServerConfig config) {
        this.config = config;
    }

    @Override
    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
        nioSocketChannel.pipeline()
                .addLast(new Socks5InitialRequestDecoder())
                .addLast(new BrowserHandler(config))
                .addLast(Socks5ServerEncoder.DEFAULT);
    }
}
