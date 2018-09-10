package WalletWatcher.Data.Datatypes;

import java.io.Serializable;
import java.util.HashSet;

public class Client implements Serializable {
    private String name;
    private HashSet<Tag> tags;

    public Client(String name) {
        this.name = name;
        this.tags = new HashSet();
    }

    public boolean setTag(Tag tag) {
        return this.tags.add(tag);
    }

    public boolean hasTag(Tag tag) {
        return this.tags.contains(tag);
    }

    @Override
    public String toString() {
        return name;
    }
}
