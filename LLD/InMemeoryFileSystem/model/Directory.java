package InMemeoryFileSystem.model;

import java.util.HashMap;
import java.util.Map;

public class Directory {
    private String name;
    public Map<String,File> files;
    public Map<String,Directory> directories;

    public Directory(String name) {
        this.name = name;
        files = new HashMap<>();
        directories = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
