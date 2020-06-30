package net.gomblotto.top;

import lombok.Getter;
import net.gomblotto.StatsCore;

public class TopManager {
    @Getter private final TopKillsGui topKillsGui;
    @Getter private final TopKillsCalc topKillsCalc;
    public TopManager(){
        topKillsCalc = new TopKillsCalc();
        topKillsCalc.runTaskTimerAsynchronously(StatsCore.getInstance(), 0L,  StatsCore.getInstance().getConfig().getInt("update-top-sec")*20L);
        this.topKillsGui = new TopKillsGui();

    }
}
