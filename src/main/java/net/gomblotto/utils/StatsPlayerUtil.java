package net.gomblotto.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.gomblotto.StatsCore;
import net.gomblotto.players.StatsPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

public class StatsPlayerUtil {
    public static StatsPlayer getStatsPlayer(Player player){
        for(StatsPlayer statsPlayer : StatsCore.getInstance().getStatsPlayerManager().getStatsPlayers()){
            if(statsPlayer.getUuid().equals(player.getUniqueId())) return statsPlayer;
        }
        return null;
    }
    public static void saveData(){
        StatsCore.getInstance().setSaving(true);
        for(StatsPlayer statsPlayer : StatsCore.getInstance().getStatsPlayerManager().getStatsPlayers()) {
            if(statsPlayer.isLogged()) {
                StatsCore.getInstance().getPlayerData().saveAll(statsPlayer.getUuid().toString(), statsPlayer.getKills(), statsPlayer.getDeaths(), statsPlayer.getMaxKS());
            }
        }
        StatsCore.getInstance().setSaving(false);

    }
}
