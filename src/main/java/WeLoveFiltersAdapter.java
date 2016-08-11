import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import org.littleshoot.proxy.HttpFiltersAdapter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.BlockingQueue;

public class WeLoveFiltersAdapter extends HttpFiltersAdapter {
    private BlockingQueue<String> queue;

    public WeLoveFiltersAdapter(HttpRequest originalRequest, ChannelHandlerContext ctx, BlockingQueue<String> queue) {
        super(originalRequest, ctx);
        this.queue = queue;
    }

    public WeLoveFiltersAdapter(HttpRequest originalRequest, BlockingQueue<String> queue) {
        this(originalRequest, null, queue);
    }

    @Override
    public HttpResponse clientToProxyRequest(HttpObject httpObject) {
        if (httpObject instanceof HttpContent) {
            HttpContent httpRequest = (HttpContent) httpObject;
            ByteBuf content = httpRequest.content();
            int len = content.readableBytes();
            byte[] temp = new byte[len];
            content.readBytes(temp, 0, len);
            content.resetReaderIndex();
            String postContent = new String(temp, 0, len);
            if (postContent.contains("sig")
                    && postContent.contains("love_space_id")
                    && postContent.contains("access_token")
                    && postContent.contains("task_type")) {
                try {
                    postContent = URLDecoder.decode(postContent, "UTF-8");
                    queue.put(postContent);
                } catch (UnsupportedEncodingException | InterruptedException e) {
                    e.printStackTrace();
                }
                System.err.println("queue put: " + postContent);
            }
        }
        return null;
    }
}
