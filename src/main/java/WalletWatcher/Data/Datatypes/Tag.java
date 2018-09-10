package WalletWatcher.Data.Datatypes;

import java.io.Serializable;
import java.util.HashMap;

public class Tag implements Serializable {
    private static int globalTag_id = 1;
    private static HashMap<String, Tag> tags = new HashMap();
    private int tag_id;
    private String tag;
    private String description;

    public static Tag getInstance(String tag) {
        return getInstance(tag, "Keine Bescheibung");
    }

    public static Tag getInstance(String tag, String description) {
        if (tags.containsKey(tag)) {
            return tags.get(tag);
        } else {
            Tag tmp = new Tag(tag, description);
            tags.put(tag, tmp);
            return tmp;
        }
    }

    private Tag(String tag, String description) {
        this.tag_id = globalTag_id++;
        this.tag = tag;
        this.description = description;
    }

    public int getTag_id() {
        return this.tag_id;
    }

    public String getTag() {
        return this.tag;
    }

    public String getDescription() {
        return this.description;
    }
}
