package net.gomblotto.top;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.math.Stats;
import lombok.Getter;
import net.gomblotto.StatsCore;
import net.gomblotto.players.StatsPlayer;
import net.gomblotto.players.StatsPlayerManager;
import net.gomblotto.utils.ItemBuilder;
import net.gomblotto.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

public class TopKillsGui {
    private Inventory inventory;
    @Getter private final HashMap<Integer, Inventory> invs = new HashMap<>();
    public TopKillsGui() {
        this.inventory = Bukkit.createInventory(null, 54, MessageUtils.color(StatsCore.getInstance().getConfig().getString("gui-name")) + "1");
    }
    public void init(Player player) {
        int i = 0;
        int page = 1;
        inventory.setItem(50, new ItemBuilder(Material.PAPER, 1).setName("§aNext Page").toItemStack());
        inventory.setItem(48, new ItemBuilder(Material.PAPER, 1).setName("§cPrevious Page").toItemStack());
        for (StatsPlayer statsPlayer : StatsCore.getInstance().getTopManager().getTopKillsCalc().getOrderedKills().keySet()) {
            if (i == 45) {
                i = 0; page++;
                inventory = Bukkit.createInventory(null, 54, MessageUtils.color(StatsCore.getInstance().getConfig().getString("gui-name")) + page);
                inventory.setItem(50, new ItemBuilder(Material.PAPER, 1).setName("§aNext Page").toItemStack());
                inventory.setItem(48, new ItemBuilder(Material.PAPER, 1).setName("§cPrevious Page").toItemStack());
            }
            ItemStack skull = new ItemStack(Material.SKULL_ITEM, i, (short) 3);
            SkullMeta meta = (SkullMeta) skull.getItemMeta();
            List<String> stringList = new ArrayList<>();
            meta.setOwner(statsPlayer.getName());
            meta.setDisplayName(MessageUtils.color(MessageUtils.replacer(statsPlayer, StatsCore.getInstance().getConfig().getString("name-item-gui"))));
            for (String s : StatsCore.getInstance().getConfig().getStringList("lines-gui")) {
                s = MessageUtils.color(MessageUtils.replacer(statsPlayer, s));
                stringList.add(s);
            }
            meta.setLore(stringList);
            skull.setItemMeta(meta);
            inventory.addItem(skull);
            i++;
            invs.put(page, inventory);

            player.openInventory(invs.get(1));
        }
    }




    public void prevPage(Player player, int currentpage){
        player.closeInventory();
        if(invs.get(currentpage - 1) != null) {
            player.openInventory(invs.get(currentpage - 1));
        }else{
            player.sendMessage(MessageUtils.color(StatsCore.getInstance().getConfigManager().getMessagesConfig().getYamlConfiguration().getString("min_page_reached")));
        }
    }

    public void nextPage(Player player, int currentpage){
        player.closeInventory();
        if(invs.get(currentpage + 1) != null) {
            player.openInventory(invs.get(currentpage + 1));
        }else{
            player.sendMessage(MessageUtils.color(StatsCore.getInstance().getConfigManager().getMessagesConfig().getYamlConfiguration().getString("max_page_reached")));
        }
    }
    public void openInventory(Player player){
        inventory.clear();
        init(player);


     /*   new RefreshGui(){
            @Override
            public void run() {
                super.run();
                player.openInventory(invs.get(1));
            }
        }.runTaskAsynchronously(StatsCore.getInstance());*/
    }
}
