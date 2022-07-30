package me.bruce.bhcf.factions.commands;

import me.bruce.bhcf.HCF;
import me.bruce.bhcf.factions.Faction;
import me.bruce.bhcf.utils.BukkitUtils;
import me.bruce.bhcf.utils.commands.annotations.BSubCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@BSubCommand(main = FactionCommand.class, name = "who")
public class FactionWhoCommand {

    public void execute(Player player, String[] args) {
        if (HCF.getINSTANCE().getFactionManager().getPlayerFaction(player) != null) {
            Faction faction = HCF.getINSTANCE().getFactionManager().getPlayerFaction(player);
            player.sendMessage(ChatColor.GRAY + BukkitUtils.STRAIGHT_LINE_DEFAULT);
            player.sendMessage(ChatColor.GRAY + " » " + ChatColor.BLUE  + faction.getName());
            player.sendMessage(ChatColor.GRAY + BukkitUtils.STRAIGHT_LINE_DEFAULT);
            player.sendMessage(ChatColor.YELLOW + "Leader: " + ChatColor.BLUE + player.getName());
            player.sendMessage(ChatColor.GRAY + BukkitUtils.STRAIGHT_LINE_DEFAULT);
        }
    }
}
