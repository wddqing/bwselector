package net.wddqing.bwselector.config;

import net.wddqing.bwselector.Selector;
import net.wddqing.bwselector.config.yaml.PluginConfig;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.IOException;

/**
 * Created by Administrator on 2016/10/22.
 */
public class NodeConfig {

    private PluginConfig config;
    private Selector selector;
    private Node[] nodes;

    public NodeConfig(Selector selector, PluginConfig config) {
        this.selector = selector;
        this.config = config;


    }

    public Node getNode(int slot) {
        if (slot >= 0 && slot < nodes.length) {
            return nodes[slot];
        }

        return null;
    }

    public int getNodeLength() {
        return nodes.length;
    }

    public void load() {
        try {
            config.load();
            nodes = new Node[config.getKeys(false).size()];

            String[] keys = config.getKeys(false).toArray(new String[0]);
            for (int i = 0; i < nodes.length; i++) {
                nodes[i] = new Node(config.getString(keys[i] + ".name"), config.getString(keys[i] + ".addr"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
