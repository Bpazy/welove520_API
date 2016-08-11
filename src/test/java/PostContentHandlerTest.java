import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PostContentHandlerTest {

    @Test
    public void testPostContentHandler() {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
        try {
            queue.put("access_token=562949961343086-24c48b1a01a674429&task_type=7&sig=HsK2ME/AQTEFRuLvTIXBEOy/Mc8" +
                    "=&love_space_id=844424932415867");
            PostContentHandler postContentHandler = new PostContentHandler(queue);
            String postContent = queue.take();
            String accessToken = MatchValue.getValue(postContent, "access_token");
            String loveSpaceId = MatchValue.getValue(postContent, "love_space_id");
            String taskType = MatchValue.getValue(postContent, "task_type");
            String sig = MatchValue.getValue(postContent, "sig");
            String s = postContentHandler.handleTomlMessage(accessToken, loveSpaceId, taskType, sig);
            String expectResult = "[xiuxi]\n" +
                    "access_token = \"562949961343086-24c48b1a01a674429\"\n" +
                    "love_space_id = \"844424932415867\"\n" +
                    "task_type = \"7\"\n" +
                    "sig = \"HsK2ME/AQTEFRuLvTIXBEOy/Mc8=\"\n";
            Assert.assertEquals(s, expectResult);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}