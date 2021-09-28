package online.nasgar.skywars.file;

import online.nasgar.skywars.utils.Utils;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileHandler extends YamlConfiguration {

    private final String name;
    private final Plugin plugin;
    private final File file;

    public FileHandler(Plugin plugin, String name, String type, File folder){
        this.plugin = plugin;
        this.name = name + (name.endsWith(type) ? "" : type);
        this.file = new File(folder, this.name);
        this.createFile();
    }

    public FileHandler(Plugin plugin, String fileName){
        this(plugin, fileName, ".yml");
    }

    public FileHandler(Plugin plugin, String fileName, String fileExtension){
        this(plugin, fileName, fileExtension, plugin.getDataFolder());
    }

    public void createFile() {
        try {
            if (!file.exists()) {
                if (this.plugin.getResource(this.name) != null) {
                    this.plugin.saveResource(this.name, false);
                } else {
                    this.save(file);
                }
                this.load(file);
                return;
            }
            this.load(file);

            this.save(file);
        } catch (InvalidConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            this.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getString(String path) {
        String string = super.getString(path);
        return string != null ? Utils.ct(string) : path;
    }

    @Override
    public String getString(String path, String def) {
        String s = super.getString(path, def);

        return s != null ? Utils.ct(s) : def;
    }

    @Override
    public List<String> getStringList(String path) {
        List<String> list = super.getStringList(path);

        list.replaceAll(Utils::ct);

        return list;
    }

}
