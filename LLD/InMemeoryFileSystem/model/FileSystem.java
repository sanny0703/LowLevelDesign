package InMemeoryFileSystem.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileSystem {
    private final Directory root;

    public FileSystem() {
        root = new Directory("/");
    }

    private List<String> ls(Directory directory) {
        List<String> list = new ArrayList<>();
        if (directory.files.size() != 0) {
            list.addAll(directory.files.keySet());
        }
        if (directory.directories.size() != 0) {
            list.addAll(directory.directories.keySet());
        }
        Collections.sort(list);
        return list;
    }

    public List<String> ls(String path) {
        String[] paths = path.split("/");
        Directory directory = root;
        for (int i = 1; i < paths.length; i++) {
            if (directory.directories.containsKey(paths[i])) {
                directory = directory.directories.get(paths[i]);
            } else {
                List<String> list = new ArrayList<>();
                list.add(paths[paths.length - 1]);
                return list;
            }
        }
        return ls(directory);
    }

    public void mkdir(String path) {
        String[] paths = path.split("/");
        Directory directory = root;
        for (int i = 1; i < paths.length; i++) {
            if (!directory.directories.containsKey(paths[i])) {
                Directory dir = new Directory(paths[i]);
                directory.directories.put(paths[i], dir);
            }
            directory = directory.directories.get(paths[i]);
        }
    }

    public void addContentToFile(String path, String content) {
        String[] paths = path.split("/");
        Directory directory = root;
        for (int i = 1; i < paths.length - 1; i++) {
            directory = directory.directories.get(paths[i]);
        }
        if (!directory.files.containsKey(paths[paths.length - 1]))
            directory.files.put(paths[paths.length - 1], new File(paths[paths.length - 1], content));
        else{
            File file = directory.files.get(paths[paths.length-1]);
            file.setContent(file.getContent()+content);
        }

    }

    public String readContentFromFile(String path) {
        String[] paths = path.split("/");
        Directory directory = root;
        for (int i = 1; i < paths.length - 1; i++) {
            directory = directory.directories.get(paths[i]);
        }
        return directory.files.get(paths[paths.length - 1]).getContent();
    }
}
