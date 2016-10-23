package net.wddqing.bwselector.command;

import net.wddqing.bwselector.Selector;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Administrator on 2016/10/21.
 */
public class SelectorCommandExecutor implements CommandExecutor {

    private Selector selector;

    public SelectorCommandExecutor(Selector selector) {
        this.selector = selector;
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        selector.getLogger().info("selector command====================");
        if (sender instanceof Player && args.length > 0) {
            selector.getMenu("menu").open((Player) sender);
            return true;
        }

        return false;
    }
}
