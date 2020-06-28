package net.gomblotto.listeners;

import net.gomblotto.StatsCore;
import net.gomblotto.utils.MessageUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {
    @EventHandler
    public void onInventory(InventoryClickEvent e){
        if(e.getInventory().getTitle().contains(MessageUtils.color(StatsCore.getInstance().getConfig().getString("gui-name")))){
            Player p = (Player) e.getWhoClicked();
            int currentpage = Integer.parseInt(e.getInventory().getTitle().replace(MessageUtils.color(StatsCore.getInstance().getConfig().getString("gui-name")), ""));
            e.setCancelled(true);
            if(e.getCurrentItem() != null){
                if(e.getSlot() == 48){
                    StatsCore.getInstance().getTopManager().getTopKillsGui().prevPage(p, currentpage);
                }else if(e.getSlot() == 50){
                    StatsCore.getInstance().getTopManager().getTopKillsGui().nextPage(p, currentpage);
                }
            }
        }
    }
}
