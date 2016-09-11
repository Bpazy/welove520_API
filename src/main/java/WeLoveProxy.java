import com.moandjiezana.toml.Toml;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import org.littleshoot.proxy.HttpFilters;
import org.littleshoot.proxy.HttpFiltersSourceAdapter;
import org.littleshoot.proxy.impl.DefaultHttpProxyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class WeLoveProxy {
    private String bindIp;
    int port;
    private Logger logger = LoggerFactory.getLogger(WeLoveProxy.class);

    public WeLoveProxy() {
        URL confUrl = ClassLoader.getSystemClassLoader().getResource("server.conf");
        if (confUrl == null) {
            logger.error("server.conf not found");
            return;
        }
        File file = new File(confUrl.getPath());
        Toml toml = new Toml().read(file);
        bindIp = toml.getString("bind_ip");
        port = Math.toIntExact(toml.getLong("port"));
    }

    public void start() {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
        DefaultHttpProxyServer
                .bootstrap()
                .withAddress(new InetSocketAddress(bindIp, port))
                .withFiltersSource(new HttpFiltersSourceAdapter() {
                    @Override
                    public HttpFilters filterRequest(HttpRequest originalRequest, ChannelHandlerContext ctx) {
                        return new WeLoveFiltersAdapter(originalRequest, queue);
                    }
                })
                .start();
        new Thread(new PostContentHandler(queue)).start();
    }
}
