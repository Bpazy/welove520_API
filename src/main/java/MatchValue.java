import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MatchValue {

    public static String getValue(String content, String key) {
        Pattern pattern = Pattern.compile("(?:.*" + key + "=)(.+?)((?:&.*)|(?:$))");
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "null";
        }
    }
}
