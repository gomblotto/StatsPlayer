package net.gomblotto.database;

import net.gomblotto.StatsCore;
import net.gomblotto.utils.StatsPlayerUtil;
import org.bukkit.scheduler.BukkitRunnable;

public class AutoSave extends BukkitRunnable {
    @Override
    public void run() {
        if(!StatsCore.getInstance().isSaving()) StatsPlayerUtil.saveData();
    }
}
