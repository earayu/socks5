package socks5.remote.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by earayu on 2017/9/1.
 */
@SpringBootApplication
public class Socks5RemoteServerApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Socks5RemoteServerApplication.class, args);
        SocksServer.start();
    }


}
