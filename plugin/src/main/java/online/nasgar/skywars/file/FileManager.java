package online.nasgar.skywars.file;

import me.yushust.inject.Binder;
import me.yushust.inject.Module;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FileManager {

    private final Map<String, FileHandler> files = new HashMap<>();

    public FileManager create(String name, FileHandler file){
        files.put(name, file);
        return this;
    }

    public Optional<FileHandler> get(String name){
        return Optional.ofNullable(files.get(name));
    }

    public Module build(){
        return binder -> files.forEach((name, file) -> binder.bind(FileHandler.class).named(name).toInstance(file));
    }
}
