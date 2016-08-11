import com.moandjiezana.toml.TomlWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class PostContentHandler implements Runnable {
    private final String fileName = "welove.conf";
    private BlockingQueue<String> queue;
    private TomlWriter tomlWriter = new TomlWriter();
    private File file = new File(fileName);
    private Logger logger = LoggerFactory.getLogger(PostContentHandler.class);

    public PostContentHandler(BlockingQueue<String> queue) {
        this.queue = queue;
        try {
            if (file.exists()) {
                boolean delete = file.delete();
                if (!delete) throw new IOException("删除" + fileName + "失败");
            }
            boolean newFile = file.createNewFile();
            if (newFile) {
                logger.info("创建" + fileName + "成功");
            } else {
                throw new IOException("创建" + fileName + "失败");
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String postContent = queue.take();
                logger.warn("Save " + postContent);
                String accessToken = MatchValue.getValue(postContent, "access_token");
                String loveSpaceId = MatchValue.getValue(postContent, "love_space_id");
                String taskType = MatchValue.getValue(postContent, "task_type");
                String sig = MatchValue.getValue(postContent, "sig");
                handleTomlMessage(accessToken, loveSpaceId, taskType, sig);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public String handleTomlMessage(String accessToken, String loveSpaceId, String taskType, String sig) throws
            IOException {
        Map<String, Object> infoMap = handlerInfo(accessToken, loveSpaceId, taskType, sig);
        String kind;
        switch (Integer.parseInt(taskType)) {
            case 7:
                kind = "xiuxi";
                break;
            case 6:
                kind = "xizao";
                break;
            case 4:
                kind = "chifan";
                break;
            case 5:
                kind = "shuijiao";
                break;
            case 13:
                kind = "hudong";
                break;
            default:
                kind = null;
        }
        Map<String, Map> map = new HashMap<>();
        map.put(kind, infoMap);
        OutputStream outputStream = new FileOutputStream(file, true);
        tomlWriter.write(map, outputStream);
        return tomlWriter.write(map);
    }

    private Map<String, Object> handlerInfo(String accessToken, String loveSpaceId, String taskType, String sig) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("access_token", accessToken);
        map.put("love_space_id", loveSpaceId);
        map.put("task_type", taskType);
        map.put("sig", sig);
        return map;
    }
}
