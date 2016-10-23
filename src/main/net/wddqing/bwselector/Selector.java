package net.wddqing.bwselector;

import net.wddqing.bwselector.command.SelectorCommandExecutor;
import net.wddqing.bwselector.config.NodeConfig;
import net.wddqing.bwselector.config.yaml.PluginConfig;
import net.wddqing.bwselector.entity.Icon;
import net.wddqing.bwselector.entity.IconMenu;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/10/21.
 */
public final class Selector extends JavaPlugin {
    private static Selector instance;
    private NodeConfig nodeConfig;

    public HashMap<String, IconMenu> getMenus() {
        return menus;
    }

    public IconMenu getMenu(String key) {
        return menus.get(key);
    }

    private HashMap<String, IconMenu> menus;

    @Override
    public void onEnable() {

        if (instance != null) {
            getLogger().warning("Please do not use /reload or plugin reloaders. Do \"cc reload\" instead");
            return;
        }
        instance = this;

        //配置文件初始化
        this.saveDefaultConfig();
        try {
            nodeConfig = new NodeConfig(this, new PluginConfig(this, "node.yml"));
            nodeConfig.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //初始化menu文件
        File menuFolder = new File(getDataFolder(), "menu");
        if (!menuFolder.isDirectory()) {
            try {
                menuFolder.mkdirs();
                this.saveResource("menu" + File.separator + "example.yml", false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        List<PluginConfig> menusList = loadMenus(menuFolder);
        menus = new HashMap<String, IconMenu>();
        for (PluginConfig menuConfig : menusList) {
            try {
                menuConfig.load();
            } catch (InvalidConfigurationException e) {
                e.printStackTrace();
                continue;
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            IconMenu menu = new IconMenu(menuConfig.getString("menu-settings.name"), menuConfig.getInt("menu-settings.rows"));
            for (String key : menuConfig.getKeys(false)) {
                if (key.equals("menu-settings")) {
                    continue;
                }
                getLogger().info("key:" + key);
                menu.setIcon(menuConfig.getInt(key + ".position-x"), menuConfig.getInt(key + ".position-y"), new Icon(
                        menuConfig.getString(key + ".command"),
                        menuConfig.getInt(key + ".id"),
                        menuConfig.getString(key + ".item"),
                        menuConfig.getString(key + ".lore"),
                        menuConfig.getInt(key + ".amount")
                ));
            }
            menus.put(menuConfig.getString("menu-settings.symbol"), menu);
        }


        //register cmd
        this.getCommand("selector").setExecutor(new SelectorCommandExecutor(this));
    }

    @Override
    public void onDisable() {

    }

    private List<PluginConfig> loadMenus(File file) {
        List<PluginConfig> list = new ArrayList<PluginConfig>();
        if (file.isDirectory()) {
            for (File subFile : file.listFiles()) {
                list.addAll(loadMenus(subFile));
            }
        } else if (file.isFile() && file.getName().endsWith(".yml")) {
            list.add(new PluginConfig(this, file));
        }

        return list;
    }
}
