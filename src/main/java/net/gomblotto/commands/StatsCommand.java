package net.gomblotto.commands;

import net.gomblotto.StatsCore;
import net.gomblotto.players.StatsPlayer;
import net.gomblotto.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.swing.*;

public class StatsCommand extends AbstractCommand {
    public StatsCommand() {
        super("statsplayer", "statsplayer.stats", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                MessageUtils.sendMultipleLine(p, StatsCore.getInstance().getConfigManager().getMessagesConfig().getYamlConfiguration().getStringList("statsplayer-view"));
                return;
            }
            if (p.hasPermission("statsplayer.stats")) {
                if (args[0].equalsIgnoreCase("gui")) {
                    StatsCore.getInstance().getTopManager().getTopKillsGui().openInventory(p);
                    return;
                }
            }

            if (p.hasPermission("statsplayer.reload")) {
                if (args[0].equalsIgnoreCase("reload")) {
                    StatsCore.getInstance().getConfigManager().reloadConfig();
                    StatsCore.getInstance().reloadConfig();
                    p.sendMessage("§aReloaded configs!");
                    return;
                }
            }
            p.sendMessage("§cInvalid argument");

        }
    }

    @Override
    public void register() {
        StatsCore.getInstance().getCommand(getCommandName()).setExecutor(this);
    }
}
