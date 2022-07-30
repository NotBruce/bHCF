package me.bruce.bhcf.listeners;

import me.bruce.bhcf.HCF;
import me.bruce.bhcf.factions.Faction;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
        for (Player player : Bukkit.getOnlinePlayers()) {
            String message = getFactionType(HCF.getINSTANCE().getFactionManager().getPlayerFaction(event.getPlayer()), event.getPlayer(), player, event.getMessage());
            player.sendMessage(message);
            Bukkit.getConsoleSender().sendMessage(message);
        }
    }

    public String getFactionType(Faction playerFaction, Player player, Player viewer, String message) {
        Faction faction = HCF.getINSTANCE().getFactionManager().getPlayerFaction(viewer);

        if (faction != null && playerFaction.getUuid() == faction.getUuid()) {
            return ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + faction.getName() + ChatColor.DARK_GRAY + "] "
                    + ChatColor.WHITE + player.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +
                    message;
        }

        if (faction != null && playerFaction.getUuid() != faction.getUuid()) {
            return ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + faction.getName() + ChatColor.DARK_GRAY + "] "
                    + ChatColor.WHITE + player.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +
                    message;
        }

        if (playerFaction == null) {
            return ChatColor.WHITE + player.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE + message;
        }

        return player.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE + message;
    }
}
