package net.gomblotto.players;

import lombok.Getter;
import net.gomblotto.StatsCore;
import net.gomblotto.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StatsPlayerManager {
    @Getter private final List<StatsPlayer> statsPlayers = new ArrayList<>();
    @Getter private final int minConsecutiveKills;
    @Getter private final boolean autoRespawn;
    @Getter private final List<StatsPlayer> topPlayers = new ArrayList<>();

    public StatsPlayerManager(){
        this.minConsecutiveKills = StatsCore.getInstance().getConfig().getInt("minium-kills-for-killstreak");
        this.autoRespawn = StatsCore.getInstance().getConfig().getBoolean("auto-respawn");
    }

    public void addStatsPlayer(StatsPlayer statsPlayer){
        statsPlayers.add(statsPlayer);
    }

    public void removeStatsPlayer(StatsPlayer statsPlayer){
        statsPlayers.remove(statsPlayer);
    }

    public void activeEffectKS(StatsPlayer statsPlayer) {
        ConfigurationSection ks_sec = StatsCore.getInstance().getConfig().getConfigurationSection("on-killstreak");
        ConfigurationSection everyks = StatsCore.getInstance().getConfig().getConfigurationSection("every-each-totkill");

        if (ks_sec != null) {
            for (String string: ks_sec.getKeys(false)) {
                int kills = Integer.parseInt(string);
                if (statsPlayer.getConsecutiveKills() == kills) {
                    desarializeSection(statsPlayer, ks_sec,string);
                }
            }
        }
        if(everyks != null) {
            if (StatsCore.getInstance().getConfig().getConfigurationSection("every-each-totkill") != null) {
                for (String string : everyks.getKeys(false)) {
                    int kills = Integer.parseInt(string);
                    if ((statsPlayer.getConsecutiveKills() % kills) == 0) {
                        desarializeSection(statsPlayer, everyks, string);
                        Bukkit.getOnlinePlayers().forEach(i -> i.sendMessage(MessageUtils.color(StatsCore.getInstance().getConfigManager().getMessagesConfig().getYamlConfiguration().getString("killstreak_message").replace("%killer_name%", statsPlayer.getName()).replace("%player_actual_ks%", String.valueOf(statsPlayer.getConsecutiveKills())))));
                    }
                }
            }
        }
    }



    private void desarializeSection(StatsPlayer player, ConfigurationSection section, String string){
        for(String s : section.getConfigurationSection(string).getStringList("add-effects")) {
            String[] strings = s.split(":");
            Bukkit.getPlayer(player.getName()).removePotionEffect(PotionEffectType.getByName(strings[0]));
            Bukkit.getPlayer(player.getName()).addPotionEffect(new PotionEffect(Objects.requireNonNull(PotionEffectType.getByName(strings[0])), Integer.parseInt(strings[2]) * 20, (Integer.parseInt(strings[1]) - 1)));
        }
        for(String s : section.getConfigurationSection(string).getStringList("commands")){
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), s.replace("%player%", player.getName()));
        }
    }
}
