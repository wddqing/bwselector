package net.wddqing.bwselector.config.yaml;

import net.wddqing.bwselector.Selector;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2016/10/21.
 */
public class PluginConfig extends YamlConfiguration {

    private File file;
    private Plugin plugin;

    public PluginConfig(Plugin plugin, String name) {
        this(plugin, new File(plugin.getDataFolder(), name));
    }

    public PluginConfig(Plugin plugin, File file) {
        super();
        this.file = file;
        this.plugin = plugin;
    }

    public void load() throws IOException, InvalidConfigurationException {
        if (!this.file.isFile()) {
            if (this.plugin.getResource(this.file.getName()) != null) {
                plugin.saveResource(this.file.getName(), false);
            } else {
                if (file.getParentFile() != null) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
        }

        // To reset all the values when loading.
        for (String section : this.getKeys(false)) {
            set(section, null);
        }
        load(file);
    }

    public void save() throws IOException {
        this.save(file);
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public String getFileName() {
        return file.getName();
    }


}
