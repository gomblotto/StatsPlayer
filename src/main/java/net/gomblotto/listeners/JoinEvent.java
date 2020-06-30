package net.gomblotto.listeners;

import net.gomblotto.StatsCore;
import net.gomblotto.players.StatsPlayer;
import net.gomblotto.utils.StatsPlayerUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class JoinEvent  implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if(StatsPlayerUtil.getStatsPlayer(e.getPlayer()) == null){
            StatsCore.getInstance().getStatsPlayerManager().addStatsPlayer(new StatsPlayer(e.getPlayer().getUniqueId(), true));
        }else{
            StatsPlayerUtil.getStatsPlayer(e.getPlayer()).setLogged(true);
        }
    }
}
