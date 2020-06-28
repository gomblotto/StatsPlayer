package net.gomblotto.commands;

import net.gomblotto.StatsCore;
import net.gomblotto.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class AbstractCommand implements CommandExecutor {
    private String commandName;
    private String permission;
    private boolean canConsoleUse;

    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (!cmd.getLabel().equalsIgnoreCase(this.commandName)) {
            return true;
        }
        if (!sender.hasPermission(this.permission)) {
            sender.sendMessage(MessageUtils.color(StatsCore.getInstance().getConfigManager().getMessagesConfig().getYamlConfiguration().getString("no_perm")));
            return true;
        }
        if (!this.canConsoleUse && !(sender instanceof Player)) {
            sender.sendMessage("Only players may use this command sorry!");
            return true;
        }
        this.execute(sender, args);
        return true;
    }

    public abstract void execute(CommandSender p0, String[] p1);

    public abstract void register();

    public AbstractCommand(String commandName, String permission, boolean canConsoleUse) {
        this.commandName = commandName;
        this.permission = permission;
        this.canConsoleUse = canConsoleUse;
    }

    public String getCommandName() {
        return this.commandName;
    }
}
