package net.wddqing.bwselector.entity;

import net.wddqing.bwselector.config.yaml.PluginConfig;
import org.apache.commons.lang.NullArgumentException;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Administrator on 2016/10/22.
 */
public class IconMenu {
    protected String title;
    protected Icon[] icons;

    public IconMenu(String title, int rows) {
        if (rows <= 0) {
            rows = 1;
        }
        this.title = title;
        this.icons = new Icon[rows * 9];
    }

    public void setIcon(int x, int y, Icon icon) {
        int slot = (y - 1) * 9 + (x - 1);
        this.setIconRaw(slot, icon);
    }

    public void setIconRaw(int slot, Icon icon) {
        if (slot >= 0 && slot < icons.length) {
            System.out.println("slot:" + slot);
            icons[slot] = icon;
        }
    }

    public Icon getIcon(int x, int y) {
        int slot = (y - 1) * 9 + (x - 1);
        return this.getIconRaw(slot);
    }

    public Icon getIconRaw(int slot) {
        if (slot >= 0 && slot < icons.length) {
            return icons[slot];
        }

        return null;
    }

    public int getRows() {
        return icons.length / 9;
    }

    public int getSize() {
        return icons.length;
    }

    public String getTitle() {
        return title;
    }

    public void open(Player player) {
        if (player == null) {
            throw new NullPointerException("empty player.");
        }

        Inventory inventory = Bukkit.createInventory(null, icons.length, title);
        System.out.println("icons " + icons.length);
        for (int i = 0; i < icons.length; i++) {
            if (icons[i] != null) {
                System.out.println("item:" + icons[i].getItem());
                System.out.println("material:" + Material.getMaterial(icons[i].getItem()));
                ItemStack itemStack = new ItemStack(Material.getMaterial(icons[i].getItem()), icons[i].getAmount());
               
                inventory.setItem(i, itemStack);
            }
        }

        player.openInventory(inventory);
    }
}
