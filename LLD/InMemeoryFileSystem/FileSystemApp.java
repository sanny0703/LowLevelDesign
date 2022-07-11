package InMemeoryFileSystem;

import InMemeoryFileSystem.model.FileSystem;

public class FileSystemApp {
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        fileSystem.mkdir("/sanjay");
        System.out.println(fileSystem.ls("/"));
        fileSystem.addContentToFile("/sanjay/file1","Hi file1 here");
        fileSystem.addContentToFile("/sanjay/file2","Hi file2 here");
        fileSystem.addContentToFile("/sanjay/file3","Hi file3 here");
        System.out.println(fileSystem.ls("/sanjay"));
        System.out.println(fileSystem.readContentFromFile("/sanjay/file1"));
    }
}
