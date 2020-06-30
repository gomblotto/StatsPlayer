package net.gomblotto.utils;

import net.gomblotto.players.StatsPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class MessageUtils {
    public static void sendMultipleLine(Player p, List<String> stringList) {
        for (String string : stringList) {
            p.sendMessage(replacer(StatsPlayerUtil.getStatsPlayer(p), color(string)));

        }
    }


    public static String replacer(StatsPlayer p, String s){
        return s.replace("%player_name%", Bukkit.getOfflinePlayer(p.getUuid()).getName())
        .replace("%player_kills%", String.valueOf(p.getKills()))
        .replace("%player_deaths%" , String.valueOf(p.getDeaths()))
        .replace("%player_actual_ks%" , String.valueOf(p.getConsecutiveKills()))
        .replace("%player_max_ks%", String.valueOf(p.getMaxKS()))
        .replace("%player_top_position%", String.valueOf(p.getTopPosition()))
        .replace("%player_kd%", String.valueOf((double) p.getKills() /p.getDeaths()));
    }
    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
