package net.gomblotto.top;

import lombok.Getter;
import net.gomblotto.StatsCore;

public class TopManager {
    @Getter private final TopKillsGui topKillsGui;
    @Getter private final TopKillsCalc topKillsCalc;
    public TopManager(){
        this.topKillsGui = new TopKillsGui();
        topKillsCalc = new TopKillsCalc();
        topKillsCalc.runTaskTimerAsynchronously(StatsCore.getInstance(), 1L,  StatsCore.getInstance().getConfig().getInt("update-top-sec")*20L);

    }
}
