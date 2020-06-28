package net.gomblotto.players;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

public class StatsPlayer  {
    @Getter @Setter private int consecutiveKills;
    @Getter @Setter private int maxKS;
    @Getter @Setter private int topPosition;
    @Getter @Setter private int kills;
    @Getter @Setter private int deaths;
    private int ks;
    @Getter private final String name;

    public StatsPlayer(String name){
        this.name = name;
    }

    public int getKd() {
        if(getDeaths() == 0) return getKills();
        return getKills()/getDeaths();
    }
}
