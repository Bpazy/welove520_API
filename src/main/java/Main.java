import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;

public class Main {
    public static void main(String[] args) throws Exception {
        Logger logger = LoggerFactory.getLogger(Main.class);
        WeLoveProxy proxy = new WeLoveProxy();
        proxy.start();
        logger.warn(String.format("请将手机HTTP代理设置为%s:%d",
                InetAddress.getLocalHost().getHostAddress(),
                proxy.port));
        logger.warn("接着打开微爱使用.配置信息会保存在根目录welove.conf");
        logger.warn("配置信息会保存在根目录welove.conf");
        logger.warn("等待数据中...");
    }
}
