package net.gomblotto.listeners;

import net.gomblotto.StatsCore;
import net.gomblotto.players.StatsPlayer;
import net.gomblotto.utils.MessageUtils;
import net.gomblotto.utils.StatsPlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvent implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        if(e.getEntity().getKiller() != null) {
           StatsPlayer killer = StatsPlayerUtil.getStatsPlayer(e.getEntity().getKiller());
           StatsPlayer victim = StatsPlayerUtil.getStatsPlayer(e.getEntity());
            if(killer != null && victim !=null) {
                killer.setKills(killer.getKills() + 1);
                victim.setDeaths(victim.getDeaths() + 1);
                victim.setConsecutiveKills(0);
                killer.setConsecutiveKills(killer.getConsecutiveKills() + 1);
                if (killer.getConsecutiveKills() > killer.getMaxKS()) killer.setMaxKS(killer.getConsecutiveKills());
                if(StatsCore.getInstance().getStatsPlayerManager().isAutoRespawn()) Bukkit.getPlayer(victim.getName()).spigot().respawn();
                e.setDeathMessage(MessageUtils.color(StatsCore.getInstance().getConfigManager().getMessagesConfig().getYamlConfiguration().getString("death_message").replace("%killer_name%", killer.getName()).replace("%death_name%", victim.getName())));
                StatsCore.getInstance().getStatsPlayerManager().activeEffectKS(killer);
            }
        }
    }

}
