package net.gomblotto;

import lombok.Getter;
import lombok.Setter;
import net.gomblotto.commands.AbstractCommand;
import net.gomblotto.commands.StatsCommand;
import net.gomblotto.configs.ConfigManager;
import net.gomblotto.database.AutoSave;
import net.gomblotto.database.PlayerData;
import net.gomblotto.listeners.InventoryListener;
import net.gomblotto.listeners.DeathEvent;
import net.gomblotto.listeners.JoinEvent;
import net.gomblotto.players.StatsPlayer;
import net.gomblotto.players.StatsPlayerManager;
import net.gomblotto.top.TopManager;
import net.gomblotto.utils.StatsPlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.stream.Collectors;

public class StatsCore extends JavaPlugin {
    @Getter private static StatsCore instance;
    @Getter private PlayerData playerData;
    @Getter private ConfigManager configManager;
    @Getter private StatsPlayerManager statsPlayerManager;
    @Getter private TopManager topManager;
    @Setter private int task;
    @Setter @Getter private boolean isSaving;
    @Override
    public void onEnable() {
        instance = this;
        saveResource("config.yml", false);
        registerListerners(new DeathEvent(), new JoinEvent(), new InventoryListener());
        (this.configManager = new ConfigManager()).loadConfig();
        this.statsPlayerManager = new StatsPlayerManager();
        loadDB();
        new StatsCommand().register();
        topManager = new TopManager();
        setTask(new AutoSave().runTaskTimerAsynchronously(this, 20L*60L, 20L*60L).getTaskId());

    }


    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTask(task);

        for(StatsPlayer statsPlayer : statsPlayerManager.getStatsPlayers()) {
            if(statsPlayer.isLogged()) {
                playerData.saveAll(statsPlayer.getUuid().toString(), statsPlayer.getKills(), statsPlayer.getDeaths(), statsPlayer.getMaxKS());
                getLogger().info("Saving the UUID ".concat(statsPlayer.getUuid().toString()));
            }

            }

        playerData.closeConnection();

    }






    public void registerListerners(Listener... listeners){
        for(Listener listener : listeners){
            getServer().getPluginManager().registerEvents(listener,this);
        }
    }


    private void loadDB(){
        try {
            (this.playerData= new PlayerData(this.configManager.getStatsSQL().getFile().getAbsolutePath())).openConnection();
            this.playerData.createNewTable();
            playerData.loadData();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
