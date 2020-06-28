package net.gomblotto.top;

import lombok.Getter;
import net.gomblotto.StatsCore;
import net.gomblotto.players.StatsPlayer;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TopKillsCalc extends BukkitRunnable {
    @Getter private final LinkedHashMap<StatsPlayer, Integer> orderedKills = new LinkedHashMap<>();

    @Override
    public void run() {
        HashMap<StatsPlayer, Integer> killsMap = new HashMap<>();
            for(StatsPlayer statsPlayer : StatsCore.getInstance().getStatsPlayerManager().getStatsPlayers()) {
                killsMap.put(statsPlayer, statsPlayer.getKills());
            }
            orderedKills.clear();
            killsMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(i -> orderedKills.put(i.getKey(), i.getValue()));
            int i = 0;
                for(StatsPlayer statsPlayer : orderedKills.keySet()) {
                    i++;
                    statsPlayer.setTopPosition(i);
                }
        }
}
