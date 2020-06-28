package net.gomblotto.utils;

import net.gomblotto.StatsCore;
import net.gomblotto.players.StatsPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class StatsPlayerUtil {
    public static StatsPlayer getStatsPlayer(Player player){
        for(StatsPlayer statsPlayer : StatsCore.getInstance().getStatsPlayerManager().getStatsPlayers()){
            if(statsPlayer.getName().equals(player.getName())) return statsPlayer;
        }
        return null;
    }
    public static void saveData(){
        for(StatsPlayer statsPlayer : StatsCore.getInstance().getStatsPlayerManager().getStatsPlayers()) {
            StatsCore.getInstance().getPlayerData().saveAll(statsPlayer.getName(), statsPlayer.getKills(), statsPlayer.getDeaths(), statsPlayer.getMaxKS());
        }
    }

}
