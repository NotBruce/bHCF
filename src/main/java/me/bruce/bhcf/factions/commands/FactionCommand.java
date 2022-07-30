package me.bruce.bhcf.factions.commands;

import me.bruce.bhcf.utils.BukkitUtils;
import me.bruce.bhcf.utils.commands.annotations.BCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@BCommand(name = "faction", aliases = {"f", "team"}, playerOnly = true)
public class FactionCommand {


    public void execute(Player player, String[] args) {
        player.sendMessage(ChatColor.GRAY + BukkitUtils.STRAIGHT_LINE_DEFAULT);
        player.sendMessage(ChatColor.GRAY + " » " + ChatColor.YELLOW + ChatColor.BOLD.toString() + "Faction Help");
        player.sendMessage(ChatColor.GRAY + BukkitUtils.STRAIGHT_LINE_DEFAULT);
    }
}
