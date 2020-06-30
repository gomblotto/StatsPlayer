package net.gomblotto.players;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.UUID;

public class StatsPlayer  {
    @Getter @Setter private int consecutiveKills;
    @Getter @Setter private int maxKS;
    @Getter @Setter private int topPosition;
    @Getter @Setter private int kills;
    @Getter @Setter private int deaths;
    @Getter private final UUID uuid;
    @Getter @Setter private boolean isLogged;

    public StatsPlayer(UUID uuid, boolean isLogged){
        this.uuid = uuid;
        this.isLogged = isLogged;
    }


}
