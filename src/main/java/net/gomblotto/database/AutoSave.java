package net.gomblotto.database;

import net.gomblotto.utils.StatsPlayerUtil;
import org.bukkit.scheduler.BukkitRunnable;

public class AutoSave extends BukkitRunnable {
    @Override
    public void run() {
        StatsPlayerUtil.saveData();
    }
}
